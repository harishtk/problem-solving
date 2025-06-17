package leetcode;

/**
 * Leetcode - 1971. Find if Path Exists in Graph
 * 
 * There is a bi-directional graph with n vertices, where each vertex is labeled from 0 to n - 1 (inclusive). 
 * The edges in the graph are represented as a 2D integer array edges, where each edges[i] = [ui, vi] 
 * denotes a bi-directional edge between vertex ui and vertex vi. Every vertex pair is connected by at most one 
 * edge, and no vertex has an edge to itself.
 *
 * You want to determine if there is a valid path that exists from vertex source to vertex destination.

 * Given edges and the integers n, source, and destination, return true if there is a valid path from source 
 * to destination, or false otherwise.
 */
public class ValidPathInGraph {
    
    public static void main(String[] args) {
        // n = 3, edges = [[0,1],[1,2],[2,0]], source = 0, destination = 2
        int n = 3;
        int[][] edges = {{0, 1}, {1, 2}, {2, 0}};
        int source = 0;
        int destination = 2;

       
        System.out.println(validPath(n, edges, source, destination));
    } 

    // Using Union-Find (Disjoint Set Union) to determine if a path exists
    public static boolean validPath(int n, int[][] edges, int source, int destination) {
        int[] par = new int[n];
        int[] size = new int[n];

        for (int i = 0; i < n; i++) {
            size[i] = 1;
            par[i] = i;
        }

        for (int[] edge : edges) {
            union(par, size, edge[0], edge[1]);
        }

        return findPar(par, source) == findPar(par, destination);
    }

    private static void union(int[] par, int[] size, int src, int dst) {
        int ultParentSrc = findPar(par, src);
        int ultParentDst = findPar(par, dst);

        if (ultParentSrc == ultParentDst) {
            return;
        }

        if (size[ultParentSrc] < size[ultParentDst]) {
            par[ultParentSrc] = ultParentDst;
            size[ultParentDst] += size[ultParentSrc];
        } else {
            par[ultParentDst] = ultParentSrc;
            size[ultParentSrc] += size[ultParentDst];
        }
    }

    private static int findPar(int[] par, int x) {
        if (par[x] != x) x = findPar(par, par[x]);
        return par[x];
    } 

}
