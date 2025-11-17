package Binary_Search.KokoEatingBananas;
/* 
Koko Eating Bananas 
Problem: Koko loves to eat bananas. There are n piles of bananas, the ith pile has piles[i] bananas. The guards have gone and will come back in h hours.
Koko can decide her bananas-per-hour eating speed of k. Each hour, she chooses some pile of bananas and eats k bananas from that pile. If the pile has less than k bananas, she eats all of them instead and will not eat any more bananas during this hour.
Koko likes to eat slowly but still wants to finish eating all the bananas before the guards return.
Return the minimum integer k such that she can eat all the bananas within h hours.

# Intution: We are going to use the Binary Search algorithm to optimize the approach.
The primary objective of the Binary Search algorithm is to efficiently determine the appropriate half to eliminate, thereby reducing the search space by half. It does this by determining a specific condition that ensures that the target is not present in that half.

Now, we are not given any sorted array on which we can apply binary search. But, if we observe closely, we can notice that our answer space i.e. [1, max(a[])] is sorted. So, we will apply binary search on the answer space.

## Key Observations with Examples

### Observation 1: Answer Space is Sorted
The possible eating speeds form a sorted range from 1 to maximum pile size.
* If speed k works, then any speed > k also works
* If speed k doesn't work, then any speed < k won't work either
* This monotonic property allows binary search application

### Observation 2: Time Calculation for Each Speed
For a given eating speed, calculate total hours needed across all piles.
Example: For piles [7, 15, 6, 3] with speed 4:
- Pile 7: ceil(7/4) = 2 hours
- Pile 15: ceil(15/4) = 4 hours  
- Pile 6: ceil(6/4) = 2 hours
- Pile 3: ceil(3/4) = 1 hour
- Total: 2 + 4 + 2 + 1 = 9 hours

### Observation 3: Binary Search Elimination Strategy
Based on total hours needed, eliminate half of the search space.
* If totalHours ≤ h: Current speed works, try smaller speeds (high = mid - 1)
* If totalHours > h: Current speed too slow, try larger speeds (low = mid + 1)

### Observation 4: Boundary Conditions
Search space boundaries ensure we find the optimal solution.
* Lower bound: 1 (minimum possible eating speed)
* Upper bound: max(piles) (eating fastest pile in 1 hour)

```
Search Space: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15]
              ^                                                    ^
             low                                                  high
```

## Step-by-Step Example
Let's work through piles = [7, 15, 6, 3], h = 8:

1. **Initialize Search Space:**
   ```
   piles = [7, 15, 6, 3]
   max(piles) = 15
   low = 1, high = 15
   ```

2. **First Iteration (mid = 8):**
   ```
   mid = (1 + 15) / 2 = 8
   Calculate hours for speed 8:
   - Pile 7: ceil(7/8) = 1 hour
   - Pile 15: ceil(15/8) = 2 hours
   - Pile 6: ceil(6/8) = 1 hour
   - Pile 3: ceil(3/8) = 1 hour
   Total = 5 hours ≤ 8, so high = mid - 1 = 7
   ```

3. **Second Iteration (mid = 4):**
   ```
   mid = (1 + 7) / 2 = 4
   Calculate hours for speed 4:
   - Pile 7: ceil(7/4) = 2 hours
   - Pile 15: ceil(15/4) = 4 hours
   - Pile 6: ceil(6/4) = 2 hours
   - Pile 3: ceil(3/4) = 1 hour
   Total = 9 hours > 8, so low = mid + 1 = 5
   ```

4. **Continue until low > high, return low = 5**

## Special Cases

### Case 1: Single Pile
Input: piles = [30], h = 5
- Behavior: Need ceil(30/k) ≤ 5, so k ≥ 6
- Result: 6

### Case 2: Equal Hours and Piles
Input: piles = [3, 6, 7, 11], h = 4
- Behavior: Each pile must be eaten in exactly 1 hour
- Result: max(piles) = 11

### Case 3: Excess Time Available
Input: piles = [30, 11, 23, 4, 20], h = 20
- Behavior: Can eat very slowly, minimum speed is 1
- Result: Speed that allows completion in exactly 20 hours or less
*/

