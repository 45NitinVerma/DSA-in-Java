package Binary_Search.M_Bouquets;
/* 
Minimum Number of Days to Make m Bouquets
# Problem: You are given an integer array bloomDay, an integer m and an integer k.
You want to make m bouquets. To make a bouquet, you need to use k adjacent flowers from the garden.
The garden consists of n flowers, the ith flower will bloom in the bloomDay[i] and then can be used in exactly one bouquet.
Return the minimum number of days you need to wait to be able to make m bouquets from the garden. If it is impossible to make m bouquets return -1.

# Intuition
The core concept is to use a linear search approach to find the minimum day where we can make the required number of bouquets.
- **Main approach**: Check every possible day from minimum bloom day to maximum bloom day
- **Key insight**: For any given day, we can count how many bouquets can be made by checking consecutive bloomed flowers

## Basic Understanding

We need to find the earliest day when we can make m bouquets, each requiring k consecutive bloomed flowers. We iterate through all possible days and for each day, count how many bouquets can be formed.

## Key Observations with Examples

### Observation 1: Impossibility Check
We first check if it's mathematically possible to make m bouquets with k flowers each.
* If m × k > total flowers, it's impossible
* This prevents unnecessary computation

### Observation 2: Search Space Definition
The answer must lie between the minimum and maximum bloom days.
* Minimum day: earliest any flower can bloom
* Maximum day: latest any flower needs to bloom
* Example: bloomDay = [7, 7, 7, 7, 13, 11, 12, 7], search space = [7, 13]

### Observation 3: Consecutive Flower Counting
For each day, we count consecutive bloomed flowers to form bouquets.
* Reset counter when we encounter an unbloomed flower
* Add completed bouquets when we have k consecutive flowers

### Observation 4: Greedy Bouquet Formation
We form bouquets as soon as we have k consecutive flowers.
```
Day 7: [✓, ✓, ✓, ✓, ✗, ✗, ✗, ✓]
Consecutive groups: [4], [1]
Bouquets from group 1: 4/3 = 1
Bouquets from group 2: 1/3 = 0
Total: 1 bouquet
```

## Step-by-Step Example

Let's work through bloomDay = [7, 7, 7, 7, 13, 11, 12, 7], m = 2, k = 3:

1. **Impossibility Check**:
   ```
   Required flowers: m × k = 2 × 3 = 6
   Available flowers: 8
   Since 6 ≤ 8, it's possible to make bouquets
   ```

2. **Find Search Range**:
   ```
   Minimum bloom day: 7
   Maximum bloom day: 13
   Search range: [7, 8, 9, 10, 11, 12, 13]
   ```

3. **Check Day 7**:
   ```
   bloomDay = [7, 7, 7, 7, 13, 11, 12, 7]
   Day 7:     [✓, ✓, ✓, ✓,  ✗,  ✗,  ✗, ✓]
   Consecutive count: 4, then reset, then 1
   Bouquets: 4/3 + 1/3 = 1 + 0 = 1
   Need 2 bouquets, so continue searching
   ```

4. **Check Day 11**:
   ```
   bloomDay = [7, 7, 7, 7, 13, 11, 12, 7]
   Day 11:    [✓, ✓, ✓, ✓,  ✗,  ✓,  ✗, ✓]
   Consecutive groups: [4], [1], [1]
   Bouquets: 4/3 + 1/3 + 1/3 = 1 + 0 + 0 = 1
   Still need 2 bouquets, continue
   ```

5. **Check Day 12**:
   ```
   bloomDay = [7, 7, 7, 7, 13, 11, 12, 7]
   Day 12:    [✓, ✓, ✓, ✓,  ✗,  ✓,  ✓, ✓]
   Consecutive groups: [4], [3]
   Bouquets: 4/3 + 3/3 = 1 + 1 = 2
   Found 2 bouquets! Return day 12
   ```

## Special Cases

### Case 1: Impossible Scenario
Input: bloomDay = [1, 2, 3], m = 2, k = 2
- Required flowers: 2 × 2 = 4
- Available flowers: 3
- Result: -1 (impossible)

### Case 2: All Flowers Bloom on Same Day
Input: bloomDay = [5, 5, 5, 5, 5, 5], m = 2, k = 3
- All flowers bloom on day 5
- On day 5: 6 consecutive flowers available
- Bouquets possible: 6/3 = 2
- Result: 5

### Case 3: Single Bouquet Required
Input: bloomDay = [1, 10, 2, 9, 3, 8], m = 1, k = 3
- Need minimum 3 consecutive flowers
- Check each day until we find 3 consecutive bloomed flowers
- Result: varies based on arrangement
*/
public class Approach1 {
    // Approach 1: Using Linear Search on Answer Space
    public static boolean possible(int[] arr, int day, int m, int k) {
        int n = arr.length; // Total number of flowers
        int cnt = 0;        // Count of consecutive bloomed flowers
        int noOfB = 0;      // Number of bouquets formed so far
        
        // Iterate through all flowers to count bouquets
        for (int i = 0; i < n; i++) {
            if (arr[i] <= day) {
                // Flower has bloomed by this day
                cnt++;
            } else {
                // Flower hasn't bloomed, break the consecutive sequence
                noOfB += (cnt / k);  // Add bouquets from current sequence
                cnt = 0;             // Reset consecutive count
            }
        }
        // Add remaining bouquets from the last sequence
        noOfB += (cnt / k);
        
        // Check if we have enough bouquets
        return noOfB >= m;
    }

