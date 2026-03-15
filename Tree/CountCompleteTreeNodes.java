package Tree;

public class CountCompleteTreeNodes {

    // -------------------------------
    // Tree Node Definition
    // -------------------------------
    static class TreeNode {
        int val;
        TreeNode left, right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    // -------------------------------------------------
    // 1️⃣ BRUTE FORCE (DFS) — works for any binary tree
    // -------------------------------------------------
    public static int countNodesBrute(TreeNode root) {
        if (root == null) return 0;
        return 1 + countNodesBrute(root.left) + countNodesBrute(root.right);
    }

    // -------------------------------------------------
    // 2️⃣ OPTIMIZED FOR COMPLETE BINARY TREE
    // -------------------------------------------------
    public static int countNodesOptimized(TreeNode root) {
        if (root == null) return 0;

        int leftHeight = getLeftHeight(root);
        int rightHeight = getRightHeight(root);

        // If left and right heights are same,
        // tree is a PERFECT binary tree
        if (leftHeight == rightHeight) {
            // Nodes = 2^height - 1
            return (1 << leftHeight) - 1;
        }

        // Otherwise, recurse on left and right subtree
        return 1 + countNodesOptimized(root.left)
                 + countNodesOptimized(root.right);
    }

    // Height by moving only left
    private static int getLeftHeight(TreeNode node) {
        int height = 0;
        while (node != null) {
            height++;
            node = node.left;
        }
        return height;
    }

    // Height by moving only right
    private static int getRightHeight(TreeNode node) {
        int height = 0;
        while (node != null) {
            height++;
            node = node.right;
        }
        return height;
    }

    // -------------------------------------------------
    // MAIN
    // -------------------------------------------------
    public static void main(String[] args) {

        /*
            Complete Binary Tree:

                    1
                   / \
                  2   3
                 / \  /
                4  5 6
        */

        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);

        System.out.println("Count (Brute Force): " + countNodesBrute(root));
        System.out.println("Count (Optimized):   " + countNodesOptimized(root));
    }
}

/*
-------------------------------------------------------
🧠 EXPLANATION

We use a special property of COMPLETE binary trees.

If:
- Height of leftmost path == height of rightmost path
→ Tree is a PERFECT binary tree

Perfect Binary Tree:
Nodes = 2^height - 1

Otherwise:
- Count recursively in left and right subtree

-------------------------------------------------------
1️⃣ BRUTE FORCE (DFS)
- Traverse all nodes
- Count 1 + left + right

Time:  O(N)
Space: O(H) (recursion stack)

-------------------------------------------------------
2️⃣ OPTIMIZED APPROACH
- Compute leftmost height and rightmost height
- If equal → directly compute nodes
- Else → recurse

Time:  O((log N)²)
Space: O(log N)

Why (log N)²?
- Height computation → O(log N)
- Recursion depth → O(log N)

-------------------------------------------------------
*/
