package ApnaCollege.BinaryTree.DiameterOfTree;

public class Approach1 {
    static class Node {
        int data;
        Node left, right;

        public Node(int data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    public static int height(Node root) {
        if(root == null) {
            return 0;
        }

        return Math.max(height(root.left), height(root.right)) + 1;
    }

    //Approach-1
    public static int diameter1(Node root) {
        if(root == null) {
            return 0;
        }

        int diamRoot = height(root.left) + height(root.right) + 1;
        int diamLeft = diameter1(root.left);
        int diamRight = diameter1(root.right);

        return Math.max(diamRoot, Math.max(diamLeft, diamRight));
    }  

    public static void main(String args[]) {
        /*
                    1
                   / \
                  2   3
                 / \   \
                4   5   6  
        */
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.right = new Node(6);

        System.out.println(diameter1(root));
    }
}
