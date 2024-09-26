// Approach 2: Recursive Backtracking
package Subset_Sum;
import java.util.ArrayList;
import java.util.List;
public class Approach2 {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5};
        int k = 5;
        List<Integer> currentSubset = new ArrayList<>();
        findSubsets(arr, 0, k, currentSubset);
    }
    public static void findSubsets(int[] arr, int index, int k, List<Integer> currentSubset) {
        if (k == 0) {
            System.out.println(currentSubset);
            return;
        }
        if (index == arr.length || k < 0)
            return;
        // Include the current element
        currentSubset.add(arr[index]);
        findSubsets(arr, index + 1, k - arr[index], currentSubset);

        // Exclude the current element
        currentSubset.remove(currentSubset.size() - 1);
        findSubsets(arr, index + 1, k, currentSubset);
    }
}
// TC: O(2^n), SC: O(n)