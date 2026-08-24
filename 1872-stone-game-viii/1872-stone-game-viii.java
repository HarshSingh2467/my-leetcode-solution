class Solution {
    public int stoneGameVIII(int[] stones) {
        int n = stones.length;
        
        // Step 1: Compute prefix sums in-place to save memory
        for (int i = 1; i < n; i++) {
            stones[i] += stones[i - 1];
        }
        
        // Step 2: Base case - if a player takes all stones from index 0 to n-1
        int dp = stones[n - 1];
        
        // Step 3: Bottom-up DP transition
        for (int i = n - 2; i > 0; i--) {
            dp = Math.max(dp, stones[i] - dp);
        }
        
        return dp;
    }
}
