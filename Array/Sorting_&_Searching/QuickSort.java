public class QuickSort {
    // Partition function implementing Hoare's partition scheme
    // Quick Sort
    public static int partition(int[] arr, int start, int end) {
        int pivot = arr[start]; // Selecting the pivot as the first element
        int i = start; // Initialize pointer i at the start
        int j = end; // Initialize pointer j at the end

        // Loop until i and j cross each other
        while (i < j) {
            // Move i to the right as long as elements are <= pivot
            while (arr[i] <= pivot && i < end) {
                i++;
            }
            // Move j to the left as long as elements are > pivot
            while (arr[j] > pivot && j > start) {
                j--;
            }
            // If i is still to the left of j, swap arr[i] and arr[j]
            if (i < j) {
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        // Place pivot in its correct sorted position by swapping arr[start] with arr[j]
        int temp = arr[start];
        arr[start] = arr[j];
        arr[j] = temp;
        return j; // Return the index where pivot is now placed
    }

     // Recursively sorts the array using the QuickSort algorithm
     static void quickSort(int[] arr, int start, int end) {
        if (start < end) {
            // Find the pivot index
            int pivotIndex = partition(arr, start, end);
            
            // Recursively sort the left and right subarrays
            quickSort(arr, start, pivotIndex - 1);
            quickSort(arr, pivotIndex + 1, end);
        }
    }

    public static void main(String[] args) {
        int[] arr = {4, 6, 2, 5, 7, 9, 1, 3}; // Initialize unsorted array
        int n = arr.length;

        // Print array before sorting
        System.out.println("Before Using Quick Sort:");
        for (int i = 0; i < n; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();

        // Sort the array
        quickSort(arr, 0, n - 1);

        // Print array after sorting
        System.out.println("After Quick Sort:");
        for (int i = 0; i < n; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
}
/*
    Time Complexity Analysis:
    - Best Case: O(n log n)
        Occurs when pivot always divides array into two equal or almost equal parts
    - Average Case: O(n log n)
        Expected case for random input arrays
    - Worst Case: O(n²)
        Occurs when pivot is always smallest/largest element
        Happens with already sorted array if we always choose last element as pivot
        
    Space Complexity Analysis:
    - Worst Case Space: O(n)
        Due to recursive call stack
    - Average Case Space: O(log n)
        Due to recursive call stack with good partitioning
        
    Advantages:
    1. In-place sorting algorithm
    2. Cache friendly
    3. Average case performance is very good
    4. Can be optimized for different data types
    
    Disadvantages:
    1. Unstable sorting algorithm
    2. Worst case time complexity is O(n²)
    3. Not adaptive - doesn't take advantage of partially sorted arrays
*/