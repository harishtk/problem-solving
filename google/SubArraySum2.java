package google;

import java.util.Stack;

import leetcode.Util;

public class SubArraySum2 {
    public static void main(String[] args) {
        final long[][] input = new long[][] {
            {1, 4, 9}
        };

        for (long[] line : input) {
            int N = line.length;

            long sumMax = calculateSum(line, N, true);
            long sumMin = calculateSum(line, N, false);

            System.out.println(sumMax - sumMin);
        }
    }

    /*
    * This problem can be broken down into two parts. We can first find the 
    * sum of the maximum elements of all the subarrays and the sum of the minimum 
    * elements of all the subarrays and find their difference. To find the 
    * minimum elements of all the subarrays we calculate two arrays - prefix 
    * array and suffix array. In prefix array we store the index of the element 
    * to the left, which is smaller than the element at the current index. And 
    * in suffix array we store the index of the element to the right, which is 
    * smaller than the element at the current index. Now we know for each index 
    * the range in which the current element is minimum. So for each element 
    * we can add the product of the current element with the range in which 
    * it is minimum in. This way we get the sum of minimum elements for all 
    * subarrays. We can do the same for maximum elements for all subarrays.
    */
    static long calculateSum(long[] arr, int n, boolean findMax) {
        long[] left = new long[n];
        long[] right = new long[n];

        Stack<Integer> st = new Stack<>();

        for (int i = 0; i < n; i++) {
            while (!st.isEmpty() && (findMax ? arr[st.peek()] <= arr[i] : arr[st.peek()] >= arr[i])) {
                st.pop();
                System.out.println(st);
            }
            left[i] = st.isEmpty() ? -1 : st.peek();
            st.push(i);
            System.out.println(st);
        }
        Util.printArr(left);
        st.clear();
        
        for (int i = n - 1; i >= 0; i--) {
            while (!st.isEmpty() && (findMax ? arr[st.peek()] < arr[i] : arr[st.peek()] > arr[i])) {
                st.pop();
            }
            right[i] = st.isEmpty() ? n : st.peek();
            st.push(i);
        }
        Util.printArr(right);

        long sum = 0;
        for (int i = 0; i < n; i++) {
            sum += arr[i] * (i - left[i]) * (right[i] - i);
        }

        return sum;
    }
}
