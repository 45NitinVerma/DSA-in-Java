package Array.Missing_and_Repeated.Optimal;
/* 
# Problem: Missing and Repeated Numbers
You are given a 0-indexed 2D integer matrix grid of size n * n with values in the range [1, n2]. Each integer appears exactly once except a which appears twice and b which is missing. The task is to find the repeating and missing numbers a and b.

Return a 0-indexed integer array ans of size 2 where ans[0] equals to a and ans[1] equals to b

## Intution:  Using XOR, we are going to solve this problem using the following 3 steps.
Assume the repeating number to be X and the missing number to be Y.

Step 1: Find the XOR of the repeating number(X) and the missing number(Y)
i.e. (X^Y) = (a[0]^a[1]^.....^a[n-1]) ^ (1^2^........^N)

In order to find the XOR of the repeating number and the missing number, we will first XOR all the array elements and with that value, we will again XOR all the numbers from 1 to N.
(X^Y) = (a[0]^a[1]^.....^a[n-1]) ^ (1^2^3^........^N)

Step 2: Find the first different bit from right between the repeating and the missing number i.e. the first set bit from right in (X^Y)

By convention, the repeating and the missing number must be different and since they are different they must contain different bits. Now, our task is to find the first different bit from the right i.e. the end. We know, the XOR of two different bits always results in 1. The position of the first different bit from the end will be the first set bit(from the right) in (X^Y) that we have found in step 1.

Step 3: Based on the position of the different bits we will group all the elements ( i.e. all array elements + all elements between 1 to N) into 2 different groups

Assume an imaginary array containing all the array elements and all the elements between 1 to N. Now, we will check that particular position for each element of that imaginary array and then if the bit is 0, we will insert the element into the 1st group otherwise, we will insert it into the 2nd group. 
After performing this step, we will get two groups. Now, if we XOR all the elements of those 2 groups, we will get 2 numbers. One of them will be the repeating number and the other will be the missing number. But until now, we do not know which one is repeating and which one is missing.

Last step: Figure out which one of the numbers is repeating and which one is missing

We will traverse the entire given array and check which one of them appears twice. And the number that appears twice is the repeating number and the other one is the missing number.

# Problem Title
Finding Missing and Repeated Numbers in an Array

# Intuition
- This problem can be solved efficiently using XOR operations
- XOR has properties that help identify the missing and repeated numbers without extra space
- We can separate numbers into groups based on bit patterns

## Basic Understanding
We have an array of size n containing numbers from 1 to n, where one number is missing and another appears twice. Our goal is to identify both of these numbers efficiently.

## Key Observations with Examples

### Observation 1: XOR Properties
- When you XOR a number with itself, you get 0: `a ^ a = 0`
- When you XOR a number with 0, you get the number: `a ^ 0 = a`
- XOR operation is commutative and associative: `a ^ b = b ^ a` and `(a ^ b) ^ c = a ^ (b ^ c)`

Example:
```
5 ^ 5 = 0
0 ^ 8 = 8
(3 ^ 4 ^ 7) ^ (4 ^ 7 ^ 9) = 3 ^ 9
```

### Observation 2: Using XOR to Find Two Unique Numbers
If we XOR all array elements with all numbers from 1 to n, the duplicated number and missing number will impact the result uniquely:
- Each number that appears exactly once in both sets will cancel out (XOR to 0)
- The missing number will be XORed only once (from the 1 to n sequence)
- The repeated number will be XORed three times (twice from array, once from 1 to n sequence)

Since `a ^ a ^ a = a`, the result will be `missing ^ repeated`

Example with array [3, 1, 2, 5, 4, 6, 7, 5]:
```
Array elements: 3 ^ 1 ^ 2 ^ 5 ^ 4 ^ 6 ^ 7 ^ 5
Numbers 1 to n: 1 ^ 2 ^ 3 ^ 4 ^ 5 ^ 6 ^ 7 ^ 8
Combined XOR: (3 ^ 1 ^ 2 ^ 5 ^ 4 ^ 6 ^ 7 ^ 5) ^ (1 ^ 2 ^ 3 ^ 4 ^ 5 ^ 6 ^ 7 ^ 8)
```

After cancellation, we get: `5 ^ 8` (since 5 appears twice in array, and 8 is missing)

### Observation 3: Bit Manipulation to Separate Numbers
Once we have `missing ^ repeated`, we can find a bit position where these two numbers differ:
- Any set bit in the XOR result indicates the two numbers differ at that position
- We can use this bit to divide all numbers into two groups
- The missing and repeated numbers will fall into different groups

```
Let's say missing ^ repeated = 13 (binary 1101)
The rightmost set bit is at position 0 (value 1)
Group 0: Numbers with bit 0 unset (even numbers)
Group 1: Numbers with bit 0 set (odd numbers)
```

### Observation 4: Finding the Individual Numbers
- Each group will contain either the missing or the repeated number
- XORing all numbers in each group will give us the two target numbers
- We can identify which is which by checking the frequency in the original array

## Step-by-Step Example
Let's work through the array [3, 1, 2, 5, 4, 6, 7, 5]:

1. Calculate the XOR of all array elements and numbers 1 to n:
   ```
   Array elements: 3 ^ 1 ^ 2 ^ 5 ^ 4 ^ 6 ^ 7 ^ 5 = 3
   Numbers 1 to 8: 1 ^ 2 ^ 3 ^ 4 ^ 5 ^ 6 ^ 7 ^ 8 = 0
   Combined XOR: 3 ^ 0 = 3
   ```
   So `missing ^ repeated = 3` (binary 011)

2. Find the rightmost set bit:
   ```
   missing ^ repeated = 3 (binary 011)
   Rightmost set bit is at position 0 (value 1)
   ```

3. Divide all numbers into two groups:
   ```
   Group with bit 0 set (odd numbers): 1, 3, 5, 7
   Group with bit 0 unset (even numbers): 2, 4, 6, 8
   ```

4. XOR all numbers in each group:
   ```
   Group with bit 0 set (from array): 1 ^ 3 ^ 5 ^ 5 ^ 7 = 1 ^ 3 ^ 7 = 5
   Group with bit 0 set (from 1 to n): 1 ^ 3 ^ 5 ^ 7 = 0
   Combined: 5 ^ 0 = 5
   
   Group with bit 0 unset (from array): 2 ^ 4 ^ 6 = 0
   Group with bit 0 unset (from 1 to n): 2 ^ 4 ^ 6 ^ 8 = 8
   Combined: 0 ^ 8 = 8
   ```

5. Verify which is repeated:
   Count occurrences in array:
   - 5 appears twice (repeated)
   - 8 doesn't appear (missing)

Result: Repeated = 5, Missing = 8

## Special Cases

### Case 1: Repeated Number at First Position
Input: [2, 2, 3, 4]
- Behavior: XOR calculations work the same
- Result: Repeated = 2, Missing = 1

### Case 2: Repeated Number at Last Position
Input: [1, 2, 3, 4, 4]
- Behavior: XOR calculations work the same
- Result: Repeated = 4, Missing = 5

### Case 3: Largest Number Missing
Input: [1, 2, 2, 4]
- Behavior: Successfully identifies 3 as missing
- Result: Repeated = 2, Missing = 3
*/
public class Approach3b {
    // Approach 3b: Using XOR
    public static int[] findMissingRepeatingNumbers(int[] a) {
        int n = a.length; // size of the array
        int xr = 0;

        // Step 1: Find XOR of all array elements and all numbers from 1 to n
        // This gives us (missing ^ repeated)
        for (int i = 0; i < n; i++) {
            xr = xr ^ a[i];      // XOR with array element
            xr = xr ^ (i + 1);   // XOR with number from 1 to n
        }

        // Step 2: Find the rightmost set bit in the XOR result
        // This bit is different between the missing and repeated numbers
        int differentiatingBit = (xr & ~(xr - 1));
        
        // Alternative for finding rightmost set bit:
        // int differentiatingBit = 1;
        // while ((xr & differentiatingBit) == 0) {
        //     differentiatingBit <<= 1;
        // }

        // Step 3: Divide all numbers into two groups based on the differentiating bit
        int zero = 0; // Numbers with differentiating bit = 0
        int one = 0;  // Numbers with differentiating bit = 1
        
        // Check array elements
        for (int i = 0; i < n; i++) {
            // If the differentiating bit is set in the current element
            if ((a[i] & differentiatingBit) != 0) {
                one = one ^ a[i];   // XOR with group 1
            } else {
                zero = zero ^ a[i]; // XOR with group 0
            }
        }
        
        // Check numbers 1 to n
        for (int i = 1; i <= n; i++) {
            // If the differentiating bit is set in the current number
            if ((i & differentiatingBit) != 0) {
                one = one ^ i;   // XOR with group 1
            } else {
                zero = zero ^ i; // XOR with group 0
            }
        }

        // Step 4: Identify which number is repeated and which is missing
        int repeatedNumber = 0;
        int missingNumber = 0;
        
        // Count occurrences of 'zero' in the array
        int count = 0;
        for (int i = 0; i < n; i++) {
            if (a[i] == zero) {
                count++;
            }
        }
        
        // If 'zero' appears twice, it's the repeated number
        // Otherwise, 'one' is the repeated number
        if (count == 2) {
            repeatedNumber = zero;
            missingNumber = one;
        } else {
            repeatedNumber = one;
            missingNumber = zero;
        }
        
        return new int[] {repeatedNumber, missingNumber};
    }

