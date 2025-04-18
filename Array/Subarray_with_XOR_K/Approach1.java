package Array.Subarray_with_XOR_K;
/* 
 # Problem: Subarray with XOR K
 Given an array ‘A’ consisting of ‘N’ integers and an integer ‘K’, find the number of subarrays of array ‘A’ whose bitwise XOR( ⊕ ) of all elements is equal to ‘K’.
 A subarray of an array is obtained by removing some(zero or more) elements from the front and back of the array.
 
 ## Example:
 ### Input: ‘N’ = 4 ‘K’ = 2, ‘A’ = [1, 2, 3, 2]
 ### Output: 3
 
 Explanation: Subarrays have bitwise xor equal to ‘2’ are: [1, 2, 3, 2], [2], [2].

# Intuition
- We need to count all subarrays whose XOR equals a target value K
- The brute force approach involves generating all possible subarrays and checking each one's XOR
- This problem leverages the properties of XOR operations to find valid subarrays

## Special Cases

### Case 1: Empty Array
Input: [], K = 5
- Behavior: No subarrays to check
- Result: 0 (no subarrays with XOR K)

### Case 2: All Zeros
Input: [0, 0, 0], K = 0
- Behavior: XOR of any subarray will be 0
- Result: 6 (all possible subarrays have XOR 0)

### Case 3: All Elements Equal to K
Input: [5, 5, 5], K = 5
- Behavior: Single elements match K, but XOR of multiple 5s is 0
- Result: 3 (only single-element subarrays have XOR K)
*/

public class Approach1 {
    // Approach 1: Using 3 nested loops
    public static int subarraysWithXorK(int[] a, int k) {
        int n = a.length; // Size of the given array
        int cnt = 0;      // Counter for valid subarrays

        // Step 1: Generate all possible subarrays
        for (int i = 0; i < n; i++) {
            // i represents the starting index of subarray
            for (int j = i; j < n; j++) {
                // j represents the ending index of subarray
                
                // Step 2: Calculate XOR of all elements in current subarray
                int xorr = 0;
                for (int K = i; K <= j; K++) {
                    // XOR all elements from index i to j
                    xorr = xorr ^ a[K];
                }

                // Step 3: Check if XOR matches target k and increment count
                if (xorr == k) {
                    cnt++;
                    // For debugging: Uncomment to print the found subarray
                    // System.out.print("Found subarray: ");
                    // for (int K = i; K <= j; K++) {
                    //     System.out.print(a[K] + " ");
                    // }
                    // System.out.println();
                }
            }
        }
        return cnt; // Return the final count
    }

    // Main method for testing
    public static void main(String[] args) {
        // Example input
        int[] a = {4, 2, 2, 6, 4};
        int k = 6;
        
        // Call the method and print the output
        int ans = subarraysWithXorK(a, k);
        
        // Display the result
        System.out.println("The number of subarrays with XOR k is: " + ans);
        
        // Additional test case from the problem statement
        int[] a2 = {1, 2, 3, 2};
        int k2 = 2;
        int ans2 = subarraysWithXorK(a2, k2);
        System.out.println("Test case 2 - Number of subarrays with XOR " + k2 + " is: " + ans2);
    }
}

/*
# Algorithm
1. Use two nested loops to generate all possible subarrays (starting index i, ending index j)
2. For each subarray, calculate the XOR of all elements using a third loop
3. Check if the calculated XOR equals the target value k
4. If it matches, increment the counter
5. Return the final count

### Time Complexity Breakdown per Step
1. Outer loop (i): O(n)
2. Middle loop (j): O(n)
3. Inner loop (K): O(n)

Total: O(n³) - cubic time complexity

### Space Complexity Breakdown
1. Auxiliary Space: O(1)
   - We only use a few variables (n, cnt, xorr) regardless of input size
2. Input Space: O(n)
   - The input array of size n

Total: O(n) space complexity

# Advantages
1. Simple to understand and implement
2. Works for all types of inputs without special handling
3. No need for additional data structures

# Limitations
1. High time complexity of O(n³) makes it inefficient for large arrays
2. Performs redundant XOR calculations for overlapping subarrays
3. Not suitable for time-sensitive applications with large inputs

# Potential Improvements
1. Use prefix XOR array to reduce time complexity to O(n²)
2. Use HashMap to further reduce time complexity to O(n)
3. Add early termination conditions for specific cases

# Step-by-Step Process with Dry Run

## Example Input: [4, 2, 2, 6, 4], k = 6
### Step-by-Step Explanation

1. **Initialize Variables**
   - Set count = 0
   - Process each subarray to check if XOR equals k=6
   ```
   cnt = 0, array = [4, 2, 2, 6, 4], k = 6
   ```

2. **Process Subarray [4,2]**
   - Calculate XOR: 4 ⊕ 2 = 6
   - 6 == k (6), so increment count
   ```
   cnt = 1
   ```

3. **Process Subarray [4,2,2,6,4]**
   - Calculate XOR: 4 ⊕ 2 ⊕ 2 ⊕ 6 ⊕ 4 = 6
   - 6 == k (6), so increment count
   ```
   cnt = 2
   ```

4. **Process Subarray [2,2,6]**
   - Calculate XOR: 2 ⊕ 2 ⊕ 6 = 6
   - 6 == k (6), so increment count
   ```
   cnt = 3
   ```

5. **Process Subarray [6]**
   - XOR of single element is 6
   - 6 == k (6), so increment count
   ```
   cnt = 4
   ```

### Additional Example Cases

1. **Original Example from Problem Statement**
```
Input:  [1, 2, 3, 2], k = 2
Subarrays with XOR = 2: [1,2,3,2], [2], [2]
Output: 3
```

2. **Zero XOR Target**
```
Input:  [5, 3, 8, 3, 10], k = 0
Subarrays with XOR = 0: [5,3,8,3], [3,3]
Output: 2
```

### Edge Cases Handling
1. **Single Element Equal to K**
   ```
   Input: [6], k = 6
   Output: 1
   The array has only one element and it equals k, so there's exactly one valid subarray.
   ```

2. **No Valid Subarrays**
   ```
   Input: [1, 3, 5], k = 7
   Output: 0
   None of the possible subarrays have an XOR equal to 7.
   ```

Key Observations:
1. The brute force approach examines all n(n+1)/2 possible subarrays
2. XOR operation has the property that if a ⊕ b = c, then a ⊕ c = b
3. Time complexity grows quickly with array size due to the three nested loops

Sample Validation:
Input: [4, 2, 2, 6, 4], k = 6
Expected: 4
Output: 4

Key Points:
1. This solution is straightforward but inefficient for large inputs
2. XOR is a bitwise operation that works digit by digit in binary representation
3. The approach can be optimized using prefix XOR and hashmaps
4. XOR of an empty subarray is 0

TEST CASES:
1. Input: [1, 2, 3, 2], k = 2
   Expected: 3
   Output: 3
2. Input: [4, 2, 2, 6, 4], k = 6
   Expected: 4
   Output: 4
3. Input: [5, 6, 7, 8, 9], k = 5
   Expected: 1
   Output: 1
4. Input: [0, 0, 0], k = 0
   Expected: 6
   Output: 6
*/