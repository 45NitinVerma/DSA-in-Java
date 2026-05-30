package Tree;

// ✅ Problem: Validate Binary Search Tree (BST)
// Language: Java
// Requirement: Single code, easy & intuitive, Hinglish comments,
//              main function included, time & space intuition at end.

/*
BST Property (short me):
1. Left subtree ke saare values < root
2. Right subtree ke saare values > root
3. Ye rule har node ke liye valid hona chahiye
*/

public class ValidateBST {

    // Tree ka structure define kar rahe hain
    static class TreeNode {
        int val;
        TreeNode left, right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    // Helper function jo range (min, max) maintain karta hai
    public static boolean isValidBST(TreeNode root) {
        // Initially koi limit nahi hoti
        return validate(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private static boolean validate(TreeNode node, long min, long max) {
        // Base case: agar node null hai, to BST valid hai
        if (node == null) return true;

        // Agar current node BST rule tod raha hai
        if (node.val <= min || node.val >= max) {
            return false;
        }

        // Left subtree: max boundary = current node value
        // Right subtree: min boundary = current node value
        return validate(node.left, min, node.val) &&
               validate(node.right, node.val, max);
    }

    // Main function for testing
    public static void main(String[] args) {
        /*
               5
              / \
             3   7
            / \   \
           2   4   8
        */
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3);
        root.right = new TreeNode(7);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(4);
        root.right.right = new TreeNode(8);

        System.out.println("Is Valid BST? " + isValidBST(root));
    }
}

/*
==================== INTUITION ====================

Soch ye hai:
Har node ke liye ek allowed range hoti hai (min, max).
- Root ke liye range = (-∞, +∞)
- Left child ke liye: (min, root.val)
- Right child ke liye: (root.val, max)

Agar koi node apni range ke bahar chali gayi → BST invalid ❌

Ye approach simple aur recursive hai.

==================== TIME COMPLEXITY ====================
O(n)
Har node sirf ek baar visit ho rahi hai.

==================== SPACE COMPLEXITY ====================
O(h)
h = height of tree (recursion stack)
Worst case (skewed tree): O(n)
Best case (balanced tree): O(log n)
========================================================
*/
