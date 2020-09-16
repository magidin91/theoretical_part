## SQL, Jdbc  

+ прочитать этот туториал при возможности - [sql-туториал](http://www.sql-tutorial.ru/ru/content.html)       

**SQL:**
+ [CREATE](CREATE) 
+ [INSERT](INSERT) 
+ [SELECT](SELECT) 
+ [Alter](Alter) 
+ [Команды PostgreSQL](#Команды-PostgreSQL)
+ [Ограничения столбцов и таблиц](Ограничения-столбцов-и-таблиц)
+ [Что такое первичный ключ?](Что-такое-первичный-ключ)
+ [Что такое внешний ключ?](Что-такое-внешний-ключ)

3. Что такое нормализация БД?
4. Что такое денормализация БД? Для чего она нужна?
5. Что такое кластерный и некластерный индекс?
6. Какие типы соединений (join) таблиц существуют? В чем их разница?
7. Что такое SQL курсор?
8. Опишите шаги по созданию и использованию курсора?
10. Что такое транзакция?
11. Что такое триггер? (Какие типы триггеров вы знаете?)
12. В чем разница между WHERE и HAVING?
13. Что такое подзапрос (sub-query)?
14. Что такое union?
15. Что такое group by
17. Что такое хранимые процедуры?
18. Что такое view(Представление)?
19. Опишите процесс создания запроса через JDBC.
20. Для чего используется конструкция try-with-resources.

## Общее:  
+ Реляционные базы хранят данные в таблицах, в которых строки соответствуют записям, а колонки полям.  
+ Типы данных:   
dec - хранит числа с фиксированной точностью, которые могут иметь до 131072 знаков в целой части и до 16383 знаков в дробной части.
То же самое, что и numeric. Данный тип может принимать два параметра precision и scale: numeric(precision, scale).
Параметр precision указывает на максимальное количество цифр, которые может хранить число.
Параметр scale представляет максимальное количество цифр, которые может содержать число после запятой.   
Это значение должно находиться в диапазоне от 0 до значения параметра precision. По умолчанию оно равно 0.  
real: хранит числа с плавающей точкой из диапазона от 1E-37 до 1E+37. Занимает 4 байта. Имеет псевдоним float4.  
double precision: хранит числа с плавающей точкой из диапазона от 1E-307 до 1E+308. Занимает 8 байт. Имеет псевдоним float8.  
Тип boolean может хранить одно из двух значений: true или false.  
json: хранит данные json в текстовом виде.  
jsonb: хранит данные json в бинарном формате.  

[к оглавлению](#SQL-Jdbc)    

## Команды PostgreSQL:
+ create database test; //потом убрат вниз
+ use test;  
или \c test; для postgresql  
Приказать СУБД использовать созданную базу данных
+ drop table students; 
 
**Дополнительно (команды SQL):**
+ DESC students;  
Посмотреть описание созданной таблицы.
+ SHOW CREATE TABLE users;  
В MySql возвращает команду CREATE TABLE, которая была использована для создания таблицы.  
+ insert into students values ('', 'Petr', '2000-06-02');  
Два апострофа означают,что значение первичного ключа должно генерироваться автоматически.  

**Получение части строки:**
**SUBSTRING_INDEX ()**  
Функция SUBSTRING_INDEX () находит все символы текстового значения, предшествующие заданному символу или подстроке.
Запятая заключается в апострофы, а функция SUBSTRING INDEX () возвращает все символы, стоящие перед запятой:  
SELECT SUBSTRING_INDEX (location, ’,’ , 1 ) FROM my__contacts; (1 – значит, что ищется первая запятая)  
[подробнее](http://old.code.mu/sql/substring_index.html)  

[к оглавлению](#SQL-Jdbc)       

## CREATE  
Создать таблицу:    
create table students (id serial primary key, name varchar(20), birth_date timestamp);  
**Создание таблицу с Внешним ключом**  
CREATE TABLE Orders (Id SERIAL PRIMARY KEY, CustomerId INTEGER, Quantity INTEGER, FOREIGN KEY (CustomerId) REFERENCES Customers (Id));  
или CREATE TABLE Orders (Id SERIAL PRIMARY KEY, CustomerId INTEGER REFERENCES Customers (Id), Quantity INTEGER); 
+ **Создать таблицу на основании указанных столбцов другой таблицы**
create table people_new as Select name, age, passport from people;  
Не только создает структуру, но и копирует данные.  

[к оглавлению](#SQL-Jdbc)    

## INSERT  
insert into students values (1, 'Ivan', '2009-06-04'), (2, 'Petr', '2009-06-04');  
Можно указать конкретные столбцы:  
insert into students (name, birth_date) values ('Ivan', '2009-06-04'), ('Petr', '2009-06-04');  
При вставке можно посмотреть значения тех столбцов, которые мы не указываем при вставке, например первичного ключа. 
insert into students (name, birth_date) values ('Ivan', '2009-06-04') RETURNING id; 
+ **INSERT с SELECT**  (вставить)  
INSERT INTO profession (profession)  SELECT  profession  FROM  my_contacts;  
Т.е. мы вставляем записи из столбца profession таблицы my_contacts в столбец profession таблицы profession.    
+ **ON CONFLICT DO NOTHING**  
Если, например, поле name задано Unique, то при обычной вставке дублирующего значения, произойдет ошибка.
Чтобы этого избежать можно использовать условие ON CONFLICT DO NOTHING/UPDATE. В этом случае вставка не произойдет, 
но и не будет выдано никакое сообщение об ошибке.    
Конструкция DO UPDATE SET обновляет поля, которые в ней указаны.    
INSERT INTO account (id, name, surname, address) VALUES (1, 'Петя', 'Петров', 'Москва, Кремль') 
ON CONFLICT (id) DO UPDATE SET name='Петя', surname='Петров';  
[подробнее](https://habr.com/ru/post/264281/)  

[к оглавлению](#SQL-Jdbc)    
  
##  SELECT  
Where (AND, OR, <>,<=, IS NULL, LIKE+ % и _ , BETWEEN, IN, NOT IN)    
Для фильтрации данных применяется оператор WHERE, после которого указывается условие, на основании которого производится фильтрация.
Если условие истинно, то строка попадает в результирующую выборку.  
<>: сравнение на неравенство - не равно  
В качестве условия могут использоваться и более сложные выражения. Например, найдем все товары, у которых совокупная стоимость больше 90 000: 
SELECT * FROM Products WHERE Price * ProductCount > 90000;  

+ **AND, OR, NOT**  
SELECT * FROM Products WHERE Manufacturer = 'Samsung' AND Price > 50000;    
SELECT * FROM Products WHERE Manufacturer = 'Samsung' OR Price > 50000;  
Применение оператора NOT - выберем все товары, у которых производитель не Samsung:  
SELECT * FROM Products WHERE NOT Manufacturer = 'Samsung';  
Но в большинстве случаев можно обойтись без оператора NOT. Предыдущий пример мы можем переписать следующим образом:  
SELECT * FROM Products WHERE Manufacturer <> 'Samsung'  
Также в одной команде SELECT можно использовать сразу несколько операторов:
SELECT * FROM Products WHERE Manufacturer = 'Samsung' OR Price > 30000 AND ProductCount > 2;  
Так как **оператор AND имеет более высокий приоритет**, то сначала будет выполняться 
подвыражение Price > 30000 AND ProductCount > 2, и только потом оператор OR. (То есть здесь выбираются товары, 
которыех на складе больше 2 и у которых одновременно цена больше 30000, либо те товары, производителем которых является Samsung.)  
С помощью скобок мы также можем переопределить порядок операций:  
SELECT * FROM Products WHERE (Manufacturer = 'Samsung' OR Price > 30000) AND ProductCount > 2;    

+ **IS NULL**  
Для проверки на наличие Null применяется оператор IS NULL.  
SELECT * FROM Products WHERE ProductCount IS NULL;  
Если, наоборот, необходимо получить строки, у которых поле ProductCount не равно NULL, то можно использовать оператор NOT:  
SELECT * FROM Products WHERE ProductCount IS NOT NULL;  
[подробнее](https://metanit.com/sql/postgresql/3.3.php)  

**Операторы сравнения при поиске текстовых данных** 
+ **LIKE**   
Ключевое слово LIKE, которое в сочетании со специальными символами ищет часть текстовой строки и возвращает совпадения.  
SELECT * FROM my_contacts Where location LIKE '%CA’;  
**Знак %** говорит, что мы ищем все значения локэйшен, которые заканчиваются на СА. Знак % — обозначает любое количество произвольных символов.
Также можно использовать %часть слова%, если ищем часть в середине слова.  
**Знак подчеркивания (_)** — представляет ровно один произвольный символ.  
SELECT first_name FROM my_contacts Where first_name LIKE '_им';
Запрос возвращает имена, которые со стоят из одной буквы + ≪иМ≫: KиM,  ТиМ и т.д.  

**Проверка вхождения значений в диапазон**  
**Обычный вариант**  
SELECT drink_name FROM drink_info WHERE calories>= 30  AND calories<= 60;  

+ **BETWEEN**  
Конструкция BETWEEN эквивалентна использованию операторов <= и >=, но не < и >.  
SELECT drink_name FROM drink_info WHERE calories BETWEEN 30 AND 60; - [30, 60]  
SELECT drink_name FROM drink_info WHERE drink_name BETWEEN 'Д ' AND 'O';  
Запрос возвращает названия всех напитков, начинающихся с Д, заканчивающихс О и всех букв между ними.  

+ **IN (совпадает со значением из набора)**   
После IN следует набор значений в круглых скобках. Если значение столбца совпадает с одним из значений набора, 
то запись или заданное подмножество столбцов включаются в результат запроса.  
SELECT date_name FROM black_book WHERE rating IN ('оригинально', 'потрясающе', 'неплохо');  
NOT IN (не входит в набор значений)  
SELECT date_name FROM black_book WHERE rating NOT IN ('оридтинально', 'потрясающе', ’неплохо’);   

+ **Другие применения NOT**  
Ключевое слово NOT может использоваться не только с IN, но и с BETWEEN и LIKE. Однако необходимо помнить, что NOT следует сразу же после WHERE.  
SELECT drink_name FROM drink_info WHERE NOT carbs BETWEEN 3 AND 5;  
Если ключевое слово Not используется с And или Or, оно записывается после And или Or.  
SELECT date_name FROM black_book WHERE date_name LIKE 'A%' and NOT date name LIKE 'E%';  

+ **Получение части строки**  
**Right, Left**  
Для выделения заданного количества символов в столбце используются функции RIGHT() и LEFT();  
SELECT RIGHT (location , 2) FROM my_contacts;  
Функция выделяет два последних символа из столбца location;
  
+ **SUBSTRING**  
Функция SUBSTRING(выражение, начальная позиция, длина) позволяет извлечь из выражения его часть заданной длины, 
начиная от заданной начальной позиции. Выражение может быть символьной или бинарной строкой, а также иметь тип text или image.  
Например, если нам потребуется получить три символа в названии корабля, начиная со второго символа:
SELECT name, SUBSTRING(name, 2, 3) FROM Ships; (выведет столбец name и столбец SUBSTRING)  
+ **SUBSTR**  
Команда SUBSTR (interests , LENGTH (interest1) +2) возвращает часть строки interest1, отрезав от нее левую часть указанной длины.
UPDATE my_contacts SET interests = SUBSTR (interest, LENGTH (interest1)+2);  

+ **AS**  
С помощью оператора AS можно изменить название выходного столбца или определить его псевдоним:  
SELECT ProductCount AS Title, Manufacturer, Price * ProductCount  AS TotalSum FROM Products;  

[к оглавлению](#SQL-Jdbc)  

## Alter
**Добавление нового столбца**  
ALTER TABLE Customers ADD Phone CHARACTER VARYING(20);  
**Удаление столбца**    
ALTER TABLE Customers DROP COLUMN Address;    
**Изменение типа столбца** 
Для изменения типа применяется ключевое слово TYPE.  
ALTER TABLE Customers ALTER COLUMN FirstName TYPE VARCHAR(50);  
**Изменение ограничений столбца**  
Для добавления ограничения применяется оператор SET, после которого указывается ограничение. 
Например, установим для столбца FirstName ограничение NOT NULL:  
ALTER TABLE Customers ALTER COLUMN FirstName SET NOT NULL;  
**Для удаления ограничения применяется оператор DROP**, после которого указывается ограничение. 
Например, удалим выше установленное ограничение:  
ALTER TABLE Customers ALTER COLUMN FirstName DROP NOT NULL;  
**Изменение ограничений таблицы**    
Добавление первичного ключа PRIMARY KEY:  
ALTER TABLE Customers ADD PRIMARY KEY (cust_id);  
Добавление ограничение UNIQUE - определим для столбца Email уникальные значения:  
ALTER TABLE Customers ADD UNIQUE (Email);  
Мы можем явным образом назначить ограничению при добавлении имя с помощью оператора CONSTRAINT.  
ALTER TABLE Customers ADD CONSTRAINT phone_unique UNIQUE (Phone);  
**Удалить ограничение**  
Чтобы удалить ограничение, надо знать его имя:  
ALTER TABLE Customers DROP CONSTRAINT phone_unique;  
**Переименование столбца и таблицы**  
Переименуем столбец Address в City:  
ALTER TABLE Customers RENAME COLUMN Address TO City;  
Переименуем таблицу Customers в Users:  
ALTER TABLE Customers RENAME TO Users;  
[подробнее](https://metanit.com/sql/postgresql/2.6.php)   
  
+ Limit и offset  
Выбрать **limit** записей, начиная с **(offset+1)**-ой:  
SELECT* FROM students LIMIT 10 OFFSET 14; (получим 10 записей, начиная с 15-ой)  
offset - это смещение, т.е. чтобы получить вторую запись, а не первую  offset = 1    

+ Значение по умолчанию в столбце таблицы    
CREATE TABLE shop_goods (id serial primary key, cost DEC(3,2) NOT NULL DEFAULT 1.00);  
cost - Этот столбец ВСЕГДА должен содержать значение. Для этого мы не только объявляем его с ключевыми словами NOT NULL, но и присваиваем значение по умолчанию 1.00  

[к оглавлению](#SQL-Jdbc)  

## Ограничения столбцов и таблиц 
+ **UNIQUE**  
Если мы хотим, чтобы столбец имел только уникальные значения, то для него можно определить атрибут UNIQUE  
CREATE TABLE Customers (Id SERIAL, FirstName varchar(30) UNIQUE );  
В данном случае FirstName будет иметь уникальное значение. И мы не сможем добавить в таблицу две строки,
у которых значения для этих столбцов будет совпадать.  
Также мы можем определить этот атрибут на уровне таблицы:  
CREATE TABLE Customers (Id SERIAL PRIMARY KEY, Email varchar(30), Phone varchar(30), Age INTEGER, UNIQUE(Email, Phone));  
или: CREATE TABLE Customers (Id SERIAL PRIMARY KEY, Email varchar(30), Phone varchar(30), Age INTEGER,  UNIQUE(Email), UNIQUE(Phone));  
+ **CHECK**   
Ключевое слово CHECK задает ограничение для диапазона значений, которые могут храниться в столбце.
Для этого после слова CHECK указывается в скобках условие, которому должен соответствовать столбец.  
CREATE TABLE Customers (Id SERIAL PRIMARY KEY,
Age INT CHECK(Age >0 AND Age < 100),  
Email VARCHAR(30) CHECK(Email !=''),   
Phone VARCHAR(20) UNIQUE CHECK(Phone !=''));  
+ **CONSTRAINT**  
С помощью ключевого слова CONSTRAINT можно задать имя для ограничений. В качестве ограничений могут использоваться **PRIMARY KEY, UNIQUE, CHECK**.
Необязательно задавать имена ограничений, но, зная имя ограничения, мы можем к нему обращаться, например, для его удаления.  
CREATE TABLE Customers (   
Id SERIAL CONSTRAINT customer_Id PRIMARY KEY,
Email VARCHAR(30) CONSTRAINT customers_email_key UNIQUE,
Phone VARCHAR(20) CONSTRAINT customers_phone_key UNIQUE); 
Мы задали названия: customer_Id, customers_email_key, customers_phone_key.    
И также можно задать все имена ограничений через атрибуты таблицы:  
CREATE TABLE Customers ( Id SERIAL, Age INTEGER, Email VARCHAR(30), Phone VARCHAR(20),  
CONSTRAINT customer_Id PRIMARY KEY(Id),  
CONSTRAINT customers_age_check CHECK(Age >0 AND Age < 100),  
CONSTRAINT customers_email_key UNIQUE(Email),  
CONSTRAINT customers_phone_key UNIQUE(Phone));  

[к оглавлению](#SQL-Jdbc) 

## UPDATE/DELETE  
**UPDATE**  
Например, увеличим у всех товаров цену на 3000:  
UPDATE Products SET Price = Price + 3000;  
В данном случае обновление касается всех строк.
С помощью выражения WHERE можно с помощью условию конкретизировать обновляемые строки:  
UPDATE Products SET Manufacturer = 'Samsung Inc.' WHERE Manufacturer = 'Samsung';  
Также можно обновлять сразу несколько столбцов:  
UPDATE Products SET Manufacturer = 'Samsung', ProductCount = ProductCount + 3 WHERE Manufacturer = 'Samsung Inc.';

**DELETE**     
Например, удалим строки, у которых производитель - Apple:
DELETE FROM Products WHERE Manufacturer='Apple';  
Если необходимо вовсе удалить все строки вне зависимости от условия, то условие можно не указывать:  
DELETE FROM Products;  

[к оглавлению](#SQL-Jdbc)    
  
## Что такое первичный ключ?  
С помощью выражения PRIMARY KEY столбец можно сделать первичным ключом. Первичный ключ - ункиальный идентификатор строки в таблице.
В качестве первичного ключа необязательно должны выступать столбцы с типом SERIAL, они могут представлять любой другой тип.  
CREATE TABLE Customers (Id SERIAL PRIMARY KEY);  
CREATE TABLE Customers (Id SERIAL, FirstName varchar(30), **PRIMARY KEY(Id)**);  
**Первичный ключ может быть составным** (compound key).
Такой ключ может потребоваться, если у нас сразу два столбца должны уникально идентифицировать строку в таблице:  
CREATE TABLE OrderLines (OrderId INTEGER, ProductId INTEGER, Quantity INTEGER, PRIMARY KEY (OrderId, ProductId));  

[к оглавлению](#SQL-Jdbc)  

##Что такое внешний ключ?  
Внешний ключ SQL — это ключ, используемый для объединения двух таблиц. Внешний ключ устанавливается для столбца из зависимой, 
подчиненной таблицы (referencing table), и указывает на один из столбцов из главной таблицы (обычно на первичный ключ). 
Т.е. внешний ключ - это поле значение, которого равно конкретному значению первичного ключа другой таблицы, 
тем самым мы можем понять, какая запись главной таблицы соответствует полю внешнеего ключа.    
Внешний ключ – это ограничение, которое поддерживает согласованное состояние данных между двумя таблицами, 
обеспечивая так называемую ссылочную целостность. **Этот тип целостности означает,
что всегда есть возможность получить полную информацию об объекте, распределенную по нескольким таблицам.**  
Т.е. таким образом мы можем разделить информацию об объекте на неск. таблиц, например,
из таблицы с заказами вынести поля покупателя и заказанного продукта в отдельные таблицы.  
Определение внешнего ключа на уровне таблицы выглядело бы следующим образом:    
CREATE TABLE Customers ( Id SERIAL PRIMARY KEY, Age INTEGER,  FirstName VARCHAR(20) NOT NULL);  
CREATE TABLE Orders (Id SERIAL PRIMARY KEY, CustomerId INTEGER, Quantity INTEGER, FOREIGN KEY (CustomerId) REFERENCES Customers (Id));  
 
**ON DELETE и ON UPDATE**     
С помощью выражений ON DELETE и ON UPDATE можно установить действия, 
которые выполняются соответственно при удалении и изменении связанной строки из главной таблицы:  
+ CASCADE: автоматически удаляет/изменяет строки из зависимой таблицы при удалении или изменении связанных строк в главной таблице.
+ RESTRICT: предотвращает какие-либо действия в зависимой таблице при удалении/изменении связанных строк в главной таблице.
То есть фактически какие-либо действия отсутствуют.
NO ACTION: действие по умолчанию, предотвращает какие-либо действия в зависимой таблице при удалении или изменении связанных строк в главной таблице.
**И генерирует ошибку.** В отличие от RESTRICT выполняет отложенную проверку на связанность между таблицами.
+ SET NULL: при удалении связанной строки из главной таблицы устанавливает для столбца внешнего ключа значение NULL.
+ SET DEFAULT: при удалении связанной строки из главной таблицы устанавливает для столбца внешнего ключа значение по умолчанию, которое задается с помощью атрибуты DEFAULT. 
Если для столбца не задано значение по умолчанию, то в качестве него применяется значение NULL.  
[подробнее](https://metanit.com/sql/postgresql/2.5.php)
[подробнее](http://www.sql-tutorial.ru/ru/book_foreign_key.html)

[к оглавлению](#SQL-Jdbc)  
## Какие типы соединений (join) таблиц существуют? В чем их разница?  
Нередко возникает ситуация, когда нам надо получить данные из нескольких таблиц, для этого можно применять JOIN-ы.  
+ **INNER JOIN** (JOIN) 
SELECT столбцы FROM таблица1 [INNER] JOIN таблица2 ON условие1 [[INNER] JOIN таблица3 ON условие2];  
Как правило, для соединения применяется первичный ключ главной таблицы и внешний ключ зависимой таблицы.  
SELECT Orders.CreatedAt, Customers.FirstName, Products.ProductName FROM Orders  
JOIN Products ON Products.Id = Orders.ProductId  
JOIN Customers ON Customers.Id=Orders.CustomerId;  
Получим заказ с информацией о покупателе из таблицы Customers и о продукте из таблицы Products.  
Условия после ключевого слова ON могут быть более сложными по составу.
Например, выбирем все заказы на товары, производителем которых является Apple:  
SELECT Orders.CreatedAt, Customers.FirstName, Products.ProductName FROM Orders  
JOIN Products **ON Products.Id = Orders.ProductId AND Products.Company='Apple'**  
JOIN Customers ON Customers.Id=Orders.CustomerId ORDER BY Customers.FirstName;    
+ **Cross Join**(перекрестное соединение)  
Перекрестное соединение создает набор строк, где каждая строка из одной таблицы соединяется с каждой строкой из второй таблицы. 
Например, соединим таблицу заказов Orders и таблицу покупателей Customers:  
SELECT * FROM Orders CROSS JOIN Customers;  
Если в таблице Orders 3 строки, а в таблице Customers то же три строки,
то в результате перекрестного соединения создается 3 * 3 = 9 строк вне зависимости, связаны ли данные строки или нет.  
Также можно опустить оператор CROSS JOIN: SELECT * FROM Orders, Customers;  
+ **OUTER JOIN**



 
 
  
  



