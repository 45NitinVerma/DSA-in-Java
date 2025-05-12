package Array.Merge_Sorted_Arrays2;
import java.util.Arrays;
/* 
# Problem: Merge Sorted Arrays
Given two sorted arrays arr1[] and arr2[] of sizes n and m in non-decreasing order. Merge them in sorted order. Modify arr1 so that it contains the first N elements and modify arr2 so that it contains the last M elements.

## Intution: In this optimal approach, we need to get rid of the extra space we were using.
If we merge the given array, one thing we can assure is that arr1[] will contain all the smaller elements and arr2[] will contain all the bigger elements. This is the logic we will use. Using the 2 pointers, we will swap the bigger elements of arr1[] with the smaller elements of arr2[] until the minimum of arr2[] becomes greater or equal to the maximum of arr1[].

## Key Observations with Examples

### Observation 1: In-Place Swapping Pattern
When merging sorted arrays without extra space, we can use a two-pointer approach to swap elements between arrays.
* The first array eventually contains all smaller elements
* The second array eventually contains all larger elements

Example:
```
arr1 = [1, 4, 8, 10], arr2 = [2, 3, 9]
```

### Observation 2: Shell Sort-like Comparison
By comparing the largest elements of arr1 with the smallest elements of arr2, we can efficiently position elements.
* If arr1[last] > arr2[first], we need to swap
* This ensures the largest element from arr1 goes to arr2 if it's bigger than arr2's smallest element

Example: 
```
Compare arr1[3]=10 with arr2[0]=2
Since 10 > 2, swap them: arr1=[1,4,8,2], arr2=[10,3,9]
```

### Observation 3: Final Sorting Requirement
After all relevant swaps, each array needs to be sorted individually.
* Swapping may have disrupted the internal sorting of each array
* A final sort of each array separately ensures complete ordering

## Step-by-Step Example
Let's work through merging arr1 = [1, 4, 8, 10] and arr2 = [2, 3, 9]:

1. Step One:
   ```
   left = 3 (last index of arr1), right = 0 (first index of arr2)
   Compare arr1[left]=10 with arr2[right]=2
   Since 10 > 2, swap them:
   arr1 = [1, 4, 8, 2], arr2 = [10, 3, 9]
   ```
   We move largest elements from arr1 to arr2 if they're larger than arr2's smallest element.

2. Step Two:
   ```
   Decrement left to 2, increment right to 1
   Compare arr1[left]=8 with arr2[right]=3
   Since 8 > 3, swap them:
   arr1 = [1, 4, 3, 2], arr2 = [10, 8, 9]
   ```
   We continue the process, ensuring larger elements move to arr2.

3. Step Three:
   ```
   Decrement left to 1, increment right to 2
   Compare arr1[left]=4 with arr2[right]=9
   Since 4 < 9, no swap needed
   ```
   No more swaps needed as arr1's element is already smaller than arr2's element.

4. Step Four:
   ```
   Sort both arrays separately:
   arr1 = [1, 2, 3, 4], arr2 = [8, 9, 10]
   ```
   Final sorting ensures each array maintains internal order.

## Special Cases

### Case 1: No Overlap Between Arrays
Input: arr1 = [1, 2, 3], arr2 = [4, 5, 6]
- Behavior: No swaps occur as arr1's largest element is already smaller than arr2's smallest
- Result: Arrays remain unchanged except for final sort

### Case 2: Complete Overlap
Input: arr1 = [7, 8, 9], arr2 = [1, 2, 3]
- Behavior: All elements get swapped
- Result: arr1 = [1, 2, 3], arr2 = [7, 8, 9]

### Case 3: Partial Overlap
Input: arr1 = [1, 5, 9], arr2 = [2, 6, 10]
- Behavior: Some elements get swapped
- Result: Elements redistribute correctly with smaller in arr1, larger in arr2


*/
public class Approach2 {
   // Approach 2: Using 2 pointers (Without extra space)
    public static void merge(long[] arr1, long[] arr2, int n, int m) {
        // Initialize pointers
        int left = n - 1;  // Points to the last element of arr1
        int right = 0;     // Points to the first element of arr2
        
        // Iterate while both pointers are within valid ranges
        while(left >= 0 && right < m) {
            // If element in arr1 is greater than element in arr2, swap them
            // This ensures larger elements move to arr2 and smaller ones to arr1
            if(arr1[left] > arr2[right]) {
                // Swap elements
                long temp = arr1[left];
                arr1[left] = arr2[right];
                arr2[right] = temp;
            }
            // Move pointers
            left--;     // Move to previous element in arr1
            right++;    // Move to next element in arr2
        }
        
        // After swapping, sort both arrays separately
        // This ensures each array maintains its sorted property
        Arrays.sort(arr1);
        Arrays.sort(arr2);
    }

