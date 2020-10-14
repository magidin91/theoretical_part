package stream;

import java.util.function.Consumer;

public class Lambda {
    public void print(String string) {
        System.out.println(string);
    }

    public static void main(String[] args) {
        // Consumer<String> printer = System.out::println; //создали лямбду
        Consumer<String> printer = string -> new Lambda().print(string); //создали лямбду c использованием метода класса
        printer.accept("Hello"); // вызвали лямбду через функц. интерфейса, передав в его метод аргумент
    }
}
