
/* 
Problem: Find the contiguous subarray within an array of integers that has the maximum sum.

# Intuition Behind Kadane's Algorithm
Instead of calculating sum for every subarray, we can use Kadane's algorithm. 
The idea is to keep track of a running sum and reset it to 0 whenever it becomes negative, 
since a negative sum can't contribute to the maximum sum going forward.

## Core Principle
The fundamental insight of Kadane's algorithm is based on this key observation: if a subarray's sum becomes negative, it cannot contribute to maximizing the sum of any future subarray. Why? Because adding a negative number always decreases the sum.

## Detailed Intuition

1. **Local vs Global Maximum**
   - We maintain two variables: a local sum (running sum) and a global maximum
   - The local sum tracks the current subarray we're building
   - The global maximum remembers the best sum we've seen so far

2. **Why Reset When Sum < 0?**
   - If our running sum becomes negative, carrying it forward can only harm future sums
   - Example: If we have [-2, 3]
     - Sum = -2 + 3 = 1
     - But just taking [3] alone gives us 3
     - Therefore, keeping the negative prefix (-2) reduced our potential maximum

3. **Building the Optimal Subarray**
   - We continuously add elements to our running sum
   - When we find a better sum, we update our global maximum
   - When sum becomes negative, we "start fresh" by resetting to 0
   - This effectively means we're starting a new potential subarray from the next position

4. **Why This Works**
   - For any position in the array, the maximum subarray ending at that position must either:
     a. Include the previous elements (if their sum is positive)
     b. Start fresh from the current position (if previous sum is negative)
   - By tracking both local and global maximums, we ensure we don't miss any potential solution

## Visual Example
```
Array: [-2, 1, -3, 4, -1, 2, 1, -5, 4]

Step by step thought process:
[-2] → Sum = -2 (negative, reset to 0)
[1]  → Sum = 1 (positive, keep going)
[-3] → Sum = -2 (negative, reset to 0)
[4]  → Sum = 4 (new best)
[-1] → Sum = 3 (still positive, keep going)
[2]  → Sum = 5 (new best)
[1]  → Sum = 6 (new best)
[-5] → Sum = 1 (still positive, keep going)
[4]  → Sum = 5 (did not beat previous best of 6)
```

## Key Insights

1. **Optimality**
   - The algorithm never misses the optimal solution because:
     - If the maximum sum includes negative numbers, their cumulative sum must still be positive
     - If their cumulative sum becomes negative, excluding them would give a better result

2. **Efficiency**
   - Single pass through the array
   - Constant extra space
   - Makes decisions locally but maintains global information

3. **Edge Cases**
   - Works with all negative numbers (returns the least negative)
   - Works with all positive numbers (returns sum of entire array)
   - Handles empty arrays (with proper implementation)
*/

package Array.Maximum_Subarray;
public class Approach3 {
    // Approach 3: Kadane's Algorithm (Dynamic Programming)
   public static long maxSubarraySum(int[] arr, int n) {
      // Initialize maximum sum to smallest possible value
      long maxi = Long.MIN_VALUE;

      // Initialize current sum
      long sum = 0;

      // Iterate through array once
      for (int i = 0; i < n; i++) {
         // Add current element to running sum
         sum += arr[i];

         // Update maximum sum if current sum is greater
         if (sum > maxi) {
            maxi = sum;
         }

         // Reset sum to 0 if it becomes negative
         // This is key to Kadane's algorithm - we don't carry forward negative sums
         if (sum < 0) {
            sum = 0;
         }

         /* // To consider the sum of the empty subarray
         if (maxi < 0) maxi = 0; */
      }

        return maxi;
    }

    public static void main(String args[]) {
        // Test case
        int[] arr = { -2, 1, -3, 4, -1, 2, 1, -5, 4 };
        int n = arr.length;
        long maxSum = maxSubarraySum(arr, n);
        System.out.println("The maximum subarray sum is: " + maxSum);
    }
}