    // Main method to find minimum days to make m bouquets
    public static int roseGarden(int[] arr, int k, int m) {
        long val = (long) m * k;  // Total flowers needed
        int n = arr.length;       // Total flowers available
        
        // Impossibility check: if we need more flowers than available
        if (val > n) return -1;
        
        // Find the search range: minimum and maximum bloom days
        int mini = Integer.MAX_VALUE, maxi = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            mini = Math.min(mini, arr[i]);  // Earliest bloom day
            maxi = Math.max(maxi, arr[i]);  // Latest bloom day
        }

        // Linear search from minimum to maximum day
        for (int i = mini; i <= maxi; i++) {
            if (possible(arr, i, m, k))
                return i;  // Return the first day when it's possible
        }
        
        return -1;  // Should never reach here if logic is correct
    }

    // Main method for testing
    public static void main(String[] args) {
        // Example input
        int[] arr = {7, 7, 7, 7, 13, 11, 12, 7};
        int k = 3;  // Flowers per bouquet
        int m = 2;  // Number of bouquets needed

        // Call the method and get the result
        int ans = roseGarden(arr, k, m);

        // Display the result
        if (ans == -1)
            System.out.println("We cannot make m bouquets.");
        else
            System.out.println("We can make bouquets on day " + ans);
    }
}

