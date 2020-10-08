package collections.generics;

import java.util.Arrays;
import java.util.List;

public class Helper<T> {
    public List<Integer> numbers(){
        return Arrays.asList(1,2);
    }

//    public static void main(String[] args) {
//        Helper helper= new Helper<>();
//        for (Integer value: helper.numbers()){
//            System.out.println(value);
//        }
//    }
/*Если класс параметризован, то даже если параметр не используется нигде, то если мы используем сырой тип, то данные о дженериках стираются.
Т.е. в нашем примере мы получим List<Object> numbers.
Чтобы метод в результате возвращал List <Integer> number, объект класса должен быть параметризован Helper<?> helper. */
}
