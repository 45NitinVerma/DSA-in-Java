package Array.Pascal_Triangle.Variant3;
import java.util.ArrayList;
import java.util.List;

/* 
# Problem: Given the number of rows n. Print the first n rows of Pascal’s triangle.

## Naive Approach:
### Intuition: The naive approach is basically a combination of variation 1 and variation 2. Here, for every row from 1 to n, we will try to generate all the row elements by simply using the naive approach of variation 2. So, we will use the same code as variation 2(naive approach), inside a loop (i.e. row runs from 1 to n).
*/
public class Approach1 {
    // Naive approach
    public static int nCr(int n, int r) {
        long res = 1;
        // calculating nCr:
        for (int i = 0; i < r; i++) {
            res = res * (n - i);
            res = res / (i + 1);
        }
        return (int) res;
    }

    public static List<List<Integer>> pascalTriangle(int n) {
        List<List<Integer>> ans = new ArrayList<>();

        // Store the entire Pascal's triangle:
        for (int row = 1; row <= n; row++) {
            List<Integer> tempLst = new ArrayList<>(); // temporary list
            for (int col = 1; col <= row; col++) {
                tempLst.add(nCr(row - 1, col - 1));
            }
            ans.add(tempLst);
        }
        return ans;
    }
    
        public static void main(String[] args) {
            int n = 5;
            List<List<Integer>> ans = pascalTriangle(n);
            for (List<Integer> it : ans) {
                for (int ele : it) {
                    System.out.print(ele + " ");
                }
                System.out.println();
            }
        }
    
}
/* 
## Algorithm:
1. First, we will run a loop(say row) from 1 to n.
2. We will use a loop(say col) to iterate over each column i.e. from 1 to n. And inside the nested loops, we will do the following steps:
3. First, we will consider row-1 as n and col-1 as r.
4. After that, we will simply calculate the value of the combination using a loop. 
5. The loop will run from 0 to r. And in each iteration, we will multiply (n-i) with the result and divide the result by (i+1).
6. Finally, we will add the element to a temporary list.
7. After calculating all the elements for all columns of a row, we will add the temporary list to our final answer list.
8. Finally, we will return the answer list.

## Time Complexity: O(n*n*r) ~ O(n3), where n = number of rows, and r = column index.

## Space Complexity: In this case, we are only using space to store the answer. That is why space complexity can be still considered as O(1).
 */