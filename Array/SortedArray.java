package Array;

public class SortedArray {
    public static boolean isSorted(int[] arr) {
        if (arr == null || arr.length < 2) {
            // An array with one or no elements is considered sorted
            return true;
        }

        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < arr[i - 1]) {
                return false; // Return false if a pair is found out of order
            }
        }

        return true; // Return true if the entire array is in order
    }

    public static void main(String[] args) {
        int[] arr = { 1, 2, 3, 4, 5 };
        System.out.println("Is the array sorted? " + isSorted(arr)); // Output: true

        int[] arr2 = { 5, 3, 8, 1 };
        System.out.println("Is the array sorted? " + isSorted(arr2)); // Output: false
    }
}
/*
Time Complexity Analysis:
   - O(n) - needs to check each element at least once

Space Complexity Analysis:
1. Auxiliary Space (Temporary Extra Space):
   - Only uses a single loop counter variable 'i'
   - No additional data structures created
   - Auxiliary Space: O(1) constant space

2. Permanent Space:
   - Input array storage: O(n) where n is array length
   - Two boolean return values: O(1)
   - Total Permanent Space: O(n)

 */