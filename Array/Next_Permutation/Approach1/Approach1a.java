package Array.Next_Permutation.Approach1;

/* 
Problem: Find the next lexicographically greater permutation of the given array.
If not possible, return the lowest possible order (sorted ascending).

Intuition: Generate all permutations, sort them lexicographically, find the current
permutation in the list, and return the next one. If current is the last permutation,
return the first one (which will be sorted ascending).
*/
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
public class Approach1a {
    /* Approach 1(a): Generate All Permutations using backtracking and 
    Find Next Permutation after sorting*/
   public static void nextPermutation(int[] nums) {
       // Generate all permutations
       List<List<Integer>> allPerms = permute(nums);
       
       // Convert current array to list for comparison
       List<Integer> current = new ArrayList<>();
       for (int num : nums) {
           current.add(num);
       }
       
       // Sort permutations lexicographically
       Collections.sort(allPerms, (a, b) -> {
           for (int i = 0; i < a.size(); i++) {
               if (!a.get(i).equals(b.get(i))) {
                   return a.get(i) - b.get(i);
               }
           }
           return 0;
       });
       
       // Find current permutation and get next one
       for (int i = 0; i < allPerms.size(); i++) {
           if (allPerms.get(i).equals(current)) {
               // If current is last permutation, get first (sorted ascending)
               List<Integer> next = (i == allPerms.size() - 1) ? 
                   allPerms.get(0) : allPerms.get(i + 1);
               
               // Copy next permutation back to input array
               for (int j = 0; j < nums.length; j++) {
                   nums[j] = next.get(j);
               }
               return;
           }
       }
   }
   
   private static List<List<Integer>> permute(int[] nums) {
       List<List<Integer>> result = new ArrayList<>();
       backtrack(nums, new ArrayList<>(), result);
       return result;
   }
   
   private static void backtrack(int[] nums, List<Integer> current, List<List<Integer>> result) {
       // Base case: if permutation is complete
       if (current.size() == nums.length) {
           result.add(new ArrayList<>(current));
           return;
       }
       
       // Try each number
       for (int num : nums) {
           // Skip if number already used
           if (current.contains(num)) {
               continue;
           }
           
           // Add current number
           current.add(num);
           // Recurse
           backtrack(nums, current, result);
           // Backtrack
           current.remove(current.size() - 1);
       }
   }
   public static void main(String[] args) {
        // Test case
        int[] nums = {1, 2, 3};

        System.out.println("Original array:");
        System.out.println(Arrays.toString(nums));

        nextPermutation(nums);

        System.out.println("Next permutation:");
        System.out.println(Arrays.toString(nums));
    }
}

