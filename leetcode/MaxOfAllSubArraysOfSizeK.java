package leetcode;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class MaxOfAllSubArraysOfSizeK {
    public static void main(String[] args) {
        int[] nums = {1, 5, 4, 2, 9, 9, 9};
        int k = 3;

        System.out.println(maxOfAllSubArrays(nums, k));
    }

    public static List<Integer> maxOfAllSubArrays(int[] nums, int k) {
        final Deque<Integer> dq = new LinkedList<>();

        // Fill in the first window
        for (int i = 0; i < k; i++) {
            while (!dq.isEmpty() && nums[i] > nums[dq.peekLast()]) {
                dq.removeLast();
            }
            dq.addLast(i);
        }

        List<Integer> result = new ArrayList<>();
        for (int i = k; i < nums.length; i++) {
            result.add(nums[dq.peek()]);

            while (!dq.isEmpty() && nums[dq.peek()] <= i - k) {
                dq.removeFirst();
            }

            while (!dq.isEmpty() && nums[i] > nums[dq.peek()]) {
                dq.removeLast();
            }

            dq.addLast(i);
        }

        return result;
    }
}
