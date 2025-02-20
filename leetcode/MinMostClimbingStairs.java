package leetcode;

import java.util.HashMap;
import java.util.Map;

public class MinMostClimbingStairs {
    public static void main(String[] args) {
        int[] cost = {10, 15, 20};

        System.out.println("Naive Recursive-->");
        System.out.println(f(cost, cost.length));

        System.out.println("Top-Down Dp with Memoization-->");
        System.out.println(minCostClimbingStairs(cost));

        System.out.println("Bottom-Up Dp with Tabulation-->");
        System.out.println(dp(cost));

        System.out.println("Bottom-Up Dp with Constant Space-->");
        System.out.println(dpConstantSpace(cost));
    }

    public static int minCostClimbingStairs(int[] cost) {
        Map<Integer, Integer> memo = new HashMap<>();
        memo.put(0, 0);
        memo.put(1, 0);
        return f(cost, cost.length, memo);
    }

    // Top-Down Dp with memoization - O(n) - O(n)
    static int f(int[] cost, int i, Map<Integer, Integer> memo) {
        if (memo.get(i) != null) return memo.get(i);

        int result = Math.min(cost[i - 2] + f(cost, i - 2, memo),
                        cost[i - 1] + f(cost, i - 1, memo));
        memo.put(i, result);
        return result;
    }

    // Top-Down Naive recursive - O(2^n) - O(n)
    static int f(int[] cost, int i) {
        if (i < 2) { return 0; }

        return Math.min(cost[i - 2] + f(cost, i - 2), 
                        cost[i - 1] + f(cost, i - 1));
    }

    // Bottomp-Up Dp with Tabulation - O(n) - O(n)
    static int dp(int[] cost) {
        int n = cost.length;
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 0;

        for (int i = 2; i <= n; i++) {
            dp[i] = Math.min(cost[i - 1] + dp[i - 1], cost[i - 2] + dp[i - 2]);
        }

        return dp[n];
    }

    // Bottom-Up Dp with Constant Space O(1)
    static int dpConstantSpace(int[] cost) {
        int n = cost.length;

        int prev = 0;
        int curr = 0;

        for (int i = 2; i <= n; i++) {
            int next = Math.min(cost[i - 1] + curr, cost[i - 2] + prev);
            prev = curr;
            curr = next;
        }

        return curr;
    }
}
