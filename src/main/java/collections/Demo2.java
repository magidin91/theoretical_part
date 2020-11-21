package collections;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Stack;

public class Demo2 {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>(List.of(1, 2, 3));
        list.removeIf(el -> el > 0);
        System.out.println(list);
        Stack g;
    }
}
class Winner{
    int a;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Winner winner = (Winner) o;
        return a == winner.a;
    }

    @Override
    public int hashCode() {
        return Objects.hash(a);
    }
}
