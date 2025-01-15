/* 
Problem: Generate all possible permutations of an array of distinct integers.
For example, given array [1,2,3], generate: [[1,2,3], [1,3,2], [2,1,3], [2,3,1], [3,1,2], [3,2,1]]

Intuition: Use backtracking to build permutations incrementally. At each step, try placing each number 
that hasn't been used yet, then backtrack by removing it to try other possibilities.
*/

package Permutations.Recursive_Approach;
import java.util.List;
import java.util.ArrayList;
public class Approach1a {
    // Approach 1(a): Backtracking with ArrayList contains check
    public static List<List<Integer>> permute(int[] nums) {
        // Initialize result list to store all permutations
        List<List<Integer>> result = new ArrayList<>();
        backtrack(nums, new ArrayList<>(), result);
        return result;
    }
    
    private static void backtrack(int[] nums, List<Integer> current, List<List<Integer>> result) {
        // Base case: if current permutation is complete
        if (current.size() == nums.length) {
            // Create a new ArrayList to avoid reference issues
            result.add(new ArrayList<>(current));
            return;
        }

        // Try adding each number from nums to current permutation
        for (int num : nums) {
            // Skip if number is already used in current permutation
            if (current.contains(num)) continue;
            
            // Add current number to try this path
            current.add(num);
            // Recursive call to continue building permutation
            backtrack(nums, current, result);
            // Backtrack by removing the last added number
            current.remove(current.size() - 1);
        }
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        List<List<Integer>> permutations = permute(nums);
        // Print all permutations
        for (List<Integer> perm : permutations) {
            System.out.println(perm);
        }
    }
}

