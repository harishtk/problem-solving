package leetcode;

public class ContainerWithMostWater {
    public static void main(String[] args) {
        int[] height = {1, 8, 6, 2, 5, 4, 8, 3, 7};

        System.out.println(maxArea(height));
    }

    // Using two pointers approach.
    public static int maxArea(int[] height) {
        int l = 0, r = height.length - 1;

        int max = 0;
        while (l < r) {
            int n = Math.min(height[l], height[r]);
            int h = (r - l);
            int area = h * n;
            
            max = Math.max(max, area);

            while (l < r && height[l] <= n) l++;
            while (l < r && height[r] <= n) r--;
        }

        return max;
    }
}
