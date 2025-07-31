package Binary_Search.First_And_Last_Position;

import java.util.ArrayList;
import java.util.Arrays;

/* 
# First and Last Position of Element in Sorted Array
## Problem: Given an array of integers nums sorted in non-decreasing order, find the starting and ending position of a given target value.
If target is not found in the array, return [-1, -1].
You must write an algorithm with O(log n) runtime complexity.

## Intution: Basically, we will traverse the entire array to find the first and the last occurrences. The steps are as follows:
*/
public class Approach1 {
    // Approach 1: Using Linear Search
    public static int[] firstAndLastPosition(ArrayList<Integer> arr, int n, int k) {
        int first = -1, last = -1;
        for (int i = 0; i < n; i++) {
            if (arr.get(i) == k) {
                if (first == -1) first = i;
                last = i;
            }
        }
        return new int[] {first, last};
    }


    public static void main(String[] args) {
        ArrayList<Integer> arr = new ArrayList<>(Arrays.asList(new Integer[] {2, 4, 6, 8, 8, 8, 11, 13}));
        int n = 8, k = 8;
        int[] ans = firstAndLastPosition(arr, n, k);
        System.out.println("The first and last positions are: "
                           + ans[0] + " " + ans[1]);
    }
}
