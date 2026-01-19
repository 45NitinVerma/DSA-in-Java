package Binary_Search.Sqrt;
/* 
Sqrt(x)
# Problem: Given a non-negative integer x, return the square root of x rounded down to the nearest integer. The returned integer should be non-negative as well.
You must not use any built-in exponent function or operator.
 */
public class Approach1 {
    // Approach 1: Using linear search
    public static int floorSqrt(int n) {
        int ans = 0;
        // linear search on the answer space
        for (long i = 1; i <= n; i++) {
            long val = i * i;
            if (val <= (long) n) {
                ans = (int) i;
            } else {
                break;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int n = 28;
        int ans = floorSqrt(n);
        System.out.println("The floor of square root of " + n + " is: " + ans);
    }
}
/* 
# Complexity Analysis:
1. Time Complexity: O(sqrt(n))
   - The loop runs until the square of i exceeds n, which is approximately sqrt(n) iterations.
2. Space Complexity: O(1)
   - Only a constant amount of space is used for variables.
 */