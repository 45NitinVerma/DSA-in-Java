import java.util.*;
public class M_Coloring {
    public static boolean graphColoring(List < Integer > [] G, int[] color, int i, int C) {
        // Initialize the graph length
        int n = G.length;
        // Start solving the coloring problem from vertex i
        if (solve(i, G, color, n, C) == true) return true;
        return false;
    }

    // Function to check if it's safe to color the current vertex with the given color
    private static boolean isSafe(int node, List < Integer > [] G, int[] color, int n, int col) {
        // Check all adjacent vertices of the current node
        for (int it: G[node]) {
            // If an adjacent vertex has the same color, return false
            if (color[it] == col) return false;
        }
        return true;
    }

    // Recursive utility function to solve the M-coloring problem
    private static boolean solve(int node, List < Integer > [] G, int[] color, int n, int m) {
        // Base case: if all vertices are colored, return true
        if (node == n) return true;

        // Try assigning each color from 1 to m to the current vertex
        for (int i = 1; i <= m; i++) {
            // Check if it's safe to color the current vertex with color i
            if (isSafe(node, G, color, n, i)) {
                // Assign color i to the current vertex
                color[node] = i;
                // Recursively color the next vertex
                if (solve(node + 1, G, color, n, m) == true) return true;
                // If coloring fails, backtrack by resetting the color of the current vertex
                color[node] = 0;
            }
        }
        return false;  // No valid color assignment found
    }

    public static void main(String[] args) {
        // Example test case: N is the number of vertices, M is the number of colors
        int N = 4, M = 3;
        @SuppressWarnings("unchecked")
        List < Integer > [] G = (ArrayList < Integer >[]) new ArrayList[N];  // Cast to List<Integer>[]
        for (int i = 0; i < N; i++) {
            G[i] = new ArrayList < > ();
        }
        // Manually adding edges to the graph
        G[0].add(1);
        G[1].add(0);
        G[1].add(2);
        G[2].add(1);
        G[2].add(3);
        G[3].add(2);
        G[3].add(0);
        G[0].add(3);
        G[0].add(2);
        G[2].add(0);

        // Array to store colors assigned to each vertex
        int[] color = new int[N];

        // Call the graphColoring function and store the result
        boolean ans = graphColoring(G, color, 0, M);

        // Output the result: 1 if possible to color, 0 if not possible
        if (ans == true)
            System.out.println("1");
        else
            System.out.println("0");
    }
}

/*
Time Complexity:
- The time complexity of the backtracking algorithm is O(m^n), where:
    - `m` is the number of colors available.
    - `n` is the number of vertices in the graph.
- In the worst case, for every vertex, we try all `m` colors, leading to a total of `m * m * ... * m` (n times), or O(m^n).
- This makes the solution exponential in terms of time complexity.

Space Complexity:
- The space complexity is O(n) where:
    - `n` is the number of vertices, for storing the `color` array of size `n` (to store the color assigned to each vertex).
    - Additional space is used for the recursive call stack, which at most can go up to `n` in depth (one recursive call for each vertex).
- Thus, the overall space complexity is O(n).
*/