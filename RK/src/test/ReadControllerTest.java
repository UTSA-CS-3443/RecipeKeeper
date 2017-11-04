package test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;

//import org.junit.After;
//import org.junit.AfterClass;
//import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import control.ReadController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import model.Ingredient;
import model.Recipe;

public class ReadControllerTest {

	
	
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
		
		try {
			/**
			 * load file
			 */
			FXMLLoader loader = new FXMLLoader(getClass().getResource("RCTest.fxml"));
			loader.load();
			ReadController controller = new ReadController(testRecipe);
			loader.setController(controller);
			loader.getController();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

	
}
