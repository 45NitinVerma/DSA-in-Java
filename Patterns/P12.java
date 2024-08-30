import java.util.Scanner;
public class P12 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the value of n: ");
        int n = scanner.nextInt();
        for (int i = 1; i <= 2*n-1; i++) {
            if(i<=n){
                for (int stars = 1; stars <= i; stars++) {
                    System.out.print("*");
                }
            } else{
                for (int stars = 1; stars <= 2*n-i; stars++) {
                    System.err.print("*");
                }
            }
            System.out.println();
        }
        scanner.close();
    }
}