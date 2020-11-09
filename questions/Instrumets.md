##  Instruments  

##  Tomcat  
[подробнее](https://javarush.ru/groups/posts/tomcat-v-java)    
  
Tomcat — это контейнер сервлетов с открытым исходным кодом, который также выполняет функцию веб-сервера.  
Большинство приложений Java запускаются с помощью командной строки и выполняют некоторые действия. 
Такие приложения реализуют одну заранее заданную функцию, после чего больше не выполняются. 
У таких программ, как правило, есть метод main, через который их можно запустить.

Веб-приложение рассчитано на взаимодействие с клиентом. Если есть запрос от клиента, он обрабатывается, 
и пользователю отправляется ответ. Если нет, приложение простаивает. 
Как реализовать такую логику в стандартном приложении, учитывая, что нужно поддерживать сессии, принимать HTTP-запросы и прочее? 
Цикл while-true? Нет, здесь нужно надежное решение.  
   
Tomcat заботится об открытии порта для взаимодействия с клиентом, настройке сессий, количестве запросов, 
длине заголовка и еще многих операциях.  

## Maven  
[apache maven](https://maven.apache.org/)  
[Maven](https://javarush.ru/groups/posts/2523-chastjh-4osnovih-maven)  

Дипенденсис – это просто java классы, а плагины – это класс, которые будут выполняться в процессе сборки.  

Главный пом может содержать модули, тогда при его сборке будут собираться зависимые помы, в которых прописаны свои билды. 
Если нам нужно, чтобы несколько модулей собиралось одинаково, мы можем прописать build  в главном поме, тогда эти мавен плагины и настройки 
будут использоваться для каждого из модулей.    

Варианты сборки мавена
<packaging>pom | jar | war | ear </packaging>  
+ pom - просто компиляция классов 
+ jar - приложение упакоывается в исполняемый jar - архив (для десктопныхх приложений)
+ war - внутрь входит веб-часть приложения: могут содержать Rest Api, фронтовую часть(ангуляр). При запуске war бронирует 
какой-то хост компьютера (по умолчанию http://localhost:8080/), для запуска нужен сервер (напр. томкат)
+ ear - внутрь входит xml-описание приложения + war + jar, ear позволяет все это скомбинирвоать и запустить в сервере приложений.  

## Последовательность сборки модулей:  
Чтобы один модуль собирался раньше другого, нужно поместить зависимость с тем модулем, который должен собраться раньше в 
наш модуль.  
Пример:  

```xml
 <!--Зависимость модуля liquibase, чтобы он собрался до модуля spring и сгенерировал таблицы, помещаем зависимость на него в модуль spring-->
        <dependency>
            <groupId>ru.dfsystems</groupId>
            <artifactId>liquibase</artifactId>
            <type>pom</type>
            <version>${project.version}</version>
        </dependency>
```
    

  

## Liquibase  
[пример](https://github.com/magidin91/spring-angular-project/tree/magidin/liquibase)  
(см. видео 2 из учебного курса DFS)    
Это система управления версиями базы данных. Позволяет не только изменять текущую БД, но и позволяет мигрировать нашу бд с однйо субд на другую.  
Мы использовали для создания структуры таблиц в нашей БД.  
changeSet - это конкретная инструкция для бд, например создать таблицу.
changelog.xml - это набор таких инструкций.  
Чейнжлоги перечисляются в master.xml.  

Commands:  
(про команды смотреть с 15 минуты) 

<goal>updateSQL</goal> - рекомендуется выполнять перед update, он сгенерирует все sql-запросы и проверит их синтаксис и порядок. И если 
обнаруживается ошибка, то update не запускается.   

<goal>update</goal> - выполняет чейнжсеты, если команда падает где-то по середине чейнжлога, то те чейнжсеты, которые выполнились - запишутся в таблицу, 
которые нет - не запишутся.  
<goal>generateChangeLog</goal> - генерирует чейнжсеты на основе бд. (для создания такой же структуры)  
Удобно сначала создать таблицы, а потом не прописывать вручную чейнжсеты на создание, а получить автоматически.      
<goal>rollback</goal>  

diff - сравнит наши чейнжсеты и структуру бд, и создат в отдельном файле чейнжсеты для различий.  

Создается две технические таблицы: databasechangelog и databasechangeloglock.  
Databasechangelog содержит выполненные чейнжсеты, и внутри содержит автора, дату описание итд  
Если мы запустим один и тот же чейнжсет второй раз, то Liquibase его просто проигнорирует.   
Databasechangeloglock блокирует подключение двух Liquibase одновременно.(т.е. одновременно только один Liquibase)  

Чейнжсеты могут быть в разных форматах: sql, xml, json, yaml, но чаще используется xml.  

Также в самом xml  чейнжлоге может быть не только xml синтаксис, но и SQL:  
```xml
<changeSet id="20200620-05" author="polovnikov">
        <preConditions onFail="MARK_RAN">
            <not>
                <tableExists tableName="ROOM" schemaName="public"/>
            </not>
        </preConditions>
        <comment>LessonList 1. Liquibase. Создание таблицы ROOM</comment>
        <sql>
            create table ROOM (
            id serial primary key,
            idd int NOT NULL,
            number varchar(5),
            block varchar(5),
            create_date timestamp(0) not null,
            delete_date timestamp(0)
            );
        </sql>
        <rollback>
            <sql>
                drop table ROOM;
            </sql>
        </rollback>
    </changeSet>
```  
Или через заранее заготовленный файл с SQL - скриптом:  
```xml
 <changeSet id="20200620-06" author="polovikov">
        <preConditions onFail="MARK_RAN">
            <not>
                <tableExists tableName="INSTRUMENT_TO_ROOM" schemaName="public"/>
            </not>
        </preConditions>
        <sqlFile path="sql/instrument_to_room.sql" relativeToChangelogFile="true"/>
        <rollback>
            <sqlFile path="sql/instrument_to_room_rollback.sql" relativeToChangelogFile="true"/>
        </rollback>
    </changeSet>
```
Скрипты лежат relativeToChangelogFile - относительно ChangelogFile, т.е. текущая директория - данный чейнжлог.  
В случае использования такого SQL, чейнжлоги зависят от конкретной субд и менее переносимы, чем чистый xml.  

PreConditions – это условие, которое выполняется перед ченджсетом. По умолчанию стоит HALT.   
1.	preConditions onFail="HALT"> т.е. ченджcет падает с ошибкой
2.	preConditions onFail="WARN"> т.е. мы получим предупреждение, что прекондишен не выполнился, но Liquibase все равно попытается выполнить ченжсет.
3.	preConditions onFail="CONTINUE"> т.е. он просто пропустит данный ченжсет, ченжлог останется пустым.
4.	**preConditions onFail="MARK_RAN"> пропустит данный ченжсет, и запишет в ченжлог MARK_RAN, что данный ченжсет не выполнился.**  
Обычно испольуется этот вариант.  
```xml
<preConditions onFail="MARK_RAN">
            <not>
                <tableExists tableName="INSTRUMENT" schemaName="public"/>
            </not>
</preConditions>
```

Ченжсеты после выполнения менять нельзя, в этом случае при его выполнении у него изменится хеш (вычисляется на сонвое содержимого чейнжсета) 
и он упадет с ошибкой. Когда мы выполняем ченжлог хеши чейжнсетов сверяются.  

PreCondition падает на этапе update sql.  
PreConditions всегда стоит первым. PreConditions и rollback не входит в хеш ченжсета.  
Rollback:  

+ <rollbackCount>2</rollbackCount> - количество ченжсетов с конца, роллбеки которых будут выполняться. 
При этом удалются и их записи в databasechangelog, как будто их не было.  
+ <rollbackDate>2020-06-20</rollbackDate> дата, начиная от которой будут выполняться роллбеки ченжсетов.
+ <rollbackTag>changelog-2.0</rollbackTag>  
Для тега, делаем перед ченжсетами ченжсет с тегом, и тогда откатятся все ченжсеты после него.  

**Через терминал запуск Liquibase - mvn clean install -Pprofile**  

Необходимые зависимости:
```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <artifactId>spring-angular-project</artifactId>
        <groupId>ru.dfsystems</groupId>
        <version>1.0-SNAPSHOT</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>

    <artifactId>liquibase</artifactId>
    <packaging>pom</packaging>

    <dependencies>
        <dependency>
            <groupId>org.postgresql</groupId>
            <artifactId>postgresql</artifactId>
            <version>42.2.14</version>
        </dependency>
    </dependencies>

Мы добавили профили, в которых указали БД и проперти для подключения к ней. Таким образом, в разных ситуациях могут использоваться разные субд и бд.
 (например, в тестах и prod или в dev)

    <profiles>
        <profile>
            <id>magidin</id>
            <properties>
                <url>jdbc:postgresql://127.0.0.1:5432/spring-angular-db</url>
                <username>admin</username>
                <password>admin</password>
            </properties>
        </profile>
    </profiles>

    <build>
        <plugins>
            <plugin>
                <groupId>org.liquibase</groupId>
                <artifactId>liquibase-maven-plugin</artifactId>
                <version>3.10.0</version>
                <configuration>
                    <defaultSchemaName>public</defaultSchemaName>
                    <changeLogFile>${basedir}/db/master.xml</changeLogFile>
<!--                    <outputChangeLogFile>${basedir}/db/output.xml</outputChangeLogFile>-->

Плагин берет проперти из профайла и определяет пропертис для подключеня к БД.

                    <url>${url}</url>
                    <username>${username}</username>
                    <password>${password}</password>
<!--                    <rollbackCount>2</rollbackCount>-->
<!--                    <rollbackDate>2020-06-20</rollbackDate>-->	
<!--                    <rollbackTag>changelog-2.0</rollbackTag>-->
                </configuration>
                <executions>
                    <execution>
                        <phase>process-resources</phase>
                        <goals>
                            <goal>updateSQL</goal>
                            <goal>update</goal>
<!--                            <goal>generateChangeLog</goal>-->
<!--                            <goal>rollback</goal>-->
                        </goals>
                    </execution>
                </executions>
            </plugin>
        </plugins>
    </build>
</project>

```  
## Jooq  
Генерирует jooq-классы: 
Если мы создали таблицы через Liquibase на основе xml чейнжлогах, то также нужн опрописать чейнжсеты с сиквенсами для этих таблиц, 
иначе они не создадутся. Через sql оин создаются сами.  
+ **pojos** - т.е. сщуности создаются автоматически (не надо создавать отдельно как в хайбернете)
+ **daos** - дефолтные дао с базовыми методами
+ **sequences**
+ validationAnnotations
+ springAnnotations - спринг аннотации типа @Repository итд
+ globalObjectReferences
+ javaTimeTypes  

Мы отдельно создавали DTO - data transfer objects, чтобы отдавать на фронт только необходимые поля, а не все поля сгенерированного pojo.  

```xml
 <build>
    <!-- <pluginManagement/> определяет параметры плагинов, которые будут унаследованы модулями в вашей сборке.
    Это подходит для случаев, когда у вас есть родительский файл pom.   -->
        <pluginManagement>
            <plugins>
              
                <!-- Плагин, который генерирует jooq классы -->
                <plugin>
                    <groupId>org.jooq</groupId>
                    <artifactId>jooq-codegen-maven</artifactId>
                    <version>${org.jooq.version}</version>

                    <executions>
                        <execution>
                            <goals>
                                <goal>generate</goal>
                            </goals>
                        </execution>
                    </executions>

                    <dependencies>
                        <dependency>
                            <groupId>org.postgresql</groupId>
                            <artifactId>postgresql</artifactId>
                            <version>${org.postgresql.version}</version>
                        </dependency>
                    </dependencies>

                    <configuration>
                        <jdbc>
                            <driver>org.postgresql.Driver</driver>
                            <url>${url}</url>
                            <username>${username}</username>
                            <password>${password}</password>
                        </jdbc>

                        <generator>
                            <!--  стратегия генерации jooq-классов-->
                            <strategy>
                                <name>ru.dfsystems.spring.tutorial.generate.CustomJooqGeneratorStrategy</name>
                            </strategy>

                            <database>
                                <!-- мета-зависимость из jooq-meta-->
                                <name>org.jooq.meta.postgres.PostgresDatabase</name>
                                <!-- .* - генерируем Jooq - классы для всех таблиц-->
                                <!-- в includes пишем, что для каких таблиц генерируем классы-->
                                <includes>.*</includes>
                                <excludes>databasechangelog | databasechangeloglock</excludes>

                                <schemata>
                                    <schema>
                                        <inputSchema>public</inputSchema>
                                    </schema>
                                </schemata>
                            </database>
                            <!-- куда генерируемв папке target-->
                            <target>
                                <packageName>ru.dfsystems.spring.tutorial.generated</packageName>
                                <directory>target/generated-sources/jooq</directory>
                            </target>

                            <!-- Что генерируем: -->
                            <generate>
                                <pojos>true</pojos>
                                <daos>true</daos>
                                <sequences>true</sequences>
                                <validationAnnotations>true</validationAnnotations>
                                <!-- спринг аннотации типа @Repository итд-->
                                <springAnnotations>true</springAnnotations>
                                <globalObjectReferences>true</globalObjectReferences>
                                <javaTimeTypes>true</javaTimeTypes>
                            </generate>
                        </generator>
                    </configuration>
                </plugin>
            </plugins>
        </pluginManagement>
    </build>
</project>
```     
Иногда джук может некоректно выбрать тип поля для класса, тогда нужно вручную прописать условие в джук-плагине. 
     