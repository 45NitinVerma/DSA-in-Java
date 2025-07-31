package Binary_Search.First_And_Last_Position;
import java.util.ArrayList;
import java.util.Arrays;

/* 
# First and Last Position of Element in Sorted Array
## Problem: Given an array of integers nums sorted in non-decreasing order, find the starting and ending position of a given target value.
If target is not found in the array, return [-1, -1].
You must write an algorithm with O(log n) runtime complexity.

## Intution:  

## Key Observations with Examples

### Observation 1: Binary Search Applicability
[Binary search is ideal for this problem because the array is sorted]
* Standard binary search finds any occurrence, but we need first and last
* We can modify binary search to find specific occurrences
* Time complexity remains O(log n)

### Observation 2: First Occurrence Logic
[To find the first occurrence, we need to continue searching leftward even after finding a match]
Example: For array [2, 4, 6, 8, 8, 8, 11, 13] and target 8:
```
First occurrence search:
Start: low=0, high=7
mid=3, arr[3]=8 (equals target) → mark first=3, search left (high=2)
mid=1, arr[1]=4 (less than target) → search right (low=2)
mid=2, arr[2]=6 (less than target) → search right (low=3)
low > high, search ends → first=3
```

### Observation 3: Last Occurrence Logic
[To find the last occurrence, we need to continue searching rightward even after finding a match]
Example: For array [2, 4, 6, 8, 8, 8, 11, 13] and target 8:
```
Last occurrence search:
Start: low=0, high=7
mid=3, arr[3]=8 (equals target) → mark last=3, search right (low=4)
mid=5, arr[5]=8 (equals target) → mark last=5, search right (low=6)
mid=6, arr[6]=11 (greater than target) → search left (high=5)
low > high, search ends → last=5
```

### Observation 4: Handling Non-Existent Targets
[If the target doesn't exist, both first and last occurrences would be -1]
* We can optimize by checking if first occurrence is -1 before searching for last occurrence
* If first occurrence is -1, we can directly return [-1, -1]

## Step-by-Step Example
Let's work through the array [2, 4, 6, 8, 8, 8, 11, 13] with target 8:

1. First Occurrence Search:
   ```
   Initial: low=0, high=7, first=-1
   
   Iteration 1:
   mid = (0+7)/2 = 3
   arr[3] = 8 == target
   first = 3
   high = 3-1 = 2 (search left)
   
   Iteration 2:
   mid = (0+2)/2 = 1
   arr[1] = 4 < target
   low = 1+1 = 2 (search right)
   
   Iteration 3:
   mid = (2+2)/2 = 2
   arr[2] = 6 < target
   low = 2+1 = 3 (search right)
   
   Iteration 4:
   low = 3, high = 2
   low > high, so exit loop
   
   Result: first = 3
   ```

2. Last Occurrence Search:
   ```
   Initial: low=0, high=7, last=-1
   
   Iteration 1:
   mid = (0+7)/2 = 3
   arr[3] = 8 == target
   last = 3
   low = 3+1 = 4 (search right)
   
   Iteration 2:
   mid = (4+7)/2 = 5
   arr[5] = 8 == target
   last = 5
   low = 5+1 = 6 (search right)
   
   Iteration 3:
   mid = (6+7)/2 = 6
   arr[6] = 11 > target
   high = 6-1 = 5 (search left)
   
   Iteration 4:
   low = 6, high = 5
   low > high, so exit loop
   
   Result: last = 5
   ```

3. Return the results:
   ```
   first = 3, last = 5
   return [3, 5]
   ```

## Special Cases

### Case 1: Target Not Found
Input: [2, 4, 6, 8, 10, 12, 14], target = 7
- Both first and last occurrence functions return -1
- Result: [-1, -1]

### Case 2: Single Occurrence
Input: [2, 4, 6, 8, 10, 12, 14], target = 8
- First occurrence function returns 3
- Last occurrence function returns 3
- Result: [3, 3]

### Case 3: Empty Array
Input: [], target = 8
- Both functions return -1 (array size is 0)
- Result: [-1, -1]
*/
public class Approach3 {
    // Approach 3: Using scratch Binary Search
    public static int firstOccurrence(ArrayList<Integer> arr, int n, int k) {
        int low = 0, high = n - 1;
        int first = -1; // Initialize result to -1 (not found)

        while (low <= high) {
            int mid = (low + high) / 2;
            
            // If target found at mid, it's a potential answer
            if (arr.get(mid) == k) {
                first = mid; // Mark current position
                high = mid - 1; // But continue searching to the left for earlier occurrences
            } 
            // If middle value is less than target, search in right half
            else if (arr.get(mid) < k) {
                low = mid + 1;
            } 
            // If middle value is greater than target, search in left half
            else {
                high = mid - 1;
            }
        }
        return first; // Return the leftmost occurrence (or -1 if not found)
    }

