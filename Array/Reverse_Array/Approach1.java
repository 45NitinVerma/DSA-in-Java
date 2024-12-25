// Approach1: Using two pointers
package Array.Reverse_Array;
import java.util.Arrays;
public class Approach1 {
    public static void reverseArray(int[] arr) {
        int start = 0;
        int end = arr.length - 1;
        while (start < end) {
            // Swap elements
            int temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;
            start++;
            end--;
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
- Only need to traverse half the array

Space Complexity: O(1)
- In-place reversal using only a temp variable
- No extra space needed proportional to input size

Algorithm Explanation:
1. Use two pointers: start (from beginning) and end (from last)
2. Swap elements at start and end positions
3. Move start pointer forward and end pointer backward
4. Continue until start >= end
*/