package leetcode;

import java.util.HashMap;
import java.util.Map;

public class FibonacciNumber {
    public static void main(String[] args) {
        
        System.out.println(fib(12));

        Map<Integer, Integer> memo = new HashMap<>();
        System.out.println(fib(12, memo));

        System.out.println(fibDpWithTabulation(12));

        System.out.println(fibDpWithConstantSpace(12));

        System.out.println(fibWithGoldenRatio(12));
    }

    // Recursive - Time O(n^2) Space O(n)
    public static int fib(int n) {
        if (n == 0) return 0;
        else if (n == 1) return 1;
        else { return fib(n - 1) + fib(n - 2); }

        // Naive Recursive Solution
        // Time: O(2^n)
        // Space: O(n)
    }

    // DP with memoization
    public static int fib(int n, Map<Integer, Integer> memo) {
        if (n == 0) { return 0; }
        else if (n == 1) { return 1; }
        
        if (memo.containsKey(n)) { return memo.get(n); }

        int result = fib(n - 1) + fib(n - 2);
        memo.put(n, result);
        return result;

        // Top Down DP With Memoization
        // Time: O(n)
        // Space: O(n)
    }

    // DP with Tabulation
    public static int fibDpWithTabulation(int n) {
        if (n == 0) { return 0; }
        else if (n == 1) { return 1; }

        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;

        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        return dp[n];

        // Bottom Up DP With Tabulation
        // Time: O(n)
        // Space: O(n)
    }

    // Bottom-Up Dp with Constant Space
    public static int fibDpWithConstantSpace(int n) {
        if (n == 0) { return 0; }
        else if (n == 1) { return 1; }

        int prev = 0;
        int curr = 1;
        for (int i = 2; i <= n; i++) {
            int next = prev + curr;
            prev = curr;
            curr = next;
        }

        return curr;

        // Bottom Up DP With Constant Space
        // Time: O(n)
        // Space: O(1)
    }

    public static int fibWithGoldenRatio(int n) {
        // Binet's constant Φ = ( 1 + √5 )/2 = 1.6180339887...
        System.out.println(Math.pow(5, 0.5) / 2);

        double goldenRatio = (1 + (Math.pow(5, 0.5))) / 2;
        return (int) (Math.round(Ma th.pow(goldenRatio, n)) / Math.pow(5, 0.5));

        // Golden Ratio 
        // Time: O(log n)
        // Space: O(1)
    }
}
