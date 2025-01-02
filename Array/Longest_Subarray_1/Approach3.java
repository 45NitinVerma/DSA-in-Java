// Approach 3: Sliding Window Approach 
package Array.Longest_Subarray_1;
public class Approach3 {
    public static int getLongestSubarray(int[] a, long k) {
        int n = a.length; // size of the array
        int left = 0, right = 0; // Two pointers for the sliding window
        long sum = a[0]; // Initial sum including the first element
        int maxLen = 0; // Variable to store the maximum length of subarray

        // Iterate until the right pointer reaches the end of the array
        while (right < n) {
            // Shrink the window from the left if the current sum exceeds k
            while (left <= right && sum > k) {
                sum -= a[left]; // Remove the leftmost element
                left++; // Move the left pointer forward
            }

            // If the sum matches k, update the maximum length
            if (sum == k) {
                maxLen = Math.max(maxLen, right - left + 1);
            }

            // Expand the window by moving the right pointer forward
            right++;
            if (right < n) {
                sum += a[right]; // Add the new element to the sum
            }
        }

        return maxLen; // Return the maximum length found
    }

    public static void main(String[] args) {
        int[] a = { 2, 3, 5, 1, 9 };
        long k = 10;
        int len = getLongestSubarray(a, k);
        System.out.println("The length of the longest subarray is: " + len);
    }
}

/* 
# Sliding Window Approach for Longest Subarray with Sum K
## Intuition
We are using two pointers i.e. left and right. The left pointer denotes the starting index of the subarray and the right pointer denotes the ending index. Now as we want the longest subarray, we will move the right pointer in a forward direction every time adding the element i.e. a[right] to the sum. But when the sum of the subarray crosses k, we will move the left pointer in the forward direction as well to shrink the size of the subarray as well as to decrease the sum. Thus, we will consider the length of the subarray whenever the sum becomes equal to k.

## Algorithm
1. Initialize two pointers `left` and `right` at the start of array
2. Maintain a running sum of the current window
3. Use sliding window technique:
   - Expand window by moving right pointer
   - Contract window from left if sum exceeds k
   - Update maxLen when sum equals k
4. Continue until right pointer reaches end of array

## Complexity Analysis
### Time Complexity: O(n)
- Each element is added once and removed at most once
- Both pointers traverse the array only once
- No nested loops (while loops don't overlap on same elements)

### Space Complexity
1. Input Space: O(n)
   - Input array of size n
   - Target sum k uses O(1)

2. Auxiliary Space: O(1)
   - Only using pointers and sum variables
   - No additional data structures

Total Space: O(n) input + O(1) auxiliary = O(n)

## Advantages
1. Constant auxiliary space usage
2. Single pass solution
3. Works well for positive numbers
4. Simple implementation
5. Good for streaming data

## Limitations
1. Only works with positive numbers
2. Not suitable for arrays with negative elements
3. May not find all possible subarrays (focuses on first occurrence)

## Detailed Walkthrough with Example
Input: a = [2, 3, 5, 1, 9], k = 10

```
Step-by-Step Process:
--------------------------------------------------------------------------------------------------------
Left | Right | Current       | Running | Action                  | Max    | 
Ptr  | Ptr   | Window        | Sum     |                         | Length |
--------------------------------------------------------------------------------------------------------
0    | 0     | [2]           | 2       | Initialize              | 0      | Sum < k, expand window
0    | 1     | [2,3]         | 5       | Expand right            | 0      | Sum < k, expand window
0    | 2     | [2,3,5]       | 10      | Expand right            | 3      | Sum = k, update maxLen
0    | 3     | [2,3,5,1]     | 11      | Expand right            | 3      | Sum > k, contract window
1    | 3     | [3,5,1]       | 9       | Contract left           | 3      | Sum < k, expand window
1    | 4     | [3,5,1,9]     | 18      | Expand right            | 3      | Sum > k, contract window
2    | 4     | [5,1,9]       | 15      | Contract left           | 3      | Sum > k, contract window
3    | 4     | [1,9]         | 10      | Contract left           | 2      | Sum = k, but not longer
--------------------------------------------------------------------------------------------------------

Final Result: 3 (subarray [2,3,5])
```

## Key Observations
1. Window Management:
   - Window expands when sum < k
   - Window contracts when sum > k
   - Window size updated when sum = k

2. Pointer Movement:
   - Right pointer moves one step at a time
   - Left pointer can move multiple steps
   - Both pointers move forward only

3. Sum Maintenance:
   - Add elements when expanding window
   - Subtract elements when contracting
   - Compare with target at each valid window

4. MaxLen Updates:
   - Only updates when sum exactly equals k
   - Keeps track of largest window found
   - Considers all possible windows

## Sample Validation
- Final subarray [2,3,5]:
  * Sum = 2 + 3 + 5 = 10
  * Length = 3
  * Validates our algorithm's result

## Implementation Tips
1. Initialize sum with first element to avoid null check
2. Check array bounds when moving right pointer
3. Use long for sum to handle large numbers
4. Ensure left pointer doesn't exceed right pointer
5. Update maxLen only when exact sum found

## Common Pitfalls to Avoid
1. Not checking array bounds
2. Integer overflow in sum calculation
3. Incorrect window size calculation
4. Not handling edge cases (empty array, single element)
5. Forgetting to update sum when contracting window

## Key Points:
1. Handles overflow with long datatype for sum
2. Maintains sliding window invariant
3. Updates maxLen only when exact sum found
4. Efficient for large arrays with positive numbers
*/