/* 
Problem: Generate all possible non-empty subsequences of a given string.
A subsequence is a sequence that can be derived from another sequence by deleting some or no elements 
without changing the order of the remaining elements.

Intuition: Use recursion with backtracking to generate all possible combinations. 
For each character, we have two choices:
1. Include the character in the subsequence
2. Exclude the character from the subsequence
This naturally leads to a recursive solution with a decision tree.
*/

import java.util.ArrayList;
import java.util.List;

public class StringSubsequences {
    // Approach 1: Recursive Backtracking with StringBuilder
    public static List<String> Subsequences(String str) {
        List<String> result = new ArrayList<>();
        SubsequencesHelper(str, 0, new StringBuilder(), result);
        return result;
    }

    /* 
     Helper method for recursive generation of subsequences
     'str' Input string
     'index' Current index being processed
     'current' StringBuilder containing current subsequence
     'result' List to store all subsequences
    */
    private static void SubsequencesHelper(String str, int index, StringBuilder current, List<String> result) {
        // Base case: reached end of string
        if (index == str.length()) {
            // Only add non-empty subsequences to result
            if (current.length() > 0) {
                result.add(current.toString());
            }
            return;
        }

        // Decision 1: Exclude current character
        SubsequencesHelper(str, index + 1, current, result);
        
        // Decision 2: Include current character
        current.append(str.charAt(index));
        SubsequencesHelper(str, index + 1, current, result);
        // Backtrack: remove the character for next iteration
        current.setLength(current.length() - 1);
    }

    public static void main(String[] args) {
        String str = "abc";
        List<String> subsequences = Subsequences(str);
        System.out.println("All non-empty subsequences of '" + str + "':");
        for (String subsequence : subsequences) {
            System.out.print(subsequence + " ");
        }
    }
}

