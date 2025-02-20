package leetcode;

import java.util.*;

public class MinCostToConnectAllPoints {
    public static void main(String[] args) {
        int[][] points = {{0,0},{2,2},{3,10},{5,2},{7,0}};

        System.out.println(minCostConnectPoints(points));
    }

    public static int minCostConnectPoints(int[][] points) {
        int n = points.length;
        PriorityQueue<Pair<Integer, Integer>> heap = new PriorityQueue<>(
            Comparator.comparingInt(Pair::getKey)
        );
        Set<Integer> visited = new HashSet<>();
        heap.add(Pair.create(0, 0));

        int totalCost = 0;
        while (visited.size() < n) {
            Pair<Integer, Integer> curr = heap.poll();
            int d = curr.getKey();
            int i = curr.getValue();
            if (visited.contains(i)) continue;
            visited.add(i);
            totalCost += d;
            int xi = points[i][0];
            int yi = points[i][1];

            for (int j = 0; j < n; j++) {
                if (!visited.contains(j)) {
                    int xj = points[j][0];
                    int yj = points[j][1];
                    int neiDst = Math.abs(xi - xj) + Math.abs(yi - yj);
                    heap.add(Pair.create(neiDst, j));
                }
            }
        }

        return totalCost;
    }
}
