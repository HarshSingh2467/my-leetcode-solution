import java.util.Arrays;

class Solution {
    public long findKthSmallest(int[] coins, int k) {
        Arrays.sort(coins);
        
        long low = 1;
        long high = (long) coins[0] * k;
        long ans = high;
        
        while (low <= high) {
            long mid = low + (high - low) / 2;
            
            if (count(mid, coins) >= k) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        
        return ans;
    }
    
    private long count(long mid, int[] coins) {
        long total = 0;
        int n = coins.length;
        int numSubsets = 1 << n;
        
        for (int i = 1; i < numSubsets; i++) {
            long currentLcm = 1;
            int setBits = 0;
            boolean skip = false;
            
            for (int j = 0; j < n; j++) {
                if (((i >> j) & 1) == 1) {
                    setBits++;
                    currentLcm = lcm(currentLcm, coins[j]);
                    
                    if (currentLcm > mid) {
                        skip = true;
                        break;
                    }
                }
            }
            
            if (!skip) {
                if (setBits % 2 == 1) {
                    total += mid / currentLcm;
                } else {
                    total -= mid / currentLcm;
                }
            }
        }
        
        return total;
    }
    
    private long gcd(long a, long b) {
        while (b != 0) {
            long temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
    
    private long lcm(long a, long b) {
        return (a / gcd(a, b)) * b;
    }
}
