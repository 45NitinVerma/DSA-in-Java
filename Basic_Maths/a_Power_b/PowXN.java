// Approach2: Fast exponentiation
package Basic_Maths.a_Power_b;
/*
 Problem: Pow(x, n)
 Given a double x and an integer n, calculate x^n (x raised to the power n).

 Approach: Fast Exponentiation (Binary Exponentiation)
 Intuition:
 - Normal multiplication n times is slow (O(n)).
 - Yahan hum power ko half-half karke calculate karte hain.
 - Agar n even hai: x^n = (x^(n/2))^2
 - Agar n odd hai: x^n = x * (x^(n/2))^2
 - Negative power ke liye: x^-n = 1 / (x^n)

 Ye approach fast hai aur recursion/iteration dono se ho sakti hai.
*/

public class PowXN {

    // Main function jo Java program run hone par execute hota hai
    public static void main(String[] args) {
        PowXN solution = new PowXN();

        double x = 2.0;
        int n = -3;

        double result = solution.myPow(x, n);
        System.out.println("Result: " + result);
    }

    // Function to calculate x^n
    public double myPow(double x, int n) {
        // Edge case: power 0 ho to answer hamesha 1 hota hai
        if (n == 0) return 1.0;

        // long isliye use kiya kyunki n = Integer.MIN_VALUE ho sakta hai
        long power = n;

        // Agar power negative hai to x ko invert karo
        if (power < 0) {
            x = 1 / x;
            power = -power;
        }

        double result = 1.0;

        // Binary Exponentiation (Iterative)
        while (power > 0) {
            // Agar power odd hai, result me current x multiply karo
            if ((power & 1) == 1) {
                result = result * x;
            }

            // x ko square karo
            x = x * x;

            // power ko half karo
            power = power >> 1;
        }

        return result;
    }
}

/*
 =========================
 Time Complexity:
 - O(log n)
 Kyunki har step me power ko half kar rahe hain.

 Space Complexity:
 - O(1)
 Sirf kuch variables use ho rahe hain, extra memory nahi.

 =========================
 Intuition (Simple Words):
 - Power ko binary form me tod do.
 - Jab bhi bit 1 mile, result me multiply karo.
 - Har step me base ka square banao aur exponent ko half karo.
 - Isse problem fast solve hoti hai even for very large n.
*/

// TC: O(log b), SC: O(1)