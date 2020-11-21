стратегия
декоратор
dependency Inversion  

## Singleton  
https://refactoring.guru/ru/design-patterns/singleton
Одиночка — это порождающий паттерн, который гарантирует существование только одного объекта определённого класса, 
а также позволяет достучаться до этого объекта из любого места программы.  

Все реализации одиночки сводятся к тому, чтобы скрыть конструктор по умолчанию и создать публичный статический метод, 
который и будет контролировать жизненный цикл объекта-одиночки.  

Если у вас есть доступ к классу одиночки, значит, будет доступ и к этому статическому методу. 
Из какой точки кода вы бы его ни вызвали, он всегда будет отдавать один и тот же объект.  
В этом примере роль Одиночки отыгрывает класс подключения к базе данных.

Этот класс не имеет публичного конструктора, поэтому единственный способ получить его объект — это вызвать метод getInstance. 
Этот метод сохранит первый созданный объект и будет возвращать его при всех последующих вызовах.  

```java
class Database is
    // Поле для хранения объекта-одиночки должно быть объявлено
    // статичным.
    private static field instance: Database

    // Конструктор одиночки всегда должен оставаться приватным,
    // чтобы клиенты не могли самостоятельно создавать
    // экземпляры этого класса через оператор `new`.
    private constructor Database() is
        // Здесь может жить код инициализации подключения к
        // серверу баз данных.
        // ...

    // Основной статический метод одиночки служит альтернативой
    // конструктору и является точкой доступа к экземпляру этого
    // класса.
    public static method getInstance() is
        if (Database.instance == null) {
Database.instance = new Database();
}
//возврщаем статич поле, т.е. имеем всегда один объект   
    return Database.instance;

```

Применимость  
+ Когда в программе должен быть единственный экземпляр какого-то класса, доступный всем клиентам 
(например, общий доступ к базе данных из разных частей программы).  
+ Одиночка скрывает от клиентов все способы создания нового объекта, кроме специального метода. 
Этот метод либо создаёт объект, либо отдаёт существующий объект, если он уже был создан.  

В отличие от глобальных переменных, Одиночка гарантирует, что никакой другой код не заменит созданный экземпляр класса,
поэтому вы всегда уверены в наличии лишь одного объекта-одиночки.  

