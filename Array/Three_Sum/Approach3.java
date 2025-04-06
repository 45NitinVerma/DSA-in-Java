package Array.Three_Sum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* 
# 3Sum
# Problem: Given an integer array nums, return all the triplets [nums[i], nums[j], nums[k]] such that i != j, i != k, and j != k, and nums[i] + nums[j] + nums[k] == 0.
Notice that the solution set must not contain duplicate triplets.

# Intuition: In this approach, we intend to get rid of two things i.e. the HashSet we were using for the look-up operation and the set data structure used to store the unique triplets.

- Main approach: Use two-pointer technique after sorting
- Instead of using HashSet for lookup and storing unique triplets, we can leverage array sorting
- By sorting first, we can skip duplicates and use two pointers to efficiently find complementary numbers

So, We will first sort the array. Then, we will fix a pointer i, and the rest 2 pointers j and k will be moving. 

Now, we need to first understand what the HashSet and the set were doing to make our algorithm work without them. So, the set data structure was basically storing the unique triplets in sorted order and the HashSet was used to search for the third element.

That is why, we will first sort the entire array, and then to get the unique triplets, we will simply skip the duplicate numbers while moving the pointers.

## How to skip duplicate numbers:
As the entire array is sorted, the duplicate numbers will be in consecutive places. So, while moving a pointer, we will check the current element and the adjacent element. Until they become different, we will move the pointer by 1 place. We will follow this process for all 3 pointers. Thus, we can easily skip the duplicate elements while moving the pointers.

Now, we can also remove the HashSet as we have two moving pointers i.e. j and k that will find the appropriate value of arr[j] and arr[k]. So, we do not need that HashSet anymore for the look-up operations.

The process will look like the following:
arr = [-2, -1,   0,   0,   1,     2]
       ↑    ↑  →             ←    ↑
       i    j  greater   lesser   k

Among the 3 pointers, 1 will be fixed and 2 will be moving. In each iteration, we will check if the sum i.e.arr[i]+arr[j]+arr[k] is equal to the target i.e. 0. 

- If the sum is greater, then we need lesser elements and so we will decrease the value of k(i.e. k--). 
- If the sum is lesser than the target, we need a bigger value and so we will increase the value of j (i.e. j++). 
- If the sum is equal to the target, we will simply insert the triplet i.e. arr[i], arr[j], arr[k], into our answer and move the pointers j and k skipping the duplicate elements.

### Observation 1: Sorting Benefits
- Sorting allows easy duplicate skipping
- Enables efficient two-pointer approach
- Makes it easy to control sum by moving pointers

Example:
```
Original: [-1, 0, 1, 2, -1, -4]
Sorted:   [-4, -1, -1, 0, 1, 2]
```

### Observation 2: Two-Pointer Movement
- Fix first number, use two pointers for remaining sum
- Left pointer moves right when sum is too small
- Right pointer moves left when sum is too large

```
nums = [-4, -1, -1, 0, 1, 2]
        i   j           k
If sum < 0: Move j right
If sum > 0: Move k left
```

### Observation 3: Duplicate Handling
- Skip duplicates for i to avoid duplicate triplets
- Skip duplicates for j and k after finding a valid triplet
```
[-4, -1, -1, 0, 1, 2]
     ↑   ↑
    Skip duplicate -1
```

## Step-by-Step Example
Let's work through array [-4, -1, -1, 0, 1, 2]:

1. First Iteration:
   ```
   i = -4, j = -1, k = 2
   sum = -4 + (-1) + 2 = -3 < 0
   Move j right
   ```

2. Second Iteration:
   ```
   i = -1, j = -1, k = 2
   Skip duplicate i
   ```
