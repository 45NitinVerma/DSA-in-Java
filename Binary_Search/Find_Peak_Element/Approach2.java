package Binary_Search.Find_Peak_Element;

import java.util.ArrayList;
import java.util.Arrays;

/* 
Find Peak Element
# Problem: A peak element is an element that is strictly greater than its neighbors.
Given a 0-indexed integer array nums, find a peak element, and return its index. If the array contains multiple peaks, return the index to any of the peaks.
You may imagine that nums[-1] = nums[n] = -∞. In other words, an element is always considered to be strictly greater than a neighbor that is outside the array.
You must write an algorithm that runs in O(log n) time.

## Intution: The primary objective of the Binary Search algorithm is to efficiently determine the appropriate half to eliminate, thereby reducing the search space by half. It does this by determining a specific condition that ensures that the target is not present in that half.
Until now, we have found how to identify if an element is a peak. But since binary search works by reducing the search space by half, we have to find a way to identify the halves and then eliminate them accordingly.

!! How to identify the halves:
        arr = [1,  2,  3,  4,  5,  6,  7,   8,        5,    1]
               -------------------------    ↑     -------------
                  left half with          peak    right half with 
                 increasing order        element   decreasing order

By observing the above image, we can clearly notice a striking distinction between the left and right halves of the peak element in the array. 
 - The left half of the peak element has an increasing order. This means for every index i, arr[i-1] < arr[i].
 - On the contrary, the right half of the peak element has a decreasing order. This means for every index i, arr[i+1] < arr[i].

Now, using the above observation, we can easily identify the left and right halves, just by checking the property of the current index, i, like the following:
 - If arr[i] > arr[i-1]: we are in the left half.
 - If arr[i] > arr[i+1]: we are in the right half.

!! How to eliminate the halves accordingly:
 - If we are in the left half of the peak element, we have to eliminate this left half (i.e.    
low = mid+1). Because our peak element appears somewhere on the right side.
 - If we are in the right half of the peak element, we have to eliminate this right half (i.e.high = mid-1). Because our peak element appears somewhere on the left side.

Now, let’s see if these conditions are enough to handle the array with multiple peaks. Based on the observation, in an array with multiple peaks, an index has four possible positions as follows:
 - The index is a common point where a decreasing sequence ends and an increasing sequence begins.
 - The index might be on the left half.
 - The index might be the peak itself.
 - The index might be on the right half.

Until now, we have found how to identify if an element is a peak and how to identify the halves and then eliminate them accordingly. So, the last 3 cases have been resolved. We have to find out how the first case should be handled.
If an index is a common point where a decreasing sequence ends and an increasing sequence begins, we can actually eliminate either the left or right half. Because both halves of such an index contain a peak. 
So, we decide to merge this case with the condition If arr[i+1] < arr[i]. You can choose otherwise as well.

## Key Observations with Examples

### Observation 1: Peak Element Properties
A peak element at index i satisfies: arr[i-1] < arr[i] > arr[i+1]
* At boundaries, we assume elements outside array are -∞
* Multiple peaks can exist, we need to find any one

### Observation 2: Array Half Identification
```
arr = [1,  2,  3,  4,  5,  6,  7,   8,        5,    1]
       -------------------------    ↑     -------------
          left half with          peak    right half with 
         increasing order        element   decreasing order
```

Example: In the above array, index 7 (value 8) is a peak element.

### Observation 3: Left Half Characteristics
If arr[i] > arr[i-1]: we are in the left half (increasing sequence)
* This means a peak exists somewhere to the right
* We should eliminate the left half and search right

### Observation 4: Right Half Characteristics  
If arr[i] > arr[i+1]: we are in the right half (decreasing sequence)
* This means a peak exists somewhere to the left
* We should eliminate the right half and search left

### Observation 5: Edge Cases Handling
```
Single element: [5] → index 0 is peak
First element peak: [8,3,2,1] → index 0 is peak  
Last element peak: [1,2,3,8] → index 3 is peak
```

## Step-by-Step Example
Let's work through array [1, 2, 3, 4, 5, 6, 7, 8, 5, 1]:

1. **Initial Setup:**
   ```
   Array: [1, 2, 3, 4, 5, 6, 7, 8, 5, 1]
   Index:  0  1  2  3  4  5  6  7  8  9
   Check edge cases: arr[0]=1 < arr[1]=2, arr[9]=1 < arr[8]=5
   So peak is not at boundaries
   Set low=1, high=8 (excluding boundary indices)
   ```

2. **First Iteration:**
   ```
   low=1, high=8, mid=(1+8)/2=4
   arr[4]=5, arr[3]=4, arr[5]=6
   arr[4] > arr[3] → we are in left half (increasing)
   Peak exists to the right, so low = mid+1 = 5
   ```

3. **Second Iteration:**
   ```
   low=5, high=8, mid=(5+8)/2=6
   arr[6]=7, arr[5]=6, arr[7]=8
   arr[6] > arr[5] → we are in left half (increasing)
   Peak exists to the right, so low = mid+1 = 7
   ```

4. **Third Iteration:**
   ```
   low=7, high=8, mid=(7+8)/2=7
   arr[7]=8, arr[6]=7, arr[8]=5
   arr[7] > arr[6] AND arr[7] > arr[8] → Peak found!
   Return index 7
   ```

## Special Cases

### Case 1: Single Element Array
Input: [5]
- Behavior: Only one element exists
- Result: Return index 0

### Case 2: Peak at First Index
Input: [8, 3, 2, 1]
- Behavior: arr[0] > arr[1], so first element is peak
- Result: Return index 0

### Case 3: Peak at Last Index
Input: [1, 2, 3, 8]
- Behavior: arr[n-1] > arr[n-2], so last element is peak
- Result: Return index 3

### Case 4: Multiple Peaks
Input: [1, 3, 2, 4, 1]
- Behavior: Both index 1 and 3 are peaks
- Result: Return any one peak index (algorithm will find one)
*/

