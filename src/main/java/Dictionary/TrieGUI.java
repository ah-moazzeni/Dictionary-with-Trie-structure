package Dictionary;

import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TrieGUI {
    public static void firstScene(Stage stage){
        stage.setTitle("first stage");
        stage.setMinHeight(700);
        stage.setMinWidth(400);
        stage.setMaxHeight(1000);
        stage.setMaxWidth(600);
//        stage.setHeight(750);
//        stage.setWidth(450);




        Label nameLabel = new Label("Name: ");
        nameLabel.setCursor(Cursor.TEXT);
        nameLabel.setTooltip(new Tooltip("NAME"));

        TextField nameTextfield = new TextField();
        nameTextfield.setTooltip(new Tooltip("Enter your name..."));
        ////////////////////////////
        nameTextfield.textProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println("textfield changed from " + oldValue + " to " + newValue);
        });
        /////////////////////////////
//        nameTextfield.setMaxWidth(250);

        Label showName = new Label();

        Button temp = new Button("Don't Click");
        temp.setId("tempID");

        Button submitButton = new Button("Submit");
        submitButton.setCursor(Cursor.HAND);
        submitButton.setTooltip(new Tooltip("Click"));
        submitButton.setId("submitID");
//        submitButton.setStyle("-fx-background-color: #0027ff;-fx-text-fill: #ff0000;");

        // background-color:black


//        button.setMaxSize(100, 200);


        HBox nameHBox = new HBox(nameLabel, nameTextfield);
        nameHBox.setAlignment(Pos.CENTER);
        nameHBox.setSpacing(20);

        VBox root = new VBox();
        root.getChildren().setAll(nameHBox, submitButton, showName, temp);
        root.setAlignment(Pos.CENTER);
        root.setSpacing(15);

    }
}
