package Array.Next_Permutation.Approach1;
/* 
Problem: Find the next lexicographically greater permutation of an integer array.
If no greater permutation exists, return the smallest possible permutation.

Intuition: 
1. Generate all possible permutations using backtracking with boolean frequency array
2. Convert input array to list for comparison
3. Sort all permutations lexicographically
4. Find input permutation in sorted list
5. Return next permutation if exists, else return first permutation
*/
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Collections;

public class Approach1c {
    /*
     * Approach 1(c): Getting all the Permutations using Backtracking with boolean
     * frequency array and finding next permutation after sorting
     */
    public static void nextPermutation(int[] nums) {
        // Generate all permutations
        List<List<Integer>> allPerms = permute(nums);

        // Convert input array to list for comparison
        List<Integer> current = new ArrayList<>();
        for (int num : nums) {
            current.add(num);
        }

        // Sort permutations lexicographically
        Collections.sort(allPerms, (a, b) -> {
            for (int i = 0; i < a.size(); i++) {
                if (!a.get(i).equals(b.get(i))) {
                    return a.get(i) - b.get(i);
                }
            }
            return 0;
        });

        // Find current permutation in sorted list
        int index = -1;
        for (int i = 0; i < allPerms.size(); i++) {
            if (allPerms.get(i).equals(current)) {
                index = i;
                break;
            }
        }

        // Get next permutation or first if current is last
        List<Integer> next = allPerms.get((index + 1) % allPerms.size());

        // Update input array in-place
        for (int i = 0; i < nums.length; i++) {
            nums[i] = next.get(i);
        }
    }

    // Generates all permutations using backtracking
    private static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> currentPerm = new ArrayList<>();
        boolean[] used = new boolean[nums.length];
        recurPermute(nums, currentPerm, result, used);
        return result;
    }

    // Recursive helper for generating permutations
    private static void recurPermute(int[] nums, List<Integer> currentPerm,
            List<List<Integer>> result, boolean[] used) {
        // Base case: permutation is complete
        if (currentPerm.size() == nums.length) {
            result.add(new ArrayList<>(currentPerm));
            return;
        }

        // Try each number in current position
        for (int i = 0; i < nums.length; i++) {
            if (!used[i]) {
                // Choose
                used[i] = true;
                currentPerm.add(nums[i]);

                // Explore
                recurPermute(nums, currentPerm, result, used);

                // Unchoose
                currentPerm.remove(currentPerm.size() - 1);
                used[i] = false;
            }
        }
    }

    public static void main(String[] args) {
        int[] nums = { 1, 2, 3 };

        System.out.println("Original Array:");
        System.out.println(Arrays.toString(nums));

        nextPermutation(nums);

        System.out.println("Next Permutation:");
        System.out.println(Arrays.toString(nums));
    }
}

