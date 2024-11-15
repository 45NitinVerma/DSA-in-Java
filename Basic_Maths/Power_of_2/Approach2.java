// Approach 2 - Dividing by 2
package Basic_Maths.Power_of_2;
import java.util.Scanner;
public class Approach2{
        public static boolean isPowerOfTwo(int n) {
            if (n == 0) return false;
            
            while (n > 0) {
                if (n == 1) return true;
                if (n % 2 != 0) break;
                n /= 2;
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