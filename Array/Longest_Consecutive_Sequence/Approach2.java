package Array.Longest_Consecutive_Sequence;
import java.util.Arrays;
/* 
# Longest Consecutive Sequence
Find the length of the longest consecutive sequence in an unsorted array.

# Intuition
- Sort the array to make consecutive elements adjacent
- Track the last seen element and count consecutive sequences
- Handle duplicates by skipping them
- Keep track of the maximum sequence length found so far

## Basic Understanding
Given an unsorted array of integers, we need to find the length of the longest sequence of consecutive integers present in the array. For example, in [100, 200, 1, 2, 3, 4, 4], the longest consecutive sequence is [1, 2, 3, 4] with length 4.

## Key Observations with Examples

### Observation 1: Sorting Benefits
- Sorting brings consecutive elements next to each other
- Makes it easier to check consecutive elements with O(1) comparison
* Example: [1, 3, 2, 4] -> [1, 2, 3, 4]

### Observation 2: Handling Duplicates
- Need to skip duplicate elements to avoid counting them multiple times
- Only increment counter for unique consecutive elements
Example: [1, 2, 2, 3] should give sequence length 3, not 4
```
Original: [1, 2, 2, 3]
Count sequence as: 1 -> 2 -> 3 (length = 3)
Skip duplicate 2
```

## Step-by-Step Example
Let's work through array [100, 200, 1, 2, 3, 4, 4]:

1. Sort the array:
   ```
   [1, 2, 3, 4, 4, 100, 200]
   First we sort to group consecutive numbers
   ```

2. Process elements:
   ```
   lastSmaller = MIN_VALUE, cnt = 0, longest = 1
   i=0: 1 starts new sequence, cnt = 1
   i=1: 2 is consecutive to 1, cnt = 2
   i=2: 3 is consecutive to 2, cnt = 3
   i=3: 4 is consecutive to 3, cnt = 4
   i=4: 4 is duplicate, skip
   i=5: 100 starts new sequence, cnt reset to 1
   i=6: 200 starts new sequence, cnt reset to 1
   ```

## Special Cases

### Case 1: Empty Array
Input: []
- Behavior: Return 0
- Result: 0

### Case 2: Array with Duplicates
Input: [1, 2, 2, 3]
- Behavior: Skip duplicates
- Result: 3

### Case 3: Single Element
Input: [5]
- Behavior: Return 1
- Result: 1
*/

public class Approach2 {
    //Approach 2: Better Approach using Sorting    
    public static int longestSuccessiveElements(int[] a) {
        int n = a.length;
        // Handle empty array
        if (n == 0) return 0;

        // Sort array to group consecutive elements
        Arrays.sort(a);
        
        // Initialize variables
        int lastSmaller = Integer.MIN_VALUE; // Track last element
        int cnt = 0;                         // Current sequence length
        int longest = 1;                     // Maximum sequence length

        // Iterate through sorted array
        for (int i = 0; i < n; i++) {
            if (a[i] - 1 == lastSmaller) {
                // Current element continues the sequence
                cnt += 1;
                lastSmaller = a[i];
            } else if (a[i] != lastSmaller) {
                // Start new sequence (skip duplicates)
                cnt = 1;
                lastSmaller = a[i];
            }
            longest = Math.max(longest, cnt);
        }
        return longest;
    }

    public static void main(String[] args) {
        int[] a = {100, 200, 1, 2, 3, 4, 4};
        int ans = longestSuccessiveElements(a);
        System.out.println("The longest consecutive sequence is " + ans);
    }
}

