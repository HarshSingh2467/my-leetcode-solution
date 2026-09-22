import java.util.Arrays;

class Solution {
    static class Node {
        int prod;
        int[] remain;

        Node(int k) {
            this.prod = 1;
            this.remain = new int[k];
        }
    }

    private int n;
    private int k;
    private Node[] tree;

    public int[] resultArray(int[] nums, int k, int[][] queries) {
        this.n = nums.length;
        this.k = k;
        this.tree = new Node[4 * n];
        
        // Initial build of the segment tree
        build(nums, 0, 0, n - 1);

        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int index = queries[i][0];
            int value = queries[i][1];
            int start = queries[i][2];
            int x = queries[i][3];

            // Point Update
            update(0, 0, n - 1, index, value);

            // Range Query from start to n - 1
            Node resultNode = query(0, 0, n - 1, start, n - 1);
            ans[i] = resultNode.remain[x];
        }

        return ans;
    }

    private Node merge(Node left, Node right) {
        Node res = new Node(k);
        res.prod = (int) ((long) left.prod * right.prod % k);
        
        // Count prefixes completely inside the left child
        for (int i = 0; i < k; i++) {
            res.remain[i] = left.remain[i];
        }
        
        // Count prefixes starting in left child and ending in right child
        for (int i = 0; i < k; i++) {
            if (right.remain[i] > 0) {
                int targetIdx = (int) ((long) i * left.prod % k);
                res.remain[targetIdx] += right.remain[i];
            }
        }
        
        return res;
    }

    private void build(int[] nums, int node, int start, int end) {
        if (start == end) {
            tree[node] = new Node(k);
            int valMod = nums[start] % k;
            tree[node].prod = valMod;
            tree[node].remain[valMod] = 1;
            return;
        }

        int mid = start + (end - start) / 2;
        build(nums, 2 * node + 1, start, mid);
        build(nums, 2 * node + 2, mid + 1, end);
        tree[node] = merge(tree[2 * node + 1], tree[2 * node + 2]);
    }

    private void update(int node, int start, int end, int idx, int val) {
        if (start == end) {
            Arrays.fill(tree[node].remain, 0);
            int valMod = val % k;
            tree[node].prod = valMod;
            tree[node].remain[valMod] = 1;
            return;
        }

        int mid = start + (end - start) / 2;
        if (idx <= mid) {
            update(2 * node + 1, start, mid, idx, val);
        } else {
            update(2 * node + 2, mid + 1, end, idx, val);
        }
        tree[node] = merge(tree[2 * node + 1], tree[2 * node + 2]);
    }

    private Node query(int node, int start, int end, int l, int r) {
        if (l <= start && end <= r) {
            return tree[node];
        }

        int mid = start + (end - start) / 2;
        if (r <= mid) {
            return query(2 * node + 1, start, mid, l, r);
        }
        if (l > mid) {
            return query(2 * node + 2, mid + 1, end, l, r);
        }

        Node leftNode = query(2 * node + 1, start, mid, l, r);
        Node rightNode = query(2 * node + 2, mid + 1, end, l, r);
        return merge(leftNode, rightNode);
    }
}
