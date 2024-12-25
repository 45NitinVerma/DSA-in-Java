//Approach 2: Optimal approach using two Pointers
package Array.Remove_Duplicates;
public class Approach2 {
    static int removeDuplicates(int[] arr) {
        // i pointer to track unique elements positions
        int i = 0;
        
        // j pointer for scanning array
        for (int j = 1; j < arr.length; j++) {
            // Agar i aur j position pe different elements hain
            if (arr[i] != arr[j]) {
                // i ko aage badhao
                i++;
                // new unique element i ki position pe copy karo
                arr[i] = arr[j];
            }
            // Agar elements same hain toh sirf j aage badhega
        }
        
        // i+1 return karenge kyunki i zero se start hua tha
        return i + 1;
    }
    public static void main(String[] args) {
        int arr[] = {1,1,2,2,2,3,3};
        int k = removeDuplicates(arr);
        System.out.println("The array after removing duplicate elements is ");
        for (int i = 0; i < k; i++) {
            System.out.print(arr[i] + " ");
        }
    }
    
}

/*
Time Complexity Analysis:
- Single pass through array using two pointers: O(n)
- Overall Time Complexity: O(n), where n is the length of input array

Space Complexity Analysis:
- No extra space used, only two pointers
- Overall Space Complexity: O(1)

Note: This two-pointer approach is more efficient in terms of space
compared to HashSet solution as it modifies array in-place without 
using any extra space.
*/