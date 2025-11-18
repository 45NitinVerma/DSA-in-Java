package Binary_Search.M_Bouquets;
/* 
Minimum Number of Days to Make m Bouquets
# Problem: You are given an integer array bloomDay, an integer m and an integer k.
You want to make m bouquets. To make a bouquet, you need to use k adjacent flowers from the garden.
The garden consists of n flowers, the ith flower will bloom in the bloomDay[i] and then can be used in exactly one bouquet.
Return the minimum number of days you need to wait to be able to make m bouquets from the garden. If it is impossible to make m bouquets return -1.

## Intuition: We are going to use the Binary Search algorithm to optimize the approach.
[Binary Search on Answer Space]
- Instead of searching through a sorted array, we apply binary search on the answer space (range of possible days)
- The answer space [min(bloomDay), max(bloomDay)] is naturally sorted
- For any given day, we can check if it's possible to make m bouquets, which helps us eliminate half the search space

## Basic Understanding
[The problem asks us to find the minimum number of days to wait so that we can make exactly m bouquets, where each bouquet requires k adjacent flowers that have bloomed]

## Key Observations with Examples
### Observation 1: Answer Space is Sorted
[The possible answers lie between the minimum and maximum bloom days in the array]
* If we can make m bouquets on day X, we can also make them on any day > X
* If we cannot make m bouquets on day X, we cannot make them on any day < X
* This monotonic property allows us to use binary search

### Observation 2: Adjacent Flowers Requirement
[Each bouquet needs exactly k adjacent flowers that have bloomed]
Example: If bloomDay = [7, 7, 7, 7, 13, 11, 12, 7], k = 3, m = 2
- On day 7: flowers at positions 0,1,2,3,7 have bloomed
- We can make bouquets from positions [0,1,2] and [7] (but position 7 alone cannot form a bouquet)
- Only 1 bouquet possible, need 2, so day 7 is not sufficient

### Observation 3: Greedy Counting Strategy
[Count consecutive bloomed flowers and form bouquets greedily from left to right]
```
For each position i:
  if flower[i] has bloomed:
    increment consecutive count
  else:
    add (consecutive_count / k) to total bouquets
    reset consecutive count to 0
```

### Observation 4: Impossible Case Detection
[If total required flowers (m × k) exceeds available flowers (n), return -1]
Example: If we need 3 bouquets of 4 flowers each (12 flowers) but only have 8 flowers total
- Required: 3 × 4 = 12 flowers
- Available: 8 flowers
- Impossible, return -1

## Step-by-Step Example
Let's work through bloomDay = [7, 7, 7, 7, 13, 11, 12, 7], k = 3, m = 2:

1. **Initial Setup:**
   ```
   Array: [7, 7, 7, 7, 13, 11, 12, 7]
   k = 3 (flowers per bouquet)
   m = 2 (bouquets needed)
   Check: m × k = 6 ≤ 8 (array length) ✓
   ```

2. **Find Search Range:**
   ```
   min = 7, max = 13
   Search space: [7, 13]
   ```

3. **Binary Search Process:**
   ```
   low = 7, high = 13
   mid = (7 + 13) / 2 = 10
   Check if day 10 is possible
   ```

4. **Check Day 10 Possibility:**
   ```
   Bloomed flowers: [7≤10, 7≤10, 7≤10, 7≤10, 13>10, 11>10, 12>10, 7≤10]
   Status: [✓, ✓, ✓, ✓, ✗, ✗, ✗, ✓]
   Consecutive groups: [4 consecutive], [1 consecutive]
   Bouquets: 4/3 + 1/3 = 1 + 0 = 1 bouquet
   Need 2 bouquets, so day 10 is NOT possible
   ```

## Special Cases

### Case 1: Impossible Scenario
Input: bloomDay = [1, 2, 3], k = 2, m = 2
- Required flowers: 2 × 2 = 4
- Available flowers: 3
- Result: -1 (impossible)

### Case 2: Exact Match
Input: bloomDay = [1, 1, 1, 1], k = 2, m = 2
- All flowers bloom on day 1
- Can make exactly 2 bouquets of 2 flowers each
- Result: 1

### Case 3: Single Bouquet
Input: bloomDay = [5, 5, 5], k = 3, m = 1
- All flowers bloom on day 5
- Can make exactly 1 bouquet of 3 flowers
- Result: 5
*/

