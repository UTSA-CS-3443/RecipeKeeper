package control;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

public class ReadController implements Initializable {
	@FXML // fx:id="servingSize"
	private ComboBox<String> servingSize = new ComboBox<String>(); // value injected my FXMLLoader

	@FXML // fx:id="motherPane"
	private BorderPane motherPane = new BorderPane();

	@FXML // fx:id="readField"
	private TextField readField = new TextField();

	// Disable TextFields
	/*	@FXML
	private void disableTextfields() {
		int i = 0;
		System.out.println("Something");
		for (Node node : getAllNodes(motherPane)) {
			if (node instanceof TextField) {
				motherPane.getChildren().get(i).setDisable(true);
				System.out.println("Got one");
			}
			if (node instanceof TextArea) {
				motherPane.getChildren().get(i).setDisable(true);
				System.out.println("Got one");
			}
			i++;
		}
	}

//	 for (int i = 0; i < motherPane.getChildren().size(); i++) {	 
//		 System.out.println("Something");
//		 if (motherPane.getChildren().get(i) instanceof TextField) {
//			 motherPane.getChildren().get(i).setDisable(true);
//			 System.out.println("Got one");
//		 }
//
//		 if (motherPane.getChildren().get(i) instanceof TextArea) {
//			 motherPane.getChildren().get(i).setDisable(true);
//		 }
//	 }

	private static ArrayList<Node> getAllNodes(Parent root) {
		ArrayList<Node> nodes = new ArrayList<Node>();
		addAllDescendants(root, nodes);
		return nodes;
	}

	private static void addAllDescendants(Parent parent, ArrayList<Node> nodes) {
		for (Node node : parent.getChildrenUnmodifiable()) {
			nodes.add(node);
			if (node instanceof Parent) {
				addAllDescendants((Parent)node, nodes);
			}
		}
	}*/

	@Override	// Method is called by the FXMLLoader when initialization is complete
	public void initialize(URL fxmlFileLocation, ResourceBundle resources) {

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
