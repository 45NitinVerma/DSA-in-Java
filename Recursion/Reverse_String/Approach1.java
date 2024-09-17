// Approach1: When input is provided as a String
package Reverse_String;
public class Approach1 {
    public static String revString(String str){
        if(str.isEmpty() || str.length()==1)
            return str;
        return revString(str.substring(1))+str.charAt(0);
    }
    public static void main(String[] args) {
        String original = "Hello, World!";
        String reversed = revString(original);
        System.out.println("Original string: " + original);
        System.out.println("Reversed string: " + reversed);
    }
}
/*
TC: O(n^2), SC: O(n)

Breakdown:
- n recursive calls, where n is the length of the string
- Each call performs string concatenation, which is O(k) where k is the current substring length
- The sum of all operations: n + (n-1) + (n-2) + ... + 2 + 1 = n(n+1)/2
- This simplifies to O(n^2)
*/