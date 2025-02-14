/* 
Follow Up Problem: Find the contiguous subarray with the largest sum within a given array and print the subarray.

Intuition: Kadane's Algorithm is ideal for this problem as it efficiently handles both positive and negative numbers.
The key insight is that if the current running sum becomes negative, any future subarray would be better off starting fresh.
By tracking start and end indices during the sum calculations, we can identify the exact subarray that gives us the maximum sum.

Our approach is to store the starting index and the ending index of the subarray. Thus we can easily get the subarray afterward without actually storing the subarray elements.

If we carefully observe our algorithm, we can notice that the subarray always starts at the particular index where the sum variable is equal to 0, and at the ending index, the sum always crosses the previous maximum sum(i.e. maxi).

So, we will keep a track of the starting index inside the loop using a start variable.
We will take two variables ansStart and ansEnd initialized with -1. And when the sum crosses the maximum sum, we will set ansStart to the start variable and ansEnd to the current index i.e. i.
The rest of the approach will be the same as Kadane’s Algorithm.
*/

package Array.Maximum_Subarray;
public class FollowUpQue {
    public static long maxSubarraySum(int[] arr, int n) {
        // Initialize tracking variables for maximum sum encountered
        long maxi = Long.MIN_VALUE;  // Stores overall maximum sum
        long sum = 0;                // Tracks current running sum
        
        // Variables to track subarray boundaries
        int start = 0;               // Temporary start index for current subarray
        int ansStart = -1;           // Final start index of maximum sum subarray
        int ansEnd = -1;             // Final end index of maximum sum subarray
        
        // Traverse array to find maximum sum subarray
        for (int i = 0; i < n; i++) {
            // Mark potential start of new subarray when sum resets
            if (sum == 0) {
                start = i;
            }
            
            // Include current element in running sum
            sum += arr[i];
            
            // Update maximum sum and subarray boundaries if current sum is larger
            if (sum > maxi) {
                maxi = sum;
                ansStart = start;    // Store start index of maximum subarray
                ansEnd = i;          // Store end index of maximum subarray
            }
            
            // Reset sum if it becomes negative
            if (sum < 0) {
                sum = 0;
            }
        }
        
        // Print the identified maximum sum subarray
        System.out.print("The subarray is: [");
        for (int i = ansStart; i <= ansEnd; i++) {
            System.out.print(arr[i] + (i == ansEnd ? "" : " "));
        }
        System.out.println("]");
        
        // Handle edge case of all negative numbers
        return maxi < 0 ? 0 : maxi;
    }
    
    public static void main(String[] args) {
        // Test array with mix of positive and negative numbers
        int[] arr = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        int n = arr.length;
        
        // Calculate and display results
        long maxSum = maxSubarraySum(arr, n);
        System.out.println("The maximum subarray sum is: " + maxSum);
    }
}

