package leetcode;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Stack;

public class DailyTemperatures {
    public static void main(String[] args) {
        int[] temperatures1 = new int[]{73,74,75,71,69,72,76,73}; // ans: [1,1,4,2,1,1,0,0]
        int[] temperatures2 = new int[]{30,40,50,60}; // ans: [1,1,1,0]
        int[] temperatures3 = new int[]{30, 60, 90}; // ans: [1, 1, 0]

        System.out.println("Input:  " + Arrays.toString(temperatures1));
        System.out.println("Output: " + Arrays.toString(dailyTemperatures(temperatures1)));
    }

    public static int[] dailyTemperatures(int[] temperatures) {
        Stack<int[]> st = new Stack<>();

        int n = temperatures.length;
        int[] result = new int[n];

        for (int i = 0; i < n; i++) {
            int temp = temperatures[i];
            if (st.isEmpty() || st.peek()[0] > temp) {
                st.push(new int[]{temp, i});
            } else {
                while (!st.isEmpty() && st.peek()[0] < temp) {
                    int[] prev = st.pop();
                    int dist = i - prev[1];
                    result[prev[1]] = dist;
                }
                st.push(new int[]{temp, i});
            }
        }

        return result;
    }

    public static int[] dailyTemperaturesUsingDeque(int[] temperatures) {
        Deque<int[]> dq = new ArrayDeque<>();

        int n = temperatures.length;
        int[] result = new int[n];

        for (int i = 0; i < n; i++) {
            int temp = temperatures[i];
            if (dq.isEmpty() || dq.getLast()[0] > temp) {
                dq.offerLast(new int[]{temp, i});
            } else {
                while (!dq.isEmpty() && dq.getLast()[0] < temp) {
                    int[] prev = dq.removeLast();
                    int dist = i - prev[1];
                    result[prev[1]] = dist;
                }
                dq.offerLast(new int[]{temp, i});
            }
        }

        return result;
    }
}
