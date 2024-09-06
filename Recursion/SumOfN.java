// Print sum of n terms
import java.util.Scanner;
public class SumOfN {
    public static int sum(int n){
        if(n == 0) return 0;
        int ans = n+sum(n-1);
        return ans;
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter value of n: ");
        int n = scanner.nextInt();
        System.out.println("Sum is: "+sum(n));
        scanner.close();
    }
}
// TC: O(n), SC: O(n)