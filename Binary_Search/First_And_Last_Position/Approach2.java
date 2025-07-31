package Binary_Search.First_And_Last_Position;

import java.util.ArrayList;
import java.util.Arrays;

/* 
# First and Last Position of Element in Sorted Array
## Problem: Given an array of integers nums sorted in non-decreasing order, find the starting and ending position of a given target value.
If target is not found in the array, return [-1, -1].
You must write an algorithm with O(log n) runtime complexity.

## Intution: Lower bound returns an index, ind, such that arr[ind] >= x(i.e. target element), and similarly, the upper bound returns the index of the first element that is greater than the target element i.e. arr[ind] > x.

For example, if the given array is {2, 4, 6, 8, 8, 8, 11, 13}, and the target k = 8, the lower bound of 8 will be at index 3(lb), and the upper bound will return index 6(ub). 

Therefore,
the first occurrence of the element = lb(the index returned by lower bound)
and the last occurrence = (ub-1)(ub = the index returned by upper bound).

There are some edge cases.
1. Edge Case 1: If the element is not present in the array.
 - If the target number is not present in the array, the lower bound will return the index of the nearest greater element. So, in the code, we have to check the following:
 --- If arr[lb] != k: The element is not present in the array and so, there will be no first or last occurrences. So, we will return -1.
2. Edge Case 2: If the element is not present in the array and all the array elements are smaller than the target number.

In this case, lower bound will return the imaginary index n i.e. the size of the array. We need to handle this case in our code as well.
If lb == n: No first or last occurrence exists. So, we will return -1.

### Note: Based on the index returned by the lower bound, we will decide if we need to calculate the upper bound because the absence of the first occurrence will guarantee that there will be no last occurrence.

## Key Observations with Examples

### Observation 1: Binary Search for Range Finding
In a sorted array, we can use binary search to efficiently find elements in O(log n) time. For this problem, we need to find a range, which requires two specialized binary searches:
* Lower bound: finds the first position where arr[i] >= target
* Upper bound: finds the first position where arr[i] > target

### Observation 2: Lower Bound vs Upper Bound
Lower bound returns an index `ind` such that arr[ind] >= target, while upper bound returns an index `ind` such that arr[ind] > target.
Example: arr = {2, 4, 6, 8, 8, 8, 11, 13}, target = 8
* Lower bound returns index 3 (first occurrence of 8)
* Upper bound returns index 6 (position of 11, first element > 8)
* Range of target is [3, 5] (upper bound - 1 = last position)

### Observation 3: Edge Cases Handling
When the target isn't in the array, we need to handle specific cases:
* If lower bound index exists but arr[lb] != target: The element is not present
* If lower bound == array length: All elements are smaller than target
```
Input arr = [2, 4, 6, 10, 12], target = 8
Lower bound returns index 3 (position of 10)
Since arr[3] != 8, return [-1, -1]
```

### Observation 4: Result Calculation
The range can be calculated using:
* First occurrence = lower bound (if target exists)
* Last occurrence = upper bound - 1 (if target exists)

## Step-by-Step Example
Let's work through arr = [2, 4, 6, 8, 8, 8, 11, 13], target = 8:

1. Step One: Find Lower Bound
   ```
   Initialize low = 0, high = 7, ans = 8 (array length)
   
   Iteration 1:
   mid = (0 + 7) / 2 = 3
   arr[3] = 8 >= 8 (target), so this could be an answer
   ans = 3, high = 2 (look for possibly smaller index)
   
   Iteration 2:
   mid = (0 + 2) / 2 = 1
   arr[1] = 4 < 8, so look to the right
   low = 2
   
   Iteration 3:
   mid = (2 + 2) / 2 = 2
   arr[2] = 6 < 8, so look to the right
   low = 3
   
   Iteration 4:
   low = 3 > high = 2, so loop terminates
   
   Lower bound is 3
   ```

2. Step Two: Check if target exists
   ```
   lb = 3, arr[3] = 8 equals target, so target exists
   ```

3. Step Three: Find Upper Bound
   ```
   Initialize low = 0, high = 7, ans = 8 (array length)
   
   Iteration 1:
   mid = (0 + 7) / 2 = 3
   arr[3] = 8 > 8? No, so look to the right
   low = 4
   
   Iteration 2:
   mid = (4 + 7) / 2 = 5
   arr[5] = 8 > 8? No, so look to the right
   low = 6
   
   Iteration 3:
   mid = (6 + 7) / 2 = 6
   arr[6] = 11 > 8? Yes, this could be an answer
   ans = 6, high = 5
   
   Iteration 4:
   low = 6 > high = 5, so loop terminates
   
   Upper bound is 6
   ```

4. Step Four: Calculate Result
   ```
   First position = lb = 3
   Last position = ub - 1 = 6 - 1 = 5
   Result = [3, 5]
   ```

## Special Cases

### Case 1: Target not present in the array
Input: arr = [2, 4, 6, 10, 12], target = 8
- Lower bound returns 3 (index of 10)
- Since arr[3] = 10 != 8, return [-1, -1]

### Case 2: All elements smaller than target
Input: arr = [2, 4, 6, 8], target = 10
- Lower bound returns 4 (array length)
- Since lb == n, return [-1, -1]

### Case 3: Target occurs once
Input: arr = [2, 4, 6, 8, 10], target = 8
- Lower bound returns 3
- Upper bound returns 4
- Result = [3, 3]
*/

