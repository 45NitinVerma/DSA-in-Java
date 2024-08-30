/* Count the number of digits of a number
   -> Method1 - By using loop    */

package DigitCounter;
import java.util.Scanner;

public class Method1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the value of n : ");
        int n = scanner.nextInt();
        int count = 0;
        while (n>0) {
            count++;
            n=n/10;
        }
        System.out.println("The total number of digits = "+count);
        scanner.close();
    }
}

// TC: O(log10(n))