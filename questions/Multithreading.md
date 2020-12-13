Источники: 
https://javarush.ru/groups/posts/1128-perevod-top-50-intervjhju-voprosov-po-nitjam-chastjh-1 
1. https://habr.com/ru/post/164487/
2. https://metanit.com/java/tutorial/8.4.php
3. https://javarush.ru/groups/posts/1993-mnogopotochnostjh-chto-delajut-metodih-klassa-thread
4. https://javarush.ru/groups/posts/2065-threadom-java-ne-isportishjh--chastjh-iv---callable-future-i-druzjhja  
https://habr.com/ru/company/luxoft/blog/157273/

## Общее  
Процесс — это совокупность кода и данных, разделяющих общее виртуальное адресное пространство.  
При помощи процессов выполнение разных программ изолировано друг от друга: 
каждое приложение использует свою область памяти, не мешая другим программам.  

## Планировщик потоков  
Планировщик потоков – это часть JVM, которая решает какой поток должен выполнится в каждый конкретный момент времени и какой поток нужно приостановить.  

В Java есть Планировщик Потоков(Thread Scheduler), который контролирует все запущенные потоки во всех программах и решает, 
какие потоки должны быть запущены, и какая строка кода выполняться. Существует две характеристики потока, 
по которым планировщик идентифицирует процесс. Первая, более важная, это приоритет потока, другая, является-ли поток демоном(daemon flag). 
Простейшее правило планировщика, это если запущены только daemon потоки, то Java Virtual Machine (JVM) вызгрузится. 
Новые потоки наследуют приоритет и daemon flag от потока, который его создал. Планировщик определяет какой поток должен быть запущен, 
анализируя приоритет всех потоков. Потоку с наивысшим приоритетом позволяется выполниться раньше, нежели потокам с более низкими приоритетами.  
  
Действия при переключении контекста:  
+ Сохранение контекста потока, закончившего выполнение.
+ Помещение этого потока в конец очереди, соответствующей его приоритету.
+ Загружает контекст потока из очереди готовых к выполнению с наибольшим приоритетом.
+ Удаляет загруженный поток из очереди и начинает его выполнять.  

Правила, определяющие когда должно происходить переключение контекста  
+ Закончился выделенный планировщиком квант времени.
+ Поток может добровольно уступить управление. Для этого достаточно явно уступить очередь на исполнение, 
приостановить или блокировать поток на время ожидания ввода вывода.
+ Один поток исполнения может быть вытеснен другим, более приоритетным потоком.  

Методы позволяющие влиять на планировщика потоков  
Методы класса java.lang.Thread:    
+ public static void sleep(long millis) throws InterruptedException
+ public static void yield()
+ public final void join() throws InterruptedException
+ public final void setPriority(int newPriority)  

## Потоки, создающиеся при старте программы по умолчанию  
Список всех потоков можно получить в дебаггере или любом профайлере типа jСonsole    
+ main  
"Основной" поток - это поток, созданный для запуска вашего main метода.
+ Attach Listener  
"Слушатель присоединения" создается JVM для приема соединений с агентом отладки JVM.
+ Common Cleaner  
Поток "Common Cleaner" относится к механизму Cleaner, который является лучшим способом для удаления объектов при удалении объектов.
(связан с фантомными ссылками)  
Можно добавить действие (по очистке ресурсов объекта) перед удалением.
+ Finalizer  
Поток "Finalizer" запускает методы finalize для недоступных объектов, поставленных в очередь потоком GC.
+ Reference Handler  
Поток "Reference Handler" выполняет обработку объектов Reference поставленных в очередь GC. 
+ Signal Dispatcher  
Поток "Диспетчер сигналов" обрабатывает собственные сигналы (например, SIGINT, SIGHUP и т.д.).
По-видимому, они должны обрабатываться выделенным (собственным) потоком из-за того, как работают связанные системные вызовы.  

В JVM также есть один или несколько собственных потоков GC, но, очевидно, они не отображаются в списке. 
Я предполагаю, что это потому, что у них нет соответствующего объекта Thread. (Они... особенные!)   

## Начало  
Планировщик решает, кто и как долго должен работать, а также что случается с потоками, когда они перестают выполняться.
Вы не можете управлять планировщиком. Для этого не предусмотрено соответствующих методов. Что еще более важно, 
процесс планирования потоков не дает вам гарантий (выполнение некоторых принципов почти гарантируется, но это очень неопределенно)!
Секрет кроется в приостановке. Приостанавливая поток даже на несколько миллисекунд, вы заставляете его изменить свое состояние и уступить место другому потоку. 
Метод sleep() гарантирует выполнение одного правила: приостановленный поток не вернется к работе, пока не истечет срок его приостановки. 
Например, если вы прикажете своему потоку остановиться на две секунды (2000 мс), он ни за что не продолжит свою работу до истечения этого времени 
(но это не значит, что он возобновится сразу через две секунды). 
Метод sleep выбрасывает проверяемое исключение (InterruptedException), поэтому все его вызовы должны быть заключены в блоки try/catch либо объявлены.  

Можно ли повторно использовать объект Thread? Можно ли дать ему новое задание и опять вызвать метод start()?
• Нет. Если метод run() завершил свою работу, то поток уже нельзя запустить. Фактически, в этот момент поток входит в состояние, о котором мы еще не упоминали, 
— состояние смерти. Несмотря на то что объект Thread навсегда потерял свою «поточность», он по-прежнему может находиться в куче, 
и у вас есть возможность вызывать другие его методы (если ситуация позволяет). 
Иными словами, у Thread больше нет отдельного стека вызовов, это уже не поток. Это просто такой же объект, как и все остальные.
Существует шаблон проектирования для создания пула потоков, которым вы можете воспользоваться для выполнения разных заданий. Но он не перезапускает мертвые потоки.  

+ Установить имя потока:
alpha. setName ("поток альфа");
+ Получить имя текущего потока:
String threadName = Thread.currentThread().getName();
+ Чтобы метод мог выполняться одновременно только в одном потоке, используйте ключевое слово synchronized.  

## Запуск потоков:  
Способ 1  
Создать объект класса Thread, передав ему в конструкторе нечто, реализующее интерфейс Runnable. Этот интерфейс содержит метод run(), 
который будет выполняться в новом потоке. Поток закончит выполнение, когда завершится его метод run().  

Способ 2
Создать потомка класса Thread и переопределить его метод run().  

Асинхронность означает то, что нельзя утверждать, что какая-либо инструкция одного потока, выполнится раньше или позже инструкции другого. 
Или, другими словами, параллельные потоки независимы друг от друга, за исключением тех случаев, 
когда программист сам описывает зависимости между потоками с помощью предусмотренных для этого средств языка.  

Способ 3  
Callable, Future  
http://java-online.ru/concurrent-callable.xhtml    
https://javadevblog.com/java-callable-kratkoe-opisanie-i-primer-ispol-zovaniya.html  

**Callable <V>**  
Задача, которая возвращает результат и может вызвать исключение. Объявляет метод call, который возвращает результат. 
Кроме того, по умолчанию он throws Exception. То есть избавляет нас от необходимости на проверяемые исключения писать try-catch блоки.  

Интерфейс Future<V>    
A  Future represents the result of an asynchronous computation.  
Методы интерфейса можно использовать для проверки завершения работы потока, ожидания завершения и получения результата. 
Результат выполнения может быть получен методом get, если поток завершил работу. Прервать выполнения задачи можно методом cancel. 
 
## Интерфейс Executor  
https://javarush.ru/groups/posts/2078-threadom-java-ne-isportishjh--chastjh-v---executor-threadpool-fork-join-pool    
Используется для execute (т.е. выполнения) некой задачи (runnable) в потоке, когда реализация создания потока скрыта от нас.  
Т.е.  Executor - это объект, который запускает выполнение таски (новом потоком, пулом потоков итд)   

```java
public interface Executor {
    /**
     * Executes the given command at some time in the future.  The command
     * may execute in a new thread, in a pooled thread, or in the calling
     * thread, at the discretion of the {@code Executor} implementation.
     */
    void execute(Runnable command);
} 
```
 

```java
public static void main(String []args) throws Exception {
	Runnable task = () -> System.out.println("Task executed");
	Executor executor = (runnable) -> {
		new Thread(runnable).start();
	};
	executor.execute(task);
}
```  

