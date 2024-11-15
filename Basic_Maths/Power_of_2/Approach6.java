// Approach 6 - By counting 1's
package Basic_Maths.Power_of_2;
import java.util.Scanner;
public class Approach6{
        public static boolean isPowerOfTwo(int n) {
            if (n <= 0) return false;
            int cnt = Integer.bitCount(n);
            return cnt == 1;
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