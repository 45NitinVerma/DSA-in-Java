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

## Intution:In the previous approach, while checking the intervals, we first selected an interval using a loop and then compared it with others using another loop. But in this approach, we will try to do the same using a single loop. Let’s understand how:

We will start traversing the given array with a single loop. At the first index, as our answer list is empty, we will insert the first element into the answer list. While traversing afterward we can find two different cases:

Case 1: If the current interval can be merged with the last inserted interval of the answer list:
In this case, we will update the end of the last inserted interval with the maximum(current interval’s end, last inserted interval’s end) and continue moving afterward. 

Case 2: If the current interval cannot be merged with the last inserted interval of the answer list:
In this case, we will insert the current interval in the answer array as it is. And after this insertion, the last inserted interval of the answer list will obviously be updated to the current interval.

Note: Within the previous approach, we have already discussed how to check if the current interval can or cannot be merged with the other interval.

Since we have sorted the intervals, the intervals which will be merging are bound to be adjacent. We kept on merging simultaneously as we were traversing through the array and when the element was non-overlapping we simply inserted the element in our answer list.

## Key Observations with Examples

### Observation 1: Sorting Importance
Sorting intervals by start time ensures that potentially overlapping intervals are adjacent.
* Without sorting: [[1,3], [8,10], [2,6], [15,18]] - hard to detect [1,3] and [2,6] overlap
* With sorting: [[1,3], [2,6], [8,10], [15,18]] - overlapping intervals are adjacent

### Observation 2: Overlap Condition
Two intervals [a,b] and [c,d] overlap when c ≤ b.
* Example 1: [1,3] and [2,6] overlap because 2 ≤ 3
* Example 2: [8,10] and [15,18] don't overlap because 15 > 10

### Observation 3: Merging Logic
When merging overlapping intervals [a,b] and [c,d], the merged interval is [a, max(b,d)].
```
[1,3] + [2,6] = [1,max(3,6)] = [1,6]
```

### Observation 4: Single-Pass Approach
We can solve this with a single pass through the sorted intervals, maintaining the "last seen" merged interval.

## Step-by-Step Example
Let's work through the example intervals = [[1,3], [2,6], [8,10], [15,18]]:

1. Step One:
   ```
   Sort intervals: [[1,3], [2,6], [8,10], [15,18]]
   ans = []
   ```
   Start with an empty result list.

2. Step Two:
   ```
   Process [1,3]:
   ans is empty, so add [1,3]
   ans = [[1,3]]
   ```
   The first interval always gets added.

3. Step Three:
   ```
   Process [2,6]:
   [2,6] overlaps with [1,3] (2 ≤ 3)
   Merge to [1,max(3,6)] = [1,6]
   ans = [[1,6]]
   ```
   Since 2 ≤ 3, the intervals overlap and we merge them.

4. Step Four:
   ```
   Process [8,10]:
   [8,10] doesn't overlap with [1,6] (8 > 6)
   Add [8,10] to ans
   ans = [[1,6], [8,10]]
   ```
   Since 8 > 6, the intervals don't overlap, so we add a new interval.

5. Step Five:
   ```
   Process [15,18]:
   [15,18] doesn't overlap with [8,10] (15 > 10)
   Add [15,18] to ans
   ans = [[1,6], [8,10], [15,18]]
   ```
   Since 15 > 10, we add another new interval.

Final result: [[1,6], [8,10], [15,18]]

## Special Cases

### Case 1: Completely Contained Interval
Input: [[1,6], [2,5]]
- Behavior: [2,5] is completely inside [1,6]
- Result: [[1,6]]

### Case 2: Same Interval Multiple Times
Input: [[1,3], [1,3]]
- Behavior: Duplicate intervals are merged
- Result: [[1,3]]

### Case 3: Single Interval
Input: [[1,6]]
- Behavior: Nothing to merge
- Result: [[1,6]]
*/
public class Approach2 {
    
