/* M1 - Finding GCD (Brute force approach) */
package GCD_or_HCF;
public class Method1 {
    public static void main(String[] args) {
        int num1=20, num2=40;
        for (int i = Math.min(num1,num2); i>=1; i--) {
            if(num1%i == 0 && num2%i == 0){
                System.out.println("GCD is: "+i);
                break;
            }
        }
    }
}
// TC - O(min(num1, num2))
// SC - O(1)