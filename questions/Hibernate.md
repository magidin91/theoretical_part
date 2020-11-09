
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