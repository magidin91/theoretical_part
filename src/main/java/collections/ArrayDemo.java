package collections;

import java.util.Arrays;

public class ArrayDemo {
    public static void main(String[] args) {
        int[] array1 = {1,2,3};
        int[] array2 = {1,2,3};
        System.out.println(array1.equals(array2));
        System.out.println(Arrays.equals(array1,array2));
    }
}
