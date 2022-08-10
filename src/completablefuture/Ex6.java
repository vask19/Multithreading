package completablefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class Ex6 {
    public static void main(String[] args) {
        CompletableFuture<String> completableFuture1 =
                CompletableFuture.supplyAsync(()->{
                            try {
                                TimeUnit.SECONDS.sleep(2);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }

                            return "result";
                })
                        .thenApplyAsync(result -> {
                            return "async "  + result;
                        });

        try {
            System.out.println('b');
            System.out.println( completableFuture1.get());
            System.out.println('a');
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
