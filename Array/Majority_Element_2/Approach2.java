package Array.Majority_Element_2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/* 
# Problem: Majority Element II
Given an integer array of size n, find all elements that appear more than ⌊ n/3 ⌋ times.

# Intuition:
Use a better data structure to reduce the number of look-up operations and hence the time complexity. Moreover, we have been calculating the count of the same element again and again – so we have to reduce that also.

- The problem requires finding elements that appear more than ⌊n/3⌋ times in an array
- Key insight: There can be at most 2 majority elements that appear more than ⌊n/3⌋ times
- Using a HashMap to track frequency efficiently

## Key Observations with Examples

### Observation 1: Maximum Possible Majority Elements
- Since we're looking for elements appearing > n/3 times
- The array can have at most 2 such elements
- Proof: If an element appears > n/3 times, it takes up more than one-third of the array
  * Three such elements would exceed the array length
  * Therefore, maximum possible majority elements is 2

### Observation 2: HashMap Usage Pattern
Example: [1, 1, 1, 2, 2, 3]
```
HashMap evolution:
Step 1: {1:1}
Step 2: {1:2}
Step 3: {1:3} ✓ (Found first majority)
Step 4: {1:3, 2:1}
Step 5: {1:3, 2:2}
Step 6: {1:3, 2:2, 3:1}
```

## Step-by-Step Example
Let's work through array [11, 33, 33, 11, 33, 11]:

1. Initialize:
   ```
   n = 6
   mini = floor(6/3) + 1 = 3
   HashMap = {}
   result = []
   ```

2. Process elements:
   ```
   11: count = 1
   33: count = 1
   33: count = 2
   11: count = 2
   33: count = 3 (add to result)
   11: count = 3 (add to result)
   ```
*/
public class Approach2 {
    // Approach 2: Using HashMap to track frequencies
    public static List<Integer> majorityElement(int[] v) {
        int n = v.length; // array size
        List<Integer> ls = new ArrayList<>(); // result list
        
        // HashMap to store element frequencies
        HashMap<Integer, Integer> mpp = new HashMap<>();
        
        // Minimum frequency needed to be majority element
        int mini = (int)(n / 3) + 1;
        
        // Count frequencies and check for majority elements
        for (int i = 0; i < n; i++) {
            // Get current count or 0 if not present
            int value = mpp.getOrDefault(v[i], 0);
            // Increment count
            mpp.put(v[i], value + 1);
            
            // Check if current element becomes majority
            if (mpp.get(v[i]) == mini) {
                ls.add(v[i]);
            }
            // Early exit if we found 2 majority elements
            if (ls.size() == 2) break;
        }
        
        return ls;
    }

