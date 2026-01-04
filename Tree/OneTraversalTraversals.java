package Tree;

import java.util.*;

// Binary tree node
class TreeNode {
    int val;
    TreeNode left, right;

    TreeNode(int val) {
        this.val = val;
        this.left = null;
        this.right = null;
    }
}

public class OneTraversalTraversals {

    // Class to store node + state
    static class Pair {
        TreeNode node;
        int state;  
        // state = 1 → pre
        // state = 2 → in
        // state = 3 → post

        Pair(TreeNode node, int state) {
            this.node = node;
            this.state = state;
        }
    }

    public static void allTraversals(TreeNode root,
                                     List<Integer> pre,
                                     List<Integer> in,
                                     List<Integer> post) {

        if (root == null) return;

        Stack<Pair> stack = new Stack<>();
        stack.push(new Pair(root, 1));

        while (!stack.isEmpty()) {
            Pair p = stack.pop();

            if (p.state == 1) {
                // PREORDER (root first)
                pre.add(p.node.val);

                // Move to next state
                p.state = 2;
                stack.push(p);

                // Push left child first
                if (p.node.left != null)
                    stack.push(new Pair(p.node.left, 1));

            } else if (p.state == 2) {
                // INORDER (left, root, right)
                in.add(p.node.val);

                // Move to next state
                p.state = 3;
                stack.push(p);

                // Push right child
                if (p.node.right != null)
                    stack.push(new Pair(p.node.right, 1));

            } else {
                // POSTORDER
                post.add(p.node.val);
            }
        }
    }

    public static void main(String[] args) {
        /*
                  1
                 / \
                2   3
               / \
              4   5
         */

        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        List<Integer> preorder = new ArrayList<>();
        List<Integer> inorder = new ArrayList<>();
        List<Integer> postorder = new ArrayList<>();

        allTraversals(root, preorder, inorder, postorder);

        System.out.println("Preorder  : " + preorder);
        System.out.println("Inorder   : " + inorder);
        System.out.println("Postorder : " + postorder);
    }
}

/*
---------------------------------------------------------------
🧠 EXPLANATION (1 Traversal – 3 Orders)

We use a stack of (node, state):

state = 1 → PREORDER  
    - Process node (add to preorder)
    - Next: go left  
state = 2 → INORDER  
    - Left is done
    - Process node (add to inorder)
    - Next: go right  
state = 3 → POSTORDER  
    - Both subtrees finished
    - Process node (add to postorder)

This way, each node is visited exactly 1 time but contributes to 3 orders.

---------------------------------------------------------------
⏱ TIME COMPLEXITY:  O(N)  
Every node processed exactly once.

💾 SPACE COMPLEXITY: O(N)  
Stack stores at most height of tree + states.

---------------------------------------------------------------
*/
