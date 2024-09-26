// Approach 1: Iterative approach (Bit Manipulation)
package Subset_Sum;
import java.util.ArrayList;
import java.util.List;
public class Approach1 {
    public static void main(String[] args) {
        int[] arr = { 1, 2, 3, 4, 5 };
        int k = 5;
        findSubsets(arr, k);
    }
    // Helper function to find subsets iteratively
    public static void findSubsets(int[] arr, int k) {
        int n = arr.length;
        for (int i = 0; i < (1 << n); i++) {
            int sum = 0;
            List<Integer> subset = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                if ((i & (1 << j)) > 0) {
                    sum += arr[j];
                    subset.add(arr[j]);
                }
            }
            if (sum == k) {
                System.out.println(subset);
            }
        }
    }
}
