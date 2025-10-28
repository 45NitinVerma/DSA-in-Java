package Graph;
import java.util.*;

// ---------------- Solution Class ----------------
class Solution {

    // Function to perform Breadth First Search (BFS)
    public ArrayList<Integer> bfsOfGraph(int V, ArrayList<ArrayList<Integer>> adj) {

        // List to store BFS traversal result
        ArrayList<Integer> bfs = new ArrayList<>();

        // Visited array to mark visited nodes
        boolean[] vis = new boolean[V];

        // Queue for BFS
        Queue<Integer> q = new LinkedList<>();

        // Step 1: Start BFS from node 0
        q.add(0);
        vis[0] = true;

        // Step 2: Process nodes level by level
        while (!q.isEmpty()) {

            // Remove front node from queue
            int node = q.poll();

            // Add it to traversal result
            bfs.add(node);

            // Step 3: Visit all adjacent (neighbour) nodes
            for (int neighbour : adj.get(node)) {
                if (!vis[neighbour]) {
                    vis[neighbour] = true;   // Mark as visited
                    q.add(neighbour);        // Enqueue neighbour
                }
            }
        }

        return bfs;
    }
}

// ---------------- Main Class ----------------
public class BFS {
    public static void main(String args[]) {

        // Number of vertices
        int V = 5;

        // Step 1: Create adjacency list
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }

        /*
            Graph Structure:
                0
               / \
              1   4
             / \
            2   3

            Edges:
            0 - 1, 0 - 4, 1 - 2, 1 - 3
        */

        adj.get(0).add(1);
        adj.get(1).add(0);
        adj.get(0).add(4);
        adj.get(4).add(0);
        adj.get(1).add(2);
        adj.get(2).add(1);
        adj.get(1).add(3);
        adj.get(3).add(1);

        // Step 2: Create Solution object and call BFS
        Solution sl = new Solution();
        ArrayList<Integer> ans = sl.bfsOfGraph(V, adj);

        // Step 3: Print BFS Traversal Result
        System.out.println("BFS Traversal starting from node 0:");
        for (int node : ans) {
            System.out.print(node + " ");
        }
        System.out.println();
    }
}

/*
--------------------------------------------
🧠 EXPLANATION:

BFS (Breadth First Search):
→ Ek traversal technique jisme hum ek node se start karte hain,
  aur uske saare adjacent nodes visit karte hain level by level.

🔹 Steps:
   1️⃣ Start from a node (e.g. 0)
   2️⃣ Mark it visited and enqueue it
   3️⃣ While queue not empty:
       - Dequeue one node
       - Visit all unvisited neighbours
       - Enqueue them
   4️⃣ Repeat until all nodes are visited

Traversal Order (for this graph):
0 → 1 → 4 → 2 → 3

--------------------------------------------
⏱️ Time Complexity: O(V + E)
   - V = number of vertices
   - E = number of edges
   - Every vertex and edge is processed once.

💾 Space Complexity: O(V)
   - For visited[] array + queue storage.
--------------------------------------------
*/

/* 
 BFS Traversal starting from node 0:
    0 1 4 2 3

 */