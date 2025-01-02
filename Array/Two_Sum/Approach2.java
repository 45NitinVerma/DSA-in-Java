/* 
Problem: Find two numbers in an array that add up to a given target sum.

Intuition: Basically, in the previous approach we selected one element and then searched for the other one using a loop. Here instead of using a loop, we will use the HashMap and store the element along will its index in the HashMap. Thus we can easily retrieve the index of the other element i.e. target-(selected element) without iterating the array.. Thus we can trim down the time complexity of the problem.
*/

package Array.Two_Sum;
import java.util.HashMap;

public class Approach2 {
   // Approach 2: Using Hashing
   public static int[] twoSum(int n, int[] arr, int target) {
      // Initialize result array with default values
      int[] ans = new int[2];
      ans[0] = ans[1] = -1;
      
      // HashMap to store number->index mapping
      HashMap<Integer, Integer> mpp = new HashMap<>();
      
      // Single pass through array
      for (int i = 0; i < n; i++) {
          // Current number being processed
          int num = arr[i];
          
          // Calculate the complement needed to reach target
          int moreNeeded = target - num;
          
          // Check if complement exists in HashMap
          if (mpp.containsKey(moreNeeded)) {
              // Found valid pair - get index of complement
              ans[0] = mpp.get(moreNeeded);
              ans[1] = i;  // Current index
              return ans;  // Early return on finding solution
          }
          
          // Store current number and its index for future lookups
          mpp.put(arr[i], i);
      }
      
      // Return [-1,-1] if no solution found
      return ans;
  }

  // Driver method to test the implementation
  public static void main(String args[]) {
      // Test input initialization
      int n = 5;
      int[] arr = {2, 6, 5, 8, 11};
      int target = 14;
      
      // Get solution
      int[] ans = twoSum(n, arr, target);
      
      // Print result
      System.out.println("This is the answer for variant 2: [" + ans[0] + ", "
                        + ans[1] + "]");
  }
}

/* 
ALGORITHM:
1. Initialize result array with -1s
2. Create HashMap to store number-index pairs
3. For each number:
   - Calculate complement (target - current number)
   - If complement exists in map, return its index and current index
   - Otherwise, add current number and index to map

Complexity Analysis:
TIME COMPLEXITY: O(n)
- Single pass through array with O(1) HashMap operations
- Single loop with O(n) iterations

SPACE COMPLEXITY:
1. Auxiliary Space: O(n)
   - HashMap stores at most n elements
2. Input Space: O(n)
   - Input array of size n

Advantages:
1. Optimal time complexity O(n)
2. Single pass solution
3. Handles negative numbers and zeros

Limitations:
1. Extra space required for HashMap
2. Doesn't handle multiple valid pairs

Potential Improvements:
1. Return all possible pairs instead of first found
2. Handle duplicate numbers in array

EXECUTION FLOW
n=5, arr=[2,6,5,8,11], target=14
----------------------------------------------------------------------------------------------------------------
Step | Array State     | HashMap State        | Variables                 | Action                  | Result
----------------------------------------------------------------------------------------------------------------
1    | [2,6,5,8,11]    | {}                   | i=0                       | Check if 12 in map      | Not found
     |                 |                      | num=2                     |                         |
     |                 |                      | moreNeeded=12             |                         |
     |                 | {2->0}               |                           | Add 2->0 to map         |
----------------------------------------------------------------------------------------------------------------
2    | [2,6,5,8,11]    | {2->0}               | i=1                       | Check if 8 in map       | Not found
     |                 |                      | num=6                     |                         |
     |                 |                      | moreNeeded=8              |                         |
     |                 | {2->0, 6->1}         |                           | Add 6->1 to map         |
----------------------------------------------------------------------------------------------------------------
3    | [2,6,5,8,11]    | {2->0, 6->1}         | i=2                       | Check if 9 in map       | Not found
     |                 |                      | num=5                     |                         |
     |                 |                      | moreNeeded=9              |                         |
     |                 | {2->0, 6->1, 5->2}   |                           | Add 5->2 to map         |
----------------------------------------------------------------------------------------------------------------
4    | [2,6,5,8,11]    | {2->0, 6->1, 5->2}   | i=3                       | Check if 6 in map       | Found
     |                 |                      | num=8                     |                         |
     |                 |                      | moreNeeded=14-8=6         |                         |
     |                 | {2->0, 6->1,         | answer=[1,3]              | Return answer           |
     |                 |  5->2, 8->3}         |                           |                         |
----------------------------------------------------------------------------------------------------------------

Step-by-Step Execution Flow:

Step 1:
- Array: [2,6,5,8,11]
- Current num = 2
- Target = 14
- moreNeeded = 14-2 = 12
- HashMap = {}
- Action: 12 not found, add 2->0 to map
- HashMap becomes {2->0}

Step 2:
- Array: [2,6,5,8,11]
- Current num = 6
- moreNeeded = 14-6 = 8
- HashMap = {2->0}
- Action: 8 not found, add 6->1 to map
- HashMap becomes {2->0, 6->1}

Step 3:
- Array: [2,6,5,8,11]
- Current num = 5
- moreNeeded = 14-5 = 9
- HashMap = {2->0, 6->1}
- Action: 9 not found, add 5->2 to map
- HashMap becomes {2->0, 6->1, 5->2}

Step 4:
- Array: [2,6,5,8,11]
- Current num = 8
- moreNeeded = 14-8 = 6
- HashMap = {2->0, 6->1, 5->2}
- Action: 6 found in map at index 1
- Return answer [1,3]

Result: Found pair (6,8) at indices [1,3] that sum to 14


Key Observations:
1. Solution finds first valid pair, not all pairs
2. Works with both positive and negative numbers

Sample Validation:
Input: n=5, arr=[2,6,5,8,11], target=14
Expected: [1,3] (6+8=14)
Output: [1,3]

TEST CASES:
1. Input: arr=[2,6,5,8,11], target=14
   Expected: [1,3]
   Output: [1,3]
2. Input: arr=[1,2,3,4], target=5
   Expected: [1,2]
   Output: [1,2]
3. Input: arr=[1,1], target=2
   Expected: [0,1]
   Output: [0,1]
*/