package leetcode;


public class MinSizeSubArraySum {
    public static void main(String[] args) {
        // int[] input = {2, 3, 1, 2, 4, 3};
        // int target = 7;
        int[] input = {1,2,3,4,5};
        int target = 11;

        System.out.println(minSubArrLen(target, input));
    }

    static int minSubArrLen(int target, int[] nums) {
        int n = nums.length;

        int shortest = Integer.MAX_VALUE, currMax = 0;
        boolean flag = false;
        for (int r = 0, l = 0; r < n; r++) {
            currMax += nums[r];
            System.out.println(String.format("l=%d r=%d currMax=%d shortest=%d", l, r, currMax, shortest));

            while (currMax >= target) {
                shortest = Math.min(shortest, r - l + 1);
                currMax -= nums[l];
                l++;
                flag = true;
                System.out.println(String.format("l=%d r=%d currMax=%d shortest=%d", l, r, currMax, shortest));
            }
        }

        return flag ? shortest : 0;
    }
}
