package leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
import java.util.concurrent.LinkedBlockingDeque;

public class FindGraphPath {
    public static void main(String[] args) {
        int n = 3;
        int[][] edges = {
            {0, 1}, {1, 2}, {2, 0}
        };
        int source = 0, destination = 2;

        HashMap<Integer, List<Integer>> graph = new HashMap<>();
        for (int[] edge : edges) {
            if (graph.get(edge[0]) == null) graph.put(edge[0], new ArrayList<>());
            if (graph.get(edge[1]) == null) graph.put(edge[1], new ArrayList<>());
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }
        System.out.println(graph);

        System.out.println(findPath(graph, source, destination, new HashSet<>()));
        System.out.println(findPath(graph, source, destination));
        System.out.println(findPathBfs(graph, source, destination));
    }

    // DFS with recursion
    static boolean findPath(HashMap<Integer, List<Integer>> graph, int source, int destination, Set<Integer> visited) {
        if (source == destination) { return true; }
        
        for (int edge : graph.get(source)) {
            if (!visited.contains(edge)) {
                visited.add(edge);
                if (findPath(graph, edge, destination, visited)) {
                    return true;
                }
            }
        }

        return false;
    }

    // DFS with stack
    static boolean findPath(HashMap<Integer, List<Integer>> graph, int source, int destination) {
        Stack<Integer> st = new Stack<>();
        Set<Integer> visited = new HashSet<>();
        
        visited.add(source);
        st.push(source);
        while (!st.isEmpty()) {
            int node = st.pop();
            if (node == destination) {
                return true;
            }

            for (int edge : graph.get(node)) {
                if (!visited.contains(edge)) {
                    visited.add(edge);
                    st.push(edge);
                }
            }
        } 

        return false;
    }

    // BFS with Deque
    static boolean findPathBfs(HashMap<Integer, List<Integer>> graph, int source, int destination) {
        Deque<Integer> dq = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();
        
        visited.add(source);
        dq.add(source);

        while (!dq.isEmpty()) {
            int node = dq.poll();
            if (node == destination) { return true; }
            for (int edge : graph.get(node)) {
                if (!visited.contains(edge)) {
                    visited.add(edge);
                    dq.add(edge);
                }
            }
        }

        return false;
    }
}
