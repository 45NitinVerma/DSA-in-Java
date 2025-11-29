package Binary_Search.CapacityToLoad;
/* 
# Capacity to Load packages in D days
## Problem: A conveyor belt has packages that must be shipped from one port to another within days days.
The ith package on the conveyor belt has a weight of weights[i]. Each day, we load the ship with packages on the conveyor belt (in the order given by weights). We may not load more weight than the maximum weight capacity of the ship.

Return the least weight capacity of the ship that will result in all the packages on the conveyor belt being shipped within days days.

## Intuition: 
We can use a linear search to find the minimum capacity required to ship all packages within the given days. We will iterate through all possible capacities starting from the maximum weight of a single package up to the total weight of all packages, checking how many days it takes to ship with each capacity.

## Key Observations with Examples

### Observation 1: Capacity Range Bounds
The minimum possible capacity must be at least the weight of the heaviest single package, and the maximum needed capacity is the sum of all package weights.
* Lower bound: max(weights[i]) - we need to carry the heaviest package
* Upper bound: sum(weights) - we could carry everything in one day

### Observation 2: Day Calculation Logic
For any given capacity, we simulate the loading process day by day.
Example: weights = [5, 4, 5, 2, 3, 4, 5, 6], capacity = 10
- Day 1: 5 + 4 = 9 (can add, total = 9)
- Day 1: 9 + 5 = 14 > 10 (cannot add, move to day 2)
- Day 2: 5 (start new day)

### Observation 3: Monotonic Property
If a capacity C can ship all packages in D days, then any capacity > C can also ship in ≤ D days.
```
Capacity:  8  9  10 11 12 ...
Days:      7  6  5  4  4  ...
```

### Observation 4: Linear Search Strategy
We check each capacity from minimum to maximum until we find one that works within the day limit.
Example: For days = 5, we test capacities 6, 7, 8, 9, 10... until findDays(capacity) ≤ 5

## Step-by-Step Example
Let's work through weights = [5, 4, 5, 2, 3, 4, 5, 6], days = 5:

1. **Initialize Bounds**:
   ```
   maxi = max(5,4,5,2,3,4,5,6) = 6
   sum = 5+4+5+2+3+4+5+6 = 34
   Search range: [6, 34]
   ```

2. **Test Capacity 6**:
   ```
   Day 1: 5 (load=5)
   Day 1: 5+4=9 > 6 ❌ → Day 2: 4 (load=4)
   Day 2: 4+5=9 > 6 ❌ → Day 3: 5 (load=5)
   Result: Need 8 days > 5 ❌
   ```

3. **Test Capacity 10**:
   ```
   Day 1: 5+4=9 ≤ 10 ✓ (load=9)
   Day 1: 9+5=14 > 10 ❌ → Day 2: 5 (load=5)
   Day 2: 5+2+3=10 ≤ 10 ✓ (load=10)
   Result: Need 5 days = 5 ✓
   ```

## Special Cases

### Case 1: Single Package
Input: weights = [10], days = 1
- Behavior: Only one package, capacity must be at least 10
- Result: 10

### Case 2: All Packages Same Weight
Input: weights = [5, 5, 5, 5], days = 2
- Behavior: Need to fit 2 packages per day
- Result: 10

### Case 3: One Day Available
Input: weights = [1, 2, 3, 4], days = 1
- Behavior: Must carry all packages in one day
- Result: 10 (sum of all weights)
 */
