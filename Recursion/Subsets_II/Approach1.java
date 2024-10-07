// Approach1: Iterative Approach
package Subsets_II;
import java.util.*;
public class Approach1 {
    public static List<List<Integer>> findSubsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);  // Sort the array to handle duplicates
        result.add(new ArrayList<>());  // Add the empty subset
        
        int start = 0;
        for (int i = 0; i < nums.length; i++) {
            int size = result.size();
            for (int j = start; j < size; j++) {
                List<Integer> newSubset = new ArrayList<>(result.get(j));
                newSubset.add(nums[i]);
                result.add(newSubset);
            }
            if (i < nums.length - 1 && nums[i] == nums[i+1]) {
                start = size;
            } else {
                start = 0;
            }
        }
        return result;
    }
    public static void main(String[] args) {
        int[] nums = {1, 2, 2};
        List<List<Integer>> result = findSubsets(nums);
        System.out.println(result);
    }
}
/*
Time Complexity: O(n log n + 2^n)
  - Sorting the array: O(n log n)
  - Iterating through subsets and adding elements: O(2^n)

Space Complexity: O(2^n * n)
  - Result set storing 2^n subsets, each of size n: O(2^n * n)
*/
