package control;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.beans.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.*;
import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;

import model.Ingredient;

/**
 * NewController initializes when user chooses to create a new recipe
 * File -> New
 * @author hoapham
 *
 */
public class NewController implements Initializable{
	@FXML // fx:id="motherPane"
	BorderPane motherPane = new BorderPane();
	
	@FXML // fx:id="recipeName"
	TextField recipeName = new TextField(); 

	@FXML // fx:id="ingreName"
	TextField ingreName = new TextField();

	@FXML // fx:id="ingreQty"
	TextField ingreQty = new TextField();

	@FXML // fx:id="ingreUnit"
	TextField ingreUnit = new TextField();
	
	@FXML // fx:id="textCategory"
	TextField textCategory = new TextField();

	@FXML // fx:id="servingSize"
	ComboBox<String> servingSize = new ComboBox<>();

	@FXML // fx:id="addIngredient"
	Button addIngredient = new Button();

	@FXML // fx:id="delIngredient"
	Button delIngredient = new Button();

	@FXML // fx:id="addCategory"
	Button addCategory = new Button();

	@FXML // fx:id="delCategory"
	Button delCategory = new Button();

	@FXML // fx:id="ingredientsTable"
	TableView<Ingredient> ingredientsTable = new TableView<>();
	
	@FXML // fx:id="categoryTable"
	ListView<String> categoryTable = new ListView<>();
	ObservableList<String> categories =FXCollections.observableArrayList();

	// temporary ingredients list
	ObservableList<Ingredient> ingredients = FXCollections.observableArrayList();

	// quantity per serving size
	List<Double> qtyPerServingSize = new ArrayList<Double>();
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		/**
		 * Sets up serving size ComboBox and
		 * its handler 
		 */
		servingSize.getItems().addAll("1", "2", "3");		
		servingSize.setOnAction( e -> {
			for (int i = 0; i < ingredients.size(); i++) {
				double tempQty;
				tempQty = Double.parseDouble(servingSize.getValue()) * qtyPerServingSize.get(i);
				System.out.println(String.valueOf(tempQty));
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
		ingredientsTable.setItems(ingredients);
		ingredientsTable.getColumns().addAll(ingredientColumn, quantityColumn, unitColumn);

		// Initialize Category table
		categoryTable.setItems(categories);
		
		/**
		 * EventHandler for adding an ingredient
		 */
		addIngredient.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				try {

					if (ingreName.getText().equals(null) || ingreQty.getText().equals(null) || ingreUnit.getText().equals(null) ||
							ingreName.getText().equals("") || ingreQty.getText().equals("") || ingreUnit.getText().equals("")) 
						throw new IngredientException("One or more fields are empty");
					else if (isNumeric(ingreName.getText())) throw new IngredientException("Ingredient Name");
					else if (!isNumeric(ingreQty.getText())) throw new IngredientException("Ingredient Quantity");
					else if (isNumeric(ingreUnit.getText())) throw new IngredientException("Ingredient Unit");

					String name, unit;
					double qty;
					name = ingreName.getText();
					qty = Integer.valueOf(ingreQty.getText());
					unit = ingreUnit.getText();
					Ingredient i = new Ingredient (name, qty, unit);
					ingredients.add(i);
					qtyPerServingSize.add(qty);

				} catch (IngredientException e) {  }
			}
		});

		/**
		 * EventHandler for deleting an ingredient
		 */
		delIngredient.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				try {
					if (ingredients.size() < 1) throw new RuntimeException(); 
					
					int selectedIndex = ingredientsTable.getSelectionModel().getSelectedIndex();
					if (selectedIndex >= 0) {
						ingredientsTable.getItems().remove(selectedIndex);
						qtyPerServingSize.remove(selectedIndex);
					}
					else {
						AlertBox.display("Warning", "No Item Selected");
					}

				} catch (RuntimeException e) {
					AlertBox.display("Warning", "Ingredient List Is Empty");
				}
			}
		});

		/**
		 * Event Handler for adding a category
		 */
		addCategory.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				
				try {
					if (textCategory.getText().equals(null) || textCategory.getText().equals("")) throw new IllegalArgumentException();
					if (isNumeric(textCategory.getText())) throw new IllegalArgumentException();
					categories.add(textCategory.getText());
				} catch (IllegalArgumentException e) {
					AlertBox.display("Warning", "Category field cannot be a number or empty");
				}
				
			}
			
		});
		
		/**
		 * Event Handler for deleting a category
		 */
		delCategory.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				
				try {
					if (categories.size() < 1) throw new RuntimeException();
					
					int selectedIndex = categoryTable.getSelectionModel().getSelectedIndex();
					if (selectedIndex >= 0) {
						categoryTable.getItems().remove(selectedIndex);
					}
					else {
						AlertBox.display("Warning", "No Item Selected");
					}
				} catch (RuntimeException e) {
					AlertBox.display("Warning", "Category List Is Empty");
				}
				
			}
			
		});
	}

	/**
	 * Check if a string is numeric
	 * @param str
	 * @return true if it is, false if otherwise
	 */
	public static boolean isNumeric(String str)  
	{  
		try  
		{  
			Double.parseDouble(str);  
		}  
		catch(NumberFormatException nfe)  
		{  
			return false;  
		}  
		return true;  
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
