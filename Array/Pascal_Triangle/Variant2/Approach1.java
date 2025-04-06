package Array.Pascal_Triangle.Variant2;
/* 
# Problem: Given the row number n. Print the n-th row of Pascal’s triangle.
## Intution: Our first observation regarding Pascal’s triangle should be that the n-th row of the triangle has exactly n elements. With this observation, we will proceed to solve this problem.

## Naive Approach: 
In this approach, for every column from 1 to n, we will calculate the element (n, c)(where n is the given row number and c is the column number that will vary from 1 to n) using the previous method. Thus, we will print the row.   

### Approach: The steps are as follows:
1. We will use a loop(say c) to iterate over each column i.e. from 1 to n. And for each column, we will do the following steps:
2. First, we will consider n-1 as n and c-1 as r.
3. After that, we will simply calculate the value of the combination using a loop. 
4. The loop will run from 0 to r. And in each iteration, we will multiply (n-i) with the result and divide the result by (i+1).
5. Finally, we will print the element.
6. Finally, the entire row will be printed.
*/

public class Approach1 {
    // Naive Approach
    // Function to calculate the combination value C(n,r)
    private static long nCr(int n, int r) {
        long result = 1;
        
        // Calculate C(n,r) using the formula
        // C(n,r) = n!/(r! * (n-r)!)
        for (int i = 0; i < r; i++) {
            result = result * (n - i);
            result = result / (i + 1);
        }
        
        return result;
    }
    
    // Function to print nth row of Pascal's Triangle
    public static void printPascalRow(int n) {
        // Row numbers start from 1
        // For each column position
        for (int c = 1; c <= n; c++) {
            // Calculate element at position (n-1)C(c-1)
            long element = nCr(n - 1, c - 1);
            System.out.print(element + " ");
        }
        System.out.println();
    }
    
    // Main method for testing
    public static void main(String[] args) {
        int n = 5; // Generate 5th row
        System.out.println("Generating row " + n + " of Pascal's Triangle:");
        printPascalRow(n);
        
        // Additional test cases
        System.out.println("\nTest cases:");
        for (int i = 1; i <= 6; i++) {
            System.out.print("Row " + i + ": ");
            printPascalRow(i);
        }
    }
}

// ## Time Complexity: O(n*r)

