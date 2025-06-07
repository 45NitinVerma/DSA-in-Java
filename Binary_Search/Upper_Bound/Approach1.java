package Binary_Search.Upper_Bound;
/* 
# Problem: Upper Bound
You are given a sorted array ‘arr’ containing ‘n’ integers and an integer ‘x’.Implement the ‘upper bound’ function to find the index of the upper bound of 'x' in the array.

Note:
1. The upper bound in a sorted array is the index of the first value that is greater than a given value. 
2. If the greater value does not exist then the answer is 'n', Where 'n' is the size of the array.
3. Try to write a solution that runs in log(n) time complexity.

## What is Upper Bound?
The upper bound algorithm finds the first or the smallest index in a sorted array where the value at that index is greater than the given key i.e. x.

The upper bound is the smallest index, ind, where arr[ind] > x.

But if any such index is not found, the upper bound algorithm returns n i.e. size of the given array. 

The main difference between the lower and upper bound is in the condition. For the lower bound the condition was arr[ind] >= x and here, in the case of the upper bound, it is arr[ind] > x.
*/
public class Approach1 {
    // Approach 1: Using Linear Search
    public static int upperBound(int[] arr, int x, int n) {
        for (int i = 0; i < n; i++) {
            if (arr[i] > x) {
                // upper bound found:
                return i;
            }
        }
        return n;
    }

    public static void main(String[] args) {
        int[] arr = {3, 5, 8, 9, 15, 19};
        int n = 6, x = 9;
        int ind = upperBound(arr, x, n);
        System.out.println("The upper bound is the index: " + ind);
    }
}
/* 
## Complexity Analysis:
1. Time Complexity: O(n) in the worst case, where n is the size of the array.
2. Space Complexity: O(1) as we are using only a constant amount of space.
*/