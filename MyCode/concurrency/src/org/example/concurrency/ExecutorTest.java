package org.example.concurrency;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;

public class ExecutorTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        var executor = Executors.newFixedThreadPool(4);

        // the executor does the creating of the Thread and the calling of start
        executor.execute(() -> System.out.println("Hello"));

        // what about waiting?
        var future = executor.submit(() -> System.out.println("How are you?"));
        future.get(); // this will force the main thread to wait until the task completes

        System.out.println("Goodbye");

        executor.shutdown();
    }
}
