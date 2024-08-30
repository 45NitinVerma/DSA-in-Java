import java.util.Scanner;

public class P20 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the value of n: ");
        int n = scanner.nextInt();
        for (int i = 0; i < n; i++) {
            for (char j = (char) ('A' + (n - i-1)); j <= 'A' + (n - 1); j++) {
                System.out.print(j);
            }
            System.out.println();
        }
        scanner.close();
    }
}