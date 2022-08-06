package threadpool_;

import java.sql.Time;
import java.util.Date;
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        for (int i =0;i<7;i++){
            executorService.submit(new Process());

        }
        executorService.shutdown();
        try {
            executorService.awaitTermination(1,TimeUnit.HOURS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }





}

class Process implements Runnable{

    @Override
    public void run() {
        System.out.println("Start work");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Finished work");
    }
}
