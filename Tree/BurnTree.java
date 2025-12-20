package Tree;

import java.util.*;

// Tree node definition
class TreeNode {
    int val;
    TreeNode left, right;

    TreeNode(int val) {
        this.val = val;
    }
}

public class BurnTree {

    // -------------------------------------------------
    // Step 1: Map each node to its parent
    // -------------------------------------------------
    private static TreeNode markParents(TreeNode root,
                                        Map<TreeNode, TreeNode> parentMap,
                                        int target) {

        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);

        TreeNode targetNode = null;

        while (!q.isEmpty()) {
            TreeNode curr = q.poll();

            if (curr.val == target) {
                targetNode = curr;
            }

            if (curr.left != null) {
                parentMap.put(curr.left, curr);
                q.add(curr.left);
            }

            if (curr.right != null) {
                parentMap.put(curr.right, curr);
                q.add(curr.right);
            }
        }
        return targetNode;
    }

    // -------------------------------------------------
    // Step 2: BFS to burn the tree
    // -------------------------------------------------
    public static int timeToBurnTree(TreeNode root, int target) {

        Map<TreeNode, TreeNode> parentMap = new HashMap<>();
        TreeNode targetNode = markParents(root, parentMap, target);

        Queue<TreeNode> q = new LinkedList<>();
        Set<TreeNode> visited = new HashSet<>();

        q.add(targetNode);
        visited.add(targetNode);

        int time = 0;

        while (!q.isEmpty()) {
            int size = q.size();
            boolean burned = false;

            for (int i = 0; i < size; i++) {
                TreeNode curr = q.poll();

                // left child
                if (curr.left != null && !visited.contains(curr.left)) {
                    burned = true;
                    visited.add(curr.left);
                    q.add(curr.left);
                }

                // right child
                if (curr.right != null && !visited.contains(curr.right)) {
                    burned = true;
                    visited.add(curr.right);
                    q.add(curr.right);
                }

                // parent
                if (parentMap.containsKey(curr) &&
                        !visited.contains(parentMap.get(curr))) {
                    burned = true;
                    visited.add(parentMap.get(curr));
                    q.add(parentMap.get(curr));
                }
            }

            // if at least one node burned in this level
            if (burned) time++;
        }

        return time;
    }

    // -------------------------------------------------
    // MAIN
    // -------------------------------------------------
    public static void main(String[] args) {

        /*
                    1
                   / \
                  2   3
                 / \   \
                4   5   6

            Target = 5
        */

        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.right = new TreeNode(6);

        int target = 5;

        System.out.println(
                "Time to burn the tree: " +
                        timeToBurnTree(root, target)
        );
    }
}

/*
-------------------------------------------------------
🧠 EXPLANATION (HINGLISH)

Step 1: Parent Mapping
- Tree ke nodes ke paas parent pointer nahi hota
- BFS karke har node ka parent store kar lete hain

Step 2: Burning Process (BFS)
- Target node se fire start hoti hai
- Har minute fire spread hoti hai:
    → left child
    → right child
    → parent
- BFS ka har level = 1 minute
- visited set use karte hain taaki loop na bane

Example:
Target = 5

Minute 0: 5
Minute 1: 2
Minute 2: 4, 1
Minute 3: 3
Minute 4: 6

Total time = 4

-------------------------------------------------------
⏱️ TIME COMPLEXITY:
- Parent mapping BFS: O(N)
- Burning BFS: O(N)
➡ Overall: O(N)

💾 SPACE COMPLEXITY:
- Parent map + visited + queue
➡ O(N)

-------------------------------------------------------
*/
