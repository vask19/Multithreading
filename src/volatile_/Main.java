package volatile_;

public class Main {
    volatile int a;
    int b;

    public static void main(String[] args) {
        Main main = new Main();


      Thread thread1 = new Thread(new Runnable() {
          @Override
          public void run() {
              for (int i = 0;i < 10000;i++){
                  main.a++;
                  main.b++;

              }
          }
      });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0;i < 10000;i++){
                    main.a++;
                    main.b++;

                }
            }
        });

        thread1.start();
        thread2.start();
        try {
            thread1.join();
            thread2.join();
            System.out.println("a = " + main.a);
            System.out.println("b = " +main.b);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}
