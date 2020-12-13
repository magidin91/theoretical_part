package multithreading;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

public class SynchronousQueueDemo {
    public static void main(String[] args) {
        BlockingQueue<Integer> queue = new SynchronousQueue<>();
        new ArrayBlockingQueue(10);
        Thread thread1 = new Thread(new Printer(queue));
        Thread thread2 = new Thread(new Writer(queue));
        thread1.start();
        thread2.start();
    }
}

class Printer implements Runnable {
    BlockingQueue<Integer> queue;

    public Printer(BlockingQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        System.out.println("works Printer");
        try {
            queue.put(1);
            System.out.println();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Writer implements Runnable {
    BlockingQueue<Integer> queue;

    public Writer(BlockingQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        System.out.println("works Writer");
        try {
            Integer i = queue.take();
            System.out.println(i);
//            queue.add(10);
            System.out.println(queue.peek());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
