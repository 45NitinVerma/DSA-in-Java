// Approach 1: Using Set
package Array.Union_Of_Array;
import java.util.*;
public class Approach1 {
    // Function to find the union of two arrays
    static ArrayList<Integer> findUnion(int[] arr1, int[] arr2, int n, int m) {
        HashSet<Integer> set = new HashSet<>(); // Set to store unique elements
        ArrayList<Integer> union = new ArrayList<>(); // List to store final union

        // Step 1: Add all elements of the first array to the set
        for (int i = 0; i < n; i++) {
            set.add(arr1[i]);
        }

        // Step 2: Add all elements of the second array to the set
        for (int i = 0; i < m; i++) {
            set.add(arr2[i]);
        }

        // Step 3: Add all unique elements from the set to the result list
        for (int element : set) {
            union.add(element);
        }

        return union;
    }

    public static void main(String args[]) {
        // Test arrays
        int n = 10, m = 7;
        int[] arr1 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int[] arr2 = {2, 3, 4, 4, 5, 11, 12};

        // Find the union
        ArrayList<Integer> union = findUnion(arr1, arr2, n, m);

        // Print the result
        System.out.println("Union of arr1 and arr2 is:");
        for (int val : union) {
            System.out.print(val + " ");
        }
    }
}
/*
 * Algorithm Explanation:
 * 1. Create a HashSet to store unique elements.
 * 2. Traverse the first array and add each element to the set.
 * 3. Traverse the second array and add each element to the set.
 * 4. Convert the set to an ArrayList (union) to get the final result.
 * 
 * Example:
 * Input: arr1 = {1, 2, 3, 4, 5}, arr2 = {3, 4, 5, 6, 7}
 * Output: Union = {1, 2, 3, 4, 5, 6, 7}
 *
 * Time Complexity:
 * - Adding elements to the set: O(n + m)
 * - Traversing the set to add elements to the result: O(n + m)
 * Total: O(n + m)
 *
 * Space Complexity:
 * - HashSet to store unique elements: O(n + m)
 * - ArrayList to store the union: O(n + m)
 */