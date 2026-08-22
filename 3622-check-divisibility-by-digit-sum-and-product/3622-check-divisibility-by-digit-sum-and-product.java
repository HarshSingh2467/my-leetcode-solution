class Solution {
    public boolean checkDivisibility(int n) {
        int s = 0;
        int p = 1;
        int x = n;
        
        while (x > 0) {
            int digit = x % 10;
            s += digit;
            p *= digit;
            x /= 10;
        }
        
        return n % (s + p) == 0;
    }
}
