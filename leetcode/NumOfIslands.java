package leetcode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

public class NumOfIslands {
    public static void main(String[] args) {
        char[][] grid1 = {
            {'1','1','1','1','0'},
            {'1','1','0','1','0'},
            {'1','1','0','0','0'},
            {'0','0','0','0','0'}
        };
        char[][] grid2 = {
            {'1','1','0','0','0'},
            {'1','1','0','0','0'},
            {'0','0','1','0','0'},
            {'0','0','0','1','1'}
          };

        System.out.println("DFS-->");
        System.out.println(numIslandsDfs(grid1));
        System.out.println(numIslandsDfs(grid2));

        char[][] grid5 = {
            {'1','1','1','1','0'},
            {'1','1','0','1','0'},
            {'1','1','0','0','0'},
            {'0','0','0','0','0'}
        };
        char[][] grid6 = {
            {'1','1','0','0','0'},
            {'1','1','0','0','0'},
            {'0','0','1','0','0'},
            {'0','0','0','1','1'}
          };

        System.out.println("DFS with Stack -->");
        System.out.println(numIslandsBfs(grid5));
        System.out.println(numIslandsBfs(grid6));

        char[][] grid3 = {
            {'1','1','1','1','0'},
            {'1','1','0','1','0'},
            {'1','1','0','0','0'},
            {'0','0','0','0','0'}
        };
        char[][] grid4 = {
            {'1','1','0','0','0'},
            {'1','1','0','0','0'},
            {'0','0','1','0','0'},
            {'0','0','0','1','1'}
          };

        System.out.println("BFS-->");
        System.out.println(numIslandsBfs(grid3));
        System.out.println(numIslandsBfs(grid4));
    }

    static int numIslandsDfs(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        int totalIslands = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1') {
                    totalIslands++;
                    dfs(grid, i, j, m, n);
                }
            }
        }

        return totalIslands;
    }

    static void dfs(char[][] grid, int i, int j, int m, int n) {
        if (i < 0 || i >= m || j < 0 || j >= n) { return; }

        if (grid[i][j] == '1') {
            grid[i][j] = '0';
            dfs(grid, i + 1, j, m, n);
            dfs(grid, i, j + 1, m, n);
            dfs(grid, i - 1, j, m, n);
            dfs(grid, i, j - 1, m, n);
        }
    }

    static int numIslandsDfsWithStack(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        int totalIslands = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1') {
                    totalIslands++;
                    dfsWithStack(grid, i, j, m, n);
                }
            }
        }

        return totalIslands;
    }

    static void dfsWithStack(char[][] grid, int i, int j, int m, int n) {
        Stack<int[]> st = new Stack<>();

        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        st.push(new int[]{i, j});
        grid[i][j] = '0'; // mark visited

        while (!st.isEmpty()) {
            int[] pos = st.pop();

            for (int[] dir : dirs) {
                int newI = pos[0] + dir[0];
                int newJ = pos[1] + dir[1];
                if (newI < 0 || newI >= m || newJ < 0 || newJ >= n || grid[newI][newJ] == '0') { continue; }
                grid[newI][newJ] = '0';
                st.push(new int[]{newI, newJ});
            }
        }
    }

    static int numIslandsBfs(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        int totalIslands = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1') {
                    totalIslands++;
                    bfs(grid, i, j, m, n);
                }
            }
        }

        return totalIslands;
    }

    static void bfs(char[][] grid, int i, int j, int m, int n) {
        Deque<int[]> dq = new ArrayDeque<>();
        
        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        dq.add(new int[]{i, j});
        grid[i][j] = '0'; // mark visited

        while (!dq.isEmpty()) {
            int[] pos = dq.poll();

            for (int[] dir : dirs) {
                int newI = pos[0] + dir[0];
                int newJ = pos[1] + dir[1];
                if (newI < 0 || newI >= m || newJ < 0 || newJ >= n || grid[newI][newJ] == '0') { continue; }
                grid[newI][newJ] = '0';
                dq.add(new int[]{newI, newJ});
            }
        }
    }

}
