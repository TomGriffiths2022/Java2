package org.example.concurrency;

public class HeyHo implements Runnable {

    /*
     * Inside the main method you should:
     * - spawn two threads that each use a HeyHo instance
     * - the first should write Hey to stdout 10 times
     * - the second should write Ho to stdout 10 times
     * Hint: don't forget basic OOP, e.g. you can pass something into the constructor
     */

    private String message;
    private int times;

    public HeyHo(String message, int times) {
        this.message = message;
        this.times = times;
    }

    public void printMessageNTimes() {
        for (var i = 0; i < times; i++) {
            System.out.println(message);
        }
    }

    @Override
    public void run() {
        printMessageNTimes();
    }

    public static void main(String[] args) {
        var t1 = new Thread(new HeyHo("Hey", 100));
        var t2 = new Thread(new HeyHo("Ho", 50));
        t1.start();
        t2.start();
    }
}
