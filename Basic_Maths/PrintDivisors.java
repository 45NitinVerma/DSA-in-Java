// Print all Divisors of a number in a sorted order
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
public class PrintDivisors {
     // Method to print all divisors of a number
     public static void printDivisors(int n) {
        List<Integer> divisors = new ArrayList<>();
        for (int i = 1; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                divisors.add(i);  // Add the divisor
                if (i != n / i) {
                    divisors.add(n / i);  // Add the corresponding divisor
                }
            }
        }
        // Sort the divisors
        Collections.sort(divisors);

        // Print the divisors
        System.out.println("Divisors of " + n + " in sorted order are: \n" + divisors);
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a number: ");
        int n = scanner.nextInt();
        printDivisors(n);
        scanner.close();
    }
}
// TC - O(sqrt(n) * log n), SC - O(sqrt(n))