/* 
ALGORITHM:
1. Generate all permutations using backtracking:
  - Use boolean array to track used numbers
  - Build permutations recursively
  - Store in result list
2. Convert input array to list for comparison
3. Sort all permutations lexicographically
4. Find index of current permutation
5. Return next permutation or first if current is last
6. Update input array in-place

Complexity Analysis:
TIME COMPLEXITY: O(n! * n)
- Generating permutations: O(n!)
- Sorting permutations: O(n! * n * log(n!))
- Finding current permutation: O(n! * n)

SPACE COMPLEXITY:
1. Auxiliary Space: O(n! * n)
  - Storing all permutations: O(n! * n)
  - Recursion stack: O(n)
  - Used array: O(n)
2. Input Space: O(n)

Advantages:
1. Guaranteed to find correct next permutation
2. Works for arrays with duplicate elements
3. Simple to understand and implement

Limitations:
1. Very high time complexity
2. High space complexity
3. Generates unnecessary permutations
4. Inefficient for large arrays

Potential Improvements:
1. Implement single-pass solution:
  - Find first decreasing element from right
  - Find next greater element
  - Reverse remaining elements
2. Remove sorting step by generating permutations in order
3. Handle edge cases without generating all permutations
4. Add early stopping when next permutation is found

Input Array: [1, 2, 3]

EXECUTION FLOW TABLE
------------------------------------------------------------------------------------------
Step | Operation          | Current State    | Details                    | Result/Output
------------------------------------------------------------------------------------------
1    | Initialize        | nums=[1,2,3]     | Start backtracking        | result=[]
                        | used=[F,F,F]      | currentPerm=[]            |
------------------------------------------------------------------------------------------
2    | First recursion   | used=[T,F,F]     | Choose 1                  | currentPerm=[1]
     | depth=1          | Options: 2,3      | Mark 1 as used            |
------------------------------------------------------------------------------------------
3    | Recursion        | used=[T,T,F]     | Choose 2                  | currentPerm=[1,2]
     | depth=2          | Options: 3        | Mark 2 as used            |
------------------------------------------------------------------------------------------
4    | Recursion        | used=[T,T,T]     | Choose 3                  | result=[[1,2,3]]
     | depth=3          | Options: none     | First permutation found   | currentPerm=[1,2,3]
------------------------------------------------------------------------------------------
5    | Backtrack        | used=[T,T,F]     | Unchoose 3               | currentPerm=[1,2]
     | depth=2          | Options: 3        | Try next option           |
------------------------------------------------------------------------------------------
6    | Recursion        | used=[T,F,T]     | Choose 3                  | currentPerm=[1,3]
     | depth=2          | Options: 2        | Try different number      |
------------------------------------------------------------------------------------------
7    | Recursion        | used=[T,T,T]     | Choose 2                  | result=[[1,2,3],
     | depth=3          | Options: none     | Second permutation found  |        [1,3,2]]
------------------------------------------------------------------------------------------
8    | Continue         | ...              | Process repeats for       | All permutations:
     | Backtracking     |                  | starting with 2,3         | [[1,2,3],[1,3,2],
     |                  |                  |                           | [2,1,3],[2,3,1],
     |                  |                  |                           | [3,1,2],[3,2,1]]
------------------------------------------------------------------------------------------
9    | Sort             | Sorted list of   | Sort permutations         | Same as above
     | Permutations     | all permutations | lexicographically        | (already sorted)
------------------------------------------------------------------------------------------
10   | Find Current     | index = 0        | Locate [1,2,3] in        | Found at index 0
     | Permutation      |                  | sorted list              |
------------------------------------------------------------------------------------------
11   | Get Next         | next = [1,3,2]   | Get permutation at       | [1,3,2]
     | Permutation      |                  | index + 1                |
------------------------------------------------------------------------------------------
12   | Update Array     | nums=[1,3,2]     | Modify input array       | Final output:
     |                  |                  | with next permutation    | [1,3,2]
------------------------------------------------------------------------------------------

STEP-BY-STEP PROCESS EXPLANATION:

1. Initialization Phase:
   - Create empty result list for all permutations
   - Initialize boolean used[] array to track used numbers
   - Create empty currentPerm list for building permutations

2. Backtracking Process (Steps 2-7):
   a) First Level (depth=1):
      - Try each number as first digit
      - Start with 1: used=[T,F,F]
      - Add 1 to currentPerm
   
   b) Second Level (depth=2):
      - Try each unused number as second digit
      - Choose 2: used=[T,T,F]
      - Add 2 to currentPerm
   
   c) Third Level (depth=3):
      - Only 3 remains unused
      - Generate complete permutation [1,2,3]
      - Add to result list
   
   d) Backtracking:
      - Remove last number (3)
      - Try next option at previous level
      - Generate [1,3,2]
      - Continue process

3. Permutation Generation (Step 8):
   - Process continues for all starting numbers
   - Generates all possible permutations
   - Stores them in result list

4. Sorting Phase (Step 9):
   - Sort all permutations lexicographically
   - Ensures proper order for finding next permutation

5. Finding Next Permutation (Steps 10-11):
   - Locate current permutation in sorted list
   - Get next permutation or wrap to first
   - Handle edge case of last permutation

6. Final Update (Step 12):
   - Copy next permutation back to input array
   - Complete the in-place modification

Key Observations:
1. All permutations must be generated before finding next
2. Lexicographical sorting is crucial for correct order
3. Modular arithmetic handles wrap-around to first permutation

Sample Validation:
Input: [1,2,3]
All Permutations (sorted): [[1,2,3], [1,3,2], [2,1,3], [2,3,1], [3,1,2], [3,2,1]]
Next Permutation: [1,3,2]

TEST CASES:
1. Input: [1,2,3]
  All Perms: [[1,2,3], [1,3,2], [2,1,3], [2,3,1], [3,1,2], [3,2,1]]
  Output: [1,3,2]
2. Input: [3,2,1]
  All Perms: [[1,2,3], [1,3,2], [2,1,3], [2,3,1], [3,1,2], [3,2,1]]
  Output: [1,2,3]

Let me explain both aspects in detail:

1. Why update the input array in-place:

a) Memory Efficiency:
- In-place modification means we don't need extra space to store the result
- Particularly important for large arrays
- Follows the problem requirement: "The replacement must be in place and use only constant extra memory"

b) Common Programming Practice:
- Many array algorithms prefer in-place modifications
- Avoids creating unnecessary copies of data
- More efficient in terms of memory management

c) Implementation in the code:
```java
// Update input array in-place
for (int i = 0; i < nums.length; i++) {
    nums[i] = next.get(i);
}
```
Instead of returning a new array, we modify the original array directly.

2. How Collections.sort works in this context:

a) The Sorting Implementation:
```java
Collections.sort(allPerms, (a, b) -> {
    for (int i = 0; i < a.size(); i++) {
        if (!a.get(i).equals(b.get(i))) {
            return a.get(i) - b.get(i);
        }
    }
    return 0;
});
```

b) Comparator Breakdown:
- Takes two permutations (Lists) `a` and `b`
- Compares them element by element
- Returns:
  * Negative if a < b
  * Positive if a > b 
  * Zero if equal

c) Example of how it sorts:
```java
Input permutations: [[1,2,3], [2,1,3], [1,3,2]]

Comparing [1,2,3] and [2,1,3]:
- First elements: 1 < 2
- Returns negative (1-2 = -1)
- [1,2,3] comes before [2,1,3]

Comparing [1,3,2] and [1,2,3]:
- First elements: 1 = 1
- Second elements: 3 > 2
- Returns positive (3-2 = 1)
- [1,2,3] comes before [1,3,2]
```

d) Final sorted result:
```java
[[1,2,3], [1,3,2], [2,1,3], [2,3,1], [3,1,2], [3,2,1]]
```

e) Key points about the comparison:
- Compares elements until it finds the first difference
- Uses natural ordering of integers
- Lexicographical comparison: compares position by position
- The comparison logic ensures lexicographical ordering

*/