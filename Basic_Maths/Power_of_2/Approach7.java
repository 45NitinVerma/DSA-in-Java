// Approach 7 - Modulo with n
package Power_of_2;
import java.util.Scanner;
public class Approach7{
        public static boolean isPowerOfTwo(int n) {
            if (n <= 0) return false;
        return (1 << 30) % n == 0;
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