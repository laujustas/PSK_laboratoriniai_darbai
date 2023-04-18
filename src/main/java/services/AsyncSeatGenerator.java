package services;

import jakarta.ejb.AsyncResult;
import jakarta.ejb.Asynchronous;
import jakarta.ejb.Stateless;

import java.io.Serializable;
import java.util.Random;
import java.util.concurrent.Future;

@Stateless
public class AsyncSeatGenerator implements Serializable {
    Random random = new Random();

    @Asynchronous
    public Future<Integer> generateSeatNumber(){
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return new AsyncResult<>(random.nextInt(200));
    }

}