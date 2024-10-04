// Count total subsets present whose sum is k
package Subset_Sum;
public class CountSubsetSum {
    public static int countSubsets(int[] arr, int k) {
        return countSubsetsHelper(arr, k, 0);
    }
    private static int countSubsetsHelper(int[] arr, int k, int index) {
        // Base case: If the target sum is 0, we have found a valid subset
        if (k == 0) 
            return 1; // We found one valid subset
        
        // Base case: If we exceed the array bounds or if the target sum becomes negative
        if (index >= arr.length || k < 0) 
            return 0; // No valid subset found
        
        // Recursive case:
        // 1. Include the current element in the subset and check the remaining elements
        int include = countSubsetsHelper(arr, k - arr[index], index + 1);

        // 2. Exclude the current element from the subset and check the remaining elements
        int exclude = countSubsetsHelper(arr, k, index + 1);

        // Return the total count from both include and exclude cases
        return include + exclude;
    }
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4};
        int k = 5;
        int result = countSubsets(arr, k);
        System.out.println("Number of subsets with sum " + k + ":\n" + result);
    }
}
// TC: O(2^n), SC: O(n)