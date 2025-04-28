package Array.Merge_Sorted_Arrays;
/* 
# Problem: Merge Sorted Arrays
You are given two integer arrays nums1 and nums2, sorted in non-decreasing order, and two integers m and n, representing the number of elements in nums1 and nums2 respectively.
Merge nums1 and nums2 into a single array sorted in non-decreasing order.
The final sorted array should not be returned by the function, but instead be stored inside the array nums1. To accommodate this, nums1 has a length of m + n, where the first m elements denote the elements that should be merged, and the last n elements are set to 0 and should be ignored. nums2 has a length of n.

Example 1:
Input: nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
Output: [1,2,2,3,5,6]
Explanation: The arrays we are merging are [1,2,3] and [2,5,6].
The result of the merge is [1,2,2,3,5,6] with the underlined elements coming from nums1.
                            - -   -

# Intuition
- Merging two sorted arrays can be done by filling the result array from the end
- By starting from the largest elements of both arrays, we can place elements in their final position without needing extra space
- This approach eliminates the need for shifting elements or using temporary arrays

## Basic Understanding
The problem asks us to merge two sorted arrays, nums1 and nums2, where nums1 has extra space at the end to accommodate all elements. Instead of using extra space, we can work backwards from the end of nums1, placing the larger elements first.

## Key Observations with Examples

### Observation 1: Working Backwards
- Since nums1 has extra space at the end, we can start filling from the back
- This prevents overwriting elements that haven't been processed yet
- We compare the largest elements from both arrays and place the larger one at the end of nums1

### Observation 2: Two Pointer Technique
- We need three pointers:
  * One pointing to the last valid element in nums1 (i)
  * One pointing to the last element in nums2 (j)
  * One pointing to the current position to fill in nums1 (idx)
```
nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
          ^     ^
        i=2   idx=5  (0-indexed)
nums2 = [2,5,6]
           ^
         j=2  (0-indexed)
```

### Observation 3: Handling Remaining Elements
- After the main comparison loop, one of the arrays might still have elements left
- If nums2 has remaining elements, they need to be copied to nums1
- If nums1 has remaining elements, they're already in their correct position

## Step-by-Step Example
Let's work through the example: nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3

1. Step One:
   ```
   nums1 = [1,2,3,0,0,0], i = 2, idx = 5
   nums2 = [2,5,6], j = 2
   
   Compare nums1[i]=3 with nums2[j]=6
   6 > 3, so nums1[idx] = 6, j--, idx--
   ```
   nums1 = [1,2,3,0,0,6], i = 2, idx = 4
   nums2 = [2,5,6], j = 1

2. Step Two:
   ```
   Compare nums1[i]=3 with nums2[j]=5
   5 > 3, so nums1[idx] = 5, j--, idx--
   ```
   nums1 = [1,2,3,0,5,6], i = 2, idx = 3
   nums2 = [2,5,6], j = 0

3. Step Three:
   ```
   Compare nums1[i]=3 with nums2[j]=2
   3 > 2, so nums1[idx] = 3, i--, idx--
   ```
   nums1 = [1,2,3,3,5,6], i = 1, idx = 2
   nums2 = [2,5,6], j = 0

4. Step Four:
   ```
   Compare nums1[i]=2 with nums2[j]=2
   2 = 2, so nums1[idx] = 2, i--, idx--
   ```
   nums1 = [1,2,2,3,5,6], i = 0, idx = 1
   nums2 = [2,5,6], j = 0

5. Step Five:
   ```
   nums2 is not depleted. j = 0, idx = 1
   There's still nums2[0] = 2 to place
   Since 2 <= nums1[0]=1, we place nums1[0] first
   Compare nums1[i]=1 with nums2[j]=2
   2 > 1, so nums1[idx] = 2, j--, idx--
   ```
   nums1 = [1,2,2,3,5,6], i = 0, idx = 0
   nums2 = [2,5,6], j = -1 (depleted)

6. Step Six:
   ```
   nums2 is now depleted.
   Copy the rest of nums1 if any (nums1[0] = 1)
   ```
   Final: nums1 = [1,2,2,3,5,6]

## Special Cases

### Case 1: nums2 is Empty (n = 0)
Input: nums1 = [1,2,3], m = 3, nums2 = [], n = 0
- No merging needed
- Result: [1,2,3]

### Case 2: nums1 is Empty (m = 0)
Input: nums1 = [0,0,0], m = 0, nums2 = [1,2,3], n = 3
- Copy all elements from nums2 to nums1
- Result: [1,2,3]

### Case 3: Both Arrays Have Equal Elements
Input: nums1 = [1,2,3,0,0,0], m = 3, nums2 = [1,2,3], n = 3
- All elements will be compared and merged in order
- Result: [1,1,2,2,3,3]
*/

