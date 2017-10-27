package model;

import java.util.ArrayList;

/**
 * Recipe only store recipe name, ingredients and categories of the recipe
 * the instructions of the recipe is stored in a html file
 * @author Hoa Pham
 *
 */
public class Recipe {
	
	String name;
	ArrayList<Ingredient> ingredients;
	ArrayList<Category> categories;
	
	
	/**
	 * Default Constructor
	 */
	public Recipe() {
		this.name = "";
		this.ingredients = new ArrayList<Ingredient>();
		this.categories = new ArrayList<Category>();
	}
	
	/**
	 * Constructor
	 * @param name
	 * @param ingredients
	 * @param categories
	 */
	public Recipe(String name, ArrayList<Ingredient> ingredients, ArrayList<Category> categories) {
		this.name = name;
		this.ingredients = ingredients;
		this.categories = categories;
	}
	
	/**
	 * Get recipe name
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * Set recipe name
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Get ingredients of the recipe
	 * @return
	 */
	public ArrayList<Ingredient> getIngredients() {
		return ingredients;
	}

	/**
	 * set ingredients of the recipe
	 * @param ingredients
	 */
	public void setIngredients(ArrayList<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}

	/**
	 * Get categories the recipe belongs to
	 * @return
	 */
	public ArrayList<Category> getCategories() {
		return categories;
	}

	/**
	 * set categories the recipe belongs to
	 * @param categories
	 */
	public void setCategories(ArrayList<Category> categories) {
		this.categories = categories;
	}
	
	
}