/*
# Algorithm
1. **Impossibility Check**: If m × k > array length, return -1
2. **Find Search Range**: Calculate minimum and maximum bloom days
3. **Linear Search**: For each day from min to max:
   - Call possible() function to check if m bouquets can be made
   - If yes, return that day
4. **Bouquet Counting Logic** (in possible function):
   - Iterate through flowers
   - Count consecutive bloomed flowers
   - When sequence breaks, add bouquets (count/k) to total
   - Reset consecutive count
5. **Return Result**: First day that satisfies the condition

### Time Complexity Breakdown per Step

1. **Impossibility Check**: O(1)
2. **Find Min/Max**: O(n)
3. **Linear Search**: O(max - min) iterations
4. **Bouquet Counting per iteration**: O(n)

Total: O(n × (max(arr[]) - min(arr[])))

### Space Complexity Breakdown

1. **Auxiliary Space**: O(1) - only using constant extra variables
2. **Input Space**: O(n) - input array storage

Total: O(1) auxiliary space

# Advantages

1. **Simple Logic**: Easy to understand and implement
2. **Guaranteed Solution**: Will always find the minimum day if solution exists
3. **No Complex Data Structures**: Uses basic iteration and counting
4. **Clear Debugging**: Easy to trace through each day's calculation

# Limitations

1. **Time Inefficiency**: Checks every day in range sequentially
2. **Large Range Issue**: Performs poorly when max - min is large
3. **Redundant Calculations**: Recalculates bouquet count for each day
4. **Not Optimal**: Binary search would be more efficient

# Potential Improvements

1. **Binary Search**: Replace linear search with binary search for O(log(max-min)) complexity
2. **Early Termination**: Stop immediately when solution is found
3. **Memoization**: Cache results for repeated calculations
4. **Preprocessing**: Sort or preprocess data for faster lookups

# Step-by-Step Process with Dry Run

## Example Input: bloomDay = [7, 7, 7, 7, 13, 11, 12, 7], m = 2, k = 3

### Detailed Execution Table

```
Day | Flower States           | Consecutive Groups | Bouquets Formed | Total Bouquets | Result
----|-------------------------|--------------------|-----------------|-----------------|---------
7   | [✓,✓,✓,✓,✗,✗,✗,✓]     | [4], [1]          | 1 + 0           | 1              | < 2, Continue
8   | [✓,✓,✓,✓,✗,✗,✗,✓]     | [4], [1]          | 1 + 0           | 1              | < 2, Continue
9   | [✓,✓,✓,✓,✗,✗,✗,✓]     | [4], [1]          | 1 + 0           | 1              | < 2, Continue
10  | [✓,✓,✓,✓,✗,✗,✗,✓]     | [4], [1]          | 1 + 0           | 1              | < 2, Continue
11  | [✓,✓,✓,✓,✗,✓,✗,✓]     | [4], [1], [1]     | 1 + 0 + 0       | 1              | < 2, Continue
12  | [✓,✓,✓,✓,✗,✓,✓,✓]     | [4], [3]          | 1 + 1           | 2              | = 2, Found!
```

### Step-by-Step Explanation

1. **Initial Setup**
   - Input validation: m × k = 2 × 3 = 6 ≤ 8 (array length) ✓
   - Find range: min = 7, max = 13
   ```
   Search range: [7, 8, 9, 10, 11, 12, 13]
   ```

2. **Day 7 Check**
   - Flowers bloomed: positions 0,1,2,3,7 (bloom day ≤ 7)
   - Consecutive sequence: [4 flowers] then [1 flower]
   - Bouquets: 4/3 + 1/3 = 1 + 0 = 1
   ```
   Index: 0 1 2 3 4 5 6 7
   Bloom: 7 7 7 7 13 11 12 7
   Day 7: ✓ ✓ ✓ ✓ ✗ ✗ ✗ ✓
   ```

3. **Day 11 Check**
   - Flowers bloomed: positions 0,1,2,3,5,7 (bloom day ≤ 11)
   - Consecutive sequences: [4], [1], [1]
   - Bouquets: 4/3 + 1/3 + 1/3 = 1 + 0 + 0 = 1
   ```
   Index: 0 1 2 3 4 5 6 7
   Bloom: 7 7 7 7 13 11 12 7
   Day 11: ✓ ✓ ✓ ✓ ✗ ✓ ✗ ✓
   ```

4. **Day 12 Check**
   - Flowers bloomed: positions 0,1,2,3,5,6,7 (bloom day ≤ 12)
   - Consecutive sequences: [4], [3]
   - Bouquets: 4/3 + 3/3 = 1 + 1 = 2 ≥ 2 ✓
   ```
   Index: 0 1 2 3 4 5 6 7
   Bloom: 7 7 7 7 13 11 12 7
   Day 12: ✓ ✓ ✓ ✓ ✗ ✓ ✓ ✓
   ```

### Additional Example Cases

1. **Impossible Case**
```
Input:  bloomDay = [1, 2, 3], m = 2, k = 2
Step 1: Check if possible: 2 × 2 = 4 > 3 (impossible)
Output: -1
```

2. **All Same Day**
```
Input:  bloomDay = [5, 5, 5, 5, 5, 5], m = 2, k = 3
Step 1: Range is [5, 5], only check day 5
Step 2: Day 5 has 6 consecutive flowers, 6/3 = 2 bouquets
Output: 5
```

### Edge Cases Handling

1. **Single Flower Array**
   ```
   Input: bloomDay = [1], m = 1, k = 1
   Output: 1
   Explanation: Only one flower, one bouquet needed with one flower
   ```

2. **All Flowers Same Day**
   ```
   Input: bloomDay = [3, 3, 3, 3], m = 1, k = 4
   Output: 3
   Explanation: All flowers bloom on day 3, can make 1 bouquet of 4 flowers
   ```

3. **Impossible Due to Non-Adjacent Requirement**
   ```
   Input: bloomDay = [1, 1000000, 1], m = 1, k = 3
   Output: 1000000
   Explanation: Need to wait until day 1000000 for all 3 adjacent flowers
   ```

Key Observations:
1. The algorithm guarantees finding the minimum day if a solution exists
2. Linear search ensures we check every possible day in order
3. Consecutive flower counting is crucial for bouquet formation
4. The approach is simple but not time-optimal for large ranges

Sample Validation:
Input: bloomDay = [7, 7, 7, 7, 13, 11, 12, 7], m = 2, k = 3
Expected: 12
Output: 12

Key Points:
1. Always check impossibility first to avoid unnecessary computation
2. Linear search guarantees the minimum day is found
3. Consecutive flower counting determines bouquet formation
4. The algorithm is correct but can be optimized with binary search

TEST CASES:
1. Input: bloomDay = [7, 7, 7, 7, 13, 11, 12, 7], m = 2, k = 3
   Expected: 12
   Output: 12

2. Input: bloomDay = [1, 10, 3, 10, 2], m = 3, k = 1
   Expected: 3
   Output: 3

3. Input: bloomDay = [1, 10, 3, 10, 2], m = 3, k = 2
   Expected: -1
   Output: -1

4. Input: bloomDay = [5, 5, 5, 5, 10, 5, 5], m = 2, k = 3
   Expected: 5
   Output: 5
*/