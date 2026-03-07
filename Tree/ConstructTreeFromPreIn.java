package Tree;

import java.util.*;

// Tree node definition
class TreeNode {
    int val;
    TreeNode left, right;

    TreeNode(int val) {
        this.val = val;
    }
}

public class ConstructTreeFromPreIn {

    // Index to track current root in preorder traversal
    private int preIndex = 0;

    // Map to store value -> index mapping for inorder traversal
    private Map<Integer, Integer> inorderIndexMap = new HashMap<>();

    // -------------------------------------------------
    // MAIN FUNCTION TO BUILD TREE
    // -------------------------------------------------
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        int n = inorder.length;

        // Build hashmap for inorder indices (O(1) lookup)
        for (int i = 0; i < n; i++) {
            inorderIndexMap.put(inorder[i], i);
        }

        return buildTreeUtil(preorder, 0, n - 1);
    }

    // -------------------------------------------------
    // RECURSIVE HELPER FUNCTION
    // -------------------------------------------------
    private TreeNode buildTreeUtil(int[] preorder, int inStart, int inEnd) {

        // Base case
        if (inStart > inEnd)
            return null;

        // Pick current root from preorder
        int rootVal = preorder[preIndex++];
        TreeNode root = new TreeNode(rootVal);

        // Find root index in inorder
        int inRootIndex = inorderIndexMap.get(rootVal);

        // Build left and right subtrees
        root.left = buildTreeUtil(preorder, inStart, inRootIndex - 1);
        root.right = buildTreeUtil(preorder, inRootIndex + 1, inEnd);

        return root;
    }

    // -------------------------------------------------
    // PRINT INORDER (for verification)
    // -------------------------------------------------
    public static void printInorder(TreeNode root) {
        if (root == null) return;
        printInorder(root.left);
        System.out.print(root.val + " ");
        printInorder(root.right);
    }

    // -------------------------------------------------
    // MAIN (DEMO)
    // -------------------------------------------------
    public static void main(String[] args) {
        ConstructTreeFromPreIn obj = new ConstructTreeFromPreIn();

        int[] preorder = {3, 9, 20, 15, 7};
        int[] inorder  = {9, 3, 15, 20, 7};

        TreeNode root = obj.buildTree(preorder, inorder);

        System.out.print("Inorder of constructed tree: ");
        printInorder(root);
    }
}

/*
-------------------------------------------------------
🧠 EXPLANATION:

Preorder: Root → Left → Right
Inorder : Left → Root → Right

Steps:
1️⃣ Preorder ka first element hamesha ROOT hota hai.
2️⃣ Us root ko inorder array mein dhundo.
3️⃣ Inorder ke:
    - left part → left subtree
    - right part → right subtree
4️⃣ Recursively same process repeat karo.

-------------------------------------------------------
EXAMPLE:

preorder = [3, 9, 20, 15, 7]
inorder  = [9, 3, 15, 20, 7]

Root = 3
Left inorder  = [9]
Right inorder = [15, 20, 7]

Left subtree root  = 9
Right subtree root = 20

-------------------------------------------------------
⏱️ TIME COMPLEXITY:
O(N)
- Each node processed once
- HashMap lookup is O(1)

💾 SPACE COMPLEXITY:
O(N)
- HashMap storage
- Recursion stack (worst case skewed tree)

-------------------------------------------------------
*/
