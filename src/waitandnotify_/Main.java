package waitandnotify_;

import javax.management.Query;
import java.util.List;
import java.util.Objects;
import java.util.Queue;
import java.util.Random;

public class Main {

    static int num;



    public static void main(String[] args) {
        Object lock = new Object();


        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                add(lock);
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(get(lock));
            }
        });

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }



    private static  void add(Object lock){
        synchronized (lock){
            System.out.println("First thread is running...");
            num = new Random().nextInt(100);
            System.out.println("First thread call notify()...");
            lock.notify();
        }
    }


    private static int get(Object lock){
        synchronized (lock){
            System.out.println("Second thread is running...");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (num == 0) {
                try {
                    System.out.println("Before wait()...");
                    lock.wait();
                    System.out.println("After wait()....");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return num;
        }
    }



}
