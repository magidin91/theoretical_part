

## Что такое ORM  
JDBC - чистые запросы на SQL  
JPA - ООП подход  
   
Разработчики каждый раз сталкивались с необходимостью писать однотипный и ненужный "обслуживающий" код (так называемый Boilerplate code)
для тривиальных операций по сохранению Java объектов в БД и наоборот, созданию Java объектов по данным из БД. 
И тогда для решения этих проблем на свет появилось такое понятие, как ORM.

ORM — Object-Relational Mapping (объектно-реляционное отображение)
Это концепция, которая связывает базы данных с парадигмой объектно-ориентированных языков программирования.
Если упростить, то ORM это связь Java объектов и записей в БД. ORM освобождает программиста от работы с SQL-скриптами и позволяет сосредоточиться на ООП.  
Т.е. используя ORM - фреймворки, реализующие JPA спецификацию, мы оперируем в рамках ООП парадигмы и манипулируем объектами, 
а не чистыми SQL запросами (как в JDBC). (взаимодействие с БД и SQL запросы работают уже "под капотом" JPA)     

**ORM — это по сути концепция о том, что Java объект можно представить как данные в БД (и наоборот).**
Она нашла воплощение в виде спецификации JPA — Java Persistence API.   

JPA - Java Persistence API, javax.persistence
JPA (Java Persistence API) — программный интерфейс API, входящий с версии Java 5 в состав платформ Java SE и Java EE. 
Одной из самых заметных реализаций JPA является Hibernate.  

JPA оперирует таким понятием, как сущность Entity, которая является POJO-классом и связана с БД с помощью аннотации @Entity
или через файл конфигурации XML. К такому классу предъявляются следующие требования :  
+ наличие пустого public или protected конструктора;  
+ не должен быть вложенным, являться интерфейсом или enum;  
+ не должен быть final и не должен содержать final-свойств;  
+ должен включать хотя бы одно @Id-поле.  
    
[подробнее](https://javarush.ru/groups/posts/2259-jpa--znakomstvo-s-tekhnologiey) 
[подробнее](http://java-online.ru/hibernate-jpa.xhtml)

## N + 1 проблема в Hibernate
[подробнее](https://youtu.be/StrgaxrAyyU?t=1855)  
```java
@Data
@NoArgsConstructor
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    private String text;
    @ManyToOne//(fetch = FetchType.LAZY)
    private Topic topic;
    public Comment(String text){
        this.text=text;
    }
}

@Data
@NoArgsConstructor
@Entity
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    private String title;
    public Topic(String title){
        this.title=title;
    }
}
```
Если у нас есть сложный объект, у которого есть поле, которое содержит объект другой таблицы, то на родительский объект потребуется
один запрос плюс по запросу на на каждый дочерний объект.

Т.е. в нашем примере создастся один селект, чтобы получить все комментарии и затем для кажого комментария по одному запросу, 
чтобы получить топик.  
```java
Hibernate: select comment0_.id as id1_0_, comment0_.text as text2_0_, comment0_.topic_id as topic_id3_0_ from Comment comment0_
Hibernate: select topic0_.id as id1_1_0_, topic0_.title as title2_1_0_ from Topic topic0_ where topic0_.id=?
Hibernate: select topic0_.id as id1_1_0_, topic0_.title as title2_1_0_ from Topic topic0_ where topic0_.id=?
Hibernate: select topic0_.id as id1_1_0_, topic0_.title as title2_1_0_ from Topic topic0_ where topic0_.id=?
Hibernate: select topic0_.id as id1_1_0_, topic0_.title as title2_1_0_ from Topic topic0_ where topic0_.id=?
Hibernate: select topic0_.id as id1_1_0_, topic0_.title as title2_1_0_ from Topic topic0_ where topic0_.id=?
comment0 topic0
comment1 topic1
comment2 topic2
comment3 topic3
comment4 topic4
```
В Jdbc мы бы сделали один Join-запрос.