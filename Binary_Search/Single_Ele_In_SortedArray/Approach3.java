package Binary_Search.Single_Ele_In_SortedArray;

import java.util.ArrayList;
import java.util.Arrays;

/* 
Single Element in a Sorted Array
# Problem: You are given a sorted array consisting of only integers where every element appears exactly twice, except for one element which appears exactly once.
Return the single element that appears only once.
Your solution must run in O(log n) time and O(1) space.

## Intution:
We need to consider 2 different cases while using Binary Search in this problem. Binary Search works by reducing the search space by half. So, at first, we need to identify the halves and then eliminate them accordingly. In addition to that, we need to check if the current element i.e. arr[mid] is the ‘single element’.
If we can resolve these two cases, we can easily apply Binary Search in this algorithm.

!! How to check if arr[mid] i.e. the current element is the single element:
A crucial observation to note is that if an element appears twice in a sequence, either the preceding or the subsequent element will also be the same. But only for the single element, this condition will not be satisfied. So, to check this, the condition will be the following:
 * If arr[mid] != arr[mid-1] and arr[mid] != arr[mid+1]: If this condition is true for arr[mid], we can conclude arr[mid] is the single element.

 The above condition will throw errors in the following 3 cases:
 - If the array size is 1.
 - If ‘mid’ points to 0 i.e. the first index.
 - If ‘mid’ points to n-1 i.e. the last index.
Note: At the start of the algorithm, we address the above edge cases without requiring separate conditions during the check for arr[mid] inside the loop. And the search space will be from index 1 to n-2 as indices 0 and n-1 have already been checked.

Resolving edge cases:
 - If n == 1: This means the array size is 1. If the array contains only one element, we will return that element only.
 - If arr[0] != arr[1]: This means the very first element of the array is the single element. So, we will return arr[0].
 - If arr[n-1] != arr[n-2]: This means the last element of the array is the single element. So, we will return arr[n-1].

!! How to identify the halves:
     arr = [1,     1,     2,     2,     3,     3,     4,     5,     5,     6,     6]
            ↑      ↑      ↑      ↑      ↑      ↑      ↑      ↑      ↑      ↑      ↑
           even   odd    even    odd   even    odd  single   odd   even   odd    even
                                                    element
           --------- Left Half -------------------         -------- Right Half ------

By observing the above image, we can clearly notice a striking distinction between the index sequences of the left and right halves of the single element in the array. 
1. The index sequence of the duplicate numbers in the left half is always (even, odd). That means one of the following conditions will be satisfied if we are in the left half:
 - If the current index is even, the element at the next odd index will be the same as the current element.
 - Similarly, If the current index is odd, the element at the preceding even index will be the same as the current element.

2. The index sequence of the duplicate numbers in the right half is always (odd, even). That means one of the following conditions will be satisfied if we are in the right half:
 - If the current index is even, the element at the preceding odd index will be the same as the current element.
 - Similarly, If the current index is odd, the element at the next even index will be the same as the current element.

Now, we can easily identify the left and right halves, just by checking the sequence of the current index, i, like the following:

 - If (i % 2 == 0 and arr[i] == arr[i+1]) or (i%2 == 1 and arr[i] == arr[i-1]), we are in the left half.
 - If (i % 2 == 0 and arr[i] == arr[i-1]) or (i%2 == 1 and arr[i] == arr[i+1]), we are in the right half.

Note: In our case, the index i refers to the index ‘mid’.

!! How to eliminate the halves:
 - If we are in the left half of the single element, we have to eliminate this left half (i.e. low = mid+1). Because our single element appears somewhere on the right side.
 - If we are in the right half of the single element, we have to eliminate this right half (i.e. high = mid-1). Because our single element appears somewhere on the left side.

Now, we have resolved the problems and we can use the binary search accordingly.

Use Binary Search to efficiently find the single element by analyzing index patterns of duplicate pairs.
- In the left half of the single element: duplicate pairs follow (even, odd) index pattern
- In the right half of the single element: duplicate pairs follow (odd, even) index pattern
- By identifying which half we're in, we can eliminate half the search space in each iteration

## Key Observations with Examples

### Observation 1: Index Pattern Analysis
The position of duplicate pairs changes before and after the single element.
* Before single element: pairs at (even, odd) indices
* After single element: pairs at (odd, even) indices

### Observation 2: Edge Case Handling
Three edge cases need special handling before applying binary search:
Example: 
- Array size = 1: return the only element
- First element is single: arr[0] != arr[1]
- Last element is single: arr[n-1] != arr[n-2]

### Observation 3: Single Element Detection
A single element can be identified when it's different from both neighbors.
Example: arr[mid] != arr[mid-1] && arr[mid] != arr[mid+1]

### Observation 4: Half Identification Logic
```
Array: [1, 1, 2, 2, 3, 3, 4, 5, 5, 6, 6]
Index:  0  1  2  3  4  5  6  7  8  9  10
       even odd even odd even odd single odd even odd even
       ←-------- Left Half --------→    ←-- Right Half --→
```

### Observation 5: Search Space Optimization
After handling edge cases, search space becomes [1, n-2] since indices 0 and n-1 are already checked.

## Step-by-Step Example
Let's work through array: [1, 1, 2, 2, 3, 3, 4, 5, 5, 6, 6]

1. **Initial Setup:**
   ```
   Array: [1, 1, 2, 2, 3, 3, 4, 5, 5, 6, 6]
   Index:  0  1  2  3  4  5  6  7  8  9  10
   low = 1, high = 9 (after edge case handling)
   ```

2. **First Iteration:**
   ```
   mid = (1 + 9) / 2 = 5
   arr[5] = 3, arr[4] = 3, arr[6] = 4
   mid % 2 = 1 (odd), arr[mid] == arr[mid-1] → Left half
   low = mid + 1 = 6
   ```

3. **Second Iteration:**
   ```
   mid = (6 + 9) / 2 = 7
   arr[7] = 5, arr[6] = 4, arr[8] = 5
   arr[7] != arr[6] && arr[7] != arr[8] → Found single element!
   Return arr[7] = 4... Wait, this is wrong!
   ```

## Special Cases

### Case 1: Array Size is 1
Input: [5]
- Behavior: Return immediately without binary search
- Result: 5

### Case 2: First Element is Single
Input: [1, 2, 2, 3, 3]
- Behavior: arr[0] != arr[1], return arr[0]
- Result: 1

### Case 3: Last Element is Single
Input: [1, 1, 2, 2, 5]
- Behavior: arr[n-1] != arr[n-2], return arr[n-1]
- Result: 5
*/

