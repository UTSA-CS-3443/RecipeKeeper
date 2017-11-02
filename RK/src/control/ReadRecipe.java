package control;

import java.io.BufferedReader;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import model.Ingredient;
import model.Recipe;
import model.RecipeList;

public class ReadRecipe {
	public static RecipeList readRecipes() {
		Path currentRelativePath = Paths.get("");
		String path = currentRelativePath.toAbsolutePath().toString() + "/src/model/Recipes";
		File dir = new File(path);
		File[] recipeFiles = dir.listFiles();
		BufferedReader br = null;
		ArrayList<Recipe> newRecipeArray = new ArrayList<Recipe>();
		if (recipeFiles != null){
			for ( File child : recipeFiles){
				String line = "";
				String newInstructions = "";
				Recipe newRecipe = new Recipe();
				ArrayList<Ingredient> newIngredientArray = new ArrayList<Ingredient>();
				ArrayList<String> newCategories = new ArrayList<String>();
				Ingredient newIngredient;
				try {
					br = new BufferedReader(new FileReader(child));
					while ((line = br.readLine()) != null && !line.equals(",")){
						ArrayList<String> lines = new ArrayList<String>();
						lines.addAll(Arrays.asList(line.split(",")));
							double quant;
							String name = "", unit = "";
							try {
								quant = Double.parseDouble(lines.get(1));
							} catch(Exception e) {
								quant = 0;
							}
							try {
								name = lines.get(0);
							} catch(Exception e) {
								lines.add(0, "");
							}
							try {
								unit = lines.get(2);
							} catch(Exception e) {
								lines.add(2, "");
							}					
						newIngredient = new Ingredient(name, quant, unit);
						newIngredientArray.add(newIngredient);
					}
					while ((line = br.readLine()) != null && !line.equals(",")){
						String[] lines = line.split(",");
						newCategories.addAll(Arrays.asList(lines));
					}
					while ((line = br.readLine()) != null){
						newInstructions += line + "\n";
					}
					newRecipe.setName(child.getName().substring(0,child.getName().length()-4));
					newRecipe.setIngredients(newIngredientArray);
					newRecipe.setCategories(newCategories);
					newRecipe.setInstructions(newInstructions);
					newRecipeArray.add(newRecipe);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					// Hoa will do this
				}
			}
		}
		RecipeList recipeList = new RecipeList(newRecipeArray);
		return recipeList;
	}
}
