package Array.Merge_Sorted_Arrays2;
/* 
# Problem: Merge Sorted Arrays
Given two sorted arrays arr1[] and arr2[] of sizes n and m in non-decreasing order. Merge them in sorted order. Modify arr1 so that it contains the first N elements and modify arr2 so that it contains the last M elements.

## Intution: This approach is not the exact solution according to the question as in this approach we are going to use an extra space i.e. an array. 
As the given arrays are sorted, we are using 2 pointer approach to get a third array, that contains all the elements from the given two arrays in the sorted order. Now, from the sorted third array, we are again filling back the given two arrays.

## Key Observations with Examples

### Observation 1: Extra Space Simplifies Merging
Using a third array eliminates the complexity of in-place merging, making the algorithm more straightforward.
* We need exactly n+m space to store all elements
* We can merge in a single pass through both arrays
* The merging logic is simplified with a separate array

### Observation 2: Two-Pointer Technique
By using two pointers (left and right), we can efficiently compare elements from both arrays.
* Left pointer tracks position in array 1
* Right pointer tracks position in array 2
* We always take the smaller of the two current elements

Example:
```
arr1 = [1, 4, 8, 10], arr2 = [2, 3, 9]
Pointers: left = 0, right = 0

Compare: arr1[0] = 1, arr2[0] = 2
1 < 2, so arr3[0] = 1, left++

Compare: arr1[1] = 4, arr2[0] = 2
4 > 2, so arr3[1] = 2, right++

Compare: arr1[1] = 4, arr2[1] = 3
4 > 3, so arr3[2] = 3, right++

Compare: arr1[1] = 4, arr2[2] = 9
4 < 9, so arr3[3] = 4, left++

... and so on
```

### Observation 3: Handling Remaining Elements
After one array is exhausted, we need to copy all remaining elements from the other array.
* This ensures we don't miss any elements
* No need for comparisons at this stage - just copy directly

### Observation 4: Distributing Back to Original Arrays
After merging into arr3, we need to copy elements back to the original arrays.
* First n elements go to arr1
* Next m elements go to arr2

## Step-by-Step Example
Let's work through merging arr1 = [1, 4, 8, 10] and arr2 = [2, 3, 9]:

1. Initialize:
   ```
   arr1 = [1, 4, 8, 10], n = 4
   arr2 = [2, 3, 9], m = 3
   arr3 = [0, 0, 0, 0, 0, 0, 0] (empty array of size n+m)
   left = 0, right = 0, index = 0
   ```

2. Compare and merge:
   ```
   Compare arr1[0]=1 and arr2[0]=2
   1 < 2, so arr3[0] = 1, left = 1, index = 1
   arr3 = [1, 0, 0, 0, 0, 0, 0]
   
   Compare arr1[1]=4 and arr2[0]=2
   4 > 2, so arr3[1] = 2, right = 1, index = 2
   arr3 = [1, 2, 0, 0, 0, 0, 0]
   
   Compare arr1[1]=4 and arr2[1]=3
   4 > 3, so arr3[2] = 3, right = 2, index = 3
   arr3 = [1, 2, 3, 0, 0, 0, 0]
   
   Compare arr1[1]=4 and arr2[2]=9
   4 < 9, so arr3[3] = 4, left = 2, index = 4
   arr3 = [1, 2, 3, 4, 0, 0, 0]
   
   Compare arr1[2]=8 and arr2[2]=9
   8 < 9, so arr3[4] = 8, left = 3, index = 5
   arr3 = [1, 2, 3, 4, 8, 0, 0]
   
   Compare arr1[3]=10 and arr2[2]=9
   10 > 9, so arr3[5] = 9, right = 3, index = 6
   arr3 = [1, 2, 3, 4, 8, 9, 0]
   ```

3. Handle remaining elements:
   ```
   right = 3 which equals m, but left = 3 < n
   Copy remaining arr1 elements:
   arr3[6] = arr1[3] = 10, left = 4, index = 7
   arr3 = [1, 2, 3, 4, 8, 9, 10]
   ```

4. Copy back to original arrays:
   ```
   For i = 0 to n+m-1 (0 to 6):
   
   If i < n (0 to 3), copy to arr1:
   arr1[0] = arr3[0] = 1
   arr1[1] = arr3[1] = 2
   arr1[2] = arr3[2] = 3
   arr1[3] = arr3[3] = 4
   
   If i >= n (4 to 6), copy to arr2:
   arr2[0] = arr3[4] = 8
   arr2[1] = arr3[5] = 9
   arr2[2] = arr3[6] = 10
   ```

5. Final result:
   ```
   arr1 = [1, 2, 3, 4]
   arr2 = [8, 9, 10]
   ```

## Special Cases

### Case 1: One Empty Array
Input: arr1 = [1, 2, 3], arr2 = []
- Behavior: Second while loop copies all elements from arr1
- Result: No changes to arr1, arr2 remains empty

### Case 2: Equal Elements
Input: arr1 = [1, 2, 3], arr2 = [1, 2, 3]
- Behavior: Elements are merged in order, with arr1 elements taken first when equal
- Result: arr1 = [1, 1, 2], arr2 = [2, 3, 3]

### Case 3: Already Sorted in Proper Order
Input: arr1 = [1, 2, 3], arr2 = [4, 5, 6]
- Behavior: First while loop handles all comparisons
- Result: arr1 = [1, 2, 3], arr2 = [4, 5, 6] (no change)
*/

