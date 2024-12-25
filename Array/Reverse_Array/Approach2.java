// Approach2: Using one pointer
package Array.Reverse_Array;
import java.util.Arrays;
public class Approach2 {
    public static void reverseArray(int[] arr) {
        int n = arr.length;
        // Using single pointer i, we calculate its counterpart using (n-1-i)
        for (int i = 0; i < n/2; i++) {
            // Swap elements using single pointer
            int temp = arr[i];
            arr[i] = arr[n-1-i];
            arr[n-1-i] = temp;
        }
    }

    public static void main(String[] args) {
        // Test cases
        int[][] testArrays = {
            {1, 2, 3, 4, 5},           // Odd length array
            {1, 2, 3, 4},              // Even length array
            {1},                       // Single element
            {1, 2},                    // Two elements
            {}                         // Empty array
        };
        for (int[] arr : testArrays) {
            System.out.println("\nOriginal array: " + Arrays.toString(arr));
            reverseArray(arr);
            System.out.println("Reversed array: " + Arrays.toString(arr));
        }
    }
}
/*
Time Complexity: O(n/2) ≈ O(n)
Space Complexity: O(1) - in-place reversal

Algorithm Explanation:
1. Use single pointer i and calculate its opposite position using (n-1-i)
2. Only traverse until halfway point (n/2)
3. Swap elements at i and (n-1-i)
*/
