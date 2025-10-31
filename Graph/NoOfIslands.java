package Graph;
import java.util.*;

// 🌊 Solution class to count the number of islands using BFS
class Solution {

    /**
     * Helper function to perform BFS traversal
     * starting from a given cell (row, col).
     */
    public void bfs(int row, int col, boolean[][] visited, char[][] grid) {
        // Queue to perform BFS
        Queue<int[]> queue = new LinkedList<>();
        
        // Add the starting cell and mark as visited
        queue.add(new int[]{row, col});
        visited[row][col] = true;

        // Possible 8 directions (N, NE, E, SE, S, SW, W, NW)
        int[] dRow = {-1, -1, -1, 0, 1, 1, 1, 0};
        int[] dCol = {-1, 0, 1, 1, 1, 0, -1, -1};

        // Process all connected land cells
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int r = current[0];
            int c = current[1];

            // Explore all 8 directions
            for (int i = 0; i < 8; i++) {
                int neighbourR = r + dRow[i];
                int neighbourC = c + dCol[i];

                // Check bounds and if the cell is unvisited land
                if (neighbourR >= 0 && neighbourR < grid.length &&
                    neighbourC >= 0 && neighbourC < grid[0].length &&
                    !visited[neighbourR][neighbourC] && grid[neighbourR][neighbourC] == '1') {
                    
                    // Mark the cell as visited and add to queue
                    visited[neighbourR][neighbourC] = true;
                    queue.add(new int[]{neighbourR, neighbourC});
                }
            }
        }
    }

    /**
     * Main function to count the number of islands in a 2D grid.
     * Island = group of connected '1's (land) surrounded by '0's (water).
     */
    public int numIslands(char[][] grid) {
        int n = grid.length;        // total rows
        int m = grid[0].length;     // total columns

        boolean[][] visited = new boolean[n][m]; // to track visited cells
        int islandCount = 0;                     // number of islands

        // Traverse each cell of the grid
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {

                // If the cell is land ('1') and not yet visited
                if (grid[i][j] == '1' && !visited[i][j]) {
                    islandCount++;          // Found a new island
                    bfs(i, j, visited, grid); // Explore the entire island
                }
            }
        }

        return islandCount;
    }
}

// 🌍 Main class
public class NoOfIslands {
    public static void main(String[] args) {

        /*
            Input grid representation:

            1 1 0 0 0
            1 1 0 0 0
            0 0 1 0 0
            0 0 0 1 1

            ✅ Here:
            - There are 3 separate islands:
                1️⃣ group at top-left
                2️⃣ single cell in middle
                3️⃣ group at bottom-right
        */

        // Create grid
        char[][] grid = {
            {'1','1','0','0','0'},
            {'1','1','0','0','0'},
            {'0','0','1','0','0'},
            {'0','0','0','1','1'}
        };

        // Create Solution object
        Solution solution = new Solution();

        // Compute and print the number of islands
        int result = solution.numIslands(grid);
        System.out.println("Number of Islands: " + result);
    }
}

/*
--------------------------------------------
🧠 EXPLANATION:

We treat the grid like a graph:
- Each cell = node
- Adjacent land cells = connected edges

We perform BFS whenever we find an unvisited land cell ('1').
That BFS call explores the entire connected region (island),
marking all its parts as visited so it’s not counted again.

So each BFS call = 1 island discovered.

--------------------------------------------
Example Grid:

1 1 0 0 0
1 1 0 0 0
0 0 1 0 0
0 0 0 1 1

Output:
Number of Islands: 3

--------------------------------------------
⏱️ Time Complexity:  O(N × M)
   → We visit every cell once at most.

💾 Space Complexity: O(N × M)
   → Visited array + BFS queue in the worst case.
--------------------------------------------
*/

/* 
 Number of Islands: 3
 */