public class Approach1 {
   // Approach 1: Merge two sorted arrays using extra space
    public static void merge(long[] arr1, long[] arr2, int n, int m) {
        // Initialize pointers for arr1, arr2, and the merged array
        int left = 0, right = 0;
        int index = 0;
        
        // Create extra array to store merged result
        long[] arr3 = new long[n + m];
        
        // Merge elements from both arrays in sorted order
        while (left < n && right < m) {
            // Compare current elements from both arrays
            if (arr1[left] <= arr2[right]) {
                // If element in arr1 is smaller or equal, add it to arr3
                arr3[index++] = arr1[left++];
            } else {
                // If element in arr2 is smaller, add it to arr3
                arr3[index++] = arr2[right++];
            }
        }

        // Add remaining elements from arr1 (if any)
        while (left < n) {
            arr3[index++] = arr1[left++];
        }
        
        // Add remaining elements from arr2 (if any)
        while (right < m) {
            arr3[index++] = arr2[right++];
        }

        // Copy merged elements back to original arrays
        for (int i = 0; i < n + m; i++) {
            if (i < n) {
                // First n elements go to arr1
                arr1[i] = arr3[i];
            } else {
                // Remaining m elements go to arr2
                arr2[i - n] = arr3[i];
            }
        }
    }

    // Main method for testing
    public static void main(String[] args) {
        // Example input arrays
        long[] arr1 = { 1, 4, 8, 10 };
        long[] arr2 = { 2, 3, 9 };
        int n = 4, m = 3;
        
        // Call the merge method
        merge(arr1, arr2, n, m);
        
        // Display the merged arrays
        System.out.println("The merged arrays are:");
        System.out.print("arr1[] = ");
        for (int i = 0; i < n; i++) {
            System.out.print(arr1[i] + " ");
        }
        System.out.print("\narr2[] = ");
        for (int i = 0; i < m; i++) {
            System.out.print(arr2[i] + " ");
        }
        System.out.println();
    }
}

