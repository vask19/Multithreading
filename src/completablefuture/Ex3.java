package completablefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class Ex3 {

    public static void main(String[] args) {
        CompletableFuture<String> completableFuture1  = CompletableFuture.supplyAsync(()->
        {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Result from supply async";
        }

        );


        System.out.println("before then apply");
        CompletableFuture<String> completableFuture2 = completableFuture1.thenApply(result ->{
            return "result from then apply: " + result;
        });
        try {
            System.out.println(completableFuture2.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        System.out.println("after then apply");

    }
}
