package control;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

import org.apache.commons.lang3.StringUtils;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import model.Addresses;
import model.AlertBox;
import model.Constants;
import model.Recipe;
import model.RecipeList;
import model.Ingredient;
import model.ReadData;

/**
 * Welcome Controller
 * @author Hoa Pham
 *
 */
public class WelcomeController implements Initializable {

	@FXML // fx:id="motherPane"
	private BorderPane motherPane;

	@FXML // fx:id="byName"
	private TextField byName;

	@FXML // fx:id="byIngredient"
	private TextField byIngredient;

	@FXML // fx:id="byCategory"
	private TextField byCategory;

	@FXML // fx:id="startRep"
	private Button startRep;

	@FXML // fx:id="findRep"
	private Button findRep;

	// search result
	private ArrayList<Recipe> result;

	// constant values
	private static Constants constants = new Constants();

	// minimum size of the window
	private static final int[] MIN_SIZES = constants.getMinSizes();

	/**
	 * user's accessing history
	 */
	private Addresses history = new Addresses();

	@Override
	public void initialize(URL url, ResourceBundle rs) {

		/**
		 * Start a new recipe
		 */
		startRep.setOnAction(new EventHandler<ActionEvent>() {
			@Override 
			public void handle(ActionEvent e) {
				try {
					String fxmlFileDir = constants.getEditDirectory();
					String cssFileDir = constants.getCssDirectory();
					FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFileDir));
					Parent root = loader.load();
					URL location = new URL(loader.getLocation().toString());

					EditController controller = loader.getController();
					controller.initData(new Recipe());
					controller.initialize(location, loader.getResources());

					Scene editWindow = new Scene(root, MIN_SIZES[0], MIN_SIZES[1]);
					editWindow.getStylesheets().add(getClass().getResource(cssFileDir).toExternalForm());
					Stage originalStage = (Stage) motherPane.getScene().getWindow();

					originalStage.setTitle("New Recipe - Edit Mode");
					originalStage.setScene(editWindow);
					originalStage.show();

					// center the stage
					Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
					originalStage.setX((primScreenBounds.getWidth() - originalStage.getWidth()) / 2);
					originalStage.setY((primScreenBounds.getHeight() - originalStage.getHeight()) / 2);

				} catch (IOException ioe) {
					AlertBox.display("Warning", "File not found.");
				}
			}
		});

		/**
		 * find a recipe and enter read mode
		 */
		findRep.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				history = new Addresses();
				history.getBackward().push(constants.getWelcomeDirectory());

				/**
				 * Copies all recipes into RecipeList data,
				 * then removes all recipes from data that do not contain the search criteria. 
				 */
				RecipeList data = ReadData.readRecipes();
				int tName = 0, tIng = 0, tCat = 0;
				try {
					for (int i = 0; i < data.getRecipes().size(); i++) 
					{
						int fName = 1, fIng = 1, fCat = 1;
						if (!byName.getText().isEmpty() && !byName.getText().equals(null) && !StringUtils.isBlank(byName.getText()))
						{
							if (data.getRecipes().get(i).getName().toLowerCase().contains(byName.getText().toLowerCase()))	
								fName = 0;
							tName++;
						}
						else {
							fName = -1;
						}
						if (!byIngredient.getText().isEmpty() && !byIngredient.getText().equals(null) && !StringUtils.isBlank(byIngredient.getText())) {
							for (int j = 0; j < data.getRecipes().get(i).getIngredients().size(); j++)
								if (data.getRecipes().get(i).getIngredients().get(j).getName().toLowerCase().contains(byIngredient.getText().toLowerCase())){
									fIng = 0;
									tIng++;
									break;
								}
						}
						else {
							fIng = -1;
						}
						if (!byCategory.getText().isEmpty() && !byCategory.getText().equals(null) && !StringUtils.isBlank(byCategory.getText())) {
							for (int j = 0; j < data.getRecipes().get(i).getCategories().size(); j++) {
								if (data.getRecipes().get(i).getCategories().get(j).toLowerCase().contains(byCategory.getText().toLowerCase())){
									fCat = 0;
									tCat++;
									break;
								}
							}
						}
						else {
							fCat = -1;
						}
						if ((fName == 1 && fIng != 0 && fCat != 0) || (fName != 0 && fIng == 1 && fCat != 0) || (fName != 0 && fIng != 0 && fCat == 1)
								|| (fName == 0 && (fIng == 1 || fCat == 1)) || (fIng == 0 && (fName == 1 || fCat == 1)) || (fCat == 0 && (fIng == 1 || fName == 1))){
							data.getRecipes().remove(i);
							i--;
						}
					}

					if (tName == 0) 
					{
						//Error handle for no Name results
					}
					if (tCat == 0) 
					{
						//Error handle for no Category results
					}
					if (tIng == 0) {
						//Error handle for no Ingredient results
					}

					// switch to recipeList view
					String fxmlFileDir = constants.getSearchDirectory();
					FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFileDir));
					Parent root = loader.load();
					URL location = new URL(loader.getLocation().toString());

					SearchInterfaceController controller = loader.getController();
					controller.setHistory(history);
					controller.initData(result);
					controller.initialize(location, loader.getResources());

					Scene recipeListView = new Scene(root, MIN_SIZES[0], MIN_SIZES[1]);
					Stage originalStage = (Stage) motherPane.getScene().getWindow();
					originalStage.setTitle("Recipe Keeper");
					originalStage.setScene(recipeListView);
					originalStage.show();

					// center the stage
					Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
					originalStage.setX((primScreenBounds.getWidth() - originalStage.getWidth()) / 2);
					originalStage.setY((primScreenBounds.getHeight() - originalStage.getHeight()) / 2);

				}
				catch (NullPointerException e) {
					//RecipeList data = ReadData.readRecipes();
					result = data.getRecipeList();

					try {
						// switch to recipeList view
						String fxmlFileDir = constants.getSearchDirectory();
						FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFileDir));
						Parent root = loader.load();
						URL location = new URL(loader.getLocation().toString());

						SearchInterfaceController controller = loader.getController();
						controller.setHistory(history);
						controller.initData(result);
						controller.initialize(location, loader.getResources());

						Scene recipeListView = new Scene(root, MIN_SIZES[0], MIN_SIZES[1]);
						Stage originalStage = (Stage) motherPane.getScene().getWindow();
						originalStage.setTitle("Recipe Keeper");
						originalStage.setScene(recipeListView);
						originalStage.show();

						// center the stage
						Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
						originalStage.setX((primScreenBounds.getWidth() - originalStage.getWidth()) / 2);
						originalStage.setY((primScreenBounds.getHeight() - originalStage.getHeight()) / 2);

					} catch (IOException io) {
						AlertBox.display("Warning", "File not found.");
						io.printStackTrace();
					}
				}
				catch (IOException ioe) {
					AlertBox.display("Warning", "File not found.");
					ioe.printStackTrace();
				}
			}
		});

	}



}
