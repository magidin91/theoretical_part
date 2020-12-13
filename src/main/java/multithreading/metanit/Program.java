package multithreading.metanit;

class JThread extends Thread {

    JThread(String name) {
        super(name);
    }

    public void run() {

        System.out.printf("%s started... \n", Thread.currentThread().getName());
        int counter = 1; // счетчик циклов
        while (!isInterrupted()) {
            System.out.println("Loop " + counter++);
            try {
                Thread.sleep(100);
            /* Однако при получении статуса потока с помощью метода isInterrupted() следует учитывать,
    что если мы обрабатываем в цикле исключение InterruptedException в блоке catch,
    то при перехвате исключения статус потока автоматически сбрасывается, и после этого isInterrupted будет возвращать false.*/
            } catch (InterruptedException e) {
                System.out.println(getName() + " has been interrupted");
                System.out.println(isInterrupted());    // false
                interrupt();    // повторно сбрасываем состояние
            }
        }
        System.out.printf("%s finished... \n", Thread.currentThread().getName());
    }
}

public class Program {

    public static void main(String[] args) {

        System.out.println("Main thread started...");
        JThread t = new JThread("JThread");
        t.start();
        try {
            Thread.sleep(150);
            t.interrupt();

            Thread.sleep(150);
        } catch (InterruptedException e) {
            System.out.println("Thread has been interrupted");
        }
        System.out.println("Main thread finished...");
    }
}