    public static void main(String args[]) {
        // Test cases
        int[][] testCases = {
            {11, 33, 33, 11, 33, 11},
            {1, 2, 2, 2, 1, 1},
            {1, 1, 1, 2, 2, 3}
        };
        
        for (int i = 0; i < testCases.length; i++) {
            System.out.println("Test Case " + (i + 1) + ":");
            System.out.println("Input: " + Arrays.toString(testCases[i]));
            List<Integer> result = majorityElement(testCases[i]);
            System.out.println("Majority Elements: " + result);
            System.out.println();
        }
    }
}
/*
# Algorithm
1. Calculate minimum frequency required (⌊n/3⌋ + 1)
2. Create HashMap to store element frequencies
3. Iterate through array:
   - Update element frequency in HashMap
   - If frequency equals required minimum, add to result
   - If found 2 majority elements, break
4. Return result list

### Time Complexity Breakdown
1. HashMap operations: O(1)
2. Single array traversal: O(n)
Total: O(n)

### Space Complexity Breakdown
1. HashMap space: O(n)
2. Result list: O(1) (max size 2)
Total: O(n)

# Advantages
1. Single pass solution
2. Early termination when 2 majority elements found
3. Simple implementation using HashMap

# Limitations
1. Requires extra space for HashMap
2. Not optimal for very small arrays due to HashMap overhead

# Step-by-Step Process with Dry Run
Let me help you with a more detailed breakdown of the Majority Element II problem with step-by-step tables and explanations.

# Detailed Table Analysis

Let's analyze the input array [11, 33, 33, 11, 33, 11] with n = 6, so ⌊n/3⌋ + 1 = 3

```
Step | Element | Current Count | HashMap State      | Result List | Detailed Action
-----|---------|---------------|-------------------|-------------|----------------
0    | -       | -             | {}               | []          | Initialize empty HashMap and result list
1    | 11      | 1             | {11:1}           | []          | Add 11 with count 1
2    | 33      | 1             | {11:1, 33:1}     | []          | Add 33 with count 1
3    | 33      | 2             | {11:1, 33:2}     | []          | Increment 33's count
4    | 11      | 2             | {11:2, 33:2}     | []          | Increment 11's count
5    | 33      | 3             | {11:2, 33:3}     | [33]        | 33 reaches threshold (3), add to result
6    | 11      | 3             | {11:3, 33:3}     | [33, 11]    | 11 reaches threshold (3), add to result
```

## Step-by-Step Process Explanation:

1. **Initial Setup**
   - Array length (n) = 6
   - Threshold = ⌊6/3⌋ + 1 = 3
   - Initialize empty HashMap and result list

2. **First Element (11)**
   - Current element: 11
   - Action: Add to HashMap with count 1
   - HashMap state: {11:1}
   - Not yet a majority element

3. **Second Element (33)**
   - Current element: 33
   - Action: Add to HashMap with count 1
   - HashMap state: {11:1, 33:1}
   - Not yet a majority element

4. **Third Element (33)**
   - Current element: 33
   - Action: Increment count of 33
   - HashMap state: {11:1, 33:2}
   - Still not a majority element

5. **Fourth Element (11)**
   - Current element: 11
   - Action: Increment count of 11
   - HashMap state: {11:2, 33:2}
   - Both elements close to majority

6. **Fifth Element (33)**
   - Current element: 33
   - Action: Increment count of 33 to 3
   - HashMap state: {11:2, 33:3}
   - 33 becomes majority element
   - Add 33 to result list

7. **Sixth Element (11)**
   - Current element: 11
   - Action: Increment count of 11 to 3
   - HashMap state: {11:3, 33:3}
   - 11 becomes majority element
   - Add 11 to result list

# Additional Test Cases with Analysis

## Test Case 1: [1, 2, 2, 2, 1, 1]
```
Step | Element | HashMap State    | Result List | Explanation
-----|---------|-----------------|-------------|-------------
1    | 1       | {1:1}           | []          | First 1
2    | 2       | {1:1, 2:1}      | []          | First 2
3    | 2       | {1:1, 2:2}      | []          | Second 2
4    | 2       | {1:1, 2:3}      | [2]         | 2 becomes majority
5    | 1       | {1:2, 2:3}      | [2]         | Second 1
6    | 1       | {1:3, 2:3}      | [2, 1]      | 1 becomes majority
```

## Test Case 2: [1, 1, 1, 2, 2, 3]
```
Step | Element | HashMap State        | Result List | Explanation
-----|---------|---------------------|-------------|-------------
1    | 1       | {1:1}               | []          | First 1
2    | 1       | {1:2}               | []          | Second 1
3    | 1       | {1:3}               | [1]         | 1 becomes majority
4    | 2       | {1:3, 2:1}          | [1]         | First 2
5    | 2       | {1:3, 2:2}          | [1]         | Second 2
6    | 3       | {1:3, 2:2, 3:1}     | [1]         | Only 1 is majority
```

# Key Points to Remember:

1. **Frequency Checking**:
   - We only add elements to result when they exactly hit the threshold
   - This prevents duplicates in the result list

2. **Early Termination**:
   - Code stops when we find 2 majority elements
   - This is because there can't be more than 2 elements appearing > n/3 times

3. **HashMap Benefits**:
   - Constant time lookups and updates
   - Automatically handles any range of numbers
   - No need for sorting or multiple passes

4. **Edge Cases Handled**:
   - Empty arrays return empty list
   - Arrays with one element return that element
   - Arrays with all same elements return single element
   - Arrays with no majority elements return empty list

This detailed breakdown should help you understand exactly how the algorithm processes each element and maintains its state through the execution.

### Edge Cases Handling
1. **Empty Array**
   ```
   Input: []
   Output: []
   Explanation: No majority elements in empty array
   ```

2. **Single Element**
   ```
   Input: [1]
   Output: [1]
   Explanation: Single element is always majority
   ```

TEST CASES:
1. Input: [11, 33, 33, 11, 33, 11]
   Expected: [11, 33]
   Output: [11, 33]
2. Input: [1, 2]
   Expected: [1, 2]
   Output: [1, 2]
3. Input: [1, 1, 1, 2, 2, 3]
   Expected: [1]
   Output: [1]

Dry Run: 
Let’s take the example of arr[] = {10,20,40,40,40}, n=5.

First, we create an unordered map to store the count of each element.

Now traverse through the array 

Found 10 at index 0, increase the value of key 10 in the map by 1.

Found 20 at index 1, increase the value of key 20 in the map by 1.

Found 40 at index 2, increase the value of key 40 in the map by 1.

Found 40 at index 3, increase the value of key 40 in the map by 1.

Found 40 at index 4, increase the value of key 40 in the map by 1.

Now, Our map will look like this:

10 -> 1 
20 -> 1
40 -> 3
Now traverse through the map, 

We found that the value of key 40 is greater than the floor(N/3). So, 40 is the answer.

*/
