package test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import model.Ingredient;
import model.Recipe;

public class CompareRecipeTest {

	@Test
	public void testCompareTo() {
		Ingredient test1 = new Ingredient("chicken", 1, "bucket");
		Ingredient test2 = new Ingredient("asdfkljsdf", 3, "oil drum");
		ArrayList<Ingredient> testIngredients = new ArrayList<Ingredient>();
		testIngredients.add(test1);
		testIngredients.add(test2);
		ArrayList<String> testCategories = new ArrayList<String>();
		testCategories.add("christmas");
		testCategories.add("spooky");
		Recipe test = new Recipe("test", testIngredients, testCategories, "cook");
		Recipe rep2 = new Recipe("test", testIngredients, testCategories, "cook");
		
		System.out.println(test.compareCategory(rep2));
		System.out.println(test.compareIngredient(rep2));
		System.out.println(test.getName().equals(rep2.getName()));
		System.out.println(test.compareTo(rep2));
	}
	
	@Test 
	public void testCompareCategories() {
		ArrayList<Ingredient> testIngredients = new ArrayList<Ingredient>();
		ArrayList<String> testCategories = new ArrayList<String>();
		ArrayList<String> testCategories1 = new ArrayList<String>();
		testCategories.add("christmas");
		testCategories1.add("spooky");
		testCategories.add("christmas");
		
		
		
		Recipe test = new Recipe("test", testIngredients, testCategories, "cook");
		Recipe rep2 = new Recipe("test", testIngredients, testCategories, "cook");
		
		//System.out.println(test.compareCategory(rep2));
	}
}
