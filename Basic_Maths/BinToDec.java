package Basic_Maths;
import java.util.Scanner;

public class BinToDec {
    public static int binaryToDecimal(int binary) {
        int decimal = 0;
        int power = 0;
        
        while (binary != 0) {
            // Extract rightmost digit
            int digit = binary % 10;
            
            // Add digit * 2^power if digit is 1
            if (digit == 1) {
                decimal += Math.pow(2, power);
            }
            
            // Move to next digit
            binary = binary / 10;
            power++;
        }
        return decimal;
    }

    public static void main(String[] args) {
        // Test cases
        int[] testCases = {1101, 1010, 111, 1, 0};
        
        for (int binary : testCases) {
            System.out.println("Binary: " + binary + 
                             " -> Decimal: " + binaryToDecimal(binary));
        }
        
        // Interactive input
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nEnter a binary number: ");
        int n = scanner.nextInt();
        System.out.println("Decimal equivalent: " + binaryToDecimal(n));
        scanner.close();
    }
}

/*
Algorithm Explanation:
1. Take binary number as input (n)
2. Initialize decimal result (ans) and power counter (i) to 0
3. While n is not 0:
   a. Get rightmost digit: digit = n % 10
   b. If digit is 1: add 2^power to result
   c. Increment power
   d. Remove rightmost digit: n = n / 10
4. Return result

Example with binary 1101:
Initial: binary = 1101, decimal = 0, power = 0

Step 1: digit = 1
        decimal = 0 + 2^0 = 1
        power = 1
        binary = 110

Step 2: digit = 0
        decimal = 1 (no change)
        power = 2
        binary = 11

Step 3: digit = 1
        decimal = 1 + 2^2 = 5
        power = 3
        binary = 1

Step 4: digit = 1
        decimal = 5 + 2^3 = 13
        power = 4
        binary = 0

Final Result: 13

Time Complexity: O(log₁₀(n))
- Number of digits in binary number
- Each digit processed once

Space Complexity: O(1)
- Only using constant extra space
- Variables decimal, power, digit independent of input size
*/
