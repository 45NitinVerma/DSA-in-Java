package Binary_Search.Sqrt;
/* 
Sqrt(x)
# Problem: Given a non-negative integer x, return the square root of x rounded down to the nearest integer. The returned integer should be non-negative as well.
You must not use any built-in exponent function or operator.

## Intuition:
The primary objective of the Binary Search algorithm is to efficiently determine the appropriate half to eliminate, thereby reducing the search space by half. It does this by determining a specific condition that ensures that the target is not present in that half.
Now, we are not given any sorted array on which we can apply binary search. But, if we observe closely, we can notice that our answer space i.e. [1, n] is sorted. So, we will apply binary search on the answer space.

Binary Search on Answer Space - Instead of searching in a traditional sorted array, we apply binary search on the answer space [1, n].
- The key insight is that if we're looking for √n, our answer lies somewhere between 1 and n
- We can eliminate half of the search space by checking if mid² ≤ n
- This allows us to efficiently narrow down to the correct floor value

## Key Observations with Examples

### Observation 1: Answer Space is Sorted
The possible answers form a sorted sequence from 1 to n
* For n = 28, possible answers are [1, 2, 3, 4, 5, 6, 7, ..., 28]
* We need to find the largest number whose square ≤ 28

### Observation 2: Binary Search Condition
We use mid² ≤ n as our condition to decide which half to eliminate
Example: For n = 28, if mid = 5, then 5² = 25 ≤ 28 (valid), so we search right half

### Observation 3: Elimination Strategy
- If mid² ≤ n: mid could be our answer, but we want the maximum such value, so eliminate left half
- If mid² > n: mid is too large, eliminate right half including mid

### Observation 4: Return Value
The high pointer will point to our final answer after the search completes
```
Search Space: [1, 2, 3, 4, 5, 6, 7, 8, ..., n]
              ↑                           ↑
             low                        high
```

## Step-by-Step Example
Let's work through n = 28:

1. **Initial Setup:**
   ```
   low = 1, high = 28
   Search space: [1, 2, 3, 4, 5, 6, ..., 28]
   ```

2. **First Iteration:**
   ```
   mid = (1 + 28) / 2 = 14
   mid² = 14² = 196 > 28
   Since 196 > 28, eliminate right half
   high = mid - 1 = 13
   ```

3. **Second Iteration:**
   ```
   low = 1, high = 13
   mid = (1 + 13) / 2 = 7
   mid² = 7² = 49 > 28
   Since 49 > 28, eliminate right half
   high = mid - 1 = 6
   ```

4. **Third Iteration:**
   ```
   low = 1, high = 6
   mid = (1 + 6) / 2 = 3
   mid² = 3² = 9 ≤ 28
   Since 9 ≤ 28, eliminate left half
   low = mid + 1 = 4
   ```

5. **Fourth Iteration:**
   ```
   low = 4, high = 6
   mid = (4 + 6) / 2 = 5
   mid² = 5² = 25 ≤ 28
   Since 25 ≤ 28, eliminate left half
   low = mid + 1 = 6
   ```

6. **Fifth Iteration:**
   ```
   low = 6, high = 6
   mid = (6 + 6) / 2 = 6
   mid² = 6² = 36 > 28
   Since 36 > 28, eliminate right half
   high = mid - 1 = 5
   ```

7. **Loop Terminates:**
   ```
   low = 6, high = 5 (low > high)
   Return high = 5
   ```

## Special Cases

### Case 1: Perfect Square
Input: n = 16
- Binary search will find mid = 4 where 4² = 16 = n
- Result: 4

### Case 2: Small Numbers
Input: n = 1
- low = 1, high = 1, mid = 1
- 1² = 1 ≤ 1, so low = 2
- Loop terminates, return high = 1
- Result: 1

### Case 3: Large Numbers
Input: n = 100
- Binary search efficiently narrows down from [1, 100] to find 10
- Result: 10
*/
public class Approach3 {
    // Approach 3: Using Binary Search
    public static int floorSqrt(int n) {
        // Initialize binary search boundaries
        int low = 1;      // Start from 1 (minimum possible answer)
        int high = n;     // End at n (maximum possible answer)
        
        // Binary search on the answer space [1, n]
        while (low <= high) {
            // Calculate middle point to avoid overflow
            long mid = (low + high) / 2;
            
            // Calculate square of mid using long to prevent overflow
            long val = mid * mid;
            
            // Check if mid could be our answer
            if (val <= (long)(n)) {
                // mid² ≤ n means mid is a valid candidate
                // But we want the maximum such value
                // So eliminate the left half and search in right half
                low = (int)(mid + 1);
            } else {
                // mid² > n means mid is too large
                // Eliminate the right half including mid
                high = (int)(mid - 1);
            }
        }
        
        // high pointer will point to our final answer
        return high;
    }

