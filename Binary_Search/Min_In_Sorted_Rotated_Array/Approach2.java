package Binary_Search.Min_In_Sorted_Rotated_Array;
/* 
# Find Minimum in Sorted Rotated Array
## Problem: Suppose an array of length n sorted in ascending order is rotated between 1 and n times. 
For example, the array nums = [0,1,2,4,5,6,7] might become:
[4,5,6,7,0,1,2] if it was rotated 4 times.
[0,1,2,4,5,6,7] if it was rotated 7 times.
Notice that rotating an array [a[0], a[1], a[2], ..., a[n-1]] 1 time results in the array [a[n-1], a[0], a[1], a[2], ..., a[n-2]].

Given the sorted rotated array nums of unique elements, return the minimum element of this array.
You must write an algorithm that runs in O(log n) time.

## Intution: 
- Key Observation: If an array is rotated and sorted, we already know that for every index, one of the 2 halves of the array will always be sorted.

Based on this observation, we adopted a straightforward two-step process to eliminate one-half of the rotated sorted array. 

 1. First, we identify the sorted half of the array. 
 2. Once found, we determine if the target is located within this sorted half. 
    - If not, we eliminate that half from further consideration. 
    - Conversely, if the target does exist in the sorted half, we eliminate the other half.

Let’s observe how we identify the sorted half:
We basically compare arr[mid] with arr[low] and arr[high] in the following way:
 - If arr[low] <= arr[mid]: In this case, we identified that the left half is sorted.
 - If arr[mid] <= arr[high]: In this case, we identified that the right half is sorted.

Let’s observe how we will find the minimum element:
In this situation, we have two possibilities to consider. The sorted half of the array may or may not include the minimum value. However, we can leverage the property of the sorted half, specifically that the leftmost element of the sorted half will always be the minimum element within that particular half.

During each iteration, we will select the leftmost element from the sorted half and discard that half from further consideration. Among all the selected elements, the minimum value will serve as our answer.

To facilitate this process, we will declare a variable called 'ans' and initialize it with a large number. Then, at each step, after selecting the leftmost element from the sorted half, we will compare it with 'ans' and update 'ans' with the smaller value (i.e., min(ans, leftmost_element)).

Note: If, at any index, both the left and right halves of the array are sorted, we have the flexibility to select the minimum value from either half and eliminate that particular half (in this case, the left half is chosen first). The algorithm already takes care of this case, so there is no need for explicit handling.

## Dry Run: 
Example:{4,5,1,2,3}
-   low=0,high=4,mid=2
    Check if arr[low] <= arr[mid], its not, 
    So right part is sorted. 
    We take ans=min(ans,arr[2]) => ans=1, and high = mid-1.

-   low=0,high=1,mid=0;
    arr[low]<=arr[mid] = true. 
    So we update ans as min(ans,arr[0]) => ans=1; 
    Since the left part was sorted low=mid+1. Which makes low = high = 1.

-   low=1,high=1,mid=1
    arr[low] <= arr[mid] = true. 
    So we update ans as min(ans,arr[1]) => ans=1;  
    Since the left part was sorted low=mid+1. Which makes low = 2. Loop Stops.

### Observation 1: Sorted Half Property
[In a rotated sorted array, at least one half will always be sorted]
* If we divide the array at any point, either left or right half must be properly sorted
* The minimum element exists at the "breaking point" of rotation

### Observation 2: Minimum Element Location
[The minimum element is at the beginning of the second part of rotation]
Example: In array [4,5,6,7,0,1,2,3], the minimum element 0 is where the rotation "breaks"

### Observation 3: Sorted Half Identification
[We can identify the sorted half by comparing arr[mid] with arr[low]]
* If arr[low] <= arr[mid], the left half is sorted
* Otherwise, the right half is sorted
```
Original: [0,1,2,3,4,5,6,7]
Rotated:  [4,5,6,7,0,1,2,3]
          L     M       H
Left half [4,5,6,7] is sorted since arr[L] <= arr[M]
Right half [0,1,2,3] contains the minimum
```

### Observation 4: Minimum Finding Strategy
[Track the smallest element seen while eliminating halves]
* If left half is sorted, the smallest element in that half is at arr[low]
* If right half is sorted, the smallest element in that half is at arr[mid]
* Track global minimum while eliminating halves

## Step-by-Step Example
Let's work through array [4,5,6,7,0,1,2,3]:

1. Initial State:
   ```
   [4,5,6,7,0,1,2,3]
    L     M       H
   ans = MAX_VALUE
   
   Check if left half is sorted:
   arr[L] <= arr[M]? 4 <= 7? Yes
   Left half is sorted, minimum in this half is arr[L] = 4
   ans = min(MAX_VALUE, 4) = 4
   Eliminate left half: L = M + 1
   ```

2. Second Iteration:
   ```
   [4,5,6,7,0,1,2,3]
              L M   H
   ans = 4
   
   Check if left half is sorted:
   arr[L] <= arr[M]? 0 <= 1? Yes
   Left half is sorted, minimum in this half is arr[L] = 0
   ans = min(4, 0) = 0
   Eliminate left half: L = M + 1
   ```

3. Third Iteration:
   ```
   [4,5,6,7,0,1,2,3]
                  LH
                  M
   ans = 0
   
   Check if left half is sorted:
   arr[L] <= arr[M]? 2 <= 2? Yes
   Left half is sorted, minimum in this half is arr[L] = 2
   ans = min(0, 2) = 0
   Eliminate left half: L = M + 1
   ```

4. Final State:
   ```
   [4,5,6,7,0,1,2,3]
                    L
                   H
   L > H, loop terminates
   ans = 0
   ```

## Special Cases

### Case 1: Not Rotated Array
Input: [1,2,3,4,5]
- Behavior: The entire array is sorted
- Result: Returns 1, the first element

### Case 2: Rotated by Array Length
Input: [1,2,3,4,5] rotated 5 times
- Behavior: Returns to original array
- Result: Returns 1, the first element

### Case 3: Single Element Array
Input: [5]
- Behavior: Only one possibility
- Result: Returns 5
*/

