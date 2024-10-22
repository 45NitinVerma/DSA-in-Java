// Approach 2: Truncating 4 "if statements" to single "for loop"
package Rat_in_maze;
import java.util.*;
public class Approach2 {
    private static void solve(int i, int j, int[][] a, int n, ArrayList<String> ans,
            String move, int[][] vis, int[] di, int[] dj) {
        // Base case: reached destination
        if (i == n - 1 && j == n - 1) {
            ans.add(move);
            return;
        }

        // Direction string corresponding to di[] and dj[] movements
        String dir = "DLRU";

        // Try all possible directions
        for (int ind = 0; ind < 4; ind++) {
            int nexti = i + di[ind];
            int nextj = j + dj[ind];

            // Check if the next position is valid and unvisited
            if (nexti >= 0 && nextj >= 0 && nexti < n && nextj < n &&
                    vis[nexti][nextj] == 0 && a[nexti][nextj] == 1) {

                // Mark current cell as visited
                vis[i][j] = 1;

                // Recursive call for next position
                solve(nexti, nextj, a, n, ans, move + dir.charAt(ind), vis, di, dj);

                // Backtrack: mark current cell as unvisited
                vis[i][j] = 0;
            }
        }
    }

    public static ArrayList<String> findPath(int[][] m, int n) {
        // Initialize visited array
        int[][] vis = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                vis[i][j] = 0;
            }
        }

        // Direction arrays
        // di[0], dj[0] = Down (+1, 0)
        // di[1], dj[1] = Left (0, -1)
        // di[2], dj[2] = Right (0, +1)
        // di[3], dj[3] = Up (-1, 0)
        int[] di = { +1, 0, 0, -1 };
        int[] dj = { 0, -1, +1, 0 };

        // Store all possible paths
        ArrayList<String> ans = new ArrayList<>();

        // Start DFS if starting cell is valid
        if (m[0][0] == 1) {
            solve(0, 0, m, n, ans, "", vis, di, dj);
        }

        return ans;
    }

    public static void main(String[] args) {
        // Example usage
        int n = 4;
        int[][] maze = {
                { 1, 0, 0, 0 },
                { 1, 1, 0, 1 },
                { 1, 1, 0, 0 },
                { 0, 1, 1, 1 }
        };

        ArrayList<String> paths = findPath(maze, n);

        if (paths.isEmpty()) {
            System.out.println("-1");
        } else {
            for (String path : paths) {
                System.out.print(path + " ");
            }
            System.out.println();
        }
    }
}
    /*
    Time Complexity Analysis:
    O(4^(N*N)), where N is the size of the maze
    
    Explanation:
    1. At each cell, we have up to 4 choices (Down, Left, Right, Up)
    2. The maximum path length can be N*N (visiting all cells)
    3. Therefore, in worst case, we might explore 4 possibilities for each cell
    4. However, actual time is usually much less due to:
       - Blocked cells (value 0)
       - Visited cell checks
       - Finding destination before exploring all paths
       - Wall boundaries
    
    Space Complexity Analysis:
    O(N*N), where N is the size of the maze
    
    Breakdown:
    1. Visited Array: O(N*N)
       - We use a N×N boolean array to track visited cells
    
    2. Recursion Stack: O(N*N)
       - Maximum depth of recursion is N*N (when we visit all cells)
       - Each recursive call stores constant extra space (i, j, move string)
    
    3. Direction Arrays: O(1)
       - di[] and dj[] arrays are constant size (4)
    
    4. Result ArrayList: O(L * P) [not counted in auxiliary space]
       - L = length of each path
       - P = number of possible paths
       - This is part of the output, so not counted in auxiliary space
    
    5. String Creation: O(N*N) for each path
       - Each path can be at most N*N length
       - Created during backtracking
       - New string at each recursive call
    
    Note: The actual space used is often less than worst case due to:
    - Early termination on finding valid paths
    - Blocked cells preventing deep recursion
    - Limited number of valid paths in typical mazes
    */