public class Approach2 {
    // Approach 2: Using Binary Search
    public static int findPeakElement(ArrayList<Integer> arr) {
        int n = arr.size(); // Get size of array

        // Edge Case 1: Single element array
        // If only one element, it's automatically a peak
        if (n == 1) return 0;
        
        // Edge Case 2: First element is peak
        // Check if first element is greater than second element
        if (arr.get(0) > arr.get(1)) return 0;
        
        // Edge Case 3: Last element is peak  
        // Check if last element is greater than second last element
        if (arr.get(n - 1) > arr.get(n - 2)) return n - 1;

        // Binary search in the range [1, n-2]
        // We exclude first and last indices as they're handled above
        int low = 1, high = n - 2;
        
        while (low <= high) {
            // Calculate middle index
            int mid = (low + high) / 2;

            // Check if arr[mid] is the peak element
            // Peak condition: arr[mid] > both neighbors
            if (arr.get(mid - 1) < arr.get(mid) && arr.get(mid) > arr.get(mid + 1))
                return mid;

            // If we are in the left half (increasing sequence):
            // arr[mid] > arr[mid-1] means we're on ascending slope
            // Peak must be to the right, so eliminate left half
            if (arr.get(mid) > arr.get(mid - 1)) {
                low = mid + 1;
            }
            // If we are in the right half (decreasing sequence):
            // Or arr[mid] is a common point between sequences
            // Peak must be to the left, so eliminate right half
            else {
                high = mid - 1;
            }
        }
        
        // This should never be reached for valid input
        return -1;
    }

    // Main method for testing
    public static void main(String[] args) {
        // Example input array
        ArrayList<Integer> arr = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 5, 1));

        // Call the method and get the peak index
        int peakIndex = findPeakElement(arr);

        // Display the result
        System.out.println("Array: " + arr);
        System.out.println("The peak is at index: " + peakIndex);
        System.out.println("Peak element value: " + arr.get(peakIndex));
    }
}
    
