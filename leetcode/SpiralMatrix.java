package leetcode;

import java.util.ArrayList;
import java.util.List;

import others.Util;

public class SpiralMatrix {
    public static void main(String[] args) {
        int[][] mat = { { 1, 2, 3, 4 },
                { 5, 6, 7, 8 },
                { 9, 10, 11, 12 },
                { 13, 14, 15, 16 } };

        int m = mat.length;
        int n = mat[0].length;

        // List<Integer> res = walkMatrix(mat);
        // System.out.println(res);
        
        // List<Integer> result = new ArrayList<>();
        // walkMatrixRecursive(mat, m, n, 0, result);
        // System.out.println(result);

        System.out.println(walkMatrixWithBoundary(mat));
    }

    // Using simulation with visited array
    static List<Integer> walkMatrix(int[][] mat) {
        
        int m = mat.length;
        int n = mat[0].length;
        
        final List<Integer> result = new ArrayList<>();
        boolean[][] visited = new boolean[m][n];

        // Change in row and col for each direction
        int[] dr = {0, 1, 0, -1};
        int[] dc = {1, 0, -1, 0};

        // Initial position of the matrix
        int r = 0, c = 0;

        // Initial direction index (0 means 'right')
        int idx = 0;

        for (int i = 0; i < m * n; i++) {

            // Add current element to the result list
            result.add(mat[r][c]);

            // Mark the cell as visited
            visited[r][c] = true;

            // Calculate the next cell coordinates based on
            // current direction;
            int newR = r + dr[idx];
            int newC = c + dc[idx];

            // Check if the next cell is within bounds and not
            // visited
            if (0 <= newR && newR < m && 0 < newC && newC < n &&
                    !visited[newR][newC]) {
                // Move to the next row and col
                r = newR;
                c = newC;
            } else {
                // Change the direction (clockwise)
                idx = (idx + 1) % 4;
                
                // Move to the next row according to new 
                // direction
                r += dr[idx];
                c += dc[idx];
            }
        }

        return result;
    }

    static List<Integer> walkMatrixWithBoundary(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;
        
        final List<Integer> result = new ArrayList<>();

        // Initialize boundaries
        int top = 0, bottom = m - 1, left = 0, right = n - 1;

        // Iterate until all elements are printed
        while (top <= bottom && left <= right) {

            // walk top row from left to right
            for (int j = left; j <= right; j++) result.add(mat[top][j]);
            top++;

            // walk right col from top to bottom
            for (int i = top; i <= bottom; i++) result.add(mat[i][right]);
            right--;

            // walk bottom row from right to left
            if (top <= bottom) {
                for (int j = right; j >= left; j--) result.add(mat[bottom][j]);
                bottom--;
            }
            
            // walk left col from bottom to top
            if (left <= right) {
                for (int i = bottom; i >= top; i--) result.add(mat[i][left]); 
                left++;
            }
        }

        return result;
    }

    static void walkMatrixRecursive(int[][] mat, int m, int n, int level, List<Integer> result) {

        // Base case
        if (level >= m / 2 && level >= n / 2) return;

        int left = level;
        int right = n - 1 - level;
        int top = level;
        int bottom = m - 1 - level;

        for (int j = left; j < right; j++) result.add(mat[top][j]);
        for (int i = top; i < bottom; i++) result.add(mat[i][right]);
        for (int j = right; j > left; j--) result.add(mat[bottom][j]);
        for (int i = bottom; i > top; i--) result.add(mat[i][left]);
        
        walkMatrixRecursive(mat, m, n, level + 1, result);
    }
}
