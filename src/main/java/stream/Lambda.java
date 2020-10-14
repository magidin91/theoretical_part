package stream;

import java.util.function.Consumer;

public class Lambda {
    public static void main(String[] args) {
        Consumer<String> printer = System.out::println; //создали лямбду
        printer.accept("Hello"); // вызвали лямбду через функц. интерфейса, передав в его метод аргумент
    }
}
