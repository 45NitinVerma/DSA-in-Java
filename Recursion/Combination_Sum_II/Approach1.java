// Approach1: Backtracking with Inclusion and Exclusion Method
package Combination_Sum_II;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
public class Approach1 {
    public static void main(String[] args) {
        int[] candidates = {2, 3, 5};
        int target = 5;
        List<List<Integer>> result = combinationSum(candidates, target);
        System.out.println(result);
    }

    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(candidates); // Sorting helps with avoiding duplicates
        findCombinations(candidates, 0, target, new ArrayList<>(), result);
        return result;
    }

    private static void findCombinations(int[] candidates, int index, int target, List<Integer> currentSubset, List<List<Integer>> result) {
        if (target == 0) {
            result.add(new ArrayList<>(currentSubset));
            return;
        }
        if (target < 0 || index == candidates.length) {
            return;
        }

        for (int i = index; i < candidates.length; i++) {
            if (i > index && candidates[i] == candidates[i-1]) continue; // Skip duplicates
            if (candidates[i] > target) break; // Optimization: stop if current number is greater than target

            // Choose the current candidate
            currentSubset.add(candidates[i]);
            // Recurse with the next index (not allowing reuse of the same element)
            findCombinations(candidates, i + 1, target - candidates[i], currentSubset, result);
            // Backtrack
            currentSubset.remove(currentSubset.size() - 1);
        }
    }
}
