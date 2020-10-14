package interviews;

public class StringReverse {
    public static void main(String[] args) {
        System.out.println(new StringReverse().reverse("hello"));
    }

    public String reverse(String str) {
        StringBuilder result = new StringBuilder();
        int i;
        for (i = str.length() - 1; i >= 0; i--)
            result.append(str.charAt(i));
        return result.toString();
    }
}
