/* 
Problem: Find the contiguous subarray with the largest sum within a given array.

Intuition: Use two nested loops to generate all possible subarrays and track the maximum sum found.
We start from each index i and extend the subarray to all possible ending positions j, calculating
the sum for each subarray.
*/

package Array.Maximum_Subarray;

public class Approach2 {
    // Approach 2: Better Brute Force - Using 2 loops
    public static int maxSubarraySum(int[] arr, int n) {
        // Initialize maximum sum to smallest possible integer
        int maxi = Integer.MIN_VALUE; 

        // Outer loop: starting point of subarray
        for (int i = 0; i < n; i++) {
            // Reset sum for each new starting point
            int sum = 0;
            
            // Inner loop: ending point of subarray
            for (int j = i; j < n; j++) {
                // Add current element to running sum
                sum += arr[j];
                
                // Update maximum if current sum is larger
                maxi = Math.max(maxi, sum);
            }
        }
        return maxi;
    }

    public static void main(String args[]) {
        int[] arr = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        int n = arr.length;
        int maxSum = maxSubarraySum(arr, n);
        System.out.println("The maximum subarray sum is: " + maxSum);
    }
}

/* 
ALGORITHM:
1. Initialize maximum sum (maxi) to Integer.MIN_VALUE
2. For each starting index i from 0 to n-1:
   a. Initialize sum as 0
   b. For each ending index j from i to n-1:
      - Add arr[j] to sum
      - Update maxi if current sum is larger
3. Return maxi

Complexity Analysis:
TIME COMPLEXITY: O(n²)
- Two nested loops, where n is the array length
- Outer loop runs n times
- Inner loop runs (n-i) times for each i

SPACE COMPLEXITY:
1. Auxiliary Space: O(1)
   - Only using fixed variables (maxi, sum, loop counters)
2. Input Space: O(n)
   - Original array storage

Advantages:
1. Simple to understand and implement
2. Works well for small arrays
3. No extra space required

Limitations:
1. Quadratic time complexity makes it inefficient for large arrays
2. Redundant calculations as sums are recomputed multiple times

Potential Improvements:
1. Use Kadane's Algorithm to achieve O(n) time complexity
2. Implement divide and conquer approach for parallelization
3. Add input validation for null/empty arrays
4. Add handling for integer overflow cases
5. Track subarray indices for visualization purposes

# Detailed Dry Run Analysis - Maximum Subarray

## Input Array: [-2, 1, -3, 4, -1, 2, 1, -5, 4]

### Step-by-Step Execution with Each Iteration

#### Starting with i = 0
```
Initial State: maxi = Integer.MIN_VALUE
```
##### For i = 0:
| j |Current Element| Running Sum | Maximum So Far | Subarray Considered             |
|---|---------------|-------------|----------------|---------------------------------|
| 0 | -2            | -2          | -2             | [-2]                            |
| 1 | 1             | -1          | -2             | [-2, 1]                         |
| 2 | -3            | -4          | -2             | [-2, 1, -3]                     |
| 3 | 4             | 0           | 0              | [-2, 1, -3, 4]                  |
| 4 | -1            | -1          | 0              | [-2, 1, -3, 4, -1]              |
| 5 | 2             | 1           | 1              | [-2, 1, -3, 4, -1, 2]           |
| 6 | 1             | 2           | 2              | [-2, 1, -3, 4, -1, 2, 1]        |
| 7 | -5            | -3          | 2              | [-2, 1, -3, 4, -1, 2, 1, -5]    |
| 8 | 4             | 1           | 2              | [-2, 1, -3, 4, -1, 2, 1, -5, 4] |

##### For i = 1:
| j |Current Element| Running Sum | Maximum So Far | Subarray Considered         |
|---|---------------|-------------|----------------|-----------------------------|
| 1 | 1             | 1           | 2              | [1]                         |
| 2 | -3            | -2          | 2              | [1, -3]                     |
| 3 | 4             | 2           | 2              | [1, -3, 4]                  |
| 4 | -1            | 1           | 2              | [1, -3, 4, -1]              |
| 5 | 2             | 3           | 3              | [1, -3, 4, -1, 2]           |
| 6 | 1             | 4           | 4              | [1, -3, 4, -1, 2, 1]        |
| 7 | -5            | -1          | 4              | [1, -3, 4, -1, 2, 1, -5]    |
| 8 | 4             | 3           | 4              | [1, -3, 4, -1, 2, 1, -5, 4] |

##### For i = 2:
| j |Current Element | Running Sum | Maximum So Far | Subarray Considered     |
|---|----------------|-------------|----------------|-------------------------|
| 2 | -3            | -3          | 4              | [-3]                     |
| 3 | 4             | 1           | 4              | [-3, 4]                  |
| 4 | -1            | 0           | 4              | [-3, 4, -1]              |
| 5 | 2             | 2           | 4              | [-3, 4, -1, 2]           |
| 6 | 1             | 3           | 4              | [-3, 4, -1, 2, 1]        |
| 7 | -5            | -2          | 4              | [-3, 4, -1, 2, 1, -5]    |
| 8 | 4             | 2           | 4              | [-3, 4, -1, 2, 1, -5, 4] |

##### For i = 3:
| j |Current Element| Running Sum | Maximum So Far | Subarray Considered |
|---|---------------|-------------|----------------|---------------------|
| 3 | 4             | 4           | 4              | [4]                 |
| 4 | -1            | 3           | 4              | [4, -1]             |
| 5 | 2             | 5           | 5              | [4, -1, 2]          |
| 6 | 1             | 6           | 6              | [4, -1, 2, 1]       |
| 7 | -5            | 1           | 6              | [4, -1, 2, 1, -5]   |
| 8 | 4             | 5           | 6              | [4, -1, 2, 1, -5, 4]|

##### For i = 4:
| j |Current Element| Running Sum | Maximum So Far | Subarray Considered |
|---|---------------|-------------|----------------|---------------------|
| 4 | -1            | -1          | 6              | [-1]                |
| 5 | 2             | 1           | 6              | [-1, 2]             |
| 6 | 1             | 2           | 6              | [-1, 2, 1]          |
| 7 | -5            | -3          | 6              | [-1, 2, 1, -5]      |
| 8 | 4             | 1           | 6              | [-1, 2, 1, -5, 4]   |

##### For i = 5:
| j |Current Element| Running Sum | Maximum So Far | Subarray Considered|
|---|---------------|-------------|----------------|--------------------|
| 5 | 2             | 2           | 6              | [2]                |
| 6 | 1             | 3           | 6              | [2, 1]             |
| 7 | -5            | -2          | 6              | [2, 1, -5]         |
| 8 | 4             | 2           | 6              | [2, 1, -5, 4]      |

##### For i = 6:
| j |Current Element| Running Sum | Maximum So Far | Subarray Considered |
|---|---------------|-------------|----------------|---------------------|
| 6 | 1             | 1           | 6              | [1]                 |
| 7 | -5            | -4          | 6              | [1, -5]             |
| 8 | 4             | 0           | 6              | [1, -5, 4]          |

##### For i = 7:
| j |Current Element| Running Sum | Maximum So Far | Subarray Considered |
|---|---------------|-------------|----------------|---------------------|
| 7 | -5            | -5          | 6              | [-5]                |
| 8 | 4             | -1          | 6              | [-5, 4]             |

##### For i = 8:
| j |Current Element| Running Sum | Maximum So Far | Subarray Considered |
|---|---------------|-------------|----------------|---------------------|
| 8 | 4             | 4           | 6              | [4]                 |

### Key Observations from the Dry Run:

1. The maximum subarray sum (6) is found when i = 3 and j = 6, corresponding to subarray [4, -1, 2, 1]

2. Important Iterations:
   - First potential maximum found at i=3, j=6 (sum = 6)
   - No better sum was found in subsequent iterations

3. Process Flow:
   - For each starting point i, we calculate sums of all possible subarrays starting at i
   - Keep track of maximum sum seen so far
   - Continue until all possible subarrays are examined

4. Pattern Recognition:
   - Positive numbers help increase the sum
   - When sum becomes negative, next positive number might start a new potential maximum subarray
   - Final maximum sum of 6 is achieved by balancing positive and negative numbers optimally

### Output Summary:
- Maximum Sum: 6
- Maximum Sum Subarray: [4, -1, 2, 1]
- Starting Index: 3
- Ending Index: 6

Key Observations:
1. Algorithm considers all possible subarrays
2. Maintains running sum to avoid recalculating from scratch
3. Updates maximum sum only when necessary

Sample Validation:
Input: [-2, 1, -3, 4, -1, 2, 1, -5, 4]
Expected: 6 (subarray [4, -1, 2, 1])
Output: 6

Key Points:
1. Better than checking all possible subarrays individually
2. Still not optimal for large datasets
3. Good for educational purposes to understand the problem

TEST CASES:
1. Input: [-2, 1, -3, 4, -1, 2, 1, -5, 4]
   Expected: 6
   Output: 6
2. Input: [-1]
   Expected: -1
   Output: -1
3. Input: [1, 2, 3, 4]
   Expected: 10
   Output: 10
4. Input: [-2, -3, -1, -5]
   Expected: -1
   Output: -1
*/