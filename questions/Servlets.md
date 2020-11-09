## Servlets


## Что такое сервлеты
[подробнее](https://javarush.ru/groups/posts/2529-chastjh-5-servletih-pishem-prostoe-veb-prilozhenie) !!!  
[letscode-ютуб-видео](https://www.youtube.com/watch?v=Jnd4PQt44j0&ab_channel=letsCode)
    

Java Servlet API — стандартизированный API, предназначенный для реализации на сервере и работе с клиентом по схеме запрос-ответ.  
Сервлет — это класс, который умеет получать запросы от клиента и возвращать ему ответы. 
Сервлеты в Java — именно те элементы, с помощью которых строится клиент-серверная архитектура.  

```java
@WebServlet("/hello")
public class MainServlet extends HttpServlet {

   @Override
   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       resp.setContentType("text/html");
       PrintWriter printWriter = resp.getWriter();
       printWriter.write("Hello!");
       printWriter.close();
   }
}
```  
(см. проект servlet_demo)  

В примере GET-запрос. Остальные http-методы обрабатываются аналогичным образом — 
переопределяя соответствующие методы родительского класса.       
  
Чтобы из обычного класса сделать http-сервлет, его нужно унаследовать от класса HttpServlet. 
Над классом указываем аннотацию @WebServlet(), в которой привязываем (мапим) сервлет к конкретному пути (“/hello”). 

Чтобы обрабатывать GET-запросы, переопределяем метод doGet(). Обрати внимание на аргументы метода — 
HttpServletRequest и HttpServletResponse. С объекта HttpServletRequest мы можем взять всю необходимую информацию о запросе, 
в HttpServletResponse можем записать наш ответ и установить необходимые заголовки.  

Если в сервлете в маппинге  указать звездочку - @WebServlet("/hello/*"), то этим сервлетом будут обрабатываться все запросы, начинающиеся с hello.  


Интерфейс, который релаизуют все сервлеты:  
```java
public interface Servlet {
    void init(ServletConfig var1) throws ServletException;

    ServletConfig getServletConfig();

    void service(ServletRequest var1, ServletResponse var2) throws ServletException, IOException;

    String getServletInfo();

    void destroy();
}
```  
+ init(ServletConfig var1) вызывается один раз при первом обращении к сервлету
+ void service(ServletRequest var1, ServletResponse var2) - вызывается при каждом обращении к сервлету  
ServletRequest содержит все параметры, полученные от пользователя (куки, хедеры, боди), ServletResponse - содержит все, что мы хотим отправить в ответ.  
Он распределяет и вызывает внутри себя конкретные методы: гет, пост итд. В нем также можно добавить логику, она будет вызываться при каждом запросе.      
+ destroy - вызывается перед завершением сервера  

Т.е. сначала вызавается инит, потом сервис, в нем вызывается конкретный метод, например doGet, сервис заверщает выполнение, и вызывается дестрой.

Далее мы переопределяем методы гет, пут, пост итд.      

## Что такое контейнер сервлетов  
[подробнее](https://javarush.ru/groups/posts/2533-chastjh-6-konteynerih-servletov)  
[letscode-ютуб-видео](https://www.youtube.com/watch?v=Jnd4PQt44j0&ab_channel=letsCode)    
Это программа, которая запускается на сервере и умеет взаимодействовать с созданными нами сервлетами. 
Иными словами, если мы хотим запустить наше веб-приложение на сервере, мы сначала разворачиваем контейнер сервлетов, 
а потом помещаем в него сервлеты.  
Схема работы проста: когда клиент обращается на сервер, контейнер обрабатывает его запрос, определяет,
какой именно сервлет должен его обработать и передает его.  

Кроме маршрутизации запросов, контейнер сервлетов выполняет и другие функции:   
+ Динамически генерирует HTML-страницы с JSP-файлов.  
+ Зашифровывает/расшифровывает HTTPS-сообщения.  
+ Предоставляет разграниченный доступ для администрирования сервлетов.  

Также неплохой контейнер сервлетов Jetty можно посмотреть по ссылке с ютуба.  
```xml
 <plugin>
                <groupId>org.eclipse.jetty</groupId>
                <artifactId>jetty-maven-plugin</artifactId>
                <version>9.4.20.v20190813</version>
                <configuration>
                    <scanIntervalSeconds>10</scanIntervalSeconds>
<!-- на одном урл может быть несколько разных приложений, у которых будут сервлеты с одинаковым маппингом,
но по этой части пути они будут различаться. Все маппинги из этого модуля мавен будут конкатенировать к my-app -->
                    <webApp>
                        <contextPath>/my-app</contextPath>
                    </webApp>
                </configuration>
            </plugin>
```  
Запускаем - maven - jetty - jetty run.  
см. пример - C:\projects\servlet_demo  

## JSP  
[подробнее](https://www.youtube.com/watch?v=nTyrXZOINqk&ab_channel=letsCode)    
Java Server Pages - шаблонизатор из мира Java EE, транслирующий динамические страницы в Java Servlet.  
JSP - это по сути сервлет, в котормо можно создавать динамические html страницы с java кодом.     

Как и в обычном сервлете, при переходе на сттраницу jsp, мы можем получить request и response, и вызывать методы из них, 
например, поулчить URI:
<%= request.getRequestURI() %>
благодаря знаку равно полученнео значение выводится на странице.  
Пример (C:\projects\servlet_demo\servlet\src\main\webapp\first-jsp.jspp):      
  
```java
<%@ page import="java.time.LocalDateTime" %> // также можно импортировать классы
<%@ page import="java.util.List" %>
<%@ page import="letscode.User" %>

<!doctype html>
<html>
<head>
    <title>First JSP</title>
</head>
<body>
<h1>Hello JSP</h1>
<%
    response.getWriter().write("First message"); // метод response.getWriter().write("") - пишет сразу в поток вывода и выводить в самом начале страницы
    out.print("right here, after header"); //out.print выводит в текущем порядке
%>
<br/>
<%= request.getRequestURI() %>
<br>
<%= LocalDateTime.now() %>
<hr/>
<%
    List<User> users = (List) request.getAttribute("users");
    User anUser = users.get(0);
%>
    <b><%= anUser.getName() %></b>
    <i><%= anUser.getCountry() %></i>
    <u><%= anUser.getAge() %></u>

<table>
<% for (User user : users) {%>
    <tr>
       <td><%= user.getName() %></td>
       <td>
           <% if (user.getCountry().equals("Vietnam")) { %>
                <b><%= user.getCountry() %></b>
           <% } else { %>
                <%= user.getCountry() %>
           <% } %>
       </td>
       <td><%= user.getAge() %></td>
    </tr>
<% } %>
</table>
</body>
</html>
```     

В конечном итоге jsp компилируется в обычный сервлет.  
Обычно джава-логику в jsp сервлете писать не принято, мы создаем  обычный сервлет, в нем реализуем нужную логику, создаем атрибут для них,  
и передаем эти данные перенаправляя исходный сервлет с установленными атрибутами на jsp сервлет.  
Пример:  
```java
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/temp-serv")
public class JspServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ArrayList<User> users = new ArrayList<User>() {{ // подготавливаем данные для jsp сервлета
            add(new User("Igor", "Vietnam", 24));
            add(new User("Mike", "Thailand", 33));
            add(new User("Joe", "Bali", 55));
        }};

        req.setAttribute("users", users); // устанавливаем наши данные для jsp сервлета

        getServletContext().getRequestDispatcher("/first-jsp.jsp").forward(req, resp); //перенапраялем запрос на этот jsp сервлет
    }
}
```   
Вывести можно атрибут следующим образом:  
<%
    List<User> users = (List) request.getAttribute("users");
    User anUser = users.get(0);
    ou.print(anUser.getname());
%> 

Использование цикла:  
```java
<% for (User user : users) {%>
    <tr>
       <td><%= user.getName() %></td>
       <td>
           <% if (user.getCountry().equals("Vietnam")) { %>
                <b><%= user.getCountry() %></b>
           <% } else { %>
                <%= user.getCountry() %>
           <% } %>
       </td>
       <td><%= user.getAge() %></td>
    </tr>
<% } %>
```  

**JSTL - стандартную библиотеку тэгов JSP (JSP standart tag library) - набор тэгов для упрощения создания динамических JSP страниц поверх Java Servlet**  