public class Approach2 {
    // Approach 2: Using 3 pointers without extra space
    // This approach fills nums1 from the end to avoid overwriting elements
    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = m - 1;     // Last valid element in nums1
        int j = n - 1;     // Last element in nums2
        int idx = m + n - 1;  // Last position in merged array

        // While both arrays have elements to process
        while (i >= 0 && j >= 0) {
            // Compare the current elements from both arrays
            // Place the larger element at the current position idx
            if (nums1[i] > nums2[j]) {
                nums1[idx--] = nums1[i--];  // Take element from nums1
            } else {
                nums1[idx--] = nums2[j--];  // Take element from nums2
            }
        }

        // If there are remaining elements in nums2, copy them to nums1
        // (No need to handle remaining elements in nums1 as they're already in their correct position)
        while (j >= 0) {
            nums1[idx--] = nums2[j--];
        }
    }

    // Main method for testing
    public static void main(String[] args) {
        // Example 1
        int[] nums1 = {1, 2, 3, 0, 0, 0};
        int m = 3;
        int[] nums2 = {2, 5, 6};
        int n = 3;
        
        System.out.println("Before merge:");
        printArray(nums1);
        
        merge(nums1, m, nums2, n);
        
        System.out.println("After merge:");
        printArray(nums1);
        
        // Example 2
        int[] nums3 = {1};
        int m2 = 1;
        int[] nums4 = {};
        int n2 = 0;
        
        System.out.println("\nExample 2 - Before merge:");
        printArray(nums3);
        
        merge(nums3, m2, nums4, n2);
        
        System.out.println("Example 2 - After merge:");
        printArray(nums3);
        
        // Example 3
        int[] nums5 = {0};
        int m3 = 0;
        int[] nums6 = {1};
        int n3 = 1;
        
        System.out.println("\nExample 3 - Before merge:");
        printArray(nums5);
        
        merge(nums5, m3, nums6, n3);
        
        System.out.println("Example 3 - After merge:");
        printArray(nums5);
    }
    
    // Helper method to print arrays
    private static void printArray(int[] arr) {
        System.out.print("[");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            if (i < arr.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }
}

