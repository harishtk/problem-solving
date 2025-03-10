package leetcode;

public class FindClosestNumber {
    public static void main(String[] args) {
        int[] input1 = {-4, -2, 1, 4, 8};
        int[] input2 = {-10000, 10000};
        int[] input3 = {-1000, -1000};
        int[] input4 = {2, 1, 1, -1, 100000};

        Util.printArr(input1);
        System.out.println("Ans: " + findClosestNumber(input1));

        Util.printArr(input2);
        System.out.println("Ans: " + findClosestNumber(input2));

        Util.printArr(input3);
        System.out.println("Ans: " + findClosestNumber(input3));

        Util.printArr(input4);
        System.out.println("Ans: " + findClosestNumber(input4));
    }

    static int findClosestNumber(int[] nums) {
        int min = Integer.MAX_VALUE;
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i] < 0 ? -nums[i] : nums[i];
            if (num < min || (num == min && nums[i] > 0)) {
                min = num;
                res = nums[i];
            }
        }

        return res;
    }
}
