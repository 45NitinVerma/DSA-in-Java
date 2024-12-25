// Approach1: Brute Force Approach
package Array.Move_Zeroes;
import java.util.*;
public class Approach1 {
    static int[] moveZeros(int n, int[] arr) {
        // Temporary ArrayList to store non-zero elements
        ArrayList<Integer> temp = new ArrayList<>();
        
        // Step 1: Copy non-zero elements to temp list
        for (int i = 0; i < n; i++) {
            if (arr[i] != 0) {
                temp.add(arr[i]);
            }
        }
        // nz = number of non-zero elements
        int nz = temp.size();
        
        // Step 2: Copy non-zero elements from temp -> array
        for (int i = 0; i < nz; i++) {
            arr[i] = temp.get(i);
        }
        
        // Step 3: Fill rest place with 0
        for (int i = nz; i < n; i++) {
            arr[i] = 0;
        }
        return arr;
    }
    public static void main(String[] args) {
        // Original array with zeros and non-zeros
        int[] arr = {1, 0, 2, 3, 2, 0, 0, 4, 5, 1};
        int n = arr.length;
        int[] result = moveZeros(n, arr);
        System.out.println("Array after moving zeros to end: ");
        for (int i = 0; i < n; i++) {
            System.out.print(result[i] + " ");
        }
    }
}
/*
Time Complexity Analysis:
- First loop to copy non-zero elements: O(n)
- Second loop to copy back elements: O(k) where k is non-zero elements
- Third loop to fill zeros: O(n-k)
- Overall Time Complexity: O(n)

Space Complexity Analysis:
1. Auxiliary Space:
   - ArrayList to store non-zero elements: O(k)
   - Where k is the count of non-zero elements
   - In worst case, k can be n, making it O(n)

2. Input Space:
   - Original array of size n: O(n)

Total Space Complexity: O(n)

Note: While this approach is easy to understand, it uses extra space.
A more space-efficient solution would be using two-pointer approach
with O(1) extra space.
*/

