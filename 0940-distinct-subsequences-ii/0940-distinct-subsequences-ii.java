import java.util.Arrays;

public class Solution {
    public int distinctSubseqII(String s) {
        int MOD = 1_000_000_007;
        long[] endsIn = new long[26];
        
        for (char c : s.toCharArray()) {
            long totalExisting = 0;
            for (long count : endsIn) {
                totalExisting = (totalExisting + count) % MOD;
            }
            
            endsIn[c - 'a'] = (totalExisting + 1) % MOD;
        }
        
        long result = 0;
        for (long count : endsIn) {
            result = (result + count) % MOD;
        }
        
        return (int) result;
    }
}
