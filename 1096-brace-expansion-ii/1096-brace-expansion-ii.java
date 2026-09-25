import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

class Solution {
    private TreeSet<String> resultSet = new TreeSet<>();

    public List<String> braceExpansionII(String expression) {
        dfs(expression);
        return new ArrayList<>(resultSet);
    }

    private void dfs(String exp) {
        // Find the first closing brace
        int closeBracePos = exp.indexOf('}');
        
        // Base case: if there are no braces left, add the final word string to the set
        if (closeBracePos == -1) {
            resultSet.add(exp);
            return;
        }

        // Find the matching opening brace for this closing brace
        int openBracePos = exp.lastIndexOf('{', closeBracePos);

        // Split the expression into three parts:
        // 'before' the opening brace, 'inside' the braces, and 'after' the closing brace
        String before = exp.substring(0, openBracePos);
        String after = exp.substring(closeBracePos + 1);
        String[] options = exp.substring(openBracePos + 1, closeBracePos).split(",");

        // Recursively evaluate the options combined with the rest of the string
        for (String option : options) {
            dfs(before + option + after);
        }
    }
}
