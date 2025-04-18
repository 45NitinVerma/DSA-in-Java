package Array.Four_Sum;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
/* 
# Problem: 4Sum
Given an array nums of n integers, return an array of all the unique quadruplets [nums[a], nums[b], nums[c], nums[d]] such that:

0 <= a, b, c, d < n
a, b, c, and d are distinct.
nums[a] + nums[b] + nums[c] + nums[d] == target
You may return the answer in any order.

# Intuition
In the previous approach, we were using 4 loops but in this, we want to reduce that to 3 loops. We have to somehow manage to calculate nums[l] as we are planning to remove the fourth loop(i.e. l). In order to calculate nums[l], we can derive a formula like the following:
- nums[l] = target - (nums[i] + nums[j] + nums[k])

So, we will first calculate the triplets nums[i], nums[j], and nums[k] using 3 loops and for the fourth one i.e. nums[l] we will not use another loop and instead we will look up the value target-(nums[i]+nums[j]+nums[k]) in the array. Thus we can remove the fourth loop from the algorithm.

For implementing the search operation of the fourth element, we will use a similar technique as we used in the case of the 3-sum problem. We will store all the elements between the indices j and k in a HashSet and then we will search for the fourth element in the HashSet.

# Why we are not inserting all the array elements in the HashSet and then searching for the fourth element:

Let’s understand this intuition using an example. Assume the given array is {1, 2, -1, -2, 2, 0, -1} and the target = 0. Now, we will notice a situation like the following:

1, 2, -1, -2, 2, 0, -1
i     j          k

Now, the fourth element should be target-(nums[i]+nums[j]+nums[k]) = 0 - (1-1+0) = 0. Now, if all the array elements are in the HashSet and we search for 0, we will end up taking the 0 at index k again. The quadruplets will be {nums[i], nums[j], nums[k], nums[k]} but this is absolutely incorrect. That is why we need to only consider the elements that are in between the indices j and k.

## Basic Understanding
The problem requires finding all sets of four numbers from an array that add up to a specified target value, without including duplicate quadruplets in the result.

## Key Observations with Examples

### Observation 1: Reducing to Two Sum Problem
We can reduce a 4-sum problem to a 3-sum problem and finally to a 2-sum problem
* First loop picks element 'a'
* Second loop picks element 'b'
* Third loop picks element 'c'
* Instead of a fourth loop, we use a hash set to find element 'd' such that a + b + c + d = target

### Observation 2: Hash Set for Finding the Fourth Element
For each triplet (a, b, c), we need to find if there exists a value 'd' such that a + b + c + d = target
* This can be rearranged as d = target - (a + b + c)
* We can use a hash set to check if this fourth value exists in O(1) time
* Example: If a=1, b=2, c=3, and target=10, we need d=4

### Observation 3: Eliminating Duplicates
Without sorting the quadruplets, the same set of numbers in different orders would be considered different
* Example: [1,2,3,4] and [4,3,2,1] represent the same quadruplet
* By sorting each quadruplet and using a set to store them, we eliminate duplicates

### Observation 4: Avoiding Integer Overflow
When dealing with potentially large integers, we need to handle possible overflow
* Using long data type for sums helps avoid integer overflow
* Example: If two elements are close to Integer.MAX_VALUE, their sum could overflow

## Step-by-Step Example
Let's work through the example: nums = [1, 0, -1, 0, -2, 2], target = 0

1. Step One:
   ```
   i=0, nums[i]=1
   j=1, nums[j]=0
   hashset = {}
   ```
   Start with first two elements 1 and 0

2. Step Two:
   ```
   k=2, nums[k]=-1
   sum = 1 + 0 + (-1) = 0
   fourth = 0 - 0 = 0
   ```
   Check if 0 exists in hashset (it doesn't yet)
   Add -1 to hashset: hashset = {-1}

3. Step Three:
   ```
   k=3, nums[k]=0
   sum = 1 + 0 + 0 = 1
   fourth = 0 - 1 = -1
   ```
   Check if -1 exists in hashset (it does!)
   Add quadruplet [1, 0, 0, -1] to result after sorting: [-1, 0, 0, 1]
   Add 0 to hashset: hashset = {-1, 0}

4. Step Four:
   ```
   Continue this process for all valid combinations of i, j, and k
   ```

5. Final Result:
   ```
   [[-2, -1, 1, 2], [-2, 0, 0, 2], [-1, 0, 0, 1]]
   ```

## Special Cases

### Case 1: Empty Array
Input: []
- Behavior: No quadruplets possible
- Result: Empty list []

### Case 2: Array with Less Than 4 Elements
Input: [1, 2, 3]
- Behavior: Not possible to form a quadruplet
- Result: Empty list []

### Case 3: Array with Duplicate Elements
Input: [1, 1, 1, 1], target = 4
- Behavior: One quadruplet [1, 1, 1, 1] is found
- Result: [[1, 1, 1, 1]]
*/
public class Approach2 {
    // Approach 2: Using 3 nested loops
    // Method to find all unique quadruplets that sum to the target
    public static List<List<Integer>> fourSum(int[] nums, int target) {
        int n = nums.length; // size of the array
        Set<List<Integer>> st = new HashSet<>(); // Using set to store unique quadruplets

        // Three nested loops to fix three elements
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                // Hash set to find the fourth element in O(1) time
                Set<Long> hashset = new HashSet<>();
                for (int k = j + 1; k < n; k++) {
                    // Calculate sum of first three elements
                    // Using long to avoid integer overflow
                    long sum = nums[i] + nums[j];
                    sum += nums[k];
                    
                    // Calculate required fourth element
                    long fourth = target - sum;
                    
                    // If fourth element exists in hashset, we found a quadruplet
                    if (hashset.contains(fourth)) {
                        // Create a new list for the quadruplet
                        List<Integer> temp = new ArrayList<>();
                        temp.add(nums[i]);
                        temp.add(nums[j]);
                        temp.add(nums[k]);
                        temp.add((int) fourth);
                        
                        // Sort to handle duplicates (e.g., [1,2,3,4] and [4,3,2,1])
                        temp.sort(Integer::compareTo);
                        
                        // Add to result set
                        st.add(temp);
                    }
                    
                    // Add current element to hashset for future checks
                    hashset.add((long) nums[k]);
                }
            }
        }
        
        // Convert set to list for return
        List<List<Integer>> ans = new ArrayList<>(st);
        return ans;
    }

    // Main method for testing
    public static void main(String[] args) {
        // Example input
        int[] nums = {4, 3, 3, 4, 4, 2, 1, 2, 1, 1};
        int target = 9;
        
        // Call the fourSum method
        List<List<Integer>> ans = fourSum(nums, target);
        
        // Display the results
        System.out.println("The quadruplets are: ");
        for (List<Integer> it : ans) {
            System.out.print("[");
            for (Integer ele : it) {
                System.out.print(ele + " ");
            }
            System.out.print("] ");
        }
        System.out.println();
    }
}
/*
# Algorithm
1. Create a HashSet to store unique quadruplets
2. Iterate through the array with three nested loops (i, j, k)
3. For each iteration of k:
   - Calculate the sum of nums[i], nums[j], and nums[k]
   - Calculate the required fourth value = target - sum
   - Check if the fourth value exists in a hash set
   - If it does, create and sort a quadruplet and add it to the result set
   - Add nums[k] to the hash set
4. Convert the resulting set to a list and return it

### Time Complexity Breakdown per Step
1. Three nested loops: O(n³) where n is the array length
2. Hash set operations (contains/add): O(1)
3. Creating and sorting quadruplets: O(1) (as we only sort 4 elements)
4. Converting set to list: O(m) where m is the number of unique quadruplets

Total: O(n³) where n is the size of the input array

### Space Complexity Breakdown
1. Auxiliary Space:
   - HashSet for storing unique quadruplets: O(m) where m is the number of unique quadruplets
   - HashSet for finding fourth elements: O(n) in worst case
   - Temporary lists for quadruplets: O(1)
2. Input Space: O(n) for the input array

Total: O(n + m) where n is the array size and m is the number of unique quadruplets

# Advantages
1. Efficient solution with O(n³) time complexity (better than the naive O(n⁴) approach)
2. Handles duplicates effectively using sets
3. Avoids integer overflow by using long data type
4. No need to sort the entire array

# Limitations
1. Still has O(n³) time complexity, which can be slow for large arrays
2. Requires extra space for hash sets
3. May have performance issues with many duplicate elements

# Potential Improvements
1. Add early termination conditions when possible
2. Implement a two-pointer approach after sorting the array to reduce complexity to O(n³)
3. Add array size checks to handle edge cases more efficiently
4. Pre-sort the array and use two pointers for the last two elements to potentially skip duplicates

# Four Sum Algorithm: Detailed Dry Run

Let's perform a detailed dry run with the example: `nums = [1, 0, -1, 0, -2, 2]`, `target = 0`

## Detailed Execution Table

| Step | i,j,k indices | nums[i], nums[j], nums[k] | sum | fourth | hashset | Action | Quadruplet Found | Result Set |
|------|---------------|---------------------------|-----|--------|---------|--------|------------------|------------|
| 1 | i=0,j=1,k=2 | 1, 0, -1 | 0 | 0 | {} | Check if 0 is in hashset | No | {} |
| 2 | i=0,j=1,k=2 | 1, 0, -1 | 0 | 0 | {} → {-1} | Add -1 to hashset | No | {} |
| 3 | i=0,j=1,k=3 | 1, 0, 0 | 1 | -1 | {-1} | Check if -1 is in hashset | Yes | {[-1,0,0,1]} |
| 4 | i=0,j=1,k=3 | 1, 0, 0 | 1 | -1 | {-1} → {-1,0} | Add 0 to hashset | Yes | {[-1,0,0,1]} |
| 5 | i=0,j=1,k=4 | 1, 0, -2 | -1 | 1 | {-1,0} | Check if 1 is in hashset | No | {[-1,0,0,1]} |
| 6 | i=0,j=1,k=4 | 1, 0, -2 | -1 | 1 | {-1,0} → {-1,0,-2} | Add -2 to hashset | No | {[-1,0,0,1]} |
| 7 | i=0,j=1,k=5 | 1, 0, 2 | 3 | -3 | {-1,0,-2} | Check if -3 is in hashset | No | {[-1,0,0,1]} |
| 8 | i=0,j=1,k=5 | 1, 0, 2 | 3 | -3 | {-1,0,-2} → {-1,0,-2,2} | Add 2 to hashset | No | {[-1,0,0,1]} |
| 9 | i=0,j=2,k=3 | 1, -1, 0 | 0 | 0 | {} | Check if 0 is in hashset | No | {[-1,0,0,1]} |
| 10 | i=0,j=2,k=3 | 1, -1, 0 | 0 | 0 | {} → {0} | Add 0 to hashset | No | {[-1,0,0,1]} |
| 11 | i=0,j=2,k=4 | 1, -1, -2 | -2 | 2 | {0} | Check if 2 is in hashset | No | {[-1,0,0,1]} |
| 12 | i=0,j=2,k=4 | 1, -1, -2 | -2 | 2 | {0} → {0,-2} | Add -2 to hashset | No | {[-1,0,0,1]} |
| 13 | i=0,j=2,k=5 | 1, -1, 2 | 2 | -2 | {0,-2} | Check if -2 is in hashset | Yes | {[-1,0,0,1],[-2,-1,1,2]} |
| 14 | i=0,j=2,k=5 | 1, -1, 2 | 2 | -2 | {0,-2} → {0,-2,2} | Add 2 to hashset | Yes | {[-1,0,0,1],[-2,-1,1,2]} |
| 15 | i=0,j=3,k=4 | 1, 0, -2 | -1 | 1 | {} | Check if 1 is in hashset | No | {[-1,0,0,1],[-2,-1,1,2]} |
| 16 | i=0,j=3,k=4 | 1, 0, -2 | -1 | 1 | {} → {-2} | Add -2 to hashset | No | {[-1,0,0,1],[-2,-1,1,2]} |
| 17 | i=0,j=3,k=5 | 1, 0, 2 | 3 | -3 | {-2} | Check if -3 is in hashset | No | {[-1,0,0,1],[-2,-1,1,2]} |
| 18 | i=0,j=3,k=5 | 1, 0, 2 | 3 | -3 | {-2} → {-2,2} | Add 2 to hashset | No | {[-1,0,0,1],[-2,-1,1,2]} |
| 19 | i=0,j=4,k=5 | 1, -2, 2 | 1 | -1 | {} | Check if -1 is in hashset | No | {[-1,0,0,1],[-2,-1,1,2]} |
| 20 | i=0,j=4,k=5 | 1, -2, 2 | 1 | -1 | {} → {2} | Add 2 to hashset | No | {[-1,0,0,1],[-2,-1,1,2]} |
| 21 | i=1,j=2,k=3 | 0, -1, 0 | -1 | 1 | {} | Check if 1 is in hashset | No | {[-1,0,0,1],[-2,-1,1,2]} |
| 22 | i=1,j=2,k=3 | 0, -1, 0 | -1 | 1 | {} → {0} | Add 0 to hashset | No | {[-1,0,0,1],[-2,-1,1,2]} |
| 23 | i=1,j=2,k=4 | 0, -1, -2 | -3 | 3 | {0} | Check if 3 is in hashset | No | {[-1,0,0,1],[-2,-1,1,2]} |
| 24 | i=1,j=2,k=4 | 0, -1, -2 | -3 | 3 | {0} → {0,-2} | Add -2 to hashset | No | {[-1,0,0,1],[-2,-1,1,2]} |
| 25 | i=1,j=2,k=5 | 0, -1, 2 | 1 | -1 | {0,-2} | Check if -1 is in hashset | No | {[-1,0,0,1],[-2,-1,1,2]} |
| 26 | i=1,j=2,k=5 | 0, -1, 2 | 1 | -1 | {0,-2} → {0,-2,2} | Add 2 to hashset | No | {[-1,0,0,1],[-2,-1,1,2]} |
| 27 | i=1,j=3,k=4 | 0, 0, -2 | -2 | 2 | {} | Check if 2 is in hashset | No | {[-1,0,0,1],[-2,-1,1,2]} |
| 28 | i=1,j=3,k=4 | 0, 0, -2 | -2 | 2 | {} → {-2} | Add -2 to hashset | No | {[-1,0,0,1],[-2,-1,1,2]} |
| 29 | i=1,j=3,k=5 | 0, 0, 2 | 2 | -2 | {-2} | Check if -2 is in hashset | Yes | {[-1,0,0,1],[-2,-1,1,2],[-2,0,0,2]} |
| 30 | i=1,j=3,k=5 | 0, 0, 2 | 2 | -2 | {-2} → {-2,2} | Add 2 to hashset | Yes | {[-1,0,0,1],[-2,-1,1,2],[-2,0,0,2]} |
| ... | ... | ... | ... | ... | ... | ... | ... | ... |

## Step-by-Step Process Explanation

1. **Step 1-2: First Iteration (i=0, j=1, k=2)**
   - Elements: 1, 0, -1
   - Sum: 1+0+(-1) = 0
   - Fourth number needed: 0-0 = 0
   - Hashset is empty, so no match found
   - Add -1 to hashset: {-1}
   - No quadruplet found

2. **Step 3-4: Second Iteration (i=0, j=1, k=3)**
   - Elements: 1, 0, 0
   - Sum: 1+0+0 = 1
   - Fourth number needed: 0-1 = -1
   - Hashset contains -1, MATCH FOUND!
   - Create quadruplet [1,0,0,-1]
   - Sort it to [-1,0,0,1]
   - Add to result set: {[-1,0,0,1]}
   - Add 0 to hashset: {-1,0}

3. **Step 5-6: Third Iteration (i=0, j=1, k=4)**
   - Elements: 1, 0, -2
   - Sum: 1+0+(-2) = -1
   - Fourth number needed: 0-(-1) = 1
   - Hashset doesn't contain 1, no match
   - Add -2 to hashset: {-1,0,-2}
   - No new quadruplet found

4. **Step 7-8: Fourth Iteration (i=0, j=1, k=5)**
   - Elements: 1, 0, 2
   - Sum: 1+0+2 = 3
   - Fourth number needed: 0-3 = -3
   - Hashset doesn't contain -3, no match
   - Add 2 to hashset: {-1,0,-2,2}
   - No new quadruplet found

5. **Step 9-10: Switch to Next j (i=0, j=2, k=3)**
   - Elements: 1, -1, 0
   - Sum: 1+(-1)+0 = 0
   - Fourth number needed: 0-0 = 0
   - New hashset is empty, no match
   - Add 0 to hashset: {0}
   - No new quadruplet found

6. **Step 11-12: Continue (i=0, j=2, k=4)**
   - Elements: 1, -1, -2
   - Sum: 1+(-1)+(-2) = -2
   - Fourth number needed: 0-(-2) = 2
   - Hashset doesn't contain 2, no match
   - Add -2 to hashset: {0,-2}
   - No new quadruplet found

7. **Step 13-14: Important Match (i=0, j=2, k=5)**
   - Elements: 1, -1, 2
   - Sum: 1+(-1)+2 = 2
   - Fourth number needed: 0-2 = -2
   - Hashset contains -2, MATCH FOUND!
   - Create quadruplet [1,-1,2,-2]
   - Sort it to [-2,-1,1,2]
   - Add to result set: {[-1,0,0,1], [-2,-1,1,2]}
   - Add 2 to hashset: {0,-2,2}

8. **Steps 15-28: Continue with other combinations**
   - Several iterations with no new quadruplets found
   - Various elements are added to hashsets

9. **Step 29-30: Another Match (i=1, j=3, k=5)**
   - Elements: 0, 0, 2
   - Sum: 0+0+2 = 2
   - Fourth number needed: 0-2 = -2
   - Hashset contains -2, MATCH FOUND!
   - Create quadruplet [0,0,2,-2]
   - Sort it to [-2,0,0,2]
   - Add to result set: {[-1,0,0,1], [-2,-1,1,2], [-2,0,0,2]}
   - Add 2 to hashset: {-2,2}

10. **Remaining Steps**
    - Algorithm continues checking all remaining combinations
    - No more unique quadruplets found
    - Final result: {[-1,0,0,1], [-2,-1,1,2], [-2,0,0,2]}

## Key Points from the Dry Run:

1. **Hashset Reset**: A new hashset is created for each (i,j) pair to find possible elements for the fourth position.

2. **Quadruplet Checking**: Each time a fourth element is found in the hashset, we create a quadruplet, sort it, and add it to our result set.

3. **Sorting for Deduplication**: Sorting each quadruplet ensures that we don't add the same combination in different orders.

4. **Adding to Hashset**: After checking if the required fourth element exists, we add the current element to the hashset for future checks.

5. **Result Set**: Using a set for the result ensures we don't have duplicate quadruplets, even if they're found through different iteration paths.

6. **Final Result**: After processing all combinations, we return the unique quadruplets that sum to the target value.

### Step-by-Step Explanation

1. **Initialize Outer Loops**
   - Set i=0, nums[i]=1
   - Set j=1, nums[j]=0
   - Initialize empty hashset
   ```
   i=0, j=1, hashset={}
   ```

2. **First Inner Loop Iteration**
   - Set k=2, nums[k]=-1
   - Calculate sum = 1 + 0 + (-1) = 0
   - Calculate fourth = 0 - 0 = 0
   - Check if 0 is in hashset (it's not)
   - Add -1 to hashset
   ```
   hashset={-1}, no quadruplet found
   ```

3. **Second Inner Loop Iteration**
   - Set k=3, nums[k]=0
   - Calculate sum = 1 + 0 + 0 = 1
   - Calculate fourth = 0 - 1 = -1
   - Check if -1 is in hashset (it is!)
   - Create quadruplet [1,0,0,-1], sort it to [-1,0,0,1]
   - Add to result set
   - Add 0 to hashset
   ```
   hashset={-1,0}, found quadruplet [-1,0,0,1]
   ```

### Additional Example Cases

1. **Simple Case**
```
Input:  [1,2,3,4], target = 10
Step 1: i=0, j=1, k=2, sum=6, fourth=4, found=true
Output: [[1,2,3,4]]
```

2. **Multiple Quadruplets**
```
Input:  [4,3,3,4,4,2,1,2,1,1], target = 9
Step 1: Process all triplets and find matching fourth elements
Output: [[1,1,3,4], [1,2,2,4], [1,2,3,3]]
```

### Edge Cases Handling
1. **Empty Array**
   ```
   Input: [], target = 0
   Output: []
   No iterations occur, empty list returned
   ```

2. **Insufficient Elements**
   ```
   Input: [1,2,3], target = 6
   Output: []
   Not enough elements for a quadruplet
   ```

3. **Handling Large Numbers**
   ```
   Input: [1000000000,1000000000,1000000000,1000000000], target = 4000000000
   Using long prevents integer overflow
   Output: [[1000000000,1000000000,1000000000,1000000000]]
   ```

Key Observations:
1. Using a hash set for the fourth element reduces time complexity from O(n⁴) to O(n³)
2. Sorting each quadruplet before adding to the result set handles duplicates
3. Using a hash set to store quadruplets eliminates duplicate results
4. Using long data type prevents integer overflow issues

Sample Validation:
Input: [4, 3, 3, 4, 4, 2, 1, 2, 1, 1], target = 9
Expected: [[1,1,3,4], [1,2,2,4], [1,2,3,3]]
Output: [[1,1,3,4], [1,2,2,4], [1,2,3,3]]

TEST CASES:
1. Input: [1,0,-1,0,-2,2], target = 0
   Expected: [[-2,-1,1,2], [-2,0,0,2], [-1,0,0,1]]
   Output: [[-2,-1,1,2], [-2,0,0,2], [-1,0,0,1]]
2. Input: [2,2,2,2,2], target = 8
   Expected: [[2,2,2,2]]
   Output: [[2,2,2,2]]
3. Input: [1000000000,1000000000,1000000000,1000000000], target = 4000000000
   Expected: [[1000000000,1000000000,1000000000,1000000000]]
   Output: [[1000000000,1000000000,1000000000,1000000000]]
 */
