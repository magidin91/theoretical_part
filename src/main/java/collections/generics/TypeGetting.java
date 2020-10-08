package collections.generics;

public class TypeGetting<T> {
    public static void main(String[] args) {
        TypeGetting<Integer> value = new TypeGetting<>();
        System.out.println(value.getClass());
    }
}
