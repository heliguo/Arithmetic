package com.example.arithmetic.dynamicprograming;

/**
 * author:lgh on 2020/6/9 11:39
 */
class Digitaltriangle {
    int[][] a = new int[5][5];

    private void set(){
        a[0][0]=7;
        a[1][0]=3;a[1][1]=8;
        a[2][0]=8;a[2][1]=1;a[2][2]=0;
        a[3][0]=2;a[3][1]=7;a[3][2]=4;a[3][3]=4;
        a[4][0]=4;a[4][1]=5;a[4][2]=2;a[4][3]=3;a[4][4]=1;
    }

}
