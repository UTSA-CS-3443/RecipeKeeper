package driver;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class RecipeKeeper extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/view/NewInterface.fxml"));
			Scene scene = new Scene(root, 529, 737);
			scene.getStylesheets().add(getClass().getResource("/view/RecipeKeeper.css").toExternalForm());
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
