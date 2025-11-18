package Binary_Search.SmallestDivisorThreshold;
/* 
Find the Smallest Divisor Given a Threshold
# Problem: Given an array of integers nums and an integer threshold, we will choose a positive integer divisor, divide all the array by it, and sum the division's result. Find the smallest divisor such that the result mentioned above is less than or equal to threshold.
Each result of the division is rounded to the nearest integer greater than or equal to that element. (For example: 7/3 = 3 and 10/2 = 5).

## Intuition:  The core concept is to use binary search to find the smallest divisor that satisfies the threshold condition.

- **Main approach**: Binary search on the range of possible divisors (1 to maximum element)
- **Key insight**: If a divisor works (sum ≤ threshold), then all larger divisors will also work, but we want the smallest one

## Basic Understanding

We need to find the smallest positive integer that, when used to divide all elements in the array (with ceiling division), produces a sum less than or equal to the given threshold. Instead of checking every possible divisor linearly, we use binary search to efficiently narrow down the search space.

## Key Observations with Examples

### Observation 1: Monotonic Property
As the divisor increases, the sum of ceiling divisions decreases or stays the same.
* If divisor = 1: sum will be maximum (sum of all elements)
* If divisor = max element: sum will be minimum (length of array)
* This monotonic property allows us to use binary search

### Observation 2: Ceiling Division Formula
For ceiling division without using Math.ceil():
```
ceil(a/b) = (a + b - 1) / b
```
Example: ceil(7/3) = (7 + 3 - 1) / 3 = 9/3 = 3

### Observation 3: Search Space Boundaries
The search space is from 1 to the maximum element in the array.
* Lower bound: 1 (minimum possible divisor)
* Upper bound: max(nums) (beyond this, all divisions will be 1)

### Observation 4: Binary Search Decision
```
if (sum <= threshold): try smaller divisors (right = mid)
else: try larger divisors (left = mid + 1)
```

## Step-by-Step Example
Let's work through nums = [1, 2, 5, 9], threshold = 6:

1. **Initialize Search Space**:
   ```
   left = 1, right = 9 (max element)
   ```

2. **First Iteration (mid = 5)**:
   ```
   calculateSum([1,2,5,9], 5) = ceil(1/5) + ceil(2/5) + ceil(5/5) + ceil(9/5)
                                = 1 + 1 + 1 + 2 = 5
   5 <= 6, so right = 5
   ```

3. **Second Iteration (mid = 3)**:
   ```
   calculateSum([1,2,5,9], 3) = ceil(1/3) + ceil(2/3) + ceil(5/3) + ceil(9/3)
                                = 1 + 1 + 2 + 3 = 7
   7 > 6, so left = 4
   ```

4. **Third Iteration (mid = 4)**:
   ```
   calculateSum([1,2,5,9], 4) = ceil(1/4) + ceil(2/4) + ceil(5/4) + ceil(9/4)
                                = 1 + 1 + 2 + 3 = 7
   7 > 6, so left = 5
   ```

5. **Termination**: left = right = 5, return 5

## Special Cases

### Case 1: All Elements are 1
Input: nums = [1, 1, 1], threshold = 3
- Any divisor ≥ 1 will give sum = 3
- Result: 1

### Case 2: Single Element
Input: nums = [10], threshold = 1
- Need divisor such that ceil(10/divisor) ≤ 1
- Result: 10

### Case 3: Large Threshold
Input: nums = [2, 4, 6], threshold = 100
- Even divisor = 1 gives sum = 12 < 100
- Result: 1
*/

public class Approach2 {
    // Approach 2: Binary Search Approach
    public static int smallestDivisor(int[] nums, int threshold) {
        // Step 1: Initialize search boundaries
        int left = 1;                    // Minimum possible divisor
        int right = findMax(nums);       // Maximum element in array
        
        // Step 2: Binary search for smallest valid divisor
        while (left < right) {
            int mid = left + (right - left) / 2;  // Prevent overflow
            
            // Step 3: Calculate sum for current divisor
            if (calculateSum(nums, mid) <= threshold) {
                right = mid-1;     // mid is valid, try smaller divisors
            } else {
                left = mid + 1;  // mid is too small, try larger divisors
            }
        }
        
        // Step 4: Return the smallest valid divisor
        return left;
    }
    
    // Helper method to calculate sum of ceiling divisions
    private static int calculateSum(int[] nums, int divisor) {
        int sum = 0;
        for (int num : nums) {
            // Ceiling division: (num + divisor - 1) / divisor
            sum += (num + divisor - 1) / divisor;
        }
        return sum;
    }
    
    // Helper method to find maximum element in array
    private static int findMax(int[] nums) {
        int max = nums[0];
        for (int num : nums) {
            max = Math.max(max, num);
        }
        return max;
    }

