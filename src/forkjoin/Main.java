package forkjoin;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class Main {

    public static void main(String[] args) {
        ForkJoinPool pool = new ForkJoinPool();
        pool.invoke(new Action(512));
        System.out.println("Finish");
    }
}




class Action extends RecursiveAction{


    private int value;


    public Action(int value) {
        this.value = value;
    }

    @Override
    protected void compute() {
        System.out.println("thread id: " + Thread.currentThread().getId() + ".....value = " + value);
        System.out.println();

        if (value <= 4){
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println();


        }

        else {
            Action action1 = new Action(value/2);
            Action action2 = new Action(value/2);
            invokeAll(action1,action2);
        }

    }
}
