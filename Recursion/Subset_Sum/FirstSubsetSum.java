// Print only first subset whose sum is k
package Subset_Sum;
import java.util.ArrayList;
import java.util.List;
public class FirstSubsetSum {
    public static List<Integer> findSubset(int[] arr, int k) {
        List<Integer> subset = new ArrayList<>();
        if (findSubsetHelper(arr, k, 0, subset)) {
            return subset;
        }
        return new ArrayList<>(); // Return empty list if no subset found
    }
    private static boolean findSubsetHelper(int[] arr, int k, int index, List<Integer> subset) {
        // Base cases
        if (k == 0) {
            return true; // We've found a subset with the target sum
        }
        if (index >= arr.length || k < 0) {
            return false; // We've exceeded the array bounds or the sum
        }

        // Include current element
        subset.add(arr[index]);
        if (findSubsetHelper(arr, k - arr[index], index + 1, subset)) {
            return true;
        }

        // Exclude current element (backtrack)
        subset.remove(subset.size() - 1);
        return findSubsetHelper(arr, k, index + 1, subset);
    }
    public static void main(String[] args) {
        int[] arr = {3, 34, 4, 12, 5, 2};
        int k = 9;
        List<Integer> result = findSubset(arr, k);
        System.out.println("First subset with sum " + k + ": \n" + result);
    }
}
// TC: O(2^n), SC: O(n)