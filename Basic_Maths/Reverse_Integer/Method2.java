// M2: When ans goes out of range
package Basic_Maths.Reverse_Integer;
import java.util.Scanner;
public class Method2 {
    public static int reverseInteger(int n) {
        int revNum = 0;
        while (n != 0) {
            // Check for potential overflow
            if (revNum > Integer.MAX_VALUE / 10 || revNum < Integer.MIN_VALUE / 10)
                return 0; 
            revNum = revNum * 10 + n % 10;
            n = n / 10;
        }
        return revNum;
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the value of n: ");
        int n = scanner.nextInt();
        int ans = reverseInteger(n);
        System.out.println("The reverse of the given number is: " + ans);
        scanner.close();
    }
}
// TC - O(log10 (n)), SC - O(1)