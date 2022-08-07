package producerconsumer;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class Main {

    private static int threadId;

    private Queue<Integer> queue =  new LinkedList<>();
    private final Object lock = new Object();
    private final int LIMIT = 10;


    public static void main(String[] args) {
        Main main = new Main();
       var thead1 =  new Thread(new Runnable() {
            @Override
            public void run() {
                main.produce();

            }
        });

       var thead2 =  new Thread(new Runnable() {
            @Override
            public void run() {
                main.consume();

            }
        });


        var thead3 =  new Thread(new Runnable() {
            @Override
            public void run() {
                main.consume();

            }
        });




       thead1.start();
       thead2.start();
       thead3.start();

        try {
            thead1.join();
            thead2.join();
            thead3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }




    private  void consume(){

        int id = ++threadId;
        while (true){
            synchronized (lock){
                while (queue.size() == 0){
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }


                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Thread "+ id);
                System.out.println(queue.poll());
                lock.notifyAll();
            }
        }


    }


    private void produce(){

        while (true){
            synchronized (lock){
                while (queue.size() == LIMIT){
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                queue.add(new Random().nextInt(100));
                lock.notify();
            }
        }

    }



}
