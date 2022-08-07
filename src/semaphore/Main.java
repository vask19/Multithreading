package semaphore;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main {



    public static void main(String[] args) throws InterruptedException {
        Calculator calculator = Calculator.getInstance();
        Random random = new Random();

        ExecutorService executorService = Executors.newFixedThreadPool(150);
        for (int i = 0;i< 150;i++){
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    try {
                        calculator.run(random.nextInt(100),random.nextInt(100));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });


        }
        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.HOURS);
    }

}


class Calculator{
    private int userCounter;
    private final Lock lock = new ReentrantLock();
    private Semaphore semaphore = new Semaphore(5);

    private static Calculator instance;


    private Calculator(){

    }

    public static Calculator getInstance(){
        if (instance == null)
            instance = new Calculator();
        return instance;
    }

    public void run(int a, int b) throws InterruptedException {
        semaphore.acquire();
        try {
            sum(a,b);
        }finally {
            semaphore.release();
        }
    }

    private void sum(int a,int b){
        synchronized (this){
            userCounter++;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }




        }
        System.out.println("user id: " + userCounter);
        System.out.println(a + " + " + b + " = " + (a + b));
        synchronized (this){
            userCounter--;
        }



    }

}
