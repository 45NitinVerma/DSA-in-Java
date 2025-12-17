package Tree;

import java.util.*;

// Node structure for Binary Tree
class TreeNode {
    int val;
    TreeNode left, right;

    TreeNode(int val) {
        this.val = val;
        left = right = null;
    }
}

public class TopViewBinaryTree {

    // Class to hold node + horizontal distance (HD)
    static class Pair {
        TreeNode node;
        int hd; // horizontal distance

        Pair(TreeNode n, int h) {
            node = n;
            hd = h;
        }
    }

    // -------------------------------------------------
    // 🔥 TOP VIEW using BFS (Level Order + HashMap)
    // -------------------------------------------------
    public static List<Integer> topView(TreeNode root) {
        List<Integer> result = new ArrayList<>();

        if (root == null)
            return result;

        // Map: HD → Node value (first node seen at that HD)
        Map<Integer, Integer> map = new TreeMap<>();

        // Queue for BFS
        Queue<Pair> queue = new LinkedList<>();

        // Root has HD = 0
        queue.add(new Pair(root, 0));

        while (!queue.isEmpty()) {
            Pair p = queue.poll();
            TreeNode curr = p.node;
            int hd = p.hd;

            // Store value only if HD is encountered for the first time
            if (!map.containsKey(hd)) {
                map.put(hd, curr.val);
            }

            // Left child → HD - 1
            if (curr.left != null)
                queue.add(new Pair(curr.left, hd - 1));

            // Right child → HD + 1
            if (curr.right != null)
                queue.add(new Pair(curr.right, hd + 1));
        }

        // Collect map values in sorted HD order
        for (int key : map.keySet()) {
            result.add(map.get(key));
        }

        return result;
    }

    // -------------------------------------------------
    // MAIN function
    // -------------------------------------------------
    public static void main(String[] args) {

        /*
                     1
                    / \
                   2   3
                    \   \
                     4   5
                      \
                       6

            Top View = [2, 1, 3, 5]
        */

        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.right = new TreeNode(4);
        root.left.right.right = new TreeNode(6);

        root.right = new TreeNode(3);
        root.right.right = new TreeNode(5);

        List<Integer> ans = topView(root);
        System.out.println("Top View of Tree: " + ans);
    }
}

/*
====================================================================
🧠 LOGIC EXPLANATION

We assign each node a **Horizontal Distance (HD)**:
- Root has HD = 0
- Left child → HD - 1
- Right child → HD + 1

Then we perform a **BFS (level order traversal)** because:
➡ BFS guarantees the first time we encounter a particular HD, that node is the one visible from the top.

We store (HD → node value) in a TreeMap so that:
- Keys remain sorted from min HD → max HD
- Extracting top view becomes easy

====================================================================
⏱ TIME & SPACE COMPLEXITY

Time Complexity:   O(N log N)  
   - Inserting into TreeMap (log N) for N nodes.

Space Complexity:  O(N)  
   - Queue + HashMap storing HD entries.

====================================================================
*/
