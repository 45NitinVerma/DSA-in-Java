// Count Prime numbers strictly less than a given number
package Basic_Maths;
import java.util.Arrays;
import java.util.Scanner;
public class CountPrimes {
    public static int countPrimes(int n) {
        if (n <= 2) return 0;
        // Create a boolean array "isPrime[0..n-1]" 
        boolean[] isPrime = new boolean[n+1];
        Arrays.fill(isPrime, true); //initialize all entries as true.
        // Apply Sieve of Eratosthenes
        for (int p = 2; p * p < n; p++) {
            if (isPrime[p]) {
                // Mark all multiples of p as not prime
                for (int i = p * p; i < n; i += p)
                    isPrime[i] = false;
            }
        }
        // Count all primes less than n
        int count = 0;
        for (int i = 2; i < n; i++)
           { if (isPrime[i]) count++;}
        return count;
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the value of n: ");
        int n = scanner.nextInt();
        int primeCount = countPrimes(n);
        System.out.println("Number of primes strictly less than " + n + " is:\n" + primeCount);
        scanner.close();
    }
}
// TC - O(n log(log n)), SC - O(n)