import java.util.Scanner;

public class P22 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the value of n: ");
        int n = scanner.nextInt();
        int spaces = 2*n-2;
        for (int i = 1; i <= 2*n-1; i++) {
            int stars = i;
            if(i>n) stars=2*n-i;
            for (int j = 1; j <= stars; j++) {
                System.out.print("*");
            }
            for(int j=1; j<=spaces; j++){
                System.out.print(" ");
            }
            if(i<n) spaces -=2;
            else spaces +=2;
            for(int j = 1; j <= stars; j++){
                System.out.print("*");
            }
            System.out.println();
        }
        scanner.close();
    }
}