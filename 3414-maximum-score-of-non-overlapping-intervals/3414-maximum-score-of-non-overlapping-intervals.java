import java.util.*;

class Solution {
    private static class Interval {
        int start, end, weight, id;
        Interval(int start, int end, int weight, int id) {
            this.start = start;
            this.end = end;
            this.weight = weight;
            this.id = id;
        }
    }

    private static class State {
        long weight;
        List<Integer> ids;
        State(long weight, List<Integer> ids) {
            this.weight = weight;
            this.ids = ids;
        }
    }

    public int[] maximumWeight(List<List<Integer>> intervalsList) {
        int n = intervalsList.size();
        Interval[] intervals = new Interval[n];
        for (int i = 0; i < n; i++) {
            intervals[i] = new Interval(
                intervalsList.get(i).get(0),
                intervalsList.get(i).get(1),
                intervalsList.get(i).get(2),
                i
            );
        }

        Arrays.sort(intervals, (a, b) -> a.start != b.start ? Integer.compare(a.start, b.start) : Integer.compare(a.end, b.end));

        State[][] memo = new State[n][5];
        State result = dp(0, 4, intervals, memo);
        
        int[] ans = new int[result.ids.size()];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = result.ids.get(i);
        }
        return ans;
    }

    private State dp(int i, int count, Interval[] intervals, State[][] memo) {
        if (count == 0 || i == intervals.length) {
            return new State(0, new ArrayList<>());
        }
        if (memo[i][count] != null) {
            return memo[i][count];
        }

        State skip = dp(i + 1, count, intervals, memo);

        int nextIdx = findNext(i, intervals);
        State takeNext = dp(nextIdx, count - 1, intervals, memo);
        
        long takeWeight = intervals[i].weight + takeNext.weight;
        List<Integer> takeIds = new ArrayList<>(takeNext.ids);
        takeIds.add(intervals[i].id);
        Collections.sort(takeIds); 

        State take = new State(takeWeight, takeIds);

        State best;
        if (take.weight > skip.weight) {
            best = take;
        } else if (skip.weight > take.weight) {
            best = skip;
        } else {
            best = getLexicographicallySmaller(take, skip);
        }

        memo[i][count] = best;
        return best;
    }

    private int findNext(int currIdx, Interval[] intervals) {
        int low = currIdx + 1, high = intervals.length - 1;
        int ans = intervals.length;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (intervals[mid].start > intervals[currIdx].end) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return ans;
    }

    private State getLexicographicallySmaller(State a, State b) {
        int lenA = a.ids.size();
        int lenB = b.ids.size();
        int minLen = Math.min(lenA, lenB);

        for (int i = 0; i < minLen; i++) {
            if (!a.ids.get(i).equals(b.ids.get(i))) {
                return a.ids.get(i) < b.ids.get(i) ? a : b;
            }
        }
        return lenA <= lenB ? a : b;
    }
}
