package Tree;

import java.util.*;

// Node Structure
class TreeNode {
    int val;
    TreeNode left, right;
    TreeNode(int v) { val = v; }
}

public class MaxWidthBinaryTree {

    // -------------------------------------------------
    // 1️⃣ BFS (Level Order Traversal with Indexing)
    // -------------------------------------------------
    public static int widthOfBinaryTree(TreeNode root) {
        if (root == null) return 0;

        // Queue of (node, index)
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(root, 0));

        int maxWidth = 0;

        while (!q.isEmpty()) {
            int size = q.size();

            // Record the index of the first node at this level
            long minIndex = q.peek().index;
            long firstIndex = 0, lastIndex = 0;

            for (int i = 0; i < size; i++) {
                Pair p = q.poll();
                TreeNode node = p.node;
                long currIndex = p.index - minIndex; 
                // Normalize indices to prevent overflow

                if (i == 0) firstIndex = currIndex;
                if (i == size - 1) lastIndex = currIndex;

                // Push children with "virtual indices"
                if (node.left != null)
                    q.add(new Pair(node.left, currIndex * 2 + 1));
                if (node.right != null)
                    q.add(new Pair(node.right, currIndex * 2 + 2));
            }

            maxWidth = Math.max(maxWidth, (int)(lastIndex - firstIndex + 1));
        }

        return maxWidth;
    }

    // Helper Pair class
    static class Pair {
        TreeNode node;
        long index;
        Pair(TreeNode n, long i) {
            node = n;
            index = i;
        }
    }

    // -------------------------------------------------
    // 🏁 MAIN
    // -------------------------------------------------
    public static void main(String[] args) {

        /*
                 1
               /   \
              3     2
             / \     \
            5   3     9
         Width at last level = positions 5 _ _ 9 → width = 4
        */

        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(3);
        root.right = new TreeNode(2);
        root.left.left = new TreeNode(5);
        root.left.right = new TreeNode(3);
        root.right.right = new TreeNode(9);

        System.out.println("Maximum Width of Binary Tree = " + widthOfBinaryTree(root));
    }
}
