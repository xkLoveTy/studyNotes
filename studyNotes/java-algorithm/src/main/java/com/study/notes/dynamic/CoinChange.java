package com.study.notes.dynamic;


/**
 *  f(x) = min {f(x - 2) + 1, f(x - 5) + 1, f(x - 7) + 1}
    f(x - 2)表示拼出x - 2元所需最少的硬币数
 */
public class CoinChange {

    public static void main(String[] args) {
        int[] coins = {2, 5, 7};
        int amount = 27;

        System.out.println(coinChange(coins, amount));

        System.out.println(coinChangeRecursion(amount));
    }


    /**
     *
     * @param coins a list of integer
     * @param amount a total mount of money amount
     * @return the fewest number of coins that you need to make
     */
    public static int coinChange(int[] coins, int amount) {
        int[] f = new int[amount + 1];
        int n = coins.length; //num of kinds of coins

        f[0] = 0;

        for (int i = 1; i <= amount; i++) {
            f[i] = Integer.MAX_VALUE;

            for (int j = 0; j < n; j ++) {
                if (i >= coins[j] && f[i - coins[j]] != Integer.MAX_VALUE) {
                    f[i] = Math.min(f[i - coins[j]] + 1, f[i]);
                }
            }
        }

        if (f[amount] == Integer.MAX_VALUE) {
            return -1;
        }

        return f[amount];
    }

    /**
     *
     * @param amount
     * @return
     */
    public static int coinChangeRecursion(int amount) {
        if (amount == 0) {
            return 0;
        }

        int res = Integer.MAX_VALUE;

        if (amount >= 2) {
            res = Math.min(res, coinChangeRecursion(amount - 2) + 1);
        }

        if (amount >= 5) {
            res = Math.min(res, coinChangeRecursion(amount - 5) + 1);
        }

        if (amount >= 7) {
            res = Math.min(res, coinChangeRecursion(amount - 7) + 1);
        }

        return res;
    }
}