public class Approach1 {
    // Approach 1: Using linear search
    public static int findDays(int[] weights, int cap) {
        int days = 1; // Start with first day
        int load = 0; // Current day's load
        int n = weights.length;
        
        // Process each package in order
        for (int i = 0; i < n; i++) {
            // Check if adding current package exceeds capacity
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

    // Method to find minimum capacity using linear search
    public static int leastWeightCapacity(int[] weights, int d) {
        // Find maximum weight and total sum
        int maxi = Integer.MIN_VALUE, sum = 0;
        for (int i = 0; i < weights.length; i++) {
            sum += weights[i];
            maxi = Math.max(maxi, weights[i]);
        }

        // Linear search from minimum to maximum possible capacity
        for (int i = maxi; i <= sum; i++) {
            if (findDays(weights, i) <= d) {
                return i; // First capacity that works
            }
        }
        
        return -1; // Should never reach here for valid input
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
1. We will use a loop(say cap) to check all possible capacities.
2. Next, inside the loop, we will send each capacity to the findDays() function to get the number of days required for that particular capacity.
3. The minimum number, for which the number of days <= d, will be the answer.

### Time Complexity Breakdown per Step
1. Finding bounds (max and sum): O(n)
2. Linear search loop: O(sum - max)
3. findDays() for each capacity: O(n)

Total: O(n × (sum - max))

### Space Complexity Breakdown
1. Auxiliary Space: O(1) - only using variables for calculation
2. Input Space: O(n) - storing the weights array

Total: O(1) auxiliary space

# Advantages
1. **Simple and intuitive**: Easy to understand and implement
2. **Guaranteed to find optimal solution**: Tests all possibilities systematically
3. **No complex logic**: Straightforward linear search approach
4. **Memory efficient**: Uses constant extra space

# Limitations
1. **Time inefficient**: Can be very slow for large weight sums
2. **Not scalable**: Performance degrades significantly with larger inputs
3. **Redundant calculations**: Tests many obviously invalid capacities
4. **Poor for large ranges**: When sum >> max, many unnecessary iterations

# Potential Improvements
1. **Binary Search**: Reduce time complexity from O(n×sum) to O(n×log(sum))
2. **Early termination**: Stop when a pattern is detected
3. **Smart bounds**: Better estimation of realistic capacity range
4. **Memoization**: Cache results of findDays() calls (though limited benefit here)

# Step-by-Step Process with Dry Run

## Example Input: weights = [5, 4, 5, 2, 3, 4, 5, 6], days = 5

### Detailed Execution Table
```
Step | Capacity | Day | Load | Package | Action | Days Needed | Valid?
-----|----------|-----|------|---------|--------|-------------|--------
1    | 6        | 1   | 0    | 5       | Add    | -           | -
2    | 6        | 1   | 5    | 4       | Add    | -           | -
3    | 6        | 1   | 9    | 5       | Next Day| -          | -
4    | 6        | 2   | 0    | 5       | Add    | -           | -
5    | 6        | 2   | 5    | 2       | Next Day| -          | -
6    | 6        | 3   | 0    | 2       | Add    | -           | -
7    | 6        | 3   | 2    | 3       | Add    | -           | -
8    | 6        | 3   | 5    | 4       | Next Day| -          | -
9    | 6        | 4   | 0    | 4       | Add    | -           | -
10   | 6        | 4   | 4    | 5       | Next Day| -          | -
11   | 6        | 5   | 0    | 5       | Add    | -           | -
12   | 6        | 5   | 5    | 6       | Next Day| -          | -
13   | 6        | 6   | 0    | 6       | Add    | 6           | No
14   | 7        | -   | -    | -       | Test   | 6           | No
15   | 8        | -   | -    | -       | Test   | 5           | No
16   | 9        | -   | -    | -       | Test   | 5           | No
17   | 10       | -   | -    | -       | Test   | 4           | Yes
```

### Step-by-Step Explanation

1. **Bounds Calculation**
   - Find maximum weight: max(5,4,5,2,3,4,5,6) = 6
   - Calculate total sum: 5+4+5+2+3+4+5+6 = 34
   ```
   Search Range: [6, 34]
   ```

2. **Test Capacity 6**
   - Day 1: Load 5, try to add 4 → 5+4=9 > 6 ❌
   - Day 2: Load 4, try to add 5 → 4+5=9 > 6 ❌
   - Continue... → Need 6 days > 5 ❌
   ```
   Capacity 6: 6 days needed > 5 required
   ```

3. **Test Capacity 10**
   - Day 1: 5+4=9 ≤ 10 ✓, try 5 → 9+5=14 > 10 ❌
   - Day 2: 5+2+3=10 ≤ 10 ✓, try 4 → 10+4=14 > 10 ❌
   - Day 3: 4+5=9 ≤ 10 ✓, try 6 → 9+6=15 > 10 ❌
   - Day 4: 6 → 4 days total ≤ 5 ✓
   ```
   Capacity 10: 4 days needed ≤ 5 required ✓
   ```

### Additional Example Cases

1. **Minimum Capacity Case**
```
Input:  weights = [10], days = 1
Step 1: maxi = 10, sum = 10
Step 2: Test capacity 10 → 1 day needed
Output: 10
```

2. **Multiple Days Case**
```
Input:  weights = [1,2,3,4,5], days = 3
Step 1: maxi = 5, sum = 15
Step 2: Test capacity 5 → 5 days needed > 3
Step 3: Test capacity 6 → 3 days needed = 3
Output: 6
```

### Edge Cases Handling
1. **Single Package**
   ```
   Input: weights = [100], days = 1
   Output: 100
   Explanation: Must carry the single package in one day
   ```

2. **One Day Available**
   ```
   Input: weights = [1,2,3,4], days = 1
   Output: 10
   Explanation: Must carry all packages in single day
   ```

3. **All Same Weights**
   ```
   Input: weights = [3,3,3,3], days = 2
   Output: 6
   Explanation: Need capacity for 2 packages per day
   ```

Key Observations:
1. The algorithm guarantees finding the minimum valid capacity
2. Each capacity test simulates the actual shipping process
3. Linear search ensures we find the first (smallest) working capacity
4. The findDays() function accurately models the constraint

Sample Validation:
Input: [5,4,5,2,3,4,5,6], days = 5
Expected: 10
Output: 10

Key Points:
1. Linear search from minimum possible to maximum possible capacity
2. Each capacity is validated by simulating the shipping process
3. First valid capacity found is guaranteed to be optimal
4. Simple but potentially slow for large input ranges

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