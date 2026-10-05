class Solution {
    public int scoreOfParentheses(String s) {
        int ans = 0;
        int layer = 0;
        
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                layer++;
            } else {
                layer--;
                // If the previous character was '(', we found an innermost "()" pair
                if (s.charAt(i - 1) == '(') {
                    ans += 1 << layer; // Equivalent to adding 2^layer
                }
            }
        }
        
        return ans;
    }
}
