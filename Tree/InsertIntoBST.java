package Tree;

// Insert into Binary Search Tree (BST)
// Demonstrates: Brute, Better, and Optimal Approaches

class TreeNode {
    int val;
    TreeNode left, right;

    TreeNode(int val) {
        this.val = val;
        left = right = null;
    }
}

public class InsertIntoBST {

    /*--------------------------------------------------
      BRUTE APPROACH (Rebuild Tree using Inorder)
      --------------------------------------------------
      Idea:
      1. Perform inorder traversal to get sorted values.
      2. Insert the new value into the sorted list.
      3. Rebuild BST from sorted list.

      ❌ Not practical, only for understanding.

      Time Complexity: O(N)
      Space Complexity: O(N)
    --------------------------------------------------*/
    public static TreeNode insertIntoBST_Brute(TreeNode root, int val) {
        java.util.List<Integer> list = new java.util.ArrayList<>();
        inorder(root, list);
        list.add(val);
        java.util.Collections.sort(list);
        return buildBST(list, 0, list.size() - 1);
    }

    private static void inorder(TreeNode root, java.util.List<Integer> list) {
        if (root == null) return;
        inorder(root.left, list);
        list.add(root.val);
        inorder(root.right, list);
    }

    private static TreeNode buildBST(java.util.List<Integer> list, int start, int end) {
        if (start > end) return null;
        int mid = (start + end) / 2;
        TreeNode root = new TreeNode(list.get(mid));
        root.left = buildBST(list, start, mid - 1);
        root.right = buildBST(list, mid + 1, end);
        return root;
    }

    /*--------------------------------------------------
      BETTER APPROACH (Recursive Insertion)
      --------------------------------------------------
      Idea:
      - Traverse recursively.
      - Insert at correct leaf position.
      - Uses BST property.

      Time Complexity: O(H)
      Space Complexity: O(H) (recursion stack)
    --------------------------------------------------*/
    public static TreeNode insertIntoBST_Better(TreeNode root, int val) {

        if (root == null) {
            return new TreeNode(val);
        }

        if (val < root.val) {
            root.left = insertIntoBST_Better(root.left, val);
        } else {
            root.right = insertIntoBST_Better(root.right, val);
        }

        return root;
    }

    /*--------------------------------------------------
      OPTIMAL APPROACH (Iterative Insertion)
      --------------------------------------------------
      Idea:
      - Traverse iteratively.
      - Stop at leaf and insert.
      - No recursion stack used.

      Time Complexity: O(H)
      Space Complexity: O(1)
    --------------------------------------------------*/
    public static TreeNode insertIntoBST_Optimal(TreeNode root, int val) {

        if (root == null) return new TreeNode(val);

        TreeNode curr = root;

        while (true) {
            if (val < curr.val) {
                if (curr.left == null) {
                    curr.left = new TreeNode(val);
                    break;
                }
                curr = curr.left;
            } else {
                if (curr.right == null) {
                    curr.right = new TreeNode(val);
                    break;
                }
                curr = curr.right;
            }
        }

        return root;
    }

    // Inorder traversal to verify BST
    public static void printInorder(TreeNode root) {
        if (root == null) return;
        printInorder(root.left);
        System.out.print(root.val + " ");
        printInorder(root.right);
    }

    // Main method
    public static void main(String[] args) {

        /*
            BST:
                4
               / \
              2   7
             / \
            1   3

            Insert: 5

            Output (Inorder):
            1 2 3 4 5 7
        */

        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.right = new TreeNode(7);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);

        System.out.println("Inorder Before Insertion:");
        printInorder(root);

        root = insertIntoBST_Optimal(root, 5);

        System.out.println("\nInorder After Insertion:");
        printInorder(root);
    }
}

/*
--------------------------------------------------
🧠 INTUITION SUMMARY (HINGLISH):

- BST me left subtree me chhoti values hoti hain,
  right subtree me badi values.
- Brute approach tree ko tod ke dubara banata hai (inefficient).
- Better approach recursion se sahi jagah insert karta hai.
- Optimal approach iterative hai, stack use nahi karta.

--------------------------------------------------
⏱️ TIME COMPLEXITY:
- Brute:    O(N)
- Better:   O(H)
- Optimal:  O(H)

(H = height of BST)

💾 SPACE COMPLEXITY:
- Brute:    O(N)
- Better:   O(H)
- Optimal:  O(1)

--------------------------------------------------
*/
