package Array.Leaders_In_Array;

import java.util.ArrayList;
import java.util.Collections;
/* 
# Leaders in Array
Find all the leaders in an array. An element is a leader if it's greater than all elements to its right.

# Intuition
We can find leaders by traversing from right to left, keeping track of the maximum element seen so far. Any element greater than this maximum is a leader.

## Basic Understanding
A leader is an element that is greater than all elements that come after it. For example, in array [10, 22, 12, 3, 0, 6], 22 is a leader because it's greater than all elements to its right.

## Key Observations with Examples

### Observation 1: Last Element Property
- The last element is always a leader since there are no elements to its right
- Example: In [16, 17, 4, 3, 5, 2], 2 is always a leader

### Observation 2: Right-to-Left Maximum Pattern
- If we track the maximum from right, any element greater than this maximum is a leader
- Example: In [16, 17, 4, 3, 5, 2]
```
From right: 2 → 5 → 3 → 4 → 17 → 16
Leaders: 17, 5, 2
```

## Step-by-Step Example
Let's work through array [10, 22, 12, 3, 0, 6]:

1. Initialize:
   max = 6 (last element)
   leaders = [6]

2. Check element 0:
   0 < 6 (not a leader)
   max remains 6

3. Check element 3:
   3 < 6 (not a leader)
   max remains 6

4. Check element 12:
   12 > 6 (is a leader)
   max becomes 12
   leaders = [6, 12]

5. Continue process...

## Special Cases

### Case 1: All Elements in Descending Order
Input: [5, 4, 3, 2, 1]
- Behavior: Every element is a leader
- Result: [5, 4, 3, 2, 1]

### Case 2: All Elements in Ascending Order
Input: [1, 2, 3, 4, 5]
- Behavior: Only last element is leader
- Result: [5]
*/

public class Approach2 {
    // Approach 2: Optimal approach to find leaders in array
    public static ArrayList<Integer> findLeaders(int[] arr, int n) {
        ArrayList<Integer> leaders = new ArrayList<>();
        
        // Last element is always a leader
        int currentMax = arr[n - 1];
        leaders.add(currentMax);
        
        // Traverse array from right to left
        for (int i = n - 2; i >= 0; i--) {
            // If current element is greater than max
            // it's a leader
            if (arr[i] > currentMax) {
                leaders.add(arr[i]);
                currentMax = arr[i];
            }
        }
        
        return leaders;
    }

    public static void main(String[] args) {
        // Example array
        int[] arr = {10, 22, 12, 3, 0, 6};
        int n = arr.length;

        // Find leaders
        ArrayList<Integer> result = findLeaders(arr, n);
        
        // Sort in descending order for standard output
        Collections.sort(result, Collections.reverseOrder());
        
        // Print results
        System.out.print("Leaders are: ");
        for (int leader : result) {
            System.out.print(leader + " ");
        }
    }
}

