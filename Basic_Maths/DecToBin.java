package Basic_Maths;
import java.util.Scanner;

public class DecToBin {
    public static int decimalToBinary(int n) {
        int ans = 0;
        int i = 0;
        
        while (n != 0) {
            // Get rightmost bit using bitwise AND with 1
            int bit = n & 1;
            
            // Build binary number from right to left
            // Using powers of 10 to position each bit
            ans = (bit * (int) Math.pow(10, i)) + ans;
            
            // Right shift to remove processed bit
            n = n >> 1;
            i++;
        }
        return ans;
    }

    public static void main(String[] args) {
        // Test cases
        int[] testCases = {13, 7, 4, 1, 0};
        
        for (int num : testCases) {
            System.out.println("Decimal: " + num + 
                             " -> Binary: " + decimalToBinary(num));
        }
        
        // Interactive input
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nEnter a decimal number: ");
        int n = scanner.nextInt();
        System.out.println("Binary representation: " + decimalToBinary(n));
        scanner.close();
    }
}

/*
Time Complexity: O(log₂n)
- We divide n by 2 in each iteration
- Number of iterations = log₂n

Space Complexity: O(1)
- Only using constant extra space
- Variables ans, i, bit are independent of input size

Algorithm Steps:
1. Take decimal number as input (n)
2. Initialize answer (ans) and position counter (i) to 0
3. While n is not 0:
   a. Get last bit using bitwise AND: bit = n & 1
   b. Add bit to answer: ans = (bit * 10^i) + ans
   c. Right shift n by 1: n = n >> 1
   d. Increment i
4. Print result

Example with n = 13:
Initial: n = 13 (1101 in binary)
         ans = 0, i = 0

Step 1: n = 13    (1101)
        bit = 13 & 1 = 1
        ans = (1 * 10^0) + 0 = 1
        n = 13 >> 1 = 6
        i = 1

Step 2: n = 6     (0110)
        bit = 6 & 1 = 0
        ans = (0 * 10^1) + 1 = 1
        n = 6 >> 1 = 3
        i = 2

Step 3: n = 3     (0011)
        bit = 3 & 1 = 1
        ans = (1 * 10^2) + 1 = 101
        n = 3 >> 1 = 1
        i = 3

Step 4: n = 1     (0001)
        bit = 1 & 1 = 1
        ans = (1 * 10^3) + 101 = 1101
        n = 1 >> 1 = 0
        i = 4

Final Result: 1101
*/