    /**
     * Find the last occurrence of target value in sorted array
     */
    public static int lastOccurrence(ArrayList<Integer> arr, int n, int k) {
        int low = 0, high = n - 1;
        int last = -1; // Initialize result to -1 (not found)

        while (low <= high) {
            int mid = (low + high) / 2;
            
            // If target found at mid, it's a potential answer
            if (arr.get(mid) == k) {
                last = mid; // Mark current position
                low = mid + 1; // But continue searching to the right for later occurrences
            } 
            // If middle value is less than target, search in right half
            else if (arr.get(mid) < k) {
                low = mid + 1;
            } 
            // If middle value is greater than target, search in left half
            else {
                high = mid - 1;
            }
        }
        return last; // Return the rightmost occurrence (or -1 if not found)
    }

    /**
     * Find both first and last positions of target in sorted array
     * Returns [-1, -1] if target not found
     */
    public static int[] firstAndLastPosition(ArrayList<Integer> arr, int n, int k) {
        // First find the first occurrence
        int first = firstOccurrence(arr, n, k);
        
        // If target not found, return [-1, -1]
        if (first == -1) return new int[] { -1, -1};
        
        // If target found, find last occurrence
        int last = lastOccurrence(arr, n, k);
        
        // Return the range [first, last]
        return new int[] {first, last};
    }

    // Main method for testing
    public static void main(String[] args) {
        // Sample input array
        ArrayList<Integer> arr = new ArrayList<>(Arrays.asList(new Integer[] {2, 4, 6, 8, 8, 8, 11, 13}));
        int n = 8, k = 8; // n is array size, k is target value
        
        // Call the function to find first and last positions
        int[] ans = firstAndLastPosition(arr, n, k);
        
        // Display the result
        System.out.println("The first and last positions are: " + ans[0] + " " + ans[1]);
    }
}

