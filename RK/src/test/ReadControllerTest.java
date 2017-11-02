package test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import control.ReadController;
import model.Ingredient;
import model.Recipe;

public class ReadControllerTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testReadController() {
		String name = "TestName";
		Ingredient i1 = new Ingredient("ingreName", 1, "ingreUnit");
		Ingredient i2 = new Ingredient("ingreName2", 2, "ingreUnit2");
		ArrayList<Ingredient> ingredients = new ArrayList<Ingredient>();
		ingredients.add(i1);
		ingredients.add(i2);
		ArrayList<String> categories = new ArrayList<String>();
		categories.add("Category 1");
		categories.add("category 2");
		String instructions = "Do something........";
		Recipe testRecipe = new Recipe(name, ingredients, categories, instructions);
		//new ReadController(testRecipe);
	}

}
