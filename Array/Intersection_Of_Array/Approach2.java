// Approach 2: Two-Pointer Method
package Array.Intersection_Of_Array;
import java.util.*;
public class Approach2 {
    public static ArrayList<Integer> intersectionOfArrays(int[] A, int[] B) {
        // Result list to store intersection elements
        ArrayList<Integer> intersection = new ArrayList<>();
        
        // Initialize two pointers for both arrays
        int i = 0, j = 0;
        
        // Traverse both arrays simultaneously
        while (i < A.length && j < B.length) {
            // If A's element is smaller, move A's pointer
            if (A[i] < B[j]) {
                i++;
            }
            // If B's element is smaller, move B's pointer
            else if (B[j] < A[i]) {
                j++;
            }
            // If elements are equal, add to result and move both pointers
            else {
                intersection.add(A[i]);
                i++;
                j++;
            }
        }
        return intersection;
    }

    public static void main(String[] args) {
        // Test input arrays
        int[] A = {1, 2, 3, 3, 4, 5, 6, 7};
        int[] B = {3, 3, 4, 4, 5, 8};
        
        // Print input arrays
        System.out.println("Array A: " + Arrays.toString(A));
        System.out.println("Array B: " + Arrays.toString(B));
        
        // Find and print intersection
        ArrayList<Integer> result = intersectionOfArrays(A, B);
        System.out.println("Intersection: " + result);
    }
}

/*
 ALGORITHM:
 1. Initialize an ArrayList to store the intersection elements.
 2. Use two pointers, i for array A and j for array B.
 3. While both pointers are within array bounds:
    a. If A[i] < B[j], increment i (move in A)
    b. If B[j] < A[i], increment j (move in B)
    c. If A[i] == B[j]:
       - Add element to intersection
       - Increment both i and j
 4. Return the intersection list.
*/

/* 
Complexity Analysis:
TIME COMPLEXITY:
- Worst Case: O(n+m) 
  * n = length of array A
  * m = length of array B
  * Single pass through both arrays
- Best Case: O(n+m)
  * Always linear time complexity

SPACE COMPLEXITY:
- Auxiliary Space: O(min(n,m))
  * Result ArrayList stores intersection elements
- Input Space: O(n+m)

Advantages:
1. Linear time complexity O(n+m)
2. Space-efficient 
3. Works with sorted arrays
4. Handles duplicates correctly
5. Single pass through arrays

Limitations:
1. Requires pre-sorted input arrays
2. Modifies pointers during traversal

Potential Improvements:
1. Add input array sorting if not pre-sorted
2. Handle edge cases for empty arrays

Two-Pointer Intersection - DETAILED WALKTHROUGH
Input Arrays:
- A = [1, 2, 3, 3, 4, 5, 6, 7]  // First sorted array
- B = [3, 3, 4, 4, 5, 8]        // Second sorted array

Detailed Step-by-Step Process:
-----|---|---|------|------|-----------------------|---------------------
Step | i | j | A[i] | B[j] | Action                | Result
-----|---|---|------|------|-----------------------|---------------------
1    | 0 | 0 | 1    | 3    | A[i] < B[j], i++      | []
2    | 1 | 0 | 2    | 3    | A[i] < B[j], i++      | []
3    | 2 | 0 | 3    | 3    | Equal, add, i++, j++  | [3]
4    | 3 | 1 | 3    | 3    | Equal, add, i++, j++  | [3, 3]
5    | 4 | 2 | 4    | 4    | Equal, add, i++, j++  | [3, 3, 4]
6    | 5 | 4 | 5    | 5    | Equal, add, i++, j++  | [3, 3, 4, 5]
7    | 6 | 5 | 6    | 8    | A[i] < B[j], i++      | [3, 3, 4, 5]
8    | 7 | 5 | 7    | 8    | A[i] < B[j], i++      | [3, 3, 4, 5]
-----|---|---|------|------|-----------------------|---------------------
Final Result: [3, 3, 4, 5]
*/
