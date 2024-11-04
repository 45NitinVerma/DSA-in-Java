// Approach 2: Optimal Approach
package Permutation_Sequence;
import java.util.*;
public class Approach2 {
    /*
    * Finds the kth permutation sequence for numbers from 1 to n.
    * 
     * The algorithm uses a mathematical approach based on factorial number system:
     * 1. Calculate (n-1)! as we'll use it to determine positions
     * 2. Convert k to 0-based indexing
     * 3. For each position:
     *    - Use k/fact to find the index of the next number
     *    - Update k to k%fact for next iteration
     *    - Update fact by dividing it by remaining numbers
     */
    static String getPermutation(int n, int k) {
        // Calculate (n-1)! and build list of available numbers
        int fact = 1;
        ArrayList<Integer> numbers = new ArrayList<>();
        for (int i = 1; i < n; i++) {
            fact = fact * i;     // Calculates (n-1)!
            numbers.add(i);      // Add numbers 1 to n-1
        }
        numbers.add(n);          // Add the last number n

        // Convert k to 0-based indexing
        k = k - 1;
        
        // Build the kth permutation
        String ans = "";
        while (true) {
            // Find the next number in the sequence
            // k/fact gives the index of the number to use
            ans = ans + "" + numbers.get(k / fact);
            numbers.remove(k / fact);
            
            // If all numbers are used, we're done
            if (numbers.size() == 0) {
                break;
            }

            // Update k and factorial for next iteration
            k = k % fact;        // Remaining positions to process
            fact = fact / numbers.size();  // Update factorial for next position
        }
        return ans;
    }

    public static void main(String args[]) {
        int n = 3, k = 3;
        String ans = getPermutation(n, k);
        System.out.println("The Kth permutation sequence is " + ans);
    }
}

/**
 * Time Complexity Analysis:
 * 1. Initial loop to calculate factorial and build numbers list: O(n)
 * 2. Main while loop runs n times (once for each position):
 *    - get() operation: O(1)
 *    - remove() operation: O(n) due to shifting elements
 *    - String concatenation: O(n) due to creating new string each time
 * 3. Overall time complexity: O(n^2)
 *    - The remove() operation in ArrayList is the dominant factor
 *    - String concatenation also contributes to quadratic complexity
 * 
 * Space Complexity Analysis:
 * 1. ArrayList<Integer> numbers: O(n) space to store n numbers
 * 2. String ans: O(n) space for final result
 * 3. Other variables (fact, k): O(1) space
 * 4. Overall space complexity: O(n)
 * 
 * Optimization Opportunities:
 * 1. Replace String concatenation with StringBuilder for better performance
 * 2. Could use LinkedList instead of ArrayList for O(1) removal
 * 3. Could pre-calculate factorials if multiple queries needed
 * 
 * Mathematical Note:
 * This algorithm uses the factorial number system concept:
 * - Each position in the sequence is determined by dividing k by the factorial
 * - The quotient gives the index of the number to use
 * - The remainder becomes the new k for next position
 * - The factorial is adjusted by dividing by remaining numbers
 */
