package control;

import java.io.File;
import java.io.FileWriter;
import java.nio.file.Path;
import java.nio.file.Paths;

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
		CreateRecipeFile(fileName, recipe);
	}
	
	public static void CreateRecipeFile(String recipeName, Recipe recipe) {
		Path currentRelativePath = Paths.get("");
		String path = currentRelativePath.toAbsolutePath().toString() + "/src/model/Recipes/";
		File file = new File( path + recipeName + ".csv");
		try {
			if (!file.exists()) {
				file.createNewFile();
			}
			FileWriter writer = new FileWriter(file);
			
			// parse the recipe into the same format with other files
			String textForm = "";
			for (Ingredient i : recipe.getIngredients()) {
				textForm += i.getName() + "," + i.getQuantity() + "," + i.getUnit() + ",,,\n";
			}
			textForm += ",\n";
			for (String category : recipe.getCategories()) {
				textForm += category + ",";
			}
			textForm += "\n,\n";
			textForm += recipe.getInstructions();
			
			// write into model.Recipes
			writer.write(textForm);
			writer.flush();
			writer.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
