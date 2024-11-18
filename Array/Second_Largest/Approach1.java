// Brute Force Approach - Sort the array first
package Array.Second_Largest;

import java.util.Arrays;

public class Approach1 {
    public static int findSecondLargest(int[] arr) {
        // Check if array is null or has less than 2 elements
        if (arr == null || arr.length < 2) {
            return -1;
        }

        // Sort the array in ascending order
        Arrays.sort(arr);

        // Handle duplicates - traverse from right to find first different element
        for (int i = arr.length - 2; i >= 0; i--) {
            if (arr[i] != arr[arr.length - 1]) {
                return arr[i];
            }
        }

        // If no second largest element is found (all elements are same)
        return -1;
    }

    public static void main(String[] args) {
        int[] arr = { 1, 2, 3, 7, 7, 5 };
        int secondLargest = findSecondLargest(arr);
        if (secondLargest != -1) {
            System.out.println("Second largest element (Brute Force): " + secondLargest);
        }
    }
}
// Time Complexity: O(n log n)
// Space Complexity: O(1)