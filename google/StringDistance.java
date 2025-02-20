package google;

import java.util.Arrays;

public class StringDistance {
    public static void main(String[] args) {
        final String[] input = new String[] {
            "codechef",
            "fochf",
            "aa",
            "alq",
            "ab",
            "ab"
        };

        for (int i = 0; i < input.length; i += 2) {
            final String s1 = input[i];
            final String s2 = input[i + 1];

            // final int m = s1.length();
            // final int n = s2.length();
            // final int[][] memo = new int[m + 1][n + 1];
            // for (int[] row : memo) Arrays.fill(row, -1);

            // // System.out.println(editDistance(s1, s2, s1.length(), s2.length()));
            // System.out.println(editDistanceMemo(s1, s2, m, n, memo));

            // System.out.println(editDistanceDp(s1, s2));
            // System.out.println(editDistanceDpOptimized(s1, s2));
            System.out.println(editDistanceFast(s1, s2));
        }
    }

    static int editDistance(String s1, String s2, int m, int n) {
        // If the first string is empty, then only option is to 
        // insert all characters of second string to first
        if (m == 0) return n;

        // If second string is empty, then only option is to
        // remove all characters of first string
        if (n == 0) return m;

        // System.out.println(String.format("%s == %s", s1.charAt(m - 1), s2.charAt(n - 1)));
        if (s1.charAt(m - 1) == s2.charAt(n - 1)) {
            return editDistance(s1, s2, m - 1, n - 1);
        }

        return 1 + Math.min(
            Math.min(
                editDistance(s1, s2, m, n - 1), 
                editDistance(s1, s2, m - 1, n)), 
            editDistance(s1, s2, m - 1, n - 1)
        );
    }

    static int editDistanceMemo(String s1, String s2, int m, int n, int[][] memo) {
        // If the first string is empty, then only option is to 
        // insert all characters of second string to first
        if (m == 0) return n;

        // If second string is empty, then only option is to
        // remove all characters of first string
        if (n == 0) return m;

        if (memo[m][n] != -1) return memo[m][n];

        // System.out.println(String.format("%s == %s", s1.charAt(m - 1), s2.charAt(n - 1)));
        if (s1.charAt(m - 1) == s2.charAt(n - 1)) {
            memo[m][n] = editDistanceMemo(s1, s2, m - 1, n - 1, memo);
        } else {
            memo[m][n] = 1 + Math.min(
                    Math.min(
                            editDistanceMemo(s1, s2, m, n - 1, memo),
                            editDistanceMemo(s1, s2, m - 1, n, memo)),
                    editDistanceMemo(s1, s2, m - 1, n - 1, memo));
        }

        return memo[m][n];
    }

    // Using DP (Bottom-Up approach)
    static int editDistanceDp(String s1, String s2) {
        final int m = s1.length();
        final int n = s2.length();
        int[][] dp = new int[m + 1][n + 1];

        // Fill the known entries in dp[][]
        // If one string is empty, then answer
        // is length of the other string.
        for (int i = 0; i <= m; i++) {
            dp[i][0] = i;
        }

        for (int j = 0; j <=n; j++) {
            dp[0][j] = j;
        }

        // Fill the rest of dp[][]
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = 1 + Math.min(
                        Math.min(
                            dp[i][j - 1], 
                            dp[i - 1][j]),
                        dp[i - 1][j - 1]
                    );
                }
            }
        }

        return dp[m][n];
    }

    static int editDistanceDpOptimized(String s1, String s2) {
        final int m = s1.length();
        final int n = s2.length();
        int[] prev = new int[n + 1];
        int[] curr = new int[n + 1];

        for (int j = 0; j <= n; j++) {
            prev[j] = j;
        }

        // Rest of the rows
        for (int i = 1; i <= m; i++) {
            curr[0] = i;
            for (int j = 1; j <= n; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    curr[j] = prev[j - 1];
                } else {
                    curr[j] = 1 + Math.min(
                        curr[j - 1],
                        Math.min(prev[j], prev[j - 1])
                    );
                }
            }
            int[] temp = prev;
            prev = curr;
            curr = temp;
        }

        return prev[n];
    }

    static int editDistanceFast(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();
        int[][] dp = new int[m][n];
        for (int[] row : dp) Arrays.fill(row, -1);

        return fill(s1, s2, m - 1, n - 1, dp);
    }

    static int fill(String s1, String s2, int i, int j, int[][] dp) {
        // Base case
        if (i < 0) return j + 1;
        if (j < 0) return i + 1;

        if (dp[i][j] != -1) return dp[i][j];

        if (s1.charAt(i) == s2.charAt(j)) {
            return dp[i][j] = fill(s1, s2, i - 1, j - 1, dp);
        } else {
            return dp[i][j] = 1 + Math.min(
                fill(s1, s2, i - 1, j - 1, dp), 
                Math.min(
                    fill(s1, s2, i, j - 1, dp),
                    fill(s1, s2, i - 1, j, dp)
                )
            );
        }
    }
}
