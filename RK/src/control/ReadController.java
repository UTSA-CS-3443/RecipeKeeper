package control;


import java.io.IOException;
import java.net.URL;
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
	MenuItem menuNew = new MenuItem();					// New...			
	
	@FXML // fx:id="menuEdit"
	MenuItem menuEdit = new MenuItem();					// Edit
	
	@FXML // fx:id="menuSave"
	MenuItem menuSave = new MenuItem();					// Save
	
	@FXML // fx:id="menuSaveAs"
	MenuItem menuSaveAs = new MenuItem();				// Save As...
	
	@FXML // fx:id="menuClose"
	MenuItem menuClose = new MenuItem();					// Close app
	
	// Recipe chose from model
	private Recipe recipe = new Recipe();
	
	static Constants constants = new Constants();
	
	// serving Sizes
	private static final String[] SERVSIZES = constants.getServsizes();
	// minimum size of the window
	private static final int[] MIN_SIZES = constants.getMinSizes();
	
	@Override	// Method is called by the FXMLLoader when initialization is complete
	public void initialize(URL fxmlFileLocation, ResourceBundle resources) {

		//Disable recipeName and instruction fields
		recipeName.setEditable(false);
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
		ingredientsTable.setItems((ObservableList<Ingredient>) recipe.getIngredients());
		ingredientsTable.getColumns().addAll(ingredientColumn, quantityColumn, unitColumn);
		
		// Initialize Category table
		categoryTable.setItems((ObservableList<String>) recipe.getCategories());
		
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
	 * Recipe getter
	 * @return
	 */
	public Recipe getRecipe() {
		return recipe;
	}

	/**
	 * Recipe setter
	 * @param recipe
	 */
	public void setRecipe(Recipe recipe) {
		this.recipe = recipe;
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
