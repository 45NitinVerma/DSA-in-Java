package Binary_Search.CapacityToLoad;
/* 
# Capacity to Load packages in D days
## Problem: A conveyor belt has packages that must be shipped from one port to another within days days.
The ith package on the conveyor belt has a weight of weights[i]. Each day, we load the ship with packages on the conveyor belt (in the order given by weights). We may not load more weight than the maximum weight capacity of the ship.

Return the least weight capacity of the ship that will result in all the packages on the conveyor belt being shipped within days days.

## Intuition:

Binary search on answer space to find minimum ship capacity.
- The answer lies between max(weights) and sum(weights)
- We can determine if a capacity works by simulating the loading process
- Use binary search to efficiently find the minimum viable capacity

## Basic Understanding

We need to find the smallest ship capacity that allows loading all packages within D days. The packages must be loaded in the given order from the conveyor belt. If adding a package exceeds the ship's capacity, we move to the next day.

## Key Observations with Examples

### Observation 1: Answer Space Boundaries
The minimum possible capacity is max(weights) - we need at least this much to carry the heaviest package.
The maximum needed capacity is sum(weights) - we could carry everything in one day.
* Lower bound: max(weights[]) 
* Upper bound: sum(weights[])

### Observation 2: Monotonic Property
If we can ship all packages in D days with capacity X, we can definitely do it with any capacity > X.
If we cannot ship with capacity X, we cannot do it with any capacity < X.
Example: If capacity 8 works for 5 days, then capacity 9, 10, etc. will also work.

### Observation 3: Simulation Strategy
For any given capacity, we can simulate the loading process:
- Start with day 1, current load = 0
- For each package: if adding it exceeds capacity, move to next day
- Count total days needed

```
Capacity = 8, Weights = [5,4,5,2,3,4,5,6]
Day 1: 5+4 = 9 > 8, so Day 1: [5], Day 2 starts with 4
Day 2: 4+5 = 9 > 8, so Day 2: [4], Day 3 starts with 5  
Day 3: 5+2+3 = 10 > 8, so Day 3: [5,2], Day 4 starts with 3
Day 4: 3+4+5 = 12 > 8, so Day 4: [3,4], Day 5 starts with 5
Day 5: 5+6 = 11 > 8, so Day 5: [5], Day 6 starts with 6
Day 6: [6]
Total: 6 days (exceeds limit of 5)
```

### Observation 4: Binary Search Decision
- If simulation shows days ≤ D: capacity might work, try smaller (search left)
- If simulation shows days > D: capacity too small, try larger (search right)

## Step-by-Step Example
Let's work through weights = [5,4,5,2,3,4,5,6], days = 5:

1. **Initialize bounds**:
   ```
   low = max(weights) = 6
   high = sum(weights) = 34
   ```

2. **First iteration (mid = 20)**:
   ```
   Simulate with capacity 20:
   Day 1: 5+4+5+2+3+4 = 23 > 20, so Day 1: [5,4,5,2,3], load = 15
   Day 2: 15+4 = 19 ≤ 20, then 19+5 = 24 > 20, so Day 2: [4], load = 4  
   Day 3: 4+5+6 = 15 ≤ 20, so Day 3: [5,6]
   Total: 3 days ≤ 5 ✓, try smaller capacity
   high = mid - 1 = 19
   ```

3. **Continue iterations**:
   ```
   Each iteration narrows the search space until finding minimum capacity
   ```

## Special Cases

### Case 1: Single Heavy Package
Input: weights = [10], days = 1
- Behavior: Must have capacity ≥ 10
- Result: 10

### Case 2: All Packages Same Weight  
Input: weights = [3,3,3,3], days = 2
- Behavior: Try to fit as many as possible per day
- Result: 6 (two packages per day)

### Case 3: Days Equal to Package Count
Input: weights = [1,2,3], days = 3  
- Behavior: One package per day, need capacity for heaviest
- Result: 3

 */
public class Approach2 {
    // Approach 2: Using binary search
    // Helper method to calculate days needed for given capacity
    public static int findDays(int[] weights, int cap) {
        int days = 1; // Start with first day
        int load = 0; // Current day's load
        int n = weights.length;
        
        for (int i = 0; i < n; i++) {
            // If adding current package exceeds capacity
            if (load + weights[i] > cap) {
                days += 1; // Move to next day
                load = weights[i]; // Start new day with current package
            } else {
                // Add package to current day
                load += weights[i];
            }
        }
        return days;
    }

