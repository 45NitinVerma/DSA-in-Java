// Approach1b: Backtracking with Element Swapping
package Permutations.Recusive_Approach;
import java.util.Arrays;
public class Appraoch1b {
    public static void permute(int[] arr, int start) {
        if (start == arr.length) {
            System.out.println(Arrays.toString(arr));
            return;
        }
        for (int i = start; i < arr.length; i++) {
            swap(arr, start, i); // Swap to create a new permutation
            permute(arr, start + 1); // Recur to permute the remaining
            swap(arr, start, i); // Backtrack
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3};
        permute(arr, 0);
    }
}
/*
Time Complexity Analysis
1.Recursive Calls:
-For an array of size n, the function generates all permutations by making recursive calls at each level. At the first level, there are n options (since any element can be chosen for the first position). At the second level, there are n-1 options for the second position, and so on.
-Hence, the number of recursive calls made by the function is proportional to the number of permutations, which is n!.

2.Swapping:
-Each call to permute involves a loop that runs n times in total for the first level, n-1 times for the second level, and so on. This is because every element is being swapped to generate different permutations.
-Therefore, in total, the number of operations involved in swapping is proportional to n times n!.

3.Printing:
-The print statement is executed once for each permutation, and there are n! permutations.
-Thus, the overall time complexity is: T(n) = O(n×n!)

-------------------------------------------------------------------------------------------------------------------

Space Complexity Analysis
1.Recursion Stack:
-The function uses recursion, and the depth of recursion is n because there are n positions to fill.
-Each recursive call takes O(1) space (besides the recursion stack) since it only requires constant space for the loop, variables, and swap operations.
-The maximum recursion depth is n, so the space complexity due to recursion is O(n).

2.Array:
-The array arr[] is passed as a reference, so no additional space is required for it. If we were storing permutations in a separate list, that would take O(n! \times n) space (but in this case, we are just printing).
-Thus, the overall space complexity is:S(n)=O(n)

Summary:
Time Complexity: O(n × n!)
Space Complexity: O(n) (due to recursion stack) 
*/