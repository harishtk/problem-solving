package others;

import java.util.Arrays;
import java.util.Stack;
import java.util.stream.Stream;

public class TrappingRainWater {
    public static void main(String[] args) {
        final int[] input = {2, 1, 5, 3, 1, 0, 4};
        Stack<Integer> st = new Stack<>();

        System.out.print("Input: ");
        Util.printArr(input);
        final TrappingRainWater app = new TrappingRainWater();
        System.out.println(app.maxWaterTwoPointers(input));
    }

    // Naive approach - O(n^2) Time and O(1) Space
    private int maxWaterNaive(int[] arr) {
        int result = 0;

        for (int i = 1; i < arr.length - 1; i++) {
            
            int left = arr[i];
            for (int j = 0; j < i; j++) {
                left = Math.max(left, arr[j]);
            }

            int right = arr[i];
            for (int j = i + 1; j < arr.length; j++) {
                right = Math.max(right, arr[j]);
            }

            System.out.println(String.format("left=%d right=%d", left, right));
            result += Math.min(left, right) - arr[i];
        }

        return result;
    }

    // Improved version
    private int maxWaterOptimized(int[] arr) {
        final int n = arr.length;

        // Left[i] contains height of tallest bar to the left
        // of i'th bar including itself
        int[] left = new int[n];

        // Right[i] contains height of tallest bar to the
        // right of i'th bar including itself
        int[] right = new int[n];

        int res = 0;

        // Fill left array
        left[0] = arr[0];
        for (int i = 1; i < n; i++) {
            left[i] = Math.max(left[i - 1], arr[i]);
        }

        // Fill right array
        right[n - 1] = arr[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            right[i] = Math.max(right[i + 1], arr[i]);
        }

        // Calculate the accumulated water element by element
        for (int i = 1; i < n - 1; i++) {
            int minOfTwo = Math.min(left[i - 1], right[i + 1]);
            if (minOfTwo > arr[i]) {
                res += minOfTwo - arr[i];
            }
        }

        return res;
    }

    // Two Pointers approach
    private int maxWaterTwoPointers(int[] arr) {
        int left = 1;
        int right = arr.length - 2;

        // lMax : Maximum in subarray arr[0..left - 1]
        // rMax : Maximum in subarray arr[right + 1..n - 1]
        int lMax = arr[left - 1];
        int rMax = arr[right + 1];

        int res = 0;
        while (left <= right) {
         
            // If rMax is smaller, then we can decide the amount of water
            // for arr[right]
            if (rMax <= lMax) {

                // Add the water for arr[right]
                res += Math.max(0, rMax - arr[right]);

                // Update right max
                rMax = Math.max(rMax, arr[right]);

                // Update right pointer as we have decided the amount of 
                // water for this
                right--;
            } else {

                // Add the water for arr[left]
                res += Math.max(0, lMax - arr[left]);

                // Update left max
                lMax = Math.max(lMax, arr[left]);

                // Update left pointer as we have decided water for this
                left++;
            }
        }

        return res;
    }
    
}
