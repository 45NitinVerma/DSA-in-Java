/* 
Problem: Find the majority element in an array that appears more than n/2 times.

Intuition: Use a HashMap to store the frequency of each element. 
Since the majority element appears more than n/2 times, there can be at most one such element.
*/

package Array.Majority_Element;

import java.util.HashMap;
import java.util.Map;

public class Approach2 {
    // Approach 2: HashMap-based Frequency Counting
    public static int majorityElement(int[] v) {
        // Get the size of input array
        int n = v.length;

        // HashMap to store element-frequency pairs
        // Key: Array element, Value: Frequency of element
        HashMap<Integer, Integer> mpp = new HashMap<>();

        // Count frequency of each element
        for (int i = 0; i < n; i++) {
            // Get current count of element (default 0 if not present)
            int value = mpp.getOrDefault(v[i], 0);
            // Increment count by 1 and update map
            mpp.put(v[i], value + 1);
        }

        // Check each element's frequency to find majority element
        for (Map.Entry<Integer, Integer> it : mpp.entrySet()) {
            // If frequency is greater than n/2, we found majority element
            if (it.getValue() > (n / 2)) {
                return it.getKey();
            }
        }

        // Return -1 if no majority element found
        return -1;
    }

    public static void main(String args[]) {
        int[] arr = {2, 2, 1, 1, 1, 2, 2};
        int ans = majorityElement(arr);
        System.out.println("The majority element is: " + ans);
    }
}

/* 
ALGORITHM:
1. Initialize HashMap to store element frequencies
2. Iterate through array to count frequencies
3. Iterate through HashMap entries to find element with frequency > n/2
4. Return majority element or -1 if none found

Complexity Analysis:
TIME COMPLEXITY: O(n) 
- First loop for counting frequencies: O(n)
- Second loop for checking HashMap entries: O(n) worst case
- HashMap operations (put, get) are O(1) average case

SPACE COMPLEXITY:
1. Auxiliary Space: O(n)
   - HashMap can store up to n different elements in worst case
2. Input Space: O(n)
   - Original input array of size n

Advantages:
1. Simple and straightforward implementation
2. Works with any range of numbers
3. Can be easily modified to find elements with other frequency thresholds

Limitations:
1. Uses extra space proportional to input size
2. Requires two passes through the data
3. HashMap operations might degrade to O(n) in worst case with poor hash function

Potential Improvements:
1. Use Boyer-Moore Voting Algorithm for O(1) space complexity
2. Add input validation for null/empty arrays
3. Use streams for more concise implementation
4. Add early termination if count exceeds n/2 during first pass
5. Use Integer.MAX_VALUE instead of -1 as sentinel value
6. Add overflow checking for large arrays
7. Use parallel streams for large datasets

TEST CASE 1: [2, 2, 1, 1, 1, 2, 2]
------------------------------------------------------------------------------------------
Step | Array Element | HashMap State         | Action                    | Explanation
------------------------------------------------------------------------------------------
1    | 2            | {2:1}                 | Insert new element        | First occurrence of 2
2    | 2            | {2:2}                 | Increment count           | Second 2 found
3    | 1            | {2:2, 1:1}            | Insert new element        | First occurrence of 1
4    | 1            | {2:2, 1:2}            | Increment count           | Second 1 found
5    | 1            | {2:2, 1:3}            | Increment count           | Third 1 found
6    | 2            | {2:3, 1:3}            | Increment count           | Third 2 found
7    | 2            | {2:4, 1:3}            | Increment count           | Fourth 2 found
Final | Check Map   | {2:4, 1:3}            | Return 2                  | 2 appears > n/2 times
Result: 2 (Majority element as it appears 4 times in array of size 7)

TEST CASE 2: [1, 1, 1]
------------------------------------------------------------------------------------------
Step | Array Element | HashMap State         | Action                    | Explanation
------------------------------------------------------------------------------------------
1    | 1            | {1:1}                 | Insert new element        | First occurrence of 1
2    | 1            | {1:2}                 | Increment count           | Second 1 found
3    | 1            | {1:3}                 | Increment count           | Third 1 found
Final | Check Map   | {1:3}                 | Return 1                  | 1 appears > n/2 times
Result: 1 (Majority element as it appears 3 times in array of size 3)

TEST CASE 3: [1, 2, 3]
------------------------------------------------------------------------------------------
Step | Array Element | HashMap State         | Action                    | Explanation
------------------------------------------------------------------------------------------
1    | 1            | {1:1}                 | Insert new element        | First occurrence of 1
2    | 2            | {1:1, 2:1}            | Insert new element        | First occurrence of 2
3    | 3            | {1:1, 2:1, 3:1}       | Insert new element        | First occurrence of 3
Final | Check Map   | {1:1, 2:1, 3:1}       | Return -1                | No element appears > n/2 times
Result: -1 (No majority element as no element appears more than 1 time in array of size 3)

TEST CASE 4: [1]
------------------------------------------------------------------------------------------
Step | Array Element | HashMap State         | Action                    | Explanation
------------------------------------------------------------------------------------------
1    | 1            | {1:1}                 | Insert new element        | First occurrence of 1
Final | Check Map   | {1:1}                 | Return 1                  | 1 appears > n/2 times
Result: 1 (Majority element as it appears 1 time in array of size 1)

Key Decision Points:
1. Map Creation:
   - HashMap is initialized empty before processing
   - getOrDefault() is used to handle first occurrences gracefully

2. Frequency Counting:
   - Each element's count is incremented using existing count + 1
   - HashMap automatically handles new elements vs existing ones

3. Majority Check:
   - n/2 threshold is calculated once (where n is array length)
   - Each entry is checked against this threshold

Edge Cases Handled:
1. Single Element Array: Works correctly as 1 > 1/2
2. Empty Array: Would return -1 (though not explicitly tested)
3. No Majority: Returns -1 when no element has majority
4. Equal Frequencies: Returns -1 when no clear majority

Memory State Analysis:
1. HashMap Size:
   - Best Case: O(1) when all elements are same
   - Worst Case: O(n) when all elements are different
   - Average Case: O(k) where k is number of unique elements

2. Variable States:
   - n: Remains constant throughout execution
   - value: Temporarily holds current count
   - mpp: Grows as new elements are encountered

Time Complexity Breakdown:
1. First Loop (Counting):
   - Each element accessed once: O(n)
   - HashMap operations: O(1) average case
   - Total: O(n)

2. Second Loop (Checking):
   - Iterate through unique elements: O(k) where k ≤ n
   - Comparison operation: O(1)
   - Total: O(n)

3. Overall: O(n) as both loops are linear

Key Observations:
1. HashMap provides O(1) average case access and modification
2. Only one element can have frequency > n/2
3. Method assumes valid input array

Sample Validation:
Input: [2, 2, 1, 1, 1, 2, 2]
Expected: 2
Output: 2 (correct as 2 appears 4 times in array of size 7)

Key Points:
1. Solution handles both positive and negative integers
2. Returns -1 when no majority element exists
3. Time-space tradeoff compared to voting algorithm

TEST CASES:
1. Input: [2, 2, 1, 1, 1, 2, 2]
   Expected: 2
   Output: 2
2. Input: [1]
   Expected: 1
   Output: 1
3. Input: [1, 2]
   Expected: -1
   Output: -1
4. Input: [1, 1, 1]
   Expected: 1
   Output: 1
*/