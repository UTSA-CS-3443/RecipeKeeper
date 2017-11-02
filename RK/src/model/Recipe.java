package model;

import java.util.ArrayList;

/**
 * Recipe class will store a recipe's name and instructions in strings, 
 * and its ingredients and categories in ArrayLists.
 * @author Robert Neuhaus
 * @author Hoa Pham
 *
 */
public class Recipe {
	
	String name;
	ArrayList<Ingredient> ingredients;
	ArrayList<String> categories;
	String instructions;
	
	/**
	 * get Intructions
	 * @return intructions as a string
	 */
	public String getInstructions() {
		return instructions;
	}

	/**
	 * set Instructions of the recipe
	 * @param instructions
	 */
	public void setInstructions(String instructions) {
		this.instructions = instructions;
	}

	/**
	 * Default Constructor
	 */
	public Recipe() {
		this.name = "";
		this.ingredients = new ArrayList<Ingredient>();
		this.categories = new ArrayList<String>();
	}
	
	/**
	 * Constructor
	 * @param name
	 * @param ingredients
	 * @param categories
	 */
	public Recipe(String name, ArrayList<Ingredient> ingredients, ArrayList<String> categories, String instructions) {
		this.name = name;
		this.ingredients = ingredients;
		this.categories = categories;
		this.instructions = instructions;
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
	public ArrayList<String> getCategories() {
		return categories;
	}

	/**
	 * set categories the recipe belongs to
	 * @param categories
	 */
	public void setCategories(ArrayList<String> categories) {
		this.categories = categories;
	}
	
	public String toString() {
		String str = "";
		for (Ingredient i : ingredients){
			str = str + i + "\n";
		}
		for (String j : categories){
			str = str + j + "\n,\n";
		}
		return str + instructions;
	}
	
}
