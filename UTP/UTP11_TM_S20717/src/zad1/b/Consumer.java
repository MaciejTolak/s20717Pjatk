package zad1.b;

import java.util.Random;

public class Consumer implements Runnable {
    private Buffer buf;
    private Random rand;

    public Consumer(Buffer buf) {
        this.buf = buf;
        rand = new Random();
    }


    @Override
    public void run() {
        while(true){
            if(Thread.currentThread().isInterrupted()){
                return;
            }
            try {
                buf.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                Thread.sleep(rand.nextInt(2000));
            } catch (InterruptedException e) {
                return;
            }
        }
    }
}
