package completablefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class Ex8 {

    public static void main(String[] args) {
        CompletableFuture<Double> weightInKg =
                CompletableFuture.supplyAsync(()->{
                    try {
                        TimeUnit.SECONDS.sleep(2);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    return 70.0;
                });

        CompletableFuture<Double> heightInSm =
                CompletableFuture.supplyAsync(()->{
                    try {
                        TimeUnit.SECONDS.sleep(2);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    return 180.0;
                });

        CompletableFuture<Double> combineFuture =
                weightInKg.thenCombine(heightInSm,(weight,height) ->{
                    Double heightInMeter = height/100;
                    return weight / (heightInMeter * heightInMeter);

                });

        try {
            System.out.println("you have : " + combineFuture.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
