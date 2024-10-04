// Approach1: Bit manipulation(Iteration)
package Subset_Sums;
import java.util.ArrayList;
import java.util.List;
public class Approach1 {
    public static List<Integer> subsetSums(int[] arr) {
        int n = arr.length;
        List<Integer> result = new ArrayList<>();

        // Total number of subsets is 2^n
        int totalSubsets = 1 << n;

        for (int i = 0; i < totalSubsets; i++) {
            int sum = 0;
            for (int j = 0; j < n; j++) {
                // Check if jth bit is set in i
                if ((i & (1 << j)) != 0) {
                    sum += arr[j];
                }
            }
            result.add(sum);
        }
        return result;
    }
    public static void main(String[] args) {
        int[] arr = { 2, 3 };
        List<Integer> sums = subsetSums(arr);
        System.out.println("Subset sums: " + sums);
    }
}
/*
Time Complexity: O(n * 2^n)
- We generate 2^n subsets using the outer loop.
- For each subset, we iterate through all n elements to calculate the sum.
- This results in n * 2^n operations.

Space Complexity: O(1)
- We only use a constant amount of extra space for variables (i, sum, j).
- Note: The space used by the result list (O(2^n)) is not counted in the auxiliary space complexity.
*/