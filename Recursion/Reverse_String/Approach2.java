// Approach2: When input is provided as a character array
package Reverse_String;
    public class Approach2 {
        public static void reverseString(char[] s) {
            reverseHelper(s, 0, s.length - 1);
        }
        private static void reverseHelper(char[] s, int left, int right) {
            // Base case
            if (left >= right) {
                return;
            }
    
            // Swap the characters at the left and right indices
            char temp = s[left];
            s[left] = s[right];
            s[right] = temp;
    
            // Recursive call to the next pair of characters
            reverseHelper(s, left + 1, right - 1);
        }
        public static void main(String[] args) {
            char[] s = {'H', 'e', 'l', 'l', 'o'};
            System.out.println("Original String: " + new String(s));
            reverseString(s);
            System.out.println("Reversed String: " + new String(s));
        }
    }
/* 
TC: O(n), SC: O(n)
Breakdown:
- n/2 recursive calls, where n is the length of the char array
- Each call performs a constant time operation (swapping)
- Total number of operations is proportional to n
- This simplifies to O(n)
*/
