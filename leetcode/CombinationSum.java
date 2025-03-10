package leetcode;

import java.util.ArrayList;
import java.util.List;

public class CombinationSum {
    public static void main(String[] args) {
        int[] candidates = {2, 3, 6, 7};
        int target = 7;

        List<List<Integer>> result = new ArrayList<>();
        f(candidates, 0, 0, target, new ArrayList<>(), result);
        System.out.println(result);
    }

    // Top-Down Recursion
    static void f(int[] nums, int i, int sum, int target, List<Integer> sol, List<List<Integer>> result) {
        if (sum == target) {
            result.add(new ArrayList<>(sol));
            return;
        }

        if (sum > target || i >= nums.length) {
            return;
        }

        f(nums, i + 1, sum, target, sol, result);
        sol.add(nums[i]);
        f(nums, i, sum + nums[i], target, sol, result);
        sol.remove(sol.size() - 1);
    }
}