public class Approach2 {
    // Approach 2: Binary Search approach to find minimum element in rotated sorted array
    // Identify sorted half and eliminate it after considering its minimum value
    public static int findMin(int[] arr) {
        int low = 0, high = arr.length - 1;
        int ans = Integer.MAX_VALUE; // Initialize answer to maximum value
        
        while (low <= high) {
            int mid = (low + high) / 2; // Calculate middle point
            
            // Check if left half is sorted
            if (arr[low] <= arr[mid]) {
                // Left half is sorted, so minimum in this half would be at low
                ans = Math.min(ans, arr[low]);
                
                // Eliminate left half since it's sorted and we've captured its minimum
                low = mid + 1;
                
            } else { // Right half is sorted
                // Right half is sorted, so minimum in this half would be at mid
                ans = Math.min(ans, arr[mid]);
                
                // Eliminate right half since it's sorted and we've captured its minimum
                high = mid - 1;
            }
        }
        
        return ans; // Return the minimum element found
    }

    // Main method for testing
    public static void main(String[] args) {
        // Example input: array rotated 4 times
        int[] arr = {4, 5, 6, 7, 0, 1, 2, 3};
        
        // Call the method and print the output
        int result = findMin(arr);
        
        // Display the result
        System.out.println("The minimum element is: " + result);
        
        // Additional test cases
        int[] arr2 = {11, 13, 15, 17, 1, 3, 5, 7, 9};
        System.out.println("Test case 2 minimum: " + findMin(arr2)); // Should output 1
        
        int[] arr3 = {1, 2, 3, 4, 5}; // Not rotated
        System.out.println("Test case 3 minimum: " + findMin(arr3)); // Should output 1
        
        int[] arr4 = {1}; // Single element
        System.out.println("Test case 4 minimum: " + findMin(arr4)); // Should output 1
    }
}
    
