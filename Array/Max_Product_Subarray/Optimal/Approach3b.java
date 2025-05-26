package Array.Max_Product_Subarray.Optimal;
/* 
# Problem: Maximum Product Subarray
Given an integer array nums, find a subarray that has the largest product, and return the product.

## Intuition
When finding the maximum product subarray, we need to track both maximum and minimum products at each position. The minimum product is important because multiplying a negative number with another negative number can result in a positive product, potentially giving us the maximum product.

- Main approach: Track both maximum and minimum products at each step
- Key insight: Negative numbers can flip the maximum and minimum products

## Basic Understanding
We need to find a contiguous subarray that produces the largest product value. Unlike the maximum sum subarray problem, we need to track minimum products as well because two negative numbers multiply to give a positive number.

## Key Observations with Examples

### Observation 1: Role of Current Element
The current element can either:
* Start a new subarray (just take the element itself)
* Continue the existing product chain
* Example: In [1,2,3,4], at position 2, we either take just 3 or multiply it with previous product 1×2=2

### Observation 2: Importance of Minimum Product
* Multiplying a negative number with a minimum product (negative) can yield a maximum product
* Example: In [1,-2,-3,4], when we reach -3, multiplying it with minimum product -2 gives 6, which is better than starting fresh with -3

### Observation 3: Handling Zero
* Zero acts as a reset point as any product including zero becomes zero
* We implicitly handle this by comparing with the current element itself at each step
* Example: In [2,3,0,4,5], when we reach 0, both products become 0, and we effectively restart

```
Example array: [2,-3,-2,4]
At index 0: prod1 = 2, prod2 = 2, result = 2
At index 1: prod1 = -3, prod2 = -6, result = 2
At index 2: prod1 = 12, prod2 = -6, result = 12
At index 3: prod1 = 48, prod2 = -24, result = 48
```

## Step-by-Step Example
Let's work through the array [1,2,-3,0,-4,-5]:

1. Step One:
   ```
   Initialize: prod1 = 1, prod2 = 1, result = 1
   ```
   We start with the first element as our initial values.

2. Step Two:
   ```
   i = 1, arr[i] = 2
   temp = max(2, max(1*2, 1*2)) = max(2, 2) = 2
   prod2 = min(2, min(1*2, 1*2)) = min(2, 2) = 2
   prod1 = 2
   result = max(1, 2) = 2
   ```
   We calculate the new maximum and minimum products.

3. Step Three:
   ```
   i = 2, arr[i] = -3
   temp = max(-3, max(2*-3, 2*-3)) = max(-3, -6) = -3
   prod2 = min(-3, min(2*-3, 2*-3)) = min(-3, -6) = -6
   prod1 = -3
   result = max(2, -3) = 2
   ```
   The maximum product remains 2.

4. Step Four:
   ```
   i = 3, arr[i] = 0
   temp = max(0, max(-3*0, -6*0)) = max(0, 0) = 0
   prod2 = min(0, min(-3*0, -6*0)) = min(0, 0) = 0
   prod1 = 0
   result = max(2, 0) = 2
   ```
   Zero resets both products to 0.

5. Step Five:
   ```
   i = 4, arr[i] = -4
   temp = max(-4, max(0*-4, 0*-4)) = max(-4, 0) = -4
   prod2 = min(-4, min(0*-4, 0*-4)) = min(-4, 0) = -4
   prod1 = -4
   result = max(2, -4) = 2
   ```
   We effectively start a new subarray with -4.

6. Step Six:
   ```
   i = 5, arr[i] = -5
   temp = max(-5, max(-4*-5, -4*-5)) = max(-5, 20) = 20
   prod2 = min(-5, min(-4*-5, -4*-5)) = min(-5, 20) = -5
   prod1 = 20
   result = max(2, 20) = 20
   ```
   The negative product from previous step gives us a maximum of 20.

## Special Cases

### Case 1: All Positive Numbers
Input: [1,2,3,4]
- Behavior: Each element increases the maximum product
- Result: 24 (the product of all elements)

### Case 2: Even Number of Negative Elements
Input: [2,-3,-2,4]
- Behavior: Two negatives make a positive, creating a high product
- Result: 48 (all elements included)

### Case 3: Odd Number of Negative Elements
Input: [2,-3,-2,-4]
- Behavior: We need to exclude one negative to maximize product
- Result: 24 (excluding -3)

### Case 4: Array Contains Zero
Input: [2,3,0,4,5]
- Behavior: Zero acts as a reset point
- Result: 20 (from the subarray [4,5])
*/

