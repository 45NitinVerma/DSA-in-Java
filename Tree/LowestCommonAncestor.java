package Tree;

class TreeNode {
    int val;
    TreeNode left, right;

    TreeNode(int x) {
        val = x;
    }
}

public class LowestCommonAncestor {

    // ------------------------------------------------------
    // ⭐ 1) Recursive LCA Function
    // ------------------------------------------------------
    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

        // Base Case 1: If tree is empty → no LCA
        if (root == null) return null;

        // Base Case 2: If either p or q matches root → root is an ancestor
        if (root == p || root == q) return root;

        // Recursive search in left and right subtrees
        TreeNode leftLCA = lowestCommonAncestor(root.left, p, q);
        TreeNode rightLCA = lowestCommonAncestor(root.right, p, q);

        // Case 1: p is in left subtree AND q is in right subtree (or vice versa)
        // Then root is the LCA
        if (leftLCA != null && rightLCA != null) 
            return root;

        // Case 2: Otherwise, return whichever side is non-null
        // (because that side contains both p and q)
        return (leftLCA != null) ? leftLCA : rightLCA;
    }

    // ------------------------------------------------------
    // MAIN (Testing)
    // ------------------------------------------------------
    public static void main(String[] args) {
        /*
                 3
                / \
               5   1
              / \ / \
             6  2 0  8
               / \
              7   4
        */

        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(5);
        root.right = new TreeNode(1);

        root.left.left = new TreeNode(6);
        root.left.right = new TreeNode(2);

        root.right.left = new TreeNode(0);
        root.right.right = new TreeNode(8);

        root.left.right.left = new TreeNode(7);
        root.left.right.right = new TreeNode(4);

        TreeNode p = root.left;       // Node 5
        TreeNode q = root.left.right.right; // Node 4

        TreeNode lca = lowestCommonAncestor(root, p, q);
        System.out.println("Lowest Common Ancestor is: " + lca.val);
    }
}

/*
====================================================================
🧠 RECURSIVE INTUITION (Hinglish)

Hum root se start karte hain aur check karte hain:

1️⃣ Agar root null → koi LCA nahi hai, return null  
2️⃣ Agar root p ya q ke barabar hai → return root  
   (kyunki root hi unka ancestor ban sakta hai)

3️⃣ Dono subtrees ko check karo:
       leftLCA  = LCA(root.left, p, q)
       rightLCA = LCA(root.right, p, q)

4️⃣ Agar:
       leftLCA != null AND rightLCA != null
   → matlab p left me mila aur q right me (ya vice-versa)
   → toh LCA = root  

5️⃣ Agar sirf ek side non-null hai, wohi LCA hai.

====================================================================
⏱️ TIME COMPLEXITY
O(N)
Har node ko bas ek baar visit karte hain.

💾 SPACE COMPLEXITY
O(H)
Recursion stack height = tree ki height  
Worst-case (skewed tree) → O(N)  
Best-case (balanced tree) → O(log N)

====================================================================
*/