У нас есть Executor для execute (т.е. выполнения) некой задачи в потоке. 
У нас есть ExecutorService — особый Executor (наследник), который имеет набор возможностей по управлению ходом выполнения. 
И у нас есть фабрика Executors, которая позволяет создавать ExecutorService.     

## Интерфейс ExecutorService  
ExecutorService является описанием особого Executor'а, который предоставляет методы по остановке работы Executor'а 
и позволяет получить Future, чтобы отслеживать процесс выполнения.  

<T> Future<T> submit(Callable<T> task);    
Отправляет задачу, возвращающую значение, на выполнение экзекьютора и возвращает Future. 
Метод Future get вернет результат задачи после успешного выполнения.  

## Executors  
Позволяет создавать доступные по умолчанию реализации ExecutorService.    
Класс Executors (фабрика для ExecutorService) предоставляет полезные методы для выполнения Callable в пуле потоков. 
Callable таски (задачи) возвращают Future объект. Используя Future мы можем узнать статус Callable таска и получить возвращенный объект. 
Это обеспечивает get() метод, который ждет завершение Callable, чтобы вернуть результат.    
Пример:  
 
```java
public static void main(String[] args) throws ExecutionException, InterruptedException {
	Callable<String> task = () -> Thread.currentThread().getName();
	ExecutorService service = Executors.newFixedThreadPool(2);
	for (int i = 0; i < 5; i++) {
		Future result = service.submit(task);
		System.out.println(result.get());
	}
	service.shutdown();
}
```
Основные шаги:   
ExecutorService executor = Executors.newFixedThreadPool(10); // создаем пул потоков, который будет выполнять наши таски    
Callable<String> callable = new MyCallable(); //создаем нашу таску  
Future<String> future = executor.submit(callable); // получаем Future, который будет содержать результат таски  
future.get() - получаем результат такси, при этом ждем окончания ее выполнения в потоке.  

## CompletableFuture  
https://sysout.ru/completablefuture/    
https://annimon.com/article/3462  

CompletableFuture наследуется от Future и предлагает много дополнительной функциональности. Можно сосотавлять цепочки из тасок, можно 
обрабатывать результат одной таски в другой итд. Можно разными способами комбинировать выполнение тасок в функциональном стиле.  

   
```java
private void asyncRun() throws ExecutionException, InterruptedException {
        final CompletableFuture<String> future = CompletableFuture.supplyAsync(()-> {
            System.out.println("job started");
            sleep(3);
            System.out.println("job done");
            return "feature done";
        });
        System.out.println("waiting...");
        String result = future.get();
        System.out.println("finished, result:" + result);
    }
```   

В этом коде используется интересная возможность CompletableFuture из пакета java.util.concurrent. 
CompletableFuture «умеет» запускать код в параллельном потоке, и при этом использует встроенный thread pool. 
Его использование упрощает код и даёт прирост в производительности приложения.  

Метод get() блокирует текущий поток. Обычно нам требуется другое. Надо не блокировать текущий поток для получения значения, 
а задать функцию, которая сделает что-то со значением сразу после того, как оно будет вычислено, в том же параллельном потоке. 
Так называемую callback-функцию. 
thenApply() - мы можем не блокируя поток, получить из предыдущего CompletableFuture результат, используя его запустить еще 
CompletableFuture и обработать этот результат в том же потоке.   
Допустим, нам надо закончить вычисления в supplyAsync() и далее сделать что-то с результатом, не блокируя текущие поток. 
Вот supplyAsync():
```java
// Create a CompletableFuture
CompletableFuture<String> whatsYourNameFuture = CompletableFuture.supplyAsync(() -> {
   try {
       TimeUnit.SECONDS.sleep(1);
   } catch (InterruptedException e) {
       throw new IllegalStateException(e);
   }
   System.out.println(Thread.currentThread().getName());
   return "Rajeev";
});

// Attach a callback to the Future using thenApply()
CompletableFuture<String> greetingFuture = whatsYourNameFuture.thenApply(name -> {
    System.out.println(Thread.currentThread().getName());    
    return "Hello " + name;
});
System.out.println(Thread.currentThread().getName()+" "+go further");
// Block and wait for the future to complete
System.out.println(Thread.currentThread().getName()+" "+greetingFuture.get());
```
  

CompletableFuture есть ещё интересные функции. Например, надо построить цепочку из асинхронных вызовов. 
Т.е. после завершения первой асинхронной функции запустить вторую, после второй третью и т.д.  
```java
private void thenApply() throws ExecutionException, InterruptedException {
        final CompletableFuture<String> future = CompletableFuture.supplyAsync(()-> {
            System.out.println("job started");
            sleep(3);
            System.out.println("job done");
            return "feature done|";
        }).thenApply(result -> {
            System.out.println("applay result:" + result);
            return result + " applied";
        });
        System.out.println("waiting...");
        String result = future.get();
        System.out.println("finished, result:" + result);
    }
```  
Сначала выполняется первая задача (sleep(3)) и только после её завершения запустится вторая задача.
Причём второй задаче в качестве входного параметра можно передать результаты первой задачи.  
  
 
   

   
 
## ThreadPoolExecutor  
https://javarush.ru/groups/posts/2078-threadom-java-ne-isportishjh--chastjh-v---executor-threadpool-fork-join-pool    
ExecutorService that executes each submitted task using one of possibly several pooled threads, normally configured
using Executors factory methods.  
```java
public static void main(String[] args) throws ExecutionException, InterruptedException {
	int threadBound = 2;
	ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(0, threadBound,
            0L, TimeUnit.SECONDS, new SynchronousQueue<>());
	Callable<String> task = () -> {
		Thread.sleep(1000);
		return Thread.currentThread().getName();
	};
	for (int i = 0; i < threadBound + 1; i++) {
		threadPoolExecutor.submit(task);
	}
	threadPoolExecutor.shutdown();
}
```
 
Внутри фабричных методов Executors в основном создаётся ThreadPoolExecutor. 
На функциональность влияет то, какие значения переданы в качестве максимума и минимума потоков, а также какая очередь для тасок используется.    

Говоря о ThreadPoolExecutor'ах, стоит отметить интересные особенности при работе.
Например, нельзя посылать задачи в ThreadPoolExecutor, если там нет места.  

 
## ScheduledThreadPoolExecutor
Кроме того, ThreadPoolExecutor имеет интересного наследника — ScheduledThreadPoolExecutor, который является ScheduledExecutorService.
Он предоставляет возможность выполнять задачу по таймеру.  
```java
public static void main(String[] args) {
	ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(4);
	Callable<String> task = () -> {
		System.out.println(Thread.currentThread().getName());
		return Thread.currentThread().getName();
	};
	scheduledExecutorService.schedule(task, 1, TimeUnit.MINUTES);
	scheduledExecutorService.shutdown();
}
```  
    
## WorkStealingPool  
Если кратко, то Work Stealing — это такой алгоритм работы, при котором простаивающие потоки 
начинают забирать задачи других потоков или задачи из общей очереди.  
помните, что cachedThreadPool создавал на каждую задачу свой поток? Потому что wait блокировал поток, 
а следующие задачи хотели выполнятся и в пуле для них создавались новые потоки. В случае со StealingPool потоки не будут вечно простаивать в wait, 
они начнут выполнять соседние задачи.  
Чем так отличается от остальных тредпулов этот WorkStealingPool? Тем, что внутри него живёт на самом деле волшебный ForkJoinPool.

## Fork/Join Pool (его ещё называют fork/join framework)
https://coderlessons.com/articles/java/fork-join-framework 
https://habr.com/ru/post/128985/  
  
ForkJoinPool – специальный вид ExecutorService (пула потоков). Предназначен для выполнения рекурсивных задач.

Задача для сервиса представляется экземпляром класса ForkJoinTask. 
В основном используются подклассы RecursiveTask и RecursiveAction, 
для задач с результатом и без соответственно. Аналогично интерфейсам Callable и Runnable обычного ExecutorService.

Тело рекурсивной операции задается в реализации метода compute() задачи ForkJoinTask. 
Здесь же создаются новые подзадачи, и запускаются параллельно методом fork(). 
Чтобы дождаться завершения выполнения задачи, на каждой форкнутой подзадаче вызывается блокирующий метод join(), 
результат выполнения при необходимости агрегируется.

С точки зрения использования метод ForkJoinTask.join() похож на аналогичный метод класса Thread. 
Но в случае fork-join поток может на самом деле не заснуть, а переключиться на выполнение другой задачи.
Такая стратегия называется work stealing, и позволяет эффективнее использовать ограниченное количество потоков.  

