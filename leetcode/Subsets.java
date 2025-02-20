package leetcode;

import java.util.ArrayList;
import java.util.List;

public class Subsets {
    public static void main(String[] args) {
        int[] input = {1, 2, 3};
        List<List<Integer>> result = new ArrayList<>();
        subsets(input, 0, input.length, new ArrayList<>(), result);
        System.out.println(result);
    }

    static void subsets(int[] nums, int i, int n, List<Integer> sol, List<List<Integer>> result) {
        if (i == n) {
            result.add(new ArrayList<>(sol));
            return;
        }
        
        subsets(nums, i + 1, n, sol, result);
        sol.add(nums[i]);
        subsets(nums, i + 1, n, sol, result);
        sol.remove(sol.size() - 1);
    }
}