/* 
ALGORITHM:
1. Create empty result list to store all permutations
2. Start backtracking with empty current list
3. For each recursive call:
   - If current permutation length equals input array length, add to result
   - Otherwise, try each number from input array
   - Skip if number already used in current permutation
   - Add number, make recursive call, then remove number (backtrack)

Complexity Analysis:
TIME COMPLEXITY: O(n! * n)
- n! for generating all permutations
- Additional O(n) for contains() check in each recursive call

SPACE COMPLEXITY:
1. Auxiliary Space: O(n)
   - Recursion stack depth of n
   - Current permutation list of max size n
2. Input Space: O(n! * n)
   - Storing all n! permutations of length n

Advantages:
1. Simple and intuitive implementation
2. Works well for small input sizes
3. Easy to modify for variations (e.g., permutations with duplicates)

Limitations:
1. O(n) contains() check in each recursive call is inefficient
2. High space complexity for storing all permutations
3. Not suitable for large inputs due to factorial time complexity

Potential Improvements:
1. Use boolean array or Set for O(1) used number lookup instead of contains()
2. Use array instead of ArrayList for current permutation
3. Generate permutations iteratively instead of recursively
4. Use indices instead of actual numbers to avoid object creation
5. Implement streaming/iterator pattern for memory efficiency

INPUT: nums = [1, 2, 3]
OUTPUT: [[1,2,3], [1,3,2], [2,1,3], [2,3,1], [3,1,2], [3,2,1]]

DETAILED EXECUTION FLOW TABLE
-----------------------------------------------------------------------------------------
Step | Call Stack | Current    | Nums Left  | Action                    | Result List
-----------------------------------------------------------------------------------------
1    | backtrack1 | []        | [1,2,3]    | Start with empty         | []
2    | backtrack2 | [1]       | [2,3]      | Add 1, recurse           | []
3    | backtrack3 | [1,2]     | [3]        | Add 2, recurse           | []
4    | backtrack4 | [1,2,3]   | []         | Add 3, complete          | [[1,2,3]]
5    | backtrack3 | [1,2]     | [3]        | Backtrack, remove 3      | [[1,2,3]]
6    | backtrack2 | [1]       | [2,3]      | Backtrack, remove 2      | [[1,2,3]]
7    | backtrack2 | [1,3]     | [2]        | Add 3, recurse           | [[1,2,3]]
8    | backtrack3 | [1,3,2]   | []         | Add 2, complete          | [[1,2,3],[1,3,2]]
9    | backtrack2 | [1,3]     | [2]        | Backtrack, remove 2      | [[1,2,3],[1,3,2]]
10   | backtrack1 | [1]       | [2,3]      | Backtrack, remove 3      | [[1,2,3],[1,3,2]]
11   | backtrack1 | []        | [1,2,3]    | Backtrack, remove 1      | [[1,2,3],[1,3,2]]
12   | backtrack2 | [2]       | [1,3]      | Add 2, recurse           | [[1,2,3],[1,3,2]]
13   | backtrack3 | [2,1]     | [3]        | Add 1, recurse           | [[1,2,3],[1,3,2]]
14   | backtrack4 | [2,1,3]   | []         | Add 3, complete          | [[1,2,3],[1,3,2],[2,1,3]]
15   | backtrack3 | [2,1]     | [3]        | Backtrack, remove 3      | [[1,2,3],[1,3,2],[2,1,3]]
16   | backtrack2 | [2]       | [1,3]      | Backtrack, remove 1      | [[1,2,3],[1,3,2],[2,1,3]]
17   | backtrack2 | [2,3]     | [1]        | Add 3, recurse           | [[1,2,3],[1,3,2],[2,1,3]]
18   | backtrack3 | [2,3,1]   | []         | Add 1, complete          | [[1,2,3],[1,3,2],[2,1,3],[2,3,1]]
19   | backtrack2 | [2,3]     | [1]        | Backtrack, remove 1      | [[1,2,3],[1,3,2],[2,1,3],[2,3,1]]
20   | backtrack1 | [2]       | [1,3]      | Backtrack, remove 3      | [[1,2,3],[1,3,2],[2,1,3],[2,3,1]]
21   | backtrack1 | []        | [1,2,3]    | Backtrack, remove 2      | [[1,2,3],[1,3,2],[2,1,3],[2,3,1]]
22   | backtrack2 | [3]       | [1,2]      | Add 3, recurse           | [[1,2,3],[1,3,2],[2,1,3],[2,3,1]]
23   | backtrack3 | [3,1]     | [2]        | Add 1, recurse           | [[1,2,3],[1,3,2],[2,1,3],[2,3,1]]
24   | backtrack4 | [3,1,2]   | []         | Add 2, complete          | [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2]]
25   | backtrack3 | [3,1]     | [2]        | Backtrack, remove 2      | [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2]]
26   | backtrack2 | [3]       | [1,2]      | Backtrack, remove 1      | [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2]]
27   | backtrack2 | [3,2]     | [1]        | Add 2, recurse           | [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2]]
28   | backtrack3 | [3,2,1]   | []         | Add 1, complete          | [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
29   | backtrack2 | [3,2]     | [1]        | Backtrack, remove 1      | [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
30   | backtrack1 | [3]       | [1,2]      | Backtrack, remove 2      | [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
31   | backtrack1 | []        | [1,2,3]    | Backtrack, remove 3      | [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]

STEP-BY-STEP EXPLANATION:

1. Initial State (Step 1):
   - Start with empty current list []
   - All numbers [1,2,3] are available

2. First Permutation [1,2,3] (Steps 2-4):
   - Add 1 → [1]
   - Add 2 → [1,2]
   - Add 3 → [1,2,3] (Complete, add to result)

3. Second Permutation [1,3,2] (Steps 5-8):
   - Backtrack to [1]
   - Add 3 → [1,3]
   - Add 2 → [1,3,2] (Complete, add to result)

4. Third Permutation [2,1,3] (Steps 12-14):
   - Backtrack to empty []
   - Add 2 → [2]
   - Add 1 → [2,1]
   - Add 3 → [2,1,3] (Complete, add to result)

5. Fourth Permutation [2,3,1] (Steps 16-18):
   - Backtrack to [2]
   - Add 3 → [2,3]
   - Add 1 → [2,3,1] (Complete, add to result)

6. Fifth Permutation [3,1,2] (Steps 22-24):
   - Backtrack to empty []
   - Add 3 → [3]
   - Add 1 → [3,1]
   - Add 2 → [3,1,2] (Complete, add to result)

7. Final Permutation [3,2,1] (Steps 27-28):
   - Backtrack to [3]
   - Add 2 → [3,2]
   - Add 1 → [3,2,1] (Complete, add to result)

KEY PATTERNS OBSERVED:

1. Depth Pattern:
   - Each recursive call adds one number
   - Maximum depth is always equal to array length (3 in this case)
   - Base case reached when current.size() == nums.length

2. Backtracking Pattern:
   - Remove last added number when backtracking
   - Try all possible numbers at each position
   - Skip numbers already used in current permutation

3. State Management:
   - Current list tracks partial permutation
   - Contains() check prevents reuse of numbers
   - New ArrayList copy created for each complete permutation

4. Recursion Tree:
   - Branches = available numbers at each step
   - Leaf nodes = complete permutations
   - Tree depth = input array length

Key Observations:
1. Each number must be used exactly once in each permutation
2. Number of permutations is factorial of input size
3. Backtracking ensures all possibilities are explored efficiently

Sample Validation:
Input: [1,2,3]
Expected: [[1,2,3], [1,3,2], [2,1,3], [2,3,1], [3,1,2], [3,2,1]]
Output: [[1,2,3], [1,3,2], [2,1,3], [2,3,1], [3,1,2], [3,2,1]]

Key Points:
1. Backtracking is key for exploring all possibilities systematically
2. Deep copy needed when adding to result to avoid reference issues
3. Contains() check ensures each number used once per permutation

TEST CASES:
1. Input: [1]
   Expected: [[1]]
   Output: [[1]]
2. Input: [1,2]
   Expected: [[1,2], [2,1]]
   Output: [[1,2], [2,1]]
3. Input: []
   Expected: [[]]
   Output: [[]]
*/