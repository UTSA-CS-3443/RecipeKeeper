package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Recipe class will store a recipe's name and instructions in strings, 
 * and its ingredients and categories in ArrayLists.
 * @author Robert Neuhaus
 * @author Hoa Pham
 *
 */
public class Recipe implements Comparable<Recipe> {

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
		this.instructions = "";
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
		return this.ingredients;
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

	/**
	 * toString method
	 */
	public String toString() {
		String str = "";
		for (Ingredient i : ingredients){
			str = str + i + "\n";
		}
		str = str + ",\n";
		for (String j : categories){
			str = str + j + ",";
		}
		str = str + "\n,\n";
		return str + instructions;
	}

	/**
	 * compareTo method of Recipe class
	 * @param other
	 * @return 1 if two recipes are identical, 0 otherwise
	 */
	public int compareTo(Recipe other) {
		int result = 0;

		if (this.getName().equals(other.getName())) {
			if(this.compareIngredient(other) == 1) {
				if(this.compareCategory(other) == 1) {
					if(this.getInstructions().equals(other.getInstructions())) {
						result = 1;
					}
				}
			}
		}

		return result;
	}

	/**
	 * compare categories of two recipes
	 * @param other
	 * @return 1 if the lists of Categories are the same, 0 otherwise
	 */
	public int compareCategory(Recipe other) {
		int result = 0;
		if (this.getCategories().size() != other.getCategories().size()) return result;
		else {
			ArrayList<String> differenceList = new ArrayList<String>();
			for (String thisCat : this.getCategories()) {
				for (String otherCat : other.getCategories()) {
					if (!thisCat.equals(otherCat)) {
						differenceList.add(otherCat);
					}
				}
			}
			if (differenceList.size() == 0) result = 1;
		}
		return result;
	}

	/**
	 * compare ingredients of two recipes
	 * @param other
	 * @return 1 if the lists of Ingredients are the same, 0 otherwise
	 */
	public int compareIngredient(Recipe other) {
		int result = 0;
		if (this.getIngredients().size() != other.getIngredients().size()) return result;
		else {
			ArrayList<Ingredient> differenceList = new ArrayList<Ingredient>();
			for (Ingredient thisIngre : this.getIngredients()) {
				for (Ingredient otherIngre : other.getIngredients()) {
					if (thisIngre.compareTo(otherIngre) == 0) {
						differenceList.add(otherIngre);
					}
				}
			}
			if (differenceList.size() == 0) result = 1;
		}

		return result;
	}
}
