package Binary_Search.Find_Nth_Root;
/* 
Find Nth Root of M
# Problem: You are given two positive integers 'n' and 'm'. You have to return the 'nth' root of 'm', i.e. 'm(1/n)'. If the 'nth root is not an integer, return -1.

Note:
'nth' root of an integer 'm' is a number, which, when raised to the power 'n', gives 'm' as a result.

## Intuition: The primary objective of the Binary Search algorithm is to efficiently determine the appropriate half to eliminate, thereby reducing the search space by half. It does this by determining a specific condition that ensures that the target is not present in that half.

Now, we are not given any sorted array on which we can apply binary search. But, if we observe closely, we can notice that our answer space i.e. [1, n] is sorted. So, we will apply binary search on the answer space.

!! Edge case: How to eliminate the halves:
Our first approach should be the following:
 - After placing low at 1 and high m, we will calculate the value of ‘mid’.
 - Now, based on the value of ‘mid’ raised to the power n, we will check if ‘mid’ can be our answer, and based on this value we will also eliminate the halves. If the value is smaller than m, we will eliminate the left half and if greater we will eliminate the right half.
But, if the given numbers m and n are big enough, we cannot store the value midn in a variable. So to resolve this problem, we will implement a function like the following:

!! func(n, m, mid):
 - We will first declare a variable ‘ans’ to store the value midn.
 - Now, we will run a loop for n times to multiply the ‘mid’ n times with ‘ans’. 
 - Inside the loop, if at any point ‘ans’ becomes greater than m, we will return 2.
 - Once the loop is completed, if the ‘ans’ is equal to m, we will return 1.
 - If the value is smaller, we will return 0.
Now, based on the output of the above function, we will check if ‘mid’ is our possible answer or we will eliminate the halves. Thus we can avoid the integer overflow case.

# Intuition
Use binary search on the answer space [1, m] to efficiently find the nth root.
- Instead of checking all possible values from 1 to m, eliminate half the search space in each iteration
- Apply binary search on sorted answer space rather than a given sorted array
- Use a helper function to avoid integer overflow when calculating mid^n

## Key Observations with Examples

### Observation 1: Answer Space is Sorted
The potential answers lie in range [1, m] and form a sorted sequence
* If x^n = m, then x ≤ m (since n ≥ 1)
* We can apply binary search on this sorted answer space
* Example: For n=3, m=27, answer space is [1,2,3,...,27]

### Observation 2: Overflow Prevention Strategy
Direct calculation of mid^n can cause integer overflow for large values
* Instead of calculating mid^n directly, use iterative multiplication
* Check for overflow at each multiplication step
* Return early if intermediate result exceeds m

### Observation 3: Three-Way Comparison Function
Use a helper function that returns 0, 1, or 2 based on comparison
```
func(mid, n, m):
- Returns 1 if mid^n == m (exact match found)
- Returns 0 if mid^n < m (need to search right half)  
- Returns 2 if mid^n > m (need to search left half)
```

### Observation 4: Binary Search Elimination Logic
Based on helper function result, eliminate appropriate half
* If func returns 1: Found exact answer
* If func returns 0: Eliminate left half (low = mid + 1)
* If func returns 2: Eliminate right half (high = mid - 1)

## Step-by-Step Example
Let's work through n=3, m=27 (finding cube root of 27):

1. **Initialize Pointers:**
   ```
   low = 1, high = 27
   Answer space: [1, 2, 3, ..., 27]
   ```

2. **First Iteration:**
   ```
   mid = (1 + 27) / 2 = 14
   func(14, 3, 27): 14^3 = 2744 > 27, returns 2
   high = mid - 1 = 13
   ```

3. **Second Iteration:**
   ```
   mid = (1 + 13) / 2 = 7
   func(7, 3, 27): 7^3 = 343 > 27, returns 2
   high = mid - 1 = 6
   ```

4. **Third Iteration:**
   ```
   mid = (1 + 6) / 2 = 3
   func(3, 3, 27): 3^3 = 27 == 27, returns 1
   Return 3 (exact match found)
   ```

## Special Cases

### Case 1: Perfect Nth Root Exists
Input: n=2, m=16
- Behavior: Binary search finds exact integer root
- Result: 4 (since 4^2 = 16)

### Case 2: No Integer Nth Root
Input: n=2, m=15
- Behavior: Binary search exhausts all possibilities
- Result: -1 (no integer x where x^2 = 15)

### Case 3: Edge Case with Small Values
Input: n=1, m=5
- Behavior: Any number raised to power 1 equals itself
- Result: 5 (since 5^1 = 5)
*/

