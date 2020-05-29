package com.example.arithmetic.summation;

import android.util.Log;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lgh on 2020/5/29 10:03
 * @description  数组
 */
public class ArraySum {

    private int[] nums   = new int[]{2, 7, 11, 15};
    private int   target = 9;

    /**
     * 自解1 时间复杂度O(n^2) 空间复杂度O(1)
     */
    public void sum1() {
        for (int i = 0; i < nums.length; i++) {
            int index = i + 1;//保证了不会用同一个元素
            for (int j = index; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    Log.e("TAG", "sum1: " + nums[i] + nums[j]);
                    break;
                }
            }
        }
    }

    /**
     * LeetCode 解法1
     *
     * @param nums
     * @param target
     * @return 同 自解1
     */
    public int[] twoSum1(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] == target - nums[i]) {
                    return new int[]{i, j};
                }
            }
        }
        throw new IllegalArgumentException("No two sum solution");
    }

    /**
     * LeetCode 解法2 两遍哈希表  时间复杂度O(n) 2n+1
     * 空间复杂度O(n)
     * 以空间换取时间
     */
    public int[] twoSum2(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement) && map.get(complement) != i) {
                return new int[]{i, map.get(complement)};
            }
        }
        throw new IllegalArgumentException("No two sum solution");
    }

    /**
     * LeetCode 解法3 1遍哈希表  时间复杂度O(n) n+1
     * 空间复杂度O(n)
     * 以空间换取时间
     */
    public int[] twoSum3(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)&& map.get(complement) != i) {
                return new int[] { map.get(complement), i };
            }
            map.put(nums[i], i);
        }
        throw new IllegalArgumentException("No two sum solution");
    }


}
