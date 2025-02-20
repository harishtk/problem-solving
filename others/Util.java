package others;

import java.util.HashSet;

public class Util {
    private Util() {}

    public static void printArr(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            if (i != arr.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println();
    }

    public static void printArr(long[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            if (i != arr.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println();
    }

    public static void printMatrix(int[][] matrix) {
        int maxLen = 1;

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
}