/* 
# Algorithm
1. Initialize empty ArrayList for leaders
2. Add last element as leader
3. Initialize currentMax as last element
4. Traverse array from right to left (n-2 to 0)
5. If current element > currentMax:
   - Add to leaders
   - Update currentMax
   6. Return leaders list

### Time Complexity Breakdown
1. Array Traversal: O(n)
2. Adding to ArrayList: O(1)
3. Final Sorting: O(k log k) where k is number of leaders

Total: O(n) + O(k log k)

### Space Complexity Breakdown
1. Auxiliary Space
- Output ArrayList: O(k) where k is number of leaders
- Variables: O(1)
Total: O(k)
2. Input Space: O(n)

# Advantages
1. O(n) time complexity - single pass through array
2. In-place algorithm - no extra space except for output
3. Simple implementation
4. Handles all edge cases efficiently

# Limitations
1. Cannot handle empty arrays without modification
2. Requires additional sorting if specific order is needed
3. Not suitable for streaming data

# Step-by-Step Process with Dry Run

## Example Input: [10, 22, 12, 3, 0, 6]
Let me break this down with a detailed table and explanation 

### Detailed Execution Table
```
Step | Index | Current Element | Max So Far | Leaders List | Comparison        | Action Taken
-----|-------|----------------|------------|--------------|-------------------|-------------
1    | 5     | 6              | 6          | [6]          | First element    | Add 6 (last element is always leader)
2    | 4     | 0              | 6          | [6]          | 0 < 6           | Skip (not a leader)
3    | 3     | 3              | 6          | [6]          | 3 < 6           | Skip (not a leader)
4    | 2     | 12             | 12         | [6, 12]      | 12 > 6          | Add 12 & update max
5    | 1     | 22             | 22         | [6, 12, 22]  | 22 > 12         | Add 22 & update max
6    | 0     | 10             | 22         | [6, 12, 22]  | 10 < 22         | Skip (not a leader)
```

### Step-by-Step Process Explanation:

1. **Initialization (Step 1)**
   - Current Element: 6 (last element)
   - Action: Add to leaders list automatically
   - Result: leaders = [6]
   - Reason: Last element is always a leader

2. **First Check (Step 2)**
   - Current Element: 0
   - Compare: 0 < 6 (max so far)
   - Action: Skip
   - Result: leaders = [6]
   - Reason: Not greater than current maximum

3. **Second Check (Step 3)**
   - Current Element: 3
   - Compare: 3 < 6 (max so far)
   - Action: Skip
   - Result: leaders = [6]
   - Reason: Not greater than current maximum

4. **Third Check (Step 4)**
   - Current Element: 12
   - Compare: 12 > 6 (max so far)
   - Action: Add to leaders & update max
   - Result: leaders = [6, 12]
   - Reason: Greater than current maximum

5. **Fourth Check (Step 5)**
   - Current Element: 22
   - Compare: 22 > 12 (max so far)
   - Action: Add to leaders & update max
   - Result: leaders = [6, 12, 22]
   - Reason: Greater than current maximum

6. **Final Check (Step 6)**
   - Current Element: 10
   - Compare: 10 < 22 (max so far)
   - Action: Skip
   - Result: leaders = [6, 12, 22]
   - Reason: Not greater than current maximum

### Final Processing:
- Before return: [6, 12, 22]
- After sorting in descending order: [22, 12, 6]

### Memory State at Each Step:
```
Initial Array:    [10, 22, 12, 3, 0, 6]
Step 1 Memory:    max = 6,    leaders = [6]
Step 2 Memory:    max = 6,    leaders = [6]
Step 3 Memory:    max = 6,    leaders = [6]
Step 4 Memory:    max = 12,   leaders = [6, 12]
Step 5 Memory:    max = 22,   leaders = [6, 12, 22]
Step 6 Memory:    max = 22,   leaders = [6, 12, 22]
```

### Variable Tracking Through Execution:
```
i (Index) Movement: 5 → 4 → 3 → 2 → 1 → 0
max Value Changes: 6 → 6 → 6 → 12 → 22 → 22
List Size Changes: 1 → 1 → 1 → 2 → 3 → 3
```

This detailed breakdown shows how the algorithm:
1. Always starts with the last element
2. Maintains a running maximum
3. Only adds elements that are greater than the current maximum
4. Works in a single pass from right to left
5. Finally sorts the result in descending order


### Edge Cases Handling
1. **Single Element Array**
   ```
   Input: [5]
   Output: [5]
   All single elements are leaders
   ```

2. **All Equal Elements**
   ```
   Input: [3, 3, 3, 3]
   Output: [3]
   Only last element is considered leader
   ```

Test Cases:
1. Input: [16, 17, 4, 3, 5, 2]
   Expected: [17, 5, 2]
   Output: [17, 5, 2]
2. Input: [1, 2, 3, 4, 5]
   Expected: [5]
   Output: [5]
3. Input: [5, 4, 3, 2, 1]
   Expected: [5, 4, 3, 2, 1]
   Output: [5, 4, 3, 2, 1]
 */