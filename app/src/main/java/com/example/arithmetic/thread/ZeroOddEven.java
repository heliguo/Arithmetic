package com.example.arithmetic.thread;

import android.os.Build;

import androidx.annotation.RequiresApi;

/**
 * @author lgh on 2020/12/8 18:31
 * @description 打印 0 奇 偶 数
 */
public class ZeroOddEven {

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static void main(String[] args) {
        ZeroEvenOdd zeroEvenOdd = new ZeroEvenOdd(6);
        new Thread(() -> {
            try {
                zeroEvenOdd.zero(System.out::print);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                zeroEvenOdd.even(System.out::print);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                zeroEvenOdd.odd(System.out::print);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

    }


}
