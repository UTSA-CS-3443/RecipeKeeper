package control;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

/**
 * Welcome Controller
 * @author Hoa Pham
 *
 */
public class WelcomeController implements Initializable {
	
	@FXML // fx:id="startRep"
	Button startRep = new Button();
	
	@FXML // fx:id="findRep"
	Button findRep = new Button();
	
	@FXML // fx:id="editRep"
	Button editRep = new Button();
	
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

}
