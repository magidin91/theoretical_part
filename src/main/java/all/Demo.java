package all;

import java.util.Set;
import java.util.TreeSet;

public class Demo {
    public static void main(String[] args) {
        Set<String> set = new TreeSet<>();
        set.add("Mila");
        set.add("Mama");
        set.add("Ramu");

        for (String text : set) {
            System.out.println(text);
        }
    }
}