В то время как вызов fork() запускает асинхронное выполнение задачи, вызов join() будет ожидать завершения задачи и извлекать ее результат. 
Следовательно, мы можем разбить данную задачу на несколько небольших задач, разветвить каждую задачу и, наконец, дождаться завершения всех задач. 
Это облегчает реализацию сложных задач.    

## ForkJoinTask  
Abstract base class for tasks that run within a ForkJoinPool.
A ForkJoinTask is a thread-like entity that is much lighter weight than a normal thread.  
ForkJoinTask реализует уже известный интерфейс Future и вместе с тем такие методы, как get() , cancel() и isDone() . 
Помимо этого этот класс также предоставляет два метода, которые дали всей платформе свое имя: fork() и join() .  
 
## Методы  
+  boolean isAlive();  
Tests if this thread is alive. A thread is alive if it has been started and has not yet died.  

InterruptedException extends Exception
Thrown when a thread is waiting, sleeping, or otherwise occupied, and the thread is interrupted, either before or during the activity. 
Occasionally a method may wish to test whether the current thread has been interrupted, and if so, to immediately throw this exception. 
The following code can be used to achieve this effect:  
if (Thread.interrupted())  // Clears interrupted status!  
throw new InterruptedException();   
  
## Метод Thread.sleep()  
public static native void sleep(long millis) throws InterruptedException;   
Thread.sleep() — статический метод класса Thread, который приостанавливает выполнение потока, в котором он был вызван. 
Во время выполнения метода sleep() система перестает выделять потоку процессорное время, распределяя его между другими потоками. 
Метод sleep() может выполняться либо заданное кол-во времени (миллисекунды или наносекунды) либо до тех пор пока он не будет остановлен прерыванием 
(в этом случае он сгенерирует исключение InterruptedException).

Thread.sleep(1500);  

## Метод yield()  
Подсказка планировщику о том, что текущий поток готов уступить его текущее использование процессора. 
Планировщик волен игнорировать это подсказка.  
Статический метод Thread.yield() заставляет процессор переключиться на обработку других потоков системы. 
Метод может быть полезным, например, когда поток ожидает наступления какого-либо события и необходимо чтобы проверка его наступления происходила как можно чаще. 
В этом случае можно поместить проверку события и метод Thread.yield() в цикл:

```java
//Ожидание поступления сообщения
while(!msgQueue.hasMessages())		//Пока в очереди нет сообщений
{
	Thread.yield();		//Передать управление другим потокам
}
```    
yield лишь передаёт некоторую рекомендацию планировщику потоков Java, что данному потоку можно дать меньше времени исполнения.  
Но что будет на самом деле, услышит ли планировщик рекомендацию и что вообще он будет делать — зависит от реализации JVM и операционной системы.  
  
## Метод join()
В Java предусмотрен механизм, позволяющий одному потоку ждать завершения выполнения другого.  
Например, чтобы главный поток подождал завершения побочного потока myThready, необходимо выполнить инструкцию myThready.join() в главном потоке.  

Метод join() имеет перегруженную версию, которая получает в качестве параметра время ожидания. 
В этом случае join() возвращает управление либо когда завершится ожидаемый поток, либо когда закончится время ожидания.    

## Завершение процесса и демоны
В Java процесс завершается тогда, когда завершается последний его поток. Даже если метод main() уже завершился, 
но еще выполняются порожденные им потоки, система будет ждать их завершения.  

Однако это правило не относится к особому виду потоков – демонам. Если завершился последний обычный поток процесса, 
и остались только потоки-демоны, то они будут принудительно завершены и выполнение процесса закончится. 
Чаще всего потоки-демоны используются для выполнения фоновых задач, обслуживающих процесс в течение его жизни.  

Объявить поток демоном достаточно просто — нужно перед запуском потока вызвать его метод setDaemon(true);
Проверить, является ли поток демоном, можно вызвав его метод boolean isDaemon();   

Такие потоки иногда ещё называются “службами”, которые обычно запускаются с наименьшим приоритетом и обеспечивают основные услуги для программы или программ, 
когда деятельность компьютера понижается. Примером такого потока может служить сборщик мусора.   

## Приоритеты потоков  
Каждый поток в системе имеет свой приоритет. Приоритет – это некоторое число в объекте потока, более высокое значение которого означает больший приоритет. 
Система в первую очередь выполняет потоки с большим приоритетом, а потоки с меньшим приоритетом получают процессорное время только тогда, 
когда их более привилегированные собратья простаивают.  

Работать с приоритетами потока можно с помощью двух функций:  

void setPriority(int priority) – устанавливает приоритет потока.  
Возможные значения priority — MIN_PRIORITY, NORM_PRIORITY и MAX_PRIORITY.  

int getPriority() – получает приоритет потока.  

## Завершение и прерывание потока  
https://metanit.com/java/tutorial/8.4.php  
Метод interrupt:      
Однако при получении статуса потока с помощью метода isInterrupted() следует учитывать,  что если мы 
обрабатываем в цикле исключение InterruptedException в блоке catch,  то при перехвате исключения статус потока автоматически сбрасывается,
и после этого isInterrupted будет возвращать false.  

Когда поток вызовет метод interrupt, метод sleep сгенерирует исключение InterruptedException, и управление перейдет к блоку catch. 
Но если мы проверим статус потока, то увидим, что метод isInterrupted возвращает false. Как вариант, 
в этом случае мы можем повторно прервать текущий поток, опять же вызвав метод interrupt(). 
Тогда при новой итерации цикла while метода isInterrupted возвратит true, и поизойдет выход из цикла.

**Либо мы можем сразу же в блоке catch выйти из цикла с помощью break.**    
  
## Синхронизация потоков. Оператор synchronized  
Когда два или более потоков имеют доступ к одному разделенному ресурсу, они нуждаются в обеспечении того, 
что ресурс будет использован только одним потоком одновременно. Процесс, с помощью которого это достигается, называется синхронизацией.  
  
Одним из способов синхронизации является использование ключевого слова synchronized. 
Этот оператор предваряет блок кода или метод, который подлежит синхронизации. 

## Статическая синхронизация  
Если метод, в котором содержится критически важная «многопоточная» логика, статический, синхронизация будет осуществляться по классу.  
Т.о. мы синхронизируем объект класса (наш_класс.class), не позволяя разным потокам одновременно изменять его статические поля.    
```java
class MyClass {
   private static String name1 = "Оля";
   private static String name2 = "Лена";

   public static void swap() {

       synchronized (MyClass.class) {
           String s = name1;
           name1 = name2;
           name2 = s;
       }
   }

}
``` 

## В чем разница между мьютексом, монитором и семафором  
https://javarush.ru/groups/posts/2174-v-chem-raznica-mezhdu-mjhjuteksom-monitorom-i-semaforom   
С каждым объектом ассоциирован некоторый монитор, а потоки могут его заблокировать "lock" или разблокировать "unlock".  
Мьютекс — это специальный объект для синхронизации потоков. Он «прикреплен» к каждому объекту в Java.  
У мьютекса есть несколько важных особенностей.  
Во-первых, возможны только два состояния — «свободен» и «занят».   
Во-вторых, состояниями нельзя управлять напрямую. В Java нет механизмов, которые позволили бы явно взять объект, 
получить его мьютекс и присвоить ему нужный статус. Прямой доступ к нему есть только у Java-машины. 
Программисты же работают с мьютексами с помощью средств языка. 
 
## Монитор  
https://javarush.ru/groups/posts/2174-v-chem-raznica-mezhdu-mjhjuteksom-monitorom-i-semaforom  
Как мы помним, каждый объект в Java имеет заголовок. В заголовке содержится различная служебная информация, 
в том числе и информация о мониторе — данные о состоянии блокировки. И как мы помним, каждый объект (т.е. каждый instance) 
имеет ассоциацию с внутренней сущностью JVM, называемой локом (intrinsic lock), который так же называют монитором.
 
Несколько нитей могут мешать друг другу при обращении к одним и тем же данным. Для решения этой проблемы придуман мьютекс (он же монитор). 
Он имеет два состояния – объект занят и объект свободен. Монитор(мьютекс) — высокоуровневый механизм взаимодействия и синхронизации потоков, 
обеспечивающий доступ к неразделяемым ресурсам.
Когда какой-то нити нужен общий для всех нитей объект, она проверяет мьютекс, связанный с этим объектом. 
Если мьютекс свободен, то нить блокирует его (помечает как занятый) и начинает использование общего ресурса. 
После того, как она сделала свои дела, мьютекс разблокируется (помечается как свободен).

