package leetcode;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class MinPathSum {
    
    public static void main(String[] args) {
        final int[][] grid = new int[][] {
            {1, 3, 1},
            {1, 5, 1},
            {4, 2, 1}
        };   

        System.out.println("Input: ");
        printMatrix(grid);
        System.out.println("==============");
        
        final MinPathSum app = new MinPathSum();

        // System.out.println(app.minPath(grid));

        final int m = grid.length;
        final int n = grid[0].length;
        System.out.println(app.minPathRecursive(grid, m - 1, n - 1));

        // final int m = grid.length;
        // final int n = grid[0].length;
        // final int[][] memo = new int[m][n];

        // for (int i = 0; i < m; i++) {
        //     for (int j = 0; j < n; j++) {
        //         memo[i][j] = -1;
        //     }
        // }
        // System.out.println(app.minPathRecursiveWithMemo(grid, m - 1, n - 1, memo));

        // System.out.println(app.minPathSumDp(grid));
        // System.out.println(app.minPathSumDp2(grid));

        // System.out.println(app.minPathSumShortestPath(grid));

        // System.out.println(app.minPathSumFast(grid));
    }

    // Simple dp without memo
    private int minPath(int[][] grid) {
        final int m = grid.length;
        final int n = grid[0].length;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) continue;

                int leftPath = Integer.MAX_VALUE, upPath = Integer.MAX_VALUE;
                if (i != 0) {
                    upPath = grid[i][j] + grid[i - 1][j]; 
                } 
                if (j != 0) {
                    leftPath = grid[i][j] + grid[i][j - 1];
                }

                grid[i][j] = Math.min(leftPath, upPath);
            }
        }

        return grid[m - 1][n - 1];
    }

    // Recursive
    private int minPathRecursive(int[][] grid, int m, int n) {
        System.out.println(String.format("m=%d n=%d", m, n));

        // If the indices are out of bounds return max value
        if (m < 0 || n < 0) {
            return Integer.MAX_VALUE;
        }

        // Base case
        if (m == 0 && n == 0) {
            return grid[m][n];
        }

        System.out.println(grid[m][n]);

        // Recursively calculate the minimum sum from all possible paths
        return grid[m][n] + Math.min(
            minPathRecursive(grid, m - 1, n), 
            minPathRecursive(grid, m, n - 1));
    }

    // Recursion with memoization
    private int minPathRecursiveWithMemo(int[][] grid, int m, int n, int[][] memo) {
        // If the indices are out of bounds return max value
        if (m < 0 || n < 0) {
            return Integer.MAX_VALUE;
        }

        // Base case 
        if (m == 0 && n == 0) {
            return grid[m][n];
        }

        // Check for already calculated values
        if (memo[m][n] != -1) {
            return memo[m][n];
        }

        memo[m][n] = grid[m][n] + Math.min(
            minPathRecursiveWithMemo(grid, m - 1, n, memo), 
            minPathRecursiveWithMemo(grid, m, n - 1, memo));
        return memo[m][n];
    }

    // Dp 
    private int minPathSumDp(int[][] grid) {
        final int m = grid.length;
        final int n = grid[0].length;

        final int[][] dp = new int[m][n];
        dp[0][0] = grid[0][0];

        // Populate first row
        for (int j = 1; j < n; j++) {
            dp[0][j] = dp[0][j - 1] + grid[0][j]; 
        }

        // Populate first col
        for (int i = 1; i < m; i++) {
            dp[i][0] = dp[i - 1][0] + grid[i][0];
        }

        // Calculate the min path cost for rest of the cells
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = grid[i][j] + Math.min(
                    dp[i - 1][j], dp[i][j - 1]);
            }
        }

        return dp[m - 1][n - 1];
    }

    // Min path sum optimized Dp
    private int minPathSumDp2(int[][] grid) {
        final int m = grid.length;
        final int n = grid[0].length;

        // The cost calculation only depends on
        // the last row. 
        final int[] dp = new int[n];
        dp[0] = grid[0][0];

        // Populate first row
        for (int j = 1; j < n; j++) {
            dp[j] = dp[j - 1] + grid[0][j];
        }

        // Calculate the cost for rest of the cells
        for (int i = 1; i < m; i++) {
            
            // Update the first col only depends up on the 
            // previous row
            dp[0] = dp[0] + grid[i][0];

            for (int j = 1; j < n; j++) {
                
                dp[j] = Math.min(
                    dp[j] + grid[i - 1][j], 
                    dp[j] + grid[i][j - 1]);
            }
        }

        return dp[n - 1];
    }

    // Using Dijkshtra's algorithm for shorest path finding.
    private int minPathSumShortestPath(int[][] grid) {
        final int m = grid.length;
        final int n = grid[0].length;

        // Projectory directions
        int[][] directions = {
            {-1, 0}, {1, 0},
            {0, -1}, {0, 1},
        };

        final PriorityQueue<Cell> pq = new PriorityQueue<>(
            Comparator.comparingInt(a -> a.cost)
        );

        // distance matrix to store the min cost to
        // reach each cell
        int[][] dist = new int[m][n];
        for (int[] row : dist) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        dist[0][0] = grid[0][0];

        pq.offer(new Cell(0, 0, grid[0][0]));

        // Dijkshtra's algorithm
        while (!pq.isEmpty()) {
            final Cell curr = pq.poll();
            int x = curr.x;
            int y = curr.y;

            // If we reached the bottom-right corner, return
            // the cost
            if (x == m - 1 && y == n - 1) {
                return dist[x][y];
            }

            printMatrix(dist);
            // Explore the neighbors
            for (int[] dirs : directions) {
                int newX = x + dirs[0];
                int newY = y + dirs[1];

                // Ensure the new cell is within bounds
                if (newX >= 0 && newX < m &&
                        newY >= 0 && newY < n) {
                    
                    // Relaxation step
                    if (dist[newX][newY] > dist[x][y] + grid[newX][newY]) {
                        dist[newX][newY] = dist[x][y] + 
                            grid[newX][newY];

                        pq.offer(new Cell(newX, newY, dist[newX][newY]));
                    }
                }
            }
        }

        return dist[m - 1][n - 1];
    }

    private int minPathSumFast(int[][] grid) {
        final int m = grid.length;
        final int n = grid[0].length;

        fill(0, 0, m, n, grid);
        printMatrix(grid);
        return grid[m - 1][n - 1];
    }

    private void fill(int r, int c, int m, int n, int[][] grid) {
        // Base case
        if (r >= m && c >= n) return;

        if (r != 0 && c != 0) grid[r][c] = grid[r][c] + Math.min(grid[r - 1][c], grid[r][c - 1]);

        for (int i = r + 1; i < m; i++) {
            if (c == 0) grid[i][c] = grid[i][c] + grid[i - 1][c];
            else grid[i][c] += Math.min(grid[i - 1][c], grid[i][c - 1]);
        }

        for (int j = c + 1; j < n; j++) {
            if (r == 0) grid[r][j] = grid[r][j] + grid[r][j - 1];
            else grid[r][j] += Math.min(grid[r][j - 1], grid[r - 1][j]);
        }

        fill(r + 1, c + 1, m, n, grid);
    }
    
    public static void printMatrix(int[][] matrix) {
        int maxLen = 3;

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                maxLen = Math.max(maxLen, String.valueOf(matrix[i][j]).length());
            }
        }
        maxLen++;

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                final int cell = matrix[i][j];
                final int spaces = maxLen - String.valueOf(cell).length();
                System.out.print(String.format("%d", cell));
                for (int k = 0; k < spaces; k++) System.out.print(" ");
            }
            System.out.println();
        }
    }

    class Cell {
        int x, y, cost;

        Cell(int x, int y, int cost) {
            this.x = x;
            this.y = y;
            this.cost = cost;
        }
    }
}