/* 
ALGORITHM:
1. Initialize variables for maximum sum (maxi) and current sum (sum)
2. Iterate through array once
3. Add current element to running sum
4. Update maximum if current sum is greater
5. Reset sum to 0 if it becomes negative
6. Return maximum sum found

Complexity Analysis:
TIME COMPLEXITY: O(n) 
- Single pass through the array where n is array length

SPACE COMPLEXITY:
1. Auxiliary Space: O(1)
   - Only using two variables regardless of input size
2. Input Space: O(n)
   - Space needed to store input array

Advantages:
1. Linear time complexity with single pass
2. Constant extra space required
3. Simple implementation
4. Handles negative numbers effectively

Limitations:
1. Doesn't handle empty array case
2. May overflow with very large numbers if not using long
3. Doesn't track the subarray indices
4. Doesn't handle all negative numbers case if zero-sum empty subarray is allowed

Potential Improvements:
1. Add index tracking to return start and end indices of maximum subarray
2. Handle empty array case with proper error checking
3. Add option to allow empty subarray (return 0 if all numbers negative)
4. Use BigInteger for handling extremely large numbers
5. Add input validation
6. Implement parallel processing for very large arrays
7. Add overflow detection and handling

Input Array: [-2, 1, -3, 4, -1, 2, 1, -5, 4]

DETAILED DRY RUN WITH VISUALIZATION:
-------------------------------------------------------------------------------------------------
Index | Array Element| Current Sum | Reset Sum? | Maximum So Far | Current Subarray    | Explanation
-------------------------------------------------------------------------------------------------
Init  | -            | 0           | No         | MIN_VALUE      | []                  | Initial state
-------------------------------------------------------------------------------------------------
0     | -2           | -2          | Yes        | -2             | [-2]                | First element, sum negative, reset to 0
-------------------------------------------------------------------------------------------------
1     | 1            | 1           | No         | 1              | [1]                 | New element positive, keep sum
-------------------------------------------------------------------------------------------------
2     | -3           | -2          | Yes        | 1              | [1, -3]             | Sum became negative, reset to 0
-------------------------------------------------------------------------------------------------
3     | 4            | 4           | No         | 4              | [4]                 | New positive sum, update maximum
-------------------------------------------------------------------------------------------------
4     | -1           | 3           | No         | 4              | [4, -1]             | Sum still positive, continue
-------------------------------------------------------------------------------------------------
5     | 2            | 5           | No         | 5              | [4, -1, 2]          | Sum increases, update maximum
-------------------------------------------------------------------------------------------------
6     | 1            | 6           | No         | 6              | [4, -1, 2, 1]       | Sum increases, update maximum
-------------------------------------------------------------------------------------------------
7     | -5           | 1           | No         | 6              | [4, -1, 2, 1, -5]   | Sum decreases but still positive
-------------------------------------------------------------------------------------------------
8     | 4            | 5           | No         | 6              | [4, -1, 2, 1, -5, 4]| Final element, max remains 6
-------------------------------------------------------------------------------------------------

I'll break down each step of the table from the dry run in detailed points:
Initial Setup:
1. Array: [-2, 1, -3, 4, -1, 2, 1, -5, 4]
2. Initialize: maxi = MIN_VALUE, sum = 0

Step-by-Step Process:

1. Index 0 (Element: -2)
   - Current Action: Add -2 to sum
   - Sum becomes: 0 + (-2) = -2
   - Maximum updated: -2 (first value)
   - Reset Check: sum < 0, so reset sum to 0
   - Current State: sum = 0, maxi = -2

2. Index 1 (Element: 1)
   - Current Action: Add 1 to sum
   - Sum becomes: 0 + 1 = 1
   - Maximum updated: 1 (better than -2)
   - Reset Check: sum > 0, no reset needed
   - Current State: sum = 1, maxi = 1

3. Index 2 (Element: -3)
   - Current Action: Add -3 to sum
   - Sum becomes: 1 + (-3) = -2
   - Maximum stays: 1 (better than -2)
   - Reset Check: sum < 0, so reset sum to 0
   - Current State: sum = 0, maxi = 1

4. Index 3 (Element: 4)
   - Current Action: Add 4 to sum
   - Sum becomes: 0 + 4 = 4
   - Maximum updated: 4 (better than 1)
   - Reset Check: sum > 0, no reset needed
   - Current State: sum = 4, maxi = 4

5. Index 4 (Element: -1)
   - Current Action: Add -1 to sum
   - Sum becomes: 4 + (-1) = 3
   - Maximum stays: 4 (better than 3)
   - Reset Check: sum > 0, no reset needed
   - Current State: sum = 3, maxi = 4

6. Index 5 (Element: 2)
   - Current Action: Add 2 to sum
   - Sum becomes: 3 + 2 = 5
   - Maximum updated: 5 (better than 4)
   - Reset Check: sum > 0, no reset needed
   - Current State: sum = 5, maxi = 5

7. Index 6 (Element: 1)
   - Current Action: Add 1 to sum
   - Sum becomes: 5 + 1 = 6
   - Maximum updated: 6 (better than 5)
   - Reset Check: sum > 0, no reset needed
   - Current State: sum = 6, maxi = 6

8. Index 7 (Element: -5)
   - Current Action: Add -5 to sum
   - Sum becomes: 6 + (-5) = 1
   - Maximum stays: 6 (better than 1)
   - Reset Check: sum > 0, no reset needed
   - Current State: sum = 1, maxi = 6

9. Index 8 (Element: 4)
   - Current Action: Add 4 to sum
   - Sum becomes: 1 + 4 = 5
   - Maximum stays: 6 (better than 5)
   - Reset Check: sum > 0, no reset needed
   - Final State: sum = 5, maxi = 6

Key Outcomes:
1. Maximum Sum Found: 6
2. Subarray that gives maximum sum: [4, -1, 2, 1]
3. Important Decision Points:
   - Reset happened after -2 and -3
   - Maximum value updated 5 times
   - No resets needed after index 3

EXPLANATION OF KEY MOMENTS:

1. Starting State:
   - Initialize maxi = MIN_VALUE
   - Initialize sum = 0

2. First Element (-2):
   - sum = 0 + (-2) = -2
   - maxi = max(-2, MIN_VALUE) = -2
   - sum < 0, so reset sum = 0
   
3. Second Element (1):
   - sum = 0 + 1 = 1
   - maxi = max(1, -2) = 1
   - sum > 0, continue

4. Third Element (-3):
   - sum = 1 + (-3) = -2
   - maxi remains 1 (no update)
   - sum < 0, so reset sum = 0

5. Fourth Element (4):
   - sum = 0 + 4 = 4
   - maxi = max(4, 1) = 4
   - sum > 0, continue

6. Fifth through Eighth Elements:
   - The sum builds up to 6 through [4, -1, 2, 1]
   - This becomes our maximum subarray
   - Later elements don't produce a larger sum

FINAL RESULT ANALYSIS:
----------------------
Maximum Sum: 6
Maximum Subarray: [4, -1, 2, 1]
Start Index: 3
End Index: 6

ADDITIONAL TEST CASES WITH DRY RUN:
----------------------------------
1. All Positive: [1, 2, 3, 4]
   Result: 10 (entire array)

2. All Negative: [-1, -2, -3, -4]
   Result: -1 (single element)

3. Mixed with Zero: [0, -1, 2, 3, -2, 0]
   Result: 5 ([2, 3])

4. Single Element: [5]
   Result: 5

5. Zero Elements: []
   Result: Implementation dependent

KEY OBSERVATIONS FROM DRY RUN:
-----------------------------
1. The algorithm effectively resets on negative sums
2. It maintains the maximum seen so far even after resets
3. The current sum can decrease but still be positive
4. The maximum is updated before any reset occurs
5. The algorithm handles both local and global maximums effectively

This dry run demonstrates how Kadane's algorithm efficiently finds the maximum subarray sum in a single pass through the array.

Key Observations:
1. Algorithm resets sum when it becomes negative
2. Maximum sum is updated before the reset
3. Works with both positive and negative numbers
4. Handles local maximum vs global maximum effectively

Sample Validation:
Input: [-2, 1, -3, 4, -1, 2, 1, -5, 4]
Expected: 6 (subarray [4, -1, 2, 1])
Output: 6

Key Points:
1. Based on Kadane's algorithm principles
2. Linear time solution to maximum subarray problem
3. Optimal for single-pass requirement
4. Maintains constant space complexity

TEST CASES:
1. Input: [-2, 1, -3, 4, -1, 2, 1, -5, 4]
   Expected: 6
   Output: 6
2. Input: [-1]
   Expected: -1
   Output: -1
3. Input: [-1, -2, -3, -4]
   Expected: -1
   Output: -1
4. Input: [1, 2, 3, 4]
   Expected: 10
   Output: 10
*/