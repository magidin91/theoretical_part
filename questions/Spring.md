## SPRING
https://javarush.ru/groups/posts/2259-jpa--znakomstvo-s-tekhnologiey jpa
https://topjava.ru/blog/spring-framework-vs-spring-boot-differences  

## Spring общее  
Spring FrameWork содержит основные аннотации, используемые в спирнг. (Dependency injection)  
Spring Boot создает и запускает спринг приложение.  
Spring Data – для подключения к бд  
Spring Integration – обмен сообщениями между приложениями.  
Spring Batch – упрощает обмен большими пакетами инф-ии.  
Spring Social – подключение api известных приложений (facebook, twitter)  
Spring Mobile – для разработки мобильных приложений.  

[к оглавлению](#SPRING)

## Dependency inversion
+ Bean - управляемый контейнером компонент (managed component), т.е. бин создается и инициализируется контейнером и 
существует внутри него.  
Анотации:
@Component("name) 
@Autowired, 
@Primary - этот бин будет инджектиться, если мы не указали @Qualifier
@Qualifier ("name) - указывает на конкретный бин. (ичпользуется вместе с @Autowired), у бина в @Component("name) должно быть обозначено имя.  

[к оглавлению](#SPRING)
## Spring Boot  
[подробнее](https://habr.com/ru/post/435144/)   
[подробнее](https://www.javadevjournal.com/spring-boot/spring-boot-application-intellij/)  
 
Spring Boot — инструмент фреймворка Spring для написания приложений на Spring с минимальной конфигурацией.
Данный инструмент также имеет встроенный контейнер сервлетов (Tomcat по умолчанию) что значительно упрощает запуск приложения. 
Веб приложения на Spring Boot запакованы в jar файл, что позволяет запускать их как обычные java приложения.  

**1) Простота управления зависимостями:**    
Чтобы ускорить процесс управления зависимостями, Spring Boot неявно упаковывает необходимые сторонние зависимости 
для каждого типа приложения на основе Spring и предоставляет их разработчику посредством так называемых starter-пакетов 
(spring-boot-starter-web, spring-boot-starter-data-jpa и т.д.)  
Starter-пакеты представляют собой набор удобных дескрипторов зависимостей, которые можно включить в свое приложение.  

Если вы хотите создать Spring web-приложение, просто добавьте зависимость spring-boot-starter-web, которая подтянет в проект все библиотеки, 
необходимые для разработки Spring MVC-приложений, таких как spring-webmvc, jackson-json, validation-api и Tomcat.  

**2) Автоматическая конфигурация:**  
Второй превосходной возможностью Spring Boot является автоматическая конфигурация приложения.  
После выбора подходящего starter-пакета, Spring Boot попытается автоматически настроить Spring-приложение на основе добавленных вами jar-зависимостей
Например, если вы добавите Spring-boot-starter-web, Spring Boot автоматически сконфигурирует такие зарегистрированные бины, 
как DispatcherServlet, ResourceHandlers, MessageSource. Если вы используете spring-boot-starter-jdbc, 
Spring Boot автоматически регистрирует бины DataSource, EntityManagerFactory, TransactionManager и считывает информацию для подключения 
к базе данных из файла application.properties  

**3) Встроенная поддержка сервера приложений — контейнера сервлетов:**  
Каждое Spring Boot web-приложение включает встроенный web-сервер.  
Разработчикам теперь не надо беспокоиться о настройке контейнера сервлетов и развертывании приложения на нем.
Теперь приложение может запускаться само, как исполняемый jar-файл с использованием встроенного сервера.   


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

## JDBC
(см.проект eduexample)  

 
  
[к оглавлению](#SPRING)