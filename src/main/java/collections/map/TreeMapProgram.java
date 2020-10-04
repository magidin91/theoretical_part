package collections.map;

import java.util.*;

public class TreeMapProgram {
      
    public static void main(String[] args) {
        //TreeMap без компаратора, сортируемая в натуральном порядке
        TreeMap<Integer, String> states = new TreeMap<Integer, String>();
       states.put(10, "Germany");
       states.put(2, "Spain");
       states.put(14, "France");
       states.put(3, "Italy");
         
       // получим объект по ключу 2
       String first = states.get(2);
       // перебор элементов
       for(Map.Entry<Integer, String> item : states.entrySet()){
         
           System.out.printf("Key: %d  Value: %s \n", item.getKey(), item.getValue());
       }
       // получим весь набор ключей
       Set<Integer> keys = states.keySet();
       // получить набор всех значений
       Collection<String> values = states.values();
         
       // получаем все объекты, которые стоят после объекта с ключом 4
       Map<Integer, String> afterMap = states.tailMap(4);
         
       // получаем все объекты, которые стоят до объекта с ключом 10
       Map<Integer, String> beforeMap = states.headMap(10);
         
       // получим последний элемент дерева
       Map.Entry<Integer, String> lastItem = states.lastEntry();
         
       System.out.printf("Last item has key %d value %s \n",lastItem.getKey(), lastItem.getValue());

       //TreeMap с компаратором
       Map<Person, String> people = new TreeMap<>(new PersonComparator());
       people.put(new Person("Tom"), "1240i54");
       people.put(new Person("Bill"), "1564i55");
       people.put(new Person("Nick"), "4540i56");
         
       for(Map.Entry<Person, String> item : people.entrySet()){
         
           System.out.printf("Key: %s  Value: %s \n", item.getValue(), item.getKey());
       }
    }
}
class Person {
    private String name;
    public Person(String name){
          
        this.name = name;
    }
    String getName(){return name;}

    public String toString (){
        return name;
    }
}

class PersonComparator implements Comparator <Person> {

    @Override
    public int compare(Person o1, Person o2) {
        return o1.getName().compareTo(o2.getName());
    }
}