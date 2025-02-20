package google;

import java.util.ArrayList;
import java.util.List;

public class ValidParenthesis {
    public static void main(String[] args) {
        List<String> result = new ArrayList<>();

        generateParenthesis(3, 0, 0, new String(), result);

        for (String s : result) System.out.println(s);
    }

    static void generateParenthesis(int n, int open, int close, String s, List<String> result) {

        // If the count of both open and close parentheses
        // reaches n, it means we have generated a valid parentheses
        if (open == n && close == n) {
            result.add(s);
            return;
        }

        // At any index i in the generation of the string s,
        // we can put an open parantheses only if its count
        // until that time is less than n
        if (open < n) {
            generateParenthesis(n, open + 1, close, s + "(", result);
        }

        // At any index i in the generation of the string s,
        // we can put an closed parentheses only if its count
        // until that time is less than m
        if (close < open) {
            generateParenthesis(n, open, close + 1, s + ")", result);
        }
    }
}
