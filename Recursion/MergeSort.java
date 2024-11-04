// MERGE SORT
public class MergeSort {
    public static void main(String[] args) {
        int[] arr = {8, 9, 2, 5, 1};
        int size = 5;

        // Print array before sorting
        System.out.println("Before Sorting");
        for(int i = 0; i < size; i++){
            System.out.print(arr[i] + " ");
        }
        System.out.println();

        // Perform merge sort
        mergeSort(arr, 0, size - 1);

        // Print array after sorting
        System.out.println("After Sorting");
        for(int i = 0; i < size; i++){
            System.out.print(arr[i] + " ");
        }
    }

    // Recursive function to apply merge sort on the array
    public static void mergeSort(int[] arr, int s, int e) {
        // Base case: If start index >= end index, array is sorted
        if(s >= e) 
            return;

        // Calculate mid-point to divide array
        int mid = (s + e) / 2;

        // Recursively sort left half of array
        mergeSort(arr, s, mid);

        // Recursively sort right half of array
        mergeSort(arr, mid + 1, e);

        // Merge both halves
        merge(arr, s, e);
    }

    // Function to merge two sorted halves into a single sorted array
    public static void merge(int[] arr, int s, int e) {
        int mid = (s + e) / 2;

        // Calculate lengths of left and right halves
        int len1 = mid - s + 1; // Length of left half
        int len2 = e - mid;     // Length of right half

        // Create temporary arrays for left and right halves
        int[] arr1 = new int[len1];
        int[] arr2 = new int[len2];

        // Copy elements into arr1 for left half
        int originalArrayIndex = s; 
        for(int i = 0; i < len1; i++) {
            arr1[i] = arr[originalArrayIndex++];
        }

        // Copy elements into arr2 for right half
        for(int i = 0; i < len2; i++) {
            arr2[i] = arr[originalArrayIndex++];
        }

        // Merge elements from arr1 and arr2 back into arr in sorted order
        originalArrayIndex = s;
        int idx1 = 0, idx2 = 0;

        // Compare elements of arr1 and arr2, placing smaller element in arr
        while(idx1 < len1 && idx2 < len2) {
            if(arr1[idx1] < arr2[idx2]) {
                arr[originalArrayIndex++] = arr1[idx1++];
            } else {
                arr[originalArrayIndex++] = arr2[idx2++];
            }
        }

        // Copy remaining elements from arr1, if any
        while(idx1 < len1) {
            arr[originalArrayIndex++] = arr1[idx1++];
        }

        // Copy remaining elements from arr2, if any
        while(idx2 < len2) {
            arr[originalArrayIndex++] = arr2[idx2++];
        }
    }
}

/*
 * Time Complexity: O(n log n)
 * - The array is divided into two halves at each recursive call, resulting in a total of `log n` levels of recursion.
 * - Merging elements at each level takes O(n) time.
 * - Therefore, the overall time complexity is O(n log n).
 * 
 * Space Complexity: O(n)
 * - The algorithm uses additional space to create temporary arrays arr1 and arr2 during each merge operation.
 * - Since we allocate extra space for half of the array at each level, the overall space complexity is O(n).
 */

