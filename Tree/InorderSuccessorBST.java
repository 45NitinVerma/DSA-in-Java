package Tree;

/*
Problem: Inorder Successor in a BST
Inorder traversal: Left -> Root -> Right
Inorder successor = next node in inorder traversal.

Approach (Iterative, without parent pointer):
- Agar current node ka right child exist karta hai:
  successor = right subtree ka leftmost node
- Warna:
  root se traverse karo aur last bada ancestor track karo
*/

class InorderSuccessorBST {

    // BST Node definition
    static class Node {
        int val;
        Node left, right;

        Node(int val) {
            this.val = val;
            this.left = this.right = null;
        }
    }

    // Function to find inorder successor
    static Node inorderSuccessor(Node root, Node target) {
        Node successor = null;
        Node curr = root;

        // Jab tak current null na ho
        while (curr != null) {
            if (target.val < curr.val) {
                // Target left side mein hai
                // Current node ek potential successor ho sakta hai
                successor = curr;
                curr = curr.left;
            } else {
                // Target right side mein hai ya equal hai
                curr = curr.right;
            }
        }
        return successor;
    }

    // Helper function to insert node in BST
    static Node insert(Node root, int val) {
        if (root == null) return new Node(val);
        if (val < root.val) root.left = insert(root.left, val);
        else root.right = insert(root.right, val);
        return root;
    }

    // Main function
    public static void main(String[] args) {
        /*
               20
              /  \
            10    30
           /  \
          5   15
        */

        Node root = null;
        int[] values = {20, 10, 30, 5, 15};

        for (int v : values) {
            root = insert(root, v);
        }

        Node target = root.left.right; // Node with value 15

        Node successor = inorderSuccessor(root, target);

        if (successor != null) {
            System.out.println("Inorder Successor of " + target.val + " is: " + successor.val);
        } else {
            System.out.println("Inorder Successor does not exist");
        }
    }
}

/*
========================
INTUITION (Simple Words)
========================
BST ka property hota hai:
Left < Root < Right

Isliye:
- Agar target ka right subtree hai, toh successor wahi milta hai
- Agar right subtree nahi hai, toh root se traverse karke
  jo pehla bada ancestor mile wahi successor hota hai

========================
TIME COMPLEXITY
========================
O(H)
H = height of BST
Worst case (skewed tree): O(N)
Best case (balanced BST): O(log N)

========================
SPACE COMPLEXITY
========================
O(1)
Koi extra data structure use nahi ki,
sirf pointers/variables use hue
*/
