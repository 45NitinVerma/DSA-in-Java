// Approach 1: Brute Force Approach
package Permutation_Sequence;
import java.util.*;

public class Approach1 {
    
    // Swap two characters at positions i and j in the character array s
    static void swap(char s[], int i, int j) {
        char ch = s[i];  // Temporary variable to hold character at index i
        s[i] = s[j];     // Swap character at index i with character at index j
        s[j] = ch;       // Assign the character from temp to position j
    }

    // Recursive helper function to generate all permutations of the array s[]
    // index keeps track of the current position we are working on
    static void permutationHelper(char s[], int index, ArrayList<String> res) {
        // Base case: if the current index reaches the end of the array, 
        // a valid permutation is created and added to the result list
        if (index == s.length) {
            String str = new String(s);  // Convert char array to string
            res.add(str);  // Add the permutation to the result list
            return;  // Return to the previous recursive call
        }

        // For each index, we swap it with every other index starting from index
        for (int i = index; i < s.length; i++) {
            swap(s, i, index);  // Swap characters to generate a new permutation
            permutationHelper(s, index + 1, res);  // Recursive call to the next index
            swap(s, i, index);  // Backtrack (undo the swap) to restore the original state
        }
    }

    // Main function to return the kth permutation sequence of numbers from 1 to n
    static String getPermutation(int n, int k) {
        String s = "";  // String to store numbers from 1 to n
        ArrayList<String> res = new ArrayList<>();  // List to store all generated permutations
        
        // Create the initial string "123...n"
        for (int i = 1; i <= n; i++) {
            s += i;  // Add numbers 1 to n to the string
        }

        // Generate all permutations of the string
        permutationHelper(s.toCharArray(), 0, res);

        // Sort the list of permutations in lexicographical order
        Collections.sort(res);

        // Return the kth permutation (1-based index, so return res.get(k-1))
        return res.get(k - 1);  // k-1 because list is 0-indexed

    }

    // Main method to test the getPermutation method
    public static void main(String args[]) {
        int n = 3, k = 3;
        String ans = getPermutation(n, k);
        System.out.println("The Kth permutation sequence is " + ans);
    }
}

/**
 * Time and Space Complexity Analysis:
 * 
 * Time Complexity:
 * 1. Generating all permutations takes O(n!) time, as there are n! possible permutations of n numbers.
 * 2. Sorting the list of permutations takes O(n! * log(n!)), since we are sorting n! elements.
 * 3. Overall time complexity is dominated by the permutation generation and sorting, so it is:
 *    O(n! * log(n!)) time.
 * 
 * Space Complexity:
 * 1. The recursive call stack can go as deep as O(n), as each recursive call reduces the problem size by 1.
 * 2. Storing all the permutations in the result list requires O(n! * n) space, where n! is the number of permutations 
 *    and each permutation is a string of length n.
 * 3. Overall space complexity is O(n! * n) for storing permutations, plus O(n) for the recursion stack.
 */
 