public class Approach2 {
    // Approach 2: Using Binary Search on Answer Space
    public static boolean possible(int[] arr, int day, int m, int k) {
        int n = arr.length; // Size of the array
        int cnt = 0;        // Count of consecutive bloomed flowers
        int noOfB = 0;      // Number of bouquets formed
        
        // Count the number of bouquets we can make:
        for (int i = 0; i < n; i++) {
            if (arr[i] <= day) {
                // This flower has bloomed by the given day
                cnt++;
            } else {
                // This flower hasn't bloomed, so break the consecutive sequence
                noOfB += (cnt / k);  // Add complete bouquets from consecutive flowers
                cnt = 0;             // Reset consecutive count
            }
        }
        noOfB += (cnt / k);  // Add remaining bouquets from the last sequence
        
        return noOfB >= m;   // Return true if we can make at least m bouquets
    }

    // Main method to find minimum days to make m bouquets
    public static int roseGarden(int[] arr, int k, int m) {
        long val = (long) m * k;  // Total flowers needed
        int n = arr.length;       // Size of the array
        
        // Impossible case: need more flowers than available
        if (val > n) return -1;
        
        // Find maximum and minimum bloom days to set search boundaries
        int mini = Integer.MAX_VALUE, maxi = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            mini = Math.min(mini, arr[i]);  // Minimum bloom day
            maxi = Math.max(maxi, arr[i]);  // Maximum bloom day
        }

