package Array.Count_Subarray_Sum_equals_k;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/* 
# Problem Title: Count Subarrays with Sum Equals K

Intutition:
In this approach, we are going to use the concept of the prefix sum to solve this problem. Here, the prefix sum of a subarray ending at index i simply means the sum of all the elements of that subarray.
The core concept is using prefix sum to efficiently count subarrays that sum to a target value k. A prefix sum tracks the cumulative sum up to each index.

- If we have a prefix sum x at index i, and we're looking for subarrays with sum k
- Then we need to find how many previous prefix sums equal (x-k)
- Those previous positions represent the start points of valid subarrays

## Basic Understanding
We need to find the count of continuous subarrays within an array whose elements sum up to a given target k.

## Key Observations with Examples

### Observation 1: Prefix Sum Pattern
- A prefix sum at index i represents the sum of all elements from index 0 to i
- We can use this to find subarrays that sum to k
```
Array:     [3,  1,  2,  4]
PrefixSum: [3,  4,  6, 10]
```

### Observation 2: Complementary Sum
- If prefix sum at index i is x, and we want sum k
- Then we look for previous prefix sum (x-k)
- The count of (x-k) represents number of valid subarrays ending at i
```
If current prefixSum = 10 and k = 6
We look for prefixSum = 10-6 = 4 in our history
```

### Observation 3: HashMap Usage
- We use HashMap to store prefix sums and their frequencies
- This gives O(1) lookup to find complementary sums
```
Map: {0:1, 3:1, 4:1, 6:1, 10:1}
```

## Step-by-Step Example
Let's work through array [3,1,2,4] with k=6:

1. Initialize:
   ```
   Map = {0:1}
   prefixSum = 0
   count = 0
   ```

2. i=0, arr[0]=3:
   ```
   prefixSum = 3
   look for (3-6) = -3 in map: not found
   Map = {0:1, 3:1}
   ```

3. i=1, arr[1]=1:
   ```
   prefixSum = 4
   look for (4-6) = -2 in map: not found
   Map = {0:1, 3:1, 4:1}
   ```

4. i=2, arr[2]=2:
   ```
   prefixSum = 6
   look for (6-6) = 0 in map: found(1)
   count = 1
   Map = {0:1, 3:1, 4:1, 6:1}
   ```

## Special Cases

### Case 1: Single Element Equals K
Input: [6], k=6
- Behavior: Works because of initial {0:1} in map
- Result: 1 subarray found

### Case 2: Zero Elements
Input: [0,0], k=0
- Behavior: Multiple valid subarrays possible
- Result: 3 subarrays ([0], [0], [0,0])
*/
public class Approach3 {
    // Approach 3: Prefix Sum with HashMap
    public static int findAllSubarraysWithGivenSum(int arr[], int k) {
        int n = arr.length;
        Map<Integer, Integer> mpp = new HashMap<>();
        int preSum = 0, cnt = 0;

        // Initialize map with 0 sum having frequency 1
        mpp.put(0, 1);

        for (int i = 0; i < n; i++) {
            // Calculate current prefix sum
            preSum += arr[i];

            // Calculate sum to remove (complementary sum)
            int remove = preSum - k;

            // Add frequency of complementary sum to result
            cnt += mpp.getOrDefault(remove, 0);

            // Update prefix sum frequency in map
            mpp.put(preSum, mpp.getOrDefault(preSum, 0) + 1);
        }
        return cnt;
    }

