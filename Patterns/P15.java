import java.util.Scanner;
public class P15 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the value of n: ");
        int n = scanner.nextInt();
        for (char i = 'A'; i <= 'A'+n; i++) {
            for (char j = 'A'; j <= i; j++) {
                System.out.print(j+" ");
            }
            System.out.println();
        }
        scanner.close();
    }
}