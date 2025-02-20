package google;

import java.util.Arrays;

public class SubArraySum {
    public static void main(String[] args) {
        final long[][] input = new long[][] {
            {1, 4, 9}
        };

        long sum = 0;
        for (long[] line : input) {
            sum = subArrays(line, 0, 0, 0);
        }
        
        System.out.println(sum);
        int[] arr = {1, 2, 3};
        // printSubArrays(arr, 0, 0);
        printSubArrays2(arr);
    }

    // Function to get max - min of the array. (Naive approach)
    private static long getMaxMinDiff(int[] arr, int start, int end) {
        if (arr.length == 0) return 0;

        long min = arr[start];
        long max = arr[start];
        for (int i = start + 1; i <= end; i++) {
            if (arr[i] < min) min = arr[i];
            if (arr[i] > max) max = arr[i];
        }

        System.out.println(String.format("(%d - %d)", max, min));
        return max - min;
    }

    // Function to generate all possible subarrays
    private static long subArrays(long[] arr, int start, int end, long sum) {

        // Base case
        if (end == arr.length) {
            return sum;
        } else if (start > end) {
            return subArrays(arr, 0, end + 1, sum);
        } else {
            sum += getMaxMinDiff(arr, start, end + 1);
            return subArrays(arr, start + 1, end, sum);
        }
    }

    // Recursive approach
    private static void printSubArrays(int[] arr, int start, int end) {

        if (end == arr.length) {
            return; 
        } else if (start > end) {
            printSubArrays(arr, 0, end + 1);
        } else {
            for (int i = start; i <= end; i++) {
                System.out.print(arr[i] + " ");
            }
            System.out.println();
            printSubArrays(arr, start + 1, end);
        }

        return;
    }

    // Bruteforce approach
    private static void printSubArrays2(int[] arr) {
        final int n = arr.length;

        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                
                for (int k = i; k <= j; k++) {
                    System.out.print(arr[k] + " ");
                }
                System.out.println();
            }
        }
    }
}