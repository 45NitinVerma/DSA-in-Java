package Array.Next_Permutation.Approach1;
/* 
Problem: Find the next lexicographically greater permutation of an integer array.
If not possible, return the array sorted in ascending order.

Intuition: Generate all permutations using backtracking, store them in a list,
sort them lexicographically, then find the input array in this list and return
the next permutation after it.
*/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Approach1b {
    /* Approach 1(b): Generate All Permutations using swapping and 
      Find Next Permutation after sorting*/
      private static List<int[]> allPermutations = new ArrayList<>();
      public static void nextPermutation(int[] nums) {
      // Generate all permutations first
      generatePermutations(nums.clone(), 0);
      
      // Sort all permutations lexicographically
      Collections.sort(allPermutations, (a, b) -> {
          for (int i = 0; i < a.length; i++) {
              if (a[i] != b[i]) {
                  return a[i] - b[i];
              }
          }
          return 0;
      });
      
      // Find the input array in our sorted permutations
      int index = -1;
      for (int i = 0; i < allPermutations.size(); i++) {
          if (Arrays.equals(allPermutations.get(i), nums)) {
              index = i;
              break;
          }
      }
      
      // Get the next permutation or first if current is last
      int[] nextPerm = allPermutations.get(
          (index == allPermutations.size() - 1) ? 0 : index + 1
      );
      
      // Copy the result back to input array
      System.arraycopy(nextPerm, 0, nums, 0, nums.length);
  }

  private static void generatePermutations(int[] arr, int start) {
      // Base case: if we've reached the end, save this permutation
      if (start == arr.length) {
          allPermutations.add(arr.clone());
          return;
      }
      
      // Try each possible element at the current position
      for (int i = start; i < arr.length; i++) {
          // Make a choice - swap elements
          swap(arr, start, i);
          
          // Explore - generate permutations for remaining positions
          generatePermutations(arr, start + 1);
          
          // Undo the choice - backtrack
          swap(arr, start, i);
      }
  }
  
  private static void swap(int[] arr, int i, int j) {
      int temp = arr[i];
      arr[i] = arr[j];
      arr[j] = temp;
  }
  public static void main(String[] args) {
    int[] nums = {1, 3, 2}; // Input array
    System.out.println("Before: " + java.util.Arrays.toString(nums));

    nextPermutation(nums);

    System.out.println("Next Permutation is: " + java.util.Arrays.toString(nums));
}
}

/* 
ALGORITHM:
1. Generate all possible permutations using backtracking:
 - For each position, try swapping with each remaining element
 - Save each complete permutation in a list
2. Sort all permutations lexicographically
3. Find the input array in the sorted list
4. Return the next permutation in the list (or first if current is last)

Complexity Analysis:
TIME COMPLEXITY: O(n! * n)
- O(n!) to generate all permutations
- O(n! * log(n!)) for sorting permutations
- O(n! * n) for comparing arrays during sorting

SPACE COMPLEXITY:
1. Auxiliary Space: O(n! * n)
 - Storing all permutations
 - Each permutation takes O(n) space
2. Input Space: O(n)
 - Original input array storage

Advantages:
1. Guaranteed to find the correct next permutation
2. Simple to understand and implement
3. Works with duplicate elements

Limitations:
1. Very inefficient for large inputs due to generating all permutations
2. High space complexity from storing all permutations
3. Unnecessary computation of all permutations when we only need next one

Potential Improvements:
1. Implement direct next permutation finding without generating all permutations
2. Use a single list to track current smallest greater permutation
3. Add early stopping when next permutation is found
4. Use iterative approach instead of recursion

EXECUTION FLOW
----------------------------------------------------------------
Step | Current State | Variables        | Action               | Explanation
----------------------------------------------------------------
1    | [1,2,3]      | start=0         | Begin generation     | Start backtracking
2    | [1,2,3]      | perms=[[1,2,3]] | Save permutation    | First complete perm
3    | [1,3,2]      | perms=updated   | Save & backtrack    | Next permutation
4    | Sorted perms | index found     | Find input array    | Locate current perm
----------------------------------------------------------------

Key Observations:
1. Every permutation is generated exactly once
2. Sorting ensures lexicographical order
3. Memory usage grows factorially with input size

Sample Validation:
Input: [1,2,3]
All permutations: [[1,2,3], [1,3,2], [2,1,3], [2,3,1], [3,1,2], [3,2,1]]
Next permutation: [1,3,2]

Key Points:
1. Uses combination of backtracking and sorting
2. Modifies input array in-place as required
3. Handles edge case of last permutation

TEST CASES:
1. Input: [1,2,3]
 Expected: [1,3,2]
 Output: [1,3,2]

2. Input: [3,2,1]
 Expected: [1,2,3]
 Output: [1,2,3]

3. Input: [1,1,5]
 Expected: [1,5,1]
 Output: [1,5,1]
*/
