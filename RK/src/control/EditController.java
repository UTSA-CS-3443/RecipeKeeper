package control;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.commons.lang3.StringUtils;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.AlertBox;
import model.Constants;
import model.Ingredient;
import model.IngredientException;
import model.Recipe;

/**
 * NewController initializes when user chooses to create a new recipe
 * File -> New or File -> Edit
 * @author Hoa Pham
 *
 */
public class EditController implements Initializable{
	@FXML // fx:id="motherPane"
	private BorderPane motherPane;

	@FXML // fx:id="recipeName"
	private TextField recipeName; 

	@FXML // fx:id="ingreName"
	private TextField ingreName;

	@FXML // fx:id="ingreQty"
	private TextField ingreQty;

	@FXML // fx:id="textCategory"
	private TextField textCategory;

	@FXML //fx:id="instructions"
	private TextArea instructions;

	@FXML // fx:id="servingSize"
	private ComboBox<String> servingSize;

	@FXML // fx:id="ingreUnit"
	private ComboBox<String> ingreUnit;

	@FXML // fx:id="menuNew"
	private MenuItem menuNew;						// New...			

	@FXML // fx:id="menuEdit"
	private MenuItem menuEdit;						// Edit

	@FXML // fx:id="menuSave"
	private MenuItem menuSave;						// Save

	@FXML // fx:id="menuSaveAs"
	private MenuItem menuSaveAs;						// Save As...

	@FXML // fx:id="backward"
	private Button backward;							// <

	@FXML // fx:id"forward"
	private Button forward;							// >

	@FXML // fx:id="addIngredient"
	private Button addIngredient;					// "+" button for ingredients

	@FXML // fx:id="delIngredient"
	private Button delIngredient;					// "-" button for ingredients

	@FXML // fx:id="addCategory"
	private Button addCategory;						// "+" button for categories

	@FXML // fx:id="delCategory"
	private Button delCategory;						// "-" button for categories

	@FXML // fx:id="ingredientsTable"
	private TableView<Ingredient> ingredientsTable;

	@FXML // fx:id="categoryTable"
	private ListView<String> categoryTable;
	private ObservableList<String> categories = FXCollections.observableArrayList();

	// Recipe chose from model
	private Recipe recipe = new Recipe();

	// constants
	private static Constants constants = new Constants();

	// list of units
	private final String[] UNITS = constants.getUnits();
	// serving Sizes
	private final String[] SERVSIZES = constants.getServsizes();
	// minimum size of the window
	private final int[] MIN_SIZES = constants.getMinSizes();


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

	public EditController() {
		super();
	}

