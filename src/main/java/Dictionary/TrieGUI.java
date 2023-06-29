package Dictionary;

import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Locale;

public class TrieGUI {
    public static void mainScene(Stage stage){
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

        Label tempLabel = new Label();

        ////////////////////////////
        wordTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            newValue = newValue.toLowerCase();
            int temp = Main.originalTrie.isExist(newValue);
            if(temp == 0){
//                System.out.println("phase 3: " + Correction.correctionSuggestion(newValue));
                tempLabel.setText(ListToString(Correction.correctionSuggestion(newValue), temp));
                scene.getStylesheets().clear();
                scene.getStylesheets().add("styleError.css");
                //phase3
            }else if(temp == 1){
                tempLabel.setText("");
                if(!newValue.equals("")) {
                    AutoComplete.getLastNode(newValue);
//                    System.out.println(AutoComplete.PossibleWords);
//                    System.out.println(AutoComplete.PrioritizeWords);
//                    System.out.println(AutoComplete.fiveRecentWords());
                    tempLabel.setText(ListToString(AutoComplete.fiveRecentWords(), temp));
                }
                scene.getStylesheets().clear();
                //phase2
            }else if(temp == 2){
                AutoComplete.getLastNode(newValue);
//                System.out.println(AutoComplete.PossibleWords);
//                System.out.println(AutoComplete.PrioritizeWords);
//                System.out.println(AutoComplete.fiveRecentWords());
                tempLabel.setText(ListToString(AutoComplete.fiveRecentWords(), temp));
                scene.getStylesheets().clear();
                scene.getStylesheets().add("styleSuccess.css");
                //phase2
            }
//            System.out.println("textfield changed from " + oldValue + " to " + newValue);
        });

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

        root.setStyle("-fx-font-size: 18;");


        root.getChildren().setAll(wordHBox, tempLabel);
//        root.getChildren().setAll(wordHBox, submitButton, tempLabel, temp);
        root.setAlignment(Pos.CENTER);
        root.setSpacing(15);

        stage.setScene(scene);
    }

    public static String ListToString(ArrayList<String> src, int mode){
        String result = "";
        if(mode == 1 || mode == 2)
            result += "Suggestions:\n";
        else if(mode == 0)
            result += "Correction Suggestions:\n";
        if(src.size() == 0)
            result += "There is no suggestion:(\n";
        else
            for(int i=1;i <= src.size();i++)
                result += i + ". " + src.get(i-1) + "\n";
        return result;
    }
}
