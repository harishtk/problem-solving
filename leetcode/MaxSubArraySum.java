package leetcode;

public class MaxSubArraySum {
    public static void main(String[] args) {
        int[] arr = {2, 3, -8, 7, -1, 2, 3};
        
        System.out.println("Top-Down Recursive approach -->");
        System.out.println(maxSubArraySum(arr, 0, 0));

        System.out.println("Bottom-Up approach Kadane's algorithm -->");
        System.out.println(maxSubArraySum(arr));
    }

    // Top-Down Recursive approach.
    static int maxSubArraySum(int[] arr, int start, int end) {
        if (end == arr.length) { return 0; }
        else if (start > end) {
            return maxSubArraySum(arr, 0, end + 1);
        } else {
            int currSum = 0;
            for (int i = start; i <= end; i++) {
                currSum += arr[i];
            }
            return Math.max(currSum, maxSubArraySum(arr, start + 1, end));
        }
    }

    // Bottom-Up approach Kadane's algorithm
    static int maxSubArraySum(int[] arr) {
        if (arr.length == 0) return 0;
        int res = arr[0], maxEnding = arr[0]; 

        for (int i = 1; i < arr.length; i++) {
            maxEnding = Math.max(arr[i], arr[i] + maxEnding);
            res = Math.max(res, maxEnding);
        }

        return res;
    }

    // Naive recursive
    static void printSubArrays(int[] arr, int start, int end) {
        if (end == arr.length) { return; }
        else if (start > end) {
            printSubArrays(arr, 0, end + 1);
        } else {
            for (int i = start; i <= end; i++) {
                System.out.print(arr[i] + " ");
            }
            System.out.println();
            printSubArrays(arr, start + 1, end);
        }
    }

    // Bruteforce approach
    private static void printSubArrays(int[] arr) {
        final int n = arr.length;

        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                
                for (int k = i; k <= j; k++) {
                    System.out.print(arr[k] + " ");
                }
                System.out.println();
            }
        }
    }
}