public class Approach2 {
    // Approach 2: Using Lower and Upper Bound algorithms
    public static int upperBound(ArrayList<Integer> arr, int n, int x) {
        int low = 0, high = n - 1;
        int ans = n; // Default answer is n if no element > x exists

        while (low <= high) {
            int mid = (low + high) / 2;
            
            // If mid element is greater than target, it's a potential answer
            if (arr.get(mid) > x) {
                ans = mid; // Update the answer
                high = mid - 1; // Look for a potentially smaller index on the left
            } else {
                low = mid + 1; // Element <= target, look on the right
            }
        }
        return ans;
    }

    // Method to find lower bound (first index where element >= target)
    public static int lowerBound(ArrayList<Integer> arr, int n, int x) {
        int low = 0, high = n - 1;
        int ans = n; // Default answer is n if no element >= x exists

        while (low <= high) {
            int mid = (low + high) / 2;
            
            // If mid element is >= target, it's a potential answer
            if (arr.get(mid) >= x) {
                ans = mid; // Update the answer
                high = mid - 1; // Look for a potentially smaller index on the left
            } else {
                low = mid + 1; // Element < target, look on the right
            }
        }
        return ans;
    }
    
    public static int[] firstAndLastPosition(ArrayList<Integer> arr, int n, int k) {
        // Find lower bound of target
        int lb = lowerBound(arr, n, k);
        
        // Check if target exists in the array
        if (lb == n || arr.get(lb) != k) 
            return new int[] { -1, -1}; // Target not found
        
        // Find upper bound of target
        int ub = upperBound(arr, n, k);
        
        // Return the range [first occurrence, last occurrence]
        return new int[] {lb, ub - 1};
    }

    public static void main(String[] args) {
        ArrayList<Integer> arr = new ArrayList<>(Arrays.asList(new Integer[] {2, 4, 6, 8, 8, 8, 11, 13}));
        int n = 8, k = 8;
        int[] ans = firstAndLastPosition(arr, n, k);
        System.out.println("The first and last positions are: "
                           + ans[0] + " " + ans[1]);
    }
    
}

