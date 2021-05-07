package zad1.b;


import java.util.concurrent.ArrayBlockingQueue;

public class Buffer {

    private int size;
    private ArrayBlockingQueue<Integer> block;


    public Buffer(int size) {
        this.size = size;
        block = new ArrayBlockingQueue<>(size);

    }

    public int get() throws InterruptedException {
        int z = block.take();
        System.out.println("Consumer zabiera liczbe " + z);
      return z;
    }

    public void put(int a) throws InterruptedException {
        block.put(a);
        System.out.println("Producer dodał liczbe "+a);

    }
}
