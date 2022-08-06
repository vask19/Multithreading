package synchronized_;

import java.util.ArrayList;
import java.util.List;

public class Main {

    static List<Integer> digits = new ArrayList<>();




    public static void main(String[] args) {



        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                increment();

            }
        });
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                increment();

            }
        });
        Thread thread3 = new Thread(new Runnable() {
            @Override
            public void run() {
                increment();

            }
        });



        thread1.start();
        thread2.start();
        thread3.start();

        try {
            thread1.join();
            thread2.join();
            thread3.join();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        printSum();

    }


    private static synchronized   void increment(){

        for (int i =0; i <100;i++){
            digits.add(i);

        }

    }

    private static void printSum(){
        Long sum = 0l;
        for (Integer digit : digits){
            sum += digit;

        }
        System.out.println(sum);
    }
}