    // Main method to find minimum capacity using binary search
    public static int leastWeightCapacity(int[] weights, int d) {
        // Initialize search boundaries
        int low = Integer.MIN_VALUE; // Will store max(weights)
        int high = 0; // Will store sum(weights)
        
        // Find max element and sum of all elements
        for (int i = 0; i < weights.length; i++) {
            high += weights[i]; // Accumulate sum
            low = Math.max(low, weights[i]); // Track maximum
        }

        // Binary search on answer space
        while (low <= high) {
            int mid = (low + high) / 2; // Potential capacity
            int numberOfDays = findDays(weights, mid); // Test this capacity
            
            if (numberOfDays <= d) {
                // Capacity works, try to find smaller one
                high = mid - 1;
            } else {
                // Capacity too small, need larger one
                low = mid + 1;
            }
        }
        return low; // Minimum viable capacity
    }
    public static void main(String[] args) {
        int[] weights = {5, 4, 5, 2, 3, 4, 5, 6};
        int d = 5;
        int ans = leastWeightCapacity(weights, d);
        System.out.println("The minimum capacity should be: " + ans);
    }
}
/* 
# Algorithm:
1. First, we will find the maximum element i.e. max(weights[]), and the summation i.e. sum(weights[]) of the given array.
2. Place the 2 pointers i.e. low and high: Initially, we will place the pointers. The pointer low will point to max(weights[]) and the high will point to sum(weights[]).
3. Calculate the ‘mid’: Now, inside the loop, we will calculate the value of ‘mid’ using the following formula:
mid = (low+high) // 2 ( ‘//’ refers to integer division)
4. Eliminate the halves based on the number of days required for the capacity ‘mid’:
 We will pass the potential capacity, represented by the variable 'mid', to the 'findDays()' function. This function will return the number of days required to ship all the weights for the particular capacity, ‘mid’.
 - If munerOfDays <= d: On satisfying this condition, we can conclude that the number ‘mid’ is one of our possible answers. But we want the minimum number. So, we will eliminate the right half and consider the left half(i.e. high = mid-1).
 - Otherwise, the value mid is smaller than the number we want. This means the numbers greater than ‘mid’ should be considered and the right half of ‘mid’ consists of such numbers. So, we will eliminate the left half and consider the right half(i.e. low = mid+1).
5. Finally, outside the loop, we will return the value of low as the pointer will be pointing to the answer.

### Time Complexity Breakdown per Step
1. Finding max and sum: O(n)
2. Binary search iterations: O(log(sum - max))
3. Each findDays() call: O(n)

Total: O(n × log(sum - max))

### Space Complexity Breakdown
1. Auxiliary Space: O(1) - only using variables for low, high, mid, days, load
2. Input Space: O(n) - for the weights array

Total: O(1) auxiliary space

# Advantages
1. Efficient time complexity compared to linear search
2. Works on answer space without needing pre-sorted input
3. Guarantees finding the minimum viable capacity
4. Space efficient with O(1) extra space

# Limitations
1. Requires the answer space to have monotonic property
2. Need to simulate the entire process for each capacity test
3. May not be intuitive for beginners to understand binary search on answers

# Potential Improvements
1. Early termination if simulation exceeds target days
2. Optimize bounds calculation for specific input patterns
3. Use more efficient mid calculation to avoid integer overflow
4. Add input validation for edge cases

# Step-by-Step Process with Dry Run

## Example Input: weights = [5, 4, 5, 2, 3, 4, 5, 6], days = 5

### Detailed Execution Table
```
Step | Low | High | Mid | Days Needed | Action | Explanation
-----|-----|------|-----|-------------|--------|-------------
Init | 6   | 34   | -   | -           | Setup  | low=max(6), high=sum(34)
1    | 6   | 34   | 20  | 3           | high=19| 3≤5, try smaller capacity
2    | 6   | 19   | 12  | 4           | high=11| 4≤5, try smaller capacity  
3    | 6   | 11   | 8   | 6           | low=9  | 6>5, need larger capacity
4    | 9   | 11   | 10  | 5           | high=9 | 5≤5, try smaller capacity
5    | 9   | 9    | 9   | 5           | high=8 | 5≤5, try smaller capacity
6    | 9   | 8    | -   | -           | Exit   | low>high, return low=9
```

### Step-by-Step Explanation

1. **Initialization**
   - Find boundaries: max = 6, sum = 34
   ```
   weights = [5, 4, 5, 2, 3, 4, 5, 6]
   low = 6 (heaviest package)
   high = 34 (all packages)
   ```

2. **Iteration 1: mid = 20**
   - Test capacity 20, simulate loading
   ```
   Day 1: 5+4+5+2+3 = 19 ≤ 20, next: 19+4 = 23 > 20
   Day 2: 4+5 = 9 ≤ 20, next: 9+6 = 15 ≤ 20  
   Day 3: empty (all loaded)
   Result: 3 days ≤ 5, so high = 19
   ```

3. **Iteration 2: mid = 12**
   - Test capacity 12
   ```
   Day 1: 5+4 = 9, next: 9+5 = 14 > 12
   Day 2: 5+2+3 = 10, next: 10+4 = 14 > 12
   Day 3: 4+5 = 9, next: 9+6 = 15 > 12
   Day 4: 6
   Result: 4 days ≤ 5, so high = 11
   ```

4. **Iteration 3: mid = 8**
   - Test capacity 8
   ```
   Day 1: 5, next: 5+4 = 9 > 8
   Day 2: 4, next: 4+5 = 9 > 8  
   Day 3: 5+2 = 7, next: 7+3 = 10 > 8
   Day 4: 3+4 = 7, next: 7+5 = 12 > 8
   Day 5: 5, next: 5+6 = 11 > 8
   Day 6: 6
   Result: 6 days > 5, so low = 9
   ```

5. **Iteration 4: mid = 10**
   - Test capacity 10
   ```
   Day 1: 5+4 = 9, next: 9+5 = 14 > 10
   Day 2: 5+2+3 = 10, next: 10+4 = 14 > 10
   Day 3: 4+5 = 9, next: 9+6 = 15 > 10
   Day 4: 6
   Result: 4 days ≤ 5, so high = 9
   ```

6. **Final Iteration: mid = 9**
   - Test capacity 9
   ```
   Day 1: 5+4 = 9, next: 9+5 = 14 > 9
   Day 2: 5+2 = 7, next: 7+3 = 10 > 9
   Day 3: 3+4 = 7, next: 7+5 = 12 > 9
   Day 4: 5, next: 5+6 = 11 > 9
   Day 5: 6
   Result: 5 days ≤ 5, so high = 8
   ```

### Additional Example Cases

1. **Minimum Days Case**
```
Input:  weights = [1,2,3,4,5], days = 1
Step 1: Need sum(weights) = 15 capacity
Output: 15
```

2. **Maximum Days Case**
```
Input:  weights = [1,2,3,4,5], days = 5  
Step 1: Need max(weights) = 5 capacity
Output: 5
```

### Edge Cases Handling
1. **Single Package**
   ```
   Input: weights = [10], days = 1
   Output: 10
   Explanation: Must carry the only package in one day
   ```

2. **All Same Weight**
   ```
   Input: weights = [3,3,3,3], days = 2
   Output: 6
   Explanation: Carry 2 packages per day
   ```

3. **Days > Package Count**
   ```
   Input: weights = [1,2,3], days = 5
   Output: 3
   Explanation: One package per day, need capacity for heaviest
   ```

Key Observations:
1. Binary search reduces search space from O(sum) to O(log(sum))
2. Each capacity test requires full simulation of the loading process
3. The answer space has monotonic property enabling binary search
4. Lower bound is max(weights), upper bound is sum(weights)

Sample Validation:
Input: [5,4,5,2,3,4,5,6], days = 5
Expected: 9
Output: 9

Key Points:
1. Answer space binary search is powerful for optimization problems
2. Simulation function must accurately model the loading constraints
3. Monotonic property ensures binary search correctness
4. Efficient solution for large input ranges

TEST CASES:
1. Input: weights = [1,2,3,4,5,6,7,8,9,10], days = 5
   Expected: 15
   Output: 15

2. Input: weights = [3,2,2,4,1,4], days = 3  
   Expected: 6
   Output: 6

3. Input: weights = [1,2,3,1,1], days = 4
   Expected: 3
   Output: 3
 */