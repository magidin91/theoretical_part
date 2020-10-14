package stream;

import java.util.*;
import java.util.function.Function;
import java.util.stream.*;

import static java.util.Collections.EMPTY_LIST;

public class DemoStream {
    public static void main(String[] args) {
        BaseStream baseStream;
        Stream stream;
        Function x;
        Collector b;
        Collectors v;
        // Stream.empty().collect();
        "твояСтрока".chars();
        //IntStream.range (0, 100).forEach(System.out::println);

        Stream.of(0, 3, 0, 0, 5)
                .peek(j -> String.format("after distinct: %d%n", j))
                .forEach(System.out::print);

        Map<Integer, String> map = Stream.of(50, 55, 69, 20, 19, 52)
                .collect(Collectors.toMap(
                        i -> i % 5,
                        i -> String.format("<%d>", i),
                        (a, g) -> String.join(", ", a, g),
                        HashMap::new
                ));
        ArrayList<NamePhoneEmail> myList = new ArrayList<>();
        Stream<NamePhone> nameAndPhone = myList.stream().map((a) -> new NamePhone(a.name, a.phonenum));

        Collection<String> collection = List.of("1", "2", "3");
        boolean s = collection.stream().anyMatch("a1"::equals);

        collection.stream().findAny().orElse("1");
        collection.parallelStream();
        //collection.stream().filter("a3"::equals).findFirst().get();
        collection.stream().filter((n) -> n.contains("1")).collect(Collectors.toList());

        List<People> peoples = Arrays.asList(new People("Вася", 16, Sex.MAN), new People("Петя", 23, Sex.MAN),
                new People("Елена", 42, Sex.WOMEN), new People("Иван Иванович", 69, Sex.MAN));

        List<People> peoples1 = peoples.stream().filter((p) -> p.getAge() >= 18 && p.getAge() < 27
                && p.getSex() == Sex.MAN).collect(Collectors.toList());
        System.out.println(peoples1);

        peoples.stream().filter((p) -> p.getSex() == Sex.MAN).mapToInt(People::getAge).average().getAsDouble();
        collection.stream().max(String::compareTo).get();

        Collection<StringBuilder> list = Arrays.asList(new StringBuilder("a1"), new StringBuilder("a2"), new StringBuilder("a3"));
        System.out.println("list = " + list);
        list.stream().forEachOrdered((p) -> p.append("_new"));
        System.out.println("forEachOrdered = " + list);

        //Collectors.summingInt, Collectors.averagingInt
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4);
        numbers.stream().collect(Collectors.summingInt(((p) -> p % 2 == 1 ? p : 0)));
        numbers.stream().collect(Collectors.averagingInt((p) -> p - 1));

        //toArray, Collectors.groupingBy, Collectors.groupingBy
        List<String> strings = Arrays.asList("a1", "b2", "c3", "a1");
        strings.stream().distinct().map(String::toUpperCase).toArray(String[]::new);
        strings.stream().collect(Collectors.groupingBy((p) -> p.substring(0, 1)));
        strings.stream().collect(Collectors.groupingBy((p) -> p.equals("")));
        Map<Boolean, List<String>> stringMap = strings.stream().collect(Collectors.groupingBy((p) -> p.equals("a1")));
        System.out.println(stringMap);

        //Collectors.groupingBy: группируем по производителю
        Stream<Phone> phoneStream = Stream.of(new Phone("iPhone X", "Apple", 600),
                new Phone("Pixel 2", "Google", 500),
                new Phone("iPhone 8", "Apple",450),
                new Phone("Galaxy S9", "Samsung", 440),
                new Phone("Galaxy S8", "Samsung", 340));
        // Coollectors.counting, Collectors.summing, maxBy и minBy, mapping
        // Map<String, List<Phone>> phonesByCompany = phoneStream.collect(Collectors.groupingBy(Phone::getCompany));
        // Map<String, Long> phonesByCompany= phoneStream.collect(Collectors.groupingBy(Phone::getCompany, Collectors.counting()));
        // Map<String, Integer> phonesByCompany = phoneStream.collect(Collectors.groupingBy(Phone::getCompany, Collectors.summingInt(Phone::getPrice)));
        //вместо получения листа телефонов, маппим телефоны в их названия и также получаем лист
        //Map<String, List<String>> phonesByCompany = phoneStream.collect(
        // Collectors.groupingBy(Phone::getCompany, Collectors.mapping(Phone::getName, Collectors.toList())));
        Map<String, Set<Integer>> phonesByCompany = phoneStream.collect(
                Collectors.groupingBy(Phone::getCompany, Collectors.mapping(Phone::getPrice, Collectors.toSet())));
        System.out.println(phonesByCompany);
    }
}
