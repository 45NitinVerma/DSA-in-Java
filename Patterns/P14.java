import java.util.Scanner;
public class P14 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the value of n: ");
        int n = scanner.nextInt();
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.print(j);
            }
            for (int space = 1; space <= 2*(n-i); space++) {
                System.out.print(" ");
            }
            for (int j = i; j >= 1; j--){
                System.out.print(j);
            }
            System.out.println();
        }
        scanner.close();
    }
}