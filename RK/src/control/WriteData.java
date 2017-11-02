package control;

import java.io.FileWriter;

import model.AlertBox;
import model.Ingredient;
import model.Recipe;

public class WriteData {

	private static final String COMMA_DELIMITER = ",";
	private static final String NEW_LINE_SEPARATOR = "\n";
	private static final String QUOTE_SEPARATOR = "\"";
	
	/**
	 * default constructor
	 */
	public WriteData() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Constructor 
	 * @param recipeName.csv
	 * @param Recipe
	 */
	public WriteData(String fileName, Recipe recipe) {
		FileWriter fileWriter = null;
		try {
			fileWriter = new FileWriter(fileName);
			for (Ingredient i : recipe.getIngredients()) {
				fileWriter.append(i.getName());
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(String.valueOf(i.getQuantity()));
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(i.getUnit());
				fileWriter.append(NEW_LINE_SEPARATOR);
			}
			fileWriter.append(COMMA_DELIMITER + NEW_LINE_SEPARATOR);
			for (String c : recipe.getCategories()) {
				fileWriter.append(c + " ");
			}
			fileWriter.append(NEW_LINE_SEPARATOR + COMMA_DELIMITER);
			fileWriter.append(QUOTE_SEPARATOR);
			fileWriter.append(recipe.getInstructions());
			fileWriter.append(QUOTE_SEPARATOR);
			AlertBox.display("Recipe Keeper", "Your recipe is saved.");
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
