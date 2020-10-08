package collections.generics;

import java.util.ArrayList;
import java.util.List;

public class Raw4 {
    public static void main(String[] args) {
        ArrayList<Integer> ints = new ArrayList();
        List<String> strings = new ArrayList(ints);
    }
}
