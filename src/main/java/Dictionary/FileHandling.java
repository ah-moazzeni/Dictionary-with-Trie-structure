package Dictionary;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

public class FileHandling {
    static ArrayList<String> readFromFile() {
        BufferedReader reader;
        ArrayList<String> temp = new ArrayList<>();
        try {
            reader = new BufferedReader(new FileReader("words.txt"));

            String line = reader.readLine();
            temp.add(line);
            while (line != null) {
                line = reader.readLine();
                if(line == null)
                    break;
                temp.add(line);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return temp;
    }
}
