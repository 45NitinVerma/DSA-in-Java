package Array.Longest_Consecutive_Sequence;

import java.util.HashSet;
import java.util.Set;
/* 
# Problem Title
Longest Consecutive Sequence
[Finding the longest consecutive sequence of numbers in an unsorted array]

# Intuition: We will adopt a similar approach to the brute-force method but with optimizations in the search process. Instead of searching sequences for every array element as in the brute-force approach, we will focus solely on finding sequences only for those numbers that can be the starting numbers of the sequences. This targeted approach narrows down our search and improves efficiency.

We will do this with the help of the Set data structure.

## How to identify if a number can be the starting number for a sequence:
1. First, we will put all the array elements into the set data structure.
2. If a number, num, is a starting number, ideally, num-1 should not exist. So, for every element, x, in the set, we will check if x-1 exists inside the set. :
- If x-1 exists: This means x cannot be a starting number and we will move on to the next element in the set.
- If x-1 does not exist: This means x is a starting number of a sequence. So, for number, x, we will start finding the consecutive elements.

## How to search for consecutive elements for a number, x:nstead of using linear search, we will use the set data structure itself to search for the elements x+1, x+2, x+3, and so on.


- Use HashSet to efficiently track and search for consecutive elements
- Only start sequence checks from potential starting numbers
- Optimize by avoiding unnecessary sequence checks

## Basic Understanding
The problem aims to find the length of the longest consecutive sequence of integers in an unsorted array. For example, in [100, 200, 1, 2, 3, 4], the longest consecutive sequence is [1, 2, 3, 4] with length 4.

## Key Observations with Examples

### Observation 1: Starting Numbers
- A number can be a starting point of a sequence only if (number-1) doesn't exist in the array
- Example: In [100, 4, 3, 2, 1], 1 and 100 are potential starting numbers

### Observation 2: HashSet Benefits
- O(1) lookup time for checking number existence
- Eliminates duplicates automatically
Example: [1, 1, 2, 2, 3] → HashSet{1, 2, 3}

### Observation 3: Sequence Building
- For each starting number, check for consecutive numbers (n+1)
- Stop when next consecutive number isn't found
Example: For starting number 1, check 2, 3, 4...until break in sequence

## Step-by-Step Example
Let's work through array [100, 200, 1, 2, 3, 4]:

1. Create HashSet:
   HashSet = {1, 2, 3, 4, 100, 200}

2. Check each number:
   100: No 99 in set → Check sequence: 100, 101(not found) → Length 1
   200: No 199 in set → Check sequence: 200, 201(not found) → Length 1
   1: No 0 in set → Check sequence: 1, 2, 3, 4, 5(not found) → Length 4
   2: 1 exists → Skip
   3: 2 exists → Skip
   4: 3 exists → Skip

## Special Cases

### Case 1: Empty Array
Input: []
- Behavior: Return 0
- Result: 0

### Case 2: No Consecutive Numbers
Input: [5, 8, 11, 14]
- Behavior: Each number forms its own sequence
- Result: 1

### Case 3: All Consecutive
Input: [1, 2, 3, 4, 5]
- Behavior: Single sequence covering all elements
- Result: 5

*/
public class Approach3 {
    // Approach 3: Optimal approach using HashSet
    public static int longestSuccessiveElements(int[] a) {
        int n = a.length;
        if (n == 0) return 0;
        
        // Initialize variables
        int longest = 1;
        Set<Integer> set = new HashSet<>();
        
        // Add all elements to HashSet
        for (int i = 0; i < n; i++) {
            set.add(a[i]);
        }
        
        // Check each potential sequence start
        for (int it : set) {
            // If it's a starting number (no previous number exists)
            if (!set.contains(it - 1)) {
                int cnt = 1;
                int x = it;
                // Count consecutive numbers
                while (set.contains(x + 1)) {
                    x = x + 1;
                    cnt = cnt + 1;
                }
                longest = Math.max(longest, cnt);
            }
        }
        return longest;
    }

    public static void main(String[] args) {
        int[] a = {100, 200, 1, 2, 3, 4};
        int ans = longestSuccessiveElements(a);
        System.out.println("The longest consecutive sequence is " + ans);
    }
}