	/**
	 * Constructor
	 * @param r
	 */
	public EditController(Recipe r) {
		initData(r);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		instructions.setWrapText(true);
		menuEdit.setDisable(true);

		/**
		 * Sets up serving size ComboBox and
		 * its handler 
		 */
		servingSize.setOnAction( e -> {
			for (int i = 0; i < ingredients.size(); i++) {
				double tempQty;
				tempQty = Double.parseDouble(servingSize.getValue()) * qtyPerServingSize.get(i);
				changeValueAt(i, 2, tempQty, ingredientsTable);
			}
		});
		
		menuSave.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				try {
					recipeName.getText();
					if (instructions.getText().equals(null)) instructions.setText("");
				} catch (NullPointerException npe) {
					AlertBox.display("Warning", "Recipe name is empty");
				} 
			}
			
		});

		/**
		 * menuSaveAs handler
		 */
		menuSaveAs.setOnAction(action -> {
			try {
				recipeName.getText();
			} catch (NullPointerException e) {
				AlertBox.display("Warnning", "Recipe name is empty");
			}
		});

		// return to the previous page
		backward.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub

			}
		});

		// return to the the page behind 
		forward.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub

			}
		});

		/**
		 * Sets up handler for unit ComboBox for ingredients 
		 * 
		 */
		ingreUnit.setOnAction(e -> {
			// add more code for listener if needed
		});

		/**
		 * EventHandler for adding an ingredient
		 */
		addIngredient.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				try {

					if (ingreName.getText().equals(null) || ingreQty.getText().equals(null) || ingreUnit.getValue().equals(null) || 
							ingreName.getText().equals("") || ingreQty.getText().equals("") || ingreUnit.getValue().equals("")) 
						throw new IngredientException("One or more fields are empty");
					else if (StringUtils.isBlank(ingreName.getText())) throw new IngredientException("Ingredient name cannot be blank");
					else if (StringUtils.isBlank(ingreUnit.getValue())) throw new IngredientException("Unit cannot be blank");
					else if (!ingreName.getText().contains("[a-zA-Z]+")) throw new IngredientException("Ingredient name can only contain alphabetical letters");
					else if (!ingreUnit.getValue().contains("[a-zA-Z]+")) throw new IngredientException("Ingredient unit only contain alphabetical letters");
					else if (containsDigit(ingreName.getText())) throw new IngredientException("Ingredient name can't contains digit");
					else if (containsDigit(ingreUnit.getValue())) throw new IngredientException("Ingredient name can't contains digit");
					else if (!isNumeric(ingreQty.getText())) throw new IngredientException("Ingredient quantity");


					String name, unit;
					double qty;
					name = ingreName.getText();
					qty = Integer.valueOf(ingreQty.getText());
					unit = ingreUnit.getValue();
					Ingredient i = new Ingredient (name, qty, unit);
					ingredients.add(i);
					qtyPerServingSize.add(qty);

				} 
				catch (IngredientException e) { 
					// add more codes if needed
				}
				catch (IllegalArgumentException np) {
					AlertBox.display("Warning", "One or more fields are empty");
				}
				catch (NullPointerException e) {
					AlertBox.display("Warning", "One or more fields are empty");
				}
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
					if (textCategory.getText().equals(null) || textCategory.getText().equals("")) throw new NullPointerException();
					else if (textCategory.getText().contains("[a-zA-Z]+")) throw new IllegalArgumentException();
					else if (StringUtils.isBlank(textCategory.getText())) throw new IngredientException();
					categories.add(textCategory.getText());
				} catch (NullPointerException npe) {
					AlertBox.display("Warning", "Category field cannot be empty");
				} catch (IllegalArgumentException e) {
					AlertBox.display("Warning", "Category field cannot be a number");
				} catch (IngredientException ie) {
					AlertBox.display("Warning", "Category field cannot contain all spaces");
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
	 * Initialize data, Recipe setter
	 * @param recipe
	 */
	public void initData(Recipe r) {

		this.recipe = r;
		recipeName.setText(recipe.getName());
		instructions.setText(recipe.getInstructions());

		// servingSize
		servingSize.getItems().addAll(SERVSIZES);

		// ComboBox for ingredient's units
		ingreUnit.getItems().addAll(UNITS);
		ingreUnit.setEditable(true);

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
			ingredients.add(i);
			qtyPerServingSize.add(i.getQuantity());
		}
		ingredientsTable.setItems(ingredients);
		ingredientsTable.getColumns().addAll(ingredientColumn, quantityColumn, unitColumn);

		// Initialize Category table
		for (String s : recipe.getCategories()) {
			categories.add(s);
		}
		categoryTable.setItems(categories);
	}

	/**
	 * get Data, recipe getter
	 * @return the recipe is being read
	 */
	public Recipe getData() {
		return this.recipe;
	}

	/**
	 * Check if a string is numeric
	 * @param str
	 * @return true if it is, false if otherwise
	 */
	private static boolean isNumeric(String str)  
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

	/**
	 * check if a String contains a digit
	 * @param a String
	 * @return true if it does, false otherwise
	 */
	private static boolean containsDigit(String s) {
		boolean result = false;
		for (char c : s.toCharArray()) {
			if (Character.isDigit(c)) {
				result = true;
				break;
			}
		}
		return result;
	}

}
