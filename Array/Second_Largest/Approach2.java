// Approach 2: Using loop 
package Array.Second_Largest;
public class Approach2 {
    public static int findSecondLargest(int[] arr) {
        // Check if array is null or has less than 2 elements
        if (arr == null || arr.length < 2) {
            return -1;
        }
        
        int largest = Integer.MIN_VALUE;
        int secondLargest = Integer.MIN_VALUE;
        
        // Single pass to find both largest and second largest
        for (int num : arr) {
            if (num > largest) {
                secondLargest = largest;
                largest = num;
            } else if (num > secondLargest && num != largest) {
                secondLargest = num;
            }
        }
        
        // Check if second largest was found
        return secondLargest == Integer.MIN_VALUE ? -1 : secondLargest;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 7, 7, 5};
        int secondLargest = findSecondLargest(arr);
        if (secondLargest != -1) {
            System.out.println("Second largest element (Optimal): " + secondLargest);
        }
    }
}
// Time Complexity: O(n)
// Space Complexity: O(1)