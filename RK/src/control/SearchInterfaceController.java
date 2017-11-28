package control;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import model.AlertBox;
import model.ConfirmBox;
import model.Constants;
import model.ReadData;
import model.Recipe;
import model.RecipeList;

/**
 * 
 * @author Hoa Pham
 *
 */
public class SearchInterfaceController implements Initializable {

	@FXML // fx:id="motherPane"
	private BorderPane motherPane;

	@FXML // fx:id="repList"
	private ListView<String> repList;

	@FXML // fx:id="open"
	private Button open;
	
	@FXML // fx:id="delRep"
	private Button delRep;
	
	@FXML // fx:id="backward"
	private Button backward;
	
	@FXML // fx:id="forward"
	private Button forward;

	// list of recipe names
	private ObservableList<String> recipeNames = FXCollections.observableArrayList();

	// Data passed from welcome screen
	private ArrayList<Recipe> readingData;

	// constant values
	private static Constants constants = new Constants();

	// minimum size of the window
	private static final int[] MIN_SIZES = constants.getMinSizes();
	
	// previous, forward data
	private String backwardAddress;
	private String forwardAddress;

	/**
	 * constructor
	 */
	public SearchInterfaceController() {
		super();
	}

	/**
	 * constructor
	 * @param result
	 */
	public SearchInterfaceController(ArrayList<Recipe> result) {
		initData(result);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		/**
		 * open a recipe
		 */
		open.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				int selectedIndex = repList.getSelectionModel().getSelectedIndex();
				if (selectedIndex >= 0) {
					try {
						String fxmlFileDir = "/view/ReadInterface.fxml";
						String cssFileDir = "/view/RecipeKeeper.css";
						FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFileDir));
						Parent root = loader.load();
						URL location = new URL(loader.getLocation().toString());
						
						ReadController controller = loader.getController();
						controller.initData(readingData.get(selectedIndex));
						controller.initialize(location, loader.getResources());
						
						Scene editWindow = new Scene(root, MIN_SIZES[0], MIN_SIZES[1]);
						editWindow.getStylesheets().add(getClass().getResource(cssFileDir).toExternalForm());
						Stage originalStage = (Stage) motherPane.getScene().getWindow();

						originalStage.setTitle(readingData.get(selectedIndex).getName() + " - View Mode");
						originalStage.setScene(editWindow);
						originalStage.show();
						
						// center the stage
						Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
						originalStage.setX((primScreenBounds.getWidth() - originalStage.getWidth()) / 2);
						originalStage.setY((primScreenBounds.getHeight() - originalStage.getHeight()) / 2);
						
					} catch (IOException ioe) {
						AlertBox.display("Warning", "File not found.");
					}
				} else {
					AlertBox.display("Warning", "No item selected");
				}
			}
			
		});
		
		/**
		 * Delete a recipe from model FOREVER. 
		 * Restoring by reset the closest commits 
		 * (read the commits carefully before reseting)
		 */
		delRep.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				try {
					if (recipeNames.size() < 1) throw new RuntimeException();
					
					int selectedIndex = repList.getSelectionModel().getSelectedIndex();
					if (selectedIndex >= 0) {
						boolean confirm = ConfirmBox.display("Notice", "Are you sure to delete this recipe?");
						if (confirm) {
							Recipe itemToBeRemoved = readingData.get(selectedIndex);
							RecipeList data = ReadData.readRecipes();
							data.rmvRecipe(itemToBeRemoved);
							repList.getItems().remove(selectedIndex);
						}
						else return;
					}
					else {
						AlertBox.display("Warning", "No Item Selected");
					}
				} catch (RuntimeException e) {
					AlertBox.display("Warning", "Recipe List is empty");
				}
			}
			
		});
		
		/**
		 * backward listener
		 */
		backward.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				try {
					String cssFileDir = "/view/RecipeKeeper.css";
					Parent root = FXMLLoader.load(getClass().getResource(backwardAddress));
					Scene homeWindow = new Scene(root, MIN_SIZES[0], MIN_SIZES[1]);
					homeWindow.getStylesheets().add(getClass().getResource(cssFileDir).toExternalForm());
					Stage originalStage = (Stage) motherPane.getScene().getWindow();

					originalStage.setTitle("Recipe Keeper");
					originalStage.setScene(homeWindow);
					originalStage.show();

					// center the stage
					Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
					originalStage.setX((primScreenBounds.getWidth() - originalStage.getWidth()) / 2);
					originalStage.setY((primScreenBounds.getHeight() - originalStage.getHeight()) / 2);

				} catch (IOException ioe) {
					AlertBox.display("Warning", "Oops! Something wrong happened.");
				}
			}
			
		});
		
		if (forwardAddress.equals("")) {
			forward.setDisable(true);
		}
		
	}

	/**
	 * Initialize data
	 * @param result
	 */
	public void initData(ArrayList<Recipe> result) {
		for (Recipe r : result) {
			recipeNames.add(r.getName());
		}
		this.readingData = result;
		// for 
		repList.setItems(recipeNames);
		repList.setStyle("-fx-background-color: #FFFFFF; -fx-accent: #ff6c00; -fx-focus-color: #ff6c00;");
		motherPane.setStyle("-fx-background-color: #FFFFFF");
		open.setStyle("-fx-background-color: #ff9900");
	}

	/**
	 * 
	 * @return
	 */
	public String getBackward() {
		return backwardAddress;
	}

	/**
	 * 
	 * @param previous
	 */
	public void setBackward(String previous) {
		this.backwardAddress = previous;
	}

	/**
	 * 
	 * @return
	 */
	public String getForward() {
		return forwardAddress;
	}

	/**
	 * 
	 * @param forward
	 */
	public void setForward(String forward) {
		this.forwardAddress = forward;
	}

}
