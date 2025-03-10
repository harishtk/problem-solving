package google_interview;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Distance Sum of Matrix
 * 
 * You are given a matrix of integers A with N rows numbered from 0 to N - 1 and M 
 * columns numbered from 0 to M - 1.
 * 
 * Distance between two cells (x1, y1) and (x2, y2) is defined as |x1 - x2| + |y1 - y2| 
 * where |x| denotes the absolute value of x.
 * 
 * The distance sum of the matrix is defined as the sum of all distances between all pairs of cells 
 * [(x1, y1), (x2, y2)] A[x1][y1] = A[x2][y2].
 * 
 */
public class DistanceSumOfMatrix {
    public static void main(String[] args) {
        test1();
        test2();
        test3();
    }

    public static void test1() {
        int N = 2, M = 3;
        int[][] A = {
            {3, 1, 3},
            {1, 2, 1}
        };
        System.out.println(distanceSum2(N, M, A));
    }

    public static void test2() {
        int N = 2, M = 2;
        int[][] A = {
            {3, 7},
            {1, 2}
        };
        System.out.println(distanceSum2(N, M, A));
    }

    public static void test3() {
        int N = 2, M = 2;
        int[][] A = {
            {1, 2},
            {2, 1}
        };
        System.out.println(distanceSum2(N, M, A));
    }

    public static int distanceSum(int N, int M, int[][] A) {
        int sum = 0;
        boolean[][] visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                for (int x = 0; x < N; x++) {
                    for (int y = 0; y < M; y++) {
                        if (i == x && j == y) {
                            continue;
                        }
                        if (!visited[x][y] && A[i][j] == A[x][y]) {
                            System.out.println(String.format("A[%d][%d] = A[%d][%d] = %d", i, j, x, y, A[i][j]));
                            sum += Math.abs(i - x) + Math.abs(j - y);
                        }
                    }
                }
                visited[i][j] = true;
            }
        }
        return sum;
    }

    public static int distanceSum2(int N, int M, int[][] A) {
        int sum = 0;
        Map<Integer, List<int[]>> map = new HashMap<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                map.computeIfAbsent(A[i][j], k -> new ArrayList<>()).add(new int[]{i, j});
            }
        }

        for (List<int[]> list : map.values()) {
            for (int i = 0; i < list.size(); i++) {
                for (int j = i + 1; j < list.size(); j++) {
                    System.out.println(String.format("A[%d][%d] = A[%d][%d] = %d", list.get(i)[0], list.get(i)[1], list.get(j)[0], list.get(j)[1], A[list.get(i)[0]][list.get(i)[1]]));
                    sum += Math.abs(list.get(i)[0] - list.get(j)[0]) + Math.abs(list.get(i)[1] - list.get(j)[1]);
                }
            }
        }
        return sum;
    }
}
