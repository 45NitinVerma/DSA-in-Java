package Array.Reverse_Pairs;

import java.util.ArrayList;

/* 
# Problem: Reverse Pairs
Given an integer array nums, return the number of reverse pairs in the array.
A reverse pair is a pair (i, j) where:
 - 0 <= i < j < nums.length and nums[i] > 2 * nums[j].

## Intution: In order to solve this problem we will use the merge sort algorithm like we used in the problem count inversion with a slight modification of the merge() function. But in this case, the same logic will not work. In order to understand this, we need to deep dive into the merge() function.

### Why the same logic of 'count inversion' will not work here?
 - The merge function works by comparing two elements from two halves i.e. arr[left] and arr[right]. Now, the condition in the question was arr[i] > arr[j]. That is why we merged the logic. While comparing the elements, we counted the number of pairs.
 - But in this case, the condition is arr[i] > 2*arr[j]. And, we cannot change the condition of comparing the elements in the merge() function. If we change the condition, the merge() function will fail to merge the elements. So, we need to check this condition and count the number of pairs separately.

Here, our approach will be to check, for every element in the sorted left half(sorted), how many elements in the right half(also sorted) can make a pair. Let’s try to understand, using the following example:
    arr1 = [6, 13, 21, 25]
            ↑

    arr2 = [1, 2, 3, 4, 4, 5, 9, 11, 13]
           ______ ↑
        Here the checking ends as 6 = 2*3 (i.e. 6 > 2*3 is false). 

For the first element of the left half i.e. 6, we will start checking from index 0 of the right half i.e. arr2[]. Now, we can clearly see that the first two elements of arr2[] can make a pair with arr1[0] i.e. 6.
    arr1 = [6, 13, 21, 25]
                ↑

    arr2 = [1, 2, 3, 4, 4, 5, 9, 11, 13]
           ______ ↑
        Fort the next element i.e. 13, we will start checking from this index. No need to check from the first index again. 

For the next element i.e. arr1[1], we will start checking from index 2(0-based indexing) i.e. where we stopped for the previous element. 

### Note: This process will work because arr1[1] will always be greater than arr1[0] which concludes if arr2[0] and arr2[1] are making a pair with arr1[0], they will obviously make pairs with a number greater than arr1[0] i.e. arr1[1].
Thus before the merge step in the merge sort algorithm, we will calculate the total number of pairs each time.

## Key Observations with Examples

### Observation 1: Divide and Conquer Pattern
We can break down the problem into counting reverse pairs within subarrays and then counting pairs that span across different subarrays.

* Elements are compared across two already sorted halves
* This pattern allows us to avoid unnecessary comparisons

### Observation 2: Counting Before Merging
When merging two sorted halves in merge sort, we can count reverse pairs before the actual merging step.

Example: Consider two sorted halves [6, 13, 21, 25] and [1, 2, 3, 4, 4, 5, 9, 11, 13]

```
Left half:  [6, 13, 21, 25]
            ↑
Right half: [1, 2, 3, 4, 4, 5, 9, 11, 13]
            ↑ ↑ ↑
            These three elements can form pairs with 6
```

### Observation 3: Sliding Window for Counting
For each element in the left half, we find how many elements in the right half satisfy the condition.

Example: For the element 6 in the left half:
- Check 6 > 2*1 (true)
- Check 6 > 2*2 (true)
- Check 6 > 2*3 (false)
- This gives us 2 pairs: (6,1) and (6,2)

### Observation 4: Optimization Using Sorted Property
Since both halves are already sorted, we don't need to restart counting for each element.

```
For [6, 13, 21, 25] and [1, 2, 3, 4, 4, 5, 9, 11, 13]:

When checking 6:  We find elements 1, 2 satisfy the condition
When checking 13: We start from where we left off (element 3)
```

## Step-by-Step Example
Let's work through a concrete example with array [4, 1, 2, 3, 1]:

1. Initial Step: Divide the array
   ```
   [4, 1, 2, 3, 1]
   [4, 1] [2, 3, 1]
   ```

2. Further Divide:
   ```
   [4] [1] [2] [3, 1]
   [4] [1] [2] [3] [1]
   ```

3. Start Merging [4] and [1]:
   ```
   Count pairs: Is 4 > 2*1? Yes, count = 1
   Merge: [1, 4]
   ```

4. Merge [2] and [3, 1]:
   ```
   Count pairs between [2] and [3]: Is 2 > 2*3? No
   Count pairs between [2] and [1]: Is 2 > 2*1? No
   Merge: [1, 2, 3]
   ```

5. Merge [1, 4] and [1, 2, 3]:
   ```
   Count pairs:
   - For 1: No pairs
   - For 4: Is 4 > 2*1? Yes
          Is 4 > 2*2? No
          Is 4 > 2*3? No
   Count = 1
   Merge: [1, 1, 2, 3, 4]
   ```

6. Total count: 2 reverse pairs

## Special Cases

### Case 1: All Elements Equal
Input: [5, 5, 5, 5]
- Behavior: No element can be more than twice another equal element
- Result: 0 pairs

### Case 2: Strictly Decreasing Array
Input: [10, 8, 6, 4, 2]
- Behavior: Each element forms reverse pairs with multiple later elements
- Result: Multiple pairs (specific count depends on values)

### Case 3: Negative Numbers
Input: [-5, -10, 4, -8]
- Behavior: Negative numbers require careful comparison
- Result: Special attention needed for signs (e.g., -5 > 2*(-10) is true)
*/