    // Main method for testing
    public static void main(String[] args) {
        // Example input
        int n = 28;

        // Call the method and get the floor of square root
        int result = floorSqrt(n);

        // Display the result
        System.out.println("The floor of square root of " + n + " is: " + result);
        
        // Additional test cases
        System.out.println("√1 = " + floorSqrt(1));   // Expected: 1
        System.out.println("√4 = " + floorSqrt(4));   // Expected: 2
        System.out.println("√8 = " + floorSqrt(8));   // Expected: 2
        System.out.println("√16 = " + floorSqrt(16)); // Expected: 4
        System.out.println("√25 = " + floorSqrt(25)); // Expected: 5
    }
}
    
/*
# Algorithm
1. **Initialize Boundaries**: Set low = 1 and high = n to define the search space
2. **Binary Search Loop**: Continue while low ≤ high
3. **Calculate Mid**: Find mid = (low + high) / 2
4. **Check Condition**: Calculate mid² and compare with n
5. **Eliminate Half**: 
   - If mid² ≤ n: Move low = mid + 1 (search right for larger values)
   - If mid² > n: Move high = mid - 1 (search left for smaller values)
6. **Return Result**: Return high (which points to the floor of square root)

### Time Complexity Breakdown per Step
1. **Initialize**: O(1)
2. **Binary Search Loop**: O(log n) iterations
3. **Mid Calculation**: O(1) per iteration
4. **Square Calculation**: O(1) per iteration
5. **Boundary Update**: O(1) per iteration

Total: **O(log n)**

### Space Complexity Breakdown
1. **Auxiliary Space**: O(1) - Only using variables low, high, mid, val
2. **Input Space**: O(1) - Single integer input
Total: **O(1)**

# Advantages
1. **Efficient**: O(log n) time complexity is much better than linear search
2. **No Built-in Functions**: Doesn't rely on built-in sqrt() or pow() functions
3. **Overflow Safe**: Uses long for intermediate calculations
4. **Space Efficient**: Uses constant extra space
5. **Easy to Understand**: Clear binary search logic

# Limitations
1. **Integer Only**: Only works for integer inputs and returns integer results
2. **Non-negative Only**: Designed for non-negative integers
3. **Potential Overflow**: For very large inputs, even long might overflow
4. **Not Exact**: Returns floor value, not the exact square root

# Potential Improvements
1. **Handle Negative Numbers**: Add input validation for negative numbers
2. **Use BigInteger**: For handling very large numbers without overflow
3. **Return Double**: Modify to return more precise decimal results
4. **Add Error Handling**: Include try-catch blocks for edge cases
5. **Optimize for Small Numbers**: Use direct lookup for very small inputs

# Step-by-Step Process with Dry Run

## Example Input: n = 28

### Detailed Execution Table
```
Iteration | Input | low | high | mid | mid² | Condition Check | Decision | Action Taken | New low | New high | Search Space | Status
----------|-------|-----|------|-----|-----|-----------------|----------|--------------|---------|----------|--------------|--------
Initial   | 28    | 1   | 28   | -   | -   | -               | -        | Initialize   | 1       | 28       | [1...28]     | Continue
1         | 28    | 1   | 28   | 14  | 196 | 196 > 28        | Too Big  | high = 13    | 1       | 13       | [1...13]     | Continue
2         | 28    | 1   | 13   | 7   | 49  | 49 > 28         | Too Big  | high = 6     | 1       | 6        | [1...6]      | Continue
3         | 28    | 1   | 6    | 3   | 9   | 9 ≤ 28          | Valid    | low = 4      | 4       | 6        | [4...6]      | Continue
4         | 28    | 4   | 6    | 5   | 25  | 25 ≤ 28         | Valid    | low = 6      | 6       | 6        | [6]          | Continue
5         | 28    | 6   | 6    | 6   | 36  | 36 > 28         | Too Big  | high = 5     | 6       | 5        | Invalid      | Exit
Final     | 28    | 6   | 5    | -   | -   | low > high      | Exit     | Return high  | -       | -        | -           | Return 5
```

### Comprehensive Step-by-Step Breakdown

#### **INITIALIZATION PHASE**
**Step 0: Setup**
- **Input**: n = 28
- **Goal**: Find largest integer x where x² ≤ 28
- **Initial Values**: low = 1, high = 28
- **Search Space**: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28]
- **Status**: Ready to start binary search

#### **ITERATION 1**
**Step 1: First Binary Search**
- **Current State**: low = 1, high = 28
- **Mid Calculation**: mid = (1 + 28) / 2 = 29/2 = 14
- **Square Calculation**: val = 14 × 14 = 196
- **Condition Check**: Is 196 ≤ 28? → NO (196 > 28)
- **Decision Logic**: Since 14² > 28, number 14 is TOO BIG
- **Reasoning**: If 14 is too big, then all numbers > 14 are also too big
- **Action**: Eliminate RIGHT HALF → high = mid - 1 = 14 - 1 = 13
- **New Bounds**: low = 1, high = 13
- **New Search Space**: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13]
- **Elements Eliminated**: [14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28]

#### **ITERATION 2**
**Step 2: Second Binary Search**
- **Current State**: low = 1, high = 13
- **Mid Calculation**: mid = (1 + 13) / 2 = 14/2 = 7
- **Square Calculation**: val = 7 × 7 = 49
- **Condition Check**: Is 49 ≤ 28? → NO (49 > 28)
- **Decision Logic**: Since 7² > 28, number 7 is TOO BIG
- **Reasoning**: If 7 is too big, then all numbers ≥ 7 in current range are too big
- **Action**: Eliminate RIGHT HALF → high = mid - 1 = 7 - 1 = 6
- **New Bounds**: low = 1, high = 6
- **New Search Space**: [1, 2, 3, 4, 5, 6]
- **Elements Eliminated**: [7, 8, 9, 10, 11, 12, 13]

#### **ITERATION 3**
**Step 3: Third Binary Search**
- **Current State**: low = 1, high = 6
- **Mid Calculation**: mid = (1 + 6) / 2 = 7/2 = 3
- **Square Calculation**: val = 3 × 3 = 9
- **Condition Check**: Is 9 ≤ 28? → YES (9 ≤ 28)
- **Decision Logic**: Since 3² ≤ 28, number 3 is VALID but we want MAXIMUM
- **Reasoning**: 3 could be our answer, but there might be larger valid numbers
- **Action**: Eliminate LEFT HALF → low = mid + 1 = 3 + 1 = 4
- **New Bounds**: low = 4, high = 6
- **New Search Space**: [4, 5, 6]
- **Elements Eliminated**: [1, 2, 3] (we know 3 is valid, but looking for larger)

#### **ITERATION 4**
**Step 4: Fourth Binary Search**
- **Current State**: low = 4, high = 6
- **Mid Calculation**: mid = (4 + 6) / 2 = 10/2 = 5
- **Square Calculation**: val = 5 × 5 = 25
- **Condition Check**: Is 25 ≤ 28? → YES (25 ≤ 28)
- **Decision Logic**: Since 5² ≤ 28, number 5 is VALID but we want MAXIMUM
- **Reasoning**: 5 could be our answer, but let's check if 6 is also valid
- **Action**: Eliminate LEFT HALF → low = mid + 1 = 5 + 1 = 6
- **New Bounds**: low = 6, high = 6
- **New Search Space**: [6]
- **Elements Eliminated**: [4, 5] (we know 5 is valid, but checking if 6 is better)

#### **ITERATION 5**
**Step 5: Fifth Binary Search**
- **Current State**: low = 6, high = 6
- **Mid Calculation**: mid = (6 + 6) / 2 = 12/2 = 6
- **Square Calculation**: val = 6 × 6 = 36
- **Condition Check**: Is 36 ≤ 28? → NO (36 > 28)
- **Decision Logic**: Since 6² > 28, number 6 is TOO BIG
- **Reasoning**: 6 is too large, so our answer must be smaller
- **Action**: Eliminate RIGHT HALF → high = mid - 1 = 6 - 1 = 5
- **New Bounds**: low = 6, high = 5
- **Loop Condition**: low > high (6 > 5) → EXIT LOOP

#### **TERMINATION PHASE**
**Step 6: Final Result**
- **Exit Condition**: low (6) > high (5)
- **Return Value**: high = 5
- **Verification**: 5² = 25 ≤ 28 ✓ and 6² = 36 > 28 ✓
- **Final Answer**: 5

### Detailed Input-Output Analysis Table

```
Test Case | Input (n) | Expected Output | Actual Output | Verification | Steps Required
----------|-----------|-----------------|---------------|--------------|----------------
1         | 1         | 1               | 1             | 1² = 1 ≤ 1   | 2 steps
2         | 4         | 2               | 2             | 2² = 4 ≤ 4   | 3 steps
3         | 8         | 2               | 2             | 2² = 4 ≤ 8   | 4 steps
4         | 16        | 4               | 4             | 4² = 16 ≤ 16 | 4 steps
5         | 25        | 5               | 5             | 5² = 25 ≤ 25 | 4 steps
6         | 28        | 5               | 5             | 5² = 25 ≤ 28 | 5 steps
7         | 100       | 10              | 10            | 10² = 100 ≤ 100 | 6 steps
```

### Point-by-Point Process Explanation

#### **Understanding the Binary Search Process:**

**1. INITIALIZATION PHASE**
   - Set up search boundaries: `low = 1`, `high = n`
   - Define search space as all integers from 1 to n
   - Goal: Find largest integer x such that x² ≤ n

**2. LOOP CONDITION CHECK**
   - Continue while `low ≤ high`
   - When `low > high`, we've found our answer

**3. MID POINT CALCULATION**
   - Calculate `mid = (low + high) / 2`
   - This gives us the middle element of current search space
   - Use integer division to get whole number

**4. SQUARE CALCULATION**
   - Calculate `val = mid * mid`
   - Use `long` data type to prevent integer overflow
   - This is our test value to compare against n

**5. CONDITION EVALUATION**
   - Check if `val ≤ n` (is mid² less than or equal to n?)
   - This determines which half of search space to eliminate

**6. DECISION MAKING PROCESS**
   - **If mid² ≤ n (VALID CANDIDATE):**
     - mid is a valid answer, but we want the MAXIMUM valid value
     - Eliminate LEFT HALF: `low = mid + 1`
     - Search in RIGHT HALF for potentially larger valid values
   
   - **If mid² > n (TOO LARGE):**
     - mid is too big, so all values ≥ mid are also too big
     - Eliminate RIGHT HALF: `high = mid - 1`
     - Search in LEFT HALF for smaller values

**7. SEARCH SPACE REDUCTION**
   - Each iteration eliminates approximately half the remaining candidates
   - Search space shrinks: n → n/2 → n/4 → n/8 → ... → 1
   - This gives us O(log n) time complexity

**8. TERMINATION AND RESULT**
   - Loop exits when `low > high`
   - At this point, `high` points to the largest valid answer
   - Return `high` as the floor of square root

#### **Key Decision Points Explained:**

**Point A: Why eliminate LEFT when mid² ≤ n?**
- When mid² ≤ n, mid is a valid candidate
- But we want the LARGEST valid candidate
- So we search RIGHT for potentially larger valid values
- We eliminate LEFT because we've already found something valid

**Point B: Why eliminate RIGHT when mid² > n?**
- When mid² > n, mid is too large
- All numbers > mid will also be too large (since squares increase)
- So we eliminate RIGHT HALF including mid
- We search LEFT for smaller, potentially valid values

**Point C: Why return high instead of low?**
- At termination: low > high
- high points to the last valid position before crossing
- low points to the first invalid position after crossing
- high gives us the floor value we want

#### **Visual Process Flow:**
```
Start: [1, 2, 3, 4, 5, 6, 7, 8, ..., n]
       ↑                             ↑
      low                          high

Step 1: Check middle, eliminate half
        [remaining valid range]
        ↑                 ↑
       low              high

Step 2: Check middle, eliminate half
        [smaller range]
        ↑        ↑
       low     high

...continue until low > high

Final: low > high, return high
```

### Multiple Test Cases with Detailed Dry Runs

#### **TEST CASE 1: n = 8**
```
Iteration | low | high | mid | mid² | Condition | Decision | Action | New Bounds | Reasoning
----------|-----|------|-----|-----|-----------|----------|--------|------------|----------
Initial   | 1   | 8    | -   | -   | -         | -        | Setup  | [1...8]    | Initialize search space
1         | 1   | 8    | 4   | 16  | 16 > 8    | Too Big  | high=3 | [1...3]    | 4² > 8, eliminate right half
2         | 1   | 3    | 2   | 4   | 4 ≤ 8     | Valid    | low=3  | [3]        | 2² ≤ 8, but check for larger
3         | 3   | 3    | 3   | 9   | 9 > 8     | Too Big  | high=2 | Invalid    | 3² > 8, so answer is 2
Final     | 3   | 2    | -   | -   | Exit      | Return   | -      | -          | Return high = 2
```

**Step-by-Step Explanation for n = 8:**
1. **Start**: Search space [1,2,3,4,5,6,7,8], looking for largest x where x² ≤ 8
2. **Step 1**: mid=4, 4²=16>8, eliminate [4,5,6,7,8], keep [1,2,3]
3. **Step 2**: mid=2, 2²=4≤8, 2 is valid but check for larger, eliminate [1,2], keep [3]
4. **Step 3**: mid=3, 3²=9>8, 3 is too big, eliminate [3]
5. **Result**: high=2, verification: 2²=4≤8 ✓ and 3²=9>8 ✓

#### **TEST CASE 2: n = 16 (Perfect Square)**
```
Iteration | low | high | mid | mid² | Condition | Decision | Action | New Bounds | Reasoning
----------|-----|------|-----|-----|-----------|----------|--------|------------|----------
Initial   | 1   | 16   | -   | -   | -         | -        | Setup  | [1...16]   | Initialize search space
1         | 1   | 16   | 8   | 64  | 64 > 16   | Too Big  | high=7 | [1...7]    | 8² > 16, eliminate right half
2         | 1   | 7    | 4   | 16  | 16 ≤ 16   | Valid    | low=5  | [5...7]    | 4² = 16, valid but check larger
3         | 5   | 7    | 6   | 36  | 36 > 16   | Too Big  | high=5 | [5]        | 6² > 16, eliminate right half
4         | 5   | 5    | 5   | 25  | 25 > 16   | Too Big  | high=4 | Invalid    | 5² > 16, so answer is 4
Final     | 5   | 4    | -   | -   | Exit      | Return   | -      | -          | Return high = 4
```

**Step-by-Step Explanation for n = 16:**
1. **Start**: Perfect square case, answer should be exactly 4
2. **Step 1**: mid=8, 8²=64>16, eliminate upper half [8...16]
3. **Step 2**: mid=4, 4²=16=16, exactly equal! But algorithm continues to check for larger
4. **Step 3**: mid=6, 6²=36>16, too big, eliminate [6,7]
5. **Step 4**: mid=5, 5²=25>16, too big
6. **Result**: high=4, perfect match for √16

#### **TEST CASE 3: n = 1 (Minimum Case)**
```
Iteration | low | high | mid | mid² | Condition | Decision | Action | New Bounds | Reasoning
----------|-----|------|-----|-----|-----------|----------|--------|------------|----------
Initial   | 1   | 1    | -   | -   | -         | -        | Setup  | [1]        | Only one possible answer
1         | 1   | 1    | 1   | 1   | 1 ≤ 1     | Valid    | low=2  | Invalid    | 1² = 1, valid but no larger numbers
Final     | 2   | 1    | -   | -   | Exit      | Return   | -      | -          | Return high = 1
```

**Step-by-Step Explanation for n = 1:**
1. **Start**: Minimum input case, only 1 is possible
2. **Step 1**: mid=1, 1²=1=1, exactly equal, but check for larger (none exist)
3. **Result**: high=1, correct answer for √1

### Edge Cases Handling
1. **Minimum Input (n = 1)**
   ```
   Input: n = 1
   Step 1: low=1, high=1, mid=1, 1²=1≤1 → low=2
   Step 2: low=2, high=1 (exit)
   Output: 1
   Explanation: √1 = 1
   ```

2. **Perfect Square (n = 25)**
   ```
   Input: n = 25
   Output: 5
   Explanation: √25 = 5 exactly
   ```

3. **Large Number (n = 100)**
   ```
   Input: n = 100
   Output: 10
   Explanation: √100 = 10 exactly
   ```

Key Observations:
1. **Answer Space**: The search space [1, n] is inherently sorted
2. **Elimination Strategy**: We eliminate half the search space in each iteration
3. **Return Value**: The high pointer always points to the correct answer
4. **Overflow Prevention**: Using long prevents integer overflow during multiplication

Sample Validation:
Input: n = 28
Expected: 5 (since 5² = 25 ≤ 28 and 6² = 36 > 28)
Output: 5

Key Points:
1. **Binary Search on Answer Space**: We search in the range [1, n] rather than a given array
2. **Condition Check**: mid² ≤ n determines which half to eliminate
3. **Maximum Valid Value**: We want the largest integer whose square ≤ n
4. **Efficient Solution**: O(log n) time complexity makes it suitable for large inputs

TEST CASES:
1. Input: n = 1
   Expected: 1
   Output: 1
2. Input: n = 4
   Expected: 2
   Output: 2
3. Input: n = 8
   Expected: 2
   Output: 2
4. Input: n = 16
   Expected: 4
   Output: 4
5. Input: n = 28
   Expected: 5
   Output: 5
6. Input: n = 100
   Expected: 10
   Output: 10
 */
