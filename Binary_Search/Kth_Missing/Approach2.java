package Binary_Search.Kth_Missing;
/* 
Kth Missing Positive Number
# Problem: Given an array arr of positive integers sorted in a strictly increasing order, and an integer k.
Return the kth positive integer that is missing from this array.

## Intuition: 
# Intuition
Binary Search on Array Indices to Find Missing Number Position
- Instead of searching for the missing number directly, we search for its position by analyzing missing counts
- Use the mathematical relationship between array values and their indices to determine missing numbers
- Apply binary search on indices to efficiently locate the range where the kth missing number exists

## Basic Understanding
Given a sorted array like [4, 7, 9, 10] and k=4, we need to find the 4th missing positive number. The complete sequence should be [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ...]. Missing numbers are [1, 2, 3, 5, 6, 8, 11, ...], so the 4th missing number is 5.

## Key Observations with Examples

### Observation 1: Missing Number Formula
For any index i, the number of missing numbers up to that index = arr[i] - (i + 1)
* If no numbers were missing, arr[i] should equal (i + 1)
* The difference gives us the count of missing numbers

Example: For array [4, 7, 9, 10] at index 1:
- Current value: arr[1] = 7
- Expected value: 1 + 1 = 2
- Missing numbers up to index 1: 7 - 2 = 5 numbers (1, 2, 3, 5, 6)

### Observation 2: Binary Search Decision Logic
We can eliminate halves based on missing count comparison with k
* If missing_count < k: The kth missing number is to the right (larger indices)
* If missing_count >= k: The kth missing number is to the left (smaller indices)

Example: If k=4 and missing_count at mid=5, then 4 < 5, so we need fewer missing numbers (go left)

### Observation 3: Answer Calculation After Binary Search
After binary search ends, high points to the largest index where missing_count < k
* The kth missing number = k + high + 1
* This formula accounts for the position adjustment needed

```
Array:     [4, 7, 9, 10]
Indices:    0  1  2   3
Missing:    3  5  6   6  (missing count up to each index)

For k=4: high will point to index 0 (missing=3 < 4)
Answer = 4 + 0 + 1 = 5
```

### Observation 4: Neighbor Identification
Binary search helps us find the closest neighbors of the kth missing number
* Left neighbor: arr[high] (present in array)
* Right neighbor: arr[high+1] (if exists)
* The kth missing number lies between these neighbors

Let’s understand it using an example. Assume the given array is {2, 3, 4, 7, 11}. Now, if no numbers were missing the given array would look like {1, 2, 3, 4, 5}. Comparing these 2 arrays, we can conclude the following:
 - Up to index 0: Only 1 number i.e. 1 is missing in the given array.
 - Up to index 1: Only 1 number i.e. 1 is missing in the given array.
 - Up to index 2: Only 1 number i.e. 1 is missing in the given array.
 - Up to index 3: 3 numbers i.e. 1, 5, and 6 are missing.
 - Up to index 4: 6 numbers i.e. 1, 5, 6, 8, 9, and 10 are missing.

For a given value of k as 5, we can determine that the answer falls within the range of 7 to 11. Since there are only 3 missing numbers up to index 3, the 5th missing number cannot be before vec[3], which is 7. Therefore, it must be located somewhere to the right of 7. Our actual answer i.e. 9 also supports this theory. So, by following this process we can find the closest neighbors (i.e. Present in the array) for the kth missing number. In our example, the closest neighbors of the 5th missing number are 7 and 11.

## How to calculate the number of missing numbers for any index i?
From the above example, we can derive a formula to find the number of missing numbers before any array index, i. The formula is
Number of missing numbers up to index i = vec[i] - (i+1).
The given array, vec, is currently containing the number vec[i] whereas it should contain (i+1) if no numbers were missing. The difference between the current and the ideal element will give the result.

## How to apply Binary Search?
We will apply binary search on the indices of the given array. For each index, we will calculate the number of missing numbers and based on it, we will try to eliminate the halves.

## How we will get the answer after all these steps?
After completing the binary search on the indices, the pointer high will point to the closest neighbor(present in the array) that is smaller than the kth missing number. 

 - So, in the given array, the preceding neighbor of the kth missing number is vec[high]. 
 - Now, we know, up to index ‘high’,
the number of missing numbers = vec[high] - (high+1).
 - But we want to go further and find the kth number. To extend our objective, we aim to find the kth number in the sequence. In order to determine the number of additional missing values required to reach the kth position, we can calculate this as
more_missing_numbers = k - (vec[high] - (high+1)).
 - Now, we will simply add more_missing_numbers to the preceding neighbor i.e. vec[high] to get the kth missing number.
kth missing number = vec[high] + k - (vec[high] - (high+1))
        =  vec[high] + k - vec[high] + high + 1
        = k + high + 1.


## Step-by-Step Example
Let's work through array [4, 7, 9, 10] with k=4:

1. **Initialize Pointers:**
   ```
   low = 0, high = 3
   Array: [4, 7, 9, 10]
   Indices: 0  1  2  3
   ```

2. **First Iteration (mid = 1):**
   ```
   mid = (0 + 3) / 2 = 1
   missing = arr[1] - (1 + 1) = 7 - 2 = 5
   Since 5 >= 4, go left: high = mid - 1 = 0
   ```

3. **Second Iteration (mid = 0):**
   ```
   mid = (0 + 0) / 2 = 0
   missing = arr[0] - (0 + 1) = 4 - 1 = 3
   Since 3 < 4, go right: low = mid + 1 = 1
   ```

4. **Loop Ends (low > high):**
   ```
   low = 1, high = 0
   Answer = k + high + 1 = 4 + 0 + 1 = 5
   ```

## Special Cases

### Case 1: All Missing Numbers Before Array Start
Input: arr = [5, 6, 7], k = 3
- Missing numbers: [1, 2, 3, 4]
- Behavior: Binary search will set high = -1
- Result: k + high + 1 = 3 + (-1) + 1 = 3

### Case 2: Missing Number After Array End
Input: arr = [1, 2, 3], k = 2
- Missing numbers: [4, 5, 6, ...]
- Behavior: Binary search will set high = 2
- Result: k + high + 1 = 2 + 2 + 1 = 5

### Case 3: No Missing Numbers in Range
Input: arr = [1, 2, 3, 4], k = 1
- Missing numbers: [5, 6, 7, ...]
- Behavior: All indices have missing_count = 0
- Result: k + high + 1 = 1 + 3 + 1 = 5
 */