Если же нить хочет использовать объект, а мьютекс заблокирован, то нить засыпает в ожидании. 
Когда мьютекс, наконец, освободится занятой нитью, наша нить тут же заблокирует его и приступит к работе.
Мьютекс встроен в класс Object и следовательно он есть у каждого объекта.
Когда одна нить заходит внутрь блока кода, помеченного словом synchronized, то Java-машина тут же блокирует мьютекс у объекта, 
который указан в круглых скобках после слова synchronized. Больше ни одна нить не сможет зайти в этот блок, пока наша нить его не покинет. 
Как только наша нить выйдет из блока, помеченного synchronized, то мьютекс тут же автоматически разблокируется и будет свободен для захвата другой нитью. 
Если же мьютекс был занят, то наша нить будет стоять на месте и ждать когда он освободится.  

## Блокировка
Если поток пытается зайти в синхронизированный метод, а монитор уже захвачен, то поток блокируется по монитору объекта.
Поток попадает в специальный пул для этого конкретного объекта и должен находиться там пока монитор не будет освобожден. 
После этого поток возвращается в состояние runnable.  

## Методы wait и notify  
https://metanit.com/java/tutorial/8.5.php  

Иногда при взаимодействии потоков встает вопрос о извещении одних потоков о действиях других. 
Например, действия одного потока зависят от результата действий другого потока, и надо как-то известить один поток, 
что второй поток произвел некую работу. И для подобных ситуаций у класса Object определено ряд методов:  

+ wait(): освобождает монитор и переводит вызывающий поток в состояние ожидания до тех пор, пока другой поток не вызовет метод notify()
+ notify(): продолжает работу потока, у которого ранее был вызван метод wait()
+ notifyAll(): возобновляет работу всех потоков, у которых ранее был вызван метод wait()  

Все эти методы вызываются только из синхронизированного контекста - синхронизированного блока или метода.  

Эти методы находятся в классе Object, т.к. монитором блокировки может служить любой объект, 
поэтому логично определить эти методы в корне иерархии, т.е. в классе Object.   

## Семафоры  
https://metanit.com/java/tutorial/8.6.php  
Дозированный доступ потоков к определенным ресурсам.  
  
Разница только в том, что мьютекс объекта может захватить одновременно только один поток, 
а в случае с семафором используется счетчик потоков, и доступ к ресурсу могут получить сразу несколько из них.  
(На самом деле мьютекс — это одноместный семафор. То есть, это семафор, счетчик которого изначально установлен в значении 1)    
void acquire() throws InterruptedException  
- Для получения одного разрешения применяется первый вариант, а для получения нескольких разрешений - второй вариант.
После вызова этого метода пока поток не получит разрешение, он блокируется.  
  
После окончания работы с ресурсом полученное ранее разрешение надо освободить с помощью метода release():  
```java
import java.util.concurrent.Semaphore;
 
public class Program {
 
    public static void main(String[] args) {
         
        Semaphore sem = new Semaphore(1); // 1 разрешение
        CommonResource res = new CommonResource();
        new Thread(new CountThread(res, sem, "CountThread 1")).start();
        new Thread(new CountThread(res, sem, "CountThread 2")).start();
        new Thread(new CountThread(res, sem, "CountThread 3")).start();
    }
}
class CommonResource{
     
    int x=0;  
}
 
class CountThread implements Runnable{
 
    CommonResource res;
    Semaphore sem;
    String name;
    CountThread(CommonResource res, Semaphore sem, String name){
        this.res=res;
        this.sem=sem;
        this.name=name;
    }
      
    public void run(){
         
        try{
            System.out.println(name + " ожидает разрешение");
            sem.acquire();
            res.x=1;
            for (int i = 1; i < 5; i++){
                System.out.println(this.name + ": " + res.x);
                res.x++;
                Thread.sleep(100);
            }
        }
        catch(InterruptedException e){System.out.println(e.getMessage());}
        System.out.println(name + " освобождает разрешение");
        sem.release();
    }
}
```          
         
## Обмен между потоками. Класс Exchanger  
https://metanit.com/java/tutorial/8.7.php 
https://habr.com/ru/post/277669/  
 
Класс Exchanger предназначен для обмена данными между потоками.  
Обмен данными производится с помощью единственного метода этого класса exchange():  
V exchange(V x) throws InterruptedException  
V exchange(V x, long timeout, TimeUnit unit) throws InterruptedException, TimeoutException  
- Ожидает прибытия другого потока в эту точку обмена, а затем передает ему данный объект, получая взамен свой объект.  

exchange (V x, long timeout, TimeUnit unit) — при вызове этой функции текущий поток приостанавливает выполнение и ожидает, 
пока другой поток вызовет свой метод exchange. Когда другой поток вызывает свой метод обмена, потоки обмениваются своими данными, 
и выполнение возобновляется. Поток ожидает только продолжительность, указанную в аргументе timeout, 
и в случае истечения времени ожидания генерируется исключение TimeoutException.     
  
Обменник является точкой синхронизации пары потоков: поток, вызывающий у обменника метод exchange() 
блокируется и ждет другой поток. Когда другой поток вызовет тот же метод, произойдет обмен объектами: 
каждая из них получит аргумент другой в методе exchange(). Стоит отметить, что обменник поддерживает передачу null значения.
Это дает возможность использовать его для передачи объекта в одну сторону, или, просто как точку синхронизации двух потоков.  

## Класс Phaser  
https://metanit.com/java/tutorial/8.8.php  
https://habr.com/ru/post/277669/    

+ int arriveAndAwaitAdvance() — указывает что поток завершил выполнение фазы. 
Поток приостанавливается до момента, пока все остальные стороны не закончат выполнять данную фазу. Возвращает номер текущей фазы;    
+ int arrive() — сообщает, что сторона завершила фазу, и возвращает номер фазы. 
При вызове данного метода поток не приостанавливается, а продолжает выполнятся;  
    
```java
import java.util.concurrent.Phaser;
 
public class Program {
 
    public static void main(String[] args) {
         
        Phaser phaser = new Phaser(1);
        new Thread(new PhaseThread(phaser, "PhaseThread 1")).start();
        new Thread(new PhaseThread(phaser, "PhaseThread 2")).start();
         
        // ждем завершения фазы 0
        int phase = phaser.getPhase();
        phaser.arriveAndAwaitAdvance();
        System.out.println("Фаза " + phase + " завершена");
        // ждем завершения фазы 1
        phase = phaser.getPhase();
        phaser.arriveAndAwaitAdvance();
        System.out.println("Фаза " + phase + " завершена");
         
        // ждем завершения фазы 2
        phase = phaser.getPhase();
        phaser.arriveAndAwaitAdvance();
        System.out.println("Фаза " + phase + " завершена");
         
        phaser.arriveAndDeregister();
    }
}
 
class PhaseThread implements Runnable{
     
    Phaser phaser;
    String name;
 
    PhaseThread(Phaser p, String n){
         
        this.phaser=p;
        this.name=n;
        phaser.register();
    }
    public void run(){
         
        System.out.println(name + " выполняет фазу " + phaser.getPhase());
        phaser.arriveAndAwaitAdvance(); // сообщаем, что первая фаза достигнута
         
        System.out.println(name + " выполняет фазу " + phaser.getPhase());
        phaser.arriveAndAwaitAdvance(); // сообщаем, что вторая фаза достигнута
 
        System.out.println(name + " выполняет фазу " + phaser.getPhase());
        phaser.arriveAndDeregister(); // сообщаем о завершении фаз и удаляем с регистрации объекты 
    }
}
```  
 
## Блокировки. ReentrantLock  
https://metanit.com/java/tutorial/8.9.php  