public class Approach2 {
    // Approach 2: Using Binary Search
    // Return 1 if mid^n == m (exact match)
    // Return 0 if mid^n < m (search right half)
    // Return 2 if mid^n > m (search left half)
    public static int func(int mid, int n, int m) {
        long ans = 1;
        // Calculate mid^n iteratively to avoid overflow
        for (int i = 1; i <= n; i++) {
            ans = ans * mid;
            // Early termination if result exceeds m
            if (ans > m) return 2;
        }
        // Check if exact match found
        if (ans == m) return 1;
        // mid^n < m, need to search larger values
        return 0;
    }

    // Method to find nth root of m using binary search
    public static int NthRoot(int n, int m) {
        // Initialize binary search boundaries
        int low = 1, high = m;
        
        // Binary search on answer space [1, m]
        while (low <= high) {
            int mid = (low + high) / 2;
            int midN = func(mid, n, m);
            
            if (midN == 1) {
                // Exact nth root found
                return mid;
            } else if (midN == 0) {
                // mid^n < m, search right half
                low = mid + 1;
            } else {
                // mid^n > m, search left half
                high = mid - 1;
            }
        }
        // No integer nth root exists
        return -1;
    }

    // Main method for testing
    public static void main(String[] args) {
        // Example input
        int n = 3, m = 27;

        // Call the method and print the output
        int result = NthRoot(n, m);

        // Display the result
        System.out.println("The " + n + "th root of " + m + " is: " + result);
        
        // Additional test cases
        System.out.println("2nd root of 16: " + NthRoot(2, 16));
        System.out.println("2nd root of 15: " + NthRoot(2, 15));
        System.out.println("4th root of 81: " + NthRoot(4, 81));
    }
}
/*
## Algorithm:
1. Place the 2 pointers i.e. low and high: Initially, we will place the pointers. The pointer low will point to 1 and the high will point to m.
2. Calculate the ‘mid’: Now, inside a loop, we will calculate the value of ‘mid’ using the following formula:
mid = (low+high) // 2 ( ‘//’ refers to integer division)
3. Eliminate the halves accordingly: 
 - If func(n, m, mid) == 1: On satisfying this condition, we can conclude that the number ‘mid’ is our answer. So, we will return to ‘mid’.
 - If func(n, m, mid) == 0: On satisfying this condition, we can conclude that the number ‘mid’ is smaller than our answer. So, we will eliminate the left half and consider the right half(i.e. low = mid+1).
 - If func(n, m, mid) == 2: the value mid is larger than the number we want. This means the numbers greater than ‘mid’ will not be our answers and the right half of ‘mid’ consists of such numbers. So, we will eliminate the right half and consider the left half(i.e. high = mid-1).
4. Finally,  if we are outside the loop, this means no answer exists. So, we will return -1.

### Time Complexity Breakdown per Step
1. Binary search loop: O(log m)
2. Helper function calculation: O(n) per iteration
3. Total iterations: O(log m)

Total: O(n × log m)

### Space Complexity Breakdown
1. Auxiliary Space: O(1) - only using constant extra variables
2. Input Space: O(1) - input parameters
Total: O(1)

# Advantages
1. Efficient for large values of m compared to linear search
2. Avoids integer overflow through careful calculation
3. Logarithmic time complexity reduces search space rapidly
4. Clear and intuitive binary search logic
5. Handles edge cases gracefully

# Limitations
1. Only works for integer roots (returns -1 for non-integer roots)
2. Time complexity still depends on n for each power calculation
3. Limited to positive integers only
4. May not be optimal for very small values of m

# Potential Improvements
1. Use Newton's method for faster convergence in some cases
2. Implement early termination for obvious cases (n=1, m=1)
3. Add input validation for negative numbers
4. Optimize power calculation using fast exponentiation
5. Consider using BigInteger for very large numbers

# Step-by-Step Process with Dry Run

## Example Input: n = 3, m = 27

### Detailed Execution Table
```
Step | low | high | mid | func_result | ans_calculation | Action | Explanation
-----|-----|------|-----|-------------|-----------------|--------|-------------
1    | 1   | 27   | 14  | 2          | 14^1=14, 14^2=196, 14^3=2744>27 | high=13 | mid^n > m, eliminate right half
2    | 1   | 13   | 7   | 2          | 7^1=7, 7^2=49, 7^3=343>27 | high=6  | mid^n > m, eliminate right half  
3    | 1   | 6    | 3   | 1          | 3^1=3, 3^2=9, 3^3=27==27 | return 3 | Exact match found!
```

### Step-by-Step Explanation

1. **Initialization**
   - Set search boundaries: low = 1, high = 27
   ```
   Search space: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27]
   ```

2. **First Binary Search Iteration**
   - Calculate mid = (1 + 27) / 2 = 14
   - Call func(14, 3, 27):
     * ans = 1
     * i=1: ans = 1 × 14 = 14
     * i=2: ans = 14 × 14 = 196  
     * i=3: ans = 196 × 14 = 2744 > 27, return 2
   - Since func returned 2, eliminate right half: high = 13
   ```
   New search space: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13]
   ```

3. **Second Binary Search Iteration**
   - Calculate mid = (1 + 13) / 2 = 7
   - Call func(7, 3, 27):
     * ans = 1
     * i=1: ans = 1 × 7 = 7
     * i=2: ans = 7 × 7 = 49
     * i=3: ans = 49 × 7 = 343 > 27, return 2
   - Since func returned 2, eliminate right half: high = 6
   ```
   New search space: [1, 2, 3, 4, 5, 6]
   ```

4. **Third Binary Search Iteration**
   - Calculate mid = (1 + 6) / 2 = 3
   - Call func(3, 3, 27):
     * ans = 1
     * i=1: ans = 1 × 3 = 3
     * i=2: ans = 3 × 3 = 9
     * i=3: ans = 9 × 3 = 27 == 27, return 1
   - Since func returned 1, exact match found! Return 3

### Additional Example Cases

1. **Perfect Square Case**
```
Input:  n=2, m=16
Step 1: low=1, high=16, mid=8, func(8,2,16)=2, high=7
Step 2: low=1, high=7, mid=4, func(4,2,16)=1, return 4
Output: 4
```

2. **No Integer Root Case**
```
Input:  n=2, m=15
Step 1: low=1, high=15, mid=8, func(8,2,15)=2, high=7
Step 2: low=1, high=7, mid=4, func(4,2,15)=2, high=3
Step 3: low=1, high=3, mid=2, func(2,2,15)=0, low=3
Step 4: low=3, high=3, mid=3, func(3,2,15)=0, low=4
Step 5: low=4, high=3, low>high, exit loop
Output: -1
```

### Edge Cases Handling
1. **Single Element Case**
   ```
   Input: n=1, m=5
   Output: 5
   Explanation: Any number raised to power 1 equals itself
   ```

2. **Perfect Root Case**
   ```
   Input: n=4, m=81
   Output: 3
   Explanation: 3^4 = 81, exact match found
   ```

3. **Large Numbers**
   ```
   Input: n=2, m=1000000
   Output: -1
   Explanation: No perfect square root exists for 1000000
   ```

Key Observations:
1. Binary search efficiently narrows down the search space by half in each iteration
2. Helper function prevents integer overflow by checking bounds at each multiplication
3. Three-way comparison (0,1,2) provides clear direction for binary search elimination
4. Algorithm handles both cases: exact roots exist and no integer roots exist

Sample Validation:
Input: n=3, m=27
Expected: 3
Output: 3 ✓

Key Points:
1. Time complexity is O(n × log m) due to binary search with power calculation
2. Space complexity is O(1) using only constant extra variables
3. Handles integer overflow gracefully through iterative power calculation
4. Returns -1 when no exact integer root exists
5. Works efficiently for large values of m

TEST CASES:
1. Input: n=3, m=27
   Expected: 3
   Output: 3 ✓

2. Input: n=2, m=16  
   Expected: 4
   Output: 4 ✓

3. Input: n=2, m=15
   Expected: -1
   Output: -1 ✓

4. Input: n=4, m=81
   Expected: 3
   Output: 3 ✓

5. Input: n=1, m=100
   Expected: 100
   Output: 100 ✓
 */