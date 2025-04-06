package Array.Three_Sum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/* 
# 3Sum
# Problem: Given an integer array nums, return all the triplets [nums[i], nums[j], nums[k]] such that i != j, i != k, and j != k, and nums[i] + nums[j] + nums[k] == 0.
Notice that the solution set must not contain duplicate triplets.

## Intuition:
In the previous approach, we utilized 3 loops, but now our goal is to reduce it to 2 loops. To achieve this, we need to find a way to calculate arr[k] since we intend to eliminate the third loop (k loop). To calculate arr[k], we can derive a formula as follows: 

arr[k] = target - (arr[i]+arr[j]+arr[k]) = 0-(arr[i]+arr[j]+arr[k]) = -(arr[i]+arr[j]+arr[k]) 

- The core idea is to find three numbers in an array that sum to zero
- Instead of using three nested loops (O(n³)), we optimize to O(n²) using a formula
- For any two numbers (a,b), we can calculate the required third number c = -(a+b)

## Basic Understanding
We need to find all unique combinations of three numbers (i,j,k) where:
1. i + j + k = 0
2. i ≠ j ≠ k (indices must be different)
3. No duplicate triplets allowed

## Key Observations with Examples

### Observation 1: Two Pointer + HashSet Pattern
- Fix one number (i)
- Use HashSet to find pairs that sum to -i
- Sort final triplets to avoid duplicates

Example:
```
Array: [-1, 0, 1, 2, -1, -4]
If we fix i = -1:
Looking for pairs that sum to 1 (-(−1))
Found: [0,1] → Triplet: [-1,0,1]
```

### Observation 2: Avoiding Duplicates
- Use Set<List<Integer>> to store final triplets
- Sort each triplet before adding to set
Example: [-1,0,1] and [0,1,-1] are same after sorting

So, we will first calculate arr[i] and arr[j] using 2 loops and for the third one i.e. arr[k] we will not use another loop and instead we will look up the value 0-(arr[i]+arr[j]) in the set data structure. Thus we can remove the third loop from the algorithm.

For implementing the search operation of the third element,  we will store all the elements between the indices i and j in a HashSet and then we will search for the third element in the HashSet.

## Why we are not inserting all the array elements in the HashSet and then searching for the third element:

Let’s understand this intuition using an example. Assume the given array is {1, 2, -1, -2, 4, 0, -1} and the target = 0. Now, we will notice a situation like the following:
arr = [1, 2, -1, -2, 4, 0, -1]
                  ↑  ↑
                  i  j

Here, arr[i] = -2 and arr[j] = 4.
Therefore, arr[k] = -(arr[i]+arr[j]) = -(-2+4) = -2

- If all the elements were in the set data structure while searching for -2, we would again pick the element at index 3, that is currently pointed by i.
- Hence, the triplet will be [arr[i], arr[j], arr[i]]. And this is an invalid triplet. That is why we cannot insert all the elements into the set data structure.


## Step-by-Step Example
Let's work through array [-1, 0, 1, 2, -1, -4]:

1. First Iteration (i = 0, nums[i] = -1):
   ```
   HashSet: empty
   j = 1 (nums[j] = 0)
   Third = -(-1 + 0) = 1
   HashSet doesn't contain 1, add 0 to HashSet
   HashSet: [0]
   ```

2. Second Iteration (same i, j = 2):
   ```
   nums[j] = 1
   Third = -(-1 + 1) = 0
   HashSet contains 0 → Found triplet [-1,0,1]
   Add 1 to HashSet
   HashSet: [0,1]
   ```
[Continue with remaining steps...]
*/

public class Approach2 {
    // Approach 2: Using 2 nested loops
    public static List<List<Integer>> triplet(int n, int[] arr) {
        // Using Set to store unique triplets
        Set<List<Integer>> st = new HashSet<>();

        for (int i = 0; i < n; i++) {
            // HashSet to store elements between i and j
            Set<Integer> hashset = new HashSet<>();
            for (int j = i + 1; j < n; j++) {
                // Calculate required third element
                int third = -(arr[i] + arr[j]);

                // If third element exists in hashset, we found a triplet
                if (hashset.contains(third)) {
                    List<Integer> temp = Arrays.asList(arr[i], arr[j], third);
                    // Sort to avoid duplicates like [-1,0,1] and [0,1,-1]
                    temp.sort(null);
                    st.add(temp);
                }
                // Add current element to hashset
                hashset.add(arr[j]);
            }
        }

        // Convert set to list for return
        return new ArrayList<>(st);
    }

