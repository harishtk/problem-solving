package leetcode;

import java.util.Stack;

/**
 * 42. Trapping Rain Water
 * 
 * Given n non-negative integers representing an elevation map where 
 * the width of each bar is 1, compute how much water it can trap after raining.
 */
public class TrappingRainWater2 {
    public static void main(String[] args) {
        int[] height = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};

        System.out.println("Naive approach");
        System.out.println(trapNaive(height));

        System.out.println("Better approach");
        System.out.println(trap(height));

        System.out.println("Using Two Pointers");
        System.out.println(trapTwoPointers(height));

        System.out.println("Using Stack");
        System.out.println(trapUsingStack(height));
        int[] height2 = {2, 1, 5, 3, 1, 0, 4};

        System.out.println(trapUsingStack(height2));
        
    }

    // Naive approach - O(n^2) - O(1)
    static int trapNaive(int[] height) {
        int res = 0;

        for (int i = 0; i < height.length; i++) {

            int left = height[i];
            for (int j = 0; j < i; j++) {
                left = Math.max(left, height[j]);
            }

            int right = height[i];
            for (int j = i + 1; j < height.length; j++) {
                right = Math.max(right, height[j]);
            }

            res += Math.min(left, right) - height[i];
        }

        return res;
    }

    // Better approach - Prefix and Suffix max for each index.
    // O(n) - O(n)
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

    // Using two pointers approach - O(n) - O(1)
    static int trapTwoPointers(int[] height) {
        int n = height.length;

        int lMax = height[0];
        int rMax = height[n - 1];
        int left = 1, right = n - 2;

        int res = 0;
        while (left <= right) {
            if (rMax <= lMax) { // Calc right water
                res += Math.max(0, rMax - height[right]);
                rMax = Math.max(rMax, height[right]);
                right--;
            } else { // Calc left water
                res += Math.max(0, lMax - height[left]);
                lMax = Math.max(lMax, height[left]);
                left++;
            }
        }

        return res;
    }

    // Using Stack - O(n) - O(n)
    static int trapUsingStack(int[] height) {
        // A stack to store all the indices of heights
        Stack<Integer> st = new Stack<>();
        int res = 0;

        for (int i = 0; i < height.length; i++) {
            while (!st.isEmpty() && height[st.peek()] < height[i]) {
                int popHeight = height[st.pop()];

                if (st.isEmpty()) break;
                
                int distance = i - st.peek() - 1;

                int water = Math.min(height[st.peek()], height[i]);
                water -= popHeight;
                
                res += distance * water;
            }
            st.push(i);
        }

        return res;
    }
}

/* 
ip {2, 1, 5, 3, 1, 0, 4}

i = 0, [0] = 2
st = 0, 
---------
i = 1, [1] = 1
st = 0, 1
----------
i = 2, [2] = 5

loop@1 [1] = 1 < [2] = 5
popHeight = [1] = 1
st = 0,

dist = 2 - 0 - 1 = 1

water = min([0] = 2, [2] = 5) = 2
water -= 1 = 1

res += 1 * 1 = 1;

loop@2
popHeight = [0] = 2
st = ,

st = 2
----------
i = 3, [3] = 3
st = 2, 3
----------
i = 4, [4] = 1
st = 2, 3, 4
----------
i = 5, [5] = 0
st = 2, 3, 4, 5
----------
i = 6, [6] = 4

loop@1
popHeight = [5] = 0
st = 2, 3, 4

dist = 6 - 4 - 1 = 1

water = min([4] = 1, [6] = 4) = 1
water -= 0 = 1

res += 1 * 1 = 2

loop@2
popHeight = [4] = 1
st = 2, 3

dist = 6 - 3 - 1 = 2

water = min([3] = 3, [6] = 4) = 3
water -= 1 = 2

res += 2 * 2 = 6

loop@3 [3] = 3 < [6] 4
popHeight = [3] = 3
st = 2

dist = 6 - 2 - 1 = 3

water = min([2] = 5, [6] = 4) = 4
water -= 3 = 1

res += 3 * 1 = 9

loop@4 [2] = 5 < [6] = 4
st = 2, 6
----------

res = 9
*/