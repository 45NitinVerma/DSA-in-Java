package Fibo_Num;
import java.util.Scanner;
public class Approach1 {
     // Function to calculate Fibonacci iteratively
     public static int fibonacci(int n) {
        if(n<=1) return n;
        int prev2 = 0, prev1 = 1;
        int curr = 0;
        for (int i = 2; i <= n; i++) {
            curr = prev1 + prev2;
            prev2 = prev1;
            prev1 = curr;
        }
        return curr;
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the value of n:");
        int n = scanner.nextInt();
        System.out.println("The " + n + "th Fibonacci number is: " + fibonacci(n));
        scanner.close();
    }
}
// TC: O(n), SC: O(1)