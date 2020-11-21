package all;

public class ConcatDemo {
    public static void main(String[] args) {
        String a = "text";
        String b = "text";
        a += 1;
        b += 1;
        System.out.println(a != b);
        Double d1 = 1d;
        Double d2 = 1d;
        System.out.println(d1 == d2);
    }
}
