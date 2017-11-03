package test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.sun.javafx.fxml.LoadListener;

import control.Constants;
import control.ReadController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
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
		
		try {
			Constants constants = new Constants();
			final int[] MIN_SIZES = constants.getMinSizes();
			/**
			 * load file
			 */
			FXMLLoader loader = new FXMLLoader( getClass().getResource("/view/ReadInterface.fxml"));
			
			/**
			 * set to scene
			 */
			Parent root = loader.load();
			Scene scene = new Scene(root, MIN_SIZES[0], MIN_SIZES[1]);
			
			/**
			 * set up controller
			 */
			ReadController controller = loader.<ReadController>getController();
			controller.initData(testRecipe);
			
			/**
			 * set stage
			 */
			Stage primaryStage = new Stage();
			primaryStage.setTitle("Interface Demo");
			primaryStage.setScene(scene);
			primaryStage.show();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	
}
