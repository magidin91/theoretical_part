package gc;

public class FinalizeDemo {
    public void finalize() {
        System.out.println("finalizes");
    }

    public static void main(String[] args) {
        FinalizeDemo finalizeDemo = new FinalizeDemo();
        finalizeDemo = null;
        System.runFinalization();
        Runtime.getRuntime().runFinalization(); // не работают
        for (int i = 0; i < 10; i++) {
            System.out.println("kek");
        }
    }
}
