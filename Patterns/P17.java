import java.util.Scanner;
public class P17 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the value of n: ");
        int n = scanner.nextInt();
        for (int i = 1; i <= n; i++) {
            for (char j = 'A'; j <= 'A'+n-i; j++) {
                System.out.print(j+" ");
            }
            System.out.println();
        }
        scanner.close();
    }
}