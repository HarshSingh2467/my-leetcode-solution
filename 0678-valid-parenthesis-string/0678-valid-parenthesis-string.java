class Solution {
    public boolean checkValidString(String s) {
        int low = 0;
        int high = 0;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            
            if (c == '(') {
                low++;
                high++;
            } else if (c == ')') {
                low = Math.max(0, low - 1);
                high--;
            } else if (c == '*') {
                low = Math.max(0, low - 1); // Treat '*' as ')' or empty
                high++;                     // Treat '*' as '('
            }

            // If max potential open brackets falls below 0, there are too many ')'
            if (high < 0) {
                return false;
            }
        }

        // Valid if all mandatory open brackets are matched
        return low == 0;
    }
}
