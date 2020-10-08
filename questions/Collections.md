## Collections Pro    

[подробнее](https://itsobes.ru/JavaSobes/tags/dzheneriki/)  
Доабвить после многопоточки - concurrent  коллекции: https://habr.com/ru/company/luxoft/blog/157273/


вставить картинку сложности алгоритмво из этйо статьи https://habr.com/ru/post/237043/
вставить картинку иерархии мап и коллекшен, Интерфейс List 

## Как можно получить тип Generics. Class<Object = Integer.Class>

+ [Что такое генерики](#Что-такое-генерики)
+ [Дженерики в коллекциях](#Дженерики-в-коллекциях)
+ [Ограничения обобщений](#Ограничения-обобщений)
+ [Raw Type](#Raw-Type)
+ [Обобщенные методы, конструкторы, интерфейсы](#Обобщенные-методы-конструкторы-интерфейсы)
+ [Wildcards](#Wildcards)
+ [Type Erasure (затирание типов)](#Type-Erasure-затирание-типов)
+ [Bridge methods](#Bridge-methods)
+ [Heap Pollution](#Heap-Pollution)
+ [Вывод типа (Type Inference)](#Вывод-типа-Type-Inference)
+ [Тонкие примеры](#Тонкие-примеры)
+ [Как можно получить тип Generics](#Как-можно-получить-тип-Generics)
+ [Iterable (итерируемый) и Iterator (итератор)](#Iterable-итерируемый-и-Iterator-итератор)
+ [Comparator](#Comparator)
+ [Что такое коллекции](#Что-такое-коллекции)
+ [Расскажите реализации данных очередей и стеков](#Расскажите-реализации-данных-очередей-и-стеков)
+ [Расскажите реализации интерфейса List](#Расскажите-реализации-интерфейса-List) 
+ [ArrayList](#ArrayList)
+ [LinkedList](#LinkedList)
+ [Отличие ArrayList от LinkedList](#Отличие-ArrayList-от-LinkedList)
+ [Расскажите про реализации Map](#Расскажите-про-реализации-Map)
+ [Расскажите про методы Object hashCode и equals](#Расскажите-про-методы-Object-hashCode-и-equals)
+ [Хешкоды разных классов](#Хешкоды-разных-классов)
+ [Хеш-таблица (Hashtable)](#Хеш-таблица-Hashtable)
+ [HashMap](#HashMap)
+ [Расскажите, что такое коллизии в Map. Как с ними бороться](#Расскажите-что-такое-коллизии-в-Map-Как-с-ними-бороться)
+ [Двоичное Дерево Поиска (Binary Search Tree)](#Двоичное-Дерево-Поиска-Binary-Search-Tree)
+ [Какие существуют алгоритмы обхода дерева](#Какие-существуют-алгоритмы-обхода-дерева)
+ [Расскажите про реализации деревьев](#Расскажите-про-реализации-деревьев)
+ [Красно-черное дерево](#Красно-черное-дерево)
+ [SortedMap](#SortedMap)
+ [TreeMap](#TreeMap)
+ [NavigableMap](#NavigableMap)
+ [Расскажите реализации интерфейса Set](#Расскажите-реализации-интерфейса-Set)
+ [LinkedHashМap и LinkedHashSet](#LinkedHashМap-и-LinkedHashSet)
+ [Отличие Set от List](#Отличие-Set-от-List)
+ [Расскажите, что такое анализ алгоритма](#Расскажите-что-такое-анализ-алгоритма)
+ [Какая временная сложность алгоритмов(O-нотация) добавления, замены и удаления в каждой из коллекций. С чем связаны отличия](#Какая-временная-сложность-алгоритмов-O-нотация-добавления-замены-и-удаления-в-каждой-из-коллекций-С-чем-связаны-отличия)
+ [Коллекции общее](#Коллекции-общее)

## Что такое генерики
[подробнее](https://urvanov.ru/2016/04/28/java-8-%d0%be%d0%b1%d0%be%d0%b1%d1%89%d0%b5%d0%bd%d0%b8%d1%8f/#typeerasure)  

Представляет из себя возможность указать обобщенный тип в классе или методе,  которое в дальнейшем 
можно использоваться в рамках класса (метода).  
  
генерики обеспечивают безопасность типов и повторное использование кода. 
Т.е. пишем один алгоритм и используем в нем разные типы данных.  
Дженерики играют ключевую роль в использовании в коллекциях.  
Информация о дженериках доступна в исходном коде и на этапе компиляции и стирается в runtime.  

## Причины использования генериков 
+ Типобезопасность:
Ошибки, связанные с некорректным использованием типов, теперь обнаруживаются на этапе компиляции.  
Проверка передаваемого значения: если аргумент метода = List<Account> accounts, то компилятор выдаст ошибку, 
если кто-то попробует передать в этот метод список объектов, отличных от класса Account.     
+ Отпала необходимость приведения типа:  
До этого сырой List list возвращал Object и его нужно было приводить к нужному типу, а дженерик - 
List <Integer> list  возвращает значение уже без приведения.  

Пример:    
class BoxPrinter<T> 
После имени класса в угловых скобках "<>" указано имя типа "Т", которое может использоваться внутри класса. 
Фактически Т – это тип, который должен быть определён позже - при создании объекта класса.  
При использовании сырых (Raw) типов, вы теряете преимущество безопасности типов, предоставляемое дженериками.  
Никогда не используйте Raw типы!!!  

**Существует 2 типа дженериков:**  
+ Параметризированый тип.
+ Wildcard.
Используется в сигнатуре методов, а также в коллекциях. Для параметризации класса - не возможно.    

Нельзя параметризовать:
+ Классы, имеющие в предках Throwable (Exceptions)  
Пример:   
class MyException<T> extends Exception {}    
Каждое catch выражение в try-catch проверяет тип полученного исключения во время выполнения программы (что равносильно instanceof).  
Произошло бы стирание типа, и мы бы получили вс е равно просто MyException<Object>. Соответственно, тип должен быть Refiable.   
+ Анонимные классы
+ Enums
+ Нельзя создать массив объектов-дженериков или даже просто типизированный массив. Cоздать переменную T[] x; можно, но не сам объект new T(size).  
new List<T>[]  
new List<String>[]  
new T[]  
(но можно для wildcard List<?>[])  
  
[подробнее](https://habr.com/ru/company/sberbank/blog/416413/)  

[к оглавлению](#collections-pro)  

## Дженерики в коллекциях  
Коллекции явялются дженериками.    
   
**Массивы в Java ковариантны.** Тип S[] является подтипом T[], если S — подтип T. 
Пример присваивания:      
String[] strings = new String[] {"a", "b", "c"};    
Object[] arr = strings;  
arr[0] = 42; // ArrayStoreException. Проблема обнаружилась на этапе выполнения программы 42 - не является строкой.  
В этом недостаток ковариантности массивов Java: мы не можем выполнить проверки на этапе компиляции, и что-то может сломаться уже в рантайме.  

**Дженерики» инвариантны!!!** 
Данный код не скомпилируется из-за первой строки, т.к. дженерики инвариантны:  
```java
  List<Number> intList = new ArrayList<Integer>(); 
        intList.add(new Integer(10)); 
        intList.add(new Float(10.0f)); 
```      
Первая строка смотрится вполне логично, т.к. ArrayList наследуется от List , а Integer наследуется от Number.
Однако допуская такую возможность, мы получили бы ошибку в третьей строке этого кода, ведь динамический тип intList - ArrayList <Integer>,
т.е. происходит нарушение типобезапасности - присвоение значение Float там, где ожидается Integer.
Дженерики созданы, чтобы избегать ошибок такого рода, поэтому существует данное ограничение.   

Но тем не менее это неудобное ограничение и Java поддерживает вайлдкарды для его обхода:  
List<Integer> ints = new ArrayList<Integer>();  
List<? extends Number> nums = ints;  
Это ковариантность. List<Integer> — подтип List<? extends Number>  

List<Number> nums = new ArrayList<Number>();  
List<? super Integer> ints = nums;  
Это контравариантность. List<Number> является подтипом List<? super Integer>.
 
[к оглавлению](#collections-pro)  

## Ограничения обобщений  
**<Т extends суперкласс>**  
В отличие от wildcard, переменные типа могут быть ограничены только сверху (только extends).        
 
**Множественные ограничения (Multiple bounds)**:   
Также можно установить сразу несколько ограничений, используя & или/и | указать несколько классов и/или интерфейсов. 
Обычно ограничивают классом и интерфейсом.  

Пример 1:    
Например, пусть класс Transaction может работать только с объектами, которые одновременно реализуют интерфейс Accountable и являются наследниками класса Person.  
Поскольку мы установили подобное ограничение, то компилятор будет распознавать объекты типа T как объекты типа Person 
(не как Object, который бы был без устанолвения ограничения). Т.е. при стирании типа парметр заменяется на левую границу.    
И в этом случае мы можем вызывать у объектов типа T методы класса Person. И мы бы не смогли бы это сделать, если бы мы не задали подобного ограничения.  

class Person{}  
interface Accountable{}  
class Transaction<T extends Person & Accountable>{}  
Сначала указывается класс, а потом интерфейс.  

Пример 2:      
```java
public static <T extends Comparable<T>> T max(Collection<T> coll) {
  T candidate = coll.iterator().next();
  for (T elt : coll) {
    if (candidate.compareTo(elt) < 0) candidate = elt;
  }
  return candidate;
}
```

В этом примере выражение T extends Comparable<T> определяет T, ограниченную сверху типом Comparable<T>.   

Кроме того, в этом примере T зависит от самого себя, это называется recursive bound — рекурсивная граница.  
Допустимы только аргументы типа, реализующие этот интерфейс.     
[к оглавлению](#collections-pro)  

## Raw Type  
**Чтобы понять, как будет себя вести приложение с сырыми типами, нужно отделить этап компиляции, когда дженерики существуют и 
этап выполнения, когда типы уже стерты. Т.е. проверяем сначала, будет ли ошибка на этапе компиляции с дженериками, а затем в рантайме без дженериков.**        
      
Если мы опустим указание типа в объекте параметризованного класса, например, как здесь: ArrayList arrayList = new ArrayList();   
то ArrayList — это Raw тип параметризованного ArrayList<T>. Используя Raw типы, мы возвращаемся в эру до дженериков и теряем топобезопасность.  

Если мы попытаемся вызвать параметризованный метод у Raw типа, то компилятор выдаст нам предупреждение «Unchecked call».
Если мы попытаемся выполнить присваивание ссылки на параметризованный тип Raw типу, то компилятор выдаст предупреждение «Unchecked assignment». 
Игнорирование этих предупреждений, как мы увидим позже, может привести к ошибкам во время выполнения нашего приложения.  

+ **Если класс параметризован, то даже если параметр не используется нигде, то если мы используем сырой тип, данные о всех дженериках стираются!       
  
Пример 1:  
```java
public class Helper<T> {
    public List<Integer> numbers(){
        return Arrays.asList(1,2);
    }
    public static void main(String[] args) {
        Helper helper= new Helper<>();
        for (Integer value: helper.numbers()){ /* получим ошибку компиляции, т.к. инф-ия  дженериках сотрется 
                                              и лист будет возвращать обеъкты типа Object */
            System.out.println(value);
        }
    }
}  
```
Чтобы метод в результате возвращал List <Integer> number, объект класса должен быть параметризован Helper<?> helper.  
Или можно сделать класс просто непараметризованным.   

Пример 2:     
```java
public class Raw1 {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("Hello", "World");
        List<Integer> data = new ArrayList(list);
        //Integer value = data.get(0); // получим ошибку, т.к. стринг не привести к инт
        //String value = data.get(0); // получим ошибку компиляции, т.к. добавлено кастование к Integer  
        System.out.println(data.get(0));  // получаем из List<Integer>  String значение
    }
}
```
И вот тут-то и кроется коварство. Без diamond синтаксиса компилятор не понимает, что его обманывают, а вот с diamond — понимает. 
  
Пример 3:  
```java
public class Raw2 {
    public static void main(String[] args) {
        ArrayList<String> strings = new ArrayList<>();
        ArrayList arrayList = new ArrayList<Integer>();
        arrayList.add("hi"); //добавили String в ArrayList<Integer>
        System.out.println(arrayList.get(0)); // получили String из ArrayList<Integer> 
    }
}
```  

Пример сработает, т.к. стринг можно добавить в непараметризованный ArrayList arrayList - для компилятора это нормальная операция.  
А во время выполнения  произдойдет стирание типов и new ArrayList<Integer>() сотрется до ArrayList<Object>(), и строка нормально доавбится.     
  
Т.е. в обоих случаях, если мы используем Raw тип в правой или левой части, компилятор не определяет ошибку, и мы ломаем логику.  
Если мы используем Raw – тип, то компилятор не проверяет совместимость типа, и ошибка обнаруживается рантайм!    
 
[к оглавлению](#collections-pro)  

## Обобщенные методы, конструкторы, интерфейсы.  
   
## Обобщенные методы  
public static <T extends Monster> void draw(T monster) {}  
  
```java
 public <T> void print(T[] items){
        for(T item: items){
            System.out.println(item);
        }
    }
```    
Особенностью обобщенного метода является использование универсального параметра в объявлении метода после всех модификаторов и перед типом возвращаемого значения.  
При вызове подобного метода перед его именем в угловых скобках указывается, какой тип будет передаваться на место универсального параметра:  
printer.<String>print(people);  
printer.<Integer>print(numbers);  
Также можно явно не указывать тип парметра, если он может быть выведен из аргументов метода.  

**Кроме того, статические методы не имеют доступа к параметрам типа параметризованных классов; 
если такие методы должны использовать параметризацию, это должно происходить на уровне метода, а не на уровне класса. 
Т.е. когда мы создаем конкретный объект параметризованного класса, статический метод не сможет получить из него параметр, 
т.к. он принадлежит классу и не зависит от экземпляров класса**            

## Обобщенные конструкторы  
Конструкторы как и методы также могут быть обобщенными. В этом случае перед конструктором также указываются в угловых скобках универсальные параметры:
```java
public class Program{
    public static void main(String[] args) {
        Account acc1 = new Account("cid2373", 5000);
        Account acc2 = new Account(53757, 4000);
    }
}
class Account{
    private String id;
    private int sum;
    <T>Account(T id, int sum){
        this.id = id.toString();
        this.sum = sum;
    }
```
В данном случаем мы явно не указываем тип, он выводится из аргумента конструктора.    

## Обобщенные интерфейсы  
При реализации подобного интерфейса есть две стратегии. 
+ Первая стратегия - когда при реализации для универсального параметра интерфейса задается конкретный тип, 
как например, в данном случае это тип String. Тогда класс, реализующий интерфейс, жестко привязан к этому типу.
+ Вторая стратегия представляет определение обобщенного класса, который также использует тот же универсальный параметр.
 
```java
class Account implements Accountable<String>{}
class Account<T> implements Accountable<T>{}
```   
[к оглавлению](#collections-pro)  
     
## Wildcards  

## Unbounded Wildcards (Неограниченные)  
[подробнее](https://docs.oracle.com/javase/tutorial/java/generics/unboundedWildcards.html)  
  
<?>  (почти аналогично <? extends Object>)  
Метасимвольный аргумент обозначается знаком ? и представляет неизвестный тип. 
Обычно wild cards используются с коллекциями, т.к. коллекции инварианты,  когда необходимо получить ковариантоность и контрвариантность.      
   
Для любого конкретного A, List<A> - это подтип List<?>. Важно, что List<Object> and List<?> - не одно и то же,  
вы можете вставить Object или его подтип в List<Object>. **Но вы можете вставить только null в List<?>**. 
Collection<?> — это тоже, что и "? extends Object". Запись вида Collection<?> равносильна Collection<? extends Object>.
Соотвественно нельзя вставить ничего кроме null. Но можно получать значения.  

Например, вот такая строка кода не скомпилируется:    
List<Number> intList = new ArrayList<Integer>(); (инвариантность)   
Но есть возможность похожей реализации:    
List<?> intList = new ArrayList<Integer>();(ковариантность + контрвариантность)        

## Bounded Wildcards (Ограниченные)
[подробнее](https://howtodoinjava.com/java/generics/java-generics-what-is-pecs-producer-extends-consumer-super/)!!!    

**PECS (Producer Extends Consumer Super)**
List<? extends T> list         
1. Если вы хотите прочитать T из списка, вам нужно объявить его <? extends Animal>.  
Но вы не можете добавлять в этот список ничего кроме null.  
Мы точно знаем, что в листе лежат Animal (но не знаем какой именно: ArrayList<Dog>() или ArrayList<Cat>(),
поэтому не можем вставлять туда объекты, только получать => получаем безопасность типов)  
Поэтому компилятор не позволяет вызывать методы, которые могут добавить невалидный тип.    

2. Если вы хотите записать T в список, вам нужно объявить его List<? super Integer>. 
Но нет никаких гарантий того, какой тип объекта вы можете прочитать из этого списка.(**кроме объектов класса Object**)  

В такой тип можно можно положить объекты границы и ниже, т.е. для List<? super Animal> можно положить объекты Animal и ниже, 
но нельзя положить выше, например, Object, т.к. List<? super Animal> list может содержать список ArrayList<Animal>();  

Компилятор выдаст ошибку, если мы попытаемся выполнить неправильную операцию, исключение - добавлении не null в extends
и получении не object из super. (см. пример Wild1 в src)     

3. Если вам нужно и читать, и записывать в коллекцию, вам нужно объявить его конкретным классом, например, List<Integer>.
Использовать подстановочные знаки в этом случае не получится.      
  
Применение вайлкардов может быть весьма полезным. 
Допустим нам необходимо посчитать сумму чисел различного типа, которые хранятся в одном списке:  
```java
public static Double sum(List<? extends Number> numList) { 
    Double result = 0.0; 
    for (Number num : numList) { 
        result += num.doubleValue(); 
    } 
    return result; 
} 
``` 
Double-тип был использован для переменной result т.к. он без проблем взаимодействует с другими числовыми типами (т.е. не будет проблем с приведением типов).    
[Когда использвать Wildcards](https://docs.oracle.com/javase/tutorial/java/generics/wildcardGuidelines.html)      
 
[к оглавлению](#collections-pro)
 
## Type Erasure (затирание типов) 
[подробнее](https://itsobes.ru/JavaSobes/chto-takoe-type-erasure/)  
 
Компилятор удаляет из байткода класс-файла информацию о типах-дженериках. Этот процесс и называется стирание типов. 
Такое решение позволило сохранить обратную совместимость без перекомпиляции кода Java 4.
  
Стирание состоит из трех действий:
+ Если параметры ограничены (bounded), вместо типа-параметра в местах использования подставляется верхняя граница, иначе Object;
+ В местах присвоения значения типа-параметра в переменную обычного типа добавляется каст к этому типу; (самый просто пример - метод get() из любого листа)    
[подробнее](https://blog.xapie.nz/2013/12/01/erasure/)  
Если в коде кастуем через конструкцию с (T) для помещения в параметризованный тип, то такое кастование не сохраняется,
(а кастование возвращаемого значение в методе create к Foo<Integer> сохранится):      
```java
private static class Foo<T> {
    T value1, value2;

    public void print() {
        System.out.println(value1);
        System.out.println(value2);
    }
}

public static <T> Foo<T> create(Object o1, Object o2) {
    Foo<T> result = new Foo<T>();
    result.value1 = (T) o1;
    result.value2 = (T) o2;
    return result;
}

public static void main(String[] args) {
    Double pi = 3.14;
    String hello = "hello";
    Foo<Integer> test = create(pi, hello);
    test.print();
}
``` 
Т.е. на этапе компиляции мы проверили, что Object o1, Object o2 может быть приведен к Integer.
А в рантайме вместо Т получили Object и легко присвоили ему String и Integer.  
  
+ Генерируются bridge-методы.(подробнее в следующем разделе ниже)      
 
Информация о типах стирается только из методов и полей, но остается в метаинформации самого класса.
Получить эту информацию в рантайме можно с помощью рефлекшна, методом getGenericType.  

Reifiable типы - тип является reifiable, если информация о нем полностью доступна во время выполнения программы. В reifiable типы входят: 
+ Примитивные типы (int, long, boolean)
+ Непараметризованные типы (String, Integer)
+ Параметризованные типы, параметры которых представлены в виде unbounded wildcard (неограниченных символов подстановки) (List<?>, Collection<?>)
+ Raw типы (List, ArrayList)
+ Массивы, компоненты которых — Reifiable типы (int[], Number[], List<?>[])  
  
Решение не делать все обобщенные типы доступными во время выполнения — это одно из наиболее важных и противоречивых проектных решений в системе типов Java. 
Так сделали для совместимости с существующим кодом. За миграционную совместимость пришлось платить — 
полная доступность системы обобщенных типов во время выполнения невозможна.  

[подробнее](https://javarush.ru/groups/posts/2315-stiranie-tipov)   
 
[к оглавлению](#collections-pro)  

## Bridge methods  
[подробнее](https://urvanov.ru/2016/04/28/java-8-%d0%be%d0%b1%d0%be%d0%b1%d1%89%d0%b5%d0%bd%d0%b8%d1%8f/#typeerasure)  
  
До стирания:
```java
public class Node<T> {
 
    public T data;
 
    public Node(T data) { this.data = data; }
 
    public void setData(T data) {
        System.out.println("Node.setData");
        this.data = data;
    }
}
 
public class MyNode extends Node<Integer> {
    public MyNode(Integer data) { super(data); }
 
    public void setData(Integer data) {
        System.out.println("MyNode.setData");
        super.setData(data);
    }
}
```
После:  
```java
public class Node {
 
    public Object data;
    public Node(Object data) { this.data = data; }
 
    public void setData(Object data) {
        System.out.println("Node.setData");
        this.data = data;
    }
}
 
public class MyNode extends Node {
    public MyNode(Integer data) { super(data); }
 
    public void setData(Integer data) {
        System.out.println("MyNode.setData");
        super.setData(data);
    }
}
``` 
После стирания типа сигнатуры методов не совпадают. Метод из класса Node становится setData(Object), 
а метод из класса MyNode  становится setData(Integer), поэтому метод setData из класса MyNode не переопределяет метод setData из класса Node.  

Чтобы исправить проблему и сохранить полиморфизм обобщённых типов после стирания типа, компилятор Java генерирует методы-мосты, 
для того чтобы расширение работало как ожидается. Для класса MyNode компилятор генерирует следующий метод-мост setData:  
```java
class MyNode extends Node {
    // Метод-мост, сгенерированный компилятором
    public void setData(Object data) {
        setData((Integer) data);
    }
 
    public void setData(Integer data) {
        System.out.println("MyNode.setData");
        super.setData(data);
    }
```
**Как вы можете видеть, метод-мост, которые имеет ту же сигнатуру, что и метод setData у класса Node делегирует действие к оригинальному методу setData.**  
[подробнее](https://itsobes.ru/JavaSobes/chto-takoe-bridge-method/)  
[подробнее](https://www.ibm.com/developerworks/ru/library/j-jtp01255/)    

[к оглавлению](#collections-pro)  

## Heap Pollution  
Загрязнение кучи возникает, когда переменная параметризованного типа ссылается на объект сырого типа.  
Дженерики защищены инвариантностью. Если попытаться положить в List<Object>  List<String>, эта ошибка произойдет уже на этапе компиляции.  
Heap pollution – ситуация, когда эта защита не срабатывает, и переменная параметризованного типа хранит в себе объект, параметризованный другим типом. Простейший пример:
ArrayList <Integer> ints = new ArrayList();
List<String> strings = new ArrayList(ints);  
Загрязнение кучи возникает при смешивании сырых типов и параметризованных типов, или при осуществлении непроверяемых преобразований типа.  
Документация гарантирует, что heap pollution не может возникнуть без варнинга этапа компиляции.  

[к оглавлению](#collections-pro)  

## Вывод типа (Type Inference)
[подробнее](https://itsobes.ru/JavaSobes/kak-rabotaet-vyvod-tipov/)  
Даймонд-оператор и выведение типа в обобщенном метод.  
  
Это возможность компилятора определять (выводить) тип из контекста. Вот пример кода:  
List<Integer> list = new ArrayList<Integer>();  
+ С появлением даймонд-оператора в  Java 7 мы можем не указывать тип у ArrayList:  
List<Integer> list = new ArrayList<>();
+ выведение типа в обобщенном методе из указанных аргументов.  

[к оглавлению](#collections-pro)  

## Тонкие примеры:
1. Затирание типа: 
Если не указать тип при создании экземпляра класса, то дженерики не учитываются везде, даже в методах, где тип класса не используется.   
Т.о. методы test работают как обычные перегруженные методы, 
т.е. вариант метода определяется наиболее подходящем типом переданной ссылочной переменной. 
Унас есть два метода тест: один принимает List collection, другой - Collection collection, соответственно выбирается более специфический 
List collection
Если указать тип при создании экземпляра, то компилятор начинает различать типы у коллекций указанных в параметрах методов.  
```java
public static class SomeType<T> {
	public <E> void test(Collection<E> collection) {
		for (E element : collection) {
			System.out.println(element);
		}
	}
	public void test(List<Integer> collection) {
		for (Integer element : collection) { //ошибка в райнтайме, т.к. нельзя привести стринг элементы к интежер
			System.out.println(element);
		}
	}

public static void main(String []args) {
SomeType st = new SomeType();
List<String> list = Arrays.asList("test");
st.test(list);
}
}  
```   
(пример SomeType<T> есть в src)  

2. Затирание типа: 
```java
public static void main(String[] args) {

       List<String> strings = new ArrayList<>();
       List<Integer> numbers = new ArrayList<>();
       List<Cat> cats = new ArrayList<>();

       System.out.println(strings.getClass() == numbers.getClass());
       System.out.println(numbers.getClass() == cats.getClass());
   }
``` 
Получим вывод: true, true.  
Но во время преобразования в байт-код все три списка превратились в List<Object>, 
поэтому при выполнении программа говорит нам, что во всех трех случаях у нас используется один и тот же класс.  
  
3. ты не можешь создать массив объектов-дженериков или даже просто типизированный массив:
К примеру, ты не сможешь сделать в Java ничего из этого:  
new List<T>[], new List<String>[], new T[]  
Если мы попытаемся создать массив списков , получим ошибку компиляции generic array creation.
Если бы компилятор позволял нам создавать такие массивы из объектов-дженериков, мы могли бы заработать кучу проблем.  
Если б мы могли создавать массивы дженериков, то мы бы могли получить райнтайм эксепшены подобного рода:
```java
public static void main(String[] args) {
   List<String>[] stringLists = new List<String>[1];  //  (1)
   List<Integer> intList = Arrays.asList(42, 65, 44);  //  (2)
   Object[] objects = stringLists;  //  (3)
   objects[0] = intList;  //  (4)
   String s = stringLists[0].get(0);  //  (5)
} 
```  

## Как можно получить тип Generics.
Существует два основных способа получить тип дженерика:  
1. Метод getGenericSuperclass() класса наследника, определившего конкретный тип родителя-дженерика, возвращает класс, которым параметризован родитель.  
2. Кроме значения типа T передавать еще и объект-дескриптор для этого типа, экземпляр класса Class<T> и сохранять его в поле класса.      
 
1 Способ (Reflection):  
getGenericSuperclass()        

Неужели информации о параметрах generic-классов при компиляции всегда теряется бесследно и не существует во время выполнения?
Нет, существует. Но только в информации о классе, который явно определяет значение параметра в его generic-родителе.  

**Т.е. создаем параметризованный класс, а затем создаем для него класс-наследник. 
Создаем объект наследника и помещаем его в переменную базового типа. И Дальше используем метод getGenericSuperclass().**    

public class FloatList extends ArrayList<Float>{}  
ArrayList<Float> listOfNumbers = new FloatList();  

Теперь, если мы будем анализировать через отражения listOfNumbers, мы сможем узнать, что это объект класса FloatList,   
для которого предком является ArrayList и этот ArrayList внутри FloatList был параметризован классом Float.   
Узнать всё это нам поможет метод Class.getGenericSuperclass().    

```java
Class actualClass = listOfNumbers.getClass();
ParameterizedType type = (ParameterizedType)actualClass.getGenericSuperclass();
System.out.println(type); // java.util.ArrayList<java.lang.Float>
Class parameter = (Class)type.getActualTypeArguments()[0];
System.out.println(parameter); // class java.lang.Float
```
В одну строку то же самое:
Class<T> t = (Class<T>) ((ParameterizedType) наш_объект.getClass().getGenericSuperclass()).getActualTypeArguments()[0];  

2 Способ:    
Мы можем запомнить тип параметра - Class<T> typeParameterClass, передав его явно в конструктор и запомнив в поле класса.  
И класс, и аргумент коснтруктора параметризованы одним типом T,  и благодаря дескриптору Class<T> мы запоминаем тип параметра.  

TestClass<MySecretClass> testString = new TestClass<>(MySecretClass.class);  
  
```java
public class TestClass<T> {
   Class<T> typeParameterClass;

   public TestClass(Class<T> typeParameterClass) {
       this.typeParameterClass = typeParameterClass;
   }

   public T createNewT() throws IllegalAccessException, InstantiationException {
       T t = typeParameterClass.newInstance();
       return t;
   }
   public static void main(String[] args) throws InstantiationException, IllegalAccessException {
       TestClass<MySecretClass> testString = new TestClass<>(MySecretClass.class);
       MySecretClass secret = testString.createNewT();
   }
}
```
Мы просто передали нужный класс-параметр в конструктор нашего класса-дженерика:   
TestClass<MySecretClass> testString = new TestClass<>(MySecretClass.class);  
Благодаря этому мы сохранили информацию о типе-параметре и уберегли ее от стирания. В итоге мы смогли создать объект T.      

3. Если мы хотим через Reflection получить информацию о типе объекта и этот тип не Reifiable, то у нас ничего не получится.  
Но, если, например, этот объект нам вернул какой-то метод, то мы можем получить тип возвращаемого этим методом значения:  
java.lang.reflect.Method.getGenericReturnType()  
**Информация о типе полей класса, параметров методов и возвращаемых ими значений доступна через Reflection.**  

Class <? extends Number> a = n.getClass() Метод getClass() возвращает дженерик Class<?>, поэтому нужно использовать wildcard, т.к. дженерики инвариантны.  
Class <Integer> b = Integer.class; (такая запись валидна)   
[подробнее](https://javarush.ru/groups/posts/2315-stiranie-tipov)  
[подробнее](https://itsobes.ru/JavaSobes/kak-instantsirovat-ekzempliar-generic-tipa/)          

[к оглавлению](#collections-pro)

## Iterable (итерируемый) и Iterator (итератор)  
(стр. 664 полное руководство, Шилдт) 
## Iterable:  
```java
public interface Iterable<T> {
    Iterator<T> iterator();

    /**
     * Performs the given action for each element of the {@code Iterable}
     * until all elements have been processed or the action throws an
     * exception. 
     */
    default void forEach(Consumer<? super T> action) {
        Objects.requireNonNull(action);
        for (T t : this) {
            action.accept(t);
        }
    }

    default Spliterator<T> spliterator() {
        return Spliterators.spliteratorUnknownSize(iterator(), 0);
    }
}
```
Если класс реализует интерфейс Iterable, это значит, что из него можно вызвать метод **Iterator<T> iterator(), который возвращает итератор.
Итератор - это интерфейс, в котором определены методы перебора элементов. Это объект, реализующий интерфейс Iterator, 
он позволяет организовать цикл для перебора коллекции, извлекая или удаляя из нее элементы.  

**Любой класс может использоваться в цикле for each, если он реализует интрфейс Iterable.**  
interface Collection<E> extends Iterable<E>, т.е. все коллекции могут использоваться в for each и имеют итератор. 
     
Методы Итератора:   
```java
public interface Iterator<E> {
 
    boolean hasNext();
    
    E next();

    /**
     * Removes from the underlying collection the last element returned
     * by this iterator (optional operation).  
     */
    default void remove() {
        throw new UnsupportedOperationException("remove");
    }

    /**
     * Performs the given action for each remaining element until all elements
     * have been processed or the action throws an exception.  
     */
    default void forEachRemaining(Consumer<? super E> action) {
        Objects.requireNonNull(action);
        while (hasNext())
            action.accept(next());
    }
}
```  
Цикл for each подходит для перебора элементов коллекции только в прямом направлении и не позволяет видоизменять элементы коллекции, 
если же нам нужны эти функции, нужно использовать итератор или листератор.  

+ Интерфейс **ListIterator** расширяет интерфейс Iterator для двустороннего обхода списка и видоизменения его элементов.  
Listiterator доступен только в тех типах коллекций, где реализуется интерфейс List.  
  
Доп. методы:  
```java
    boolean hasPrevious();
    E previous();
//Возвращает индекс следующего элемента в списке.
    int nextIndex();
    int previousIndex();

    // Modification Operations
//Присваивает заданный объек'.l'текущему элементу списка.
    void set(E e);
//Вводит заданный объек'.l' перед элементом, который должен быть возвращен в результате последующего вызова метода next()
    void add(E e);
```  
## Spliterator (Итераторы-разделители)  
Наиболее важной особенностью интерфейса Spliterator является его способность поддерживать параллельную итерацию отдельных частей последовательности
элементов, а следовательно, и параллельное программирование.  
Сплитератор также полезен в последовательнмо программировании, т.к. имеет метод:   
Ьoolean tryAdvance(Consumer<?super Т> действие) - Выполняет заданное действие над следующим элементом в итерации. 
(т.е. сочетает в себе операции, выполняемые методами hasNext () и next ())  
Пример:  
```java
ArrayList<Double> vals = List.of(1.0, 2.0, 3.0);
// вызвать метод tryAdvance() для вывода содержимого списочного массива vals
Spliterator<Double> spltitr = vals.spliterator();
while(spltitr.tryAdvance((n) ->System.out.println(n)));
```  
(n) ->System.out.println(n)) - это лямбда для Consumer<?super Т> действие.  

[к оглавлению](#collections-pro)  

## Comparator  
Функция сравнения, которая определяет общий порядок объектов в коллекции. (сортировка)  
```java
class Student{
 String student_name;
 int id;

 Student(int id, String  student_name){
  this.id = id;
  this.student_name = student_name;
 }

 public String toString(){
  return id + " " + student_name;
 }
}

class StudentIdComparator implements Comparator<student>{

 public int compare(Student e1, Student e2) {
  return e1.id.compareTo(e2.id);
 }
}
``` 
Есть второй вариант.  
Если класс реализует интерфейс Comparable, то коллекцию таких объектов можно сравнивать просто через Collections.sort(cars);  
Comparable делает наши объекты «сравнимыми» и создает для них наиболее естественный порядок сортировки, который будет использоваться в большинстве случаев.  
```java
public class Car implements Comparable<Car> {

   private int manufactureYear;
   private String model;
   private int maxSpeed;

   public Car(int manufactureYear, String model, int maxSpeed) {
       this.manufactureYear = manufactureYear;
       this.model = model;
       this.maxSpeed = maxSpeed;
   }

   @Override
   public int compareTo(Car o) {
       return 0;
   }
}
```  
[к оглавлению](#collections-pro)

## Что такое коллекции.  
![alt text](https://github.com/magidin91/theoretical_part/blob/master/src/main/resources/collection.png)  
    
Коллекция - это группа элементов. 
Java Collection Framework — иерархия интерфейсов и их реализаций, которые яаляются структурами данных. 
На вершине иерархии в Java Collection Framework располагаются 2 интерфейса: 
Collection и Map. JDK не предоставляет никаких прямых реализаций интерфейса Collection, но предлагает реализации более специфичных интерфейсов:
Set, List.  
  
Интерфейс Collection содержит основные методы коллекций:  
+ add(E e); size(); contains(Object o); toArray();   
+ remove(Object o); containsAll(Collection<?> c); removeAll(Collection<?> c);  addAll(Collection<? extends E> c);   
+ **removeIf(Predicate<? super E> filter);**  **stream();** 
+ retainAll(Collection<?> c) - Удаляет элементы, не принадлежащие переданной коллекции   

Базовые интерфейсы коллекций:   
+ Queue - Расширяет интерфейс Collection для управления специальными типами списков, где элементы удаляются только из начала списка.    
+ List
+ Set 
+ Map (не реализует интерфейс Collection)  

Большинство базовых реализаций коллекций не синхронизированы: ArrayList, LinkedList, HashMap, LinkedHashMap, TreeMap, HashSet, LinkHashSet, TreeSet.
  
+ Можно воспользоваться стандартным механизмом синхронизации, при котором в качестве монитора передаться ссылка объекта, инкапсулирующий данную коллекцию.
+ Если такого объекта нет, можно воспользоваться методом synchronizedSortedMap. Например: SortedMap m = Collections.synchronizedSortedMap(new TreeMap(...));  

Синхронизирвоаны: Hashtable, Vector, Stack - частично синронизирован, ConcurrentHashMap.     

Фабричные методы Map.of(); Set.of(); List.of(); - возврщают неизменяемые коллекции. Нельзя вводить null.  
При вводе дублирующих значений в Set.of() выбросит IllegalArgumentException!.  

Дополнительные:  
+ SortedSet - Расширяет интерфейс Set для управления отсортированными множествами 
+ NavigableSet - Расширяет интерфейс SortedSet для извлечения элементов по результатам поиска ближайшего совпадения   
[подробнее](https://habr.com/ru/post/237043/)      

[к оглавлению](#collections-pro)  

## Расскажите реализации данных очередей и стеков.
+ **Queue** (одностороняя очередь) - когда элементы можно получить в том порядке в котором добавляли. **FIFO**
+ **Dequeue** (двусторонняя очередь) - можно вставлять/получать элементы из начали и конца. 
+ **Stack** - можно получить только последний элемент **LIFO**

```java
 Queue<Integer> myQ = new LinkedList<Integer>();
```   

## Интерфейс Queue 
(добавляем в конец, берем из начала)  
Этот интерфейс описывает коллекции с предопределённым способом вставки и извлечения элементов, а именно — очереди FIFO (first-in-first-out).  
Очереди применяются в тех случаях, коrда требуется накапливать объекты и извлекать их по принципу "первым пришел - первым обслужен".    
Интерфейс очереди определяет, что элементы можно добавлять в хвосте очереди, удалять их в ее голове, 
а также выяснять, сколько элементов находится в очереди в данный момент.   
  
NullPointerException генерируется, когда предпринимается попытка сохранить null, а пустые элементы в очереди не разрешены.  

## Класс PriorityQueue (отсортированный Queue)  
Класс PriorityQueue расширяет класс AbstractQueue и реализует интерфейс Queue.  В основе лежит массив. 
Он служит для создания очереди по приоритетам на основании компаратора очереди.  

PriorityQueue — является единственной прямой реализацией интерфейса Queue (была добавлена, как и интерфейс Queue, в Java 1.5),
не считая класса LinkedList, который так же реализует этот интерфейс, но был реализован намного раньше.
Особенностью данной очереди является возможность управления порядком элементов. 
По-умолчанию, элементы сортируются с использованием **natural ordering**, но это поведение может быть переопределено при помощи объекта Comparator,
который задаётся при создании очереди. Данная коллекция не поддерживает null в качестве элементов.    

## Интерфейс Deque (FIFO + LIFO)  
Deque<E> extends Queue<E>    
Очередь + Стек   
Интерфейс Deque расширяет интерфейс Queue и определяет поведение двусторонней очереди, которая может функционировать 
как стандартная очередь по принципу FIFO или как стек по принципу "последним вошел - первым обслужен”. 
Поддерживает вставку и удаление элементов в оба конца.  

## Класс ArrayDeque
Очередь + Стек на базе массива.   
Класс **ArrayDeque** расширяет класс AbstractCollection и реализует интерфейс Deque. Он не добавляет свои методы. Это двусторонняя очередь на базе массива.  
Эта коллекция представляет собой реализацию с использованием массивов, подобно ArrayList, но не позволяет обращаться к элементам по индексу и хранение null. 
Как заявлено в документации, коллекция работает быстрее чем Stack, если используется как LIFO коллекция (т.к. стек - частично синхронизирован), 
а также быстрее чем LinkedList, если используется как FIFO.      

## LinkedList  
Реализует Deque.  

[к оглавлению](#collections-pro)   
  
## Очередь на двух стеках  
```java
public class SimpleQueue<T> {
    private SimpleStack<T> stack = new SimpleStack<>();
    private SimpleStack<T> queue = new SimpleStack<>();

    /**
     * Добавляет элемент в стэк в начало
     */
    public void push(T value) {
        stack.push(value);
    }

    /**
     * переворачиваем стек, чтобы удалять первые элементы
     */
    public T poll() {
        if (queue.getSize()==0) {
            int size = stack.getSize();
            for (int i = 0; i < size; i++) {
                queue.push((stack.poll()));
            }
        }
       return queue.poll();
    }
} 
```  

[к оглавлению](#collections-pro)   
  
## Расскажите реализации интерфейса List.  
![alt text](https://github.com/magidin91/theoretical_part/blob/master/src/main/resources/List.png)  
  
List расширяет интерфейс Collection и определяет такое поведение коллекций, которое сохраняет последовательность элементов.  

+ **Vector** - динамический массив. Он подобен классу ArrayList, но имеет два отличия: синхронизирован и 
содержит немало устаревших методов, дублирующих функции методов, определенных в каркасе коллекций Collections Fгamework. 
Вместо класса Vector следует nрименять класс ArrayList. По nростой nричине: все методы из класса Vector синхронизированы. 
К объекту тиnа Vector можно безоnасно обращаться одновременно из двух nотоков исnолнения. 
Но если обращаться к такому объекту только из одного nотока исnолнения, что случается намного чаще, 
то в nрикладном коде вnустую тратится время на синхронизацию.  
  
+ **Stack** (добавляем в конец, берем из конца)       
Является производным от класса Vector и реализует стандартный стек, действующий по принципу "последним пришел - первым обслужен(LIFO).  
(push – помещает элемент в конец стека, pop – возвращает элемент из конца стека и удаляет его) Следует также заметить, 
что класс Stack по-прежнему рекомендуется для употребления. Тем не менее вместо него лучше выбрать класс ArrayDeque. 
 
+ **ArrayList** - динамический массив с несинхронизированными методами.   
+ **LinkedList** - двусвязный список.  
  
Фабрики для получения List:
+ List.of(1,2,3) - возвращает неизменяемый лист  
Не допускается указывать пустые (null) элементы создаваемого списка.   
+ Arrays.asList(1,2,3) - возвращает лист фиксированной длины  

Методы:  
sort(Comparator<? super E> c) - Осуществляет сортировку списка по заданному правилу.  
subList(int fromIndex, int toIndex) - полнофункциональный список, он работает и на запись, внося соответствующие изменения в родительский список.   

[к оглавлению](#collections-pro)  

## ArrayList  
+ DEFAULT_CAPACITY = 10;    
+ public ArrayList(int initialCapacity) - создает начальный массив определенного размера. **Если мы знаем примерное количество элементов,
которые будут храниться, то лучше испол-ть его, чтобы не тратить время и ресурсы на увеличение массива.**     
+ trimToSize() - урезает емкость листа до текущего кол-ва элементов  
+ ensureCapacity(int minCapacity) - увеличивает емкость листа, чтобы вместить minCapacity элементов   
+ list.subList(start, end).clear(); - удаляем несколько рядом стоящих элементов    
+ removeRange(int fromIndex, int toIndex) - удаляет из списка все элементы, которые находятся между начальным 
указанным индексом (включительно) и конечным указанным индексом (не включительно).  
+ replaceAll(UnaryOperator<e> operator) - Изменяет все элементы коллекции  

1. Если места в массиве недостаточно, новая емкость рассчитывается по формуле 1.5 * oldCapacity  + 1. 
Подробнее:  
int newCapacity = oldCapacity + (oldCapacity >> 1)
">> 1" – побитовый сдвиг вправо на единицу (уменьшает в 2 раза). 
По сути, означает деление на 2 в степени 1. Получается, что мы делим 10 на 2 и прибавляем 10. 
Итого, новая емкость массива равна 15, но так как мы добавляем 11 элемент, то 15 + 1 = 16.
Второй момент это копирование элементов. Оно осуществляется с помощью native метода System.arraycopy(), который написан не на Java.     

**Добавление элемента**  
При добавлении элемента возможно нужно будет увеличить длину массива, это скажется на скорости операции.  
Добавление в «середину» списка:  
Добавление элемента на позицию с определенным индексом происходит в три этапа:   
 
1) проверяется, достаточно ли места в массиве для вставки нового элемента;    
ensureCapacity(size+1);    
2) подготавливается место для нового элемента с помощью System.arraycopy()  
3) значение записывается в позицию с указанным индексом.  

Когда необходимо записать во внутренний массив ещё один элемент, а места там нет, то внутри ArrayList происходит вот что:
+ Создается новый массив размером, в 1.5 раза больше исходного, плюс один элемент.
+ Все элементы из старого массива копируются в новый массив
+ Новый массив сохраняется во внутренней переменной объекта ArrayList, а старый массив объявляется мусором.  
Увеличив емкость массива заранее, можно избежать дополнительного перераспределения оперативной памяти впоследствии.    
  
**Как можно догадаться, в случаях, когда происходит вставка элемента по индексу и при этом в вашем массиве нет свободных мест, 
то вызов System.arraycopy() случится дважды: первый в ensureCapacity(), второй в самом методе add(index, value), 
что явно скажется на скорости всей операции добавления.** 
 
**Удаление элемента**  
Важно понимать, что автоматического уменьшения размера (количество ячеек) внутреннего массива при удалении элементов из него не происходит, 
что может привести к своеобразным утечкам памяти. Поэтому не стоит пренебрегать методом trimToSize().  

Сложность алгоритма:  
+ на поиск по индексу О(1), на поиск по значению О(N)  
+ на вставку О(N) (т.к. нужно сдвигать каждый элемент)    
[подробнее](https://habr.com/ru/post/262943/)    

[к оглавлению](#collections-pro)    
  
## LinkedList  
[https://habr.com/ru/post/127864/](https://habr.com/ru/post/127864/)  

Двунаправленный список, расширяет класс AbstractSequentalList(реализует последовательный доступ: итератор и т.д.) и 
реализует интерфейсы List, Dequeue(двусторонняя очередь). Каждый элемент структуры содержит указатели на предыдущий и следующий элементы. 
Итератор поддерживает обход в обе стороны. Позволяет добавлять любые элементы в том числе и null.          
**Есть смысл использовать, если подразумевается вставка и удаление из середины после итерирования до нужного элемента.**      

```java
public class LinkedList<E>
    extends AbstractSequentialList<E>
    implements List<E>, Deque<E>, Cloneable, java.io.Serializable
{
    transient int size = 0;
    transient Node<E> first;
    transient Node<E> last;

    private static class Node<E> {
        E item;
        Node<E> next;
        Node<E> prev;

        Node(Node<E> prev, E element, Node<E> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }
}
```
Каждый раз при добавлении нового элемента, по сути выполняется два шага:  
1) создается новый новый экземпляр класса Node
2) переопределяются указатели на предыдущий и следующий элемент  

Вставка в середину по индексу: Метод entry(index) пробегает по всему списку в поисках элемента с указанным индексом. 
Направление обхода определяется условием (index < (size >> 1)). 
По факту получается что для нахождения нужного элемента перебирается не больше половины списка, 
время на поиск растет линейно — O(n). Удаление также занимает O(n).  
Внутри метода remove(value) просматриваются все элементы списка в поисках нужного. Удален будет лишь первый найденный элемент.         

Преимущества:
1. Удаление и вставка в середину.  
**При этом применяется итератор или листератор и вставка элементов благодаря методам итераторов next, hasnext, add, remove.**   
Если в коллекции предполагается лишь несколько элементов, то для ее составления лучше воспользоваться списочным массивом типа ArrayList.    

Обычные массивы и ArrayList страдают существенным недостатком. Удаление элемента из середины массива обходится дорого с точки зрения потребляемых вычислительных ресурсов,
потому что все элементы, следующие за удаляемым, приходится перемещать к началу массива. Это же справедливо и для ввода элементов в середине массива.
Удаление элемента из середины связанного списка - недорогая операция с точки зрения потребляемых вычислительных ресурсов, 
поскольку в этом случае достаточно обновить лишь связки, соседние с удаляемым элементом.  

2. Вставка и удаление из начала и вставка в конец.
LinkedList без итератора выигрывает при вставке и удалении элемента из начала, а также вставке  в конец. 
(т.к. ArrayList молжет понадобиться увеличить размер массива, если мы достигли предела)  
Добавление и удаление элемента из начала и конца выполняется за время O(1).      

Недостатки: 
1. Произвольный доступ по индексу.  
В связных списках не поддерживается быстрый произвольный доступ. Если требуется проанализировать n-й элемент связного списка, 
то начинать придется с самого начала и перебрать первые n - 1 элементов списка, причем без всяких пропусков.
Именно по этой причине программисты обычно не применяют связные списки в тех случаях, когда к элементам требуется обращаться по индексу.  

Итоги  
— Из LinkedList можно организовать стэк, очередь, или двойную очередь, со временем доступа O(1);  
- На добавление и удаление из середины списка, используя ListIterator.add() и ListIterator.remove(), потребуется O(1); Также на удаление, вставку 
и получение из начала и конца.   
- Остальные операции займут O(n)  
- Не синхронизирован.    
 
[к оглавлению](#collections-pro)  

## Отличие ArrayList от LinkedList.  
Упорядоченная коллекция на основе массива (ArrayList) отличается быстрым произвольным доступом, 
и поэтому методы из интерфейса List целесообразно вызывать с индексом. А связный список отличается медленным произвольным доступом.  

**ArrayList** реализован на массивах. (используют, если чаще читаются элементы, чем добавляются)
Хранит свои элементы в массиве.
+ \+ осуществляется быстрый поиск элементов (по индексу).
+ \+ меньше расходует памяти на хранение элементов
+ \- увеличение ArrayList'a происходит медленно.
+ \- при вставке элемента (или удалении) в середину или в начало, приходится переписывать все элементы.  
Операция вставки в середину происходит в основном быстрее в ArrayList, но не всегда. 
Однозначно сравнить скорость этих двух алгоритмов без учёта изначального размера списка.

**LinkedList** является представителем двунаправленного списка.  
(используется если элементы чаще добавляются, чем читаются)
Хранит свои элементы в обектах у которых есть ссылки на предыдущий и следующий элементы.
+ \+ быстрая вставка и удаление в середину списка (переписать next и prev и всё), если проитерировались итератором до позции
+ \- долгий поиск в середине (нужно перебрать все элементы)

[к оглавлению](#collections-pro)

## Расскажите про реализации Map.  
![alt text](https://github.com/magidin91/theoretical_part/blob/master/src/main/resources/map.png)    
  
+ **WeakHashMap** -реализация хэш-таблицы, которая организована с использованием weak references.  
(пример загрузчик классов, ключ - объект класса, значение - сам класс, если не осталось больше ссылок на этот класс, класс выгружается)   
Другими словами, Garbage Collector автоматически удалит элемент из коллекции при следующей сборке мусора, если на ключ этого элеметна нет жёстких ссылок.  
+ **Hashtable**
+ **HashMap** - основан на хэш-таблицах. Ключи и значения могут быть любых типов, в том числе и null. 
Данная реализация не дает гарантий относительно порядка элементов. Не синхронизированная хэш-таблица (быстрая)
+ **LinkedHashMap** - расширяет класс **HashMap**. 
Он создает связный список элементов в карте, расположенных в порядке добавления.
+ **TreeMap** - упорядоченная по ключам. Основана на красно-черных деревьях. Может использовать компаратор в конструкторе. 
Объекты сохраняются в отсортированном порядке по возрастанию. 
Время доступа и извлечения элементов достаточно мало - log(N), что делает класс TreeMap блестящим выбором для хранения больших 
объемов отсортированной информации  

Mapы не реализуют интерфейс Iterable. Это означает, что перебрать содержимое отображения организовав цикл for в стиле for each, не удастся. 
Более того, нельзя получить итератор отображения. Но можно получить представление отображения в виде коллекции, 
которое допускает перебор содержимого в цикле или с помощью итератора. (entryset)      

Методы: 
+ Фабричный метод Map.of(…) создает неизменяемую мапу (в неё нельзя помещать null-элементы).

[к оглавлению](#collections-pro)  

## Расскажите про методы Object hashCode и equals.  
[подробнее](https://habr.com/ru/company/mailru/blog/321306/)
[подробнее](https://blog-house.pro/java-interviews-questions/post-63453/) 
 
public native int hashCode();  
Метод хешкод, определенный в классе Object, возвращает уникальное целое число для объекта, 
если число объектов не превышает диапазон int, иначе может быть коллизия – повторное использование хешкодов для разных объектов.    
  
**Т.е. даже если объекты имеют одинаковые поля хешкод у них будет разный** (если метод хешкод еще не переопределн). 
Поэтому в собственных классах мы переопределяем хешкод, чтобы одинаковые объекты имели один хешкод.
(и перезаписались в мапе, а не тратили лишние ячейки и память)          

Обратите внимание, что идентификационная реализация hashCode() из класса Object зависит от JVM.
Реализация по умолчанию hashCode() не имеет отношения к адресу памяти объекта, как минимум в OpenJDK. 
В версиях 6 и 7 это случайно генерируемое число. В версиях 8 и 9 это число, полученное на основании состояния потока.  
В HotSpot идентификационный хеш генерируется лишь раз, а затем кешируется в слове mark в заголовке объекта.   
Т.е. hashCode() имеет разные реализации в зависимости от JDK.

public boolean equals(Object obj) {
    return (this == obj);
}

**Метод equals() в Object сравнивает ссылки, поэтому даже объекты с одинаковыми полями и содержанием будут не равны**, 
т.к. созданы в разных ячейках памяти. (поэтому его тоже надо переопределять)

Метод hashCode используется для числового представления объекта, метод equals для сравнения двух объектов.
При переопределении метода equals всегда переопределяют hashCode.
Если метод equals true то hashCode всегда равны, но не наоборот, потому что возможны 
коллизии и для разных элементов будет одинаковый hashCode.
       
```java
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Animal animal = (Animal) o;
        return a == animal.a &&
                Objects.equals(b, animal.b);
    }
    @Override
    public int hashCode() {
        return Objects.hash(a, b);
    }
```  
**Метод HashCode переопределяется так:**
1. Простое нечетное число (31 популярно)
2. Умножаем результат на другое простое нечетное число (популярно 17) 
прибавляем hashCode поля которое относится к бизнес логике.
3. Повторяем пункт 2 пока не кончатся поля которые относятся к бизнес логике  

[к оглавлению](#collections-pro)  

## Хешкоды разных классов  
Во многих класса хешкод уже переопределен: обертки, коллекции.    
  
+ Примитивные типы не являются объектами, поэтому для них нельзя вызвать метод хеш-код. 
Хеш-код можно получить для оберток примитивного типа, в которых он переопределен следующим образом:  
+ Для Byte, Short, Integer, Character хеш-код будет возвращать (int)value, т.е. int значение примитива.
public static int hashCode(byte value) {return (int)value;}  
+ Для Long: (int)(value ^ (value >>> 32))  
Cмещаем верхние биты в диапазон int  и объединяем исключающим OR.  
+ Для Boolean 1231 для true для 1237 false. (value ? 1231 : 1237)  
+ **Для массивов хеш-код не переопределен, вызывается метод хешкод из класса Object.**    
Для того, чтобы получить хэш - код, основанный на содержании массива можно использовать **Arrays.hashCode(Object a[]);**
public static int hashCode(Object a[]) {
    if (a == null)
        return 0;
    int result = 1;
    for (Object element : a)
        result = 31 * result + (element == null ? 0 : element.hashCode());
    return result;
}

+ В коллекциях метод хешкод переопределен по-разному и основан на содержащихся элементах.
ArrayList:
public int hashCode() {
    int expectedModCount = modCount;
    int hash = hashCodeRange(0, size);
    checkForComodification(expectedModCount);
    return hash;
}

int hashCodeRange(int from, int to) {
        final Object[] es = elementData;
        if (to > es.length) {
            throw new ConcurrentModificationException();
        }
        int hashCode = 1;
        for (int i = from; i < to; i++) {
            Object e = es[i];
            hashCode = 31 * hashCode + (e == null ? 0 : e.hashCode());
        }
        return hashCode;
    }

HashSet:
public int hashCode() {
    int h = 0;
    Iterator<E> i = iterator();
    while (i.hasNext()) {
        E obj = i.next();
        if (obj != null)
            h += obj.hashCode();
    }
    return h;
}

LinkedList:
public int hashCode() {
    int hashCode = 1;
    for (E e : this)
        hashCode = 31*hashCode + (e==null ? 0 : e.hashCode());
    return hashCode;
}  

[к оглавлению](#collections-pro)   
 
## Хеш-таблица (Hashtable)   
Хеш-таблицей называется структура данных, которая отображает ключи на значения и обеспечивает очень быструю вставку и поиск.    
Независимо от количества элементов вставка и поиск (а иногда и удаление) выполняются за O(1). 
По сути, это обычный массив, где местоположение элемента зависит от значения самого элемента.  
Хештаблица не гарантирует тот же порядок элементов, что был при добавлении.    
 
**Связь между значением элемента и его позицией в хеш-таблице задает хеш-функция.** Хеш-функция получает входную часть данных - ключ, 
а на выходе она выдает целое число, известное как хеш-значение. Затем, хеш-значение привязывает наш ключ к определенному индексу хеш-таблицы.  
По этой причине важно, чтобы хеш-функция вела себя последовательно и выводила один и тот же индекс для одинаковых входных данных.  

У хеш-таблиц также имеются недостатки. **Они реализуются на базе массивов, а массивы трудно расширить после создания.** У некоторых разновидностей
хеш-таблиц быстродействие катастрофически падает при заполнении таблицы, поэтому программист должен точно представлять, 
сколько элементов данных будет храниться в таблице (или приготовиться к периодическому перемещению данных в другую хеш-таблицу большего размера).

Hashtable:  
+ **В отличие от новых коллекций Hashtable синхронизирован. Если потокобезопасная реализация не нужна, рекомендуется использовать HashMap.**  
+ Hashtable имеет более устаревший алгоритм вставки: метод определения индекса проще и нет фичи для перехода к дереву, если в бакете скапливается много объектов.  
+ Hashtable не позволяет использовать null в качестве значения или ключа. (HashMap позволяет)        
[к оглавлению](#collections-pro)   
   
## HashMap
[подробнее](https://javarush.ru/groups/posts/2496-podrobnihy-razbor-klassa-hashmap)
Хеш-мапа обеспечивает очень быструю вставку, поиск и удаление, дает константное время - O(1), если у ключа хорошая хеш-функция, 
которая адекватно распрделяет элементы по бакетам.         
Данная реализация не дает гарантий относительно порядка элементов.  
  
В HashMap хеш-таблица реализована на основе массива односвязных списков. Элементы массива (ячейки) еще называются корзинами «buckets»,
которые используются для хранения отдельно взятых узлов. Каждый из бакетов представляет из себя коллекцию (список или дерево). 
Узел представляет собой объект вложенного класса Node (или TreeNode при древовидной структуре). 
По сути, внутри ячейки массива лежит LinkedList, только список односвязный, либо красное-черное дерево.  

Добавление элемента выполняется за константное время O(1), но время удаления, получения зависит от распределения хэш-функции.
В идеале является константным, но может быть и линейным O(n)
(наверно, либо когда в кадом списке много элементов, либо когда уже деревья и нужно делать вращения).      

Поля класса HashMap:  
+ transient Node <K,V>[] table – сама хеш-таблица, реализованная на основе массива, для хранения пар «ключ-значение» в виде узлов. Здесь хранятся наши Node;
+ **final float loadFactor** — этот параметр отвечает за то, при какой степени загруженности хеш-таблицы необходимо создавать новую хеш-таблицу, 
т.е. как только хеш-таблица заполнилась на 75%, будет создана новая хеш-таблица с перемещением в неё текущих элементов.   
(затратная операция, так как требуется пересчет индексов и распределение элементво по новой таблице);    
+ int threshold — предельное количество элементов, при достижении которого размер хэш-таблицы увеличивается вдвое. Рассчитывается по формуле (capacity * loadFactor);
Если значение првышено, то таблица увеличивается в 2 раза.
+ transient Set< Map.Entry< K,V>> entrySet — содержит кешированный entrySet(), с помощью которого мы можем перебирать HashMap.  

И константы:
static final int DEFAULT_INITIAL_CAPACITY= 1 << 4 — **емкость хеш-таблицы по умолчанию (16);**
static final int MAXIMUM_CAPACITY = 1 << 30 — максимально возможная емкость хеш-таблицы (приблизительно 1 млрд.);
static final float DEFAULT_LOAD_FACTOR = 0.75f — коэффициент загрузки, используемый по умолчанию;
**static final int TREEIFY_THRESHOLD = 8 — испольузется в вычислении TREEIFY_THRESHOLD - 1 = 7 
Т.е. если в бакете стало 7 элементов, переходим к красно-черному дереву, если количество бакетов в текущей хеш-таблице не меньше 64**         
static final int UNTREEIFY_THRESHOLD = 6 — если количество элементов в одной корзине уменьшится до 6, то произойдет обратный переход от дерева к связному списку;
static final int MIN_TREEIFY_CAPACITY = 64 — минимальная емкость (количество корзин) хеш-таблицы, 
при которой возможен переход к древовидной структуре. Т.е. если в хеш-таблице по крайней мере 64 бакета и в одном бакете 8 или более элементов, 
то произойдет переход к древовидной структуре.       
 
Конструкторы класса:
+ public HashMap() — создает хеш-отображение по умолчанию: объемом (capacity) = 16 и с коэффициентом загруженности (load factor) = 0.75;   
+ public HashMap(Map<? extends K, ? extends V> m) — создает хеш-отображение, инициализируемое элементами 
другого заданного отображения с той начальной емкостью, которой хватит вместить в себя элементы другого отображения;  
+ public HashMap(int initialCapacity) — создает хеш-отображение с заданной начальной емкостью. 
**Чтобы не тратить время и ресурсы на затратное создание нового массива и перехеширование элементов, лучше сразу создать подходящую емкость.**    
Для корректной и правильной работы HashMap размер внутреннего массива обязательно должен быть степенью двойки (т.е. 16, 64, 128 и т.д.);  
+ public HashMap(int initialCapacity, float loadFactor) — создает хеш-отображение с заданными параметрами: начальной емкостью и коэффициентом загруженности.  

Важно: сам массив создается при первом добавлении элемента в мапу, сам конструктор этот массив не создает. 
(put-> putVal-> resize() - метод в котором создается массив)      
 
**Как вычисляются hash функция?**  
Хеш нужен для определения ячейки в массиве. Т.к. хеш может выходить за длину массива, его нужно ограничить, для этого используется маска: 
раньше использовался остаток от деления: i = hash % n, остаток от деления всегда меньше делителя - длины массива. 
Позже стал использовать вариант с побитовым "&": **i = (n - 1) & hash** , он работает аналогично, но быстрее. 
Соотвественно (n - 1) & hash возвратит только биты в пределах длины массива.  
0000 1111 = 15 &    
1011 0011 =   
0000 0011    
  
Но в этом случае верхние биты не используются в вычислении индекса, т.к. они отсекаются побитовым "&". 
Чтобы этого не происходило мы сначала трансформируем хешкод:  
(h = key.hashCode()) ^ (h >>> 16)  
Мы сдвигаем биты вправо, затем смешиваем их с нижними битами с помощью побитового XOR, получая более ровное распределение битов и меньшее количество коллизий.    
Operation	           Binary Value  
h = key.hashCode()	1111 1111 1111 1111 1111 1111 1111 1111    
h >>> 16	        0000 0000 0000 0000 1111 1111 1111 1111    
h ^ (h >>> 16)	    1111 1111 1111 1111 0000 0000 0000 0000  

Например, для Integer, Float – если мы в HashMap кладем маленькие значения, то у них и биты хеш-кодов будут заполнены только нижние.
(т.к. для них метод  хешкод переопределен и возвращает (int)value)   
В таком случае ключи в HashMap будут иметь тенденцию скапливаться в нижних ячейках, а верхние будут оставаться пустыми, 
что не очень эффективно (коллизии). На то, в какой бакет попадёт новая запись, влияют только младшие биты хеша. 
Поэтому и придумали различными манипуляциями подмешивать старшие биты хеша в младшие,
чтобы улучшить распределение по бакетам (чтобы старшие биты родного хеша объекта начали вносить коррективы в то, в какой бакет попадёт объект)  

Вставка объекта: 
1. HashMap <String, Integer> map = new HashMap<>(); - создаем мапу  
2. map.put("KING", 100);  -> вычисляется  hash("KING"), вызывается  putVal(hash(key), key, value, false, true);
3. if ((tab = table) == null || (n = tab.length) == 0) - Проверяем создана ли таблица, т.к. она еще не создана, 
вызывается resize(), который ее создает. Длина таблицы записывается в переменную n = (tab = resize()).length    
4. i = (n - 1) & hash - вычисляем индекс бакета  
5. if ((p = tab[i = (n - 1) & hash]) == null) - проверяем, что в бакете еще нет элемента.
6. tab[i] = newNode(hash, key, value, null); - т.к. элмента там еще нет, создаем ноду и вставляем в ячейку.  
7. if (++size > threshold)  
   resize();                
После добавления будет произведена проверка не превышает ли текущее количество элементов параметр threshold, 
Если превышает, тогда будет вызван метод resize() для увеличения размера хеш-таблицы.  
   
В хешмапе значения могут быть дублированными. Допускаются null ключи (но только один, так как не допускаются дубликаты ключей - они перезаписываются).  
HashMap не является синхронизированным и потокобезопасным. **Можно использовать ConcurrentHashMap для работы в многопоточной среде.**              
         
[к оглавлению](#collections-pro)  
      
## Расскажите, что такое коллизии в Map. Как с ними бороться.
Коллизия - это когда в одном бакете оказывается более одного элемента.  
Ситуация, когда разные ключи попадают в один и тот же бакет (даже с разными хешами), называется коллизией или столкновением. 
Даже если хеш-таблица больше, чем набор данных, и была выбрана хорошая хеш-функция, это не гарантирует того, что коллизии не возникнут. 
Да и значение хеша ограничено диапазоном значений типа int (порядка 4 млрд.). 
Полученное новое значение также нужно куда-то записать, и для этого нужно определить, куда именно оно будет записано. 
Это называется решением коллизии. Существует два подхода:  

+ метод цепочек (реализован в HashMap) — т.е. в ячейке на самом деле содержится список. 
А уже в списке может содержаться несколько значений (не обязательно с одинаковым хеш-кодом).  
+ метод открытой адресации (реализован в IdentityHashMap) – заключается в поиске первой пустой ячейки после той, на которую указала хеш-функция;  

Добавление элементов при коллизии (в пределах одного бакета) выглядит следующим образом:  
+ проверяем с помощью методов hashCode() и equals(), что оба ключа одинаковы.  
+ если ключи одинаковы, заменить текущее значение новым.  
+ иначе связать новый и старый объекты с помощью структуры данных "связный список", 
указав ссылку на следующий объект в текущем и сохранить оба под нужным индексом; либо осуществить переход к древовидной структуре.  

После добавления нового элемента в цикле for увеличивается счетчик,который отвечает за количество элементов в бакете:   
for (int binCount = 0; ; ++binCount)     
**До тех пор, пока их количество не станет равно или больше 7**: binCount >= TREEIFY_THRESHOLD – 1  
В таком случае произойдет вызов метода treeifyBin() - берет массив и заменяет все ноды на триноды, 
и  treeify(), который уже из замененных тринод формирует красно-черное дерево.  
     
**Однако, если количество бакетов в текущей хеш-таблице меньше 64:**  
if (tab == null || (n = tab.length) < MIN_TREEIFY_CAPACITY)     
Вместо перехода к древовидной структуре будет вызван метод resize() для увеличения размера хеш-таблицы с перераспределением элементов.
Метод resize() перебирает все элементы текущего хранилища, пересчитывает их индексы (с учетом нового размера) 
и перераспределяет элементы по новому массиву.     

Коллизия это также ситуация, когда для двух объектов вычисляется одинаковый хеш код. Причиной такой коллизии может быть неверно выбранная хеш функция, 
в результате чего ключи будут попадать в один бакет. В этмо случаем нужно сделать более распределенную функцию хешкода.      

[к оглавлению](#collections-pro)  

## Двоичное Дерево Поиска (Binary Search Tree)
[подробнее](https://www.rsdn.org/article/alg/binstree.xml)  

Бинарные деревья являются отсортированной структурой данных.     
Двоичным деревом поиска (ДДП) называют дерево, все вершины которого упорядочены, каждая вершина имеет не более двух потомков
(назовём их левым и правым), и все вершины, кроме корня, имеют родителя. Вершины, не имеющие потомков, называются листами.  
ДДП позволяет выполнять следующие основные операции:
+ Поиск вершины по ключу.  
+ Определение вершин с минимальным и максимальным значением ключа.  
+ Переход к предыдущей или последующей вершине, в порядке, определяемом ключами.  
+ Вставка вершины.  
+ Удаление вершины.  

Двоичное дерево может быть логически разбито на уровни. Корень дерева является нулевым уровнем, 
потомки корня – первым уровнем, их потомки – вторым, и т.д. Глубина дерева это его максимальный уровень.  

**Эффективность поиска по дереву напрямую связана с его сбалансированностью**, то есть с максимальной разницей 
между глубиной левого и правого поддерева среди всех вершин. Имеется два крайних случая – сбалансированное бинарное дерево 
(где каждый уровень имеет полный набор вершин) и вырожденное дерево, где на каждый уровень приходится по одной вершине.
Вырожденное дерево эквивалентно связанному списку. Время выполнения всех основных операций пропорционально глубине дерева. 
Таким образом, скоростные характеристики поиска в ДДП могут варьироваться от O(logN) в случае законченного дерева до O(N) – в случае вырожденного.  

Операции с деревом же работают за O(h), где h — максимальная глубина дерева (глубина — расстояние от корня до вершины). 
В оптимальном случае, когда глубина всех листьев одинакова, в дереве будет n=2^h вершин. 
Значит, сложность операций в деревьях, близких к оптимуму будет O(log(n)).      

ДДП может быть использовано для реализации таких абстракций, как сортированный список, словарь (набор соответствий "ключ-значение"), 
очередь с приоритетами и так далее.  
При реализации дерева помимо значения ключа (key) и данных также хранятся три указателя: на родителя, левого и правого потомков.
  
Свойство упорядоченности двоичного дерева поиска  
Если x – это произвольная вершина в ДДП, а вершина y находится в левом поддереве вершины x, то y.key < x.key. 
Если x – это произвольная вершина ДДП, а вершина y находится в правом поддереве вершины x, то y.key >= x.key.  

**Удаление элемента из дерева**  
Если у удаляемого элемента нет потомков или один потомок, то удалить просто - либо просто удаляем, либо заменяем преемником. 
Если у удаляемого элемента есть два потомка, то удаляемый узел надо заменить на преемника. 
Т.к. используется сложный алгоритм для поиска преемника, то **часто вместо удаления используют флаг isDeleted. 
В остальных методах проверяют значение этого флага.**   
 
**Поиск вершины в ДДП**
Идея поиска проста. Алгоритм поиска в ДДП по своей природе рекурсивен. При его описании проще всего использовать понятие поддерева.
Поиск начинается с корня дерева, который принимается за корень текущего поддерева, и его ключ сравнивается с искомым. 
Если они равны, то, очевидно, поиск закончен. Если ключ, который мы ищем, оказался больше текущего, то, очевидно, 
что нужная вершина находится в правом поддереве, иначе – в левом. Далее эта операция повторяется для правого или левого поддерева.  
  
Основной проблемой использования ДДП является то, что методы вставки и удаления вершин, гарантируя сохранение свойства упорядоченности, 
совершенно не способствуют оптимизации основных операций над ДДП. Например, если вставить в ДДП последовательность возрастающих или убывающих чисел,
оно превратится в двусвязный список, а основные операции будут занимать время, пропорциональное количеству вершин О(N), а не его логарифму.  

Таким образом, для получения производительности порядка O(logN) нужно, чтобы дерево имело как можно более высокую сбалансированность 
(то есть имело возможно меньшую глубину).  Для решения этой проблемы были придуманы самобалансирующиеся деревья. 
Когда после добавления элемента в дереве возникает перекос, оно немного меняет порядок элементов.  

Обычно выделяются несколько типов сбалансированности. Полная сбалансированность, это когда для каждой вершины дерева 
количества вершин в левом и правом поддеревьях различаются не более чем на 1. К сожалению, такой сбалансированности 
трудно добиться на практике. Поэтому на практике используются менее жесткие виды сбалансированности.  

Сбалансированные деревья:  
1. B-дерево - просто сбаланисированное, в котором каждый узел содержит множество ключей и имеет более двух потомков.  
2. АВЛ-дерево - двочиное сбалансированное, для каждой его вершины высота её двух поддеревьев различается не более чем на 1, 
(но в разных ветвях допускается разница более чем на 1 вершину) [подробнее](https://ru.wikipedia.org/wiki/%D0%90%D0%92%D0%9B-%D0%B4%D0%B5%D1%80%D0%B5%D0%B2%D0%BE)    
3. Красно-чёрное дерево    
  
[к оглавлению](#collections-pro)  

## Какие существуют алгоритмы обхода дерева  
[подробнее](https://www.rsdn.org/article/alg/binstree.xml)  
Обход дерева  — вид обхода графа, обусловливающий процесс посещения (проверки и/или обновления) каждого узла
структуры дерева данных ровно один раз. Такие обходы классифицируются по порядку, в котором узлы посещаются.  

Есть три способа обхода: Прямой (preorder), Поперечный (inorder), Обратный (postorder).  
+ Прямой обход: сначала обходится данная вершина, левое поддерево данной вершины, затем правое поддерево данной вершины.  
+ Поперечный обход: сначала обходится левое поддерево данной вершины, затем данная вершина, затем правое поддерево данной вершины.   
Вершины при этом будут следовать в неубывающем (по ключам key) порядке.  
+ Обратный обход: сначала обходится левое поддерево данной вершины, затем правое, затем данная вершина.  

**Наиболее часто употребляется поперечный обход**, так как во всех других способах обхода следующие друг за другом 
вершины не связаны никакими условиями отношения.  

[к оглавлению](#collections-pro)  

## Расскажите про реализации деревьев.
+ **TreeMap** - Упорядоченная по ключам. Основана на красно-черных деревьях. Может использовать компаратор в конструкторе. 
+ **TreeSet** (основан на TreeMap)  

[к оглавлению](#collections-pro)  

## Красно-черное дерево  
Это ДДП, каждая вершина которых хранит ещё одно дополнительное логическое поле (color), обозначающее цвет: красный или чёрный.  
Фактически, в КЧД гарантируется, что уровни любых двух листьев отличаются не более, чем в два раза. Этого условия оказывается достаточно,
чтобы обеспечить скоростные характеристики поиска, близкие к O(logN). При вставке/удалении производятся дополнительные действия по балансировке дерева, 
которые не могут не замедлить работу с деревом. 
 
Свойства красно-черных деревьев:
1) Каждый узел окрашен либо в красный, либо в черный цвет (в структуре данных узла появляется дополнительное поле – бит цвета).
2) Корень окрашен в черный цвет.
3) Листья(так называемые NULL-узлы) окрашены в черный цвет.
4) Каждый красный узел должен иметь два черных дочерних узла. Нужно отметить, что у черного узла могут быть черные дочерние узлы. Красные узлы в качестве дочерних могут иметь только черные.
5) Пути от узла к его листьям должны содержать одинаковое количество черных узлов(это черная глубина).    

Перечисленные свойства гарантируют, что самая длинная ветвь от корня к листу не более чем вдвое длиннее любой другой ветви от корня к листу. 
Чтобы понять, почему это так, рассмотрим дерево с черной высотой 2. Кратчайшее возможное расстояние от корня до листа равно двум – когда оба узла черные.
Длиннейшее расстояние от корня до листа равно четырем – узлы при этом покрашены (от корня к листу) так: красный–>черный–>красный–>черный. 
Сюда нельзя добавить черные узлы, поскольку при этом нарушится свойство 4, из которого вытекает корректность понятия черной глубины.  

Поскольку согласно свойству 4 у красных узлов непременно черные наследники, в подобной последовательности недопустимы и два красных узла подряд. 
Таким образом, длиннейший путь, который мы можем сконструировать, состоит из чередования красных и черных узлов, что и приводит нас к удвоенной длине пути,
проходящего только через черные узлы. Все операции над деревом должны уметь работать с перечисленными свойствами. 
В частности, при вставке и удалении эти свойства должны сохраниться.  [подробнее](http://www.quizful.net/post/Java-TreeMap)  

Вращения
Операции вставки и удаления вершин в КЧД могут нарушать свойства КЧД. Чтобы восстановить эти свойства, 
надо будет перекрашивать некоторые вершины и менять структуру дерева. Для изменения структуры используются операции, называемые вращением. 
Возвращая КЧД его свойства, вращения так же восстанавливают сбалансированность дерева.  
Кч дерево - это оптимальное  соотношение сбалансированности к затратам на поддержание этой сбалансированности, он гарантирует логифмичяескую сложность 
на вставку, удаление и поиск.     

Плюсы структуры:    
1. Высокая скорость поиска - О(log(N)). (у хеш-таблицы - О(1))  
2. Упорядоченность данных.  
Из кч дерева проще получать данные по разным фильтрам (например фамилии от одной буква до другой итд)  
    
[к оглавлению](#collections-pro)  

## SortedMap  
**См. java доки**  
  
Интерфейс SortedMap хранит данные в отсортированном по ключу виде в порядке: 
Comparable ключей (k1.compareTo(k2)) или   
Comparator - обычно передается в конструктор при создании мапы. (comparator.compare(k1, k2)). 
Если мы передаем компаратор, то он будет приоритетнее чем Comparable.      
**Все ключи такой мапы должны реализовывать Comparable interface или быть применимы к переданному в конструктор компаратору.**  
  
SortedMap добавляет ряд методов:
+ K firstKey(): возвращает ключ первого элемента отображения
+ K lastKey(): возвращает ключ последнего элемента отображения
+ SortedMap<K, V> headMap(K end): возвращает отображение SortedMap, которые содержит все элементы оригинального SortedMap вплоть до элемента с ключом end
+ SortedMap<K, V> tailMap(K start): возвращает отображение SortedMap, которые содержит все элементы оригинального SortedMap, начиная с элемента с ключом start
+ SortedMap<K, V> subMap(K start, K end): возвращает отображение SortedMap, которые содержит все элементы оригинального SortedMap 
вплоть от элемента с ключом start до элемента с ключом end    
  
[к оглавлению](#collections-pro)  

## NavigableMap  
**См. java доки**  
  
**Интерфейс, который расширяет SortedMap с помощью методов навигации, и обеспечивает возможность
получения элементов отсортированного отображения относительно других элементов.**  
  
Методы lowerEntry, floorEntry, ceilingEntry, higherEntry ассоциируется с ключами соответственно меньше, меньше или равно,
больше или равно и больше заданного ключа, возвращая null, если такого ключа нет. Также метод subMap.
      
Например:
+ **SortedMap<K,V> subMap(K fromKey, K toKey);** - Возвращает представление (view) части мапы, ключи которой варьируются от
fromKey  включительно до toKey не включая. Возвращенная карта поддерживается этой картой, поэтому изменения
в возвращенной карте отражаются на этой карте, и наоборот.   
Т.е. если у нас есть мапа фамилий работников, вводим c какой по какую букву хотим найти и получаем список нужных фамилий работников.                                              
+ Map.Entry<K,V> lowerEntry(K key); - Возвращает сопоставление ключ-значение, связанное с наибольшим ключом
строго меньше заданного ключа, или null, если акого ключа нет.    
+ Map.Entry<K,V> floorEntry(K key); - Возвращает сопоставление ключ-значение, связанное с наибольшим ключом
меньше или равно заданному ключу, или  null, если такого ключа нет.  
+ Map.Entry<K,V> ceilingEntry(K key); - Возвращает сопоставление ключ-значение, связанное с наименьшим ключом
больше или равно заданному ключу, или null, если такого ключа не существует.
+ Map.Entry<K,V> higherEntry(K key); - Возвращает сопоставление ключ-значение, связанное с наименьшим ключом
строго больше заданного ключа, или {@code null}, если такого ключа нет.  

[к оглавлению](#collections-pro)    

## TreeMap
TreeMap хранит данные в отсортированном по ключу виде. TreeMap основан на Красно-Черном дереве, вследствие чего 
TreeMap сортирует элементы по ключу в естественном порядке или на основе заданного вами компаратора.
TreeMap гарантирует скорость доступа log(n) для операций get, put и remove, containsKey. 
Время доступа и извлечения элементов достаточно мало, что делает класс TreeMap блестящим выбором 
для хранения больших объемов отсортированной информации, которая должна быть быстро найдена.  
    
К LinkedList тримап не вырождается, т.к. красно-черное дерево может делать балансировку.    
  
Он наследуется от класса AbstractMap и реализует интерфейс NavigableMap,
а следовательно, также и интерфейс SortedMap. Соответственно тримап имеет все методы этих интерфейсов, для поиска элементов по разным фильтрам.
(subMap, lowerEntry, floorEntry, ceilingEntry, higherEntry и т.д.)       
 
**При попытке добавить null-элемент в TreeMap происходит исключение NullPointerException.**  
**Классы Integer, String, Double и т.п. реализуют интерфейс Comparable. Если вы создали собственный класс для ключей 
и не реализовали интерфейс Comparable (и не используете Comparator), то при попытке добавления объекта в коллекцию 
будет брошено исключение java.lang.ClassCastException!**  
  
TreeMap не синхронизирован. По этому, если имеется множество потоков, работающих с данной коллекцией и хотя бы один поток может её изменять , 
то коллекцию нужно синхронизировать внешне.  

Класс TreeMap имеет следующие конструкторы:  
1. TreeMap()  
2. TreeMap(Comparator comp)  
3. TreeMap(Map m)  
4. TreeMap(SortedMap sm) - создаст TreeMap на основе уже имеющегося SortedMap, элементы в которой будут **отсортированы по закону передаваемой SortedMap.**  
   
**private transient Entry<K,V> root;**    
TreeMap имеет поле root в котором объект Entry:  

```java
 static final class Entry<K,V> implements Map.Entry<K,V> {
        K key;
        V value;
        Entry<K,V> left;
        Entry<K,V> right;
        Entry<K,V> parent;
        boolean color = BLACK; 
} 
```  

Вставка объекта (с испольованием компоратора):  
```java
  do {
      Entry<K,V> t = root;
        Entry<K,V> parent;
        Comparator<? super K> cpr = comparator;
        if (cpr != null) {
            do {
                parent = t;
                cmp = cpr.compare(key, t.key);
                if (cmp < 0)
                    t = t.left;
                else if (cmp > 0)
                    t = t.right;
                else
                    return t.setValue(value);
            } while (t != null);   
```  
Идем от корня по ветвям, когда доходим до значения null, значит в parent у нас установлен родитель для нашего нового элемента,
соответственно устанавливаем его:  
Entry<K,V> e = new Entry<>(key, value, parent);    
if (cmp < 0)  
parent.left = e;  
else  
parent.right = e;
(Подробнее - смотреть джава доки, метод put(K key, V value))           
 
Пример:  
[Классы примера](https://github.com/magidin91/theoretical_part/blob/collections/src/main/java/collections/map/TreeMapProgram.java) // помнять ан ссылку из мастера

```java
public class Program{
    public static void main(String[] args) {

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
          
       Map<String, Person> people = new TreeMap<String, Person>();
       people.put("1240i54", new Person("Tom"));
       people.put("1564i55", new Person("Bill"));
       people.put("4540i56", new Person("Nick"));
         
       for(Map.Entry<String, Person> item : people.entrySet()){
           System.out.printf("Key: %s  Value: %s \n", item.getKey(), item.getValue().getName());
       }
    }
}
class Person{
      
    private String name;
    public Person(String name){
          
        this.name = name;
    }
    String getName(){return name;}
}
```  
Ввод элемента в древовидное множество происходит медленнее, чем в хеш-таблицу , но все же намного быстрее,
чем в требуемое место связного списка. Если древовидное множество состоит из п элементов, то в среднем требуется
log(N) сравнений, чтобы найти правильное расположение нового элемента. Так, если древовидное множество уже содержит 1000 элементов, 
для ввода нового элемента потребуется около 10 сравнений.  

Тримап медленнее Хешмапы, но удобна если данные должны быть отсортированы.       
[подробнее](http://www.quizful.net/post/Java-TreeMap)    

[к оглавлению](#collections-pro)  

## Расскажите реализации интерфейса Set.  
![alt text](https://github.com/magidin91/theoretical_part/blob/master/src/main/resources/Set.png)    

**Set** не может содержать дубликаты. Он их перезаписывает.    

+ **HashSet** - основан на HashMap, вместо value заглушка Object
+ **LinkedHashSet** - расширяет HashSet, позволяет получать элементы в порядке их добавления, но требует больше памяти.
+ **TreeSet** - основан на TreeMap, вместо value заглушка Object 
По сути эти сеты не имеют собственные реализации, а основаны на соответствующих мапах.  

[к оглавлению](#collections-pro)  

## LinkedHashМap и LinkedHashSet  
[подробнее](https://habr.com/ru/post/129037/)  
 
Класс LinkedHashMap расширяет класс HashMap. Он создает двусвязный список элементов хешмапы  в том порядке, 
в котором они вводились в него. (или в порядке последнего доступа) Затем двусвязный список он используется для итерации по таблице.      

Конструктор, где можно указать accessOrder:   
```java
   public LinkedHashMap(int initialCapacity, float loadFactor, boolean accessOrder) {
        super(initialCapacity, loadFactor);
        this.accessOrder = accessOrder;
    }
```

```java
public class LinkedHashMap<K,V> extends HashMap<K,V> implements Map<K,V>
{
    /* указывает каким образом будет осуществляться доступ к элементам при использовании итератора. 
       При значении true — по порядку последнего доступа (об этом в конце статьи). 
       При значении false доступ осуществляется в том порядке, в каком элементы были вставлены.
     */
    final boolean accessOrder;

    transient LinkedHashMap.Entry<K,V> head;
    transient LinkedHashMap.Entry<K,V> tail;
    /**
     * HashMap.Node subclass for normal LinkedHashMap entries.
     */
    static class Entry<K,V> extends HashMap.Node<K,V> {
        Entry<K,V> before, after;
        Entry(int hash, K key, V value, Node<K,V> next) {
            super(hash, key, value, next);
        }
    }

    
}
```
Как мы видим из кода добавляется два поля - голова и хвост, а также класс ноды расширяется двумя полями - следующий и предыдущий элемент, 
таким образом мы  сохраняем порядок вставленных элементов.  

**Вставка вызывается следующим образом:**    
+ вызывается метод put из HashMap
+ а в нем вызывается метод Node<K,V> newNode, который переопределен в LinkedHashMap  
```java
<K,V> newNode(int hash, K key, V value, Node<K,V> e) {
        LinkedHashMap.Entry<K,V> p = new LinkedHashMap.Entry<>(hash, key, value, e);
        linkNodeLast(p);
        return p;
    }
```  
+ вызывается метод, который запоминает хвост, затем записывает в хвост новый элемент, если last == null, значит, 
в таблице нет элементов и записываем наш элемент также в голову. Если же нет, то просто записываем для нашего элемента предыдущим элементом 
хвост, а для хвоста записываем следующим элементом - наш новый элемент.  
```java  
  private void linkNodeLast(LinkedHashMap.Entry<K,V> p) {
        LinkedHashMap.Entry<K,V> last = tail;
        tail = p;
        if (last == null)
            head = p;
        else {
            p.before = last;
            last.after = p;
        }
    }
```
 
**accessOrder == true**  
В такой ситуации поведение LinkedHashMap меняется и при вызовах метода get(),  порядок элементов будет изменен — 
элемент к которому обращаемся будет помещен в конец.  
Для этого в LinkedHashMap переопределн метод get():  
```java
 public V get(Object key) {
        Node<K,V> e;
        if ((e = getNode(hash(key), key)) == null)
            return null;
        if (accessOrder)
            afterNodeAccess(e);
        return e.value;
    }
```  
В afterNodeAccess(e); перезаписывается связь.    
      
Эта реализация избавляет своих клиентов от неопределенного, как правило, хаотического порядка, обеспечиваемого HashMap и Hashtable,
без увеличения затрат, связанных с упорядочиванием в TreeMap.  

Данная структура может слегка уступать по производительности родительскому HashMap, при этом время выполнения операций 
add(), contains(), remove() остается константой — O(1). 
Понадобится чуть больше места в памяти для хранения элементов и их связей, но это совсем небольшая плата за дополнительные фишечки.  
Благодаря этому мы сможем проитерировать элемеенты в порядке добавления или доступа.    
 
[к оглавлению](#collections-pro)  

## Отличие Set от List.
                                     List                       Set             

    Duplicates                       YES                        NO               

      Order                        ORDERED          DEPENDS ON IMPLEMENTATION  

    Positional Access(index)         YES                        NO                 

[к оглавлению](#collections-pro)  

## Расскажите, что такое анализ алгоритма.  
[подробнее](https://javarush.ru/groups/posts/2325-slozhnostjh-algoritmov)
[подробнее](https://www.youtube.com/watch?v=ZRdOb4yR0kk&ab_channel=CronisAcademy)  
    
**Сложность алгоритма - это количество шагов алгоритма к количеству данных.**
Это то как соотносится колличество данных со скоростью работы алгоритма.
O(1)-константное время, O(log(n)) , O(n) - линейное.  
  
Для оценки сложности алгоритмов в программировании создали специальное обозначение под названием Big-O (“большая О”).
Big-O позволяет оценить, насколько время выполнения алгоритма зависит от переданных в него данных.   
Скорость алгоритма нельзя оценивать просто с точки зрения времени, т.к. один алгоритм будет выполняться разное время на разных машинах.  
Вместо этого Big-O - показывает какое количество шагов потребуется, чтобы алгоритм завершил свое выполнение.   
 
+ Линейная сложность означает, что при увеличении объема данных время работы алгоритма вырастет примерно пропорционально.  
+ Постоянная сложность означает, что время работы алгоритма не зависит от количества передаваемых в алгоритм данных.
+ Логарифмическая - для алгоритма, где на каждой итерации берется половина элементов - сложность **будет включать log(n)**,
т.е. она может быть больше, например, n * log(n), но сути это не меняет.
Рекурсивная функция = О(N)    

Оценка сложности алгоритма:  
1. Отбрасывание констант (все константные коэффициенты отбрасываются), т.к. мы оцениваем алгоритм на бесконечности, 
то  O(2n) и O(n) - одно и то же, как и O(n) + O(1) =  O(n)    
2. Неважная сложность: O(N^2 +N) = O(N^2),  одна сложность поглощает другую, если она значительно больше (значительно больше = больше в 2 раза)  
Но O(N^2 + B) = O(N^2 + B), выражение не может быть упрощено, т.к. мы ничего не знаем о B, пока мы ничего не знаем о перменной мы не можем выбросить ее из формулы.  
3. Когда сложение, а когда умножение сложности алгоритма:  
Если выполнение циклов последовательно и не зависит от выполнения друг друга, то сложность = O(A + B)  
Если же A цикл находится в B цикле, то циклы зависимы и сложность =  O(A * B)  

Для оцнки сложности алгоритма есть 2 характирестики: 
+ оценка по времени - это то что мы делали выше    
+ оценка по памяти  - нужно оценить, какое дополнительное количество памяти требуется алгоритму, чтобы получить результат, 
в приницпе то же самое, что и оценка по времени, т.е. сколько элементов нужно держать в памяти. Если это константа, 
то - константная сложность, если линейно завсит от числа элементов- то линейная и т.д.       
 
[к оглавлению](#collections-pro)

## Какая временная сложность алгоритмов(O-нотация) добавления, замены и удаления в каждой из коллекций. С чем связаны отличия.  
![alt text](https://github.com/magidin91/theoretical_part/blob/master/src/main/resources/O(n).png)  

+ **ArrayList:** индекс - 0(1), поиск 0(n). вставка 0(n), удаление O(n).
+ **LinkedList:** индекс - 0(n), поиск 0(n). вставка 0(1), удаление O(1).
+ **Деревья** для всех операций - O(log(n)).
+ **Хэш-таблицы** - O(1) для всех операций, если не считать коллизии  

[подробнее](https://habr.com/ru/post/237043/)  

[к оглавлению](#collections-pro)  

## Коллекции общее: 
+ ArrayList - для чтения по индексу
+ LinkedList - если будем вставлять в начало или конец 
+ Hashmap - самая быстрая:  
Добавление элемента выполняется за константное время O(1), но время удаления, получения зависит от распределения хэш-функции.
В идеале является константным, но может быть и линейным O(n)  
+ LinkedHashmap избавляет своих клиентов от неопределенного, как правило, хаотического порядка, обеспечиваемого HashMap и Hashtable,
без увеличения затрат, связанных с упорядочиванием в TreeMap. Понадобится чуть больше места в памяти для хранения элементов и их связей,
но это совсем небольшая плата за дополнительные фишечки. Благодаря этому мы сможем проитерировать элементы в порядке добавления или доступа.           
+ TreeMap - для хранения больших объемов отсортированной информации, которая должна быть быстро найдена. 

## Дополнительные материалы по generics
+ [Сырые типы - Generics #1 - Advanced Java](https://www.youtube.com/watch?v=MniNZsyjH9E&list=PL6jg6AGdCNaX1yIJpX4sgALBTmTVc_uOJ)
+ [Наследование и расширители обобщений - Generics #2 - Advanced Java](https://www.youtube.com/watch?v=pezRhckJbFE&index=2&list=PL6jg6AGdCNaX1yIJpX4sgALBTmTVc_uOJ)
+ [Рекурсивное расширение типа - Generics #3 - Advanced Java](https://www.youtube.com/watch?v=ns8T7-nI_Ec&index=3&list=PL6jg6AGdCNaX1yIJpX4sgALBTmTVc_uOJ)
+ [Александр Маторин — Неочевидные Дженерики](https://www.youtube.com/watch?v=_0c9Fd9FacU&t=4s)
+ [Введение в Java. Generics. Wildcards | Технострим](https://www.youtube.com/watch?v=FRmgcBxJvb4)

[к оглавлению](#collections-pro)