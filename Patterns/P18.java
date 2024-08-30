import java.util.Scanner;
public class P18 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the value of n: ");
        int n = scanner.nextInt();
        for (int i = 1; i <= n; i++) {
            //space
            for (int space=1; space<=n-i; space++) {
                System.out.print(" ");
            }
            //numbers
            for(int j=1; j<=i; j++){
                System.out.print(j);
            }
            for(int k=i-1; k>=1; k--){
                System.out.print(k);
            }
            System.out.println();
        }
        scanner.close();
    }
}