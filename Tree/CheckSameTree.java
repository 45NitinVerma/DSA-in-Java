package Tree;

import java.util.*;

// Basic TreeNode definition (same as your other files)
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {}
    TreeNode(int x) { this.val = x; }
    TreeNode(int x, TreeNode left, TreeNode right) {
        this.val = x;
        this.left = left;
        this.right = right;
    }
}

public class CheckSameTree {

    // -------------------------------------------------
    // 1) RECURSIVE APPROACH (straightforward)
    // -------------------------------------------------
    // Idea:
    // Two trees are same iff:
    //  - both nodes are null -> true
    //  - exactly one null     -> false
    //  - both non-null: values equal AND left-subtrees same AND right-subtrees same
    public static boolean isSameTreeRecursive(TreeNode p, TreeNode q) {
        // both null -> identical
        if (p == null && q == null) return true;

        // only one null -> not identical
        if (p == null || q == null) return false;

        // both non-null -> check value and recurse on children
        if (p.val != q.val) return false;

        return isSameTreeRecursive(p.left, q.left) && isSameTreeRecursive(p.right, q.right);
    }

    // -------------------------------------------------
    // 2) ITERATIVE APPROACH (BFS using a queue)
    // -------------------------------------------------
    // Idea:
    // Use a queue of pairs. For each pair (node1, node2):
    //  - if both null -> continue
    //  - if only one null or values differ -> return false
    //  - else push their children pairs (left-left, right-right)
    public static boolean isSameTreeIterative(TreeNode p, TreeNode q) {
        Queue<TreeNode[]> queue = new LinkedList<>();
        queue.add(new TreeNode[] { p, q });

        while (!queue.isEmpty()) {
            TreeNode[] pair = queue.poll();
            TreeNode n1 = pair[0];
            TreeNode n2 = pair[1];

            // both null -> this pair is fine; continue
            if (n1 == null && n2 == null) continue;

            // one null or value mismatch -> trees differ
            if (n1 == null || n2 == null) return false;
            if (n1.val != n2.val) return false;

            // push children pairs in the same relative order
            queue.add(new TreeNode[] { n1.left, n2.left });
            queue.add(new TreeNode[] { n1.right, n2.right });
        }

        // all pairs matched
        return true;
    }

    // -------------------------------------------------
    // Helper: build a sample tree (for demo)
    // -------------------------------------------------
    // Builds:
    //       1
    //      / \
    //     2   3
    static TreeNode sampleTree1() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        return root;
    }

    // Builds:
    //       1
    //      / \
    //     2   3
    static TreeNode sampleTree2() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        return root;
    }

    // Builds:
    //       1
    //      / \
    //     3   2   (different structure/values)
    static TreeNode sampleTreeDifferent() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(3);
        root.right = new TreeNode(2);
        return root;
    }

    // -------------------------------------------------
    // MAIN (demo)
    // -------------------------------------------------
    public static void main(String[] args) {
        TreeNode a1 = sampleTree1();
        TreeNode a2 = sampleTree2();
        TreeNode b  = sampleTreeDifferent();

        System.out.println("Recursive: a1 vs a2 -> " + (isSameTreeRecursive(a1, a2) ? "Same" : "Different"));
        System.out.println("Recursive: a1 vs b  -> " + (isSameTreeRecursive(a1, b) ? "Same" : "Different"));

        System.out.println("Iterative: a1 vs a2 -> " + (isSameTreeIterative(a1, a2) ? "Same" : "Different"));
        System.out.println("Iterative: a1 vs b  -> " + (isSameTreeIterative(a1, b) ? "Same" : "Different"));
    }
}

/*
--------------------------------------------
EXPLANATION (short):

RECURSIVE:
- Very simple and natural for trees.
- For each corresponding node pair, check:
  1) both null -> true
  2) one null  -> false
  3) values equal -> recurse on left & right

ITERATIVE:
- Use a queue of node-pairs (pNode, qNode).
- Handle same checks per pair, push children pairs in same order.

--------------------------------------------
COMPLEXITY:

Let N = number of nodes in the smaller/larger of the two trees 
(in worst case we visit all nodes of both trees, but complexity expressed in terms of nodes visited).

Time Complexity: O(min(N1, N2)) ~ O(N) in practice (we stop early if mismatch).
Space Complexity:
- Recursive: O(H) recursion stack, where H = tree height (O(N) worst-case skewed, O(log N) balanced).
- Iterative: O(W) queue width (worst-case O(N), average depends on tree shape).

Both methods are correct; choose recursive for simplicity, iterative to avoid recursion-depth issues.
--------------------------------------------
*/
