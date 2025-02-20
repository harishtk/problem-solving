package leetcode;

import java.util.HashMap;
import java.util.Map;

public class JumpGame {
    public static void main(String[] args) {
        int[] case1 = {2, 3, 1, 1, 4};
        int[] case2 = {3, 2, 1, 0, 4};

        System.out.println("Naive Recursive -->");
        System.out.println(canJump(case1, 0));
        System.out.println(canJump(case2, 0));

        System.out.println("Top-Down Dp with Memoization -->");
        System.out.println(canJump(case1, 0, new HashMap<>()));
        System.out.println(canJump(case2, 0, new HashMap<>()));

        System.out.println("Greedy approach -->");
        System.out.println(canJumpGreedy(case1));
        System.out.println(canJumpGreedy(case2));

        System.out.println("Naive approach -->");
        System.out.println(canJumpNaive(case1));
        System.out.println(canJumpNaive(case2));

    }

    // Naive Recursive - O(2^n) - O(n)
    static boolean canJump(int[] nums, int i) {
        if (i == nums.length - 1) { return true; }
        for (int k = i + 1; k <= i + nums[i]; k++) {
            if (canJump(nums, k)) return true;
        }
        return false;
    }
    
    // Top-Down Dp with Memoization - O(n) - O(n)
    static boolean canJump(int[] nums, int i, Map<Integer, Boolean> memo) {
        if (i == nums.length - 1) { return true; }
        for (int k = i + 1; k <= i + nums[i]; k++) {
            if (memo.get(k) != null) continue;
            if (canJump(nums, k, memo)) return true;
            else memo.put(k, false);
        }
        return false;
    }
    
    // Bottom-Up Dp with Tabulation - O(n) - O(n)
    static boolean canJump(int[] nums) {
        int n = nums.length;

        for (int i = 0; i < n; i++) {

        }

        return false;
    }

    // Naive approach
    static boolean canJumpNaive(int[] nums) {
        int reachable = 0;

        // {2, 3, 1, 1, 4}
        for (int i = 0; i < nums.length; i++) {
            if (i > reachable) return false;
            reachable = Math.max(reachable, i + nums[i]);
            System.out.println("Reachable: " + reachable + " i=" + i);
        }

        return true;
    }

    // Greedy - Start at end - O(n) - O(1)
    static boolean canJumpGreedy(int[] nums) {
        int n = nums.length;
        int target = nums[n - 1];

        for (int i = n - 1; i >= 0; i--) {
            if (i + nums[i] >= target) {
                target = i;
            }
        }

        return target == 0;
    }
}
