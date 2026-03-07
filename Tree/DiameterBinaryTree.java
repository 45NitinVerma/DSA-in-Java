// File: DiameterBinaryTree.java
package Tree;

/*
 Compute diameter (maximum path) of a binary tree using recursive heights.

 We'll compute:
   diameterInNodes(root) -> returns number of nodes on longest path between any two nodes.
 If you prefer diameter in edges, subtract 1 from the result.

 Approach:
 - For each node, get leftHeight and rightHeight.
 - Candidate diameter through node = leftHeight + rightHeight + 1 (nodes counted).
 - Keep global max while computing heights (post-order).
*/

class TreeNode {
    int val;
    TreeNode left, right;
    TreeNode(int x) { val = x; }
}

public class DiameterBinaryTree {

    // Wrapper to hold global maximum
    private static class Holder {
        int maxNodes = 0;
    }

    // Returns height (number of nodes on longest path from node down to leaf)
    private static int heightAndComputeDiameter(TreeNode node, Holder h) {
        if (node == null) return 0;

        int leftH = heightAndComputeDiameter(node.left, h);
        int rightH = heightAndComputeDiameter(node.right, h);

        // nodes on path passing through this node
        int nodesThrough = leftH + rightH + 1;
        h.maxNodes = Math.max(h.maxNodes, nodesThrough);

        // return height in nodes (max depth of subtree)
        return 1 + Math.max(leftH, rightH);
    }

    // Public API: diameter as number of nodes on longest path
    public static int diameterInNodes(TreeNode root) {
        Holder h = new Holder();
        heightAndComputeDiameter(root, h);
        return h.maxNodes;
    }

    // If you prefer diameter in edges:
    // public static int diameterInEdges(TreeNode root) {
    //     int nodes = diameterInNodes(root);
    //     return nodes == 0 ? 0 : nodes - 1;
    // }

    // Demo
    public static void main(String[] args) {
        /*
             Example tree:
                  1
                 / \
                2   3
               / \
              4   5
             The longest path is 4 -> 2 -> 5 (or 4->2->1->3 if longer),
             depending on shape. For this tree:
             left height = 2 (nodes 2->4), right height = 1 (node 3)
             Best path: 4 -> 2 -> 1 -> 3 => 4 nodes -> diameterInNodes = 4
        */
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        int diameterNodes = diameterInNodes(root);
        System.out.println("Diameter (in nodes): " + diameterNodes);
        System.out.println("Diameter (in edges): " + (diameterNodes - 1));
    }
}

/*
Complexity:
- Time:  O(N)   (each node visited once)
- Space: O(H)   (recursion stack, H = tree height)
*/
