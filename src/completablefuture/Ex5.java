package completablefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class Ex5 {
    public static void main(String[] args) {
        CompletableFuture completableFuture1 = CompletableFuture.supplyAsync(()->{

            return "Laptop";
        }
        ).thenAccept(product ->{
            System.out.println("product type : " + product);
        });
        try {
            completableFuture1.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }



}
