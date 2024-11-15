// Approach 2: Using Merge Sort
package Array.Sorting_Searching.Count_Inversions;
import java.util.ArrayList;
public class Approach2 {

    // Helper function to merge two halves and count inversions
    private static int merge(int[] arr, int low, int mid, int high) {
        ArrayList<Integer> temp = new ArrayList<>(); // Temporary array for merged elements
        int left = low;      // Starting index of the left half
        int right = mid + 1; // Starting index of the right half
        int cnt = 0;         // Count of inversions

        // Store elements in sorted order in the temporary array and count inversions
        while (left <= mid && right <= high) {
            // If left half element is smaller, add it to temp
            if (arr[left] <= arr[right]) {
                temp.add(arr[left]);
                left++;
            } else {
                // Otherwise, add right half element to temp and count inversions
                temp.add(arr[right]);
                cnt += (mid - left + 1); // All elements in left half after 'left' are greater than arr[right]
                right++;
            }
        }

        // Copy any remaining elements from the left half
        while (left <= mid) {
            temp.add(arr[left]);
            left++;
        }

        // Copy any remaining elements from the right half
        while (right <= high) {
            temp.add(arr[right]);
            right++;
        }

        // Copy elements from temporary array back to the original array
        for (int i = low; i <= high; i++) {
            arr[i] = temp.get(i - low);
        }
        
        return cnt; // Return the count of inversions found during merging
    }

    // Recursive function to perform merge sort and count inversions
    public static int mergeSort(int[] arr, int low, int high) {
        int cnt = 0;
        if (low >= high) return cnt; // Base case: one element, no inversions
        int mid = (low + high) / 2;
        
        // Recursively sort the left half and count inversions
        cnt += mergeSort(arr, low, mid);
        
        // Recursively sort the right half and count inversions
        cnt += mergeSort(arr, mid + 1, high);
        
        // Merge both halves and count cross inversions
        cnt += merge(arr, low, mid, high);
        
        return cnt; // Return total count of inversions
    }

    // Function to initialize inversion counting on the array
    public static int numberOfInversions(int[] a, int n) {
        return mergeSort(a, 0, n - 1); // Start merge sort on the entire array
    }

    public static void main(String[] args) {
        int[] a = {5, 4, 3, 2, 1}; // Input array
        int n = 5;                 // Length of the array
        int cnt = numberOfInversions(a, n); // Get the number of inversions
        System.out.println("The number of inversions are: " + cnt); // Output result
    }
}

/*
 * Time Complexity: O(n log n)
 * - The mergeSort function divides the array into halves at each level, creating log n levels.
 * - At each level, merging requires O(n) time to count inversions and merge elements.
 * - Therefore, total time complexity is O(n log n).
 * 
 * Space Complexity: O(n)
 * - An ArrayList is used in each merge step as a temporary array, which requires O(n) space.
 * - Additionally, the recursive call stack can go up to O(log n) space.
 * - Overall, the space complexity is O(n) for storing temporary elements.
 */
