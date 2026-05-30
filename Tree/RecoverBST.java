package Tree;

// Definition for a binary tree node
class TreeNode {
    int val;
    TreeNode left, right;

    TreeNode(int val) {
        this.val = val;
        this.left = this.right = null;
    }
}

public class RecoverBST {

    // Ye 4 pointers important hain incorrect nodes detect karne ke liye
    TreeNode first, middle, last, prev;

    // Inorder traversal function
    public void inorderT(TreeNode root) {
        if (root == null) return;

        // Left subtree
        inorderT(root.left);

        // Check violation: agar current node prev se chhota hai → BST violate hua
        if (prev != null && root.val < prev.val) {

            // First violation
            if (first == null) {
                first = prev;      // bada wala (galat)
                middle = root;     // chhota wala (galat)
            } 
            // Second violation
            else {
                last = root;
            }
        }

        // Update prev
        prev = root;

        // Right subtree
        inorderT(root.right);
    }

    public void recoverTree(TreeNode root) {
        first = middle = last = null;

        // prev ko minimum se initialize karte hain
        prev = new TreeNode(Integer.MIN_VALUE);

        // Inorder traversal se nodes detect karenge
        inorderT(root);

        // Case 1: Non-adjacent swapped nodes
        if (first != null && last != null) {
            int temp = first.val;
            first.val = last.val;
            last.val = temp;
        } 
        // Case 2: Adjacent swapped nodes
        else if (first != null && middle != null) {
            int temp = first.val;
            first.val = middle.val;
            middle.val = temp;
        }
    }

    // Helper: inorder print (check karne ke liye)
    public static void printInorder(TreeNode root) {
        if (root == null) return;
        printInorder(root.left);
        System.out.print(root.val + " ");
        printInorder(root.right);
    }

    public static void main(String[] args) {
        /*
            Example BST (galat swapped):
                    3
                   / \
                  1   4
                     /
                    2

            Yahan 2 aur 3 swapped hain
        */

        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(1);
        root.right = new TreeNode(4);
        root.right.left = new TreeNode(2);

        System.out.print("Before Recovery (Inorder): ");
        printInorder(root);
        System.out.println();

        RecoverBST obj = new RecoverBST();
        obj.recoverTree(root);

        System.out.print("After Recovery (Inorder): ");
        printInorder(root);
        System.out.println();
    }
}


/*
==================== INTUITION (Hinglish) ====================

👉 BST ka inorder traversal hamesha sorted hota hai.

👉 Agar 2 nodes galti se swap ho gaye:
   - Toh inorder me 2 jagah violation dikhega
   - (prev.val > current.val)

👉 Hum 4 pointers use karte hain:
   - first  → pehla galat bada element
   - middle → pehla galat chhota element
   - last   → dusra galat chhota element
   - prev   → previous node (inorder me)

👉 Cases:
   1. Non-adjacent swap:
      e.g. 1 5 3 4 2 6
      → first = 5, last = 2

   2. Adjacent swap:
      e.g. 1 3 2 4 5
      → first = 3, middle = 2

👉 Final:
   - Agar last mila → swap(first, last)
   - Warna → swap(first, middle)


==================== TIME & SPACE ====================

⏱ Time Complexity: O(N)
   → Har node ko ek baar visit karte hain

📦 Space Complexity: O(H)
   → Recursion stack (height of tree)
   → Worst case: O(N), Best: O(log N)

====================================================
*/

