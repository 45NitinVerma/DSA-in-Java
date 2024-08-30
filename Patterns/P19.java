import java.util.Scanner;
public class P19 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the value of n: ");
        int n = scanner.nextInt();
        for (int i = 1; i <= n; i++) {
            //space
            for (int space=1; space<=n-i; space++) {
                System.out.print(" ");
            }
            //characters
            char ch = 'A';
            for (int j = 1; j <= (2*i-1); j++) {
                System.out.print(ch);
                if (j>(2*i-1)/2) ch--;
                else ch++;
            }
            System.out.println();
        }
        scanner.close();
    }
}