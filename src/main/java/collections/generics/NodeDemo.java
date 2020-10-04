package collections.generics;

public class NodeDemo {
    public static void main(String[] args) {
        MyNode mn = new MyNode(5);
        Node n = mn; // Сырой тип - компилятор генерирует предупреждение unchecked warning
        n.setData("Hello");
        Integer x = mn.data;
    }
}
