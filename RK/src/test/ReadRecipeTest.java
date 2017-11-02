package test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import control.ReadRecipe;
import model.Recipe;
import model.RecipeList;

public class ReadRecipeTest {

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
	public void testReadRecipes() {
		RecipeList rl = ReadRecipe.readRecipes();
		for (Recipe r : rl.getRecipeList()) {
			System.out.println(r.toString());
		}
	}

}
