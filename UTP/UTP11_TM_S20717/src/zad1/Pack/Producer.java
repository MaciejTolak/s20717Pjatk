package zad1.Pack;

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
            buf.put(n);
            try {
                Thread.sleep(rand.nextInt(2000));
            } catch (InterruptedException e) {
                return;
            }
        }
    }
}
