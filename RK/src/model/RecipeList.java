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
	ArrayList<Recipe> recipeList = new ArrayList<Recipe>();

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
	 * @param rName
	 * @return recipe with the needed name
	 */
	public Recipe getRecipeByName(String rName) {
		Recipe recipeByName = null;
		for (Recipe r : recipeList) {
			if (r.getName().equals(rName))
				recipeByName = r;
		}
		return recipeByName;
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
