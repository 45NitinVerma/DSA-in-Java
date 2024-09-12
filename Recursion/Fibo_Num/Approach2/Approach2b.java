// Approach2(b) - Using memoization
package Fibo_Num.Approach2;
import java.util.Scanner;
public class Approach2b {
    public static int mfib(int n, int[] F) {
        if (n <= 1) return n;
        if (F[n] != -1) return F[n];
        F[n] = mfib(n - 1, F) + mfib(n - 2, F);
        return F[n];
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the value of n: ");
        int n = scanner.nextInt();

        // Initialize the memoization array F with size n+1
        int[] F = new int[n + 1];
        for (int i = 0; i <= n; i++)
            F[i] = -1;
        System.out.println("The " + n + "th Fibonacci number is: "  + mfib(n, F));
        scanner.close();
    }
}
// TC: O(n), SC: O(n)