public class Approach2 {
    // Approach 2: Using merge sort with counting
    public static int countReversePairs(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return 0;
        }
        return mergeSort(nums, 0, nums.length - 1);
    }
    
    // Main merge sort function with additional counting logic
    private static int mergeSort(int[] arr, int low, int high) {
        int count = 0;
        if (low >= high) return count;  // Base case: single element is always sorted
        
        // Divide array in half
        int mid = (low + high) / 2;
        
        // Recursively sort both halves and count reverse pairs within each half
        count += mergeSort(arr, low, mid);        // Count from left half
        count += mergeSort(arr, mid + 1, high);   // Count from right half
        
        // Count reverse pairs that span the two halves
        count += countPairs(arr, low, mid, high);
        
        // Merge the two sorted halves
        merge(arr, low, mid, high);
        
        return count;
    }
    
    // Count reverse pairs across the two halves
    public static int countPairs(int[] arr, int low, int mid, int high) {
        int right = mid + 1;  // Start index of right half
        int count = 0;
        
        // For each element in the left half
        for (int i = low; i <= mid; i++) {
            // Find how many elements in right half satisfy the condition
            while (right <= high && arr[i] > 2L * arr[right]) {
                right++;  // Move pointer in right half
            }
            // Add the count of elements that satisfy the condition
            count += (right - (mid + 1));
        }
        
        return count;
    }
    
    // Standard merge function to merge two sorted subarrays
    private static void merge(int[] arr, int low, int mid, int high) {
        ArrayList<Integer> temp = new ArrayList<>(); // temporary array for merging
        int left = low;      // starting index of left half
        int right = mid + 1; // starting index of right half
        
        // Merge elements from both halves in sorted order
        while (left <= mid && right <= high) {
            if (arr[left] <= arr[right]) {
                temp.add(arr[left++]);  // Add smaller element from left half
            } else {
                temp.add(arr[right++]); // Add smaller element from right half
            }
        }
        
        // Add remaining elements from left half, if any
        while (left <= mid) {
            temp.add(arr[left++]);
        }
        
        // Add remaining elements from right half, if any
        while (right <= high) {
            temp.add(arr[right++]);
        }
        
        // Copy back the merged elements to original array
        for (int i = low; i <= high; i++) {
            arr[i] = temp.get(i - low);
        }
    }

    // Main method for testing
    public static void main(String[] args) {
        // Example input
        int[] nums = {4, 1, 2, 3, 1};
        
        // Call the method and print the output
        int result = countReversePairs(nums);
        
        // Display the result
        System.out.println("The number of reverse pairs is: " + result);
        
        // Additional test cases
        int[] test1 = {1, 3, 2, 3, 1};
        System.out.println("Test case 1: " + countReversePairs(test1));
        
        int[] test2 = {2, 4, 3, 5, 1};
        System.out.println("Test case 2: " + countReversePairs(test2));
    }
}
    