    // Optimized Approach: Sort + Single Pass 
    public static List<List<Integer>> mergeOverlappingIntervals(int[][] intervals) {
        int n = intervals.length; // size of the array
        
        // Step 1: Sort intervals by their start time
        // This places potentially overlapping intervals adjacent to each other
        Arrays.sort(intervals, new Comparator<int[]>() {
            public int compare(int[] a, int[] b) {
                return a[0] - b[0]; // Sort based on start time
            }
        });

        // Alternative sort using lambda (Java 8+):
        // Arrays.sort(intervals, (a, b) -> a[0] - b[0]);

        // Result list to store merged intervals
        List<List<Integer>> ans = new ArrayList<>();

        // Step 2: Process each interval sequentially
        for (int i = 0; i < n; i++) {
            // Extract current interval for clarity
            int currentStart = intervals[i][0];
            int currentEnd = intervals[i][1];
            
            // Case 1: First interval or non-overlapping interval
            // - If results list is empty, add first interval
            // - Or if current interval starts after the end of last merged interval
            if (ans.isEmpty() || currentStart > ans.get(ans.size() - 1).get(1)) {
                // Add as a new non-overlapping interval
                ans.add(Arrays.asList(currentStart, currentEnd));
            }
            // Case 2: Overlapping interval
            // - Current interval starts before or at the end of last merged interval
            else {
                // Get the last interval in our result
                List<Integer> lastInterval = ans.get(ans.size() - 1);
                
                // Update the end time of last interval if current interval extends further
                // max(last_end, current_end) ensures we cover the entire range
                lastInterval.set(1, Math.max(lastInterval.get(1), currentEnd));
            }
        }
        
        return ans;
    }

    // Main method for testing
    public static void main(String[] args) {
        // Example 1
        int[][] intervals1 = {{1, 3}, {8, 10}, {2, 6}, {15, 18}};
        List<List<Integer>> result1 = mergeOverlappingIntervals(intervals1);
        System.out.println("Example 1:");
        System.out.print("Input: ");
        printIntervals(intervals1);
        System.out.print("Output: ");
        printResult(result1);
        
        // Example 2
        int[][] intervals2 = {{1, 4}, {4, 5}};
        List<List<Integer>> result2 = mergeOverlappingIntervals(intervals2);
        System.out.println("\nExample 2:");
        System.out.print("Input: ");
        printIntervals(intervals2);
        System.out.print("Output: ");
        printResult(result2);
        
        // Example 3 - Edge case with contained intervals
        int[][] intervals3 = {{1, 10}, {2, 6}, {3, 5}};
        List<List<Integer>> result3 = mergeOverlappingIntervals(intervals3);
        System.out.println("\nExample 3 (Contained intervals):");
        System.out.print("Input: ");
        printIntervals(intervals3);
        System.out.print("Output: ");
        printResult(result3);
    }
    
    // Helper method to print input intervals
    private static void printIntervals(int[][] intervals) {
        System.out.print("[");
        for (int i = 0; i < intervals.length; i++) {
            System.out.print("[" + intervals[i][0] + "," + intervals[i][1] + "]");
            if (i < intervals.length - 1) System.out.print(", ");
        }
        System.out.println("]");
    }
    
