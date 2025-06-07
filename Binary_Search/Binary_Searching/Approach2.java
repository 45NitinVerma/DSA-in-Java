package Binary_Search.Binary_Searching;
/* 
# Problem: Binary Search
Given an array of integers nums which is sorted in ascending order, and an integer target, write a function to search target in nums. If target exists, then return its index. Otherwise, return -1.

You must write an algorithm with O(log n) runtime complexity.
 */
public class Approach2 {
    // Approach 2: Using Recursive Binary Search
    public static int binarySearch(int[] nums, int low, int high, int target) {
        if (low > high) return -1; //Base case.

        // Perform the steps:
        int mid = (low + high) / 2;
        if (nums[mid] == target) return mid;
        else if (target > nums[mid])
            return binarySearch(nums, mid + 1, high, target);
        return binarySearch(nums, low, mid - 1, target);
    }

    public static int search(int[] nums, int target) {
        return binarySearch(nums, 0, nums.length - 1, target);
    }

    public static void main(String[] args) {
        int[] a = {3, 4, 6, 7, 9, 12, 16, 17};
        int target = 6;
        int ind = search(a, target);
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
2. Compare the middle element with the target and trim down the search space:
In this step, we can observe 3 different cases:
 - If arr[mid] == target: We have found the target. From this step, we can return the index of the target, and the recursion will end.
 - If target > arr[mid]: This case signifies our target is located on the right half of the array. So, the next recursion call will be binarySearch(nums, mid+1, high).
 - If target < arr[mid]: This case signifies our target is located on the left half of the array. So, the next recursion call will be binarySearch(nums, low, mid-1).
- Base case: The base case of the recursion will be low > high. If (low > high), the search space becomes invalid which means the target is not present in the array.

## Complexity Analysis:
1. Time Complexity: O(log n) - In the algorithm, in every step, we are basically dividing the search space into 2 equal halves. This is actually equivalent to dividing the size of the array by 2, every time. After a certain number of divisions, the size will reduce to such an extent that we will not be able to divide that anymore and the process will stop.
2. Space Complexity: 
- Auxiliary space: O(n) - The space complexity of the recursive approach is O(n) because of the recursion stack.
- Input space: O(n)
*/