public class Approach2 {
    // Approach 2 - Binary Search on Answer Space
    public static int findMax(int[] v) {
        int maxi = Integer.MIN_VALUE;
        int n = v.length;
        
        // Iterate through array to find maximum pile size
        for (int i = 0; i < n; i++) {
            maxi = Math.max(maxi, v[i]);
        }
        return maxi;
    }

    // Helper method to calculate total hours needed for given eating speed
    public static int calculateTotalHours(int[] v, int hourly) {
        int totalH = 0;
        int n = v.length;
        
        // Calculate hours needed for each pile and sum them up
        for (int i = 0; i < n; i++) {
            // Use ceiling division to get hours needed for current pile
            totalH += Math.ceil((double)(v[i]) / (double)(hourly));
        }
        return totalH;
    }

    // Main method to find minimum eating rate using binary search
    public static int minimumRateToEatBananas(int[] v, int h) {
        // Set search boundaries: minimum speed = 1, maximum speed = max pile size
        int low = 1, high = findMax(v);

        // Apply binary search on the answer space
        while (low <= high) {
            // Calculate middle point of current search space
            int mid = (low + high) / 2;
            
            // Calculate total hours needed if eating at 'mid' bananas per hour
            int totalH = calculateTotalHours(v, mid);
            
            // If current speed allows completion within time limit
            if (totalH <= h) {
                // Try to find a smaller valid speed (search left half)
                high = mid - 1;
            } else {
                // Current speed too slow, need faster speed (search right half)
                low = mid + 1;
            }
        }
        // Return the minimum valid eating speed
        return low;
    }

    // Main method for testing
    public static void main(String[] args) {
        // Example input: banana piles and available hours
        int[] v = {7, 15, 6, 3};
        int h = 8;

        // Call the method to find minimum eating rate
        int ans = minimumRateToEatBananas(v, h);

        // Display the result
        System.out.println("Koko should eat at least " + ans + " bananas/hr.");
        
        // Additional test cases
        System.out.println("\n--- Additional Test Cases ---");
        
        int[] test1 = {30, 11, 23, 4, 20};
        int h1 = 5;
        System.out.println("Test 1 - Piles: [30,11,23,4,20], Hours: " + h1 + 
                          " -> Result: " + minimumRateToEatBananas(test1, h1));
        
        int[] test2 = {30, 11, 23, 4, 20};
        int h2 = 6;
        System.out.println("Test 2 - Piles: [30,11,23,4,20], Hours: " + h2 + 
                          " -> Result: " + minimumRateToEatBananas(test2, h2));
    }
}

