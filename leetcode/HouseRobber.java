package leetcode;

import java.util.HashMap;
import java.util.Map;

public class HouseRobber {
    public static void main(String[] args) {
        // int[] houses = {2, 7, 9, 3, 1};
        int[] houses = {1, 2, 3, 1};
        int n = houses.length;

        System.out.println("Naive recursive-->");
        System.out.println(f(houses, n - 1));

        System.out.println("Top-Down Dp with Memoization-->");
        System.out.println(f(houses, n - 1, new HashMap<>()));

        System.out.println("Bottom-Up Dp with Tabulation-->");
        System.out.println(dpWithTabulation(houses));

        System.out.println("Bottom-UP Dp with Constant Space");
        System.out.println(dpWithConstantSpace(houses));
    }

    // Naive Recursvie - O(2^n) - O(n)
    static int f(int[] nums, int i) {
        if (i == 0) { return nums[0]; }
        if (i == 1) { return Math.max(nums[0], nums[1]); }
        return Math.max(nums[i] + f(nums, i - 2), f(nums, i - 1));
    }

    // Top-Down Dp with Memoization
    static int f(int[] nums, int i, Map<Integer, Integer> memo) {
        if (i == 0) { return nums[0]; }
        if (i == 1) { return Math.max(nums[0], nums[1]); }
        if (memo.get(i) != null) { return memo.get(i); }
        int result = Math.max(nums[i] + f(nums, i - 2, memo), f(nums, i - 1, memo));
        memo.put(i, result);
        return result;
    }

    // Bottom-Up Dp with Tabulation
    static int dpWithTabulation(int[] nums) {
        int n = nums.length;
        if (n == 1) { return nums[0]; }
        if (n == 2) { return Math.max(nums[0], nums[1]); }

        int[] dp = new int[n];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);

        for (int i = 2; i < n; i++) {
            Util.printArr(dp);
            dp[i] = Math.max(nums[i] + dp[i - 2], dp[i - 1]);
        }

        return dp[n - 1];
    }

    // Bottom-Up Dp with Constant Space
    static int dpWithConstantSpace(int[] nums) {
        int n = nums.length;
        if (n == 1) { return nums[0]; }
        if (n == 2) { return Math.max(nums[0], nums[1]); }

        int prev = nums[0];
        int curr = Math.max(nums[0], nums[1]);

        for (int i = 2; i < n; i++) {
            int next = Math.max(nums[i] + prev, curr);
            prev = curr;
            curr = next;
        }

        return curr;
    }
}
