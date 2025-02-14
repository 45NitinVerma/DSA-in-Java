package Array.Next_Permutation;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/* 
Problem: Find the next lexicographically greater permutation of the given array/list.
If such arrangement is not possible, it must rearrange it as the lowest possible order (i.e., sorted in ascending order).

# Intuition for Next Permutation
  - Identify the "break point" where the current permutation can be modified to obtain the next lexicographical order.
  - Swap the element at the break point with the smallest element in the right half that is greater than it.
  - Reverse the right half to ensure it is in ascending order, forming the next permutation.

## Basic Understanding
The next permutation of an array is the next lexicographically greater rearrangement of its elements. If no such arrangement exists, return the smallest possible arrangement.

## Key Observations with Examples

### Observation 1: Dictionary Order Pattern
Consider dictionary-ordered strings to understand the pattern:
* "raj", "rax", "rbx"
* Common prefixes determine the ordering:
  - "raj" vs "rax": common prefix "ra", 'j' < 'x', so "raj" comes first
  - "rax" vs "rbx": common prefix "r", 'a' < 'b', so "rax" comes first

Similarly for numbers, consider [1,2,3] permutations:
```
[1,2,3] → [1,3,2] → [2,1,3] → [2,3,1] → [3,1,2] → [3,2,1]
```

### Observation 2: Break Point Structure
Every permutation (except the last) has a "break point" where the pattern changes.

Example: [2,1,5,4,3,0,0]
```
Breaking it down:
- From right: [0,0,3,4,5,1,2]
- Notice the first decrease: 5→1
- Break point is at index 1 (value 1)
```

Visual representation:
```
[2,1,5,4,3,0,0]
   ↑
   break point (1 < 5)
```

### Observation 3: Right Half Properties
The right half after break point is always in decreasing order:
```
[2,1,5,4,3,0,0]
     ↓ ↓ ↓ ↓ ↓
     decreasing (5,4,3,0,0)
```

This is crucial because:
1. If no break point exists, array is in decreasing order
2. Makes finding next greater element easier

### Observation 4: Next Greater Element
After finding break point, we need the next greater element from the right half.

Example: [2,1,5,4,3,0,0]
```
1. Break point value: 1
2. Right half: [5,4,3,0,0]
3. Next greater than 1 is 3
4. After swap: [2,3,5,4,1,0,0]
```

### Observation 5: Final Reversal
After swapping, the right half needs to be reversed to get the smallest possible arrangement:

```
[2,3,5,4,1,0,0] → [2,3,0,0,1,4,5]
     ↓ ↓ ↓ ↓ ↓        
     reverse this part
```

## Step-by-Step Example
Let's work through [2,1,5,4,3,0,0]:

1. Find break point:
   ```
   [2,1,5,4,3,0,0]
      ↑
   1 < 5 (break point found)
   ```

2. Find next greater in right half:
   ```
   [2,1,5,4,3,0,0]
      ↑     ↑
   3 is next greater than 1
   ```

3. Swap elements:
   ```
   [2,1,5,4,3,0,0] → [2,3,5,4,1,0,0]
      ↑   ↑            
   ```

4. Reverse right half:
   ```
   [2,3,5,4,1,0,0] → [2,3,0,0,1,4,5]
        ↓ ↓ ↓ ↓ ↓
   ```

## Special Cases

### Case 1: Descending Array
Input: [3,2,1]
- No break point exists
- Reverse entire array
- Result: [1,2,3]

### Case 2: Array with Duplicates
Input: [1,1,5]
- Break point at first 1
- Next greater is 5
- After swap and reverse: [1,5,1]

### Case 3: Single Element
Input: [1]
- No break point possible
- Return same array

This intuitive understanding helps us implement the solution efficiently and handle all possible cases correctly.
*/

public class Approach2 {
    // Approach 2: Optimal Approach  
    public static List<Integer> nextGreaterPermutation(List<Integer> A) {
        int n = A.size();

        // Step 1: Find the break point - first pair from right where A[i] < A[i+1]
        int ind = -1;
        for (int i = n - 2; i >= 0; i--) {
            if (A.get(i) < A.get(i + 1)) {
                ind = i;
                break;
            }
        }

        // If no break point exists, array is in descending order
        // Reverse to get smallest permutation
        if (ind == -1) {
            Collections.reverse(A);
            return A;
        }

        // Step 2: Find smallest element greater than A[ind] from right
        // and swap with A[ind]
        for (int i = n - 1; i > ind; i--) {
            if (A.get(i) > A.get(ind)) {
                // Swap elements
                int tmp = A.get(i);
                A.set(i, A.get(ind));
                A.set(ind, tmp);
                break;
            }
        }

        // Step 3: Reverse elements after ind to get smallest arrangement
        List<Integer> sublist = A.subList(ind + 1, n);
        Collections.reverse(sublist);

        return A;
    }

    public static void main(String args[]) {
        List<Integer> A = Arrays.asList(new Integer[] {2, 1, 5, 4, 3, 0, 0});
        List<Integer> ans = nextGreaterPermutation(A);

        System.out.print("The next permutation is: [");
        for (int i = 0; i < ans.size(); i++) {
            System.out.print(ans.get(i) + " ");
        }
        System.out.println("]");
    }
}