## Фабрика   
[подробнее](https://javarush.ru/groups/posts/2370-pattern-proektirovanija-factory)   
Шаблон проектирования Фабрика позволяет управлять созданием объектов.  
Фабрика — это шаблон проектирования, который помогает решить проблему создания различных объектов
в зависимости от некоторых условий.  
```java
//общий кофейный класс
public class Coffee {
    public void grindCoffee(){
        // перемалываем кофе
    }
    public void makeCoffee(){
        // делаем кофе
    }
    public void pourIntoCup(){
        // наливаем в чашку
    }
}
//конкретные виды кофе
public class Americano extends Coffee {}
public class Cappuccino extends Coffee {}
public class CaffeLatte extends Coffee {}
public class Espresso extends Coffee {}

//енам, который мы будем использовать, как условия.  
public enum CoffeeType {
    ESPRESSO,
    AMERICANO,
    CAFFE_LATTE,
    CAPPUCCINO
}
```
Сама фабрика:  
Передаем уловие - енам вида кофе, и фабрика создает соответствующий тип кофе.  
```java
public class SimpleCoffeeFactory {
    public Coffee createCoffee (CoffeeType type) {
        Coffee coffee = null;

        switch (type) {
            case AMERICANO:
                coffee = new Americano();
                break;
            case ESPRESSO:
                coffee = new Espresso();
                break;
            case CAPPUCCINO:
                coffee = new Cappucino();
                break;
            case CAFFE_LATTE:
                coffee = new CaffeLatte();
                break;
        }
        return coffee;
    }
} 

```
Класс  кофейни с использованием фабрики:
```java
public class CoffeeShop {

    private final SimpleCoffeeFactory coffeeFactory;

    public CoffeeShop(SimpleCoffeeFactory coffeeFactory) {
        this.coffeeFactory = coffeeFactory;
    }

    public Coffee orderCoffee(CoffeeType type) {
        Coffee coffee = coffeeFactory.createCoffee(type);
        coffee.grindCoffee();
        coffee.makeCoffee();
        coffee.pourIntoCup();

        System.out.println("Вот ваш кофе! Спасибо, приходите еще!");
        return coffee;
    }
}

```    
Т.е. алгоритм следующий, создаем фабрику - т.е. метод, который принимает некоторое условие и в зависимости от него создает 
конкретный дочерний экземпляр (например в switch операторе). 
Далее мы создаем класс, который будет использовать фабрику. В нем мы передаем объект фабрики, и вызываем метод фабрики, 
т.е. делегируем создание конкретного кофе классу фабрики.  

## Фабричный метод  
[подробнее](https://javarush.ru/groups/posts/2372--patternih-proektirovanija-factorymethod)   

```java
public abstract class Coffee {
    public void makeCoffee(){
        // делаем кофе
    }
    public void pourIntoCup(){
        // наливаем в чашку
    }
}

public abstract class CoffeeShop {

    public Coffee orderCoffee(CoffeeType type) {
        Coffee coffee = createCoffee(type);

        coffee.makeCoffee();
        coffee.pourIntoCup();

        System.out.println("Вот ваш кофе! Спасибо, приходите еще!");
        return coffee;
    }

    protected abstract Coffee createCoffee(CoffeeType type);
}

public class ItalianCoffeeShop extends CoffeeShop {

    @Override
    public Coffee createCoffee (CoffeeType type) {
        Coffee coffee = null;
        switch (type) {
            case AMERICANO:
                coffee = new ItalianStyleAmericano();
                break;
            case ESPRESSO:
                coffee = new ItalianStyleEspresso();
                break;
            case CAPPUCCINO:
                coffee = new ItalianStyleCappuccino();
                break;
            case CAFFE_LATTE:
                coffee = new ItalianStyleCaffeLatte();
                break;
        }
        return coffee;
    }
}

public class AmericanCoffeeShop extends CoffeeShop {
    @Override
    public Coffee createCoffee (CoffeeType type) {
        Coffee coffee = null;

        switch (type) {
            case AMERICANO:
                coffee = new AmericanStyleAmericano();
                break;
            case ESPRESSO:
                coffee = new AmericanStyleEspresso();
                break;
            case CAPPUCCINO:
                coffee = new AmericanStyleCappuccino();
                break;
            case CAFFE_LATTE:
                coffee = new AmericanStyleCaffeLatte();
                break;
        }

        return coffee;
    }
}

public class Main {
    public static void main(String[] args) {
        CoffeeShop italianCoffeeShop = new ItalianCoffeeShop();
        italianCoffeeShop.orderCoffee(CoffeeType.CAFFE_LATTE);

        CoffeeShop americanCoffeeShop = new AmericanCoffeeShop();
        americanCoffeeShop.orderCoffee(CoffeeType.CAFFE_LATTE);
    }
}  
``` 
Паттерн фабричный метод определяет интерфейс создания объекта, но позволяет субклассам выбрать класс создаваемого экземпляра. 
Таким образом, Фабричный метод делегирует операцию создания экземпляра субклассам.  
  
У абстрактоной кофейни есть абстрактный метод - Coffee createCoffee(CoffeeType type)), а конкретные кофейни предлагают конкретные реализации 
этого метода, которые создают соответствующие им виды кофе (выбирают класс создаваемог оэкземпляра).        

## Стратегия   

## Декоратор, вынести из основного конспекта + все остальное.  

Чтобы понять этот материал, тебе нужно быть знакомым с шаблонами проектирования, особенно с Наблюдателем (Observer) и Фасадом (Facade).    