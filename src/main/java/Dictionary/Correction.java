package Dictionary;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Stack;
import java.util.stream.Collectors;

public class Correction {
    static void findAllWords(Node node, Stack characters, ArrayList<Word> res, String word, Boolean reverse) {

        for (Node i : node.letters) {
            if (i != null) {
                characters.push(i.getLetter());
                if (i.getFinalLetter()) {
                    String temp = characters.stream()
                            .map(String::valueOf)
                            .collect(Collectors.joining()).toString();
                    if (reverse) {
                        String reversedWord = "";
                        Character ch;
                        for (int j = 0; j < temp.length(); j++) {
                            ch = temp.charAt(j); //extracts each character
                            reversedWord = ch + reversedWord; //adds each character in front of the existing string
                        }
                        res.add(new Word(i.getFrequency(), distance(temp, word), reversedWord));
                    } else {
                        res.add(new Word(i.getFrequency(), distance(temp, word), temp));

                    }
                }
                findAllWords(i, characters, res, word, reverse);
                characters.pop();
            }
        }
    }

    static ArrayList<String> findLongestPrefix(String word, Trie trie, ArrayList res, Boolean reverse,Boolean secondList) {

        Stack<Character> characters = new Stack<>();

        Node root = trie.root;
        Node second = null;
        for (int i = 0; i < word.length(); i++) {
            int index = word.charAt(i);
            index -= 97;
            if (root.letters.get(index) == null) {
                break;
            } else {
                second = root;
                root = root.letters.get(index);
                characters.push(root.getLetter());
            }

        }
        if(secondList){
            characters.pop();
            findAllWords(second, characters, res, word, reverse);

        }
        else {
            findAllWords(root, characters, res, word, reverse);
        }
        return res;
    }

    static int distance(String templateWord, String word) {
        int distance = 0;
        if (templateWord.length() > word.length()) {
            String temp = templateWord;
            templateWord = word;
            word = temp;
        }
        for (int i = 0; i < templateWord.length(); i++) {
            int first = word.charAt(i);
            int second = templateWord.charAt(i);
            if (first != second) {
                distance++;
            }
        }
        distance += word.length() - templateWord.length();


        return distance;
    }

    static ArrayList<String> correctionSuggestion(String word) {
        ArrayList<Word> res = new ArrayList<>();
        findLongestPrefix(word, Main.originalTrie, res, false,false);
        String reversedWord = "";
        Character ch;
        for (int i = 0; i < word.length(); i++) {
            ch = word.charAt(i); //extracts each character
            reversedWord = ch + reversedWord; //adds each character in front of the existing string
        }
        findLongestPrefix(reversedWord, Main.reversedTrie, res, true,false);

        Quicksort.quickSort(res, 0, res.size() - 1);
        ArrayList<String> result = new ArrayList<>();
        for (int i = 0; i < res.size(); i++) {
            Boolean flag = false;
            for (String j : result){
                if (Objects.equals(res.get(i).word, j)){
                    flag =true;
                    break;
                }
            }
            if (!flag){

                result.add(res.get(i).word);
            }
            if (i == 4) {break;}
        }
        int size = res.size();
        System.out.println(result);
        if(size<5){
            res.clear();
            findLongestPrefix(word, Main.originalTrie, res, false,true);
            findLongestPrefix(reversedWord, Main.reversedTrie, res, true,true);
            Quicksort.quickSort(res, 0, res.size() - 1);
            for (int i = 0; i < res.size(); i++) {
                Boolean flag = false;
                for (String j : result){
                    if (Objects.equals(res.get(i).word, j)){
                        flag =true;
                        break;
                    }
                }
                if (!flag){

                    result.add(res.get(i).word);
                }
                if (i == 4-size) {break;}
            }
        }

        return result;
    }
}
