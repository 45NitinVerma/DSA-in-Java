package Tree;
import java.util.*;

// TreeNode class represents a node in a Binary Tree
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        this.val = x;
        this.left = null;
        this.right = null;
    }
}

public class DFS {

    // ---------------- INORDER Traversal ----------------
    // (Left → Root → Right)
    public static void inorder(TreeNode root, List<Integer> result) {
        if (root == null) return;

        inorder(root.left, result);   // Visit Left Subtree
        result.add(root.val);         // Visit Root
        inorder(root.right, result);  // Visit Right Subtree
    }

    // ---------------- PREORDER Traversal ----------------
    // (Root → Left → Right)
    public static void preorder(TreeNode root, List<Integer> result) {
        if (root == null) return;

        result.add(root.val);         // Visit Root
        preorder(root.left, result);  // Visit Left Subtree
        preorder(root.right, result); // Visit Right Subtree
    }

    // ---------------- POSTORDER Traversal ----------------
    // (Left → Right → Root)
    public static void postorder(TreeNode root, List<Integer> result) {
        if (root == null) return;

        postorder(root.left, result);   // Visit Left Subtree
        postorder(root.right, result);  // Visit Right Subtree
        result.add(root.val);           // Visit Root
    }

    // Function to print list
    public static void printList(String traversalType, List<Integer> list) {
        System.out.print(traversalType + ": ");
        for (int num : list) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    // ---------------- MAIN FUNCTION ----------------
    public static void main(String[] args) {

        /*
             Constructed Binary Tree:

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

        // Store traversal results
        List<Integer> inorderList = new ArrayList<>();
        List<Integer> preorderList = new ArrayList<>();
        List<Integer> postorderList = new ArrayList<>();

        // Perform DFS traversals
        inorder(root, inorderList);
        preorder(root, preorderList);
        postorder(root, postorderList);

        // Print results
        printList("Inorder Traversal", inorderList);
        printList("Preorder Traversal", preorderList);
        printList("Postorder Traversal", postorderList);
    }
}

/*
--------------------------------------------
🧠 EXPLANATION:

DFS = Depth First Search

We visit all nodes in depth direction before backtracking.

🔹 Inorder (Left → Root → Right)
   → Gives sorted order if tree is BST
   Example: [4, 2, 5, 1, 3]

🔹 Preorder (Root → Left → Right)
   → Used to copy or serialize the tree
   Example: [1, 2, 4, 5, 3]

🔹 Postorder (Left → Right → Root)
   → Used to delete or free the tree
   Example: [4, 5, 2, 3, 1]

--------------------------------------------
⏱️ Time Complexity:  O(N)
    - Each node is visited exactly once.

💾 Space Complexity: O(H)
    - H = height of the tree (for recursion stack)
    - Worst case (skewed tree): O(N)
    - Best case (balanced tree): O(log N)
--------------------------------------------
*/


/* 
Inorder Traversal: 4 2 5 1 3 
Preorder Traversal: 1 2 4 5 3 
Postorder Traversal: 4 5 2 3 1 
 */