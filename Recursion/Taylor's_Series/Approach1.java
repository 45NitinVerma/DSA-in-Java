// Approach1: Taylor's Series using Recursion
public class Approach1 {
    //Calculate terms of Taylor Series
    public static double taylorTerm(int x, int n){
        if(n==0) return 1;
        return (taylorTerm(x, n-1) + (Math.pow(x,n)/fact(n)));
    }
    //Helper function to calculate factorial
    public static int fact(int n){
        if(n==0 || n==1) return 1;
        return n*fact(n-1);
    }
    public static void main(String[] args) {
        int x = 2; // e^2
        int terms = 10;
        double result = taylorTerm(x, terms-1);
        System.out.println("e^" + x + " = " + result);
        System.out.println("Actual value of e^" + x + " = " + Math.exp(x));
    }
}
// TC: O(n^2), SC: O(n)