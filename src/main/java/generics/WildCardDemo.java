package generics;

import java.util.ArrayList;
import java.util.List;

public class WildCardDemo {
    public static void main(String[] args) {
        List<? super Animal> animals = new ArrayList<>();
        animals.add(new Cat());
        animals.add(new Dog());
        //animals.add(new Object());
        for(Object object:animals){
            System.out.println(object);
        }
    }
}