/*
# Algorithm
1. Define two binary search functions:
   - lowerBound: Find the first index where arr[i] >= target
   - upperBound: Find the first index where arr[i] > target
2. In firstAndLastPosition function:
   - Find the lower bound (lb) of the target element
   - If lb == n or arr[lb] != target, return [-1, -1]
   - Find the upper bound (ub) of the target element
   - Return [lb, ub-1] as the range

### Time Complexity Breakdown per Step
1. Lower Bound calculation: O(log n)
2. Upper Bound calculation: O(log n)

Total: O(log n)

### Space Complexity Breakdown
1. Auxiliary Space: O(1) 
   - We only use a few variables regardless of input size
2. Input Space: O(n)
   - For storing the input array of size n
Total: O(n)

# Advantages
1. Optimal time complexity of O(log n) achieved through binary search
2. Works efficiently for large sorted arrays
3. Handles all edge cases correctly
4. Clean separation of concerns with separate functions for lower and upper bound

# Limitations
1. Only works on sorted arrays
2. Requires the array to be stored in memory
3. Might not be the most intuitive approach for someone unfamiliar with lower/upper bound concepts

# Potential Improvements
1. Could be implemented with pure arrays instead of ArrayList for performance
2. Could add more detailed error checking and validation
3. Could add support for handling duplicate targets more efficiently

# Step-by-Step Process with Dry Run

## Example Input: arr = [2, 4, 6, 8, 8, 8, 11, 13], target = 8

### Detailed Execution Table
```
Step | Function          | Input State                    | Variables               | Action                   | Output/Return        | Explanation
-----|------------------|-------------------------------|------------------------|--------------------------|---------------------|-------------
1    | firstAndLastPosition | arr=[2,4,6,8,8,8,11,13], n=8, k=8 | N/A                    | Call lowerBound()        | N/A                 | Start by finding the first occurrence of target
2    | lowerBound       | arr=[2,4,6,8,8,8,11,13], n=8, x=8 | low=0, high=7, ans=8    | mid=(0+7)/2=3           | N/A                 | Initialize binary search with array bounds
3    | lowerBound       | Same                          | arr[3]=8 >= 8? Yes      | Update ans=3, high=2     | N/A                 | Found potential first occurrence, look further left
4    | lowerBound       | Same                          | low=0, high=2, mid=1    | arr[1]=4 < 8             | N/A                 | Element at mid is smaller than target, look right
5    | lowerBound       | Same                          | low=2, high=2, mid=2    | arr[2]=6 < 8             | N/A                 | Element at mid is smaller than target, look right
6    | lowerBound       | Same                          | low=3, high=2           | low > high, terminate    | Return ans=3        | Binary search complete, lower bound is 3
7    | firstAndLastPosition | arr=[2,4,6,8,8,8,11,13], lb=3 | arr[lb]=8 == k? Yes    | Continue execution       | N/A                 | Target exists in array, proceed to find last occurrence
8    | firstAndLastPosition | Same                          | N/A                    | Call upperBound()        | N/A                 | Start finding upper bound (index of first element > target)
9    | upperBound       | arr=[2,4,6,8,8,8,11,13], n=8, x=8 | low=0, high=7, ans=8    | mid=(0+7)/2=3           | N/A                 | Initialize binary search with array bounds
10   | upperBound       | Same                          | arr[3]=8 > 8? No        | low=mid+1=4              | N/A                 | Element at mid not greater than target, look right
11   | upperBound       | Same                          | low=4, high=7, mid=5    | arr[5]=8 > 8? No         | low=mid+1=6         | Element at mid not greater than target, look right
12   | upperBound       | Same                          | low=6, high=7, mid=6    | arr[6]=11 > 8? Yes       | ans=6, high=5       | Found element greater than target, update ans & look left
13   | upperBound       | Same                          | low=6, high=5           | low > high, terminate    | Return ans=6        | Binary search complete, upper bound is 6
14   | firstAndLastPosition | lb=3, ub=6                    | N/A                    | Calculate result        | Return [3, 5]       | First occurrence at lb=3, last at ub-1=5
```

### Example 2: Target Not in Array
```
Input: arr = [2, 4, 6, 10, 12], target = 8

Step | Function          | Input State                | Variables               | Action                   | Output/Return        | Explanation
-----|------------------|---------------------------|------------------------|--------------------------|---------------------|-------------
1    | firstAndLastPosition | arr=[2,4,6,10,12], n=5, k=8 | N/A                    | Call lowerBound()        | N/A                 | Start finding first occurrence
2    | lowerBound       | arr=[2,4,6,10,12], n=5, x=8 | low=0, high=4, ans=5    | mid=(0+4)/2=2           | N/A                 | Initialize binary search
3    | lowerBound       | Same                      | arr[2]=6 < 8           | low=mid+1=3              | N/A                 | Element at mid is smaller than target, look right
4    | lowerBound       | Same                      | low=3, high=4, mid=3    | arr[3]=10 >= 8          | ans=3, high=2       | Found potential answer, look left
5    | lowerBound       | Same                      | low=3, high=2           | low > high, terminate    | Return ans=3        | Binary search complete, lower bound is 3
6    | firstAndLastPosition | arr=[2,4,6,10,12], lb=3 | arr[lb]=10 != k(8)      | Target not found         | Return [-1, -1]     | Target does not exist in array
```

### Example 3: Target Present Once
```
Input: arr = [2, 4, 6, 8, 10], target = 8

Step | Function          | Input State               | Variables               | Action                   | Output/Return        | Explanation
-----|------------------|--------------------------|------------------------|--------------------------|---------------------|-------------
1    | firstAndLastPosition | arr=[2,4,6,8,10], n=5, k=8 | N/A                    | Call lowerBound()        | N/A                 | Start finding first occurrence
2-5  | lowerBound       | [Steps similar to above] | [Binary search steps]   | [Binary search process]   | Return lb=3         | Found lower bound at index 3
6    | firstAndLastPosition | arr=[2,4,6,8,10], lb=3    | arr[lb]=8 == k? Yes    | Continue execution       | N/A                 | Target exists, find upper bound
7-11 | upperBound       | [Steps similar to above] | [Binary search steps]   | [Binary search process]   | Return ub=4         | Found upper bound at index 4
12   | firstAndLastPosition | lb=3, ub=4               | N/A                    | Calculate result        | Return [3, 3]       | Target appears only once
```

### Step-by-Step Explanation in Points

#### Case 1: Target Found Multiple Times (arr=[2,4,6,8,8,8,11,13], target=8)

1. **Initialization** (Step 1):
   - We start by calling firstAndLastPosition with our sorted array [2,4,6,8,8,8,11,13]
   - The function needs to find the range where target value 8 appears

2. **Finding Lower Bound - Start** (Step 2):
   - Call lowerBound function to find first occurrence
   - Initialize low=0, high=7 (array length-1), ans=8 (default answer if no element found)
   - The algorithm will use binary search to efficiently find the first position

3. **First Binary Search Iteration** (Step 3):
   - Calculate mid = (0+7)/2 = 3
   - Check if arr[3]=8 >= target(8)? Yes
   - This means index 3 could be our answer, but we need to check if there's an earlier occurrence
   - Update ans=3 (our current best answer)
   - Set high=2 to search the left half (0 to 2)

4. **Second Binary Search Iteration** (Step 4):
   - Calculate mid = (0+2)/2 = 1
   - Check if arr[1]=4 >= target(8)? No
   - This means we need to look in the right half
   - Update low=2 (search from index 2 to 2)

5. **Third Binary Search Iteration** (Step 5):
   - Calculate mid = (2+2)/2 = 2
   - Check if arr[2]=6 >= target(8)? No
   - Need to look further right
   - Update low=3

6. **Lower Bound Search Termination** (Step 6):
   - Now low=3 > high=2, so the loop terminates
   - Return ans=3 as the lower bound (first occurrence)
   - Visual representation:
     ```
     Array: [2, 4, 6, 8, 8, 8, 11, 13]
              0  1  2  3  4  5   6   7
                       ↑
                   lower bound
     ```

7. **Target Existence Check** (Step 7):
   - Back in firstAndLastPosition function
   - Check if target exists: arr[lb]=arr[3]=8 == target(8)? Yes
   - Since the target exists, we continue to find the last occurrence

8. **Finding Upper Bound - Start** (Steps 8-9):
   - Call upperBound function to find the position after the last occurrence
   - Initialize low=0, high=7, ans=8 (default)
   - This will find the first position where element > target

9. **Upper Bound First Iteration** (Step 10):
   - Calculate mid = (0+7)/2 = 3
   - Check if arr[3]=8 > target(8)? No (it's equal)
   - Since we want the first position where element > target, we look rightward
   - Update low=4 to search the right half (4 to 7)

10. **Upper Bound Second Iteration** (Step 11):
    - Calculate mid = (4+7)/2 = 5
    - Check if arr[5]=8 > target(8)? No (it's equal)
    - Continue looking right
    - Update low=6 (search from 6 to 7)

11. **Upper Bound Third Iteration** (Step 12):
    - Calculate mid = (6+7)/2 = 6
    - Check if arr[6]=11 > target(8)? Yes
    - We found an element greater than target, so this is a potential answer
    - Update ans=6, high=5 to check if there's an earlier position with element > target

12. **Upper Bound Search Termination** (Step 13):
    - Now low=6 > high=5, so the loop terminates
    - Return ans=6 as the upper bound
    - Visual representation:
      ```
      Array: [2, 4, 6, 8, 8, 8, 11, 13]
               0  1  2  3  4  5   6   7
                                  ↑
                              upper bound
      ```

13. **Final Result Calculation** (Step 14):
    - First position = lb = 3
    - Last position = ub-1 = 6-1 = 5
    - Return [3, 5] as the range where target appears
    - The target 8 appears at indices 3, 4, and 5

#### Case 2: Target Not Found (arr=[2,4,6,10,12], target=8)

1. **Lower Bound Search** (Steps 1-5):
   - Binary search finds lb=3 (index where arr[lb]=10 is first element >= 8)
   - Visual check: arr=[2,4,6,10,12], lb points to 10

2. **Target Existence Check** (Step 6):
   - Check if arr[lb]=arr[3]=10 == target(8)? No
   - Since arr[lb] != target, we know target doesn't exist in the array
   - Immediately return [-1, -1] without calling upperBound

#### Case 3: Target Found Once (arr=[2,4,6,8,10], target=8)

1. **Lower Bound Search** (Steps 1-5):
   - Binary search finds lb=3 (index where arr[lb]=8 is first element >= 8)

2. **Target Existence Check** (Step 6):
   - Check if arr[lb]=arr[3]=8 == target(8)? Yes
   - Continue to find upper bound

3. **Upper Bound Search** (Steps 7-11):
   - Binary search finds ub=4 (index where arr[ub]=10 is first element > 8)

4. **Final Result Calculation** (Step 12):
   - First position = lb = 3
   - Last position = ub-1 = 4-1 = 3
   - Return [3, 3] indicating target appears only once at index 3

### Additional Example Cases

1. **Target Not Present**
```
Input:  arr = [2, 4, 6, 10, 12], target = 8
Step 1: lowerBound returns 3 (index of 10)
Step 2: Check arr[3] = 10 != 8, return [-1, -1]
Output: [-1, -1]
```

2. **Target Appears Once**
```
Input:  arr = [2, 4, 6, 8, 10], target = 8
Step 1: lowerBound returns 3 (index of 8)
Step 2: Check arr[3] = 8 == 8, continue
Step 3: upperBound returns 4 (index of 10)
Step 4: Return [3, 3]
Output: [3, 3]
```

### Edge Cases Handling
1. **Target Smaller Than All Elements**
   ```
   Input: arr = [5, 7, 9, 11], target = 3
   lowerBound returns 0 (first element position)
   Check arr[0] = 5 != 3, return [-1, -1]
   Output: [-1, -1]
   ```

2. **Target Greater Than All Elements**
   ```
   Input: arr = [2, 4, 6, 8], target = 10
   lowerBound returns 4 (array length)
   Since lb == n, return [-1, -1]
   Output: [-1, -1]
   ```

Key Observations:
1. Binary search efficiently finds the range in O(log n) time
2. The combination of lower and upper bound provides a clean solution
3. Edge cases are properly handled with simple checks
4. The algorithm works for any sorted array regardless of duplicates

Sample Validation:
Input: [2, 4, 6, 8, 8, 8, 11, 13], target = 8
Expected: [3, 5]
Output: [3, 5]

Key Points:
1. Lower bound finds the first occurrence of the target
2. Upper bound finds the position after the last occurrence
3. The approach handles all edge cases correctly
4. Time complexity is optimal for this problem at O(log n)

TEST CASES:
1. Input: [2, 4, 6, 8, 8, 8, 11, 13], target = 8
   Expected: [3, 5]
   Output: [3, 5]
2. Input: [2, 4, 6, 10, 12], target = 8
   Expected: [-1, -1]
   Output: [-1, -1]
3. Input: [2, 4, 6, 8, 10], target = 8
   Expected: [3, 3]
   Output: [3, 3]
4. Input: [5, 7, 9, 11], target = 3
   Expected: [-1, -1]
   Output: [-1, -1]
5. Input: [2, 4, 6, 8], target = 10
   Expected: [-1, -1]
   Output: [-1, -1]
*/