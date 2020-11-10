package com.example.arithmetic.leetcode;

/**
 * @author lgh on 2020/11/10 20:11
 * @description 爬楼梯
 * <p>
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 * <p>
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 * <p>
 * 注意：给定 n 是一个正整数。
 */
public class ClimbStairs_6 {


    public static void main(String[] args) {
        System.out.println("1=============" + climbStairs1(8));//1 1;2 2;3 4;4 1111,121,211,112,22
        System.out.println("2=============" + climbStairs2(5));//1 1;2 2;3 4;4
    }

    /**
     * 动态规划
     * f(x) = f(x-1) + f(x-2)
     * 时间复杂度：循环执行 n 次，每次花费常数的时间代价，故渐进时间复杂度为 O(n)。
     * 空间复杂度：这里只用了常数个变量作为辅助空间，故渐进空间复杂度为 O(1)。
     */
    public static int climbStairs1(int n) {
        int p, q = 0, r = 1;
        for (int i = 0; i < n; i++) {
            p = q;
            q = r;
            r = p + q;
        }
        return r;
    }

    /**
     * 矩阵快速幂
     */
    public static int climbStairs2(int n) {

        return 0;
    }

}