Организация блокировки в общем случае довольно проста: для получения блокировки вызывается метод lock(),
а после окончания работы с общими ресурсами вызывается метод unlock(), который снимает блокировку.    
```java
import java.util.concurrent.locks.ReentrantLock;
 
public class Program {
  
    public static void main(String[] args) {
          
        CommonResource commonResource= new CommonResource();
        ReentrantLock locker = new ReentrantLock(); // создаем заглушку
        for (int i = 1; i < 6; i++){
              
            Thread t = new Thread(new CountThread(commonResource, locker));
            t.setName("Thread "+ i);
            t.start();
        }
    }
}
  
class CommonResource{
      
    int x=0;
}
  
class CountThread implements Runnable{
  
    CommonResource res;
    ReentrantLock locker;
    CountThread(CommonResource res, ReentrantLock lock){
        this.res=res;
        locker = lock;
    }
    public void run(){
         
        locker.lock(); // устанавливаем блокировку
        try{
            res.x=1;
            for (int i = 1; i < 5; i++){
                System.out.printf("%s %d \n", Thread.currentThread().getName(), res.x);
                res.x++;
                Thread.sleep(100);
            }
        }
        catch(InterruptedException e){
            System.out.println(e.getMessage());
        }
        finally{
            locker.unlock(); // снимаем блокировку
        }
    }
}
```   
## Жизненный цикл потока  
Когда поток только создан, то он имеет статус NEW. В
таком положении он ещё не запущен и планировщик потоков Java (Thread Scheduler) ещё не знает ничего о новом потоке.  

Для того, чтобы о потоке узнал планировщик потоков, необходимо вызвать метод thread.start(). 
Тогда поток перейдёт в состояние RUNNABLE. В интернете есть много неправильных схем, где разделяют состояния Runnable и Running.
Но это ошибка, т.к. Java не отличает статус "готов к работе" и "работает (выполняется)".

Когда поток жив, но не активен (не Runnable), он находится в одном из двух состояний:  
BLOCKED — ожидает захода в защищённую (protected) секцию, т.е. в synchonized блок.
WAITING — ожидает другой поток по условию. Если условие выполняется, планировщик потоков запускает поток.
Если поток ожидает по времени, он находится в статусе TIMED_WAITING.   

Если поток больше не выполняется (завершился успешно или с exception), он переходит в статус TERMINATED.  
  
New -> Runnable или BLOCKED|WAITING -> TERMINATED  

## Проблемы, которые создает многопоточность  
Есть две конкретные проблемы, которые может вызвать использование многопоточности — взаимная блокировка (deadlock) и состояние гонки (race condition).  
+ Deadlock — ситуация, при которой несколько потоков находятся в состоянии ожидания ресурсов, занятых друг другом, 
и ни один из них не может продолжать выполнение.
+ Состояние гонки — ошибка проектирования многопоточной системы или приложения, при которой работа системы или приложения зависит от того,
в каком порядке выполняются части кода.    

## Deadlock
https://javarush.ru/groups/posts/2060-threadom-java-ne-isportishjh--chastjh-iii---vzaimodeystvie  
Когда два и более потоков вечно ожидают друг друга — это называется Deadlock.
(когда потоки "зависают" на системном ожидании монитора)    

## Livelock
https://javarush.ru/groups/posts/2060-threadom-java-ne-isportishjh--chastjh-iii---vzaimodeystvie  
Livelock заключается в том, что потоки внешне как бы живут, но при этом не могут ничего сделать, т.к. условие, 
по которым они пытаются продолжить свою работу, не могут выполниться. По сути Livelock похож на deadlock,
но только потоки не "зависают" на системном ожидании монитора, а что-то вечно делают.
Запускаем 2 потока, тогда каждый получает по локу, и мы не можем выйти из цикла, потому что не можем получить оба лока одновременно.    

```java
try {
                while (!firstLocked || !secondLocked) {
                    firstLocked = first.tryLock(100, TimeUnit.MILLISECONDS);
                   Thread.currentThread().sleep(val * 1000);
                    secondLocked = second.tryLock(100, TimeUnit.MILLISECONDS);
                   Thread.currentThread().sleep(val * 1000);
                }
                first.unlock();
                second.unlock();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
```  

## Starvation  
Помимо блокировок (deadlock и livelock) есть ещё одна проблема при работе с многопоточностью — Starvation, или "голодание". 
От блокировок это явление отличается тем, что потоки не заблокированы, а им просто не хватает ресурсов на всех. 
Поэтому пока одни потоки на себя берут всё время выполнения, другие не могут выполниться.  

## Race condition
https://ru.wikipedia.org/wiki/%D0%A1%D0%BE%D1%81%D1%82%D0%BE%D1%8F%D0%BD%D0%B8%D0%B5_%D0%B3%D0%BE%D0%BD%D0%BA%D0%B8
  
Состояние гонки (англ. race condition), также конкуренция[1] — ошибка проектирования многопоточной приложения, 
при которой работа приложения зависит от того, в каком порядке выполняются части кода. 
Это явление заключается в том, что потоки делят между собой некоторый ресурс и код написан таким образом,
что не предусматривает корректную работу в таком случае.  

## ThreadGroup
https://docs.oracle.com/javase/8/docs/api/java/lang/ThreadGroup.html    
Класс java.lang.ThreadGroup представляет набор потоков. Он также может включать другие группы потоков. 
Группы потоков формируют дерево, в котором каждая группа потоков, кроме начальной группы потоков, имеет родителя.  
ThreadGroup используется для удобного управления сразу группой потоков. Например, можно узнатиь, сколько активных потоков 
в группе или вызвать interrupt для всех потоков в группе.     

Создаем группу ThreadGroup group1 = new ThreadGroup("GROUP 1");
Создаем поток с указанием этой группы new Thread(ThreadGroup group, Runnable target); - new Thread(group1, Runnable target);
  
Потоки объединяются в группы потоков по соображениям улучшения управляемости и безопасности. 
Одна группа потоков может принадлежать другой группе, составляя иерархию с основной группой на верхнем уровне.
Потоки, относящиеся к группе, могут управляться единовременно – вы вправе прервать работу сразу всех потоков группы либо
установить для них единое максимальное значение приоритета выполнения.  

По умолчанию создаваемым потокам не присваивается определенный уровень защиты. 
В результате любой поток из любой группы может свободно контролировать и изменять потоки в других группах. 
Однако можно использовать абстрактный класс SecurityManager для указания ограничений доступа к определенным группам потоков. 
Для этого необходимо создать подкласс класса SecurityManager и заменить те методы, которые используются для защиты потоков.   

Для того, чтобы отдельный поток не мог начать останавливать и прерывать все потоки подряд, введено понятие группы. 
Поток может оказывать влияние только на потоки, которые находятся в одной с ним группе. Группу потоков представляет класс ThreadGroup. 
Такая организация позволяет защитить потоки от нежелательного внешнего воздействия.  

## ThreadPool  
Пул потоков повторно использует ранее созданные потоки для выполнения текущих задач и предлагает решение проблемы издержек цикла потоков и перерасхода ресурсов. 
Поскольку поток уже существует, когда приходит запрос, задержка, вызванная созданием потока, устраняется, что делает приложение более отзывчивым.  

Java обеспечивает среду Executor, которая сосредоточена вокруг интерфейса Executor, 
его подчиненного интерфейса — ExecutorService и класса ThreadPoolExecutor , который реализует оба этих интерфейса. 
Используя исполнителя, нужно только реализовать объекты Runnable и отправить их исполнителю для выполнения.  

Они позволяют вам использовать преимущества потоков, но сосредотачиваются на задачах, которые вы хотите, чтобы поток выполнял, а не на механике потоков.
Чтобы использовать пулы потоков, мы сначала создаем объект ExecutorService и передаем ему набор задач. 
Класс ThreadPoolExecutor позволяет установить ядро и максимальный размер пула. Runnables, 
которые запускаются определенным потоком, выполняются последовательно.  

## ThreadLocal  
https://urvanov.ru/2017/04/02/threadlocal-%D0%B2-java/    
Локальная переменная для потока.  
Это переменная, которую можно получить в любом месте потока. При этом создав ThreadLocal однажды, мы можем получить ThreadLocal для 
каждого потока, которая будет независимо инициализирована для каждого потока.   
  
Класс java.lang.ThreadLocal<T> используется для хранения переменных, которые должны быть доступны для всего потока. 
Фактически это нечто вроде ещё одной области видимости переменных. Класс ThreadLocal  имеет методы get  и set, 
которые позволяют получить текущее значение и установить новое значение.  

Обычно экземпляры ThreadLocal  объявляются как приватные статические переменные в классе. 
Каждый поток получает из метода get своё значение и устанавливает через set тоже своё значение, изолированное от других потоков.  

private static ThreadLocal<String> threadLocal = new ThreadLocal<>();    
threadLocal.set("From main thread");  
System.out.println("fromMainThread: " + threadLocal.get());      

## Синхронизированные коллекции  
Vector, HashTable, Stack являются устаревшими коллекциями, и пишут что их не используют в виду синхронизированности их методов.  
+ Потокобезопасность не бесплатна  
+ Такие коллекции работают значительно медленее, чем не синхронизированные аналоги  

