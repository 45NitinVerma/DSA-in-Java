package Binary_Search.Lower_Bound;
/* 
# Problem: Implement lower bound
You are given an array 'arr' sorted in non-decreasing order and a number 'x'. You must return the index of the lower bound of 'x'.

Note:
1. For a sorted array 'arr', 'lower_bound' of a number 'x' is defined as the smallest index 'idx' such that the value 'arr[idx]' is not less than 'x'.If all numbers are smaller than 'x', then 'n' should be the 'lower_bound' of 'x', where 'n' is the size of array.
2. Try to do this in O(log(n)).

## What is Lower Bound?
    The lower bound algorithm finds the first or the smallest index in a sorted array where the value at that index is greater than or equal to a given key i.e. x.

    The lower bound is the smallest index, ind, where arr[ind] >= x. But if any such index is not found, the lower bound algorithm returns n i.e. size of the given array.

 */
public class Approach1 {
    // Approach 1: Using Linear Search
    public static int lowerBound(int []arr, int n, int x) {
        for (int i = 0; i < n; i++) {
            if (arr[i] >= x) {
                // lower bound found:
                return i;
            }
        }
        return n;
    }

    public static void main(String[] args) {
        int[] arr = {3, 5, 8, 15, 19};
        int n = 5, x = 9;
        int ind = lowerBound(arr, n, x);
        System.out.println("The lower bound is the index: " + ind);
    }
}
/* 
## Complexity Analysis:
1. Time Complexity: O(n)), where n is the size of the array.
2. Space Complexity: O(1), as we are using only a constant amount of space.
 */