public class Approach2 {
    // Approach 2: Binary Search
    public static int missingK(int[] arr, int n, int k) {
        int low = 0, high = n - 1;
        
        // Binary search on array indices
        while (low <= high) {
            int mid = (low + high) / 2;
            
            // Calculate missing numbers up to index mid
            // Formula: arr[mid] - (mid + 1)
            int missing = arr[mid] - (mid + 1);
            
            if (missing < k) {
                // Need more missing numbers, search right half
                low = mid + 1;
            } else {
                // Too many missing numbers, search left half
                high = mid - 1;
            }
        }
        
        // After loop: high points to largest index where missing < k
        // kth missing number = k + high + 1
        return k + high + 1;
    }

    public static void main(String[] args) {
        int[] vec = {4, 7, 9, 10};
        int n = 4, k = 4;
        int ans = missingK(vec, n, k);
        System.out.println("The missing number is: " + ans);
    }
}
/* 
# Algorithm
1. Initialize two pointers: low = 0, high = n-1
2. While low <= high, calculate mid = (low + high) / 2
3. Calculate missing numbers up to index mid using formula: arr[mid] - (mid + 1)
4. If missing < k, search right half (low = mid + 1)
5. If missing >= k, search left half (high = mid - 1)
6. Return k + high + 1 as the kth missing number

### Time Complexity Breakdown per Step
1. Initialize pointers: O(1)
2. Binary search loop: O(log n)
3. Missing number calculation per iteration: O(1)
4. Final answer calculation: O(1)

Total: **O(log n)**

### Space Complexity Breakdown
1. Auxiliary Space: O(1) - only using a few variables
2. Input Space: O(1) - not modifying input array
Total: **O(1)**

# Advantages
1. **Efficient Time Complexity**: O(log n) compared to O(n) linear approach
2. **Optimal Space Usage**: O(1) space complexity
3. **Mathematical Elegance**: Uses clever formula to avoid explicit missing number enumeration
4. **Handles Edge Cases**: Works for all positions of missing numbers
5. **No Array Modification**: Doesn't alter the input array

# Limitations
1. **Array Must Be Sorted**: Requires pre-sorted input array
2. **Positive Integers Only**: Algorithm designed specifically for positive integers
3. **Mathematical Complexity**: Formula derivation might be non-intuitive
4. **Limited to Missing Numbers**: Cannot handle duplicate or negative numbers
5. **Index Dependency**: Relies heavily on mathematical relationship between values and indices

# Potential Improvements
1. **Add Input Validation**: Check for sorted array and positive integers
2. **Handle Edge Cases**: Add explicit handling for empty arrays or k=0
3. **Generic Implementation**: Extend to handle different number ranges
4. **Iterative to Recursive**: Could implement recursive binary search variant
5. **Multiple Missing Ranges**: Extend to find multiple missing number ranges efficiently

# Step-by-Step Process with Dry Run

## Example Input: arr = [4, 7, 9, 10], k = 4

### Detailed Execution Table
```
Step | low | high | mid | arr[mid] | missing | k | Action     | Explanation
-----|-----|------|-----|----------|---------|---|------------|-------------
1    | 0   | 3    | 1   | 7        | 5       | 4 | high=0     | 5>=4, go left
2    | 0   | 0    | 0   | 4        | 3       | 4 | low=1      | 3<4, go right
3    | 1   | 0    | -   | -        | -       | 4 | Exit loop  | low>high
4    | -   | -    | -   | -        | -       | 4 | Return 5   | k+high+1=4+0+1
```

### Step-by-Step Explanation

1. **Initialization Phase**
   - Set low = 0, high = 3 (last index)
   - Array state: [4, 7, 9, 10] with indices [0, 1, 2, 3]
   ```
   Complete sequence should be: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
   Missing numbers are: [1, 2, 3, 5, 6, 8]
   We need the 4th missing number
   ```

2. **First Binary Search Iteration (mid = 1)**
   - Calculate mid = (0 + 3) / 2 = 1
   - At index 1: arr[1] = 7
   - Missing count = 7 - (1 + 1) = 5
   - Since 5 >= 4, the 4th missing number is to the left
   - Update: high = mid - 1 = 0
   ```
   Missing numbers up to index 1: [1, 2, 3, 5, 6] (count = 5)
   Since we need 4th missing and have 5, answer is among first 4
   ```

3. **Second Binary Search Iteration (mid = 0)**
   - Calculate mid = (0 + 0) / 2 = 0
   - At index 0: arr[0] = 4
   - Missing count = 4 - (0 + 1) = 3
   - Since 3 < 4, the 4th missing number is to the right
   - Update: low = mid + 1 = 1
   ```
   Missing numbers up to index 0: [1, 2, 3] (count = 3)
   Since we need 4th missing and only have 3, need to look further
   ```

4. **Loop Termination and Answer Calculation**
   - Now low = 1, high = 0, so low > high
   - Exit the while loop
   - Calculate answer: k + high + 1 = 4 + 0 + 1 = 5
   ```
   high = 0 means largest index where missing < k
   Need 1 more missing number beyond arr[0] = 4
   Next missing after [1, 2, 3] is 5
   ```

### Additional Example Cases

1. **Case: Early Missing Numbers**
```
Input:  arr = [2, 3, 4, 7, 11], k = 5
Step 1: low=0, high=4, mid=2, missing=1, 1<5 → low=3
Step 2: low=3, high=4, mid=3, missing=3, 3<5 → low=4  
Step 3: low=4, high=4, mid=4, missing=6, 6>=5 → high=3
Step 4: low=4, high=3, exit loop
Output: k+high+1 = 5+3+1 = 9
```

2. **Case: All Missing Before Array**
```
Input:  arr = [5, 6, 7, 8], k = 3  
Step 1: low=0, high=3, mid=1, missing=4, 4>=3 → high=0
Step 2: low=0, high=0, mid=0, missing=4, 4>=3 → high=-1
Step 3: low=0, high=-1, exit loop
Output: k+high+1 = 3+(-1)+1 = 3
```

### Edge Cases Handling
1. **Empty Array**
   ```
   Input: arr = [], k = 5
   Output: 5 (since high = -1 initially)
   Explanation: All positive numbers are missing
   ```

2. **K Greater Than All Missing Numbers**
   ```
   Input: arr = [1000], k = 500
   Output: 500 (since 999 numbers missing before 1000)
   Explanation: Answer falls in the gap before first element
   ```

3. **Single Element Array**
   ```
   Input: arr = [3], k = 2
   Output: 2 (missing numbers: [1, 2, 4, 5, ...])
   Explanation: 2nd missing number is 2 itself
   ```

Key Observations:
1. **Binary search eliminates half the search space in each iteration**
2. **The missing count formula arr[i] - (i+1) is crucial for decision making**  
3. **Final answer formula k + high + 1 accounts for position adjustment**
4. **Algorithm works by finding the boundary where missing count transitions around k**
5. **High pointer always ends up pointing to the largest valid index where missing < k**

Sample Validation:
Input: [4, 7, 9, 10], k=4
Expected: 5 (missing sequence: 1,2,3,5,6,8,11...)
Output: 5 ✓

Key Points:
1. **Missing number calculation is the core insight of this approach**
2. **Binary search is applied on indices, not on the answer space**
3. **The mathematical relationship between array values and indices drives the algorithm**
4. **Edge cases are naturally handled by the formula k + high + 1**
5. **Time complexity is logarithmic due to binary search on indices**

TEST CASES:
1. Input: arr=[4,7,9,10], k=4
   Expected: 5
   Output: 5 ✓

2. Input: arr=[2,3,4,7,11], k=5  
   Expected: 9
   Output: 9 ✓
   
3. Input: arr=[1,2,3,4], k=2
   Expected: 6
   Output: 6 ✓
   
4. Input: arr=[5,6,7,8], k=1
   Expected: 1  
   Output: 1 ✓
   
5. Input: arr=[1], k=3
   Expected: 4
   Output: 4 ✓
 */