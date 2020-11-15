## SPRING
1. [Habr](https://habr.com/ru/post/490586/) или копия на комп-ре  
https://javarush.ru/groups/posts/2259-jpa--znakomstvo-s-tekhnologiey jpa
https://topjava.ru/blog/spring-framework-vs-spring-boot-differences  
http://www.finecosoft.ru/SpringDI

**Вопросы для собеседований:**    
https://github.com/timmson/java-interview/blob/master/011-spring.md#%D0%B2-%D1%87%D1%91%D0%BC-%D1%80%D0%B0%D0%B7%D0%BD%D0%B8%D1%86%D0%B0-%D0%BC%D0%B5%D0%B6%D0%B4%D1%83-bean-%D0%B8-component
https://javastudy.ru/interview/jee-spring-questions-answers/  
16, 18, 22, 23, 24, 25, 30, 
https://habr.com/ru/post/350682/  

Spring описывают как облегченную платформу для построения Java-приложений. 
Во-первых, Spring можно использовать для построения любого приложения на языке Java (т.е. автономных, веб приложений, приложений JEE и т.д.), 
что отличает Spring от многих других платформ, таких как Apache Struts, которая ограничена только веб-приложениями. 
Во-вторых, характеристика “облегченная” в действительности не имеет никакого отношения к количеству классов или размеру дистрибутива; 
напротив, она определяет принцип всей философии Spring — минимальное воздействие. Платформа Spring является облегченной в том смысле, 
что для использования ядра Spring вы должны вносить минимальные  изменения в код своего приложения, а если в какой-то момент 
вы решите больше не пользоваться Spring, то и это сделать очень просто. О
братите внимание, что речь идет только о ядре Spring — многие дополнительные компоненты Spring, такие как доступ к
данным, требуют более тесной привязки к Spring Framework.  

К достоинствам Spring можно отнести:  

+ Внедрение зависимостей (DI) и инверсия управления (IoC) позволяют писать независимые друг от друга компоненты, 
что дает преимущества в командной разработке, переносимости модулей и т.д..
+ Spring IoC контейнер управляет жизненным циклом Spring Bean.
+ Проект Spring содержит в себе множество подпроектов, которые затрагивают важные части создания софта, такие как вебсервисы,
веб программирование, работа с базами данных, загрузка файлов, обработка ошибок и многое другое. 
Всё это настраивается в едином формате и упрощает поддержку приложения.        

Основное:
1. Внедрении зависимостей (Spring IOC, Dependency Injection)  
Inversion of Control - контейнер: конфигурирование компонентов приложений и управление жизненным циклом Java-объектов.  
2. Прокси Cglib, AOP (пр. @Transactional)
3. Управление ресурсами.   
+ ApplicationContext ctx = new AnnotationConfigApplicationContext(someConfigClass);   
Resource aFileTemplate = ctx.getResource("file:///someDirectory/application.properties");  
4. Spring Environment  
+ Получение пропертей из файлов типа application.properties  
ApplicationContext ctx = new AnnotationConfigApplicationContext(someConfigClass);  
Environment env = ctx.getEnvironment();   
String databaseUrl = env.getProperty("database.url");
+ Spring @PropertySources  
5. Spring аннотация @Value и внедрение значений свойств  

Цель Spring Framework состоит в том, чтобы «упростить» доступную функциональность Java, подготовить ее к внедрению зависимостей и, 
следовательно, упростить использование API в контексте Spring.  
    

## Spring общее  
Spring FrameWork содержит основные аннотации, используемые в спирнг. (Dependency injection)  
Spring Boot создает и запускает спринг приложение.  
Spring Data – для подключения к бд  
Spring Integration – обмен сообщениями между приложениями.  
Spring Batch – упрощает обмен большими пакетами инф-ии.  
Spring Social – подключение api известных приложений (facebook, twitter)  
Spring Mobile – для разработки мобильных приложений.  

[к оглавлению](#SPRING)

## Dependency injection  
Внедрение зависимости (Dependency injection, DI) — процесс предоставления внешней зависимости программному компоненту. 
Является специфичной формой «инверсии управления» ( Inversion of control, IoC), когда она применяется к управлению зависимостями.  

Достоинства Dependency injection:  
+ Сокращение объема связующего кода. Зачастую этот код очень прост — при создании зависимости должен создаваться новый экземпляр соответствующего объекта.  
+ Упрощенная конфигурация приложения. Для конфигурирования классов, которые могут быть внедрены в другие классы, можно использовать аннотации или XML-файлы.  
+ Улучшенная возможность тестирования. Когда классы проектируются для DI, становится возможной простая замена зависимостей. 
+ Стимулирование качественных проектных решений для приложений. Вообще говоря, проектирование для DI означает проектирование с использованием интерфейсов.
+ Благодаря Dependency injection возможно изменение поведения системы без ее перекомпиляции только за счет ее конфигурации, 
т.е. изменения конфигурационого xml-файла или property-файла, или аннотаций.

**Как реализуется DI в Spring Framework?**  
Реализация DI в Spring основана на двух ключевых концепциях Java — компонентах JavaBean и интерфейсах.  

Типы реализации внедрения зависимостей в Spring:
+ Через поля
+ Через конструкторы
+ Через сеттеры


+ Bean - управляемый контейнером компонент (managed component), т.е. бин создается и инициализируется контейнером и 
существует внутри него.  
Анотации:
@Component("name) 
@Autowired, 
@Primary - этот бин будет инджектиться, если мы не указали @Qualifier
@Qualifier ("name) - указывает на конкретный бин. (ичпользуется вместе с @Autowired), у бина в @Component("name) должно быть обозначено имя.  

[к оглавлению](#SPRING)

## Inversion of Control  
[инверсия управления](https://habr.com/ru/post/490586/)  
По своей сути IoC, а, следовательно, и DI, направлены на то, чтобы предложить простой механизм для предоставления зависимостей компонента. и 
      
**Inversion of Control (Инверсия управления) - это когда класс получает зависимость внешнием образом (например, когда мы передаем ее через аргумент конструктора), 
в отличие от случая, когда зависимость создается внутри класса например, как в статической фабрике как в предыдущем примере.  
Т.е. класс не знает, как создается зависимость.**        

Пример:  
Для подключения к БД, нам нужно создать connection, для этого мы можем, например, создать статический класс, который 
будет возвращать этот connection.  

Однако у этого решения есть еще несколько недостатков:
+ UserDAO активно должен знать, где получить свои зависимости, он должен вызвать класс, создающий connection.
+ Если ваша программа становится больше, и вы получаете все больше и больше зависимостей, у вас будет один монстр класс (Application.INSTANCE.dataSource()), 
который обрабатывает все ваши зависимости. В этот момент вы захотите разделить вещи на несколько классов / фабрик и т.д.  

Давайте сделаем еще один шаг вперед.
Было бы неплохо, если бы вам в классе UserDAO вообще не приходилось беспокоиться о поиске зависимостей? 
Вместо того, чтобы активно вызывать Application.INSTANCE.dataSource(), ваш UserDAO мог бы сообщить, что он ему нужен, но больше не контролирует, 
когда / как / откуда он его получает?  
Это то, что называется инверсией управления (Inversion of Control).  
**Инверсия управления - это когда класс получает зависимость внешнием образом (например, когда мы передаем ее через аргумент конструктора)**, 
в отличие от случая, когда зависимость создается внутри класса например, как в статической фабрике как в предыдущем примере.    
```java
import javax.sql.DataSource;

public class UserDao {

    private DataSource dataSource;

    private UserDao(DataSource dataSource) { // (1)
        this.dataSource = dataSource;
    }

    public User findById(Integer id) {
        try (Connection connection = dataSource.getConnection()) { // (2)
               PreparedStatement selectStatement = connection.prepareStatement("select * from users where id =  ?");
               // TODO execute the select etc.
        }
    }

    public User findByFirstName(String firstName) {
        try (Connection connection = dataSource.getConnection()) { // (2)
               PreparedStatement selectStatement = connection.prepareStatement("select * from users where first_name =  ?");
               // TODO execute the select etc.
        }
    }
}
```
+ Всякий раз, когда вызывающий создает новый UserDao через свой конструктор, вызывающий также должен передать действительный источник данных.
+ Методы findByX будут просто использовать этот источник данных.

С точки зрения UserDao это выглядит намного лучше. Он больше не знает ни о классе приложения, ни о том, как создавать сами источники данных. 
Он только объявляет миру, что «если вы хотите создать (то есть использовать) меня, вам нужно дать мне источник данных».  
Но представьте, что вы хотите запустить свое приложение. Если раньше вы могли вызывать «new UserService()», то теперь вам нужно обязательно вызвать новый UserDao(dataSource).  
Следовательно, проблема в том, что вы, как программист, все еще создаете UserDAO с помощью их конструктора и, таким образом, устанавливаете зависимость DataSource вручную.  

Разве не было бы хорошо, если бы кто-то знал, что ваш UserDAO имеет зависимость от конструктора DataSource, и знал, как ее создать? 
А затем волшебным образом сконструирует для вас оба объекта: работающий DataSource и работающий UserDao?
**Этот кто-то является контейнером внедрения зависимостей и является именно тем, что представляет собой среда Spring.**  
Как уже упоминалось в самом начале, Spring Framework по своей сути является контейнером внедрения зависимостей, 
который управляет написанными вами классами и их зависимостями для вас.  

## Dependency injection  
Внедрение зависимости (Dependency injection, DI) — процесс предоставления внешней зависимости программному компоненту.  
Т.е. создание экземпляров классов и внедрение в зависимости.    
Является специфичной формой «инверсии управления»( Inversion of control, IoC)  

## В чем разница между IOC (Inversion of Control) и Application Context?
IOC — инверсия управления. Вместо ручного внедрения зависимостей, фреймворк забирает ответственность за это.
ApplicationContext — реализация IOC спрингом.

Bean Factory — это базовая версия IOC контейнера  
Application Context также включает дополнительные функции, которые обычно нужны для разработки корпоративных приложений    

## BeanFactory
Основным интерфейсом, отвечающим за управление компонентами и их зависимостями в Spring Framework, является BeanFactory («фабрика бинов»). 
В определенный момент времени приложение должно инициализировать класс, реализующий этот интерфейс, и предоставить информацию о конфигурации системы.
 
BeanFactory - это корневой интерфейс для доступа к контейнеру бинов.   

## ApplicationContext  
ApplicationContext — реализация IoC в Spring (это контейнер зависимостей). Bean Factory — это базовая версия IoC контейнера Application Context 
также включает дополнительные функции, которые обычно нужны для разработки корпоративных приложений.  
  
Тот, кто контролирует все ваши классы и может управлять ими соответствующим образом 
(читай: создайте их с необходимыми зависимостями), называется ApplicationContext.  

Чего мы хотим добиться, так это следующего кода:  
```java
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import javax.sql.DataSource;

public class MyApplication {

    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(someConfigClass); // (1)

        UserDao userDao = ctx.getBean(UserDao.class); // (2)
        User user1 = userDao.findById(1);
        User user2 = userDao.findById(2);

        DataSource dataSource = ctx.getBean(DataSource.class); // (3)
        // etc ...
    }
}
``` 

+ Здесь мы создаем наш Spring ApplicationContext. 
+ ApplicationContext может дать нам полностью сконфигурированный UserDao, то есть один с его набором зависимостей DataSource.   

Вам, как вызывающей стороне, больше не нужно беспокоиться о создании классов, вы можете просто попросить ApplicationContext предоставить вам рабочие классы.  


В приведенном выше коде мы помещаем переменную с именем someConfigClass в конструктор AnnotationConfigApplicationContext.  
```java
    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(someConfigClass); // (1)
        // ...
    }
}
```
То, что вы действительно хотите передать в конструктор ApplicationContext, это ссылка на класс конфигурации, который должен выглядеть следующим образом:  
```java
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyApplicationContextConfiguration {  // (1)

    @Bean
    public DataSource dataSource() {  // (2)
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setUser("root");
        dataSource.setPassword("s3cr3t");
        dataSource.setURL("jdbc:mysql://localhost:3306/myDatabase");
        return dataSource;
    }

    @Bean
    public UserDao userDao() { // (3)
        return new UserDao(dataSource());
    }

}
```   
## В чем различие между web.xml и the Spring Context - servlet.xml?  
web.xml — Метаданные и конфигурация любого веб-приложения, совместимого с Java EE. Java EE стандарт для веб-приложений.  
servlet.xml — файл конфигурации, специфичный для Spring Framework.  

## Что делает аннотация @Bean? Что такое Spring Bean?  
Бин - это управляемый контейнером компонент, то есть бин создается и инициализируется контейнером и существует внутри него. 
Бин - это java класс.  
  
## Какое значение имеет конфигурационный файл Spring Bean?
Конфигурационный файл спринг определяет все бины, которые будут инициализированы в Spring Context. 
При создании экземпляра Spring ApplicationContext будет прочитан конфигурационный xml файл и выполнены указанные в нем необходимые инициализации. 
Отдельно от базовой конфигурации, в файле могут содержаться описание перехватчиков (interceptors), view resolvers, настройки локализации и др..  

## В чём разница между @Bean и @Component?  
@Bean используется в конфигурационных классах Spring. Он используется для непосредственного создания бина.
@Component используется со всеми классами, которыми должен управлять Spring. Когда Spring видит класс с @Component, 
Spring определяет этот класс как кандидата для создания bean.  

## Что такое жизненный цикл Spring Bean?
Жизненный цикл Spring бина – время существования класса.
Spring бины инициализируются при инициализации Spring контейнера и происходит внедрение всех зависимостей.
Когда контейнер уничтожается, то уничтожается и всё содержимое. Если нам необходимо задать какое-либо действие при инициализации и уничтожении бина, 
то нужно воспользоваться методами init() и destroy(). Для этого можно использовать аннотации @PostConstruct и @PreDestroy().  
```java
 @PostConstruct
    public void init(){
        System.out.println("Bean init method called");
    }
     

    @PreDestroy
    public void destroy(){
        System.out.println("Bean destroy method called");
    }
```

Или через xml конфигурацию:
```xml
<bean name="myBean" class="ru.javastudy.spring.MyBean"
        init-method="init" destroy-method="destroy">
    <property name="someProp" ref="someProp"></property>
</bean>
```

Методы жизненного цикла управляемого компонента (lifecycle callback methods) без аннотаций:  
Для того, чтобы продемонстрировать обратный вызов от контейнера к управляемому компоненту после его инициализации, 
необходимо немного расширить используемый во многих предыдущих примерах класс Person – надо реализовать 
предусмотренный интерфейсом InitializingBean метод afterPropertiesSet.
Кроме того для демонстрации возможности указания callback метода при конфигурации, будет реализован метод init:  

```java
publicclass ExtendedPerson extends Person
      implements InitializingBean{
      publicvoid init() {
            System.out.println("Init callback method");
      }
     
      publicvoid afterPropertiesSet() throws Exception {
            System.out.println("Method of the InitializingBean interface");
      }
}
```

## Каковы различные типы автоматического связывания в Spring?  
https://proselyte.net/tutorials/spring-tutorial-full-version/autowiring/  

## Что такое Spring bean scope?
Сколько экземпляров наших DAO следует создать в Spring? Чтобы ответить на этот вопрос, вам нужно узнать о bean scope (область применения бина).  
+ Должен ли Spring создать singleton: все ваши DAO используют один и тот же источник данных?
+ Должен ли Spring создать prototype: все ваши DAO получают свой собственный источник данных?
+ Или ваши компоненты должны иметь еще более сложные области действия, например: новый источник данных для HttpRequest? Или за HttpSession? Или за WebSocket?

```java
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyApplicationContextConfiguration {

    @Bean
    @Scope("singleton")
    // @Scope("prototype") etc.
    public DataSource dataSource() {
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setUser("root");
        dataSource.setPassword("s3cr3t");
        dataSource.setURL("jdbc:mysql://localhost:3306/myDatabase");
        return dataSource;
    }
}
```  

Аннотация области (scope annotation) определяет, сколько экземпляров создаст Spring. И, как упоминалось выше, это довольно просто:  
Scope("singleton") → Ваш бин будет синглтоном, т.е. будет только один экземпляр.  
Scope("prototype") → Каждый раз, когда кому-то нужна ссылка на ваш компонент, Spring создает новый. (Здесь есть несколько предостережений, например, внедрения прототипов в синглтоны).  
Scope("session") → Для каждого сеанса HTTP пользователя будет создан один компонент.  
и т.п.  

## Что такое BeanPostProcessor?
Интерфейс BeanPostProcessor позволяет вклиниться в процесс настройки ваших бинов до того, как они попадут в контейнер.
Интерфейс несет в себе несколько методов.  
```java
public interface BeanPostProcessor {
    Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException;

    Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException;
}
```

Оба метода вызываются для каждого бина. У обоих методов параметры абсолютно одинаковые. 
Разница только в порядке их вызова. Первый вызывается до init-метода, второй, после. Важно понимать,
что на данном этапе экземпляр бина уже создан и идет его дополнительная настройка. Тут есть два важных момента:

Оба метода в итоге должны вернуть бин. Если в методе вы вернете null, то при получении этого бина из контекста вы получите null, 
а поскольку через BeanPostProcessor проходят все бины, после поднятия контекста, при запросе любого бина вы будете получать фиг, в смысле null.  

Если вы хотите сделать прокси над вашим объектом, то имейте ввиду, что это принято делать после вызова init метода, 
иначе говоря это нужно делать в методе postProcessAfterInitialization.
Процесс дополнительной настройки показан на рисунке ниже. 
Порядок в котором будут вызваны BeanPostProcessor не известен, но мы точно знаем что выполнены они будут последовательно.    

## @ComponentScan  
ComponentScan говорит Spring, где искать бины.  
 
1. Искать бины в том же пакете, где класс конфигурации.  
То, что делает эта аннотация @ComponentScan, это сказать Spring: Посмотрите на все классы Java в том же пакете, 
что и конфигурация контекста и найди бины, помеченные соответствующими аннотациями типа @Component!
Это означает, что, если ваш MyApplicationContextConfiguration находится в пакете com.marcobehler, 
Spring будет сканировать каждый пакет, включая подпакеты, который начинается с com.marcobehler для поиска потенциальных компонентов Spring.  

2. Еще В @ComponentScan можно явно указать пакеты, которые должны сканироваться.  
  
3. Также если у нас есть xml-конфигурация, можно в ней поределить тег ComponentScan и обозначить пакеты, где искать бины.    

**SpringBoot:**  
```java
 @SpringBootApplication
 public class Application {
     public static void main(String[] args) {
         SpringApplication.run(Application.class, args);
     }
 }
```  
@SpringBootApplication определяет автоматическое сканирование пакета, где находится класс Application
Всё будет в порядке, ваш код целиком находится в указанном пакете или его подпакетах.  

Однако, если необходимый вам компонент находится в другом пакете, вы должны использовать дополнительно аннотацию @ComponentScan, 
где перечислите все дополнительные пакеты для сканирования.   

## Инициализация примитивов (простых значений)
Свойства класса, которые должны быть инициализированы примитивными значениями, такие как строка или число, задаются в конфигурационном файле с помощью атрибута или дочернего элемента value. Чтобы инициализировать экземпляр класса Person необходимые значения могут быть заданы в конфигурационном файле следующим образом:
```xml
     <bean id"person" class="com.company.initializationdemo.Person">
           <property name="firstName" value="Ivan"/>
           <property name="lastName">
                 <value>Segeev</value>
           </property>
           <property name="age">
                 <value>30</value>
           </property>
     </bean>
```

## AOP и прокси Cglib    
В Spring по умолчанию используется AOP на основе прокси. Он оборачивает ваши компоненты в прокси для достижения таких вещей, как управление транзакциями. 
 
Spring желает создавать прокси?  
Потому что это позволяет Spring дать вашим компонентам дополнительные функции без изменения кода. 
В сущности, это то, что является аспектно-ориентированным (или: AOP) программированием.

Давайте рассмотрим самый популярный пример AOP — аннотацию Spring @Transactional.  

```java
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyApplicationContextConfiguration {

    @Bean
    public UserService userService() { // (1)
        return new UserService();
    }
}

@Component
public class UserService {

    @Transactional           // (2)
    public User activateUser(Integer id) {  // (1)
        // execute some sql
        // send an event
        // send an email
    }
}
``` 

@Transactional сигнализирует Spring, что для работы этого метода activateUser необходимо открытое соединение с базой данных / транзакция 
и что указанная транзакция также должна быть зафиксирована в конце. И Spring должна сделать это.  

Проблема: хотя Spring может создать ваш компонент UserService через конфигурацию applicationContext, он не может переписать ваш UserService. 
Он не может просто вставить туда код, который открывает соединение с базой данных и фиксирует транзакцию с базой данных.
Но что она может сделать, так это создать прокси вокруг вашего UserService, который будет транзакционным. 
Таким образом, только прокси-сервер должен знать, как открывать и закрывать соединение с базой данных, а затем может просто делегировать его вашему UserService.  

ContextConfiguration для @ Transactional: 
```java
@Configuration
@EnableTransactionManagement // (1)
public class MyApplicationContextConfiguration {

    @Bean
    public UserService userService() { // (2)
        return new UserService();
    }
}
``` 
Мы добавили аннотацию, сигнализирующую Spring: да, нам нужна поддержка @Transactional, которая автоматически включает прокси Cglib под капотом.  
С указанным выше набором аннотаций Spring не просто создает и возвращает ваш UserService здесь. Он создает Cglib-прокси вашего компонента, 
который выглядит, пахнет и делегирует ваш UserService, но фактически оборачивает ваш UserService и предоставляет свои функции управления транзакциями.  

Прокси являются выбором по умолчанию при программировании AOP с помощью Spring. Однако вы не ограничены использованием прокси, 
вы также можете пройти полный маршрут AspectJ, который при желании изменяет ваш фактический байт-код.   

## Управление ресурсами Spring  
Абстракция ресурсов Spring:  
```java
import org.springframework.core.io.Resource;

public class MyApplication {
    public static void main(String[] args) {
            ApplicationContext ctx = new AnnotationConfigApplicationContext(someConfigClass); // (1)

            Resource aClasspathTemplate = ctx.getResource("classpath:somePackage/application.properties"); // (2)

            Resource aFileTemplate = ctx.getResource("file:///someDirectory/application.properties"); // (3)

            Resource anHttpTemplate = ctx.getResource("https://marcobehler.com/application.properties"); // (4)

            Resource depends = ctx.getResource("myhost.com/resource/path/myTemplate.txt"); // (5)

            Resource s3Resources = ctx.getResource("s3://myBucket/myFile.txt"); // (6)
    }
}
```
Как всегда, вам нужен ApplicationContext для начала.  
+ Когда вы вызываете getResource() для applicationContext со строкой, которая начинается с classpath:, Spring будет искать ресурс в вашем application classpath.
+ Когда вы вызываете getResource() со строкой, начинающейся с file:, Spring будет искать файл на вашем жестком диске.
+ Когда вы вызываете getResource() со строкой, которая начинается с https: (или http), Spring будет искать файл в Интернете.  

Короче говоря, Spring дает вам возможность доступа к ресурсам с помощью приятного небольшого синтаксиса. Интерфейс ресурса имеет несколько интересных методов:   
```java
public interface Resource extends InputStreamSource {

    boolean exists();

    String getFilename();

    File getFile() throws IOException;

    InputStream getInputStream() throws IOException;

    // ... other methods commented out
}
```
Это позволяет вам делать все, что вы хотите с ресурсом, независимо от того, живете ли вы в Интернете, на вашем пути к классам или на жестком диске.  

## Spring Environment  
Большая часть любого приложения — это чтение свойств (properties), таких как имя пользователя и пароли базы данных,
конфигурация почтового сервера, детализированная конфигурация оплаты и т.д.  
В простейшем виде эти свойства находятся в файлах .properties, и их может быть много:  
в файловой системе или на сетевом диске, Некоторые могут даже прийти в форме переменных среды операционной системы.  

Spring пытается упростить вам регистрацию и автоматический поиск свойств во всех этих различных источниках с помощью абстракции environment.  

```java
import org.springframework.core.env.Environment;
public class MyApplication {

    public static void main(String[] args) {
           ApplicationContext ctx = new AnnotationConfigApplicationContext(someConfigClass);
           Environment env = ctx.getEnvironment(); // (1)
           String databaseUrl = env.getProperty("database.url"); // (2)
           boolean containsPassword = env.containsProperty("database.password");
           // etc
    }
}
```
Через applicationContext вы всегда можете получить доступ к текущей среды (environment) выполняемого Spring приложения.
Среда (environment), с другой стороны, позволяет вам получать доступ к свойствам. **(это же можно сделать через аннотацию @value)**      

## Spring @PropertySources
В двух словах, среда состоит из одного или многих источников свойств выполняемого Spring приложения. Например:
+ /mydir/application.properties
+ classpath:/application-default.properties  

Довольно легко определить новые @PropertySources самостоятельно:    
```java
import org.springframework.context.annotation.PropertySources;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySources(
        {@PropertySource("classpath:/com/${my.placeholder:default/path}/app.properties"),
         @PropertySource("file://myFolder/app-production.properties")})
public class MyApplicationContextConfiguration {
    // your beans
}
```  
Аннотация @PropertySource работает с любым допустимым классом конфигурации Spring и позволяет вам определять новые дополнительные источники 
с помощью абстракции ресурсов Spring: помните, что все дело в префиксах: http://, file://, classpath: и т.д.,

Определение свойств через @PropertySources — это хорошо, но разве нет лучшего способа, чем пройти через среду, чтобы получить к ним доступ? Да, есть.  

## Spring аннотация @Value и внедрение значений свойств  
Альтернатива использования Environment.  
Вы можете внедрять значения свойств в ваши bean-компоненты, так же, как вы бы добавили зависимость с аннотацией @Autowired.
Но для свойств вам нужно использовать аннотацию @Value.  
 ```java
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;

@Component
public class PaymentService {

    @Value("${paypal.password}")  // (1)
    private String paypalPassword;

     public PaymentService(@Value("${paypal.url}") String paypalUrl) { // (2)
         this.paypalUrl = paypalUrl;
    }
}
```  
Всякий раз, когда вы используете аннотацию @Value, Spring будет проходить через вашу (иерархическую) среду 
и искать соответствующее свойство — или выдавать сообщение об ошибке, если такого свойства не существует.  

## MVC  
[подробнее](https://javarush.ru/groups/posts/2536-chastjh-7-znakomstvo-s-patternom-mvc-model-view-controller)    
MVC — это именно набор архитектурных идей и принципов для построения сложных систем с пользовательским интерфейсом.    
Model. Первая компонента/модуль — так называемая модель. Она содержит всю бизнес-логику приложения.   
View. Вторая часть системы — вид. Данный модуль отвечает за отображение данных пользователю. Все, что видит пользователь, генерируется видом.  

Controller. Третьим звеном данной цепи является контроллер. В нем хранится код, который отвечает за обработку действий пользователя
(любое действие пользователя в системе обрабатывается в контроллере).  

Модель — самая независимая часть системы. Настолько независимая, что она не должна ничего знать о модулях Вид и Контроллер.
Модель настолько независима, что ее разработчики могут практически ничего не знать о Виде и Контроллере.

Основное предназначение Вида — предоставлять информацию из Модели в удобном для восприятия пользователя формате. Основное ограничение Вида — он никак не должен изменять модель.  
Основная цель следования принципам MVC — отделить реализацию бизнес-логики приложения (модели) от ее визуализации (вида). 

Модель - модели и сервисы, контроллер - контроллер, вид - это фронт, который мы используем, т.е. в контроллере всегда возвращается лист объектов, 
а отображение их может быть разное в зависимости от используемого фронта, который мы можем менять, не меняя контроллер.  (Thymeleaf, angular)   
 
## Spring Web MVC  
Spring Web MVC, также известный как Spring MVC, является веб-средой Spring.  

Аннотации контроллеров:  
Это могут быть RestControllers (где вы отправляете XML или JSON клиенту) или старые добрые HTML-контроллеры, 
где вы генерируете HTML с такими фреймворками, как Thymeleaf, Velocity или Freemarker.
  
Controllers в MVC:    
**DispatcherServlet** в MVC - это сервлет, который обрабатывает любой входящий HTTP-запрос, 
анализирует его содержимое и пересылает данные в виде объектов Java в класс Controller.  
Он также берет выходные данные с этих контроллеров и конвертирует их в HTML / JSON / XML, в зависимости от того, что подходит.  
Т.е. DispatcherServlet отвечает за маршрутизацию и маппинг.  

Как передать переменную в html запрос?  
```html
<html>
  <body>
    Hello $user.name, this is your account!
    <!-- list subscriptions etc -->
  </body>
</html>
```
```java
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class AccountController {

    @GetMapping("/account/{userId}")
    public String account(Model model, @PathVariable Integer userId) { // (1)
        // TODO validate user id
        model.addAttribute("user", userDao.findById(userId)); // (2)
        return "templates/account"; // (3)
    }
}
```
Spring может автоматически ввести параметр "Model" в методы вашего контроллера.     
+ Как упоминалось ранее, модель содержит любые данные, которые вы хотели бы представить в своем представлении, т.е. ваш шаблон.
+ Модель Spring ведет себя почти как map (отображение), вы просто добавляете в нее все свои данные и затем можете ссылаться на ключи отображения из вашего шаблона.
+ Опять же, это ссылка на ваш view, шаблон аккаунта, который мы написали выше.  

## Как генерировать JSON / XML (представления) с помощью Spring Web MVC 
 
```java
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HealthController {

    @GetMapping("/health")
    @ResponseBody // (1)
    public HealthStatus health() {
        return new HealthStatus(); // (2)
    }
}
```  
+ На этот раз вы добавляете дополнительную аннотацию @ResponseBody в метод контроллера, который сообщает Spring, 
что вы хотите записать свой Java-объект HealthStatus непосредственно в HttpResponse (например, в виде XML или JSON).
+ Вы просто возвращаете простой Java-объект внутри вашего метода, а не строковую ссылку на ваше представление.  

Как работает согласование контента (content negotiation) Spring MVC?  
+ Указав заголовок Accept, «Accept: application/json» или «Accept: application/xml»
+ Добавив расширение URL к вашему пути запроса,  /health.json или /health.xml
+ Добавив параметр запроса в путь запроса, например /health?Format = json  

Spring MVC @RestController — это не что иное, как Spring MVC @Controller в сочетании с аннотацией Spring MVC @ResponseBody.  

Вкратце: он позволяет вам сосредоточиться на написании Java-классов, вместо того, чтобы иметь дело с сланцевым кодом сервлета, 
то есть работать с Http-запросами и ответами, и дает вам хорошее разделение проблем между вашей моделью и представлениями.  

## Spring Boot  
[подробнее](https://habr.com/ru/post/435144/)   
[подробнее](https://www.javadevjournal.com/spring-boot/spring-boot-application-intellij/)  
 
Spring Boot — инструмент фреймворка Spring для написания приложений на Spring с минимальной конфигурацией.
Данный инструмент также имеет встроенный контейнер сервлетов (Tomcat по умолчанию), что значительно упрощает запуск приложения. 
Веб приложения на Spring Boot запакованы в jar файл, что позволяет запускать их как обычные java приложения.  

**1. Простота управления зависимостями:**    
Чтобы ускорить процесс управления зависимостями, Spring Boot неявно упаковывает необходимые сторонние зависимости 
для каждого типа приложения на основе Spring и предоставляет их разработчику посредством так называемых starter-пакетов 
(spring-boot-starter-web, spring-boot-starter-data-jpa и т.д.)  
Starter-пакеты представляют собой набор удобных дескрипторов зависимостей, которые можно включить в свое приложение.  

Если вы хотите создать Spring web-приложение, просто добавьте зависимость spring-boot-starter-web, которая подтянет в проект все библиотеки, 
необходимые для разработки Spring MVC-приложений, таких как spring-webmvc, jackson-json, validation-api и Tomcat.  

**2. Автоматическая конфигурация:**  
Второй превосходной возможностью Spring Boot является автоматическая конфигурация приложения.  
После выбора подходящего starter-пакета, Spring Boot попытается автоматически настроить Spring-приложение на основе добавленных вами jar-зависимостей
Например, если вы добавите Spring-boot-starter-web, Spring Boot автоматически сконфигурирует такие зарегистрированные бины, 
как DispatcherServlet, ResourceHandlers, MessageSource. Если вы используете spring-boot-starter-jdbc, 
Spring Boot автоматически регистрирует бины DataSource, EntityManagerFactory, TransactionManager и считывает информацию для подключения 
к базе данных из файла application.properties  

**3. Всегда загружает встроенный Tomcat, чтобы вы могли сразу увидеть результаты работы ваших Rest контроллеров.**
  
**4. Всегда и автоматически ищет пропертис: application.properties в разных местах и читает их.**  

**5. Автоматически настраивайте все для отправки/получения JSON, не беспокоясь о конкретных зависимостях Maven/Gradle.**  
 
Все это запускается основным методом в классе Java, который снабжен аннотацией @SpringBootApplication.  
Spring Boot — это все, что нужно для того, чтобы собрать существующие части Spring Framework, 
предварительно сконфигурировать и упаковать их с минимальными затратами на разработку.    
 
[к оглавлению](#SPRING)
## Spring dependencies and plugins 
  
## Spring Boot Starter Parent  
[подробнее](https://www.baeldung.com/spring-boot-starter-parent)  
```xml
<parent>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-parent</artifactId>
    <version>2.2.6.RELEASE</version>
</parent>
```
Spring-boot-starter-parent предоставляет конфигурации по умолчанию для нашего приложения и полное дерево зависимостей 
для быстрого построения нашего проекта Spring Boot. Кроме того, он наследует управление зависимостями от spring-boot-dependencies, 
которая является родительской для него.    
После того, как мы объявили spring-boot-starter-parent в нашем проекте, мы можем вытащить любую зависимость от spring-boot-starter-parent, 
просто объявив ее в нашем теге <dependencies>, при этом нам также не нужно указывать версию, она подтянется из парента.  

**Также в нем настроен билд со всеми плагинами, при запуске читаются все пропертис.  Т.е. он отвечает за настройку нашего приложения, 
чтобы оно стартавало как спринг.**    

Чтобы изменить версиию зависимости, предоставляемую стартер-парентом, мы можем явно объявить зависимость и ее версию в разделе dependencyManagement:  
```xml
<dependencyManagement>
    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jpa</artifactId>
            <version>2.2.5.RELEASE</version>
        </dependency>
    </dependencies>
</dependencyManagement>
```

**Properties:**    
Чтобы изменить значение любого property, определенного в родительском элементе starter, мы можем повторно объявить его в разделе свойств.
Spring-boot-starter-parent через свои родительские зависимости spring-boot-dependencies использует свойства для настройки всех версий зависимостей, 
версий Java и версий плагинов Maven.  
Если мы хотим изменить версию любой зависимости, которую мы хотим извлечь из стартового родителя, мы можем добавить зависимость 
в тег зависимости и непосредственно настроить ее свойство:
```xml
<properties>
    <junit.version>4.11</junit.version>
</properties>
```

## Spring-boot-starter-web  
[подробнее](https://www.baeldung.com/spring-boot-starters  

Spring Web — зависимость которая включает в себя все настройки Spring MVC и позволяет писать REST API без дополнительных настроек.  
(чтобы не использовать отдельно такие библиотеки, как Spring MVC, Tomcat и Jackson)  
   
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
```   

## Spring Boot Maven Plugin  
The Spring Boot Maven Plugin предоставляет Spring Boot поддержку в Apache Maven. 
Он позволяет упаковать исполняемые jar или war архивы, запустить Spring Boot application, 
сгенерировать build information и запустить ваше Spring Boot application перед запуском интеграционных тестов.  
```xml
 <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>
```

## spring-boot-starter-data-jpa 
Spring Data JPA — позволяет работать с SQL с помощью Java Persistence API, используя Spring Data и Hibernate.  
```xml
<dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>
``` 
[к оглавлению](#SPRING)

## Разное  
+ Thymeleaf — современный серверный шаблонизатор Java для веб-приложений. Можно сказать что Thymeleaf это современная версия jsp.
+ AJAX - технология, чтобы делать запросы на серевер без перезагрузки страницы.  

[к оглавлению](#SPRING)  

## Маппинг  

## Jackson  
Для Jackson маппера, который работает по умолчанию для маппнига в JSON нужно в ентити обязательно указывать **пустой конструктор, 
а также геттеры и сеттеры**, чтобы он мог получить и заполнить поля.   

## ModelMapper  

Библиотека для маппинга  
ModelMapper mapper – этот объект, который можно сразу добавлять, т.к. эта зависимость, есть спринге.
Используется так:  
```java
private ModelMapper mapper;  

private ResultClass> mapper(InputClass inputClassObject) {
        return mapper.map(inputClassObject, ResultClass.class);   
}
```  
Т.е. в маппер мы передаем объект и  класс, который хотим получить в результате.  
  
[к оглавлению](#SPRING)

## JPA  
В отличии от JDBC запросы могут генерироваться автоматически ORM-ом исходя из названия и сигнатуры метода.
Обычно такие автоматически генерируемые SQL-запросы менее эффективные, чем JDBC запросы, 
т.к. там где бы был один JDBC запрос будет сгенерировано несколько SQL-запросов через JPA, и соответственно несколько будет обращений к бд.    
В JPA мы выигрываем в количестве кода и трудоемкости написания кода, но проигрываем в эффективности и быстроте JDBC.  
При этом в spring-boot-starter-data-jpa внутри содержатся зависимости jdbc и hibernate.
(т.е. если мы явно не добавим эти зависмости, они все будут подтянуты из spring-boot-starter-data-jpa)   

Также мы можем написать нативыне SQL-запросы.    
выполняем Query с параметром :productId, который берем из параметра метода @Param("productId") int productId:  
@Query(value = "select max(price) from sales_periods where product = :productId", nativeQuery = true)  
Integer getMaxPriceByProductId(@Param("productId") int productId);

```java
@Repository
public interface SalesPeriodsRepository extends JpaRepository<SalesPeriods, Integer> {
    /**
     * выполняем Query с параметром :productId, который берем из параметра метода @Param("productId") int productId
     */
    @Query(value = "select max(price) from sales_periods where product = :productId", nativeQuery = true)
    Integer getMaxPriceByProductId(@Param("productId") int productId);

    /**
     * запрос генерируется автоматически ORM исхояд из названия и сигнатуры метода
     */
    boolean existsByPrice(long price);
    List<SalesPeriods> findByDateToIsNull();
    List<SalesPeriods> findByProductName(String productName);
    List<SalesPeriods> findByProduct(Product product);
    List<SalesPeriods> findByDateToIsNullAndProductId(Integer productId);
}
```

## JDBC
(см.проект eduexample)
[ссылка на пример](https://github.com/magidin91/theoretical_part/blob/master/spring/src/main/java/ru/education/jdbc/SalesProductJDBCRepository.java) 

Создаем репозиторий, в котором указываем тимплейт, тимплейт берет настройки из application.yml для подключения к бд,  и и знего мы пишем запросы:  
```java
@Repository
public class SalesProductJDBCRepository {
    private final JdbcTemplate jdbcTemplate;

    public SalesProductJDBCRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int count() {
        return jdbcTemplate.queryForObject("select count(*) from sales_periods", Integer.class);
        //Выполняет запрос и преобразует результат к определенному классу
    }

    public List<SalesPeriodsJDBCDemo> getSalesPeriods() {
        return jdbcTemplate.query("select* from sales_periods", (rs, rowNum) -> new SalesPeriodsJDBCDemo( //rowMapper
                rs.getInt("id"), // чтобы преобразовать записи из бд (ResultSet) к нашему классу
                rs.getLong("price"),
                rs.getDate("date_from"),
                rs.getDate("date_to"),
                rs.getInt("product")
        ));
    }

    public List<Product> getProductsWithActivePeriods() {
        return jdbcTemplate.query(
                "Select p.id, p.name from product as p Inner join sales_periods as sp " +
                        "On p.id = sp.product where sp.date_to is null",
                (rs, rowNum) -> new Product(
                        rs.getInt("id"),
                        rs.getString("name")
                ));
    }
}

``` 
 
[к оглавлению](#SPRING)  

## Тестирование  
[пример](https://github.com/magidin91/spring_basic/tree/master/src/test)  
+ см. ворд. файл Java\===Spring===\Intellecta\eduexample    
1) Мы использовали H2 SQL in memory БД, которая создается при запуске тестов и удаляется после.
Добавили зависиомости для тестов:  
```xml
		<!-- test -->
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
		</dependency>
		<dependency>
			<groupId>com.h2database</groupId>
			<artifactId>h2</artifactId>
			<version>1.4.200</version>
			<scope>test</scope>
		</dependency>
		<!-- test -->
```  
2) Добавили в ресурсы тестов конфигурацию application.yml, который будет использоваться для модульных тестов.  
```yaml 
spring:
  datasource:
    driver-class-name: org.h2.Driver
    url: jdbc:h2:mem:test
    username: tester
    password:
    sql-script-encoding: UTF-8
  jpa:
    show sql: true
    properties:
      hibernate:
        format_sql: true
        dialect: org.hibernate.dialect.H2Dialect
        hbm2ddl:
          auto: none
      javax:
        persistence:
          validation:
            mode: none
```
3) Добавили в ресурсы тестов скрипты для создания и таблиц и наполнения их данными (schema.sql, data.sql), нужно использовать именно эти названия.    
4) Создаели класс конфигурации
```java
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Конфигурация для тестов
 */
@EnableAutoConfiguration
@EnableJpaRepositories(basePackages = {"ru.education.jpa"}) // указываем, где репозитории Jpa
@ComponentScan (basePackages = {"ru.education.service.impl"}) // указываем спрингу, где искать компоненты - сервисы
@EntityScan(basePackages = {"ru.education.entity"}) // указываем, где искать сущности Jpa
public class TestConfig {
}
```  
## Transactional  
Чтобы каждый тест откатывался используем эту аннотацию, ее можно использовать в двух конфигурациях.  
1.  
@RunWith(SpringRunner.class)  
@SpringBootTest  
**@Transactional**  
@ContextConfiguration(classes = {TestConfig.class})  

Здесь в конфиге TestConfig.class прописана аннтация @EnableAutoConfiguration, поэтому доступен дефолтный PlatformTransactionManager.
@EnableAutoConfiguration  
@EnableJpaRepositories(basePackages = {"com.education.jpa"})  
@ComponentScan(basePackages = {"com.education.service"})  
@EntityScan(basePackages = {"com.education.entity"})  
public class TestConfig {}  

2.	Также можно не использовать @ContextConfiguration, тогда в самом тесте нужно указать @EnableAutoConfiguration, чтобы получить PlatformTransactionManager.  
@RunWith(SpringRunner.class)  
@SpringBootTest(classes = {TypeController.class, MockTypeService.class})  
@Transactional  
@EnableAutoConfiguration  

## ExceptionController  
Нужно написать ExceptionController, чтобы при получении ошибки, отлавливать их, возвращать объект ErrorResponseEntity 
с информацией об ошибке, и возвращать конретный статус.  
ExceptionController - контроллер, который крутится в контексте спринга, но когда бросается Exception, контроллер перехватывает его  
соответствующим этому исключению обработчиком.    
ErrorResponseEntity - сущность, которая возвращается контроллером при какой-то ошибке.  

## Модульнео тестирование контроллеров  
(см. ProductControllerTest)    
Создали мок для сервиса, т.к. в модульных тестах мы должны тестировать конкретную функциональность, в нашем случае контроллеры.  
Мок возвращает просто некоторые тестовые данные. 
Также создаем MockMvc mockMvc, чтобы вызывать нужные http - запросы в ручную.  
В тестах контроллеров обычно проверяется просто статус, возвращаемый методом.      

[к оглавлению](#SPRING)  

## Spring Security  
(см. проект spring_basic)  
https://gist.github.com/zmts/802dc9c3510d79fd40f9dc38a12bccfc      
+ Аутентификация(authentication, от греч. αὐθεντικός  – реальный, подлинный) - это процесс проверки учётных данных пользователя (логин/пароль). 
Проверка подлинности пользователя путём сравнения введённого им логина/пароля с данными сохранёнными в базе данных.  
+ Авторизация(authorization — разрешение, уполномочивание) - это проверка прав пользователя на доступ к определенным ресурсам. 
 
JSON Web Token (JWT) — содержит три блока, разделенных точками: заголовок(header), набор полей (payload) и сигнатуру.  
[подробнее](https://proglib.io/p/json-tokens/)  
  
Первые два блока представлены в JSON-формате и дополнительно закодированы в формат base64. Набор полей содержит произвольные пары имя/значения, 
притом стандарт JWT определяет несколько зарезервированных имен (iss, aud, exp и другие). 
Сигнатура может генерироваться при помощи и симметричных алгоритмов шифрования, и асимметричных. Кроме того, существует отдельный стандарт, 
отписывающий формат зашифрованного JWT-токена.  
Токены предоставляют собой средство авторизации для каждого запроса от клиента к серверу. 
Токены(и соответственно сигнатура токена) генерируются на сервере основываясь на секретном ключе(который хранится на сервере) и payload'e. 
Токен в итоге хранится на клиенте и используется при необходимости авторизации какого-либо запроса.

  
Ключевые объекты контекста Spring Security:

+ SecurityContextHolder, в нем содержится информация о текущем контексте безопасности приложения,
который включает в себя подробную информацию о пользователе(Principal) работающем в настоящее время с приложением. 
По умолчанию SecurityContextHolder используетThreadLocal для хранения такой информации, что означает, 
что контекст безопасности всегда доступен для методов исполняющихся в том же самом потоке. 
Для того что бы изменить стратегию хранения этой информации можно воспользоваться статическим методом класса 
SecurityContextHolder.setStrategyName(String strategy). Более подробно SecurityContextHolder  
+ SecurityContext, содержит объект Authentication и в случае необходимости информацию системы безопасности, связанную с запросом от пользователя.
+ Authentication представляет пользователя (Principal) с точки зрения Spring Security.
Represents the token for an authentication request or for an authenticated principal once the request has been processed by the
AuthenticationManager#authenticate(Authentication) method.  
+ GrantedAuthority отражает разрешения выданные пользователю в масштабе всего приложения, такие разрешения (как правило называются «роли»), 
например ROLE_ANONYMOUS, ROLE_USER, ROLE_ADMIN.  
+ UserDetails предоставляет необходимую информацию для построения объекта Authentication из DAO объектов приложения 
или других источников данных системы безопасности. Объект UserDetailsсодержит имя пользователя, пароль, флаги: 
isAccountNonExpired, isAccountNonLocked, isCredentialsNonExpired, isEnabled и Collection — прав (ролей) пользователя.  
+ UserDetailsService, используется чтобы создать UserDetails объект путем реализации единственного метода этого интерфейса  

UserDetails loadUserByUsername(String username) throws UsernameNotFoundException; 
Позволяет получить из источника данных объект пользователя и сформировать из него объект UserDetails который будет использоваться контекстом Spring Security.  

Добавить зависимости  
```xml
	<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-security</artifactId>
		</dependency>
		<dependency>
			<groupId>io.jsonwebtoken</groupId>
			<artifactId>jjwt</artifactId>
			<version>0.9.1</version>
		</dependency>

```
На методы контроллера мы накидываем, какая привелегия должна быть у пользователя, чтобы получить доступ к ресурсу. 
```java
 @GetMapping
    @PreAuthorize("hasPermission('product', 'read')")
    List<Product> findAll() {
        return productService.findAll();
    }
```  
По дефолту в спринг секьюрити есть метод http://localhost:8080/login с боди, который не нужно явно указывать в контроллере 
 {
        "username" : "user",
        "password" : "123"  
 }
Нам методах, доступ к которым должен проверять спринг секьюрити, нужно накинуть аннотоацию:  
@PreAuthorize("hasPermission('product', 'read')")  

1. При логине мы вызываем фильтр JwtLoginFilter  
2. При повторном запросе вызываем фильтр JwtAuthenticationFilter
````java
.addFilterBefore(new JwtLoginFilter("/login", authenticationManager()),
                        UsernamePasswordAuthenticationFilter.class)
                /* при любом запросе будет вызываться фильтр для проверки прав юзера */
                .addFilterBefore(new JwtAuthenticationFilter(),
                        UsernamePasswordAuthenticationFilter.class);
````
Подробнее:

1. **При логине - фильтр JwtLoginFilter:** 
Делаем первичный запрос аутентификации (login)-> попадаем в метод attemptAuthentication класса JwtLoginFilter, в нем из запроса 
получаем данные - пользователя (логин и пароль) -> далее вызывается метод loadUserByUsername класса SecurityUserDetailsManager, 
который достает по логиу соответствующего пользователя по логину, далее пришедший и вычитанный пользователи сравниваются, и если 
они равны вызывается метод successfulAuthentication класса JwtLoginFilter, в котором вызывается addAuthentication - он генерирует JWT токен, 
а  successfulAuthentication добавляет этот JWT токен (строку) в заголовок риспонса для пользователя->  этот сохраняется на фронте, и
при следующем запросе уже не нужно логиниться, а проверется токен, который отправляется вместе с реквестом.  
2. **При повторном запросе - фильтр JwtAuthenticationFilter:**    
При запросе, до вызова метода контроллера вызывается метод doFilter из JwtAuthenticationFilter - он проверяет, 
есть ли у юзера необходимое разрешение.  В нем происходят следующие шаги:   
1. Вызывыается Authentication getAuthentication(HttpServletRequest request) из TokenAuthenticationService, в нем мы 
получаем токен getToken, затем getUsername - парсим этот токен (дешифруем) и получаем username, далее по этому username 
получаем пользователя с его разрешениями и создаем для спринга токен Authentication.  
2. Устанавливаем эту аутентификацию в SecurityContextHolder: SecurityContextHolder.getContext().setAuthentication(authentication); 
3. Далее взывается filterChain.doFilter(request, response); в котором вызывается 
boolean hasPermission(Authentication authentication, Object resource, Object action) из класса DefaultPermissionEvaluator,  
этот метод из запроса получаем необходимое разрешение (Object resource, Object action) и далее из Authentication объекта 
мы получили юзера, и проверяем есть ли в его списке привелегий нужное разрешение. Если разрешения нет, то мы получим 403 ошибку - 
access denied (аутентифицирован, но не имеет разрешения)

Чтобы вспомнить обязательн опройтись дебаггом.  
**Чтобы через постман получить токен, нужно взять его из хедера ответа, key - Authorization, а чтобы при следующем запросе испольовать его -
вставить его, в headers запроса, key = Authorization, value = токен.**   

[к оглавлению](#SPRING)    

##  Аспектно-ориентированное програмирвоание (AOP)  

АОП предоставляет возможность реализации сквозной логики — т.е. логики, которая применяется к множеству частей приложения — 
в одном месте и обеспечения автоматического применения этой логики по всему приложению.  
Подход Spring к АОП заключается в создании “динамических прокси” для целевых объектов и “привязывании” объектов к конфигурированному 
совету для выполнения сквозной логики.  
  
Нужный функционал (join points) мы объединяем в pointcut, объявляем советы, которые будем использовать для данного pointcut'а, 
и средствами АОП (создается так называемый перехватчик или Proxy, который берет управление на себя) "внедряем" в код приложения. 
Такую функциональность еще называют сквозной или cross-cutting. 
Если вы подумали, что с помощью АОП, мы можем выполнять дополнительные действия над существующим кодом, при этом не изменяя его, то вы правы! 
Способов применению этому много: логирование, безопасность, управление транзакциями и т.д., 
когда существующий класс или метод должен знать только то, что он выполняет. В нашем случае мы используем логирование. 
Согласитесь, что нерезонно его "пихать" прямо внутрь метода. Методу неважно, что и как мы логируем, ему главное выполнить свою задачу. 
Используя АОП, мы разбиваем код на несколько подмодулей, тем самым делая его более читаемым и поддерживаемым.   
  
+ Аспект (aspect) – модуль или класс, реализующий сквозную функциональность. Аспект изменяет поведение остального кода, 
применяя совет в точках соединения, определённых некоторым срезом. Также аспект может использоваться для внедрения функциональности;  
+ Совет (advice) – дополнительная логика — код, который должен быть вызван из точки соединения. 
Совет может быть выполнен до, после или вместо точки соединения;  
+ Точка соединения (join point) — точка в выполняемой программе (вызов метода, создание объекта, обращение к переменной), где следует применить совет;
+ Срез (pointcut) — набор точек соединения. Срез определяет, подходит ли данная точка соединения к заданному совету;
+ Внедрение (introduction) — изменение структуры класса и/или изменение иерархии наследования для добавления функциональности аспекта в инородный код;
+ Цель (target) – объект, к которому будут применяться советы;
+ Переплетение (weaving) – связывание объектов с соответствующими аспектами (возможно на этапе компиляции, загрузки или выполнения программы)  
  
Посмотреть внимательнее примеры точек соединения.  
Примеры точек соединения:  
execution(static * com.xyz..*.*(..)) – выполнение кода любого статического метода в пакете com.xyz;  

+ call(void MyInterface.*(..)) – вызов любого метода, возвращающего void, интерфейса MyInterface;  
+ initialization(MyClass || MyOtherClass) – инициализация класса MyClass или MyOtherClass;  
+ staticinitialization(MyClass+ && !MyClass) – статическая инициализация класса, имя которого начинается на MyClass, но не сам MyClass;  
+ handler(ArrayOutOfBoundsException) – выполнение обработчика исключения ArrayOutOfBoundsException;  
+ get/set(static int MyClass.x) — чтение / запись свойства x класса MyClass;  
+ this/target(MyClass) – выполнение точки соединения, соответствующей объекту типа MyClass;  
+ args(Integer) – выполнение точки соединения, в которой доступен аргумент типа Integer;  
+ if(thisJoinPoint.getKind().equals(«call»)) – совпадает со всеми точками соединения, в которых заданное выражение истинно;  
+ within/withincode(MyClass) — совпадает со всеми точками соединения, встречающимися в коде заданного класса;  
+ cflow/cflowbelow(call(void MyClass.test())) – совпадает со всеми точками соединения, встречающимися в потоке выполнения заданного среза;  
+ @annotation(MyAnnotation) – выполнение точки соединения, цель которой помечена аннотацией @MyAnnotation.    

Что же касается советов, то их количество намного меньше, но они полностью покрывают всё необходимое множество ситуаций:

+ before – запуск совета до выполнения точки соединения,
+ after returning — запуск совета после нормального выполнения точки соединения,
+ after throwing — запуск совета после выброса исключения в процессе выполнения точки соединения,
+ after — запуск совета после любого варианта выполнения точки соединения,
+ around – запуск совета вместо выполнения точки соединения (выполнение точки соединения может быть вызвано внутри совета).  

```java
@Aspect
@Component
public class WebServiceLogger {
    private static final Logger LOG = LoggerFactory.getLogger(WebServiceLogger.class);

    /**
     * Пойнткат (точка соединения)
     * В данном случае - это методы, помеченные аннотацией @Loggable
     */
    @Pointcut("@annotation(com.education.annotation.Loggable)")
    public void loggableMethod() {
    }

    /**
     * Пойнткат для всех публичных методов из папки service
     * .*(..) - обозначает - "все методы"
     */
    @Pointcut(value = "execution(public * com.education.service.*.*(..))")
    public void serviceMethod() {
    }

    /**
     * Пойнткат "serviceMethod()" - любой публичный метод из ProductService
     */
    @Pointcut(value = "execution(public * com.education.service.ProductService.*(..))")
    public void productServiceMethod() {
    }

//    /* Вызывается вместо методов, которые соответствуют serviceMethod() и также loggableMethod() */
//    @Around(value = "productServiceMethod() && loggableMethod()")
//    public Object logWebServiceLog(ProceedingJoinPoint thisJoinPoint) throws Throwable {
//        String methodName = thisJoinPoint.getSignature().getName();
//        /* получили методы аргумента */
//        Object[] methodArgs = thisJoinPoint.getArgs();
//        LOG.info("Call method " + methodName + " with args " + Arrays.toString(methodArgs));
//        /* выполняем сам метод */
//        Object result = thisJoinPoint.proceed();
//        LOG.info("Method " + methodName + " returns " + result);
//        return result;
//    }

    /**
     * Совет, выполняеый до точки соединения
     */
    @Before(value = "serviceMethod()")
    public void beforeWebServiceLog() {
        LOG.info("Method starts working");
    }

    /**
     * Совет, выполняеый после точки соединения
     */
    @After(value = "serviceMethod()")
    public void afterWebServiceLog() {
        LOG.info("Method has finished");
    }
}
```      
1. Создаем аспект - просто класс, который определяют дополнительную логику, советы и точки соединения.  
[Пример:](https://github.com/magidin91/spring_basic/blob/master/src/main/java/com/education/aspects/WebServiceLogger.java)   

+ Создаем совет, т.е. метод, который будет вызывататся в точке соединения. (у него мы обозначаем @Pointcut, для которых он будет вызываться)  
слева от метода можно кликнуть по иконке M и посмотреть для каких методов будет работать точка соединения.    
+ Создаем точки соединения
1. Точками соединения могут быть обозначенные методы, например:  
@Pointcut(value = "execution(public * com.education.service.*.*(..))") // для всех публичных методов из папки service 
public void serviceMethod() {}  
@Pointcut(value = "execution(public * com.education.service.ProductService.*(..))") // для всех публичных методов из  класса ProductService  
  
2. Все методы, помеченные аннотацией @Loggable
@Pointcut("@annotation(com.education.annotation.Loggable)")  
public void loggableMethod() {}  
для этого можно создать свою аннотацию:  
public @interface Loggable {}  

Вопросы с javastudy:  

## Как получить объекты ServletContext и ServletConfig внутри Spring Bean?  
http://java-online.ru/servlet-context.xhtml    
Доступны два способа для получения основных объектов контейнера внутри бина:  

Реализовать один из Spring*Aware (ApplicationContextAware, ServletContextAware, ServletConfigAware и др.) интерфейсов.  
Использовать автоматическое связывание @Autowired в спринг. Способ работает внутри контейнера спринг.  
@Autowired  
ServletContext servletContext;  

Конфигурация сервлета ServletConfig  
Интерфейс javax.servlet.ServletConfig используется для передачи конфигурационной информации сервлету. 
Каждый сервлет имеет свой собственный объект ServletConfig, за создание экземпляра которого отвечает контейнер сервлетов. 
Для установки параметров конфигурации сервлета необходимо использовать теги <init-param>, <param-name>, <param-value> в дескрипторе приложения web.xml.  

init - метод инициализации сервлета.
```java
public void init(ServletConfig config) 
{ 
    System.out.println ("Версия сервлета : " + config.getInitParameter("version")); 
} 
```
Интерфейс ServletConfig включает следующие методы, имена которых говорят сами за себя и составляют суть интерфейса javax.servlet.ServletConfig:

+ public String getServletName()
+ public ServletContext getServletContext()
+ public String getInitParameter(String name)
+ public java.util.Enumeration getInitParameterNames()  
Особый интерес представляет метод getServletContext(), возвращающий ссылку на контекст сервлета.  

Контекст сервлета ServletContext  
Информация о контексте сервера доступна через объект ServletContext. 
Сервлет может получить этот объект, вызывая метод getServletContext() объекта ServletConfig.
Необходимо помнить, что этот объект передается сервлету во время инициализации в методе init().  

Эти функции открывают доступ к таким параметрам, как имя хоста, порт и прочие полезности.  
Интерфейс ServletContext определяет несколько методов, представленные в таблице:  

getAttribute ()	Гибкий способ получения информации о сервере через пары атрибутов имя/значение. Зависит от сервера.  
GetMimeType ()	Возвращает тип MIME данного файла.  
getRealPath ()	Этот метод преобразует относительный или виртуальный путь в новый путь относительно месторасположения корня HTML-документов сервера.  
getServerInfo ()	Возвращает имя и версию сетевой службы, в которой исполняется сервлет.  
getServlet ()	Возвращает объект Servlet указанного имени. Полезен при доступе к службам других сервлетов.  
getServletNames ()	Возвращает список имен сервлетов, доступных в текущем пространстве имен.  
log ()	Записывает информацию в файл регистрации сервлета. Имя файла регистрации и его формат зависят от сервера.  

## Каковы различные типы автоматического связывания в Spring?  
https://proselyte.net/tutorials/spring-tutorial-full-version/autowiring/
В Spring Framework предусмотрены 4 различных режима автоматического разрешения зависимостей (4 значения xml атрибута autowire):

+ byName   Spring пытается для каждой переменной управляемого компонента найти в окружении бин с аналогичным именем.
+ byType   Spring пытается для каждой переменной управляемого компонента найти в окружении бин аналогичного типа. 
Например, если управляемый компонент имеет поле типа Person, то Spring Framework проверяет, 
существует ли сконфигурированный компонент этого типа и если существует, то связывает его со этим полем.
Если существует несколько управляемых компонент искомого типа, то окружение Spring не может сделать выбор самостоятельно и генерирует исключительную ситуацию.
+ constructor   этот режим автоматического разрешения зависимостей работает аналогично режиму byName.
 Разница заключается в том, что вместо использования методов присваивания (setter method), 
в данном случае используются соответствующие конструкторы управляемых компонент.
+ autodect  в этом случае окружение Spring самостоятельно выбирает режим автоматического связывания.
Если у управляемого компонента существует не использующий параметров конструктор (default constructor), 
то будет выбран режим byName, если же такой конструктор существует, то будет использован режим constructor.

## Расскажите, что вы знаете о DispatcherServlet и ContextLoaderListener  
DispatcherServlet – сервлет диспетчера. Этот сервлет анализирует запросы и направляет их соответствующему контроллеру для обработки.
В приложении Spring MVC может существовать произвольное количество экземпляров DispatcherServlet, предназначенных для разных целей.  
Каждый экземпляр DispatcherServlet имеет собственную конфигурацию WebApplicationContext, которая определяет характеристики уровня сервлета, 
такие как контроллеры, поддерживающие сервлет, отображение обработчиков, распознавание представлений, интернационализация, оформление темами, 
проверка достоверности, преобразование типов и форматирование и т.п  

**ContextLoaderListener**  
ContextLoaderListener – слушатель при старте и завершении корневого класса Spring WebApplicationContext. 
Основным назначением является связывание жизненного цикла ApplicationContext и ServletContext, а так же автоматического создания ApplicationContext. 
Можно использовать этот класс для доступа к бинам из различных контекстов спринг. Настраивается в web.xml.  

## Что такое ViewResolver в Spring?  
https://javastudy.ru/spring-mvc/spring-mvc-viewresolver/  
ViewResolver – распознаватель представлений.   Интерфейс ViewResolver в Spring MVC (из пакета org.springframework.web.servlet)
поддерживает распознавание представлений на основе логического имени, возвращаемого контроллером.  

## Что такое MultipartResolver и когда его использовать? 
Загрузка файла:
https://zen.yandex.ru/media/id/5ac20956168a91ffeae449c5/spring-boot-mvc-zagruzka-failov-na-server-i-razdacha-statiki-5b03d8dd7ddde8be6c50bfb9   
https://javastudy.ru/spring-mvc/spring-mvc-load-files/    
Интерфейс MultipartResolver используется для загрузки файлов.  

## Как обрабатывать исключения в Spring MVC Framework?  
В Spring MVC интерфейс HandlerExceptionResolver (из пакета org.springframework.web.servlet) предназначен для работы с непредвиденными исключениями, 
возникающими во время выполнения обработчиков. По умолчанию DispatcherServlet регистрирует класс DefaultHandlerExceptionResolver
(из пакета org.springframework.web.servlet.mvc.support). Этот распознаватель обрабатывает определенные стандартные исключения Spring MVC, 
устанавливая специальный код состояния ответа.  

## Как проверить (валидировать) данные формы в Spring Web MVC Framework?  
Spring поддерживает аннотации валидации из JSR-303, а так же возможность создания своих реализаций классов валидаторов. Пример использования аннотаций:  
```java
@Size(min=2, max=30) 
    private String name;
      
    @NotEmpty @Email
    private String email;
      
    @NotNull @Min(18) @Max(100)
    private Integer age;
      
    @NotNull
    private Gender gender;
      
    @DateTimeFormat(pattern="MM/dd/yyyy")
    @NotNull @Past
    private Date birthday;
```  
 
## Что вы знаете о Spring MVC Interceptor и как он используется?
https://javastudy.ru/spring-mvc/interceptors/  
https://o7planning.org/ru/11229/spring-mvc-interceptors-tutorial  

MVC Interceptor позволяет вам выполнять задачи, которые являются общими для каждого запроса или набора запросов, 
без необходимости вырезать и вставлять код в каждый класс контроллера.  

Ваш класс  Interceptor должен выполнить интерфейс  org.springframework.web.servlet.HandlerInterceptor или расшириться 
из класс  org.springframework.web.servlet.handler.HandlerInterceptorAdapter. 
Нужно определить три метода: preHandle(), postHandle(), afterCompletion(), 
которые вызываются перед обработкой запроса актуальным классом, после этой обработки, а так же после окончания запроса соответственно.

Каждый запрос может пройти через многие  Interceptor.  

## Почему иногда мы используем @ResponseBody, а иногда ResponseEntity?  
ResponseEntity необходим, только если мы хотим кастомизировать ответ, добавив к нему статус ответа. Во всех остальных случаях будем использовать @ResponseBody.  
Для @ResponseBody единственные состояния статуса это SUCCESS(200), если всё ок и SERVER ERROR(500), если произошла какая-либо ошибка.  
Допустим мы что-то создали и хотим отправить статус CREATED(201). В этом случае мы используем ResponseEntity.  


## В чем разница между ModelMap и ModelAndView?
Model — интерфейс, ModelMap его реализация..
ModelAndView является контейнером для пары, как ModelMap и View.  

Обычно я люблю использовать ModelAndView. Однако есть так же способ когда мы задаем необходимые атрибуты в ModelMap, 
и возвращаем название View обычной строкой из метода контроллера.  


## @SpringBootApplication  
```java
@SpringBootApplication
public class ServingWebContentApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServingWebContentApplication.class, args);
    }
}
```

@SpringBootApplication это удобная аннотация, которая добавляет все следующее:

+ @Configuration: Помечает класс как источник бинов для application context. 
+ @EnableAutoConfiguration: 
Говорит Spring Boot начать добавление компонентов на основе параметров пути к классу,
других компонентов и различных параметров свойств. Например, если spring-webmvc находится в пути к классам,
эта аннотация помечает приложение как веб-приложение и активирует ключевые действия, такие как настройка DispatcherServlet.  
+ @ComponentScan: Указывает Spring искать components, configurations, and services в пакете, где лежит файл запуска с @SpringBootApplication.  
В спрингбуте не нужны никакие xml конфигурации.  

## Статические страницы  
По умолчанию в Spring Boot нужно помещеать статические страницы (включая HTML, JavaScript и CSS) в ресурсах в папке /static (или /public).  
Если в этой папке (src/main/resources/static/index.html) создать страницу index.html, 
то она по умолчанию будет открываться по основному маппингу http://localhost:8080.  
  
## Spring practice

Через шаблонизатор thymeleaf:  

```java
@Controller
public class GreetingController {

	@GetMapping("/greeting")
	public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
		model.addAttribute("name", name);
		return "greeting";
	}

}
```  
 
```html
<!DOCTYPE HTML>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <title>Getting Started: Serving Web Content</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
</head>
<body>
<p th:text="'Hello, ' + ${name} + '!'" />
</body>
</html>
```

@RequestParam(name="name", required=false, defaultValue="World") 
+ String name - это параметр, который мы получаем из запроса.
Или как агрументурл в гет запросе. Например, http://localhost:8080/greeting?name=misha.  
Или из тела пост запроса. (например, из html- формы)  
  
+ required=false - аргумент необязателен
+ defaultValue="World" - значение по умолчанию  

Model model - объект через, который мы передаем атрибуты в представление:  
model.addAttribute("name", name); - в представлении при вызове "name" мы получим значение String name.  

Далее возвращаем наше html-представление.  

**Через шаблонизатор Mustache:**  
```java
@Controller
public class GreetingController {
    @GetMapping("/greeting")
    public String greeting(
            @RequestParam(name="name", required=false, defaultValue="World") String name,
            Map<String, Object> model
    ) {
        model.put("name", name);
        return "greeting";
    }
}
```   
```html
<!DOCTYPE HTML>
<html>
<head>
    <title>Getting Started: Serving Web Content</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
</head>
<body>
<div>Hello, {{name}}</div>
</body>
</html>
```  
Вместо Model model используем Map<String, Object> model, чтобы передавать параметры в представление.  
    
 

      




   


