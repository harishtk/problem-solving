package takeufwd;

import java.util.Arrays;

/**
 * Given an integer array nums. Return the number of inversions in the array.
 *
 * Two elements a[i] and a[j] form an inversion if a[i] > a[j] and i < j. 1. It
 * indicates how close an array is to being sorted. 2. A sorted array has an
 * inversion count of 0. 3. An array sorted in descending order has maximum
 * inversion.
 */
public class CountInversions {

    public static void main(String[] args) {
        int[] input = {2, 3, 7, 1, 3, 5};  // 5
        int[] input2 = {-10, -5, 6, 11, 15, 17}; // 0 

        System.out.print("Input: " + Arrays.toString(input) + "\n");
        System.out.println(numberOfInversions(input));
        System.out.print("Input: " + Arrays.toString(input2) + "\n");
        System.out.println(numberOfInversions(input2));
    }

    public static long numberOfInversions(int[] nums) {
        int n = nums.length;
        int i = 0, j = 1;

        long inversions = 0;
        while (i < j && i < n) {
            int e1 = nums[i];
            while (j < n) {
                int e2 = nums[j];
                if (e1 > e2) {
                    inversions++;
                }
                j++;
            }
            i++;
            j = i + 1;
        }

        return inversions;
    }
}
