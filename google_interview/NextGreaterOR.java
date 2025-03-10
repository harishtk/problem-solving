package google_interview;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Next Greater OR
 * 
 * You are given an integer N denoting the size of an array A consisting of integers.
 * You are given a task to tell the minimum size of the subarray for each index i, which
 * starts from index i + 1 that has OR greater than prefix OR (OR of all elements from 0 to i).
 * If there is no such subarray possible for an index i, print -1.
 * 
 * Task:
 * You are required to print an array of size N, where ith element represents the size of
 * subarray starting from index i + 1 in array A which has OR greater than prefix OR.
 * (OR of all elements from 0 to i). If such a subarray does not exist, print -1 on that
 * element.
 */
public class NextGreaterOR {
    public static void main(String[] args) {
        test1();
    }

    public static void test1() {
        int N = 4;
        int[] A = { 1, 1, 1, 2 };

        // Ans: { 1, 2, 1, -1 }
        System.out.println(solve(N, A));
    }

    

    public static List<Integer> solve(int N, int[] A) {
        int[] ans = new int[N];
        Arrays.fill(ans, -1);

        for (int i = 0; i < N; i++) {
            int prefixOr = 0;
            for (int j = 0; j <= i; j++) {
                prefixOr |= A[j];
            }
            int subarrayOr = 0;
            for (int j = i + 1; j < N; j++) {
                subarrayOr |= A[j];
                if (subarrayOr > prefixOr) {
                    ans[i] = j - i;
                    break;
                }
            }
        }

        List<Integer> result = new ArrayList<>();
        for (int i : ans) result.add(i);
        return result;
    }
}
