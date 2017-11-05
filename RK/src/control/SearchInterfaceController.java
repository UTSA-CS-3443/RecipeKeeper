package control;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import model.Recipe;

public class SearchInterfaceController implements Initializable {
	
	@FXML // fx:id="motherPane"
	private BorderPane motherPane;
	
	@FXML // fx:id="repList"
	private ListView<String> repList;
	
	@FXML // fx:id="open"
	private Button open;

	private ObservableList<String> recipeNames = FXCollections.observableArrayList();
	
	/**
	 * constructor
	 */
	public SearchInterfaceController() {
		super();
	}
	
	/**
	 * constructor
	 * @param result
	 */
	public SearchInterfaceController(ArrayList<Recipe> result) {
		
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * Initialize data
	 * @param result
	 */
	public void initData(ArrayList<Recipe> result) {
		for (Recipe r : result) {
			recipeNames.add(r.getName());
		}
		repList.setItems(recipeNames);
	}
	
}
