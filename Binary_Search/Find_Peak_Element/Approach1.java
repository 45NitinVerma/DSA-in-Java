package Binary_Search.Find_Peak_Element;

import java.util.ArrayList;
import java.util.Arrays;

/* 
Find Peak Element
# Problem: A peak element is an element that is strictly greater than its neighbors.
Given a 0-indexed integer array nums, find a peak element, and return its index. If the array contains multiple peaks, return the index to any of the peaks.
You may imagine that nums[-1] = nums[n] = -∞. In other words, an element is always considered to be strictly greater than a neighbor that is outside the array.
You must write an algorithm that runs in O(log n) time.

!! What is a peak element?
A peak element in an array refers to the element that is greater than both of its neighbors. Basically, if arr[i] is the peak element, arr[i] > arr[i-1] and arr[i] > arr[i+1].

!! How to identify if an element arr[i] is a peak:
We know that if arr[i] is the peak, arr[i] > arr[i-1] and arr[i] > arr[i+1]. So, we can check this condition for all the elements and identify the peak. But there are the following edge cases:
 - If n == 1: The aforementioned condition will not be applicable. In this scenario, when the array size is 1, the single element within the array serves as the peak, and thus we should return its index. Prior information specifies that for the first element, we should treat the previous element as negative infinity, and similarly, for the last element, we should consider the next element as negative infinity.
 - If i == 0: The aforementioned condition will not be applicable as arr[i-1] will refer to arr[-1] which is invalid. So, in this case, we should check if arr[0] > arr[1]. If this condition holds, we can conclude that arr[0] is a peak. Prior information specifies that for the first element, we should treat the previous element as negative infinity.
 - If i == n-1: The aforementioned condition will not be applicable as arr[i+1] will refer to arr[n] which is again invalid. So, in this case, we should check if arr[n-1] > arr[n-2]. If this condition holds, we can conclude that arr[n-1] is a peak. Prior information specifies that for the last element, we should treat the next element as negative infinity.

## Intuition: A simple approach involves iterating through the array and checking specific conditions for each element to determine the peak. By considering all the necessary conditions, including edge cases, our final condition can be summarized as follows:
If ((i == 0 or arr[i-1] < arr[i]) and (i == n-1 or arr[i] > arr[i+1])), we have found a peak. In such cases, we can return the index of the element satisfying this condition.
 */
public class Approach1 {
    // Approach 1: Using Linear Search
    public static int findPeakElement(ArrayList<Integer> arr) {
        int n = arr.size(); // Size of array.

        for (int i = 0; i < n; i++) {
            // Checking for the peak:
            if ((i == 0 || arr.get(i - 1) < arr.get(i))
                    && (i == n - 1 || arr.get(i) > arr.get(i + 1))) {
                return i;
            }
        }
        // Dummy return statement
        return -1;
    }

    public static void main(String[] args) {
        ArrayList<Integer> arr =
            new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 5, 1));
        int ans = findPeakElement(arr);
        System.out.println("The peak is at index: " + ans);
    }
}
/* 
# Algorithm:
1. We will start traversing the array and for every index, we will check the below condition.
2. If((i == 0 or arr[i-1] < arr[i]) and (i == n-1 or arr[i] > arr[i+1])): whenever this condition is true for an element, we should return its index.

# Complexity Analysis:
1. Time Complexity: O(n), where n is the size of the array. We are traversing the array once.
2. Space Complexity: O(1), as we are not using any extra space.
 */