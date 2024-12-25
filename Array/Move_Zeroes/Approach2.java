// Approach2: Optimal approach using two pointers
package Array.Move_Zeroes;
public class Approach2 {
    public static int[] moveZeros(int n, int[] arr) {
        // Find first zero's position
        int j = -1;
        for (int i = 0; i < n; i++) {
            if (arr[i] == 0) {
                j = i;
                break;
            }
        }
        
        // If no zeros found, return original array
        if (j == -1) return arr;
        
        // Use two-pointer technique to move zeros to end
        // j points to zero position
        // i scans for non-zero elements
        for (int i = j + 1; i < n; i++) {
            if (arr[i] != 0) {
                // Swap non-zero element with zero
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                // Move j to next zero position
                j++;
            }
        }
        return arr;
    }

    public static void main(String[] args) {
        int[] arr = {1, 0, 2, 3, 2, 0, 0, 4, 5, 1};
        int n = arr.length; // Better to use array's length property
        
        System.out.println("Original array:");
        for (int i = 0; i < n; i++) {
            System.out.print(arr[i] + " ");
        }
        
        int[] result = moveZeros(n, arr);
        
        System.out.println("\nArray after moving zeros to end:");
        for (int i = 0; i < n; i++) {
            System.out.print(result[i] + " ");
        }
    }
}
/*
Time Complexity Analysis:
- First loop to find zero: O(n)
- Second loop for swapping: O(n)
- Overall Time Complexity: O(n)

Space Complexity Analysis:
1. Auxiliary Space:
- Only few variables (i, j, temp): O(1)
- No extra arrays or data structures used

2. Input Space:
- Original array: O(n)

Total Space Complexity: O(1) auxiliary + O(n) input = O(n)

Algorithm Explanation:
1. First find the position of first zero (j)
2. Use two pointers:
- j points to zero positions
- i scans array for non-zero elements
3. When i finds non-zero element, swap with j's position
4. Increment j to next zero position
5. Continue until end of array

Note: This is more efficient than the ArrayList approach
as it uses O(1) auxiliary space and does in-place swapping.
*/