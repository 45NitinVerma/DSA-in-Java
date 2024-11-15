package Basic_Maths.GCD_or_HCF;
// M2 - GCD using Euclid's algorithm
import java.util.Scanner;
public class Method2 {
    public static int gcd(int a, int b) {
        while (a>0 && b>0) {
            if(a>b) a=a%b;
            else b=b%a;
        }
        if(a==0) return b;
        else return a;
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the first number: ");
        int num1 = scanner.nextInt();
        
        System.out.println("Enter the second number: ");
        int num2 = scanner.nextInt();
        
        int gcd = gcd(num1, num2);
        System.out.println("The GCD of " + num1 + " and " + num2 + " is: " + gcd);
        scanner.close();
    }
}
// TC - O(log(min(num1, num2)))
// SC - O(1)