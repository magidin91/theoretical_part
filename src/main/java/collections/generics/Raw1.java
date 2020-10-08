package collections.generics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Raw1 {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("Hello", "World");
        List<Integer> data = new ArrayList(list);
        Integer value = data.get(0); //получим ошибку рантайм, если из List<Integer> data захотим получить Integer
        //String value = data.get(0); // - получим ошибку компиляции, т.к. работает приведение к Integer
        System.out.println(data.get(0));  // т.е. мы получаем из List<Integer> data String значение
    }
}
