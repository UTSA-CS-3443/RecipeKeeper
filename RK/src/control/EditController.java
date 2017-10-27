package control;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import model.Ingredient;
import model.Recipe;

public class EditController implements Initializable {

	@FXML // fx:id="motherPane"
	BorderPane motherPane = new BorderPane();

	@FXML // fx:id="recipeName"
	TextField recipeName = new TextField(); 

	@FXML // fx:id="ingreName"
	TextField ingreName = new TextField();

	@FXML // fx:id="ingreQty"
	TextField ingreQty = new TextField();

	@FXML // fx:id="textCategory"
	TextField textCategory = new TextField();

	@FXML // fx:id="servingSize"
	ComboBox<String> servingSize = new ComboBox<>();

	@FXML // fx:id="ingreUnit"
	ComboBox<String> ingreUnit = new ComboBox<>();

	@FXML // fx:id="addIngredient"
	Button addIngredient = new Button();					// "+" button for ingredients

	@FXML // fx:id="delIngredient"
	Button delIngredient = new Button();					// "-" button for ingredients

	@FXML // fx:id="addCategory"
	Button addCategory = new Button();					// "+" button for categories

	@FXML // fx:id="delCategory"
	Button delCategory = new Button();					// "-" button for categories

	@FXML // fx:id="ingredientsTable"
	TableView<Ingredient> ingredientsTable = new TableView<>();
	
	/**
	 * Read data from model below this block of comments
	 */
	Recipe recipe = new Recipe();
	
	// list of units
	private static final String[] UNITS = { "lb", "ml", "tps", "tbs" };
	
	/**
	 * temporary ingredient list
	 * will be added to part of a new recipe if the user wants to
	 */
	ObservableList<Ingredient> ingredients = FXCollections.observableArrayList();

	/**
	 * Quantity per servingSize: created when an ingredient is added
	 * used for changing servingSize. Ex: when user enter (chicken, 1, lb)
	 * qtyPerServingSize will add 1 
	 */
	List<Double> qtyPerServingSize = new ArrayList<Double>();

	@FXML // fx:id="categoryTable"
	ListView<String> categoryTable = new ListView<>();
	ObservableList<String> categories = FXCollections.observableArrayList();

	@Override	// Method is called by the FXMLLoader when initialization is complete
	public void initialize(URL fxmlFileLocation, ResourceBundle resources) {

		// add serving sizes
		servingSize.getItems().addAll("1","2","3");

		// Ingredient column
		TableColumn<Ingredient, String> ingredientColumn = new TableColumn<>("Ingredient");
		ingredientColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

		// Quantity column
		TableColumn<Ingredient, String> quantityColumn = new TableColumn<>("Quantity");
		quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));	

		// Quantity column
		TableColumn<Ingredient, String> unitColumn = new TableColumn<>("Unit");
		unitColumn.setCellValueFactory(new PropertyValueFactory<>("unit"));

		// Set ingredientsTable
		ingredientsTable.setItems(ingredients);
		ingredientsTable.getColumns().addAll(ingredientColumn, quantityColumn, unitColumn);

		// Initialize Category table
		categoryTable.setItems(categories);

		/**
		 * Listen for changes to the fruit comboBox selection
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
