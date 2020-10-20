package com.example.arithmetic;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author lgh on 2020/10/20:19:40
 * @description 生成不重复的随机数
 */
public class RandomCoder {

    public static void main(String[] args) {
        System.out.println(randomDiff(12));

    }

    public static List<Integer> randomDiff(int n) {
        List<Integer> list = new ArrayList<>();
        Random rand = new Random(System.currentTimeMillis());
        boolean[] bool = new boolean[n];
        int num;
        for (int i = 0; i < n; i++) {
            do {
                // 如果产生的数相同继续循环
                num = rand.nextInt(n);
            } while (bool[num]);
            bool[num] = true;
            list.add(num);
        }
        return list;

    }


}
