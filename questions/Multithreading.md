Runnable threadJob = new MyRunnable();
Thread myThread = new Thread(threadJob);
myThread.start();

Планировщик решает, кто и как долго должен работать, а также что случается с потоками, когда они перестают выполняться.
Вы не можете управлять планировщиком. Для этого не предусмотрено соответствующих методов. Что еще более важно, процесс планирования потоков не дает вам гарантий (выполнение некоторых принципов почти гарантируется, но это очень неопределенно)!
Секрет кроется в приостановке. Приостанавливая поток даже на несколько миллисекунд, вы заставляете его изменить свое состояние и уступить место другому потоку. Метод sleep() гарантирует выполнение одного правила: приостановленный поток не вернется к работе, пока не истечет срок его приостановки. Например, если вы прикажете своему потоку остановиться на две секунды (2000 мс), он ни за что не продолжит свою работу до истечения этого времени (но это не значит, что он возобновится сразу через две секунды). Метод sleep выбрасывает проверяемое исключение (InterruptedExcep- tion), поэтому все его вызовы должны быть заключены в блоки try/catch либо объявлены.
Можно ли повторно использовать объект Thread? Можно ли дать ему новое задание и опять вызвать метод start()?
• Нет. Если метод run() завершил свою работу, то поток уже нельзя запустить. Фактически, в этот момент поток входит в состояние, о котором мы еще не упоминали, — состояние смерти. Несмотря на то что объект Thread навсегда потерял свою «поточность», он по-прежнему может находиться в куче, и у вас есть возможность вызывать другие его методы (если ситуация позволяет). Иными словами, у Thread больше нет отдельного стека вызовов, это уже не поток. Это просто такой же объект, как и все остальные.
Существует шаблон проектирования для создания пула потоков, которым вы можете воспользоваться для выполнения разных заданий. Но он не перезапускает мертвые потоки.
Установить имя потока:

alpha. setName ("поток альфа");

Получить имя текущего потока:
String threadName = Thread.currentThread().getName();

Чтобы метод мог выполняться одновременно только в одном потоке, используйте ключевое слово synchronized.