// Approach 3: Using XOR (Optimal Approach)
package Array.Find_Single_Element;
import java.util.*;

public class Approach3 {
    public static int getSingleElement(int[] arr) {
        // Size of the array
        int n = arr.length;
        
        // XOR all the elements
        int xorr = 0;
        for (int i = 0; i < n; i++) {
            xorr = xorr ^ arr[i];
        }
        return xorr;
    }

    public static void main(String[] args) {
        // Test input array
        int[] arr = {4, 1, 2, 1, 2};
        
        // Print input array
        System.out.println("Input Array: " + Arrays.toString(arr));
        
        // Find and print the single element
        int ans = getSingleElement(arr);
        System.out.println("The single element is: " + ans);
    }
}

/*
 * ALGORITHM:
 * 1. Initialize a variable 'xorr' to 0 to store XOR results
 * 2. Iterate through the array once:
 *    - For each element, perform XOR operation with 'xorr'
 * 3. Return the final XOR value
 * 
 * The algorithm works because:
 * - XOR of a number with itself is 0
 * - XOR of a number with 0 is the number itself
 * - XOR operation is associative and commutative
 * Therefore, all paired numbers cancel out, leaving only the single element
*/

/* 
Complexity Analysis:
TIME COMPLEXITY:
- O(n) where n is the length of array
- Single pass through the array
- Each XOR operation is O(1)

SPACE COMPLEXITY:
- O(1) constant extra space
- Only uses one variable (xorr) regardless of input size
- Input Space: O(n) for the input array

Advantages:
1. Optimal time complexity O(n)
2. Constant space complexity O(1)
3. No need for sorting or extra data structures
4. Works with any integer values
5. Simple implementation

Limitations:
1. Works only when all numbers except one appear exactly twice
2. Cannot handle cases where numbers appear odd number of times
3. May not preserve order information
4. Limited to finding single unique element

Detailed XOR Operation Walkthrough:
Input Array: [4, 1, 2, 1, 2]
-------------------------------------------------------------------------
Step | Current Element | XOR Operation | Result | Explanation
-------------------------------------------------------------------------
1    | 4              | 0 ^ 4         | 4      | XOR with initial value
2    | 1              | 4 ^ 1         | 5      | XOR with previous result
3    | 2              | 5 ^ 2         | 7      | XOR with previous result
4    | 1              | 7 ^ 1         | 6      | 1 appears again, cancels first 1
5    | 2              | 6 ^ 2         | 4      | 2 appears again, cancels first 2
-------------------------------------------------------------------------
Final Result: 4 (The single element in the array)

Binary representation of operations:
4: 100
1: 001
2: 010
1: 001
2: 010
-----------------
4: 100 (Result after all XOR operations)
*/