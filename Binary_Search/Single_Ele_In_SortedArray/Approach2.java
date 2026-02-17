package Binary_Search.Single_Ele_In_SortedArray;

import java.util.ArrayList;
import java.util.Arrays;

/* 
Single Element in a Sorted Array
# Problem: You are given a sorted array consisting of only integers where every element appears exactly twice, except for one element which appears exactly once.
Return the single element that appears only once.
Your solution must run in O(log n) time and O(1) space.

## Intution:
We can simplify the above approach using the XOR operation. We need to remember 2 important properties of XOR:

a ^ a = 0, XOR of two same numbers results in 0.
a ^ 0 = a, XOR of a number with 0 always results in that number.
Now, if we XOR all the array elements, all the duplicates will result in 0 and we will be left with a single element.
 */
public class Approach2 {
    // Approach 2: Using XOR
    public static int singleNonDuplicate(ArrayList<Integer> arr) {
        int n = arr.size(); //size of the array.
        int ans = 0;
        // XOR all the elements:
        for (int i = 0; i < n; i++) {
            ans = ans ^ arr.get(i);
        }
        return ans;
    }

    public static void main(String[] args) {
        ArrayList<Integer> arr =
            new ArrayList<>(Arrays.asList(1, 1, 2, 2, 3, 3, 4, 5, 5, 6, 6));
        int ans = singleNonDuplicate(arr);
        System.out.println("The single element is: " + ans);
    }
}
/* 
# Algorithm:
1. We will declare an ‘ans’ variable initialized with 0.
2. We will traverse the array and XOR each element with the variable ‘ans’.
3. After complete traversal, the ‘ans’ variable will store the single element and we will return it.

# Complexity Analysis:
1. Time Complexity: O(n), where n is the size of the array.
2. Space Complexity: O(1), as we are using only a constant amount of space.
 */