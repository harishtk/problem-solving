package others;

import java.util.Arrays;
import java.util.Stack;
import java.util.stream.Stream;

public class MaxArea {
    public static void main(String[] args) {
        // int[][] grid = {
        //     {1, 0, 0, 0, 0},
        //     {1, 1, 1, 1, 1},
        //     {1, 0, 0, 1, 1},
        //     {1, 0, 0, 1, 1}
        // };
        // int[][] grid = {
        //     {1, 0},
        //     {0, 1},
        // };
        // int[][] grid = {
        //     {0, 1, 0, 0, 1}, 
        //     {0, 0, 0, 0, 0}, 
        //     {0, 1, 0, 0, 0}, 
        //     {0, 1, 1, 0, 0}, 
        //     {0, 1, 1, 1, 1}
        // };
        int[][] grid = {
            {0, 1, 1, 0},
            {1, 1, 1, 1},
            {1, 1, 1, 1},
            {1, 1, 0, 0}
        };

        final int m = grid.length;
        final int n = grid[0].length;

        System.out.println(findMaxAreaDp(grid, m, n));
    }

    static int findMaxArea(int[][] grid, int m, int n) {
        int[] height = new int[n];
        int maxArea = 0;
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                height[j] = grid[i][j] == 0 ? 0 : height[j] + 1;
            }
            maxArea = Math.max(maxArea, maxHistogramArea(height));
        }
        
        return maxArea;
    }
    
    static int maxHistogramArea(int[] heights) {
        Stack<Integer> stack = new Stack<>();
        int maxArea = 0;
        int index = 0;
        
        while (index < heights.length) {
            if (stack.isEmpty() || heights[index] >= heights[stack.peek()]) {
                stack.push(index++);
            } else {
                int top = stack.pop();
                int area = heights[top] * (stack.isEmpty() ? index : index - stack.peek() - 1);
                maxArea = Math.max(maxArea, area);
            }
        }
        
        while (!stack.isEmpty()) {
            int top = stack.pop();
            int area = heights[top] * (stack.isEmpty() ? index : index - stack.peek() - 1);
            maxArea = Math.max(maxArea, area);
        }
        
        return maxArea;
    }

    // Using dp with memoization
    static int findMaxAreaDp(int[][] grid, int m, int n) {

        // 2D matrix to store the width of 1's
        // ending at each cell.
        int[][] memo = new int[m][n];
        int result = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) continue;

                // Set the width of 1's at i, j
                if (j == 0) memo[i][j] = 1;
                else memo[i][j] = 1 + memo[i][j - 1];

                int width = memo[i][j];

                // Traverse row by row, update the
                // minimum width and calculate area.
                for (int k = i; k >= 0; k--) {
                    width = Math.min(width, memo[k][j]);
                    int area = width * (i - k + 1);

                    result = Math.max(result, area);
                }
            }
            Util.printMatrix(memo);
            System.out.println("=====================");
        }

        Util.printMatrix(memo);

        return result;
    }
}
