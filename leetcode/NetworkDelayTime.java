package leetcode;

import java.util.*;

/** Leetcode - 743
 * You are given a network of n nodes, labeled from 1 to n. You are also given times,
 *  a list of travel times as directed edges times[i] = (ui, vi, wi), where ui is the source node, 
 * vi is the target node, and wi is the time it takes for a signal to travel from source to target.
 * 
 * We will send a signal from a given node k. Return the minimum time it takes for 
 * all the n nodes to receive the signal. If it is impossible for all the n nodes 
 * to receive the signal, return -1.
 */
public class NetworkDelayTime {

    static final int SOURCE = 0;
    static final int DEST = 1;
    static final int TIME = 2;

    public static void main(String[] args) {
        int[][] times = {{2,1,1},{2,3,1},{3,4,1}};
        int n = 4, k = 2;

        System.out.println(networkDelayTime(times, n, k));
    }

    public static int networkDelayTime(int[][] times, int n, int k) {
        HashMap<Integer, List<Pair<Integer, Integer>>> graph = new HashMap<>();

        for (int i = 0; i < times.length; i++) {
            int[] edge = times[i];
            int src = edge[SOURCE];
            int dst = edge[DEST];
            int time = edge[TIME];
            if (graph.get(src) == null) graph.put(src, new ArrayList<>());
            graph.get(src).add(Pair.create(time, dst));
        }

        HashMap<Integer, Integer> minTimes = new HashMap<>();
        PriorityQueue<Pair<Integer, Integer>> heap = new PriorityQueue<>(
            Comparator.comparingInt(Pair::getKey)
        );
        heap.add(new Pair<Integer, Integer>(0, k));

        while (!heap.isEmpty()) {
            Pair<Integer, Integer> curr = heap.poll();
            int timeToI = curr.getKey();
            int i = curr.getValue();

            if (minTimes.keySet().contains(i)) { continue; }
            minTimes.put(i, timeToI);
            for (Pair<Integer, Integer> neigh : graph.getOrDefault(i, Collections.emptyList())) {
                int neighTime = neigh.getKey();
                int neighNode = neigh.getValue();
                heap.add(new Pair<Integer, Integer>(timeToI + neighTime, neighNode));
            }

        }

        if (minTimes.keySet().size() == n) {
            return Collections.max(minTimes.values());
        } else {
            return -1;
        }
    }

    // Using Dijkstra's algorithm
    public static int networkDelayTime2(int[][] times, int n, int k) { 
        
    }
}
