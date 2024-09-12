// Approach2(a): Not optimised
package Fibo_Num.Approach2;
import java.util.Scanner;
public class Approach2a {
    public static int fib(int n){
        if(n<=1) return n;
        return fib(n-1)+fib(n-2);
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the value of n: ");
        int n = scanner.nextInt();
        System.out.println("The " + n + "th Fibonacci number is: "+fib(n));
        scanner.close();
    }
}
// TC: O(2^n), SC: O(n)