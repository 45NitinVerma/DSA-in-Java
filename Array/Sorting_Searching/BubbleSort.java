package Array.Sorting_Searching;
public class BubbleSort {
    // Method to perform Bubble Sort on an array
    public static void bubbleSort(int[] arr) {
        int n = arr.length;
        // Traverse through all array elements
        for (int i = 0; i < n - 1; i++) {
            // Flag to check if any swapping happened in this pass
            boolean swapped = false;

            // Last i elements are already in place, so reduce the range by i
            for (int j = 0; j < n - 1 - i; j++) {
                // Compare adjacent elements and swap if the current element is greater
                if (arr[j] > arr[j + 1]) {
                    // Swap arr[j] and arr[j + 1]
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;

                    // Set flag to true if swapping happened
                    swapped = true;
                }
            }

            // If no two elements were swapped in the inner loop, break
            if (!swapped) {
                break;
            }
        }
    }

    // Main method to test the bubbleSort method
    public static void main(String[] args) {
        int[] arr = {64, 34, 25, 12, 22, 11, 90};
        System.out.println("Original array:");
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();

        // Call bubbleSort method
        bubbleSort(arr);

        System.out.println("Sorted array:");
        for (int num : arr) {
            System.out.print(num + " ");
        }
    }
}
/*
Time Complexity Analysis:
- Worst Case: O(n^2) - Occurs when the array is in reverse order.
- Average Case: O(n^2) - Occurs for a random arrangement of elements.
- Best Case: O(n) - Occurs when the array is already sorted (with optimization using the flag).

Space Complexity Analysis:
- Space Complexity: O(1) - The algorithm sorts the array in-place without using any additional data structures.
*/