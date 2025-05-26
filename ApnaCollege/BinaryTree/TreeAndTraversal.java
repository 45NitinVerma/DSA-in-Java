package ApnaCollege.BinaryTree;
import java.util.*;

public class TreeAndTraversal {
    static class Node {
        int data;
        Node left, right;

        public Node(int item) {
            data = item;
            left = right = null;
        }
    }
    
    static class BinaryTree{
        // Function to build a binary tree from preorder traversal
        static int idx = -1;
        public Node BuildTree(int[] nodes){
            idx++;
            if(nodes[idx] == -1){
                return null;
            }
            Node newNode = new Node(nodes[idx]);
            newNode.left = BuildTree(nodes);
            newNode.right = BuildTree(nodes);

            return newNode;
        }

        public void preOrder(Node root){ // O(n)
            if(root == null){
                return;
            }
            System.out.print(root.data + " ");
            preOrder(root.left);
            preOrder(root.right);
        }

        public void inOrder(Node root){ // O(n)
            if(root == null){
                return;
            }
            inOrder(root.left);
            System.out.print(root.data + " ");
            inOrder(root.right);
        }

        public void postOrder(Node root){ // O(n)
            if(root == null){
                return;
            }
            postOrder(root.left);
            postOrder(root.right);
            System.out.print(root.data + " ");
        }

        public void levelOrder(Node root){ // O(2*n)
            if(root == null){
                return;
            }
            Queue<Node> q = new LinkedList<>();
            q.add(root);
            q.add(null);
            while(!q.isEmpty()){
                Node currNode = q.poll();
                if(currNode == null){
                    System.out.println();
                    if(q.isEmpty()){
                        break;
                    }
                    q.add(null); // Add null to mark the end of the next level
                } else {
                    System.out.print(currNode.data + " ");
                    if(currNode.left != null){
                        q.add(currNode.left);
                    }
                    if(currNode.right != null){
                        q.add(currNode.right);
                    }
                }
            }
        }
    }
    public static void main(String[] args) {
        int nodes[] = {1, 2, 4, -1, -1, 5, -1, -1, 3, -1, 6, -1, -1};
        BinaryTree tree = new BinaryTree();
        Node root = tree.BuildTree(nodes);
        System.out.println("Preorder Traversal of the constructed tree:");
        tree.preOrder(root);
        System.out.println("\nInorder Traversal of the constructed tree:");
        tree.inOrder(root);
        System.out.println("\nPostorder Traversal of the constructed tree:");
        tree.postOrder(root);
        System.out.println("\nLevel Order Traversal of the constructed tree:");
        tree.levelOrder(root);
    }
}
