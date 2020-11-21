package collections;

import java.util.ArrayList;
import java.util.List;

public class ListIteratorVsIterator {
    public static void main(String[] args) {
        List<User> list = new ArrayList<>(List.of(new User(1), new User(2), new User(3)));
        for (User el : list) {
            el.setNumber(10); //можно изменять сами элементы
            System.out.println(list);
        }
    }
}

class User {
    int number;

    public User(int number) {
        this.number = number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "User{" +
                "number=" + number +
                '}';
    }
}