package Dictionary;

import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TrieGUI {
    public static void firstScene(Stage stage){
        VBox root = new VBox();
        Scene scene = new Scene(root, 400, 700);

        stage.setTitle("Dictionary");
        stage.setMinHeight(700);
        stage.setMinWidth(400);
        stage.setMaxHeight(700);
        stage.setMaxWidth(400);

        Label wordLabel = new Label("Word: ");
        wordLabel.setCursor(Cursor.TEXT);
        wordLabel.setTooltip(new Tooltip("WORD"));

        TextField wordTextField = new TextField();
        wordTextField.setTooltip(new Tooltip("Enter your word..."));

        ////////////////////////////
        wordTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            int temp = Trie.isExist(newValue);
            if(temp == 0){
                scene.getStylesheets().add("styleError.css");
                //phase3
            }else if(temp == 1){
                //phase2
            }else if(temp == 2){
                scene.getStylesheets().add("styleSuccess.css");
                //phase2
            }
            System.out.println("textfield changed from " + oldValue + " to " + newValue);
        });

        Label tempLabel = new Label();

        /*
        Button temp = new Button("Don't Click");
        temp.setId("tempID");

        Button submitButton = new Button("Submit");
        submitButton.setCursor(Cursor.HAND);
        submitButton.setTooltip(new Tooltip("Click"));
        submitButton.setId("submitID");
         */

//        submitButton.setStyle("-fx-background-color: #0027ff;-fx-text-fill: #ff0000;");

        // background-color:black


//        button.setMaxSize(100, 200);
        /*
        submitButton.setOnAction(actionEvent -> {
//            tempLabel.setText(wordTextField.getText());
            System.out.println(wordTextField.getText());
            wordTextField.setText("");
        });
         */

        HBox wordHBox = new HBox(wordLabel, wordTextField);
        wordHBox.setAlignment(Pos.CENTER);
        wordHBox.setSpacing(20);


        root.getChildren().setAll(wordHBox, tempLabel);
//        root.getChildren().setAll(wordHBox, submitButton, tempLabel, temp);
        root.setAlignment(Pos.CENTER);
        root.setSpacing(15);

        stage.setScene(scene);
    }
}
