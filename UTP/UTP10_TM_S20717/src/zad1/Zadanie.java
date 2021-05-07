package zad1;

import javax.swing.*;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public class Zadanie implements Callable<Integer> {

    private ExecutorService essa;
    private int limit;
    private int suma;
    private JPanel jPanel;
    private JTextArea jTA;
    private List<JButton> list;
    private JButton button;
    private int threadNumber;
    private int tmp;
    private Future<Integer> future;
    private int wait;


    public Zadanie(ExecutorService essa, int limit, JPanel jPanel, JTextArea jTA, int threadNumber, List<JButton> list, JButton button) {
        this.essa = essa;
        this.limit = limit;
        this.jPanel = jPanel;
        this.jTA = jTA;
        this.suma = 0;
        this.threadNumber = threadNumber;
        this.tmp = 0;
        this.list = list;
        this.button = button;
    }

    @Override
    public Integer call() throws Exception {
        Random rand = new Random();

        while (suma <= limit) {
            synchronized (this) {
                while (tmp == 1) {
                    this.wait();
                }
            }

            int random = rand.nextInt(100) + 1;
            suma += random;
            jTA.append("Thread " + threadNumber + " (" + "limit = " + limit + ") " + random + "," + "sum = " + suma + "\n");

            Thread.sleep(rand.nextInt(3000)+1000);

        }

        jTA.append("Thread " + threadNumber + " DONE!\n");
        button.setText("Thread " + threadNumber + " DONE!");
        button.setEnabled(false);
        list.remove(button);
        Thread.sleep(5000);
        jPanel.remove(button);
        jPanel.repaint();

        if (Thread.currentThread().isInterrupted()) {
            return null;
        }
        return suma;
    }


    public void wznowZadanie() {
        tmp = 0;
        synchronized (this) {
            this.notify();
        }
    }

    public void zatrzymajZadanie() {
        tmp = 1;
    }

    public void rozpocznijZadanie() {
        Callable<Integer> callable = this;
        future = essa.submit(callable);
    }

    public Future<Integer> getFuture() {
        return future;
    }
}
