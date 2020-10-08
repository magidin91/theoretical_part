package collections.generics;

import java.util.Arrays;
import java.util.List;

public class Helper2 {
    public List<Integer> numbers(){
        return Arrays.asList(1,2);
    }

    public static void main(String[] args) {
        Helper2 helper = new Helper2();
        for (Integer value: helper.numbers()){
            System.out.println(value);
        }
    }
}
