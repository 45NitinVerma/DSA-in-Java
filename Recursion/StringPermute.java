// Approach: Recursion with swapping elements
import java.util.Arrays;
public class StringPermute {
    public static void permute(char[] arr, int index) {
        if (index == arr.length - 1) {
            System.out.println(String.valueOf(arr));
            return;
        }

        for (int i = index; i < arr.length; i++) {
            if (i != index && arr[i] == arr[index]) {
                continue; // Skip duplicates
            }
            swap(arr, index, i); // Swap the current index with i
            permute(arr, index + 1); // Recur for the next index
            swap(arr, index, i); // Backtrack by undoing the swap
        }
    }

    // Swap helper function
    public static void swap(char[] arr, int i, int j) {
        char temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        String input = "bca";
        char[] arr = input.toCharArray();
        Arrays.sort(arr); // Sort the array to ensure lexicographical order
        permute(arr, 0);  // Generate permutations
    }
}
/*
Time Complexity:
-n! for generating all permutations.
-n because, at each level of recursion, you loop through the remaining characters, making n recursive calls for the first level, n-1 for the second, and so on.

Space Complexity:
-The space complexity is dominated by the recursion stack, which can go as deep as O(n) levels for an array of size n.
-If we consider additional space for storing permutations (if needed), it would be O(n! * n). However, if the result is just printed, the space complexity is just O(n) for the recursion stack.
 */