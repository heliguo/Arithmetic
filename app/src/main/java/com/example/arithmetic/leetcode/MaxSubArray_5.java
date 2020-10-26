package com.example.arithmetic.leetcode;

/**
 * @author lgh on 2020/10/26 19:50
 * @description 最大子序和
 * <p>
 * 输入: [-2,1,-3,4,-1,2,1,-5,4]
 * 输出: 6
 * 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
 */
public class MaxSubArray_5 {

    public static void main(String[] args) {

        int[] a = new int[]{-2, 1, -3, 4, -1, 2, 1, -5, -4};
        int[] b = new int[]{-2, 3, -1, 1, -3};

        System.out.println("1 ============== " + maxSubArray1(a));
        System.out.println("2 ============== " + maxSubArray(b));

    }

    /**
     * 法一：贪心算法
     * 若当前指针所指元素之前的和小于0，则丢弃当前元素之前的数列
     * 空间复杂度：O(1),时间复杂度O(n)
     */
    private static int maxSubArray1(int[] a) {
        int cur_sum = a[0];
        int max_sum = a[0];
        for (int value : a) {
            cur_sum = Math.max(value, cur_sum + value);
            max_sum = Math.max(cur_sum, max_sum);
        }
        return max_sum;

    }

    /**
     * 法二：动态规划
     * 空间复杂度：O(1),时间复杂度O(n)
     * f(i)=max{f(i−1)+ai,ai}
     */
    private static int maxSubArray(int[] nums) {

        int pre = 0, maxAns = nums[0];
        for (int x : nums) {
            pre = Math.max(pre + x, x);
            maxAns = Math.max(maxAns, pre);
        }
        return maxAns;

    }

}
