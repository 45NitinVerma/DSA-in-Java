package Binary_Search.Number_Of_Occurrences;
/* 
# Number of Occurrences
## Problem: You have been given a sorted array/list of integers 'arr' of size 'n' and an integer 'x'.
Find the total number of occurrences of 'x' in the array/list.
 */
public class Approach1 {
    // Approach 1: Using Linear Search
    public static int count(int arr[], int n, int x) {
        int cnt = 0;
        for (int i = 0; i < n; i++) {

            // counting the occurrences:
            if (arr[i] == x) cnt++;
        }
        return cnt;
    }

    public static void main(String[] args) {
        int[] arr =  {2, 4, 6, 8, 8, 8, 11, 13};
        int n = 8, x = 8;
        int ans = count(arr, n, x);
        System.out.println("The number of occurrences is: " + ans);
    }
}
/* 
# Time Complexity: O(n)
# Space Complexity: O(1)
 */