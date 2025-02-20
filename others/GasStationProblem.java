package others;

import java.util.Arrays;

public class GasStationProblem {
    public static void main(String[] args) {
        int N = 4;
        // int[] pumps = {4, 5, 7, 4};
        // int[] cost = {6, 6, 3, 5};
        int[] pumps = {5, 5, 1, 3, 4};
        int[] cost = {8, 1, 7, 1, 1};

        System.out.print("Gas:  ");
        Util.printArr(pumps);
        System.out.print("Cost: ");
        Util.printArr(cost);
        System.out.println(canTourGreedyOptimized(N, pumps, cost));
    }

    static int canTour(int n, int[] pumps, int[] cost) {
        int startIdx = -1;

        for (int i = 0; i < n; i++) {
            
            int tank = 0;
            boolean flag = true;
            for (int j = 0; j < n; j++) {
                
                int idx = (i + j) % n;
                tank = tank + pumps[idx] - cost[idx];

                // If available gas is less than zero, then it isn't
                // possible to proceed further with this starting point
                if (tank < 0) {
                    flag = false;
                    break;
                }
            }

            // If flag is true then we have found the
            // valid starting point
            if (flag) {
                startIdx = i;
                break;
            }
        }

        return startIdx;
    }

    static int canTourGreedy(int n, int[] pumps, int[] cost) {
        int startIdx = 0;

        // Initially the tank is empty
        int tank = 0;

        for (int i = 0; i < n; i++) {
            tank = tank + pumps[i] - cost[i];

            if (tank < 0) {
                startIdx = i + 1;
                tank = 0;
            }
        }

        // Checking if startIdx can be a valid
        // starting point for the circular tour
        tank = 0;
        for (int i = 0; i < n; i++) {
            // Circular indx
            int idx = (i + startIdx) % n;
            tank = tank + pumps[idx] - cost[idx];
            if (tank < 0) {
                return -1;
            }
        }

        return startIdx;
    }

    static int canTourGreedyOptimized(int n, int[] pumps, int[] cost) {
        int totalTank = 0;
        int tank = 0;
        int startIdx = 0;

        for (int i = 0; i < n; i++) {
            tank += pumps[i] - cost[i];
            totalTank += pumps[i] - cost[i];

            if (tank < 0) {
                startIdx = i + 1;
                tank = 0;
            }
        }

        if (totalTank < 0) {
            return -1;
        }

        return startIdx;
    }
}
