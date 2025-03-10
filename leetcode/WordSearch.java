package leetcode;

public class WordSearch {
    public static void main(String[] args) {
        char[][] board = {
            { 'A', 'B', 'C', 'E' },
            { 'S', 'F', 'C', 'S' },
            { 'A', 'D', 'E', 'E' }
        };
        String word = "ABCCED";

        System.out.println(exist(board, word));
    }

    public static boolean exist(char[][] board, String word) {
        int m = board.length;
        int n = board[0].length;
        int W = word.length();

        if (m == 1 && n == 1) {
            return board[0][0] == word.charAt(0);
        }

        boolean[][] visited = new boolean[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (backtrack(board, i, j, 0, word, visited)) {
                    return true;
                }
            }
        }

        return false;
    }

    public static boolean backtrack(char[][] board, int i, int j, int k, String word, boolean[][] visited) {
        if (k == word.length()) { return true; }

        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length ||
            visited[i][j] || word.charAt(k) != board[i][j]) { return false; }

        visited[i][j] = true;
        boolean found = backtrack(board, i + 1, j, k + 1, word, visited) ||
                        backtrack(board, i, j + 1, k + 1, word, visited) ||
                        backtrack(board, i - 1, j, k + 1, word, visited) ||
                        backtrack(board, i, j - 1, k + 1, word, visited);
        
        visited[i][j] = false;
        return found;
    }

    static void printGrid(char[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
    }
}
