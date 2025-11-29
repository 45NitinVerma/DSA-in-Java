package Binary_Search.Aggressive_Cows;

import java.util.Arrays;

/* 
# Aggressive Cows Problem: You are given an array 'arr' consisting of 'n' integers which denote the position of a stall.
You are also given an integer 'k' which denotes the number of aggressive cows.
You are given the task of assigning stalls to 'k' cows such that the minimum distance between any two of them is the maximum possible.
Print the maximum possible minimum distance.

## Intuition: 
[Linear search through all possible minimum distances to find the maximum achievable minimum distance]
- Try every possible distance from 1 to (max_position - min_position)
- For each distance, check if we can place all k cows with at least that minimum distance
- Return the largest distance for which placement is possible

## Basic Understanding
[The problem asks us to place k cows in n stalls positioned at different locations such that no two cows are too close to each other. We want to maximize the minimum distance between any two cows.]

## Key Observations with Examples

### Observation 1: Greedy Placement Strategy
[To check if we can place cows with minimum distance 'd', we use a greedy approach]
* Place the first cow at the first stall (after sorting)
* Place subsequent cows at the first available stall that is at least distance 'd' away
* If we can place all k cows this way, then distance 'd' is achievable

### Observation 2: Sorting Requirement
[Stalls must be sorted to apply greedy placement effectively]
Example: For stalls [0, 3, 4, 7, 10, 9], we sort to get [0, 3, 4, 7, 9, 10]

### Observation 3: Search Space Bounds
[The minimum distance can range from 1 to (max_stall - min_stall)]
* Minimum possible: 1 (cows can be adjacent)
* Maximum possible: (last_stall - first_stall) when k=2
Example: For [0, 3, 4, 7, 9, 10], search space is [1, 10]

### Observation 4: Monotonic Property
[If we can place cows with minimum distance 'd', we can also place them with any distance < d]
```
Distance: 1  2  3  4  5  6  7  8  9  10
Can Place: T  T  T  F  F  F  F  F  F  F
           ↑        ↑
        Works   First Fail
```

## Step-by-Step Example
Let's work through stalls = [0, 3, 4, 7, 10, 9], k = 4:

1. Step One: Sort the stalls
   ```
   Original: [0, 3, 4, 7, 10, 9]
   Sorted:   [0, 3, 4, 7, 9, 10]
   ```
   [Sort to enable greedy placement strategy]

2. Step Two: Calculate search limit
   ```
   limit = stalls[5] - stalls[0] = 10 - 0 = 10
   ```
   [Maximum possible minimum distance is 10]

3. Step Three: Try distance = 1
   ```
   canWePlace([0,3,4,7,9,10], dist=1, cows=4)
   Place cow 1 at position 0
   Place cow 2 at position 3 (3-0 >= 1 ✓)
   Place cow 3 at position 4 (4-3 >= 1 ✓)
   Place cow 4 at position 7 (7-4 >= 1 ✓)
   Result: true
   ```

4. Step Four: Try distance = 2
   ```
   canWePlace([0,3,4,7,9,10], dist=2, cows=4)
   Place cow 1 at position 0
   Place cow 2 at position 3 (3-0 >= 2 ✓)
   Place cow 3 at position 7 (7-3 >= 2 ✓)
   Place cow 4 at position 9 (9-7 >= 2 ✓)
   Result: true
   ```

5. Step Five: Try distance = 3
   ```
   canWePlace([0,3,4,7,9,10], dist=3, cows=4)
   Place cow 1 at position 0
   Place cow 2 at position 3 (3-0 >= 3 ✓)
   Place cow 3 at position 7 (7-3 >= 3 ✓)
   Place cow 4 at position 10 (10-7 >= 3 ✓)
   Result: true
   ```

6. Step Six: Try distance = 4
   ```
   canWePlace([0,3,4,7,9,10], dist=4, cows=4)
   Place cow 1 at position 0
   Can't place cow 2 at position 3 (3-0 < 4 ✗)
   Can't place cow 2 at position 4 (4-0 < 4 ✗)
   Place cow 2 at position 7 (7-0 >= 4 ✓)
   Can't place cow 3 at position 9 (9-7 < 4 ✗)
   Place cow 3 at position 10 (10-7 < 4 ✗)
   Only placed 2 cows, need 4
   Result: false
   ```

## Special Cases

### Case 1: All Cows Can Be Placed at Maximum Distance
Input: stalls = [1, 10], k = 2
- Behavior: Maximum distance possible is 9
- Result: 9

### Case 2: Minimum Distance Required
Input: stalls = [1, 2, 3, 4], k = 4
- Behavior: Must place cows at consecutive positions
- Result: 1

### Case 3: Large Gap Between Stalls
Input: stalls = [1, 2, 100], k = 2
- Behavior: Can place cows at positions 1 and 100
- Result: 99

 */
