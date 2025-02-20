package leetcode;

import java.util.HashSet;
import java.util.Set;

public class LongestSubStringWithoutRepeatingChars {
    public static void main(String[] args) {
        String input = "abcabcbb";

        System.out.println("Input: " + input);
        System.out.println(lengthOfLongestSubstringUsingSet(input));
        System.out.println(lengthOfLongestSubstringUsingLastIndex(input));
    }

    static int lengthOfLongestSubstringUsingSet(String s) {
        if (s.length() == 0 || s.length() == 1) return s.length();
        if (s.isBlank()) return 1;

        int n = s.length();
        Set<Character> visited = new HashSet<>();
        int maxWindowSize = 0;
        int left = 0, right;

        for (right = 0; right < n; right++) {
            while (visited.contains(s.charAt(right))) {
                visited.remove(s.charAt(left));
                left++;
            }

            visited.add(s.charAt(right));

            maxWindowSize = Math.max(maxWindowSize, right - left + 1);
        }

        return maxWindowSize;
    }

    static int lengthOfLongestSubstringUsingLastIndex(String s) {
        int n = s.length(), longest = 0;
        int[] nextIndex = new int[128];

        for (int r = 0, l = 0; r < n; r++) {
            l = Math.max(nextIndex[s.charAt(r)], l);
            longest = Math.max(longest, r - l + 1);
            nextIndex[s.charAt(r)] = r + 1;
        }

        return longest;
    }
}
