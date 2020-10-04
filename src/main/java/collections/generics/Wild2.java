package collections.generics;

import java.util.ArrayList;
import java.util.List;

public class Wild2 {
    public static void main(String[] args) {
        ArrayList<String> strings = new ArrayList<>();
        ArrayList arrayList = new ArrayList();
        arrayList = strings; // Ok
        strings = arrayList; // Unchecked assignment
        arrayList.add(1);
        System.out.println(arrayList.get(0));
    }
    public static <T> Object getFirst(List<? super T> list) {
        return list.get(0);
    }
}