/*
# Algorithm
1. **Handle Edge Cases**: Check for single element, first element peak, and last element peak
2. **Initialize Binary Search**: Set low=1, high=n-2 (excluding boundary indices already checked)
3. **Calculate Mid Point**: mid = (low + high) / 2
4. **Check Peak Condition**: If arr[mid] > arr[mid-1] AND arr[mid] > arr[mid+1], return mid
5. **Determine Search Direction**: 
   - If arr[mid] > arr[mid-1]: we're in left half, search right (low = mid+1)
   - Else: we're in right half, search left (high = mid-1)
6. **Repeat**: Continue until peak is found

### Time Complexity Breakdown per Step
1. **Edge case checks**: O(1)
2. **Binary search loop**: O(log n) - search space halved each iteration
3. **Peak condition check**: O(1) per iteration
4. **Update pointers**: O(1) per iteration

Total: **O(log n)**

### Space Complexity Breakdown
1. **Auxiliary Space**: O(1) - only using constant extra variables (low, high, mid)
2. **Input Space**: O(n) - input array space
Total: **O(1)** auxiliary space

# Advantages

1. **Optimal Time Complexity**: O(log n) is much better than O(n) linear search
2. **Guaranteed Solution**: Algorithm always finds a peak (mathematical guarantee)
3. **Handles Multiple Peaks**: Works correctly even when multiple peaks exist
4. **Space Efficient**: Uses only constant extra space
5. **Edge Case Robust**: Handles all boundary conditions properly

# Limitations

1. **Finds Any Peak**: Doesn't guarantee finding the highest peak, just any peak
2. **Integer Overflow**: Mid calculation could overflow for very large arrays
3. **ArrayList Overhead**: get() operations have slight overhead compared to arrays
4. **Requires Distinct Neighbors**: Assumes peak is strictly greater than neighbors

# Potential Improvements

1. **Use Arrays Instead of ArrayList**: Direct array access would be slightly faster
2. **Overflow-Safe Mid Calculation**: Use `low + (high - low) / 2` instead of `(low + high) / 2`
3. **Find Maximum Peak**: Modify to find the highest peak among all peaks if needed
4. **Generic Implementation**: Make it work with any Comparable type, not just integers

# Step-by-Step Process with Detailed Dry Run

## Example Input: [1, 2, 3, 4, 5, 6, 7, 8, 5, 1]

### Comprehensive Execution Table
```
Step | Input Array              | n | low | high | mid | arr[mid-1] | arr[mid] | arr[mid+1] | Peak Check | Direction Check | Action Taken | Explanation
-----|--------------------------|---|-----|------|-----|------------|----------|------------|------------|-----------------|--------------|-------------
0    | [1,2,3,4,5,6,7,8,5,1]   |10 |  -  |  -   |  -  |     -      |    -     |     -      |     -      |       -         | Edge Cases   | Check n==1? No. Check arr[0]>arr[1]? 1>2? No. Check arr[9]>arr[8]? 1>5? No.
1    | [1,2,3,4,5,6,7,8,5,1]   |10 |  1  |  8   |  4  |     4      |    5     |     6      | 4<5 but 5<6| 5>4 (left half) | low = 5      | Not a peak. In increasing sequence, peak is to the right.
2    | [1,2,3,4,5,6,7,8,5,1]   |10 |  5  |  8   |  6  |     6      |    7     |     8      | 6<7 but 7<8| 7>6 (left half) | low = 7      | Not a peak. Still in increasing sequence, peak is to the right.
3    | [1,2,3,4,5,6,7,8,5,1]   |10 |  7  |  8   |  7  |     7      |    8     |     5      | 7<8 and 8>5|       -         | Return 7     | Peak found! arr[7]=8 is greater than both neighbors.
```

### Input-Output Analysis Table
```
Test Case | Input Array                    | Expected Output | Actual Output | Peak Element Value | Explanation
----------|--------------------------------|-----------------|---------------|-------------------|-------------
1         | [1,2,3,4,5,6,7,8,5,1]         | 7               | 7             | 8                 | Peak at index 7, value 8 > neighbors 7,5
2         | [1]                            | 0               | 0             | 1                 | Single element is always peak
3         | [5,3,2,1]                      | 0               | 0             | 5                 | First element 5 > second element 3
4         | [1,2,3,5]                      | 3               | 3             | 5                 | Last element 5 > previous element 3
5         | [1,3,2,4,1]                    | 1 or 3          | 1             | 3                 | Multiple peaks exist, algorithm finds index 1
6         | [1,2,1,3,5,6,4]               | 1,4,5 possible  | 5             | 6                 | Multiple peaks, algorithm finds index 5
```

### Detailed Step-by-Step Process Explanation

#### **STEP 0: Edge Cases Validation**

**Input Analysis:**
- Array: [1, 2, 3, 4, 5, 6, 7, 8, 5, 1]
- Array size n = 10

**Process Points:**
1. **Check if array has single element**: n == 1? → 10 == 1? → **False**
2. **Check if first element is peak**: arr[0] > arr[1]? → 1 > 2? → **False**
3. **Check if last element is peak**: arr[n-1] > arr[n-2]? → arr[9] > arr[8]? → 1 > 5? → **False**
4. **Conclusion**: No edge case peaks found, proceed with binary search
5. **Initialize search bounds**: low = 1, high = n-2 = 8

```
Visual State:
Array: [1, 2, 3, 4, 5, 6, 7, 8, 5, 1]
Index:  0  1  2  3  4  5  6  7  8  9
        ×  ←─────────────────────→  ×
      skip        search range    skip
```

---

#### **STEP 1: First Binary Search Iteration**

**Current State:**
- low = 1, high = 8
- Calculate mid = (1 + 8) / 2 = 4

**Process Points:**
1. **Access elements**: arr[mid-1] = arr[3] = 4, arr[mid] = arr[4] = 5, arr[mid+1] = arr[5] = 6
2. **Peak condition check**: Is arr[3] < arr[4] AND arr[4] > arr[5]?
   - Left neighbor: 4 < 5? → **True**
   - Right neighbor: 5 > 6? → **False**
3. **Result**: Not a peak because 5 < 6
4. **Direction analysis**: Since arr[4] > arr[3] → 5 > 4 → **True**
5. **Interpretation**: We are in the left half (increasing sequence)
6. **Action**: Peak must be to the right, so eliminate left half
7. **Update bounds**: low = mid + 1 = 4 + 1 = 5

```
Visual State:
Array: [1, 2, 3, 4, 5, 6, 7, 8, 5, 1]
Index:  0  1  2  3  4  5  6  7  8  9
           ×  ×  ×  ↑  ←───────────→
         eliminated  mid    new range
```

---

#### **STEP 2: Second Binary Search Iteration**

**Current State:**
- low = 5, high = 8
- Calculate mid = (5 + 8) / 2 = 6

**Process Points:**
1. **Access elements**: arr[mid-1] = arr[5] = 6, arr[mid] = arr[6] = 7, arr[mid+1] = arr[7] = 8
2. **Peak condition check**: Is arr[5] < arr[6] AND arr[6] > arr[7]?
   - Left neighbor: 6 < 7? → **True**
   - Right neighbor: 7 > 8? → **False**
3. **Result**: Not a peak because 7 < 8
4. **Direction analysis**: Since arr[6] > arr[5] → 7 > 6 → **True**
5. **Interpretation**: Still in the left half (increasing sequence)
6. **Action**: Peak must be to the right, so eliminate left half
7. **Update bounds**: low = mid + 1 = 6 + 1 = 7

```
Visual State:
Array: [1, 2, 3, 4, 5, 6, 7, 8, 5, 1]
Index:  0  1  2  3  4  5  6  7  8  9
           ×  ×  ×  ×  ×  ↑  ←─────→
                     eliminated  new range
```

---

#### **STEP 3: Third Binary Search Iteration - Peak Found!**

**Current State:**
- low = 7, high = 8
- Calculate mid = (7 + 8) / 2 = 7

**Process Points:**
1. **Access elements**: arr[mid-1] = arr[6] = 7, arr[mid] = arr[7] = 8, arr[mid+1] = arr[8] = 5
2. **Peak condition check**: Is arr[6] < arr[7] AND arr[7] > arr[8]?
   - Left neighbor: 7 < 8? → **True**
   - Right neighbor: 8 > 5? → **True**
3. **Result**: **PEAK FOUND!** Both conditions satisfied
4. **Final answer**: Return index 7
5. **Peak element value**: arr[7] = 8

```
Visual State:
Array: [1, 2, 3, 4, 5, 6, 7, 8, 5, 1]
Index:  0  1  2  3  4  5  6  7  8  9
           ×  ×  ×  ×  ×  ×  ★  
                              PEAK!
```

### Summary of Each Iteration

#### **Iteration 1 Analysis:**
- **Search Space**: Indices 1 to 8 (7 elements)
- **Mid Element**: Index 4, Value 5
- **Decision Logic**: 5 > 4 (increasing trend) → search right half
- **Elimination**: Removed indices 1,2,3,4 (4 elements)
- **Remaining Space**: Indices 5 to 8 (4 elements)

#### **Iteration 2 Analysis:**
- **Search Space**: Indices 5 to 8 (4 elements)
- **Mid Element**: Index 6, Value 7
- **Decision Logic**: 7 > 6 (still increasing) → search right half
- **Elimination**: Removed indices 5,6 (2 elements)
- **Remaining Space**: Indices 7 to 8 (2 elements)

#### **Iteration 3 Analysis:**
- **Search Space**: Indices 7 to 8 (2 elements)
- **Mid Element**: Index 7, Value 8
- **Decision Logic**: 8 > 7 AND 8 > 5 → Peak found!
- **Final Result**: Index 7 with peak value 8

### Binary Search Elimination Pattern
```
Initial:    [1, 2, 3, 4, 5, 6, 7, 8, 5, 1]  (Search: indices 1-8)
After It1:  [×, ×, ×, ×, ×, 6, 7, 8, 5, ×]  (Search: indices 5-8)
After It2:  [×, ×, ×, ×, ×, ×, ×, 8, 5, ×]  (Search: indices 7-8)
Final:      [×, ×, ×, ×, ×, ×, ×, ★, ×, ×]  (Peak found at index 7)
```

### Additional Example Cases

1. **Early Peak Case**
```
Input:  [3, 2, 1]
Step 1: Check arr[0]=3 > arr[1]=2 → Peak at index 0
Output: 0
```

2. **Late Peak Case**
```
Input:  [1, 2, 5]
Step 1: Check arr[2]=5 > arr[1]=2 → Peak at index 2  
Output: 2
```

3. **Middle Peak Case**
```
Input:  [1, 3, 2]
Step 1: Edge cases don't apply
Step 2: low=1, high=1, mid=1
Step 3: arr[1]=3 > arr[0]=1 AND arr[1]=3 > arr[2]=2 → Peak at index 1
Output: 1
```

### Edge Cases Handling

1. **Single Element Array**
   ```
   Input: [42]
   Output: 0
   Explanation: Only one element, automatically a peak
   ```

2. **Two Element Array - First Peak**
   ```
   Input: [5, 3]
   Output: 0  
   Explanation: arr[0] > arr[1], so first element is peak
   ```

3. **Two Element Array - Second Peak**
   ```
   Input: [3, 5]
   Output: 1
   Explanation: arr[1] > arr[0], so last element is peak
   ```

4. **All Equal Elements (if allowed)**
   ```
   Input: [4, 4, 4, 4]
   Output: Algorithm behavior depends on strict inequality requirement
   ```

Key Observations:
1. **Binary search eliminates half the search space in each iteration**
2. **Peak element is guaranteed to exist due to boundary conditions (nums[-1] = nums[n] = -∞)**
3. **Algorithm works by following the increasing slope toward a peak**
4. **Edge cases handle boundary peaks efficiently without extra loop iterations**
5. **Time complexity is logarithmic, making it efficient for large arrays**

Sample Validation:
Input: [1, 2, 3, 4, 5, 6, 7, 8, 5, 1]
Expected: Any valid peak index (7 in this case)
Output: 7

Key Points:
1. **The algorithm guarantees finding a peak, not necessarily the global maximum**
2. **Binary search approach reduces O(n) linear search to O(log n)**
3. **Edge case handling prevents array index out of bounds errors**
4. **The search space is strategically reduced by analyzing slope direction**
5. **Mathematical proof ensures a peak always exists in the chosen search direction**

TEST CASES:
1. Input: [1, 2, 3, 4, 5, 6, 7, 8, 5, 1]
   Expected: 7
   Output: 7

2. Input: [1, 2, 1, 3, 5, 6, 4]
   Expected: 1, 5, or any valid peak
   Output: 5

3. Input: [1]
   Expected: 0
   Output: 0

4. Input: [1, 2]
   Expected: 1
   Output: 1

5. Input: [2, 1]
   Expected: 0
   Output: 0
 */