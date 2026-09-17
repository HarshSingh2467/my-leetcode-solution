import java.util.Arrays;

class Solution {
    public int minSumOfLengths(int[] arr, int target) {
        int n = arr.length;
        
        // minLens[i] stores the minimum length of a valid sub-array in arr[0...i]
        int[] minLens = new int[n];
        Arrays.fill(minLens, Integer.MAX_VALUE);
        
        int left = 0;
        int currentSum = 0;
        int minTotalLen = Integer.MAX_VALUE;
        int bestLenSoFar = Integer.MAX_VALUE;
        
        for (int right = 0; right < n; right++) {
            currentSum += arr[right];
            
            // Shrink window if the sum exceeds target
            while (currentSum > target) {
                currentSum -= arr[left];
                left++;
            }
            
            // Found a valid sub-array matching target
            if (currentSum == target) {
                int currentLen = right - left + 1;
                
                // If a valid sub-array exists strictly to the left of the current 'left' pointer
                if (left > 0 && minLens[left - 1] != Integer.MAX_VALUE) {
                    minTotalLen = Math.min(minTotalLen, currentLen + minLens[left - 1]);
                }
                
                // Update the best single sub-array length found up to the current index
                bestLenSoFar = Math.min(bestLenSoFar, currentLen);
            }
            
            // Record the best length found up to index 'right'
            minLens[right] = bestLenSoFar;
        }
        
        return minTotalLen == Integer.MAX_VALUE ? -1 : minTotalLen;
    }
}
