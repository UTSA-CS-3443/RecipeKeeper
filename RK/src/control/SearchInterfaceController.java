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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.AlertBox;
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

	// list of recipe names
	private ObservableList<String> recipeNames = FXCollections.observableArrayList();

	// Data passed from welcome screen
	private ArrayList<Recipe> readingData;

	// constant values
	static Constants constants = new Constants();

	// minimum size of the window
	private static final int[] MIN_SIZES = constants.getMinSizes();

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
					} catch (IOException ioe) {
						AlertBox.display("Warning", "File not found.");
					}
				} else {
					AlertBox.display("Warning", "No item selected");
				}
			}

		});

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
		
	}

}
