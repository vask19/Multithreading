package volatile_;

public class Main {

    private static volatile boolean running = true;

    public static void main(String[] args) {
        Thread thread3 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                    running = false;
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            int i = 1;
            @Override
            public void run() {
                while (running){
                    System.out.println(i++);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        thread2.start();
        thread3.start();
        try {
            thread2.join();
            thread3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
