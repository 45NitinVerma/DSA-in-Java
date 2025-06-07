package Binary_Search.Lower_Bound;
/* 
# Problem: Implement lower bound
You are given an array 'arr' sorted in non-decreasing order and a number 'x'. You must return the index of the lower bound of 'x'.

Note:
1. For a sorted array 'arr', 'lower_bound' of a number 'x' is defined as the smallest index 'idx' such that the value 'arr[idx]' is not less than 'x'.If all numbers are smaller than 'x', then 'n' should be the 'lower_bound' of 'x', where 'n' is the size of array.
2. Try to do this in O(log(n)).

## What is Lower Bound?
    The lower bound algorithm finds the first or the smallest index in a sorted array where the value at that index is greater than or equal to a given key i.e. x.

    The lower bound is the smallest index, ind, where arr[ind] >= x. But if any such index is not found, the lower bound algorithm returns n i.e. size of the given array.

# Problem Title
Implement Lower Bound in a Sorted Array

# Intuition
- Lower bound finds the smallest index where the array value is greater than or equal to a target value
- In a sorted array, binary search is optimal for this purpose
- We need to track potential answers while narrowing down the search space

## Basic Understanding
The problem asks us to find the first position in a sorted array where the element is greater than or equal to a given value 'x'. If no such element exists, we return the size of the array.

## Key Observations with Examples

### Observation 1: Sorted Array Property
The array is sorted in non-decreasing order, which allows us to use binary search.
* We can eliminate half of the search space with each comparison
* This results in O(log n) time complexity

### Observation 2: Lower Bound Definition
Lower bound is the smallest index 'idx' where arr[idx] >= x.
Example: For array [3, 5, 8, 15, 19] and x = 8:
* Lower bound is index 2 (value 8)
* For x = 9, lower bound is index 3 (value 15)

### Observation 3: Default Answer
If all elements are smaller than x, the lower bound is 'n' (array size).
Example: For array [3, 5, 8, 15, 19] and x = 20:
* Lower bound is index 5 (array size)

### Observation 4: Binary Search Modifications
In traditional binary search, we stop when we find the element. Here, we need to continue searching to find the smallest valid index.
```
When arr[mid] >= x:
  - We found a potential answer, so we store it
  - We continue searching in the left half for possibly smaller valid indices
```

## Step-by-Step Example
Let's work through array [3, 5, 8, 15, 19] with target x = 9:

1. Step One:
   ```
   low = 0, high = 4, ans = 5 (default)
   mid = (0+4)/2 = 2
   arr[mid] = 8, which is < 9
   So we search in right half: low = mid + 1 = 3
   ```
   We don't have a potential answer yet since 8 < 9.

2. Step Two:
   ```
   low = 3, high = 4, ans = 5
   mid = (3+4)/2 = 3
   arr[mid] = 15, which is >= 9
   So we update ans = 3 and search in left half: high = mid - 1 = 2
   ```
   We've found a potential answer at index 3.

3. Step Three:
   ```
   low = 3, high = 2
   Since low > high, we exit the loop
   ```
   Our final answer is ans = 3.

## Special Cases

### Case 1: Target equals an array element
Input: arr = [3, 5, 8, 15, 19], x = 8
- Binary search finds arr[2] = 8
- Lower bound is index 2

### Case 2: Target between array elements
Input: arr = [3, 5, 8, 15, 19], x = 9
- Binary search finds arr[3] = 15 as next greater element
- Lower bound is index 3

### Case 3: Target smaller than all elements
Input: arr = [3, 5, 8, 15, 19], x = 1
- Binary search finds arr[0] = 3 as first element >= x
- Lower bound is index 0

### Case 4: Target larger than all elements
Input: arr = [3, 5, 8, 15, 19], x = 20
- Binary search doesn't find any element >= x
- Lower bound is index 5 (array size)
*/

public class Approach2 {
    // Approach 2: Using Binary Search
    public static int lowerBound(int[] arr, int n, int x) {
        // Initialize search space
        int low = 0, high = n - 1;
        // Default answer is n (when all elements are smaller than x)
        int ans = n;

        // Binary search loop
        while (low <= high) {
            // Calculate middle index
            int mid = (low + high) / 2;
            
            // If current element is >= x, it's a potential answer
            if (arr[mid] >= x) {
                // Update our answer to this index
                ans = mid;
                // Look for smaller index on the left that also satisfies the condition
                high = mid - 1;
            } else {
                // Current element is < x, so look on the right
                low = mid + 1;
            }
        }
        // Return the smallest index where arr[index] >= x
        return ans;
    }

    // Main method for testing
    public static void main(String[] args) {
        // Example input array (sorted)
        int[] arr = {3, 5, 8, 15, 19};
        int n = 5, x = 9;
        
        // Call the lowerBound method
        int ind = lowerBound(arr, n, x);
        
        // Display the result
        System.out.println("The lower bound is the index: " + ind);
    }
}

