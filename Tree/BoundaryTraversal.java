package Tree;

import java.util.*;

class TreeNode {
    int val;
    TreeNode left, right;
    TreeNode(int val) { this.val = val; }
}

public class BoundaryTraversal {

    // -------------------------------------------------------------
    // Helper: Check if a node is a leaf
    // -------------------------------------------------------------
    private static boolean isLeaf(TreeNode node) {
        return node.left == null && node.right == null;
    }

    // -------------------------------------------------------------
    // Step 2: Add left boundary (excluding leaves)
    // -------------------------------------------------------------
    private static void addLeftBoundary(TreeNode root, List<Integer> res) {
        TreeNode curr = root.left;
        while (curr != null) {
            if (!isLeaf(curr)) res.add(curr.val);
            if (curr.left != null) curr = curr.left;
            else curr = curr.right;
        }
    }

    // -------------------------------------------------------------
    // Step 3: Add all leaf nodes (left → right)
    // -------------------------------------------------------------
    private static void addLeaves(TreeNode root, List<Integer> res) {
        if (root == null) return;

        if (isLeaf(root)) {
            res.add(root.val);
            return;
        }
        addLeaves(root.left, res);
        addLeaves(root.right, res);
    }

    // -------------------------------------------------------------
    // Step 4: Add right boundary (excluding leaves, bottom-up)
    // -------------------------------------------------------------
    private static void addRightBoundary(TreeNode root, List<Integer> res) {
        TreeNode curr = root.right;
        Stack<Integer> stack = new Stack<>();  // we want reverse order

        while (curr != null) {
            if (!isLeaf(curr)) stack.push(curr.val);

            if (curr.right != null) curr = curr.right;
            else curr = curr.left;
        }

        // Add nodes in reverse (bottom → top)
        while (!stack.isEmpty()) res.add(stack.pop());
    }

    // -------------------------------------------------------------
    // MAIN FUNCTION: Boundary Traversal (Anti-clockwise)
    // -------------------------------------------------------------
    public static List<Integer> boundaryTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();

        if (root == null) return res;

        // Step 1: Root (only if not a leaf)
        if (!isLeaf(root)) res.add(root.val);

        addLeftBoundary(root, res);
        addLeaves(root, res);
        addRightBoundary(root, res);

        return res;
    }

    // -------------------------------------------------------------
    // DEMO
    // -------------------------------------------------------------
    public static void main(String[] args) {
        /*
                  20
                /    \
              8       22
            /   \       \
           4    12       25
               /  \
              10  14
        */

        TreeNode root = new TreeNode(20);
        root.left = new TreeNode(8);
        root.right = new TreeNode(22);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(12);
        root.left.right.left = new TreeNode(10);
        root.left.right.right = new TreeNode(14);
        root.right.right = new TreeNode(25);

        List<Integer> boundary = boundaryTraversal(root);
        System.out.println("Boundary Traversal: " + boundary);
    }
}

/*
==================================================================
🧠 BOUNDARY TRAVERSAL LOGIC (ANTI-CLOCKWISE):

1️⃣ Add the root (only if not a leaf)

2️⃣ Add LEFT BOUNDARY (top → down, excluding leaves)
   Example: 20 → 8

3️⃣ Add ALL LEAVES (left → right)
   Example: 4, 10, 14, 25

4️⃣ Add RIGHT BOUNDARY (bottom → top, excluding leaves)
   Example: 22

Final Output Example:
[20, 8, 4, 10, 14, 25, 22]

==================================================================
⏱ TIME COMPLEXITY:
O(N) — each node is visited once

💾 SPACE COMPLEXITY:
O(H) — recursion + stack, where H = height of tree
==================================================================
*/
