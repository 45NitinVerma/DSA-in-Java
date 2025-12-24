package Tree;

/*
 Check if a binary tree is height-balanced.
 Uses a helper that returns:
   - height of subtree (>= 0) when balanced
   - -1 when subtree is unbalanced (early exit propagation)
*/

class TreeNode {
    int val;
    TreeNode left, right;
    TreeNode(int x) { val = x; }
}

public class CheckBalancedBinaryTree {

    // Helper returns height if balanced, otherwise -1
    private static int heightIfBalanced(TreeNode node) {
        if (node == null) return 0;

        int leftH = heightIfBalanced(node.left);
        if (leftH == -1) return -1; // left subtree unbalanced => propagate

        int rightH = heightIfBalanced(node.right);
        if (rightH == -1) return -1; // right subtree unbalanced => propagate

        if (Math.abs(leftH - rightH) > 1) return -1; // current node unbalanced

        return 1 + Math.max(leftH, rightH); // return height
    }

    // Public API
    public static boolean isBalanced(TreeNode root) {
        return heightIfBalanced(root) != -1;
    }

    // Demo
    public static void main(String[] args) {
        /*
               1
              / \
             2   3
            /
           4
         Balanced? yes (heights differ at most 1)
        */
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);

        System.out.println("Is Balanced? " + (isBalanced(root) ? "Yes" : "No"));

        // Make it unbalanced by adding more depth on left
        root.left.left.left = new TreeNode(5);
        System.out.println("Is Balanced now? " + (isBalanced(root) ? "Yes" : "No"));
    }
}

/*
Complexity:
- Time:  O(N)   (each node visited once)
- Space: O(H)   (recursion stack, H = tree height)
*/
