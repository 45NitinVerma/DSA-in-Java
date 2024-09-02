// Approach 8 - By Recursion
package Power_of_2;
import java.util.Scanner;
public class Approach8{
        public static boolean isPowerOfTwo(int n) {
            if (n <= 0) return false;
            if (n == 1) return true;
            return (n % 2 == 0) && isPowerOfTwo(n / 2);
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
//  TC - O(logn), SC - O(logn)