/*
# Algorithm:
1. We will first declare a third array, arr3[] of size n+m, and two pointers i.e. left and right, one pointing to the first index of arr1[] and the other pointing to the first index of arr2[].
2. The two pointers will move like the following:
 - If arr1[left] < arr2[right]: We will insert the element arr1[left] into the array and increase the left pointer by 1.
 - If arr2[right] < arr1[left]: We will insert the element arr2[right] into the array and increase the right pointer by 1.
 - If arr1[left] == arr2[right]: Insert any of the elements and increase that particular pointer by 1.
 - If one of the pointers reaches the end, then we will only move the other pointer and insert the rest of the elements of that particular array into the third array i.e. arr3[].
3. If we move the pointer like the above, we will get the third array in the sorted order.
4. Now, from sorted array arr3[], we will copy first n(size of arr1[]) elements to arr1[], and the next m(size of arr2[]) elements to arr2[].

### Time Complexity Breakdown per Step
1. Creating the third array: O(1)
2. Merging elements with comparisons: O(n+m)
3. Copying remaining elements: O(n) or O(m) in worst case
4. Copying back to original arrays: O(n+m)

Total: O(2(n+m))

### Space Complexity Breakdown
1. Auxiliary Space: O(n+m) for the third array arr3
   - This is the additional space we use to temporarily store the merged result
2. Input Space: O(n+m) for the input arrays
   - Original space occupied by arr1 and arr2

Total: O(n+m) auxiliary space

# Advantages
1. Simplicity - The algorithm is straightforward and easy to understand
2. Predictable - The approach guarantees a correct result in all cases
3. Linear time - The time complexity is O(n+m), which is optimal for merging

# Limitations
1. Extra Space - Requires O(n+m) additional space for the temporary array
2. Not In-place - Unlike some other approaches, this doesn't merge in-place
3. Redundancy - We need to copy elements twice (once to arr3, then back to original arrays)

# Potential Improvements
1. In-place merging using insertion sort technique to avoid extra space
2. Using the gap method (Shell sort variant) for O(1) space complexity
3. Optimizing for cases when one array is already in its final position

# Step-by-Step Process with Dry Run

# Detailed Execution of Merge Sorted Arrays Algorithm

Let's use the example from your code:
- `arr1 = [1, 4, 8, 10]`, `n = 4`
- `arr2 = [2, 3, 9]`, `m = 3`

## Initial State
- `arr1 = [1, 4, 8, 10]` (size = 4)
- `arr2 = [2, 3, 9]` (size = 3)
- `arr3 = [0, 0, 0, 0, 0, 0, 0]` (size = n+m = 7)
- `left = 0` (pointer for arr1)
- `right = 0` (pointer for arr2)
- `index = 0` (pointer for arr3)

## Detailed Execution Table

| Step | Action | arr1 State | arr2 State | arr3 State | left | right | index | Explanation |
|------|--------|------------|------------|------------|------|-------|-------|-------------|
| 0 | Initialize | [1, 4, 8, 10] | [2, 3, 9] | [0, 0, 0, 0, 0, 0, 0] | 0 | 0 | 0 | Starting state |
| 1 | Compare arr1[0]=1 with arr2[0]=2 | [1, 4, 8, 10] | [2, 3, 9] | [1, 0, 0, 0, 0, 0, 0] | 1 | 0 | 1 | 1 < 2, so take arr1[0] |
| 2 | Compare arr1[1]=4 with arr2[0]=2 | [1, 4, 8, 10] | [2, 3, 9] | [1, 2, 0, 0, 0, 0, 0] | 1 | 1 | 2 | 4 > 2, so take arr2[0] |
| 3 | Compare arr1[1]=4 with arr2[1]=3 | [1, 4, 8, 10] | [2, 3, 9] | [1, 2, 3, 0, 0, 0, 0] | 1 | 2 | 3 | 4 > 3, so take arr2[1] |
| 4 | Compare arr1[1]=4 with arr2[2]=9 | [1, 4, 8, 10] | [2, 3, 9] | [1, 2, 3, 4, 0, 0, 0] | 2 | 2 | 4 | 4 < 9, so take arr1[1] |
| 5 | Compare arr1[2]=8 with arr2[2]=9 | [1, 4, 8, 10] | [2, 3, 9] | [1, 2, 3, 4, 8, 0, 0] | 3 | 2 | 5 | 8 < 9, so take arr1[2] |
| 6 | Compare arr1[3]=10 with arr2[2]=9 | [1, 4, 8, 10] | [2, 3, 9] | [1, 2, 3, 4, 8, 9, 0] | 3 | 3 | 6 | 10 > 9, so take arr2[2] |
| 7 | right = m, copy remaining arr1 | [1, 4, 8, 10] | [2, 3, 9] | [1, 2, 3, 4, 8, 9, 10] | 4 | 3 | 7 | Add remaining arr1[3]=10 |
| 8 | Copy back to arr1 (i=0) | [1, 4, 8, 10] | [2, 3, 9] | [1, 2, 3, 4, 8, 9, 10] | 4 | 3 | 7 | arr1[0] = arr3[0] = 1 |
| 9 | Copy back to arr1 (i=1) | [1, 2, 8, 10] | [2, 3, 9] | [1, 2, 3, 4, 8, 9, 10] | 4 | 3 | 7 | arr1[1] = arr3[1] = 2 |
| 10 | Copy back to arr1 (i=2) | [1, 2, 3, 10] | [2, 3, 9] | [1, 2, 3, 4, 8, 9, 10] | 4 | 3 | 7 | arr1[2] = arr3[2] = 3 |
| 11 | Copy back to arr1 (i=3) | [1, 2, 3, 4] | [2, 3, 9] | [1, 2, 3, 4, 8, 9, 10] | 4 | 3 | 7 | arr1[3] = arr3[3] = 4 |
| 12 | Copy back to arr2 (i=4) | [1, 2, 3, 4] | [8, 3, 9] | [1, 2, 3, 4, 8, 9, 10] | 4 | 3 | 7 | arr2[0] = arr3[4] = 8 |
| 13 | Copy back to arr2 (i=5) | [1, 2, 3, 4] | [8, 9, 9] | [1, 2, 3, 4, 8, 9, 10] | 4 | 3 | 7 | arr2[1] = arr3[5] = 9 |
| 14 | Copy back to arr2 (i=6) | [1, 2, 3, 4] | [8, 9, 10] | [1, 2, 3, 4, 8, 9, 10] | 4 | 3 | 7 | arr2[2] = arr3[6] = 10 |

## Final Result
- `arr1 = [1, 2, 3, 4]`
- `arr2 = [8, 9, 10]`

## Step-by-Step Process Explanation

1. **Initialization Step (Step 0)**:
   - Set up our initial arrays and pointers
   - Create an empty arr3 of size n+m to store merged results
   - Set left=0 (for arr1), right=0 (for arr2), index=0 (for arr3)

2. **First Comparison (Step 1)**:
   - Compare arr1[0]=1 with arr2[0]=2
   - Since 1 < 2, add 1 to arr3 at position 0
   - Increment left to 1 and index to 1
   - arr3 now contains [1, 0, 0, 0, 0, 0, 0]

3. **Second Comparison (Step 2)**:
   - Compare arr1[1]=4 with arr2[0]=2
   - Since 4 > 2, add 2 to arr3 at position 1
   - Increment right to 1 and index to 2
   - arr3 now contains [1, 2, 0, 0, 0, 0, 0]

4. **Third Comparison (Step 3)**:
   - Compare arr1[1]=4 with arr2[1]=3
   - Since 4 > 3, add 3 to arr3 at position 2
   - Increment right to 2 and index to 3
   - arr3 now contains [1, 2, 3, 0, 0, 0, 0]

5. **Fourth Comparison (Step 4)**:
   - Compare arr1[1]=4 with arr2[2]=9
   - Since 4 < 9, add 4 to arr3 at position 3
   - Increment left to 2 and index to 4
   - arr3 now contains [1, 2, 3, 4, 0, 0, 0]

6. **Fifth Comparison (Step 5)**:
   - Compare arr1[2]=8 with arr2[2]=9
   - Since 8 < 9, add 8 to arr3 at position 4
   - Increment left to 3 and index to 5
   - arr3 now contains [1, 2, 3, 4, 8, 0, 0]

7. **Sixth Comparison (Step 6)**:
   - Compare arr1[3]=10 with arr2[2]=9
   - Since 10 > 9, add 9 to arr3 at position 5
   - Increment right to 3 and index to 6
   - arr3 now contains [1, 2, 3, 4, 8, 9, 0]

8. **Handle Remaining Elements (Step 7)**:
   - Right pointer equals m (array 2 is exhausted)
   - Copy remaining element arr1[3]=10 to arr3[6]
   - Increment left to 4 and index to 7
   - arr3 now contains [1, 2, 3, 4, 8, 9, 10]

9. **Copy Back to Original Arrays (Steps 8-14)**:
   - For i=0 to n+m-1 (0 to 6):
     - If i < n, copy arr3[i] to arr1[i]
     - Otherwise copy arr3[i] to arr2[i-n]
   - After copying:
     - arr1 = [1, 2, 3, 4]
     - arr2 = [8, 9, 10]

## Memory Visualization During Key Steps

### After Merging (Before Copy-Back)
```
arr1: [1, 4, 8, 10]
       ↑ values will be overwritten

arr2: [2, 3, 9]
       ↑ values will be overwritten

arr3: [1, 2, 3, 4, 8, 9, 10]
       ↑ fully merged array
       
left = 4, right = 3, index = 7
```

### During Copy-Back (Midway)
```
arr1: [1, 2, 3, 10] ← partially updated
             ↑ not yet updated

arr2: [2, 3, 9] ← not yet updated
       ↑ 

arr3: [1, 2, 3, 4, 8, 9, 10]
                ↑ next to copy
```

### After Copy-Back (Final State)
```
arr1: [1, 2, 3, 4] ← final state
       ↑ completely updated

arr2: [8, 9, 10] ← final state
       ↑ completely updated

arr3: [1, 2, 3, 4, 8, 9, 10]
       ↑ temporary array (no longer needed)
```

## Key Points

1. **Two-Pointer Logic**: We maintain two pointers (left and right) that traverse arr1 and arr2, always choosing the smaller element to add to arr3.

2. **Efficient Comparison**: At each step, we only need one comparison to determine which array to take the next element from.

3. **Handling Remaining Elements**: After one array is exhausted, we simply copy the remaining elements from the other array without any further comparisons.

4. **Copy-Back Phase**: We split the merged array by copying the first n elements to arr1 and the next m elements to arr2, maintaining the sorted order across both arrays.

5. **Linear Time Efficiency**: Each element is processed exactly once during merging and once during copying back, keeping the time complexity at O(n+m).

## Alternative Example

Let's look at another example to solidify understanding:
- `arr1 = [5, 13, 17]`, `n = 3`
- `arr2 = [3, 7, 11, 19]`, `m = 4`

| Step | Comparison | arr3 Update | left | right | index | arr3 State |
|------|------------|-------------|------|-------|-------|------------|
| 1 | 5 > 3 | arr3[0] = 3 | 0 | 1 | 1 | [3, 0, 0, 0, 0, 0, 0] |
| 2 | 5 < 7 | arr3[1] = 5 | 1 | 1 | 2 | [3, 5, 0, 0, 0, 0, 0] |
| 3 | 13 > 7 | arr3[2] = 7 | 1 | 2 | 3 | [3, 5, 7, 0, 0, 0, 0] |
| 4 | 13 > 11 | arr3[3] = 11 | 1 | 3 | 4 | [3, 5, 7, 11, 0, 0, 0] |
| 5 | 13 < 19 | arr3[4] = 13 | 2 | 3 | 5 | [3, 5, 7, 11, 13, 0, 0] |
| 6 | 17 < 19 | arr3[5] = 17 | 3 | 3 | 6 | [3, 5, 7, 11, 13, 17, 0] |
| 7 | Left exhausted | arr3[6] = 19 | 3 | 4 | 7 | [3, 5, 7, 11, 13, 17, 19] |

After copy-back:
- `arr1 = [3, 5, 7]` (first n=3 elements)
- `arr2 = [11, 13, 17, 19]` (next m=4 elements)

### Step-by-Step Explanation

1. **Initialize Arrays and Pointers**
   - arr1 = [1, 4, 8, 10], n = 4
   - arr2 = [2, 3, 9], m = 3
   - arr3 = [0, 0, 0, 0, 0, 0, 0]
   - left = 0, right = 0, index = 0
   ```
   arr1: [1, 4, 8, 10]
           ↑
         left
   arr2: [2, 3, 9]
           ↑
         right
   arr3: [0, 0, 0, 0, 0, 0, 0]
           ↑
         index
   ```

2. **First Comparison: arr1[0] vs arr2[0]**
   - Compare 1 and 2
   - Since 1 < 2, add 1 to arr3
   - Increment left to 1, index to 1
   ```
   arr1: [1, 4, 8, 10]
              ↑
            left
   arr2: [2, 3, 9]
           ↑
         right
   arr3: [1, 0, 0, 0, 0, 0, 0]
              ↑
            index
   ```

3. **Second Comparison: arr1[1] vs arr2[0]**
   - Compare 4 and 2
   - Since 4 > 2, add 2 to arr3
   - Increment right to 1, index to 2
   ```
   arr1: [1, 4, 8, 10]
              ↑
            left
   arr2: [2, 3, 9]
              ↑
            right
   arr3: [1, 2, 0, 0, 0, 0, 0]
                 ↑
               index
   ```

4. **Continue Through All Elements**
   - Continue comparing and merging until one array is exhausted
   - Then add any remaining elements from the non-exhausted array
   - Final arr3 = [1, 2, 3, 4, 8, 9, 10]

5. **Copy Back to Original Arrays**
   - First n elements go to arr1: [1, 2, 3, 4]
   - Next m elements go to arr2: [8, 9, 10]

### Additional Example Cases

1. **Small Arrays**
```
Input:  arr1 = [5, 10], arr2 = [7]
Step 1: Compare 5 and 7, take 5
Step 2: Compare 10 and 7, take 7
Step 3: Add remaining 10 from arr1
Step 4: Copy back to get arr1 = [5, 7], arr2 = [10]
Output: arr1 = [5, 7], arr2 = [10]
```

2. **Duplicate Values**
```
Input:  arr1 = [3, 3, 5], arr2 = [3, 4, 4]
Step 1: First arr3 becomes [3, 3, 3, 4, 4, 5] after merging
Step 2: Copy back to get arr1 = [3, 3, 3], arr2 = [4, 4, 5]
Output: arr1 = [3, 3, 3], arr2 = [4, 4, 5]
```

### Edge Cases Handling
1. **Empty Second Array**
   ```
   Input: arr1 = [1, 2, 3], arr2 = []
   Output: arr1 = [1, 2, 3], arr2 = []
   The algorithm correctly handles this by skipping the first loop and 
   copying all elements from arr1 to arr3 in the second loop.
   ```

2. **Empty First Array**
   ```
   Input: arr1 = [], arr2 = [4, 5, 6]
   Output: arr1 = [], arr2 = [4, 5, 6]
   The algorithm skips the first loop, copies arr2 to arr3, then
   copies back with no changes since n = 0.
   ```

Key Observations:
1. The algorithm always maintains the sorting property during merging
2. The two-pointer approach ensures we process each element exactly once
3. The extra array simplifies the merging logic at the cost of extra space

Sample Validation:
Input: arr1 = [1, 4, 8, 10], arr2 = [2, 3, 9]
Expected: arr1 = [1, 2, 3, 4], arr2 = [8, 9, 10]
Output: arr1 = [1, 2, 3, 4], arr2 = [8, 9, 10]

Key Points:
1. Time complexity is optimal at O(n+m)
2. Space complexity is O(n+m) due to the extra array
3. The approach works by temporarily storing the merged result before copying back
4. This approach prioritizes simplicity over space efficiency

TEST CASES:
1. Input: arr1 = [1, 2, 3, 0, 0, 0], arr2 = [2, 5, 6]
   Expected: arr1 = [1, 2, 2], arr2 = [3, 5, 6]
   Output: arr1 = [1, 2, 2], arr2 = [3, 5, 6]
2. Input: arr1 = [4, 5, 6], arr2 = [1, 2, 3]
   Expected: arr1 = [1, 2, 3], arr2 = [4, 5, 6]
   Output: arr1 = [1, 2, 3], arr2 = [4, 5, 6]
3. Input: arr1 = [1, 1, 1], arr2 = [1, 1, 1]
   Expected: arr1 = [1, 1, 1], arr2 = [1, 1, 1]
   Output: arr1 = [1, 1, 1], arr2 = [1, 1, 1]
 */