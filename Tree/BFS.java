package Tree;
import java.util.*;

// Node structure for a Binary Tree
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    // Default constructor
    TreeNode() {}

    // Constructor with value
    TreeNode(int x) {
        this.val = x;
    }

    // Constructor with value, left and right child
    TreeNode(int x, TreeNode left, TreeNode right) {
        this.val = x;
        this.left = left;
        this.right = right;
    }
}

// BFS (Level Order Traversal) Solution
class Solution {

    // Function to perform Level Order Traversal
    public List<List<Integer>> levelOrder(TreeNode root) {

        // Final result: List of levels
        List<List<Integer>> result = new ArrayList<>();

        // Base case: if tree is empty
        if (root == null) {
            return result;
        }

        // Queue for BFS traversal
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        // Continue until all nodes are processed
        while (!queue.isEmpty()) {
            int levelSize = queue.size(); // Number of nodes in this level
            List<Integer> currentLevel = new ArrayList<>();

            // Process all nodes of current level
            for (int i = 0; i < levelSize; i++) {
                TreeNode currentNode = queue.poll(); // Remove front of queue
                currentLevel.add(currentNode.val);   // Store value

                // Add left and right children to queue (for next level)
                if (currentNode.left != null) {
                    queue.add(currentNode.left);
                }
                if (currentNode.right != null) {
                    queue.add(currentNode.right);
                }
            }

            // Add this level's list to the result
            result.add(currentLevel);
        }

        return result;
    }
}

// Main class
public class BFS {

    // Helper function to print one level
    static void printList(List<Integer> list) {
        for (int num : list) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    // Main driver function
    public static void main(String[] args) {

        /*
            Constructed Binary Tree:

                    1
                   / \
                  2   3
                 / \
                4   5
        */

        // Step 1: Create nodes
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        // Step 2: Create Solution object and call levelOrder()
        Solution solution = new Solution();
        List<List<Integer>> levels = solution.levelOrder(root);

        // Step 3: Print the result
        System.out.println("Level Order Traversal of Tree:");
        for (List<Integer> level : levels) {
            printList(level);
        }
    }
}

/*
--------------------------------------------
🧠 EXPLANATION:

We are doing a BFS (Breadth First Search):
1. Use a queue to traverse the tree level by level.
2. For every node:
   - Visit it (store its value).
   - Add its children to the queue.
3. Once a level is completely processed, move to the next.

Example Tree:
        1
       / \
      2   3
     / \
    4   5

Level Order Output:
[
  [1],
  [2, 3],
  [4, 5]
]

--------------------------------------------
⏱️ Time Complexity:  O(N)
    - Every node is visited exactly once.
    - N = total number of nodes in the tree.

💾 Space Complexity: O(N)
    - In the worst case (last level of a complete binary tree),
      the queue can hold about N/2 nodes.
--------------------------------------------
*/


/* 
 Level Order Traversal of Tree:
    1 
    2 3 
    4 5 

 */