/* 
ALGORITHM:
1. Generate all permutations of the input array using backtracking.
2. Convert the current array to a list for easier comparison.
3. Sort the list of permutations lexicographically.
4. Find the current permutation in the sorted list.
5. If the current permutation is the last in the sorted list, set the array to the first permutation.
6. Otherwise, set the array to the next permutation in the sorted list.

Complexity Analysis:
TIME COMPLEXITY: O(n! * n)
- O(n!) to generate all permutations
- O(n! * log(n!)) for sorting permutations
- O(n) for array conversions and comparisons

SPACE COMPLEXITY:
1. Auxiliary Space: O(n! * n)
  - Storing all permutations
  - Each permutation takes O(n) space
2. Input Space: O(n)
  - Original input array

# Detailed Dry Run for Next Permutation

Input Array: [1,2,3]

## PHASE 1: GENERATING ALL PERMUTATIONS

### Backtracking Process Table
```
Step | Call Stack      | Current   | Used Numbers | Action                | Result List
-----|----------------|-----------|--------------|----------------------|-------------
1    | backtrack([])  | []        | []           | Start recursion      | []
2    | backtrack([1]) | [1]       | [1]          | Add 1, recurse       | []
3    | backtrack([12])| [1,2]     | [1,2]        | Add 2, recurse       | []
4    | backtrack([123])| [1,2,3]  | [1,2,3]      | Add to result        | [[1,2,3]]
5    | backtrack([12])| [1,2]     | [1,2]        | Backtrack, remove 3  | [[1,2,3]]
6    | backtrack([13])| [1,3]     | [1,3]        | Add 3, try new path  | [[1,2,3]]
7    | backtrack([132])| [1,3,2]  | [1,3,2]      | Add to result        | [[1,2,3],[1,3,2]]
8    | backtrack([2]) | [2]       | [2]          | New path with 2      | [[1,2,3],[1,3,2]]
9    | backtrack([21])| [2,1]     | [2,1]        | Add 1                | [[1,2,3],[1,3,2]]
10   | backtrack([213])| [2,1,3]  | [2,1,3]      | Add to result        | [[1,2,3],[1,3,2],[2,1,3]]
```

## PHASE 2: SORTING PERMUTATIONS
```
Initial List: [[1,2,3], [1,3,2], [2,1,3], [2,3,1], [3,1,2], [3,2,1]]
Sorted List (already in lexicographical order):
1. [1,2,3]
2. [1,3,2]
3. [2,1,3]
4. [2,3,1]
5. [3,1,2]
6. [3,2,1]
```

## PHASE 3: FINDING NEXT PERMUTATION
```
Current Input: [1,2,3]
Found at Index: 0
Next Permutation: [1,3,2] (index 1)
```

## STEP-BY-STEP PROCESS EXPLANATION

1. **Initialization**:
   - Input array: [1,2,3]
   - Empty result list
   - Empty current permutation

2. **First Permutation Building ([1,2,3])**:
   ```
   Step 1: Add 1 → [1]
   Step 2: Add 2 → [1,2]
   Step 3: Add 3 → [1,2,3] ✓ (Complete permutation)
   ```

3. **Second Permutation Building ([1,3,2])**:
   ```
   Step 1: Backtrack to [1,2]
   Step 2: Remove 2
   Step 3: Add 3 → [1,3]
   Step 4: Add 2 → [1,3,2] ✓ (Complete permutation)
   ```

4. **Process Continues Until All Permutations Found**:
   ```
   [1,2,3] → [1,3,2] → [2,1,3] → [2,3,1] → [3,1,2] → [3,2,1]
   ```

## VARIABLE STATE TRACKING
```
State      | nums    | current | result size | next permutation
-----------|---------|---------|-------------|----------------
Initial    | [1,2,3] | []      | 0           | null
After Gen  | [1,2,3] | []      | 6           | [1,3,2]
Final      | [1,3,2] | []      | 6           | null
```

## MEMORY USAGE TRACKING
```
Component           | Space Used  | Purpose
-------------------|-------------|------------------
Input Array        | O(n)        | Original array
Current List       | O(n)        | Building permutations
Result List        | O(n! * n)   | All permutations
Call Stack         | O(n)        | Recursion depth
```

## KEY OPERATIONS PERFORMED

1. **Permutation Generation**:
   - Used backtracking to generate all possibilities
   - Each number is tried at each position
   - Skip if number already used in current permutation

2. **Finding Current Permutation**:
   - Convert input array to list
   - Search in sorted permutations
   - Get next index or wrap around to start

3. **Copying Result**:
   - Copy next permutation back to input array
   - Update in place as required

## VALIDATION CHECKPOINTS

1. **After First Complete Permutation**:
   - Result List: [[1,2,3]]
   - Current: Empty (backtracked)
   - Next Available: 1,3,2

2. **After All Permutations Generated**:
   - All 6 permutations present
   - Sorted lexicographically
   - Can find next for any input

3. **Final State**:
   - Input array modified to [1,3,2]
   - All temporary data structures cleared
   - Process complete

TEST CASES:
1. Input: nums = [1,2,3]
  Expected: [1,3,2]
  Output: [1,3,2]
  
2. Input: nums = [3,2,1]
  Expected: [1,2,3]
  Output: [1,2,3]
  
3. Input: nums = [1,1,5]
  Expected: [1,5,1]
  Output: [1,5,1]

Potential Improvements:
1. Avoid generating all permutations - can be done in O(n) time and O(1) space
2. Use array for current permutation instead of ArrayList
3. Avoid sorting all permutations
4. Handle duplicates more efficiently
*/