// Approach 1: Brute Force Approach 
package Array.Find_Single_Element;

import java.util.*;

public class Approach1 {
    public static int getSingleElement(int[] arr) {
        // Size of the array
        int n = arr.length;

        // Run a loop for selecting elements
        for (int i = 0; i < n; i++) {
            int num = arr[i]; // Current element
            int count = 0; // Track occurrences

            // Find occurrences using linear search
            for (int j = 0; j < n; j++) {
                if (arr[j] == num) {
                    count++;
                }
            }

            // If element appears exactly once, return it
            if (count == 1) {
                return num;
            }
        }

        // If no single element found
        return -1;
    }

    public static void main(String[] args) {
        // Test input array
        int[] arr = { 4, 1, 2, 1, 2 };

        // Print input array
        System.out.println("Input Array: " + Arrays.toString(arr));

        // Find and print the single element
        int result = getSingleElement(arr);
        System.out.println("The single element is: " + result);
    }
}

/*
 * ALGORITHM:
 * 1. Get the size of input array
 * 2. For each element in the array:
 *    a. Select current element
 *    b. Count its occurrences in entire array
 *    c. If count equals 1, return that element
 * 3. Return -1 if no single element found
*/

/* 
Complexity Analysis:
TIME COMPLEXITY:
- Worst Case: O(n²) 
  * n = length of array
  * Outer loop runs n times
 * Inner loop runs n times for each outer loop iteration
- Best Case: O(n)
  * When single element is at first position
- Average Case: O(n²)

SPACE COMPLEXITY:
- Auxiliary Space: O(1)
  * Only using constant extra space
- Input Space: O(n)

Advantages:
1. Simple to implement
2. No extra space required
3. Works with any integer values
4. No sorting required

Limitations:
1. Quadratic time complexity
2. Not efficient for large arrays
3. Performs redundant checks

 Potential Improvements:
 1. Use HashMap for O(n) time complexity
 2. Use XOR operation for O(n) time complexity
 3. Use sorting followed by linear scan
 4. Use frequency array for bounded integers
 
 Single Element Search - DETAILED WALKTHROUGH
Input Array: [4, 1, 2, 1, 2]

Detailed Step-by-Step Process:
-------------------------------------------------------------------------
Index | Current Element             | Count Process              | Result
-------------------------------------------------------------------------
i=0   | num = 4                     | Count occurrences of 4     | count = 1
      | - Found single occurrence   |                            |
      | - Return 4 as answer        |                            |
      |                             |                            |
i=1   | num = 1                     | (not executed)             | -
i=2   | num = 2                     | (not executed)             | -
i=3   | num = 1                     | (not executed)             | -
i=4   | num = 2                     | (not executed)             | -
-------------------------------------------------------------------------
Final Result: 4

Note: Algorithm stops at first iteration since it found
the element that appears exactly once (4).
If we continued:
    i=1 | num=1 | Count occurrences of 1: | count=2
    i=2 | num=2 | Count occurrences of 2: | count=2
    i=3 | num=1 | Count occurrences of 1: | count=2
    i=4 | num=2 | Count occurrences of 2: | count=2
*/