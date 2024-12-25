// Approach 1: Brute Force Appraoch
package Array.Rotate_Array;
public class Approach1 {
    static void leftRotate(int arr[], int n, int d) {
        if (n == 0) return;
        
        d = d % n; // Get the effective number of rotations

        // checking if d is a multiple of n:
        if (d == 0)
            return;

        int[] temp = new int[d]; // temporary array

        // step 1: Copying first d elements in the temporary array:
        for (int i = 0; i < d; i++) {
            temp[i] = arr[i];
        }

        // step 2: Shift last (n-d) elements to the left by d places 
        for (int i = d; i < n; i++) {
            arr[i - d] = arr[i];
        }

        // step 3: Place the elements of the temporary array in the last d places
        for (int i = n - d; i < n; i++) {
            arr[i] = temp[i - (n - d)];
        }
    }

    public static void main(String args[]) {
        int n = 7;
        int arr[] = { 1, 2, 3, 4, 5, 6, 7 };
        int d = 3;
        System.out.println("Before rotation:");
        for (int i = 0; i < n; i++)
            System.out.print(arr[i] + " ");
        System.out.println();

        leftRotate(arr, n, d);
        System.out.println("After rotation:");
        for (int i = 0; i < n; i++)
            System.out.print(arr[i] + " ");
        System.out.println();
    }
}
/*
Time Complexity Analysis:
- Modulo operation for d: O(1)
- Copying first d elements to temp array: O(d)
- Shifting elements left by d positions: O(n-d)
- Placing temp elements back: O(d)
- Overall Time Complexity: O(n) where n is array length

Space Complexity Analysis:

1. Auxiliary Space (Temporary/Extra Space):
   - Temporary array of size d: O(d)
   - Few integer variables (i, n, d): O(1)
   - Total Auxiliary Space: O(d)

2. Permanent Space (Input Space):
   - Input array of size n: O(n)
   - Total Permanent Space: O(n)

Total Space Complexity: O(n + d)
- In worst case, d can be n-1, making it O(2n) ≈ O(n)
- In best case, d=1, making it O(n + 1) ≈ O(n)
*/