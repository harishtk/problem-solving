package others;

import java.util.Arrays;

/**
 * Given an integer array of N elements. You need to find the maximum 
 * sum of two elements such that sum is closest to zero. This problem
 *  is a variation of standard 2 Sum problems.
 */
public class TwoSumPairClosest {
    public static void main(String[] args) {
        final int[] input = {-8, 5, 2, -6};

        System.out.print("Input: ");
        Util.printArr(input);
        final TwoSumPairClosest app = new TwoSumPairClosest();
        // System.out.println(app.minAbsSumPairNaive(input));
        // System.out.println(app.minAbsSumPairSortnBinarySearch(input));
        System.out.println(app.closestToZeroSumPair(input));
    }

    // A Naive approach
    private int minAbsSumPairNaive(int[] arr) {

        // Initialize the result with the sum
        // of the first two elemtns
        int res = arr[0] + arr[1];

        // Consider every pair, find its sum and 
        // update result if we get a smaller value
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                final int sum = arr[i] + arr[j];
                if (Math.abs(sum) < Math.abs(res)) {
                    res = sum;
                }
            }
        }

        return res;
    }

    // Using sort and binary search
    private int minAbsSumPairSortnBinarySearch(int[] arr) {
        Arrays.sort(arr);

        final int n = arr.length;

        int res = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            
            // Consider current element as first element
            // of the pair and find the other element using
            // binary search
            int x = arr[i];

            int left = i + 1, right = n - 1;

            while (left <= right) {
                final int mid = (left + right) / 2;

                final int curr = arr[mid] + x;

                if (curr == 0) {
                    return 0;
                }

                if (Math.abs(curr) < Math.abs(res)) {
                    res = curr;
                }

                if (curr < 0) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }

        return res;
    }

    // Using two pointer approach
    private int closestToZeroSumPair(int[] arr) {

        // Sorting the array in ascending order
        Arrays.sort(arr);

        int i = 0, j = arr.length - 1;

        // Initializing sum with the first and last elements
        int sum = arr[i] + arr[j];

        // Initializing the result with the absolute value 
        // of the initial sum
        int diff = Math.abs(sum);

        while (i < j) {
            // If we have zero sum, there's no result
            // better. Hence, we return 0.
            if (arr[i] + arr[j] == 0) {
                return 0;
            }

            // If we get a better result, we update the 
            // difference
            if (Math.abs(arr[i] + arr[j]) <
                Math.abs(diff)) {
                    diff = arr[i] + arr[j];
                    sum = arr[i] + arr[j];
            } else if (Math.abs(arr[i] + arr[j]) ==
                        Math.abs(diff)) {
                // If there are multiple pairs with the same minimum
                // absolute difference, return the pair
                // with the larger sum
                sum = Math.max(sum, arr[i] + arr[j]); 
            }

            // If the current sum is greater than zero, we 
            // search for a smaller sum
            if (arr[i] + arr[j] > 0) {
                j--;
            } else {
                i++;
            }
        }

        return sum;
    }
}