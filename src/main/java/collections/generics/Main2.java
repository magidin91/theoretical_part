package collections.generics;

public class Main2 {
//Попытка поместить в массив значение неверного типа приведет к исключению:
   public static void main(String[] args) {
       Object x[] = new String[3];
       x[0] = new Integer(222);
   }
}