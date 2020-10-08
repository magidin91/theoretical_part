package collections.generics;

import java.lang.reflect.ParameterizedType;

public class Child extends Parent<String> {
    public static void main(String[] args) {
        Parent<String> genericObject = new Child();
        //получаем класс наследнка
        Class actualClass = genericObject.getClass();// получаем класс class collections.generics.Child
        System.out.println(actualClass);
        //получаем парметризованный класс родителя
        ParameterizedType type = (ParameterizedType)actualClass.getGenericSuperclass();
        System.out.println(type); // collections.generics.Parent<java.lang.String>
        // получаем тип дженерика родителя
        Class parameter = (Class)type.getActualTypeArguments()[0];
        System.out.println(parameter); // class java.lang.String

        //тоже самое в одну строку:
        Class parameter2 =  (Class)(((ParameterizedType) genericObject.getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
        System.out.println(parameter2);
    }
}