public class Approach3 {
    // Approach 3: Using Binary Search
    public static int singleNonDuplicate(ArrayList<Integer> arr) {
        int n = arr.size(); // Size of the array
        
        // Edge Case 1: If array has only one element
        if (n == 1)
            return arr.get(0);
            
        // Edge Case 2: If first element is the single element
        if (!arr.get(0).equals(arr.get(1)))
            return arr.get(0);
            
        // Edge Case 3: If last element is the single element
        if (!arr.get(n - 1).equals(arr.get(n - 2)))
            return arr.get(n - 1);

        // Binary search in range [1, n-2] (excluding edge cases)
        int low = 1, high = n - 2;
        
        while (low <= high) {
            int mid = (low + high) / 2;

            // Check if arr[mid] is the single element
            // Single element won't match with either neighbor
            if (!arr.get(mid).equals(arr.get(mid + 1)) && !arr.get(mid).equals(arr.get(mid - 1))) {
                return arr.get(mid);
            }

            // Determine which half we're in based on index pattern
            // Left half: pairs at (even,odd) indices
            if ((mid % 2 == 1 && arr.get(mid).equals(arr.get(mid - 1)))
                    || (mid % 2 == 0 && arr.get(mid).equals(arr.get(mid + 1)))) {
                // We are in left half, eliminate it
                // Single element is in right half
                low = mid + 1;
            }
            // Right half: pairs at (odd,even) indices  
            else {
                // We are in right half, eliminate it
                // Single element is in left half
                high = mid - 1;
            }
        }

        // This should never be reached for valid input
        return -1;
    }

