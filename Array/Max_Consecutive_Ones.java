
package Array;
public class Max_Consecutive_Ones {
    /*
     Finds the maximum number of consecutive 1's in the given array
    */
    static int findMaxConsecutiveOnes(int nums[]) {
        // Current count of consecutive 1's
        int cnt = 0;
        
        // Maximum count of consecutive 1's found so far
        int maxi = 0;
        
        // Iterate through the array
        for (int i = 0; i < nums.length; i++) {
            // If current element is 1, increment consecutive count
            if (nums[i] == 1) {
                cnt++;
            } 
            // If current element is 0, reset consecutive count
            else {
                cnt = 0;
            }

            // Update maximum consecutive 1's count
            maxi = Math.max(maxi, cnt);
        }
        return maxi;
    }

    public static void main(String args[]) {
        // Test input array
        int nums[] = { 1, 1, 0, 1, 1, 1 };
        
        // Find maximum consecutive 1's
        int ans = findMaxConsecutiveOnes(nums);
        
        // Print result
        System.out.println("The maximum consecutive 1's are " + ans);
    }
}

/*
ALGORITHM:
1. Initialize two variables:
   - cnt: to track current consecutive 1's count
   - maxi: to store maximum consecutive 1's found
2. Iterate through the array:
   - If current element is 1, increment cnt
   - If current element is 0, reset cnt to 0
   - Update maxi with maximum of current maxi and cnt
3. Return maxi as the result

COMPLEXITY ANALYSIS:
TIME COMPLEXITY:
- O(n), where n is the length of the input array
- Single pass through the array
- Each element is visited exactly once

SPACE COMPLEXITY:
- O(1) Auxiliary Space
    - Uses only two integer variables (cnt, maxi)
    - No extra data structures used
- O(n) Input Space

DETAILED WALKTHROUGH:
Input: [1, 1, 0, 1, 1, 1]
-------------------------------------------------------------------------
Iteration | Current Element | cnt | maxi | Action
-------------------------------------------------------------------------
0         | 1               | 1   | 1    | First 1 found
1         | 1               | 2   | 2    | Consecutive 1's increased
2         | 0               | 0   | 2    | Reset due to 0
3         | 1               | 1   | 2    | New 1 sequence starts
4         | 1               | 2   | 2    | Consecutive 1's increased
5         | 1               | 3   | 3    | Longest consecutive 1's sequence
-------------------------------------------------------------------------
Final Result: 3

ADVANTAGES:
1. Simple and intuitive approach
2. Linear time complexity
3. Constant space complexity
4. Handles various input scenarios

LIMITATIONS:
1. Works only for finding consecutive 1's
2. Assumes input is an integer array
3. No handling for null or empty arrays

POTENTIAL IMPROVEMENTS:
1. Add input validation
2. Handle edge cases like null or empty arrays
3. Generalize to find consecutive elements of any value
*/