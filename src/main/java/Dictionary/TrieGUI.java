package Dictionary;

import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class TrieGUI {
    public static void mainScene(Stage stage){
        VBox root = new VBox();
        Scene scene = new Scene(root, 400, 700);

        stage.setTitle("Dictionary");
        stage.setMinHeight(700);
        stage.setMinWidth(400);
        stage.setMaxHeight(700);
        stage.setMaxWidth(400);

        Image image=new Image("search-icon.png");
        ImageView  imageView=new ImageView();
        imageView.setImage(image);

        Label wordLabel = new Label("Word: ");
        wordLabel.setCursor(Cursor.TEXT);
        wordLabel.setTooltip(new Tooltip("WORD"));

        TextField wordTextField = new TextField();
        wordTextField.setTooltip(new Tooltip("Enter your word..."));


        Label tempLabel = new Label();

        Button submitButton = new Button("Submit");
        submitButton.setCursor(Cursor.HAND);
        submitButton.setTooltip(new Tooltip("Press to Submit"));

        AtomicInteger temp = new AtomicInteger();
        AtomicReference<String> word = new AtomicReference<>("");

        submitButton.setOnAction(actionEvent -> {
            if(temp.get() == 2){
                Main.originalTrie.updateFreq(String.valueOf(word));
            }
        });

        wordTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            newValue = newValue.toLowerCase();
            newValue = newValue.replaceAll("[^a-zA-Z0-9]", "");
            wordTextField.setText(newValue);
            temp.set(Main.originalTrie.isExist(newValue));
            word.set(newValue); //for update frequency
            if(temp.get() == 0){
                tempLabel.setText(ListToString(Correction.correctionSuggestion(newValue), temp.get()));
                scene.getStylesheets().clear();
                scene.getStylesheets().add("styleError.css");
            }else if(temp.get() == 1){
                tempLabel.setText("");
                if(!newValue.equals("")) {
                    AutoComplete.getLastNode(newValue);
                    tempLabel.setText(ListToString(AutoComplete.fiveRecentWords(), temp.get()));
                }
                scene.getStylesheets().clear();
            }else if(temp.get() == 2){
                AutoComplete.getLastNode(newValue);
                tempLabel.setText(ListToString(AutoComplete.fiveRecentWords(), temp.get()));
                scene.getStylesheets().clear();
                scene.getStylesheets().add("styleSuccess.css");
            }
        });

        HBox wordInputHBox = new HBox(imageView, wordTextField);
        wordInputHBox.setAlignment(Pos.CENTER);

        HBox wordHBox = new HBox(wordInputHBox, submitButton);
        wordHBox.setAlignment(Pos.CENTER);
        wordHBox.setSpacing(15);

        root.setStyle("-fx-font-size: 18;-fx-background-color: #c2c2c2;");

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
