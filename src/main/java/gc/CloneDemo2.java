package gc;

public class CloneDemo2 implements Cloneable {
    public static void main(String[] args) throws CloneNotSupportedException {
        // корнируем объект без переопределения clone()
        CloneDemo2 object = new CloneDemo2();
        CloneDemo2 clonedObject = (CloneDemo2) object.clone();
        Object o;
    }
}