/*
# Algorithm
1. Initialize two pointers: low at 0 and high at array length - 1
2. Initialize answer variable (ans) to maximum possible integer value
3. While low <= high:
   a. Calculate mid = (low + high) / 2
   b. Check which half is sorted:
      i. If left half is sorted (arr[low] <= arr[mid]):
         - Update ans = min(ans, arr[low]) since arr[low] is the minimum in sorted left half
         - Eliminate left half by setting low = mid + 1
      ii. If right half is sorted:
         - Update ans = min(ans, arr[mid]) since arr[mid] is the minimum in sorted right half
         - Eliminate right half by setting high = mid - 1
4. Return ans as the minimum element

### Time Complexity Breakdown per Step
1. Binary search loop: O(log n)
2. Comparisons and minimum calculations inside loop: O(1)

Total: O(log n)

### Space Complexity Breakdown
1. Auxiliary Space: O(1)
   - Only using constant extra space (variables low, high, mid, ans)
2. Input Space: O(n)
   - The input array of size n

Total: O(1) auxiliary space

# Advantages
1. Efficient O(log n) time complexity instead of O(n) linear search
2. Works correctly for all valid inputs - rotated or non-rotated arrays
3. Simple implementation with minimal space requirements
4. Can easily be adapted for other problems on rotated arrays

# Limitations
1. Only works for arrays with unique elements
2. Requires the original array to have been sorted before rotation
3. Does not provide the rotation count directly (would need modification)

# Potential Improvements
1. Add handling for arrays with duplicate elements
2. Combine with finding rotation count in the same pass
3. Optimize by using bit manipulation for midpoint calculation to avoid overflow

# Step-by-Step Process with Dry Run

## Example Input: [4, 5, 1, 2, 3]

### Detailed Execution Table
```
Step | Array State             | Action                   | Variables                | Explanation
-----|-------------------------|--------------------------|--------------------------|-------------
0    | [4,5,1,2,3]             | Initialize               | low=0,high=4,ans=MAX_VAL | Set initial values
1    | [4,5,1,2,3]             | Calculate mid            | mid=2                    | mid = (0+4)/2 = 2
     | [4,5,1,2,3]             | Check if left is sorted  | arr[0]<=arr[2]? No      | 4 <= 1? No, left half is not sorted
     | [4,5,1,2,3]             | Update ans               | ans=min(MAX_VAL,1)=1    | Right half must be sorted, update minimum
     | [4,5,1,2,3]             | Eliminate right half     | high=mid-1=1            | Set high to mid-1
2    | [4,5,1,2,3]             | Calculate mid            | mid=0                    | mid = (0+1)/2 = 0
     | [4,5,1,2,3]             | Check if left is sorted  | arr[0]<=arr[0]? Yes     | 4 <= 4? Yes, left half is sorted
     | [4,5,1,2,3]             | Update ans               | ans=min(1,4)=1          | Update minimum
     | [4,5,1,2,3]             | Eliminate left half      | low=mid+1=1             | Set low to mid+1
3    | [4,5,1,2,3]             | Calculate mid            | mid=1                    | mid = (1+1)/2 = 1
     | [4,5,1,2,3]             | Check if left is sorted  | arr[1]<=arr[1]? Yes     | 5 <= 5? Yes, left half is sorted
     | [4,5,1,2,3]             | Update ans               | ans=min(1,5)=1          | Update minimum
     | [4,5,1,2,3]             | Eliminate left half      | low=mid+1=2             | Set low to mid+1
4    | [4,5,1,2,3]             | Check low<=high          | 2<=1? No                | Loop terminates
     | [4,5,1,2,3]             | Return ans               | ans=1                   | Return minimum = 1
```

### Step-by-Step Explanation

1. **Initial Setup**
   - Set low=0, high=4, ans=MAX_VALUE
   ```
   [4, 5, 1, 2, 3]
    L     M     H
   ```

2. **First Iteration**
   - mid = 2, arr[mid] = 1
   - Check if left half is sorted: arr[low] <= arr[mid]? 4 <= 1? No
   - Therefore, right half is sorted, and minimum in right half is at arr[mid] = 1
   - Update ans = min(MAX_VALUE, 1) = 1
   - Eliminate right half: high = mid - 1 = 1
   ```
   [4, 5, 1, 2, 3]
    L  H  M
   ```

3. **Second Iteration**
   - mid = 0, arr[mid] = 4
   - Check if left half is sorted: arr[low] <= arr[mid]? 4 <= 4? Yes
   - Minimum in sorted left half is at arr[low] = 4
   - Update ans = min(1, 4) = 1
   - Eliminate left half: low = mid + 1 = 1
   ```
   [4, 5, 1, 2, 3]
    M  L/H
   ```

4. **Third Iteration**
   - mid = 1, arr[mid] = 5
   - Check if left half is sorted: arr[low] <= arr[mid]? 5 <= 5? Yes
   - Minimum in sorted left half is at arr[low] = 5
   - Update ans = min(1, 5) = 1
   - Eliminate left half: low = mid + 1 = 2
   ```
   [4, 5, 1, 2, 3]
       M  L  H
   ```

5. **Loop Termination**
   - low > high? 2 > 1? Yes, so exit loop
   - Return ans = 1

### Additional Example Cases

1. **Standard Rotated Array**
```
Input:  [7, 8, 9, 1, 2, 3]
Step 1: low=0, high=5, mid=2, arr[mid]=9
        Left half [7,8,9] is sorted, min=7, eliminate left half
Step 2: low=3, high=5, mid=4, arr[mid]=2
        Left half [1,2] is sorted, min=1, eliminate left half
Step 3: low=5, high=5, mid=5, arr[mid]=3
        Left half [3] is sorted, min=1, eliminate left half
Step 4: low=6, high=5, exit loop
Output: 1
```

2. **Not Rotated Array**
```
Input:  [1, 2, 3, 4, 5]
Step 1: low=0, high=4, mid=2, arr[mid]=3
        Left half [1,2,3] is sorted, min=1, eliminate left half
Step 2: low=3, high=4, mid=3, arr[mid]=4
        Left half [4] is sorted, min=1, eliminate left half  
Step 3: low=4, high=4, mid=4, arr[mid]=5
        Left half [5] is sorted, min=1, eliminate left half
Step 4: low=5, high=4, exit loop
Output: 1
```

### Edge Cases Handling
1. **Single Element Array**
   ```
   Input: [5]
   Step 1: low=0, high=0, mid=0, arr[mid]=5
           Left half [5] is sorted, min=5, eliminate left half
   Step 2: low=1, high=0, exit loop
   Output: 5
   ```

2. **Two Element Array**
   ```
   Input: [2, 1]
   Step 1: low=0, high=1, mid=0, arr[mid]=2
           Left half [2] is sorted, min=2, eliminate left half
   Step 2: low=1, high=1, mid=1, arr[mid]=1
           Left half [1] is sorted, min=1, eliminate left half
   Step 3: low=2, high=1, exit loop
   Output: 1
   ```

Key Observations:
1. The algorithm always finds the minimum because it tracks the minimum seen while eliminating halves
2. Even when the array is not rotated, the algorithm works correctly
3. The method efficiently narrows down the search space by half in each iteration

Sample Validation:
Input: [4, 5, 6, 7, 0, 1, 2, 3]
Expected: 0
Output: 0

Key Points:
1. Minimum element is always at the "breaking point" of rotation
2. At least one half of the array is always sorted
3. The smallest element in the sorted half is either at low or mid
4. We can safely eliminate halves after capturing their minimum value

TEST CASES:
1. Input: [3, 4, 5, 1, 2]
   Expected: 1
   Output: 1
2. Input: [11, 13, 15, 17]
   Expected: 11
   Output: 11
3. Input: [11, 13, 15, 17, 1, 3, 5, 7, 9]
   Expected: 1
   Output: 1
4. Input: [5]
   Expected: 5
   Output: 5
 */