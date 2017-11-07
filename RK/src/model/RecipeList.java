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
			for (Ingredient ingredient : r.getIngredients()) {
				if (ingredient.getName().contains(rIngre))
					result.add(r);
				break;	
			}
		}
		return result;
	}

	/**
	 * Get recipes by category requested
	 * @param requested category
	 * @return a list of recipes that contains the requested category
	 */
	public ArrayList<Recipe> getRecipeByCategory(String rCat) {
		ArrayList<Recipe> result = new ArrayList<Recipe>();
		for (Recipe r : recipeList) {
			for (String cate : r.getCategories()) {
				if (cate.contains(rCat)) result.add(r);
				break;
			}
		}
		return result;
	}

	/**
	 * Get recipes by ingredient and category
	 * @param ingre
	 * @param cat
	 * @return a list of recipes that contains the requested ingredient and category
	 */
	public ArrayList<Recipe> getRecipeByIngreCat(String ingre, String cat) {
		ArrayList<Recipe> result = new ArrayList<Recipe>();
		for (Recipe r : recipeList) {
			for (Ingredient ingredient : r.getIngredients()) {
				if (ingredient.getName().contains(ingre)) {
					for (String s : r.getCategories()) {
						if (s.contains(cat)) result.add(r);
						break;
					}
				}
				break;
			}
		}
		return result;
	}

	/**
	 * get recipe by name and ingredient
	 * @param name
	 * @param ingre
	 * @return a list of recipes that contains the requested ingredient and name
	 */
	public ArrayList<Recipe> getRecipeByNameIngre(String name, String ingre) {
		ArrayList<Recipe> result = new ArrayList<Recipe>();
		for (Recipe r : recipeList) {
			if (r.getName().contains(name)) {
				for (Ingredient ingredient : r.getIngredients()) {
					if (ingredient.getName().contains(ingre)) result.add(r);
					break;
				}
			}
		}
		return result;
	}
	
	/**
	 * get recipe by name and category
	 * @param name
	 * @param cate
	 * @return a list of recipes that contains the requested category and name
	 */
	public ArrayList<Recipe> getRecipeByNameCate (String name, String cate) {
		ArrayList<Recipe> result = new ArrayList<Recipe>();
		for (Recipe r : recipeList) {
			if (r.getName().contains(name)) {
				for (String category : r.getCategories()) {
					if (category.contains(cate)) result.add(r);
					break;
				}
			}
		}
		return result;
	}
	
	/**
	 * get recipe by name, ingredient and category
	 * @param name
	 * @param ingre
	 * @param cate
	 * @returna list of recipes that contains the requested category, name, and ingredient
	 */
	public ArrayList<Recipe> getRecipeByAll (String name, String ingre, String cate) {
		ArrayList<Recipe> result = new ArrayList<Recipe>();
		for (Recipe r : recipeList) {
			if (r.getName().contains(name)) {
				for (String category : r.getCategories()) {
					if (category.contains(cate)) result.add(r); {
						for (Ingredient ingredient : r.getIngredients()) {
							if (ingredient.getName().contains(ingre)) result.add(r);
							break;
						}
					}
					break;
				}
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