    public static void main(String[] args) {
        // Test cases
        int[] arr = {3, 1, 2, 4};
        int k = 6;
        
        // Print input
        System.out.println("Array: " + Arrays.toString(arr));
        System.out.println("Target sum: " + k);
        
        // Get and print result
        int result = findAllSubarraysWithGivenSum(arr, k);
        System.out.println("Number of subarrays with sum " + k + ": " + result);
    }
}
/*
# Algorithm
1. Initialize HashMap with (0,1) and variables prefixSum=0, count=0
2. Iterate through array:
   - Add current element to prefixSum
   - Calculate remove = prefixSum - k
   - Add frequency of remove from map to count
   - Update prefixSum frequency in map
3. Return final count

### Time Complexity Breakdown
1. HashMap operations: O(1)
2. Single array traversal: O(n)
Total: O(n)

### Space Complexity Breakdown
1. HashMap space: O(n)
2. Constant variables: O(1)
Total: O(n)

# Advantages
1. Single pass solution
2. Handles negative numbers
3. Works with zero sum subarrays

# Limitations
1. Extra space required for HashMap
2. May not handle very large numbers due to overflow
3. Not suitable for parallel processing

# Potential Improvements
1. Use long instead of int for larger numbers
2. Add input validation
3. Consider using ThreadLocal for parallel processing

# Step-by-Step Process with Dry Run

## Example Input: [3,1,2,4], k=6
I'll provide a detailed breakdown of the algorithm with a comprehensive step-by-step dry run.

Let's use the example array [3, 1, 2, 4] with k = 6

## Detailed Execution Table with Step-by-Step Analysis

```
Step | Index | Current | PrefixSum | Remove  | Map Before        | Count | Map After         | Subarrays Found
-----|--------|----------|-----------|---------|------------------|-------|-------------------|----------------
0    | -      | -        | 0         | -       | {}              | 0     | {0:1}            | None
1    | 0      | 3        | 3         | -3      | {0:1}           | 0     | {0:1, 3:1}       | None
2    | 1      | 1        | 4         | -2      | {0:1, 3:1}      | 0     | {0:1, 3:1, 4:1}  | None
3    | 2      | 2        | 6         | 0       | {0:1, 3:1, 4:1} | 1     | {0:1, 3:1, 4:1, 6:1} | [3,1,2]
4    | 3      | 4        | 10        | 4       | {0:1, 3:1, 4:1, 6:1} | 2 | {0:1, 3:1, 4:1, 6:1, 10:1} | [2,4]
```

## Step-by-Step Explanation

1. **Initialization (Step 0)**:
   - Map is initialized with {0:1}
   - PrefixSum = 0
   - Count = 0
   - This handles the case where the entire subarray from index 0 sums to k

2. **Processing 3 (Step 1)**:
   - Current element = 3
   - PrefixSum becomes 3
   - Remove = 3 - 6 = -3
   - Check if -3 exists in map (it doesn't)
   - Add (3,1) to map
   - No subarrays found yet

3. **Processing 1 (Step 2)**:
   - Current element = 1
   - PrefixSum becomes 4 (3 + 1)
   - Remove = 4 - 6 = -2
   - Check if -2 exists in map (it doesn't)
   - Add (4,1) to map
   - No subarrays found yet

4. **Processing 2 (Step 3)**:
   - Current element = 2
   - PrefixSum becomes 6 (4 + 2)
   - Remove = 6 - 6 = 0
   - Check if 0 exists in map (it does!)
   - Count increases by 1
   - Add (6,1) to map
   - Found subarray: [3,1,2]

5. **Processing 4 (Step 4)**:
   - Current element = 4
   - PrefixSum becomes 10 (6 + 4)
   - Remove = 10 - 6 = 4
   - Check if 4 exists in map (it does!)
   - Count increases by 1
   - Add (10,1) to map
   - Found subarray: [2,4]

## Key Points in Each Step:

1. **Map State Changes**:
   - Step 0: {0:1} (Initial state)
   - Step 1: {0:1, 3:1}
   - Step 2: {0:1, 3:1, 4:1}
   - Step 3: {0:1, 3:1, 4:1, 6:1}
   - Step 4: {0:1, 3:1, 4:1, 6:1, 10:1}

2. **Subarray Detection**:
   - First subarray found at Step 3: [3,1,2]
   - Second subarray found at Step 4: [2,4]

3. **Remove Value Significance**:
   - When Remove exists in map, it means we found a subarray
   - Remove = PrefixSum - k
   - When Remove is found in map, it means there exists a previous point where the difference between current sum and that point equals k

4. **Count Updates**:
   - Increases only when Remove exists in map
   - Final count = 2 (representing two valid subarrays)

## Visual Representation of Found Subarrays:
```
Array: [3, 1, 2, 4]
         -------    First subarray: [3,1,2] = 6
            ----    Second subarray: [2,4] = 6
```

This detailed breakdown shows how the algorithm uses prefix sums and a HashMap to efficiently find all subarrays that sum to k. The key insight is that at each step, we're not just looking at the current sum, but also checking if we can remove some prefix sum to get our target k.
```

### Edge Cases Handling
1. **Empty Array**
   ```
   Input: [], k=5
   Output: 0
   Explanation: No subarrays possible
   ```

2. **Array with Single Element**
   ```
   Input: [6], k=6
   Output: 1
   Explanation: Entire array is the subarray
   ```

TEST CASES:
1. Input: [3,1,2,4], k=6
   Expected: 2
   Output: 2
2. Input: [1,1,1], k=2
   Expected: 2
   Output: 2
3. Input: [1,-1,0], k=0
   Expected: 3
   Output: 3
 */

