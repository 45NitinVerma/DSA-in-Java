// Approach 2a: Using Array Hashing
package Array.Find_Single_Element.Approach2;

public class Approach2a {
    public static int getSingleElement(int[] arr) {
        // Size of the array
        int n = arr.length;

        // Find the maximum element for hash array size
        int maxi = arr[0];
        for (int i = 0; i < n; i++) {
            maxi = Math.max(maxi, arr[i]);
        }

        // Declare hash array of size maxi+1 and count frequencies
        int[] hash = new int[maxi + 1];
        for (int i = 0; i < n; i++) {
            hash[arr[i]]++;
        }

        // Find and return the element with frequency 1
        for (int i = 0; i < n; i++) {
            if (hash[arr[i]] == 1)
                return arr[i];
        }

        // This line will never execute if array contains a single element
        return -1;
    }

    public static void main(String[] args) {
        int[] arr = {4, 1, 2, 1, 2};
        int ans = getSingleElement(arr);
        System.out.println("The single element is: " + ans);
    }
}

/*
 * ALGORITHM:
 * 1. Find the maximum element in the array to determine hash array size
 * 2. Create a hash array to store frequency counts
 * 3. Count frequency of each element using hash array
 * 4. Iterate through original array and return first element with frequency 1
 * 5. Return -1 if no single element found 
 */

/*
Complexity Analysis:
TIME COMPLEXITY:
- O(n) for finding maximum element
- O(n) for counting frequencies
- O(n) for finding single element
- Overall: O(n) where n is array length

SPACE COMPLEXITY:
- O(max) where max is the maximum element in array
- Hash array stores frequencies from 0 to max

Advantages:
1. Linear time complexity O(n)
2. Simple implementation
3. Works with unsorted arrays
4. Can handle multiple pairs of duplicates

Limitations:
1. Space complexity depends on maximum element value
2. Not memory efficient for sparse arrays with large max value
3. Requires non-negative integers

Potential Improvements:
1. Use HashMap for negative numbers/non-integers
2. XOR approach for O(1) space complexity
3. Binary search for sorted arrays

Detailed Walkthrough Example:
Input Array: [4, 1, 2, 1, 2]
-------------------------------------------------------------------------
Step 1: Find Maximum Element
- Initialize maxi = arr[0] = 4
- Final maxi = 4

Step 2: Create Hash Array
- Size = maxi + 1 = 5
- hash = [0, 0, 0, 0, 0]

Step 3: Count Frequencies
Initial:  [0, 0, 0, 0, 0]
After 4:  [0, 0, 0, 0, 1]
After 1:  [0, 1, 0, 0, 1]
After 2:  [0, 1, 1, 0, 1]
After 1:  [0, 2, 1, 0, 1]
After 2:  [0, 2, 2, 0, 1]

Step 4: Find Single Element
- Check arr[0] = 4: hash[4] = 1 ✓
- Return 4

Final Result: 4 (single element)
*/