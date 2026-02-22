package Tree;

/*
Problem: Construct BST from Preorder Traversal
Language: Java
Approach: Brute Force -> Better -> Optimal
Sab kuch ek hi file + hinglish comments + main function
*/

import java.util.*;

public class ConstructBSTFromPreorder {

    // =========================
    // Tree Node Definition
    // =========================
    static class TreeNode {
        int val;
        TreeNode left, right;

        TreeNode(int val) {
            this.val = val;
            left = right = null;
        }
    }

    // =========================================================
    // 1️⃣ BRUTE FORCE APPROACH
    // Idea:
    // - Preorder traversal ka first element hamesha root hota hai
    // - Baaki elements ko split karo:
    //   left subtree = root se chhote
    //   right subtree = root se bade
    // - Recursively BST banao
    // =========================================================
    public static TreeNode buildBSTBrute(int[] preorder) {
        return buildBruteHelper(preorder, 0, preorder.length - 1);
    }

    private static TreeNode buildBruteHelper(int[] arr, int start, int end) {
        if (start > end) return null;

        TreeNode root = new TreeNode(arr[start]);

        int idx = start + 1;
        // root se bade element ka index dhundo
        while (idx <= end && arr[idx] < root.val) {
            idx++;
        }

        // left aur right subtree recursively
        root.left = buildBruteHelper(arr, start + 1, idx - 1);
        root.right = buildBruteHelper(arr, idx, end);

        return root;
    }

    // =========================================================
    // 2️⃣ BETTER APPROACH (Using Stack)
    // Idea:
    // - Stack use karo ancestors track karne ke liye
    // - Jab current value chhoti ho → left child
    // - Jab badi ho → pop until sahi parent mile
    // =========================================================
    public static TreeNode buildBSTStack(int[] preorder) {
        if (preorder.length == 0) return null;

        TreeNode root = new TreeNode(preorder[0]);
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        for (int i = 1; i < preorder.length; i++) {
            TreeNode node = new TreeNode(preorder[i]);

            // Case 1: left child
            if (preorder[i] < stack.peek().val) {
                stack.peek().left = node;
            } 
            // Case 2: right child
            else {
                TreeNode parent = null;
                while (!stack.isEmpty() && preorder[i] > stack.peek().val) {
                    parent = stack.pop();
                }
                parent.right = node;
            }

            stack.push(node);
        }
        return root;
    }

    // =========================================================
    // 3️⃣ OPTIMAL APPROACH (Recursion + Upper Bound)
    // Idea:
    // - Preorder ka nature use karo
    // - Har node ke liye ek upper bound maintain karo
    // - Index globally move karega
    // =========================================================
    static int idx = 0;

    public static TreeNode buildBSTOptimal(int[] preorder) {
        idx = 0;
        return buildOptimalHelper(preorder, Integer.MAX_VALUE);
    }

    private static TreeNode buildOptimalHelper(int[] arr, int bound) {
        // agar array khatam ya bound cross ho gaya
        if (idx == arr.length || arr[idx] > bound) {
            return null;
        }

        TreeNode root = new TreeNode(arr[idx++]);

        // left subtree ka bound = root.val
        root.left = buildOptimalHelper(arr, root.val);
        // right subtree ka bound = parent ka bound
        root.right = buildOptimalHelper(arr, bound);

        return root;
    }

    // =========================
    // Inorder traversal (BST verify karne ke liye)
    // =========================
    public static void inorder(TreeNode root) {
        if (root == null) return;
        inorder(root.left);
        System.out.print(root.val + " ");
        inorder(root.right);
    }

    // =========================
    // MAIN FUNCTION
    // =========================
    public static void main(String[] args) {
        int[] preorder = {8, 5, 1, 7, 10, 12};

        // Brute
        TreeNode brute = buildBSTBrute(preorder);
        System.out.print("Brute Inorder: ");
        inorder(brute);
        System.out.println();

        // Better (Stack)
        TreeNode better = buildBSTStack(preorder);
        System.out.print("Stack Inorder: ");
        inorder(better);
        System.out.println();

        // Optimal
        TreeNode optimal = buildBSTOptimal(preorder);
        System.out.print("Optimal Inorder: ");
        inorder(optimal);
        System.out.println();
    }
}

/*
=========================
⏱ TIME & SPACE COMPLEXITY
=========================

1️⃣ Brute Force:
Time: O(N^2) worst case (sorted preorder)
Space: O(N) recursion stack
Intuition:
Har node ke liye split point dhundhna padta hai → slow

2️⃣ Better (Stack):
Time: O(N)
Space: O(N) stack
Intuition:
Ancestors ko stack me rakhkar correct parent find karte hain

3️⃣ Optimal (Best):
Time: O(N)
Space: O(N) recursion stack
Intuition:
BST ka upper bound rule use karke bina extra scan ke tree banate hain
Har element sirf ek baar process hota hai ✔

👉 Interview me OPTIMAL approach sabse recommended hai
*/
