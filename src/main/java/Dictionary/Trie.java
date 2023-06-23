package Dictionary;
import java.util.Stack;
import java.util.stream.Collectors;
public class Trie {
    Node root = new Node('.',false,null);


    void addWord(String word){
        Node root = this.root;

        for (int i = 0; i < word.length(); i++) {
            int index =word.charAt(i);
            if(Character.isUpperCase(word.charAt(i))){
                index+=32;
            }

            index-=97;
            Boolean finalLetter = false;
            if (i==word.length()-1){
                finalLetter=true;
            }
            if(root.letters.get(index)==null) {
                root.letters.set(index, new Node(word.charAt(i), finalLetter, root));
            }else{
                if(finalLetter==true){
                    root.letters.get(index).setFinalLetter(true);
                }
            }
            root=root.letters.get(index);
        }
    }
    void print(Node root , Stack characters){
        for(Node i : root.letters){
            if (i!= null){
                characters.push(i.getLetter());
                if(i.getFinalLetter()){
                    System.out.println(characters.stream()
                            .map(String::valueOf)
                            .collect(Collectors.joining()));
                }
                print(i,characters);
                characters.pop();
            }
        }
    }
    void printAllTrie(){
        Stack<Character> characters = new Stack<>();
        print(this.root , characters);
    }
    Boolean isExist(String word){
        Node root = this.root;
        for (int i = 0; i < word.length(); i++) {
            int index = word.charAt(i);
            index -= 97;
            Boolean finalLetter = false;
            if (i == word.length() - 1) {
                finalLetter = true;
            }
            if (root.letters.get(index) == null) {
                return false;
            }
            root = root.letters.get(index);
        }
        return true;
    }
}
