package leetcode;

public class ClimbingStairs {
    public static void main(String[] args) {
        System.out.println(climbStairs(2));
    }

    public static int climbStairs(int n) {
        // n=3={1, 2, 3} 
        // {1, 1, 1}=3 {1, 2}=3 {2, 1}=3
        if (n == 1) { return 1; }
        if (n == 2) { return 2; } 
        
        int twoBack = 1;
        int oneBack = 2;

        for (int i = 2; i < n; i++) {
            int next = twoBack + oneBack;
            twoBack = oneBack;
            oneBack = next;
        }

        return oneBack;
    }
}
