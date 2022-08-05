package thread;

public class Main {

    public static void main(String[] args) {

        Thread thread1 = new ThreadExample();
        thread1.start();
        Thread thread2 = new Thread(new ThreadExample2());
        thread2.start();
        System.out.println("Hello from main thread");
    }


}




class ThreadExample2 implements Runnable{

    @Override
    public void run() {
        System.out.println("Hello from implements Runnable");
        for (int i = 0;i< 10;i++){
            System.out.println(i);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}


class ThreadExample extends Thread{

    public void run(){
        System.out.println("Hello from extends Thread");
        for (int i =0;i< 10;i++){
            System.out.println(i);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


}
