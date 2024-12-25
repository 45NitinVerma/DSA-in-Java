// Approach 2: Using Two Pointers
package Array.Union_Of_Array;
import java.util.*;
public class Approach2 {
    // Function to find the union of two sorted arrays
    static ArrayList<Integer> findUnion(int arr1[], int arr2[], int n, int m) {
        int i = 0, j = 0; // Pointers for traversing arr1 and arr2
        ArrayList<Integer> union = new ArrayList<>(); // To store the union of arrays

        // Step 1: Traverse both arrays and merge unique elements
        while (i < n && j < m) {
            // Case 1: arr1[i] <= arr2[j]
            if (arr1[i] <= arr2[j]) {
                // Add to union if it's not already present (avoid duplicates)
                if (union.size() == 0 || union.get(union.size() - 1) != arr1[i]) {
                    union.add(arr1[i]);
                }
                i++;
            } 
            // Case 2: arr1[i] > arr2[j]
            else {
                if (union.size() == 0 || union.get(union.size() - 1) != arr2[j]) {
                    union.add(arr2[j]);
                }
                j++;
            }
        }

        // Step 2: Add remaining elements of arr1 (if any)
        while (i < n) {
            if (union.get(union.size() - 1) != arr1[i]) {
                union.add(arr1[i]);
            }
            i++;
        }

        // Step 3: Add remaining elements of arr2 (if any)
        while (j < m) {
            if (union.get(union.size() - 1) != arr2[j]) {
                union.add(arr2[j]);
            }
            j++;
        }

        return union; // Return the result
    }

    public static void main(String args[]) {
        // Input arrays
        int arr1[] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int arr2[] = {2, 3, 4, 4, 5, 11, 12};

        // Call the function to find the union
        ArrayList<Integer> union = findUnion(arr1, arr2, arr1.length, arr2.length);

        // Print the result
        System.out.println("Union of arr1 and arr2 is:");
        for (int val : union) {
            System.out.print(val + " ");
        }
        System.out.println();
    }
}

/*
Algorithm Explanation:
1. Initialize two pointers i and j at the start of arr1 and arr2 respectively.
2. Traverse both arrays:
   - If arr1[i] <= arr2[j], add arr1[i] to the union if it is not a duplicate.
   - If arr1[i] > arr2[j], add arr2[j] to the union if it is not a duplicate.
3. After traversing one array completely, add the remaining elements of the other array to the union.
4. Return the final list containing unique elements in sorted order.

Time Complexity Analysis:
- Main while loop: O(min(n,m))
- Remaining elements processing: O(max(n,m))
- Overall Time Complexity: O(n + m)

Space Complexity Analysis:
1. Auxiliary Space:
   - ArrayList for union: O(n + m) in worst case
   - Few variables (i, j): O(1)
   
2. Input Space: O(n + m)

Total Space Complexity: O(n + m)
*/