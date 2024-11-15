// Approach 3 - Using Left Shift
package Basic_Maths.Power_of_2;
import java.util.Scanner;
public class Approach3{
        public static boolean isPowerOfTwo(int n) {
            int x = 1;
            while (x <= n) {
                if (x == n) return true;
                if (x > Integer.MAX_VALUE / 2) break;
                x = x << 1;
            }
            return false;
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
//  TC - O(logn), SC - O(1)