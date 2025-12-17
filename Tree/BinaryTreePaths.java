package Tree;

import java.util.ArrayList;
import java.util.List;

// Basic Binary Tree Node
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {}

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

public class BinaryTreePaths {

    // -------------------------------------------------
    // Recursive helper: build all root-to-leaf paths
    // -------------------------------------------------
    private static void dfs(TreeNode node, String path, List<String> result) {
        if (node == null) return;

        // Add current node's value to the path
        if (path.isEmpty()) {
            path = Integer.toString(node.val);
        } else {
            path = path + "->" + node.val;
        }

        // If it's a leaf node, this path is complete
        if (node.left == null && node.right == null) {
            result.add(path);
            return;
        }

        // Recurse to left and right children
        dfs(node.left, path, result);
        dfs(node.right, path, result);
    }

    // -------------------------------------------------
    // Main function: returns all root-to-leaf paths
    // -------------------------------------------------
    public static List<String> binaryTreePaths(TreeNode root) {
        List<String> result = new ArrayList<>();
        if (root == null) return result;

        dfs(root, "", result);
        return result;
    }

    // -------------------------------------------------
    // DEMO main
    // -------------------------------------------------
    public static void main(String[] args) {
        /*
                1
               / \
              2   3
             /
            5

            Expected paths:
            "1->2->5"
            "1->3"
        */

        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2, new TreeNode(5), null);
        root.right = new TreeNode(3);

        List<String> paths = binaryTreePaths(root);

        System.out.println("Root to Leaf Paths:");
        for (String path : paths) {
            System.out.println(path);
        }
    }
}

/*
-------------------------------------------------------
EXPLANATION:

We need to print all paths from root to every leaf node.

Idea:
- Use DFS (recursion).
- Maintain a "path" string from root to current node.
- When we reach a leaf (left == null && right == null), 
  add the path to the result list.

At each recursive call:
- Append current node's value to the path.
- Recurse to left and right children.

-------------------------------------------------------
TIME COMPLEXITY:
- O(N * H)
  N = number of nodes
  H = height of tree (for building path strings)
  Roughly O(N * average path length)

SPACE COMPLEXITY:
- O(H) recursion stack
- O(N * H) for storing all path strings in list
-------------------------------------------------------
*/
