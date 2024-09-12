// Approach1: Iterative method to print factorial of a number 
package Fact_of_num;
import java.util.Scanner;
public class Approach1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the value of n: ");
        int n = scanner.nextInt();
        long ans = 1L;
        for (int i = 1; i <=n; i++) {
            ans *= i;
        }
        System.out.println("Factorial of "+n+" is: "+ans);
        scanner.close();
    }
}
// TC: O(n), SC: O(1)