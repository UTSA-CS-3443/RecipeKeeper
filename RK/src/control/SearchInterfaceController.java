package control;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;

public class SearchInterfaceController implements Initializable {
	
	@FXML // fx:id="motherPane"
	private BorderPane motherPane;
	
	@FXML // fx:id="repList"
	private ListView<String> repList;
	
	@FXML // fx:id="open"
	private Button open;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	
}
