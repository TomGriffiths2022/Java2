package org.example.concurrency;

public class MyThread extends Thread {
    // extending Thread is not a good idea, as you can only have 1 super class (i.e. can't extend more than one class)

    @Override
    public void run() {
        // this code will be executed when the thread is scheduled by the OS
        // NEVER call this method directly!
        System.out.println(Thread.currentThread().getName() + " executing run");
        System.out.println("Hello");
    }

    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName() + " executing main");
        var myThread = new MyThread();
        myThread.start(); // this method asks the OS to schedule this thread; the OS will call run at some point
        System.out.println("Goodbye");
    }
}
