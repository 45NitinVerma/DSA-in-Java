package Array.Majority_Element_2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* 
# Problem: Majority Element II
Given an integer array of size n, find all elements that appear more than ⌊ n/3 ⌋ times.

# Intuition: If the array contains the majority of elements, their occurrence must be greater than the floor(N/3). Now, we can say that the count of minority elements and majority elements is equal up to a certain point in the array. So when we traverse through the array we try to keep track of the counts of elements and the elements themselves for which we are tracking the counts. 

After traversing the whole array, we will check the elements stored in the variables. Then we need to check if the stored elements are the majority elements or not by manually checking their counts.

Note: This intuition is simply the logic of cancellation i.e. a variation of Moore’s Voting Algorithm that we used in the problem Majority Element (> N/2).

- Using Extended Boyer Moore's Voting Algorithm
- Based on the concept that there can be at most 2 majority elements appearing more than ⌊n/3⌋ times
- Uses cancellation principle: majority elements will survive after canceling out with minority elements

## Basic Understanding
We need to find elements that appear more than ⌊n/3⌋ times in an array. For example, in a size-6 array, we're looking for elements appearing more than 2 times (⌊6/3⌋ = 2).

## Key Observations with Examples

### Observation 1: Maximum Count of Majority Elements
- There can be at most 2 elements appearing more than ⌊n/3⌋ times
- Proof: If each majority element appears more than n/3 times, we can have maximum 2 such elements as:
  * 2 elements × (n/3 + 1) occurences = 2n/3 + 2
  * Remaining elements must fit in: n - (2n/3 + 2) space

### Observation 2: Voting Mechanism
We maintain two candidates and their counts:
```
Array: [1, 1, 1, 3, 3, 2, 2, 2]
Initially: 
el1 = 1, cnt1 = 1
el2 = 3, cnt2 = 1

After processing:
el1 = 1 (appears 3 times)
el2 = 2 (appears 3 times)
```

## Step-by-Step Example
Let's work through array [11, 33, 33, 11, 33, 11]:

1. Initial State:
   ```
   el1 = 11, cnt1 = 1
   el2 = 33, cnt2 = 1
   ```

2. Processing 33:
   ```
   el1 = 11, cnt1 = 1
   el2 = 33, cnt2 = 2
   ```

3. Final State:
   ```
   el1 = 11 (count = 3)
   el2 = 33 (count = 3)
   Both qualify as majority elements (appear > 2 times)
   ```
*/
public class Approach3 {
    // Approach 3: Using Extended Boyer Moore's Voting Algorithm
    // Method to find elements appearing more than n/3 times
    public static List<Integer> majorityElement(int[] v) {
        int n = v.length;

        // Variables to store potential majority elements and their counts
        int cnt1 = 0, cnt2 = 0;
        int el1 = Integer.MIN_VALUE;
        int el2 = Integer.MIN_VALUE;

        // Phase 1: Finding potential candidates
        for (int i = 0; i < n; i++) {
            // If cnt1 is 0 and current element isn't el2, make current element as el1
            if (cnt1 == 0 && el2 != v[i]) {
                cnt1 = 1;
                el1 = v[i];
            }
            // If cnt2 is 0 and current element isn't el1, make current element as el2
            else if (cnt2 == 0 && el1 != v[i]) {
                cnt2 = 1;
                el2 = v[i];
            }
            // If current element matches el1 or el2, increment respective count
            else if (v[i] == el1) cnt1++;
            else if (v[i] == el2) cnt2++;
            // If no match, decrement both counts
            else {
                cnt1--;
                cnt2--;
            }
        }

        List<Integer> result = new ArrayList<>();

        // Phase 2: Verification of candidates
        cnt1 = 0;
        cnt2 = 0;
        for (int num : v) {
            if (num == el1) cnt1++;
            if (num == el2) cnt2++;
        }

        // Check if counts exceed n/3
        int threshold = n / 3;
        if (cnt1 > threshold) result.add(el1);
        if (cnt2 > threshold) result.add(el2);

        return result;
    }

