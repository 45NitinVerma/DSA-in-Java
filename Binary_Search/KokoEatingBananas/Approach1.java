package Binary_Search.KokoEatingBananas;
/* 
Koko Eating Bananas 
Problem: Koko loves to eat bananas. There are n piles of bananas, the ith pile has piles[i] bananas. The guards have gone and will come back in h hours.
Koko can decide her bananas-per-hour eating speed of k. Each hour, she chooses some pile of bananas and eats k bananas from that pile. If the pile has less than k bananas, she eats all of them instead and will not eat any more bananas during this hour.
Koko likes to eat slowly but still wants to finish eating all the bananas before the guards return.
Return the minimum integer k such that she can eat all the bananas within h hours.

# Intution: The extremely naive approach is to check all possible answers from 1 to max(a[]). The minimum number for which the required time <= h, is our answer.

- Main approach: Check all possible eating speeds from 1 to maximum pile size
- Key insight: The first speed that satisfies the time constraint is our answer

## Basic Understanding
Koko needs to eat all bananas from n piles within h hours. She can eat at most k bananas per hour from any single pile. We need to find the minimum value of k that allows her to finish all bananas in time.

## Key Observations with Examples

### Observation 1: Linear Search Pattern
We check every possible eating speed from 1 to the maximum pile size
* Start with slowest possible speed (1 banana/hour)
* Increment speed until we find one that works
* First valid speed is the minimum required

### Observation 2: Time Calculation Method
For each pile, calculate hours needed using ceiling division
Example: If pile has 7 bananas and speed is 3 bananas/hour
```
Hours needed = ceil(7/3) = ceil(2.33) = 3 hours
```

### Observation 3: Greedy Selection
Koko can choose any pile each hour, but optimally she'll finish piles completely
* She won't waste time switching between incomplete piles
* Each pile requires ceil(pile_size/speed) hours minimum

### Observation 4: Upper Bound Optimization
Maximum possible speed needed is the largest pile size
* If speed >= max_pile, each pile takes exactly 1 hour
* No point checking speeds beyond this limit

```
Example: piles = [7, 15, 6, 3], h = 8
Max pile = 15, so we check speeds 1 to 15
Speed 1: 7+15+6+3 = 31 hours > 8 ❌
Speed 2: 4+8+3+2 = 17 hours > 8 ❌
Speed 3: 3+5+2+1 = 11 hours > 8 ❌
Speed 4: 2+4+2+1 = 9 hours > 8 ❌
Speed 5: 2+3+2+1 = 8 hours = 8 ✅
```

## Step-by-Step Example
Let's work through piles = [7, 15, 6, 3], h = 8:

1. Step One: Find Maximum Pile Size
   ```
   piles = [7, 15, 6, 3]
   max_pile = 15
   ```
   We'll check speeds from 1 to 15

2. Step Two: Check Speed k = 1
   ```
   Hours for pile 7: ceil(7/1) = 7
   Hours for pile 15: ceil(15/1) = 15
   Hours for pile 6: ceil(6/1) = 6
   Hours for pile 3: ceil(3/1) = 3
   Total: 7+15+6+3 = 31 hours > 8 ❌
   ```

3. Step Three: Check Speed k = 2
   ```
   Hours for pile 7: ceil(7/2) = 4
   Hours for pile 15: ceil(15/2) = 8
   Hours for pile 6: ceil(6/2) = 3
   Hours for pile 3: ceil(3/2) = 2
   Total: 4+8+3+2 = 17 hours > 8 ❌
   ```

4. Step Four: Check Speed k = 5
   ```
   Hours for pile 7: ceil(7/5) = 2
   Hours for pile 15: ceil(15/5) = 3
   Hours for pile 6: ceil(6/5) = 2
   Hours for pile 3: ceil(3/5) = 1
   Total: 2+3+2+1 = 8 hours = 8 ✅
   ```

## Special Cases

### Case 1: Single Pile
Input: piles = [30], h = 4
- Behavior: Need speed where ceil(30/k) ≤ 4
- Result: k = 8 (since ceil(30/8) = 4)

### Case 2: Each Pile Fits in One Hour
Input: piles = [3, 6, 7, 11], h = 8
- Behavior: With speed 11, each pile takes 1 hour
- Result: k = 11 (total 4 hours < 8)

### Case 3: Tight Time Constraint
Input: piles = [30, 11, 23, 4, 20], h = 5
- Behavior: Need exactly 1 hour per pile
- Result: k = 30 (maximum pile size)
*/
public class Approach1 {
    // Approach 1 - Linear Search
    // Helper method to find the maximum element in the array
    public static int findMax(int[] v) {
        int maxi = Integer.MIN_VALUE;
        int n = v.length;
        
        // Iterate through array to find maximum value
        for (int i = 0; i < n; i++) {
            maxi = Math.max(maxi, v[i]);
        }
        return maxi;
    }