/*
# Algorithm
1. Check if array is empty, return 0 if true
2. Sort the array using Arrays.sort()
3. Initialize tracking variables (lastSmaller, cnt, longest)
4. Iterate through sorted array:
   - If current element is consecutive to lastSmaller, increment count
   - If current element is different from lastSmaller, start new sequence
   - Update longest sequence length
5. Return longest sequence found

### Time Complexity Breakdown per Step
1. Array sort: O(n log n)
2. Array iteration: O(n)
Total: O(n log n)

### Space Complexity Breakdown
1. Auxiliary Space: O(1)
- Only using a few variables regardless of input size
2. Input Space: O(1)
- No additional space proportional to input
Total: O(1)

# Advantages
1. Simple implementation
2. Space efficient (O(1) extra space)
3. Handles duplicates effectively
4. Easy to understand and maintain

# Limitations
1. Modifies original array due to sorting
2. Not optimal time complexity (O(n log n) vs possible O(n))
3. Not suitable for streaming data
4. Not suitable when preserving original array order is required

# Potential Improvements
1. Implement O(n) solution using HashSet
2. Add option to preserve original array
3. Handle negative numbers more efficiently
4. Add parallel processing for very large arrays

# Step-by-Step Process with Dry Run

I'll create a detailed breakdown of the algorithm with a comprehensive table and step-by-step explanation.

Let's take the example input: [100, 200, 1, 2, 3, 4, 4]

### Detailed Execution Table
```
Step | Current Element | Array State             | lastSmaller | cnt | longest | Action Taken                    | Result State
-----|----------------|--------------------------|-------------|-----|---------|---------------------------------|-------------
0    | -              | [100,200,1,2,3,4,4]      | MIN_VALUE   | 0   | 1       | Initial state                   | -
1    | -              | [1,2,3,4,4,100,200]      | MIN_VALUE   | 0   | 1       | Array sorted                    | -
2    | 1              | [1,2,3,4,4,100,200]      | 1           | 1   | 1       | New sequence starts             | longest=1
3    | 2              | [1,2,3,4,4,100,200]      | 2           | 2   | 2       | Consecutive to 1                | longest=2
4    | 3              | [1,2,3,4,4,100,200]      | 3           | 3   | 3       | Consecutive to 2                | longest=3
5    | 4              | [1,2,3,4,4,100,200]      | 4           | 4   | 4       | Consecutive to 3                | longest=4
6    | 4              | [1,2,3,4,4,100,200]      | 4           | 4   | 4       | Duplicate, skip                 | longest=4
7    | 100            | [1,2,3,4,4,100,200]      | 100         | 1   | 4       | New sequence, not consecutive   | longest=4
8    | 200            | [1,2,3,4,4,100,200]      | 200         | 1   | 4       | New sequence, not consecutive   | longest=4
```

### Step-by-Step Process Explanation:

1. **Initial Setup**
   - Array is unsorted: [100, 200, 1, 2, 3, 4, 4]
   - lastSmaller = MIN_VALUE
   - cnt = 0
   - longest = 1

2. **Sorting Phase**
   - Array is sorted to: [1, 2, 3, 4, 4, 100, 200]
   - This brings consecutive elements together

3. **Processing Element 1**
   - Current element: 1
   - Since 1 ≠ lastSmaller
   - Set cnt = 1
   - Update lastSmaller = 1
   - longest remains 1

4. **Processing Element 2**
   - Current element: 2
   - 2-1 = lastSmaller (1)
   - Increment cnt to 2 
   - Update lastSmaller = 2
   - longest updated to 2

5. **Processing Element 3**
   - Current element: 3
   - 3-1 = lastSmaller (2)
   - Increment cnt to 3
   - Update lastSmaller = 3
   - longest updated to 3

6. **Processing Element 4**
   - Current element: 4
   - 4-1 = lastSmaller (3)
   - Increment cnt to 4
   - Update lastSmaller = 4
   - longest updated to 4

7. **Processing Duplicate 4**
   - Current element: 4
   - 4 = lastSmaller (4)
   - Skip (duplicate)
   - cnt remains 4
   - longest remains 4

8. **Processing Element 100**
   - Current element: 100
   - Not consecutive to 4
   - Reset cnt to 1
   - Update lastSmaller = 100
   - longest remains 4

9. **Processing Final Element 200**
   - Current element: 200
   - Not consecutive to 100
   - Reset cnt to 1
   - Update lastSmaller = 200
   - longest remains 4

### Key Decision Points in Each Step:

1. **Element Comparison Decision**:
   - If (current - 1 == lastSmaller) → Consecutive element found
   - If (current == lastSmaller) → Duplicate found
   - If neither → New sequence starts

2. **Counter Management**:
   - Increment cnt for consecutive elements
   - Reset cnt=1 for new sequences
   - Keep cnt unchanged for duplicates

3. **Longest Sequence Tracking**:
   - Update longest after each element processing
   - Keep maximum value encountered so far

This detailed breakdown shows how the algorithm processes each element and maintains the state variables to find the longest consecutive sequence, which in this case is 4 (the sequence 1,2,3,4).

### Edge Cases Handling
1. **Empty Array**
   Input: []
   Output: 0
   Handles empty array check at start

2. **All Duplicates**
   Input: [1,1,1,1]
   Output: 1
   Skips duplicates using a[i] != lastSmaller check

3. **Single Element**
   Input: [5]
   Output: 1
   Works correctly due to longest initialization

Key Observations:
1. Sorting simplifies consecutive element detection
2. Need to handle duplicates explicitly
3. Maintaining lastSmaller helps track sequences

Sample Validation:
Input: [100, 200, 1, 2, 3, 4, 4]
Expected: 4
Output: 4

TEST CASES:
1. Input: [100, 200, 1, 2, 3, 4, 4]
   Expected: 4
   Output: 4
2. Input: [1, 2, 2, 3]
   Expected: 3
   Output: 3
3. Input: []
   Expected: 0
   Output: 0
4. Input: [5]
   Expected: 1
   Output: 1
5. Input: [1, 1, 1]
   Expected: 1
   Output: 1 
*/