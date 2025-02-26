package leetcode;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 973. K Closest Points to Origin
 * 
 * Given an array of points where points[i] = [xi, yi] represents a 
 * point on the X-Y plane and an integer k, return the k closest points 
 * to the origin (0, 0).
 * 
 * The distance between two points on the X-Y plane is the Euclidean distance (i.e., √(x1 - x2)2 + (y1 - y2)2).
 * 
 * You may return the answer in any order. The answer is guaranteed to be unique (except for the order that it is in).
 */
public class KClosestPointsToOrigin {
    public static void main(String[] args) {
        int[][] points = {{3, 3}, {5, -1}, {-2, 4}};
        int k = 2;   

        Util.printMatrix(kClosest(points, k));
        Util.printMatrix(kClosest2(points, k));
    }

    public static int[][] kClosest(int[][] points, int k) {
        PriorityQueue<Pair<Double, int[]>> heap = new PriorityQueue<>(
            new Comparator<Pair<Double, int[]>>() {
                @Override
                public int compare(Pair<Double, int[]> o1, Pair<Double, int[]> o2) {
                    return Double.compare(o2.getKey(), o1.getKey());
                }
                
            }
        ); 

        for (int[] pt : points) {
            double dist = Math.pow(pt[0], 2) + Math.pow(pt[1], 2);
            if (heap.size() < k) {
                heap.offer(new Pair<Double,int[]>(dist, pt));
            } else {
                heap.offer(new Pair<Double,int[]>(dist, pt));
                heap.poll();
            }
        }

        int[][] ret = new int[k][2];
        for (int i = k - 1; i >= 0; i--) {
            int[] pt = heap.poll().getValue();
            ret[i] = pt;
        }
        return ret;
    }

    public static int[][] kClosest2(int[][] points, int k) {
        // Custom comparator to sort based on distance
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>((a, b) -> {
            int distA = a[0] * a[0] + a[1] * a[1];
            int distB = b[0] * b[0] + b[1] * b[1];
            return Integer.compare(distB, distA); // Max-heap
        });

        for (int[] point : points) {
            maxHeap.add(point);
            if (maxHeap.size() > k) {
                maxHeap.poll(); // Remove the farthest point
            }
        }

        // Convert the heap to an array
        int[][] result = new int[k][2];
        for (int i = 0; i < k; i++) {
            result[i] = maxHeap.poll();
        }

        return result;
    }
}
