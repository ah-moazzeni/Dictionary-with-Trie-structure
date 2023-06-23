package Dictionary;

import javafx.application.Application;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Main extends Application{
    static Trie originalTrie = new Trie();
    static Trie reversedTrie = new Trie();
    static ArrayList<String> words =  FileHandling.readFromFile();
    public static void main(String[] args) {
        for(String word:words){
            originalTrie.addWord(word);
            String reversedWord = "";
            Character ch;
            for (int i=0; i<word.length(); i++)
            {
                ch= word.charAt(i); //extracts each character
                reversedWord= ch+reversedWord; //adds each character in front of the existing string
            }
            reversedTrie.addWord(reversedWord);
        }

        originalTrie.printAllTrie();
        reversedTrie.printAllTrie();

        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
//        stage.show();
        TrieGUI.firstScene(stage);
    }
}
