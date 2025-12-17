package Tree;

import java.util.*;

// -------------------------------------------------------------
// Node structure
// -------------------------------------------------------------
class Node {
    int data;
    Node left, right;

    Node(int val) {
        this.data = val;
        left = right = null;
    }
}

// -------------------------------------------------------------
// Solution class for Vertical Order Traversal
// -------------------------------------------------------------
class Solution {

    // Helper Pair class to store node with vertical and level (row)
    static class Pair {
        Node node;
        int vertical;   // column number
        int level;      // row number

        Pair(Node n, int v, int l) {
            node = n;
            vertical = v;
            level = l;
        }
    }

    // ---------------------------------------------------------
    // Vertical Order Traversal Function
    // ---------------------------------------------------------
    public List<List<Integer>> verticalTraversal(Node root) {

        // TreeMap maintains sorted vertical columns
        // For each vertical: TreeMap sorts levels (rows)
        // For each level: PriorityQueue stores values sorted in ascending order
        TreeMap<Integer, TreeMap<Integer, PriorityQueue<Integer>>> map = new TreeMap<>();

        // BFS Queue -> each element has (node, vertical, level)
        Queue<Pair> q = new LinkedList<>();
        q.offer(new Pair(root, 0, 0));

        // BFS traversal
        while (!q.isEmpty()) {
            Pair p = q.poll();
            Node node = p.node;
            int v = p.vertical;
            int l = p.level;

            // Insert into structure
            map.putIfAbsent(v, new TreeMap<>());
            map.get(v).putIfAbsent(l, new PriorityQueue<>());
            map.get(v).get(l).offer(node.data);

            // Left child → vertical - 1, level + 1
            if (node.left != null)
                q.offer(new Pair(node.left, v - 1, l + 1));

            // Right child → vertical + 1, level + 1
            if (node.right != null)
                q.offer(new Pair(node.right, v + 1, l + 1));
        }

        // Build final answer from sorted map
        List<List<Integer>> ans = new ArrayList<>();

        for (TreeMap<Integer, PriorityQueue<Integer>> colMap : map.values()) {
            List<Integer> col = new ArrayList<>();
            for (PriorityQueue<Integer> pq : colMap.values()) {
                while (!pq.isEmpty()) {
                    col.add(pq.poll());
                }
            }
            ans.add(col);
        }

        return ans;
    }
}

// -------------------------------------------------------------
// MAIN class for testing
// -------------------------------------------------------------
public class VerticalOrderTraversal {

    public static void main(String[] args) {

        /*
                   3
                 /   \
                9     20
                     /  \
                    15   7
        Expected Vertical Order:
        [
            [9],
            [3, 15],
            [20],
            [7]
        ]
        */

        Node root = new Node(3);
        root.left = new Node(9);
        root.right = new Node(20);
        root.right.left = new Node(15);
        root.right.right = new Node(7);

        Solution sol = new Solution();
        List<List<Integer>> ans = sol.verticalTraversal(root);

        System.out.println("Vertical Order Traversal:");
        for (List<Integer> list : ans) {
            System.out.println(list);
        }
    }
}

/*
=====================================================================
🧠 EXPLANATION — How Vertical Order Works

We consider:
- vertical → horizontal distance from root (root = 0)
- level    → depth of node (root = 0)

Left child  → vertical - 1, level + 1  
Right child → vertical + 1, level + 1  

We use:
TreeMap<Integer, TreeMap<Integer, PriorityQueue<Integer>>>

Why?
• TreeMap keeps verticals sorted
• Inner TreeMap keeps levels sorted
• PriorityQueue handles multiple nodes at same vertical+level in sorted order

=====================================================================
⏱️ TIME COMPLEXITY:
- BFS Traversal: O(N)
- Insertion into TreeMap + PriorityQueue: O(log N)
Total: O(N log N)

💾 SPACE COMPLEXITY:
- Map + Queue: O(N)

=====================================================================
*/
