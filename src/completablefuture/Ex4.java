package completablefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class Ex4 {
    public static void main(String[] args) {

        CompletableFuture<String > completableFuture1 = CompletableFuture.supplyAsync(()->{

            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return "supply async";
        })





        .thenApply(result ->{
            return "then apply 1 /" + result;
        }).thenApply(result ->{
            return "then apply 2 /" + result;
        }).thenApply(result -> {
            return "then apply 3 /" + result;
        });

        try {
            System.out.println(  completableFuture1.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
