package Array.Pair_Sum2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Approach1 {
    // Approach 1: Brute Force using nested loops
    public static List<int[]> pairSum2_BruteForce(int[] arr, int n, int s) {
        // Initialize result list to store valid pairs
        List<int[]> pairs = new ArrayList<>();
        
        // Iterate through each possible first element
        for (int i = 0; i < n; i++) {
            // Check all elements after current first element
            for (int j = i + 1; j < n; j++) {
                // If pair sums to target, add to result
                if (arr[i] + arr[j] == s) {
                    // Ensure smaller number is first in pair
                    pairs.add(new int[]{
                        Math.min(arr[i], arr[j]), 
                        Math.max(arr[i], arr[j])
                    });
                }
            }
        }
        
        // Sort pairs for consistent output
        Collections.sort(pairs, (a, b) -> {
            if (a[0] != b[0]) return a[0] - b[0];
            return a[1] - b[1];
        });
        
        return pairs;
    }

    // Driver method for testing
    public static void main(String[] args) {
        int[] arr = {11, 15, 6, 8, 9, 10};
        int n = arr.length;
        int targetSum = 16;

        List<int[]> result = pairSum2_BruteForce(arr, n, targetSum);
        System.out.println("Pairs with sum " + targetSum + ":");
        for (int[] pair : result) {
            System.out.println("[" + pair[0] + ", " + pair[1] + "]");
        }
    }
}
