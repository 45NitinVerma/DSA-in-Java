// Approach1: Using two pointers
package Reverse_Array;
import java.util.Arrays;
public class Approach1 {
    public static void reverseArray(int[] arr) {
        int left = 0;
        int right = arr.length - 1;
        while (left < right) {
            // Swap the elements at left and right
            int temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;

            // Move the pointers
            left++;
            right--;
        }
    }
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5};
        System.out.println("Original array: " + Arrays.toString(arr));
        reverseArray(arr);
        System.out.println("Reversed array: " + Arrays.toString(arr));
    }
}
// TC: O(n), SC: O(n)