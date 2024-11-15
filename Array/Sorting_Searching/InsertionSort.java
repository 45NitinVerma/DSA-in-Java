package Array.Sorting_Searching;
public class InsertionSort {
    // Method to perform insertion sort
    public static void insertionSort(int[] arr) {
        // Traverse from the second element to the end of the array
        for (int i = 1; i < arr.length; i++) {
            int key = arr[i]; // The element to be compared and inserted
            int j = i - 1;

            // Shift elements of the sorted portion that are greater than key
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j]; // Shift element to the right
                j--;
            }

            // Insert key in its correct position
            arr[j + 1] = key;
        }
    }

    // Utility method to print the array
    public static void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    // Main method to test the insertion sort
    public static void main(String[] args) {
        int[] arr = {12, 11, 13, 5, 6};
        System.out.println("Original Array:");
        printArray(arr);

        insertionSort(arr);

        System.out.println("Sorted Array:");
        printArray(arr);
    }
}
/*
 * Time Complexity:
 * - Best Case: O(n), occurs when the array is already sorted.
 * - Average Case: O(n^2), due to the nested loop structure.
 * - Worst Case: O(n^2), occurs when the array is sorted in reverse order.
 *
 * Space Complexity:
 * - O(1), as insertion sort is an in-place sorting algorithm.
 */