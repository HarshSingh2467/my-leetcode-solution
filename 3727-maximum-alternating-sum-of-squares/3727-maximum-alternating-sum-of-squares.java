import java.util.Arrays;

public class Solution {
    public long maxAlternatingSum(int[] nums) {
        int n = nums.length;
        long[] squared = new long[n];
        
        // Step 1: Compute squares of all elements
        for (int i = 0; i < n; i++) {
            squared[i] = (long) nums[i] * nums[i];
        }
        
        // Step 2: Sort in ascending order
        Arrays.sort(squared);
        
        long totalSum = 0;
        int positiveCount = (n + 1) / 2; // Number of even indices (0, 2, 4...)
        
        // Step 3: Assign the largest elements to positive spots and smallest to negative spots
        for (int i = 0; i < n; i++) {
            if (i >= n - positiveCount) {
                // These are the largest elements
                totalSum += squared[i];
            } else {
                // These are the smallest elements
                totalSum -= squared[i];
            }
        }
        
        return totalSum;
    }
}
