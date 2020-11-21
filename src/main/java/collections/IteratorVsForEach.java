package collections;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Удаление элемента во время итерации
 */
public class IteratorVsForEach {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>(List.of(1, 2, 3));
//        for (Integer el: list) {
//            list.remove(el); // ConcurrentModificationException

//        }
        System.out.println(list);
        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()) {
            Integer el = iterator.next(); // получаем элемент
            //iterator.remove(); // удаляем этот элемент
            System.out.println(list);
        }
    }
}
