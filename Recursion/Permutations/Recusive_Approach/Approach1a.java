// Approach 1(a): Backtracking with ArrayList contains check
package Permutations.Recusive_Approach;
import java.util.List;
import java.util.ArrayList;
public class Approach1a {
    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(nums, new ArrayList<>(), result);
        return result;
    }

    private static void backtrack(int[] nums, List<Integer> current, List<List<Integer>> result) {
        if (current.size() == nums.length) {
            result.add(new ArrayList<>(current));
            return;
        }

        for (int num : nums) {
            if (current.contains(num)) continue;
            current.add(num);
            backtrack(nums, current, result);
            current.remove(current.size() - 1);
        }
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        List<List<Integer>> permutations = permute(nums);
        for (List<Integer> perm : permutations) {
            System.out.println(perm);
        }
    }
}
/*
Time Complexity Analysis:
-We generate all possible permutations, which is n! for n distinct elements.
-For each permutation, we perform O(n) work to create a copy of the permutation and add it to the result.
-Therefore, the overall time complexity is O(n * n!).

Space Complexity Analysis:
1.Recursion stack:
-The maximum depth of the recursion tree is n (the length of the input array).
-At each level, we store O(n) data (the current permutation).
-This contributes O(n^2) to the space complexity.

2.Result storage:
-We store all n! permutations, each of length n.
-This contributes O(n * n!) to the space complexity.

3.Current permutation:
-We maintain a single current permutation of maximum length n.
-This contributes O(n) to the space complexity.
*/