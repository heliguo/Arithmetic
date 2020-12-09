package com.example.arithmetic.thread;

/**
 * @author lgh on 2020/12/8 20:06
 * @description
 */
public class H2O {

    private int hCount;
    private int oCount;
    private final Object lock = new Object();

    public H2O() {
        hCount = 2;
        oCount = 0;
    }

    public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
        synchronized (lock) {
            while (hCount == 0) {
                lock.wait();
            }
            // releaseHydrogen.run() outputs "H". Do not change or remove this line.
            releaseHydrogen.run();
            hCount--;
            if (hCount == 0)
                oCount = 1;
            lock.notifyAll();
        }
    }

    public void oxygen(Runnable releaseOxygen) throws InterruptedException {
        synchronized (lock) {
            while (oCount == 0) {
                lock.wait();
            }
            // releaseOxygen.run() outputs "O". Do not change or remove this line.
            releaseOxygen.run();
            oCount--;
            hCount = 2;
            lock.notifyAll();
        }
    }
}
