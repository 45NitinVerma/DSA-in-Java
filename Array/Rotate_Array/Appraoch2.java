// Approach 2: Optimised Approach using Reversal Algorithm
package Array.Rotate_Array;
public class Appraoch2 {

    // Reversal helper function
    static void reverse(int arr[], int start, int end) {
        while (start <= end) {
            // Swap elements at start and end
            int temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;
            start++;
            end--;
        }
    }
    static void leftRotate(int arr[], int n, int d) {
        if (n == 0) return;
        
        // Handle large rotations similar to previous approach
        d = d % n;
        
        // Three step reversal algorithm:
        // 1. Reverse first d elements
        reverse(arr, 0, d - 1);
        
        // 2. Reverse remaining n-d elements
        reverse(arr, d, n - 1);
        
        // 3. Reverse entire array
        reverse(arr, 0, n - 1);
    }
    
    public static void main(String[] args) {
        int arr[] = {1, 2, 3, 4, 5, 6, 7};
        int n = arr.length;
        int d = 3;
        System.out.println("Original array:");
        for (int i = 0; i < n; i++) {
            System.out.print(arr[i] + " ");
        }
        
        leftRotate(arr, n, d);
        
        System.out.println("\nArray after rotating " + d + " positions:");
        for (int i = 0; i < n; i++) {
            System.out.print(arr[i] + " ");
        }
    }
}
/*
Time Complexity Analysis:
- Modulo operation: O(1)
- Three reverse operations: O(d) + O(n-d) + O(n) = O(n)
- Overall Time Complexity: O(n)

Space Complexity Analysis:
- Only uses a single temp variable for swapping
- Overall Space Complexity: O(1)

Note: This is more space-efficient than the previous O(d) 
approach that used a temporary array
*/