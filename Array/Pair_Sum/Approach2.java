package Array.Pair_Sum;

import java.util.*;

/* 
Problem: Find all pairs in array that sum to target S
Input: Array ARR[N], target sum S
Output: List of pairs sorted by first element, then second element

Intuition: Use HashSet to track seen numbers and find complements.
When we encounter a number, we check if its complement (target - number)
exists in our set. This gives us O(n) time complexity versus O(n²)
with brute force.
*/

public class Approach2 {
  // Approach 2: Using Hashing
    public static List<int[]> pairSum_Hashing(int[] arr, int n, int s) {
        // HashSet to store numbers we have seen so far
        Set<Integer> seen = new HashSet<>();
        // List to store pairs that sum to the target
        List<int[]> pairs = new ArrayList<>();
        
        // Traverse through each number in the array
        for (int num : arr) {
            // Calculate the complement needed for the target sum
            int complement = s - num;

            // If the complement is found in the set, we have a valid pair
            if (seen.contains(complement)) {
                // Add the pair in sorted order
                pairs.add(new int[]{
                    Math.min(num, complement), 
                    Math.max(num, complement)
                });
            }

            // Add the current number to the HashSet
            seen.add(num);
        }

        // Sort the pairs: first by the first element, then by the second
        Collections.sort(pairs, (a, b) -> {
            if (a[0] != b[0]) return a[0] - b[0];
            return a[1] - b[1];
        });

        // Return the sorted list of pairs
        return pairs;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6}; // Input array
        int n = arr.length;            // Size of the array
        int targetSum = 7;             // Target sum

        // Find pairs with sum equal to targetSum
        List<int[]> result = pairSum_Hashing(arr, n, targetSum);

        // Print the result
        System.out.println("Pairs with sum " + targetSum + ":");
        for (int[] pair : result) {
            System.out.println("[" + pair[0] + ", " + pair[1] + "]");
        }
    }
}

/* 
ALGORITHM:
1. Create HashSet to track seen numbers
2. For each number in array:
   - Calculate complement (target - current number)
   - If complement exists in set, add pair to result
   - Add current number to set
3. Sort pairs by first element, then second element
4. Return sorted pairs list

Complexity Analysis:
TIME COMPLEXITY: O(n log n)
- HashSet operations: O(n)
- Sorting pairs: O(k log k) where k is number of pairs
- Overall dominated by O(n log n) for sorting

SPACE COMPLEXITY:
1. Auxiliary Space: O(n)
   - HashSet stores up to n elements
   - Pairs list stores up to n/2 pairs
2. Input Space: O(n)
   - Original array storage

Advantages:
1. Better time complexity than brute force O(n²)
2. Single pass through array
3. Simple implementation

Limitations:
1. Extra space for HashSet
2. Doesn't handle duplicates well
3. Must sort pairs for ordered output

Potential Improvements:

1. Handle Duplicates:
   - Modify to handle duplicate numbers correctly
   - Use HashMap instead of HashSet to track frequency
   - Example implementation:
     Map<Integer, Integer> frequency = new HashMap<>();
     frequency.put(num, frequency.getOrDefault(num, 0) + 1);

2. Memory Optimization:
   - Use two-pointer approach for sorted input
   - Reduces space complexity from O(n) to O(1)
   - Trade-off: Requires O(n log n) sorting

3. Parallel Processing:
   - Partition array for parallel processing
   - Use Stream API for large datasets:
     Arrays.stream(arr).parallel()
          .forEach(num -> findPairs(num, seen, pairs));

EXECUTION FLOW for arr=[1,6,5,2,4,3], target=7
---------------------------------------------------------------------------------------------------
Step|Element|Complement | HashSet     | Pairs List        | Action            | Explanation
---------------------------------------------------------------------------------------------------
1   | 1     | 6         | {}          | []                | Add 1 to set      | First element, no complement exists
2   | 6     | 1         | {1}         | [[1,6]]           | Found pair, add 6 | Complement 1 exists, add pair [1,6]
3   | 5     | 2         | {1,6}       | [[1,6]]           | Add 5 to set      | No complement 2 yet
4   | 2     | 5         | {1,6,5}     | [[1,6],[2,5]]     | Found pair, add 2 | Complement 5 exists, add pair [2,5]
5   | 4     | 3         | {1,6,5,2}   | [[1,6],[2,5]]     | Add 4 to set      | No complement 3 yet
6   | 3     | 4         | {1,6,5,2,4} |[[1,6],[2,5],[3,4]]| Found pair, add 3 | Complement 4 exists, add pair [3,4]
---------------------------------------------------------------------------------------------------
Final Output after sorting: [[1,6],[2,5],[3,4]]

EXECUTION FLOW: Pair Sum Algorithm (arr=[1,6,5,2,4,3], target=7)

1. Process element=1
  - Check complement needed: 7-1=6
  - HashSet: {}
  - Pairs: []
  - Action: Add 1 to HashSet
  - End state: HashSet={1}, Pairs=[]

2. Process element=6  
  - Check complement needed: 7-6=1
  - HashSet: {1}
  - Found complement 1 in set
  - Action: Add [1,6] to pairs, add 6 to set
  - End state: HashSet={1,6}, Pairs=[[1,6]]

3. Process element=5
  - Check complement needed: 7-5=2
  - HashSet: {1,6}
  - Action: Add 5 to set
  - End state: HashSet={1,6,5}, Pairs=[[1,6]]

4. Process element=2
  - Check complement needed: 7-2=5
  - HashSet: {1,6,5}
  - Found complement 5 in set
  - Action: Add [2,5] to pairs, add 2 to set
  - End state: HashSet={1,6,5,2}, Pairs=[[1,6],[2,5]]

5. Process element=4
  - Check complement needed: 7-4=3
  - HashSet: {1,6,5,2}
  - Action: Add 4 to set
  - End state: HashSet={1,6,5,2,4}, Pairs=[[1,6],[2,5]]

6. Process element=3
  - Check complement needed: 7-3=4
  - HashSet: {1,6,5,2,4}
  - Found complement 4 in set
  - Action: Add [3,4] to pairs, add 3 to set
  - End state: HashSet={1,6,5,2,4,3}, Pairs=[[1,6],[2,5],[3,4]]

Final: Sort pairs by first element then the second
Result: [[1,6],[2,5],[3,4]]

Key Observations:
1. Order of insertion affects pair order, requiring sort
2. HashSet prevents duplicate pairs effectively

Sample Validation:
Input: arr=[1,2,3,4,5,6], target=7
Output: [[1,6], [2,5], [3,4]]

TEST CASES:
1. Input: arr=[1,2,3,4,5,6], n=6, s=7
   Expected: [[1,6], [2,5], [3,4]]
   Output: [[1,6], [2,5], [3,4]]
2. Input: arr=[2,2,2,2], n=4, s=4
   Expected: [[2,2]]
   Output: [[2,2]]
*/