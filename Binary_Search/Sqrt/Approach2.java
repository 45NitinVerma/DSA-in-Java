package Binary_Search.Sqrt;
/* 
Sqrt(x)
# Problem: Given a non-negative integer x, return the square root of x rounded down to the nearest integer. The returned integer should be non-negative as well.
You must not use any built-in exponent function or operator.
 */
public class Approach2 {
    // Approach 2: Using inbuilt sqrt function
    public static int floorSqrt(int n) {
        int ans = (int) Math.sqrt(n);
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
1. Time Complexity: O(1)
   - The inbuilt sqrt function computes the square root in constant time.
2. Space Complexity: O(1)
 */