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
Ключевые объекты контекста Spring Security:

+ SecurityContextHolder, в нем содержится информация о текущем контексте безопасности приложения,
который включает в себя подробную информацию о пользователе(Principal) работающем в настоящее время с приложением. 
По умолчанию SecurityContextHolder используетThreadLocal для хранения такой информации, что означает, 
что контекст безопасности всегда доступен для методов исполняющихся в том же самом потоке. 
Для того что бы изменить стратегию хранения этой информации можно воспользоваться статическим методом класса 
SecurityContextHolder.setStrategyName(String strategy). Более подробно SecurityContextHolder  
+ SecurityContext, содержит объект Authentication и в случае необходимости информацию системы безопасности, связанную с запросом от пользователя.
+ Authentication представляет пользователя (Principal) с точки зрения Spring Security.
+ GrantedAuthority отражает разрешения выданные пользователю в масштабе всего приложения, такие разрешения (как правило называются «роли»), 
например ROLE_ANONYMOUS, ROLE_USER, ROLE_ADMIN.  
+ UserDetails предоставляет необходимую информацию для построения объекта Authentication из DAO объектов приложения 
или других источников данных системы безопасности. Объект UserDetailsсодержит имя пользователя, пароль, флаги: 
isAccountNonExpired, isAccountNonLocked, isCredentialsNonExpired, isEnabled и Collection — прав (ролей) пользователя.  
+ UserDetailsService, используется чтобы создать UserDetails объект путем реализации единственного метода этого интерфейса  

UserDetails loadUserByUsername(String username) throws UsernameNotFoundException; 
Позволяет получить из источника данных объект пользователя и сформировать из него объект UserDetails который будет использоваться контекстом Spring Security.  

1. Добавить зависиомсти
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
2.        