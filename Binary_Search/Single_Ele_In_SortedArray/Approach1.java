package Binary_Search.Single_Ele_In_SortedArray;

import java.util.ArrayList;
import java.util.Arrays;

/* 
Single Element in a Sorted Array
# Problem: You are given a sorted array consisting of only integers where every element appears exactly twice, except for one element which appears exactly once.
Return the single element that appears only once.
Your solution must run in O(log n) time and O(1) space.

## Intution:
A crucial observation to note is that if an element appears twice in a sequence, either the preceding or the subsequent element will also be the same. But only for the single element, this condition will not be satisfied. So, to check this the condition will be the following:
* If arr[i] != arr[i-1] and arr[i] != arr[i+1]: If this condition is true for any element, arr[i], we can conclude this is the single element.

Edge Cases:
 - If n == 1: This means the array size is 1. If the array contains only one element, we will return that element only.
 - If i == 0: This means this is the very first element of the array. The only condition, we need to check is: arr[i] != arr[i+1].
 - If i == n-1: This means this is the last element of the array. The only condition, we need to check is: arr[i] != arr[i-1].
So, we will traverse the array and we will check for the above conditions.
 */
public class Approach1 {
    // Approach 1: Naive Approach
    public static int singleNonDuplicate(ArrayList<Integer> arr) {
        int n = arr.size(); // Size of the array.
        if (n == 1)
            return arr.get(0);

        for (int i = 0; i < n; i++) {
            // Check for first index:
            if (i == 0) {
                if (!arr.get(i).equals(arr.get(i + 1)))
                    return arr.get(i);
            }
            // Check for last index:
            else if (i == n - 1) {
                if (!arr.get(i).equals(arr.get(i - 1)))
                    return arr.get(i);
            } else {
                if (!arr.get(i).equals(arr.get(i - 1)) && !arr.get(i).equals(arr.get(i + 1)))
                    return arr.get(i);
            }
        }

        // Dummy return statement:
        return -1;
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
1. At first, we will check if the array contains only 1 element. If it is, we will simply return that element.
2. We will start traversing the array. Then for every element, we will check the following.
 - If i == 0: If we are at the first index, we will check if the next element is equal.
   - If arr[i] != arr[i+1]: This means arr[i] is the single element and so we will return arr[i].
 - If i == n-1: If we are at the last index, we will check if the previous element is equal.
   - If arr[i] != arr[i-1]: This means arr[i] is the single element and so we will return arr[i].
3. For the elements other than the first and last, we will check:
 - If arr[i] != arr[i-1] and arr[i] != arr[i+1]: If this condition is true for any element, arr[i], we can conclude this is the single element. And we should return arr[i].

# Complexity Analysis:
1. Time Complexity: O(n), where n is the size of the array.
2. Space Complexity: O(1), as we are using only a constant amount of space.
 */