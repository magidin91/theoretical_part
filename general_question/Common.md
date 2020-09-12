## Common questions  

+ [Как работает статическая типизация в Java](#Как-работает-статическая-типизация-в-Java)

## Как работает статическая типизация в Java  

+ Статическая типизация — проверка безопасности типов программы на этапе компиляции
 (путем анализа исходного кода программы).
+ Динамическая типизация — проверка безопасности типов программы во время ее выполнения.  

Java использует статическую проверку типов, то есть анализ программы во время ее компиляции.
Основная идея выбора такого подхода проверки типов состоит в том, чтобы проверять типы только 1 раз при компиляции,
тем самым повышая скорость выполнения программы. Также это позволяет обнаруживать возможные ошибки.  
Статическая типизация в Java  

Давайте рассмотрим простой пример статической типизации в Java. Есть два класса: Alpha и Beta с методами, которые что-то делают:

```java
class Alfa {
	Alfa getThis() {
		return this;
	}
 
	public void doAlfa() {
		System.out.println("что-то делаем в Alfa");
	}
}
 
class Beta extends Alfa {
	public void doBeta() {
		System.out.println("что-то делаем в Beta");
	}
}

class Omega extends Alfa{
	public void doOmega() {
		System.out.println("что-то делаем с Omega");
	}
}
```
Примеры:  
+ new Beta().getThis()? - работает, т.к. B - наследник А (кастинг вверх) 
+ new Beta().getThis().doBeta(); - ошибка компиляции  
Проблема заключается в том, что ее исходный тип Аlfa и компилятор не знает реальный тип во время компиляции, 
поэтому он видит объект типа Аlfa. Исходя из этого нам не доступен метод doBeta().
+ ((Beta) new Beta().getThis()).doBeta(); - обошли проблему с помощью приведения типов к Beta:
+ ((Omega) new Beta().getThis()).doOmega(); - ройдет статическую компиляцию, т.к. альфа можно привести к омега, но упадет во время выполнения    
Так написать можно и это даже пройдет статическую проверку типов (по той же причине, что и с new Beta().getThis().doBeta();), 
однако во время выполнения будет брошено исключение «java.lang.ClassCastException:
Beta cannot be cast to Omega«, так как Beta не может быть приведена к Omega.  

[подробнее](https://javadevblog.com/kak-rabotaet-staticheskaya-tipizatsiya-v-java.html)  

[к оглавлению](#Common-questions)