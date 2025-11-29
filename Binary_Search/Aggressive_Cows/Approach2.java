package Binary_Search.Aggressive_Cows;

import java.util.Arrays;

/* 
# Aggressive Cows Problem: You are given an array 'arr' consisting of 'n' integers which denote the position of a stall.
You are also given an integer 'k' which denotes the number of aggressive cows.
You are given the task of assigning stalls to 'k' cows such that the minimum distance between any two of them is the maximum possible.
Print the maximum possible minimum distance.

## Intuition: 
Binary Search on Answer Space - Instead of searching for a specific element, we search for the maximum possible minimum distance.
- The answer space ranges from 1 to (max_position - min_position)
- This answer space has a property: if we can place cows with distance 'd', we can also place them with any distance < 'd'
- This creates a sorted answer space where we can apply binary search

For example, the given array is {1, 2, 8, 4, 9}. The possible distances are the following:
 arr: [1   2   3   4   5   6   7   8 ]
      -possible-|---not possible-----

## Basic Understanding
We have n stalls at different positions and k aggressive cows. We need to place all k cows such that the minimum distance between any two cows is maximized. This is an optimization problem that can be solved using binary search on the answer space.

## Key Observations with Examples

### Observation 1: Sorted Answer Space Pattern
The answer space follows a specific pattern where smaller distances are always possible if larger distances are possible.
* If distance 'd' is possible, then all distances ≤ 'd' are also possible
* If distance 'd' is not possible, then all distances > 'd' are also not possible

### Observation 2: Greedy Placement Strategy
For checking if a distance 'd' is feasible, we use greedy approach:
Example: Place first cow at first position, then place next cow at the first position that's at least 'd' distance away

### Observation 3: Binary Search on Maximum Value
We're looking for the maximum value in the "possible" range, so when we find a feasible distance, we search in the right half for a potentially larger answer.

### Observation 4: Array Sorting Requirement
The stalls array must be sorted to apply the greedy placement strategy effectively.

```
Original: [0, 3, 4, 7, 10, 9]
Sorted:   [0, 3, 4, 7, 9, 10]
Answer Space: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
Pattern: [possible distances | impossible distances]
```

## Step-by-Step Example
Let's work through stalls = [0, 3, 4, 7, 9, 10], k = 4:

1. **Sorting Phase**:
   ```
   Original: [0, 3, 4, 7, 10, 9]
   Sorted:   [0, 3, 4, 7, 9, 10]
   ```

2. **Binary Search Setup**:
   ```
   low = 1, high = 10 - 0 = 10
   Answer space: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
   ```

3. **Binary Search Iterations**:
   ```
   Iteration 1: mid = 5
   Check if distance 5 is possible → Yes
   Update: low = 6
   
   Iteration 2: mid = 8  
   Check if distance 8 is possible → No
   Update: high = 7
   
   Final Answer: high = 3
   ```

## Special Cases

### Case 1: All Cows Can Be Placed
Input: stalls = [1, 2, 3, 4, 5], k = 2
- Behavior: Maximum distance possible
- Result: 4 (place at positions 1 and 5)

### Case 2: Minimum Distance Required
Input: stalls = [1, 2, 3], k = 3
- Behavior: Must place at every available position
- Result: 1 (minimum possible distance)

### Case 3: Only Two Cows
Input: stalls = [1, 2, 8, 4, 9], k = 2
- Behavior: Place at extreme positions for maximum distance
- Result: 8 (place at positions 1 and 9)

 */
