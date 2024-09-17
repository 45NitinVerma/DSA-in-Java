// pow(a,b) funtion using recursion
public class PowFunction {
    // Recursive function to calculate a^b
    public static int power(int a, int b) {
        // Base case: any number raised to 0 is 1
        if (b == 0) return 1;
        // Recursive case: a^b = a * a^(b-1)
        return a * power(a, b - 1);
    }
    public static void main(String[] args) {
        int a = 4;
        int b = 5;
        System.out.println(a + " raised to the power " + b + " is: \n" + power(a, b));
    }
}
// TC: O(b), SC: O(b)