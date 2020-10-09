package collections;

import java.util.*;
import java.util.stream.Collectors;
//Даны две строки. Нужно проверить, что вторая строка получилась методом перестановок символов в первой строке.
public class FreezeStr {
    public static boolean eq(String left, String right) {
        if (left.length() != right.length()) {
            return false;
        }
        List<Character> charLeft = left.chars().mapToObj(c -> (char) c).collect(Collectors.toList());
        List<Character> charRight = right.chars().mapToObj(c -> (char) c).collect(Collectors.toList());

        for (Character character : charRight) {
            if (!charLeft.remove(character)) {
                return false;
            }
        }
        return true;
    }
}