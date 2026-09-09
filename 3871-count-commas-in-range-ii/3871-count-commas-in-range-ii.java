class Solution {
    public long countCommas(long n) {
        long totalCommas = 0;
        
        // Loop through thresholds starting at 1,000, multiplying by 1,000 each step
        for (long threshold = 1000; threshold <= n; threshold *= 1000) {
            // Number of elements from 'threshold' to 'n' inclusive
            totalCommas += (n - threshold + 1);
        }
        
        return totalCommas;
    }
}
