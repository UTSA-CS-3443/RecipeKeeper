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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import model.AlertBox;
import model.Ingredient;
import model.Recipe;
import model.RecipeList;

/**
 * Controller for Reading interface
 * @author Hoa Pham
 *
 */
public class ReadController implements Initializable {
	
	@FXML // fx:id="motherPane"
	private BorderPane motherPane;
	
	@FXML // fx:id="recipeName"
	private TextField recipeName; 
	
	@FXML // fx:id="instructions"
	private TextArea instructions;

	@FXML // fx:id="servingSize"
	private ComboBox<String> servingSize;

	@FXML // fx:id="ingredientsTable"
	private TableView<Ingredient> ingredientsTable;
	
	@FXML // fx:id="categoryTable"
	private ListView<String> categoryTable;
	private ObservableList<String> rCategories  = FXCollections.observableArrayList();
	
	@FXML // fx:id="menuNew"
	private MenuItem menuNew;					// New...			
	
	@FXML // fx:id="menuEdit"
	private MenuItem menuEdit;					// Edit
	
	@FXML // fx:id="menuSave"
	private MenuItem menuSave;					// Save
	
	@FXML // fx:id="menuSaveAs"
	private MenuItem menuSaveAs;					// Save As...
	
	@FXML // fx:id="menuClose"
	private MenuItem menuClose;					// Close app
	
	// Recipe chose from model
	private Recipe recipe = new Recipe();
	
	// ingredients from recipe for reading
	private ObservableList<Ingredient> rIngredients = FXCollections.observableArrayList();
	
	// constant values
	static Constants constants = new Constants();
	
	// serving Sizes
	private static final String[] SERVSIZES = constants.getServsizes();
	// minimum size of the window
	private static final int[] MIN_SIZES = constants.getMinSizes();
	
	/**
	 * 
	 */
	public ReadController() {
		super();
	}
	
	/**
	 * Constructor
	 * @param r
	 */
	public ReadController(Recipe r) {
		initData(r);
	}
	
	// Method is called by the FXMLLoader when initialization is complete
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		// Initialize and set editable to false
		recipeName.setText(recipe.getName());
		recipeName.setEditable(false);
		// disable instructions field
		instructions.setEditable(false);
		
		// add serving sizes
		servingSize.getItems().addAll(SERVSIZES);
		/**
		 * Second method for serving size listener
		 * Listen for changes to the serving size selection
		 * and update the displayed serving size
		 */
		servingSize.setOnAction( e -> {
			for (int i = 0; i < recipe.getIngredients().size(); i++) {
				double tempQty;
				tempQty = Double.parseDouble(servingSize.getValue()) * recipe.getIngredients().get(i).getQuantity();
				changeValueAt(i, 2, tempQty, ingredientsTable);
			}
		});

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
		for (Ingredient i : recipe.getIngredients()) {
			rIngredients.add(i);
		}
		ingredientsTable.setItems(rIngredients);
		ingredientsTable.getColumns().addAll(ingredientColumn, quantityColumn, unitColumn);
		
		// Initialize categoryTable
		for (String s : recipe.getCategories()) {
			rCategories.add(s);
		}
		categoryTable.setItems(rCategories);
		
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
				
			}
		});
		
	}	
	
	/**
	 * Initialize data, Recipe setter
	 * @param recipe
	 */
	public void initData(Recipe r) {
		this.recipe = r;
	}
	
	/**
	 * get Data, recipe getter
	 * @return the recipe is being read
	 */
	public Recipe getData() {
		return this.recipe;
	}
	
	/**
	 * Change value at specific location of TableView
	 * used for servingSize onAction
	 * @param row
	 * @param col
	 * @param value
	 * @param table
	 */
	private static void changeValueAt(int row, int col, double value, TableView<Ingredient> table) {
		Ingredient newData = table.getItems().get(row);
		newData.setQuantity(value);
		table.getItems().set(row, newData);
	}

}
