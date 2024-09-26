import java.util.ArrayList;
import java.util.List;
public class StringSubsequences {
    public static List<String> Subsequences(String str) {
        List<String> result = new ArrayList<>();
        SubsequencesHelper(str, 0, new StringBuilder(), result);
        return result;
    }
    private static void SubsequencesHelper(String str, int index, StringBuilder current, List<String> result) {
        // Base case
        if (index == str.length()) {
            if (current.length() > 0) 
            { result.add(current.toString());}
            return;
        }
        // Exclude the current character (backtrack)
        SubsequencesHelper(str, index + 1, current, result);
        
        // Include the current character
        current.append(str.charAt(index));
        SubsequencesHelper(str, index + 1, current, result);
        current.setLength(current.length() - 1);

    }
    public static void main(String[] args) {
        String str = "abc";
        List<String> subsequences = Subsequences(str);
        System.out.println("All non-empty subsequences of '" + str + "':");
        for (String subsequence : subsequences) {
            System.out.print(subsequence+" ");
        }
    }
}
// TC: O(n*2^n), SC: O(n*2^n)