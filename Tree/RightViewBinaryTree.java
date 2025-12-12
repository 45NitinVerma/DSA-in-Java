package Tree;

import java.util.*;

// TreeNode definition
class TreeNode {
    int val;
    TreeNode left, right;

    TreeNode(int val) {
        this.val = val;
    }
}

public class RightViewBinaryTree {

    // ---------------------------------------------------
    // 1️⃣ BFS APPROACH (Level Order)
    // ---------------------------------------------------
    // For every level, the LAST node is part of right view
    public static List<Integer> rightViewBFS(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;

        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);

        while (!q.isEmpty()) {
            int levelSize = q.size();

            for (int i = 0; i < levelSize; i++) {
                TreeNode node = q.poll();

                // LAST node of this level → right view element
                if (i == levelSize - 1)
                    result.add(node.val);

                if (node.left != null) q.add(node.left);
                if (node.right != null) q.add(node.right);
            }
        }

        return result;
    }


    // ---------------------------------------------------
    // 2️⃣ DFS APPROACH (Root → Right → Left)
    // ---------------------------------------------------
    // First node visited at each depth = rightmost node
    public static void dfs(TreeNode node, int level, List<Integer> result) {
        if (node == null) return;

        // first time reaching this level → take it
        if (level == result.size())
            result.add(node.val);

        // go right first (right view)
        dfs(node.right, level + 1, result);
        dfs(node.left, level + 1, result);
    }

    public static List<Integer> rightViewDFS(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        dfs(root, 0, result);
        return result;
    }


    // ---------------------------------------------------
    // MAIN EXAMPLE
    // ---------------------------------------------------
    public static void main(String[] args) {

        /*
                 1
                / \
               2   3
              / \   \
             4   5   6

          Right View = [1, 3, 6]
        */

        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.right = new TreeNode(6);

        System.out.println("Right View (BFS):  " + rightViewBFS(root));
        System.out.println("Right View (DFS):  " + rightViewDFS(root));
    }
}

/*
==================================================================
🧠 EXPLANATION

RIGHT VIEW = Set of nodes visible when the tree is viewed from right.

---------------------------------------------------
1️⃣ BFS (Level Order)
---------------------------------------------------
- For each level, add ONLY the **last node**.
- Since BFS processes nodes level by level,
  the last node of every level is the rightmost node.

Time:  O(N)
Space: O(N)   (queue)

---------------------------------------------------
2️⃣ DFS (Root-Right-Left Preorder)
---------------------------------------------------
- Visit root → right → left
- The first time we reach a level → that node is the rightmost at that level.
- Store one node per depth level.

Time:  O(N)
Space: O(H) recursion (H = height)

---------------------------------------------------
*/
