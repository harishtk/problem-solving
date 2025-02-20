package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class LongestIncreasingSubSequence {
    public static void main(String[] args) {
        int[] nums = {10, 9, 2, 5, 3, 7, 101, 18};

        System.out.println("Recursion -->");
        System.out.println(longestLISRecursion(nums));

        System.out.println("Top-Down Dp with Memoizatoin -->");
        System.out.println(longestLISRecursionWithMemo(nums));
        
        System.out.println("Bottom-Up Dp with Tabulation -->");
        System.out.println(longestLIS(nums));

        System.out.println("Using Binary Search -->");
        System.out.println(longestLISWitbBinarySearch(nums));
    }

    // Naive Recursion - O(2^n) - O(n)
    static int longestLISRecursion(int[] nums) {
        int n = nums.length;
        int res = 1;
        for (int i = 1; i < n; i++) {
            res = Math.max(res, f(nums, i));
        }
        return res;
    }

    static int f(int[] nums, int i) {
        if (i == 0) { return 1; }

        int max = 1;
        for (int j = 0; j < i; j++) {
            if (nums[i] < nums[j]) {
                max = Math.max(max, f(nums, j) + 1);
            }
        }

        return max;
    }

    // Top-Down Dp with Memoization - O(2^n) - O(n)
    static int longestLISRecursionWithMemo(int[] nums) {
        int n = nums.length;

        int[] memo = new int[n];
        Arrays.fill(memo, -1);

        int res = 1;
        for (int i = 1; i < n; i++) {
            res = Math.max(res, f(nums, i, memo));
        }
        return res;
    }

    static int f(int[] nums, int i, int[] memo) {
        if (i == 0) { return 1; }

        int max = 1;
        for (int j = 0; j < i; j++) {
            if (nums[i] < nums[j]) {
                max = Math.max(max, f(nums, j, memo) + 1);
            }
        }

        memo[i] = max;
        return memo[i];
    }

    // Bottom-Up Dp with Tabulation - O(n^2) - O(n)
    static int longestLIS(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp, 1);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }

        int max = Integer.MIN_VALUE;
        for (int i = 0; i < dp.length; i++)
            max = Math.max(max, dp[i]);

        return max;
    }

    // Using Binary Search - O(n logn) - O(n)
    static int longestLISWitbBinarySearch(int[] nums) {
        int n = nums.length;
        List<Integer> result = new ArrayList<>();

        result.add(nums[0]);

        for (int i = 1; i < n; i++) {   
            if (nums[i] > result.get(result.size() - 1)) {
                result.add(nums[i]);
            } else {
                int low = Collections.binarySearch(result, nums[i]);
                // System.out.println(result + "<--" + nums[i] + ", lo=" + low);

                if (low < 0) {
                    low = -(low + 1); 
                }
                // System.out.println("Lo updated: " + low);
                result.set(low, nums[i]);
                // System.out.println("Updated: " + result);
            }
        }

        return result.size();
    }
}
