package completablefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class Ex7 {

    public static void main(String[] args) {
        CompletableFuture<CompletableFuture<String>> completableFuture
                = getUserDetail("5").thenApply( user -> getUserName(user));

        CompletableFuture completableFuture2 =
                getUserDetail("6").thenCompose(user -> getUserName(user));

        try {
            System.out.println(completableFuture2.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }


    static CompletableFuture<String >getUserDetail(String id){
        return CompletableFuture.supplyAsync(()->{
            return "user with id: " + id;
        });
    }

   static CompletableFuture<String > getUserName(String user){
        return CompletableFuture.supplyAsync(()->{
            return user + ". has name  Robert";
        });
    }
}
