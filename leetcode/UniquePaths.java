package leetcode;

import java.util.Arrays;

/**
 * 62. Unique Paths
 * 
 * There is a robot on an m x n grid. The robot is initially located at the 
 * top-left corner (i.e., grid[0][0]). The robot tries to move to the bottom-right 
 * corner (i.e., grid[m - 1][n - 1]). The robot can only move either down or right 
 * at any point in time.
 * 
 * Given the two integers m and n, return the number of possible unique paths
 * that the robot can take to reach the bottom-right corner.
 */
public class UniquePaths {
    public static void main(String[] args) {
        int m = 3, n = 7;
        
        System.out.println("Naive Recursive -->");
        System.out.println(f(m - 1, n - 1, m, n));

        int[][] memo = new int[m][n];
        for (int[] row : memo) Arrays.fill(row, -1);
        System.out.println("Naive Recursive with Memo -->");
        System.out.println(f(m - 1, n - 1, m, n, memo));

        System.out.println("Bottom-Up Dp with Tabulation -->");
        System.out.println(dpWithTabulation(m, n));

        System.out.println("Bottom-Up Dp with Tabulation optimized -->");
        System.out.println(dpWithTabulationOptimized(m, n));
    }

    // Naive Recursive approach - O(2^m*n) - O(m + n)
    static int f(int i, int j, int m, int n) {
        if (i < 0 || i > m || j < 0 || j > n) { return 0; }
        else if (i == 0 && j == 0) { return 1; }
        else { 
            return f(i - 1, j, m, n) + f(i, j - 1, m, n);
        }
    }

    // Naive Recursive approach - O(2^m*n) - O(m + n)
    static int f(int i, int j, int m, int n, int[][] memo) {
        if (i < 0 || i > m || j < 0 || j > n) { return 0; }
        else if (i == 0 && j == 0) { return 1; }
        else if (memo[i][j] != -1) { return memo[i][j]; }
        else { 
            memo[i][j] = f(i - 1, j, m, n, memo) + f(i, j - 1, m, n, memo);
            return memo[i][j];
        }
    }

    // Bottom-Up Dp with Tabulation - O(m * n) - O(m * n)
    static int dpWithTabulation(int m, int n) {
        int[][] dp = new int[m][n];

        // Populate first row
        for (int j = 0; j < n; j++) {
            dp[0][j] = 1;
        }

        // Populate first col
        for (int i = 0; i < m; i++) {
            dp[i][0] = 1;
        }

        // Let us visit on every path and increment the overlapping cells.
        for (int i = 1; i < m; i++) {;
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }

        Util.printMatrix(dp);
        return dp[m - 1][n - 1];
    }

    // Bottom-Up Dp with Tabulation (Optimized) - O(m * n) - O(m + n)
    // Intuition: For this problem we only need values of previous 
    // row and previous cols.
    static int dpWithTabulationOptimized(int m, int n) {
        int[] prevRow = new int[n];
        int[] prevCol = new int[m];

        // Populate first row
        for (int j = 0; j < n; j++) {
            prevRow[j] = 1;
        }

        // Populate first col
        for (int i = 0; i < m; i++) {
            prevCol[i] = 1;
        }

        // Let us visit on every path and increment the overlapping cells.
        int[] currRow = new int[n];
        int[] currCol = new int[m];
        for (int i = 1; i < m; i++) {
            currCol[i] = currCol[i] + prevCol[i - 1];
            for (int j = 1; j < n; j++) {
                currRow[j] = currRow[j] + prevRow[j - 1];
            }
            prevRow = currRow;
            prevCol = currCol;
        }

        // Util.printMatrix(dp);
        Util.printArr(prevRow);
        Util.printArr(prevCol);
        return prevRow[n - 1] + prevCol[m - 1];
    }
}
