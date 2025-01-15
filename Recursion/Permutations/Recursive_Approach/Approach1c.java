/* 
Problem: Generate all possible permutations of an array of distinct integers.

Intuition: Use backtracking with a frequency array to keep track of used elements. 
At each step, we try to place each unused number in the current position and recursively 
generate permutations for the remaining positions.
*/
package Permutations.Recursive_Approach;
import java.util.ArrayList;
import java.util.List;

public class Approach1c {
    // Approach 1 (c): Backtracking with boolean frequency array
    private static void recurPermute(int[] nums, List<Integer> currentPerm,
            List<List<Integer>> result, boolean[] used) {
        // Base case: If current permutation length equals input array length
        // we have a complete permutation
        if (currentPerm.size() == nums.length) {
            result.add(new ArrayList<>(currentPerm)); // Create deep copy and add to result
            return;
        }

        // Try each number in the current position
        for (int i = 0; i < nums.length; i++) {
            // Skip if number is already used in current permutation
            if (!used[i]) {
                // 1. Choose - Mark number as used and add to current permutation
                used[i] = true;
                currentPerm.add(nums[i]);

                // 2. Explore - Recursively generate permutations for next position
                recurPermute(nums, currentPerm, result, used);

                // 3. Unchoose - Backtrack by removing number and marking as unused
                currentPerm.remove(currentPerm.size() - 1);
                used[i] = false;
            }
        }
    }
    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> currentPerm = new ArrayList<>();
        boolean[] used = new boolean[nums.length];
        recurPermute(nums, currentPerm, result, used);
        return result;
    }

    public static void main(String[] args) {
        int[] arr = { 1, 2, 3 };
        System.out.println(permute(arr));
    }
}