*/
public class Approach3 {
    // Approach 3: 2 Pointer Approach
    public static List<List<Integer>> findTriplets(int n, int[] arr) {
        List<List<Integer>> result = new ArrayList<>();
        // Sort array to enable two-pointer technique
        Arrays.sort(arr);

        for (int i = 0; i < n; i++) {
            // Skip duplicates for first number
            if (i > 0 && arr[i] == arr[i - 1]) continue;

            // Use two pointers: j moves right, k moves left
            int j = i + 1;
            int k = n - 1;

            while (j < k) {
                int sum = arr[i] + arr[j] + arr[k];
                
                if (sum < 0) {
                    j++; // Need larger sum, move left pointer right
                } else if (sum > 0) {
                    k--; // Need smaller sum, move right pointer left
                } else {
                    // Found valid triplet
                    result.add(Arrays.asList(arr[i], arr[j], arr[k]));
                    j++;
                    k--;
                    
                    // Skip duplicates for second number
                    while (j < k && arr[j] == arr[j - 1]) j++;
                    // Skip duplicates for third number
                    while (j < k && arr[k] == arr[k + 1]) k--;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        // Test case
        int[] arr = {-1, 0, 1, 2, -1, -4};
        int n = arr.length;
        
        List<List<Integer>> result = findTriplets(n, arr);
        
        // Print results
        System.out.println("Input Array: " + Arrays.toString(arr));
        System.out.println("Triplets with sum 0:");
        for (List<Integer> triplet : result) {
            System.out.println(triplet);
        }
    }
}
/*
# Algorithm:
1. First, we will sort the entire array.
2. We will use a loop(say i) that will run from 0 to n-1. This i will represent the fixed pointer. In each iteration, this value will be fixed for all different values of the rest of the 2 pointers. Inside the loop, we will first check if the current and the previous element is the same and if it is we will do nothing and continue to the next value of i.
3. After that, there will be 2 moving pointers i.e. j(starts from i+1) and k(starts from the last index). The pointer j will move forward and the pointer k will move backward until they cross each other while the value of i will be fixed.
- Now we will check the sum i.e. arr[i]+arr[j]+arr[k].
- If the sum is greater, then we need lesser elements and so we will decrease the value of k(i.e. k--). 
- If the sum is lesser than the target, we need a bigger value and so we will increase the value of j (i.e. j++). 
- If the sum is equal to the target, we will simply insert the triplet i.e. arr[i], arr[j], arr[k] into our answer and move the pointers j and k skipping the duplicate elements(i.e. by checking the adjacent elements while moving the pointers).
4. Finally, we will have a list of unique triplets.

### Time Complexity Breakdown per Step
1. Sorting: O(n log n)
2. Main loop: O(n)
3. Two-pointer loop: O(n)
Total: O(n²)

### Space Complexity Breakdown
1. Auxiliary Space: O(1) (excluding output space)
2. Output Space: O(k) where k is number of triplets
Total: O(k)

# Advantages
1. No extra space needed for HashSet
2. Handles duplicates efficiently
3. Better time complexity than brute force

# Limitations
1. Requires sorting the array
2. Modifies input array
3. Not suitable for streaming data

# Step-by-Step Process with Dry Run
### Detailed Execution Table for Input: [-1, 0, 1, 2, -1, -4]

```
Initial Array: [-1, 0, 1, 2, -1, -4]
Sorted Array:  [-4, -1, -1, 0, 1, 2]

Step | Array State        | i  | j  | k | Current Sum | Action                  | Triplets Found
-----|-------------------|----|----|---|-------------|------------------------|---------------
1    | [-4,-1,-1,0,1,2] | -4 | -1 | 2 | -3         | Sum < 0, j++          | []
2    | [-4,-1,-1,0,1,2] | -4 | -1 | 2 | -3         | Sum < 0, j++          | []
3    | [-4,-1,-1,0,1,2] | -4 | 0  | 2 | -2         | Sum < 0, j++          | []
4    | [-4,-1,-1,0,1,2] | -4 | 1  | 2 | -1         | Sum < 0, j++          | []
5    | [-4,-1,-1,0,1,2] | -1 | -1 | 2 | 0          | Found! Add & skip dup  | [[-1,-1,2]]
6    | [-4,-1,-1,0,1,2] | -1 | 0  | 1 | 0          | Found! Add triplet     | [[-1,-1,2],[-1,0,1]]
7    | [-4,-1,-1,0,1,2] | 0  | 1  | 2 | 3          | Sum > 0, k--          | [[-1,-1,2],[-1,0,1]]
```

### Step-by-Step Process Explanation:

1. **Initial Setup (Step 1)**:
   - Original array: [-1, 0, 1, 2, -1, -4]
   - After sorting: [-4, -1, -1, 0, 1, 2]
   - Set i = -4 (first element)
   - j = i + 1 = -1
   - k = last element = 2

2. **First Iteration (Step 1-4)**:
   - With i = -4:
   - Sum = -4 + (-1) + 2 = -3
   - Since sum < 0, move j right
   - Continue moving j until it reaches 1
   - No valid triplets found with i = -4

3. **Second Iteration (Step 5)**:
   - Move to i = -1
   - j = i + 1 = -1
   - k = 2
   - Sum = -1 + (-1) + 2 = 0
   - Found first valid triplet: [-1, -1, 2]

4. **Third Iteration (Step 6)**:
   - Still with i = -1
   - j = 0
   - k = 1
   - Sum = -1 + 0 + 1 = 0
   - Found second valid triplet: [-1, 0, 1]

5. **Final Iteration (Step 7)**:
   - i = 0
   - j = 1
   - k = 2
   - Sum = 0 + 1 + 2 = 3
   - Sum > 0, no more valid triplets possible

### Key Points from the Table:

1. **Pointer Movement**:
   - When sum < 0: j moves right (need larger numbers)
   - When sum > 0: k moves left (need smaller numbers)
   - When sum = 0: record triplet, move both j and k

2. **Duplicate Handling**:
   - Skip duplicate i values: notice we skip second -1 as i
   - After finding triplet, skip duplicate j and k values

3. **Termination Conditions**:
   - Process stops when j and k cross
   - For each i, process all possible j,k combinations

4. **Result Collection**:
   - Two unique triplets found: [-1,-1,2] and [-1,0,1]
   - All triplets sum to exactly 0
   - No duplicate combinations in result

### Visualization of Pointer Movement:
```
Initial State:
[-4, -1, -1, 0, 1, 2]
 i   j           k

After first move:
[-4, -1, -1, 0, 1, 2]
 i       j       k

Found first triplet:
[-4, -1, -1, 0, 1, 2]
     i   j       k

Found second triplet:
[-4, -1, -1, 0, 1, 2]
     i      j  k
```

### Edge Cases Handling
1. **Empty Array**
   ```
   Input: []
   Output: []
   Explanation: No triplets possible
   ```

2. **Array with less than 3 elements**
   ```
   Input: [1, 2]
   Output: []
   Explanation: Not enough elements
   ```

TEST CASES:
1. Input: [-1, 0, 1, 2, -1, -4]
   Expected: [[-1, -1, 2], [-1, 0, 1]]
   Output: [[-1, -1, 2], [-1, 0, 1]]

2. Input: [0, 0, 0, 0]
   Expected: [[0, 0, 0]]
   Output: [[0, 0, 0]]
 */
