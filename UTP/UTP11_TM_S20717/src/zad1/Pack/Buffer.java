package zad1.Pack;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Buffer {
    private int size;
    private Lock zamek;
    private Queue<Integer> kolejka;
    private Condition warunekP;
    private Condition warunekC;


    public Buffer(int size){
        this.size = size;
        zamek = new ReentrantLock();
        kolejka = new LinkedList<>();
        warunekP = zamek.newCondition();
        warunekC = zamek.newCondition();
    }

    public int get(){
        zamek.lock();
        int z = 0;
        try{
            while(kolejka.size() <= 0){
                warunekC.await();
            }


        warunekP.signal();
        z = kolejka.poll();
        System.out.println("Consumer zabiera liczbe " + z);
        return z;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return z;
        } finally {
            zamek.unlock();
        }
    }


    public void put(int a){
        zamek.lock();
         try{
                 while(kolejka.size()>= size){
                     warunekP.await();
             }
             warunekC.signal();
             kolejka.add(a);
             System.out.println("Producer dodał liczbe "+a);
         } catch (InterruptedException e) {
             e.printStackTrace();
         } finally {
             zamek.unlock();
         }

    }


}
