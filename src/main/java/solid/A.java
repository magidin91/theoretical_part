package solid;

public class A implements SomeInterface {
}

class B {
    public static void main(String[] args) {
        SomeInterface someInterface = new A();
        // SomeInterface someInterface2 = new B();
    }
}

interface SomeInterface {
}
