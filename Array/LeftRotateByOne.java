package Array;
public class LeftRotateByOne {
    public static void leftRotateByOne(int[] arr) {
        int n = arr.length;

        // Step 1: Store the first element in a temporary variable
        int temp = arr[0];

        // Step 2: Shift all elements one position to the left
        for (int i = 1; i < n; i++) {
            arr[i-1] = arr[i];
        }

        // Step 3: Place the first element at the end of the array
        arr[n - 1] = temp;
    }

    public static void main(String[] args) {
        int[] arr = { 1, 2, 3, 4, 5 };

        System.out.println("Original Array:");
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();

        // Call the function to rotate the array by one
        leftRotateByOne(arr);

        System.out.println("Array after left rotation by one:");
        for (int num : arr) {
            System.out.print(num + " ");
        }
    }
}
/*
 * Time Complexity:
 * - The loop shifts all elements once, so the time complexity is O(n).
 *
 * Space Complexity:
 * - The temporary variable `temp` uses O(1) extra space.
 * - Hence, the space complexity is O(1).
 * - O(n) SC for permanent space
 */