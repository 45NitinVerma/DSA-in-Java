package Tree;

import java.util.*;

// Node structure
class TreeNode {
    int val;
    TreeNode left, right;

    TreeNode(int val) {
        this.val = val;
        this.left = null;
        this.right = null;
    }
}

public class LeftViewBinaryTree {

    // ----------------------------------------------------------
    // 1️⃣ LEFT VIEW USING BFS (Level Order Traversal)
    // ----------------------------------------------------------
    public static List<Integer> leftViewBFS(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        if (root == null) return ans;

        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);

        while (!q.isEmpty()) {
            int size = q.size();

            // First node of each level is part of left view
            for (int i = 0; i < size; i++) {
                TreeNode node = q.poll();

                if (i == 0) ans.add(node.val); // left-most element of level

                if (node.left != null) q.add(node.left);
                if (node.right != null) q.add(node.right);
            }
        }

        return ans;
    }

    // ----------------------------------------------------------
    // 2️⃣ LEFT VIEW USING DFS (Root → Left → Right)
    // ----------------------------------------------------------
    public static void leftViewDFSUtil(TreeNode node, int level, List<Integer> ans) {
        if (node == null) return;

        // First time visiting this level → this is the left view node
        if (level == ans.size()) ans.add(node.val);

        // Go left first to ensure leftmost nodes are captured
        leftViewDFSUtil(node.left, level + 1, ans);
        leftViewDFSUtil(node.right, level + 1, ans);
    }

    public static List<Integer> leftViewDFS(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        leftViewDFSUtil(root, 0, ans);
        return ans;
    }

    // ----------------------------------------------------------
    // MAIN
    // ----------------------------------------------------------
    public static void main(String[] args) {

        /*
                 1
                / \
               2   3
              / \   \
             4   5   6

            Left View = [1, 2, 4]
        */

        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.right = new TreeNode(6);

        System.out.println("Left View (BFS): " + leftViewBFS(root));
        System.out.println("Left View (DFS): " + leftViewDFS(root));
    }
}


/*
====================================================================
🧠 LEFT VIEW OF BINARY TREE — EXPLANATION

Left View = “first node seen from left side”
→ i.e. for each level, take the **leftmost** node.

====================================================================
1️⃣ BFS (Level Order)
-----------------------------------------
At each level:
   - Process all nodes of that level
   - The node processed at i=0 (first index) is leftmost
   - Add it to answer

Example:
Level 0: [1] → take 1  
Level 1: [2, 3] → take 2  
Level 2: [4, 5, 6] → take 4  

====================================================================
2️⃣ DFS (Root → Left → Right)
-----------------------------------------
Visit nodes in this order:
   - Preorder: (root, left, right)

Maintain:
   level = depth of node  
   ans.size() = number of levels processed so far

Rule:
   If level == ans.size()  
       → This is the first node of that level  
       → Add to left view

This guarantees leftmost node appears first.

====================================================================
⏱ TIME COMPLEXITY:
   BFS  → O(N)  
   DFS  → O(N)

💾 SPACE COMPLEXITY:
   BFS  → O(width of tree)  
   DFS  → O(height), worst-case O(N) for skewed tree

====================================================================
*/
