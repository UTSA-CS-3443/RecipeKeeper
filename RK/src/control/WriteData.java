package control;

import java.io.File;
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
		File file = new File(fileName);
		try {
			if (!file.exists()) {
				file.createNewFile();
			}
			FileWriter writer = new FileWriter(fileName);
			writer.write(recipe.toString());
			writer.flush();
			writer.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
