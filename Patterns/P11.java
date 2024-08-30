import java.util.Scanner;
public class P11 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the value of n: ");
        int n = scanner.nextInt();
        for (int i = 1; i <= n; i++) {
            for (int space = 1; space  < i ; space++) {
               System.out.print(" ");
            }
            for (int stars = 1; stars <= (2*n)-(2*i-1) ; stars++) {
                System.out.print("*");
            }
            System.out.println();
        }
        scanner.close();
    }
}