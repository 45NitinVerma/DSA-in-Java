package Tree;

// Ceil and Floor in a Binary Search Tree (BST)
// Demonstrates: Brute Force, Better, and Optimal Approaches

class TreeNode {
    int val;
    TreeNode left, right;

    TreeNode(int val) {
        this.val = val;
        left = right = null;
    }
}

public class CeilAndFloorInBST {

    /*--------------------------------------------------
      BASIC APPROACH (Brute Force - Inorder Traversal)
      --------------------------------------------------
      Idea:
      - Perform inorder traversal (sorted order).
      - Store all values.
      - Iterate through array to find ceil & floor.

      Time Complexity: O(N)
      Space Complexity: O(N)
    --------------------------------------------------*/

    static int ceil = -1, floor = -1;

    public static void findCeilFloor_Basic(TreeNode root, int key) {
        ceil = -1;
        floor = -1;
        inorder(root, key);
    }

    private static void inorder(TreeNode node, int key) {
        if (node == null) return;

        inorder(node.left, key);

        if (node.val >= key && ceil == -1)
            ceil = node.val;

        if (node.val <= key)
            floor = node.val;

        inorder(node.right, key);
    }

    /*--------------------------------------------------
      BETTER APPROACH (Recursive BST Property)
      --------------------------------------------------
      Idea:
      - Use BST properties:
        If node.val == key → ceil = floor = key
        If node.val > key → possible ceil, move left
        If node.val < key → possible floor, move right

      Time Complexity: O(H)
      Space Complexity: O(H) (recursion stack)
    --------------------------------------------------*/

    public static void findCeilFloor_Better(TreeNode root, int key) {
        ceil = -1;
        floor = -1;
        helper(root, key);
    }

    private static void helper(TreeNode node, int key) {
        if (node == null) return;

        if (node.val == key) {
            ceil = floor = node.val;
            return;
        }

        if (node.val > key) {
            ceil = node.val;
            helper(node.left, key);
        } else {
            floor = node.val;
            helper(node.right, key);
        }
    }

    /*--------------------------------------------------
      OPTIMAL APPROACH (Iterative BST Traversal)
      --------------------------------------------------
      Idea:
      - Traverse BST without recursion.
      - Use BST rules to move left/right.
      - Update ceil and floor on the go.

      Time Complexity: O(H)
      Space Complexity: O(1)
    --------------------------------------------------*/

    public static void findCeilFloor_Optimal(TreeNode root, int key) {
        ceil = -1;
        floor = -1;

        TreeNode curr = root;

        while (curr != null) {
            if (curr.val == key) {
                ceil = floor = curr.val;
                return;
            }

            if (curr.val > key) {
                ceil = curr.val;
                curr = curr.left;
            } else {
                floor = curr.val;
                curr = curr.right;
            }
        }
    }

    // Main method for testing
    public static void main(String[] args) {

        /*
              BST:
                    8
                  /   \
                 4     12
                / \    / \
               2   6  10  14

            key = 5
            Floor = 4
            Ceil  = 6
        */

        TreeNode root = new TreeNode(8);
        root.left = new TreeNode(4);
        root.right = new TreeNode(12);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(6);
        root.right.left = new TreeNode(10);
        root.right.right = new TreeNode(14);

        int key = 5;

        // Choose any approach to test
        findCeilFloor_Optimal(root, key);

        System.out.println("Key: " + key);
        System.out.println("Floor: " + floor);
        System.out.println("Ceil : " + ceil);
    }
}

/*
--------------------------------------------------
🧠 INTUITION SUMMARY (HINGLISH):

- Floor = greatest value ≤ key
- Ceil  = smallest value ≥ key

- Brute force me inorder traversal karke
  values collect karte hain.
- Better approach me BST property use karte
  hain with recursion.
- Optimal approach me same BST logic ko
  iterative banake O(1) space achieve karte hain.

--------------------------------------------------
⏱️ TIME COMPLEXITY:
- Brute:    O(N)
- Better:   O(H)
- Optimal:  O(H)

💾 SPACE COMPLEXITY:
- Brute:    O(N)
- Better:   O(H)
- Optimal:  O(1)

(H = height of BST)
--------------------------------------------------
*/

