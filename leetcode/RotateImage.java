package leetcode;


public class RotateImage {
    public static void main(String[] args) {
        int[][] mat = {
            {1, 2, 3, 4},
            {5, 6, 7, 8},
            {9, 10, 11, 12},
            {13, 14, 15, 16}
        };

        Util.printMatrix(mat);
        System.out.println("Rotated-->");
        rotate90(mat);
        Util.printMatrix(mat);;
    }

    static void rotateMatrixAntiClockwise(int[][] mat) {
        int n = mat.length;

        // Consider all cycles one by one
        for (int i = 0; i < n / 2; i++) {
            // Consider elements in group of 4
            // as p1, p2, p3 & p4 in current square
            for (int j = i; j < n - j - 1; j++) {
                int temp = mat[i][j];
                mat[i][j] = mat[j][n - 1 - i];
                mat[j][n - 1 - i] = mat[n - 1 - i][n - 1 - j];
                mat[n - 1 - i][n - 1 - j] = mat[n - 1 - j][i];
                mat[n - 1 - j][i] = temp;
            }
        }
    }

    static void rotateMatrixClockWise(int[][] mat) {
        int n = mat.length;

        // Consider all cycles one by one
        for (int i = 0; i < n / 2; i++) {
            // Consider elements in group of 4
            // as p1, p2, p3 & p4 in current square
            for (int j = i; j < n - i - 1; j++) {
                int temp = mat[i][j];
                mat[i][j] = mat[n - 1 - j][i];                  // Move p4 to p1
                mat[n - 1 - j][i] = mat[n - 1 - i][n - 1 - j];  // Move p3 to p4
                mat[n - 1 - i][n - 1 - j] = mat[j][n - 1 - i];  // Move p2 to p3
                mat[j][n - 1 - i] = temp;                       // Move p1 to p2

            }
        }
    }

    static void rotate90(int[][] mat) {
        int n = mat.length;

        // Perform Transpose
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int temp = mat[i][j];
                mat[i][j] = mat[j][i];
                mat[j][i] = temp;
            }
        }

        // Reverse each row
        for (int i = 0; i < n; i++) {
            int start = 0, end = n - 1;
            while (start < end) {
                int temp = mat[i][start];
                mat[i][start] = mat[i][end];
                mat[i][end] = temp;
                start++;
                end--;
            }
        }
    }
}
