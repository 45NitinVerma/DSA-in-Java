package Tree;
import java.util.*;

// Definition for a binary tree node.
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int val) {
        this.val = val;
    }
}

public class AllNodesDistanceK {

    // -------------------------------------------------
    // Step 1: Map each node to its parent
    // -------------------------------------------------
    static void markParents(TreeNode root, Map<TreeNode, TreeNode> parentMap) {
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);

        while (!q.isEmpty()) {
            TreeNode curr = q.poll();

            if (curr.left != null) {
                parentMap.put(curr.left, curr);
                q.add(curr.left);
            }

            if (curr.right != null) {
                parentMap.put(curr.right, curr);
                q.add(curr.right);
            }
        }
    }

    // -------------------------------------------------
    // Step 2: BFS from target node
    // -------------------------------------------------
    static List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        Map<TreeNode, TreeNode> parentMap = new HashMap<>();
        markParents(root, parentMap);

        Queue<TreeNode> q = new LinkedList<>();
        Set<TreeNode> visited = new HashSet<>();

        q.add(target);
        visited.add(target);

        int currentDistance = 0;

        while (!q.isEmpty()) {
            int size = q.size();

            // If we reached distance K, stop BFS
            if (currentDistance == k) break;

            currentDistance++;

            for (int i = 0; i < size; i++) {
                TreeNode node = q.poll();

                // Left child
                if (node.left != null && !visited.contains(node.left)) {
                    visited.add(node.left);
                    q.add(node.left);
                }

                // Right child
                if (node.right != null && !visited.contains(node.right)) {
                    visited.add(node.right);
                    q.add(node.right);
                }

                // Parent
                if (parentMap.containsKey(node) &&
                        !visited.contains(parentMap.get(node))) {
                    visited.add(parentMap.get(node));
                    q.add(parentMap.get(node));
                }
            }
        }

        // All nodes currently in queue are at distance K
        List<Integer> result = new ArrayList<>();
        while (!q.isEmpty()) {
            result.add(q.poll().val);
        }

        return result;
    }

    // -------------------------------------------------
    // MAIN
    // -------------------------------------------------
    public static void main(String[] args) {

        /*
                  3
                 / \
                5   1
               / \ / \
              6  2 0  8
                / \
               7   4

        Target = 5, K = 2
        Output = [7, 4, 1]
        */

        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(5);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(6);
        root.left.right = new TreeNode(2);
        root.right.left = new TreeNode(0);
        root.right.right = new TreeNode(8);
        root.left.right.left = new TreeNode(7);
        root.left.right.right = new TreeNode(4);

        TreeNode target = root.left; // node with value 5
        int k = 2;

        List<Integer> ans = distanceK(root, target, k);
        System.out.println("Nodes at distance " + k + " from target: " + ans);
    }
}

/*
-------------------------------------------------------
🧠 EXPLANATION (Hinglish):

Binary tree mein sirf left/right pointer hota hai,
lekin is problem mein hume parent ki taraf bhi move karna hota hai.

STEP 1:
- Har node ka parent store kar lete hain (using HashMap).
- BFS se tree traverse karke child → parent relation banate hain.

STEP 2:
- Ab target node se BFS start karte hain.
- Har node se 3 jagah ja sakte hain:
   1) left child
   2) right child
   3) parent
- visited set use karte hain taaki infinite loop na bane.

STEP 3:
- BFS ko level-by-level chalaate hain.
- Jab distance == K ho jaye, BFS stop.
- Queue mein jo nodes bache hote hain → wahi answer.

-------------------------------------------------------
⏱️ TIME COMPLEXITY:
- O(N)
  (Each node is visited at most once)

💾 SPACE COMPLEXITY:
- O(N)
  (parent map + visited set + queue)

-------------------------------------------------------
*/
