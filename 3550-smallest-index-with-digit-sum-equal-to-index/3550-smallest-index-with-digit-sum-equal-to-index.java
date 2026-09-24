class Solution {
    public int smallestIndex(int[] nums) {
        // Loop through the array from the smallest index to find the first match
        for (int i = 0; i < nums.length; ++i) {
            int digitSum = 0;
            int temp = nums[i];
            
            // Extract and sum each individual decimal digit
            while (temp > 0) {
                digitSum += temp % 10;
                temp /= 10;
            }
            
            // Check if the total digit sum matches the current position index
            if (digitSum == i) {
                return i;
            }
        }
        
        // Return -1 if no matching index is found in the entire array
        return -1;
    }
}
