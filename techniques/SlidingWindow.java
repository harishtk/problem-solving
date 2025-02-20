package techniques;

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.TreeSet;

/**
 * 1. To find the maximum sum of all subarrays of size K:
 * Given an array of integers of size ‘n’, Our aim is to calculate
 * the maximum sum of ‘k’ consecutive elements in the array.
 */
public class SlidingWindow {
    public static void main(String[] args) {
        int arr[] = { 1, 4, 2, 10, 2, 3, 1, 0, 20 };
        int k = 4;

        long startMillis, stopMillis;
        startMillis = System.nanoTime();
        System.out.println(maxSumNaive(arr, k));
        stopMillis = System.nanoTime();
        System.out.println("Naive: " + (stopMillis - startMillis) + "ms");

        startMillis = System.nanoTime();
        System.out.println(maxSumSlidingWindow(arr, k));
        stopMillis = System.nanoTime();
        System.out.println("Sliding Window: " + (stopMillis - startMillis) + "ms");
    }

    static int maxSumSlidingWindow(int[] arr, int k) {
        int n = arr.length;

        if (n <= k) {
            System.out.println("Invalid window size " + k);
            return -1;
        }

        int maxSum = 0;
        for (int i = 0; i < k; i++) {
            maxSum += arr[i];
        }

        int windowSum = maxSum;
        for (int i = k; i < n; i++) {
            windowSum += arr[i] - arr[i - k];
            maxSum = Math.max(maxSum, windowSum);
        }

        return maxSum;
    }

    static int maxSumNaive(int[] arr, int k) {
        int n = arr.length;

        int maxSum = Integer.MIN_VALUE;
        for (int i = 0; i < n - k + 1; i++) {
            int currSum = 0;
            for (int j = 0; j < k; j++) {
                currSum += arr[i + j];
            }
            maxSum = Math.max(maxSum, currSum);
        }

        return maxSum;
    }

    static class Tuple<First, Second, Third> implements Comparable<Tuple<First, Second, Third>> {
        First first;
        Second second;
        Third third;
        Tuple(First first, Second second, Third third) {
            this.first = first;
            this.second = second;
            this.third = third;
        }

        First getFirst() { return first; }
        Second getSecond() { return second; }
        Third getThird() { return third; }

        @Override
        public int compareTo(Tuple<First, Second, Third> other) {
            
        }

        @Override
        public String toString() {
            return "Tuple[" + first + ", " + second + ", " + third + "]";
        }
    }
}