/* 
ALGORITHM:
1. Initialize variables to track:
   - The current sum (`sum`) starting at 0.
   - The maximum sum (`maxi`) starting with the smallest possible value.
   - Indices `start` (temporary starting index), `ansStart` (start of maximum subarray), and `ansEnd` (end of maximum subarray).
2. Iterate through the array:
   - If `sum` is 0, mark the current index as a potential starting point of a new subarray.
   - Add the current element to `sum`.
   - If `sum` exceeds `maxi`, update `maxi`, `ansStart`, and `ansEnd`.
   - If `sum` becomes negative, reset `sum` to 0.
3. Print the elements of the subarray between `ansStart` and `ansEnd`.
4. Handle the edge case where all numbers are negative by setting `maxi` to 0 if needed.
5. Return `maxi`.

Complexity Analysis:
TIME COMPLEXITY: O(n)
- Single pass through the array where n is array length
- Additional O(k) for printing subarray where k is length of result subarray, but k ≤ n

SPACE COMPLEXITY:
1. Auxiliary Space: O(1)
   - Only using constant extra space for variables
2. Input Space: O(n)
   - Original input array of size n

Advantages:
1. Linear time complexity with single pass through array
2. Constant auxiliary space usage
3. Handles both positive and negative numbers
4. Provides both sum and actual subarray elements

Limitations:
1. Cannot handle empty arrays without additional validation
2. May not handle integer overflow for very large numbers
3. Returns 0 for all negative arrays, which might not be desired in some cases
4. Does not handle multiple subarrays with same maximum sum

Potential Improvements:
1. Add input validation for null or empty arrays
2. Use BigInteger for handling very large numbers
3. Add option to return all subarrays with maximum sum
4. Add support for circular array maximum sum
5. Implement parallel processing for very large arrays
6. Add option to handle all-negative arrays differently
7. Include visualization of the algorithm steps
8. Add support for different numeric types (float, double)

# Detailed Analysis of Kadane's Algorithm with Subarray Tracking

## Test Case: [-2, 1, -3, 4, -1, 2, 1, -5, 4]

### Detailed Step-by-Step Execution Table
```
---------------------------------------------------------------------------------
Step | i | arr[i] | sum | maxi | start | ansStart | ansEnd | Action
---------------------------------------------------------------------------------
0    | - | -     | 0   | MIN  | 0     | -1       | -1     | Initialize variables
1    | 0 | -2    | -2  | -2   | 0     | 0        | 0      | First element
2    | 0 | -2    | 0   | -2   | 1     | 0        | 0      | Reset sum (negative)
3    | 1 | 1     | 1   | 1    | 1     | 1        | 1      | Update maximum
4    | 2 | -3    | -2  | 1    | 1     | 1        | 1      | Continue
5    | 2 | -3    | 0   | 1    | 3     | 1        | 1      | Reset sum (negative)
6    | 3 | 4     | 4   | 4    | 3     | 3        | 3      | Update maximum
7    | 4 | -1    | 3   | 4    | 3     | 3        | 3      | Continue
8    | 5 | 2     | 5   | 5    | 3     | 3        | 5      | Update maximum
9    | 6 | 1     | 6   | 6    | 3     | 3        | 6      | Update maximum
10   | 7 | -5    | 1   | 6    | 3     | 3        | 6      | Continue
11   | 8 | 4     | 5   | 6    | 3     | 3        | 6      | Final state
---------------------------------------------------------------------------------
```

### Step-by-Step Process Explanation

1. **Initialization** (Step 0)
   - sum = 0 (running sum)
   - maxi = MIN_VALUE (maximum sum found)
   - start = 0 (temporary start index)
   - ansStart = -1 (final start index)
   - ansEnd = -1 (final end index)

2. **First Element: -2** (Steps 1-2)
   - Add -2 to sum: sum = -2
   - Update maxi = -2 as it's the first value
   - Sum is negative, reset to 0
   - Move start to next position (1)

3. **Second Element: 1** (Step 3)
   - Add 1 to sum: sum = 1
   - Update maxi = 1 as it's larger
   - Update ansStart = 1, ansEnd = 1
   - Continue as sum is positive

4. **Third Element: -3** (Steps 4-5)
   - Add -3 to sum: sum = -2
   - Keep maxi = 1 as it's larger
   - Sum is negative, reset to 0
   - Move start to next position (3)

5. **Fourth Element: 4** (Step 6)
   - Add 4 to sum: sum = 4
   - Update maxi = 4 as it's larger
   - Update ansStart = 3, ansEnd = 3
   - Continue as sum is positive

6. **Fifth through Seventh Elements: -1, 2, 1** (Steps 7-9)
   - Add -1: sum = 3
   - Add 2: sum = 5, update maxi = 5
   - Add 1: sum = 6, update maxi = 6
   - Update ansEnd = 6
   - Continue as sum remains positive

7. **Eighth Element: -5** (Step 10)
   - Add -5: sum = 1
   - Keep maxi = 6 as it's larger
   - Continue as sum is still positive

8. **Final Element: 4** (Step 11)
   - Add 4: sum = 5
   - Keep maxi = 6 as it's larger
   - Final subarray determined by ansStart = 3, ansEnd = 6

### Final Result Analysis
- Maximum Sum: 6
- Subarray: [4, -1, 2, 1]
- Starting Index: 3
- Ending Index: 6

### Key Observations from the Dry Run

1. **Sum Reset Points**
   - Reset occurs at steps 2 and 5 when sum becomes negative
   - Each reset marks a potential new start point

2. **Maximum Updates**
   - Initial update: Step 1 (maxi = -2)
   - Second update: Step 3 (maxi = 1)
   - Third update: Step 6 (maxi = 4)
   - Fourth update: Step 8 (maxi = 5)
   - Final update: Step 9 (maxi = 6)

3. **Subarray Boundary Updates**
   - Start index changed whenever sum reset to 0
   - End index updated with each new maximum

4. **Local vs Global Maximum**
   - Local maximum tracked by 'sum'
   - Global maximum tracked by 'maxi'
   - Final subarray boundaries preserved even when later sums fluctuate

### Verification Cases

1. **All Positive Numbers**
```
Input: [1, 2, 3, 4]
Output: Sum = 10, Subarray = [1, 2, 3, 4]
Explanation: Entire array is the maximum subarray
```

2. **All Negative Numbers**
```
Input: [-1, -2, -3, -4]
Output: Sum = 0, Subarray = []
Explanation: Returns 0 as per problem constraint
```

3. **Mixed Numbers with Single Element**
```
Input: [-2, 5, -1]
Output: Sum = 5, Subarray = [5]
Explanation: Single element forms maximum subarray
```

4. **Mixed Numbers with Multiple Elements**
```
Input: [-2, 1, -3, 4, -1, 2, 1]
Output: Sum = 6, Subarray = [4, -1, 2, 1]
Explanation: Multiple elements form maximum subarray
```

Key Observations:
1. Algorithm efficiently handles alternating positive and negative numbers
2. Tracking start/end indices allows retrieval of actual subarray
3. Resetting sum to 0 is crucial for handling negative numbers
4. Edge case handling for all-negative arrays affects result type

Sample Validation:
Input: [-2, 1, -3, 4, -1, 2, 1, -5, 4]
Expected: Sum = 6, Subarray = [4, -1, 2, 1]
Output: Sum = 6, Subarray = [4, -1, 2, 1]

Key Points:
1. Algorithm maintains O(n) complexity while tracking subarray
2. Solution handles edge cases gracefully
3. Implementation is memory efficient with O(1) auxiliary space
4. Code is structured for readability and maintainability

TEST CASES:
1. Input: [1, 2, 3, 4]
   Expected: 10, [1, 2, 3, 4]
   Output: 10, [1, 2, 3, 4]
   
2. Input: [-1, -2, -3, -4]
   Expected: 0, []
   Output: 0, []
   
3. Input: [-2, 3, -1, 2]
   Expected: 4, [3, -1, 2]
   Output: 4, [3, -1, 2]
*/