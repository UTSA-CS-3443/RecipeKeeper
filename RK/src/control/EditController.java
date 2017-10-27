package control;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

public class EditController implements Initializable {
	
	@FXML	// fx:id="addIngredient"
	private Button addIngredient = new Button();			// "+" button
	
	@FXML	// fx:id="subtractIngredient"
	private Button subtractIngredient = new Button();		// "-" button
	
	@FXML // fx:id="servingSize"
	private ComboBox<String> servingSize = new ComboBox<String>(); // value injected my FXMLLoader

	@Override	// Method is called by the FXMLLoader when initialization is complete
	public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
		
		 assert servingSize != null : "fx:id=\"servingSize\" was not injected: "
		 		+ "check your FXML file";
		
		// add serving sizes
		servingSize.getItems().addAll("1","2","3");
		
		/**
		 * Listen for changes to the fruit comboBox selection
		 * and update the displayed serving size
		 */
		servingSize.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> selected, String oldValue, String newValue) {
				servingSize.setValue(newValue); // Serving Size: newValue ????
				
				// do some more stuffs with ingredients
			}
		});
	
	
	}
	
}
