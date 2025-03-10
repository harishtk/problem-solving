package leetcode;

import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class MaxOfDistinctSubArraysOfK {
    public static void main(String[] args) {
        int[] nums = {1, 5, 4, 2, 9, 9, 9};
        int k = 3;

        System.out.println(maximumSubarraySum(nums, k));
        System.out.println(maximumSubarraySumUsingSet(nums, k));
    }

    public static long maximumSubarraySum(int[] nums, int k) {
        int n = nums.length;
        HashMap<Integer, Integer> map = new HashMap<>();

        long currSum = 0, maxSum = 0;
        int left = 0, i = 0;

        while (i < k && i < n) {
            currSum += nums[i];
            if (map.containsKey(nums[i])) {
                map.put(nums[i], map.get(nums[i]) + 1);
            } else {
                map.put(nums[i], 1);
            }
            i++;
        }

        if (map.size() == k) { 
            maxSum = currSum;
        }

        for (i = k; i < n; i++) {
            if (map.containsKey(nums[i])) {
                map.put(nums[i], map.get(nums[i]) + 1);
            } else {
                map.put(nums[i], 1);
            }

            if (map.containsKey(nums[left])) {
                map.put(nums[left], map.get(nums[left]) - 1);

                if (map.get(nums[left]) <= 0) {
                    map.remove(nums[left]);
                }
            }
            

            currSum += nums[i];
            currSum -= nums[left];

            if (map.size() == k) {
                maxSum = Math.max(maxSum, currSum);
            }

            left++;
        }

        return maxSum;
    }

    public static long maximumSubarraySumUsingSet(int[] nums, int k)  {
        int n = nums.length;
        if (k > n) return 0;

        long maxSum = 0, currSum = 0;
        int i = 0;
        HashSet<Integer> set = new HashSet<>();
        
        for (int j = 0; j < n; j++) {
            while (set.size() == k || set.contains(nums[j])) {
                currSum -= nums[i];
                set.remove(nums[i]);
                i++;
            }

            currSum += nums[j];
            set.add(nums[j]);

            if ((j - i + 1) == k) {
                maxSum = Math.max(maxSum, currSum);
            }
        }

        return maxSum;
    }
}