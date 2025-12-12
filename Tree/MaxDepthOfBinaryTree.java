package Tree;

import java.util.*;

// Definition for a binary tree node
class TreeNode {
    int val;
    TreeNode left, right;

    TreeNode(int val) {
        this.val = val;
        left = right = null;
    }
}

public class MaxDepthOfBinaryTree {

    // -------------------------------------------------------
    // 1️⃣ RECURSION (DFS) – Top-Down approach
    // -------------------------------------------------------
    // Depth = 1 + max(depth of left subtree, depth of right subtree)
    public static int maxDepthDFS(TreeNode root) {
        if (root == null)
            return 0;

        int left = maxDepthDFS(root.left);
        int right = maxDepthDFS(root.right);

        return 1 + Math.max(left, right);
    }


    // -------------------------------------------------------
    // 2️⃣ BFS (Level Order Traversal)
    // -------------------------------------------------------
    // Count number of levels in BFS → that's the depth
    public static int maxDepthBFS(TreeNode root) {
        if (root == null)
            return 0;

        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);

        int depth = 0;

        while (!q.isEmpty()) {
            int size = q.size(); // nodes in current level

            // Process entire level
            for (int i = 0; i < size; i++) {
                TreeNode node = q.poll();

                if (node.left != null) q.add(node.left);
                if (node.right != null) q.add(node.right);
            }

            depth++; // after finishing each level
        }

        return depth;
    }


    // -------------------------------------------------------
    // MAIN (Demo)
    // -------------------------------------------------------
    public static void main(String[] args) {

        /*
                1
               / \
              2   3
             / \
            4   5

           Expected depth = 3
        */

        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        System.out.println("Maximum Depth (DFS Recursion): " + maxDepthDFS(root));
        System.out.println("Maximum Depth (BFS): " + maxDepthBFS(root));
    }
}

/*
===========================================================
🧠 EXPLANATION

▶ DFS Approach (Recursion)
Depth of a tree:
    depth = 1 + max(left subtree depth, right subtree depth)

Base case → null node has depth 0.

This is a bottom-up depth calculation.

-----------------------------------------------------------

▶ BFS Approach (Level Order)
In BFS we visit the tree level by level.

Depth = number of levels.

Example:
Level 1 → 1
Level 2 → 2,3
Level 3 → 4,5

Total levels = 3 → depth = 3.

-----------------------------------------------------------

⏱️ TIME COMPLEXITY  
- DFS: O(N)  
- BFS: O(N)  
N = number of nodes

💾 SPACE COMPLEXITY  
- DFS: O(H) recursion stack  (H = height)  
- BFS: O(W) queue size      (W = max width)

===========================================================
*/

