package collections.generics;

import java.util.ArrayList;

public class Raw2 {
    public static void main(String[] args) {
        ArrayList<String> strings = new ArrayList<>();
        ArrayList arrayList = new ArrayList<Integer>();
        arrayList.add("hi"); //добавили String в ArrayList<Integer>
        System.out.println(arrayList.get(0)); // получили String из ArrayList<Integer>
    }
}
