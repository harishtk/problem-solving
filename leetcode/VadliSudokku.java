package leetcode;

import java.util.HashSet;
import java.util.Set;

public class VadliSudokku {

    public static void main(String[] args) {
        char[][] board1 = {
            {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
            {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
            {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
            {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
            {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
            {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
            {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
            {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
            {'.', '.', '.', '.', '8', '.', '.', '7', '9'}}; // valid

        char[][] board2 = {
            {'8', '3', '.', '.', '7', '.', '.', '.', '.'},
            {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
            {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
            {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
            {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
            {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
            {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
            {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
            {'.', '.', '.', '.', '8', '.', '.', '7', '9'}}; // invalid

        System.out.println("Using set: board1 is " + isValidSudokkuUsingSet(board1));
        System.out.println("Using set: board2 is " + isValidSudokkuUsingSet(board2));

        System.out.println("Using optimal: board1 is " + isValidSudokkuOptimal(board1));
        System.out.println("Using optimal: board2 is " + isValidSudokkuOptimal(board2));

    }

    public static boolean isValidAnagram(String s1, String s2) {
        return true;
    }

    public static boolean isValidSudokkuUsingSet(char[][] board) {
        // Validate rows
        for (int i = 0; i < 9; i++) {
            Set<Character> set = new HashSet<>();
            for (int j = 0; j < 9; j++) {
                char cellValue = board[i][j];
                if (set.contains(cellValue)) {
                    return false;
                } else if (cellValue != '.') {
                    set.add(cellValue);
                }
            }
        }

        // Validate Cols
        for (int i = 0; i < 9; i++) {
            Set<Character> set = new HashSet<>();
            for (int j = 0; j < 9; j++) {
                char cellValue = board[j][i];
                if (set.contains(cellValue)) {
                    return false;
                } else if (cellValue != '.') {
                    set.add(cellValue);
                }
            }
        }

        // Valiate cells
        int[][] starts = new int[][]{
            {0, 0}, {0, 3}, {0, 6},
            {3, 0}, {3, 3}, {3, 6},
            {6, 0}, {6, 3}, {6, 6},
        };

        for (int[] start : starts) {
            Set<Character> set = new HashSet<>();
            for (int i = start[0]; i < start[0] + 3; i++) {
                for (int j = start[1]; j < start[1] + 3; j++) {
                    char cellValue = board[j][i];
                    if (set.contains(cellValue)) {
                        return false;
                    } else if (cellValue != '.') {
                        set.add(cellValue);
                    }
                }
            }
        }

        return true;
    }

    public static boolean isValidSudokkuOptimal(char[][] board) {
        boolean[][] rows = new boolean[9][9];
        boolean[][] cols = new boolean[9][9];
        boolean[][] boxes = new boolean[9][9];

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == '.') continue;

                // Converts character '1'–'9' to integer index 0–8
                int digit = board[i][j] - '1';
                int boxIndex = (i / 3) * 3 + (j / 3);

                if (rows[i][digit] || cols[j][digit] || boxes[boxIndex][digit]) {
                    return false;
                }

                rows[i][digit] = cols[j][digit] = boxes[boxIndex][digit] = true;
            }
        }

        return true;
    }
}
