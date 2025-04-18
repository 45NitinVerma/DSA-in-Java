package Array.Four_Sum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
/* 
# Problem: 4Sum
Given an array nums of n integers, return an array of all the unique quadruplets [nums[a], nums[b], nums[c], nums[d]] such that:

0 <= a, b, c, d < n
a, b, c, and d are distinct.
nums[a] + nums[b] + nums[c] + nums[d] == target
You may return the answer in any order.

 */
public class Approach1 {
    // Approach 1: Using 4 nested loops
    public static List<List<Integer>> fourSum(int[] nums, int target) {
        int n = nums.length; // size of the array
        Set<List<Integer>> set = new HashSet<>(); // To store unique quadruplets

        // checking all possible quadruplets using 4 nested loops:
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                for (int k = j + 1; k < n; k++) {
                    for (int l = k + 1; l < n; l++) {
                        // taking bigger data type (long) to avoid integer overflow:
                        long sum = (long)nums[i] + nums[j];
                        sum += nums[k];
                        sum += nums[l];

                        if (sum == target) {
                            // Create a list with the four numbers
                            List<Integer> temp = Arrays.asList(nums[i], nums[j], nums[k], nums[l]);
                            // Sort to ensure uniqueness when added to set
                            Collections.sort(temp);
                            // Add to set to eliminate duplicates
                            set.add(temp);
                        }
                    }
                }
            }
        }
        // Convert set to list for final result
        List<List<Integer>> ans = new ArrayList<>(set);
        return ans;
    }

    public static void main(String[] args) {
        int[] nums = {4, 3, 3, 4, 4, 2, 1, 2, 1, 1};
        int target = 9;
        List<List<Integer>> ans = fourSum(nums, target);
        System.out.println("The quadruplets are: ");
        for (List<Integer> it : ans) {
            System.out.print("[");
            for (int ele : it) {
                System.out.print(ele + " ");
            }
            System.out.print("] ");
        }
        System.out.println();
    }
}