    // Helper method to calculate total hours needed for given eating speed
    public static int calculateTotalHours(int[] v, int hourly) {
        int totalH = 0;
        int n = v.length;
        
        // Calculate hours needed for each pile
        for (int i = 0; i < n; i++) {
            // Use ceiling division to get hours for current pile
            totalH += Math.ceil((double)(v[i]) / (double)(hourly));
        }
        return totalH;
    }

    // Main method to find minimum eating rate
    public static int minimumRateToEatBananas(int[] v, int h) {
        // Find the maximum pile size (upper bound for search)
        int maxi = findMax(v);

        // Try each possible eating speed from 1 to maximum pile size
        for (int i = 1; i <= maxi; i++) {
            // Calculate total hours needed with current speed
            int reqTime = calculateTotalHours(v, i);
            
            // If current speed allows finishing within time limit
            if (reqTime <= h) {
                return i; // Return first valid speed (minimum)
            }
        }

        // Fallback return (should never reach here with valid input)
        return maxi;
    }

    // Main method for testing
    public static void main(String[] args) {
        // Example input
        int[] v = {7, 15, 6, 3};
        int h = 8;

        // Call the method and get result
        int ans = minimumRateToEatBananas(v, h);

        // Display the result
        System.out.println("Koko should eat at least " + ans + " bananas/hr.");
    }
}
    