    // Helper method to print result
    private static void printResult(List<List<Integer>> result) {
        System.out.print("[");
        for (int i = 0; i < result.size(); i++) {
            System.out.print("[" + result.get(i).get(0) + "," + result.get(i).get(1) + "]");
            if (i < result.size() - 1) System.out.print(", ");
        }
        System.out.println("]");
    }
}
/*
## Algorithm:: 
1. First, we will group the closer intervals by sorting the given array of intervals(if it is not already sorted).
2. After that, we will start traversing the array using a loop(say i) and insert the first element into our answer list(as the answer list is empty).
3. Now, while traversing we will face two different cases:
 - Case 1: If the current interval can be merged with the last inserted interval of the answer list:
  In this case, we will update the end of the last inserted interval with the maximum(current interval’s end, last inserted interval’s end) and continue moving afterward. 
 - Case 2: If the current interval cannot be merged with the last inserted interval of the answer list:
  In this case, we will insert the current interval in the answer array as it is. And after this insertion, the last inserted interval of the answer list will obviously be updated to the current interval.
4. Thus the answer list will be updated with the merged intervals and finally, we will return the answer list.

### Time Complexity Breakdown per Step
1. Sorting intervals: O(n log n)
2. Single-pass traversal: O(n)
 
Total: O(n log n), dominated by the sorting step

### Space Complexity Breakdown
1. Auxiliary Space: O(n) for storing the merged intervals in the worst case
   - In the worst case, no intervals overlap, resulting in output size equal to input
2. Input Space: O(n) for the input array of intervals
   
Total: O(n)

# Advantages
1. Efficient single-pass algorithm after sorting
2. Clean, intuitive approach that mimics how humans would solve this problem
3. Works well with large datasets due to optimal O(n log n) time complexity
4. Handles all edge cases correctly (contained intervals, adjacent intervals)

# Limitations
1. Requires sorting, so cannot achieve better than O(n log n) time complexity
2. Modifies the last interval in-place, which might affect code readability
3. Not an online algorithm - requires all intervals to be known upfront

# Potential Improvements
1. Use a more memory-efficient representation if intervals array is very large
2. Consider using a min-heap approach if the input is a stream of intervals
3. If intervals are already sorted, we can skip the sorting step
4. Could be parallelized for very large datasets by dividing and conquering

# Step-by-Step Process with Dry Run
# Merge Intervals - Detailed Step-by-Step Dry Run

## Example Input: [[1,3], [8,10], [2,6], [15,18]]

### Initial Setup
- Input intervals: [[1,3], [8,10], [2,6], [15,18]]
- After sorting: [[1,3], [2,6], [8,10], [15,18]]
- Result list (initially empty): []

### Detailed Execution Table

| Step | Current Interval | Process | Result List | Last Interval in Result | Comparison | Action | Explanation |
|------|------------------|---------|------------|-------------------------|------------|--------|-------------|
| 1    | [1,3]            | Check if result is empty | [] | None | Result is empty | Add [1,3] to result | First interval always gets added |
| 2    | [2,6]            | Compare with last interval | [[1,3]] | [1,3] | Is 2 ≤ 3? YES | Merge [1,3] with [2,6] | Since 2 ≤ 3, intervals overlap. Merge to [1,max(3,6)] = [1,6] |
| 3    | [8,10]           | Compare with last interval | [[1,6]] | [1,6] | Is 8 ≤ 6? NO | Add [8,10] to result | Since 8 > 6, intervals don't overlap. Add new interval |
| 4    | [15,18]          | Compare with last interval | [[1,6], [8,10]] | [8,10] | Is 15 ≤ 10? NO | Add [15,18] to result | Since 15 > 10, intervals don't overlap. Add new interval |

**Final Result**: [[1,6], [8,10], [15,18]]

## Step-by-Step Process Explanation

### Step 1: Processing First Interval [1,3]
1. **Initial state**: 
   - Result list is empty: []
   - Current interval is [1,3]

2. **Decision process**:
   - Since the result list is empty, we add [1,3] without any comparison
   
3. **Action taken**:
   - Add [1,3] to the result list
   
4. **End state**:
   - Result list: [[1,3]]

### Step 2: Processing Second Interval [2,6]
1. **Initial state**: 
   - Result list: [[1,3]]
   - Current interval: [2,6]
   - Last interval in result: [1,3]

2. **Decision process**:
   - Compare start of current interval (2) with end of last interval (3)
   - Check if 2 ≤ 3? YES, they overlap
   
3. **Merging calculation**:
   - New merged interval: [1, max(3,6)]
   - Take start of last interval (1)
   - Take maximum of end values (max(3,6) = 6)
   - Result: [1,6]
   
4. **Action taken**:
   - Update last interval in result to [1,6]
   
5. **End state**:
   - Result list: [[1,6]]

### Step 3: Processing Third Interval [8,10]
1. **Initial state**: 
   - Result list: [[1,6]]
   - Current interval: [8,10]
   - Last interval in result: [1,6]

2. **Decision process**:
   - Compare start of current interval (8) with end of last interval (6)
   - Check if 8 ≤ 6? NO, they don't overlap
   
3. **Action taken**:
   - Add [8,10] as a new interval to the result list
   
4. **End state**:
   - Result list: [[1,6], [8,10]]

### Step 4: Processing Fourth Interval [15,18]
1. **Initial state**: 
   - Result list: [[1,6], [8,10]]
   - Current interval: [15,18]
   - Last interval in result: [8,10]

2. **Decision process**:
   - Compare start of current interval (15) with end of last interval (10)
   - Check if 15 ≤ 10? NO, they don't overlap
   
3. **Action taken**:
   - Add [15,18] as a new interval to the result list
   
4. **End state**:
   - Result list: [[1,6], [8,10], [15,18]]

## Example 2: Contained and Adjacent Intervals

### Input: [[1,5], [2,3], [4,8], [5,9]]

| Step | Current Interval | Process | Result List | Last Interval in Result | Comparison | Action | Explanation |
|------|------------------|---------|------------|-------------------------|------------|--------|-------------|
| 1    | [1,5]            | Check if result is empty | [] | None | Result is empty | Add [1,5] to result | First interval always gets added |
| 2    | [2,3]            | Compare with last interval | [[1,5]] | [1,5] | Is 2 ≤ 5? YES | No change needed | [2,3] is completely contained in [1,5], so no update needed |
| 3    | [4,8]            | Compare with last interval | [[1,5]] | [1,5] | Is 4 ≤ 5? YES | Merge to [1,8] | Intervals overlap, update end to max(5,8) = 8 |
| 4    | [5,9]            | Compare with last interval | [[1,8]] | [1,8] | Is 5 ≤ 8? YES | Merge to [1,9] | Adjacent intervals count as overlapping, update end to max(8,9) = 9 |

**Final Result**: [[1,9]]

## Key Points About The Algorithm:

1. **Sorting importance**: 
   - Sorting by start time ensures potentially overlapping intervals are adjacent
   - Makes it possible to solve the problem in a single pass

2. **Two key operations**:
   - Adding a new interval (when no overlap)
   - Extending the last interval (when overlap exists)

3. **Overlap check formula**:
   - Two intervals [a,b] and [c,d] overlap when c ≤ b
   - This covers both partial overlap and "touching" intervals

4. **Merging formula**:
   - When merging [a,b] and [c,d], the result is [a, max(b,d)]
   - This ensures we cover the entire range of both intervals

5. **Comparison optimization**:
   - We only need to compare with the last interval in our result
   - This is possible because intervals are sorted

6. **Special cases handled**:
   - Completely contained intervals (like [1,5] and [2,3])
   - Adjacent intervals (like [1,5] and [5,9])
   - Identical intervals (automatically merged)

7. **Memory efficiency**:
   - We build the result on-the-fly
   - No need to store all intervals during processing

### Step-by-Step Explanation

1. **Initialization and Sorting**
   - Input: [[1,3], [8,10], [2,6], [15,18]]
   - After sorting: [[1,3], [2,6], [8,10], [15,18]]
   ```
   Initial result list: []
   ```

2. **Process First Interval [1,3]**
   - Result list is empty, so add [1,3]
   ```
   Current result: [[1,3]]
   ```

3. **Process Second Interval [2,6]**
   - Check: Does [2,6] overlap with [1,3]?
   - Since 2 <= 3, YES they overlap
   - Merge them: [1, max(3,6)] = [1,6]
   ```
   Current result: [[1,6]]
   ```

4. **Process Third Interval [8,10]**
   - Check: Does [8,10] overlap with [1,6]?
   - Since 8 > 6, NO they don't overlap
   - Add [8,10] as a new interval
   ```
   Current result: [[1,6], [8,10]]
   ```

5. **Process Fourth Interval [15,18]**
   - Check: Does [15,18] overlap with [8,10]?
   - Since 15 > 10, NO they don't overlap
   - Add [15,18] as a new interval
   ```
   Final result: [[1,6], [8,10], [15,18]]
   ```

### Additional Example Cases

1. **Adjacent Intervals**
```
Input:  [[1,4], [4,5]]
Step 1: Sort (already sorted)
Step 2: Add [1,4] to result
Step 3: Process [4,5]. Since 4 = 4, they are considered overlapping
        Merge to [1,5]
Output: [[1,5]]
```

2. **Completely Contained Intervals**
```
Input:  [[1,10], [2,6], [3,5]]
Step 1: Sort (already sorted)
Step 2: Add [1,10] to result
Step 3: Process [2,6]. Since 2 < 10, they overlap
        Merge to [1,10] (no change as [2,6] is contained in [1,10])
Step 4: Process [3,5]. Since 3 < 10, they overlap
        Merge to [1,10] (no change as [3,5] is contained in [1,10])
Output: [[1,10]]
```

### Edge Cases Handling
1. **Empty Input**
   ```
   Input: []
   Output: []
   No intervals to process, return empty list
   ```

2. **Single Interval**
   ```
   Input: [[1,6]]
   Output: [[1,6]]
   Only one interval, nothing to merge
   ```

3. **Identical Intervals**
   ```
   Input: [[2,5], [2,5], [2,5]]
   Output: [[2,5]]
   All intervals are identical, so they merge into one
   ```

Key Observations:
1. The key to this algorithm is sorting by start time first
2. After sorting, we only need to compare each interval with the last merged interval
3. When merging, we take the maximum of the end times to cover the entire range
4. The algorithm handles adjacent intervals (like [1,4] and [4,5]) as overlapping

Sample Validation:
Input: [[1,3], [2,6], [8,10], [15,18]]
Expected: [[1,6], [8,10], [15,18]]
Output: [[1,6], [8,10], [15,18]] ✓

Key Points:
1. Intervals are first sorted by start time
2. We only need to keep track of the last merged interval
3. Only two cases: either create a new interval or extend the last one
4. Overlapping check: if current_start <= last_end
5. Merging formula: [last_start, max(last_end, current_end)]

TEST CASES:
1. Input: [[1,3], [2,6], [8,10], [15,18]]
   Expected: [[1,6], [8,10], [15,18]]
   Output: [[1,6], [8,10], [15,18]]
2. Input: [[1,4], [4,5]]
   Expected: [[1,5]]
   Output: [[1,5]]
3. Input: [[1,4], [0,4]]
   Expected: [[0,4]]
   Output: [[0,4]]
4. Input: [[1,4], [2,3]]
   Expected: [[1,4]]
   Output: [[1,4]]
*/