/* 
Question: Why do we need to set the value of 0?
Let’s understand this using an example. Assume the given array is [3, -3, 1, 1, 1] and k is 3. Now, for index 0, we get the total prefix sum as 3, and k is also 3. So, the prefix sum of the remove-part should be x-k = 3-3 = 0. Now, if the value is not previously set for the key 0 in the map, we will get the default value 0 for the key 0 and we will add 0 to our answer. This will mean that we have not found any subarray with sum 3 till now. But this should not be the case as index 0 itself is a subarray with sum k i.e. 3.
So, in order to avoid this situation we need to set the value of 0 as 1 on the map beforehand.
Let me explain this concept with a detailed dry run of the example [3, -3, 1, 1, 1] with k = 3, both with and without initializing {0:1} in the map.

# Case 1: Without Initializing {0:1}

```
Step | Index  | Current  | PrefixSum | Remove  | Map Before    |  Count | Map After      | Subarrays Found
-----|--------|----------|-----------|---------|---------------|--------|----------------|----------------
1    | 0      | 3        | 3         | 0       | {}            | 0      | {3:1}          | None (Problem!)
2    | 1      | -3       | 0         | -3      | {3:1}         | 0      | {3:1, 0:1}     | None
3    | 2      | 1        | 1         | -2      | {3:1, 0:1}    | 0      | {3:1, 0:1, 1:1}| None
4    | 3      | 1        | 2         | -1      | {3:1, 0:1, 1:1}| 0     | {..., 2:1}     | None
5    | 4      | 1        | 3         | 0       | {..., 2:1}    | 0      | {..., 3:1}     | None
```

# Case 2: With Initializing {0:1} (Correct Approach)

```
Step | Index | Current | PrefixSum | Remove | Map Before     | Count | Map After      | Subarrays Found
-----|--------|----------|-----------|---------|---------------|--------|----------------|----------------
0    | -      | -        | 0         | -       | {}            | 0      | {0:1}          | None
1    | 0      | 3        | 3         | 0       | {0:1}         | 1      | {0:1, 3:1}     | [3]
2    | 1      | -3       | 0         | -3      | {0:1, 3:1}    | 1      | {0:2, 3:1}     | None
3    | 2      | 1        | 1         | -2      | {0:2, 3:1}    | 1      | {0:2, 3:1, 1:1}| None
4    | 3      | 1        | 2         | -1      | {...}         | 1      | {..., 2:1}     | None
5    | 4      | 1        | 3         | 0       | {...}         | 2      | {..., 3:1}     | [1,1,1]
```

## Why This Matters: Step-by-Step Explanation

1. **At Index 0 (Critical Point)**:
   ```
   Current element: 3
   PrefixSum: 3
   k: 3
   Remove = PrefixSum - k = 3 - 3 = 0
   ```

   ### Without {0:1}:
   - Map doesn't contain 0
   - Count stays 0
   - Misses the valid subarray [3]

   ### With {0:1}:
   - Map contains 0
   - Count becomes 1
   - Correctly identifies [3] as a valid subarray

2. **Visual Representation of Valid Subarrays**:
   ```
   Array: [3, -3, 1, 1, 1]
   [3]           ⬅ First valid subarray (found because of {0:1})
         [1,1,1] ⬅ Second valid subarray
   ```

## Key Points:

1. **Purpose of {0:1}**:
   - Represents an empty subarray with sum 0
   - Helps identify subarrays starting from index 0
   - Essential for finding single-element subarrays that equal k

2. **Without {0:1}**:
   - Misses subarrays that start from index 0
   - Underreports the total count
   - Fails to identify single-element subarrays equal to k

3. **With {0:1}**:
   - Correctly identifies all valid subarrays
   - Maintains accurate count
   - Handles edge cases properly

## Example Cases That Would Fail Without {0:1}:

1. **Single Element Equal to K**:
   ```
   Array: [3], k=3
   Without {0:1}: Count = 0 (Wrong)
   With {0:1}: Count = 1 (Correct)
   ```

2. **Array Starting With K**:
   ```
   Array: [3,2,1], k=3
   Without {0:1}: Misses [3]
   With {0:1}: Finds [3] and any other valid subarrays
   ```

This is why initializing the map with {0:1} is crucial - it ensures we don't miss valid subarrays that start from the beginning of the array and correctly handle cases where individual elements equal our target sum k.
 */