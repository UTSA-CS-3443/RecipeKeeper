package control;

import java.io.FileWriter;

import model.AlertBox;
import model.Ingredient;
import model.Recipe;

public class WriteData {
	
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
			fileWriter.append(recipe.toString());
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
