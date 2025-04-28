package Array.Merge_Intervals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/* 
# Merge Intervals
# Problem: Given an array of intervals where intervals[i] = [starti, endi], merge all overlapping intervals, and return an array of the non-overlapping intervals that cover all the intervals in the input.
Input: intervals = [[1,3],[2,6],[8,10],[15,18]]
Output: [[1,6],[8,10],[15,18]]
Explanation: Since intervals [1,3] and [2,6] overlap, merge them into [1,6].

## Intution: In this article, in order to understand the concept in a better way, we have assumed the intervals in the form (start, end). The first and second elements of each interval can be assumed as the start and end of that particular interval. We are going to use this convention i.e. the names, start and end, throughout the article.

The intuition of this approach is pretty straightforward. We are just grouping close intervals by sorting the given array. After that, we merge an interval with the other by checking if one can be a part of the other interval. For this checking, we are first selecting a particular interval using a loop and then we are comparing the rest of the intervals using another loop.

## Key Observations with Examples

### Observation 1: Sorting Simplifies the Problem
[Sorting intervals by start time ensures that overlapping intervals will be adjacent]
* When intervals are sorted by start time, we only need to compare each interval with the previous merged result
* Comparing arbitrary intervals becomes unnecessary

Example:
```
Before sorting: [[1,3], [8,10], [2,6], [15,18]]
After sorting:  [[1,3], [2,6], [8,10], [15,18]]
```

### Observation 2: Overlapping Condition
[Two intervals overlap when the start of the second interval is less than or equal to the end of the first interval]

Example:
```
Intervals [1,3] and [2,6] overlap because 2 ≤ 3
Intervals [3,6] and [7,9] don't overlap because 7 > 6
```

### Observation 3: Merged Interval Boundaries
[When merging two intervals, the resulting interval starts at the minimum of both start times and ends at the maximum of both end times]

Example:
```
Merging [1,3] and [2,6] results in [1,6]
- Take minimum of start times: min(1,2) = 1
- Take maximum of end times: max(3,6) = 6
```

### Observation 4: Two-Pointer Technique Efficiency
[Using nested loops lets us group multiple consecutive overlapping intervals in one pass]
* The outer loop (i) selects the current interval
* The inner loop (j) extends the end time as far as possible

## Step-by-Step Example
Let's work through intervals = [[1,3],[2,6],[8,10],[15,18]]:

1. Step One:
   ```
   Sort intervals by start time:
   [[1,3], [2,6], [8,10], [15,18]]
   ```

2. Step Two:
   ```
   i=0: Select interval [1,3]
   start=1, end=3
   
   j=1: Check [2,6]
   2 <= 3? Yes, they overlap
   Update end = max(3,6) = 6
   
   j=2: Check [8,10]
   8 <= 6? No, they don't overlap
   Break inner loop
   
   Add [1,6] to result
   ```

3. Step Three:
   ```
   i=1: Select interval [2,6]
   Already covered by previous merged interval [1,6]
   Skip
   ```

4. Step Four:
   ```
   i=2: Select interval [8,10]
   start=8, end=10
   
   j=3: Check [15,18]
   15 <= 10? No, they don't overlap
   Break inner loop
   
   Add [8,10] to result
   ```

5. Step Five:
   ```
   i=3: Select interval [15,18]
   start=15, end=18
   
   No more intervals to check
   
   Add [15,18] to result
   ```

6. Final result:
   ```
   [[1,6], [8,10], [15,18]]
   ```

## Special Cases

### Case 1: All Intervals Overlap
Input: [[1,4], [2,5], [3,6], [4,7]]
- Behavior: All intervals overlap with at least one other interval
- Result: A single merged interval [1,7]

### Case 2: No Overlapping Intervals
Input: [[1,2], [3,4], [5,6]]
- Behavior: No intervals overlap
- Result: Input array is returned unchanged

### Case 3: Single Interval Contained Within Another
Input: [[1,10], [2,5], [3,4]]
- Behavior: The larger interval completely contains the smaller ones
- Result: Single interval [1,10]
*/
public class Approach1 {
    // Approach 1: Two-pointer approach with nested loops
    public static List<List<Integer>> mergeOverlappingIntervals(int[][] arr) {
        int n = arr.length; // size of the array
        
        // Sort the intervals based on start time (first element of each interval)
        Arrays.sort(arr, new Comparator<int[]>() {
            public int compare(int[] a, int[] b) {
                return a[0] - b[0];
            }
        });
        // Can also use lambda: Arrays.sort(arr, (a, b) -> a[0] - b[0]);

        // List to store the merged intervals
        List<List<Integer>> ans = new ArrayList<>();

        for (int i = 0; i < n; i++) { // Outer loop to select each interval
            int start = arr[i][0]; // Start time of current interval
            int end = arr[i][1];   // End time of current interval

            // Skip intervals that are already included in the last merged interval
            if (!ans.isEmpty() && end <= ans.get(ans.size() - 1).get(1)) {
                continue;
            }

            // Inner loop to check and merge with subsequent overlapping intervals
            for (int j = i + 1; j < n; j++) {
                if (arr[j][0] <= end) {
                    // If next interval starts before current end, merge by taking max end time
                    end = Math.max(end, arr[j][1]);
                } else {
                    // Once we find a non-overlapping interval, we can stop
                    break;
                }
            }
            
            // Add the merged interval to our answer list
            ans.add(Arrays.asList(start, end));
        }
        
        return ans;
    }

