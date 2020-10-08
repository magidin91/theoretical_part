package collections.generics;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class Generics {
    public static void main(String[] args) {
        SomeType st = new SomeType();
        List<Integer> list = Arrays.asList(1); // для листа выбирается более специфичный Лист, хотя Collection<String> параметризован
        st.test(list);
    }
    public static class SomeType {
        public void test(Collection<String> collection) {
            for (String element : collection) {
                System.out.println(element);
            }
        }
        public void test(List<Integer> collection) {
            for (Integer element : collection) {
                System.out.println(element);
            }
        }
    }
}  