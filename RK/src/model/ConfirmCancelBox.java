package model;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * create a warning window that force the user to choose an action
 * before continuing to use the app
 * @author Hoa Pham
 *
 */
public class ConfirmCancelBox {
	//Create variable
    static int answer;

    public static int display(String title, String message) {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(250);
        Label label = new Label();
        label.setText(message);

        //Create two buttons
        Button yesButton = new Button("Yes");
        Button noButton = new Button("No");
        Button cancelButton = new Button("Cancel");

        //Clicking will set answer and close window
        yesButton.setOnAction(e -> {
            answer = 1;
            window.close();
        });
        noButton.setOnAction(e -> {
            answer = -1;
            window.close();
        });
        cancelButton.setOnAction(e -> {
        		answer = 0;
        		window.close();
        });

        VBox layout = new VBox(10);
        
        HBox buttonContainer = new HBox();
        buttonContainer.setAlignment(Pos.CENTER);
        buttonContainer.setSpacing(3);
        buttonContainer.getChildren().addAll(yesButton, noButton, cancelButton);

        //Add buttons
        layout.getChildren().addAll(label, buttonContainer);
        VBox.setMargin(label, new Insets(0,3,0,3));
        VBox.setMargin(buttonContainer, new Insets(0,0,3,0));
        layout.setAlignment(Pos.CENTER);
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();

        //Make sure to return answer
        return answer;
    }
}
