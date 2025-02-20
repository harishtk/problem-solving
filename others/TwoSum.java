package others;

import java.util.*;
import java.util.Collections;

public class TwoSum {
    public static void main(String[] args) {
        // final int[] input = {2, 7, 11, 15};
        // final int target = 9;
        final int[] input = {3, 2, 4};
        final int target = 6;

        System.out.print("Initial: ");
        printArr(input);

        final List<Integer> result = twoSumHashMap(input, target);
        for (int i : result) {
            System.out.print(input[i] + " ");
        }
        System.out.println();
    }

    // Two Pointers Approach
    private static List<Integer> twoSum(final int[] arr, final int target) {
        int l = 0, r = arr.length - 1;

        while (l < r) {
            final int currSum = arr[l] + arr[r];

            if (currSum == target) {
                return Arrays.asList(l, r);
            } else if (currSum < target) {
                l++;
            } else {
                r--;
            }
        }

        return Collections.emptyList();
    }

    // with HashMap
    private static List<Integer> twoSumHashMap(int[] arr, int target) {
        final HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < arr.length; i++) {
            final int num = arr[i];

            final int diff = target - num;

            if (map.containsKey(diff)) {
                return Arrays.asList(map.get(diff), i);
            }

            map.put(num, i);
        }

        return Collections.emptyList();
    }

    private static void printArr(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            if (i != arr.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println();
    }
    
}
