package others;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MissingNumber {
    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 3, 4, 6, 7, 8, 9, 10};
        int n = 10;

        System.out.println("Input: " + Arrays.toString(arr));
        System.out.println("Missing num using map: " + findMissingNumUsingMap(arr, n));
        System.out.println("Missing num using xor: " + findMissingNum(arr, n));
        System.out.println("Missing num using formula: " + findMissingNumUsingFormula(arr, n));
    }

    static int findMissingNum(int[] arr, int n) {
        int xor = 0;
        for (int i = 1; i <= n; i++) xor ^= i;
        for (int num : arr) xor ^= num;
        return xor;
    }

    static int findMissingNumUsingMap(int[] arr, int n) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 1; i <= n; i++) { map.put(i, 1); }
        for (int e : arr) { map.put(e, 0); }
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() == 1) { return entry.getKey(); }
        }
        return -1;
    }

    static int findMissingNumUsingFormula(int[] arr, int n) {
        int sum = n * (n + 1) >> 1;
        int currSum = 0;
        for (int e : arr) currSum += e;
        return sum - currSum;
    }
}
