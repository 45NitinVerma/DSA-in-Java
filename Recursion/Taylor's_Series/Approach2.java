// Approach2: Horner's Method for Taylor's Series
public class Approach2 {
    static double s = 1;
    public static double taylorHorner(int x, int n) {
        if (n == 0)
            return s;
        s = 1 + x * s / n;
        return taylorHorner(x, n - 1);
    }
    
    public static void main(String[] args) {
        int x = 2;  // Calculate e^2
        int terms = 10;
        double result = taylorHorner(x, terms - 1);
        System.out.println("e^" + x + " = " + result);
        System.out.println("Actual value of e^" + x + " = " + Math.exp(x));
    }
}
// TC: O(n), SC: O(n)