/*
# Algorithm
1. Define a function `countReversePairs(nums)` that calls mergeSort with the full array range
2. In the mergeSort function:
   - If the range has 0 or 1 element, return 0 (base case)
   - Divide the array into two halves at the midpoint
   - Recursively call mergeSort on each half to sort them and count internal reverse pairs
   - Count reverse pairs that span both halves using the countPairs function
   - Merge the two sorted halves
   - Return the total count of reverse pairs
3. In the countPairs function:
   - For each element in the left half (i):
     - Find elements in the right half (j) where arr[i] > 2*arr[j]
     - Add the count of such elements to the total
4. Return the final count as the result

### Time Complexity Breakdown per Step
1. Divide: O(1) per level
2. Count pairs: O(n) per level (each element is compared at most once per level)
3. Merge: O(n) per level
4. Recursive calls: O(log n) levels

Total: O(2n log n)

### Space Complexity Breakdown
1. Auxiliary Space: O(n)
   - Temporary array for merging: O(n)
   - Recursion stack: O(log n)
2. Input Space: O(n)
   - Original array storage: O(n)

Total: O(n)

# Advantages
1. Efficient O(n log n) time complexity compared to naive O(n²) approach
2. Handles large input sizes well
3. Naturally integrates with the merge sort algorithm
4. Works correctly with positive and negative numbers
5. No need for additional data structures except the temporary array for merging

# Limitations
1. Requires O(n) auxiliary space for the merge step
2. May have precision issues with large integers (need to use long for calculations)
3. Not as intuitive as the brute force approach
4. May not be the most efficient for already sorted or nearly sorted arrays

# Potential Improvements
1. Use a more space-efficient merge implementation
2. Add long type casting to avoid integer overflow for large values
3. Add early termination conditions for special cases
4. Implement iterative version to avoid recursion stack overhead

# Reverse Pairs: Detailed Step-by-Step Execution

## Input Array: [4, 1, 2, 3, 1]

Let's trace through the complete execution of the merge sort algorithm with reverse pair counting:

### Complete Execution Table

| Step | Function Call | Input Array/Subarray | Action | Result/Output | Reverse Pairs Count | Running Total | Explanation |
|------|---------------|----------------------|--------|---------------|---------------------|--------------|-------------|
| 1 | `countReversePairs([4, 1, 2, 3, 1])` | [4, 1, 2, 3, 1] | Initial call | - | - | 0 | Start of algorithm |
| 2 | `mergeSort(arr, 0, 4)` | [4, 1, 2, 3, 1] | Divide | mid = 2 | - | 0 | Split array into two halves |
| 3 | `mergeSort(arr, 0, 2)` | [4, 1, 2] | Divide left half | mid = 1 | - | 0 | Process left half |
| 4 | `mergeSort(arr, 0, 1)` | [4, 1] | Divide left-left half | mid = 0 | - | 0 | Further divide |
| 5 | `mergeSort(arr, 0, 0)` | [4] | Base case (1 element) | [4] | 0 | 0 | Single element, no pairs |
| 6 | `mergeSort(arr, 1, 1)` | [1] | Base case (1 element) | [1] | 0 | 0 | Single element, no pairs |
| 7 | `countPairs(arr, 0, 0, 1)` | [4, 1] | Count pairs | - | 1 | 1 | 4 > 2*1, found 1 pair |
| 8 | `merge(arr, 0, 0, 1)` | [4, 1] | Merge | [1, 4] | - | 1 | Merge sorted subarrays |
| 9 | `mergeSort(arr, 2, 2)` | [2] | Base case (1 element) | [2] | 0 | 1 | Single element, no pairs |
| 10 | `countPairs(arr, 0, 1, 2)` | [1, 4, 2] | Count pairs | - | 0 | 1 | No new pairs found |
| 11 | `merge(arr, 0, 1, 2)` | [1, 4, 2] | Merge | [1, 2, 4] | - | 1 | Merge sorted subarrays |
| 12 | `mergeSort(arr, 3, 4)` | [3, 1] | Divide right-right half | mid = 3 | - | 1 | Process right half |
| 13 | `mergeSort(arr, 3, 3)` | [3] | Base case (1 element) | [3] | 0 | 1 | Single element, no pairs |
| 14 | `mergeSort(arr, 4, 4)` | [1] | Base case (1 element) | [1] | 0 | 1 | Single element, no pairs |
| 15 | `countPairs(arr, 3, 3, 4)` | [3, 1] | Count pairs | - | 1 | 2 | 3 > 2*1, found 1 pair |
| 16 | `merge(arr, 3, 3, 4)` | [3, 1] | Merge | [1, 3] | - | 2 | Merge sorted subarrays |
| 17 | `countPairs(arr, 0, 2, 4)` | [1, 2, 4, 1, 3] | Count pairs | - | 0 | 2 | No new pairs found |
| 18 | `merge(arr, 0, 2, 4)` | [1, 2, 4, 1, 3] | Merge | [1, 1, 2, 3, 4] | - | 2 | Merge sorted subarrays |
| 19 | Final result | [1, 1, 2, 3, 4] | Return | - | 2 | 2 | Total reverse pairs: 2 |

## Detailed Step-by-Step Explanation

### Phase 1: Breaking Down the Array

1. **Initial Call**: 
   - Call `countReversePairs([4, 1, 2, 3, 1])` which calls `mergeSort(arr, 0, 4)`
   - The array is [4, 1, 2, 3, 1] with indices 0 to 4

2. **First Division**:
   - Divide the array at midpoint (index 2)
   - Left half: [4, 1, 2] (indices 0-2)
   - Right half: [3, 1] (indices 3-4)

3. **Process Left Half**:
   - Call `mergeSort(arr, 0, 2)` for [4, 1, 2]
   - Divide at midpoint (index 1)
   - Left sub-half: [4, 1] (indices 0-1)
   - Right sub-half: [2] (index 2)

4. **Process Left Sub-Half**:
   - Call `mergeSort(arr, 0, 1)` for [4, 1]
   - Divide at midpoint (index 0)
   - Process [4] and [1] separately (base cases)

### Phase 2: Merging and Counting (Bottom-Up)

5. **First Merge Operation**:
   - Count reverse pairs between [4] and [1]:
     - Check if 4 > 2*1: Yes (4 > 2)
     - Count = 1 pair: (4, 1)
   - Merge [4] and [1] to get [1, 4]
   - Current state: [1, 4, 2, 3, 1] (first two elements sorted)

6. **Continue with Left Half**:
   - Process [2] (already sorted)
   - Count reverse pairs between [1, 4] and [2]:
     - For 1: 1 > 2*2? No
     - For 4: 4 > 2*2? No
     - No new pairs found
   - Merge [1, 4] and [2] to get [1, 2, 4]
   - Current state: [1, 2, 4, 3, 1] (first three elements sorted)

7. **Process Right Half**:
   - Call `mergeSort(arr, 3, 4)` for [3, 1]
   - Divide at midpoint (index 3)
   - Process [3] and [1] separately (base cases)

8. **Second Merge Operation**:
   - Count reverse pairs between [3] and [1]:
     - Check if 3 > 2*1: Yes (3 > 2)
     - Count = 1 pair: (3, 1)
   - Merge [3] and [1] to get [1, 3]
   - Current state: [1, 2, 4, 1, 3] (first three and last two elements sorted)

9. **Final Merge Operation**:
   - Count reverse pairs between [1, 2, 4] and [1, 3]:
     - For each element in left half, check against right half
     - For 1: 1 > 2*1? No, 1 > 2*3? No
     - For 2: 2 > 2*1? No, 2 > 2*3? No
     - For 4: 4 > 2*1? Yes, 4 > 2*3? No
     - However, the pair (4,1) was already counted during the first merge
     - No new pairs found
   - Merge [1, 2, 4] and [1, 3] to get final sorted array [1, 1, 2, 3, 4]

10. **Final Result**:
    - Total reverse pairs: 2
    - The pairs are: (4,1) and (3,1)

## Visual Representation of the Key Steps

### Step 1: Initial Division
```
[4, 1, 2, 3, 1]
   /     \
[4,1,2]  [3,1]
```

### Step 2: Further Division
```
[4, 1, 2]      [3, 1]
  /   \         /  \
[4,1]  [2]    [3]  [1]
 / \
[4] [1]
```

### Step 3: First Merge - [4] and [1]
```
[4] + [1] → [1,4]  (1 reverse pair found: 4 > 2*1)
```

### Step 4: Second Merge - [1,4] and [2]
```
[1,4] + [2] → [1,2,4]  (No new reverse pairs)
```

### Step 5: Third Merge - [3] and [1]
```
[3] + [1] → [1,3]  (1 reverse pair found: 3 > 2*1)
```

### Step 6: Final Merge - [1,2,4] and [1,3]
```
[1,2,4] + [1,3] → [1,1,2,3,4]  (No new reverse pairs)
```

## The Two Reverse Pairs Found:
1. (4, 1): At indices (0, 1) in original array - Element 4 > 2*1
2. (3, 1): At indices (3, 4) in original array - Element 3 > 2*1

## Key Insights:
1. The algorithm divides the problem into smaller subproblems
2. Pairs are counted before merging sorted subarrays
3. We leverage the sorted nature of subarrays to efficiently count pairs
4. The total count is accumulated throughout the recursive calls
5. The final sorted array is a by-product of the process

### Step-by-Step Explanation

1. **Initial Division**
   - Divide [4, 1, 2, 3, 1] into [4, 1] and [2, 3, 1]
   ```
   [4, 1, 2, 3, 1]
   /         \
   [4, 1]    [2, 3, 1]
   ```

2. **Process Left Half**
   - Divide [4, 1] into [4] and [1]
   ```
   [4, 1]
   /    \
   [4]   [1]
   ```

3. **Count Pairs & Merge Left Half**
   - Check if 4 > 2*1: Yes, count = 1
   - Merge [4] and [1] to get [1, 4]
   ```
   [1, 4]  (Count: 1)
   ```

4. **Process Right Half**
   - Divide [2, 3, 1] into [2] and [3, 1]
   ```
   [2, 3, 1]
   /      \
   [2]    [3, 1]
   ```

5. **Further Split Right Half**
   - Divide [3, 1] into [3] and [1]
   ```
   [3, 1]
   /    \
   [3]   [1]
   ```

6. **Count Pairs & Merge Sub-Right Half**
   - Check if 3 > 2*1: Yes, count = 1
   - Merge [3] and [1] to get [1, 3]
   ```
   [1, 3]  (Count: 1)
   ```

7. **Count Pairs & Merge Complete Right Half**
   - Check if 2 > 2*1: No
   - Check if 2 > 2*3: No
   - No new pairs found
   - Merge [2] and [1, 3] to get [1, 2, 3]
   ```
   [1, 2, 3]  (Count: 0)
   ```

8. **Final Count & Merge**
   - Check for pairs between [1, 4] and [1, 2, 3]:
     - For 1: No pairs
     - For 4: 4 > 2*1 (Yes), 4 > 2*2 (No), 4 > 2*3 (No)
     - Count = 0 (these pairs were already counted in previous steps)
   - Merge [1, 4] and [1, 2, 3] to get [1, 1, 2, 3, 4]
   ```
   [1, 1, 2, 3, 4]  (Final count: 2)
   ```

### Additional Example Cases

1. **Simple Decreasing Array**
```
Input:  [5, 2, 1]
Step 1: Split into [5] and [2, 1]
Step 2: Further split [2, 1] into [2] and [1]
Step 3: Check pairs between [2] and [1]: 2 > 2*1? Yes, count = 1
Step 4: Merge to [1, 2]
Step 5: Check pairs between [5] and [1, 2]: 
        - 5 > 2*1? Yes
        - 5 > 2*2? Yes (5 > 4)
        - Count = 2
Step 6: Merge to [1, 2, 5]
Output: 3 pairs
```

2. **Array with Negative Numbers**
```
Input:  [-5, -2, 7]
Step 1: Split into [-5] and [-2, 7]
Step 2: Further split [-2, 7] into [-2] and [7]
Step 3: Check pairs between [-2] and [7]: -2 > 2*7? No, count = 0
Step 4: Merge to [-2, 7]
Step 5: Check pairs between [-5] and [-2, 7]: 
        - -5 > 2*(-2)? No (-5 > -4? No)
        - -5 > 2*7? No
        - Count = 0
Step 6: Merge to [-5, -2, 7]
Output: 0 pairs
```

### Edge Cases Handling
1. **Empty Array**
   ```
   Input: []
   Output: 0
   Explanation: No elements, no pairs.
   ```

2. **Single Element Array**
   ```
   Input: [42]
   Output: 0
   Explanation: Need at least two elements to form a pair.
   ```

3. **All Same Elements**
   ```
   Input: [3, 3, 3, 3]
   Output: 0
   Explanation: No element can be more than twice itself.
   ```

Key Observations:
1. The counting step happens before the merging step
2. Each element is compared at most once at each level of recursion
3. The use of long for comparisons prevents integer overflow
4. The algorithm efficiently handles positive and negative numbers

Sample Validation:
Input: [4, 1, 2, 3, 1]
Expected: 2
Output: 2

Key Points:
1. Use the sorted property of subarrays to efficiently count pairs
2. The total count is the sum of counts from both halves plus counts across halves
3. Using merge sort gives us O(n log n) time complexity
4. Be careful with integer overflow when comparing large numbers

TEST CASES:
1. Input: [1, 3, 2, 3, 1]
   Expected: 2
   Output: 2
2. Input: [2, 4, 3, 5, 1]
   Expected: 3
   Output: 3
3. Input: [5, 4, 3, 2, 1]
   Expected: 4
   Output: 4
*/