/*
# Algorithm
1. Create a HashSet to store unique quadruplets
2. Use four nested loops to generate all possible combinations of four elements:
   - First loop iterates through indices 0 to n
   - Second loop iterates through indices i+1 to n
   - Third loop iterates through indices j+1 to n
   - Fourth loop iterates through indices k+1 to n
3. For each quadruplet, compute the sum using long data type
4. If sum equals target, create a list with the four values
5. Sort the list and add it to the HashSet to handle duplicates
6. Convert the HashSet to ArrayList and return the result

### Time Complexity Breakdown per Step
1. Four nested loops: O(n⁴)
2. Sorting each quadruplet: O(4log4) which is constant
3. Adding to HashSet: O(1) on average
4. Converting HashSet to ArrayList: O(number of unique quadruplets)

Total: O(n⁴), where n is the size of the input array

### Space Complexity Breakdown
1. Auxiliary Space: O(k), where k is the number of unique quadruplets
   - HashSet to store unique quadruplets
   - ArrayList to return the result
2. Input Space: O(n) for the input array

Total: O(n + k), which can be simplified to O(k) as k ≤ C(n,4) which is O(n⁴)

# Advantages
1. Simple and straightforward implementation
2. Correctly handles integer overflow using long data type
3. Correctly handles duplicate quadruplets using a HashSet
4. Works for all valid inputs without requiring pre-processing

# Limitations
1. Extremely inefficient for large arrays due to O(n⁴) time complexity
2. Uses additional space to store results and handle duplicates
3. Slower than more optimized approaches that use sorting and two-pointer technique

# Potential Improvements
1. Sort the array first and use a two-pointer approach to reduce complexity to O(n³)
2. Implement early termination conditions to skip unnecessary iterations
3. Use a different approach for duplicate handling to avoid sorting each quadruplet
4. Use bit manipulation techniques for small arrays to improve performance

# Step-by-Step Process with Dry Run

## Example Input: [4, 3, 3, 4, 4, 2, 1, 2, 1, 1], target = 9

### Detailed Execution Table
```
Step | i  | j  | k  | l  | Quadruplet     | Sum | Action                   | Explanation
-----|----|----|----|----|----------------|-----|--------------------------|-------------
1    | 0  | 1  | 2  | 3  | [4,3,3,4]      | 14  | Skip (sum != target)    | First quadruplet checked doesn't equal target
...  | ...| ...| ...| ...| ...            | ... | ...                      | Many iterations skipped for brevity
2    | 0  | 1  | 8  | 9  | [4,3,1,1]      | 9   | Add [1,1,3,4] to set    | Found a valid quadruplet
...  | ...| ...| ...| ...| ...            | ... | ...                      | More iterations skipped
3    | 1  | 2  | 5  | 6  | [3,3,2,1]      | 9   | Add [1,2,3,3] to set    | Another valid quadruplet
...  | ...| ...| ...| ...| ...            | ... | ...                      | More iterations skipped
4    | 5  | 6  | 7  | 9  | [2,1,2,1]      | 6   | Skip (sum != target)    | Sum doesn't equal target
...  | ...| ...| ...| ...| ...            | ... | ...                      | Final iterations
5    | -  | -  | -  | -  | -              | -   | Convert set to list     | Final result: [[1,1,3,4], [1,2,2,4], [1,2,3,3]]
```

### Step-by-Step Explanation

1. **Initialize Data Structures**
   - Create a HashSet to store unique quadruplets
   ```
   set = {}
   ```

2. **Loop Through All Combinations**
   - Using 4 nested loops to check all possible quadruplets
   ```
   For each valid (i,j,k,l) combination:
     Calculate sum = nums[i] + nums[j] + nums[k] + nums[l]
     If sum == target:
       Add sorted quadruplet to set
   ```

3. **Found First Match: [4,3,1,1]**
   - Sum = 4 + 3 + 1 + 1 = 9 (equals target)
   - Sort to [1,1,3,4] and add to set
   ```
   set = {[1,1,3,4]}
   ```

4. **Found Another Match: [3,3,2,1]**
   - Sum = 3 + 3 + 2 + 1 = 9 (equals target)
   - Sort to [1,2,3,3] and add to set
   ```
   set = {[1,1,3,4], [1,2,3,3]}
   ```

5. **Final Result**
   - Convert set to list
   ```
   result = [[1,1,3,4], [1,2,3,3]]
   ```

### Additional Example Cases

1. **Small Input Example**
```
Input:  [1,0,-1,0,-2,2], target=0
Step 1: Check all combinations: Found [-2,-1,1,2], [-1,0,0,1]
Output: [[-2,-1,1,2], [-1,0,0,1]]
```

2. **No Valid Quadruplets**
```
Input:  [1,2,3,4], target=100
Step 1: Check all combinations, no sum equals target
Output: []
```

### Edge Cases Handling
1. **Empty Array**
   ```
   Input: [], target=0
   Output: [] (no quadruplets possible)
   Explanation: Array size less than 4, so no valid quadruplets can be formed.
   ```

2. **Integer Overflow**
   ```
   Input: [1000000000,1000000000,1000000000,1000000000], target=4000000000
   Output: [[1000000000,1000000000,1000000000,1000000000]]
   Explanation: Using long prevents integer overflow and correctly identifies the quadruplet.
   ```

Key Observations:
1. The brute force approach explores all possible combinations using 4 nested loops
2. Using a set ensures we only collect unique quadruplets
3. Sorting each quadruplet before adding to set handles different orderings of the same numbers
4. Using long data type prevents integer overflow issues

Sample Validation:
Input: [4, 3, 3, 4, 4, 2, 1, 2, 1, 1], target=9
Expected: Quadruplets that sum to 9
Output: [[1,1,3,4], [1,2,2,4], [1,2,3,3]]

Key Points:
1. This approach has O(n⁴) time complexity, making it inefficient for large arrays
2. Using long type for sum calculation is essential to prevent overflow
3. The nested loops ensure we only use distinct indices (i < j < k < l)
4. The HashSet ensures we only include unique quadruplets in our result

TEST CASES:
1. Input: [1,0,-1,0,-2,2], target=0
   Expected: [[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]
   Output: [[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]
2. Input: [2,2,2,2,2], target=8
   Expected: [[2,2,2,2]]
   Output: [[2,2,2,2]]
3. Input: [-3,-2,-1,0,0,1,2,3], target=0
   Expected: [[-3,-2,2,3],[-3,-1,1,3],[-3,0,0,3],[-3,0,1,2],[-2,-1,0,3],[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]
   Output: [[-3,-2,2,3],[-3,-1,1,3],[-3,0,0,3],[-3,0,1,2],[-2,-1,0,3],[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]
 */