public class Approach1 {
    // Approach 1: Using Linear Search
    // aggressiveCows method to find maximum possible minimum distance
    public static int aggressiveCows(int[] stalls, int k) {
        int n = stalls.length; // Number of stalls
        // Sort stalls to enable greedy placement strategy
        Arrays.sort(stalls);
        // Maximum possible minimum distance is from first to last stall
        int limit = stalls[n - 1] - stalls[0];
        // Try each possible distance from 1 to limit
        for (int i = 1; i <= limit; i++) {
            // If we cannot place cows with minimum distance i
            if (canWePlace(stalls, i, k) == false) {
                // Return the previous distance (i-1) as it was the maximum achievable
                return (i - 1);
            }
        }
        // If all distances work, return the maximum possible distance
        return limit;
    }
    // Helper method to check if we can place cows with at least 'dist' distance apart
    public static boolean canWePlace(int[] stalls, int dist, int cows) {
        int n = stalls.length; 
        int cntCows = 1; // Number of cows placed so far
        int last = stalls[0]; // Position of the last placed cow (initially first stall)
        
        // Try to place remaining cows starting from second stall
        for (int i = 1; i < n; i++) {
            // If current stall is at least 'dist' away from last placed cow
            if (stalls[i] - last >= dist) {
                cntCows++; // Place cow at current stall
                last = stalls[i]; // Update last cow position
            }
            // If we've successfully placed all required cows
            if (cntCows >= cows) return true;
        }
        // If we couldn't place all cows with minimum distance 'dist'
        return false;
    }
    public static void main(String[] args) {
        int[] stalls = {0, 3, 4, 7, 10, 9};
        int k = 4;
        int ans = aggressiveCows(stalls, k);
        System.out.println("The maximum possible minimum distance is: " + ans);
    }
}
/* 
# Algorithm
1. Sort the stalls array to enable greedy placement
2. Calculate the search limit (maximum possible distance)
3. For each distance from 1 to limit:
   - Use canWePlace() to check if all k cows can be placed with this minimum distance
   - If placement fails, return (current_distance - 1)
4. If all distances work, return the limit

### Time Complexity Breakdown per Step
1. Sorting: O(n log n)
2. Linear search through distances: O(limit) where limit = max - min
3. For each distance, canWePlace(): O(n)

Total: O(n log n + limit × n) = O(n log n + (max-min) × n)

### Space Complexity Breakdown
1. Auxiliary Space: O(1) - only using constant extra variables
2. Input Space: O(n) - for storing the stalls array

Total: O(1) auxiliary space

# Advantages
1. Simple and intuitive approach - easy to understand
2. Guaranteed to find the optimal solution
3. No complex logic or edge case handling required
4. Works correctly for all valid inputs

# Limitations
1. Time complexity can be high when the range (max-min) is large
2. Inefficient compared to binary search approach
3. May timeout for very large inputs with wide stall position ranges
4. Performs unnecessary checks even after finding the answer

# Potential Improvements
1. Use binary search instead of linear search to reduce time complexity from O(limit × n) to O(log(limit) × n)
2. Add early termination conditions in canWePlace()
3. Optimize the search range by calculating tighter bounds
4. Use more efficient sorting algorithms for specific cases

# Step-by-Step Process with Dry Run

## Example Input: stalls = [0, 3, 4, 7, 10, 9], k = 4

### Detailed Execution Table
```
Step | Distance | Stalls State        | Can Place? | Cow Positions    | Action          | Result
-----|----------|-------------------- |------------|------------------|-----------------|--------
0    | -        | [0,3,4,7,10,9]     | -          | -                | Sort array      | [0,3,4,7,9,10]
1    | 1        | [0,3,4,7,9,10]     | Yes        | [0,3,4,7]        | Try distance 1  | Continue
2    | 2        | [0,3,4,7,9,10]     | Yes        | [0,3,7,9]        | Try distance 2  | Continue
3    | 3        | [0,3,4,7,9,10]     | Yes        | [0,3,7,10]       | Try distance 3  | Continue
4    | 4        | [0,3,4,7,9,10]     | No         | [0,7] only       | Try distance 4  | Return 3
```

### Step-by-Step Explanation

1. **Initial Setup and Sorting**
   - Sort the stalls array: [0, 3, 4, 7, 10, 9] → [0, 3, 4, 7, 9, 10]
   - Calculate limit: 10 - 0 = 10
   ```
   Sorted stalls: [0, 3, 4, 7, 9, 10]
   Search range: [1, 10]
   ```

2. **Try Distance = 1**
   - Place cow 1 at position 0
   - Place cow 2 at position 3 (3-0 = 3 ≥ 1 ✓)
   - Place cow 3 at position 4 (4-3 = 1 ≥ 1 ✓)
   - Place cow 4 at position 7 (7-4 = 3 ≥ 1 ✓)
   - Result: Successfully placed 4 cows
   ```
   Cows placed at: [0, 3, 4, 7]
   Minimum distance achieved: 1
   ```

3. **Try Distance = 2**
   - Place cow 1 at position 0
   - Skip position 3 due to insufficient distance, place cow 2 at position 3 (3-0 = 3 ≥ 2 ✓)
   - Skip positions 4, place cow 3 at position 7 (7-3 = 4 ≥ 2 ✓)
   - Place cow 4 at position 9 (9-7 = 2 ≥ 2 ✓)
   - Result: Successfully placed 4 cows
   ```
   Cows placed at: [0, 3, 7, 9]
   Minimum distance achieved: 2
   ```

4. **Try Distance = 3**
   - Place cow 1 at position 0
   - Place cow 2 at position 3 (3-0 = 3 ≥ 3 ✓)
   - Skip positions 4, place cow 3 at position 7 (7-3 = 4 ≥ 3 ✓)
   - Skip position 9, place cow 4 at position 10 (10-7 = 3 ≥ 3 ✓)
   - Result: Successfully placed 4 cows
   ```
   Cows placed at: [0, 3, 7, 10]
   Minimum distance achieved: 3
   ```

5. **Try Distance = 4**
   - Place cow 1 at position 0
   - Skip positions 3, 4 (insufficient distance), place cow 2 at position 7 (7-0 = 7 ≥ 4 ✓)
   - Skip position 9 (9-7 = 2 < 4), try position 10 (10-7 = 3 < 4)
   - Only 2 cows placed, need 4
   - Result: Cannot place all cows with distance 4
   ```
   Cows placed at: [0, 7] only
   Failed to place 4 cows
   Return: 4-1 = 3
   ```

### Additional Example Cases

1. **Minimum Distance Case**
```
Input:  stalls = [1, 2, 3, 4], k = 4
Step 1: Sort → [1, 2, 3, 4]
Step 2: Try distance 1 → Place at [1, 2, 3, 4] ✓
Step 3: Try distance 2 → Can only place 2 cows ✗
Output: 1
```

2. **Maximum Distance Case**
```
Input:  stalls = [1, 100], k = 2
Step 1: Sort → [1, 100]
Step 2: Try distances 1 to 99 → All work for 2 cows
Output: 99
```

### Edge Cases Handling
1. **Only 2 Cows**
   ```
   Input: stalls = [1, 5, 10], k = 2
   Output: 9 (place at positions 1 and 10)
   Explanation: Maximum distance between first and last stall
   ```

2. **All Stalls Consecutive**
   ```
   Input: stalls = [1, 2, 3, 4, 5], k = 3
   Output: 2 (place at positions 1, 3, 5)
   Explanation: Best we can do with consecutive positions
   ```

3. **More Stalls Than Needed**
   ```
   Input: stalls = [1, 2, 4, 8, 16], k = 2
   Output: 15 (place at positions 1 and 16)
   Explanation: Use first and last positions for maximum distance
   ```

Key Observations:
1. Sorting is crucial for the greedy placement strategy to work
2. The canWePlace() function uses a greedy approach - always place the cow at the first available position
3. Linear search guarantees finding the optimal solution but may be slow for large ranges
4. The algorithm stops as soon as it finds a distance that doesn't work

Sample Validation:
Input: [0, 3, 4, 7, 10, 9], k = 4
Expected: 3
Output: 3

Key Points:
1. Always sort the array first to enable efficient placement checking
2. Use greedy strategy in canWePlace() - place cows as early as possible
3. Linear search ensures we find the maximum achievable minimum distance
4. Time complexity depends on the range of stall positions, not just the number of stalls

TEST CASES:
1. Input: stalls = [0, 3, 4, 7, 10, 9], k = 4
   Expected: 3
   Output: 3
   
2. Input: stalls = [1, 2, 3, 4], k = 4
   Expected: 1
   Output: 1
   
3. Input: stalls = [1, 100], k = 2
   Expected: 99
   Output: 99
   
4. Input: stalls = [4, 2, 1, 3, 6], k = 2
   Expected: 5
   Output: 5
   
5. Input: stalls = [1, 2, 4, 8, 9], k = 3
   Expected: 3
   Output: 3
 */