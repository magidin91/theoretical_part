package collections.generics;

import java.util.ArrayList;
import java.util.List;

public class Pecs {
    public static void main(String[] args) {
        List<? super Integer> list = new ArrayList<Number>(List.of(1, 2.0, 2, 5));
        list.add(5);
        //list.add(5.1); // ошибка
        Object object = list.get(0); // хотя мы и кладем Integer  и ниже, мы не можем получить Integer,
        // т.к лист уже может включать элементы Number,  например Double
    }
}
