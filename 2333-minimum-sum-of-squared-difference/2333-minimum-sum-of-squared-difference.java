import java.util.Arrays;

class Solution {
    public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
        int n = nums1.length;
        long[] diffs = new long[n];
        long totalDiffSum = 0;
        long maxDiff = 0;
        long k = (long) k1 + k2;

        for (int i = 0; i < n; i++) {
            diffs[i] = Math.abs(nums1[i] - nums2[i]);
            totalDiffSum += diffs[i];
            maxDiff = Math.max(maxDiff, diffs[i]);
        }

        // If the total operations allowed exceed the entire delta, we can make everything 0
        if (totalDiffSum <= k) {
            return 0;
        }

        // Binary search for the ceiling limit
        long low = 0, high = maxDiff;
        long optimalCeiling = maxDiff;

        while (low <= high) {
            long mid = low + (high - low) / 2;
            long requiredOps = 0;
            for (long d : diffs) {
                if (d > mid) {
                    requiredOps += (d - mid);
                }
            }

            if (requiredOps <= k) {
                optimalCeiling = mid;
                high = mid - 1; // Try to lower the ceiling further
            } else {
                low = mid + 1;
            }
        }

        // Apply operations to bring everything above optimalCeiling down to it
        for (int i = 0; i < n; i++) {
            if (diffs[i] > optimalCeiling) {
                k -= (diffs[i] - optimalCeiling);
                diffs[i] = optimalCeiling;
            }
        }

        // Distribute any leftover operations evenly among the elements currently at the ceiling
        for (int i = 0; i < n && k > 0; i++) {
            if (diffs[i] == optimalCeiling) {
                diffs[i]--;
                k--;
            }
        }

        // Compute final squared sum
        long minSquaredSum = 0;
        for (long d : diffs) {
            minSquaredSum += d * d;
        }

        return minSquaredSum;
    }
}
