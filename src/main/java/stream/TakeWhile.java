package stream;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TakeWhile {
    public static void main(String[] args) {
        List<Integer> numbers = Stream.of(1, 2, 0, 3, 5, 6, 7, 1, 2).takeWhile(e -> e < 4).collect(Collectors.toList());
        System.out.println(numbers);
        List<Integer> numbers2 = Stream.of(1, 2, 0, 3, 5, 6, 7, 1, 2).dropWhile(e -> e < 4).collect(Collectors.toList());
        System.out.println(numbers2);
        //Stream.of(null).collect(Collectors.toList());

        ArrayList<String> list = new ArrayList<>(List.of("a", "b", "c", "d"));
        Stream.ofNullable(list).forEach(System.out::println);
        Optional x;
    }
}
