// Check for prime number
package Basic_Maths;
import java.util.Scanner;
public class CheckPrime {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the value of n: ");
        int n = scanner.nextInt();
        if (n <= 1) {
            System.out.println("Not prime");
            scanner.close(); // Close the scanner to avoid resource leaks
            return; 
        }
        boolean isPrime = true;
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                isPrime = false; // If divisible, n is not prime
                break; 
            }
        }
        // Output the result based on the isPrime flag
        if (isPrime) {
            System.out.println("Prime");
        } else {
            System.out.println("Not prime");
        }
        scanner.close(); // Close the scanner to avoid resource leaks
    }
}
// TC - O(sqrt(n)), SC - O(1)