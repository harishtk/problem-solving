package google;

import java.util.Arrays;

public class MaxProductSubArray {
    public static void main(String[] args) {
        int[] arr = {3, 5, -2, -4};

        System.out.println(subArrayProductDp(arr));
    }

    static long subArrayProduct(int[] arr) {
        long sum = Integer.MIN_VALUE;
        int n = arr.length; 

        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                long currSum = product(arr, i, j + 1);
                if (currSum > sum) sum = currSum;
            }

        }

        return sum;
    }

    static long product(int[] arr, int from, int to) {
        // Util.printArr(Arrays.copyOfRange(arr, from, to));

        int result = 1;
        for (int i = from; i < to; i++) {
            result = result * arr[i];
        }
        return result;
    } 

    // Using Kadane's Algorithm
    static long subArrayProductDp(int[] arr) {
        if (arr.length == 0) return 0;

        long maxProduct = arr[0];
        long minProduct = arr[0];
        long result = arr[0];

        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < 0) {
                long temp = maxProduct;
                maxProduct = minProduct;
                minProduct = temp;
            }

            maxProduct = Math.max(arr[i], maxProduct * arr[i]);
            minProduct = Math.min(arr[i], minProduct * arr[i]);

            result = Math.max(result, maxProduct);
        }

        return result;
    }
}
