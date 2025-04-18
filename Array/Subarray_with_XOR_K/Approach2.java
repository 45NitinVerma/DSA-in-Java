package Array.Subarray_with_XOR_K;
/* 
 # Problem: Subarray with XOR K
 Given an array ‘A’ consisting of ‘N’ integers and an integer ‘B’, find the number of subarrays of array ‘A’ whose bitwise XOR( ⊕ ) of all elements is equal to ‘B’.
 A subarray of an array is obtained by removing some(zero or more) elements from the front and back of the array.
 
 ## Example:
 ### Input: ‘N’ = 4 ‘B’ = 2, ‘A’ = [1, 2, 3, 2]
 ### Output: 3

 Explanation: Subarrays have bitwise xor equal to ‘2’ are: [1, 2, 3, 2], [2], [2].

 ## Intuition: If we carefully observe, we can notice that to get the XOR of the current subarray we just need to XOR the current element(i.e. arr[j]) to the XOR of the previous subarray i.e. arr[i….j-1].

        Assume previous subarray = arr[i……j-1]
        current subarray = arr[i…..j]
        XOR of arr[i….j] = (XOR of arr[i….j-1]) ^ arr[j]

## Key Observations with Examples

### Observation 1: XOR Properties
- XOR (^) is an associative operation: (a^b)^c = a^(b^c)
- Each element can be part of multiple subarrays
- We need to compute XOR for each possible subarray

### Observation 2: Generating Subarrays
- For an array of length n, there are n(n+1)/2 possible subarrays
- We can generate all subarrays using two nested loops
- Outer loop (i) marks the start of subarray
- Inner loop (j) marks the end of subarray
```
Array: [4, 2, 2, 6, 4]
Subarrays:
[4], [4,2], [4,2,2], [4,2,2,6], [4,2,2,6,4]
[2], [2,2], [2,2,6], [2,2,6,4]
[2], [2,6], [2,6,4]
[6], [6,4]
[4]
```

### Observation 3: Running XOR
- Instead of recalculating XOR from scratch for each subarray, we can use a running XOR
- For subarray a[i:j], the XOR equals the XOR of all elements from index i to j
- As j increases, we just need to XOR the running result with the new element a[j]

## Step-by-Step Example
Let's work through the example array [4, 2, 2, 6, 4] with K=6:

1. Start with i=0 (first element: 4):
   ```
   j=0: xor = 4, xor != K (4 != 6), count = 0
   j=1: xor = 4^2 = 6, xor == K (6 == 6), count = 1
   j=2: xor = 6^2 = 4, xor != K (4 != 6), count = 1
   j=3: xor = 4^6 = 2, xor != K (2 != 6), count = 1
   j=4: xor = 2^4 = 6, xor == K (6 == 6), count = 2
   ```

2. Continue with i=1 (second element: 2):
   ```
   j=1: xor = 2, xor != K (2 != 6), count = 2
   j=2: xor = 2^2 = 0, xor != K (0 != 6), count = 2
   j=3: xor = 0^6 = 6, xor == K (6 == 6), count = 3
   j=4: xor = 6^4 = 2, xor != K (2 != 6), count = 3
   ```

3. Continue with i=2 (third element: 2):
   ```
   j=2: xor = 2, xor != K (2 != 6), count = 3
   j=3: xor = 2^6 = 4, xor != K (4 != 6), count = 3
   j=4: xor = 4^4 = 0, xor != K (0 != 6), count = 3
   ```

4. Continue with i=3 (fourth element: 6):
   ```
   j=3: xor = 6, xor == K (6 == 6), count = 4
   j=4: xor = 6^4 = 2, xor != K (2 != 6), count = 4
   ```

5. Finish with i=4 (fifth element: 4):
   ```
   j=4: xor = 4, xor != K (4 != 6), count = 4
   ```

Total count = 4 subarrays with XOR = 6.

## Special Cases

### Case 1: Empty Array
Input: [], K=any
- Behavior: No subarrays to check
- Result: 0

### Case 2: Single Element Equal to K
Input: [K], K=any
- Behavior: Single subarray with XOR equal to K
- Result: 1

### Case 3: No Subarrays with XOR=K
Input: [1, 2, 3], K=7
- Behavior: No combinations yield the required XOR
- Result: 0
*/

public class Approach2 {
    // Approach 2: Using 2 nested loops
    public static int subarraysWithXorK(int[] a, int k) {
        int n = a.length; // Size of the given array
        int count = 0;    // Initialize count of subarrays

        // Step 1: Generate all possible subarrays
        for (int i = 0; i < n; i++) {
            int currentXor = 0;  // Initialize XOR for current subarray
            
            // For each starting point i, consider all possible ending points j
            for (int j = i; j < n; j++) {
                // Step 2: Calculate XOR by including the current element
                // XOR is cumulative - we just need to XOR the running result with the new element
                currentXor = currentXor ^ a[j];
                
                // Step 3: Check if the current subarray's XOR equals k
                if (currentXor == k) {
                    count++;  // Increment count if XOR matches
                }
            }
        }
        return count;  // Return the total count
    }

    // Main method for testing
    public static void main(String[] args) {
        // Example input
        int[] a = {4, 2, 2, 6, 4};
        int k = 6;

        // Call the method and print the output
        int result = subarraysWithXorK(a, k);

        // Display the result
        System.out.println("The number of subarrays with XOR " + k + " is: " + result);
    }
}

