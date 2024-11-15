package Array.Sorting_Searching;
public class SelectionSort {
    
    // Method to perform selection sort on an array
    public static void selectionSort(int[] arr) {
        int n = arr.length;
        
        // Loop over each element in the array (except the last one)
        for (int i = 0; i <= n - 2; i++) {
            int minIndex = i; // Assume the first unsorted element is the minimum

            // Find the index of the minimum element in the remaining unsorted array
            for (int j = i + 1; j <= n-1; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j; // Update the minimum element index
                }
            }
            
            // Swap the found minimum element with the first unsorted element
            if (minIndex != i) {
                int temp = arr[minIndex];
                arr[minIndex] = arr[i];
                arr[i] = temp;
            }
        }
    }

    // Utility method to print an array
    public static void printArray(int[] arr) {
        for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] arr = {64, 25, 12, 22, 11}; // Sample array
        System.out.println("Original array:");
        printArray(arr);
        
        selectionSort(arr); // Call selection sort
        
        System.out.println("Sorted array:");
        printArray(arr);
    }
}
/*
 * Time Complexity:
 * - Best Case: O(n^2), even if the array is already sorted.
 * - Average Case: O(n^2), as each element is compared with the remaining unsorted elements.
 * - Worst Case: O(n^2), when the array is in reverse order.
 *
 * Space Complexity:
 * - O(1), as it is an in-place sorting algorithm requiring only a few additional variables.
 */