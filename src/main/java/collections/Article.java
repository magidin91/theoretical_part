package collections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Article {
    public static boolean generateBy(String origin, String line) {
        String newOrigin = origin
                .replace(",", "")
                .replace(".", "")
                .replace("!", "");
        String[] originArray = newOrigin.split(" ");
        String newLine = line.replace(",", "");
        String[] lineArray = newLine.split(" ");
        List<String> origin_array = new ArrayList<>(Arrays.asList(originArray));
        for (String string : lineArray) {
            if (!origin_array.remove(string)) {
                return false;
            }
        }
        return true;
    }
}