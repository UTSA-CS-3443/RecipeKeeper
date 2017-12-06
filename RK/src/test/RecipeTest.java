package test;

import java.util.ArrayList;

import org.junit.Test;

import model.Ingredient;
import model.Recipe;

/**
 * Test toString method of Recipe class
 * @author Hoa Pham
 *
 */
public class RecipeTest {

	@Test
	public void testToString() {
		Ingredient test1 = new Ingredient("chicken", 1, "bucket");
		Ingredient test2 = new Ingredient("doritos", 3, "oil drum");
		ArrayList<Ingredient> testIngredients = new ArrayList<Ingredient>();
		testIngredients.add(test1);
		testIngredients.add(test2);
		ArrayList<String> testCategories = new ArrayList<String>();
		testCategories.add("christmas");
		testCategories.add("spooky");
		Recipe test = new Recipe("test", testIngredients, testCategories, "cook");
		System.out.println(test);
	}

}
