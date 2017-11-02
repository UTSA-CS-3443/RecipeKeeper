package model;

import java.util.ArrayList;

/**
 * RecipeList class contains all of the recipe in model
 * @author Hoa Pham
 *
 */
public class RecipeList {
	/**
	 * List of recipes
	 */
	private ArrayList<Recipe> recipeList = new ArrayList<Recipe>();

	/**
	 * constructor
	 * @param recipeList
	 */
	public RecipeList(ArrayList<Recipe> recipeList) {
		this.recipeList = recipeList;
	}

	/**
	 * Add a recipe to the list
	 * @param recipe
	 */
	public void addRecipe(Recipe recipe) {
		recipeList.add(recipe);
	}
	
	/**
	 * Get a recipe by its name
	 * @param requested recipe name
	 * @return a list of recipes that contains the requested name
	 */
	public ArrayList<Recipe> getRecipeByName(String rName) {
		ArrayList<Recipe> result = new ArrayList<Recipe>();
		for (Recipe r : recipeList) {
			if (r.getName().contains(rName))
				result.add(r);
		}
		return result;
	}
	
	/**
	 * Get a recipe by the ingredient requested
	 * @param requested ingredient
	 * @return a list of recipes that contains the requested ingredient
	 */
	public ArrayList<Recipe> getRecipeByIngredients(String rIngre) {
		ArrayList<Recipe> result = new ArrayList<Recipe>();
		for (Recipe r : recipeList) {
			for (String ingre : r.getCategories()) {
				if (ingre.contains(rIngre))
					result.add(r);
			}
		}
		return result;
	}
	
	/**
	 * Get a recipe by category requested
	 * @param requested category
	 * @return a list of recipes that contains the requested category
	 */
	public ArrayList<Recipe> getRecipeByCategory(String rCat) {
		ArrayList<Recipe> result = new ArrayList<Recipe>();
		for (Recipe r : recipeList) {
			for (String cate : r.getCategories()) {
				if (cate.contains(rCat)) 
					result.add(r);
			}
		}
		return result;
	}
	
	/**
	 * RecipeList getter
	 * @return ArrayList<Recipe>
	 */
	public ArrayList<Recipe> getRecipeList() {
		return recipeList;
	}

	/**
	 * RecipeList setter
	 * @param ArrayList<Recipe>
	 */
	public void setRecipeList(ArrayList<Recipe> recipeList) {
		this.recipeList = recipeList;
	}
}
