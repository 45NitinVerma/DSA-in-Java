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

# Intuition
- The core concept is to merge two sorted arrays (nums1 and nums2) into a single sorted array
- Since nums1 already has enough space to hold the merged array, we need to modify nums1 in-place
- We use extra space to temporarily store the elements of nums1 before merging, which simplifies the process

## Basic Understanding
This problem asks us to combine two separately sorted arrays into one sorted array. The key constraint is that we must store the result in the first array (nums1), which has been padded with zeros to accommodate the elements from the second array (nums2).

## Key Observations with Examples

### Observation 1: In-place Modification
- We need to modify nums1 in-place rather than returning a new array
- nums1 has extra space (filled with zeros) to hold all elements from both arrays
- The challenge is to merge while preserving the sorted order

### Observation 2: Three-Pointer Approach with Temporary Array
- Create a temporary array to store the valid elements from nums1
- Use three pointers to track positions in the temp array, nums2, and the result array (nums1)
- Compare elements from temp and nums2, placing the smaller element into nums1

### Observation 3: Edge Cases
- If one array is empty (m=0 or n=0), we simply need to copy the non-empty array
- We don't need to process the trailing zeros in nums1 as they'll be overwritten
```
Example:
nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3

1. Copy first m elements of nums1 to temp: temp = [1,2,3]
2. Start merging using three pointers:
   - Pointer for temp: i = 0
   - Pointer for nums2: j = 0
   - Pointer for result in nums1: k = 0

3. Compare and merge:
   temp[0]=1 vs nums2[0]=2 → nums1[0]=1 (from temp)
   temp[1]=2 vs nums2[0]=2 → nums1[1]=2 (from temp)
   temp[2]=3 vs nums2[0]=2 → nums1[2]=2 (from nums2)
   temp[2]=3 vs nums2[1]=5 → nums1[3]=3 (from temp)
   No more elements in temp, copy remaining nums2 elements
   nums1[4]=5, nums1[5]=6 (from nums2)

4. Final nums1 = [1,2,2,3,5,6]
```

## Step-by-Step Example
Let's work through the example: nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3

1. Step One:
   ```
   Create temp array: temp = [1,2,3]
   Initialize pointers: i=0 (temp), j=0 (nums2), k=0 (nums1)
   ```
   We copy the first m elements of nums1 to preserve them.

2. Step Two:
   ```
   Compare temp[0]=1 and nums2[0]=2
   1 < 2, so nums1[0] = 1, increment i and k
   i=1, j=0, k=1
   nums1 = [1,2,3,0,0,0]
   ```

3. Step Three:
   ```
   Compare temp[1]=2 and nums2[0]=2
   2 == 2, so nums1[1] = 2, increment i and k
   i=2, j=0, k=2
   nums1 = [1,2,3,0,0,0]
   ```
   When elements are equal, we've chosen to prioritize temp array.

4. Step Four:
   ```
   Compare temp[2]=3 and nums2[0]=2
   3 > 2, so nums1[2] = 2, increment j and k
   i=2, j=1, k=3
   nums1 = [1,2,2,0,0,0]
   ```

5. Step Five:
   ```
   Compare temp[2]=3 and nums2[1]=5
   3 < 5, so nums1[3] = 3, increment i and k
   i=3, j=1, k=4
   nums1 = [1,2,2,3,0,0]
   ```
   Now i=3, which means we've processed all elements from temp.

6. Step Six:
   ```
   i >= m, so copy remaining elements from nums2
   nums1[4] = nums2[1] = 5
   nums1[5] = nums2[2] = 6
   nums1 = [1,2,2,3,5,6]
   ```

## Special Cases

### Case 1: Empty nums1 (m=0)
Input: nums1 = [0,0,0], m = 0, nums2 = [1,2,3], n = 3
- No elements in nums1 to merge
- Result: Copy all elements from nums2 to nums1
- Output: [1,2,3]

### Case 2: Empty nums2 (n=0)
Input: nums1 = [1,2,3], m = 3, nums2 = [], n = 0
- No elements in nums2 to merge
- Result: nums1 remains unchanged
- Output: [1,2,3]

