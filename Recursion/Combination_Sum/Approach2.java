// Approach2: Optimised backtracking with loop 
package Combination_Sum;
import java.util.ArrayList;
import java.util.List;
public class Approach2 {
    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(candidates, 0, target, new ArrayList<>(), result);
        return result;
    }

    private static void backtrack(int[] candidates, int start, int target, List<Integer> current, List<List<Integer>> result) {
        if (target == 0) {
            result.add(new ArrayList<>(current));
            return;
        }
        if (target < 0) {
            return;
        }
        
        for (int i = start; i < candidates.length; i++) {
            current.add(candidates[i]);
            backtrack(candidates, i, target - candidates[i], current, result);
            current.remove(current.size() - 1);
        }
    }

    public static void main(String[] args) {
        int[] candidates = {2,3};
        int target = 5;
        List<List<Integer>> result = combinationSum(candidates, target);
        System.out.println(result);
    }
}
// TC: O(2^t), SC: O(t+2^t*t), where t is the target value