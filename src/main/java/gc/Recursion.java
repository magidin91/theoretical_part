package gc;

public class Recursion {
    public static void main(String[] args) {
        new Recursion().recursion();
    }
    public void recursion(){
        System.out.println("I");
        recursion();
    }
}
