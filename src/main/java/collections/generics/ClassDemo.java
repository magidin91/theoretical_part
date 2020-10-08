package collections.generics;

public class ClassDemo {
    public static void main(String[] args) {
        Integer integer = 1;
        Class <Integer> integerClass = (Class<Integer>) integer.getClass();
        Number n = new Integer(1);
        Class <? extends Number> a = n.getClass();// т.к. дженерики инвариантны
        Class <Integer> b = Integer.class;
        System.out.println(b);
    }
}
