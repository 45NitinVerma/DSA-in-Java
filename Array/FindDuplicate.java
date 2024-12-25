package Array;
public class FindDuplicate {
    public static int findDuplicate(int[] arr) {
        int n = arr.length - 1; // As the array size is N and contains numbers between 1 to N-1
        int ans = 0;
        // XOR all elements of the array
        for (int num : arr) {
            ans ^= num;
        }

        // XOR all natural numbers from 1 to N-1
        for (int i = 1; i <= n; i++) {
            ans ^= i;
        }

        // XORing xorArray and xorNatural gives the duplicate element
        return ans;
    }
    public static void main(String[] args) {
        int[] arr = { 1, 2, 3, 4, 4 };
        System.out.println("Duplicate element: " + findDuplicate(arr)); // Output: 4
    }
}