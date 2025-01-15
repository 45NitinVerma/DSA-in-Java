/* 
Problem: Generate all possible permutations of an array of integers using backtracking.

Intuition: We can generate all permutations by fixing one position at a time and recursively 
generating permutations for the remaining positions. We use swapping to avoid using extra space 
for creating new arrays.
*/

package Permutations.Recursive_Approach;
import java.util.Arrays;

public class Approach1b {
    // Approach 1b: Backtracking with Element Swapping
    public static void permute(int[] arr, int start) {
        // Base case: if we've reached the end of array, we've found a permutation
        if (start == arr.length) {
            System.out.println(Arrays.toString(arr));
            return;
        }

        // Try each possible element at the current position
        for (int i = start; i < arr.length; i++) {
            // 1. Make a choice - swap current element to position 'start'
            swap(arr, start, i);
            
            // 2. Explore - recursively generate permutations for remaining positions
            permute(arr, start + 1);
            
            // 3. Undo the choice - backtrack by swapping back
            swap(arr, start, i);
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3};
        permute(arr, 0);
    }
}

/* 
ALGORITHM:
1. Start with the first position (index 0)
2. For each remaining position:
   - Swap the current element with each element including and after it
   - Recursively generate permutations for the remaining positions
   - Backtrack by swapping elements back to their original positions
3. When we reach the end of the array, print the current permutation

Complexity Analysis:
TIME COMPLEXITY: O(n!)
- We generate all possible permutations
- For n elements, we have n! different permutations
- At each level, we make n-k swaps where k is the current level

SPACE COMPLEXITY:
1. Auxiliary Space: O(n)
   - Recursion stack space depth is n (length of array)
2. Input Space: O(n)
   - Space needed for input array of length n

Advantages:
1. In-place algorithm - no extra array space needed
2. Simple implementation using recursion
3. Natural backtracking process through swapping

Limitations:
1. Not suitable for generating permutations of very large arrays due to n! complexity
2. Recursive approach may cause stack overflow for large inputs
3. Modifies the input array during execution (though restores it)

Potential Improvements:
1. Add support for handling duplicate elements
2. Return permutations as a List instead of printing
3. Add iterative version to avoid recursion stack
4. Add bounds checking for array indices
5. Implement early stopping if only k permutations are needed
6. Add support for generic types instead of just integers
7. Add parallel processing for large arrays
8. Implement lexicographic ordering of permutations

# Detailed Analysis of Permutation Algorithm

## Input Array: [1, 2, 3]

### Complete Execution Table
```
Step | Current Array | Start | i | Action                    | After Swap     | Stack Level
-----|--------------|-------|---|---------------------------|-----------------|-------------
1    | [1,2,3]      | 0     | 0 | swap(0,0)                | [1,2,3]          | 1
2    | [1,2,3]      | 1     | 1 | swap(1,1)                | [1,2,3]          | 2
3    | [1,2,3]      | 2     | 2 | Print                    | [1,2,3]          | 3
4    | [1,2,3]      | 1     | 2 | swap(1,2)                | [1,3,2]          | 2
5    | [1,3,2]      | 2     | 2 | Print                    | [1,3,2]          | 3
6    | [1,2,3]      | 0     | 1 | swap(0,1)                | [2,1,3]          | 1
7    | [2,1,3]      | 1     | 1 | swap(1,1)                | [2,1,3]          | 2
8    | [2,1,3]      | 2     | 2 | Print                    | [2,1,3]          | 3
9    | [2,1,3]      | 1     | 2 | swap(1,2)                | [2,3,1]          | 2
10   | [2,3,1]      | 2     | 2 | Print                    | [2,3,1]          | 3
11   | [1,2,3]      | 0     | 2 | swap(0,2)                | [3,2,1]          | 1
12   | [3,2,1]      | 1     | 1 | swap(1,1)                | [3,2,1]          | 2
13   | [3,2,1]      | 2     | 2 | Print                    | [3,2,1]          | 3
14   | [3,2,1]      | 1     | 2 | swap(1,2)                | [3,1,2]          | 2
15   | [3,1,2]      | 2     | 2 | Print                    | [3,1,2]          | 3
```

### Step-by-Step Explanation

1. Initial Call (Step 1):
   - Start with array [1,2,3]
   - start = 0, we begin with first position
   - First swap is with itself (1↔1)

2. First Recursion Level (Steps 2-5):
   - Fix 1 at position 0
   - Generate permutations of [2,3]
   - Results in permutations [1,2,3] and [1,3,2]

3. Backtrack and Second Position (Steps 6-10):
   - Return to start = 0
   - Swap 1 and 2 to get [2,1,3]
   - Generate permutations with 2 fixed
   - Results in permutations [2,1,3] and [2,3,1]

4. Final Position Permutations (Steps 11-15):
   - Return to start = 0
   - Swap 1 and 3 to get [3,2,1]
   - Generate final permutations
   - Results in permutations [3,2,1] and [3,1,2]

### Output Sequence Generation:
1. [1,2,3] - Original order
2. [1,3,2] - Swap positions 1 and 2
3. [2,1,3] - Swap positions 0 and 1 from original
4. [2,3,1] - Swap positions 1 and 2
5. [3,2,1] - Swap positions 0 and 2 from original
6. [3,1,2] - Swap positions 1 and 2

### Key Points in the Process:

1. Stack Level Management:
   - Level 1: Choosing first position
   - Level 2: Choosing second position
   - Level 3: Final position reached, print permutation

2. Swap Operations:
   - Each swap operation creates a new permutation possibility
   - Backtracking restores the array to its previous state
   - Swaps happen at each recursive level

3. Recursive Pattern:
   - Each recursive call fixes one more position
   - Deeper recursion levels work with fewer unfixed positions
   - Base case reached when start equals array length

4. Backtracking Process:
   - After printing a permutation, backtrack to try different combinations
   - Undo swaps to restore array to previous state
   - Continue with next possible swap at current level

### Pattern Observations:

1. First Position (start = 0):
   - 1 → generates [1,2,3] and [1,3,2]
   - 2 → generates [2,1,3] and [2,3,1]
   - 3 → generates [3,2,1] and [3,1,2]

2. Second Position (start = 1):
   - For each first position, try all remaining elements
   - Each generates two permutations

3. Third Position (start = 2):
   - Final position is fixed by previous choices
   - Results in printing current permutation

### Memory Stack State:
```
Level 3: Printing permutation
    ↑
Level 2: Handling second position
    ↑
Level 1: Handling first position
    ↑
Main program
```

Key Observations:
1. Each element gets to be in each position exactly once
2. The algorithm maintains the original array order after completion
3. The number of recursive calls grows factorially

Sample Validation:
Input: [1,2,3]
Expected: All 6 permutations
Output: [1,2,3], [1,3,2], [2,1,3], [2,3,1], [3,2,1], [3,1,2]

Key Points:
1. Backtracking ensures we don't need extra space for storing permutations
2. The swap operation is key to generating permutations efficiently
3. Each recursive call fixes one more position in the array

TEST CASES:
1. Input: [1,2,3]
   Expected: 6 permutations
   Output: All permutations printed correctly

2. Input: [1]
   Expected: [1]
   Output: Single element array returns itself

3. Input: []
   Expected: []
   Output: Empty array handles correctly
*/