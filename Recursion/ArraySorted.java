// Check if array is sorted?
public class ArraySorted {
    public static boolean isSorted(int[] arr, int index) {
        // Base case: if we've reached the end of the array, it's sorted
        if (index == arr.length - 1) {
            return true;
        }
        // Check if current element is greater than the next element
        if (arr[index] > arr[index + 1]) {
            return false;
        }
        // Recursive call to check the rest of the array
        return isSorted(arr, index + 1);
    }
    public static void main(String[] args) {
        int[] arr = {2, 4, 5, 1, 9, 6, 3, 8, 7};
        boolean sorted = isSorted(arr, 0);
        System.out.println("Is the array sorted? \n" + sorted);
    }
}
// TC: O(n), SC: O(n)