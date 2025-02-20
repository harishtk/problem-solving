package leetcode;

import java.util.ArrayList;
import java.util.List;

public class Permutations {
    public static void main(String[] args) {
        int[] input = {1, 2, 3};
        List<List<Integer>> result = new ArrayList<>();
        permute(input, input.length, new ArrayList<>(), result);
        System.out.println(result);
    }

    static void permute(int[] nums, int n, List<Integer> sol, List<List<Integer>> result) {
        if (sol.size() == n) {
            result.add(new ArrayList<>(sol));
            return;
        }

        for (int num : nums) {
            if (!sol.contains(num)) {
                sol.add(num);
                permute(nums, n, sol, result);
                sol.remove(sol.size() - 1);
            }
        }
    }
}
