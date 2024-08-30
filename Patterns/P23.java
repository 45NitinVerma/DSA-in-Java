import java.util.Scanner;

public class P23 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the value of n: ");
        int n = scanner.nextInt();
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if(i==1 || j==1 || i==n || j==n) 
                    System.out.print("*");
                    else System.out.print(" ");
            }
            System.out.println();
        }
        scanner.close();
    }
}