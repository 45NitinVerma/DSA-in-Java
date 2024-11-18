// Approach 2 - Optimised approach
package Array.Largest_Element;
public class Approach2 {
    public static int findLargest(int[] arr) {
        // Check for null or empty array
        if (arr == null || arr.length == 0) {
            return -1;
        }
        
        int largest = arr[0];
        for(int i = 1; i < arr.length; i++) {  // Start from index 1
            if(arr[i] > largest) {
                largest = arr[i];
            }
        }
        return largest;
    }

    public static void main(String[] args) {
        int[] arr = {3,2,5,4,9};
        int result = findLargest(arr);
        if(result != -1) {
            System.out.println("Largest element is " + result);
        }
    }
}
// Time Complexity: O(n)
// Space Complexity: O(1)