public class Approach2 {
    // Approach 2: Using Binary Search
    // Main method to find maximum possible minimum distance
    public static int aggressiveCows(int[] stalls, int k) {
        int n = stalls.length; // Size of stalls array
        // Sort the stalls array for greedy placement
        Arrays.sort(stalls);
        
        // Binary search bounds
        int low = 1; // Minimum possible distance
        int high = stalls[n - 1] - stalls[0]; // Maximum possible distance
        
        // Apply binary search on answer space
        while (low <= high) {
            int mid = (low + high) / 2; // Current distance to check
            
            // If we can place all cows with distance 'mid'
            if (canWePlace(stalls, mid, k) == true) {
                low = mid + 1; // Try for larger distance (right half)
            } else {
                high = mid - 1; // Try for smaller distance (left half)
            }
        }
        return high; // 'high' will be the largest feasible distance
    }
    // Helper method to check if we can place cows with at least 'dist' distance apart
    public static boolean canWePlace(int[] stalls, int dist, int cows) {
        int n = stalls.length; // Total number of stalls
        int cntCows = 1; // Count of cows placed (first cow always placed at stalls[0])
        int last = stalls[0]; // Position of last placed cow
        
        // Try to place remaining cows
        for (int i = 1; i < n; i++) {
            // If current stall is at least 'dist' away from last placed cow
            if (stalls[i] - last >= dist) {
                cntCows++; // Place cow at current stall
                last = stalls[i]; // Update last placed cow position
            }
            // If we've placed all required cows, return true
            if (cntCows >= cows) return true;
        }
        // Return false if we couldn't place all cows
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
1. **Sort the stalls array** to enable greedy placement strategy
2. **Initialize binary search bounds** - low = 1, high = (max_position - min_position)
3. **Calculate mid value** as potential minimum distance to check
4. **Use canWePlace() helper function** to verify if mid distance is feasible
5. **Update search bounds based on feasibility**:
   - If feasible: search right half (low = mid + 1)
   - If not feasible: search left half (high = mid - 1)
6. **Return high** as the maximum feasible minimum distance

### Time Complexity Breakdown per Step
1. **Sorting**: O(n log n)
2. **Binary Search**: O(log(max_distance)) iterations
3. **canWePlace() per iteration**: O(n)
4. **Total Binary Search**: O(n × log(max_distance))

Total: **O(n log n + n × log(max_distance))**

### Space Complexity Breakdown
1. **Auxiliary Space**: O(1) - only using variables for binary search
2. **Input Space**: O(n) - storing the stalls array
3. **Sorting Space**: O(log n) - recursion stack for Arrays.sort()

Total: **O(log n)**

# Advantages
1. **Efficient Time Complexity**: Much better than brute force O(n × max_distance)
2. **Optimal Solution**: Guaranteed to find the maximum possible minimum distance
3. **Space Efficient**: Uses minimal extra space
4. **Scalable**: Works well for large inputs due to logarithmic search
5. **Clear Logic**: Binary search pattern is easy to understand and implement

# Limitations
1. **Requires Sorting**: Additional O(n log n) preprocessing step needed
2. **Integer Distances Only**: Works only with discrete distance values
3. **Fixed Number of Cows**: Doesn't handle dynamic cow count efficiently
4. **Memory Access Pattern**: Random array access during binary search
5. **Not Online Algorithm**: Requires all stall positions upfront

# Potential Improvements
1. **Parallel Processing**: canWePlace() checks could be parallelized for very large arrays
2. **Early Termination**: Add early exit conditions in canWePlace() for obvious cases
3. **Cache-Friendly**: Optimize memory access patterns for better cache performance
4. **Floating Point**: Extend to handle continuous distance values if needed
5. **Dynamic Programming**: For multiple queries, cache intermediate results

# Step-by-Step Process with Dry Run

## Example Input: stalls = [0, 3, 4, 7, 10, 9], k = 4

### Detailed Execution Table
```
Step | Stalls State | Low | High | Mid | canWePlace(mid) | Action | Explanation
-----|--------------|-----|------|-----|-----------------|--------|-------------
0    | [0,3,4,7,10,9] | -   | -    | -   | -              | Sort   | Sort array first
1    | [0,3,4,7,9,10] | 1   | 10   | 5   | Check          | -      | Initial bounds set
1a   | [0,3,4,7,9,10] | 1   | 10   | 5   | true           | low=6  | Distance 5 possible
2    | [0,3,4,7,9,10] | 6   | 10   | 8   | Check          | -      | Search right half
2a   | [0,3,4,7,9,10] | 6   | 10   | 8   | false          | high=7 | Distance 8 impossible
3    | [0,3,4,7,9,10] | 6   | 7    | 6   | Check          | -      | Search left half
3a   | [0,3,4,7,9,10] | 6   | 7    | 6   | false          | high=5 | Distance 6 impossible
4    | [0,3,4,7,9,10] | 6   | 5    | -   | -              | Exit   | low > high, return high=5
```

### Step-by-Step Explanation

1. **Initial Setup and Sorting**
   - Sort the stalls array: [0, 3, 4, 7, 10, 9] → [0, 3, 4, 7, 9, 10]
   - Set binary search bounds: low = 1, high = 10 - 0 = 10
   ```
   Sorted stalls: [0, 3, 4, 7, 9, 10]
   Search space: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
   ```

2. **First Binary Search Iteration (mid = 5)**
   - Check if distance 5 is possible using canWePlace(stalls, 5, 4)
   - Place cows: Position 0 → Position 7 (distance ≥ 5) → Position 10 (distance ≥ 5)
   - Successfully placed 3 cows, need 4 cows → Not enough cows placed → false
   ```
   Distance 5 check: [Cow1: 0] → [Cow2: 7] → [Cow3: 10] = 3 cows < 4 needed
   Result: false, update high = 4
   ```

3. **Second Binary Search Iteration (mid = 2)**
   - Check if distance 2 is possible using canWePlace(stalls, 2, 4)
   - Place cows: 0 → 3 → 4 (only 1 distance) → 7 → 9
   - Successfully placed 4 cows → true
   ```
   Distance 2 check: [0] → [3] → [4] → [7] → [9] = 4+ cows ≥ 4 needed
   Result: true, update low = 3
   ```

4. **Third Binary Search Iteration (mid = 3)**
   - Check if distance 3 is possible using canWePlace(stalls, 3, 4)
   - Place cows: 0 → 3 → 7 → 10
   - Successfully placed 4 cows → true
   ```
   Distance 3 check: [0] → [3] → [7] → [10] = 4 cows = 4 needed
   Result: true, update low = 4
   ```

5. **Fourth Binary Search Iteration (mid = 4)**
   - low = 4, high = 4, so mid = 4
   - Check if distance 4 is possible
   - Place cows: 0 → 4 → 9 (only 3 cows placed)
   - Cannot place 4 cows → false
   ```
   Distance 4 check: [0] → [4] → [9] = 3 cows < 4 needed
   Result: false, update high = 3
   ```

### Additional Example Cases

1. **Minimum Distance Case**
```
Input:  stalls = [1, 2, 3, 4], k = 4
Step 1: All positions needed, minimum distance = 1
Output: 1
```

2. **Maximum Distance Case**
```
Input:  stalls = [1, 10], k = 2
Step 1: Place at extreme ends for maximum distance
Output: 9
```

### Edge Cases Handling
1. **Only Two Stalls**
   ```
   Input: stalls = [1, 5], k = 2
   Output: 4
   Explanation: Maximum distance between two positions
   ```

2. **All Adjacent Stalls**
   ```
   Input: stalls = [1, 2, 3, 4, 5], k = 5
   Output: 1
   Explanation: Must use all stalls, minimum distance is 1
   ```

3. **Large Gap Between Stalls**
   ```
   Input: stalls = [1, 100], k = 2
   Output: 99
   Explanation: Utilize the large gap for maximum distance
   ```

Key Observations:
1. **Binary search eliminates half the search space** in each iteration
2. **Greedy placement strategy** ensures optimal cow positioning for each distance check
3. **Sorting is crucial** for the greedy approach to work correctly
4. **Answer space has monotonic property** - if distance d works, all distances < d also work

Sample Validation:
Input: stalls = [0, 3, 4, 7, 9, 10], k = 4
Expected: 3
Output: 3

Key Points:
1. **The algorithm finds the maximum value** in the "feasible" portion of answer space
2. **canWePlace() function uses greedy strategy** - always place cow at the first available position
3. **Binary search optimizes** what would otherwise be a linear search through all possible distances
4. **Sorting enables the greedy approach** by ensuring we always check positions in ascending order

TEST CASES:
1. Input: stalls = [0, 3, 4, 7, 9, 10], k = 4
   Expected: 3
   Output: 3
2. Input: stalls = [1, 2, 3, 4, 5], k = 3  
   Expected: 2
   Output: 2
3. Input: stalls = [1, 10, 20, 30], k = 2
   Expected: 29
   Output: 29
4. Input: stalls = [4, 2, 1, 3, 6], k = 2
   Expected: 5
   Output: 5
 */