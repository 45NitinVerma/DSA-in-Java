package Tree;

import java.util.*;

public class UniqueBSTs {

    // ===================== APPROACH 1: MEMOIZATION (TOP-DOWN) =====================
    public static int numTreesMemo(int n) {
        int[] dp = new int[n + 1];
        Arrays.fill(dp, -1);
        return solve(n, dp);
    }

    private static int solve(int n, int[] dp) {
        // base case: agar 0 ya 1 node hai -> sirf 1 BST possible
        if (n <= 1) return 1;

        // agar already calculate kiya hai to return kar do
        if (dp[n] != -1) return dp[n];

        int ans = 0;

        // har node ko root bana ke try karte hain
        for (int root = 1; root <= n; root++) {
            // left subtree me (root-1) nodes
            int left = solve(root - 1, dp);

            // right subtree me (n-root) nodes
            int right = solve(n - root, dp);

            // total combinations = left * right
            ans += left * right;
        }

        return dp[n] = ans;
    }


    // ===================== APPROACH 2: TABULATION (BOTTOM-UP) =====================
    public static int numTreesTab(int n) {
        int[] dp = new int[n + 1];

        // base case
        dp[0] = 1;
        dp[1] = 1;

        // nodes = total nodes in tree
        for (int nodes = 2; nodes <= n; nodes++) {

            // har node ko root consider karte hain
            for (int root = 1; root <= nodes; root++) {

                // left subtree nodes = root-1
                // right subtree nodes = nodes-root
                dp[nodes] += dp[root - 1] * dp[nodes - root];
            }
        }

        return dp[n];
    }


    // ===================== MAIN FUNCTION =====================
    public static void main(String[] args) {
        int n = 5;

        System.out.println("Using Memoization: " + numTreesMemo(n));
        System.out.println("Using Tabulation: " + numTreesTab(n));
    }


    /*
    ===================== INTUITION (Hinglish) =====================

    Problem: Unique BSTs count karna hai given n nodes.

    Idea:
    Har node ko root bana ke socho:
    
    Agar root = i hai:
        left subtree me (i-1) nodes honge
        right subtree me (n-i) nodes honge

    Total BST = left ways * right ways

    To total:
        sum over i = 1 to n:
            dp[i-1] * dp[n-i]

    Ye actually Catalan Number ka formula hai.

    ===================== TIME & SPACE COMPLEXITY =====================

    1. Memoization:
        Time: O(n^2)
            - har n ke liye loop chal raha hai (1 to n)
        Space: O(n) (dp array + recursion stack)

    2. Tabulation:
        Time: O(n^2)
        Space: O(n)

    ===================== NOTE =====================
    Ye problem directly Catalan Number se related hai:
        Cn = sum(Ci-1 * Cn-i)

    ===================== DRY RUN (n=3) =====================
    dp[0] = 1
    dp[1] = 1

    dp[2]:
        root=1 -> dp[0]*dp[1] = 1
        root=2 -> dp[1]*dp[0] = 1
        total = 2

    dp[3]:
        root=1 -> dp[0]*dp[2] = 2
        root=2 -> dp[1]*dp[1] = 1
        root=3 -> dp[2]*dp[0] = 2
        total = 5

    Answer = 5
    */
}
