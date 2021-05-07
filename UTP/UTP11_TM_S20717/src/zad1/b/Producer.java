package zad1.b;

import java.util.Random;

public class Producer implements Runnable {
    private Buffer buf;
    private Random rand;

    public Producer(Buffer buf) {
        this.buf = buf;
        rand = new Random();
    }


    @Override
    public void run() {
        int n;
        while(true){
            if(Thread.currentThread().isInterrupted()){
                return;
            }
            n = rand.nextInt(150)+1;
            try {
                buf.put(n);
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
