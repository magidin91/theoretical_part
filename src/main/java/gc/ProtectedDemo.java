package gc;

public class ProtectedDemo {
    public static void main(String[] args) {
        new ProtectedDemo().protectedMethod();
    }
    protected void protectedMethod(){
        System.out.println("works");
    }
}
