package collections.generics;

import java.util.List;

public class Wild1 {
    public static void main(String[] args) {
        List<? super Number> numLis = List.of(1, 2, 2.0);
        Object object = numLis.get(0);
        //Number object = numLis.get(0);
        System.out.println(object);
        List<? extends Number> intLis = List.of(1, 2);
        Number number = intLis.get(0);
        intLis.add(null);
        //Number object = numLis.get(0);
        System.out.println(number);
    }
}
