// Print values from 1 to n
import java.util.Scanner;
public class OnetoN {
    public static void print(int n){
        if(n == 0) return;
        print(n-1);
        System.out.print(n+" ");
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter value of n: ");
        int n = scanner.nextInt();
        print(n);
        scanner.close();
    }
}
// TC: O(n), SC: O(n)