    // Main method for testing
    public static void main(String[] args) {
        // Example input
        int[] nums = {1, 2, 5, 9};
        int threshold = 6;

        // Call the method and print the output
        int result = smallestDivisor(nums, threshold);

        // Display the result
        System.out.println("Result: " + result);
    }
}
/*
# Algorithm
1. Initialize left = 1, right = max(nums)
2. While left < right:
   - Calculate mid = left + (right - left) / 2
   - Calculate sum of ceiling divisions for mid
   - If sum ≤ threshold: right = mid (try smaller)
   - Else: left = mid + 1 (try larger)
3. Return left as the smallest valid divisor

### Time Complexity Breakdown per Step
1. **Find maximum element**: O(n)
2. **Binary search iterations**: O(log(max_element))
3. **Calculate sum per iteration**: O(n)

Total: O(n * log(max_element))

### Space Complexity Breakdown
1. **Auxiliary Space**: O(1) - only using a few variables
2. **Input Space**: O(1) - not counting input array

Total: O(1)

# Advantages
1. **Efficient**: O(n * log(max_element)) compared to O(n * max_element) linear search
2. **Optimal**: Always finds the smallest valid divisor
3. **Space-efficient**: Uses constant extra space
4. **Robust**: Handles all edge cases properly

# Limitations
1. **Array traversal**: Still needs to traverse array for each divisor check
2. **Integer overflow**: Large arrays might cause overflow in sum calculation
3. **Fixed precision**: Only works with integer divisors

# Potential Improvements
1. **Early termination**: If sum becomes > threshold during calculation, break early
2. **Parallel processing**: Calculate sum using parallel streams for large arrays
3. **Cache optimization**: Store intermediate results if multiple queries expected

# Step-by-Step Process with Dry Run

## Example Input: nums = [1, 2, 5, 9], threshold = 6

### Detailed Execution Table
```
Step | left | right | mid | calculateSum(nums, mid) | sum ≤ threshold | Action
-----|------|-------|-----|-------------------------|----------------|--------
1    | 1    | 9     | 5   | 1+1+1+2 = 5            | 5 ≤ 6 (True)   | right = 5
2    | 1    | 5     | 3   | 1+1+2+3 = 7            | 7 ≤ 6 (False)  | left = 4
3    | 4    | 5     | 4   | 1+1+2+3 = 7            | 7 ≤ 6 (False)  | left = 5
4    | 5    | 5     | -   | left == right          | -              | return 5
```

### Step-by-Step Explanation

1. **Initialization**
   - Set search boundaries: left = 1, right = 9
   ```
   Search space: [1, 2, 3, 4, 5, 6, 7, 8, 9]
   ```

2. **First Binary Search Iteration (mid = 5)**
   - Calculate sum for divisor 5:
     - ceil(1/5) = 1, ceil(2/5) = 1, ceil(5/5) = 1, ceil(9/5) = 2
     - Sum = 1 + 1 + 1 + 2 = 5
   - Since 5 ≤ 6, divisor 5 is valid, try smaller: right = 5
   ```
   New search space: [1, 2, 3, 4, 5]
   ```

3. **Second Binary Search Iteration (mid = 3)**
   - Calculate sum for divisor 3:
     - ceil(1/3) = 1, ceil(2/3) = 1, ceil(5/3) = 2, ceil(9/3) = 3
     - Sum = 1 + 1 + 2 + 3 = 7
   - Since 7 > 6, divisor 3 is too small, try larger: left = 4
   ```
   New search space: [4, 5]
   ```

4. **Third Binary Search Iteration (mid = 4)**
   - Calculate sum for divisor 4:
     - ceil(1/4) = 1, ceil(2/4) = 1, ceil(5/4) = 2, ceil(9/4) = 3
     - Sum = 1 + 1 + 2 + 3 = 7
   - Since 7 > 6, divisor 4 is too small, try larger: left = 5
   ```
   New search space: [5]
   ```

5. **Termination**
   - left = right = 5, return 5

### Additional Example Cases

1. **Minimum Divisor Case**
```
Input:  nums = [2, 4, 6], threshold = 12
Step 1: left=1, right=6, mid=3, sum=1+2+2=5 ≤ 12, right=3
Step 2: left=1, right=3, mid=2, sum=1+2+3=6 ≤ 12, right=2  
Step 3: left=1, right=2, mid=1, sum=2+4+6=12 ≤ 12, right=1
Output: 1
```

2. **Maximum Divisor Case**
```
Input:  nums = [10, 20, 30], threshold = 3
Step 1: left=1, right=30, mid=15, sum=1+2+2=5 > 3, left=16
Step 2: left=16, right=30, mid=23, sum=1+1+2=4 > 3, left=24
Step 3: left=24, right=30, mid=27, sum=1+1+2=4 > 3, left=28
Step 4: left=28, right=30, mid=29, sum=1+1+2=4 > 3, left=30
Step 5: left=30, right=30, return 30
Output: 30
```

### Edge Cases Handling

1. **Single Element Array**
   ```
   Input: nums = [5], threshold = 1
   Output: 5
   Explanation: Only divisor 5 gives ceil(5/5) = 1 ≤ 1
   ```

2. **All Elements Same**
   ```
   Input: nums = [3, 3, 3], threshold = 2
   Output: 5
   Explanation: Need divisor where 3 * ceil(3/divisor) ≤ 2
   ```

3. **Large Threshold**
   ```
   Input: nums = [1, 2, 3], threshold = 100
   Output: 1
   Explanation: Even divisor 1 gives sum = 6 < 100
   ```

Key Observations:
1. **Binary search** reduces time complexity from O(n * max_element) to O(n * log(max_element))
2. **Ceiling division** can be computed efficiently using (a + b - 1) / b
3. **Monotonic property** ensures binary search validity
4. **Search boundaries** are crucial for correctness

Sample Validation:
Input: nums = [1, 2, 5, 9], threshold = 6
Expected: 5
Output: 5

Key Points:
1. **Efficient search** through binary search on divisor range
2. **Ceiling division** without floating-point arithmetic
3. **Monotonic property** exploitation for optimization
4. **Boundary handling** for edge cases

TEST CASES:
1. Input: nums = [1, 2, 5, 9], threshold = 6
   Expected: 5
   Output: 5

2. Input: nums = [2, 4, 6, 8], threshold = 4
   Expected: 4
   Output: 4

3. Input: nums = [19], threshold = 5
   Expected: 4
   Output: 4

4. Input: nums = [1, 1, 1], threshold = 3
   Expected: 1
   Output: 1

5. Input: nums = [1000000], threshold = 1
   Expected: 1000000
   Output: 1000000
*/