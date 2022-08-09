package completablefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class Ex2 {

    public static void main(String[] args) {
        CompletableFuture<String > future = CompletableFuture.supplyAsync(()->{
            try {
                TimeUnit.SECONDS.sleep(4);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return "Result";
        });

        try {
            String result = future.get();
            System.out.println(result);
            System.out.println("after");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
