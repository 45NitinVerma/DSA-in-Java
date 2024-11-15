/* Count the number of digits of a number
   -> Method2 - By using log10    */

package Basic_Maths.DigitCounter;
import java.util.Scanner;
import static java.lang.Math.*;

public class Method2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the value of n : ");
        int n = scanner.nextInt();
        
        // Calculating the number of digits using log10
        int count = (int)(log10(n)) + 1;
        System.out.println("The total number of digits = " + count);
        
        scanner.close();
    }
}
