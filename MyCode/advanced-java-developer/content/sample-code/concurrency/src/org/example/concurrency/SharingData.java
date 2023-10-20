package org.example.concurrency;

public class SharingData implements Runnable {

    // non-final fields are not thread-safe
    // mutable fields are not thread-safe either
    private int x = 1;

    public int getX() {
        return x;
    }

    @Override
    public void run() {
        for (var i = 0; i < 5000; i++) {
            // the thread must obtain a ref to this before entering the block
            // only one thread can hold the ref at any one time
            // this may negate the benefit of threading in the first place
            synchronized (this) {
                x += 1;
            }
            /*
             * One line of source code is not necessarily one line of bytecode
             * This line compiles into bytecode a bit like this:
             * READ X
             * READ 1
             * INPLACE ADD
             * WRITE X
             * If t1 is switched out of the CPU just before writing back to x...
             * then x will be corrupted.
             */
        }
    }

    public static void main(String[] args) throws InterruptedException {
        var sharingData = new SharingData();
        var t1 = new Thread(sharingData);
        var t2 = new Thread(sharingData);
        t1.start();
        t2.start();
        t1.join(); // <!-- force the main thread to wait or t1 to finish
        t2.join(); // <!-- force the main thread to wait or t2 to finish
        System.out.println(sharingData.getX());
    }
}
