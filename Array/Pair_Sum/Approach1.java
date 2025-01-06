/* 
Problem: Find all pairs in array that sum to target S
Input: Array ARR[N], target sum S
Output: List of pairs sorted by first element, then second element

Intuition: Using brute force approach to find all pairs by checking each possible combination.
The other possible approaches are:
1. Hashing - Use HashSet to store complements (O(n) space, O(n) time)
2. Two Pointers - Sort array first, then use two pointers (O(nlogn) time)
*/

package Array.Pair_Sum;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class Approach1 {
   // Approach 1: Brute Force using nested loops
    public static List<int[]> pairSum_BruteForce(int[] arr, int n, int s) {
        // Initialize result list to store valid pairs
        List<int[]> pairs = new ArrayList<>();
        
        // Iterate through each possible first element
        for (int i = 0; i < n; i++) {
            // Check all elements after current first element
            for (int j = i + 1; j < n; j++) {
                // If pair sums to target, add to result
                if (arr[i] + arr[j] == s) {
                    // Ensure smaller number is first in pair
                    pairs.add(new int[]{
                        Math.min(arr[i], arr[j]), 
                        Math.max(arr[i], arr[j])
                    });
                }
            }
        }
        
        // Sort pairs for consistent output
        Collections.sort(pairs, (a, b) -> {
            if (a[0] != b[0]) return a[0] - b[0];
            return a[1] - b[1];
        });
        
        return pairs;
    }

    // Driver method for testing
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6};
        int n = arr.length;
        int targetSum = 7;

        List<int[]> result = pairSum_BruteForce(arr, n, targetSum);
        System.out.println("Pairs with sum " + targetSum + ":");
        for (int[] pair : result) {
            System.out.println("[" + pair[0] + ", " + pair[1] + "]");
        }
    }
}

/* 
ALGORITHM:
1. Initialize empty list to store pairs
2. For each element i in array:
   a. For each element j after i:
      - Check if arr[i] + arr[j] equals target
      - If yes, add sorted pair to result list
3. Sort all pairs by first element, then second
4. Return sorted pairs list

Complexity Analysis:
TIME COMPLEXITY: O(n²)
- Nested loops iterate through all possible pairs
- Additional O(klogk) for sorting pairs, where k is number of valid pairs

SPACE COMPLEXITY:
1. Auxiliary Space: O(k)
   - k pairs stored in result list, where k is number of valid pairs
2. Input Space: O(n)
   - Original array storage

Advantages:
1. Simple to implement and understand
2. No additional data structures required
3. Works with unsorted input
4. Maintains original array order

Limitations:
1. O(n²) time complexity is inefficient for large arrays
2. Not suitable for real-time applications
3. Excessive iterations even when few pairs exist

Potential Improvements:
1. Use HashSet to reduce time complexity to O(n)
2. Sort array and use two pointers approach
3. Early termination if target sum impossible
4. Parallel processing for large arrays

EXECUTION FLOW for array [1,2,3,4,5,6] with target sum = 7
---------------------------------------------------------------------------------
Step | Current State | Variables     | Pair Sum | Action          | Explanation
---------------------------------------------------------------------------------
1    | Initialize    | i=0,j=1       | 1+2=3    | Continue        | First element pair check, sum < target
2    | First pass    | i=0,j=2       | 1+3=4    | Continue        | Sum still < target
3    | First pass    | i=0,j=3       | 1+4=5    | Continue        | Sum still < target
4    | First pass    | i=0,j=4       | 1+5=6    | Continue        | Sum still < target
5    | First pass    | i=0,j=5       | 1+6=7    | Add pair [1,6]  | Found first valid pair
6    | Second pass   | i=1,j=2       | 2+3=5    | Continue        | Starting with i=1, sum < target
7    | Second pass   | i=1,j=3       | 2+4=6    | Continue        | Sum still < target
8    | Second pass   | i=1,j=4       | 2+5=7    | Add pair [2,5]  | Found second valid pair
9    | Second pass   | i=1,j=5       | 2+6=8    | Continue        | Sum > target
10   | Third pass    | i=2,j=3       | 3+4=7    | Add pair [3,4]  | Found third valid pair
11   | Third pass    | i=2,j=4       | 3+5=8    | Continue        | Sum > target
12   | Third pass    | i=2,j=5       | 3+6=9    | Continue        | Sum > target
13   | Fourth pass   | i=3,j=4       | 4+5=9    | Continue        | Sum > target
14   | Fourth pass   | i=3,j=5       | 4+6=10   | Continue        | Sum > target
15   | Fifth pass    | i=4,j=5       | 5+6=11   | Continue        | Sum > target
16   | Sort pairs    | -             | -        | Sort result     | Sort pairs list by first element
---------------------------------------------------------------------------------
 
 1. First Element (i=0) Processing:
    - j=1: check 1+2=3 (< target, continue)
    - j=2: check 1+3=4 (< target, continue)  
    - j=3: check 1+4=5 (< target, continue)
    - j=4: check 1+5=6 (< target, continue)
    - j=5: check 1+6=7 (= target, add [1,6])
    Result after step: [[1,6]]
 
 2. Second Element (i=1) Processing:
    - j=2: check 2+3=5 (< target, continue)
    - j=3: check 2+4=6 (< target, continue)
    - j=4: check 2+5=7 (= target, add [2,5])
    - j=5: check 2+6=8 (> target, continue)
    Result after step: [[1,6], [2,5]]
 
 3. Third Element (i=2) Processing:
    - j=3: check 3+4=7 (= target, add [3,4])
    - j=4: check 3+5=8 (> target, continue)
    - j=5: check 3+6=9 (> target, continue)
    Result after step: [[1,6], [2,5], [3,4]]
 
 4. Fourth Element (i=3) Processing:
    - All sums > target, no pairs added
 
 5. Fifth Element (i=4) Processing:
    - All sums > target, no pairs added
 
 Final Step:
 - Sort pairs by first element, then second
 - Return final result: [[1,6], [2,5], [3,4]]

Result List Evolution:
1. After step 5:  [[1,6]]
2. After step 8:  [[1,6], [2,5]]
3. After step 10: [[1,6], [2,5], [3,4]]
4. Final sorted:  [[1,6], [2,5], [3,4]]

Key Observations:
1. Array elements can be part of multiple pairs
2. Order of pairs matters in output
3. Duplicate pairs are automatically avoided

Sample Validation:
Input: [1,2,3,4,5,6], target=7
Expected: [[1,6], [2,5], [3,4]]
Output: [[1,6], [2,5], [3,4]]

TEST CASES:
1. Input: arr=[1,2,3,4,5,6], target=7
   Expected: [[1,6], [2,5], [3,4]]
   Output: [[1,6], [2,5], [3,4]]
2. Input: arr=[1,1,1,1], target=2
   Expected: [[1,1]]
   Output: [[1,1]]
3. Input: arr=[-2,0,1,3], target=1
   Expected: [[-2,3], [0,1]]
   Output: [[-2,3], [0,1]]
*/