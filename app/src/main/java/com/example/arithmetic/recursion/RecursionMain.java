package com.example.arithmetic.recursion;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author lgh on 2020/7/16:9:12
 * @description 递归 归并排序
 */
public class RecursionMain {

    private static final long[] rewards = new long[]{
            1, 2, 5, 10
    };

    /**
     * 递归调用
     *
     * @param total  输入条件 总奖金
     * @param result 保存结果
     */
    public static void get(long total, ArrayList<Long> result) {
        if (total == 0) {
            System.out.println(result);
        } else {
            if (total >= 0) {
                for (long reward : rewards) {
                    ArrayList<Long> newResult = (ArrayList<Long>) result.clone();
                    newResult.add(reward);
                    get(total - reward, newResult);
                }
            }
        }

    }

    private static final int[] num = new int[]{
            1, 2, 3, 4, 5, 6, 7, 8, 9
    };

    public static void getProduct(int src, ArrayList<Integer> result) {
        if (src == 1) {
            if (!result.contains(1)) {
                result.add(1);
            }
            System.out.println(result);
        } else {
            if (src > 1) {
                for (int i : num) {
                    if (src % i != 0) {
                        continue;
                    }
                    ArrayList<Integer> newResult = ((ArrayList<Integer>) result.clone());
                    if (i == 1 && newResult.contains(1)) {
                        continue;
                    }
                    newResult.add(i);
                    getProduct(src / i, newResult);
                }
            }
        }

    }

    public static int[] merge_sort(int[] src) {
        if (src.length == 0)
            return new int[0];
        if (src.length == 1)
            return src;
        int mid = src.length / 2;
        int[] left = Arrays.copyOfRange(src, 0, mid);
        int[] right = Arrays.copyOfRange(src, mid, src.length);
        left = merge_sort(left);
        right = merge_sort(right);
        return merge(left, right);
    }

    public static int[] merge(int[] left, int[] right) {
        if (left == null)
            left = new int[0];
        if (right == null)
            right = new int[0];

        int[] merge = new int[left.length + right.length];
        int mi = 0, li = 0, ri = 0;
        while (li < left.length && ri < right.length) {
            if (left[li] < right[ri]) {
                merge[mi] = left[li];
                li++;
            } else {
                merge[mi] = right[ri];
                ri++;
            }
            mi++;
        }
        if (li < left.length) {
            for (int i = li; i < left.length; i++) {
                merge[mi] = left[i];
                mi++;
            }
        }
        if (ri < right.length) {
            for (int i = ri; i < right.length; i++) {
                merge[mi] = right[i];
                mi++;
            }
        }
        return merge;
    }

    public static void main(String[] args) {
        ArrayList<Long> arrayList = new ArrayList<>();
        ArrayList<Integer> arrays = new ArrayList<>();
        //        get(11, arrayList);
        //        getProduct(8, arrays);
        int[] a = new int[]{
                89, 56, 12, 124, 1, 2444, 55, 457, 3, 898, 55, 44
        };
        int[] ints = merge_sort(a);
        for (int anInt : ints) {
            System.out.println(anInt);
        }

    }

}
