import java.util.Arrays;

class Solution {
    public int firstStableIndex(int[] nums, int k) {
        int n = nums.length;
        int[] rightMin = new int[n];
        
        // Precompute suffix minimums from right to left
        rightMin[n - 1] = nums[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            rightMin[i] = Math.min(rightMin[i + 1], nums[i]);
        }
        
        // Track prefix maximum from left to right
        int leftMax = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            leftMax = Math.max(leftMax, nums[i]);
            // Check stability condition
            if (leftMax - rightMin[i] <= k) {
                return i;
            }
        }
        
        return -1;
    }
}
