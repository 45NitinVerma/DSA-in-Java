/* 
Problem: Sort an array containing only 0s, 1s, and 2s without using any sorting algorithm.
Also known as the Dutch National Flag problem.

Intuition: Use three pointers (low, mid, high) to partition the array into three regions:
- [0 to low-1]: contains 0s
- [low to mid-1]: contains 1s
- [mid to high]: unsorted region
- [high+1 to n-1]: contains 2s
*/
package Array.Sort_0_1_2;
import java.util.ArrayList;
import java.util.Arrays;

public class Approach3 {
    // Approach 3: Dutch National Flag Algorithm (Three Pointer Approach)
    public static void sortArray(ArrayList<Integer> arr, int n) {
        // Initialize three pointers
        int low = 0;      // points to the rightmost boundary of 0s
        int mid = 0;      // scanning pointer
        int high = n - 1; // points to the leftmost boundary of 2s

        while (mid <= high) {
            if (arr.get(mid) == 0) {
                // If the element is 0, swap with the element at 'low'
                int temp = arr.get(low);
                arr.set(low, arr.get(mid));
                arr.set(mid, temp);

                // Increment both 'low' and 'mid'
                low++;
                mid++;
            } else if (arr.get(mid) == 1) {
                // If the element is 1, move 'mid' forward
                mid++;
            } else {
                // If the element is 2, swap with the element at 'high'
                int temp = arr.get(mid);
                arr.set(mid, arr.get(high));
                arr.set(high, temp);

                // Decrement 'high'
                high--;
            }
        }
    }
    
    public static void main(String args[]) {
        int n = 6;
        ArrayList<Integer> arr = new ArrayList<>(Arrays.asList(0, 2, 1, 2, 0, 1));
        
        System.out.println("Before sorting: " + arr);
        sortArray(arr, n);
        System.out.println("After sorting: " + arr);
    }
}

