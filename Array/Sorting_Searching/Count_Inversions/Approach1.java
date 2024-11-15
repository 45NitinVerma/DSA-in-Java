package Array.Sorting_Searching.Count_Inversions;
// Approach 1: Brute Force Approach
public class Approach1 {

    // Function to count the number of inversions in the array
    public static int numberOfInversions(int[] a, int n) {
        int cnt = 0; // Counter for inversion pairs
        
        // Loop through each element to find pairs
        for (int i = 0; i < n; i++) {
            // For each element 'a[i]', compare it with all subsequent elements 'a[j]'
            for (int j = i + 1; j < n; j++) {
                // If a[i] > a[j], it is an inversion
                if (a[i] > a[j]) cnt++;
            }
        }
        return cnt; // Return the total count of inversions
    }

    public static void main(String[] args) {
        int[] a = {5, 4, 3, 2, 1}; // Array to check for inversions
        int n = 5;                  // Length of the array
        int cnt = numberOfInversions(a, n); // Count inversions in array 'a'
        System.out.println("The number of inversions is: " + cnt); // Output result
    }
}

/*
 * Time Complexity: O(n^2)
 * - The code uses two nested loops where each element is compared with every 
 *   other element following it in the array.
 * - For an array of length n, the inner loop runs approximately n(n-1)/2 times,
 *   resulting in O(n^2) complexity.
 * 
 * Space Complexity: O(1)
 * - Only a constant amount of extra space (for the 'cnt' variable) is used, 
 *   regardless of the size of the input array.
 * - Therefore, the space complexity is O(1), making it an in-place approach 
 *   without requiring additional storage proportional to the input size.
 */
