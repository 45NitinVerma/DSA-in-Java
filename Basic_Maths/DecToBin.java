// Conver Decimal number to its Binary form
package Basic_Maths;
import java.util.Scanner;
public class DecToBin {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the Decimal Number : ");
        int n = scanner.nextInt();
        int ans = 0;
        int i = 0;
        while (n != 0) {
            int bit = n & 1; // Get the last bit (0 or 1)
            ans = (bit * (int) Math.pow(10, i)) + ans; // Build the binary number in reverse
            n = n >> 1; // Shift n to the right by 1 bit (divide by 2)
            i++;
        }
        System.out.println("Answer is: " + ans);
        scanner.close();
    }
}
// TC - 0(log2 (n))
// SC - O(1)