/*
# Algorithm
1. Create HashSet and add all array elements
2. For each number in set:
   - Check if it's a starting number (n-1 doesn't exist)
   - If yes, count consecutive numbers until sequence breaks
3. Track and return maximum sequence length

### Time Complexity Breakdown per Step
1. Creating HashSet: O(n)
2. Checking each number: O(n)
3. Finding consecutive numbers: O(length of sequence)
Total: O(n)

### Space Complexity Breakdown
1. Auxiliary Space: O(n) for HashSet
2. Input Space: O(n) for input array
Total: O(n)

# Advantages
1. O(n) time complexity
2. Handles duplicates efficiently
3. No need for sorting
4. Space-efficient for large datasets

# Limitations
1. Requires extra space for HashSet
2. Not optimal for very small arrays
3. Doesn't maintain sequence order

# Potential Improvements
1. Add optimization for small arrays
2. Implement stream-based processing for large datasets
3. Add functionality to return the actual sequence

# Step-by-Step Process with Dry Run

## Example Input: [100, 200, 1, 2, 3, 4]
Let me break this down with a detailed step-by-step explanation using the input array [100, 200, 1, 2, 3, 4].

### Detailed Execution Table with Step-by-Step Process
```
Step | Current Element | Set State              | Action                             | Current Sequence | Length | Max Length | Explanation
-----|----------------|------------------------|-----------------------------------|-----------------|---------|------------|-------------
1    | -              | {}                     | Initialize HashSet                | -               | 0       | 1          | Start with empty set
2    | 100            | {100}                  | Add to set                        | -               | 0       | 1          | First element added
3    | 200            | {100,200}              | Add to set                        | -               | 0       | 1          | Second element added
4    | 1              | {1,100,200}            | Add to set                        | -               | 0       | 1          | Third element added
5    | 2              | {1,2,100,200}          | Add to set                        | -               | 0       | 1          | Fourth element added
6    | 3              | {1,2,3,100,200}        | Add to set                        | -               | 0       | 1          | Fifth element added
7    | 4              | {1,2,3,4,100,200}      | Add to set                        | -               | 0       | 1          | Sixth element added
8    | 100            | {1,2,3,4,100,200}      | Check sequence start (99 ∉ set)   | [100]           | 1       | 1          | First sequence check
9    | 200            | {1,2,3,4,100,200}      | Check sequence start (199 ∉ set)  | [200]           | 1       | 1          | Second sequence check
10   | 1              | {1,2,3,4,100,200}      | Check sequence start (0 ∉ set)    | [1,2,3,4]       | 4       | 4          | Found longest sequence
11   | 2              | {1,2,3,4,100,200}      | Skip (1 ∈ set)                   | -               | -       | 4          | Not a start number
12   | 3              | {1,2,3,4,100,200}      | Skip (2 ∈ set)                   | -               | -       | 4          | Not a start number
13   | 4              | {1,2,3,4,100,200}      | Skip (3 ∈ set)                   | -               | -       | 4          | Not a start number
```

### Step-by-Step Explanation:

1. **Initialization Phase (Steps 1-7)**:
   - Start with an empty HashSet
   - Add each element from the array to the set
   - After this phase, set contains {1,2,3,4,100,200}

2. **Checking Element 100 (Step 8)**:
   - Check if 99 exists in set (No)
   - Start sequence from 100
   - Check if 101 exists (No)
   - Sequence length = 1
   - Max length updated to 1

3. **Checking Element 200 (Step 9)**:
   - Check if 199 exists in set (No)
   - Start sequence from 200
   - Check if 201 exists (No)
   - Sequence length = 1
   - Max length remains 1

4. **Checking Element 1 (Step 10)**:
   - Check if 0 exists in set (No)
   - Start sequence from 1
   - Check consecutive numbers:
     * Found 2 → continue
     * Found 3 → continue
     * Found 4 → continue
     * 5 not found → stop
   - Sequence length = 4
   - Max length updated to 4

5. **Remaining Elements (Steps 11-13)**:
   - Element 2: Skipped as 1 exists
   - Element 3: Skipped as 2 exists
   - Element 4: Skipped as 3 exists

### Key Points from the Table:
1. Only three elements (100, 200, and 1) are checked for sequences as they are potential starting points
2. Elements 2, 3, and 4 are skipped as they can't be sequence starts
3. The longest sequence [1,2,3,4] is found in Step 10
4. The algorithm efficiently avoids unnecessary checks by skipping elements that can't be sequence starts

This tabular format clearly shows how the algorithm:
- Builds the HashSet initially
- Only processes potential sequence starting points
- Skips elements that can't be sequence starts
- Tracks the maximum sequence length throughout the process

### Edge Cases Handling
1. **Empty Array**
   ```
   Input: []
   Output: 0
   Handles empty array case with initial check
   ```

2. **Single Element**
   ```
   Input: [5]
   Output: 1
   Returns 1 for single element arrays
   ```

Sample Validation:
Input: [100, 200, 1, 2, 3, 4]
Expected: 4
Output: 4

TEST CASES:
1. Input: []
   Expected: 0
   Output: 0
2. Input: [1]
   Expected: 1
   Output: 1
3. Input: [1, 2, 3, 4, 5]
   Expected: 5
   Output: 5
4. Input: [1, 3, 5, 7]
   Expected: 1
   Output: 1
 */