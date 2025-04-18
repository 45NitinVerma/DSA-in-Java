package Array.Four_Sum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* 
# Problem: 4Sum
Given an array nums of n integers, return an array of all the unique quadruplets [nums[a], nums[b], nums[c], nums[d]] such that:

0 <= a, b, c, d < n
a, b, c, and d are distinct.
nums[a] + nums[b] + nums[c] + nums[d] == target
You may return the answer in any order.

# Intuition
- The core idea is to use a combination of sorting and the two-pointer technique to efficiently find quadruplets.
- By fixing two elements with nested loops and using two pointers for the remaining two elements, we can avoid using four nested loops.
- Sorting enables us to skip duplicates and optimize the search with the two-pointer approach.

In this approach, we intend to get rid of two things i.e. the HashSet we were using for the look-up operation and the set data structure used to store the unique quadruplets.

Again the solution to this will be similar to the optimal approach of the  3-sum problem. In that approach, we had fixed a pointer i, and the rest 2 pointers were moving. Similarly, here, as we are dealing with quads instead of triplets we will fix 2 pointers i.e. i and j and the rest of the 2 pointers will be moving.

Now, we need to first understand what the HashSet and the set were doing to make our algorithm work without them. So, the set data structure was basically storing the unique quadruplets in sorted order and the HashSet was used to search for the fourth element.

To get the quadruplets in sorted order, we will sort the entire array in the first step and to get the unique quads, we will simply skip the duplicate numbers while moving the pointers.

## How to skip duplicate numbers:
As the entire array is sorted, the duplicate numbers will be in consecutive places. So, while moving a pointer, we will check the current element and the adjacent element. Until they become different, we will move the pointer by 1 place. We will follow this process for all 4 pointers. Thus, we can easily skip the duplicate elements while moving the pointers.

Now, we can also remove the HashSet as we have two moving pointers i.e. k and l that will find the appropriate value of nums[k] and nums[l]. So, we do not need the HashSet anymore.

The process will look like this:

        arr = [-2, -1,  0,     0,     1,      2]
               ↑    ↑   ↑    →             ←   ↑
               i    j   k    greater   lesser  l
        
Among the 4 pointers, 2 will be fixed and 2 will be moving. In each iteration, we will check if the sum i.e. nums[i]+nums[j]+nums[k]+nums[l] is equal to the target. 

- If the sum is greater, then we need lesser elements and so we will decrease the value of l. 
- If the sum is lesser than the target, we need a bigger value and so we will increase the value of k. 
- If the sum is equal to the target, we will simply insert the quad i.e. nums[i], nums[j], nums[k], and nums[l] into our answer and move the pointers k and l skipping the duplicate elements.

## Basic Understanding
The problem asks us to find all unique sets of four numbers from the input array that add up to a specific target value. With sorting and two-pointer technique, we can solve this in O(n³) time instead of the naive O(n⁴) approach.

## Key Observations with Examples

### Observation 1: Sorting Enables Optimization
Sorting the array makes it possible to:
- Skip duplicate values efficiently
- Use a two-pointer approach to find pairs with a specific sum
* Example: [1,1,2,2,3,3,4,4] - sorted array allows us to process only one occurrence of each value

### Observation 2: Two-Pointer Technique
After fixing the first two elements, we can find the remaining two elements using two pointers.
* Left pointer starts right after the second fixed element
* Right pointer starts from the end of the array
* Move pointers based on the current sum compared to the target

Example:
```
Array: [1,2,3,4,5,6], Target: 14
Fix i=0 (value 1), j=1 (value 2)
Sum needed from remaining elements: 14 - (1+2) = 11
Use k=2 (left pointer, value 3) and l=5 (right pointer, value 6)
Current sum: 3+6=9, need more, increment k
k=3 (value 4), l=5 (value 6): 4+6=10, need more, increment k
k=4 (value 5), l=5 (value 6): Can't proceed as k must be < l
```

### Observation 3: Handling Duplicates
Duplicate quadruplets must be avoided, which is handled by:
- Skipping duplicate values for the first two indices
- Skipping duplicates when moving the two pointers

Example:
```
[1,1,2,2,3,3,4,4], Target: 10
For i=0 (value 1), we process j=1,2,3...
For i=1 (value 1), we SKIP since it's a duplicate of the previous i
```

