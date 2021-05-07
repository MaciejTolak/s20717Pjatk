package zad1.Pack;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args)  {
        Buffer buf = new Buffer(20);
        Producer producer = new Producer(buf);
        Consumer consumer = new Consumer(buf);

        ExecutorService executorService = Executors.newCachedThreadPool();

        try {
            executorService.execute(producer);
            executorService.execute(consumer);
            Thread.sleep(15000);
            executorService.shutdownNow();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}
