package Array.Subarray_with_XOR_K;

import java.util.HashMap;
import java.util.Map;

/* 
 # Problem: Subarray with XOR K
 Given an array ‘A’ consisting of ‘N’ integers and an integer ‘B’, find the number of subarrays of array ‘A’ whose bitwise XOR( ⊕ ) of all elements is equal to ‘B’.
 A subarray of an array is obtained by removing some(zero or more) elements from the front and back of the array.
 
 ## Example:
 ### Input: ‘N’ = 4 ‘B’ = 2, ‘A’ = [1, 2, 3, 2]
 ### Output: 3

 Explanation: Subarrays have bitwise xor equal to ‘2’ are: [1, 2, 3, 2], [2], [2].

# Intuition
- Use prefix XOR and a hash map to efficiently track subarrays with the target XOR value
- Leverage the property: If prefix XOR up to index i is `xr` and we need XOR = K, then we need previous prefix XOR = `xr ⊕ K`

## Basic Understanding
The problem asks us to count all subarrays where the XOR of all elements equals K. A subarray is a contiguous section of the original array. We need to efficiently find all such subarrays without generating all possible subarrays (which would be inefficient).

## Key Observations with Examples

### Observation 1: Prefix XOR Property
Observation: Assume, the prefix XOR of a subarray ending at index i is xr. In that subarray, we will search for another subarray ending at index i, whose XOR is equal to k. Here, we need to observe that if there exists another subarray ending at index i, with XOR k, then the prefix XOR of the rest of the subarray will be xr^k. The below image will clarify the concept:
---
    x ^ k = xr
    x ^ k ^ k = xr ^ k 
    x = xr ^ k
---

    arr = [4, 2, 2, 6, 4], k = 6
              i   
    xr = 4 ⊕ 2 = 6
    prefix XOR = x = xr ⊕ k = 6 ⊕ 6 = 0

If the XOR of elements from index 0 to i is `xr`, and we want a subarray ending at index i with XOR = K, then we need a previous position j where the XOR from 0 to j equals `xr ⊕ K`.

* Example: 
  * Array: [4, 2, 2, 6, 4], K = 6
  * If at i=1, prefix XOR = x = xr ⊕ K = 6 ⊕ 6 = 0
  * For subarray XOR = 6, we need previous prefix XOR = 0⊕6 = 6

Now, there may exist multiple subarrays with the prefix XOR xr^k. So, the number of subarrays with XOR k that we can generate from the entire subarray ending at index i, is exactly equal to the number of subarrays with the prefix XOR xr^k, present in that subarray.

So, for a subarray ending at index i, instead of counting the subarrays with XOR k, we can count the subarrays with the prefix XOR xr^k present in it.

That is why, instead of searching the subarrays with XOR k, we will keep the occurrence of the prefix XOR of the subarrays using a map data structure. 

In the map, we will store every prefix XOR calculated, with its occurrence in a <key, value> pair. Now, at index i, we just need to check the map data structure to get the number of times that the subarrays with the prefix XOR xr^k occur. Then we will simply add that number to our count.

We will apply the above process for all possible indices of the given array. The possible values of the index i can be from 0 to n-1(where n = size of the array).
### Observation 2: Mathematical Formula
For a subarray [j+1, i] to have XOR = K:
* prefix_xor[i] ⊕ prefix_xor[j] = K
* This can be rewritten as: prefix_xor[j] = prefix_xor[i] ⊕ K

### Observation 3: Using Hash Map for Efficiency
By storing frequency of previous prefix XORs in a hash map, we can instantly check if there exist subarrays ending at current position with XOR = K.

```
Visualization:
Array:    [4,  2,  2,  6,  4]
Position:  0   1   2   3   4

Prefix XOR:  4   6   4   2   6

For K = 6:
At i=0: xr = 4, looking for 4⊕6 = 2 (not found)
At i=1: xr = 6, looking for 6⊕6 = 0 (found 1 time)
At i=2: xr = 4, looking for 4⊕6 = 2 (not found)
At i=3: xr = 2, looking for 2⊕6 = 4 (found 2 times)
At i=4: xr = 6, looking for 6⊕6 = 0 (found 1 time)

Total count: 1 + 2 + 1 = 4
```

## Step-by-Step Example
Let's work through the example [4, 2, 2, 6, 4] with K = 6:

1. Step One:
   ```
   Initialize: 
   - xr = 0 (running XOR)
   - cnt = 0 (subarray count)
   - Map = {0:1} (initialize with 0 XOR having frequency 1)
   ```
   This initialization handles the case where the entire subarray from beginning has XOR = K.

2. Step Two:
   ```
   Process i=0, a[0] = 4:
   - xr = 0 ⊕ 4 = 4
   - x = 4 ⊕ 6 = 2 (need previous prefix XOR = 2)
   - Map doesn't contain 2, so no valid subarrays ending here
   - Update Map: {0:1, 4:1}
   - cnt = 0
   ```

3. Step Three:
   ```
   Process i=1, a[1] = 2:
   - xr = 4 ⊕ 2 = 6
   - x = 6 ⊕ 6 = 0 (need previous prefix XOR = 0)
   - Map contains 0 with frequency 1, so add 1 to cnt
   - Update Map: {0:1, 4:1, 6:1}
   - cnt = 1
   ```
   Found subarray [0,1] with XOR = 6

4. Step Four:
   ```
   Process i=2, a[2] = 2:
   - xr = 6 ⊕ 2 = 4
   - x = 4 ⊕ 6 = 2 (need previous prefix XOR = 2)
   - Map doesn't contain 2, so no valid subarrays ending here
   - Update Map: {0:1, 4:2, 6:1}
   - cnt = 1
   ```

5. Step Five:
   ```
   Process i=3, a[3] = 6:
   - xr = 4 ⊕ 6 = 2
   - x = 2 ⊕ 6 = 4 (need previous prefix XOR = 4)
   - Map contains 4 with frequency 2, so add 2 to cnt
   - Update Map: {0:1, 4:2, 6:1, 2:1}
   - cnt = 3
   ```
   Found subarrays [0,3] and [2,3] with XOR = 6

6. Step Six:
   ```
   Process i=4, a[4] = 4:
   - xr = 2 ⊕ 4 = 6
   - x = 6 ⊕ 6 = 0 (need previous prefix XOR = 0)
   - Map contains 0 with frequency 1, so add 1 to cnt
   - Update Map: {0:1, 4:2, 6:2, 2:1}
   - cnt = 4
   ```
   Found subarray [0,4] with XOR = 6

## Special Cases

### Case 1: Empty Subarray
Input: Empty array
- Not valid for this problem
- Result: 0 (no subarrays)

### Case 2: Single Element Equal to K
Input: [6], K = 6
- Behavior: Single element forms a valid subarray
- Result: 1 (the element itself)

### Case 3: No Valid Subarrays
Input: [1, 3, 5], K = 10
- Behavior: No combination creates XOR of 10
- Result: 0 (no valid subarrays)
*/