### Observation 4: Integer Overflow Prevention
Using long for the sum calculation prevents potential integer overflow when adding four integers.

## Step-by-Step Example
Let's work through array [1,0,-1,0,-2,2] with target 0:

1. Step One:
   ```
   Sort the array: [-2,-1,0,0,1,2]
   ```
   This ordering helps us skip duplicates and use the two-pointer technique.

2. Step Two:
   ```
   i=0 (value -2), j=1 (value -1)
   Two pointers: k=2 (value 0), l=5 (value 2)
   Sum: -2 + -1 + 0 + 2 = -1 < 0 (target)
   Move k right to increase sum: k=3 (value 0)
   ```

3. Step Three:
   ```
   i=0 (value -2), j=1 (value -1)
   Two pointers: k=3 (value 0), l=5 (value 2)
   Sum: -2 + -1 + 0 + 2 = -1 < 0 (target)
   Move k right to increase sum: k=4 (value 1)
   ```

4. Step Four:
   ```
   i=0 (value -2), j=1 (value -1)
   Two pointers: k=4 (value 1), l=5 (value 2)
   Sum: -2 + -1 + 1 + 2 = 0 == 0 (target)
   Found quadruplet: [-2,-1,1,2]
   Move both pointers: k=5, l=4
   Invalid state (k >= l), move to next j
   ```

5. Continue this process for all valid combinations of i and j.

## Special Cases

### Case 1: Empty Array or Array with Fewer Than 4 Elements
Input: [] or [1,2,3]
- Behavior: No quadruplets can be formed
- Result: Return empty list

### Case 2: Array with Exactly 4 Elements That Sum to Target
Input: [1,2,3,4], Target: 10
- Behavior: Only one possible quadruplet to check
- Result: [[1,2,3,4]]

### Case 3: Array with Many Duplicates
Input: [1,1,1,1,1,1], Target: 4
- Behavior: Potential for many duplicate quadruplets
- Result: Only unique quadruplets returned: [[1,1,1,1]]
*/
public class Approach3 {
    
    // Approach 3: Two-Pointer Approach with Sorting
    // Method to solve the Four Sum problem 
    public static List<List<Integer>> fourSum(int[] nums, int target) {
        int n = nums.length; // size of the array
        List<List<Integer>> ans = new ArrayList<>();

        // sort the given array to use two-pointer technique
        Arrays.sort(nums);

        // calculating the quadruplets using two nested loops and two pointers
        for (int i = 0; i < n; i++) {
            // avoid the duplicates while moving i
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            
            for (int j = i + 1; j < n; j++) {
                // avoid the duplicates while moving j
                if (j > i + 1 && nums[j] == nums[j - 1]) continue;

                // use two pointers for the remaining two elements
                int k = j + 1;   // left pointer starts just after j
                int l = n - 1;   // right pointer starts from end
                
                while (k < l) {
                    // use long to prevent potential integer overflow
                    long sum = nums[i];
                    sum += nums[j];
                    sum += nums[k];
                    sum += nums[l];
                    
                    if (sum == target) {
                        // found a quadruplet with target sum, add to result
                        List<Integer> temp = new ArrayList<>();
                        temp.add(nums[i]);
                        temp.add(nums[j]);
                        temp.add(nums[k]);
                        temp.add(nums[l]);
                        ans.add(temp);
                        
                        // move both pointers and skip duplicates
                        k++;
                        l--;
                        
                        // skip duplicate values for k
                        while (k < l && nums[k] == nums[k - 1]) k++;
                        // skip duplicate values for l
                        while (k < l && nums[l] == nums[l + 1]) l--;
                    } 
                    else if (sum < target) {
                        // current sum is too small, increment left pointer
                        k++;
                    }
                    else {
                        // current sum is too large, decrement right pointer
                        l--;
                    }
                }
            }
        }

        return ans;
    }

