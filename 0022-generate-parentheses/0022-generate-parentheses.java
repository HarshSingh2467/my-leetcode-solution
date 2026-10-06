import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<String> generateParenthesis(int n) {
        List<List<String>> dp = new ArrayList<>();
        // Base case: 0 pairs of parentheses
        dp.add(new ArrayList<>(List.of("")));

        for (int i = 1; i <= n; i++) {
            List<String> currentList = new ArrayList<>();
            for (int c = 0; c < i; c++) {
                List<String> lefts = dp.get(c);
                List<String> rights = dp.get(i - 1 - c);
                
                for (String left : lefts) {
                    for (String right : rights) {
                        currentList.add("(" + left + ")" + right);
                    }
                }
            }
            dp.add(currentList);
        }

        return dp.get(n);
    }
}
