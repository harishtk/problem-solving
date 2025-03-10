package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LetterCombinations {
    static HashMap<Character, String> lettersMap = new HashMap<>();
    
    LetterCombinations() {
        lettersMap.put('2', "abc");
        lettersMap.put('3', "def");
        lettersMap.put('4', "ghi");
        lettersMap.put('5', "jkl");
        lettersMap.put('6', "mno");
        lettersMap.put('7', "pqrs");
        lettersMap.put('8', "tuv");
        lettersMap.put('9', "wxyz");
    }

    public static void main(String[] args) {
        String digits = "23";
        final LetterCombinations app = new LetterCombinations();

        System.out.println(letterCombinations(digits));
    }

    public static List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();
        if (digits.isBlank()) return result;
        backtrack(digits, 0, digits.length(), new StringBuilder(), result);
        return result;
    }

    static void backtrack(String digits, int i, int n, StringBuilder sol, List<String> result) {
        if (i == n) {
            result.add(sol.toString());
            return;
        }

        for (char letter : lettersMap.getOrDefault(digits.charAt(i), "").toCharArray()) {
            sol.append(String.valueOf(letter));
            backtrack(digits, i + 1, n, sol, result);
            sol.delete(sol.length() - 1, sol.length());
        }
    }
}