    // Main method for testing
    public static void main(String[] args) {
        // Example input: single element is 4
        ArrayList<Integer> arr = new ArrayList<>(Arrays.asList(1, 1, 2, 2, 3, 3, 4, 5, 5, 6, 6));

        // Call the method and get result
        int result = singleNonDuplicate(arr);

        // Display the result
        System.out.println("Array: " + arr);
        System.out.println("The single element is: " + result);
        
        // Test other cases
        ArrayList<Integer> arr2 = new ArrayList<>(Arrays.asList(1, 2, 2, 3, 3));
        System.out.println("Array: " + arr2);
        System.out.println("The single element is: " + singleNonDuplicate(arr2));
        
        ArrayList<Integer> arr3 = new ArrayList<>(Arrays.asList(1, 1, 2));
        System.out.println("Array: " + arr3);
        System.out.println("The single element is: " + singleNonDuplicate(arr3));
    }
}
    
/*
# Algorithm:
1. If n == 1: This means the array size is 1. If the array contains only one element, we will return that element only.
2. If arr[0] != arr[1]: This means the very first element of the array is the single element. So, we will return arr[0].
3. If arr[n-1] != arr[n-2]: This means the last element of the array is the single element. So, we will return arr[n-1].
4. Place the 2 pointers i.e. low and high: Initially, we will place the pointers excluding index 0 and n-1 like this: low will point to index 1, and high will point to index n-2 i.e. the second last index.
5. Calculate the ‘mid’: Now, inside a loop, we will calculate the value of ‘mid’ using the following formula:
mid = (low+high) // 2 ( ‘//’ refers to integer division)
6. Check if arr[mid] is the single element:
 - If arr[mid] != arr[mid-1] and arr[mid] != arr[mid+1]: If this condition is true for arr[mid], we can conclude arr[mid] is the single element. We will return arr[mid].
7. If (mid % 2 == 0 and arr[mid] == arr[mid+1])
or (mid%2 == 1 and arr[mid] == arr[mid-1]): This means we are in the left half and we should eliminate it as our single element appears on the right. So, we will do this:
low = mid+1.
8. Otherwise, we are in the right half and we should eliminate it as our single element appears on the left. So, we will do this: high = mid-1.

### Time Complexity Breakdown per Step
1. Edge cases handling: O(1)
2. Binary search loop: O(log n)
3. Mid calculation: O(1) per iteration
4. Single element check: O(1) per iteration
5. Half identification: O(1) per iteration
6. Pointer update: O(1) per iteration

Total: **O(log n)**

### Space Complexity Breakdown
1. Auxiliary Space: O(1) - only using low, high, mid variables
2. Input Space: O(n) - input array storage

Total: **O(1)** auxiliary space

# Advantages
1. **Optimal Time Complexity**: O(log n) is much better than linear search O(n)
2. **Constant Space**: Uses only O(1) extra space
3. **Handles Edge Cases**: Efficiently handles all boundary conditions
4. **Clear Logic**: Index pattern analysis makes the approach intuitive
5. **No Extra Data Structures**: Works directly on input array

# Limitations
1. **Requires Sorted Array**: Algorithm depends on the sorted property
2. **Specific Problem Constraint**: Only works when exactly one element appears once
3. **Integer Division**: Uses integer division which might need careful handling
4. **Index Bounds**: Requires careful handling of array bounds to avoid errors

# Potential Improvements
1. **Generic Type Support**: Could be made generic to work with any Comparable type
2. **Array Support**: Could support regular arrays instead of just ArrayList
3. **Better Error Handling**: Could add validation for input constraints
4. **Bit Manipulation**: Alternative approach using XOR for comparison

# Step-by-Step Process with Dry Run

## Example Input: [1, 1, 2, 2, 3, 3, 4, 5, 5, 6, 6]

### Complete Detailed Execution Table

```
Step | Array State                | low | high | mid | arr[mid] | arr[mid-1] | arr[mid+1] | mid%2 | Condition Check | Action Taken | New Bounds | Explanation
-----|----------------------------|-----|------|-----|----------|------------|------------|-------|-----------------|--------------|------------|-------------
0    | [1,1,2,2,3,3,4,5,5,6,6]   | -   | -    | -   | -        | -          | -          | -     | n==1? No        | Continue     | -          | Array size = 11, not 1
     | Index: 0,1,2,3,4,5,6,7,8,9,10| -   | -    | -   | -        | -          | -          | -     | arr[0]!=arr[1]? | Continue     | -          | arr[0]=1, arr[1]=1, equal
     |                            | -   | -    | -   | -        | -          | -          | -     | arr[10]!=arr[9]?| Continue     | -          | arr[10]=6, arr[9]=6, equal
1    | [1,1,2,2,3,3,4,5,5,6,6]   | 1   | 9    | -   | -        | -          | -          | -     | Initialize      | Set bounds   | low=1,high=9| Start binary search
2    | [1,1,2,2,3,3,4,5,5,6,6]   | 1   | 9    | 5   | 3        | 3          | 4          | 1     | Single element? | No           | -          | arr[5]=3, arr[4]=3, arr[6]=4
     |                            |     |      |     |          |            |            |       | arr[5]==arr[4]? | Yes          | -          | 3 == 3, true
     |                            |     |      |     |          |            |            |       | mid%2==1?       | Yes          | -          | 5%2 = 1 (odd index)
     |                            |     |      |     |          |            |            |       | Left half?      | Yes          | low=mid+1  | Odd index + equal to previous = left half
3    | [1,1,2,2,3,3,4,5,5,6,6]   | 6   | 9    | 7   | 5        | 4          | 5          | 1     | Single element? | No           | -          | arr[7]=5, arr[6]=4, arr[8]=5
     |                            |     |      |     |          |            |            |       | arr[7]==arr[8]? | Yes          | -          | 5 == 5, true
     |                            |     |      |     |          |            |            |       | mid%2==1?       | Yes          | -          | 7%2 = 1 (odd index)
     |                            |     |      |     |          |            |            |       | Right half?     | Yes          | high=mid-1 | Odd index + equal to next = right half
4    | [1,1,2,2,3,3,4,5,5,6,6]   | 6   | 6    | 6   | 4        | 3          | 5          | 0     | Single element? | Yes          | Return 4   | -          | arr[6]=4, arr[5]=3, arr[7]=5
     |                            |     |      |     |          |            |            |       | arr[6]!=arr[5]? | Yes          | -          | 4 != 3, true
     |                            |     |      |     |          |            |            |       | arr[6]!=arr[7]? | Yes          | -          | 4 != 5, true
     |                            |     |      |     |          |            |            |       | Found single!   | Return       | Result=4   | Single element found at index 6
```

### Visual Step-by-Step Process

#### Initial State Analysis:
```
Array:  [1, 1, 2, 2, 3, 3, 4, 5, 5, 6, 6]
Index:   0  1  2  3  4  5  6  7  8  9  10
Pairs: (0,1)(2,3)(4,5) 6 (7,8)(9,10)
                       ↑
                 Single Element
```

#### Step 0: Edge Cases Verification
```
Check 1: n == 1? → 11 == 1? → FALSE → Continue
Check 2: arr[0] != arr[1]? → 1 != 1? → FALSE → Continue  
Check 3: arr[10] != arr[9]? → 6 != 6? → FALSE → Continue
Result: No edge case detected, proceed with binary search
```

#### Step 1: Initialize Binary Search
```
Search Space: Exclude indices 0 and 10 (already checked)
low = 1, high = 9
Search Range: [1, 2, 3, 4, 5, 6, 7, 8, 9]
Array Values: [1, 2, 2, 3, 3, 4, 5, 5, 6]
```

#### Step 2: First Binary Search Iteration (low=1, high=9)
```
Calculate mid: mid = (1 + 9) / 2 = 5
Current element: arr[5] = 3
Left neighbor: arr[4] = 3  
Right neighbor: arr[6] = 4

Single Element Check:
- arr[5] != arr[4]? → 3 != 3? → FALSE
- arr[5] != arr[6]? → 3 != 4? → TRUE
- Both conditions needed for single element → NOT SINGLE

Half Identification:
- mid % 2 = 5 % 2 = 1 (odd index)
- arr[5] == arr[4]? → 3 == 3? → TRUE
- Pattern: odd index equals previous → LEFT HALF PATTERN
- Action: Eliminate left half → low = mid + 1 = 6

Visual:
[1, 1, 2, 2, 3, 3, 4, 5, 5, 6, 6]
←---eliminated---→  ←remaining→
```

#### Step 3: Second Binary Search Iteration (low=6, high=9)
```
Calculate mid: mid = (6 + 9) / 2 = 7
Current element: arr[7] = 5
Left neighbor: arr[6] = 4
Right neighbor: arr[8] = 5

Single Element Check:
- arr[7] != arr[6]? → 5 != 4? → TRUE
- arr[7] != arr[8]? → 5 != 5? → FALSE  
- Both conditions needed for single element → NOT SINGLE

Half Identification:
- mid % 2 = 7 % 2 = 1 (odd index)
- arr[7] == arr[8]? → 5 == 5? → TRUE
- Pattern: odd index equals next → RIGHT HALF PATTERN
- Action: Eliminate right half → high = mid - 1 = 6

Visual:
[1, 1, 2, 2, 3, 3, 4, 5, 5, 6, 6]
                   ↑  ←eliminated→
                only remaining
```

#### Step 4: Third Binary Search Iteration (low=6, high=6)
```
Calculate mid: mid = (6 + 6) / 2 = 6
Current element: arr[6] = 4
Left neighbor: arr[5] = 3
Right neighbor: arr[7] = 5

Single Element Check:
- arr[6] != arr[5]? → 4 != 3? → TRUE
- arr[6] != arr[7]? → 4 != 5? → TRUE
- Both conditions TRUE → SINGLE ELEMENT FOUND!

Return: arr[6] = 4

Visual:
[1, 1, 2, 2, 3, 3, 4, 5, 5, 6, 6]
                   ↑
            SINGLE ELEMENT = 4
```

## Detailed Point-by-Point Process Explanation

### Phase 1: Edge Case Handling
1. **Array Size Check**: 
   - Check if `n == 1`, if true return `arr[0]`
   - Current: `n = 11`, condition false, continue

2. **First Element Check**:
   - Check if `arr[0] != arr[1]`, if true return `arr[0]` 
   - Current: `arr[0] = 1, arr[1] = 1`, condition false, continue

3. **Last Element Check**:
   - Check if `arr[n-1] != arr[n-2]`, if true return `arr[n-1]`
   - Current: `arr[10] = 6, arr[9] = 6`, condition false, continue

### Phase 2: Binary Search Setup  
4. **Initialize Pointers**:
   - Set `low = 1` (skip first element, already checked)
   - Set `high = n-2 = 9` (skip last element, already checked)
   - Search space: indices [1, 2, 3, 4, 5, 6, 7, 8, 9]

### Phase 3: Binary Search Iterations

#### Iteration 1: Finding Mid and Analysis
5. **Calculate Middle**:
   - `mid = (low + high) / 2 = (1 + 9) / 2 = 5`
   - Current element: `arr[5] = 3`

6. **Single Element Check**:
   - Check left neighbor: `arr[4] = 3`
   - Check right neighbor: `arr[6] = 4` 
   - Condition: `arr[5] != arr[4] AND arr[5] != arr[6]`
   - Result: `3 != 3 AND 3 != 4` → `FALSE AND TRUE` → `FALSE`
   - Not a single element, continue analysis

7. **Half Identification**:
   - Calculate: `mid % 2 = 5 % 2 = 1` (odd index)
   - Check pattern: `arr[5] == arr[4]?` → `3 == 3?` → `TRUE`
   - Pattern: odd index with equal previous element → **LEFT HALF**
   - Logic: In left half, duplicates appear at (even, odd) positions
   - Since we're in left half, single element is in right half

8. **Eliminate Left Half**:
   - Update: `low = mid + 1 = 5 + 1 = 6`
   - New search space: [6, 7, 8, 9]

#### Iteration 2: Narrowing Down
9. **Calculate New Middle**:
   - `mid = (low + high) / 2 = (6 + 9) / 2 = 7`
   - Current element: `arr[7] = 5`

10. **Single Element Check**:
    - Check left neighbor: `arr[6] = 4`
    - Check right neighbor: `arr[8] = 5`
    - Condition: `arr[7] != arr[6] AND arr[7] != arr[8]`
    - Result: `5 != 4 AND 5 != 5` → `TRUE AND FALSE` → `FALSE`
    - Not a single element, continue analysis

11. **Half Identification**:
    - Calculate: `mid % 2 = 7 % 2 = 1` (odd index)
    - Check pattern: `arr[7] == arr[8]?` → `5 == 5?` → `TRUE`
    - Pattern: odd index with equal next element → **RIGHT HALF**
    - Logic: In right half, duplicates appear at (odd, even) positions
    - Since we're in right half, single element is in left half

12. **Eliminate Right Half**:
    - Update: `high = mid - 1 = 7 - 1 = 6`
    - New search space: [6]

#### Iteration 3: Final Check
13. **Calculate Final Middle**:
    - `mid = (low + high) / 2 = (6 + 6) / 2 = 6`
    - Current element: `arr[6] = 4`

14. **Single Element Check**:
    - Check left neighbor: `arr[5] = 3` 
    - Check right neighbor: `arr[7] = 5`
    - Condition: `arr[6] != arr[5] AND arr[6] != arr[7]`
    - Result: `4 != 3 AND 4 != 5` → `TRUE AND TRUE` → `TRUE`
    - **SINGLE ELEMENT FOUND!**

15. **Return Result**:
    - Return `arr[6] = 4`
    - Algorithm complete

### Phase 4: Pattern Recognition Summary
16. **Left Half Pattern Recognition**:
    - Duplicates at positions: (even, odd)
    - Example: (0,1)→1, (2,3)→2, (4,5)→3
    - When `mid` is odd and equals previous: LEFT HALF
    - When `mid` is even and equals next: LEFT HALF

17. **Right Half Pattern Recognition**:  
    - Duplicates at positions: (odd, even)
    - Example: (7,8)→5, (9,10)→6
    - When `mid` is odd and equals next: RIGHT HALF
    - When `mid` is even and equals previous: RIGHT HALF

### Key Decision Points Summary:
- **Edge cases eliminate**: Array size 1, first element single, last element single
- **Binary search range**: [1, n-2] to avoid boundary issues
- **Pattern detection**: Index parity + neighbor equality determines half
- **Search space reduction**: Each iteration eliminates ~50% of remaining elements
- **Termination condition**: Element differs from both neighbors

### Step-by-Step Explanation

1. **Edge Cases Check**
   - Array size = 11 ≠ 1, continue
   - arr[0] = 1, arr[1] = 1, they're equal, continue  
   - arr[10] = 6, arr[9] = 6, they're equal, continue
   ```
   No edge case triggered, proceed with binary search
   ```

2. **Initialize Search Space**
   - Set low = 1, high = 9 (excluding indices 0 and 10)
   ```
   Search space: [1, 2, 3, 4, 5, 6, 7, 8, 9]
   Array values: [1, 2, 2, 3, 3, 4, 5, 5, 6]
   ```

3. **First Binary Search Iteration**
   - mid = (1 + 9) / 2 = 5
   - arr[5] = 3, check if it's single: arr[4] = 3, arr[6] = 4
   - arr[5] == arr[4], so not single
   - mid % 2 = 1 (odd), arr[5] == arr[4] → Left half pattern
   - Update: low = mid + 1 = 6
   ```
   Pattern: (even=4, odd=5) both have value 3 → Left half
   ```

4. **Second Binary Search Iteration**
   - mid = (6 + 9) / 2 = 7  
   - arr[7] = 5, check if it's single: arr[6] = 4, arr[8] = 5
   - arr[7] != arr[6] but arr[7] == arr[8], so not single
   - mid % 2 = 1 (odd), arr[7] == arr[8] → Right half pattern
   - Update: high = mid - 1 = 6
   ```
   Pattern: (odd=7, even=8) both have value 5 → Right half
   ```

5. **Third Binary Search Iteration**
   - mid = (6 + 6) / 2 = 6
   - arr[6] = 4, check if it's single: arr[5] = 3, arr[7] = 5  
   - arr[6] != arr[5] && arr[6] != arr[7] → Found single element!
   - Return arr[6] = 4
   ```
   Element 4 at index 6 has no matching neighbor → Single element found
   ```

### Additional Example Cases

1. **Edge Case - First Element Single**
```
Input:  [1, 2, 2, 3, 3]
Step 1: arr[0] != arr[1] → 1 != 2
Output: 1
```

2. **Edge Case - Last Element Single**  
```
Input:  [1, 1, 2, 2, 5]
Step 1: arr[4] != arr[3] → 5 != 2
Output: 5
```

3. **Single Element Array**
```
Input:  [7]
Step 1: n == 1
Output: 7
```

### Edge Cases Handling
1. **Array Size 1**
   ```
   Input: [42]
   Output: 42
   Explanation: Only one element exists, must be the single element
   ```

2. **Single Element at Start**
   ```
   Input: [3, 4, 4, 5, 5]
   Output: 3  
   Explanation: First element doesn't match second, so it's single
   ```

3. **Single Element at End**
   ```
   Input: [1, 1, 2, 2, 9]
   Output: 9
   Explanation: Last element doesn't match second-to-last, so it's single
   ```

Key Observations:
1. **Index Pattern Recognition**: The disruption in (even,odd) vs (odd,even) pairing guides the search
2. **Edge Case Optimization**: Handling boundaries first reduces search space complexity
3. **Binary Search Efficiency**: Each iteration eliminates half the remaining search space
4. **Neighbor Comparison**: Single element identification relies on neighbor differences

Sample Validation:
Input: [1, 1, 2, 2, 3, 3, 4, 5, 5, 6, 6]
Expected: 4
Output: 4

Key Points:
1. **Sorted Array Advantage**: Allows predictable pairing patterns analysis
2. **O(log n) Efficiency**: Binary search reduces time complexity significantly
3. **Pattern Recognition**: Understanding index patterns is crucial for elimination logic
4. **Boundary Handling**: Edge cases must be resolved before main algorithm

TEST CASES:
1. Input: [1, 1, 2, 2, 3, 3, 4, 5, 5, 6, 6]
   Expected: 4
   Output: 4

2. Input: [1, 2, 2, 3, 3]  
   Expected: 1
   Output: 1

3. Input: [1, 1, 2, 2, 3]
   Expected: 3
   Output: 3

4. Input: [5]
   Expected: 5
   Output: 5

5. Input: [1, 1, 2]
   Expected: 2
   Output: 2

 */