public class Approach3b {
    // Approach 3b: Using Kadane's algorithm 
    public static int maxProductSubArray(int[] arr) {
        // Initialize all three variables with the first element
        int prod1 = arr[0]; // Tracks maximum product ending at current position
        int prod2 = arr[0]; // Tracks minimum product ending at current position
        int result = arr[0]; // Tracks overall maximum product found so far

        for (int i = 1; i < arr.length; i++) {
            // Calculate the maximum of:
            // 1. Current element alone
            // 2. Current element * maximum product so far
            // 3. Current element * minimum product so far (important for negative numbers)
            int temp = Math.max(arr[i], Math.max(prod1 * arr[i], prod2 * arr[i]));

            // Update minimum product in the same way
            prod2 = Math.min(arr[i], Math.min(prod1 * arr[i], prod2 * arr[i]));

            // Update maximum product
            prod1 = temp;

            // Update the global maximum if needed
            result = Math.max(result, prod1);
        }

        return result;
    }

    // Main method for testing
    public static void main(String[] args) {
        // Example input
        int nums[] = { 1, 2, -3, 0, -4, -5 };

        // Call the method and print the output
        int answer = maxProductSubArray(nums);

        // Display the result
        System.out.println("The maximum product subarray is: " + answer);
    }
}
    
