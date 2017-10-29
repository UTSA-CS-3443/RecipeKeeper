package control;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import model.AlertBox;
import model.Ingredient;
import model.Recipe;

public class ReadController implements Initializable {
	
	@FXML // fx:id="motherPane"
	BorderPane motherPane = new BorderPane();
	
	@FXML // fx:id="recipeName"
	TextField recipeName = new TextField(); 
	
	@FXML // fx:id="instructions"
	TextArea instructions = new TextArea();

	@FXML // fx:id="servingSize"
	ComboBox<String> servingSize = new ComboBox<>();

	@FXML // fx:id="ingredientsTable"
	TableView<Ingredient> ingredientsTable = new TableView<>();
	
	@FXML // fx:id="categoryTable"
	ListView<String> categoryTable = new ListView<>();
	ObservableList<String> categories = FXCollections.observableArrayList();
	
	@FXML // fx:id="menuNew"
	MenuItem menuNew = new MenuItem();
	
	@FXML // fx:id="menuEdit"
	MenuItem menuEdit = new MenuItem();
	
	@FXML // fx:id="menuSave"
	MenuItem menuSave = new MenuItem();
	
	@FXML // fx:id="menuSaveAs"
	MenuItem menuSaveAs = new MenuItem();
	
	@FXML // fx:id="menuClose"
	MenuItem menuClose = new MenuItem();
	
	// Recipe chose from model
	Recipe recipe = new Recipe();
	
	// serving Sizes
	private static final String[] SERVSIZES = { "1", "2", "3" };
	
	public static final int[] MIN_SIZES = { 529,737 };

	@Override	// Method is called by the FXMLLoader when initialization is complete
	public void initialize(URL fxmlFileLocation, ResourceBundle resources) {

		//Disable recipeName and instruction fields
		recipeName.setEditable(false);
		instructions.setEditable(false);
		
		// add serving sizes
		servingSize.getItems().addAll(SERVSIZES);

		// menuNew listener
		menuNew.setOnAction(action -> {
			try {
				String fxmlFileDir = "/view/EditInterface.fxml";
				String cssFileDir = "/view/RecipeKeeper.css";
				Parent root = FXMLLoader.load(getClass().getResource(fxmlFileDir));
				Scene editWindow = new Scene(root, MIN_SIZES[0], MIN_SIZES[1]);
				editWindow.getStylesheets().add(getClass().getResource(cssFileDir).toExternalForm());
				Stage originalStage = (Stage) motherPane.getScene().getWindow();
				originalStage.setScene(editWindow);
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
		
		/**
		 * Second method for serving size listener
		 * Listen for changes to the serving size selection
		 * and update the displayed serving size
		 */
		servingSize.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> selected, String oldValue, String newValue) {
				servingSize.setValue(newValue); // Serving Size: newValue ????

				// do some more stuffs with ingredients
			}
		});
	}

}
