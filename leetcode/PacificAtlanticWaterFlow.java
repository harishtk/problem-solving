package leetcode;

import java.util.*;

public class PacificAtlanticWaterFlow {
    public static void main(String[] args) {
        int[][] heights = {
            // pacific (top and left)
            {1, 2, 2, 3, 5},
            {3, 2, 3, 4, 4},
            {2, 4, 5, 3, 1},
            {6, 7, 1, 4, 5},
            {5, 1, 1, 2, 4}
            // atlantic (bottom and right)
        };

        System.out.println(pacificAtlantic(heights));
    }

    public static List<List<Integer>> pacificAtlantic(int[][] heights) {
        List<List<Integer>> result = new ArrayList<>();
        if (heights == null || heights.length == 0 || heights[0].length == 0) return result;

        int m = heights.length;
        int n = heights[0].length;

        boolean[][] pacific     = new boolean[m][n];
        boolean[][] atlantic    = new boolean[m][n];

        Queue<int[]> pacQueue   = new LinkedList<>();
        Queue<int[]> atlQueue   = new LinkedList<>();

        // Fill in pacific border cells
        for (int i = 0; i < m; i++) {
            pacQueue.add(new int[]{i, 0});
            pacific[i][0] = true;
        }

        for (int j = 0; j < n; j++) {
            pacQueue.add(new int[]{0, j});
            pacific[0][j] = true;
        }

        // Fill in atlantic border cells
        for (int i = 0; i < m; i++) {
            atlQueue.add(new int[]{i, n - 1});
            atlantic[i][n - 1] = true;
        }

        for (int j = 0; j < n; j++) {
            atlQueue.add(new int[]{m - 1, j});
            atlantic[m - 1][j] = true;
        }

        bfs(pacQueue, pacific, heights);
        bfs(atlQueue, atlantic, heights);

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (pacific[i][j] && atlantic[i][j]) {
                    result.add(Arrays.asList(i, j));
                }
            }
        }

        return result;
    }

    static void bfs(Queue<int[]> queue, boolean[][] ocean, int[][] heights) {
        int m = heights.length;
        int n = heights[0].length;
        int[][] dirs = {{0, 1}, {1, 0}, {0, - 1}, {-1, 0}};

        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            int r = cell[0];
            int c = cell[1];

            for (int[] dir : dirs) {
                int newR = r + dir[0];
                int newC = c + dir[1];
                
                if (newR >= 0 && newR < m && newC >= 0 && newC < n && 
                    !ocean[newR][newC] && heights[newR][newC] >= heights[r][c]) {
                    ocean[newR][newC] = true;
                    queue.add(new int[]{newR, newC});
                }
            }
        }
    }
}
