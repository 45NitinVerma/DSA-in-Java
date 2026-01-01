package Tree;

class TreeNode {
    int val;
    TreeNode left, right;

    TreeNode(int val) {
        this.val = val;
        this.left = null;
        this.right = null;
    }
}

public class ChildrenSumProperty {

    public static void changeTree(TreeNode root) {
        // Base case
        if (root == null) return;

        // Step 1: Calculate children sum
        int child = 0;
        if (root.left != null) child += root.left.val;
        if (root.right != null) child += root.right.val;

        // Step 2: Fix current node based on children sum
        if (child >= root.val) {
            root.val = child;
        } else {
            // Push parent value downward
            if (root.left != null) {
                root.left.val = root.val;
            } else if (root.right != null) {
                root.right.val = root.val;
            }
        }

        // Step 3: Recurse into left & right subtrees
        changeTree(root.left);
        changeTree(root.right);

        // Step 4: After recursion, update node to sum of children
        int total = 0;
        if (root.left != null) total += root.left.val;
        if (root.right != null) total += root.right.val;

        // Only update if node has at least one child
        if (root.left != null || root.right != null) {
            root.val = total;
        }
    }

    // Utility inorder traversal
    public static void inorder(TreeNode root) {
        if (root == null) return;
        inorder(root.left);
        System.out.print(root.val + " ");
        inorder(root.right);
    }

    public static void main(String[] args) {
        
        /*
               50
             /    \
            7      2
           / \    /
          3   5  1
        */

        TreeNode root = new TreeNode(50);
        root.left = new TreeNode(7);
        root.right = new TreeNode(2);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(1);

        changeTree(root);

        System.out.println("Inorder after Children Sum Property:");
        inorder(root);
    }
}