    // Main method for testing
    public static void main(String[] args) {
        // Example input: [[1,3], [8,10], [2,6], [15,18]]
        int[][] arr = {{1, 3}, {8, 10}, {2, 6}, {15, 18}};
        
        // Call the mergeOverlappingIntervals method
        List<List<Integer>> ans = mergeOverlappingIntervals(arr);
        
        // Display the result
        System.out.print("The merged intervals are: \n");
        for (List<Integer> it : ans) {
            System.out.print("[" + it.get(0) + ", " + it.get(1) + "] ");
        }
        System.out.println();
        
        // Expected output: [1, 6] [8, 10] [15, 18]
    }
}
/*
# Algorithm
1. First, we will group the closer intervals by sorting the given array of intervals(if it is not already sorted).
2. After that, we will select one interval at a time using a loop(say i) and insert it into our answer list(if the answer list is empty or the current interval cannot be merged with the last interval of the answer list). While traversing and inserting we will skip the intervals that lie in the last inserted interval of our answer list.
3. Now, for each interval arr[i], using another loop (say j) we are going to check the rest of the intervals(i.e. From index i+1 to n-1) if they can be merged with the selected interval.
4. Inside loop j, we will continue to merge all the intervals that lie in the selected interval. 
 - How to check if the current interval can be merged with the selected interval:
    We will compare the current interval’s start with the end of the selected interval. If the start is smaller or equal to the end, we can conclude the current interval can be a part of the selected interval. So, we will update the selected interval’s end with the maximum(current interval’s end, selected interval’s end) in the answer list(not in the original array).
5. We will break out of loop j, from the first element that cannot be a part of the selected interval.
 - How to check if the current interval is not a part of the selected interval:
    We will compare the current interval’s start with the end of the selected interval. If the start is greater than the end, we can conclude the current interval cannot be a part of the selected interval.
6. Finally, we will return the answer list.

### Time Complexity Breakdown per Step
1. Sorting the intervals: O(n log n)
2. Iterating through the intervals (i loop): O(n)
3. Checking subsequent intervals (j loop): O(n) in worst case 
   - Though this is nested, each interval is processed at most twice
   - Once as the outer loop interval, once as an inner loop interval

Total: O(n log n) due to sorting

### Space Complexity Breakdown
1. Auxiliary Space: O(n) for storing the result list
   - In worst case, no intervals merge and we store all original intervals
2. Input Space: O(n) for the input array
   - Not considered in space complexity analysis as it's given

Total: O(n)

# Advantages
1. Intuitive and straightforward implementation
2. Handles all edge cases correctly
3. Produces sorted output (intervals are in ascending order by start time)
4. Works well for small to medium-sized inputs

# Limitations
1. Not optimal for sparse intervals (many non-overlapping intervals)
2. Uses nested loops which might be intimidating
3. Creates new objects for the result list, which may have memory overhead
4. Not optimal for extremely large inputs due to O(n log n) time complexity

# Potential Improvements
1. Single-pass implementation: The problem can be solved in a single pass without the inner loop
2. In-place modification: For some applications, modifying the original array might be preferable
3. Linear interval representation: For certain applications, intervals might be more efficiently stored as events (start/end)
4. Early termination: Add additional checks to exit early when no more merges are possible

# Step-by-Step Process with Dry Run
# Merge Intervals - Detailed Execution Table

## Input: [[1,3], [8,10], [2,6], [15,18]]

### Step 1: Sort the array by start times
```
Original Array: [[1,3], [8,10], [2,6], [15,18]]
Sorted Array:   [[1,3], [2,6], [8,10], [15,18]]
```

### Detailed Execution Table

| Step | Action | Current Interval | Result List | Check Interval | Overlap Check | New End Value | Explanation |
|------|--------|-----------------|------------|---------------|--------------|--------------|-------------|
| 1 | Sort array | - | [] | - | - | - | Sort by start time |
| 2 | i=0 | [1,3] | [] | - | - | - | Select first interval |
| 3 | Check inner loop | [1,3] | [] | [2,6] (j=1) | 2≤3? Yes | max(3,6)=6 | Intervals overlap, update end |
| 4 | Continue inner loop | [1,6] | [] | [8,10] (j=2) | 8≤6? No | - | No overlap, exit inner loop |
| 5 | Add to result | [1,6] | [[1,6]] | - | - | - | Add merged interval to result |
| 6 | i=1 | [2,6] | [[1,6]] | - | end(6)≤lastEnd(6)? Yes | - | Skip (already included in result) |
| 7 | i=2 | [8,10] | [[1,6]] | - | - | - | Select next interval |
| 8 | Check inner loop | [8,10] | [[1,6]] | [15,18] (j=3) | 15≤10? No | - | No overlap, exit inner loop |
| 9 | Add to result | [8,10] | [[1,6], [8,10]] | - | - | - | Add interval to result |
| 10 | i=3 | [15,18] | [[1,6], [8,10]] | - | - | - | Select last interval |
| 11 | Check inner loop | [15,18] | [[1,6], [8,10]] | None (j=4) | - | - | No more intervals to check |
| 12 | Add to result | [15,18] | [[1,6], [8,10], [15,18]] | - | - | - | Add interval to result |
| 13 | Return | - | [[1,6], [8,10], [15,18]] | - | - | - | Return final result |

## Step-by-Step Process Explanation in Points

### 1. Initialization and Sorting
- **Initial Input**: Array `[[1,3], [8,10], [2,6], [15,18]]`
- **After Sorting**: Array becomes `[[1,3], [2,6], [8,10], [15,18]]`
- **Explanation**: We sort by the first element (start time) of each interval to ensure overlapping intervals are adjacent
- **Result List**: Empty `[]`

### 2. Processing First Interval [1,3] (i=0)
- **Current Interval**: `[1,3]` (start=1, end=3)
- **Check Next Interval**: `[2,6]` (j=1)
- **Overlap Check**: Is 2 ≤ 3? Yes, intervals overlap
- **Action**: Update end = max(3,6) = 6
- **Current Interval Becomes**: `[1,6]`
- **Check Next Interval**: `[8,10]` (j=2)
- **Overlap Check**: Is 8 ≤ 6? No, intervals don't overlap
- **Action**: Exit inner loop, add `[1,6]` to result
- **Result List**: `[[1,6]]`

### 3. Processing Second Interval [2,6] (i=1)
- **Current Interval**: `[2,6]` (start=2, end=6)
- **Skip Check**: Is 6 ≤ 6? Yes (interval is already included in last result interval)
- **Action**: Skip this interval entirely
- **Result List**: Still `[[1,6]]`

### 4. Processing Third Interval [8,10] (i=2)
- **Current Interval**: `[8,10]` (start=8, end=10)
- **Skip Check**: Is 10 ≤ 6? No (interval is not included in last result interval)
- **Check Next Interval**: `[15,18]` (j=3)
- **Overlap Check**: Is 15 ≤ 10? No, intervals don't overlap
- **Action**: Exit inner loop, add `[8,10]` to result
- **Result List**: `[[1,6], [8,10]]`

### 5. Processing Fourth Interval [15,18] (i=3)
- **Current Interval**: `[15,18]` (start=15, end=18)
- **Skip Check**: Is 18 ≤ 10? No (interval is not included in last result interval)
- **Check Next Interval**: None (j=4, out of bounds)
- **Action**: Add `[15,18]` to result
- **Result List**: `[[1,6], [8,10], [15,18]]`

### 6. Final Result
- **Output**: `[[1,6], [8,10], [15,18]]`
- **Verification**: 
  - `[1,3]` and `[2,6]` merged into `[1,6]`
  - `[8,10]` remains unchanged
  - `[15,18]` remains unchanged

## Visual Step-by-Step Process

### Step 1: Sort the intervals
```
Before: [1,3] [8,10] [2,6] [15,18]
After:  [1,3] [2,6] [8,10] [15,18]
```

### Step 2: Process interval [1,3]
```
Current: [1,3]
Check [2,6]: Overlaps because 2 ≤ 3
Merge:  [1,6]  (taking max end)
Check [8,10]: No overlap because 8 > 6
Result: [[1,6]]
```

### Step 3: Process interval [2,6]
```
Current: [2,6]
Skip because end(6) ≤ last result end(6)
Result: [[1,6]]
```

### Step 4: Process interval [8,10]
```
Current: [8,10]
Check [15,18]: No overlap because 15 > 10
Result: [[1,6], [8,10]]
```

### Step 5: Process interval [15,18]
```
Current: [15,18]
No more intervals to check
Result: [[1,6], [8,10], [15,18]]
```

## Key Decision Points in the Algorithm

1. **Sorting Decision Point**: 
   - **Decision**: Sort by start time
   - **Reasoning**: Makes overlapping intervals adjacent, simplifying the merge process

2. **Skip Logic Decision Point**:
   - **Decision**: Skip interval if already included in previous result
   - **Reasoning**: Prevents redundant processing of intervals already covered

3. **Overlap Detection Decision Point**:
   - **Decision**: Check if next interval's start ≤ current interval's end
   - **Reasoning**: This is the definition of overlapping intervals

4. **Merge Strategy Decision Point**:
   - **Decision**: Take min of starts (already guaranteed by sorting) and max of ends
   - **Reasoning**: Creates the smallest interval that contains both original intervals

## Alternative Input Example: [[1,4], [4,5]]

| Step | Action | Current Interval | Result List | Check Interval | Overlap Check | New End Value | Explanation |
|------|--------|-----------------|------------|---------------|--------------|--------------|-------------|
| 1 | Sort array | - | [] | - | - | - | Already sorted |
| 2 | i=0 | [1,4] | [] | - | - | - | Select first interval |
| 3 | Check inner loop | [1,4] | [] | [4,5] (j=1) | 4≤4? Yes | max(4,5)=5 | Intervals overlap (touching) |
| 4 | Add to result | [1,5] | [[1,5]] | - | - | - | Add merged interval to result |
| 5 | i=1 | [4,5] | [[1,5]] | - | end(5)≤lastEnd(5)? Yes | - | Skip (already included in result) |
| 6 | Return | - | [[1,5]] | - | - | - | Return final result |

This example demonstrates that touching intervals (where end of first = start of second) are considered overlapping.

### Step-by-Step Explanation

1. **Sort the Intervals**
   - Original intervals: [[1,3], [8,10], [2,6], [15,18]]
   - Sorted intervals: [[1,3], [2,6], [8,10], [15,18]]
   ```
   [1,3] [2,6] [8,10] [15,18]
   ```

2. **Process First Interval [1,3]**
   - Initialize start=1, end=3
   - Check next interval [2,6]: Since 2 ≤ 3, they overlap
   - Update end = max(3,6) = 6
   - Check next interval [8,10]: Since 8 > 6, no overlap
   - Add [1,6] to result
   ```
   Result: [1,6]
   ```

3. **Process Second Interval [2,6]**
   - Since end=6 ≤ last merged interval's end (6), skip this interval
   ```
   Result: [1,6]
   ```

4. **Process Third Interval [8,10]**
   - Initialize start=8, end=10
   - Check next interval [15,18]: Since 15 > 10, no overlap
   - Add [8,10] to result
   ```
   Result: [1,6] [8,10]
   ```

5. **Process Fourth Interval [15,18]**
   - Initialize start=15, end=18
   - No more intervals to check
   - Add [15,18] to result
   ```
   Result: [1,6] [8,10] [15,18]
   ```

### Additional Example Cases

1. **Completely Overlapping Case**
```
Input:  [[1,10], [2,9], [3,8], [4,7]]
Step 1: Sort -> [[1,10], [2,9], [3,8], [4,7]]
Step 2: Process [1,10] -> all other intervals are contained within it
Output: [[1,10]]
```

2. **Chained Overlapping Case**
```
Input:  [[1,3], [3,6], [6,9], [9,12]]
Step 1: Sort -> [[1,3], [3,6], [6,9], [9,12]]
Step 2: Process each interval, merging adjacent ones
Output: [[1,12]]  (all intervals form a chain)
```

### Edge Cases Handling
1. **Empty Input**
   ```
   Input: []
   Output: []
   The algorithm returns an empty list since there are no intervals to merge.
   ```

2. **Single Interval**
   ```
   Input: [[1,5]]
   Output: [[1,5]]
   A single interval has nothing to merge with, so it's returned as is.
   ```

3. **Already Merged Input**
   ```
   Input: [[1,5], [6,10], [11,15]]
   Output: [[1,5], [6,10], [11,15]]
   No intervals overlap, so the output is the same as the input.
   ```

Key Observations:
1. The sorting step is crucial for the algorithm to work efficiently
2. The skip condition prevents duplicate processing of already merged intervals
3. The inner loop efficiently extends the current interval as far as possible
4. The resulting intervals are guaranteed to be non-overlapping and sorted

Sample Validation:
Input: [[1,3], [2,6], [8,10], [15,18]]
Expected: [[1,6], [8,10], [15,18]]
Output: [[1,6], [8,10], [15,18]] ✓

Key Points:
1. Time complexity is dominated by the sorting operation: O(n log n)
2. The algorithm makes at most two passes through the data
3. The result is always sorted by interval start time
4. Each interval in the output represents one or more merged intervals from the input

TEST CASES:
1. Input: [[1,3], [2,6], [8,10], [15,18]]
   Expected: [[1,6], [8,10], [15,18]]
   Output: [[1,6], [8,10], [15,18]]
2. Input: [[1,4], [4,5]]
   Expected: [[1,5]]  (touching intervals are considered overlapping)
   Output: [[1,5]]
3. Input: [[1,4], [0,4]]
   Expected: [[0,4]]  (completely overlapping)
   Output: [[0,4]]
4. Input: [[1,4], [0,1]]
   Expected: [[0,4]]  (touching at one point)
   Output: [[0,4]]
5. Input: [[1,4], [2,3]]
   Expected: [[1,4]]  (one interval completely contains the other)
   Output: [[1,4]]
 */