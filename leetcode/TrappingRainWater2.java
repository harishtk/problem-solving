package leetcode;

/**
 * 42. Trapping Rain Water
 * 
 * Given n non-negative integers representing an elevation map where 
 * the width of each bar is 1, compute how much water it can trap after raining.
 */
public class TrappingRainWater2 {
    public static void main(String[] args) {
        int[] height = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};

        System.out.println(trap(height));
    }

    static int trap(int[] height) {
        int n = height.length;
        int leftWall = 0, rightWall = 0;

        int[] leftMax = new int[n];
        int[] rightMax = new int[n];

        for (int i = 0; i < n; i++) {
            leftMax[i] = leftWall;
            leftWall = Math.max(leftWall, height[i]);
        }

        for (int j = n - 1; j >= 0; j--) {
            rightMax[j] = rightWall;
            rightWall = Math.max(rightWall, height[j]);
        }

        int sum = 0; 
        for (int i = 0; i < n; i++) {
            int container = Math.min(leftMax[i], rightMax[i]);
            sum += Math.max(0, container - height[i]);
        }

        return sum;
    }
}
