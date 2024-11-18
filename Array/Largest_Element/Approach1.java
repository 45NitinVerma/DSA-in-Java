// Approach 1 - Brute force approach 
package Array.Largest_Element;
import java.util.Arrays;
public class Approach1 {
    public static int findLargest(int[] arr) {
        // Check if array is null or empty
        if (arr == null || arr.length == 0) {
            return -1;
        }
        
        // Sort the array in ascending order
        Arrays.sort(arr);
        
        // Return the last element (largest)
        return arr[arr.length - 1];
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 7, 7, 5};
        int largest = findLargest(arr);
        if(largest != -1) {
            System.out.println("Largest element: " + largest);
        }
    }
}
// Time Complexity: O(n log n)
// Space Complexity: O(1)