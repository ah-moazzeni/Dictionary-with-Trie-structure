package Dictionary;

import java.util.*;
import java.util.stream.Collectors;

public class AutoComplete {
    static ArrayList<String> PossibleWords = new ArrayList<>();
    static String pre = "";
    static HashMap<Integer, ArrayList<String>> PrioritizeWords = new HashMap<>();

    public static void getLastNode(String prefix){
        pre = prefix;
        PossibleWords.clear();
        PrioritizeWords.clear();
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
                    }
                }
                getPossibleWords(i);
                characters.pop();
            }
        }
    }

    static ArrayList<String> fiveRecentWords(){
        ArrayList<String> result = new ArrayList<>();
        boolean flag = false;

        List<Integer> keys = new ArrayList<>(PrioritizeWords.keySet());
        ListIterator<Integer> itr = keys.listIterator(keys.size());
        while (itr.hasPrevious()) {
            int i = itr.previous();
            if (PrioritizeWords.get(i) != null) {
                for (int j = 0; j < PrioritizeWords.get(i).size(); j++) {
                    result.add(PrioritizeWords.get(i).get(j));
                    if (result.size() == 5) {
                        flag = true;
                        break;
                    }
                }
            }
            if (flag)
                break;
        }
        return result;
    }
}
