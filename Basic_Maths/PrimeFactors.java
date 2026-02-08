package Basic_Maths;

// =======================================================
// Problem: Find Prime Factors of a Number
// Language: Java
// Style: Single file, intuitive, Hinglish comments
// Approaches covered:
// 1) Brute Force
// 2) Optimized (sqrt method)
// 3) Most Optimized (smallest prime factor idea)
// =======================================================

import java.util.*;

public class PrimeFactors {

    // -------------------------------------------------------
    // Approach 1: Brute Force
    // Idea:
    // 2 se n tak har number check karo
    // Agar i se n divide ho raha hai, to i prime factor ho sakta hai
    // Jab tak divide hota rahe, divide karte jao
    // -------------------------------------------------------
    static List<Integer> primeFactorsBrute(int n) {
        List<Integer> factors = new ArrayList<>();

        for (int i = 2; i <= n; i++) {          // 2 se n tak check
            factors.add(i);                 // factor add karo
            while (n % i == 0) {                // jab tak divisible hai
                n = n / i;                      // n ko chhota karo
            }
        }
        return factors;
    }

    // -------------------------------------------------------
    // Approach 2: Optimized using sqrt(n)
    // Idea:
    // Agar n composite hai, to uska ek factor sqrt(n) se chhota hoga
    // Isliye sirf sqrt(n) tak check karna kaafi hai
    // Loop ke baad agar n > 1 bacha, to wo prime hi hoga
    // -------------------------------------------------------
    static List<Integer> primeFactorsOptimized(int n) {
        List<Integer> factors = new ArrayList<>();

        for (int i = 2; i * i <= n; i++) {       // i <= sqrt(n)
            factors.add(i);
            while (n % i == 0) {
                n = n / i;
            }
        }

        // Agar n abhi bhi > 1 hai, to wo prime factor hai
        if (n > 1) {
            factors.add(n);
        }

        return factors;
    }

    // -------------------------------------------------------
    // Approach 3: Most Optimized (using Smallest Prime Factor logic)
    // Idea:
    // Pehle 2 ko handle karo
    // Phir sirf odd numbers check karo (kyunki even already handle ho chuke)
    // Yeh approach practically fastest hoti hai
    // -------------------------------------------------------
    static List<Integer> primeFactorsMostOptimized(int n) {
        List<Integer> factors = new ArrayList<>();

        // Step 1: 2 ke factors nikaalo
        factors.add(2);
        while (n % 2 == 0) {
            n = n / 2;
        }

        // Step 2: Sirf odd numbers check karo
        for (int i = 3; i * i <= n; i += 2) {
            factors.add(i);
            while (n % i == 0) {
                n = n / i;
            }
        }

        // Step 3: Agar n > 1 bacha hai
        if (n > 1) {
            factors.add(n);
        }

        return factors;
    }

    // -------------------------------------------------------
    // Main Function
    // Yahin se program start hota hai
    // -------------------------------------------------------
    public static void main(String[] args) {
        int n = 84;   // Example number

        System.out.println("Number: " + n);

        System.out.println("\nBrute Force Prime Factors:");
        System.out.println(primeFactorsBrute(n));

        System.out.println("\nOptimized (sqrt) Prime Factors:");
        System.out.println(primeFactorsOptimized(n));

        System.out.println("\nMost Optimized Prime Factors:");
        System.out.println(primeFactorsMostOptimized(n));
    }
}

/*
===========================================================
INTUITION + TIME & SPACE COMPLEXITY (Summary)
===========================================================

1) Brute Force Approach
   - Intuition:
     Har number ko try karo ki wo factor hai ya nahi
     Simple hai par slow hai
   - Time Complexity: O(n)
   - Space Complexity: O(1) (excluding output list)

2) Optimized using sqrt(n)
   - Intuition:
     Agar n composite hai to uska ek factor sqrt(n) se chhota hoga
     Isliye sqrt(n) tak check karna enough hai
   - Time Complexity: O(sqrt(n))
   - Space Complexity: O(1)

3) Most Optimized Approach
   - Intuition:
     Pehle 2 ko alag handle karo
     Baaki sirf odd numbers check karo
     Extra unnecessary checks avoid hote hain
   - Time Complexity: O(sqrt(n))
   - Space Complexity: O(1)

NOTE:
- Interview aur competitive programming ke liye Approach 2 aur 3 best hain
- Brute force sirf concept samajhne ke liye use hota hai
===========================================================
*/
