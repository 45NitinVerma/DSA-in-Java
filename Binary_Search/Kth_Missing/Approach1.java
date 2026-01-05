package Binary_Search.Kth_Missing;
/* 
Kth Missing Positive Number
# Problem: Given an array arr of positive integers sorted in a strictly increasing order, and an integer k.
Return the kth positive integer that is missing from this array.

## Intuition: 
The core concept is to shift our target value k whenever we encounter array elements that are less than or equal to our current k value.
- Main approach: Linear traversal with k-shifting technique
- Key insight: If an array element is ≤ k, it means one of the missing numbers we were counting is actually present, so we need to look for the next missing number

## Basic Understanding
We need to find the kth missing positive integer from a sorted array. Instead of generating all missing numbers, we cleverly shift our target k value whenever we find that a number we thought was missing is actually present in the array.

## Key Observations with Examples

### Observation 1: K-Shifting Pattern
When we encounter an array element that is ≤ k, it means this number is not missing, so we increment k to look for the next potential missing number.
* If arr[i] ≤ k, then k should be incremented
* If arr[i] > k, then k is our answer (it's missing)

### Observation 2: Sorted Array Advantage
Since the array is sorted, once we find an element > k, all subsequent elements will also be > k.
Example: In array [4, 7, 9, 10] with k=4, once we see 7 > 4, we know 9 and 10 will also be > 4.

### Observation 3: Missing Number Identification
The algorithm identifies missing numbers by checking if current array elements "consume" our k value.
```
Array: [4, 7, 9, 10], k = 4
Missing numbers: 1, 2, 3, 5, 6, 8, 11, 12...
4th missing number = 5
```

### Observation 4: Early Termination
We can break the loop as soon as we find an element > k, because k at that point is our answer.

## Step-by-Step Example
Let's work through array [4, 7, 9, 10] with k = 4:

1. Step One: Initialize
   ```
   arr = [4, 7, 9, 10]
   k = 4 (looking for 4th missing number)
   i = 0
   ```

2. Step Two: Check arr[0] = 4
   ```
   arr[0] = 4, k = 4
   Since 4 ≤ 4, increment k to 5
   k becomes 5 (now looking for 5th missing number)
   ```

3. Step Three: Check arr[1] = 7
   ```
   arr[1] = 7, k = 5
   Since 7 > 5, break the loop
   Return k = 5
   ```

## Special Cases

### Case 1: All Array Elements Are Small
Input: arr = [1, 2, 3], k = 5
- All elements ≤ k, so k keeps incrementing
- Result: k becomes 8 (5th missing number after [1,2,3])

### Case 2: First Element Is Large
Input: arr = [10, 11, 12], k = 3
- First element 10 > 3, so break immediately
- Result: 3 (since 1, 2, 3 are all missing)

### Case 3: Empty Array
Input: arr = [], k = 4
- No elements to check
- Result: 4 (4th positive number)

 */
public class Approach1 {
    // Approach 1: Linear Search
    public static int missingK(int[] vec, int n, int k) {
        // Traverse through the sorted array
        for (int i = 0; i < n; i++) {
            // If current element is less than or equal to k
            // it means this number is present, so we need to look for next missing number
            if (vec[i] <= k) {
                k++; // Shift k to find the next missing number
            } else {
                // Current element is greater than k
                // This means k is missing, so break
                break;
            }
        }
        // Return the kth missing positive number
        return k;
    }

