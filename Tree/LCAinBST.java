package Tree;

// LCA in BST (Lowest Common Ancestor)
// Hinglish comments + main function included

class Node {
    int val;
    Node left, right;

    Node(int val) {
        this.val = val;
        left = right = null;
    }
}

public class LCAinBST {

    // Function to find LCA in BST
    public static Node lcaBST(Node root, int p, int q) {

        // Base case: agar tree hi empty hai
        if (root == null) return null;

        /*
         BST property:
         - Left subtree -> smaller values
         - Right subtree -> larger values
        */

        // Agar dono values root se choti hain → left side jao
        if (p < root.val && q < root.val) {
            return lcaBST(root.left, p, q);
        }

        // Agar dono values root se badi hain → right side jao
        if (p > root.val && q > root.val) {
            return lcaBST(root.right, p, q);
        }

        /*
         Yahan aane ka matlab:
         - Ek value left me aur ek right me hai
         OR
         - Root hi p ya q ke barabar hai

         Is case me root hi LCA hoga
        */
        return root;
    }

    public static void main(String[] args) {

        /*
              6
             / \
            2   8
           / \ / \
          0  4 7  9
            / \
           3   5
        */

        Node root = new Node(6);
        root.left = new Node(2);
        root.right = new Node(8);
        root.left.left = new Node(0);
        root.left.right = new Node(4);
        root.left.right.left = new Node(3);
        root.left.right.right = new Node(5);
        root.right.left = new Node(7);
        root.right.right = new Node(9);

        int p = 2, q = 8;

        Node ans = lcaBST(root, p, q);

        if (ans != null) {
            System.out.println("LCA of " + p + " and " + q + " is: " + ans.val);
        } else {
            System.out.println("LCA not found");
        }
    }
}

/*
========================
INTUITION (Simple Words)
========================
BST me values sorted hoti hain:
- Agar dono nodes current root se choti hain → left jao
- Agar dono badi hain → right jao
- Agar ek left aur ek right ya root ke barabar → wahi LCA

Isliye poora tree traverse karne ki zarurat nahi hoti 👍

========================
TIME COMPLEXITY
========================
O(h)
h = height of BST
- Balanced BST → O(log n)
- Skewed BST → O(n)

========================
SPACE COMPLEXITY
========================
O(h)
- Recursion stack ke liye
- Worst case skewed tree me O(n)
*/
