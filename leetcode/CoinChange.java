package leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CoinChange {
    public static void main(String[] args) {
        int[] coins = {1, 2, 5};
        int amount = 11;

        System.out.println("Top-Down Naive Recursion -->");
        System.out.println(f(coins, amount));

        System.out.println("Top-Down Dp with Memoization -->");
        Map<Integer, Integer> memo = new HashMap<>();
        memo.put(0, 0);

        int res = f(coins, amount, memo);
        System.out.println(res == Integer.MAX_VALUE ? -1 : res);

        System.out.println("Bottom-Up Dp with Tabulation");
        System.out.println(minCoins(new int[] {186, 419, 83, 408}, 6249));
        System.out.println(minCoins(new int[] {2}, 3));
    }

    // Top-Down Naive recursion
    static int f(int[] coins, int amount) {
        if (amount == 0) { return 0; }
        else if (amount < 0) { return -1; }

        int minCount = -1;
        for (int coin : coins) {
            int count = f(coins, amount - coin);
            if (count >= 0) {
                minCount = minCount < 0 ? count + 1 : Math.min(minCount, count + 1); 
            }
        }
        return minCount;
    }

    // Top-Down Dp with memoization
    static int f(int[] coins, int amt, Map<Integer, Integer> memo) {
        if (memo.containsKey(amt)) { return memo.get(amt); }

        int min = Integer.MAX_VALUE;
        for (int i = 0; i < coins.length; i++) {
            int coin = coins[i];
            int diff = amt - coin;
            if (diff < 0) break;
            min = Math.min(min, 1 + f(coins, diff, memo));
        }

        memo.put(amt, min);
        return min;
    }

    // Bottom-Up Dp with Tabulation
    static int minCoins(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        Arrays.sort(coins);

        for (int i = 1; i < amount + 1; i++) {

            for (int coin : coins) {
                if (i - coin < 0) break;
                if (dp[i - coin] != Integer.MAX_VALUE) {
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1); 
                }
            }
        }

        if (dp[amount] != Integer.MAX_VALUE) {
            return dp[amount];
        } else {
            return -1;
        }
    }
}
