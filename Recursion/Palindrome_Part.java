import java.util.*;

public class Palindrome_Part {
    public static List<List<String>> partition(String s) {
        List<List<String>> res = new ArrayList<>();
        List<String> path = new ArrayList<>();
        partitionHelper(0, s, path, res);
        return res;
    }

    static void partitionHelper(int index, String s, List<String> path, List<List<String>> res) {
        // Base case: if we've reached the end of the string, add the current partition
        // to the result
        if (index == s.length()) {
            res.add(new ArrayList<>(path));
            return;
        }
        // Try all possible substrings starting from the current index
        for (int i = index; i < s.length(); ++i) {
            // If the current substring is a palindrome, add it to the path and recurse
            if (isPalindrome(s, index, i)) {
                path.add(s.substring(index, i + 1));
                partitionHelper(i + 1, s, path, res);
                path.remove(path.size() - 1);
            }
        }
    }

    static boolean isPalindrome(String s, int start, int end) {
        while (start <= end) {
            if (s.charAt(start++) != s.charAt(end--))
                return false;
        }
        return true;
    }

    public static void main(String args[]) {
        String s = "aabb";
        List<List<String>> ans = partition(s);
        System.out.println("The Palindromic partitions are :-");
        System.out.print(" [ ");
        for (int i = 0; i < ans.size(); i++) {
            System.out.print("[ ");
            for (int j = 0; j < ans.get(i).size(); j++) {
                System.out.print(ans.get(i).get(j) + " ");
            }
            System.out.print("] ");
        }
        System.out.print("]");

    }
}
/*
 * Time Complexity:
 * - The time complexity of the solution is O(n * 2^n), where n is the length of
 * the string.
 * - Here's why:
 * 1. There are 2^n possible partitions of the string because every character
 * can either start a new partition or continue the current one.
 * 2. For each partition, we check if every substring is a palindrome, which
 * takes O(n) time in the worst case.
 * 
 * Space Complexity:
 * - The space complexity is O(n), where n is the length of the string.
 * - This comes from:
 * 1. The space required for the recursion call stack, which can go as deep as
 * the length of the string (O(n)).
 * 2. The space used for storing the current path of substrings (which can also
 * be O(n)).
 * 3. The result list (`res`) can hold up to 2^n possible partitions, but this
 * space is not included in the recursive space complexity.
 */