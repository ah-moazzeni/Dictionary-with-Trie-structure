package Dictionary;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.*;
public class Node implements Serializable {
    private Character letter;
    ArrayList<Node>letters =new ArrayList<Node>(Collections.nCopies(26, null));
    private Node father;
    private Boolean finalLetter;
    private Integer frequency = 0;

    public Integer getFrequency() {
        return frequency;
    }

    public void updateFrequency() {
        this.frequency++;
    }

    public Character getLetter() {
        return letter;
    }

    public Node getFather() {
        return father;
    }

    public Boolean getFinalLetter() {
        return finalLetter;
    }

    public void setLetters(ArrayList<Node> letters) {
        this.letters = letters;
    }

    public void setFather(Node father) {
        this.father = father;
    }

    public void setFinalLetter(Boolean finalLetter) {
        this.finalLetter = finalLetter;
    }

    public Node(Character letter, Boolean finalLetter, Node father) {
        this.letter = letter;
        this.finalLetter = finalLetter;
        this.father=father;
    }
}
