package Tree;
// Delete Node in a Binary Search Tree
// Demonstrates: Recursive & Iterative approaches using Inorder Predecessor

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int val) {
        this.val = val;
    }
}

public class DeleteNodeInBST {

    /*--------------------------------------------------
      RECURSIVE APPROACH (Using Inorder Predecessor)
      --------------------------------------------------
      Idea:
      - Traverse BST to find the node.
      - If node has 0 or 1 child → return the child.
      - If node has 2 children:
          - Find inorder predecessor (max in left subtree)
          - Attach right subtree to predecessor
          - Return left subtree as new root

      Time Complexity: O(H)
      Space Complexity: O(H)  (recursion stack)
    --------------------------------------------------*/
    public static TreeNode deleteNodeRecursive(TreeNode root, int key) {

        if (root == null) return null;

        if (key < root.val) {
            root.left = deleteNodeRecursive(root.left, key);
        } else if (key > root.val) {
            root.right = deleteNodeRecursive(root.right, key);
        } else {
            // Case 1 & 2: 0 or 1 child
            if (root.left == null) return root.right;
            if (root.right == null) return root.left;

            // Case 3: 2 children
            TreeNode rightChild = root.right;
            TreeNode predecessor = findPredecessor(root.left);

            predecessor.right = rightChild;
            return root.left;
        }

        return root;
    }

    // Find inorder predecessor (rightmost node of left subtree)
    public static TreeNode findPredecessor(TreeNode node) {
        while (node.right != null) {
            node = node.right;
        }
        return node;
    }

    /*--------------------------------------------------
      ITERATIVE APPROACH (Using Inorder Predecessor)
      --------------------------------------------------
      Idea:
      - Traverse iteratively to find the node.
      - Keep track of parent.
      - Handle 3 deletion cases explicitly.
      - No recursion used.

      Time Complexity: O(H)
      Space Complexity: O(1)
    --------------------------------------------------*/
    public static TreeNode deleteNodeIterative(TreeNode root, int key) {

        TreeNode curr = root;
        TreeNode parent = null;

        // Search for node
        while (curr != null && curr.val != key) {
            parent = curr;
            if (key < curr.val) curr = curr.left;
            else curr = curr.right;
        }

        // Key not found
        if (curr == null) return root;

        // Case 1 & 2: Node has 0 or 1 child
        if (curr.left == null || curr.right == null) {
            TreeNode child = (curr.left != null) ? curr.left : curr.right;

            if (parent == null) return child; // deleting root

            if (parent.left == curr) parent.left = child;
            else parent.right = child;

            return root;
        }

        // Case 3: Node has 2 children
        TreeNode predParent = curr;
        TreeNode predecessor = curr.left;

        while (predecessor.right != null) {
            predParent = predecessor;
            predecessor = predecessor.right;
        }

        // Attach predecessor
        curr.val = predecessor.val;

        // Remove predecessor node
        if (predParent.right == predecessor)
            predParent.right = predecessor.left;
        else
            predParent.left = predecessor.left;

        return root;
    }

    // Inorder traversal to verify BST
    public static void inorder(TreeNode root) {
        if (root == null) return;
        inorder(root.left);
        System.out.print(root.val + " ");
        inorder(root.right);
    }

    // Main function
    public static void main(String[] args) {

        /*
              BST:
                    5
                   / \
                  3   6
                 / \
                2   4
                 \
                  1

            Delete key = 3

            Expected Inorder:
            1 2 4 5 6
        */

        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3);
        root.right = new TreeNode(6);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(4);
        root.left.left.right = new TreeNode(1);

        System.out.println("Original BST (Inorder):");
        inorder(root);
        System.out.println();

        root = deleteNodeRecursive(root, 3);
        // root = deleteNodeIterative(root, 3);

        System.out.println("After Deletion (Inorder):");
        inorder(root);
    }
}

/*
--------------------------------------------------
🧠 INTUITION SUMMARY (HINGLISH):

- BST me delete karte waqt 3 cases hote hain:
  1) Leaf node → directly remove
  2) Single child → child ko upar attach
  3) Two children →
     - Inorder predecessor nikaalo (left subtree ka max)
     - Uske right me original right subtree attach karo
     - Left subtree ko new root bana do

- Recursive approach elegant hota hai
- Iterative approach stack space save karta hai

--------------------------------------------------
⏱️ TIME COMPLEXITY:
- Both approaches: O(H)  (H = height of BST)

💾 SPACE COMPLEXITY:
- Recursive: O(H)
- Iterative: O(1)

--------------------------------------------------
*/
