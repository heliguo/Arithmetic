package com.example.arithmetic.dynamicprograming;

import java.util.Arrays;

/**
 * author:lgh on 2020/6/9 9:58
 */
class Main {

    private int result(int n, int m) {
        int[][] a = new int[n][m];
        for (int i = 0; i < n; i++) {
            a[i][0] = 1;
        }
        for (int j = 0; j < m; j++) {
            a[0][j] = 1;
        }
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                a[i][j] = a[i - 1][j] + a[i][j - 1];
            }
        }
        return a[n - 1][m - 1];
    }

    public int uniquePaths(int m, int n) {
        int[][] grid = new int[m][n];
        for (int i = 0; i < m; i++) {
            grid[i][0] = 1;
        }
        for (int i = 0; i < n; i++) {
            grid[0][i] = 1;
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                grid[i][j] = grid[i - 1][j] + grid[i][j - 1];
            }
        }
        return grid[m - 1][n - 1];
    }


    /**
     * 从解法二可以看出，我们求一个点需要的是它头上的那个点和它本行左边的那个点，只要有这两个点，
     * 那么就能够计算出当前点
     * 所以可以直接将两行数据优化为一行，每次循环都会提前计算它左边的点，
     * 这个左边的点就可以理解成本行左边的那个点，而因为
     * 当前点还未进行计算，这个位置上实际存储的数据是它头上的那个点（上一行）的数据，
     * 因此空间复杂度优化为O(n)
     */

    public int uniquePaths3(int m, int n) {
        int[] arr = new int[n];
        Arrays.fill(arr, 1);
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                arr[j] += arr[j - 1];
            }
        }
        return arr[n - 1];
    }

    public static void main(String[] args) {
        Main main = new Main();
        int result = main.result(3, 4);
        int r1 = main.uniquePaths(3, 4);
        int r3 = new Main().uniquePaths3(4, 3);
        System.out.println(result);
        System.out.println(r1);
        System.out.println(r3);
    }


}