/* 
ALGORITHM:
1. Initialize three pointers: low = 0, mid = 0, high = n-1
2. While mid <= high:
   - If element is 0: swap with low pointer, increment both low and mid
   - If element is 1: increment mid
   - If element is 2: swap with high pointer, decrement high

Complexity Analysis:
TIME COMPLEXITY: O(n)
- Single pass through the array with three pointers
- Each element is processed exactly once

SPACE COMPLEXITY:
1. Auxiliary Space: O(1)
   - Only using three pointers regardless of input size
2. Input Space: O(n)
   - Storage for the input ArrayList

Advantages:
1. Single pass solution - very efficient
2. In-place sorting - no extra space required
3. Stable for 1s (maintains relative order)
4. Simple to implement

Limitations:
1. Only works for arrays containing exactly three distinct values (0, 1, 2)
2. Not stable for 0s and 2s (relative order might change)
3. Requires multiple swap operations

Potential Improvements:
1. Add input validation to ensure array only contains 0s, 1s, and 2s
2. Implement error handling for invalid inputs
3. Make it generic for any three values, not just 0,1,2
4. Add parallel processing for very large arrays
5. Optimize number of swaps by handling edge cases
6. Add ability to sort in descending order

# Detailed Execution Trace for Array [0, 2, 1, 2, 0, 1]

## Initial Setup:
- Array size (n) = 6
- Initial pointers: low = 0, mid = 0, high = 5

```
Step-by-Step Execution Table
------------------------------------------------------------------------------------------------------------------
Step | Array State     | low | mid | high | Current Element | Action               | Explanation
------------------------------------------------------------------------------------------------------------------
0    | [0,2,1,2,0,1]   | 0   | 0   | 5    | arr[0] = 0     | Initial state         | Starting configuration
------------------------------------------------------------------------------------------------------------------
1    | [0,2,1,2,0,1]   | 0   | 0   | 5    | arr[0] = 0     | Swap(low,mid)         | Found 0: swap with low
                                                              low++, mid++         | Both pointers increment
------------------------------------------------------------------------------------------------------------------
2    | [0,2,1,2,0,1]   | 1   | 1   | 5    | arr[1] = 2     | Swap(mid,high)        | Found 2: swap with high
                                                              high--               | high decrements
------------------------------------------------------------------------------------------------------------------
3    | [0,1,1,2,0,2]   | 1   | 1   | 4    | arr[1] = 1     | mid++                 | Found 1: just move ahead
------------------------------------------------------------------------------------------------------------------
4    | [0,1,1,2,0,2]   | 1   | 2   | 4    | arr[2] = 1     | mid++                 | Found 1: just move ahead
------------------------------------------------------------------------------------------------------------------
5    | [0,1,1,2,0,2]   | 1   | 3   | 4    | arr[3] = 2     | Swap(mid,high)        | Found 2: swap with high
                                                              high--               | high decrements
------------------------------------------------------------------------------------------------------------------
6    | [0,1,1,0,2,2]   | 1   | 3   | 3    | arr[3] = 0     | Swap(low,mid)         | Found 0: swap with low
                                                              low++, mid++         | Both pointers increment
------------------------------------------------------------------------------------------------------------------
7    | [0,0,1,1,2,2]   | 2   | 4   | 3    | -              | Exit while loop       | mid > high, sorting complete
------------------------------------------------------------------------------------------------------------------
```

## Detailed Breakdown of Each Step:

1. **Initial State**:
   - Array: [0,2,1,2,0,1]
   - Action: Check arr[0] = 0
   - Result: Will swap with itself (low=mid=0)

2. **After First 0**:
   - Array: [0,2,1,2,0,1]
   - Pointers updated: low=1, mid=1
   - Next element to process: 2

3. **Processing First 2**:
   - Array: [0,1,1,2,0,2]
   - Swapped with high element
   - high decrements to 4

4. **Processing First 1**:
   - Array: [0,1,1,2,0,2]
   - Simply increment mid
   - No swaps needed

5. **Processing Second 1**:
   - Array: [0,1,1,2,0,2]
   - Simply increment mid
   - No swaps needed

6. **Processing Second 2**:
   - Array: [0,1,1,0,2,2]
   - Swapped with high element
   - high decrements to 3

7. **Processing Last 0**:
   - Array: [0,0,1,1,2,2]
   - Swapped with low element
   - Final sorted array achieved

## Key Observations from Execution:

1. **Pattern in Swaps**:
   - 0s are always swapped towards the front
   - 2s are always swapped towards the end
   - 1s don't require any swaps

2. **Pointer Movements**:
   - low only moves after handling 0s
   - high only moves after handling 2s
   - mid moves after handling 0s and 1s

3. **Termination Condition**:
   - Algorithm stops when mid passes high
   - This ensures all elements are properly segregated

4. **Number of Operations**:
   - Total swaps performed: 4
   - Total comparisons: 7
   - Total pointer movements: 11

## Final Result Verification:
- Input:  [0,2,1,2,0,1]
- Output: [0,0,1,1,2,2]
- All 0s are at the beginning
- All 1s are in the middle
- All 2s are at the end

This detailed trace shows how the Dutch National Flag algorithm efficiently sorts the array in a single pass while maintaining the relative order of elements within each group (0s, 1s, and 2s).

Key Observations:
1. The algorithm maintains three regions of sorted elements
2. Elements between mid and high are yet to be examined
3. After completion, all 0s are at start, 2s at end, and 1s in middle

Key Points:
1. Algorithm is based on the Dutch National Flag problem by Edsger Dijkstra
2. Efficient for small and large arrays alike
3. Very useful in applications where three-way partitioning is needed

TEST CASES:
1. Input: [2, 2, 1, 1, 0, 0]
   Expected: [0, 0, 1, 1, 2, 2]
   
2. Input: [0, 0, 0]
   Expected: [0, 0, 0]
   
3. Input: [2, 1, 0]
   Expected: [0, 1, 2]
   
4. Input: []
   Expected: []
*/