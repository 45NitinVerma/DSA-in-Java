import java.util.Scanner;
public class P13 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the value of n: ");
        int n = scanner.nextInt();
        int start = 1;
        for (int i = 1; i <= n; i++) {
            if (i%2 == 0) {
                start = 0;
            }else start = 1;
            for (int j = 1; j <= i; j++) {
                System.out.print(start+" ");
                start = 1-start;
            }
            System.out.println();
        }
        scanner.close();
    }
}