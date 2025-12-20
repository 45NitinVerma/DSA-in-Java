package Tree;

import java.util.*;

// Basic TreeNode definition
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

public class SymmetricTree {

    // -------------------------------------------------
    // 1️⃣ RECURSIVE APPROACH (DFS)
    // -------------------------------------------------
    // Check if two subtrees are mirror of each other
    private boolean isMirror(TreeNode left, TreeNode right) {
        // If both null → symmetric
        if (left == null && right == null) return true;

        // If one null and other not → not symmetric
        if (left == null || right == null) return false;

        // Values must match AND:
        // left.left  ↔ right.right
        // left.right ↔ right.left
        return (left.val == right.val)
                && isMirror(left.left, right.right)
                && isMirror(left.right, right.left);
    }

    // Public method - recursive version
    public boolean isSymmetricRecursive(TreeNode root) {
        if (root == null) return true;
        return isMirror(root.left, root.right);
    }

    // -------------------------------------------------
    // 2️⃣ ITERATIVE APPROACH (BFS with Queue)
    // -------------------------------------------------
    public boolean isSymmetricIterative(TreeNode root) {
        if (root == null) return true;

        Queue<TreeNode> q = new LinkedList<>();
        // Push left and right child as a pair
        q.add(root.left);
        q.add(root.right);

        while (!q.isEmpty()) {
            // Take two nodes at a time (they should be mirror)
            TreeNode left = q.poll();
            TreeNode right = q.poll();

            // Both null → continue
            if (left == null && right == null) continue;

            // One null or values differ → not symmetric
            if (left == null || right == null || left.val != right.val)
                return false;

            // Push children in mirror order
            q.add(left.left);
            q.add(right.right);

            q.add(left.right);
            q.add(right.left);
        }

        return true;
    }

    // -------------------------------------------------
    // DEMO MAIN
    // -------------------------------------------------
    public static void main(String[] args) {
        /*
                1
               / \
              2   2
             / \ / \
            3  4 4  3

            This tree IS symmetric.
        */

        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2, new TreeNode(3), new TreeNode(4));
        root.right = new TreeNode(2, new TreeNode(4), new TreeNode(3));

        SymmetricTree sol = new SymmetricTree();

        System.out.println("Recursive check: " + sol.isSymmetricRecursive(root));
        System.out.println("Iterative check: " + sol.isSymmetricIterative(root));
    }
}

/*
-------------------------------------------------------
🧠 INTUITION (Hinglish):

Symmetric tree ka matlab:
Left subtree aur right subtree **mirror image** hone chahiye.

Recursive idea:
- Do nodes compare karo: left & right
- Conditions:
  1. Dono null → symmetric at this point
  2. Ek null, ek non-null → not symmetric
  3. Values same hone chahiye
  4. left.left  ↔ right.right
     left.right ↔ right.left
  Aise recursively sab check karte hain.

Iterative idea (Queue se BFS):
- Queue me pair-pair nodes daalte hain:
  - Pehle root.left, root.right
- Har step pe do nodes nikalte hain:
  - Agar dono null → continue
  - Agar ek null ya value alag → not symmetric
  - Phir unke children mirror order me queue me daalo:
        left.left  & right.right
        left.right & right.left

-------------------------------------------------------
⏱ TIME COMPLEXITY:
- Recursive: O(N)
- Iterative: O(N)
  (Har node max ek baar process hota hai)

💾 SPACE COMPLEXITY:
- Recursive: O(H) stack space, worst O(N) (skewed tree)
- Iterative: O(N) in worst case (queue size for wide levels)

N = number of nodes, H = height of tree
-------------------------------------------------------
*/
