package gc;

public class Demo {
    public static void main(String[] args) {
        new Demo().protectedMethod();
    }


    protected void protectedMethod(){
        System.out.println("works");
    }
}
