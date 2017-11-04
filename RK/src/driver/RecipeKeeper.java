package driver;
	
import java.net.URL;
import java.util.ArrayList;

import control.Constants;
import control.ReadController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import model.Ingredient;
import model.Recipe;
import javafx.scene.Parent;
import javafx.scene.Scene;

/**
 * Driver class of Recipe Keeper
 * @author Hoa Pham
 *
 */
public class RecipeKeeper extends Application {
	
	Constants constants = new Constants();
	
	
	
	@Override
	public void start(Stage primaryStage) {
		try {
			
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
			
			String fileDirectory = "/view/ReadInterface.fxml";
			String cssDirectory = "/view/RecipeKeeper.css";
			FXMLLoader loader = new FXMLLoader(getClass().getResource(fileDirectory));
			Parent root = loader.load();
			URL location = new URL(loader.getLocation().toString());
			
			ReadController controller = loader.getController();
			controller.initData(testRecipe);
			controller.initialize(location, loader.getResources());
			
			
			final int[] MIN_SIZES = constants.getMinSizes();
			Scene scene = new Scene(root, MIN_SIZES[0], MIN_SIZES[1]);
			
			scene.getStylesheets().add(getClass().getResource(cssDirectory).toExternalForm());
			primaryStage.setTitle("Interface Demo");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
