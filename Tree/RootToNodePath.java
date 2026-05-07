package Tree;

import java.util.ArrayList;
import java.util.List;

// Basic Binary Tree Node
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

public class RootToNodePath {

    // -------------------------------------------------
    // Helper function: fills `path` with nodes from root
    // to target, if such a path exists.
    //
    // Returns true if target is found in this subtree,
    // otherwise false.
    // -------------------------------------------------
    private static boolean getPath(TreeNode root, int target, List<Integer> path) {
        if (root == null) {
            return false;
        }

        // Add current node to path
        path.add(root.val);

        // If current node is the target, we are done
        if (root.val == target) {
            return true;
        }

        // Recur on left or right subtree
        if (getPath(root.left, target, path) || getPath(root.right, target, path)) {
            return true;
        }

        // If not found in either subtree, backtrack:
        // remove current node from path and return false
        path.remove(path.size() - 1);
        return false;
    }

    // -------------------------------------------------
    // Returns the path from root to target as a list.
    // If target is not found, returns an empty list.
    // -------------------------------------------------
    public static List<Integer> rootToNodePath(TreeNode root, int target) {
        List<Integer> path = new ArrayList<>();

        if (getPath(root, target, path)) {
            return path;
        }
        // if target not found, return empty list
        return new ArrayList<>();
    }

    // -------------------------------------------------
    // DEMO main()
    // -------------------------------------------------
    public static void main(String[] args) {

        /*
                 1
               /   \
              2     3
             / \
            4   5

           Example path (root to node 5): [1, 2, 5]
        */

        TreeNode root = new TreeNode(1);
        root.left  = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left  = new TreeNode(4);
        root.left.right = new TreeNode(5);

        int target = 5;

        List<Integer> path = rootToNodePath(root, target);

        if (path.isEmpty()) {
            System.out.println("Target " + target + " not found in tree.");
        } else {
            System.out.print("Path from root to node " + target + ": ");
            for (int val : path) {
                System.out.print(val + " ");
            }
            System.out.println();
        }
    }
}

/*
-----------------------------------------------------------
EXPLANATION (Root to Target Path via Recursion + Backtracking)

We want the path from root to a given target node value:

1. Start from the root and add current node to `path`.
2. If current node is the target → return true (path is ready).
3. Otherwise, recursively search in left subtree and then right subtree.
4. If either recursive call returns true → propagate true upwards.
5. If both left and right subtrees return false:
   - This means target is NOT in this branch.
   - So we remove the current node from `path` (backtrack) and return false.

Backtracking is important to ensure the `path` list only contains
the nodes from root to the target, not extra nodes where search failed.

-----------------------------------------------------------
TIME COMPLEXITY:
- O(N) in worst case, where N is number of nodes.
- We may visit every node once.

SPACE COMPLEXITY:
- O(H) for recursion stack, where H is height of tree.
- O(H) also for `path` list (at most one node per level).
- Worst case (skewed tree): O(N)

-----------------------------------------------------------
*/
