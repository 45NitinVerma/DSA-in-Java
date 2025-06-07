package Binary_Search.Binary_Searching;
/* 
# Problem: Binary Search
Given an array of integers nums which is sorted in ascending order, and an integer target, write a function to search target in nums. If target exists, then return its index. Otherwise, return -1.

You must write an algorithm with O(log n) runtime complexity.
 */
public class Approach1 {
        // Approach 1: Using Iterative Binary Search
        public static int binarySearch(int[] nums, int target) {
            int n = nums.length; //size of the array.
            int low = 0, high = n - 1;
    
            // Perform the steps:
            while (low <= high) {
                int mid = (low + high) / 2;
                if (nums[mid] == target) return mid;
                else if (target > nums[mid]) low = mid + 1;
                else high = mid - 1;
            }
            return -1;
        }
    
        public static void main(String[] args) {
            int[] a = {3, 4, 6, 7, 9, 12, 16, 17};
            int target = 6;
            int ind = binarySearch(a, target);
            if (ind == -1)
                System.out.println("The target is not present.");
            else
                System.out.println("The target is at index: " + ind);
        }
}
/* 
## Algorithm:
1. Divide the search space into 2 halves:
 - In order to divide the search space, we need to find the middle point of it. So, we will take a ‘mid’ pointer and do the following:
        mid = (low+high) // 2 ( ‘//’ refers to integer division)
2. Compare the middle element with the target: In this step, we can observe 3 different cases:
 - If arr[mid] == target: We have found the target. From this step, we can return the index of the target possibly.
 - If target > arr[mid]: This case signifies our target is located on the right half of the array. So, the next search space will be the right half.
 - If target < arr[mid]: This case signifies our target is located on the left half of the array. So, the next search space will be the left half.
3. Trim down the search space:
Based on the probable location of the target we will trim down the search space.
 - If the target occurs on the left, we should set the high pointer to mid-1. Thus the left half will be the next search space.
 - Similarly, if the target occurs on the right, we should set the low pointer to mid+1. Thus the right half will be the next search space.

## Complexity Analysis:
1. Time Complexity: O(log n) - The time complexity of binary search is O(log n) because the search space is halved in each iteration.
2. Space Complexity: 
- Auxiliary space: O(1)
- Input space: O(n)
 */