/*
## Algorithm:
1. First, we will find the maximum value i.e. max(a[]) in the given array.
2. We will run a loop(say i) from 1 to max(a[]), to check all possible answers.
3. For each number i, we will calculate the hours required to consume all the bananas from the pile. We will do this using the function calculateTotalHours(), discussed below.
4. The first i, for which the required hours <= h, we will return that value of i.

calculateTotalHours(a[], hourly):
1. a[] -> the given array
2. Hourly -> the possible number of bananas, Koko will eat in an hour.
 - We will iterate every pile of the given array using a loop(say i).
 - For every pile i, we will calculate the hour i.e. ceil(v[i] / hourly), and add it to the total hours.
 - Finally, we will return the total hours.

## Complexity Analysis:
1. Time Complexity: O(max(a[]) * N), where max(a[]) is the maximum element in the array and N = size of the array.
- Reason: We are running nested loops. The outer loop runs for max(a[]) times in the worst case and the inner loop runs for N times.
2. Space Complexity: O(1), as we are using only a constant amount of space

# Advantages
1. Simple and intuitive approach
2. Guaranteed to find the optimal solution
3. Easy to understand and implement
4. No complex data structures required
5. Works for all valid inputs

# Limitations
1. Time complexity is high for large maximum pile sizes
2. Inefficient when maximum pile is much larger than optimal answer
3. Performs unnecessary calculations for obviously invalid speeds
4. Not suitable for large datasets with big pile sizes

# Potential Improvements
1. Use binary search instead of linear search (reduces time to O(N log max_pile))
2. Add early termination conditions
3. Start from a better lower bound estimate
4. Use mathematical optimization techniques

# Step-by-Step Process with Dry Run

## Example Input: piles = [7, 15, 6, 3], h = 8

### Detailed Execution Table
```
Step | Speed | Pile 7 Hours | Pile 15 Hours | Pile 6 Hours | Pile 3 Hours | Total Hours | Valid? | Action
-----|-------|--------------|---------------|--------------|--------------|-------------|--------|--------
1    | 1     | ceil(7/1)=7  | ceil(15/1)=15 | ceil(6/1)=6  | ceil(3/1)=3  | 31          | No     | Continue
2    | 2     | ceil(7/2)=4  | ceil(15/2)=8  | ceil(6/2)=3  | ceil(3/2)=2  | 17          | No     | Continue
3    | 3     | ceil(7/3)=3  | ceil(15/3)=5  | ceil(6/3)=2  | ceil(3/3)=1  | 11          | No     | Continue
4    | 4     | ceil(7/4)=2  | ceil(15/4)=4  | ceil(6/4)=2  | ceil(3/4)=1  | 9           | No     | Continue
5    | 5     | ceil(7/5)=2  | ceil(15/5)=3  | ceil(6/5)=2  | ceil(3/5)=1  | 8           | Yes    | Return 5
```

### Step-by-Step Explanation

1. **Initialize and Find Maximum**
   - Input: piles = [7, 15, 6, 3], h = 8
   - Find maximum pile size: max = 15
   ```
   Array: [7, 15, 6, 3]
   Maximum: 15
   Search range: 1 to 15
   ```

2. **Try Speed k = 1**
   - Calculate hours for each pile with speed 1
   ```
   Pile 7: ceil(7/1) = 7 hours
   Pile 15: ceil(15/1) = 15 hours
   Pile 6: ceil(6/1) = 6 hours
   Pile 3: ceil(3/1) = 3 hours
   Total: 31 hours > 8 ❌
   ```

3. **Try Speed k = 2**
   - Calculate hours for each pile with speed 2
   ```
   Pile 7: ceil(7/2) = ceil(3.5) = 4 hours
   Pile 15: ceil(15/2) = ceil(7.5) = 8 hours
   Pile 6: ceil(6/2) = 3 hours
   Pile 3: ceil(3/2) = ceil(1.5) = 2 hours
   Total: 17 hours > 8 ❌
   ```

4. **Try Speed k = 3**
   - Calculate hours for each pile with speed 3
   ```
   Pile 7: ceil(7/3) = ceil(2.33) = 3 hours
   Pile 15: ceil(15/3) = 5 hours
   Pile 6: ceil(6/3) = 2 hours
   Pile 3: ceil(3/3) = 1 hour
   Total: 11 hours > 8 ❌
   ```

5. **Try Speed k = 4**
   - Calculate hours for each pile with speed 4
   ```
   Pile 7: ceil(7/4) = ceil(1.75) = 2 hours
   Pile 15: ceil(15/4) = ceil(3.75) = 4 hours
   Pile 6: ceil(6/4) = ceil(1.5) = 2 hours
   Pile 3: ceil(3/4) = ceil(0.75) = 1 hour
   Total: 9 hours > 8 ❌
   ```

6. **Try Speed k = 5**
   - Calculate hours for each pile with speed 5
   ```
   Pile 7: ceil(7/5) = ceil(1.4) = 2 hours
   Pile 15: ceil(15/5) = 3 hours
   Pile 6: ceil(6/5) = ceil(1.2) = 2 hours
   Pile 3: ceil(3/5) = ceil(0.6) = 1 hour
   Total: 8 hours = 8 ✅ FOUND!
   ```

### Additional Example Cases

1. **Small Piles Case**
```
Input:  piles = [3, 6, 7, 11], h = 8
Step 1: max = 11, try speeds 1 to 11
Step 2: Speed 2 → 2+3+4+6 = 15 > 8 ❌
Step 3: Speed 3 → 1+2+3+4 = 10 > 8 ❌
Step 4: Speed 4 → 1+2+2+3 = 8 = 8 ✅
Output: 4
```

2. **Large Pile Case**
```
Input:  piles = [30, 11, 23, 4, 20], h = 6
Step 1: max = 30, try speeds 1 to 30
Step 2: Speed 23 → 2+1+1+1+1 = 6 = 6 ✅
Output: 23
```

### Edge Cases Handling
1. **Single Pile**
   ```
   Input: piles = [10], h = 3
   Speed needed: ceil(10/k) ≤ 3
   Minimum k where ceil(10/k) ≤ 3 is k = 4
   Output: 4
   ```

2. **Exact Time Match**
   ```
   Input: piles = [5, 5, 5, 5], h = 4
   With speed 5: each pile takes 1 hour
   Total: 4 hours = 4 ✅
   Output: 5
   ```

3. **Large Time Allowance**
   ```
   Input: piles = [1, 1, 1, 1], h = 10
   With speed 1: total 4 hours < 10
   Output: 1 (minimum possible speed)
   ```

Key Observations:
1. The algorithm always finds the minimum valid eating speed
2. Ceiling division ensures whole hours are counted
3. First valid speed encountered is automatically the minimum
4. Time complexity grows with maximum pile size

Sample Validation:
Input: [7, 15, 6, 3], h = 8
Expected: 5
Output: 5

Key Points:
1. Linear search guarantees finding minimum valid speed
2. Ceiling division handles fractional hours correctly
3. Algorithm stops at first valid solution
4. Maximum pile size provides upper bound for search

TEST CASES:
1. Input: piles = [3, 6, 7, 11], h = 8
   Expected: 4
   Output: 4
2. Input: piles = [30, 11, 23, 4, 20], h = 5
   Expected: 30
   Output: 30
3. Input: piles = [30, 11, 23, 4, 20], h = 6
   Expected: 23
   Output: 23
4. Input: piles = [1], h = 1
   Expected: 1
   Output: 1
 */