package Binary_Search.Min_In_Sorted_Rotated_Array;
/* 
# Find Minimum in Sorted Rotated Array
## Problem: Suppose an array of length n sorted in ascending order is rotated between 1 and n times. 
For example, the array nums = [0,1,2,4,5,6,7] might become:
[4,5,6,7,0,1,2] if it was rotated 4 times.
[0,1,2,4,5,6,7] if it was rotated 7 times.
Notice that rotating an array [a[0], a[1], a[2], ..., a[n-1]] 1 time results in the array [a[n-1], a[0], a[1], a[2], ..., a[n-2]].

Given the sorted rotated array nums of unique elements, return the minimum element of this array.
You must write an algorithm that runs in O(log n) time.
 */
public class Approach1 {
    // Approach 1: Using Linear Search
    public static int findMin(int []arr) {
        int n = arr.length; // size of the array.
        int mini = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            // Always keep the minimum.
            mini = Math.min(mini, arr[i]);
        }
        return mini;
    }

    public static void main(String[] args) {
        int[] arr = {4, 5, 6, 7, 0, 1, 2, 3};
        int ans = findMin(arr);
        System.out.println("The minimum element is: " + ans );
    }
}
/* 
#Complexity Analysis
1. Time Complexity: O(n)
    - The time complexity of this approach is O(n) because we are iterating through the entire array to find the minimum element.
2. Space Complexity: O(1)
    - The space complexity of this approach is O(1) because we are using a constant amount of extra space to store the minimum element.
 */