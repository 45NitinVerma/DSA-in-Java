package Tree;
import java.util.*;

class TreeNode {
    int val;
    TreeNode left, right;

    TreeNode(int val) {
        this.val = val;
        this.left = null;
        this.right = null;
    }
}

public class MorrisTraversal {

    /*--------------------------------------------------
      MORRIS INORDER TRAVERSAL
      --------------------------------------------------
      Idea:
      - Avoid recursion & stack
      - Use temporary threads to predecessor
      - Restore tree after traversal

      Time Complexity: O(N)
      Space Complexity: O(1)
    --------------------------------------------------*/

    public static List<Integer> morrisInorder(TreeNode root) {

        List<Integer> inorder = new ArrayList<>();
        TreeNode curr = root;

        while (curr != null) {

            // Case 1: No left child
            if (curr.left == null) {
                inorder.add(curr.val); // visit
                curr = curr.right;
            }

            // Case 2: Left child exists
            else {
                TreeNode prev = curr.left;

                // Find rightmost node in left subtree
                while (prev.right != null && prev.right != curr) {
                    prev = prev.right;
                }

                // Create thread
                if (prev.right == null) {
                    prev.right = curr;
                    curr = curr.left;
                }
                // Thread already exists → remove & visit
                else {
                    prev.right = null;
                    inorder.add(curr.val);
                    curr = curr.right;
                }
            }
        }
        return inorder;
    }

        /*--------------------------------------------------
      MORRIS PREORDER TRAVERSAL
      --------------------------------------------------
      Difference from Inorder:
      - Visit node BEFORE creating thread

      Time Complexity: O(N)
      Space Complexity: O(1)
    --------------------------------------------------*/

    public static List<Integer> morrisPreorder(TreeNode root) {

        List<Integer> preorder = new ArrayList<>();
        TreeNode curr = root;

        while (curr != null) {

            // Case 1: No left child
            if (curr.left == null) {
                preorder.add(curr.val); // visit
                curr = curr.right;
            }

            // Case 2: Left child exists
            else {
                TreeNode prev = curr.left;

                while (prev.right != null && prev.right != curr) {
                    prev = prev.right;
                }

                // Create thread and visit
                if (prev.right == null) {
                    preorder.add(curr.val);
                    prev.right = curr;
                    curr = curr.left;
                }
                // Remove thread
                else {
                    prev.right = null;
                    curr = curr.right;
                }
            }
        }
        return preorder;
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

        System.out.println("Morris Inorder Traversal: " + morrisInorder(root)); // Expected: [4, 2, 5, 1, 3]

        // Reconstruct tree for preorder as morris traversal modifies the tree
        root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        System.out.println("Morris Preorder Traversal: " + morrisPreorder(root)); // Expected: [1, 2, 4, 5, 3]
    }
    
}
