// Approach1: Backtracking with Inclusion and Exclusion Method 
package Combination_Sum;
import java.util.ArrayList;
import java.util.List;
public class Approach1 {
    public static void main(String[] args) {
        int[] candidates = {2,3};
        int target = 5;
        List<List<Integer>> result = combinationSum(candidates, target);
        System.out.println(result);
    }

    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
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

        // Include the current element (allow for repeats)
        currentSubset.add(candidates[index]);
        findCombinations(candidates, index, target - candidates[index], currentSubset, result);

        // Exclude the current element
        currentSubset.remove(currentSubset.size() - 1);
        findCombinations(candidates, index + 1, target, currentSubset, result);
    }
}
// TC: O(2^t), where t is the target value
// SC: O(t+2^t*t)