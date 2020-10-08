package collections.generics;

import java.util.List;
import java.util.Spliterator;

public class SpliteratorDemo {
    public static void main(String[] args) {
        List <Double> vals = List.of(1.0, 2.0, 3.0);
// вызвать метод tryAdvance() для вывода содержимого списочного массива vals
        Spliterator<Double> spltitr = vals.spliterator();
        while(spltitr.tryAdvance((n) ->System.out.println(n)));
    }
}