/*
# Algorithm
## firstOccurrence(): We will declare the 2 pointers and a ‘first’ variable initialized to -1(as If we don’t find any index, we will return -1).
1. Place the 2 pointers i.e. low and high: Initially, we will place the pointers like this: low will point to the first index, and high will point to the last index.
2. Calculate the ‘mid’: Now, we will calculate the value of mid using the following formula:
mid = (low+high) // 2 ( ‘//’ refers to integer division)
3. Compare arr[mid] with k: With comparing arr[mid] to k, we can observe 3 different cases:
 - Case 1 - If arr[mid] == k: This condition means that the index mid may be an answer. So, we will update the ‘first’ variable with mid and search in the left half if there is any smaller index that satisfies the same condition as we want the ‘first’ variable to be as minimum as possible.
 - Case 2 - If arr[mid] < k: In this case, mid cannot be our answer and we need to find some bigger element. So, we will eliminate the left half and search in the right half for the answer.
 - Case 3: If arr[mid] > k: In this case, mid cannot be our answer and we need to find some smaller element. So, we will eliminate the right half and search in the left half for the answer.
The above process will continue until the pointer low crosses high.

Note: If the firstOccurrence() function returns a value of -1, it indicates that the target element is not found in the array. In such a scenario, there is no need to proceed with the lastOccurrence() function. We can directly conclude that the element is not present and return -1 from this step.

## lastOccurrence(): We will declare the 2 pointers and a ‘last’ variable initialized to -1(as If we don’t find any index, we will return -1).
1. Place the 2 pointers i.e. low and high: Initially, we will place the pointers like this: low will point to the first index and high will point to the last index.
2. Calculate the ‘mid’: Now, we will calculate the value of mid using the following formula:
mid = (low+high) // 2 ( ‘//’ refers to integer division)
3. Compare arr[mid] with k: With comparing arr[mid] to k, we can observe 3 different cases:
 - Case 1 - If arr[mid] == k: This condition means that the index mid may be an answer. So, we will update the ‘last’ variable with mid and search in the right half if there is any larger index that satisfies the same condition as we want the ‘last’ variable to be as maximum as possible.
 - Case 2 - If arr[mid] < k: In this case, mid cannot be our answer and we need to find some bigger element. So, we will eliminate the left half and search in the right half for the answer.
 - Case 3: If arr[mid] > k: In this case, mid cannot be our answer and we need to find some smaller element. So, we will eliminate the right half and search in the left half for the answer.

### Time Complexity Breakdown per Step
1. First occurrence search: O(log n) - Binary search
2. Last occurrence search: O(log n) - Binary search

Total: O(log n) - Both searches are independent and have the same complexity

### Space Complexity Breakdown
1. Auxiliary Space: O(1) - Only using a constant number of variables
   - We use fixed number of variables regardless of input size
2. Input Space: O(n) - The input array
   - The size of input array is proportional to n

Total: O(1) auxiliary space complexity (excluding input)

# Advantages
1. Optimal time complexity of O(log n) using binary search
2. Clean separation of concerns with separate functions for first and last occurrences
3. Early termination if target is not found in the array
4. Works with any sorted array, regardless of the number of occurrences of the target

# Limitations
1. Only works on sorted arrays
2. Requires two binary searches, which could be combined into one with more complex logic
3. Not optimized for arrays with many duplicate elements
4. The ArrayList implementation adds slight overhead compared to using a primitive array

# Potential Improvements
1. Combine the two binary searches into a single pass with more complex logic
2. Use primitive arrays instead of ArrayList for better performance
3. Add bounds checking to handle empty arrays and invalid inputs
4. Use bit manipulation for computing the middle index to avoid potential overflow
5. Implement an iterative approach to avoid function call overhead
# First and Last Position of Element in Sorted Array
[Finding the first and last occurrence of a target value in a sorted array]

# Step-by-Step Process with Dry Run

## Example Input: [2, 4, 6, 8, 8, 8, 11, 13], target = 8

### Detailed Execution Table
```
Step | Input Array          | Action                    | Variables                | Current Search Space    | Output                  | Explanation
-----|----------------------|---------------------------|--------------------------|-------------------------|-------------------------|-------------
1    | [2,4,6,8,8,8,11,13]  | Call firstOccurrence      | n=8, k=8                 | Full array              | -                       | Start searching for first occurrence
2    | [2,4,6,8,8,8,11,13]  | Initialize                | low=0, high=7, first=-1  | [0,1,2,3,4,5,6,7]       | -                       | Set up binary search variables
3    | [2,4,6,8,8,8,11,13]  | Calculate mid             | mid=(0+7)/2=3            | [0,1,2,3,4,5,6,7]       | -                       | Find middle index
4    | [2,4,6,8,8,8,11,13]  | Check arr[mid]==k         | arr[3]=8==8              | [0,1,2,3,4,5,6,7]       | -                       | Found target at index 3
5    | [2,4,6,8,8,8,11,13]  | Update first & high       | first=3, high=2          | [0,1,2]                 | -                       | Save position & search left for earlier occurrence
6    | [2,4,6,8,8,8,11,13]  | Calculate mid             | mid=(0+2)/2=1            | [0,1,2]                 | -                       | Find middle index in left subarray
7    | [2,4,6,8,8,8,11,13]  | Check arr[mid]<k          | arr[1]=4<8               | [0,1,2]                 | -                       | Middle value is less than target
8    | [2,4,6,8,8,8,11,13]  | Update low                | low=2                    | [2]                     | -                       | Search right portion of left subarray
9    | [2,4,6,8,8,8,11,13]  | Calculate mid             | mid=(2+2)/2=2            | [2]                     | -                       | Find middle index
10   | [2,4,6,8,8,8,11,13]  | Check arr[mid]<k          | arr[2]=6<8               | [2]                     | -                       | Middle value is less than target
11   | [2,4,6,8,8,8,11,13]  | Update low                | low=3                    | []                      | -                       | Move low beyond high, search space empty
12   | [2,4,6,8,8,8,11,13]  | Check low<=high           | 3>2, exit loop           | []                      | -                       | Binary search complete
13   | [2,4,6,8,8,8,11,13]  | Return first              | first=3                  | -                       | 3                       | First occurrence found at index 3
14   | [2,4,6,8,8,8,11,13]  | Call lastOccurrence       | n=8, k=8                 | Full array              | -                       | Start searching for last occurrence
15   | [2,4,6,8,8,8,11,13]  | Initialize                | low=0, high=7, last=-1   | [0,1,2,3,4,5,6,7]       | -                       | Set up binary search variables
16   | [2,4,6,8,8,8,11,13]  | Calculate mid             | mid=(0+7)/2=3            | [0,1,2,3,4,5,6,7]       | -                       | Find middle index
17   | [2,4,6,8,8,8,11,13]  | Check arr[mid]==k         | arr[3]=8==8              | [0,1,2,3,4,5,6,7]       | -                       | Found target at index 3
18   | [2,4,6,8,8,8,11,13]  | Update last & low         | last=3, low=4            | [4,5,6,7]               | -                       | Save position & search right for later occurrence
19   | [2,4,6,8,8,8,11,13]  | Calculate mid             | mid=(4+7)/2=5            | [4,5,6,7]               | -                       | Find middle index in right subarray
20   | [2,4,6,8,8,8,11,13]  | Check arr[mid]==k         | arr[5]=8==8              | [4,5,6,7]               | -                       | Found target at index 5
21   | [2,4,6,8,8,8,11,13]  | Update last & low         | last=5, low=6            | [6,7]                   | -                       | Save position & continue searching right
22   | [2,4,6,8,8,8,11,13]  | Calculate mid             | mid=(6+7)/2=6            | [6,7]                   | -                       | Find middle index
23   | [2,4,6,8,8,8,11,13]  | Check arr[mid]>k          | arr[6]=11>8              | [6,7]                   | -                       | Middle value is greater than target
24   | [2,4,6,8,8,8,11,13]  | Update high               | high=5                   | []                      | -                       | Move high below low, search space empty
25   | [2,4,6,8,8,8,11,13]  | Check low<=high           | 6>5, exit loop           | []                      | -                       | Binary search complete
26   | [2,4,6,8,8,8,11,13]  | Return last               | last=5                   | -                       | 5                       | Last occurrence found at index 5
27   | [2,4,6,8,8,8,11,13]  | Combine results           | first=3, last=5          | -                       | [3,5]                   | Return first and last positions
```

### Step-by-Step Explanation

Let's break down each step of the algorithm's execution for finding the first and last position of 8 in the array [2, 4, 6, 8, 8, 8, 11, 13]:

1. **First Occurrence Search Initialization**
   - We begin by initializing our binary search parameters for finding the first occurrence
   - Set low=0 (start index), high=7 (end index), first=-1 (result not found yet)
   - The entire array [2, 4, 6, 8, 8, 8, 11, 13] is our initial search space
   - Target value = 8

2. **First Binary Search - First Iteration**
   - Calculate mid index: mid = (0+7)/2 = 3
   - Compare array value at mid with target: arr[3] = 8, which equals our target 8
   - Since we found our target, we update first = 3 to mark this position
   - Since we're looking for the FIRST occurrence, we need to check if there are earlier occurrences
   - We update high = mid - 1 = 2 to search the left portion of the array
   - Our search space is now [2, 4, 6]
   ```
   Array: [2, 4, 6, 8, 8, 8, 11, 13]
           0  1  2  3  4  5  6   7
                    ^
                   mid
           ^        ^
          low      high
   ```

3. **First Binary Search - Second Iteration**
   - Calculate mid index: mid = (0+2)/2 = 1
   - Compare array value at mid with target: arr[1] = 4, which is less than our target 8
   - Since the value is less than the target, we need to search in the right half
   - Update low = mid + 1 = 2
   - Our search space is now just [6]
   ```
   Array: [2, 4, 6, 8, 8, 8, 11, 13]
           0  1  2  3  4  5  6   7
              ^
             mid
           ^     ^
          low   high
   ```

4. **First Binary Search - Third Iteration**
   - Calculate mid index: mid = (2+2)/2 = 2
   - Compare array value at mid with target: arr[2] = 6, which is less than our target 8
   - Since the value is less than the target, we search in the right half
   - Update low = mid + 1 = 3
   - Low is now greater than high (3 > 2), so our search space is empty
   ```
   Array: [2, 4, 6, 8, 8, 8, 11, 13]
           0  1  2  3  4  5  6   7
                 ^
                mid
                 ^
               low,high
   ```

5. **First Binary Search - Termination**
   - Since low > high, we exit the loop
   - The value of first = 3 remains our answer for the first occurrence
   - Return first = 3

6. **Last Occurrence Search Initialization**
   - We begin the second search to find the last occurrence
   - Set low=0 (start index), high=7 (end index), last=-1 (result not found yet)
   - The entire array [2, 4, 6, 8, 8, 8, 11, 13] is our initial search space

7. **Last Binary Search - First Iteration**
   - Calculate mid index: mid = (0+7)/2 = 3
   - Compare array value at mid with target: arr[3] = 8, which equals our target 8
   - Since we found our target, we update last = 3 to mark this position
   - Since we're looking for the LAST occurrence, we need to check if there are later occurrences
   - We update low = mid + 1 = 4 to search the right portion of the array
   - Our search space is now [8, 8, 11, 13]
   ```
   Array: [2, 4, 6, 8, 8, 8, 11, 13]
           0  1  2  3  4  5  6   7
                    ^
                   mid
                      ^           ^
                     low         high
   ```

8. **Last Binary Search - Second Iteration**
   - Calculate mid index: mid = (4+7)/2 = 5
   - Compare array value at mid with target: arr[5] = 8, which equals our target 8
   - Update last = 5 to mark this position
   - Update low = mid + 1 = 6 to continue searching rightward
   - Our search space is now [11, 13]
   ```
   Array: [2, 4, 6, 8, 8, 8, 11, 13]
           0  1  2  3  4  5  6   7
                          ^
                         mid
                            ^     ^
                           low   high
   ```

9. **Last Binary Search - Third Iteration**
   - Calculate mid index: mid = (6+7)/2 = 6
   - Compare array value at mid with target: arr[6] = 11, which is greater than our target 8
   - Since the value is greater than the target, we search in the left half
   - Update high = mid - 1 = 5
   - Low is now greater than high (6 > 5), so our search space is empty
   ```
   Array: [2, 4, 6, 8, 8, 8, 11, 13]
           0  1  2  3  4  5  6   7
                               ^
                              mid
                            ^  ^
                          high low
   ```

10. **Last Binary Search - Termination**
    - Since low > high, we exit the loop
    - The value of last = 5 remains our answer for the last occurrence
    - Return last = 5

11. **Final Result Combination**
    - We now have first = 3 and last = 5
    - We return the result as [3, 5], which represents the range of indices where the target value 8 appears
    - This correctly identifies that the value 8 appears at positions 3, 4, and 5 in the input array

### Additional Example Cases

1. **Target Not Found**
```
Input: [2, 4, 6, 8, 10, 12, 14], target = 7
firstOccurrence returns -1
We directly return [-1, -1] without calling lastOccurrence
Output: [-1, -1]
```

2. **Single Occurrence**
```
Input: [2, 4, 6, 8, 10, 12, 14], target = 8
firstOccurrence returns 3
lastOccurrence returns 3
Output: [3, 3]
```

### Edge Cases Handling
1. **Empty Array**
   ```
   Input: [], target = 8
   n = 0, so high = -1
   low = 0 > high = -1, loop doesn't execute
   Output: [-1, -1]
   ```

2. **Target at Beginning**
   ```
   Input: [8, 8, 10, 12, 14], target = 8
   firstOccurrence returns 0
   lastOccurrence returns 1
   Output: [0, 1]
   ```

3. **Target at End**
   ```
   Input: [2, 4, 6, 8, 8], target = 8
   firstOccurrence returns 3
   lastOccurrence returns 4
   Output: [3, 4]
   ```

Key Observations:
1. The modified binary search correctly finds both bounds of the target range
2. We optimize by returning early if the target is not found in the first search
3. Each binary search reduces the search space by half in each iteration
4. The approach handles all edge cases correctly

Sample Validation:
Input: [2, 4, 6, 8, 8, 8, 11, 13], target = 8
Expected: [3, 5]
Output: [3, 5]

Key Points:
1. Time complexity is O(log n) as it uses binary search
2. Space complexity is O(1) as it uses constant extra space
3. The algorithm works by modifying the standard binary search to find bounds
4. We can optimize by skipping the second search if the first returns -1

TEST CASES:
1. Input: [2, 4, 6, 8, 8, 8, 11, 13], target = 8
   Expected: [3, 5]
   Output: [3, 5]
2. Input: [2, 4, 6, 8, 8, 8, 11, 13], target = 6
   Expected: [2, 2]
   Output: [2, 2]
3. Input: [2, 4, 6, 8, 8, 8, 11, 13], target = 9
   Expected: [-1, -1]
   Output: [-1, -1]
4. Input: [], target = 8
   Expected: [-1, -1]
   Output: [-1, -1]
5. Input: [8], target = 8
   Expected: [0, 0]
   Output: [0, 0]
*/
