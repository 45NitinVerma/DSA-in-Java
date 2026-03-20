package Tree;

/*
 Problem: Kth Smallest Element in a BST
 Approach: Inorder Traversal (Left -> Root -> Right)
 Kyunki BST ka inorder traversal hamesha sorted order deta hai,
 toh hum simply inorder me traverse karke kth element pick kar sakte hain.
*/

class TreeNode {
    int val;
    TreeNode left, right;

    TreeNode(int val) {
        this.val = val;
        this.left = this.right = null;
    }
}

public class KthSmallestInBST {

    // Counter to track nodes visited so far
    static int count = 0;
    static int answer = -1;

    // Inorder traversal function
    public static void inorder(TreeNode root, int k) {
        // Base case: agar node null hai toh return
        if (root == null) return;

        // Step 1: Left subtree visit karo
        inorder(root.left, k);

        // Step 2: Current node process karo
        count++;
        if (count == k) {
            answer = root.val;
            return; // kth mil gaya, aage jaane ki zarurat nahi
        }

        // Step 3: Right subtree visit karo
        inorder(root.right, k);
    }

    // Main function jo kth smallest return karega
    public static int kthSmallest(TreeNode root, int k) {
        count = 0;        // har test case ke liye reset
        answer = -1;
        inorder(root, k);
        return answer;
    }

    // Driver / Main method
    public static void main(String[] args) {
        /*
              5
             / \
            3   6
           / \
          2   4
         /
        1

        Inorder traversal: 1 2 3 4 5 6
        */

        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3);
        root.right = new TreeNode(6);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(4);
        root.left.left.left = new TreeNode(1);

        int k = 3;
        System.out.println("Kth Smallest Element is: " + kthSmallest(root, k));
    }
}

/*
========================
INTUITION (Samajhne ka tarika):
========================
BST ka special property hota hai:
- Left subtree ke saare elements chhote
- Right subtree ke saare elements bade

Inorder traversal (Left, Root, Right) BST ko sorted order me nikal deta hai.
Isliye jaise hi hum kth node visit karte hain inorder me,
wahi answer hota hai.

========================
TIME COMPLEXITY:
========================
O(N) worst case me, jab hume poora tree traverse karna pade.
(Average case me O(k) bhi ho sakta hai agar kth jaldi mil jaye)

========================
SPACE COMPLEXITY:
========================
O(H) where H = height of tree
(recursive stack ki wajah se)
Worst case skewed tree me H = N
========================
*/
