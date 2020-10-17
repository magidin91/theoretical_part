package ru.education.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity //обозначаем, что это сущность
@Table(name = "product") //указываем таблицу, к которой сущность привязана
@Getter //аннотации ломбок вместо геттеров, сеттеров и констр-ра без аргументов
@Setter
public class Product {
    public static String Type_Name = "Продукт";

    @Id //указываем, что поле является идентификатором
    @Column(name = "id", nullable = false) //указываем колонку, к которой привязано поле, + первич. ключ не мб пустым
    private Integer id;

    @Column(name = "name")
    private String name;


    public Product() {
    }

    public Product(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

/*    public Product() { //пустой констр-р нужен для преобр-ия к json (jackson mapper)
      Тут были еще геттеры и сеттеры, но мы заменили их аннотациями ломбок:  @Getter, @Setter, @NoArgsConstructor
*/
}
