package com.example.arithmetic.thread;

import java.util.BitSet;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

/**
 * author:lgh on 2020/6/10 21:08
 */
class Main {

    private enum R {T1, T2}

    private static volatile R r = R.T1;
    static volatile boolean flag = false;

    static char[] a1 = "123456".toCharArray();
    static char[] a2 = "abcdef".toCharArray();

    static Thread t1 = null, t2 = null;


    //方法4
    //为空阻塞线程，满了也会堵塞
    static BlockingQueue<String> q1 = new ArrayBlockingQueue<>(1);
    static BlockingQueue<String> q2 = new ArrayBlockingQueue<>(1);

    //方法5
    static final Object o = new Object();

    private static final CountDownLatch latch = new CountDownLatch(1);

    //方法6 Lock+Condition
    static Lock lock = new ReentrantLock();//可重入锁
    static Condition condition1 = lock.newCondition();

    static Condition condition2 = lock.newCondition();


    private static void test1() {
        t1 = new Thread(() -> {
            //            Looper.prepare();
            for (char c : a1) {
                System.out.println(c);
                LockSupport.unpark(t2);
                LockSupport.park();
            }
            //            Looper.loop();
        });
        t2 = new Thread(() -> {
            for (char c : a2) {
                LockSupport.park();
                System.out.println(c);
                LockSupport.unpark(t1);
            }
        });

        t1.start();
        t2.start();

    }

    private static void test2() {
        new Thread(() -> {
            for (char c : a1) {
                while (r != R.T1) {
                }
                System.out.println(c);
                r = R.T2;
            }


        }).start();

        new Thread(() -> {

            for (char c : a2) {
                while (r != R.T2) {
                }
                System.out.println(c);
                r = R.T1;
            }

        }).start();
    }

    private static void test3() {
        new Thread(() -> {
            for (char c : a1) {
                while (flag) {
                }
                System.out.println(c);
                flag = true;
            }


        }).start();

        new Thread(() -> {

            for (char c : a2) {
                while (!flag) {
                }
                System.out.println(c);
                flag = false;
            }

        }).start();
    }

    private static void test4() {
        new Thread(() -> {
            for (char c : a1) {
                System.out.println(c);
                try {
                    q1.put("ok");
                    q2.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }


        }).start();

        new Thread(() -> {

            for (char c : a2) {
                try {
                    q1.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println(c);

                try {
                    q2.put("ok");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }).start();
    }

    private static void test5() {
        new Thread(() -> {
            synchronized (o) {
                for (char c : a1) {
                    System.out.println(c);
                    latch.countDown();//唤醒线程
                    try {
                        o.notify();//唤醒请求该对象锁的任意线程
                        o.wait();//本线程让出锁，并进入等待队列
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                o.notify();//必须，否则线程一直在等待，程序无法执行完
            }

        }, "t1").start();

        new Thread(() -> {
            try {
                latch.await();//先不执行，确保上一个线程先执行
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (o) {
                for (char c : a2) {
                    System.out.println(c);
                    try {
                        o.notify();
                        o.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                o.notify();
            }

        }, "t2").start();
    }


    public static void main(String[] args) {
        //                test1();
        //        test2();
        //        test3();
        //        test4();
        //        test5();
        //        test6();
        //        System.out.println(8>>3);
        int[] array = new int[]{1, 2, 3, 22, 0, 65};
        BitSet bitSet = new BitSet(array.length);
        //将数组内容组bitmap
        for (int i = 0; i < array.length; i++) {
            bitSet.set(array[i], true);
        }
        System.out.println(bitSet.size());
        for (int i = 0; i < bitSet.length(); i++) {
            if (bitSet.get(i))
                System.out.println(i);
        }

    }

    private static void test6() {
        new Thread(() -> {

            try {
                lock.lock();//加锁后其他拥有该锁的线程不能执行
                for (char c : a1) {
                    System.out.println(c);
                    latch.countDown();//唤醒线程2
                    condition1.signal();//唤起
                    condition2.await();
                }
                condition1.signal();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }

        }).start();

        new Thread(() -> {

            //保证上面线程先执行
            try {
                latch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            try {
                lock.lock();
                for (char c : a2) {
                    System.out.println(c);
                    condition2.signal();
                    condition1.await();
                }
                condition2.signal();

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }

        }).start();
    }


}
