package Dictionary;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;

public class Main extends Application{
     ;
    static Trie reversedTrie ;
    static Trie originalTrie;

    static ObjectInputStream originalIn;
    static ObjectInputStream reversedIn;



    static ArrayList<String> words =  FileHandling.readFromFile();

    public Main() throws IOException, ClassNotFoundException {
    }

    public static void main(String[] args) throws IOException {
            try {
                originalIn = new ObjectInputStream(new FileInputStream("originalTree.txt"));
                reversedIn = new ObjectInputStream(new FileInputStream("reversedTree.txt"));
                originalTrie = (Trie)originalIn.readObject();
                originalIn.close();
                reversedTrie = (Trie)reversedIn.readObject();
                reversedIn.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }



//
//
//        for(String word:words){
//            word=word.toLowerCase();
//            originalTrie.addWord(word);
//            String reversedWord = "";
//            Character ch;
//            for (int i=0; i<word.length(); i++)
//            {
//                ch= word.charAt(i); //extracts each character
//                reversedWord= ch+reversedWord; //adds each character in front of the existing string
//            }
//            reversedTrie.addWord(reversedWord);
//        }
//
        originalTrie.printAllTrie();
        reversedTrie.printAllTrie();
//        System.out.println(Correction.correctionSuggestion("dian"));
        launch(args);


        FileOutputStream fout=new FileOutputStream("originalTree.txt");
        ObjectOutputStream out=new ObjectOutputStream(fout);
        out.writeObject(originalTrie);
        out.flush();
        //closing the stream
        out.close();
        FileOutputStream kout=new FileOutputStream("reversedTree.txt");
        ObjectOutputStream ut=new ObjectOutputStream(kout);
        ut.writeObject(reversedTrie);
        ut.flush();
        //closing the stream
        ut.close();
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.show();
        TrieGUI.mainScene(stage);
    }
}
