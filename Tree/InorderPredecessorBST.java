package Tree;

/*
 Problem: Inorder Predecessor in a BST
 Inorder predecessor = node with the maximum value smaller than the given key.
*/

class Node {
    int data;
    Node left, right;

    Node(int data) {
        this.data = data;
        this.left = this.right = null;
    }
}

public class InorderPredecessorBST {

    // MAIN FUNCTION to find inorder predecessor
    public static Node inorderPredecessor(Node root, int key) {
        Node predecessor = null;

        while (root != null) {

            // Agar current node ka data key se bada ya equal hai
            // toh left side jao (kyunki predecessor chhota hoga)
            if (key <= root.data) {
                root = root.left;
            }
            // Agar current node ka data key se chhota hai
            // toh ye predecessor ho sakta hai
            else {
                predecessor = root; // possible answer
                root = root.right;  // aur bada chhota dhundhne right jao
            }
        }
        return predecessor;
    }

    // Sample MAIN method
    public static void main(String[] args) {

        /*
                20
               /  \
             10    30
               \
               15
        */

        Node root = new Node(20);
        root.left = new Node(10);
        root.right = new Node(30);
        root.left.right = new Node(15);

        int key = 15;

        Node result = inorderPredecessor(root, key);

        if (result != null)
            System.out.println("Inorder Predecessor of " + key + " is: " + result.data);
        else
            System.out.println("Inorder Predecessor does not exist");
    }
}

/*
================ INTUITION =================
BST ka property use karte hain:
- Left subtree = chhote elements
- Right subtree = bade elements

Key ke predecessor ka matlab:
- Key se chhota
- Aur sabse zyada close (maximum among smaller values)

Har node par:
1. Agar key <= node.data:
   → predecessor left side me hoga
2. Agar key > node.data:
   → ye node ek valid predecessor ho sakta hai
   → right side jaakar aur better candidate dhundhte hain

================ TIME COMPLEXITY =================
O(h)
- h = height of BST
- Balanced BST → O(log n)
- Skewed BST → O(n)

================ SPACE COMPLEXITY =================
O(1)
- Sirf variables use hue, recursion ya extra stack nahi
*/
