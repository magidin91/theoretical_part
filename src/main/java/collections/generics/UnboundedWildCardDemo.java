package collections.generics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UnboundedWildCardDemo {
    public static void main(String[] args) {
        List<?> li = Arrays.asList(1, 2, 3);
        List list;
        list = new ArrayList<Integer>();
        list.add(1);
        list.add("hello");
    }
}
