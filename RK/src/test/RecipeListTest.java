package test;

import java.util.ArrayList;
import org.junit.Test;
import model.RecipeList;
import model.Recipe;
import model.WriteData;

public class RecipeListTest {

	@Test
	public void testRmvRecipe() {
		ArrayList<Recipe> testRecipes = new ArrayList<Recipe>();
		Recipe r1 = new Recipe();
		Recipe r2 = new Recipe();
		testRecipes.add(r1);
		r1.setName("r1");
		testRecipes.add(r2);
		r2.setName("r2");
		RecipeList testRecipeList = new RecipeList(testRecipes);
		System.out.print("Current recipes: ");
		for (Recipe recipe : testRecipeList.getRecipeList()) {
			System.out.print(recipe.getName() + " ");
			WriteData.CreateRecipeFile(recipe.getName(), recipe);
		}
		//testRecipeList.rmvRecipe(r1); //Uncomment this line to remove r1 file and object
		System.out.print("\nCurrent recipes: ");
		for (Recipe recipe : testRecipeList.getRecipeList())
			System.out.print(recipe.getName() + " ");
		//testRecipeList.rmvRecipe(r2); //Uncomment this line to remove r2 file and object
		System.out.print("\nCurrent recipes: ");
		for (Recipe recipe : testRecipeList.getRecipeList())
			System.out.print(recipe.getName() + " ");
	}

}
