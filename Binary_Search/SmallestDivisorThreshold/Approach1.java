package Binary_Search.SmallestDivisorThreshold;
/* 
Find the Smallest Divisor Given a Threshold
# Problem: Given an array of integers nums and an integer threshold, we will choose a positive integer divisor, divide all the array by it, and sum the division's result. Find the smallest divisor such that the result mentioned above is less than or equal to threshold.
Each result of the division is rounded to the nearest integer greater than or equal to that element. (For example: 7/3 = 3 and 10/2 = 5).

## Intuition: The core concept is to find the minimum divisor that keeps the sum of ceiling divisions within the threshold limit.

- **Main approach**: Linear search through all possible divisors starting from 1
- **Key insight**: As divisor increases, the sum of divisions decreases
- **Strategy**: Check each divisor sequentially until we find one that satisfies the threshold condition

## Basic Understanding

We need to find the smallest positive integer divisor such that when we divide each element in the array by this divisor (using ceiling division) and sum all results, the total sum is ≤ threshold.

## Key Observations with Examples

### Observation 1: Ceiling Division Pattern
Each division result is rounded up to the nearest integer (ceiling division).
* For 7/3: result is 3 (not 2.33...)
* For 10/2: result is 5 (exactly)
* For 5/4: result is 2 (not 1.25)

### Observation 2: Inverse Relationship
As the divisor increases, the sum of ceiling divisions decreases.
Example: Array [1, 2, 5, 9]
- Divisor 1: (1/1) + (2/1) + (5/1) + (9/1) = 1 + 2 + 5 + 9 = 17
- Divisor 2: (1/2) + (2/2) + (5/2) + (9/2) = 1 + 1 + 3 + 5 = 10
- Divisor 3: (1/3) + (2/3) + (5/3) + (9/3) = 1 + 1 + 2 + 3 = 7

### Observation 3: Ceiling Division Implementation
Using the formula: `(num + divisor - 1) / divisor` gives ceiling division without floating point operations.
```
For num = 7, divisor = 3:
(7 + 3 - 1) / 3 = 9 / 3 = 3 ✓
```

### Observation 4: Search Space Boundary
The maximum possible divisor needed is the maximum element in the array, because any larger divisor would make all divisions equal to 1.

## Step-by-Step Example
Let's work through nums = [1, 2, 5, 9], threshold = 6:

1. **Initialize divisor = 1**:
   ```
   calculateSum([1, 2, 5, 9], 1):
   - 1/1 = 1
   - 2/1 = 2  
   - 5/1 = 5
   - 9/1 = 9
   Sum = 1 + 2 + 5 + 9 = 17
   17 > 6, so continue
   ```

2. **Try divisor = 2**:
   ```
   calculateSum([1, 2, 5, 9], 2):
   - (1+2-1)/2 = 2/2 = 1
   - (2+2-1)/2 = 3/2 = 1
   - (5+2-1)/2 = 6/2 = 3
   - (9+2-1)/2 = 10/2 = 5
   Sum = 1 + 1 + 3 + 5 = 10
   10 > 6, so continue
   ```

3. **Try divisor = 3**:
   ```
   calculateSum([1, 2, 5, 9], 3):
   - (1+3-1)/3 = 3/3 = 1
   - (2+3-1)/3 = 4/3 = 1
   - (5+3-1)/3 = 7/3 = 2
   - (9+3-1)/3 = 11/3 = 3
   Sum = 1 + 1 + 2 + 3 = 7
   7 > 6, so continue
   ```

4. **Try divisor = 5**:
   ```
   calculateSum([1, 2, 5, 9], 5):
   - (1+5-1)/5 = 5/5 = 1
   - (2+5-1)/5 = 6/5 = 1
   - (5+5-1)/5 = 9/5 = 1
   - (9+5-1)/5 = 13/5 = 2
   Sum = 1 + 1 + 1 + 2 = 5
   5 ≤ 6, so return 5
   ```

## Special Cases

### Case 1: All Elements Are 1
Input: nums = [1, 1, 1], threshold = 3
- Behavior: Any divisor ≥ 1 will result in sum = 3
- Result: 1 (smallest possible divisor)

### Case 2: Single Element
Input: nums = [10], threshold = 1
- Behavior: Need ceiling(10/divisor) ≤ 1, so divisor ≥ 10
- Result: 10

### Case 3: Threshold Equals Array Length
Input: nums = [2, 4, 6], threshold = 3
- Behavior: Need each element to become 1 after division
- Result: 6 (largest element)
*/
public class Approach1 {
    
