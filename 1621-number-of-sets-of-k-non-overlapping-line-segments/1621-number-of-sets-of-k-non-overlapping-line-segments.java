class Solution {
    public int numberOfSets(int n, int k) {
        int MOD = 1_000_000_007;
        
        // dp[i][j] stores the number of ways to form j segments using the first i points
        int[][] dp = new int[n][k + 1];
        
        // Base case: 0 segments can always be formed in 1 way (by choosing nothing)
        for (int i = 0; i < n; i++) {
            dp[i][0] = 1;
        }
        
        // Fill the DP table
        for (int j = 1; j <= k; j++) {
            int pointsSum = 0;
            for (int i = 1; i < n; i++) {
                // pointsSum accumulates the ways to start a new segment from previous points
                pointsSum = (pointsSum + dp[i - 1][j - 1]) % MOD;
                
                // dp[i][j] is the sum of:
                // 1. Not using point i to end any segment: dp[i - 1][j]
                // 2. Using point i to end a segment starting anywhere before: pointsSum
                dp[i][j] = (dp[i - 1][j] + pointsSum) % MOD;
            }
        }
        
        return dp[n - 1][k];
    }
}
