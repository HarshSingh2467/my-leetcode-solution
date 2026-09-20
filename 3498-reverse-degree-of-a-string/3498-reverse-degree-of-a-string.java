import java.util.stream.IntStream;

class Solution {
    public int reverseDegree(String s) {
        return IntStream.range(0, s.length())
            .map(i -> (26 - (s.charAt(i) - 'a')) * (i + 1))
            .sum();
    }
}