/* 
ALGORITHM:
1. Create a boolean array to track used elements
2. Use recursive backtracking:
   - Base case: If current permutation length equals input array length
   - For each number in array:
     a. If number is unused, mark as used and add to current permutation
     b. Recursively generate permutations for remaining positions
     c. Backtrack by removing number and marking as unused
3. Return list of all permutations

Complexity Analysis:
TIME COMPLEXITY: O(n! * n)
- We generate n! permutations
- For each permutation, we spend O(n) time copying the current permutation

SPACE COMPLEXITY:
1. Auxiliary Space: O(n)
   - Boolean array of size n
   - Recursion stack depth of n
   - Current permutation array of max size n
2. Output Space: O(n! * n)
   - We store n! permutations, each of length n

Advantages:
1. Simple and intuitive implementation using backtracking
2. Space-efficient compared to approaches that clone entire arrays
3. Easy to modify for variations (e.g., permutations with duplicates)

Limitations:
1. Not suitable for large inputs due to factorial time complexity
2. Requires extra space for frequency array
3. Generates all permutations at once, may not be suitable for memory-constrained environments

Potential Improvements:
1. Use bit manipulation instead of boolean array to save space
2. Implement iterator pattern to generate permutations one at a time
3. Add parallel processing for large inputs
4. Use Heap's algorithm for better performance
5. Add input validation and error handling
6. Add support for handling duplicate elements
7. Implement as iterator to allow for lazy generation of permutations

# Detailed Analysis of Permutation Generation
Input Array: [1, 2, 3]

## Complete Step-by-Step Execution Table
```
Step | Current Permutation | Used Array     | Action                         | Stack Level | Result List
-----|-------------------|----------------|----------------------------------|-------------|----------------------------------------
1    | []                | [F, F, F]      | Start with empty                 | 0           | []
2    | [1]               | [T, F, F]      | Choose 1                         | 1           | []
3    | [1,2]             | [T, T, F]      | Choose 2                         | 2           | []
4    | [1,2,3]           | [T, T, T]      | Choose 3                         | 3           | [[1,2,3]]
5    | [1,2]             | [T, T, F]      | Backtrack, remove 3              | 2           | [[1,2,3]]
6    | [1]               | [T, F, F]      | Backtrack, remove 2              | 1           | [[1,2,3]]
7    | [1,3]             | [T, F, T]      | Choose 3                         | 2           | [[1,2,3]]
8    | [1,3,2]           | [T, T, T]      | Choose 2                         | 3           | [[1,2,3], [1,3,2]]
9    | [1,3]             | [T, F, T]      | Backtrack, remove 2              | 2           | [[1,2,3], [1,3,2]]
10   | [1]               | [T, F, F]      | Backtrack, remove 3              | 1           | [[1,2,3], [1,3,2]]
11   | []                | [F, F, F]      | Backtrack, remove 1              | 0           | [[1,2,3], [1,3,2]]
12   | [2]               | [F, T, F]      | Choose 2                         | 1           | [[1,2,3], [1,3,2]]
13   | [2,1]             | [T, T, F]      | Choose 1                         | 2           | [[1,2,3], [1,3,2]]
14   | [2,1,3]           | [T, T, T]      | Choose 3                         | 3           | [[1,2,3], [1,3,2], [2,1,3]]
15   | [2,1]             | [T, T, F]      | Backtrack, remove 3              | 2           | [[1,2,3], [1,3,2], [2,1,3]]
16   | [2]               | [F, T, F]      | Backtrack, remove 1              | 1           | [[1,2,3], [1,3,2], [2,1,3]]
17   | [2,3]             | [F, T, T]      | Choose 3                         | 2           | [[1,2,3], [1,3,2], [2,1,3]]
18   | [2,3,1]           | [T, T, T]      | Choose 1                         | 3           | [[1,2,3], [1,3,2], [2,1,3], [2,3,1]]
19   | [2,3]             | [F, T, T]      | Backtrack, remove 1              | 2           | [[1,2,3], [1,3,2], [2,1,3], [2,3,1]]
20   | [2]               | [F, T, F]      | Backtrack, remove 3              | 1           | [[1,2,3], [1,3,2], [2,1,3], [2,3,1]]
21   | []                | [F, F, F]      | Backtrack, remove 2              | 0           | [[1,2,3], [1,3,2], [2,1,3], [2,3,1]]
22   | [3]               | [F, F, T]      | Choose 3                         | 1           | [[1,2,3], [1,3,2], [2,1,3], [2,3,1]]
23   | [3,1]             | [T, F, T]      | Choose 1                         | 2           | [[1,2,3], [1,3,2], [2,1,3], [2,3,1]]
24   | [3,1,2]           | [T, T, T]      | Choose 2                         | 3           | [[1,2,3], [1,3,2], [2,1,3], [2,3,1], [3,1,2]]
25   | [3,1]             | [T, F, T]      | Backtrack, remove 2              | 2           | [[1,2,3], [1,3,2], [2,1,3], [2,3,1], [3,1,2]]
26   | [3]               | [F, F, T]      | Backtrack, remove 1              | 1           | [[1,2,3], [1,3,2], [2,1,3], [2,3,1], [3,1,2]]
27   | [3,2]             | [F, T, T]      | Choose 2                         | 2           | [[1,2,3], [1,3,2], [2,1,3], [2,3,1], [3,1,2]]
28   | [3,2,1]           | [T, T, T]      | Choose 1                         | 3           | [[1,2,3], [1,3,2], [2,1,3], [2,3,1], [3,1,2], [3,2,1]]
29   | [3,2]             | [F, T, T]      | Backtrack, remove 1              | 2           | [[1,2,3], [1,3,2], [2,1,3], [2,3,1], [3,1,2], [3,2,1]]
30   | [3]               | [F, F, T]      | Backtrack, remove 2              | 1           | [[1,2,3], [1,3,2], [2,1,3], [2,3,1], [3,1,2], [3,2,1]]
31   | []                | [F, F, F]      | Backtrack, remove 3              | 0           | [[1,2,3], [1,3,2], [2,1,3], [2,3,1], [3,1,2], [3,2,1]]
```

## Detailed Step-by-Step Process Explanation

1. Initial State (Step 1):
   - Start with empty permutation []
   - All elements are unused [F, F, F]
   - Result list is empty

2. First Branch - Starting with 1 (Steps 2-10):
   - Choose 1: [1]
   - Two sub-branches possible:
     a. Add 2 then 3: Creates [1,2,3]
     b. Add 3 then 2: Creates [1,3,2]
   - Backtrack to empty state

3. Second Branch - Starting with 2 (Steps 11-20):
   - Choose 2: [2]
   - Two sub-branches possible:
     a. Add 1 then 3: Creates [2,1,3]
     b. Add 3 then 1: Creates [2,3,1]
   - Backtrack to empty state

4. Third Branch - Starting with 3 (Steps 21-31):
   - Choose 3: [3]
   - Two sub-branches possible:
     a. Add 1 then 2: Creates [3,1,2]
     b. Add 2 then 1: Creates [3,2,1]
   - Backtrack to empty state

## Key State Transitions:

1. Choosing Elements:
   - When an element is chosen:
     * Add to current permutation
     * Mark as used (T) in used array
     * Increment stack level

2. Backtracking:
   - When backtracking occurs:
     * Remove last element from current permutation
     * Mark as unused (F) in used array
     * Decrement stack level

3. Adding to Result:
   - When current permutation length equals input array length:
     * Add copy of current permutation to result list
     * Begin backtracking

## Stack Level Significance:
- Level 0: Empty permutation
- Level 1: Single element chosen
- Level 2: Two elements chosen
- Level 3: Complete permutation (for input size 3)

## State Management:
1. Used Array:
   - [F, F, F]: No elements used
   - [T, F, F]: First element used
   - [T, T, F]: First and second elements used
   - [T, T, T]: All elements used

2. Result List Growth:
   - Starts empty
   - Adds each complete permutation
   - Final size: 6 (3! permutations)

This process ensures:
1. All possible combinations are explored
2. No duplicate permutations are generated
3. Each element is used exactly once in each permutation
4. Complete backtracking to explore all paths

The final output contains all 6 possible permutations:
[[1,2,3], [1,3,2], [2,1,3], [2,3,1], [3,1,2], [3,2,1]]

Key Observations:
1. The algorithm maintains the invariant that each number is used exactly once in each permutation
2. Backtracking ensures we explore all possible combinations efficiently
3. The frequency array provides O(1) lookup for element availability

Sample Validation:
Input: [1,2,3]
Expected: [[1,2,3], [1,3,2], [2,1,3], [2,3,1], [3,1,2], [3,2,1]]
Output: [[1,2,3], [1,3,2], [2,1,3], [2,3,1], [3,1,2], [3,2,1]]

Key Points:
1. Uses "choose-explore-unchoose" pattern of backtracking
2. Maintains state using boolean array instead of creating new arrays
3. Creates deep copy only when adding to final result

TEST CASES:
1. Input: [1]
   Expected: [[1]]
   Output: [[1]]
2. Input: [1,2]
   Expected: [[1,2], [2,1]]
   Output: [[1,2], [2,1]]
3. Input: []
   Expected: [[]]
   Output: [[]]
*/