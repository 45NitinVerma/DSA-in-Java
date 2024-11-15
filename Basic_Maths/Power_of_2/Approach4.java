// Approach 4 - Ceil and floor
package Basic_Maths.Power_of_2;
import java.util.Scanner;
public class Approach4{
        public static boolean isPowerOfTwo(int n) {
            if (n == 0) return false;
        return Math.floor(Math.log(n) / Math.log(2)) == 
                    Math.ceil(Math.log(n) / Math.log(2));
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