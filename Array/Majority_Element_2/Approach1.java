package Array.Majority_Element_2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* 
# Problem: Majority Element II
Given an integer array of size n, find all elements that appear more than ⌊ n/3 ⌋ times.

# Intutiton: 
Observation: How many integers can occur more than floor(N/3) times in the given array:

If we closely observe, in the given array, there can be a maximum of two integers that can occur more than floor(N/3) times. Let’s understand it using the following scenario:
Assume there are 8 elements in the given array. Now, if there is any majority element, it should occur more than floor(8/3) = 2 times. So, the majority of elements should occur at least 3 times. Now, if we imagine there are 3 majority elements, then the total occurrence of them will be 3X3 = 9 i.e. greater than the size of the array. But this should not happen. That is why there can be a maximum of 2 majority elements.

## Basic Understanding 
- If array size is n, we're looking for elements appearing > ⌊n/3⌋ times
- For example, in array of size 9: ⌊9/3⌋ = 3, so we want elements appearing > 3 times
- Maximum possible majority elements is 2 because if there were 3 elements each appearing > n/3 times, total occurrences would exceed array size

## Key Observations with Examples

### Observation 1: Maximum Count Property
- For array size n, if element appears > ⌊n/3⌋ times, it's a majority element
- Example: n = 6, ⌊6/3⌋ = 2, so elements must appear > 2 times
```
Array: [11, 33, 33, 11, 33, 11]
33 appears 3 times (> 2)
11 appears 3 times (> 2)
Both are majority elements
```

### Observation 2: Maximum Two Elements
- There can be at most 2 majority elements
- Proof: If 3 elements each appear > n/3 times, total occurrences > n
```
For n = 6:
If three elements each appear > 2 times
Minimum total = 3 × 3 = 9 > 6 (array size)
Therefore impossible to have 3 majority elements
```

## Step-by-Step Example
Let's work through array [11, 33, 33, 11, 33, 11]:

1. Initial State:
   ```
   Array: [11, 33, 33, 11, 33, 11]
   n = 6, threshold = 6/3 = 2
   ```

2. First Pass (i = 0, v[i] = 11):
   ```
   Count occurrences of 11: 3
   3 > 2, so add 11 to result
   ```

3. Second Pass (i = 1, v[i] = 33):
   ```
   Count occurrences of 33: 3
   3 > 2, so add 33 to result
   ```
*/
public class Approach1 {
    // Approach 1: Using 2 nested loops
    public static List<Integer> majorityElement(int[] v) {
        int n = v.length; // Size of array
        List<Integer> ls = new ArrayList<>(); // Store majority elements
        
        // Outer loop to select each element
        for (int i = 0; i < n; i++) {
            // Skip if current element is already in result
            if (ls.size() == 0 || ls.get(0) != v[i]) {
                int count = 0;
                
                // Count occurrences of current element
                for (int j = 0; j < n; j++) {
                    if (v[j] == v[i]) {
                        count++;
                    }
                }
                
                // Add to result if count exceeds n/3
                if (count > (n / 3)) {
                    ls.add(v[i]);
                }
            }
            
            // Early termination - can't have more than 2 majority elements
            if (ls.size() == 2) break;
        }
        
        return ls;
    }

    public static void main(String args[]) {
        // Test cases
        int[][] testArrays = {
            {11, 33, 33, 11, 33, 11},  // Two majority elements
            {1, 2, 2, 2, 1, 1},        // Two majority elements
            {1, 1, 1, 2, 2, 3},        // One majority element
            {1, 2, 3, 4}               // No majority element
        };
        
        for (int i = 0; i < testArrays.length; i++) {
            System.out.println("Test case " + (i + 1) + ":");
            System.out.println("Input array: " + Arrays.toString(testArrays[i]));
            List<Integer> result = majorityElement(testArrays[i]);
            System.out.println("Majority elements: " + result);
            System.out.println();
        }
    }
}

/*
# Algorithm
1. Initialize empty result list
2. For each element in array:
   - Skip if element already in result
   - Count occurrences using inner loop
   - If count > n/3, add to result
   - Break if found 2 majority elements
3. Return result list

### Time Complexity Breakdown
1. Outer loop: O(n)
2. Inner loop: O(n)
3. Result list operations: O(1)
Total: O(n²)

### Space Complexity Breakdown
1. Result list: O(1) - maximum size 2
2. Count variable: O(1)
Total: O(1)

# Advantages
1. Simple implementation
2. Constant space complexity
3. Works well for small arrays
4. Early termination when 2 elements found

# Limitations
1. O(n²) time complexity
2. Not optimal for large arrays
3. Redundant counting of same elements

# Potential Improvements
1. Use HashMap to count frequencies in O(n)
2. Implement Boyer-Moore Voting Algorithm
3. Use sorting based approach

# Step-by-Step Process with Dry Run

## Example Input: [11, 33, 33, 11, 33, 11]

### Detailed Execution Table
```
Step | Element | Count | Result List | Action
-----|---------|-------|-------------|--------
1    | 11      | 3     | []         | Add 11
2    | 33      | 3     | [11]       | Add 33
3    | 33      | -     | [11,33]    | Skip (already in list)
4    | 11      | -     | [11,33]    | Break (2 elements found)
```

### Edge Cases Handling
1. **Empty Array**
   ```
   Input: []
   Output: []
   Explanation: No majority elements possible
   ```

2. **Single Element**
   ```
   Input: [1]
   Output: [1]
   Explanation: Single element always majority
   ```

TEST CASES:
1. Input: [11, 33, 33, 11, 33, 11]
   Expected: [11, 33]
   Output: [11, 33]
2. Input: [1, 2, 3]
   Expected: []
   Output: []
3. Input: [1, 1, 1, 2, 2, 3]
   Expected: [1]
   Output: [1]

*/