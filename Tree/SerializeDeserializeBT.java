package Tree;

import java.util.*;
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int val) {
        this.val = val;
        this.left = null;
        this.right = null;
    }
}

public class SerializeDeserializeBT {

    /* ---------- SERIALIZE ---------- */
    // Converts Binary Tree to String
    public static String serialize(TreeNode root) {

        // If tree is empty
        if (root == null)
            return "";

        StringBuilder sb = new StringBuilder();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        // Level Order Traversal
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();

            if (node == null) {
                sb.append("null,");
                continue;
            }

            sb.append(node.val).append(",");
            queue.add(node.left);
            queue.add(node.right);
        }

        return sb.toString();
    }

    /* ---------- DESERIALIZE ---------- */
    // Converts String back to Binary Tree
    public static TreeNode deserialize(String data) {

        // If string is empty
        if (data == null || data.length() == 0)
            return null;

        String[] values = data.split(",");
        TreeNode root = new TreeNode(Integer.parseInt(values[0]));

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        int i = 1;

        while (!queue.isEmpty()) {
            TreeNode current = queue.poll();

            // Left child
            if (!values[i].equals("null")) {
                current.left = new TreeNode(Integer.parseInt(values[i]));
                queue.add(current.left);
            }
            i++;

            // Right child
            if (!values[i].equals("null")) {
                current.right = new TreeNode(Integer.parseInt(values[i]));
                queue.add(current.right);
            }
            i++;
        }

        return root;
    }

    /* ---------- Inorder Traversal (for checking) ---------- */
    public static void inorder(TreeNode root) {
        if (root == null)
            return;
        inorder(root.left);
        System.out.print(root.val + " ");
        inorder(root.right);
    }

    /* ---------- Main ---------- */
    public static void main(String[] args) {

        /*
                Original Tree:

                        1
                       / \
                      2   3
                         / \
                        4   5
        */

        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(5);

        // Serialize
        String data = serialize(root);
        System.out.println("Serialized Tree:");
        System.out.println(data);

        // Deserialize
        TreeNode newRoot = deserialize(data);

        System.out.println("Inorder Traversal after Deserialization:");
        inorder(newRoot);
    }
}

/*
--------------------------------------------
🧠 EXPLANATION (Hinglish):

🔹 Serialize:
- Level order traversal (BFS) use karte hain
- Node value store karte hain
- Null nodes ko "null" likhte hain
- Comma se separate kar dete hain

Example Serialized String:
1,2,3,null,null,4,5,null,null,null,null,

🔹 Deserialize:
- String ko split karte hain
- First value se root banta hai
- Queue se nodes nikal kar left & right attach karte hain
- "null" ka matlab child exist nahi karta

--------------------------------------------
⏱️ Time Complexity:
- Serialize → O(N)
- Deserialize → O(N)

💾 Space Complexity:
- Serialize → O(N)
- Deserialize → O(N)

N = total number of nodes in the tree
--------------------------------------------
*/
