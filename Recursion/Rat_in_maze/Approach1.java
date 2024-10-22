// Approach 1: Using 4 "if statements"
package Rat_in_maze;

import java.util.ArrayList;
import java.util.Collections;

public class Approach1 {

    public static ArrayList<String> findPath(int[][] a, int n) {
        ArrayList<String> ans = new ArrayList<>();

        // If starting cell is blocked, return no path
        if (a[0][0] == 0 || a[n - 1][n - 1] == 0) {
            return ans;
        }

        boolean[][] vis = new boolean[n][n]; // to keep track of visited cells
        solve(0, 0, a, n, ans, "", vis);

        // Sort the paths in lexicographical order
        Collections.sort(ans);
        return ans;
    }

    private static void solve(int i, int j, int[][] a, int n, ArrayList<String> ans, String move, boolean[][] vis) {
        // If reached the destination
        if (i == n - 1 && j == n - 1) {
            ans.add(move);
            return;
        }

        // Mark the current cell as visited
        vis[i][j] = true;

        // Move Down
        if (isSafe(i + 1, j, a, n, vis)) {
            solve(i + 1, j, a, n, ans, move + 'D', vis);
        }

        // Move Left
        if (isSafe(i, j - 1, a, n, vis)) {
            solve(i, j - 1, a, n, ans, move + 'L', vis);
        }

        // Move Right
        if (isSafe(i, j + 1, a, n, vis)) {
            solve(i, j + 1, a, n, ans, move + 'R', vis);
        }

        // Move Up
        if (isSafe(i - 1, j, a, n, vis)) {
            solve(i - 1, j, a, n, ans, move + 'U', vis);
        }

        // Backtrack and unmark the visited cell
        vis[i][j] = false;
    }

    private static boolean isSafe(int i, int j, int[][] a, int n, boolean[][] vis) {
        return i >= 0 && j >= 0 && i < n && j < n && a[i][j] == 1 && !vis[i][j];
    }

    public static void main(String[] args) {
        int n = 4;
        int[][] a = { { 1, 0, 0, 0 },
                { 1, 1, 0, 1 },
                { 1, 1, 0, 0 },
                { 0, 1, 1, 1 } };

        ArrayList<String> res = findPath(a, n);

        if (res.size() > 0) {
            for (String path : res) {
                System.out.print(path + " ");
            }
            System.out.println();
        } else {
            System.out.println(-1);
        }
    }
}

/*
 * Time Complexity:
 * The time complexity of this solution is O(4^(n^2)), where n is the size of
 * the matrix.
 * This is because in the worst case, the rat can move in four possible
 * directions from each cell (Down, Left, Right, Up)
 * and explore every cell in the n*n matrix.
 * 
 * Space Complexity:
 * The space complexity is O(n^2) due to the additional space required for the
 * visited[][] matrix
 * and the recursion call stack, which can go up to O(n^2) in the worst case.
 * 
 */