/* 
ALGORITHM:
1. Traverse array from right to find first pair where A[i] < A[i+1]
2. If no such pair exists, reverse entire array
3. If pair found, find smallest element > A[i] from right
4. Swap these elements
5. Reverse all elements after index i

Complexity Analysis:
TIME COMPLEXITY: O(N) 
- One pass to find break point: O(N)
- One pass to find swap element: O(N)
- One pass to reverse remaining array: O(N)

SPACE COMPLEXITY:
1. Auxiliary Space: O(1)
   - Only using constant extra space for variables
2. Input Space: O(N)
   - Space needed to store input array

Advantages:
1. Single pass solution with O(N) time complexity
2. In-place algorithm with O(1) extra space
3. Handles all edge cases effectively

Limitations:
1. Modifies input array in-place which might not be desired
2. Not thread-safe due to in-place modifications
3. Limited to finding only next permutation, not Kth permutation

Potential Improvements:
1. Add input validation for null or empty lists
2. Make method thread-safe by creating copy of input
3. Add option to find previous permutation
4. Optimize for special cases (e.g., array already sorted)
5. Add generic support for comparable types
6. Add documentation for edge cases
7. Implement iterator pattern for generating all permutations

# Step-by-Step Process for Next Permutation with Dry Run

## Example Input: [2, 1, 5, 4, 3, 0, 0]

### Detailed Execution Table
```
Step | Array State         | Action                           | Variables          | Explanation
-----|--------------------|---------------------------------|-------------------|-------------
1    | [2,1,5,4,3,0,0]    | Start scan from right           | i = 5            | Initialize second-last index
2    | [2,1,5,4,3,0,0]    | Compare A[5] with A[6]          | ind = -1         | 0 ≤ 0, continue
3    | [2,1,5,4,3,0,0]    | Compare A[4] with A[5]          | ind = -1         | 3 > 0, continue
4    | [2,1,5,4,3,0,0]    | Compare A[3] with A[4]          | ind = -1         | 4 > 3, continue
5    | [2,1,5,4,3,0,0]    | Compare A[2] with A[3]          | ind = -1         | 5 > 4, continue
6    | [2,1,5,4,3,0,0]    | Compare A[1] with A[2]          | ind = 1          | 1 < 5, break point found!
7    | [2,1,5,4,3,0,0]    | Start scan for next greater     | i = 6            | Find element > A[1]
8    | [2,3,5,4,1,0,0]    | Swap A[1] with A[4]            | tmp = 3          | 3 is next greater than 1
9    | [2,3,0,0,1,4,5]    | Reverse subarray after ind     | -                | Reverse elements after index 1
```

### Step-by-Step Explanation

1. **Finding Break Point**
   - Start from second-last index (i = n-2)
   - Compare each element with next element
   - Continue until A[i] < A[i+1] is found
   ```
   Index:  0 1 2 3 4 5 6
   Array: [2,1,5,4,3,0,0]
          ↑
          Break point at index 1 (1 < 5)
   ```

2. **Finding Next Greater Element**
   - Start from end
   - Find smallest element greater than break point value
   ```
   Index:  0 1 2 3 4 5 6
   Array: [2,1,5,4,3,0,0]
            ↑   ↑
            1   3 (next greater)
   ```

3. **Swapping Elements**
   - Swap break point element with next greater
   ```
   Before: [2,1,5,4,3,0,0]
   After:  [2,3,5,4,1,0,0]
            ↑   ↑
         Swapped 1 and 3
   ```

4. **Reversing Right Half**
   - Reverse all elements after break point
   ```
   Before: [2,3,5,4,1,0,0]
   After:  [2,3,0,0,1,4,5]
              ↓↓↓↓↓↓↓
           Reversed portion
   ```

### Additional Example Cases

1. **Descending Order Array**
```
Input:  [3,2,1]
Step 1: No break point found (ind = -1)
Step 2: Reverse entire array
Output: [1,2,3]
```

2. **Array with Duplicates**
```
Input:  [1,1,5]
Step 1: Break point at index 1 (1 < 5)
Step 2: Swap 1 with 5
Step 3: Reverse after index 1
Output: [1,5,1]
```

3. **Already Maximum Permutation**
```
Input:  [5,4,3,2,1]
Step 1: No break point found
Step 2: Reverse entire array
Output: [1,2,3,4,5]
```

### Time Complexity Breakdown per Step
1. Finding break point: O(n)
2. Finding next greater: O(n)
3. Swapping: O(1)
4. Reversing right half: O(n)
Total: O(n)

### Space Complexity Breakdown
1. Variables (ind, i, tmp): O(1)
2. Input array modification: In-place
Total: O(1)

### Edge Cases Handling
1. **Single Element Array**
   ```
   Input: [1]
   Output: [1]
   No action needed
   ```

2. **Two Element Array**
   ```
   Input: [1,2]
   Output: [2,1]
   Simple swap if ascending
   ```

3. **All Elements Same**
   ```
   Input: [1,1,1]
   Output: [1,1,1]
   No break point found, reverse gives same array
   ```

This step-by-step process ensures we capture every detail of the algorithm's execution and helps understand how it handles different input scenarios.

Key Observations:
1. Break point indicates where next permutation differs
2. Elements after break point are in descending order
3. Reversing gives smallest possible arrangement

Sample Validation:
Input: [2,1,5,4,3,0,0]
Expected: [2,3,0,0,1,4,5]
Output: [2,3,0,0,1,4,5]

Key Points:
1. Always returns valid next permutation or smallest possible
2. Handles duplicate elements correctly
3. Works for both ascending and descending sequences

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