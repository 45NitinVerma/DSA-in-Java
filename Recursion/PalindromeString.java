// Check if string is palindrome 
public class PalindromeString {
        public static boolean isPalindrome(char[] s) {
            return palindromeHelper(s, 0);
        }
        private static boolean palindromeHelper(char[] s, int index) {
            int n = s.length;
            if (index >= n / 2) {
                return true;
            }
    
            // Check if characters at current index and its mirror index are the same
            if (s[index] != s[n - 1 - index]) {
                return false;  // If they are different, it's not a palindrome
            }
    
            // Recursive call to the next index
            return palindromeHelper(s, index + 1);
        }
        public static void main(String[] args) {
            char[] s = {'r', 'a', 'c', 'e', 'c', 'a', 'r'};
            if (isPalindrome(s)) {
                System.out.println("The string is a palindrome.");
            } else {
                System.out.println("The string is not a palindrome.");
            }
        }
    }
// TC: O(n), SC: O(n)