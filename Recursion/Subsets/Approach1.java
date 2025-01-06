/* 
Problem: Generate all possible subsets (power set) of a given set of integers.

Intuition: Use binary numbers from 0 to 2^n-1 to represent all possible combinations of elements, 
where n is the number of elements in the input set. Each bit position represents whether to include 
the corresponding element in the subset.
*/

package Subsets;
import java.util.*;

public class Approach1 {
    public static List<List<Integer>> PowerSetBinary(List<Integer> set) {
        List<List<Integer>> powerSet = new ArrayList<>();
        int n = set.size();
        int powerSetSize = 1 << n; // 2^n subsets

        // Iterate over all possible subsets represented by binary numbers
        for (int i = 0; i < powerSetSize; i++) {
            List<Integer> subset = new ArrayList<>();
            // Check each bit of the binary number to decide inclusion
            for (int j = 0; j < n; j++) {
                if ((i & (1 << j)) > 0) { // Check if j-th bit is set in i
                    subset.add(set.get(j)); // Include the corresponding element
                }
            }
            powerSet.add(subset); // Add the subset to the power set
        }
        return powerSet;
    }

    public static void main(String[] args) {
        List<Integer> set = Arrays.asList(1, 2, 3);
        System.out.println("Power Set using Binary method:");
        System.out.println(PowerSetBinary(set));
    }
}

/* 
ALGORITHM:
1. Calculate total number of subsets as 2^n
2. Iterate from 0 to 2^n-1
3. For each number, check its binary representation
4. Include elements where corresponding bit is 1
5. Add generated subset to result

Complexity Analysis:
TIME COMPLEXITY: O(n * 2^n)
- For each subset (2^n), we check n bits

SPACE COMPLEXITY:
1. Auxiliary Space: O(n * 2^n)
   - Storing all subsets, each of size up to n
2. Input Space: O(n)
   - Original input set

EXECUTION FLOW
----------------------------------------------------------------
Step | Current State   | Variables      | Action                  
----------------------------------------------------------------
1    | Initial        | i=0,n=3        | Create empty subset     
2    | First iter     | i=1,j=0        | Check first bit (1)     
3    | Second iter    | i=1,j=1        | Check second bit (0)    
----------------------------------------------------------------

Key Observations:
1. Each subset corresponds to binary representation of a number
2. Total subsets always equal 2^n including empty set

TEST CASES:
1. Input: [1, 2, 3]
   Output: [[], [1], [2], [1,2], [3], [1,3], [2,3], [1,2,3]]
2. Input: []
   Output: [[]]
*/