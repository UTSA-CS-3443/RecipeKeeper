package control;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

import org.apache.commons.lang3.StringUtils;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.AlertBox;
import model.Recipe;
import model.RecipeList;
import model.Ingredient;

/**
 * Welcome Controller
 * @author Hoa Pham
 *
 */
public class WelcomeController implements Initializable {

	@FXML // fx:id="motherPane"
	private BorderPane motherPane = new BorderPane();

	@FXML // fx:id="byName"
	private TextField byName = new TextField();

	@FXML // fx:id="byIngredient"
	private TextField byIngredient = new TextField();

	@FXML // fx:id="byCategory"
	private TextField byCategory = new TextField();

	@FXML // fx:id="startRep"
	private Button startRep = new Button();

	@FXML // fx:id="findRep"
	private Button findRep = new Button();

	// search result
	private ArrayList<Recipe> result;

	// constant values
	private static Constants constants = new Constants();

	// minimum size of the window
	private static final int[] MIN_SIZES = constants.getMinSizes();

	@Override
	public void initialize(URL url, ResourceBundle rs) {

		/**
		 * Start a new recipe
		 */
		startRep.setOnAction( action -> {
			try {
				String fxmlFileDir = "/view/EditInterface.fxml";
				String cssFileDir = "/view/RecipeKeeper.css";
				Parent root = FXMLLoader.load(getClass().getResource(fxmlFileDir));
				Scene editWindow = new Scene(root, MIN_SIZES[0], MIN_SIZES[1]);
				editWindow.getStylesheets().add(getClass().getResource(cssFileDir).toExternalForm());
				Stage originalStage = (Stage) motherPane.getScene().getWindow();
				originalStage.setScene(editWindow);
			} catch (IOException ioe) {

			}
		});

		/**
		 * find a recipe and enter read mode
		 */
		findRep.setOnAction(action -> {
			/**
			 * Copies all recipes into RecipeList data,
			 * then removes all recipes from data that do not contain the search criteria. 
			 */
			RecipeList data = ReadData.readRecipes();
			try {
			for (int i = 0; i < data.getRecipes().size(); i++) 
			{
				int fName = 1, fIng = 1, fCat = 1;
				if (!byName.getText().isEmpty() && !byName.getText().equals(null) && !StringUtils.isBlank(byName.getText()))
				{
					if (data.getRecipes().get(i).getName().toLowerCase().contains(byName.getText().toLowerCase()))	
							fName = 0;
				}
				else {
					fName = -1;
				}
				if (!byIngredient.getText().isEmpty() && !byIngredient.getText().equals(null) && !StringUtils.isBlank(byIngredient.getText())) {
					for (int j = 0; j < data.getRecipes().get(i).getIngredients().size(); j++)
						if (data.getRecipes().get(i).getIngredients().get(j).getName().toLowerCase().contains(byIngredient.getText().toLowerCase())){
							fIng = 0;
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
			//for (int i = 0; i < data.getRecipes().size(); i++)
				//System.out.println(data.getRecipes().get(i).getName()); //Test Print
			
			/*
			try {
				// if all 3 fields empty, null, blank
				if ((byName.getText().isEmpty() || byName.getText().equals(null) || StringUtils.isBlank(byName.getText()))
						&& (byIngredient.getText().isEmpty() || byIngredient.getText().equals(null) || StringUtils.isBlank(byIngredient.getText())) 
						&& (byCategory.getText().isEmpty() || byCategory.getText().equals(null)) || StringUtils.isBlank(byCategory.getText())) 
					throw new NullPointerException();
				// search by only name
				else if ( !(byName.getText().isEmpty() || byName.getText().equals(null) || StringUtils.isBlank(byName.getText()))
						&& (byIngredient.getText().isEmpty() || byIngredient.getText().equals(null) || StringUtils.isBlank(byIngredient.getText())) 
						&& (byCategory.getText().isEmpty() || byCategory.getText().equals(null)) || StringUtils.isBlank(byCategory.getText())) {
					String neededName = byName.getText();
					RecipeList data = ReadData.readRecipes();
					result = data.getRecipeByName(neededName);
				}
				// search by only ingredient
				else if ( !(byIngredient.getText().isEmpty() || byIngredient.getText().equals(null) || StringUtils.isBlank(byIngredient.getText())) 
						&& (byName.getText().isEmpty() || byName.getText().equals(null) || StringUtils.isBlank(byName.getText())) 
						&& (byCategory.getText().isEmpty() || byCategory.getText().equals(null)) || StringUtils.isBlank(byCategory.getText()))  {
					String neededIngredient = byIngredient.getText();
					RecipeList data = ReadData.readRecipes();
					result = data.getRecipeByIngredients(neededIngredient);
				}
				// search by only category
				else if ( !(byCategory.getText().isEmpty() || byCategory.getText().equals(null)) || StringUtils.isBlank(byCategory.getText())
						&& (byName.getText().isEmpty() || byName.getText().equals(null) || StringUtils.isBlank(byName.getText()))
						&& (byIngredient.getText().isEmpty() || byIngredient.getText().equals(null) || StringUtils.isBlank(byIngredient.getText())) )  {
					String neededCategory = byCategory.getText();
					RecipeList data = ReadData.readRecipes();
					result = data.getRecipeByCategory(neededCategory);
				}
				// search by ingredient and category
				else if (   (byName.getText().isEmpty() || byName.getText().equals(null) || StringUtils.isBlank(byName.getText()))
						&& !(byIngredient.getText().isEmpty() || byIngredient.getText().equals(null) || StringUtils.isBlank(byIngredient.getText()))
						&& !(byCategory.getText().isEmpty() || byCategory.getText().equals(null)) || StringUtils.isBlank(byCategory.getText())) {
					String neededIngredient = byIngredient.getText();
					String needCategory = byCategory.getText();
					RecipeList data = ReadData.readRecipes();
					result = data.getRecipeByIngreCat(neededIngredient, needCategory);
				} 
				// search by name and category
				else if ( !(byName.getText().isEmpty() || byName.getText().equals(null) || StringUtils.isBlank(byName.getText()))
						&& (byIngredient.getText().isEmpty() || byIngredient.getText().equals(null) || StringUtils.isBlank(byIngredient.getText()))
						&& !(byCategory.getText().isEmpty() || byCategory.getText().equals(null)) || StringUtils.isBlank(byCategory.getText())) {
					String neededName = byName.getText();
					String neededCategory = byCategory.getText();
					RecipeList data = ReadData.readRecipes();
					result = data.getRecipeByNameCate(neededName, neededCategory);
				}
				// search by name and ingredient
				else if ( !(byName.getText().isEmpty() || byName.getText().equals(null) || StringUtils.isBlank(byName.getText()))
						&& !(byIngredient.getText().isEmpty() || byIngredient.getText().equals(null) || StringUtils.isBlank(byIngredient.getText()))
						&& (byCategory.getText().isEmpty() || byCategory.getText().equals(null)) || StringUtils.isBlank(byCategory.getText())) {
					String neededName = byName.getText();
					String neededIngredient = byIngredient.getText();
					RecipeList data = ReadData.readRecipes();
					result = data.getRecipeByNameIngre(neededName, neededIngredient);
				}
				// search by all 3 elements
				else {
					String neededName = byName.getText();
					String neededIngredient = byIngredient.getText();
					String neededCategory = byCategory.getText();
					RecipeList data = ReadData.readRecipes();
					result = data.getRecipeByAll(neededName, neededIngredient, neededCategory);
				}
				*/
				// switch to recipeList view
				String fxmlFileDir = "/view/SearchInterface.fxml";
				FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFileDir));
				Parent root = loader.load();
				URL location = new URL(loader.getLocation().toString());

				SearchInterfaceController controller = loader.getController();
				controller.initData(result);
				controller.initialize(location, loader.getResources());

				Scene recipeListView = new Scene(root, 300, 270);
				Stage originalStage = (Stage) motherPane.getScene().getWindow();
				originalStage.setTitle("Recipe Keeper");
				originalStage.setScene(recipeListView);
				originalStage.show();
			}
			catch (NullPointerException e) {
				//RecipeList data = ReadData.readRecipes();
				result = data.getRecipeList();

				try {
					// switch to recipeList view
					String fxmlFileDir = "/view/SearchInterface.fxml";
					FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFileDir));
					Parent root = loader.load();
					URL location = new URL(loader.getLocation().toString());

					SearchInterfaceController controller = loader.getController();
					controller.initData(result);
					controller.initialize(location, loader.getResources());

					Scene recipeListView = new Scene(root, 300, 270);
					Stage originalStage = (Stage) motherPane.getScene().getWindow();
					originalStage.setTitle("Recipe Keeper");
					originalStage.setScene(recipeListView);
					originalStage.show();
				} catch (IOException io) {
					AlertBox.display("Warning", "File not found.");
				}
			}
			catch (IOException ioe) {
				AlertBox.display("Warning", "File not found.");
			}
		});

	}



}