    // Main method for testing
    public static void main(String[] args) {
        int[] arr = {-1, 0, 1, 2, -1, -4};
        int n = arr.length;
        
        List<List<Integer>> result = triplet(n, arr);
        
        // Pretty print results
        System.out.println("Input Array: " + Arrays.toString(arr));
        System.out.println("Triplets that sum to zero:");
        for (List<Integer> triplet : result) {
            System.out.println(triplet);
        }
    }
}
/*
# Algorithm:
1. First, we will declare a set data structure as we want unique triplets.
2. Then we will use the first loop(say i) that will run from 0 to n-1.
3. Inside it, there will be the second loop(say j) that will run from i+1 to n-1.
4. Before the second loop, we will declare another HashSet to store the array elements as we intend to search for the third element using this HashSet.
5. Inside the second loop, we will calculate the value of the third element i.e. -(arr[i]+arr[j]).
6. If the third element exists in the HashSet, we will sort these 3 values i.e. arr[i], arr[j], and the third element, and insert it in the set data structure declared in step 1.
7. After that, we will insert the j-th element i.e. arr[j] in the HashSet as we only want to insert those array elements that are in between indices i and j.
8. Finally, we will return a list of triplets stored in the set data structure.

### Time Complexity Breakdown per Step
1. Outer loop: O(n)
2. Inner loop: O(n)
3. HashSet operations: O(1)
4. Sorting triplet: O(1) (always 3 elements)
Total: O(n²)

### Space Complexity Breakdown
- Auxiliary Space
 1. Set for unique triplets: O(n)
 2. HashSet for elements: O(n)
- Input Space: O(n)

# Advantages
1. More efficient than brute force O(n³)
2. Automatically handles duplicates
3. No need for explicit sorting of input array

# Limitations
1. Still O(n²) complexity
2. Extra space for HashSet
3. Order of elements in output might differ from input

# Step-by-Step Process with Dry Run
Let me provide a detailed dry run with visualization and step-by-step explanation.

# Detailed Dry Run for Input: [-1, 0, 1, 2, -1, -4]

### Complete Execution Table
```
Step | i (index) | j (index) | arr[i] | arr[j] | third | HashSet     | Found Triplet  | Result Set
-----|-----------|-----------|---------|---------|--------|-------------|----------------|------------
1    | 0         | 1         | -1      | 0       | 1      | [0]         | No            | []
2    | 0         | 2         | -1      | 1       | 0      | [0,1]       | Yes           | [-1,0,1]
3    | 0         | 3         | -1      | 2       | -1     | [0,1,2]     | No            | [-1,0,1]
4    | 0         | 4         | -1      | -1      | 2      | [0,1,2,-1]  | Yes           | [-1,0,1],[-1,-1,2]
5    | 0         | 5         | -1      | -4      | 5      | [0,1,2,-1,-4]| No           | [-1,0,1],[-1,-1,2]
6    | 1         | 2         | 0       | 1       | -1     | [1]         | No            | [-1,0,1],[-1,-1,2]
7    | 1         | 3         | 0       | 2       | -2     | [1,2]       | No            | [-1,0,1],[-1,-1,2]
8    | 1         | 4         | 0       | -1      | 1      | [1,2,-1]    | Yes           | [-1,0,1],[-1,-1,2]
9    | 1         | 5         | 0       | -4      | 4      | [1,2,-1,-4] | No            | [-1,0,1],[-1,-1,2]
...continues
```

### Step-by-Step Process Explanation:

1. **Step 1:**
   - i = 0 (arr[i] = -1)
   - j = 1 (arr[j] = 0)
   - third = -(-1 + 0) = 1
   - Add 0 to HashSet
   - No triplet found as 1 not in HashSet

2. **Step 2:**
   - i = 0 (arr[i] = -1)
   - j = 2 (arr[j] = 1)
   - third = -(-1 + 1) = 0
   - HashSet contains 0
   - Found triplet [-1,0,1]
   - Add 1 to HashSet

3. **Step 3:**
   - i = 0 (arr[i] = -1)
   - j = 3 (arr[j] = 2)
   - third = -(-1 + 2) = -1
   - Add 2 to HashSet
   - No new triplet found

4. **Step 4:**
   - i = 0 (arr[i] = -1)
   - j = 4 (arr[j] = -1)
   - third = -(-1 + -1) = 2
   - HashSet contains 2
   - Found triplet [-1,-1,2]
   - Add -1 to HashSet

### Visual State Changes:

```
Initial Array: [-1, 0, 1, 2, -1, -4]

Step 1:
i → -1
     j
HashSet: [0]
Triplets: []

Step 2:
i → -1
        j
HashSet: [0,1]
Triplets: [[-1,0,1]]

Step 3:
i → -1
           j
HashSet: [0,1,2]
Triplets: [[-1,0,1]]

Step 4:
i → -1
              j
HashSet: [0,1,2,-1]
Triplets: [[-1,0,1],[-1,-1,2]]
```

### Key Points in Process:

1. **HashSet Management:**
   - HashSet is cleared for each new i
   - Only elements between i and j are stored
   - Used for O(1) lookup of third element

2. **Triplet Formation:**
   - When third element found in HashSet:
     * Create triplet [arr[i], arr[j], third]
     * Sort triplet
     * Add to result set

3. **Duplicate Handling:**
   - Using Set<List<Integer>> automatically removes duplicates
   - Sorting each triplet ensures [-1,0,1] and [1,-1,0] are considered same

4. **Termination:**
   - Process continues until i reaches n-2
   - For each i, j goes from i+1 to n-1

### Common Patterns Observed:

1. **Finding Third Element:**
   ```
   For any i,j:
   third = -(arr[i] + arr[j])
   If third exists in HashSet → Valid triplet
   ```

2. **HashSet Usage:**
   ```
   New HashSet for each i
   Add elements as j progresses
   Lookup third element
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
   Input: [1,2]
   Output: []
   Explanation: Can't form triplet
   ```

TEST CASES:
1. Input: [-1, 0, 1, 2, -1, -4]
   Expected: [[-1,-1,2],[-1,0,1]]
   Output: [[-1,-1,2],[-1,0,1]]
2. Input: [0,0,0]
   Expected: [[0,0,0]]
   Output: [[0,0,0]]
3. Input: []
   Expected: []
   Output: []
 */