/*
## Algorithm:
1. First, we will find the maximum element in the given array i.e. max(a[]).
2. Place the 2 pointers i.e. low and high: Initially, we will place the pointers. The pointer low will point to 1 and the high will point to max(a[]).
3. Calculate the ‘mid’: Now, inside the loop, we will calculate the value of ‘mid’ using the following formula:
mid = (low+high) // 2 ( ‘//’ refers to integer division)
4. Eliminate the halves based on the time required if Koko eats ‘mid’ bananas/hr:
We will first calculate the total time(required to consume all the bananas in the array) i.e. totalH using the function calculateTotalHours(a[], mid):
 - If totalH <= h: On satisfying this condition, we can conclude that the number ‘mid’ is one of our possible answers. But we want the minimum number. So, we will eliminate the right half and consider the left half(i.e. high = mid-1).
 - Otherwise, the value mid is smaller than the number we want(as the totalH > h). This means the numbers greater than ‘mid’ should be considered and the right half of ‘mid’ consists of such numbers. So, we will eliminate the left half and consider the right half(i.e. low = mid+1).
5. Finally, outside the loop, we will return the value of low as the pointer will be pointing to the answer.
5. The steps from 2-4 will be inside a loop and the loop will continue until low crosses high.

Note: Please make sure to refer to the video and try out some test cases of your own to understand, how the pointer ‘low’ will be always pointing to the answer in this case. This is also the reason we have not used any extra variable here to store the answer.

calculateTotalHours(a[], hourly):
1. a[] -> the given array
2. Hourly -> the possible number of bananas, Koko will eat in an hour.
 - We will iterate every pile of the given array using a loop(say i).
 - For every pile i, we will calculate the hour i.e. ceil(v[i] / hourly), and add it to the total hours.
 - Finally, we will return the total hours.

## Complexity Analysis:
1. Time Complexity: O(N * log(max(a[]))), where max(a[]) is the maximum element in the array and N = size of the array.
- Reason: We are applying Binary search for the range [1, max(a[])], and for every value of ‘mid’, we are traversing the entire array inside the function named calculateTotalHours().

2. Space Complexity: O(1), as we are not using any extra space.

# Advantages
1. Optimal time complexity using binary search on answer space
2. No need to check every possible eating speed from 1 to max(piles)
3. Leverages monotonic property of the problem for efficient elimination
4. Simple and intuitive approach once the answer space is identified
5. Works well for large ranges of possible eating speeds

# Limitations
1. Requires understanding of binary search on answer space concept
2. Need to identify the monotonic property in the problem
3. Involves floating-point arithmetic for ceiling calculation
4. May have precision issues with very large numbers in ceiling calculation

# Potential Improvements
1. Use integer arithmetic instead of floating-point for ceiling: (v[i] + hourly - 1) / hourly
2. Add input validation for edge cases (empty array, invalid hours)
3. Optimize findMax() by combining with main logic to avoid extra pass
4. Add early termination if h >= sum of all piles (answer would be 1)
5. Consider using more descriptive variable names for better readability

# Step-by-Step Process with Dry Run

## Example Input: piles = [7, 15, 6, 3], h = 8

### Detailed Execution Table
```
Step | low | high | mid | Hours Calculation | Total Hours | Action | Explanation
-----|-----|------|-----|-------------------|-------------|--------|-------------
0    | 1   | 15   | -   | -                 | -           | Init   | Set boundaries
1    | 1   | 15   | 8   | [1,2,1,1]        | 5           | h=7    | 5≤8, try smaller
2    | 1   | 7    | 4   | [2,4,2,1]        | 9           | l=5    | 9>8, need larger
3    | 5   | 7    | 6   | [2,3,1,1]        | 7           | h=5    | 7≤8, try smaller
4    | 5   | 5    | 5   | [2,3,2,1]        | 8           | h=4    | 8≤8, try smaller
5    | 5   | 4    | -   | -                 | -           | End    | low>high, return 5
```

### Step-by-Step Explanation

1. **Initialization**
   - Find maximum pile size: max([7,15,6,3]) = 15
   ```
   low = 1, high = 15
   Search space: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15]
   ```

2. **First Binary Search Iteration (mid = 8)**
   - Calculate total hours for speed 8:
   ```
   Pile 7: ceil(7/8) = ceil(0.875) = 1 hour
   Pile 15: ceil(15/8) = ceil(1.875) = 2 hours
   Pile 6: ceil(6/8) = ceil(0.75) = 1 hour
   Pile 3: ceil(3/8) = ceil(0.375) = 1 hour
   Total: 1 + 2 + 1 + 1 = 5 hours ≤ 8 ✓
   ```
   - Since 5 ≤ 8, speed 8 works, try smaller: high = 7

3. **Second Binary Search Iteration (mid = 4)**
   - Calculate total hours for speed 4:
   ```
   Pile 7: ceil(7/4) = ceil(1.75) = 2 hours
   Pile 15: ceil(15/4) = ceil(3.75) = 4 hours
   Pile 6: ceil(6/4) = ceil(1.5) = 2 hours
   Pile 3: ceil(3/4) = ceil(0.75) = 1 hour
   Total: 2 + 4 + 2 + 1 = 9 hours > 8 ✗
   ```
   - Since 9 > 8, speed 4 too slow, need larger: low = 5

4. **Third Binary Search Iteration (mid = 6)**
   - Calculate total hours for speed 6:
   ```
   Pile 7: ceil(7/6) = ceil(1.167) = 2 hours
   Pile 15: ceil(15/6) = ceil(2.5) = 3 hours
   Pile 6: ceil(6/6) = ceil(1.0) = 1 hour
   Pile 3: ceil(3/6) = ceil(0.5) = 1 hour
   Total: 2 + 3 + 1 + 1 = 7 hours ≤ 8 ✓
   ```
   - Since 7 ≤ 8, speed 6 works, try smaller: high = 5

5. **Fourth Binary Search Iteration (mid = 5)**
   - Calculate total hours for speed 5:
   ```
   Pile 7: ceil(7/5) = ceil(1.4) = 2 hours
   Pile 15: ceil(15/5) = ceil(3.0) = 3 hours
   Pile 6: ceil(6/5) = ceil(1.2) = 2 hours
   Pile 3: ceil(3/5) = ceil(0.6) = 1 hour
   Total: 2 + 3 + 2 + 1 = 8 hours ≤ 8 ✓
   ```
   - Since 8 ≤ 8, speed 5 works, try smaller: high = 4

6. **Termination**
   - Now low = 5, high = 4, so low > high
   - Return low = 5 as the minimum eating speed

### Additional Example Cases

1. **Large Piles Case**
```
Input:  piles = [30, 11, 23, 4, 20], h = 5
Step 1: max(piles) = 30, so search in [1, 30]
Step 2: Binary search finds minimum speed = 30
Output: 30 bananas/hour
```

2. **Plenty of Time Case**
```
Input:  piles = [3, 6, 7, 11], h = 20
Step 1: Can eat very slowly since h is large
Step 2: Binary search finds minimum speed = 2
Output: 2 bananas/hour
```

### Edge Cases Handling
1. **Single Pile**
   ```
   Input: piles = [10], h = 3
   Output: ceil(10/3) = 4 bananas/hour
   Explanation: Need at least 4 to finish 10 bananas in 3 hours
   ```

2. **Hours Equal to Piles Count**
   ```
   Input: piles = [1, 2, 3, 4], h = 4
   Output: 4 bananas/hour
   Explanation: Must eat largest pile (4) in 1 hour
   ```

3. **Minimum Speed Case**
   ```
   Input: piles = [1, 1, 1, 1], h = 10
   Output: 1 banana/hour
   Explanation: Can eat very slowly with excess time
   ```

Key Observations:
1. Binary search reduces time complexity from O(N * max(piles)) to O(N * log(max(piles)))
2. The answer space [1, max(piles)] has monotonic property crucial for binary search
3. Ceiling function ensures we account for partial hours correctly
4. The low pointer always converges to the minimum valid answer

Sample Validation:
Input: [7, 15, 6, 3], h = 8
Expected: 5
Output: 5 (takes exactly 8 hours: 2+3+2+1=8)

Key Points:
1. Always use ceiling division for time calculation per pile
2. Binary search on answer space requires identifying monotonic property
3. The elimination strategy depends on whether current speed meets time constraint
4. Final answer is always the low pointer after binary search completion

TEST CASES:
1. Input: piles = [7, 15, 6, 3], h = 8
   Expected: 5
   Output: 5

2. Input: piles = [30, 11, 23, 4, 20], h = 5
   Expected: 30
   Output: 30

3. Input: piles = [30, 11, 23, 4, 20], h = 6
   Expected: 23
   Output: 23

4. Input: piles = [3, 6, 7, 11], h = 8
   Expected: 4
   Output: 4
 */