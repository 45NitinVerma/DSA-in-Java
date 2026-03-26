package Tree;

class TreeNode {
    int val;
    TreeNode left, right;

    TreeNode(int val) {
        this.val = val;
        left = right = null;
    }
}

public class MaxPathSumBinaryTree {

    // Global variable to store overall max path sum
    static int maxSum;

    // -----------------------------------------------------------
    // DFS FUNCTION
    // Returns:
    //   maximum path sum *starting from this node going downward*
    //
    // Updates:
    //   maxSum = maximum path sum anywhere in the tree
    // -----------------------------------------------------------
    public static int dfs(TreeNode root) {
        if (root == null)
            return 0;

        // Calculate max path sum from left & right children
        int leftGain = Math.max(0, dfs(root.left));   // negative paths are ignored
        int rightGain = Math.max(0, dfs(root.right)); // negative paths are ignored

        // Best path including current node as highest point (turning point)
        int currentPathSum = root.val + leftGain + rightGain;

        // Update global max
        maxSum = Math.max(maxSum, currentPathSum);

        // Return max downward path to parent
        return root.val + Math.max(leftGain, rightGain);
    }

    // Wrapper function
    public static int maxPathSum(TreeNode root) {
        maxSum = Integer.MIN_VALUE; 
        dfs(root);
        return maxSum;
    }

    // -----------------------------------------------------------
    // MAIN (Demo)
    // -----------------------------------------------------------
    public static void main(String[] args) {

        /*
                -10
                 / \
                9   20
                   /  \
                  15   7

            Maximum Path = 15 + 20 + 7 = 42
        */

        TreeNode root = new TreeNode(-10);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);

        System.out.println("Maximum Path Sum: " + maxPathSum(root));
    }
}

/*
======================================================================
🧠 EXPLANATION (Hinglish + Clarity)

This problem asks for the path (anywhere in the tree) that gives
**maximum possible sum**, where the path:
  ✔ can start anywhere  
  ✔ can end anywhere  
  ✔ must go *downward* but may “turn” at one node  

Same DFS logic as maximum depth, but instead of height we compute:
    best downward path from a node = node.val + max(left, right)
(INCL: ignoring negative values)

But the path that creates the answer is:
    leftGain + root.val + rightGain
(where leftGain and rightGain are positive contributions)

We track this globally using maxSum.

----------------------------------------------------------------------
🌳 For each node:
1. Get leftGain and rightGain  
   → only keep positive contributions  
2. Compute turning-path sum = root.val + leftGain + rightGain  
   → update global max  
3. Return downward path to parent  
   → root.val + max(leftGain, rightGain)

----------------------------------------------------------------------
⏱ TIME COMPLEXITY:   O(N)  
💾 SPACE COMPLEXITY:  O(H) recursion stack (H = height of tree)
======================================================================
*/