    // Main method for testing
    public static void main(String[] args) {
        // Example input
        int[] nums = {4, 3, 3, 4, 4, 2, 1, 2, 1, 1};
        int target = 9;
        
        // Call the method and print the output
        List<List<Integer>> ans = fourSum(nums, target);
        
        // Display the result
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
1. Sort the input array in ascending order.
2. We will use a loop(say i) that will run from 0 to n-1. This i will represent one of the fixed pointers. In each iteration, this value will be fixed for all different values of the rest of the 3 pointers. Inside the loop, we will first check if the current and the previous element is the same and if it is we will do nothing and continue to the next value of i.
3. For each valid combination of i and j:
   a. Use two pointers (k starts from j+1 and l starts from the last index) for the remaining two elements.The pointer k will move forward and the pointer l will move backward until they cross each other while the value of i and j will be fixed. 
   b. Compute the sum of the four elements.
   c. If sum equals target, add the quadruplet to the result.
   d. Adjust the pointers based on the comparison of sum and target.
4. Skip duplicate values at all levels to avoid duplicate quadruplets.
5. Return the list of all unique quadruplets found.

### Time Complexity Breakdown per Step
1. Sorting: O(n log n)
2. First loop (i): O(n)
3. Second loop (j): O(n)
4. Two-pointer loop (k and l): O(n)
5. ArrayList operations: O(1)

Total: O(n log n) + O(n³) = O(n³) since the nested loops dominate.

### Space Complexity Breakdown
1. Auxiliary Space: O(1) for variables and pointers
   - Not counting the output space which depends on the number of valid quadruplets
2. Input Space: O(n) for the input array
3. Output Space: O(quadruplets) where quadruplets is the number of valid solutions

Total: O(1) auxiliary space (excluding output)

# Advantages
1. More efficient than the brute force O(n⁴) approach.
2. Handles duplicate elements elegantly without using extra space.
3. Prevents integer overflow by using long for sum calculations.
4. Maintains uniqueness of quadruplets without additional data structures.

# Limitations
1. Requires sorting the input array, which modifies the original array.
2. Not optimal for very large arrays due to the O(n³) time complexity.
3. Two-pointer technique requires the array to be sorted.
4. Not suitable for finding quadruplets with constraints other than exact sum.

# Potential Improvements
1. Use a hash set to track seen pairs for the last two elements, potentially reducing complexity (though with additional space).
2. Implement early termination conditions based on sorted array properties.
3. Parallelize the computation for very large arrays.
4. Use a sliding window technique for specific input patterns.

# Four Sum Algorithm: Detailed Execution Table and Step-by-Step Process

## Input and Output
- **Input Array**: [1, 0, -1, 0, -2, 2]
- **Target Sum**: 0
- **Expected Output**: [[-2,-1,1,2], [-2,0,0,2], [-1,0,0,1]]

## Detailed Execution Table

| Step | Action | Array State | Pointers | Current Values | Current Sum | Comparison | Result | Next Action |
|------|--------|------------|----------|----------------|-------------|------------|--------|-------------|
| 1 | Sort array | [-2,-1,0,0,1,2] | - | - | - | - | - | Initialize i=0 |
| 2 | First i loop | [-2,-1,0,0,1,2] | i=0 | nums[i]=-2 | - | - | - | Initialize j=1 |
| 3 | First j loop | [-2,-1,0,0,1,2] | i=0, j=1 | nums[i]=-2, nums[j]=-1 | - | - | - | Initialize k=2, l=5 |
| 4 | Check sum | [-2,-1,0,0,1,2] | i=0, j=1, k=2, l=5 | (-2, -1, 0, 2) | -1 | -1 < 0 | Sum too small | Increment k to 3 |
| 5 | Check sum | [-2,-1,0,0,1,2] | i=0, j=1, k=3, l=5 | (-2, -1, 0, 2) | -1 | -1 < 0 | Sum too small | Increment k to 4 |
| 6 | Check sum | [-2,-1,0,0,1,2] | i=0, j=1, k=4, l=5 | (-2, -1, 1, 2) | 0 | 0 == 0 | Found quadruplet | Add [-2,-1,1,2], increment k to 5, decrement l to 4 |
| 7 | Check pointers | [-2,-1,0,0,1,2] | i=0, j=1, k=5, l=4 | - | - | k >= l | Invalid pointers | Move to next j value, j=2 |
| 8 | Check j | [-2,-1,0,0,1,2] | i=0, j=2 | nums[i]=-2, nums[j]=0 | - | - | - | Initialize k=3, l=5 |
| 9 | Check sum | [-2,-1,0,0,1,2] | i=0, j=2, k=3, l=5 | (-2, 0, 0, 2) | 0 | 0 == 0 | Found quadruplet | Add [-2,0,0,2], increment k to 4, decrement l to 4 |
| 10 | Check pointers | [-2,-1,0,0,1,2] | i=0, j=2, k=4, l=4 | - | - | k >= l | Invalid pointers | Move to next j, but check if j+1 is duplicate |
| 11 | Check duplicate j | [-2,-1,0,0,1,2] | i=0, j=3 | nums[j-1]=0, nums[j]=0 | - | nums[j]==nums[j-1] | j is duplicate | Skip to next j value, j=3 |
| 12 | Check sum | [-2,-1,0,0,1,2] | i=0, j=3, k=4, l=5 | (-2, 0, 1, 2) | 1 | 1 > 0 | Sum too large | Decrement l to 4 |
| 13 | Check pointers | [-2,-1,0,0,1,2] | i=0, j=3, k=4, l=4 | - | - | k >= l | Invalid pointers | Move to next j, j=4 |
| 14 | Check j range | [-2,-1,0,0,1,2] | i=0, j=4 | - | - | j < n | Valid j | Initialize k=5, l=5 |
| 15 | Check pointers | [-2,-1,0,0,1,2] | i=0, j=4, k=5, l=5 | - | - | k >= l | Invalid pointers | Move to next i, i=1 |
| 16 | Second i loop | [-2,-1,0,0,1,2] | i=1 | nums[i]=-1 | - | - | - | Initialize j=2 |
| 17 | Check sum | [-2,-1,0,0,1,2] | i=1, j=2, k=3, l=5 | (-1, 0, 0, 2) | 1 | 1 > 0 | Sum too large | Decrement l to 4 |
| 18 | Check sum | [-2,-1,0,0,1,2] | i=1, j=2, k=3, l=4 | (-1, 0, 0, 1) | 0 | 0 == 0 | Found quadruplet | Add [-1,0,0,1], increment k to 4, decrement l to 3 |
| 19 | Check pointers | [-2,-1,0,0,1,2] | i=1, j=2, k=4, l=3 | - | - | k > l | Invalid pointers | Move to next j, but check if j+1 is duplicate |
| 20 | Check duplicate j | [-2,-1,0,0,1,2] | i=1, j=3 | nums[j-1]=0, nums[j]=0 | - | nums[j]==nums[j-1] | j is duplicate | Skip to next j, j=4 |
| 21 | Continue process for all i, j combinations... |
| 22 | Process completed | - | - | - | - | - | Final Result | [[-2,-1,1,2], [-2,0,0,2], [-1,0,0,1]] |

## Step-by-Step Process Explanation

1. **Array Sorting**
   - Original array `[1, 0, -1, 0, -2, 2]` is sorted to `[-2, -1, 0, 0, 1, 2]`
   - Sorting enables us to use the two-pointer technique and easily skip duplicates

2. **Outer Loop Initialization (i=0)**
   - Start with the first element `-2` at index 0
   - This will be the first element in our potential quadruplets

3. **Inner Loop Initialization (j=1)**
   - Second element `-1` at index 1
   - Combined with the first element, we have `-2` and `-1`

4. **Two Pointers Setup (k=2, l=5)**
   - Left pointer k starts at index 2 (value `0`)
   - Right pointer l starts at index 5 (value `2`)
   - We now have four elements: `-2`, `-1`, `0`, `2`

5. **First Sum Check**
   - Sum = -2 + (-1) + 0 + 2 = -1
   - -1 < 0 (target), so the sum is too small
   - Increment k to increase the sum: k becomes 3 (still value `0`)

6. **Second Sum Check**
   - Sum = -2 + (-1) + 0 + 2 = -1 (still the same, as nums[k] is still 0)
   - -1 < 0 (target), so the sum is too small
   - Increment k to increase the sum: k becomes 4 (value `1`)

7. **Third Sum Check - First Quadruplet Found**
   - Sum = -2 + (-1) + 1 + 2 = 0
   - 0 == 0 (target), we found a valid quadruplet!
   - Add [-2, -1, 1, 2] to the result
   - Move both pointers: k++ (becomes 5), l-- (becomes 4)

8. **Pointer Validity Check**
   - Now k=5, l=4, which means k >= l
   - This is an invalid state, so we move to the next j value

9. **Process Next j Value (j=2)**
   - Second element is now `0` at index 2
   - Reset pointers: k=3 (value `0`), l=5 (value `2`)
   - Current elements: `-2`, `0`, `0`, `2`

10. **Fourth Sum Check - Second Quadruplet Found**
    - Sum = -2 + 0 + 0 + 2 = 0
    - 0 == 0 (target), we found another valid quadruplet!
    - Add [-2, 0, 0, 2] to the result
    - Move both pointers: k++ (becomes 4), l-- (becomes 4)

11. **Duplicate Handling for j**
    - We notice nums[3] == nums[2] both are `0`
    - Skip this duplicate value to avoid duplicate quadruplets
    - This is handled by the condition: `if (j > i + 1 && nums[j] == nums[j - 1]) continue;`

12. **Process for i=1, j=2**
    - First element is now `-1` at index 1
    - Second element is `0` at index 2
    - Pointers: k=3 (value `0`), l=5 (value `2`)
    - Current elements: `-1`, `0`, `0`, `2`

13. **Sum Check for New Combination**
    - Sum = -1 + 0 + 0 + 2 = 1
    - 1 > 0 (target), so the sum is too large
    - Decrement l to decrease sum: l becomes 4 (value `1`)

14. **Final Quadruplet Found**
    - Sum = -1 + 0 + 0 + 1 = 0
    - 0 == 0 (target), we found the third valid quadruplet!
    - Add [-1, 0, 0, 1] to the result
    - Move pointers: k++ (becomes 4), l-- (becomes 3)

15. **Complete Execution**
    - Continue this process for all valid combinations of i and j
    - Skip duplicates at each level to ensure all quadruplets are unique
    - Return the final list of quadruplets: [[-2,-1,1,2], [-2,0,0,2], [-1,0,0,1]]

## Key Points About the Process

1. **Sorting Benefits**
   - Enables efficient duplicate skipping
   - Allows the two-pointer technique to work effectively
   - Makes the algorithm more predictable as values increase from left to right

2. **Duplicate Handling**
   - For index i: `if (i > 0 && nums[i] == nums[i - 1]) continue;`
   - For index j: `if (j > i + 1 && nums[j] == nums[j - 1]) continue;`
   - For index k: `while (k < l && nums[k] == nums[k - 1]) k++;`
   - For index l: `while (k < l && nums[l] == nums[l + 1]) l--;`

3. **Two-Pointer Movement Logic**
   - If sum < target: Increment k to increase the sum
   - If sum > target: Decrement l to decrease the sum
   - If sum == target: Add quadruplet, then increment k and decrement l

4. **Early Termination Optimizations**
   - After sorting, we could add additional checks:
     - If nums[i] > 0 and nums[i] > target, break (all remaining values will be larger)
     - If nums[i] + nums[j] + nums[k] + nums[l] < target and nums[i] + nums[j] + nums[n-2] + nums[n-1] < target, break j loop

5. **Integer Overflow Prevention**
   - Using long type for sum calculation prevents potential overflow
   - This is important when dealing with large integer values

### Step-by-Step Explanation

1. **Sort the Array**
   - Original array [1,0,-1,0,-2,2] becomes [-2,-1,0,0,1,2]
   ```
   [-2, -1, 0, 0, 1, 2]
    ^    ^  ^  ^  ^  ^
   ```

2. **First Iteration (i=0, j=1)**
   - Fix first element as -2 and second as -1
   - Use two pointers: k=2 (value 0) and l=5 (value 2)
   ```
   [-2, -1, 0, 0, 1, 2]
    i    j  k     ^  l
   ```
   - Sum = -2 + -1 + 0 + 2 = -1 < 0, increment k

3. **Adjust Pointers**
   - k=3 (value 0), l=5 (value 2)
   ```
   [-2, -1, 0, 0, 1, 2]
    i    j     k  ^  l
   ```
   - Sum = -2 + -1 + 0 + 2 = -1 < 0, increment k

4. **Find First Quadruplet**
   - k=4 (value 1), l=5 (value 2)
   ```
   [-2, -1, 0, 0, 1, 2]
    i    j        k  l
   ```
   - Sum = -2 + -1 + 1 + 2 = 0 == target
   - Add [-2,-1,1,2] to result
   - Increment k, decrement l: k=5, l=4 (invalid state)

5. **Continue with Next j**
   - i=0, j=2 (value 0)
   - k=3 (value 0), l=5 (value 2)
   ```
   [-2, -1, 0, 0, 1, 2]
    i       j  k     l
   ```
   - Sum = -2 + 0 + 0 + 2 = 0 == target
   - Add [-2,0,0,2] to result

6. **Skip Duplicate Value for j**
   - For i=0, j=3 (value 0) would be a duplicate of j=2, but the algorithm checks this
   ```
   [-2, -1, 0, 0, 1, 2]
    i       j  ^
             |
           duplicate
   ```

### Additional Example Cases

1. **Array with Duplicates**
```
Input:  [4, 3, 3, 4, 4, 2, 1, 2, 1, 1], Target: 9
Sort:   [1, 1, 1, 2, 2, 3, 3, 4, 4, 4]
Step 1: i=0 (value 1), j=1 (value 1), k=2 (value 1), l=9 (value 4)
        Sum = 1+1+1+4 = 7 < 9, increment k
Step 2: i=0, j=1, k=3 (value 2), l=9 (value 4)
        Sum = 1+1+2+4 = 8 < 9, increment k
Step 3: i=0, j=1, k=4 (value 2), l=9 (value 4)
        Sum = 1+1+2+4 = 8 < 9, increment k
Output: [[1,1,3,4], [1,2,2,4], [2,3,4,0]]
```

2. **Target Not Possible**
```
Input:  [1, 2, 3, 4], Target: 100
Sort:   [1, 2, 3, 4] (already sorted)
Step 1: i=0 (value 1), j=1 (value 2), k=2 (value 3), l=3 (value 4)
        Sum = 1+2+3+4 = 10 < 100, increment k
Step 2: k>=l (invalid state), no quadruplet found
Output: []
```

### Edge Cases Handling
1. **Array with Fewer Than 4 Elements**
   ```
   Input: [1, 2, 3]
   Output: []
   The algorithm implicitly handles this by not entering the loops when n < 4.
   ```

2. **Large Numbers with Potential Overflow**
   ```
   Input: [1000000000, 1000000000, 1000000000, 1000000000], Target: 4000000000
   Output: [[1000000000,1000000000,1000000000,1000000000]]
   Using long for sum calculation prevents overflow.
   ```

3. **All Zeros with Target Zero**
   ```
   Input: [0, 0, 0, 0, 0], Target: 0
   Output: [[0,0,0,0]]
   Duplicate handling ensures only one quadruplet is returned.
   ```

Key Observations:
1. The two-pointer technique efficiently reduces the time complexity from O(n⁴) to O(n³).
2. Sorting and duplicate skipping ensure all quadruplets are unique.
3. Long type for sum calculation prevents integer overflow.
4. The algorithm handles edge cases gracefully.

Sample Validation:
Input: [1, 0, -1, 0, -2, 2], Target: 0
Expected: [[-2,-1,1,2], [-2,0,0,2], [-1,0,0,1]]
Output: [[-2,-1,1,2], [-2,0,0,2], [-1,0,0,1]]

Key Points:
1. Sort first to enable efficient two-pointer technique.
2. Skip duplicates at all levels to ensure uniqueness.
3. Use long for sum calculation to prevent overflow.
4. Adjust pointers based on sum comparison with target.

TEST CASES:
1. Input: [1,0,-1,0,-2,2], Target: 0
   Expected: [[-2,-1,1,2], [-2,0,0,2], [-1,0,0,1]]
   Output: [[-2,-1,1,2], [-2,0,0,2], [-1,0,0,1]]
2. Input: [2,2,2,2,2], Target: 8
   Expected: [[2,2,2,2]]
   Output: [[2,2,2,2]]
3. Input: [4,3,3,4,4,2,1,2,1,1], Target: 9
   Expected: [[1,1,3,4], [1,2,2,4], [2,3,4,0]] 
   Output: [[1,1,3,4], [1,2,2,4], [2,3,4,0]]
 */