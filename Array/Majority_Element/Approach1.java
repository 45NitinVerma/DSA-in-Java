/* 
Problem: Find the majority element in an array
A majority element is an element that appears more than n/2 times in an array of size n.

Intuition: Use a brute force approach to count the frequency of each element 
and check if any element appears more than n/2 times.
*/

package Array.Majority_Element;
public class Approach1 {
    // Approach 1: Brute Force with Nested Loops
    public static int majorityElement(int[] v) {
        // Get size of input array
        int n = v.length;

        // Iterate through each element
        for (int i = 0; i < n; i++) {
            int currentElement = v[i];
            int count = 0;
            
            // Count frequency of current element
            for (int j = 0; j < n; j++) {
                if (v[j] == currentElement) {
                    count++;
                }
            }
            
            // Check if current element is majority element
            if (count > n/2) {
                return currentElement;
            }
        }
        
        // No majority element found
        return -1;
    }

    public static void main(String args[]) {
        int[] arr = {2, 2, 1, 1, 1, 2, 2};
        int result = majorityElement(arr);
        System.out.println("The majority element is: " + result);
    }
}

/* 
ALGORITHM:
1. Get array length n
2. For each element in array:
   - Count its frequency in entire array
   - If frequency > n/2, return the element
3. If no majority element found, return -1

Complexity Analysis:
TIME COMPLEXITY: O(n²)
- Two nested loops, each running n times
- Outer loop: O(n)
- Inner loop: O(n)
- Total: O(n) * O(n) = O(n²)

SPACE COMPLEXITY:
1. Auxiliary Space: O(1)
   - Only using constant extra space for variables
2. Input Space: O(n)
   - Original array of size n

Advantages:
1. Simple to implement and understand
2. No extra space required
3. Works with any input array without modification

Limitations:
1. Very inefficient for large arrays due to O(n²) time complexity
2. Redundant counting of same elements multiple times
3. Not suitable for real-time applications with large datasets

Potential Improvements:
1. Use Moore's Voting Algorithm for O(n) time complexity
2. Use HashMap to store frequency counts (O(n) time, O(n) space)
3. Sort array first and find middle element (O(nlogn) time)
4. Use divide and conquer approach
5. Add input validation for edge cases (null array, empty array)

DETAILED EXECUTION FLOW for input array [2, 2, 1, 1, 1, 2, 2]
-----------------------------------------------------------------------------------------------------------------------------------------
Step | i | j | Current Element | Count | Array Position being Checked | Is Match? | Running Count | Majority Check | Result/Action
-----------------------------------------------------------------------------------------------------------------------------------------
1    | 0 | 0 | 2              | 0     | arr[0] = 2                  | Yes       | 1            | 1 ≤ 3.5       | Continue counting
2    | 0 | 1 | 2              | 1     | arr[1] = 2                  | Yes       | 2            | 2 ≤ 3.5       | Continue counting
3    | 0 | 2 | 2              | 2     | arr[2] = 1                  | No        | 2            | 2 ≤ 3.5       | Continue counting
4    | 0 | 3 | 2              | 2     | arr[3] = 1                  | No        | 2            | 2 ≤ 3.5       | Continue counting
5    | 0 | 4 | 2              | 2     | arr[4] = 1                  | No        | 2            | 2 ≤ 3.5       | Continue counting
6    | 0 | 5 | 2              | 2     | arr[5] = 2                  | Yes       | 3            | 3 ≤ 3.5       | Continue counting
7    | 0 | 6 | 2              | 3     | arr[6] = 2                  | Yes       | 4            | 4 > 3.5       | Found majority!
-----------------------------------------------------------------------------------------------------------------------------------------

Alternative Case: [1, 1, 1, 2, 2]
-----------------------------------------------------------------------------------------------------------------------------------------
Step | i | j | Current Element | Count | Array Position being Checked | Is Match? | Running Count | Majority Check | Result/Action
-----------------------------------------------------------------------------------------------------------------------------------------
1    | 0 | 0 | 1              | 0     | arr[0] = 1                  | Yes       | 1            | 1 ≤ 2.5       | Continue counting
2    | 0 | 1 | 1              | 1     | arr[1] = 1                  | Yes       | 2            | 2 ≤ 2.5       | Continue counting
3    | 0 | 2 | 1              | 2     | arr[2] = 1                  | Yes       | 3            | 3 > 2.5       | Found majority!
-----------------------------------------------------------------------------------------------------------------------------------------

Edge Case: Empty Array []
-----------------------------------------------------------------------------------------------------------------------------------------
Step | i | j | Current Element | Count | Array Position being Checked | Is Match? | Running Count | Majority Check | Result/Action
-----------------------------------------------------------------------------------------------------------------------------------------
N/A  | - | - | -              | -     | -                           | -         | -            | -             | Return -1
-----------------------------------------------------------------------------------------------------------------------------------------

Edge Case: Single Element [5]
-----------------------------------------------------------------------------------------------------------------------------------------
Step | i | j | Current Element | Count | Array Position being Checked | Is Match? | Running Count | Majority Check | Result/Action
-----------------------------------------------------------------------------------------------------------------------------------------
1    | 0 | 0 | 5              | 0     | arr[0] = 5                   | Yes       | 1            | 1 > 0.5       | Found majority!
-----------------------------------------------------------------------------------------------------------------------------------------

EXPLANATION OF KEY STEPS:

1. Initial Setup:
   - Array length (n) is calculated
   - Majority threshold is set to n/2 (3.5 for array of length 7)

2. Outer Loop (i):
   - Selects each element as potential majority element
   - Resets count for each new element

3. Inner Loop (j):
   - Counts occurrences of current element
   - Compares each array position with current element

4. Counting Process:
   - Increments count when match found
   - Keeps running total for current element

5. Majority Check:
   - After inner loop completes, compares count with n/2
   - Returns element if count exceeds threshold

6. Termination:
   - Returns -1 if no majority element found
   - Returns immediately if majority element found

KEY POINTS IN EXECUTION:
1. First majority check occurs after completing inner loop
2. Algorithm continues even after finding majority (could be optimized)
3. Each element is compared with every other element (including itself)
4. Count resets for each new outer loop iteration

SPECIAL CONDITIONS:
1. Empty Array: Immediate return of -1
2. Single Element: Always majority
3. Even Length Array: Threshold is strictly > n/2
4. Odd Length Array: Threshold is strictly > n/2

This detailed flow helps visualize how the algorithm:
- Processes each element
- Handles different array sizes
- Deals with edge cases
- Identifies the majority element

Key Observations:
1. Algorithm always makes n² comparisons even when majority element is found early
2. Same elements are counted multiple times unnecessarily
3. No early termination even if majority element is found

Sample Validation:
Input: [2, 2, 1, 1, 1, 2, 2]
Expected: 2
Output: 2

Key Points:
1. Brute force solution demonstrates the concept but is inefficient
2. Better algorithms exist for practical applications
3. Trade-off between time complexity and space complexity in improved solutions

TEST CASES:
1. Input: [2, 2, 1, 1, 1, 2, 2]
   Expected: 2
   Output: 2
2. Input: [1]
   Expected: 1
   Output: 1
3. Input: [1, 1, 1]
   Expected: 1
   Output: 1
4. Input: [1, 2]
   Expected: -1
   Output: -1
*/