Новые синхронизированные коллекции используют более быстрый алгоритм, когда блокируется не вся коллекция целиком, 
а только часть (блок), поэтому Vector, HashTable, Stack не стоит использовать ни в многопоточности 
(они работают медленнее, чем новые коллекции), ни в однопоточном - они просто избыточны.  

## Volatile  
Volatile необходимо использовать для переменных, которые используются разными потоками. Это связано с тем, что значение переменной, 
объявленной без volatile, может кэшироваться отдельно для каждого потока, и значение из этого кэша может различаться для каждого из них. 
Объявление переменной с ключевым словом volatile отключает для неё такое кэширование и все запросы к переменной будут направляться непосредственно в память.  

Все действия выполняет процессор. Но результаты вычислений нужно где-то хранить. Для этого есть основная память и есть аппаратный кэш у процессора. 
Эти кэши процессора — своего рода маленький кусочек памяти для более быстрого обращения к данным, чем обращения к основной памяти.
Но у всего есть и минус: данные в кэше могут быть не актуальны (как в примере выше, когда значение флага не обновилось). 
Так вот, ключевое слово volatile указывает JVM, что мы не хотим кэшировать нашу переменную. Это позволяет увидеть актуальный результат во всех потоках.
 
Если мы объявляем в нашей программе какую-то переменную, со словом volatile это означает, что:  
+ Она всегда будет атомарно читаться и записываться. Даже если это 64-битные double или long.
+ Java-машина не будет помещать ее в кэш. Так что ситуация, когда 10 потоков работают со своими локальными копиями исключена.  

##  Атомарность  
http://java-online.ru/concurrent-atomic.xhtml  

Атомарные классы используются, когда нужно провести атомарные операции с данными, при этом обычно они более легковесны, 
чем использование блоикровок, т.к. используют оптимистическую блокировку (метод compareAndSet)    

Атомарные операции — это операции, которые нельзя разделить.  Операция называется атомарной, 
если её можно безопасно выполнять при параллельных вычислениях в нескольких потоках, 
не используя при этом ни блокировок, ни синхронизацию synchronized.   
  
Почему важна атомарность? В примере с инкрементом, если появится состояние гонки, в любой момент общий ресурс 
(т.е. общее значение) может внезапно измениться. Кроме того, важно, что 64-битные структуры тоже не атомарны, например long и double.  

long и double — самые «тяжеловесные» примитивы в Java: они весят по 64 бита. 
И в некоторых 32-битных платформах просто не реализована атомарность чтения и записи 64-битных переменных. 
Такие переменные читаются и записываются в две операции. Сначала в переменную записываются первые 32 бита, потом еще 32.

Соответственно, в этих случаях может возникнуть проблема. Один поток записывает какое-то 64-битное значение в переменную Х, 
и делает он это «в два захода». В то же время второй поток пытается прочитать значение этой переменной, причем делает это как раз посередине, 
когда первые 32 бита уже записаны, а вторые — еще нет. 
В результате он читает промежуточное, некорректное значение, и получается ошибка.      
 
Пакет java.util.concurrent.atomic содержит девять классов для выполнения атомарных операций. Т.е. в этих классах 
есть готовые методы для выполнения определенных операций.  
  
public static AtomicInteger atomic = new AtomicInteger(0);  
+ atomic.incrementAndGet(); - инкремент  
+ getAndAdd(long delta) - добавить к числу итд    
  
Блокировка подразумевает пессимистический подход, разрешая только одному потоку выполнять определенный код, связанный 
с изменением значения некоторой «общей» переменной. Таким образом, никакой другой поток не имеет доступа к определенным переменным. 
Но можно использовать и оптимистический подход. В этом случае блокировки не происходит, и если поток обнаруживает, 
что значение переменной изменилось другим потоком, то он повторяет операцию снова, но уже с новым значением переменной. 
Так работают атомарные классы.  


long getAndAdd(long delta) - Атомарно добавляет заданное значение к текущему значению  

```java
private volatile long value;
 
public final long get() {
    return value;
}
 
public final long getAndAdd(long delta) {
    while (true) {
        long current = get();
        long next = current + delta;
        if (compareAndSet(current, next))
            return current;
    }
}
```  

Каждый атомарный класс включает метод compareAndSet, представляющий механизм оптимистичной блокировки и 
позволяющий изменить значение value только в том случае, если оно равно ожидаемому значению (т.е. current). 
Если значение value было изменено в другом потоке, то оно не будет равно ожидаемому значению. 
Следовательно, метод compareAndSet вернет значение false, что приведет к новой итерации цикла while в методе getAndAdd. 
Таким образом, в очередном цикле в переменную current будет считано обновленное значение value, после чего будет выполнено сложение 
и новая попытка записи получившегося значения (т.е. next).    

**Основная выгода от атомарных (CAS) операций появляется только при условии, когда переключать контекст процессора 
с потока на поток (использование блокировок) становится менее выгодно, чем немного покрутиться в цикле while,** 
выполняя метод boolean compareAndSwap(oldValue, newValue). 
Если время, потраченное в этом цикле, превышает 1 квант потока, то, с точки зрения производительности, 
может быть невыгодно использовать атомарные переменные.  

Atomic-классы для boolean, integer, long и ссылок на объекты.
Классы этой группы содержат метод compareAndSet, принимающий 2 аргумента : предполагаемое текущее и новое значения. 
Метод устанавливает объекту новое значение, если текущее равно предполагаемому, и возвращает true. 
Если текущее значение изменилось, то метод вернет false и новое значение не будет установлено.
Кроме этого, классы имеют метод getAndSet, который безусловно устанавливает новое значение и возвращает старое.      

compareAndSet в себе использует **нативный** метод compareAndSetLong, который испольузет unsafe.compareAndSwapInt, 
который выполняется на процессоре атомарно и на ассемблере выглядит следующим образом, если включить распечатку ассемблерного кода:  
lock cmpxchg [esi+0xC], ecx.  
Таким образом мы получаем работу в цикле с проверкой переменной, 
причем которая может окончиться неудачей и всю работу в цикле до проверки необходимо начинать заново.    
    
Метод compareAndSet реализует механизм оптимистической блокировки. 
**Знакомые с набором команд процессоров специалисты знают, что ряд архитектур имеют инструкцию Compare-And-Swap (CAS), 
которая является реализацией этой самой операции. Таким образом, на уровне инструкций процессора имеется поддержка необходимой атомарной операции. 
На архитектурах, где инструкция не поддерживается, операции реализованы иными низкоуровневыми средствами.**  
Т.е. compareAndSet реализована на низком уровне и обеспечивает быстрое выполнение.  

Compare-and-Swap  
https://habr.com/ru/post/319036/  
Вспомним, что из себя представляет CAS (в процессорах Intel он осуществляется группой команд cmpxchg) – 
Операция CAS включает 3 объекта-операнда: адрес ячейки памяти (V), ожидаемое старое значение (A) и новое значение (B). 
Процессор атомарно обновляет адрес ячейки (V), если значение в ячейке памяти совпадает со старым ожидаемым значением(A),
иначе изменения не зафиксируется. В любом случае, будет выведена величина, которая предшествовала времени запроса. 
Некоторые варианты метода CAS просто сообщают, успешно ли прошла операция, вместо того, чтобы отобразить само текущее значение. 
Фактически, CAS только сообщает: «Наверное,  значение по адресу V равняется A; если так оно и есть, поместите туда же B, 
в противном случае не делайте этого, но обязательно скажите мне, какая величина — текущая.»  

Производительность атомарных классов  
Согласно множеству источников неблокирующие алгоритмы в большинстве случаев более масштабируемы и намного производительнее, чем блокировки. 
Это связано с тем, что операции CAS реализованы на уровне машинных инструкций, а блокировки тяжеловесны и используют приостановку
и возобновление потоков, переключение контекста и т.д. Тем не менее, блокировки демонстрируют лучший результат только при очень «высокой конкуренции», 
что в реальной жизни встречается не так часто.

Основной недостаток неблокирующих алгоритмов связан со сложностью их реализации по сравнению с блокировками. 
Особенно это касается ситуаций, когда необходимо контролировать состояние не одного поля, а нескольких.  

AtomicReference<BigInteger> element;   
An object reference that may be updated atomically.  

## Правила «happens-before»  
https://javarush.ru/groups/posts/1998-upravlenie-potokami-metodih-volatile-i-yield  
  
