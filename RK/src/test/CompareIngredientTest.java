package test;

import static org.junit.Assert.*;

import org.junit.Test;

import model.Ingredient;

public class CompareIngredientTest {

	@Test
	public void testEqual() {
		Ingredient i1 = new Ingredient ("beef", 1, "lb");
		Ingredient i2 = new Ingredient ("beef", 1, "lb");
		int result = i1.compareTo(i2);
		assertEquals("expected 1 instead of 0", 1, result);
	}
	
	@Test 
	public void testNotEqualUnit() {
		Ingredient i1 = new Ingredient ("beef", 1, "lb");
		Ingredient i2 = new Ingredient ("beef", 1, "kg");
		int result = i1.compareTo(i2);
		assertEquals("expected 0 instead of 1", 0, result);
	}
	
	@Test
	public void testNotEqualQty() {
		Ingredient i1 = new Ingredient ("beef", 1, "lb");
		Ingredient i2 = new Ingredient ("beef", 2, "kg");
		int result = i1.compareTo(i2);
		assertEquals("expected 0 instead of 1", 0, result);
	}
	
	@Test 
	public void testNotEqualName() {
		Ingredient i1 = new Ingredient ("beef", 1, "lb");
		Ingredient i2 = new Ingredient ("chicken", 1, "kg");
		int result = i1.compareTo(i2);
		assertEquals("expected 0 instead of 1", 0, result);
	}
}
