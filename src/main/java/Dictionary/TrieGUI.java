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

        wordTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            newValue = newValue.toLowerCase();
            int temp = Main.originalTrie.isExist(newValue);
            if(temp == 0){
                tempLabel.setText(ListToString(Correction.correctionSuggestion(newValue), temp));
                scene.getStylesheets().clear();
                scene.getStylesheets().add("styleError.css");
            }else if(temp == 1){
                tempLabel.setText("");
                if(!newValue.equals("")) {
                    AutoComplete.getLastNode(newValue);
                    tempLabel.setText(ListToString(AutoComplete.fiveRecentWords(), temp));
                }
                scene.getStylesheets().clear();
            }else if(temp == 2){
                AutoComplete.getLastNode(newValue);
                tempLabel.setText(ListToString(AutoComplete.fiveRecentWords(), temp));
                scene.getStylesheets().clear();
                scene.getStylesheets().add("styleSuccess.css");
            }
        });

        HBox wordHBox = new HBox(wordLabel, wordTextField);
        wordHBox.setAlignment(Pos.CENTER);
        wordHBox.setSpacing(20);

        root.setStyle("-fx-font-size: 18;");


        root.getChildren().setAll(wordHBox, tempLabel);
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
