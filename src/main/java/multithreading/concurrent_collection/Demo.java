package multithreading.concurrent_collection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Demo {
    public static void main(String[] args) {

        List<Integer> list = new ArrayList<>(List.of(1, 2, 3));
        List<Integer> synchronizedList = Collections.synchronizedList(list);
        synchronizedList.add(4);
        System.out.println(synchronizedList);
        System.out.println(list);
        User user = new User(2);
        System.out.println(user);
        System.out.println(user.hashCode());
        user.i = 5;
        System.out.println(user);
        System.out.println(user.hashCode());
        new Thread().yield();
    }
}

class User {
    int i;

    public User(int i) {
        this.i = i;
    }

    @Override
    public String toString() {
        return "User{" +
                "i=" + i +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return i == user.i;
    }

    @Override
    public int hashCode() {
        return Objects.hash(i);
    }
}
