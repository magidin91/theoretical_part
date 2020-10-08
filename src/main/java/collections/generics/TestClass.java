package collections.generics;

public class TestClass<T> {

   private T value1;
   private T value2;

   public void printValues() {
       System.out.println(value1);
       System.out.println(value2);
   }

   public static <S> TestClass<S> createAndAdd2Values(Object o1, Object o2) {
       TestClass<S> result = new TestClass<>();
       result.value1 = (S) o1;
       result.value2 = (S) o2;
       return result;
   }

   public static void main(String[] args) {
       Double d = 22.111;
       String s = "Test String";
       TestClass<Integer> test = createAndAdd2Values(d, s);
       test.printValues();
   }
}
