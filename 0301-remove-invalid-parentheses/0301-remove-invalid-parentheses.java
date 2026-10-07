import java.util.ArrayList;
import java.util.List;

public class Solution {
    public List<String> removeInvalidParentheses(String s) {
        int leftRem = 0, rightRem = 0;

        // Step 1: Count the minimum number of left and right parentheses to remove
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                leftRem++;
            } else if (c == ')') {
                if (leftRem > 0) {
                    leftRem--; // Valid pair found, reduce left count
                } else {
                    rightRem++; // Misplaced right parenthesis
                }
            }
        }

        List<String> result = new ArrayList<>();
        dfs(s, 0, leftRem, rightRem, result);
        return result;
    }

    private void dfs(String s, int start, int leftRem, int rightRem, List<String> result) {
        // Base Case: If no more removals are needed, check if the current string is valid
        if (leftRem == 0 && rightRem == 0) {
            if (isValid(s)) {
                result.add(s);
            }
            return;
        }

        for (int i = start; i < s.length(); i++) {
            // Optimization: Skip duplicate characters to avoid duplicate branches
            if (i > start && s.charAt(i) == s.charAt(i - 1)) {
                continue;
            }

            char c = s.charAt(i);
            // Try removing a '('
            if (c == '(' && leftRem > 0) {
                dfs(s.substring(0, i) + s.substring(i + 1), i, leftRem - 1, rightRem, result);
            }
            // Try removing a ')'
            if (c == ')' && rightRem > 0) {
                dfs(s.substring(0, i) + s.substring(i + 1), i, leftRem, rightRem - 1, result);
            }
        }
    }

    // Helper method to check if a string has balanced parentheses
    private boolean isValid(String s) {
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                count++;
            } else if (c == ')') {
                count--;
                if (count < 0) return false; // More ')' than '(' at any point means invalid
            }
        }
        return count == 0;
    }
}