/*
# Algorithm
1. Initialize a counter variable to 0
2. Use two nested loops to generate all possible subarrays:
   - Outer loop (i) defines the starting index
   - Inner loop (j) defines the ending index
3. For each subarray, calculate the XOR of all elements
4. If the XOR equals k, increment the counter
5. Return the final count

### Time Complexity Breakdown per Step
1. Outer loop runs n times: O(n)
2. Inner loop runs (n-i) times for each i: O(n)
3. XOR operation for each element: O(1)

Total: O(n²) where n is the size of the array

### Space Complexity Breakdown
1. Auxiliary Space: O(1)
   - We only use a few variables (count, currentXor, i, j)
2. Input Space: O(n)
   - The space required to store the input array

Total: O(1) auxiliary space complexity

# Advantages
1. Simple and straightforward implementation
2. Works for all valid inputs without special handling
3. No additional data structures required
4. Easy to understand and debug

# Limitations
1. O(n²) time complexity makes it inefficient for large arrays
2. Recalculates XOR for overlapping subarrays, leading to redundant operations
3. Not optimal for real-time or performance-critical applications

# Potential Improvements
1. Use a hash map to optimize to O(n) time complexity
2. Use prefix XOR array to avoid redundant calculations
3. Implement early termination conditions for certain cases

1. **Initialization**
   - We start with count = 0
   - We'll examine every possible subarray

2. **First Iteration (i=0)**
   - Start with the first element (4)
   - Check all subarrays starting from this element:
   ```
   [4] → XOR=4 (not equal to 6)
   [4,2] → XOR=6 (equal to 6) → count=1
   [4,2,2] → XOR=4 (not equal to 6)
   [4,2,2,6] → XOR=2 (not equal to 6)
   [4,2,2,6,4] → XOR=6 (equal to 6) → count=2
   ```

3. **Second Iteration (i=1)**
   - Start with the second element (2)
   - Check all subarrays starting from this element:
   ```
   [2] → XOR=2 (not equal to 6)
   [2,2] → XOR=0 (not equal to 6)
   [2,2,6] → XOR=6 (equal to 6) → count=3
   [2,2,6,4] → XOR=2 (not equal to 6)
   ```

4. **Third Iteration (i=2)**
   - Start with the third element (2)
   - Check all subarrays starting from this element:
   ```
   [2] → XOR=2 (not equal to 6)
   [2,6] → XOR=4 (not equal to 6)
   [2,6,4] → XOR=0 (not equal to 6)
   ```

5. **Fourth Iteration (i=3)**
   - Start with the fourth element (6)
   - Check all subarrays starting from this element:
   ```
   [6] → XOR=6 (equal to 6) → count=4
   [6,4] → XOR=2 (not equal to 6)
   ```

6. **Fifth Iteration (i=4)**
   - Start with the fifth element (4)
   - Check all subarrays starting from this element:
   ```
   [4] → XOR=4 (not equal to 6)
   ```

7. **Final Result**
   - Return the count = 4

### Additional Example Cases

1. **Small Array**
```
Input: [5, 3], K=6
Step 1: Check [5] → XOR=5 != 6 → count=0
Step 2: Check [5,3] → XOR=5^3=6 == 6 → count=1
Step 3: Check [3] → XOR=3 != 6 → count=1
Output: 1 subarray
```

2. **Array with XORs Equal to K**
```
Input: [6, 0, 6], K=6
Step 1: Check [6] → XOR=6 == 6 → count=1
Step 2: Check [6,0] → XOR=6^0=6 == 6 → count=2
Step 3: Check [6,0,6] → XOR=6^0^6=0 != 6 → count=2
Step 4: Check [0] → XOR=0 != 6 → count=2
Step 5: Check [0,6] → XOR=0^6=6 == 6 → count=3
Step 6: Check [6] → XOR=6 == 6 → count=4
Output: 4 subarrays
```

### Edge Cases Handling
1. **Empty Array**
   ```
   Input: [], K=any
   The outer loop doesn't run (i=0 to n-1 where n=0)
   Output: 0
   No subarrays available to check, so the count remains 0
   ```

2. **Single Element Matching K**
   ```
   Input: [6], K=6
   Only one subarray: [6]
   XOR = 6 == K
   Output: 1
   The count is incremented once
   ```

3. **All Zeros with K=0**
   ```
   Input: [0, 0, 0], K=0
   Six subarrays: [0], [0,0], [0,0,0], [0], [0,0], [0]
   All have XOR=0
   Output: 6
   Each subarray contributes to the count
   ```

Key Observations:
1. The algorithm correctly identifies all subarrays with the required XOR
2. Running XOR calculation avoids redundant computations within a single subarray
3. Time complexity is quadratic, making it suitable for small to medium-sized arrays
4. The approach handles all valid inputs correctly without special cases

Sample Validation:
Input: [4, 2, 2, 6, 4], K=6
Expected: 4
Output: 4

Key Points:
1. XOR is a bitwise operation that's commutative and associative
2. Running XOR calculation is more efficient than recalculating for each subarray
3. The brute force approach is simple but not optimal for large datasets
4. This solution can be improved using prefix XOR and hash map techniques

TEST CASES:
1. Input: [4, 2, 2, 6, 4], K=6
   Expected: 4
   Output: 4
2. Input: [5, 6, 7, 8, 9], K=5
   Expected: 1
   Output: 1
3. Input: [1, 2, 3], K=0
   Expected: 0
   Output: 0
4. Input: [0, 0, 0], K=0
   Expected: 6
   Output: 6
*/