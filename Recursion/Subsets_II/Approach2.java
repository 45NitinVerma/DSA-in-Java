// Approach2: (Backtracking) Recursive Approach
package Subsets_II;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
public class Approach2 {
    public static List<List<Integer>> findSubsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);  // Sort the array to handle duplicates
        backtrack(nums, 0, new ArrayList<>(), result);
        return result;
    }
    
    private static void backtrack(int[] nums, int start, List<Integer> current, List<List<Integer>> result) {
        result.add(new ArrayList<>(current));
        
        for (int i = start; i < nums.length; i++) {
            if (i > start && nums[i] == nums[i-1]) continue;  // Skip duplicates
            current.add(nums[i]);
            backtrack(nums, i + 1, current, result);
            current.remove(current.size() - 1);
        }
    }
    public static void main(String[] args) {
        int[] nums = {1, 2, 2};
        List<List<Integer>> result = findSubsets(nums);
        System.out.println(result);
    }
}
/*
Time Complexity: O(n log n + 2^n * n)
  - Sorting the array: O(n log n)
  - Backtracking recursion generates all subsets: O(2^n)
  - Copying each subset to result: O(n) for each subset

Space Complexity: O(2^n * n)
  - Recursion stack: O(n)
  - Result set storing 2^n subsets, each of size n: O(2^n * n)
*/