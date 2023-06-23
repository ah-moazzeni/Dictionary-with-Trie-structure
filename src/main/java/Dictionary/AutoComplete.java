package Dictionary;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;
import java.util.stream.Collectors;

public class AutoComplete {
    static ArrayList<String> PossibleWords = new ArrayList<>();
    static String pre = "";
    static HashMap<Integer, ArrayList<String>> PrioritizeWords = new HashMap<>();

    public static void getLastNode(String prefix){
        pre = prefix;
        Node temp = Main.originalTrie.root;

        for(int i=0;i<prefix.length();i++)
            temp = temp.letters.get(prefix.charAt(i)-97);

        getPossibleWords(temp);
    }

    static Stack<Character> characters = new Stack<>();
    static void getPossibleWords(Node root){
        for(Node i : root.letters){
            if (i!= null){
                characters.push(i.getLetter());
                if(i.getFinalLetter()){
                    String temp = pre;
                    temp+=(characters.stream()
                            .map(String::valueOf)
                            .collect(Collectors.joining()));
                    if(!PossibleWords.contains(temp))
                        PossibleWords.add(temp);
                    if(!PrioritizeWords.containsKey(i.getFrequency())){
                        PrioritizeWords.put(i.getFrequency(), new ArrayList<>());
                    }
                    if(!PrioritizeWords.get(i.getFrequency()).contains(temp)) {
                        PrioritizeWords.get(i.getFrequency()).add(temp);
                        if(i.getFrequency()>0)
                            PrioritizeWords.get(i.getFrequency()-1).remove(temp);
                    }
//                    if(PrioritizeWords.containsKey(i.getFrequency())){
//                        PrioritizeWords.get(i.getFrequency()).add(temp);
//                    }else {
//                        PrioritizeWords.put(i.getFrequency(), temp);
//                    }
                }
                getPossibleWords(i);
                characters.pop();
            }
        }
    }
}
