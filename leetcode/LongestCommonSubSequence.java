package leetcode;

import java.util.*;

public class LongestCommonSubSequence {
    public static void main(String[] args) {
        String text1 = "abcde", text2 = "ace";

        System.out.println("Recursive approach -->");
        System.out.println(longestCommonSubsequenceRecursive(text1, text2));

        System.out.println(longestCommonSubsequence(text1, text2));

        System.out.println(longestCommonSubsequence("abc", "def"));
    }

    // Top-Down Dp with Memoization - O(2 ^ (m * n)) - O(m * n)
    public static int longestCommonSubsequenceRecursive(String text1, String text2) {
        int m = text1.length();
        int n = text2.length();
        int[][] memo = new int[m][n];
        for (int[] row : memo) Arrays.fill(row, -1);

        return f(text1, text2, 0, 0, m, n, memo);
    }

    // Recursive 
    static int f(String text1, String text2, int i, int j, int m, int n) {
        if (i == m || j == n) return 0;
        if (text1.charAt(i) == text2.charAt(j)) {
            return 1 + f(text1, text2, i + 1, j + 1, m, n);
        } else {
            return Math.max(
                f(text1, text2, i + 1, j, m, n),
                f(text1, text2, i, j + 1, m, n)
            );
        }
    }

    // Top-Down Dp with Memo
    static int f(String text1, String text2, int i, int j, int m, int n, int[][] memo) {
        if (i == m || j == n) return 0;
        if (memo[i][j] != -1) return memo[i][j];
        if (text1.charAt(i) == text2.charAt(j)) {
            return 1 + f(text1, text2, i + 1, j + 1, m, n);
        } else {
            memo[i][j] = Math.max(
                f(text1, text2, i + 1, j, m, n),
                f(text1, text2, i, j + 1, m, n)
            );
            return memo[i][j];
        }
    }

    // Bottom-Up Dp with Tabulation.
    public static int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length();
        int n = text2.length();
        int[][] dp = new int[m + 1][n + 1];

        // for (int j = 0; j < n; j++) {
        //     dp[0][j] = text1.charAt(0) == text2.charAt(j) ? 1 : 0;
        // }

        // for (int i = 0; i < m; i++) {
        //     dp[i][0] = text1.charAt(i) == text2.charAt(0) ? 1 : 0;
        // }

        Util.printMatrix(dp);
        System.out.println("================");

        for (int i = 1; i < m + 1; i++) {
            for (int j = 1; j < n + 1; j++) {            
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
                Util.printMatrix(dp);
                System.out.println("-------------------");
            }
        }

        Util.printMatrix(dp);
        System.out.println("===================");

        return dp[m][n];
    }

}
