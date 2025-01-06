/*
Problem: Generate all possible subsets (Power Set) of a given set of integers.

Intuition: Instead of using recursion, we can build subsets iteratively. For each new element,
we add it to all existing subsets to create new subsets. This follows the mathematical principle
that for a set of n elements, there are 2^n possible subsets.
*/

// Approach 2: Using iterative approach
package Subsets;
import java.util.*;

public class Approach2 {
    public static List<List<Integer>> PowerSetIterative(List<Integer> set) {
        // Initialize result list to store all subsets
        List<List<Integer>> powerSet = new ArrayList<>();
        
        // Start with empty subset
        powerSet.add(new ArrayList<>());
        
        // Iterate through each element in input set
        for (Integer item : set) {
            // Get current size of power set before adding new subsets
            int size = powerSet.size();
            
            // For each existing subset, create a new subset by adding current element
            for (int i = 0; i < size; i++) {
                // Create new subset from existing subset
                List<Integer> newSubset = new ArrayList<>(powerSet.get(i));
                // Add current element to create new subset
                newSubset.add(item);
                // Add new subset to power set
                powerSet.add(newSubset);
            }
        }
        return powerSet;
    }

    public static void main(String[] args) {
        List<Integer> set = Arrays.asList(1, 2, 3);
        System.out.println("Power Set using Iterative method:");
        System.out.println(PowerSetIterative(set));
    }
}

/*
ALGORITHM:
1. Initialize empty power set with empty subset
2. For each element in input set:
   a. Get current size of power set
   b. For each existing subset:
      - Create new subset by copying existing subset
      - Add current element to new subset
      - Add new subset to power set
3. Return final power set

Complexity Analysis:
TIME COMPLEXITY: O(n * 2^n)
- For a set of n elements, we generate 2^n subsets
- For each subset, we may need to copy up to n elements

SPACE COMPLEXITY:
1. Auxiliary Space: O(n * 2^n)
   - We store 2^n subsets
   - Each subset can contain up to n elements
2. Input Space: O(n)
   - Space required to store input set of n elements

Advantages:
1. No recursion stack overhead
2. More intuitive and easier to understand
3. Better memory efficiency compared to recursive approach
4. Easier to debug and trace

Limitations:
1. Still exponential time complexity
2. May not be suitable for very large sets
3. Memory usage grows exponentially with input size

Potential Improvements:
1. Use BitSet or integer bits to represent subsets for better space efficiency
2. Implement parallel processing for large sets
3. Add input validation and handling of null values
4. Optimize memory allocation by pre-sizing ArrayList
5. Add option to generate subsets of specific size only
6. Implement iterator pattern for memory-efficient generation

EXECUTION FLOW
-------------------------------------------------------------------------
Step | Current State | Variables | Action | Explanation
-------------------------------------------------------------------------
1    | Initial      | powerSet=[] | Add empty set | Start with empty set
2    | First elem   | item=1     | Add [1] | Add subsets with first element
3    | Second elem  | item=2     | Add [2],[1,2] | Add subsets with second
4    | Third elem   | item=3     | Add [3],[1,3],[2,3],[1,2,3] | Complete
-------------------------------------------------------------------------

Key Observations:
1. Number of subsets doubles with each new element
2. Order of elements doesn't affect final result
3. Each element is considered exactly once

Sample Validation:
Input: [1, 2, 3]
Expected: [[], [1], [2], [1, 2], [3], [1, 3], [2, 3], [1, 2, 3]]
Output: [[], [1], [2], [1, 2], [3], [1, 3], [2, 3], [1, 2, 3]]

Key Points:
1. Maintains subset property (every subset of a subset is also in power set)
2. Generates subsets in lexicographic order
3. Handles duplicate elements based on input list behavior

TEST CASES:
1. Input: []
   Expected: [[]]
   Output: [[]]
2. Input: [1]
   Expected: [[], [1]]
   Output: [[], [1]]
3. Input: [1, 2]
   Expected: [[], [1], [2], [1, 2]]
   Output: [[], [1], [2], [1, 2]]
*/