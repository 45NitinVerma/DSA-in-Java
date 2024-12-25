// Appraoch 1: Brute Force Approach 
package Array.Intersection_Of_Array;
import java.util.*;
public class Approach1 {
    public static ArrayList<Integer> intersectionOfArrays(int[] A, int[] B) {
        // Result list to store intersection elements
        ArrayList<Integer> intersection = new ArrayList<>();
        
        // Track visited elements in B to handle duplicates
        int[] visited = new int[B.length];
        
        // Iterate through each element in A
        for (int i = 0; i < A.length; i++) {
            // Search for matching element in B
            for (int j = 0; j < B.length; j++) {
                // Check if current elements match and not previously visited
                if (A[i] == B[j] && visited[j] == 0) {
                    // Add matched element to result
                    intersection.add(B[j]);
                    
                    // Mark this element as visited to avoid duplicate processing
                    visited[j] = 1;
                    
                    // Exit inner loop after finding first match
                    break;
                } 
                // Optimization for sorted arrays: 
                // If B element is greater, no need to continue searching
                else if (B[j] > A[i]) {
                    break;
                }
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
 * ALGORITHM:
 * 1. Initialize an ArrayList to store the intersection elements.
 * 2. Create a visited array to keep track of matched elements in B.
 * 3. For each element in array A:
 *    a. Iterate through array B.
 *    b. If A[i] matches B[j] and B[j] is not visited:
 *       - Add B[j] to the result.
 *       - Mark B[j] as visited.
 *       - Break the inner loop to avoid duplicate counting.
 *    c. If B[j] > A[i], break early (since B is sorted).
 * 4. Return the intersection list.
*/

/* 
Complexity Analysis:
TIME COMPLEXITY:
- Worst Case: O(n*m) 
  * n = length of array A
  * m = length of array B
  * Occurs when we need to compare each element of A with each element of B
- Best Case: O(n) 
  * Happens when elements of B are always greater than A's elements
- Average Case: O(n*m)

SPACE COMPLEXITY:
- Auxiliary Space: O(m)
  * visited array: O(m)
  * result ArrayList: O(min(n,m))
- Input Space: O(n+m)

Advantages:
1. Handles duplicates correctly
2. Maintains original order of elements
3. Uses sorting property for potential optimization
4. Simple and intuitive implementation

Limitations:
1. Quadratic time complexity in worst case
2. Requires pre-sorted input arrays for optimal performance
3. Not the most efficient for very large arrays

Potential Improvements:
1. Consider using two-pointer approach for O(n+m) time complexity
2. For unsorted arrays, consider using HashSet for O(n) time complexity

Intersection of Arrays - DETAILED WALKTHROUGH
Input Arrays:
- A = [1, 2, 3, 3, 4, 5, 6, 7]  // First input array
- B = [3, 3, 4, 4, 5, 8]         // Second input array
- visited = [0, 0, 0, 0, 0, 0]   // Tracking visited elements in array B

Detailed Step-by-Step Matching Process:
-------------------------------------------------------------------------
Index | Current A Element | Action                  | Result      | Visited
-------------------------------------------------------------------------
i=0   | A[0] = 1          | No match in B           | []          | [0,0,0,0,0,0]
       - 1 is smaller than all elements in B
       - Continue searching

i=1   | A[1] = 2          | No match in B           | []          | [0,0,0,0,0,0]
       - 2 is not found in B
       - Continue searching

i=2   | A[2] = 3          | Match found             | [3]         | [1,0,0,0,0,0]
       - Matches B[0] = 3
       - First occurrence of 3 is added
       - Mark B's first 3 as visited

i=3   | A[3] = 3          | Match found             | [3, 3]      | [1,1,0,0,0,0]
       - Matches B[1] = 3
       - Second occurrence of 3 is added
       - Mark B's second 3 as visited

i=4   | A[4] = 4          | Match found             | [3, 3, 4]   | [1,1,1,0,0,0]
       - Matches B[2] = 4
       - First occurrence of 4 is added
       - Mark B's first 4 as visited

i=5   | A[5] = 5          | Match found             | [3, 3, 4, 5] | [1,1,1,0,1,0]
       - Matches B[4] = 5
       - 5 is added to result
       - Mark B's 5 as visited

i=6-7 | A[6] = 6, A[7] = 7| No matches              | [3, 3, 4, 5] | [1,1,1,0,1,0]
       - No further matches found

-------------------------------------------------------------------------
Final Result: [3, 3, 4, 5]
*/