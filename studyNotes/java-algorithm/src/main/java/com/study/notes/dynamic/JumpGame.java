package com.study.notes.dynamic;

/**
 * 题目：有n块石头分别在X轴的0，1...n-1位置，一只青蛙在石头0，想跳到石头n-1
 * 如果青蛙在第i块石头上，他最多可以向右跳举例ai，问青蛙是否能跳到石头n-1
 * 例子：
 * 输入：a=[2,3,1,1,4]
 * 输出：true
 *
 *  确定状态：
 *  最后一步：如果青蛙能跳到最后一块石头n-1，考虑最后一跳
 *  这一步是从石头i跳过来，i<n-1
 *  这需要同时满足2个条件：
 *  （1）青蛙可以跳到石头i
 *  （2）最后一步不超过跳跃的最大距离：n-1-i<=ai
 *
 *  子问题：
 *  青蛙能不能跳到石头i（i < n - 1）
 *  原问题：青蛙能不能跳到石头i（i < n - 1）
 *
 *  状态：设f(j)表示青蛙能不能跳到石头j
 *
 *  转移方程：
 *  f[j] = OR0<=i<j(f[i] And i + a[i] >= j)
 *  OR 0<=i<j : 枚举上一个跳到的石头i，只要有一个i满足(f[i] And i + a[i] >= j)
 *
 *  初始条件：f[0] = true,因为青蛙开始在石头0
 *
 *
 *
 *
 */
public class JumpGame {


    public static void main(String[] args) {
        int[] a = {2, 3, 1, 1, 4};

        System.out.println(canJump(a));
    }


    public static boolean canJump(int[] a) {
        boolean[] f = new boolean[a.length];

        f[0] = true;

        for (int j = 1; j < a.length; j++) {
            f[j] = false;
            //previous stone i
            //last jump is from i to j
            for (int i = 0; i < j; i++) {
                if (f[i] && i + a[i] >= j) {
                    f[j] = true;
                }
            }
        }


        return f[a.length - 1];
    }
}
