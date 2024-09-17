// To find 2 raised to power n
import java.util.Scanner;
public class PowerOf2{
        public static int PowerOfTwo(int n) {
            if(n==0 || n==1) return n+1;
            return 2*PowerOfTwo(n-1);
        }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number: ");
        int n = scanner.nextInt();
        System.out.println("2 to the power "+n+" is: "+PowerOfTwo(n));
        scanner.close();
    }
}
//  TC - O(n), SC - O(n)