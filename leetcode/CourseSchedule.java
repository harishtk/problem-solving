package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class CourseSchedule {
    public static void main(String[] args) {
        // numCourses = 2, prerequisites = [[1,0]]
        // int numCourses = 2;
        // int[][] prerequisites = {{1, 0}};

        int numCourses = 2;
        int[][] prerequisites = {{1, 0}, {0, 1}};

        // System.out.println(canFinish(numCourses, prerequisites));
        List<Integer> res = new ArrayList<>();
        int[] order = findOrder(numCourses, prerequisites);
        for (int i = 0; i < order.length; i++) res.add(order[i]);
        System.out.println(res);
    }

    static final int UNVISITED  = 0;
    static final int VISITING   = 1;
    static final int VISITED    = 2;

    public static boolean canFinish(int numCourses, int[][] prerequisites) {
        HashMap<Integer, List<Integer>> graph = new HashMap<>();

        for (int[] prerequisite : prerequisites) {
            int course1 = prerequisite[0];
            int course2 = prerequisite[1];
            if (graph.get(course1) == null) graph.put(course1, new ArrayList<>());
            if (graph.get(course2) == null) graph.put(course2, new ArrayList<>());
            graph.get(course1).add(course2);
        }

        int[] states = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            if (!dfs(graph, i, states)) {
                return false;
            }
        }
        return true;
    }

    static boolean dfs(HashMap<Integer, List<Integer>> graph, int i, int[] states) {
        int state = states[i];
        if (state == VISITED) { return true; }
        else if (state == VISITING) { return false; }
        
        states[i] = VISITING;

        for (int neigh : graph.get(i)) {
            if (!dfs(graph, neigh, states)) { return false; }
        }

        states[i] = VISITED;
        return true;
    }

    static int orderIdx;
    public static int[] findOrder(int numCourses, int[][] prerequisites) {
        HashMap<Integer, List<Integer>> graph = new HashMap<>();

        for (int[] prerequisite : prerequisites) {
            int course1 = prerequisite[0];
            int course2 = prerequisite[1];
            if (graph.get(course1) == null) graph.put(course1, new ArrayList<>());
            if (graph.get(course2) == null) graph.put(course2, new ArrayList<>());
            graph.get(course1).add(course2);
        }

        int[] states = new int[numCourses];
        int[] order = new int[numCourses];
        orderIdx = 0;
        for (int i = 0; i < numCourses; i++) {
            if (!dfs(graph, i, states, order)) {
                return new int[]{}; 
            }
        }

        return order;   
    }

    static boolean dfs(HashMap<Integer, List<Integer>> graph, int i, int[] states, int[] order) {
        int state = states[i];
        if (state == VISITED) { return true; }
        else if (state == VISITING ) { return false; }

        states[i] = VISITING;

        for (int neigh : graph.getOrDefault(i, Collections.emptyList())) {
            if (!dfs(graph, neigh, states, order)) {
                return false;
            }
        }
        states[i] = VISITED;
        order[orderIdx++] = i;
        return true;
    }
}
