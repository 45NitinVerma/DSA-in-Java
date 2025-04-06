package Array.Count_Subarray_Sum_equals_k;
/* 
Problem: Subarray Sum Equals K
Given an array of integers nums and an integer k, return the total number of subarrays whose sum equals to k.
A subarray is a contiguous non-empty sequence of elements within an array.

Intuition: 
The core concept is to find all contiguous subarrays in an array whose elements sum up to a target value k.

- Main approach: Brute force using three nested loops
- Key insight: Generate all possible subarrays and check their sums
We will check the sum of every possible subarray and count how many of them are equal to k. To get every possible subarray sum, we will be using three nested loops. The first two loops(say i and j) will iterate over every possible starting index and ending index of a subarray. Basically, in each iteration, the subarray range will be from index i to index j. Using another loop we will get the sum of the elements of the subarray [i…..j]. Among all values of the sum calculated, we will only consider those that are equal to k.

Note: We are selecting every possible subarray using two nested loops and for each of them, we add all its elements using another loop.


## Basic Understanding
We need to count the number of contiguous sequences within the array where the elements add up to exactly k. For example, if we have array [3,1,2,4] and k=6, we need to find all sequences that sum to 6.

## Key Observations with Examples

### Observation 1: Subarray Properties
- A subarray must be contiguous (elements must be consecutive)
- We need start and end indices to define a subarray
- Empty subarrays are not considered
Example: In [3,1,2,4], valid subarrays include [3], [3,1], [3,1,2], etc.

### Observation 2: Sum Calculation
- Each subarray sum needs to be calculated separately
- We can get all possible subarrays using two loops (start and end indices)
Example: For subarray [3,1,2], sum = 3+1+2 = 6

## Step-by-Step Example
Let's work through array [3,1,2,4] with k=6:

1. First Iteration (i=0):
   ```
   [3] -> sum = 3
   [3,1] -> sum = 4
   [3,1,2] -> sum = 6 ✓ (Found first subarray)
   [3,1,2,4] -> sum = 10
   ```

2. Second Iteration (i=1):
   ```
   [1] -> sum = 1
   [1,2] -> sum = 3
   [1,2,4] -> sum = 7
   ```

## Special Cases

### Case 1: Single Element Equals K
Input: [6], k=6
- Behavior: Single element forms a valid subarray
- Result: Count = 1

### Case 2: Empty Array
Input: [], k=6
- Behavior: No subarrays possible
- Result: Count = 0
*/

import java.util.Arrays;

public class Approach1 {
    
    //Approach 1: Brute Force Approach using 3 nested loops
    public static int findAllSubarraysWithGivenSum(int arr[], int k) {
        int n = arr.length;
        int count = 0;  // Counter for valid subarrays
        
        // Outer loop: Starting index
        for (int i = 0; i < n; i++) {
            // Middle loop: Ending index
            for (int j = i; j < n; j++) {
                int subarraySum = 0;
                
                // Inner loop: Calculate sum of current subarray
                for (int m = i; m <= j; m++) {
                    subarraySum += arr[m];
                }
                
                // Check if current subarray sum equals k
                if (subarraySum == k) {
                    count++;
                }
            }
        }
        return count;
    }