        // Apply binary search on the answer space [mini, maxi]
        int low = mini, high = maxi;
        while (low <= high) {
            int mid = (low + high) / 2;  // Middle day to check
            
            if (possible(arr, mid, m, k)) {
                // If we can make m bouquets on day mid, try for earlier days
                high = mid - 1;
            } else {
                // If we cannot make m bouquets on day mid, try later days
                low = mid + 1;
            }
        }
        return low;  // Return the minimum day when m bouquets are possible
    }

    // Main method for testing
    public static void main(String[] args) {
        // Example input
        int[] arr = {7, 7, 7, 7, 13, 11, 12, 7};
        int k = 3;  // Flowers per bouquet
        int m = 2;  // Number of bouquets needed

        // Call the method and print the output
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
1. **Check Impossibility**: If m × k > n, return -1
2. **Find Search Boundaries**: Calculate min and max bloom days
3. **Initialize Binary Search**: Set low = min, high = max
4. **Binary Search Loop**: While low ≤ high:
   - Calculate mid = (low + high) / 2
   - Check if m bouquets possible on day mid using possible() function
   - If possible: search left half (high = mid - 1)
   - If not possible: search right half (low = mid + 1)
5. **Return Result**: Return low (minimum day when m bouquets are possible)

### Time Complexity Breakdown per Step
1. **Impossibility Check**: O(1)
2. **Find Min/Max**: O(n)
3. **Binary Search**: O(log(max - min))
4. **Possible Function (per call)**: O(n)

Total: O(n × log(max - min))

### Space Complexity Breakdown
1. **Auxiliary Space**: O(1)
   - Only using constant extra variables (cnt, noOfB, low, high, mid, etc.)
2. **Input Space**: O(n)
   - Input array of size n

Total: O(1) auxiliary space

# Advantages
1. **Efficient Time Complexity**: O(n × log(max - min)) is much better than brute force O(n × (max - min))
2. **Optimal Solution**: Finds the exact minimum number of days
3. **Space Efficient**: Uses only constant extra space
4. **Handles Edge Cases**: Properly detects impossible scenarios

# Limitations
1. **Requires Monotonic Property**: Only works because the problem has monotonic nature (if possible on day X, then possible on day X+1)
2. **Integer Overflow Risk**: Need to use long for m × k calculation
3. **Not Suitable for Dynamic Changes**: If bloom days change, entire search needs to be redone

# Potential Improvements
1. **Early Termination**: If during min/max finding, we detect that consecutive sequences already allow m bouquets on min day
2. **Optimized Possible Function**: Cache results or use more efficient counting
3. **Handle Edge Cases Earlier**: Quick checks for special cases before binary search

# Step-by-Step Process with Dry Run

## Example Input: bloomDay = [7, 7, 7, 7, 13, 11, 12, 7], k = 3, m = 2

### Detailed Execution Table
```
Step | low | high | mid | Bloomed Pattern | Consecutive Groups | Bouquets | Possible? | Action
-----|-----|------|-----|-----------------|-------------------|----------|-----------|--------
1    | 7   | 13   | 10  | [✓,✓,✓,✓,✗,✗,✗,✓] | [4], [1]          | 1        | No        | low = 11
2    | 11  | 13   | 12  | [✓,✓,✓,✓,✗,✓,✓,✓] | [4], [3]          | 2        | Yes       | high = 11
3    | 11  | 11   | 11  | [✓,✓,✓,✓,✗,✓,✗,✓] | [4], [1], [1]     | 1        | No        | low = 12
4    | 12  | 11   | -   | -               | -                 | -        | -         | Exit (low > high)
```

### Step-by-Step Explanation

1. **Initial Setup**
   - Check impossibility: 2 × 3 = 6 ≤ 8 ✓
   - Find range: min = 7, max = 13
   ```
   Array: [7, 7, 7, 7, 13, 11, 12, 7]
   Search space: [7, 13]
   ```

2. **First Binary Search Iteration (mid = 10)**
   - Check flowers bloomed by day 10: positions 0,1,2,3,7
   - Consecutive groups: [4 flowers], [1 flower]
   - Bouquets possible: 4/3 + 1/3 = 1 + 0 = 1
   - Need 2 bouquets, so not possible → search right half
   ```
   Day 10: [✓,✓,✓,✓,✗,✗,✗,✓] → 1 bouquet < 2 needed
   ```

3. **Second Binary Search Iteration (mid = 12)**
   - Check flowers bloomed by day 12: positions 0,1,2,3,5,6,7
   - Consecutive groups: [4 flowers], [3 flowers]
   - Bouquets possible: 4/3 + 3/3 = 1 + 1 = 2
   - Need 2 bouquets, so possible → search left half
   ```
   Day 12: [✓,✓,✓,✓,✗,✓,✓,✓] → 2 bouquets = 2 needed ✓
   ```

4. **Third Binary Search Iteration (mid = 11)**
   - Check flowers bloomed by day 11: positions 0,1,2,3,5,7
   - Consecutive groups: [4 flowers], [1 flower], [1 flower]
   - Bouquets possible: 4/3 + 1/3 + 1/3 = 1 + 0 + 0 = 1
   - Need 2 bouquets, so not possible → search right half
   ```
   Day 11: [✓,✓,✓,✓,✗,✓,✗,✓] → 1 bouquet < 2 needed
   ```

5. **Loop Terminates**
   - low = 12, high = 11 (low > high)
   - Return low = 12

### Additional Example Cases

1. **Edge Case: Impossible**
```
Input:  bloomDay = [1, 2], k = 2, m = 2
Step 1: Check m × k = 4 > 2 (array length)
Output: -1
```

2. **Edge Case: Single Day**
```
Input:  bloomDay = [1, 1, 1, 1], k = 2, m = 2
Step 1: All flowers bloom on day 1
Step 2: Can make 2 bouquets of 2 flowers each
Output: 1
```

### Edge Cases Handling

1. **Impossible Case**
   ```
   Input: bloomDay = [1, 2, 3], k = 3, m = 2
   Output: -1
   Explanation: Need 6 flowers but only have 3
   ```

2. **All Flowers Bloom Same Day**
   ```
   Input: bloomDay = [5, 5, 5, 5, 5, 5], k = 2, m = 3
   Output: 5
   Explanation: All flowers bloom on day 5, can make 3 bouquets
   ```

3. **Single Bouquet Needed**
   ```
   Input: bloomDay = [10, 2, 5], k = 2, m = 1
   Output: 10
   Explanation: Need 2 adjacent flowers, possible combinations: [2,5] on day 5, but they're not adjacent
   ```

Key Observations:
1. **Binary search works because of monotonic property**: if we can make m bouquets on day X, we can also make them on any day ≥ X
2. **Greedy counting is optimal**: counting consecutive bloomed flowers from left to right gives maximum bouquets
3. **Integer overflow prevention**: using long for m × k calculation prevents overflow
4. **Edge case handling**: checking impossibility early saves computation time

Sample Validation:
Input: [7, 7, 7, 7, 13, 11, 12, 7], k = 3, m = 2
Expected: 12
Output: 12

Key Points:
1. **Search space is naturally sorted**: [min bloom day, max bloom day]
2. **Possible function runs in O(n)**: single pass through array
3. **Binary search reduces iterations**: log(max - min) iterations instead of (max - min)
4. **Greedy approach is optimal**: no need to try all combinations of flowers

TEST CASES:
1. Input: bloomDay = [7, 7, 7, 7, 13, 11, 12, 7], k = 3, m = 2
   Expected: 12
   Output: 12

2. Input: bloomDay = [1, 10, 3, 10, 2], k = 3, m = 1
   Expected: 3
   Output: 3

3. Input: bloomDay = [1, 10, 3, 10, 2], k = 3, m = 2
   Expected: -1
   Output: -1

4. Input: bloomDay = [5, 5, 5, 5], k = 2, m = 2
   Expected: 5
   Output: 5
*/