Каждое из этих правил дает гарантию, что при написании многопоточной программы одни события в 100% случаев будут происходить раньше, 
чем другие, и что поток B в момент выполнения операции 2 всегда будет в курсе изменений, которые поток А сделал во время операции 1.  
     
Правила:  
1. Освобождение мьютекса happens before происходит раньше захвата этого же монитора другим потоком.  
2. Метод Thread.start() happens before Thread.run().  
Это правило гарантирует, что установленные до запуска Thread.start() значения всех переменных будут видны внутри начавшего выполнение метода run().  
3. Завершение метода run() happens before выход из метода join().  
4. Запись в volatile переменную happens-before чтение из той же переменной.  
При использовании ключевого слова volatile мы, фактически, всегда будем получать актуальное значение. 
Даже в случае с long и double, о проблемах с которыми говорилось ранее.  
Так вот, Правило 4 гарантирует нам: если объявить переменную z как volatile, изменения ее значений в одном потоке всегда будут видны в другом потоке.  

## Потокобезопасные concurrent коллекции
  
1. Метод обрамления Collections.synchronizedList.    
Использование методов обрамления для получения синхронизированных коллекций представляет скрытую угрозу, 
поскольку разработчики полагают, что, раз коллекции синхронизированы, то они полностью потокобезопасны, 
и пренебрегают должной синхронизацией составных операций.  
 
Collections.synchronizedList (List/Set/Map) - Возвращает синхронизированный (потокобезопасный) список, 
поддерживающий указанный список. (т.е. изменения в синхронизированном списке, также отобразятся в начальном)  
Упрощённый подход к синхронизации с использованием методов обрамлений имеет существенный недостаток, 
вязанный с препятствованием масштабируемости, поскольку с коллекцией одновременно может работать только один поток.

**Методы итератора (iterator(), listIterator()) в  Collections.synchronizedList не синхронизированы!!!** Т.е. здесь 
при модификации во время итерирования тоже будет ConcurrentModificationException.     

It is imperative that the user manually synchronize on the returned list when traversing it via Iterator, Spliterator
or Stream.

List list = Collections.synchronizedList(new ArrayList());  
          ...  
synchronized (list) {  
Iterator i = list.iterator(); // Must be in synchronized block  
while (i.hasNext())  
foo(i.next());  
} 

Нужно ли дополнительно синхронизировать блок по коллекции завсит от содержания блока:  
+ Если блок представляет одиночную, атомарную опреацию на листе, синронизация - лишняя.  
+ Если блок представляет множественные операции на листе и нужно поддерживать блокировку на протяжении всей операции соединения
- тогда синхронизация не будет лишней. Одним из распространенных примеров этого является итерирование списка.

Так, если один поток изменяет содержимое коллекции, а второй поток обрабатывает ее итератором Iterator, 
то при вызове метода Iterator.hasNext() или Iterator.next() будет вызвано исключение ConcurrentModificationException. 
Чтобы обезопасить приложение от вызова исключения также, как и в предыдущем примере, необходимо целиком блокировать List на время перебора.  

Таким образом, использование методов обрамления для получения синхронизированных коллекций представляет скрытую угрозу, 
поскольку разработчики полагают, что, раз коллекции синхронизированы, то они полностью потокобезопасны, 
и пренебрегают должной синхронизацией составных операций. 
Такие программы могут нормально функционировть при лёгкой нагрузке, но при серьёзной нагрузке они могут 
вызывать NullPointerException или ConcurrentModificationException.  

Один из подходов к улучшению масштабируемости коллекции при сохранении потокобезопасности состоит в том, 
чтобы обходиться без общей блокировки всей таблицы, а использовать блокировки для каждого hash backet 
(или, в более общем случае, пула блокировок, где каждая блокировка защищает несколько бакетов). 
Это позволяет нескольким потокам обращаться к различным частям коллекции одновременно, 
без соперничества за единственную на всю коллекцию блокировку. Данный подход улучшает масштабируемость операций вставки, извлечения и удаления.  

## ConcurrentHashMap  
https://www.ibm.com/developerworks/ru/library/j-jtp07233/index.html    
Класс ConcurrentHashMap появился в пакете java.util.concurrent в JDK 1.5, является потокобезопасной реализацией Map 
и предоставляет намного большую степень масштабирования (параллелизма), чем synchronizedMap.   

**Реализует интерфейс ConcurrentMap, в котором определены несколько атомарных методов.**     

Отличие ConcurrentHashMap связано с внутренней структурой хранения пар key-value. 
СoncurrentHashMap использует несколько сегментов, и данный класс нужно рассматривать как группу HashMap’ов. 
Количество сегментов по умолчанию равно 16. Если пара key-value хранится в 10-ом сегменте, то ConcurrentHashMap заблокирует, 
при необходимости, только 10-й сегмент, и не будет блокировать остальные 15.  

ConcurrentHashMap добивается более высокой степени параллелизма, слегка смягчая обещания, которые даются тем, кто её вызывает. 
Операция извлечения возвратит значение, вставленное самой последней завершившейся операцией вставки, а также может возвратить значение, 
добавленное операцией вставки, выполняемой в настоящее время (но она никогда не возвратит бессмыслицы). 
Итераторы, возвращаемые ConcurrentHashMap.iterator() возвратят каждый элемент не более одного раза и никогда не выкинут 
ConcurrentModificationException, но могут отображать или не отображать вставки или удаления, имевшие место со времени, 
когда итератор был сконструирован. Блокировки целой таблицы не требуются (да и невозможны) для обеспечения потокобезопасности 
при переборе коллекции. ConcurrentHashMap может использоваться для замены synchronizedMap или Hashtable в любом приложении, 
которое не основано на способности делать блокировку всей таблицы для предотвращения модификаций.  

В СoncurrentHashMap можно во время итерирования изменять мапу, используя методы самой мапы  
```java
 while(it.hasNext()){
            String key = it.next();
            if (key.equals("2")) {
                map.put(key + "new", "222");
```
При этом просто в HashMap мы бы получили ConcurrentModificationException.  

Если мы установили не очень точное инишел капасити, то когда мапа будет расширяться по размеру, она будет полностью локаться!  

## ConcurrentSkipListMap  
https://ru.wikipedia.org/wiki/%D0%A1%D0%BF%D0%B8%D1%81%D0%BE%D0%BA_%D1%81_%D0%BF%D1%80%D0%BE%D0%BF%D1%83%D1%81%D0%BA%D0%B0%D0%BC%D0%B8    
https://www.youtube.com/watch?v=-yQeYo32Lt4&list=PL6jg6AGdCNaWtTjsYJ9t0VaITpIZm4pMt&index=4&ab_channel=%D0%9E%D0%BD%D0%BB%D0%B0%D0%B9%D0%BD%D1%83%D1%80%D0%BE%D0%BA%D0%B8%D0%BF%D0%BEJava  

Аналог коллекции TreeMap с сортировкой данных по ключу и с поддержкой многопоточности;  
Если необходимо иметь отсортированное множество элементов, следует использовать ConcurrentSkipListSet, 
который реализует интерфейсы SortedSet и NavigableSet.  

Реализация ConcurrentSkipListSet базируется на ConcurrentSkipListMap, структура ConcurrentSkipListSet похожа на структуру LinkedHashMap. 
Каждый элемент skip list структуры, кроме значения, содержит ссылку на соседние элементы. 
Также есть ссылки высших порядков, которые указывают на элементы, находящиеся впереди текущего, на произвольное число в определенном диапазоне, 
заданном для этого уровня ссылок. Для следующего уровня ссылок это число больше, чем для предыдущего.

Преимущество этой структуры данных — поиск нужного элемента происходит очень быстро за счет использования  ссылок высших порядков. 
Производительность поиска в этой структуре данных сравнима с поиском элементов в бинарном дереве. 
Для вставки изменения этой структуры данных не нужно полностью  блокировать структуру, достаточно найти элемент, который будет удален, 
и заблокировать два соседних элемента для изменения ссылок, указывающих  на элемент, подлежащий изменению.  

Beware that, unlike in most collections, the size method is not a constant-time operation. 
Because of the asynchronous nature of these sets, determining the current number of elements requires a traversal of the elements, 
and so may report inaccurate results if this collection is modified during traversal. Additionally, the bulk operations addAll, 
removeAll, retainAll, containsAll, equals, and toArray are not guaranteed to be performed atomically. For example, 
an iterator operating concurrently with an addAll operation might view only some of the added elements.  

