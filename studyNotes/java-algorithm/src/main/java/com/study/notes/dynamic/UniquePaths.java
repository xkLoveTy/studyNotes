package com.study.notes.dynamic;


/**
 * 解题思路：
 * 最后一步：无论机器人用何种方式到达右下角，总有最后挪动的那一步：向右或者向下
 * 右下角坐标设为（m-1, n-1）
 * 那么前一步机器人一定是在（m-2, n-1)或者(m-1,n-2)
 *
 * 问题转化为机器人有多少种方式从左上角走到（m-2, n-1)和(m-1,n-2)
 * 原问题要求有多少种方式从左上角走到（m-1, n-1)
 *
 * 状态：设f[i][j]为机器人有多少种方式从左上角走到（i, j）
 *
 * f[i][j] = f[i -1][j] + f[i][j - 1]
 *
 * 初始条件：f[0][0] = 1，机器人只有一种方式走到左上角
 * 边界情况：i=0或者j=0,则前一步只能有一个方向过来 f[i][j] = 1
 *
 */
public class UniquePaths {

    public static void main(String[] args) {
        System.out.println(uniquePaths(4, 5));
    }


    public static int uniquePaths(int m, int n) {
        int[][]f = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 || j == 0) {
                    f[i][j] = 1;
                }
                else {
                    f[i][j] = f[i -1][j] + f[i][j - 1];
                }
            }
        }

        return f[m - 1][n - 1];

    }



}
