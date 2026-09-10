class Solution {
    private int matchingNodesCount = 0;

    public int averageOfSubtree(TreeNode root) {
        calculateSumAndCount(root);
        return matchingNodesCount;
    }

    private int[] calculateSumAndCount(TreeNode node) {
        if (node == null) {
            return new int[]{0, 0}; 
        }

        int[] leftResult = calculateSumAndCount(node.left);
        int[] rightResult = calculateSumAndCount(node.right);

        int currentSum = leftResult[0] + rightResult[0] + node.val;
        int currentCount = leftResult[1] + rightResult[1] + 1;

        int average = currentSum / currentCount;

        if (node.val == average) {
            matchingNodesCount++;
        }

        return new int[]{currentSum, currentCount};
    }
}
