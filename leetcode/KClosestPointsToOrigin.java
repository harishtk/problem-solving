package leetcode;

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
        System.out.println();
        Util.printMatrix(kClosest2(points, k));
        System.out.println();
        Util.printMatrix(kClosest3(points, k));
    }

    public static int[][] kClosest(int[][] points, int k) {
        PriorityQueue<Pair<Double, int[]>> heap = new PriorityQueue<>(
            (Pair<Double, int[]> o1, Pair<Double, int[]> o2) -> Double.compare(o2.getKey(), o1.getKey())); 

        for (int[] pt : points) {
            double dist = Math.pow(pt[0], 2) + Math.pow(pt[1], 2);
            if (heap.size() < k) {
                heap.offer(new Pair<>(dist, pt));
            } else {
                heap.offer(new Pair<>(dist, pt));
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

    // Using Quick-Select (a modified version of quicksort)
    public static int[][] kClosest3(int[][] points, int k) {
        quickSelect(points, 0, points.length - 1, k);
        int[][] result = new int[k][2];
        System.arraycopy(points, 0, result, 0, k);
        return result;
    }

    private static void quickSelect(int[][] points, int left, int right, int k) {
        if (left >= right) return;

        int pivotIndex = partition(points, left, right);
        int leftLength = pivotIndex - left + 1;

        if (k < leftLength) {
            quickSelect(points, left, pivotIndex - 1, k);
        } else if (k > leftLength) {
            quickSelect(points, pivotIndex + 1, right, k - leftLength);
        }
    }

    private static int partition(int[][] points, int left, int right) {
        int[] pivot = points[right];
        double pivotDist = dist(pivot);
        int i = left;

        for (int j = left; j < right; j++) {
            if (dist(points[j]) < pivotDist) {
                swap(points, i, j);
                i++;
            }
        }

        swap(points, i, right);
        return i;
    }

    private static double dist(int[] point) {
        return point[0] * point[0] + point[1] * point[1];
    }

    private static void swap(int[][] points, int i, int j) {
        int[] temp = points[i];
        points[i] = points[j];
        points[j] = temp; 
    }
}
