// Approach1c: Backtracking with boolean frequency array
package Recusive_Approach;
import java.util.ArrayList;
import java.util.List;
public class Approach1c {
    private static void recurPermute(int[] nums, List<Integer> ds, List<List<Integer>> ans, boolean[] freq) {
        // Base case: If we have added 'n' numbers to the current permutation, store the
        // result
        if (ds.size() == nums.length) {
            ans.add(new ArrayList<>(ds)); // Save the current permutation
            return;
        }

        // Recursive case: Try to add every number to the current permutation
        for (int i = 0; i < nums.length; i++) {
            if (!freq[i]) { // If the number is not yet used in this permutation
                freq[i] = true; // Mark the number as used
                ds.add(nums[i]); // Add the number to the current permutation
                recurPermute(nums, ds, ans, freq); // Recur to build the next position

                // Backtracking step: Undo the last choice
                ds.remove(ds.size() - 1); // Remove the last number
                freq[i] = false; // Unmark the number as used
            }
        }
    }

    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>(); // Final result list to store all permutations
        List<Integer> ds = new ArrayList<>(); // Temporary list for current permutation
        boolean[] freq = new boolean[nums.length]; // Boolean array to track used elements
        recurPermute(nums, ds, ans, freq); // Start recursion
        return ans;
    }
    public static void main(String[] args) {
        int[] arr = {1, 2, 3};
        System.out.println(permute(arr));
    }
}

/* 
Time Complexity: O(n * n!)
- We generate all n! permutations.
- For each permutation, we perform O(n) work:
   * O(n) to build the permutation (adding n elements to ds)
   * O(n) to create a copy of ds when adding to ans
   - The recursion tree has n levels, with n! leaf nodes (complete permutations).
   - At each node, we perform O(1) work (adding/removing element, updating freq).
   - At leaf nodes, we perform O(n) work to copy the permutation.
   Total: O(n * n!)
   
Space Complexity: O(n * n!)
   1. Recursion stack: O(n)
   - Maximum depth of recursion tree is n.
   2. freq array: O(n)
   - Boolean array of size n.
   3. ds list: O(n)
   - Stores current permutation, maximum size n.
   4. ans list: O(n * n!)
   - Stores all n! permutations, each of size n.
   5. Temporary space for new ArrayList: O(n)
   - When adding a complete permutation to ans.
   Total: O(n) + O(n) + O(n) + O(n * n!) + O(n) = O(n * n!)
   
   Note: If we only consider auxiliary space (excluding output storage),
   the space complexity would be O(n).
*/