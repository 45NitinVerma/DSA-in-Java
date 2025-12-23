package Tree;

import java.util.*;

// Node structure for Binary Tree
class TreeNode {
    int val;
    TreeNode left, right;

    TreeNode(int x) {
        this.val = x;
        this.left = null;
        this.right = null;
    }
}

// Helper class to store a node along with its horizontal distance (hd)
class Pair {
    TreeNode node;
    int hd;  // horizontal distance

    Pair(TreeNode node, int hd) {
        this.node = node;
        this.hd = hd;
    }
}

public class BottomViewOfBinaryTree {

    // -------------------------------------------------
    // FUNCTION: bottomView
    // Returns list of node values in bottom view order
    // -------------------------------------------------
    public static List<Integer> bottomView(TreeNode root) {

        List<Integer> ans = new ArrayList<>();
        if (root == null) return ans;

        // Map: hd -> node value (we will store the last seen node at each hd)
        Map<Integer, Integer> hdToValue = new TreeMap<>(); // TreeMap gives sorted keys

        // Queue for BFS: stores (node, hd)
        Queue<Pair> queue = new LinkedList<>();
        queue.offer(new Pair(root, 0));  // root has horizontal distance 0

        while (!queue.isEmpty()) {
            Pair cur = queue.poll();
            TreeNode node = cur.node;
            int hd = cur.hd;

            // For bottom view: overwrite value at this hd
            // Because the last node at this hd (level-wise) is the bottom-most
            hdToValue.put(hd, node.val);

            // Left child: hd - 1
            if (node.left != null) {
                queue.offer(new Pair(node.left, hd - 1));
            }

            // Right child: hd + 1
            if (node.right != null) {
                queue.offer(new Pair(node.right, hd + 1));
            }
        }

        // Collect values in increasing order of horizontal distance
        for (int key : hdToValue.keySet()) {
            ans.add(hdToValue.get(key));
        }

        return ans;
    }

    // -------------------------------------------------
    // MAIN: demo
    // -------------------------------------------------
    public static void main(String[] args) {

        /*
                 20
                /  \
               8   22
              / \    \
             5  3    25
               / \
              10 14

        Bottom view: 5 10 3 14 25
        */

        TreeNode root = new TreeNode(20);
        root.left = new TreeNode(8);
        root.right = new TreeNode(22);
        root.left.left = new TreeNode(5);
        root.left.right = new TreeNode(3);
        root.left.right.left = new TreeNode(10);
        root.left.right.right = new TreeNode(14);
        root.right.right = new TreeNode(25);

        List<Integer> bottom = bottomView(root);
        System.out.println("Bottom View of the Tree:");
        for (int val : bottom) {
            System.out.print(val + " ");
        }
    }
}

/*
===========================================================
🧠 BOTTOM VIEW OF BINARY TREE — EXPLANATION

Goal:
------
Print the nodes visible when the tree is viewed from the bottom.

Idea:
------
Use:
1) Horizontal Distance (hd) from root:
   - root has hd = 0
   - left child → hd - 1
   - right child → hd + 1

2) Level Order Traversal (BFS):
   - We traverse level by level.
   - For each (node, hd), we store node.val in a map: hd -> node.val
   - Because BFS goes top → bottom, the last node we see at a given hd
     will be the bottom-most node at that horizontal distance.

3) Use TreeMap<Integer,Integer>:
   - Keys (hd) are sorted.
   - Final bottom view is map.values() in increasing hd order.

Example:
---------
For the tree:

         20
        /  \
       8   22
      / \    \
     5  3    25
       / \
      10 14

Horizontal distances:
20 -> hd 0
8  -> hd -1
22 -> hd 1
5  -> hd -2
3  -> hd 0
10 -> hd -1
14 -> hd 1
25 -> hd 2

From bottom:
hd -2 → 5
hd -1 → 10
hd  0 → 3
hd  1 → 14
hd  2 → 25

So bottom view = [5, 10, 3, 14, 25]

-----------------------------------------------------------
⏱ TIME COMPLEXITY:
- We visit each node exactly once.
- For each node, map operations are O(log N) (TreeMap).
- Overall: O(N log N) (N = number of nodes)

💾 SPACE COMPLEXITY:
- Queue for BFS: O(N)
- Map for hd → node: O(N)
- Overall: O(N)
-----------------------------------------------------------
*/