/*
# Algorithm
1. Initialize three variables: prod1 (max product), prod2 (min product), and result (global max) with the first element
2. Iterate through the array starting from the second element
3. For each element, calculate:
   - New maximum product = max(current element, current element * previous max, current element * previous min)
   - New minimum product = min(current element, current element * previous max, current element * previous min)
4. Update the global maximum product
5. Return the result

### Time Complexity Breakdown per Step
1. Initialization: O(1)
2. Array iteration: O(n)
3. Calculations at each step: O(1)

Total: O(n) where n is the length of the array

### Space Complexity Breakdown
1. Auxiliary Space: O(1) - We only use three variables regardless of input size
   - prod1, prod2, result, and temp are the only additional variables
2. Input Space: O(n) - The input array of size n

Total: O(1) auxiliary space complexity

# Advantages
1. Optimal time complexity of O(n)
2. Constant space complexity O(1)
3. Single pass through the array
4. Handles both positive and negative numbers effectively
5. Implicitly handles zeros which act as reset points

# Limitations
1. Does not track the actual subarray indices, only the maximum product
2. Might overflow with very large numbers (though this is a limitation of the data type, not the algorithm)
3. For an empty array, would need additional edge case handling

# Potential Improvements
1. Add tracking of the start and end indices of the maximum product subarray
2. Handle potential integer overflow by checking for results getting too large
3. Add explicit edge case handling for empty arrays

# Detailed Step-by-Step Dry Run: Maximum Product Subarray

## Example Input: [1, 2, -3, 0, -4, -5]

### Execution Table with Step-by-Step Tracking

Let's trace through the algorithm's execution for the input array [1, 2, -3, 0, -4, -5]:

| Step | Current Element | Calculation for prod1 | New prod1 | Calculation for prod2 | New prod2 | Update result | Current result |
|------|----------------|----------------------|-----------|----------------------|-----------|--------------|---------------|
| Init | 1              | Initialize           | 1         | Initialize           | 1         | Initialize    | 1             |
| 1    | 2              | max(2, 1×2, 1×2) = 2 | 2         | min(2, 1×2, 1×2) = 2 | 2         | max(1, 2) = 2 | 2             |
| 2    | -3             | max(-3, 2×-3, 2×-3) = -3 | -3    | min(-3, 2×-3, 2×-3) = -6 | -6    | max(2, -3) = 2 | 2             |
| 3    | 0              | max(0, -3×0, -6×0) = 0 | 0       | min(0, -3×0, -6×0) = 0 | 0       | max(2, 0) = 2 | 2             |
| 4    | -4             | max(-4, 0×-4, 0×-4) = -4 | -4    | min(-4, 0×-4, 0×-4) = -4 | -4    | max(2, -4) = 2 | 2             |
| 5    | -5             | max(-5, -4×-5, -4×-5) = 20 | 20  | min(-5, -4×-5, -4×-5) = -5 | -5  | max(2, 20) = 20 | 20            |
| Final Result: 20 |

## Detailed Step-by-Step Explanation

### Step 0: Initialization
- We start by setting all three variables (prod1, prod2, result) to the first element: 1
- **prod1** = 1 (maximum product ending at current position)
- **prod2** = 1 (minimum product ending at current position)
- **result** = 1 (overall maximum product found so far)

### Step 1: Process Element at Index 1 (value = 2)
1. Calculate the new maximum product (temp):
   - Current element alone: 2
   - Current element × maximum product so far: 1 × 2 = 2
   - Current element × minimum product so far: 1 × 2 = 2
   - Take the maximum: max(2, 2, 2) = 2
   - temp = 2

2. Calculate the new minimum product (prod2):
   - Current element alone: 2
   - Current element × maximum product so far: 1 × 2 = 2
   - Current element × minimum product so far: 1 × 2 = 2
   - Take the minimum: min(2, 2, 2) = 2
   - prod2 = 2

3. Update maximum product: prod1 = temp = 2

4. Update the global maximum:
   - result = max(1, 2) = 2

### Step 2: Process Element at Index 2 (value = -3)
1. Calculate the new maximum product (temp):
   - Current element alone: -3
   - Current element × maximum product so far: 2 × -3 = -6
   - Current element × minimum product so far: 2 × -3 = -6
   - Take the maximum: max(-3, -6, -6) = -3
   - temp = -3

2. Calculate the new minimum product (prod2):
   - Current element alone: -3
   - Current element × maximum product so far: 2 × -3 = -6
   - Current element × minimum product so far: 2 × -3 = -6
   - Take the minimum: min(-3, -6, -6) = -6
   - prod2 = -6

3. Update maximum product: prod1 = temp = -3

4. Update the global maximum:
   - result = max(2, -3) = 2

### Step 3: Process Element at Index 3 (value = 0)
1. Calculate the new maximum product (temp):
   - Current element alone: 0
   - Current element × maximum product so far: -3 × 0 = 0
   - Current element × minimum product so far: -6 × 0 = 0
   - Take the maximum: max(0, 0, 0) = 0
   - temp = 0

2. Calculate the new minimum product (prod2):
   - Current element alone: 0
   - Current element × maximum product so far: -3 × 0 = 0
   - Current element × minimum product so far: -6 × 0 = 0
   - Take the minimum: min(0, 0, 0) = 0
   - prod2 = 0

3. Update maximum product: prod1 = temp = 0

4. Update the global maximum:
   - result = max(2, 0) = 2

### Step 4: Process Element at Index 4 (value = -4)
1. Calculate the new maximum product (temp):
   - Current element alone: -4
   - Current element × maximum product so far: 0 × -4 = 0
   - Current element × minimum product so far: 0 × -4 = 0
   - Take the maximum: max(-4, 0, 0) = 0
   - temp = 0

2. Calculate the new minimum product (prod2):
   - Current element alone: -4
   - Current element × maximum product so far: 0 × -4 = 0
   - Current element × minimum product so far: 0 × -4 = 0
   - Take the minimum: min(-4, 0, 0) = -4
   - prod2 = -4

3. Update maximum product: prod1 = temp = 0

4. Update the global maximum:
   - result = max(2, 0) = 2

### Step 5: Process Element at Index 5 (value = -5)
1. Calculate the new maximum product (temp):
   - Current element alone: -5
   - Current element × maximum product so far: 0 × -5 = 0
   - Current element × minimum product so far: -4 × -5 = 20
   - Take the maximum: max(-5, 0, 20) = 20
   - temp = 20

2. Calculate the new minimum product (prod2):
   - Current element alone: -5
   - Current element × maximum product so far: 0 × -5 = 0
   - Current element × minimum product so far: -4 × -5 = 20
   - Take the minimum: min(-5, 0, 20) = -5
   - prod2 = -5

3. Update maximum product: prod1 = temp = 20

4. Update the global maximum:
   - result = max(2, 20) = 20

### Final Result: 20

## Key Points of the Algorithm

1. **Tracking Both Maximum and Minimum Products**:
   - We need to track both maximum and minimum products because multiplying by a negative number can flip them
   - When we encounter a negative number, the minimum product can become the maximum product

2. **Three Options at Each Step**:
   - Start a new subarray with just the current element
   - Extend the maximum product subarray by multiplying with the current element
   - Extend the minimum product subarray by multiplying with the current element (important for negative numbers)

3. **The Role of Zero**:
   - Zero elements effectively reset both products to zero
   - This is implicitly handled in our algorithm by considering the current element alone

4. **Final Result**:
   - The maximum product is 20, which comes from the subarray [-4, -5]
   - This demonstrates how two negative numbers multiply to give a positive result

## Additional Test Cases with Step-by-Step Execution

### Test Case 1: [2, 3, -2, 4]

| Step | Current Element | Calculation for prod1 | New prod1 | Calculation for prod2 | New prod2 | Update result | Current result |
|------|----------------|----------------------|-----------|----------------------|-----------|--------------|---------------|
| Init | 2              | Initialize           | 2         | Initialize           | 2         | Initialize    | 2             |
| 1    | 3              | max(3, 2×3, 2×3) = 6 | 6         | min(3, 2×3, 2×3) = 3 | 3         | max(2, 6) = 6 | 6             |
| 2    | -2             | max(-2, 6×-2, 3×-2) = -2 | -2    | min(-2, 6×-2, 3×-2) = -12 | -12    | max(6, -2) = 6 | 6             |
| 3    | 4              | max(4, -2×4, -12×4) = 4 | 4       | min(4, -2×4, -12×4) = -48 | -48       | max(6, 4) = 6 | 6             |
| Final Result: 6 |

### Test Case 2: [-2, 0, -1]

| Step | Current Element | Calculation for prod1 | New prod1 | Calculation for prod2 | New prod2 | Update result | Current result |
|------|----------------|----------------------|-----------|----------------------|-----------|--------------|---------------|
| Init | -2             | Initialize           | -2        | Initialize           | -2        | Initialize    | -2            |
| 1    | 0              | max(0, -2×0, -2×0) = 0 | 0       | min(0, -2×0, -2×0) = 0 | 0       | max(-2, 0) = 0 | 0             |
| 2    | -1             | max(-1, 0×-1, 0×-1) = -1 | -1    | min(-1, 0×-1, 0×-1) = -1 | -1    | max(0, -1) = 0 | 0             |
| Final Result: 0 |

### Test Case 3: [-2, -3, -4]

| Step | Current Element | Calculation for prod1 | New prod1 | Calculation for prod2 | New prod2 | Update result | Current result |
|------|----------------|----------------------|-----------|----------------------|-----------|--------------|---------------|
| Init | -2             | Initialize           | -2        | Initialize           | -2        | Initialize    | -2            |
| 1    | -3             | max(-3, -2×-3, -2×-3) = 6 | 6    | min(-3, -2×-3, -2×-3) = -3 | -3    | max(-2, 6) = 6 | 6             |
| 2    | -4             | max(-4, 6×-4, -3×-4) = 12 | 12    | min(-4, 6×-4, -3×-4) = -24 | -24    | max(6, 12) = 12 | 12            |
| Final Result: 12 |

## Summary

The algorithm efficiently finds the maximum product subarray by tracking both maximum and minimum products at each position. The key insight is that a minimum product can become a maximum product when multiplied by a negative number. By considering all three possibilities (starting a new subarray, extending maximum, or extending minimum) at each step, we ensure we don't miss any potential maximum product subarrays.

In our main example [1, 2, -3, 0, -4, -5], the maximum product subarray is [-4, -5] with a product of 20, demonstrating how the algorithm correctly handles negative numbers and zeros.

### Step-by-Step Explanation

1. **Initialize**
   - Set prod1=1, prod2=1, result=1 from the first element
   ```
   Array: [1,2,-3,0,-4,-5]
          ^
   ```

2. **Process Element 2**
   - Calculate new prod1 = max(2, 1*2, 1*2) = 2
   - Calculate new prod2 = min(2, 1*2, 1*2) = 2
   - Update result = max(1, 2) = 2
   ```
   Array: [1,2,-3,0,-4,-5]
            ^
   ```

3. **Process Element -3**
   - Calculate new prod1 = max(-3, 2*-3, 2*-3) = -3
   - Calculate new prod2 = min(-3, 2*-3, 2*-3) = -6
   - Update result = max(2, -3) = 2
   ```
   Array: [1,2,-3,0,-4,-5]
              ^
   ```

4. **Process Element 0**
   - Calculate new prod1 = max(0, -3*0, -6*0) = 0
   - Calculate new prod2 = min(0, -3*0, -6*0) = 0
   - Update result = max(2, 0) = 2
   ```
   Array: [1,2,-3,0,-4,-5]
                ^
   ```

5. **Process Element -4**
   - Calculate new prod1 = max(-4, 0*-4, 0*-4) = -4
   - Calculate new prod2 = min(-4, 0*-4, 0*-4) = -4
   - Update result = max(2, -4) = 2
   ```
   Array: [1,2,-3,0,-4,-5]
                  ^
   ```

6. **Process Element -5**
   - Calculate new prod1 = max(-5, -4*-5, -4*-5) = 20
   - Calculate new prod2 = min(-5, -4*-5, -4*-5) = -5
   - Update result = max(2, 20) = 20
   ```
   Array: [1,2,-3,0,-4,-5]
                    ^
   ```

### Additional Example Cases

1. **All Positive Numbers**
```
Input:  [1,2,3,4]
Step 1: prod1=1, prod2=1, result=1
Step 2: prod1=2, prod2=2, result=2
Step 3: prod1=6, prod2=6, result=6
Step 4: prod1=24, prod2=24, result=24
Output: 24
```

2. **Even Number of Negative Numbers**
```
Input:  [2,-3,-2,4]
Step 1: prod1=2, prod2=2, result=2
Step 2: prod1=-3, prod2=-6, result=2
Step 3: prod1=12, prod2=-6, result=12
Step 4: prod1=48, prod2=-24, result=48
Output: 48
```

### Edge Cases Handling
1. **Single Element Array**
   ```
   Input: [5]
   Output: 5
   The algorithm correctly initializes all values with the first element.
   ```

2. **Array with All Zeroes**
   ```
   Input: [0,0,0]
   Output: 0
   All products remain zero throughout.
   ```

3. **Array with All Negative Numbers and Odd Count**
   ```
   Input: [-1,-2,-3]
   The algorithm will give 2 as the answer (product of -2 and -1).
   ```

Key Observations:
1. The algorithm elegantly handles the sign changes by tracking both maximum and minimum products
2. Zero elements effectively reset both products to zero
3. The maximum product can come from multiplying a current element with either the previous maximum or minimum

TEST CASES:
1. Input: [2,3,-2,4]
   Expected: 6
   Output: 6

2. Input: [-2,0,-1]
   Expected: 0
   Output: 0

3. Input: [-2,-3,-4]
   Expected: 12
   Output: 12

4. Input: [1,2,-3,0,-4,-5]
   Expected: 20
   Output: 20
*/
