/* Find the reverse of given number */
package Basic_Maths.Reverse_Integer;
import java.util.Scanner;
public class Method1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the value of n : ");
        int n = scanner.nextInt();
        int revNum = 0;
        while (n != 0) {
            revNum = (revNum * 10) + n % 10;
            n = n / 10;
        }
        System.out.println("The reverse of given number is : " + revNum);
        scanner.close();
    }
}

// TC: O(log10(n))