### Case 3: One element in each array
Input: nums1 = [1,0], m = 1, nums2 = [2], n = 1
- Merge [1] and [2]
- Result: [1,2]
*/

public class Approach1 {
    // Approach 1: Using extra space array
    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        // Create a temporary array to store the first m elements of nums1
        int[] temp = new int[m];
        for (int x = 0; x < m; x++) {
            temp[x] = nums1[x];
        }
        
        // Initialize pointers for each array
        int i = 0; // pointer for temp array
        int j = 0; // pointer for nums2 array
        int k = 0; // pointer for nums1 array (result)
        
        // Merge elements from temp and nums2 into nums1
        while (i < m && j < n) {
            // Compare current elements from both arrays
            if (temp[i] <= nums2[j]) {
                // If temp element is smaller or equal, place it in nums1
                nums1[k++] = temp[i++];
            } else {
                // If nums2 element is smaller, place it in nums1
                nums1[k++] = nums2[j++];
            }
        }
        
        // If there are remaining elements in temp, copy them to nums1
        while (i < m) {
            nums1[k++] = temp[i++];
        }
        
        // If there are remaining elements in nums2, copy them to nums1
        while (j < n) {
            nums1[k++] = nums2[j++];
        }
    }
    
    // Main method for testing
    public static void main(String[] args) {
        // Example input
        int[] nums1 = {1, 2, 3, 0, 0, 0};
        int m = 3;
        int[] nums2 = {2, 5, 6};
        int n = 3;
        
        // Call the merge method
        merge(nums1, m, nums2, n);
        
        // Display the result
        System.out.print("Result: [");
        for (int i = 0; i < nums1.length; i++) {
            System.out.print(nums1[i]);
            if (i < nums1.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }
}

/*
# Algorithm
1. Create a temporary array "temp" to store the first m elements of nums1
2. Initialize three pointers: i (for temp), j (for nums2), and k (for result in nums1)
3. Compare elements from temp and nums2, placing the smaller one into nums1
4. After one array is exhausted, copy any remaining elements from the other array to nums1

### Time Complexity Breakdown per Step
1. Creating temporary array and copying elements: O(m)
2. Merging arrays using three pointers: O(m + n)
3. Copying remaining elements: O(m) or O(n) in worst case

Total: O(m + n) - we process each element exactly once

### Space Complexity Breakdown
1. Auxiliary Space: O(m) for the temporary array
2. Input Space: O(m + n) as nums1 already contains space for both arrays

Total: O(m) extra space

# Advantages
1. Simple and intuitive implementation
2. Stable sorting - maintains the relative order of equal elements
3. Easy to understand and implement
4. Works efficiently for small to medium-sized arrays

# Limitations
1. Requires O(m) extra space for the temporary array
2. Not as space-efficient as an in-place approach
3. Creates additional memory overhead due to the temporary array

# Potential Improvements
1. Implement an in-place solution by starting from the end of the arrays
2. Use a more memory-efficient approach that doesn't require additional space
3. Optimize for specific cases, such as when one array is much smaller than the other

# Step-by-Step Process with Dry Run

## Example Input: nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3

### Detailed Execution Table
```
Step | temp       | nums1          | i | j | k | Action
-----|------------|----------------|---|---|---|--------
0    | []         | [1,2,3,0,0,0]  | - | - | - | Initialize
1    | [1,2,3]    | [1,2,3,0,0,0]  | 0 | 0 | 0 | Copy first m elements to temp
2    | [1,2,3]    | [1,2,3,0,0,0]  | 1 | 0 | 1 | Compare: temp[0]=1 < nums2[0]=2, set nums1[0]=1
3    | [1,2,3]    | [1,2,3,0,0,0]  | 2 | 0 | 2 | Compare: temp[1]=2 == nums2[0]=2, set nums1[1]=2
4    | [1,2,3]    | [1,2,2,0,0,0]  | 2 | 1 | 3 | Compare: temp[2]=3 > nums2[0]=2, set nums1[2]=2
5    | [1,2,3]    | [1,2,2,3,0,0]  | 3 | 1 | 4 | Compare: temp[2]=3 < nums2[1]=5, set nums1[3]=3
6    | [1,2,3]    | [1,2,2,3,5,0]  | 3 | 2 | 5 | All temp elements processed, copy nums2[1]=5 to nums1[4]
7    | [1,2,3]    | [1,2,2,3,5,6]  | 3 | 3 | 6 | Copy nums2[2]=6 to nums1[5]
```

### Step-by-Step Explanation

1. **Create Temporary Array**
   - Create temp array and copy the first m elements: temp = [1,2,3]
   ```
   nums1 = [1,2,3,0,0,0]
   temp = [1,2,3]
   ```

2. **Initialize Pointers**
   - i=0 (pointer for temp)
   - j=0 (pointer for nums2)
   - k=0 (pointer for nums1)

3. **Compare and Merge Elements**
   - Compare temp[0]=1 and nums2[0]=2
   - 1 < 2, so place 1 in nums1[0], increment i and k
   ```
   nums1 = [1,2,3,0,0,0]
   i=1, j=0, k=1
   ```

4. **Continue Merging**
   - Compare temp[1]=2 and nums2[0]=2
   - 2 == 2, we choose from temp, place 2 in nums1[1], increment i and k
   ```
   nums1 = [1,2,3,0,0,0]
   i=2, j=0, k=2
   ```

5. **Process Smaller Element**
   - Compare temp[2]=3 and nums2[0]=2
   - 3 > 2, so place 2 in nums1[2], increment j and k
   ```
   nums1 = [1,2,2,0,0,0]
   i=2, j=1, k=3
   ```

6. **Continue Merging**
   - Compare temp[2]=3 and nums2[1]=5
   - 3 < 5, so place 3 in nums1[3], increment i and k
   ```
   nums1 = [1,2,2,3,0,0]
   i=3, j=1, k=4
   ```

7. **Process Remaining Elements**
   - i=3 >= m=3, so we've processed all temp elements
   - Copy remaining elements from nums2 to nums1
   ```
   nums1 = [1,2,2,3,5,6]
   ```

### Additional Example Cases

1. **Both Arrays Already Sorted**
```
Input:  nums1 = [1,3,5,0,0,0], m = 3, nums2 = [2,4,6], n = 3
Step 1: Create temp = [1,3,5]
Step 2: Merge elements comparing temp and nums2
Output: [1,2,3,4,5,6]
```

2. **First Array Elements All Smaller**
```
Input:  nums1 = [1,2,3,0,0,0], m = 3, nums2 = [4,5,6], n = 3
Step 1: Create temp = [1,2,3]
Step 2: Merge elements comparing temp and nums2
Output: [1,2,3,4,5,6]
```

### Edge Cases Handling
1. **Empty First Array**
   ```
   Input: nums1 = [0,0,0], m = 0, nums2 = [1,2,3], n = 3
   Output: [1,2,3]
   Explanation: Since m=0, we skip the temp array creation and copying, and directly copy all elements from nums2 to nums1.
   ```

2. **Empty Second Array**
   ```
   Input: nums1 = [1,2,3], m = 3, nums2 = [], n = 0
   Output: [1,2,3]
   Explanation: Since n=0, there are no elements to merge from nums2, so nums1 remains unchanged.
   ```

Key Observations:
1. The extra space approach simplifies the merging process
2. The three-pointer technique allows for efficient comparison and merging
3. We handle edge cases where one array is empty
4. The solution guarantees that the merged array is sorted

Sample Validation:
Input: nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
Expected: [1,2,2,3,5,6]
Output: [1,2,2,3,5,6]

Key Points:
1. Creating a temporary array preserves the original elements of nums1
2. The solution has a time complexity of O(m+n)
3. The space complexity is O(m) for the temporary array
4. The merging process is stable, maintaining the relative order of equal elements

TEST CASES:
1. Input: nums1 = [1], m = 1, nums2 = [], n = 0
   Expected: [1]
   Output: [1]
2. Input: nums1 = [0], m = 0, nums2 = [1], n = 1
   Expected: [1]
   Output: [1]
3. Input: nums1 = [4,5,6,0,0,0], m = 3, nums2 = [1,2,3], n = 3
   Expected: [1,2,3,4,5,6]
   Output: [1,2,3,4,5,6]
 */