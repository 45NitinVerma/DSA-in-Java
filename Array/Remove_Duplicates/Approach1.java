// Approach 1: Brute Force Approach
package Array.Remove_Duplicates;
import java.util.*;
public class Approach1 {
    // Method to remove duplicates from a sorted array
    static int removeDuplicates(int[] arr) {
        //Using HashSet to store unique elements
        HashSet<Integer> set = new HashSet<>();
        
        // Add all array elements to hashset
        for (int i = 0; i < arr.length; i++) {
            set.add(arr[i]); // Duplicate elements apne aap hat jayenge
        }
        
        // 'k' ki value set ki size ke barabar hai, jo unique elements ki count hai
        int k = set.size();
        int j = 0; // Index to overwrite the original array
        
        // Set ke har element ko wapas array me copy karte hain
        for (int x : set) {
            arr[j++] = x; // Overwrite the array with unique elements
        }
        
        // 'k' return karte hain, jo unique elements ki count hai
        return k;
    }
    public static void main(String[] args) {
        int arr[] = {1, 1, 2, 2, 2, 3, 3};
        int k = removeDuplicates(arr);
        System.out.println("The array after removing duplicate elements is ");
        for (int i = 0; i < k; i++) {
            System.out.print(arr[i] + " ");
        }
    }
}
/*
Time Complexity Analysis:
- First loop to add elements to HashSet: O(n)
- Second loop to copy elements back to array: O(n)
- Overall Time Complexity: O(n), where n is the length of input array

Space Complexity Analysis:
- Extra space used by HashSet: O(n) in worst case
- No other significant extra space used
- Overall Space Complexity: O(n), where n is the length of input array

Note: While this solution is simple to implement, it doesn't fulfill the 
"in-place" requirement in its truest sense as it uses extra space.
A two-pointer approach would give O(1) space complexity.
*/