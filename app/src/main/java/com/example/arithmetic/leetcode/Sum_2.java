package com.example.arithmetic.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lgh on 2020/10/12:20:44
 * @description 两数之和
 * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，
 * 并返回他们的数组下标。
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。
 * <p>
 * 示例： nums = [2, 7, 11, 15], target = 9
 * 因为 nums[0] + nums[1] = 2 + 7 = 9
 * 所以返回 [0, 1]
 */
public class Sum_2 {

    public static void main(String[] args) {
        int[] ints = new int[]{2, 7, 11, 15};
        int target = 9;
        System.out.println("1============" + print(twoSum1(ints, target)));
        System.out.println("2============" + print(twoSum2(ints, target)));

    }

    /**
     * 法一：暴力枚举
     * 时间复杂度：O(N^2)
     * 其中 N 是数组中的元素数量。最坏情况下数组中任意两个数都要被匹配一次。
     * 空间复杂度：O(1)。
     */

    public static int[] twoSum1(int[] nums, int target) {
        int length = nums.length;
        for (int i = 0; i < length; i++) {
            for (int j = i + 1; j < length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[0];
    }

    /**
     * 法二：has表
     * 时间复杂度：O(N)，其中 N 是数组中的元素数量。对于每一个元素 x，
     * 我们可以 O(1) 地寻找 target - x。但由于哈希碰撞的存在，如果存在链表的话，
     * 哈希查找的时间复杂度不一定为 O(1)，
     * <p>
     * 空间复杂度：O(N)，其中 N 是数组中的元素数量。主要为哈希表的开销。
     * <p>
     */

    public static int[] twoSum2(int[] nums, int target) {
        Map<Integer, Integer> hashtable = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (hashtable.containsKey(target - nums[i])) {
                return new int[]{hashtable.get(target - nums[i]), i};
            }
            hashtable.put(nums[i], i);
        }
        return new int[0];
    }

    public static String print(int[] ints) {
        StringBuilder a = new StringBuilder();
        for (int anInt : ints) {
            a.append(anInt).append(",");
        }
        return a.toString().substring(0, a.length() - 1);
    }

}