    public static void main(String[] args) {
        // Test cases
        int[][] testArrays = {
            {2, 1, 1, 3, 1, 4, 5, 6},
            {1, 2, 2, 3, 2, 1, 1, 3},
            {1, 1, 1, 2, 2, 2}
        };

        for (int i = 0; i < testArrays.length; i++) {
            System.out.println("Test Case " + (i + 1) + ":");
            System.out.println("Input: " + Arrays.toString(testArrays[i]));
            List<Integer> result = majorityElement(testArrays[i]);
            System.out.println("Majority Elements: " + result);
            System.out.println();
        }
    }
}
/*
# Algorithm
1. Initialize two counters (cnt1, cnt2) and two elements (el1, el2)
2. First pass through array:
   - If cnt1 is 0 and current element ≠ el2, make it el1
   - If cnt2 is 0 and current element ≠ el1, make it el2
   - If current matches el1/el2, increment respective counter
   - Otherwise, decrement both counters
3. Second pass: Count actual frequencies of el1 and el2
4. Return elements whose count > ⌊n/3⌋

### Time Complexity Breakdown
1. First Pass: O(n)
2. Second Pass: O(n)
Total: O(n)

### Space Complexity Breakdown
1. Auxiliary Space: O(1) for variables
2. Output Space: O(1) as maximum 2 elements can be in result
Total: O(1)

# Advantages
1. Optimal time complexity O(n)
2. Constant space complexity O(1)
3. Single pass algorithm for candidate selection

# Limitations
1. Doesn't maintain element order in result
2. Requires two passes through array
3. May need modification if more than 2 majority elements possible

# Step-by-Step Process with Dry Run
I'll create a detailed breakdown of the Extended Boyer Moore's Voting Algorithm for the array [2, 1, 1, 3, 1, 4, 5, 6].

### Detailed Execution Table
```
Step | Current | Action                  | el1 | cnt1 | el2 | cnt2 | Explanation
-----|---------|------------------------|-----|------|-----|------|-------------
0    | -       | Initialize             | MIN | 0    | MIN | 0    | Initial state
1    | 2       | Set el1               | 2   | 1    | MIN | 0    | First element becomes el1
2    | 1       | Set el2               | 2   | 1    | 1   | 1    | New element becomes el2
3    | 1       | Increment el2 count   | 2   | 1    | 1   | 2    | Matches el2
4    | 3       | Decrement both counts | 2   | 0    | 1   | 1    | No match, decrement both
5    | 1       | Increment el2 count   | 2   | 0    | 1   | 2    | Matches el2
6    | 4       | Decrement el2 count   | 2   | 0    | 1   | 1    | No match, decrement both
7    | 5       | Decrement el2 count   | 2   | 0    | 1   | 0    | No match, decrement both
8    | 6       | Set el2               | 2   | 0    | 6   | 1    | New element becomes el2
```

### Step-by-Step Process Explanation

1. **Initial State**
   - el1 = MIN_VALUE, cnt1 = 0
   - el2 = MIN_VALUE, cnt2 = 0
   - Array is unprocessed

2. **Processing 2**
   - cnt1 is 0, so 2 becomes el1
   - el1 = 2, cnt1 = 1
   - el2 remains unchanged

3. **Processing 1**
   - Current element = 1
   - Doesn't match el1 (2)
   - cnt2 is 0, so 1 becomes el2
   - el1 = 2, cnt1 = 1, el2 = 1, cnt2 = 1

4. **Processing 1**
   - Current element = 1
   - Matches el2, increment cnt2
   - el1 = 2, cnt1 = 1, el2 = 1, cnt2 = 2

5. **Processing 3**
   - Current element = 3
   - Matches neither el1 nor el2
   - Decrement both counters
   - el1 = 2, cnt1 = 0, el2 = 1, cnt2 = 1

6. **Processing 1**
   - Current element = 1
   - Matches el2, increment cnt2
   - el1 = 2, cnt1 = 0, el2 = 1, cnt2 = 2

7. **Processing 4**
   - Current element = 4
   - Matches neither el1 nor el2 and cnt1 = 0
   - cnt1++, el1=4
   - el1 = 4, cnt1 = 1, el2 = 1, cnt2 = 2

8. **Processing 5**
   - Current element = 5
   - Matches neither el1 nor el2
   - Decrement both counters
   - el1 = 4, cnt1 = 0, el2 = 1, cnt2 = 1

9. **Processing 6**
   - Current element = 6
   - cnt2 is 0, so 6 becomes el2
   - el1 = 6, cnt1 = 1, el2 = 6, cnt2 = 1

### Final Verification Phase
```
Element Count Table:
Element | Frequency
--------|----------
1       | 3
2       | 1
3       | 1
4       | 1
5       | 1
6       | 1
```

### Result Analysis
- Array length (n) = 8
- Required frequency for majority = ⌊8/3⌋ + 1 = 3
- Only element 1 appears 3 times
- Therefore, result = [1]

### Key Observations from this Example:
1. Element 1 survived as it appeared most frequently (3 times)
2. Initial element 2 got cancelled out due to subsequent different elements
3. Final state of counters doesn't guarantee majority - verification phase is crucial
4. The algorithm successfully identified the only element appearing more than ⌊n/3⌋ times

This example perfectly demonstrates how the algorithm:
- Maintains two potential candidates
- Updates counters based on element matches
- Handles cancellation when encountering different elements
- Requires final verification to confirm actual majority elements

### Additional Test Cases with Detailed Breakdown

1. **Test Case: [1, 2, 2, 3, 2]**
```
Step | Current | el1 | cnt1 | el2 | cnt2 | Action
-----|---------|-----|------|-----|------|--------
0    | 1       | 1   | 1    | MIN | 0    | Make 1 as el1
1    | 2       | 1   | 1    | 2   | 1    | Make 2 as el2
2    | 2       | 1   | 1    | 2   | 2    | Increment cnt2
3    | 3       | 1   | 0    | 2   | 1    | Decrement both counts
4    | 2       | 1   | 0    | 2   | 2    | Increment cnt2

Verification:
Element 1: count = 1 (not majority)
Element 2: count = 3 (majority)
Result: [2]
```

2. **Test Case: [1, 1, 1, 2, 2, 2]**
```
Step | Current | el1 | cnt1 | el2 | cnt2 | Action
-----|---------|-----|------|-----|------|--------
0    | 1       | 1   | 1    | MIN | 0    | Make 1 as el1
1    | 1       | 1   | 2    | MIN | 0    | Increment cnt1
2    | 1       | 1   | 3    | MIN | 0    | Increment cnt1
3    | 2       | 1   | 3    | 2   | 1    | Make 2 as el2
4    | 2       | 1   | 3    | 2   | 2    | Increment cnt2
5    | 2       | 1   | 3    | 2   | 3    | Increment cnt2

Verification:
Element 1: count = 3 (majority)
Element 2: count = 3 (majority)
Result: [1, 2]
```

Key Points to Remember:
1. The algorithm maintains two potential candidates (el1, el2)
2. When encountering a new element:
   - If it matches either candidate, increment respective counter
   - If a counter is 0, make it a new candidate
   - Otherwise, decrement both counters
3. Final verification ensures actual majority elements
4. The algorithm guarantees finding all elements appearing > ⌊n/3⌋ times

This detailed breakdown should help understand how the algorithm processes each element and maintains the majority element candidates through counting and verification phases.

TEST CASES:
1. Input: [11, 33, 33, 11, 33, 11]
   Expected: [11, 33]
   Output: [11, 33]
2. Input: [1, 2]
   Expected: [1, 2]
   Output: [1, 2]
3. Input: [1, 1, 1, 2, 2, 2]
   Expected: [1, 2]
   Output: [1, 2]
*/

/* 
# Algorithm:
Initialize 4 variables:
cnt1 & cnt2 –  for tracking the counts of elements
el1 & el2 – for storing the majority of elements.
Traverse through the given array.
If cnt1 is 0 and the current element is not el2 then store the current element of the array as el1 along with increasing the cnt1 value by 1.
If cnt2 is 0 and the current element is not el1 then store the current element of the array as el2 along with increasing the cnt2 value by 1.
If the current element and el1 are the same increase the cnt1 by 1.
If the current element and el2 are the same increase the cnt2 by 1.
Other than all the above cases: decrease cnt1 and cnt2 by 1.
The integers present in el1 & el2 should be the result we are expecting. So, using another loop, we will manually check their counts if they are greater than the floor(N/3).
 */