// Approach2: Recursive method to print factorial of a number 
package Fact_of_num;
import java.util.Scanner;
public class Approach2 {
    public static long fact(int n){
        if(n==0 || n==1) return 1;
        return n*fact(n-1);
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the value of n: ");
        int n = scanner.nextInt();
        System.out.println("Factorial of "+n+" is: "+fact(n));
        scanner.close();
    }
}
// TC: O(n), SC: O(n)