    // Main method for testing
    public static void main(String[] args) {
        // Test case
        int[] arr = {3, 1, 2, 4};
        int k = 6;
        
        // Calculate and display result
        int result = findAllSubarraysWithGivenSum(arr, k);
        System.out.println("Array: " + Arrays.toString(arr));
        System.out.println("Target sum (k): " + k);
        System.out.println("Number of subarrays with sum " + k + ": " + result);
    }
}
/*
# Algorithm
1. Initialize a counter variable to 0
2. For each starting index i from 0 to n-1:
3. For each ending index j from i to n-1:
4. Calculate sum of subarray from index i to j
5. If sum equals k, increment counter
6. Return counter

### Time Complexity Breakdown per Step
1. Outer loop (i): O(n)
2. Middle loop (j): O(n)
3. Inner loop (sum calculation): O(n)
Total: O(n³)

### Space Complexity Breakdown
1. Auxiliary Space: O(1)
   - Only using constant extra space
2. Input Space: O(n)
   - Original array storage
Total: O(n)

# Advantages
1. Simple to understand and implement
2. Works for any array type (positive, negative, zero)
3. No extra space required

# Limitations
1. High time complexity O(n³)
2. Not suitable for large arrays
3. Performs redundant calculations

# Potential Improvements
1. Use prefix sum array to reduce time complexity to O(n²)
2. Use HashMap to achieve O(n) time complexity
3. Handle edge cases more efficiently

# Step-by-Step Process with Dry Run

## Example Input: [3,1,2,4], k=6

I'll provide a detailed step-by-step analysis with a comprehensive table and explanation.

Let's use the example array [3,1,2,4] with k=6:

### Detailed Execution Table
```
Step | i | j | Subarray    | Calculation      | Sum | Match k=6? | Count
-----|---|---|-------------|------------------|-----|------------|-------
1    | 0 | 0 | [3]        | 3                | 3   | No         | 0
2    | 0 | 1 | [3,1]      | 3+1              | 4   | No         | 0
3    | 0 | 2 | [3,1,2]    | 3+1+2            | 6   | Yes        | 1
4    | 0 | 3 | [3,1,2,4]  | 3+1+2+4          | 10  | No         | 1
5    | 1 | 1 | [1]        | 1                | 1   | No         | 1
6    | 1 | 2 | [1,2]      | 1+2              | 3   | No         | 1
7    | 1 | 3 | [1,2,4]    | 1+2+4            | 7   | No         | 1
8    | 2 | 2 | [2]        | 2                | 2   | No         | 1
9    | 2 | 3 | [2,4]      | 2+4              | 6   | Yes        | 2
10   | 3 | 3 | [4]        | 4                | 4   | No         | 2
```

### Step-by-Step Process Explanation:

1. **Starting with i=0:**
   - First checks single element [3]
   - Then [3,1]
   - Then [3,1,2] → Found first match! (sum = 6)
   - Finally [3,1,2,4]

2. **Moving to i=1:**
   - Checks [1]
   - Then [1,2]
   - Then [1,2,4]

3. **Moving to i=2:**
   - Checks [2]
   - Then [2,4] → Found second match! (sum = 6)

4. **Finally, i=3:**
   - Checks [4]

### Key Points from the Table:

1. **Pattern of Checking:**
   - For each starting index (i), we check all possible subarrays ending at j≥i
   - The sum is calculated cumulatively for each subarray

2. **Found Matches:**
   - [3,1,2] at step 3
   - [2,4] at step 9

3. **Counter Behavior:**
   - Starts at 0
   - Increments to 1 at step 3
   - Increments to 2 at step 9
   - Final result: 2 subarrays found

### Visual Representation of Matches:
```
Array: [3, 1, 2, 4]
Match 1: [3, 1, 2] ✓
Match 2: [2, 4] ✓
```

### Process Summary:
1. **Initialization Phase:**
   - Array: [3,1,2,4]
   - Target k: 6
   - Counter initialized: 0

2. **Processing Phase:**
   - Each element becomes a starting point
   - All possible endings are checked
   - Sum is calculated and compared with k=6

3. **Matching Phase:**
   - When sum equals k (6):
     * Counter increments
     * Continues checking remaining possibilities

4. **Completion Phase:**
   - All possible subarrays checked
   - Final count: 2 matches found

### Memory Trace for Matches:
```
First Match:  [3,1,2]   = 3+1+2 = 6
Second Match: [2,4]     = 2+4   = 6
```

This detailed breakdown shows how the algorithm systematically checks every possible subarray combination and identifies those that sum to the target value k=6. Each step in the table represents a single subarray check, with the count incrementing whenever a match is found.

### Edge Cases Handling
1. **Empty Array**
   ```
   Input: [], k=5
   Output: 0
   Explanation: No subarrays possible
   ```

2. **Single Element Array**
   ```
   Input: [6], k=6
   Output: 1
   Explanation: Single element equals k
   ```

TEST CASES:
1. Input: [3,1,2,4], k=6
   Expected: 1
   Output: 1
2. Input: [1,1,1], k=2
   Expected: 2
   Output: 2
3. Input: [1,2,3], k=3
   Expected: 2
   Output: 2

*/