    // Approach 1: Linear Search Approach
    public static int smallestDivisor(int[] nums, int threshold) {
        // Start checking from divisor = 1
        int divisor = 1;
        
        // Continue searching until we find a divisor that satisfies the condition
        while (true) {
            // Calculate sum of ceiling divisions for current divisor
            int sum = calculateSum(nums, divisor);
            
            // If sum is within threshold, we found our answer
            if (sum <= threshold) {
                return divisor;
            }
            
            // Otherwise, try next divisor
            divisor++;
        }
    }
    
    // Helper method to calculate sum of ceiling divisions
    private static int calculateSum(int[] nums, int divisor) {
        int sum = 0;
        
        // For each number in the array
        for (int num : nums) {
            // Add ceiling division result to sum
            // Formula: (num + divisor - 1) / divisor gives ceiling division
            sum += (num + divisor - 1) / divisor;
        }
        
        return sum;
    }

    // Main method for testing
    public static void main(String[] args) {
        // Example input
        int[] nums = {1, 2, 5, 9};
        int threshold = 6;

        // Call the method and print the output
        int result = smallestDivisor(nums, threshold);

        // Display the result
        System.out.println("Smallest Divisor: " + result);
    }
}

/*
# Algorithm
1. Initialize divisor = 1
2. Calculate sum of ceiling divisions for current divisor
3. If sum ≤ threshold, return current divisor
4. Otherwise, increment divisor and repeat from step 2

### Time Complexity Breakdown per Step
1. **Outer loop**: O(max(nums)) - worst case we check up to maximum element
2. **calculateSum method**: O(n) - iterate through all elements
3. **Ceiling division**: O(1) - constant time operation

Total: **O(n × max(nums))**

### Space Complexity Breakdown
1. **Auxiliary Space**: O(1) - only using a few variables
2. **Input Space**: O(n) - input array storage

Total: **O(1)** auxiliary space

# Advantages
1. **Simple and intuitive**: Easy to understand and implement
2. **Guaranteed to find answer**: Will always find the smallest divisor
3. **No complex logic**: Straightforward linear search approach
4. **Easy to debug**: Can trace through each step easily

# Limitations
1. **Inefficient for large ranges**: Could be slow if maximum element is very large
2. **No early termination optimization**: Doesn't use the monotonic property
3. **Sequential search**: Doesn't leverage binary search potential
4. **Higher time complexity**: O(n × max(nums)) vs potential O(n × log(max(nums)))

# Potential Improvements
1. **Binary Search**: Use binary search on the divisor range [1, max(nums)]
2. **Early termination**: Stop when sum becomes much smaller than threshold
3. **Preprocessing**: Sort array to potentially optimize calculateSum
4. **Memoization**: Cache results for repeated divisor calculations

# Step-by-Step Process with Dry Run

## Example Input: nums = [1, 2, 5, 9], threshold = 6

### Detailed Execution Table
```
Step | Divisor | Calculations | Individual Results | Sum | Sum ≤ 6? | Action
-----|---------|--------------|-------------------|-----|-----------|--------
1    | 1       | 1/1,2/1,5/1,9/1 | 1,2,5,9        | 17  | No        | Continue
2    | 2       | 1/2,2/2,5/2,9/2 | 1,1,3,5        | 10  | No        | Continue  
3    | 3       | 1/3,2/3,5/3,9/3 | 1,1,2,3        | 7   | No        | Continue
4    | 4       | 1/4,2/4,5/4,9/4 | 1,1,2,3        | 7   | No        | Continue
5    | 5       | 1/5,2/5,5/5,9/5 | 1,1,1,2        | 5   | Yes       | Return 5
```

### Step-by-Step Explanation

1. **Initialize divisor = 1**
   - Calculate sum for divisor 1
   ```
   nums = [1, 2, 5, 9], divisor = 1
   calculateSum: 1/1 + 2/1 + 5/1 + 9/1 = 1 + 2 + 5 + 9 = 17
   17 > 6, so continue
   ```

2. **Try divisor = 2**
   - Calculate sum for divisor 2
   ```
   nums = [1, 2, 5, 9], divisor = 2
   calculateSum: ⌈1/2⌉ + ⌈2/2⌉ + ⌈5/2⌉ + ⌈9/2⌉ = 1 + 1 + 3 + 5 = 10
   10 > 6, so continue
   ```

3. **Try divisor = 3**
   - Calculate sum for divisor 3
   ```
   nums = [1, 2, 5, 9], divisor = 3
   calculateSum: ⌈1/3⌉ + ⌈2/3⌉ + ⌈5/3⌉ + ⌈9/3⌉ = 1 + 1 + 2 + 3 = 7
   7 > 6, so continue
   ```

4. **Try divisor = 4**
   - Calculate sum for divisor 4
   ```
   nums = [1, 2, 5, 9], divisor = 4
   calculateSum: ⌈1/4⌉ + ⌈2/4⌉ + ⌈5/4⌉ + ⌈9/4⌉ = 1 + 1 + 2 + 3 = 7
   7 > 6, so continue
   ```

5. **Try divisor = 5**
   - Calculate sum for divisor 5
   ```
   nums = [1, 2, 5, 9], divisor = 5
   calculateSum: ⌈1/5⌉ + ⌈2/5⌉ + ⌈5/5⌉ + ⌈9/5⌉ = 1 + 1 + 1 + 2 = 5
   5 ≤ 6, so return 5
   ```

### Additional Example Cases

1. **Small Array Case**
```
Input:  nums = [2, 4], threshold = 3
Step 1: divisor = 1 → sum = 2 + 4 = 6 > 3
Step 2: divisor = 2 → sum = 1 + 2 = 3 ≤ 3
Output: 2
```

2. **Single Element Case**
```
Input:  nums = [10], threshold = 1
Step 1: divisor = 1 → sum = 10 > 1
...
Step 10: divisor = 10 → sum = 1 ≤ 1
Output: 10
```

### Edge Cases Handling

1. **All Elements Equal to 1**
   ```
   Input: nums = [1, 1, 1], threshold = 3
   Output: 1
   Explanation: Any divisor ≥ 1 gives sum = 3, so smallest is 1
   ```

2. **Threshold Equals Array Length**
   ```
   Input: nums = [5, 10, 15], threshold = 3
   Output: 15
   Explanation: Need each element to become 1, so divisor = max(nums)
   ```

3. **Very Large Threshold**
   ```
   Input: nums = [1, 2, 3], threshold = 100
   Output: 1
   Explanation: Even divisor = 1 gives sum = 6 ≤ 100
   ```

Key Observations:
1. **Monotonic Property**: As divisor increases, sum decreases
2. **Ceiling Division**: Always rounds up, never down
3. **Search Space**: Bounded by [1, max(nums)]
4. **Linear Search**: Checks each divisor sequentially

Sample Validation:
Input: nums = [1, 2, 5, 9], threshold = 6
Expected: 5
Output: 5

Key Points:
1. **Linear approach**: Simple but potentially slow for large ranges
2. **Ceiling division formula**: (num + divisor - 1) / divisor
3. **Sequential search**: Guaranteed to find the smallest valid divisor
4. **No optimization**: Doesn't use binary search potential

TEST CASES:
1. Input: nums = [1, 2, 5, 9], threshold = 6
   Expected: 5
   Output: 5

2. Input: nums = [2, 4, 6], threshold = 3
   Expected: 6
   Output: 6

3. Input: nums = [1, 1, 1], threshold = 3
   Expected: 1
   Output: 1

4. Input: nums = [10], threshold = 1
   Expected: 10
   Output: 10

5. Input: nums = [5, 10, 15], threshold = 10
   Expected: 5
   Output: 5
*/
