// Convert Binary number to its Decimal form
package Basic_Maths;
import java.util.Scanner;
public class BinToDec {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the Binary Number: ");
        int n = scanner.nextInt();
        int i = 0, ans = 0;
        // Process each digit of the binary number
        while (n != 0) {
            int digit = n % 10;  // Get the last digit (0 or 1)
            if (digit == 1) {
                ans += Math.pow(2, i);  // Add the value of the bit
            }
            n = n / 10;  // Remove the last digit
            i++;  // Move to the next bit position
        }
        System.out.println("The Decimal equivalent is: " + ans);
        scanner.close();
    }
}
// TC - O(log10 (n))
// SC - O(1)