package com.example.arithmetic.thread;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;
import java.util.function.IntConsumer;

/**
 * @author lgh on 2020/12/8 19:45
 * @description
 */
public class ZeroEvenOdd {
    private int n;

    public ZeroEvenOdd(int n) {
        this.n = n;
    }

    Semaphore z = new Semaphore(1);//一个许可
    Semaphore e = new Semaphore(0);//release 会产生一个许可
    Semaphore o = new Semaphore(0);

    CyclicBarrier mCyclicBarrier = new CyclicBarrier(4);//参与的线程数
    CyclicBarrier mCyclicBarrier1 = new CyclicBarrier(4, new Runnable() {
        @Override
        public void run() {
            //todo 最后一个到达的线程要做的任务
            try {
                mCyclicBarrier.await();//表示到达栅栏
            } catch (BrokenBarrierException | InterruptedException brokenBarrierException) {
                brokenBarrierException.printStackTrace();
            }
        }
    });

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void zero(IntConsumer printNumber) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            z.acquire();
            printNumber.accept(0);
            if ((i & 1) == 0) {
                o.release();
            } else {
                e.release();
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void even(IntConsumer printNumber) throws InterruptedException {
        for (int i = 2; i <= n; i += 2) {
            e.acquire();
            printNumber.accept(i);
            z.release();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void odd(IntConsumer printNumber) throws InterruptedException {
        for (int i = 1; i <= n; i += 2) {
            o.acquire();
            printNumber.accept(i);
            z.release();
        }
    }
}
