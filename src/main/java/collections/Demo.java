package collections;

import java.util.*;

public class Demo {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        Vector a;
        Stack stack;
        LinkedList b;
        Queue<String> X;
        Deque<String> X1;
        ArrayDeque<String> X3;
        PriorityQueue<String> X4;
        List.of(1, 3, 3);
        Arrays.asList(1, 2, 3);
        new HashMap();
        new Hashtable();
        new TreeMap();
        new TreeSet();
        new LinkedHashMap();
        Set x;
        new HashSet<>();
        new LinkedHashSet();
        Map.of(1, 1);
        //Set.of(null, 1);
        //List.of(null, 1);
        HashSet<Integer> hashSet = new HashSet<>();
        hashSet.add(1);
        hashSet.add(1);
        new PriorityQueue();
        Iterator iterator;
        new WeakHashMap();
        Spliterator<Integer> spliterator = list.spliterator();
        while (spliterator.tryAdvance(System.out::println)) ;
        new Hashtable<>();
        String thisString;
        HashSet<Integer> hashSet1 = new HashSet<>(Set.of(1, 2, 3));
        System.out.println(hashSet1);
        hashSet.add(1);
        System.out.println(hashSet1);
    }
}
