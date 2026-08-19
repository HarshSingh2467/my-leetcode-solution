import java.util.HashMap;
import java.util.Map;

public class Solution {
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {
        Map<Integer, Integer> rowToSeats = new HashMap<>();
        
        for (int[] seat : reservedSeats) {
            int row = seat[0];
            int seatNum = seat[1];
            if (seatNum >= 2 && seatNum <= 9) {
                rowToSeats.put(row, rowToSeats.getOrDefault(row, 0) | (1 << (seatNum - 2)));
            }
        }
        
        int maxGroups = (n - rowToSeats.size()) * 2;
        
        int leftMask = 15;   
        int middleMask = 60; 
        int rightMask = 240; 
        
        for (int reservedMask : rowToSeats.values()) {
            boolean leftFree = (reservedMask & leftMask) == 0;
            boolean rightFree = (reservedMask & rightMask) == 0;
            
            if (leftFree && rightFree) {
                maxGroups += 2;
            } else if (leftFree || rightFree || (reservedMask & middleMask) == 0) {
                maxGroups += 1;
            }
        }
        
        return maxGroups;
    }
}
