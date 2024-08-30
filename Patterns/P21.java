import java.util.Scanner;

public class P21 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the value of n: ");
        int n = scanner.nextInt();
        for (int i =1; i <= n; i++) {
            for (int j = 1; j <= n+1-i; j++) {
                System.out.print("*");
            }
            for (int space = 0; space <= 2*i-3; space++) {
                System.out.print(" ");
            }
            for (int j = 1; j <= n+1-i; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
        for (int i =1; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.print("*");
            }
            for (int space = 2*(n-i); space > 0; space--) {
                System.out.print(" ");
            }
            for (int j = 1; j <= i; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
        scanner.close();
    }
}