// Approach 3: Recursion With Memoization
package Subset_Sum;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class Approach3 {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5};
        int k = 5;
        List<Integer> currentSubset = new ArrayList<>();
        Map<String, Boolean> memo = new HashMap<>();
        findSubsets(arr, 0, k, currentSubset, memo);
    }
    public static boolean findSubsets(int[] arr, int index, int k, List<Integer> currentSubset, Map<String, Boolean> memo) {
        if (k == 0) {
            System.out.println(currentSubset);
            return true;
        }
        if (index == arr.length || k < 0) 
            return false;
        String key = index + "-" + k;
        if (memo.containsKey(key)) {
            return memo.get(key);
        }

        // Include the current element
        currentSubset.add(arr[index]);
        boolean include = findSubsets(arr, index + 1, k - arr[index], currentSubset, memo);

        // Exclude the current element
        currentSubset.remove(currentSubset.size() - 1);
        boolean exclude = findSubsets(arr, index + 1, k, currentSubset, memo);

        memo.put(key, include || exclude);
        return memo.get(key);
    }
}
// TC: O(n*k), SC: O(n*k)