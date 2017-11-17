package driver;
	
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import control.EditController;
import control.ReadController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;
import javafx.stage.Stage;
import model.Constants;
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
		
	@Override
	public void start(Stage primaryStage) {
		try {
			Constants constants = new Constants();
			int[] MIN_SIZES = constants.getMinSizes();
			
			String fileDirectory = "/view/Welcome.fxml";
			String cssDirectory = "/view/RecipeKeeper.css";

 			FXMLLoader loader = new FXMLLoader(getClass().getResource(fileDirectory));
 			Parent root = loader.load();
	
			Scene scene = new Scene(root, MIN_SIZES[0], MIN_SIZES[1]);
			scene.getStylesheets().add(getClass().getResource(cssDirectory).toExternalForm());
			primaryStage.setTitle("Recipe Keeper");
			primaryStage.setScene(scene);
			primaryStage.show();
			
			// center the stage
			Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
	        primaryStage.setX((primScreenBounds.getWidth() - primaryStage.getWidth()) / 2);
	        primaryStage.setY((primScreenBounds.getHeight() - primaryStage.getHeight()) / 2);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