/* 
## Algorithm:
As the array is sorted, we will apply the Binary Search algorithm to find the index. The steps are as follows:
We will declare the 2 pointers and an ‘ans’ variable initialized to n i.e. the size of the array(as If we don’t find any index, we will return n).
1. Place the 2 pointers i.e. low and high: Initially, we will place the pointers like this: low will point to the first index, and high will point to the last index.
2. Calculate the ‘mid’: Now, we will calculate the value of mid using the following formula:
mid = (low+high) // 2 ( ‘//’ refers to integer division)
3. Compare arr[mid] with x: With comparing arr[mid] to x, we can observe 2 different cases:
 - Case 1 - If arr[mid] >= x: This condition means that the index mid may be an answer. So, we will update the ‘ans’ variable with mid and search in the left half if there is any smaller index that satisfies the same condition. Here, we are eliminating the right half.
 - Case 2 - If arr[mid] < x: In this case, mid cannot be our answer and we need to find some bigger element. So, we will eliminate the left half and search in the right half for the answer.

## Complexity Analysis:
1. Time Complexity: O(log(n)), where n is the size of the array.
2. Space Complexity: O(1), as we are using only a constant amount of space.

### Time Complexity Breakdown per Step
1. Initializing variables: O(1)
2. Binary search loop: O(log n)
   - Each iteration cuts the search space in half
   - At most log n iterations needed

Total: O(log n)

### Space Complexity Breakdown
1. Auxiliary Space: O(1)
   - We only use a constant number of variables regardless of input size
2. Input Space: O(n)
   - For storing the input array

Total: O(1) auxiliary space

# Advantages
1. Efficient O(log n) time complexity instead of O(n) linear search
2. Works correctly for all edge cases including when the target is outside array bounds
3. Simple implementation with minimal space requirements
4. Handles duplicates correctly by always finding the first occurrence

# Limitations
1. Only works on sorted arrays
2. Requires random access to array elements
3. May have numeric issues if the array size is very large (when calculating mid)

# Potential Improvements
1. Use low + (high - low) / 2 instead of (low + high) / 2 to avoid integer overflow
2. For very large arrays, iterative approach might cause stack overflow, so iterative is preferred

# Step-by-Step Process with Dry Run

## Example Input: arr = [3, 5, 8, 15, 19], x = 9

### Detailed Execution Table
```
Step | Search Space | mid | arr[mid] | Condition | Action              | Updated Values
-----|--------------|-----|----------|-----------|---------------------|---------------
1    | [0, 4]       | 2   | 8        | 8 < 9     | Search right half   | low = 3, high = 4, ans = 5
2    | [3, 4]       | 3   | 15       | 15 >= 9   | Update ans, search left | low = 3, high = 2, ans = 3
3    | [3, 2]       | -   | -        | low > high| Exit loop          | ans = 3
```

### Step-by-Step Explanation

1. **Initialize Search Space**
   - We start with low = 0, high = 4, ans = 5 (array size)
   ```
   arr: [3, 5, 8, 15, 19]
   low: 0, high: 4, ans: 5
   ```

2. **First Iteration**
   - mid = (0+4)/2 = 2
   - arr[mid] = 8 is less than x = 9
   - We need to search in the right half
   ```
   arr: [3, 5, 8, 15, 19]
          L     M     H
   low: 3, high: 4, ans: 5
   ```

3. **Second Iteration**
   - mid = (3+4)/2 = 3
   - arr[mid] = 15 is greater than x = 9
   - We've found a potential answer, update ans = 3
   - Now search in the left half for a possibly smaller index
   ```
   arr: [3, 5, 8, 15, 19]
                    M H
                   L
   low: 3, high: 2, ans: 3
   ```

4. **Exit Condition**
   - Now low > high, so we exit the loop
   - Our final answer is ans = 3

### Additional Example Cases

1. **Target equals an existing element**
```
Input:  arr = [3, 5, 8, 15, 19], x = 8
Step 1: low = 0, high = 4, mid = 2, arr[mid] = 8 >= 8, ans = 2, high = 1
Step 2: low = 0, high = 1, mid = 0, arr[mid] = 3 < 8, low = 1
Step 3: low = 1, high = 1, mid = 1, arr[mid] = 5 < 8, low = 2
Step 4: low = 2, high = 1, exit loop
Output: 2
```

2. **Target smaller than all elements**
```
Input:  arr = [3, 5, 8, 15, 19], x = 1
Step 1: low = 0, high = 4, mid = 2, arr[mid] = 8 > 1, ans = 2, high = 1
Step 2: low = 0, high = 1, mid = 0, arr[mid] = 3 > 1, ans = 0, high = -1
Step 3: low = 0, high = -1, exit loop
Output: 0
```

### Edge Cases Handling
1. **Target greater than all elements**
   ```
   Input: arr = [3, 5, 8, 15, 19], x = 20
   Binary search doesn't find any element >= x
   We return the default ans = n = 5
   ```

2. **Array with duplicates**
   ```
   Input: arr = [3, 5, 5, 8, 8, 15], x = 5
   Our algorithm would find the first occurrence at index 1
   ```

Key Observations:
1. The algorithm always finds the smallest index that satisfies arr[index] >= x
2. If no satisfying index exists, it returns the array size
3. The time complexity is O(log n) which makes it efficient for large arrays

Sample Validation:
Input: arr = [3, 5, 8, 15, 19], x = 9
Expected: 3
Output: 3 (arr[3] = 15 is the first element greater than or equal to 9)

Key Points:
1. The binary search approach enables efficient search in sorted arrays
2. We update our answer whenever we find a valid element
3. We continue searching in the left half to find potentially smaller valid indices
4. Default answer is n when no valid element is found

TEST CASES:
1. Input: arr = [3, 5, 8, 15, 19], x = 5
   Expected: 1
   Output: 1
2. Input: arr = [1, 2, 3, 4, 5], x = 0
   Expected: 0
   Output: 0
3. Input: arr = [1, 1, 2, 2, 3], x = 2
   Expected: 2
   Output: 2
4. Input: arr = [5, 10, 15, 20, 25], x = 30
   Expected: 5
   Output: 5
*/