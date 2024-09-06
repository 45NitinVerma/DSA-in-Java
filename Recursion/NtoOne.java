// Print values from n to 1
import java.util.Scanner;
public class NtoOne {
    public static void print(int n){
        if(n == 0) return;
        System.out.print(n+" ");
        print(n-1);
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
