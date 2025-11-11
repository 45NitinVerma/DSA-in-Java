package Binary_Search.Search_In_Rotated_Sorted_Array;

import java.util.ArrayList;
import java.util.Arrays;

/* 
# Search in Rotated Sorted Array
## Problem: There is an integer array nums sorted in ascending order (with distinct values).
Prior to being passed to your function, nums is possibly rotated at an unknown pivot index k (1 <= k < nums.length) such that the resulting array is [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]] (0-indexed). For example, [0,1,2,4,5,6,7] might be rotated at pivot index 3 and become [4,5,6,7,0,1,2].

Given the array nums after the possible rotation and an integer target, return the index of target if it is in nums, or -1 if it is not in nums.
You must write an algorithm with O(log n) runtime complexity.

## Intution: To utilize the binary search algorithm effectively, it is crucial to ensure that the input array is sorted. By having a sorted array, we guarantee that each index divides the array into two sorted halves. In the search process, we compare the target value with the middle element, i.e. arr[mid], and then eliminate either the left or right half accordingly. This elimination becomes feasible due to the inherent property of the sorted halves(i.e. Both halves always remain sorted).

However, in this case, the array is both rotated and sorted. As a result, the property of having sorted halves no longer holds. This disruption in the sorting order affects the elimination process, making it unreliable to determine the target's location by solely comparing it with arr[mid]. To illustrate this situation, consider the following example:

    arr = [7,  8,  9,  1,  2,  3,  4,  5,  6] ,    target = 8
           ↑               ↑               ↑
          low             mid             high
    Considering the comparison made, such as target > arr[mid] (e.g. 8>2), we would expect the target to be in the right half. However, due to the array rotation, the number 8 is actually situated in the left half. This rotation creates a challenge in the elimination process.

## Key Observation: Though the array is rotated, we can clearly notice that for every index, one of the 2 halves will always be sorted. In the above example, the right half of the index mid is sorted.

So, to efficiently search for a target value using this observation, we will follow a simple two-step process. 
1. First, we identify the sorted half of the array. 
2. Once found, we determine if the target is located within this sorted half. 
 - If not, we eliminate that half from further consideration. 
 - Conversely, if the target does exist in the sorted half, we eliminate the other half.
*/
public class Approach2 {
    // Approach 2: Using Binary Search
    public static int search(ArrayList<Integer> arr, int n, int k) {
        int low = 0, high = n - 1;
        while (low <= high) {
            int mid = (low + high) / 2;

            // if mid points to the target
            if (arr.get(mid) == k)
                return mid;

            // if left part is sorted
            if (arr.get(low) <= arr.get(mid)) {
                if (arr.get(low) <= k && k <= arr.get(mid)) {
                    // element exists
                    high = mid - 1;
                } else {
                    // element does not exist
                    low = mid + 1;
                }
            } else { // if right part is sorted
                if (arr.get(mid) <= k && k <= arr.get(high)) {
                    // element exists
                    low = mid + 1;
                } else {
                    // element does not exist
                    high = mid - 1;
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        ArrayList<Integer> arr = new ArrayList<>(Arrays.asList(7, 8, 9, 1, 2, 3, 4, 5, 6));
        int n = 9, k = 1;
        int ans = search(arr, n, k);
        if (ans == -1)
            System.out.println("Target is not present.");
        else
            System.out.println("The index is: " + ans);
    }
}
/* 
# Algorithm:
1. Place the 2 pointers i.e. low and high: Initially, we will place the pointers like this: low will point to the first index, and high will point to the last index.
2. Calculate the ‘mid’: Now, inside a loop, we will calculate the value of ‘mid’ using the following formula:
mid = (low+high) // 2 ( ‘//’ refers to integer division)
3. Check if arr[mid] == target: If it is, return the index mid.
4. Identify the sorted half, check where the target is located, and then eliminate one half accordingly:
 - If arr[low] <= arr[mid]: This condition ensures that the left part is sorted.
   - If arr[low] <= target && target <= arr[mid]: It signifies that the target is in this sorted half. So, we will eliminate the right half (high = mid-1).
   - Otherwise, the target does not exist in the sorted half. So, we will eliminate this left half by doing low = mid+1.
 - Otherwise, if the right half is sorted:
   - If arr[mid] <= target && target <= arr[high]: It signifies that the target is in this sorted right half. So, we will eliminate the left half (low = mid+1).
   - Otherwise, the target does not exist in this sorted half. So, we will eliminate this right half by doing high = mid-1.
5. Once, the ‘mid’ points to the target, the index will be returned.
6. This process will be inside a loop and the loop will continue until low crosses high. If no index is found, we will return -1.

# Complexity Analysis:
1. Time Complexity: O(log n) - The time complexity of this approach is logarithmic because we are halving the search space in each iteration.
2. Space Complexity: O(1) - We are using a constant amount of space.
 */