package org.example.concurrency;

public class MyRunnable implements Runnable {

    @Override
    public void run() {
        // this code will be executed when the thread is scheduled by the OS
        // NEVER call this method directly!
        System.out.println(Thread.currentThread().getName() + " executing run...");
        System.out.println("Hello");
    }

    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName() + " executing main...");
        var t1 = new Thread(new MyRunnable());
        var t2 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " executing lambda...");
            System.out.println("How are you?");
        });
        t1.start(); // <!-- ask the OS to schedule this thread; the OS will call run at some point
        t2.start(); // <!-- there is no guarantee that this thread will execute after t1
        System.out.println("Goodbye");
    }
}