/*
# Algorithm
1. Initialize three pointers:
   - i pointing to the last valid element in nums1 (index m-1)
   - j pointing to the last element in nums2 (index n-1)
   - idx pointing to the last position in the final merged array (index m+n-1)
2. Compare the elements at i and j and place the larger one at position idx
3. Decrement the pointer of the array from which we took the element
4. Decrement idx to move to the next position to fill
5. Repeat steps 2-4 until either i or j becomes negative
6. If j is still non-negative, copy the remaining elements from nums2 to nums1
7. If i is still non-negative, no action needed as elements are already in place

### Time Complexity Breakdown per Step
1. Initialization of pointers: O(1)
2. While loop comparing and merging elements: O(m+n)
3. Handling remaining elements in nums2: O(n) in worst case

Total: O(m+n) - We process each element exactly once

### Space Complexity Breakdown
1. Auxiliary Space: O(1)
   - We only use three pointers regardless of input size
2. Input Space: O(m+n)
   - The original arrays take O(m+n) space
   - But since we're modifying nums1 in-place, no additional space is needed

Total: O(1) auxiliary space

# Advantages
1. In-place operation - no extra space required
2. Linear time complexity O(m+n)
3. Works from the end, avoiding costly shifts or overwrites
4. Simple implementation with only three pointers
5. Handles cases where one array is empty elegantly

# Limitations
1. Modifies the input array nums1 directly
2. Requires nums1 to have enough capacity (m+n) to store the merged result
3. Can't be used if we need to preserve the original arrays

# Potential Improvements
1. Add input validation to ensure m and n are non-negative
2. Add checks to verify that nums1 has enough space (length >= m+n)
3. For very large arrays, could implement a parallel version for certain segments
4. Could handle equal elements more intentionally (e.g., to maintain stability)

# Step-by-Step Process with Dry Run
# Detailed Dry Run: Merge Sorted Arrays

Let's walk through the algorithm step by step with the example:
- nums1 = [1,2,3,0,0,0], m = 3
- nums2 = [2,5,6], n = 3

## Initial Setup
- i = m-1 = 2 (last valid element in nums1)
- j = n-1 = 2 (last element in nums2)
- idx = m+n-1 = 5 (last position in merged array)

## Detailed Execution Table

| Step | nums1 State | nums1[i] | nums2[j] | Comparison | Action | New Pointers | Explanation |
|------|-------------|-----------|-----------|------------|--------|--------------|-------------|
| 0 | [1,2,3,0,0,0] | 3 | 6 | 3 < 6 | nums1[5] = 6 | i=2, j=1, idx=4 | Initialize pointers and compare first elements. 6 > 3, so place 6 at the end. |
| 1 | [1,2,3,0,0,6] | 3 | 5 | 3 < 5 | nums1[4] = 5 | i=2, j=0, idx=3 | Compare 3 and 5. 5 > 3, so place 5 at position 4. |
| 2 | [1,2,3,0,5,6] | 3 | 2 | 3 > 2 | nums1[3] = 3 | i=1, j=0, idx=2 | Compare 3 and 2. 3 > 2, so place 3 at position 3. |
| 3 | [1,2,3,3,5,6] | 2 | 2 | 2 = 2 | nums1[2] = 2 | i=1, j=-1, idx=1 | Compare 2 and 2. For equal values, we take from nums2 (could also take from nums1). |
| 4 | [1,2,2,3,5,6] | 2 | N/A | N/A | N/A | i=1, j=-1, idx=1 | nums2 is depleted. Only nums1 elements remain. |
| 5 | [1,2,2,3,5,6] | 1 | N/A | N/A | nums1[1] = 1 | i=0, j=-1, idx=0 | Copy remaining nums1 element to its position (which is already there). |
| Final | [1,2,2,3,5,6] | N/A | N/A | N/A | N/A | i=-1, j=-1, idx=-1 | All elements processed. |

## Step-by-Step Process Explanation

### Step 0: Initialization
- Initialize pointers: i=2, j=2, idx=5
- nums1 = [1,2,3,0,0,0]
- nums2 = [2,5,6]
- Compare nums1[i]=3 with nums2[j]=6
- Since 6 > 3, we place 6 at position idx=5
- Update: nums1[5] = 6
- Decrement j to 1 and idx to 4
- New state: nums1 = [1,2,3,0,0,6]

### Step 1: Second Comparison
- Current pointers: i=2, j=1, idx=4
- Compare nums1[i]=3 with nums2[j]=5
- Since 5 > 3, we place 5 at position idx=4
- Update: nums1[4] = 5
- Decrement j to 0 and idx to 3
- New state: nums1 = [1,2,3,0,5,6]

### Step 2: Third Comparison
- Current pointers: i=2, j=0, idx=3
- Compare nums1[i]=3 with nums2[j]=2
- Since 3 > 2, we place 3 at position idx=3
- Update: nums1[3] = 3
- Decrement i to 1 and idx to 2
- New state: nums1 = [1,2,3,3,5,6]

### Step 3: Fourth Comparison
- Current pointers: i=1, j=0, idx=2
- Compare nums1[i]=2 with nums2[j]=2
- Since they're equal, we choose to place nums2[j]=2
- Update: nums1[2] = 2
- Decrement j to -1 and idx to 1
- New state: nums1 = [1,2,2,3,5,6]

### Step 4: Handle Remaining Elements
- Current pointers: i=1, j=-1, idx=1
- nums2 is depleted (j < 0)
- Only elements in nums1 remain
- Since elements in nums1 are already in their correct positions, no action is needed
- New state: nums1 = [1,2,2,3,5,6]

### Step 5: Final Element
- Current pointers: i=0, j=-1, idx=0
- Only one element in nums1 remains: nums1[0]=1
- It's already in the correct position
- No action needed
- Final state: nums1 = [1,2,2,3,5,6]

## Key Points in the Process
1. The algorithm works by filling the array from the end, placing the larger elements first
2. At each step, we compare the current elements from both arrays and place the larger one
3. When nums2 is depleted, no further action is needed as the remaining nums1 elements are already in position
4. When elements are equal, we can choose either one (the implementation chooses nums2)
5. The final result is a merged sorted array stored in nums1

## Additional Test Case Execution

Let's trace another example:
- nums1 = [4,5,6,0,0,0], m = 3
- nums2 = [1,2,3], n = 3

| Step | nums1 State | nums1[i] | nums2[j] | Comparison | Action | New Pointers | Explanation |
|------|-------------|-----------|-----------|------------|--------|--------------|-------------|
| 0 | [4,5,6,0,0,0] | 6 | 3 | 6 > 3 | nums1[5] = 6 | i=1, j=2, idx=4 | 6 > 3, place 6 at the end |
| 1 | [4,5,6,0,0,6] | 5 | 3 | 5 > 3 | nums1[4] = 5 | i=0, j=2, idx=3 | 5 > 3, place 5 at position 4 |
| 2 | [4,5,6,0,5,6] | 4 | 3 | 4 > 3 | nums1[3] = 4 | i=-1, j=2, idx=2 | 4 > 3, place 4 at position 3 |
| 3 | [4,5,6,4,5,6] | N/A | 3 | N/A | nums1[2] = 3 | i=-1, j=1, idx=1 | nums1 depleted, copy remaining nums2 elements |
| 4 | [4,5,3,4,5,6] | N/A | 2 | N/A | nums1[1] = 2 | i=-1, j=0, idx=0 | Continue copying from nums2 |
| 5 | [4,2,3,4,5,6] | N/A | 1 | N/A | nums1[0] = 1 | i=-1, j=-1, idx=-1 | Copy last element from nums2 |
| Final | [1,2,3,4,5,6] | N/A | N/A | N/A | N/A | i=-1, j=-1, idx=-1 | All elements processed |

## Why This Approach Works
1. **Avoiding Overwrites**: By starting from the end, we avoid overwriting elements that haven't been processed yet
2. **In-place Operation**: No extra space needed beyond the existing arrays
3. **Linear Time**: Each element is processed exactly once, resulting in O(m+n) time complexity
4. **Handling Edge Cases**: The approach naturally handles cases where one array is empty
5. **Stability**: Equal elements maintain their relative order in the final array

### Step-by-Step Explanation

1. **Initialize Pointers**
   - Set i = m-1 = 2 (last element in nums1)
   - Set j = n-1 = 2 (last element in nums2)
   - Set idx = m+n-1 = 5 (last position in merged array)
   ```
   nums1 = [1,2,3,0,0,0]
            ^   ^
           i=2 idx=5
   nums2 = [2,5,6]
              ^
             j=2
   ```

2. **First Comparison**
   - Compare nums1[i]=3 and nums2[j]=6
   - Since 6 > 3, set nums1[idx] = 6
   - Decrement j to 1 and idx to 4
   ```
   nums1 = [1,2,3,0,0,6]
            ^     ^
           i=2   idx=4
   nums2 = [2,5,6]
            ^
           j=1
   ```

3. **Second Comparison**
   - Compare nums1[i]=3 and nums2[j]=5
   - Since 5 > 3, set nums1[idx] = 5
   - Decrement j to 0 and idx to 3
   ```
   nums1 = [1,2,3,0,5,6]
            ^   ^
           i=2 idx=3
   nums2 = [2,5,6]
            ^
           j=0
   ```

4. **Third Comparison**
   - Compare nums1[i]=3 and nums2[j]=2
   - Since 3 > 2, set nums1[idx] = 3
   - Decrement i to 1 and idx to 2
   ```
   nums1 = [1,2,3,3,5,6]
            ^ ^
           i=1 idx=2
   nums2 = [2,5,6]
            ^
           j=0
   ```

5. **Fourth Comparison**
   - Compare nums1[i]=2 and nums2[j]=2
   - Since they're equal, we choose nums2[j]
   - Set nums1[idx] = 2
   - Decrement j to -1 and idx to 1
   ```
   nums1 = [1,2,2,3,5,6]
            ^ ^
           i=1 idx=1
   nums2 = [2,5,6]
           ^
          j=-1 (depleted)
   ```

6. **Handle Remaining Elements**
   - nums2 is depleted (j < 0)
   - We still have nums1[i]=1 to consider
   - Since it's already in position, no action needed
   - Final array: [1,2,2,3,5,6]

### Additional Example Cases

1. **Both Arrays Have Distinct Elements**
```
Input:  nums1 = [1,3,5,0,0,0], m = 3, nums2 = [2,4,6], n = 3
Step 1: Compare 5 and 6, place 6 at end → [1,3,5,0,0,6]
Step 2: Compare 5 and 4, place 5 at second-to-last → [1,3,5,0,5,6]
Step 3: Compare 3 and 4, place 4 at position 3 → [1,3,5,4,5,6]
Step 4: Compare 3 and 2, place 3 at position 2 → [1,3,3,4,5,6]
Step 5: Compare 1 and 2, place 2 at position 1 → [1,2,3,4,5,6]
Step 6: Only 1 remains, place at position 0 → [1,2,3,4,5,6]
Output: [1,2,3,4,5,6]
```

2. **One Array Empty (m = 0)**
```
Input:  nums1 = [0,0,0], m = 0, nums2 = [1,2,3], n = 3
Step 1: nums1 has no valid elements (i < 0)
Step 2: Copy all elements from nums2 → [1,2,3]
Output: [1,2,3]
```

### Edge Cases Handling
1. **nums2 is Empty (n = 0)**
   ```
   Input: nums1 = [1,2,3], m = 3, nums2 = [], n = 0
   Action: The while loop never executes as j < 0
   Output: [1,2,3]
   Explanation: No merging needed, nums1 remains unchanged
   ```

2. **nums1 is Empty (m = 0)**
   ```
   Input: nums1 = [0,0,0], m = 0, nums2 = [1,2,3], n = 3
   Action: First while loop skipped as i < 0, second while loop copies nums2 to nums1
   Output: [1,2,3]
   Explanation: All elements from nums2 are copied to nums1
   ```

3. **Equal Elements**
   ```
   Input: nums1 = [2,2,2,0,0,0], m = 3, nums2 = [2,2,2], n = 3
   Action: Elements are merged, taking nums2 first when equal
   Output: [2,2,2,2,2,2]
   Explanation: Equal elements are merged in a stable manner
   ```

Key Observations:
1. The algorithm correctly handles cases where one of the arrays is empty
2. When nums2 is depleted, no extra processing is needed for nums1
3. The approach is stable - equal elements maintain their relative order
4. Working from the end is crucial to avoid overwriting elements
5. The time complexity is optimal at O(m+n)

Sample Validation:
Input: nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
Expected: [1,2,2,3,5,6]
Output: [1,2,2,3,5,6]

TEST CASES:
1. Input: nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
   Expected: [1,2,2,3,5,6]
   Output: [1,2,2,3,5,6]
2. Input: nums1 = [1], m = 1, nums2 = [], n = 0
   Expected: [1]
   Output: [1]
3. Input: nums1 = [0], m = 0, nums2 = [1], n = 1
   Expected: [1]
   Output: [1]
4. Input: nums1 = [4,5,6,0,0,0], m = 3, nums2 = [1,2,3], n = 3
   Expected: [1,2,3,4,5,6]
   Output: [1,2,3,4,5,6]
5. Input: nums1 = [1,3,5,7,0,0,0,0], m = 4, nums2 = [2,4,6,8], n = 4
   Expected: [1,2,3,4,5,6,7,8]
   Output: [1,2,3,4,5,6,7,8]
 */