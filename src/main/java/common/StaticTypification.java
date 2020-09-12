package common;
//статическая типизация во время компиляции
public class StaticTypification {
    public static void main(String[] args) {
        new Beta().getThis();// работает, т.к. B наследнки А
//        new Beta().getThis().doBeta(); /* ошибка компиляции
//        Проблема заключается в том, что ее исходный тип Аlfa и компилятор не знает реальный тип во время компиляции, поэтому он видит объект типа Аlfa*/
        ((Beta) new Beta().getThis()).doBeta(); //привели результат к бетта перед вывзовом метода
        ((Omega) new Beta().getThis()).doOmega(); //пройдет статическую компиляцию, т.к. альфа можно привести к омега, но упадет во время выполнения
    }
}
