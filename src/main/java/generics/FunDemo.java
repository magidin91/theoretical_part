package generics;

import java.util.ArrayList;
import java.util.List;

public class FunDemo {
    public static void main(String[] args) {
        ArrayList<Long> myList = new ArrayList<>();
        List tr = myList;
        tr.add("ha-ha-ha");
        List<Double> lst = (ArrayList<Double>) tr;
        lst.add(2.5);
        System.out.println(tr.size());
        System.out.println(tr.get(0));
        System.out.println(tr.get(1));
    }
}
