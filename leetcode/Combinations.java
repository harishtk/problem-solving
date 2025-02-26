package leetcode;

import java.util.ArrayList;
import java.util.List;

public class Combinations {
    public static void main(String[] args) {
        int n = 4, k = 2;

        List<List<Integer>> result = new ArrayList<>();
        f(n, k, new ArrayList<>(), result);
        System.out.println("Top-Down Recursion");
        System.out.println(result);
        result.clear();

        System.out.println("Bottom-Up Recursion");
        backtrack(n, 1, k, new ArrayList<>(), result);
        System.out.println(result);
        result.clear();
    }

    // Top-Down Recursion
    static void f(int n, int k, List<Integer> sol, List<List<Integer>> result) {
        if (sol.size() == k) {
            result.add(new ArrayList<>(sol));
            return;
        }

        if (n > k - sol.size()) {
            f(n - 1, k, sol, result);
        }

        sol.add(n);
        f(n - 1, k, sol, result);
        sol.remove(sol.size() - 1);
    }

    static void backtrack(int n, int current, int k, List<Integer> sol, List<List<Integer>> result) {
        if (k == 0) {
            result.add(new ArrayList<>(sol));
            return;
        }

        for (int i = current; i <= n; i++) {
            sol.add(i);
            backtrack(n, i + 1, k - 1, sol, result);
            sol.remove(sol.size() - 1);
        }

    }
}
