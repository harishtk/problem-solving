package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GroupAnagrams {
    public static void main(String[] args) {
        String[] arr1 = new String[] {"eat","tea","tan","ate","nat","bat"};

        System.out.println("Input: " + Arrays.toString(arr1));
        System.out.println("Grouped Anagrams: " + groupAnagrams(arr1));
    }

    public static List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();

        for (String s : strs) {
            int[] counter = new int[26];
            for (char c : s.toCharArray()) {
                counter[c - 'a']++;
            }

            String key = Arrays.toString(counter);
            map.computeIfAbsent(key, k -> new ArrayList<>()).add(s);
        }

        return map.values().stream().collect(Collectors.toList());
    }
}