    public static void main(String[] args) {
        // Example input: sorted array of positive integers
        int[] vec = {4, 7, 9, 10};
        int n = 4;
        int k = 4; // Find 4th missing positive number

        // Call the method and get the result
        int ans = missingK(vec, n, k);

        // Display the result
        System.out.println("The missing number is: " + ans);
    }
}
/*
# Algorithm
1. Initialize loop counter i = 0
2. Traverse array from index 0 to n-1
3. For each element vec[i], check if vec[i] ≤ k
4. If vec[i] ≤ k, increment k by 1 (shift to next missing number)
5. If vec[i] > k, break the loop (k is the answer)
6. Return the final value of k

### Time Complexity Breakdown per Step
1. Array traversal: O(n) in worst case
2. Comparison operation: O(1) per iteration
3. K increment operation: O(1) per iteration

Total: O(n)

### Space Complexity Breakdown
1. Auxiliary Space: O(1) - only using loop variable i
2. Input Space: O(n) - for storing the input array

Total: O(1) auxiliary space

# Advantages
1. Simple and intuitive approach
2. No extra space needed for storing missing numbers
3. Early termination possible when element > k is found
4. Works efficiently for small arrays
5. Easy to understand and implement

# Limitations
1. Time complexity is O(n) which can be improved
2. Not optimal for very large arrays
3. Doesn't utilize the sorted nature of array efficiently
4. Sequential processing - no parallel optimization possible

# Potential Improvements
1. Binary Search approach can reduce time complexity to O(log n)
2. Mathematical formula approach for specific cases
3. Two-pointer technique for certain scenarios

# Step-by-Step Process with Dry Run

## Example Input: arr = [4, 7, 9, 10], k = 4

### Detailed Execution Table
```
Step | i | arr[i] | k | Condition | Action | New k | Status
-----|---|--------|---|-----------|--------|-------|--------
1    | 0 | 4      | 4 | 4 ≤ 4     | k++    | 5     | Continue
2    | 1 | 7      | 5 | 7 > 5     | break  | 5     | Break & Return
```

### Step-by-Step Explanation

1. **Initialization**
   - Array: [4, 7, 9, 10], k = 4, i = 0
   ```
   Missing numbers so far: 1, 2, 3, ?
   We're looking for the 4th missing number
   ```

2. **First Iteration (i = 0)**
   - Check arr[0] = 4 against k = 4
   - Since 4 ≤ 4, number 4 is present in array
   - Increment k to 5 (now looking for 5th missing number)
   ```
   Missing numbers so far: 1, 2, 3, 5, ?
   Now looking for 5th missing number
   ```

3. **Second Iteration (i = 1)**
   - Check arr[1] = 7 against k = 5
   - Since 7 > 5, number 5 is missing
   - Break loop and return k = 5
   ```
   Missing numbers: 1, 2, 3, 5
   4th missing number = 5
   ```

### Additional Example Cases

1. **Larger K Value**
```
Input:  arr = [2, 3, 4, 7, 11], k = 6
Step 1: arr[0]=2 ≤ 6, k becomes 7
Step 2: arr[1]=3 ≤ 7, k becomes 8  
Step 3: arr[2]=4 ≤ 8, k becomes 9
Step 4: arr[3]=7 ≤ 9, k becomes 10
Step 5: arr[4]=11 > 10, break
Output: 10
```

2. **Small Array**
```
Input:  arr = [1, 3], k = 3
Step 1: arr[0]=1 ≤ 3, k becomes 4
Step 2: arr[1]=3 ≤ 4, k becomes 5
Step 3: Loop ends, return k = 5
Output: 5
```

### Edge Cases Handling
1. **Empty Array**
   ```
   Input: arr = [], k = 3
   Output: 3
   Explanation: No elements to check, so 3rd missing number is 3
   ```

2. **All Elements Greater Than K**
   ```
   Input: arr = [10, 11, 12], k = 2
   Output: 2
   Explanation: First element 10 > 2, so break immediately
   ```

3. **Single Element Array**
   ```
   Input: arr = [5], k = 2
   Output: 2
   Explanation: 5 > 2, so 2 is missing
   ```

Key Observations:
1. The algorithm shifts k whenever an array element "consumes" a missing number slot
2. Early termination occurs when we find an element larger than current k
3. The final k value represents the kth missing positive integer
4. Array being sorted allows for early termination optimization

Sample Validation:
Input: [4, 7, 9, 10], k = 4
Expected: 5 (missing numbers: 1, 2, 3, 5)
Output: 5

Key Points:
1. Time complexity: O(n) in worst case
2. Space complexity: O(1) auxiliary space
3. Simple linear approach with k-shifting technique
4. Utilizes sorted array property for early termination

TEST CASES:
1. Input: arr = [4, 7, 9, 10], k = 4
   Expected: 5
   Output: 5

2. Input: arr = [1, 2, 3, 4], k = 2
   Expected: 6
   Output: 6

3. Input: arr = [2], k = 1
   Expected: 1
   Output: 1

4. Input: arr = [1, 3, 5], k = 4
   Expected: 7
   Output: 7
 */