package Array.Missing_and_Repeated;
/* 
# Problem: Missing and Repeated Numbers
You are given an array of size ‘N’. The elements of the array are in the range from 1 to ‘N’.

Ideally, the array should contain elements from 1 to ‘N’. But due to some miscalculations, there is a number R in the range [1, N] which appears in the array twice and another number M in the range [1, N] which is missing from the array.

Your task is to find the missing number (M) and the repeating number (R).

For example:
Consider an array of size six. The elements of the array are { 6, 4, 3, 5, 5, 1 }. 
The array should contain elements from one to six. Here, 2 is not present and 5 is occurring twice. Thus, 2 is the missing number (M) and 5 is the repeating number (R). 

## Intution:  For each number between 1 to N, we will try to count the occurrence in the given array using linear search. And the element with occurrence as 2 will be the repeating number and the number with 0 occurrences will be the missing number.
*/

public class Approach1 {
    // Approach 1: Using Linear Search
    public static int[] findMissingRepeatingNumbers(int[] a) {
        int n = a.length; // size of the array
        int repeating = -1, missing = -1;

        //Find the repeating and missing number:
        for (int i = 1; i <= n; i++) {
            //Count the occurrences:
            int cnt = 0;
            for (int j = 0; j < n; j++) {
                if (a[j] == i) cnt++;
            }

            if (cnt == 2) repeating = i;
            else if (cnt == 0) missing = i;

            if (repeating != -1 && missing != -1)
                break;
        }
        int[] ans = {repeating, missing};
        return ans;
    }

    public static void main(String[] args) {
        int[] a = {3, 1, 2, 5, 4, 6, 7, 5};
        int[] ans = findMissingRepeatingNumbers(a);
        System.out.println("The repeating and missing numbers are: {"
                           + ans[0] + ", " + ans[1] + "}");
    }
}
/* 
# Algorithm:
1. We will run a loop(say i) from 1 to N.
2. For each integer, i, we will count its occurrence in the given array using linear search.
3. We will store those two elements that have the occurrence of 2 and 0.
4. Finally, we will return the elements.

## Time Complexity: O(N^2)
## Space Complexity: 
 - Auxiliary Space: O(1)
 - Input Space: O(N)
 */