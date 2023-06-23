package Dictionary;

import java.util.ArrayList;
import java.util.Stack;
import java.util.stream.Collectors;

public class Correction {
    static ArrayList<String> findAllWords(Node node,Stack characters){
        ArrayList<String> res  =new ArrayList<>();
        for(Node i : node.letters){
            if (i!= null){
                characters.push(i.getLetter());
                if(i.getFinalLetter()){
                    res.add(characters.stream()
                            .map(String::valueOf)
                            .collect(Collectors.joining()));
                }
                (i,characters);
                characters.pop();
            }
        }
    }

    static ArrayList<String> findLongestPrefix(String word , Trie trie){

        Stack<Character> characters = new Stack<>();

        Node root = trie.root;
        for (int i = 0; i < word.length(); i++) {
            int index =word.charAt(i);
            index-=97;
            if(root.letters.get(index) == null){
                break;
            }else {
                root = root.letters.get(index);
                characters.push(root.getLetter());
            }
        }




        return res;
    }

    static int distance(String templateWord , String word){
        int distance= 0;
        if (templateWord.length() > word.length()){
            String temp = templateWord;
            templateWord=word;
            word = temp;
        }
        for (int i = 0; i < templateWord.length(); i++) {
            int first = word.charAt(i);
            int second = templateWord.charAt(i);
            if(first != second){
                distance++;
            }
        }
        distance+=word.length()-templateWord.length();


        return distance;
    }
    static ArrayList<String> correctionSuggestion(String word){
        ArrayList<String> res = new ArrayList<>();






        return res;
    }
}
