// Approach2: Fast exponentiation
package Basic_Maths.a_Power_b;
public class Approach2 {
    public static int fastExponentiation(int a, int b){
        int res = 1;
        while(b>0){
            if((b&1) == 1) //if b is odd
                res = res * a;
            a = a*a;
            b = b>>1; //divide by 2
        }
        return res;
    }
    public static void main(String[] args) {
        int a = 2, b = 5;
        System.out.println("Answer: " + fastExponentiation(a, b));
    }
}
// TC: O(log b), SC: O(1)