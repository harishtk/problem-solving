package leetcode;

import java.util.HashMap;

/**
 * Input: numbers = [2,7,11,15], target = 9
 * Output: [1,2]
 * Explanation: The sum of 2 and 7 is 9. Therefore, index1 = 1, index2 = 2. We return [1, 2].
 */
public class TwoSum2 {
    public static void main(String[] args) {
        int[] nums = {2, 7, 11, 15};
        int target = 9;

        System.out.println("Using HashMap: ");
        Util.printArr(twoSumHashMap(nums, target));

        System.out.println("Using two pointers: ");
        Util.printArr(twoSum(nums, target));

    }

    public static int[] twoSumHashMap(int[] numbers, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < numbers.length; i++) {
            int num = numbers[i];
            if (map.containsKey(num)) {
                return new int[] {map.get(num) + 1, i + 1};
            } else {
                int diff = target - num;
                map.put(diff, i);
            }
        }

        return new int[]{};
    }

    // Using Two Pointers approach
    public static int[] twoSum(int[] numbers, int target) {
        int i = 0, j = numbers.length - 1;

        while (i < j) {
            int currSum = numbers[i] + numbers[j];
            if (currSum == target) {
                return new int[] {i + 1, j + 1};
            } else if (currSum < target) {
                i++;
            } else {
                j--;
            }
        }

        return new int[]{};
    }

}
