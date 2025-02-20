package leetcode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Set;
import java.util.Stack;

public class MaxAreaIsland {
    public static void main(String[] args) {
        int[][] grid = {
            {0,0,1,0,0,0,0,1,0,0,0,0,0},
            {0,0,0,0,0,0,0,1,1,1,0,0,0},
            {0,1,1,0,1,0,0,0,0,0,0,0,0},
            {0,1,0,0,1,1,0,0,1,0,1,0,0},
            {0,1,0,0,1,1,0,0,1,1,1,0,0},
            {0,0,0,0,0,0,0,0,0,0,1,0,0},
            {0,0,0,0,0,0,0,1,1,1,0,0,0},
            {0,0,0,0,0,0,0,1,1,0,0,0,0}
        };

        System.out.println(maxAreaOfIsland(grid));
        // System.out.println(maxAreaOfIslandIterDfs(grid));
        // System.out.println(maxAreaOfIslandBfs(grid));
    }

    public static int maxAreaOfIsland(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        int maxArea = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    int area = dfs(grid, i, j, m, n);
                    maxArea = Math.max(maxArea, area);
                }
            }
        }

        return maxArea;
    }

    static int dfs(int[][] grid, int i, int j, int m, int n) {
        if (i < 0 || i >= m || j < 0 || j >= n) { return 0; }

        if (grid[i][j] == 1) {
            grid[i][j] = 0;
            int bottom = dfs(grid, i + 1, j, m, n);
            int right = dfs(grid, i, j + 1, m, n);
            int top = dfs(grid, i - 1, j, m, n);
            int left = dfs(grid, i, j - 1, m, n);
            return 1 + bottom + right + top + left;
        } else {
            return 0;
        }
    }

    public static int maxAreaOfIslandIterDfs(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        int maxArea = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    int area = iterDfs(grid, i, j, m, n);
                    maxArea = Math.max(maxArea, area);
                }
            }
        }

        return maxArea;
    }

    static int iterDfs(int[][] grid, int i, int j, int m, int n) {
        Stack<int[]> stack = new Stack<>();
        boolean[][] visited = new boolean[m][n];

        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        stack.push(new int[]{i, j});
        visited[i][j] = true;

        int area = grid[i][j];
        while (!stack.isEmpty()) {
            int[] pos = stack.pop();

            for (int[] dir : dirs) {
                int newI = pos[0] + dir[0];
                int newJ = pos[1] + dir[1];

                if (newI < 0 || newI >= m || newJ < 0 || newJ >= n || grid[newI][newJ] == 0 || visited[newI][newJ]) { continue; }
                area++;
                visited[newI][newJ] = true;
                stack.push(new int[]{newI, newJ});
            }
        }

        return area;
    }

    public static int maxAreaOfIslandBfs(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        int maxArea = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    int area = bfs(grid, i, j, m, n);
                    maxArea = Math.max(maxArea, area);
                }
            }
        }

        return maxArea;
    }

    static int bfs(int[][] grid, int i, int j, int m, int n) {
        Deque<int[]> dq = new ArrayDeque<>();
        boolean[][] visited = new boolean[m][n];

        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        dq.offer(new int[]{i, j});
        visited[i][j] = true;

        int area = grid[i][j];
        while (!dq.isEmpty()) {
            int[] pos = dq.poll();

            for (int[] dir : dirs) {
                int newI = pos[0] + dir[0];
                int newJ = pos[1] + dir[1];

                if (newI < 0 || newI >= m || newJ < 0 || newJ >= n || grid[newI][newJ] == 0 || visited[newI][newJ]) { continue; }
                area++;
                visited[newI][newJ] = true;
                dq.offer(new int[]{newI, newJ});
            }
        }

        return area;
    }

}
