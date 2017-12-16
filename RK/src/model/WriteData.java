package model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * WriteData
 * Create a new csv file with customized format when user hit
 * "Save, Save As" in edit mode
 * @author Hoa Pham
 * @author Loc Nguyen
 */
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
		String path = currentRelativePath.toAbsolutePath().toString() + "/Recipes/";
		Path pPath = Paths.get(path);
		if (!Files.exists(pPath))
			try {
				Files.createDirectories(pPath);
			} catch (IOException e) {
				e.printStackTrace();
			}
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