Список с пропусками (англ. Skip List) — вероятностная структура данных, основанная на нескольких параллельных отсортированных 
связных списках с эффективностью, сравнимой с двоичным деревом (порядка O(log n) среднее время для большинства операций).

В основе списка с пропусками лежит расширение отсортированного связного списка дополнительными связями, добавленными 
в случайных путях с геометрическим/негативным биномиальным распределением[1], таким образом, чтобы поиск по списку 
мог быстро пропускать части этого списка. Вставка, поиск и удаление выполняются за логарифмическое случайное время.    

## CopyOnWriteArrayList  
Если мы модифицируем лист, то создается копия, которая иземеняется и потом становится главной. При этом если мы 
вызываем итератор, то он работает такжес копией -  со старыми данными и не не кидает ConcurrentModificationException,
если в параллельном потоке изменяются данные листа.

**Лок списка происходит только для методов модификации, делается копия массива и сетится в переменную массива. 
Поэтому удобно использовать коллекцию для итерирования, т.к. она не блокируется, но если очень часто вставляем, то 
часто будет происходит копирование массива.**   
 
Класс CopyOnWriteArrayList следует использовать вместо ArrayList в потоконагруженных приложениях, 
где могут иметь место нечастые операции вставки и удаления в одних потоках и одновременный перебор в других.
Это типично для случая, когда коллекция ArrayList используется для хранения списка объектов.     

Если вы используете обычный ArrayList для хранения списка подписчиков, то до тех пор, пока список допускает изменения и 
к нему могут обращаться много потоков, вы должны либо блокировать целый список во время перебора, либо клонировать его перед перебором, 
оба варианта обходятся значительной ценой. CopyOnWriteArrayList вместо этого создаёт новую копию списка каждый раз, 
когда выполняется модифицирующая операция, и гарантируется, что её итераторы возвращают состояние списка на момент, 
когда итератор был сконструирован и не выкинут ConcurrentModificationException. 
Нет необходимости клонировать список до перебора или блокировать его во время перебора, потому что копия списка, 
которую видит итератор, не будет изменяться. Другими словами, CopyOnWriteArrayList содержит изменяемую ссылку на неизменяемый массив, 
поэтому до тех пор, пока эта ссылка остаётся фиксированной, 
вы получаете все преимущества потокобезопасности от неизменности без необходимости блокировок.  

Модифицированная версия и становится "главной". Основная фишка в том, что старая версия массива запоминается в итераторе, 
и итератор может итерироваться по старой весии без какой-либо синхронизации.    

Идея подхода copy-on-write заключается в том, что при чтении области данных используется общая копия, в случае изменения данных — создается новая копия.  

## CopyOnWriteArraySet  
https://javarush.ru/groups/posts/1439-kak-poljhzovatjhsja-copyonwritearrayset-v-java-s-primerom-perevod    
CopyOnWriteArraySet лучше всего подходит для read-only коллекций, размер которых достаточно мал, 
чтобы их скопировать, если произойдут некоторые изменяющие операции.  

Короче говоря, используйте CopyOnWriteArraySet если set достаточно мал, чтобы копировать его при добавлении, 
задании значения или удалении объектов, и основной целью является чтение обновляемых от случая к случаю данных. 
Кроме того, если вы хотите удалить элементы во время итерации, не используйте эту реализацию, 
потому что ее итератор не поддерживает remove(), и бросает java.lang.UnsupportedOperationException.  

**В своей работе итераторы используют «моментальный снимок» массива, который был сделан на момент создания итератора.**    

CopyOnWriteArraySet лучше всего подходит для приложений, в которых:  
+ Размеры Set'ов, как правило остаются небольшими.
+ Операции read-only значительно превосходят операции, изменяющие объекты.
+ Вы должны предотвратить помехи между потоками во время обхода Set'а.
+ Еще одним преимуществом CopyOnWriteArraySet является потокобезопасность. Эта коллекция поддерживает параллелизм.
+ Мутативные операции (добавление, изменение, удаление и т.д.) являются дорогостоящими, так как они, как правило, требуют копирования всего базового массива.
+ Итераторы не поддерживают мутативную операцию удаления.
+ Обход используя итератор достаточно быстр и во время него исключены вмешательства других потоков.
В своей работе итераторы опираются на моментальный снимок массива, который был сделан во время создания итератора.  

Пример подтверждает, что итератор набора данных CopyOnWriteArraySet не вызвал исключения ConcurrentModificationException 
при одновременном переборе и изменении значений.  
  
## CountDownLatch  
https://pro-java.ru/parallelizm-v-java/klass-countdownlatch-primery-realizacii-koda-v-java/    
https://habr.com/ru/post/277669/  
Иногда требуется, чтобы поток исполнения находился в режиме ожидания до тех пор, пока не наступит одно (или больше) событие.  
Для этих целей в парал­лельном API предоставляется класс CountDownLatch, реализующий самоблокировку с обратным отсчетом. 
Объект этого класса изначально создается с количеством событий, которые должны произойти до того момента, как будет снята самоблокировка. 
Всякий раз, когда происходит событие, значение счетчика уменьшается.  

void await() throws InterruptedException  
В первой форме ожидание длится до тех пор, пока отсчет, связанный с вызывающим объектом типа CountDownLatch, не достигнет нуля.  
Чтобы известить о событии, следует вызвать метод countDown().  

Блочится поток, в котором вызывается метод await().  
Класс CountDownLatch является эффективным и простым в употреблении средством синхронизации, которое окажется полезным в тех случаях, 
когда поток исполнения должен находиться в состоянии ожидания до тех пор, пока не произойдет одно или несколько событий.  

## BLOCKINGQUEUE  
https://www.youtube.com/watch?v=nUYOGkh9XqE&list=PL6jg6AGdCNaWtTjsYJ9t0VaITpIZm4pMt&index=5&ab_channel=%D0%9E%D0%BD%D0%BB%D0%B0%D0%B9%D0%BD%D1%83%D1%80%D0%BE%D0%BA%D0%B8%D0%BF%D0%BEJava  
https://dataart.ua/articles/mnogopotochnost-v-java-lekfiya-5-atomarnye-peremennye-i-mnogopotochnye-kollekfii/  
Используется для синхронизации потоков, похожа на wait notify или Phaser, Exchanger. 

BlockingQueue не поддерживает значение null, при попытке вставить его генерируется NullPointerException.  

Очередь фиксированного размера, в которой добавлены методы
put() - блокирует, если нет места  
take() - блокирует, если пустая.
Можно синхронизирвоать потоки, т.к. методы блокируют. 
Плюс потоки могут обменяться данными. (для этого можно создать очередь с одним элементом)    

SynchronousQueue
Оба метода блокируют поток, пока не будет вызван другой метод.    
SynchronousQueue – это специальный тип  BlockingQueue, в котором каждая операция insert 
должна ждать соответствующую команду remove в другой нити, и наоборот.
Когда вы вызываете метод put() у SynchronousQueue, он блокируется до тех пор, пока другая нить не заберет этот элемент из него. 
Соответственно, если другая нить пытается удалить элемент из него, а элемента там нет, то эта нить блокируется до тех пор, 
пока другая нить не положит элемент в очередь. Можно представить  SynchronousQueue как спортсмена (нить) бегущего с олимпийским факелом, 
он бежит с факелом (объектом который передается) и передает его другому спортсмену, ожидающему с другой стороны.  
Эта очередь будет возвращать null, она нужна только для синхронизации потоков.  
Плюс 2 потока могут обменяться данными.  

Класс SynchronousQueue для добавления элемента в очередь вызывает метод put(). 
При вызове этого метода вызывающий поток блокируется, пока другой поток не вызовет метод take(). 
Также класс SynchronousQueue можно представить как точку синхронизации между двумя потоками, когда один поток дает элемент, 
а второй его получает. Также про SynchronousQueue говорят, что это очередь без единого элемента.

Т. к. SynchronousQueue — очередь без элементов, эта коллекция ведет себя специфически: метод isEmpty()
всегда возвращает true, метод iterator() возвращает пустой итератор, метод hasNext() всегда возвращает false,
peek() всегда возвращает null, size() всегда возвращает 0.    

+ Если попробуем положить элемент обычным методом queue.add(10);, то получим исключение.  
Exception in thread "Thread-1" java.lang.IllegalStateException: Queue full  
+ queue.peek() вернет null         
      
        
  
           
   
   