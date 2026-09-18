import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public List<String> maxNumOfSubstrings(String s) {
        int n = s.length();
        int[] leftmost = new int[26];
        int[] rightmost = new int[26];
        Arrays.fill(leftmost, n);
        Arrays.fill(rightmost, -1);
        
        // 1. Record the leftmost and rightmost index for each character
        for (int i = 0; i < n; i++) {
            int c = s.charAt(i) - 'a';
            leftmost[c] = Math.min(leftmost[c], i);
            rightmost[c] = i;
        }
        
        List<String> ans = new ArrayList<>();
        int rightBound = -1; // Tracks the end of the last added valid substring
        
        // 2. Iterate backwards from the end of the string to find valid intervals greedily
        for (int i = 0; i < n; i++) {
            // Check only the starting positions of valid characters
            if (i == leftmost[s.charAt(i) - 'a']) {
                int newRight = checkSubstring(s, i, leftmost, rightmost);
                
                if (newRight != -1) {
                    // If the new substring is completely inside the last added one, replace it
                    if (i <= rightBound) {
                        ans.set(ans.size() - 1, s.substring(i, newRight + 1));
                    } else {
                        ans.add(s.substring(i, newRight + 1));
                    }
                    rightBound = newRight;
                }
            }
        }
        return ans;
    }
    
    // Helper method to expand bounds and check if a valid substring can be formed starting at 'left'
    private int checkSubstring(String s, int left, int[] leftmost, int[] rightmost) {
        int right = rightmost[s.charAt(left) - 'a'];
        for (int i = left; i <= right; i++) {
            int c = s.charAt(i) - 'a';
            // If this character appears before our starting index, the interval is invalid
            if (leftmost[c] < left) {
                return -1;
            }
            // Expand the right boundary if this character extends further out
            right = Math.max(right, rightmost[c]);
        }
        return right;
    }
}
