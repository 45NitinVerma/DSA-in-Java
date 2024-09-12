// Approach2: Using One pointer
package Reverse_Array;
import java.util.Arrays;
public class Approach2 {
    public static void reverseArray(int[] arr, int i) {
        int n = arr.length;
        // Base case: Stop when i reaches half of the array
        if (i >= n / 2) return;

        // Swap the elements at index and (n - i - 1)
        int temp = arr[i];
        arr[i] = arr[n - i - 1];
        arr[n - i - 1] = temp;
        reverseArray(arr, i + 1); // Recursive call
    }
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5};
        System.out.println("Original array: " + Arrays.toString(arr));
        reverseArray(arr, 0);
        System.out.println("Reversed array: " + Arrays.toString(arr));
    }
}
// TC: O(n), SC: O(n)