package Binary_Search.Number_Of_Occurrences;
/* 
# Number of Occurrences
## Problem: You have been given a sorted array/list of integers 'arr' of size 'n' and an integer 'x'.
Find the total number of occurrences of 'x' in the array/list.
 */
public class Approach2 {
    // Approach 2: Using Binary Search
    public static int firstOccurrence(int[] arr, int n, int k) {
        int low = 0, high = n - 1;
        int first = -1;

        while (low <= high) {
            int mid = (low + high) / 2;
            // maybe an answer
            if (arr[mid] == k) {
                first = mid;
                // look for smaller index on the left
                high = mid - 1;
            } else if (arr[mid] < k) {
                low = mid + 1; // look on the right
            } else {
                high = mid - 1; // look on the left
            }
        }
        return first;
    }

    public static int lastOccurrence(int[] arr, int n, int k) {
        int low = 0, high = n - 1;
        int last = -1;

        while (low <= high) {
            int mid = (low + high) / 2;
            // maybe an answer
            if (arr[mid] == k) {
                last = mid;
                // look for larger index on the right
                low = mid + 1;
            } else if (arr[mid] < k) {
                low = mid + 1; // look on the right
            } else {
                high = mid - 1; // look on the left
            }
        }
        return last;
    }

    public static int[] firstAndLastPosition(int[] arr, int n, int k) {
        int first = firstOccurrence(arr, n, k);
        if (first == -1) return new int[] { -1, -1};
        int last = lastOccurrence(arr, n, k);
        return new int[] {first, last};
    }

    public static int count(int arr[], int n, int x) {
        int[] ans = firstAndLastPosition(arr, n, x);
        if (ans[0] == -1) return 0;
        return (ans[1] - ans[0] + 1);
    }

    public static void main(String[] args) {
        int[] arr =  {2, 4, 6, 8, 8, 8, 11, 13};
        int n = 8, x = 8;
        int ans = count(arr, n, x);
        System.out.println("The number of occurrences is: " + ans);
    }
}
/*
# Algorithm:
1. We will get the first and the last occurrences of the number using the function firstAndLastPosition(). For the implementation details of the function, please refer to the previous article.
2. After getting the indices, we will check the following cases:
 - If the first index == -1: This means that the target value is not present in the array. So, we will return 0 as the answer.
 - Otherwise: We will find the total number of occurrences like this:
        The total number of occurrences  = (last index - first index + 1) and return this length as the answer.
        
# Complexity Analysis:
1. Time Complexity: O(2*log n) 
    => O(log n) for first and last occurrence.
    => O(log n) for the main function.
    => O(log n) + O(log n) = O(2*log n) => O(log n).
2. Space Complexity: O(1) as we are using constant space.
*/