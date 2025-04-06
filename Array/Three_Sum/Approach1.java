package Array.Three_Sum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/* 
# 3Sum
# Problem: Given an integer array nums, return all the triplets [nums[i], nums[j], nums[k]] such that i != j, i != k, and j != k, and nums[i] + nums[j] + nums[k] == 0.
Notice that the solution set must not contain duplicate triplets.

## Intuition:
- Core idea: Find all combinations of three numbers that sum to zero
- Use three nested loops to check all possible combinations
- Use a HashSet to avoid duplicate triplets
- Sort each triplet to ensure uniqueness

## Key Observations with Examples

### Observation 1: Triplet Formation
- Each triplet must contain three different elements
- Order doesn't matter ([-1,0,1] is same as [1,0,-1])
- Sum must be exactly zero

Example:
```
Array: [-1, 0, 1, 2, -1, -4]
Valid triplets: [-1, -1, 2], [-1, 0, 1]
```

### Observation 2: Duplicate Handling
- We need to avoid duplicate triplets
- Sorting each triplet before adding to result helps identify duplicates
```
Without sorting: [-1,0,1] and [1,0,-1] would be considered different
With sorting: Both become [-1,0,1]
```

## Step-by-Step Example
Let's work through array [-1, 0, 1, 2, -1, -4]:

1. First Iteration (i=0, arr[i]=-1):
   ```
   j=1, k=2: [-1, 0, 1] ✓ (Sum = 0)
   j=1, k=3: [-1, 0, 2] ✗ (Sum = 1)
   j=1, k=4: [-1, 0, -1] ✗ (Sum = -2)
   ...
   ```

2. Second Iteration (i=1, arr[i]=0):
   ```
   j=2, k=3: [0, 1, 2] ✗ (Sum = 3)
   j=2, k=4: [0, 1, -1] ✓ (Sum = 0, but duplicate)
   ...
   ```
*/
public class Approach1 {
    // Approach 1: Using 3 nested loops
    public static List<List<Integer>> triplet(int n, int[] arr) {
        // Using HashSet to automatically handle duplicates
        Set<List<Integer>> st = new HashSet<>();

        // Three nested loops to check all possible combinations
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                for (int k = j + 1; k < n; k++) {
                    // Check if current triplet sums to zero
                    if (arr[i] + arr[j] + arr[k] == 0) {
                        // Create and sort triplet before adding
                        List<Integer> temp = Arrays.asList(arr[i], arr[j], arr[k]);
                        temp.sort(null);
                        st.add(temp);
                    }
                }
            }
        }

        // Convert set to list for return
        return new ArrayList<>(st);
    }

    // Main method for testing
    public static void main(String[] args) {
        int[] arr = {-1, 0, 1, 2, -1, -4};
        int n = arr.length;
        List<List<Integer>> ans = triplet(n, arr);
        
        // Print results
        for (List<Integer> it : ans) {
            System.out.print("[");
            for (Integer i : it) {
                System.out.print(i + " ");
            }
            System.out.print("] ");
        }
        System.out.println();
    }
}

/*
# Algorithm
1. Initialize an empty HashSet to store unique triplets
2. Use three nested loops to iterate through all possible combinations
3. For each combination, check if sum equals zero
4. If sum is zero, sort the triplet and add to HashSet
5. Convert HashSet to ArrayList and return

### Time Complexity Breakdown per Step
1. Three nested loops: O(n³)
2. Sorting each triplet: O(3 log 3) ≈ O(1)
3. HashSet operations: O(1) average

Total: O(n³)

### Space Complexity Breakdown
1. HashSet storage: O(k) where k is number of valid triplets
2. Output ArrayList: O(k)
3. Temporary arrays for triplets: O(1)

Total: O(k) where k is number of valid triplets

# Advantages
1. Simple to implement and understand
2. Automatically handles duplicates using HashSet
3. No sorting of input array required

# Limitations
1. High time complexity of O(n³)
2. Not suitable for large arrays
3. Uses extra space for HashSet

# Potential Improvements
1. Use sorting and two-pointer technique to reduce complexity to O(n²)
2. Use binary search to find the third number
3. Implement early breaking conditions

# Step-by-Step Process with Dry Run

## Example Input: [-1, 0, 1, 2, -1, -4]

### Detailed Execution Table
```
Step | i | j | k | Current Sum | Triplet     | Added to Set
-----|---|---|---|-------------|-------------|-------------
1    | 0 | 1 | 2 | 0          | [-1,0,1]    | Yes
2    | 0 | 1 | 3 | 1          | [-1,0,2]    | No
3    | 0 | 1 | 4 | -2         | [-1,0,-1]   | No
4    | 0 | 2 | 3 | 2          | [-1,1,2]    | No
...  |...|...|...| ...        | ...         | ...
```

### Edge Cases Handling
1. **Empty Array**
   ```
   Input: []
   Output: []
   ```

2. **No Valid Triplets**
   ```
   Input: [1, 2, 3, 4]
   Output: []
   ```

TEST CASES:
1. Input: [-1, 0, 1, 2, -1, -4]
   Expected: [[-1, -1, 2], [-1, 0, 1]]
   Output: [[-1, -1, 2], [-1, 0, 1]]

2. Input: [0, 0, 0]
   Expected: [[0, 0, 0]]
   Output: [[0, 0, 0]]

3. Input: []
   Expected: []
   Output: []
 */