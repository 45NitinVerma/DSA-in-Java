// Approach2: Optimised backtracking with loop 
package Combination_Sum_II;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
public class Approach2 {
    public static void main(String[] args) {
        int[] candidates = {1,1,1,2,2};
        int target = 4;
        List<List<Integer>> result = combinationSum2(candidates, target);
        System.out.println(result);
    }

    public static List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(candidates); // Sort to handle duplicates
        backtrack(candidates, 0, target, new ArrayList<>(), result);
        return result;
    }

    private static void backtrack(int[] candidates, int start, int target, List<Integer> current, List<List<Integer>> result) {
        if (target == 0) {
            result.add(new ArrayList<>(current));
            return;
        }

        for (int i = start; i < candidates.length; i++) {
            if (i>start && candidates[i] == candidates[i-1]) continue; // Skip duplicates
            if (candidates[i] > target) break; // Optimization: stop if current number is greater than target

            current.add(candidates[i]);
            backtrack(candidates, i + 1, target - candidates[i], current, result);
            current.remove(current.size() - 1);
        }
    }
}
/* 
Time Complexity: O(2^n)
- In the worst case, we might end up exploring all possible subsets of the input array.
- Each element has two choices: either it's included in the current combination or it's not.
- This leads to a decision tree with potentially 2^n nodes, where n is the number of candidates.
- The actual time complexity is often better due to pruning (breaking when candidate > target).

Space Complexity: O(n)
- The space complexity comes from two main factors:
  1. Recursion stack: In the worst case, the recursion depth can go up to n.
  2. The 'current' list: It can store at most n elements.
- The 'result' list is not counted in the space complexity as it's considered part of the output.
- While we're creating new lists for each valid combination, these are part of the output 
  and not the auxiliary space used by the algorithm itself.

Note: n is the number of candidates in the input array.
*/