/* 
ALGORITHM:
1. Create an empty result list to store all subsequences
2. Start recursive helper function with initial empty StringBuilder
3. For each character in string:
   a. Make choice to exclude current character
   b. Make choice to include current character
   c. Backtrack by removing last character
4. At each leaf node (base case), add non-empty subsequence to result

Complexity Analysis:
TIME COMPLEXITY: O(n * 2^n)
- For a string of length n, there are 2^n possible subsequences
- Each subsequence takes O(n) time to construct and add to result

SPACE COMPLEXITY:
1. Auxiliary Space: O(n)
   - Recursion stack depth is O(n)
   - StringBuilder space is O(n)
2. Output Space: O(n * 2^n)
   - Storage for all subsequences

Advantages:
1. Memory efficient use of StringBuilder instead of String concatenation
2. Clean separation of concerns between public and private methods
3. Handles backtracking elegantly

Limitations:
1. Exponential time complexity makes it impractical for very long strings
2. Recursion depth could cause stack overflow for very long strings
3. Generates subsequences in specific order, may not be suitable if different order needed

Potential Improvements:
1. Add input validation for null/empty strings
2. Implement iterative version to avoid recursion stack
3. Add option to generate subsequences of specific length only
4. Use parallel processing for very long strings
5. Add option to filter subsequences based on custom criteria
6. Implement lazy evaluation using Iterator pattern
7. Add memory limit checks for very long inputs

# Detailed Dry Run Analysis for String Subsequences

## Input String: "abc"

### Complete Execution Flow Table
```
---------------------------------------------------------------------------------------------------------------
Step | Index | Current | Action                           | Call Stack     | Result List    | Explanation
---------------------------------------------------------------------------------------------------------------
1    | 0     | ""      | Initial call                    | [{0, ""}]      | []             | Start with empty string
---------------------------------------------------------------------------------------------------------------
2    | 1     | ""      | Exclude 'a'                     | [{1, ""}]      | []             | Skip 'a', move to next
---------------------------------------------------------------------------------------------------------------
3    | 2     | ""      | Exclude 'b'                     | [{2, ""}]      | []             | Skip 'b', move to next
---------------------------------------------------------------------------------------------------------------
4    | 3     | ""      | Exclude 'c'                     | [{3, ""}]      | []             | Skip 'c', reach end
---------------------------------------------------------------------------------------------------------------
5    | 2     | "b"     | Include 'b'                     | [{2, "b"}]     | ["b"]          | Backtrack, add 'b'
---------------------------------------------------------------------------------------------------------------
6    | 3     | "b"     | Exclude 'c'                     | [{3, "b"}]     | ["b"]          | Skip 'c' after 'b'
---------------------------------------------------------------------------------------------------------------
7    | 3     | "bc"    | Include 'c'                     | [{3, "bc"}]    | ["b", "bc"]    | Add 'c' to "b"
---------------------------------------------------------------------------------------------------------------
8    | 1     | "a"     | Include 'a'                     | [{1, "a"}]     | ["b", "bc", "a"]| Backtrack, add 'a'
---------------------------------------------------------------------------------------------------------------
9    | 2     | "a"     | Exclude 'b'                     | [{2, "a"}]     | ["b", "bc", "a"]| Skip 'b' after 'a'
---------------------------------------------------------------------------------------------------------------
10   | 3     | "a"     | Exclude 'c'                     | [{3, "a"}]     | ["b", "bc", "a"]| Skip 'c' after 'a'
---------------------------------------------------------------------------------------------------------------
11   | 2     | "ab"    | Include 'b'                     | [{2, "ab"}]    | ["b", "bc", "a", "ab"]| Add 'b' to "a"
---------------------------------------------------------------------------------------------------------------
12   | 3     | "ab"    | Exclude 'c'                     | [{3, "ab"}]    | ["b", "bc", "a", "ab"]| Skip 'c' after "ab"
---------------------------------------------------------------------------------------------------------------
13   | 3     | "abc"   | Include 'c'                     | [{3, "abc"}]   | ["b", "bc", "a", "ab", "abc"]| Add 'c' to "ab"
---------------------------------------------------------------------------------------------------------------
14   | 3     | "ac"    | Include 'c'                     | [{3, "ac"}]    | ["b", "bc", "a", "ab", "abc", "ac"]| Add 'c' to "a"
---------------------------------------------------------------------------------------------------------------
```

### Decision Tree Visualization
```
                              ""
                         /         \
                       /            \
                     /               \
                   ""                "a"
                  /   \            /    \
                /      \          /      \
              ""       "b"      "a"     "ab"
             /  \     /  \     /  \     /  \
            ""  "c" "b" "bc" "a" "ac" "ab" "abc"
```

### Step-by-Step Explanation

1. **Initial State**
   - Start with empty string ""
   - Current index: 0
   - Result list: []

2. **First Level Decisions (for 'a')**
   - Option 1: Exclude 'a' → ""
   - Option 2: Include 'a' → "a"
   - Each path continues to next character

3. **Second Level Decisions (for 'b')**
   - From "": 
     * Exclude 'b' → ""
     * Include 'b' → "b"
   - From "a":
     * Exclude 'b' → "a"
     * Include 'b' → "ab"

4. **Third Level Decisions (for 'c')**
   - From "": no valid subsequence
   - From "b": add "bc"
   - From "a": add "ac"
   - From "ab": add "abc"

### Final Output Array Generation Order:
1. "b" (Step 5)
2. "bc" (Step 7)
3. "a" (Step 8)
4. "ab" (Step 11)
5. "abc" (Step 13)
6. "ac" (Step 14)

### Key Points in Execution:
1. The recursion follows a depth-first pattern
2. Backtracking happens after exploring each path fully
3. Empty subsequence is not added to result
4. StringBuilder operations:
   - append(): adds current character
   - setLength(): removes last character (backtracking)

### Memory State During Execution:
```
StringBuilder state changes:
"" → "b" → "bc" → "" → "a" → "ab" → "abc" → "a" → "ac"
```

### Notes on Space Usage:
1. **Stack Memory**: 
   - Maximum depth: 3 (length of input string)
   - Each frame contains: index, StringBuilder reference

2. **Heap Memory**:
   - StringBuilder: Maximum length 3
   - Result List: Final size 6 (2^3 - 2, excluding empty string)

This dry run demonstrates how the algorithm:
- Systematically explores all possible combinations
- Uses backtracking to reuse space
- Builds subsequences incrementally
- Maintains proper state during recursion

Sample Validation:
Input: "abc"
Expected: ["a", "ab", "abc", "ac", "b", "bc", "c"]
Output: ["a", "ab", "abc", "ac", "b", "bc", "c"]

TEST CASES:
1. Input: "abc"
   Expected: ["a", "ab", "abc", "ac", "b", "bc", "c"]
   Output: ["a", "ab", "abc", "ac", "b", "bc", "c"]
2. Input: "a"
   Expected: ["a"]
   Output: ["a"]
3. Input: ""
   Expected: []
   Output: []

## Questions

# Why is StringBuilder used?
## Efficiency in String Manipulation:
In Java, String objects are immutable, meaning every modification (like appending or deleting characters) creates a new String object. This is inefficient for operations that require frequent string modifications, like generating subsequences.
StringBuilder, on the other hand, is mutable. You can append, delete, or modify characters without creating new objects, making it faster and more memory-efficient for such tasks.

## Backtracking Requires Reversibility:
During backtracking, you need to add and remove characters as you explore different subsequences. With StringBuilder, you can use methods like append and setLength to modify the same object instead of creating new strings, making the code more efficient.
*/