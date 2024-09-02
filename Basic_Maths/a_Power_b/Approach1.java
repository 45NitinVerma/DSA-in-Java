// Approach1 - Iterative Approach
package a_Power_b;
public class Approach1 {
    public static long powerIterative(int a, int b) {
        long result = 1;
        for (int i = 0; i < b; i++) {
            result *= a;
        }
        return result;
    }

    public static void main(String[] args) {
        int a = 2, b = 10;
        System.out.println("Answer: " + powerIterative(a, b));
    }
}
// TC - O(b), SC - O(1)