package Array.Count_Subarray_Sum_equals_k;
/* 
Problem: Subarray Sum Equals K
Given an array of integers nums and an integer k, return the total number of subarrays whose sum equals to k.
A subarray is a contiguous non-empty sequence of elements within an array.

Intuition: If we carefully observe, we can notice that to get the sum of the current subarray we just need to add the current element(i.e. arr[j]) to the sum of the previous subarray i.e. arr[i….j-1].

- The core idea is to find all contiguous subarrays whose elements sum up to a target value k
- We use cumulative sum approach to efficiently calculate subarray sums
- Instead of calculating each subarray sum separately, we build upon previously calculated sums

Assume previous subarray = arr[i……j-1]
current subarray = arr[i…..j]
Sum of arr[i….j] = (sum of arr[i….j-1]) + arr[j]

This is how we can remove the third loop and while moving j pointer, we can calculate the sum.

## Basic Understanding
The problem aims to count the number of contiguous segments within an array whose elements add up exactly to k. For example, if k=6 and array is [3,1,2,4], we need to find all possible continuous sequences that sum to 6.

## Key Observations with Examples

### Observation 1: Cumulative Sum Pattern
- For any subarray ending at index j, its sum can be built from the previous sum
- If we have sum of arr[i..j-1], then sum of arr[i..j] = previous_sum + arr[j]
Example: 
```
Array: [3,1,2,4]
For i=0:
j=0: sum = 3
j=1: sum = 3 + 1 = 4
j=2: sum = 4 + 2 = 6 (Found a subarray!)
j=3: sum = 6 + 4 = 10
```

### Observation 2: Subarray Properties
- Subarrays must be contiguous
- Empty subarrays are not considered
- Order matters - elements must be consecutive

## Step-by-Step Example
Let's work through array [3,1,2,4] with k=6:

1. Start with i=0:
   ```
   [3] -> sum = 3
   [3,1] -> sum = 4
   [3,1,2] -> sum = 6 ✓ (Found first subarray)
   [3,1,2,4] -> sum = 10
   ```

2. Start with i=1:
   ```
   [1] -> sum = 1
   [1,2] -> sum = 3
   [1,2,4] -> sum = 7
   ```

3. Start with i=2:
   ```
   [2] -> sum = 2
   [2,4] -> sum = 6 ✓ (Found second subarray)
   ```

4. Start with i=3:
   ```
   [4] -> sum = 4
   ```

## Special Cases

### Case 1: All Elements Equal to K
Input: [6,6,6], k=6
- Each individual element forms a subarray
- Result: 3 subarrays

### Case 2: No Valid Subarrays
Input: [1,2,3], k=10
- No combination sums to k
- Result: 0 subarrays

*/
public class Apporach2 {
    
    // Approach 2: Better Approach with Cumulative Sum   
    public static int findAllSubarraysWithGivenSum(int[] arr, int k) {
        int n = arr.length;  // Get array length
        int cnt = 0;         // Initialize counter for valid subarrays
        
        // Outer loop: represents starting point of subarray
        for (int i = 0; i < n; i++) {
            int sum = 0;  // Reset sum for each new starting point
            
            // Inner loop: represents ending point of subarray
            for (int j = i; j < n; j++) {
                sum += arr[j];  // Add current element to running sum
                
                // Check if current subarray sums to k
                if (sum == k) {
                    cnt++;
                }
            }
        }
        return cnt;
    }

    // Main method for testing
    public static void main(String[] args) {
        // Test array and target sum
        int[] arr = {3, 1, 2, 4};
        int k = 6;
        
        // Calculate and display result
        int result = findAllSubarraysWithGivenSum(arr, k);
        System.out.println("The number of subarrays with sum " + k + " is: " + result);
        
        // Print example of found subarrays
        printValidSubarrays(arr, k);
    }
    
    // Helper method to print valid subarrays
    private static void printValidSubarrays(int[] arr, int k) {
        for (int i = 0; i < arr.length; i++) {
            int sum = 0;
            for (int j = i; j < arr.length; j++) {
                sum += arr[j];
                if (sum == k) {
                    System.out.print("Found subarray: [");
                    for (int m = i; m <= j; m++) {
                        System.out.print(arr[m] + (m < j ? "," : ""));
                    }
                    System.out.println("]");
                }
            }
        }
    }
}
/*
# Algorithm
1. Initialize counter variable to 0
2. For each possible starting index i:
   - Reset sum to 0
   - For each possible ending index j from i to end:
     - Add arr[j] to sum
     - If sum equals k, increment counter
3. Return final counter value

### Time Complexity Breakdown per Step
1. Outer loop: O(n)
2. Inner loop: O(n)
3. Sum calculation: O(1)
Total: O(n²)

### Space Complexity Breakdown
1. Auxiliary Space: O(1)
   - Only using constant extra space
2. Input Space: O(n)
   - Original array storage
Total: O(n)

# Advantages
1. Simple to implement
2. Works with both positive and negative numbers
3. No extra space required
4. Handles all edge cases naturally

# Limitations
1. O(n²) time complexity
2. Not optimal for very large arrays
3. Repeated calculations of sums

# Potential Improvements
1. Use HashMap to store prefix sums for O(n) time complexity
2. Use sliding window technique for positive numbers only
3. Parallel processing for very large arrays

# Step-by-Step Process with Dry Run

## Example Input: [3,1,2,4], k=6

### Detailed Execution Table
```
Step | i | j | sum | Action                    | Count
-----|---|---|-----|---------------------------|-------
1    | 0 | 0 | 3   | Add 3                    | 0
2    | 0 | 1 | 4   | Add 1                    | 0
3    | 0 | 2 | 6   | Add 2, Found subarray!   | 1
4    | 0 | 3 | 10  | Add 4                    | 1
5    | 1 | 1 | 1   | Add 1                    | 1
6    | 1 | 2 | 3   | Add 2                    | 1
7    | 1 | 3 | 7   | Add 4                    | 1
8    | 2 | 2 | 2   | Add 2                    | 1
9    | 2 | 3 | 6   | Add 4, Found subarray!   | 2
10   | 3 | 3 | 4   | Add 4                    | 2
```

### Test Cases
1. Input: [3,1,2,4], k=6
   Expected: 2
   Output: 2
   
2. Input: [1,1,1], k=2
   Expected: 2
   Output: 2
   
3. Input: [1], k=1
   Expected: 1
   Output: 1

Key Points:
1. Always reset sum when starting new subarray
2. Check sum after each addition
3. Consider all possible subarray lengths
4. Handle edge cases properly
*/