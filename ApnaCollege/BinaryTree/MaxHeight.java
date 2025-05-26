package ApnaCollege.BinaryTree;

public class MaxHeight {
    static class Node {
        int data;
        Node left, right;

        public Node(int item) {
            data = item;
            left = right = null;
        }
    }

    static class BinaryTree {
        // Function to build a binary tree from preorder traversal
        static int idx = -1;

        public Node buildTree(int[] nodes) {
            idx++;
            if (nodes[idx] == -1) {
                return null;
            }
            Node newNode = new Node(nodes[idx]);
            newNode.left = buildTree(nodes);
            newNode.right = buildTree(nodes);

            return newNode;
        }

        // Function to find the maximum height of the binary tree
        public int maxHeight(Node root) {
            if (root == null) {
                return 0;
            }
            int leftHeight = maxHeight(root.left);
            int rightHeight = maxHeight(root.right);
            return Math.max(leftHeight, rightHeight) + 1;
        }
    }
    public static void main(String[] args) {
        int nodes[] = {1, 2, 4, -1, -1, 5, -1, -1, 3, -1, 6, -1, -1};
        BinaryTree tree = new BinaryTree();
        Node root = tree.buildTree(nodes);
        System.out.println("Max Height of the tree is: " + tree.maxHeight(root));
    }
}
