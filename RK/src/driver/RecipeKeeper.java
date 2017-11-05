package driver;
	
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import control.Constants;
import control.EditController;
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
						
			String fileDirectory = "/view/Welcome.fxml";
			String cssDirectory = "/view/RecipeKeeper.css";

 			FXMLLoader loader = new FXMLLoader(getClass().getResource(fileDirectory));
 			Parent root = loader.load();
 			
			Scene scene = new Scene(root, 370, 250);
			
			scene.getStylesheets().add(getClass().getResource(cssDirectory).toExternalForm());
			primaryStage.setTitle("Recipe Keeper");
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
