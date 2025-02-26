package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Subsets {
    public static void main(String[] args) {
        int[] input = {1, 2, 3};
        List<List<Integer>> result = new ArrayList<>();
        subsets(input, 0, input.length, new ArrayList<>(), result);
        System.out.println(result);
    }

    // Naive 
    static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        result.add(Arrays.asList()); // Empty set

        int n = nums.length;
        

        return result;
    }

    // Using recursion
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
