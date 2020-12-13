package multithreading;

public class MyDemo {
    public static void main(String[] args) throws InterruptedException {
        ThreadGroup threadGroup = new ThreadGroup("group1");
        Thread thread = new Thread(threadGroup, new MyThread());
        thread.setPriority(10);
        thread.start();
        Thread.sleep(1000);
        thread.interrupt();
        System.out.println("main отработал");
    }
}

class MyThread implements Runnable {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().isInterrupted());
        while (!Thread.interrupted()) {
            System.out.println("отработал");
        }
        System.out.println(Thread.currentThread().isInterrupted());
    }
}
