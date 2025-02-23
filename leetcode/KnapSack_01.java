package leetcode;

import java.util.Arrays;

public class KnapSack_01 {
    public static void main(String[] args) {
        int m = 8;
        int n = 4;
        int[] profits = {1, 2, 5, 6};
        int[] weights = {2, 3, 4, 5};

        System.out.println("Top-Down Recursion: ");
        System.out.println(maxProfitRecursive(m, weights, profits, n)   );

        int[][] memo = new int[n + 1][m + 1];
        for (int[] row : memo) Arrays.fill(row, -1);
        System.out.println("Top-Down Dp With Memoization: ");
        System.out.println(maxProfitRecursive(m, weights, profits, n, memo));

        System.out.println("Top-Down Dp with Tabulation: ");
        System.out.println(maxProfit(m, n, profits, weights));

        System.out.println("Space Optimized Dp: ");
        System.out.println(maxProfitDp2(m, n, weights, profits) );
    }

    // Top-Down Recursive approach - O(2^n) - O(n)
    static int maxProfitRecursive(int capacity, int[] weights, int[] profits, int n) {
        if (n == 0 || capacity == 0) {
            return 0;
        }

        if (weights[n - 1] > capacity) {
            return maxProfitRecursive(capacity, weights, profits, n - 1);
        }

        return Math.max(
            maxProfitRecursive(capacity, weights, profits, n - 1),
            profits[n - 1] + maxProfitRecursive(capacity - weights[n - 1], weights, profits, n - 1));
    }

    // Top-Down Dp with Memo - O(n * m) - O(n * m)
    static int maxProfitRecursive(int capacity, int[] weights, int[] profits, int n, int[][] memo) {
        if (n == 0 || capacity == 0) {
            return 0;
        }

        if (weights[n - 1] > capacity) {
            return maxProfitRecursive(capacity, weights, profits, n - 1, memo);
        }

        if (memo[n][capacity] != -1) {
            return memo[n][capacity];
        }

        memo[n][capacity] = Math.max(
            maxProfitRecursive(capacity, weights, profits, n - 1, memo),
            profits[n - 1] + maxProfitRecursive(capacity - weights[n - 1], weights, profits, n - 1, memo));

        return memo[n][capacity];
    }

    // Bottum-Up Dp with Tabulation - O(n * m) - O(n * m)
    static int maxProfit(int m, int n, int[] profits, int[] weights) {
        int[][] dp = new int[n + 1][m + 1];
        
        // Fill first row
        Arrays.fill(dp[0], 0);
        // Fill first col
        for (int i = 0; i <= n; i++) {
            dp[i][0] = 0;
        }

        // int[] profits = {1, 2, 5, 6};
        // int[] weights = {2, 3, 4, 5};

        // Fill the remaining cells 
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (weights[i - 1] <= j) {
                    dp[i][j] = Math.max(profits[i - 1] + dp[i - 1][j - weights[i - 1]], dp[i - 1][j]);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        Util.printMatrix(dp);

        return dp[n][m];
    }

    // Space-optimized Dp approach
    static int maxProfitDp2(int m, int n, int[] weights, int[] profits) {
        int[] dp = new int[m + 1];

        // int[] profits = {1, 2, 5, 6};
        // int[] weights = {2, 3, 4, 5};
        
        for (int i = 1; i < n + 1; i++) {
            for (int j = m; j >= 0; j--) {
                Util.printArr(dp);
                if (weights[i - 1] <= j) {
                    dp[j] = Math.max(dp[j], dp[j - weights[i - 1]] + profits[i - 1]);
                }
            }
        }

        return dp[m];
    }
}