    // Main method for testing
    public static void main(String[] args) {
        long[] arr1 = {1, 4, 8, 10};  // First sorted array
        long[] arr2 = {2, 3, 9};      // Second sorted array
        int n = 4, m = 3;             // Sizes of the arrays
        
        System.out.println("Before merging:");
        System.out.print("arr1[] = ");
        for (int i = 0; i < n; i++) {
            System.out.print(arr1[i] + " ");
        }
        System.out.print("\narr2[] = ");
        for (int i = 0; i < m; i++) {
            System.out.print(arr2[i] + " ");
        }
        
        // Call the merge method
        merge(arr1, arr2, n, m);
        
        System.out.println("\n\nAfter merging:");
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
1. We will declare two pointers i.e. left and right. The left pointer will point to the last index of the arr1[](i.e. Basically the maximum element of the array). The right pointer will point to the first index of the arr2[](i.e. Basically the minimum element of the array).
2. Now, the left pointer will move toward index 0 and the right pointer will move towards the index m-1. While moving the two pointers we will face 2 different cases like the following:
 - If arr1[left] > arr2[right]: In this case, we will swap the elements and move the pointers to the next positions.
 - If arr1[left] <= arr2[right]: In this case, we will stop moving the pointers as arr1[] and arr2[] are containing correct elements.
3. Thus, after step 2, arr1[] will contain all smaller elements and arr2[] will contain all bigger elements. 
4. Finally, we will sort the two arrays.

### Time Complexity Breakdown per Step
1. Swapping process: O(min(n, m)) - We perform at most min(n, m) swaps
2. Sorting arr1: O(n log n)
3. Sorting arr2: O(m log m)

Total: O(n log n + m log m) - dominated by the sorting operations

### Space Complexity Breakdown
1. Auxiliary Space: O(1) - Only a few variables used regardless of input size
   - We only use temporary variables for swapping and pointers
2. Input Space: O(n + m) - The space required to store the input arrays
   - This is not counted in auxiliary space as it's required by the problem

Total: O(1) auxiliary space

# Advantages
1. No additional space required beyond the input arrays
2. Simple to implement with minimal pointer manipulation
3. Works efficiently when arrays have minimal overlapping elements
4. Only requires one pass through the arrays before sorting

# Limitations
1. Requires sorting after swapping, which adds O(n log n + m log m) time complexity
2. May perform unnecessary swaps in some cases
3. Not optimal for very large arrays due to the sorting overhead
4. The solution is not stable (equal elements may change relative order)

# Potential Improvements
1. Use a gap method (shell sort variant) to reduce time complexity to O((n+m)log(n+m))
2. Implement insertion sort instead of swapping and sorting to maintain stability
3. Use a hybrid approach for different sized arrays to optimize performance
4. Employ binary search to find optimal swap positions rather than linear comparison

# Step-by-Step Process with Dry Run
# Detailed Step-by-Step Process with Dry Run for Merge Sorted Arrays

## Example Input: 
- arr1 = [1, 4, 8, 10]
- arr2 = [2, 3, 9]
- n = 4 (size of arr1)
- m = 3 (size of arr2)

## Detailed Execution Table

| Step | arr1 (Before) | arr2 (Before) | Comparison | Swap? | arr1 (After) | arr2 (After) | left | right | Explanation |
|------|--------------|--------------|------------|-------|--------------|--------------|------|-------|-------------|
| Init | [1, 4, 8, 10] | [2, 3, 9] | - | - | [1, 4, 8, 10] | [2, 3, 9] | 3 | 0 | Initialize pointers: left=3, right=0 |
| 1 | [1, 4, 8, 10] | [2, 3, 9] | arr1[3]=10 > arr2[0]=2 | Yes | [1, 4, 8, 2] | [10, 3, 9] | 2 | 1 | Swap 10 and 2, decrement left, increment right |
| 2 | [1, 4, 8, 2] | [10, 3, 9] | arr1[2]=8 > arr2[1]=3 | Yes | [1, 4, 3, 2] | [10, 8, 9] | 1 | 2 | Swap 8 and 3, decrement left, increment right |
| 3 | [1, 4, 3, 2] | [10, 8, 9] | arr1[1]=4 < arr2[2]=9 | No | [1, 4, 3, 2] | [10, 8, 9] | 0 | 3 | No swap (4<9), decrement left, increment right |
| 4 | [1, 4, 3, 2] | [10, 8, 9] | - | - | [1, 4, 3, 2] | [10, 8, 9] | -1 | 3 | right=3 is out of bounds for arr2, exit loop |
| Sort | [1, 4, 3, 2] | [10, 8, 9] | - | - | [1, 2, 3, 4] | [8, 9, 10] | - | - | Final sorting of both arrays |

## Detailed Explanation In Points

### Step 1: Initialization
* **Initial State**: 
  * arr1 = [1, 4, 8, 10]
  * arr2 = [2, 3, 9]
* **Pointers Setup**:
  * left = 3 (pointing to the last element of arr1)
  * right = 0 (pointing to the first element of arr2)
* **Visualization**:
  ```
  arr1 = [1, 4, 8, 10] ← left points here
  arr2 = [2, 3, 9] ← right points here
         ^
  ```

### Step 2: First Comparison and Swap
* **Comparison**: arr1[left] vs arr2[right] → 10 vs 2
* **Decision**: Since 10 > 2, we swap the elements
* **Swap Operation**:
  * temp = arr1[left] = 10
  * arr1[left] = arr2[right] = 2
  * arr2[right] = temp = 10
* **Updated Arrays**:
  * arr1 = [1, 4, 8, 2]
  * arr2 = [10, 3, 9]
* **Pointer Movement**:
  * left decreases to 2
  * right increases to 1
* **Visualization**:
  ```
  arr1 = [1, 4, 8, 2]
              ^    (previous left position)
  arr2 = [10, 3, 9]
          ^    (previous right position)
  ```

### Step 3: Second Comparison and Swap
* **Comparison**: arr1[left] vs arr2[right] → 8 vs 3
* **Decision**: Since 8 > 3, we swap the elements
* **Swap Operation**:
  * temp = arr1[left] = 8
  * arr1[left] = arr2[right] = 3
  * arr2[right] = temp = 8
* **Updated Arrays**:
  * arr1 = [1, 4, 3, 2]
  * arr2 = [10, 8, 9]
* **Pointer Movement**:
  * left decreases to 1
  * right increases to 2
* **Visualization**:
  ```
  arr1 = [1, 4, 3, 2]
           ^    (left is now here)
  arr2 = [10, 8, 9]
              ^  (right is now here)
  ```

### Step 4: Third Comparison - No Swap
* **Comparison**: arr1[left] vs arr2[right] → 4 vs 9
* **Decision**: Since 4 < 9, no swap is needed
* **Arrays Remain**:
  * arr1 = [1, 4, 3, 2]
  * arr2 = [10, 8, 9]
* **Pointer Movement**:
  * left decreases to 0
  * right increases to 3 (now out of bounds for arr2)
* **Visualization**:
  ```
  arr1 = [1, 4, 3, 2]
        ^    (left is now here)
  arr2 = [10, 8, 9]
                 (right is now out of bounds)
  ```

### Step 5: Loop Termination
* **Condition Check**: right = 3 is out of bounds for arr2 (size m = 3)
* **Result**: The while loop terminates
* **Final Unsorted Arrays**:
  * arr1 = [1, 4, 3, 2]
  * arr2 = [10, 8, 9]

### Step 6: Sorting Both Arrays
* **Sort arr1**: [1, 4, 3, 2] → [1, 2, 3, 4]
* **Sort arr2**: [10, 8, 9] → [8, 9, 10]
* **Final Result**:
  * arr1 = [1, 2, 3, 4]
  * arr2 = [8, 9, 10]
* **Verification**:
  * All elements in arr1 are smaller than all elements in arr2
  * Both arrays are internally sorted

## Key Takeaways from the Execution

1. **Swapping Strategy**: The algorithm swaps elements between arrays to ensure all smaller elements end up in arr1 and larger elements in arr2.

2. **Pointer Movement**: The left pointer moves backward in arr1 while the right pointer moves forward in arr2, allowing us to compare the largest elements of arr1 with the smallest elements of arr2.

3. **Swap Criteria**: Elements are swapped only when arr1[left] > arr2[right], ensuring smaller elements migrate to arr1.

4. **Final Sorting**: After the swapping phase, both arrays need to be sorted separately to maintain their internal sorted order.

5. **Termination**: The process stops when either the left pointer reaches the beginning of arr1 or the right pointer reaches the end of arr2.

6. **Result Verification**: After execution, every element in arr1 should be less than or equal to every element in arr2, and both arrays should be sorted.

## Additional Example: One More Dry Run with Different Input

### Example Input:
- arr1 = [5, 6, 7, 0, 0]
- arr2 = [1, 4]
- n = 3 (first 3 elements of arr1 are valid)
- m = 2 (size of arr2)

### Brief Execution Summary:

1. **Initial State**:
   - arr1 = [5, 6, 7, 0, 0], left = 2
   - arr2 = [1, 4], right = 0

2. **Comparison 1**: arr1[2]=7 > arr2[0]=1
   - Swap: arr1 = [5, 6, 1, 0, 0], arr2 = [7, 4]
   - left = 1, right = 1

3. **Comparison 2**: arr1[1]=6 > arr2[1]=4
   - Swap: arr1 = [5, 4, 1, 0, 0], arr2 = [7, 6]
   - left = 0, right = 2 (right is now out of bounds)

4. **Sort Both Arrays**:
   - arr1 = [1, 4, 5, 0, 0] (only sorting the valid n=3 elements)
   - arr2 = [6, 7]

5. **Final Result**:
   - arr1 = [1, 4, 5, 0, 0]
   - arr2 = [6, 7]
   - All valid elements in arr1 are smaller than all elements in arr2

This solution efficiently merges two sorted arrays without using extra space, maintaining the property that all elements in the first array are smaller than the elements in the second array.

### Step-by-Step Explanation

1. **Initialize Pointers**
   - Set left = 3 (last index of arr1) and right = 0 (first index of arr2)
   ```
   arr1 = [1, 4, 8, 10], arr2 = [2, 3, 9]
           ^  ^  ^  ^            ^  ^  ^
                    left         right
   ```

2. **First Comparison and Swap**
   - Compare arr1[left]=10 with arr2[right]=2
   - Since 10 > 2, swap them
   ```
   arr1 = [1, 4, 8, 2], arr2 = [10, 3, 9]
           ^  ^  ^  ^            ^  ^  ^
                    left         right
   ```
   - Decrement left to 2, increment right to 1

3. **Second Comparison and Swap**
   - Compare arr1[left]=8 with arr2[right]=3
   - Since 8 > 3, swap them
   ```
   arr1 = [1, 4, 3, 2], arr2 = [10, 8, 9]
           ^  ^  ^  ^            ^  ^  ^
              left                  right
   ```
   - Decrement left to 1, increment right to 2

4. **Third Comparison - No Swap**
   - Compare arr1[left]=4 with arr2[right]=9
   - Since 4 < 9, no swap needed
   ```
   arr1 = [1, 4, 3, 2], arr2 = [10, 8, 9]
           ^  ^  ^  ^            ^  ^  ^
              left                     right
   ```
   - Decrement left to 0, increment right to 3 (out of bounds for arr2)

5. **Sort Both Arrays**
   - Sort arr1: [1, 2, 3, 4]
   - Sort arr2: [8, 9, 10]
   ```
   Final result:
   arr1 = [1, 2, 3, 4], arr2 = [8, 9, 10]
   ```

### Additional Example Cases

1. **No Overlap Case**
```
Input:  arr1 = [1, 2, 3], arr2 = [4, 5, 6]
Step 1: Compare arr1[2]=3 with arr2[0]=4, no swap needed (3 < 4)
Output: arr1 = [1, 2, 3], arr2 = [4, 5, 6] (unchanged)
```

2. **Complete Overlap Case**
```
Input:  arr1 = [7, 8, 9], arr2 = [1, 2, 3]
Step 1: Compare arr1[2]=9 with arr2[0]=1, swap (9 > 1): arr1 = [7, 8, 1], arr2 = [9, 2, 3]
Step 2: Compare arr1[1]=8 with arr2[1]=2, swap (8 > 2): arr1 = [7, 2, 1], arr2 = [9, 8, 3]
Step 3: Compare arr1[0]=7 with arr2[2]=3, swap (7 > 3): arr1 = [3, 2, 1], arr2 = [9, 8, 7]
Step 4: Sort both arrays
Output: arr1 = [1, 2, 3], arr2 = [7, 8, 9]
```

### Edge Cases Handling
1. **One Empty Array**
   ```
   Input: arr1 = [], arr2 = [1, 2, 3]
   Output: arr1 = [], arr2 = [1, 2, 3]
   The algorithm handles this by not entering the while loop when n=0
   ```

2. **Equal Elements in Both Arrays**
   ```
   Input: arr1 = [1, 2, 2], arr2 = [2, 3, 4]
   Process: 
   - Compare arr1[2]=2 with arr2[0]=2, no swap needed (2 == 2)
   Output: arr1 = [1, 2, 2], arr2 = [2, 3, 4]
   The algorithm correctly keeps equal elements in place
   ```

Key Observations:
1. The algorithm performs best when there's minimal overlap between array ranges
2. The final sorting step is crucial to maintain the sorted property within each array
3. The algorithm maintains the property that all elements in arr1 are smaller than or equal to the smallest element in arr2 that wasn't swapped
4. At most min(n,m) swaps are performed

Sample Validation:
Input: arr1 = [1, 4, 8, 10], arr2 = [2, 3, 9]
Expected: arr1 = [1, 2, 3, 4], arr2 = [8, 9, 10]
Output: arr1 = [1, 2, 3, 4], arr2 = [8, 9, 10]

Key Points:
1. The approach works by redistributing elements between arrays
2. No extra space is used beyond the input arrays themselves
3. The final time complexity is dominated by the sorting operations
4. The solution efficiently handles various overlap scenarios

TEST CASES:
1. Input: arr1 = [1, 3, 5, 7], arr2 = [0, 2, 6, 8, 9]
   Expected: arr1 = [0, 1, 2, 3], arr2 = [5, 6, 7, 8, 9]
   Output: arr1 = [0, 1, 2, 3], arr2 = [5, 6, 7, 8, 9]

2. Input: arr1 = [10, 12], arr2 = [5, 18, 20]
   Expected: arr1 = [5, 10], arr2 = [12, 18, 20]
   Output: arr1 = [5, 10], arr2 = [12, 18, 20]

3. Input: arr1 = [1, 1, 1], arr2 = [2, 2, 2]
   Expected: arr1 = [1, 1, 1], arr2 = [2, 2, 2]
   Output: arr1 = [1, 1, 1], arr2 = [2, 2, 2]
*/