public class Approach3 {
    // Approach 3: Using HashMap 
    public static int subarraysWithXorK(int[] a, int k) {
        int n = a.length; // Size of the given array
        int xr = 0;       // Running XOR (prefix XOR)
        int cnt = 0;      // Count of valid subarrays
        
        // Map to store frequency of prefix XORs
        Map<Integer, Integer> mpp = new HashMap<>();
        
        // Initialize map with 0 XOR having frequency 1
        // This accounts for subarrays starting from index 0
        mpp.put(xr, 1);

        for (int i = 0; i < n; i++) {
            // Update running XOR by including current element
            xr = xr ^ a[i];

            // Calculate the complementary XOR value needed
            // If current prefix XOR is 'xr' and we need XOR 'k',
            // then we need to find previous prefix XOR 'xr ^ k'
            int x = xr ^ k;

            // If we found complementary XOR in our map,
            // add its frequency to our count
            if (mpp.containsKey(x)) {
                cnt += mpp.get(x);
            }

            // Update the frequency of current prefix XOR in map
            if (mpp.containsKey(xr)) {
                mpp.put(xr, mpp.get(xr) + 1);
            } else {
                mpp.put(xr, 1);
            }
        }
        return cnt;
    }

    // Main method for testing
    public static void main(String[] args) {
        // Example input
        int[] a = {4, 2, 2, 6, 4};
        int k = 6;

        // Call the method and print the output
        int result = subarraysWithXorK(a, k);

        // Display the result
        System.out.println("The number of subarrays with XOR k is: " + result);
        
        // Additional test case
        int[] b = {1, 2, 3, 2};
        k = 2;
        result = subarraysWithXorK(b, k);
        System.out.println("For array [1,2,3,2] with k=2, result: " + result);
    }
}
/*
# Algorithm
1. Initialize a running XOR variable `xr` to 0
2. Initialize a HashMap to store prefix XOR frequencies, initially with {0:1}
3. Initialize a counter `cnt` to 0
4. Iterate through the array:
   a. Update running XOR with current element: `xr = xr ^ a[i]`
   b. Calculate complement XOR: `x = xr ^ k`
   c. If complement exists in map, add its frequency to counter
   d. Update frequency of current running XOR in map
5. Return the counter

### Time Complexity Breakdown per Step
1. Initialization: O(1)
2. Array iteration: O(N)
3. HashMap operations per iteration: O(1) average case
Total: O(N)

### Space Complexity Breakdown
1. Auxiliary Space: O(N)
   - HashMap can store up to N different prefix XORs in worst case
2. Input Space: O(N)
   - Original array
Total: O(N)

## Question: Why do we need to set the value of 0 beforehand?
Let’s understand this using an example. Assume the given array is [3, 3, 1, 1, 1] and k is 3. Now, for index 0, we get the total prefix XOR as 3, and k is also 3. So, the prefix XOR xr^k will be = 3^3 = 0. Now, if the value is not previously set for the key 0 in the map, we will get the default value 0 and we will add 0 to our answer. This will mean that we have not found any subarray with XOR 3 till now. But this should not be the case as index 0 itself is a subarray with XOR k i.e. 3.
So, in order to avoid this situation we need to set the value of 0 as 1 on the map beforehand.

# Advantages
1. Efficient O(N) time complexity compared to O(N²) brute force
2. Handles all edge cases gracefully
3. Uses mathematical property of XOR to transform the problem
4. Avoids generating all possible subarrays explicitly

# Limitations
1. Requires extra O(N) space for the HashMap
2. Might face integer overflow if dealing with very large arrays (can be mitigated)
3. Performance depends on HashMap efficiency (could be affected by poor hash functions)

# Potential Improvements
1. Use array instead of HashMap if the range of array elements is small
2. Implement early termination if specific conditions are met
3. Parallelize the algorithm for very large arrays

# Step-by-Step Process with Dry Run

## Example Input: [4, 2, 2, 6, 4], K = 6
# Subarray with XOR K - Detailed Dry Run

## Example Input: [4, 2, 2, 6, 4], K = 6

### Detailed Execution Table

| Step | Array Element | Prefix XOR | Looking for (XOR ⊕ K) | Map Before | Map Update | Count | Subarrays Found |
|------|---------------|-----------|---------------------|------------|------------|-------|-----------------|
| Init | - | 0 | - | {} | {0:1} | 0 | - |
| 1 | 4 | 4 | 4⊕6=2 | {0:1} | {0:1, 4:1} | 0 | None |
| 2 | 2 | 6 | 6⊕6=0 | {0:1, 4:1} | {0:1, 4:1, 6:1} | 1 | [4,2] |
| 3 | 2 | 4 | 4⊕6=2 | {0:1, 4:1, 6:1} | {0:1, 4:2, 6:1} | 1 | None |
| 4 | 6 | 2 | 2⊕6=4 | {0:1, 4:2, 6:1} | {0:1, 4:2, 6:1, 2:1} | 3 | [4,2,2,6], [2,6] |
| 5 | 4 | 6 | 6⊕6=0 | {0:1, 4:2, 6:1, 2:1} | {0:1, 4:2, 6:2, 2:1} | 4 | [4,2,2,6,4] |

## Visual Representation of Subarrays Found
```
Array:          [4,  2,  2,  6,  4]
                 ↑   ↑
                 └───┘   Subarray with XOR = 6 (Found in Step 2)
                 
Array:          [4,  2,  2,  6,  4]
                 ↑       ↑
                 └───────┘   Subarray with XOR = 6 (Found in Step 4)
                 
Array:          [4,  2,  2,  6,  4]
                         ↑   ↑
                         └───┘   Subarray with XOR = 6 (Found in Step 4)
                 
Array:          [4,  2,  2,  6,  4]
                 ↑               ↑
                 └───────────────┘   Subarray with XOR = 6 (Found in Step 5)
```

## Step-by-Step Explanation

### 1. Initialization
  - Set running XOR (xr) = 0
  - Set count (cnt) = 0
  - Initialize HashMap with entry {0:1} (meaning XOR value 0 has been seen 1 time)
  - This initialization is crucial as it handles the case where a subarray starts from index 0

### 2. Process First Element (a[0] = 4)
  - Calculate new prefix XOR: xr = 0 ⊕ 4 = 4
  - Calculate x = xr ⊕ K = 4 ⊕ 6 = 2 (looking for prefix XOR = 2)
  - Check if 2 exists in map: No
  - No subarrays found ending at this position
  - Update map: Add {4:1} to map → {0:1, 4:1}

### 3. Process Second Element (a[1] = 2)
  - Calculate new prefix XOR: xr = 4 ⊕ 2 = 6
  - Calculate x = xr ⊕ K = 6 ⊕ 6 = 0 (looking for prefix XOR = 0)
  - Check if 0 exists in map: Yes, with frequency 1
  - Add frequency to count: cnt = 0 + 1 = 1
  - First subarray found: [4,2] with XOR = 6
  - Update map: Add {6:1} to map → {0:1, 4:1, 6:1}

### 4. Process Third Element (a[2] = 2)
  - Calculate new prefix XOR: xr = 6 ⊕ 2 = 4
  - Calculate x = xr ⊕ K = 4 ⊕ 6 = 2 (looking for prefix XOR = 2)
  - Check if 2 exists in map: No
  - No new subarrays found
  - Update map: Increment 4's frequency → {0:1, 4:2, 6:1}

### 5. Process Fourth Element (a[3] = 6)
  - Calculate new prefix XOR: xr = 4 ⊕ 6 = 2
  - Calculate x = xr ⊕ K = 2 ⊕ 6 = 4 (looking for prefix XOR = 4)
  - Check if 4 exists in map: Yes, with frequency 2
  - Add frequency to count: cnt = 1 + 2 = 3
  - Two new subarrays found:
    - [2,2,6] (from index 1 to 3)
    - [6] (index 3)
  - Update map: Add {2:1} to map → {0:1, 4:2, 6:2, 2:1}

### 6. Process Fifth Element (a[4] = 4)
  - Calculate new prefix XOR: xr = 2 ⊕ 4 = 6
  - Calculate x = xr ⊕ K = 6 ⊕ 6 = 0 (looking for prefix XOR = 0)
  - Check if 0 exists in map: Yes, with frequency 1
  - Add frequency to count: cnt = 3 + 1 = 4
  - One new subarray found: [4,2,2,6,4] (from index 0 to 4)
  - Update map: Increment 6's frequency → {0:1, 4:2, 6:2, 2:1}

## Why This Algorithm Works

1. **XOR Property Leverage**: 
   - If prefix XOR from 0 to i is XOR₁
   - And prefix XOR from 0 to j is XOR₂
   - Then XOR of subarray from j+1 to i is XOR₁ ⊕ XOR₂

2. **Mathematical Insight**:
   - For a subarray to have XOR = K, we need:
   - prefix_xor[i] ⊕ prefix_xor[j] = K
   - Which can be rewritten as: prefix_xor[j] = prefix_xor[i] ⊕ K

3. **HashMap Efficiency**:
   - Instead of checking all possible previous indices
   - We store all seen prefix XORs in a hash map with their frequencies
   - This allows O(1) lookup to find how many valid subarrays end at current position

## Additional Examples with Input/Output Analysis

### Example 1: [1, 2, 3, 2], K = 2

| Step | Element | Prefix XOR | Looking for | Map Before | Map After | Count | Subarrays |
|------|---------|------------|-------------|------------|-----------|-------|-----------|
| Init | - | 0 | - | {} | {0:1} | 0 | - |
| 1 | 1 | 1 | 1⊕2=3 | {0:1} | {0:1, 1:1} | 0 | None |
| 2 | 2 | 3 | 3⊕2=1 | {0:1, 1:1} | {0:1, 1:1, 3:1} | 1 | [2] |
| 3 | 3 | 0 | 0⊕2=2 | {0:1, 1:1, 3:1} | {0:2, 1:1, 3:1} | 1 | None |
| 4 | 2 | 2 | 2⊕2=0 | {0:2, 1:1, 3:1} | {0:2, 1:1, 3:1, 2:1} | 3 | [3,2], [1,2,3,2] |

Final Output: 3 subarrays with XOR = 2: [2], [3,2], [1,2,3,2]

### Example 2: [5, 3, 8, 3, 10], K = 8

| Step | Element | Prefix XOR | Looking for | Map Before | Map After | Count | Subarrays |
|------|---------|------------|-------------|------------|-----------|-------|-----------|
| Init | - | 0 | - | {} | {0:1} | 0 | - |
| 1 | 5 | 5 | 5⊕8=13 | {0:1} | {0:1, 5:1} | 0 | None |
| 2 | 3 | 6 | 6⊕8=14 | {0:1, 5:1} | {0:1, 5:1, 6:1} | 0 | None |
| 3 | 8 | 14 | 14⊕8=6 | {0:1, 5:1, 6:1} | {0:1, 5:1, 6:1, 14:1} | 1 | [8] |
| 4 | 3 | 9 | 9⊕8=1 | {0:1, 5:1, 6:1, 14:1} | {0:1, 5:1, 6:1, 14:1, 9:1} | 1 | None |
| 5 | 10 | 3 | 3⊕8=11 | {0:1, 5:1, 6:1, 14:1, 9:1} | {0:1, 5:1, 6:1, 14:1, 9:1, 3:1} | 1 | None |

Final Output: 1 subarray with XOR = 8: [8]

### Step-by-Step Explanation

1. **Initialization**
   - Set xr = 0, cnt = 0, mpp = {0:1}
   ```
   Array: [4, 2, 2, 6, 4], K = 6
   Map: {0:1}
   Count: 0
   ```

2. **Process First Element (4)**
   - xr = 0 ⊕ 4 = 4
   - x = 4 ⊕ 6 = 2 (Looking for previous prefix XOR = 2)
   - 2 not in map, so no subarrays found
   - Update map: {0:1, 4:1}
   ```
   Array: [4, 2, 2, 6, 4], current at [4]
   Map: {0:1, 4:1}
   Count: 0
   ```

3. **Process Second Element (2)**
   - xr = 4 ⊕ 2 = 6
   - x = 6 ⊕ 6 = 0 (Looking for previous prefix XOR = 0)
   - 0 in map with count 1, so add 1 to result
   - Update map: {0:1, 4:1, 6:1}
   ```
   Array: [4, 2, 2, 6, 4], current at [4, 2]
   Map: {0:1, 4:1, 6:1}
   Count: 1
   Subarrays found: [4, 2]
   ```

4. **Process Third Element (2)**
   - xr = 6 ⊕ 2 = 4
   - x = 4 ⊕ 6 = 2 (Looking for previous prefix XOR = 2)
   - 2 not in map, so no new subarrays found
   - Update map: {0:1, 4:2, 6:1}
   ```
   Array: [4, 2, 2, 6, 4], current at [4, 2, 2]
   Map: {0:1, 4:2, 6:1}
   Count: 1
   ```

5. **Process Fourth Element (6)**
   - xr = 4 ⊕ 6 = 2
   - x = 2 ⊕ 6 = 4 (Looking for previous prefix XOR = 4)
   - 4 in map with count 2, so add 2 to result
   - Update map: {0:1, 4:2, 6:1, 2:1}
   ```
   Array: [4, 2, 2, 6, 4], current at [4, 2, 2, 6]
   Map: {0:1, 4:2, 6:1, 2:1}
   Count: 3
   Subarrays found: [4, 2], [4, 2, 2, 6], [2, 6]
   ```

6. **Process Fifth Element (4)**
   - xr = 2 ⊕ 4 = 6
   - x = 6 ⊕ 6 = 0 (Looking for previous prefix XOR = 0)
   - 0 in map with count 1, so add 1 to result
   - Update map: {0:1, 4:2, 6:2, 2:1}
   ```
   Array: [4, 2, 2, 6, 4], current at [4, 2, 2, 6, 4]
   Map: {0:1, 4:2, 6:2, 2:1}
   Count: 4
   Subarrays found: [4, 2], [4, 2, 2, 6], [2, 6], [4, 2, 2, 6, 4]
   ```

### Additional Example Cases

1. **Simple Array with Multiple Valid Subarrays**
```
Input: [1, 2, 3, 2], K = 2
Step 1: Map = {0:1}, xr = 0, Looking for 0⊕2 = 2, not found, Map = {0:1, 1:1}
Step 2: Map = {0:1, 1:1}, xr = 1⊕2 = 3, Looking for 3⊕2 = 1, found 1 time, Map = {0:1, 1:1, 3:1}, Count = 1
Step 3: Map = {0:1, 1:1, 3:1}, xr = 3⊕3 = 0, Looking for 0⊕2 = 2, not found, Map = {0:2, 1:1, 3:1}, Count = 1
Step 4: Map = {0:2, 1:1, 3:1}, xr = 0⊕2 = 2, Looking for 2⊕2 = 0, found 2 times, Map = {0:2, 1:1, 3:1, 2:1}, Count = 3
Output: 3
```

2. **Array with No Valid Subarrays**
```
Input: [5, 7, 8], K = 10
Step 1: Map = {0:1}, xr = 0⊕5 = 5, Looking for 5⊕10 = 15, not found, Map = {0:1, 5:1}
Step 2: Map = {0:1, 5:1}, xr = 5⊕7 = 2, Looking for 2⊕10 = 8, not found, Map = {0:1, 5:1, 2:1}
Step 3: Map = {0:1, 5:1, 2:1}, xr = 2⊕8 = 10, Looking for 10⊕10 = 0, found 1 time, Map = {0:1, 5:1, 2:1, 10:1}, Count = 1
Output: 1
```

### Edge Cases Handling
1. **Array with Single Element Equal to K**
   ```
   Input: [6], K = 6
   Map = {0:1}, xr = 0⊕6 = 6, Looking for 6⊕6 = 0, found 1 time, Map = {0:1, 6:1}, Count = 1
   Output: 1
   ```

2. **Array with Zero Elements Equal to K**
   ```
   Input: [0, 0, 0], K = 0
   Initialization: Map = {0:1}, Count = 0
   Step 1: xr = 0⊕0 = 0, Looking for 0⊕0 = 0, found 1 time, Map = {0:2}, Count = 1
   Step 2: xr = 0⊕0 = 0, Looking for 0⊕0 = 0, found 2 times, Map = {0:3}, Count = 3
   Step 3: xr = 0⊕0 = 0, Looking for 0⊕0 = 0, found 3 times, Map = {0:4}, Count = 6
   Output: 6 (Includes subarrays: [0], [0], [0], [0,0], [0,0], [0,0,0])
   ```

Key Observations:
1. The algorithm correctly identifies all subarrays with the target XOR
2. The use of prefix XOR and hash map provides O(N) time complexity
3. The initialization of map with {0:1} handles cases where a subarray starts from the beginning
4. XOR's properties (associativity, commutativity) make this approach possible

Sample Validation:
Input: [4, 2, 2, 6, 4], K = 6
Expected: 4
Output: 4

Key Points:
1. Prefix XOR is a powerful technique for subarray problems
2. XOR of a value with itself results in 0
3. The formula xr ⊕ k = x can be rewritten as x = xr ⊕ k
4. Hash map allows efficient lookup of required previous prefix XORs

TEST CASES:
1. Input: [4, 2, 2, 6, 4], K = 6
   Expected: 4
   Output: 4
2. Input: [1, 2, 3, 2], K = 2
   Expected: 3
   Output: 3
3. Input: [5, 6, 7, 8, 9], K = 5
   Expected: 2
   Output: 2
4. Input: [0, 0, 0], K = 0
   Expected: 6
   Output: 6
*/