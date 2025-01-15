/* 
Problem: Sort an array containing only 0s, 1s, and 2s in ascending order.

Intuition:  Since in this case there are only 3 distinct values in the array so it's easy to maintain the count of all, Like the count of 0, 1, and 2. This can be followed by overwriting the array based on the frequency(count) of the values.
*/
package Array.Sort_0_1_2;
import java.util.ArrayList;
import java.util.Arrays;

public class Approach2 {
   // Approach 2: Counting Method
   public static void sortArray(ArrayList<Integer> arr, int n) {
        // Counter variables for each number
        int cnt0 = 0, cnt1 = 0;

        // Count occurrences of each number
        for (int i = 0; i < n; i++) {
            if (arr.get(i) == 0) cnt0++;
            else if (arr.get(i) == 1) cnt1++;
            // else cnt2++;
        }

        // Replace elements in the original array in sorted order
        for (int i = 0; i < cnt0; i++) arr.set(i, 0);          // Fill 0s
        for (int i = cnt0; i < cnt0 + cnt1; i++) arr.set(i, 1); // Fill 1s
        for (int i = cnt0 + cnt1; i < n; i++) arr.set(i, 2);    // Fill 2s
    }

    public static void main(String args[]) {
        int n = 6;
        ArrayList<Integer> arr = new ArrayList<>(Arrays.asList(new Integer[] {0, 2, 1, 2, 0, 1}));
        sortArray(arr, n);
        System.out.println("After sorting:");
        for (int i = 0; i < n; i++) {
            System.out.print(arr.get(i) + " ");
        }
        System.out.println();
    }
}

/* 
ALGORITHM:
1. Initialize three counter variables cnt0, cnt1, and cnt2 to keep track of 0s, 1s, and 2s
2. Traverse the array once to count occurrences of each number
3. Overwrite the original array:
   - First cnt0 positions with 0s
   - Next cnt1 positions with 1s
   - Remaining positions with 2s

Complexity Analysis:
TIME COMPLEXITY: O(N)
- First traversal to count: O(N)
- Second traversal to replace: O(N)
- Total: O(N)

SPACE COMPLEXITY:
1. Auxiliary Space: O(1)
   - Only using three counter variables regardless of input size
2. Input Space: O(N)
   - Space for the input ArrayList

Advantages:
1. Single pass counting makes it more efficient than comparison-based sorting
2. In-place modification without requiring extra space
3. Simple implementation with minimal variables

Limitations:
1. Only works for arrays containing specifically 0s, 1s, and 2s
2. Modifies the original array (might not be desired in all cases)
3. Not stable (doesn't preserve relative order of same elements)

EXECUTION FLOW:
------------------------------------------------------------------------------------------
Step | Current State           | Variables                  | Explanation
------------------------------------------------------------------------------------------
1    | Input array             | cnt0=0, cnt1=0, cnt2=0     | Initialize counters.
2    | After counting elements | cnt0=2, cnt1=2, cnt2=2     | Count occurrences of 0, 1, 2.
3    | Replace 0s              | First 2 elements set to 0  | Write 0s to the array.
4    | Replace 1s              | Next 2 elements set to 1   | Write 1s to the array.
5    | Replace 2s              | Last 2 elements set to 2   | Write 2s to the array.
------------------------------------------------------------------------------------------

Key Observations:
1. The algorithm trades comparison operations for counting operations
2. Requires exactly two passes through the array
3. Works well for small ranges of integers (0-2 in this case)

Sample Validation:
Input: [0, 2, 1, 2, 0, 1]
Expected: [0, 0, 1, 1, 2, 2]
Output: [0, 0, 1, 1, 2, 2]

TEST CASES:
1. Input: [0, 2, 1, 2, 0, 1]
   Expected: [0, 0, 1, 1, 2, 2]
   Output: [0, 0, 1, 1, 2, 2]
2. Input: [0, 0, 0]
   Expected: [0, 0, 0]
   Output: [0, 0, 0]
3. Input: [2, 2, 1, 1, 0, 0]
   Expected: [0, 0, 1, 1, 2, 2]
   Output: [0, 0, 1, 1, 2, 2]
*/