    // Main method for testing
    public static void main(String[] args) {
        // Example input: array with repeated 5 and missing 8
        int[] a = {3, 1, 2, 5, 4, 6, 7, 5};
        
        // Call the method and print the output
        int[] result = findMissingRepeatingNumbers(a);
        
        // Display the result
        System.out.println("The repeating and missing numbers are: {" 
                          + result[0] + ", " + result[1] + "}");
                          
        // Additional test cases
        int[][] testCases = {
            {2, 2, 3, 4},        // Repeated at first position
            {1, 2, 3, 4, 4},     // Repeated at last position
            {1, 2, 2, 4}         // Middle number missing
        };
        
        for (int i = 0; i < testCases.length; i++) {
            int[] testResult = findMissingRepeatingNumbers(testCases[i]);
            System.out.println("Test case " + (i+1) + " result: {" 
                              + testResult[0] + ", " + testResult[1] + "}");
        }
    }
}
    
/*
# Algorithm
1. For the first step, we will run a loop and calculate the XOR of all the array elements and the numbers between 1 to N. Let’s call this value xr.
2. In order to find the position of the first set bit from the right, we can either use a loop or we can perform AND of the xr and negation of (xr-1) i.e. (xr & ~(xr-1)).
3. Now, we will take two variables i.e. zero and one. Now, we will check the bit of that position for every element (array elements as well as numbers between 1 to N).
 - If the bit is 1: We will XOR that element with variable one.
 - If the bit is 0: We will XOR that element with variable zero.
4.Finally, we have two variables i.e. two numbers zero and one. Among them, one is repeating and the other is missing. It’s time to identify them. 
 - We will traverse the entire array and check how many times variable zero appears. 
 - If it appears twice, it will be the repeating number, otherwise, it will be the missing. 
 Now, based on variable zero’s identity, we can easily identify in which category, variable one belongs.

### Time Complexity Breakdown per Step
1. Computing XOR of array and 1 to n: O(n)
2. Finding differentiating bit: O(1)
3. Grouping based on differentiating bit: O(n)
4. Identifying repeated and missing: O(n)

Total: O(n)

### Space Complexity Breakdown
1. Auxiliary Space: O(1)
   - We only use a constant number of variables
2. Input Space: O(n)
   - The input array of size n

Total: O(n)

# Advantages
1. O(n) time complexity without using extra space
2. No risk of integer overflow as we don't use summation or squares
3. Works with any range of integers (not just 1 to n)
4. Simple and efficient bit manipulation approach

# Limitations
1. Requires that all other numbers appear exactly once
2. Might be harder to understand compared to mathematical approaches
3. Performance on modern CPUs may vary due to bit manipulation operations

# Potential Improvements
1. The code could check if n equals array length early to ensure the problem assumptions are met
2. For very large arrays, we could use parallel processing for the XOR operations
3. Add error handling for edge cases like empty arrays

# Detailed Trace of Missing and Repeated Numbers Algorithm

## Input Array
[3, 1, 2, 5, 4, 6, 7, 5]

## Expected Output
[5, 8] (Repeated = 5, Missing = 8)

## Tracing the Algorithm Step by Step

### Stage 1: Computing the XOR of all elements and numbers 1 to n

| Iteration | Array Element | Number (1 to n) | Current XOR Value | Operation | Result |
|-----------|---------------|-----------------|-------------------|-----------|--------|
| Initial   | -             | -               | 0                 | -         | 0      |
| i = 0     | a[0] = 3      | 1               | 0                 | 0 ^ 3 ^ 1 | 2      |
| i = 1     | a[1] = 1      | 2               | 2                 | 2 ^ 1 ^ 2 | 1      |
| i = 2     | a[2] = 2      | 3               | 1                 | 1 ^ 2 ^ 3 | 0      |
| i = 3     | a[3] = 5      | 4               | 0                 | 0 ^ 5 ^ 4 | 1      |
| i = 4     | a[4] = 4      | 5               | 1                 | 1 ^ 4 ^ 5 | 0      |
| i = 5     | a[5] = 6      | 6               | 0                 | 0 ^ 6 ^ 6 | 0      |
| i = 6     | a[6] = 7      | 7               | 0                 | 0 ^ 7 ^ 7 | 0      |
| i = 7     | a[7] = 5      | 8               | 0                 | 0 ^ 5 ^ 8 | 13     |

Final XOR value = 13 (binary 1101)

This XOR value represents `repeated_number ^ missing_number` = 5 ^ 8 = 13

### Stage 2: Finding the rightmost set bit

| Operation              | Calculation         | Result             |
|------------------------|--------------------|---------------------|
| XOR value              | 13                 | Binary: 1101        |
| XOR value - 1          | 13 - 1 = 12        | Binary: 1100        |
| ~(XOR value - 1)       | ~12                | Binary: ...0011     |
| XOR value & ~(XOR - 1) | 1101 & 0011        | Binary: 0001 = 1    |

The rightmost set bit is at position 0 (value = 1)

### Stage 3: Separating numbers into two groups

#### Group 0: Numbers where bit 0 is NOT set (even numbers)
#### Group 1: Numbers where bit 0 is SET (odd numbers)

| Number | Binary | Bit 0 Set? | Group |
|--------|--------|------------|-------|
| a[0] = 3 | 0011 | Yes | Group 1 |
| a[1] = 1 | 0001 | Yes | Group 1 |
| a[2] = 2 | 0010 | No  | Group 0 |
| a[3] = 5 | 0101 | Yes | Group 1 |
| a[4] = 4 | 0100 | No  | Group 0 |
| a[5] = 6 | 0110 | No  | Group 0 |
| a[6] = 7 | 0111 | Yes | Group 1 |
| a[7] = 5 | 0101 | Yes | Group 1 |
| 1 | 0001 | Yes | Group 1 |
| 2 | 0010 | No  | Group 0 |
| 3 | 0011 | Yes | Group 1 |
| 4 | 0100 | No  | Group 0 |
| 5 | 0101 | Yes | Group 1 |
| 6 | 0110 | No  | Group 0 |
| 7 | 0111 | Yes | Group 1 |
| 8 | 1000 | No  | Group 0 |

### Stage 4: XOR computation for each group

#### Group 0 (Even numbers) XOR Tracing

| Elements              | Current XOR | Operation             | Result |
|-----------------------|-------------|------------------------|--------|
| Initial               | 0           | -                      | 0      |
| a[2] = 2              | 0           | 0 ^ 2                  | 2      |
| a[4] = 4              | 2           | 2 ^ 4                  | 6      |
| a[5] = 6              | 6           | 6 ^ 6                  | 0      |
| Number 2              | 0           | 0 ^ 2                  | 2      |
| Number 4              | 2           | 2 ^ 4                  | 6      |
| Number 6              | 6           | 6 ^ 6                  | 0      |
| Number 8              | 0           | 0 ^ 8                  | 8      |

Final result for Group 0: 8

#### Group 1 (Odd numbers) XOR Tracing

| Elements              | Current XOR | Operation             | Result |
|-----------------------|-------------|------------------------|--------|
| Initial               | 0           | -                      | 0      |
| a[0] = 3              | 0           | 0 ^ 3                  | 3      |
| a[1] = 1              | 3           | 3 ^ 1                  | 2      |
| a[3] = 5              | 2           | 2 ^ 5                  | 7      |
| a[6] = 7              | 7           | 7 ^ 7                  | 0      |
| a[7] = 5              | 0           | 0 ^ 5                  | 5      |
| Number 1              | 5           | 5 ^ 1                  | 4      |
| Number 3              | 4           | 4 ^ 3                  | 7      |
| Number 5              | 7           | 7 ^ 5                  | 2      |
| Number 7              | 2           | 2 ^ 7                  | 5      |

Final result for Group 1: 5

### Stage 5: Identifying the repeated and missing numbers

| Number | Count in Array | Status |
|--------|----------------|--------|
| 8 (Group 0 result) | 0 | Not present = Missing |
| 5 (Group 1 result) | 2 | Appears twice = Repeated |

Result: [5, 8] (Repeated = 5, Missing = 8)

## Step-by-Step Process Explanation

1. **Initialize XOR Value:**
   - We start with `xr = 0` to accumulate the XOR of all elements.

2. **Calculate XOR of Array Elements and Numbers 1 to n:**
   - For each position i in the array, we XOR:
     - The array element at position i (a[i])
     - The number i+1 (representing numbers 1 to n)
   - This causes all numbers that appear exactly once in both sets to cancel out
   - Only the repeated number and missing number will affect the final XOR value
   - After all iterations, `xr` contains `repeated_number ^ missing_number`
   - In our example: `xr = 5 ^ 8 = 13` (binary 1101)

3. **Find the Rightmost Set Bit:**
   - We need to identify a bit position where the repeated and missing numbers differ
   - The expression `number = (xr & ~(xr - 1))` isolates the rightmost set bit
   - In binary, 13 is 1101, and 12 is 1100
   - ~12 is ...0011
   - 1101 & 0011 = 0001 (decimal 1)
   - This means the rightmost differing bit is at position 0 (value 1)

4. **Divide Numbers into Two Groups:**
   - Group 0: Numbers with bit 0 NOT set (all even numbers)
   - Group 1: Numbers with bit 0 set (all odd numbers)
   - We process both array elements and numbers 1 to n
   - For each group, we compute the XOR of all numbers in that group
   - Group 0 XOR result: 8
   - Group 1 XOR result: 5

5. **Determine Which Number is Repeated:**
   - Count occurrences of both numbers in the original array
   - Number 8 appears 0 times → Missing
   - Number 5 appears 2 times → Repeated
   - Therefore, the result is [5, 8]

## Key Insights from the Trace

1. The XOR of all array elements and numbers 1 to n isolates the XOR of the missing and repeated numbers.

2. The rightmost set bit in this XOR value indicates a position where the missing and repeated numbers differ.

3. By separating numbers based on this bit, we create two groups such that:
   - One group contains the missing number (but not the repeated)
   - The other group contains the repeated number (but not the missing)

4. In our example, the algorithm separates even and odd numbers because the rightmost differing bit between 5 and 8 is at position 0.

5. The final verification step (counting occurrences) correctly identifies which number is repeated and which is missing.

### Step-by-Step Explanation

1. **Initialize**
   - Set xr = 0
   ```
   xr = 0
   ```

2. **XOR all elements and 1 to n**
   - Compute xr = (3 ^ 1 ^ 2 ^ 5 ^ 4 ^ 6 ^ 7 ^ 5) ^ (1 ^ 2 ^ 3 ^ 4 ^ 5 ^ 6 ^ 7 ^ 8)
   - Similar elements cancel out, leaving: xr = 5 ^ 5 ^ 8 = 5 ^ 5 ^ 8 = 0 ^ 8 = 8
   - Wait, that's not right. Let's trace it carefully:
   - a = [3, 1, 2, 5, 4, 6, 7, 5]
   - XOR of array elements: 3 ^ 1 ^ 2 ^ 5 ^ 4 ^ 6 ^ 7 ^ 5
   - XOR of 1 to 8: 1 ^ 2 ^ 3 ^ 4 ^ 5 ^ 6 ^ 7 ^ 8
   - Combined: (3 ^ 1 ^ 2 ^ 5 ^ 4 ^ 6 ^ 7 ^ 5) ^ (1 ^ 2 ^ 3 ^ 4 ^ 5 ^ 6 ^ 7 ^ 8)
   - After cancellation: 5 ^ 8 = 13 (binary 1101)
   ```
   xr = 13 (binary 1101)
   ```

3. **Find rightmost set bit**
   - number = xr & ~(xr - 1) = 13 & ~12 = 13 & 3 = 1
   ```
   rightmost set bit = 1 (binary 0001)
   ```

4. **Group by bit**
   - Group with bit 0 set (odd numbers): 1, 3, 5, 7
   - Group with bit 0 unset (even numbers): 2, 4, 6, 8
   - XOR of odd numbers in array: 1 ^ 3 ^ 5 ^ 5 ^ 7 = 5
   - XOR of odd numbers 1 to n: 1 ^ 3 ^ 5 ^ 7 = 0
   - XOR of group 1: 5 ^ 0 = 5
   - XOR of even numbers in array: 2 ^ 4 ^ 6 = 0
   - XOR of even numbers 1 to n: 2 ^ 4 ^ 6 ^ 8 = 8
   - XOR of group 0: 0 ^ 8 = 8
   ```
   zero = 8, one = 5
   ```

5. **Count frequency and identify**
   - Count occurrences of 8 in array: 0
   - Count occurrences of 5 in array: 2
   - Since 5 appears twice, it's the repeated number
   - 8 is missing
   ```
   repeated = 5, missing = 8
   ```

### Additional Example Cases

1. **Repeated Number at Start**
```
Input: [2, 2, 3, 4]
XOR result: 2 ^ 2 ^ 3 ^ 4 ^ 1 ^ 2 ^ 3 ^ 4 = 1
Differentiating bit: 1 (binary 0001)
Group 0 (even): 2, 2, 4 -> XOR = 2
Group 1 (odd): 3, 1, 3 -> XOR = 1
Count of 2 in array: 2
Result: Repeated = 2, Missing = 1
```

2. **Middle Number Missing**
```
Input: [1, 2, 2, 4]
XOR result: 1 ^ 2 ^ 2 ^ 4 ^ 1 ^ 2 ^ 3 ^ 4 = 3
Differentiating bit: 1 (binary 0001)
Group 0 (even): 2, 2, 4 -> XOR = 4
Group 1 (odd): 1, 1, 3 -> XOR = 3
Count of 4 in array: 1
Result: Repeated = 2, Missing = 3
```

### Edge Cases Handling
1. **Array with size 2**
   ```
   Input: [1, 1]
   XOR result: 1 ^ 1 ^ 1 ^ 2 = 3
   Differentiating bit: 1 (binary 0001)
   Group 0 (even): none -> XOR = 0
   Group 1 (odd): 1, 1, 1 -> XOR = 1
   Count of 0 in array: 0
   Result: Repeated = 1, Missing = 2
   ```

2. **Consecutive Repeated Numbers**
   ```
   Input: [1, 2, 3, 3]
   XOR result: 1 ^ 2 ^ 3 ^ 3 ^ 1 ^ 2 ^ 3 ^ 4 = 4
   Differentiating bit: 4 (binary 0100)
   Group with bit 2 set: 4
   Group with bit 2 unset: 1, 2, 3, 3, 1, 2, 3
   Result: Repeated = 3, Missing = 4
   ```

Key Observations:
1. The XOR of all elements and 1 to n isolates the XOR of the missing and repeated numbers
2. The rightmost set bit helps separate numbers into two distinct groups
3. The repeated number will have a count of 2 in the original array
4. This approach uses constant space regardless of input size

Sample Validation:
Input: [3, 1, 2, 5, 4, 6, 7, 5]
Expected: [5, 8]
Output: [5, 8]

Key Points:
1. XOR properties allow us to identify differing numbers
2. Bit manipulation provides an elegant solution without additional space
3. The approach works for any array size following the problem constraints
4. Time complexity is linear and space complexity is constant

TEST CASES:
1. Input: [3, 1, 2, 5, 4, 6, 7, 5]
   Expected: [5, 8]
   Output: [5, 8]
2. Input: [2, 2, 3, 4]
   Expected: [2, 1]
   Output: [2, 1]
3. Input: [1, 2, 3, 4, 4]
   Expected: [4, 5]
   Output: [4, 5]
4. Input: [1, 2, 2, 4]
   Expected: [2, 3]
   Output: [2, 3]
*/