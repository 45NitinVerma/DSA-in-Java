// Approach2: Backtracking(Recursion)
package Subset_Sums;
import java.util.ArrayList;
import java.util.List;
public class Approach2 {
    public static List<Integer> subsetSums(int[] arr) {
        List<Integer> result = new ArrayList<>();
        subsetSumsHelper(arr, 0, 0, result);
        return result;
    }

    private static void subsetSumsHelper(int[] arr, int index, int currentSum, List<Integer> result) {
        if (index == arr.length) {
            result.add(currentSum);
            return;
        }

        // Include the current element
        subsetSumsHelper(arr, index + 1, currentSum + arr[index], result);

        // Exclude the current element
        subsetSumsHelper(arr, index + 1, currentSum, result);
    }
    public static void main(String[] args) {
        int[] arr = {2, 3};
        List<Integer> sums = subsetSums(arr);
        System.out.println("Subset sums: " + sums);
    }
}
/* 
 Time Complexity: O(2^n)
 - In the worst case, we generate all possible subsets.
 - For each element, we have two choices (include or exclude), leading to 2^n recursive calls.
 
 Space Complexity: O(n)
 - The maximum depth of the recursion tree is n (the length of the input array).
 - The space used by the call stack is O(n).
 - Note: The space used by the result list (O(2^n)) is not counted in the recursive space complexity.
*/