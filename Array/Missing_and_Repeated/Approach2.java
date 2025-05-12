package Array.Missing_and_Repeated;
/* 
# Problem: Missing and Repeated Numbers
You are given an array of size ‘N’. The elements of the array are in the range from 1 to ‘N’.

Ideally, the array should contain elements from 1 to ‘N’. But due to some miscalculations, there is a number R in the range [1, N] which appears in the array twice and another number M in the range [1, N] which is missing from the array.

Your task is to find the missing number (M) and the repeating number (R).

For example:
Consider an array of size six. The elements of the array are { 6, 4, 3, 5, 5, 1 }. 
The array should contain elements from one to six. Here, 2 is not present and 5 is occurring twice. Thus, 2 is the missing number (M) and 5 is the repeating number (R). 

## Intution:  Instead of counting the occurrences every time, using the hashing technique, we will store the frequency of each element between 1 to N. Now, the element with frequency 2 will be the repeating number and the element with frequency 0 will be the missing number.

Note: Here, we can solve this problem using a hash array.
*/
public class Approach2 {
    // Approach 2: Using Hashing
    public static int[] findMissingRepeatingNumbers(int[] a) {
        int n = a.length; // size of the array
        int[] hash = new int[n + 1]; // hash array

        //update the hash array:
        for (int i = 0; i < n; i++) {
            hash[a[i]]++;
        }

        //Find the repeating and missing number:
        int repeating = -1, missing = -1;
        for (int i = 1; i <= n; i++) {
            if (hash[i] == 2) repeating = i;
            else if (hash[i] == 0) missing = i;

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
1. The range of the number is 1 to N. So, we need a hash array of size N+1 (as we want to store the frequency of N as well).
2. We will iterate all the elements of the given array and update the hash array accordingly i.e. hash[a[i]] = hash[a[i]]+1.
3. Now, we will iterate on the hash array and return the two elements with frequencies 2 and 0.

## Time Complexity: O(N)
## Space Complexity:
    - Auxiliary Space: O(N)
    - Input Space: O(N)
 */