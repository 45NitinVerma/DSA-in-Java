package Graph;

import java.util.*;

// ---------------- Solution Class ----------------
class DfsSolution {

    // Function to perform Depth First Search (DFS)
    public void dfs(int v, List<Integer>[] adj, boolean[] visited, List<Integer> result) {
        
        // Step 1: Mark current node as visited
        visited[v] = true;

        // Step 2: Add this node to result list
        result.add(v);

        // Step 3: Visit all unvisited adjacent nodes (neighbors)
        for (int u : adj[v]) {
            if (!visited[u]) {
                dfs(u, adj, visited, result);  // Recursive DFS call
            }
        }
    }
}

// ---------------- Main Class ----------------
public class DFS {
    public static void main(String[] args) {

        // Number of vertices
        int V = 5;

        // Step 1: Create adjacency list for the graph
        List<Integer>[] adj = new ArrayList[V];
        for (int i = 0; i < V; i++) {
            adj[i] = new ArrayList<>();
        }

        /*
            Graph Structure:
                0
               / \
              1   2
              |    \
              3     4

            Edges:
            0 - 1, 0 - 2, 1 - 3, 2 - 4
        */

        adj[0].addAll(Arrays.asList(1, 2));
        adj[1].addAll(Arrays.asList(0, 3));
        adj[2].addAll(Arrays.asList(0, 4));
        adj[3].add(1);
        adj[4].add(2);

        // Step 2: Create visited array to track visited nodes
        boolean[] visited = new boolean[V];

        // Step 3: List to store DFS traversal order
        List<Integer> result = new ArrayList<>();

        // Step 4: Create Solution object and call DFS
        DfsSolution sol = new DfsSolution();

        // Run DFS starting from node 0
        sol.dfs(0, adj, visited, result);

        // Step 5: Print DFS Traversal Result
        System.out.println("DFS Traversal starting from node 0:");
        for (int node : result) {
            System.out.print(node + " ");
        }
        System.out.println();
    }
}

/*
--------------------------------------------
🧠 EXPLANATION:

DFS (Depth First Search):
→ Ek graph traversal technique hai jisme hum ek node se
   start karte hain aur uske adjacent (connected) nodes
   ko depth tak visit karte hain, backtrack karte hue
   sab nodes cover karte hain.

🔹 Steps:
   1️⃣ Start from a node (source)
   2️⃣ Mark it as visited
   3️⃣ Visit all unvisited neighbours recursively

Traversal Order (for this graph):
0 → 1 → 3 → 2 → 4

--------------------------------------------
⏱️ Time Complexity: O(V + E)
   - V = number of vertices (nodes)
   - E = number of edges
   - Because we visit every vertex and edge once.

💾 Space Complexity: O(V)
   - For visited[] array and recursion stack in worst case.
--------------------------------------------
*/

/* 
 DFS Traversal starting from node 0:
    0 1 3 2 4

 */