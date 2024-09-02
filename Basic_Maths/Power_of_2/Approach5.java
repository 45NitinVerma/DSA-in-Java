// Approach 5 - Using AND operator
package Power_of_2;
import java.util.Scanner;
public class Approach5{
        public static boolean isPowerOfTwo(int n) {
                return (n > 0) && ((n & (n - 1)) == 0);
        }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number: ");
        int n = scanner.nextInt();
        if(isPowerOfTwo(n)) System.out.println("It is power of 2");
        else System.out.println("Not a power of 2");
        scanner.close();
    }
}
//  TC - O(1), SC - O(1)