package Binary_Search.Find_Nth_Root;
/* 
Find Nth Root of M
# Problem: You are given two positive integers 'n' and 'm'. You have to return the 'nth' root of 'm', i.e. 'm(1/n)'. If the 'nth root is not an integer, return -1.

Note:
'nth' root of an integer 'm' is a number, which, when raised to the power 'n', gives 'm' as a result.

## Intuition: We can guarantee that our answer will lie between the range from 1 to m i.e. the given number. So, we will perform a linear search on this range and we will find the number x, such that
func(x, n) = m. If no such number exists, we will return -1.

Note: func(x, n) returns the value of x raised to the power n i.e. xn.
 */
public class Approach1 {
    // Approach 1: Using Linear Search
    // Power Exponential Method
    public static long func(int b, int exp) {
        long  ans = 1;
        long base = b;
        while (exp > 0) {
            if (exp % 2 == 1) {
                exp--;
                ans = ans * base;
            } else {
                exp /= 2;
                base = base * base;
            }
        }
        return ans;
    }

    public static int NthRoot(int n, int m) {
        //Use linear search on the answer space:
        for (int i = 1; i <= m; i++) {
            long val = func(i, n);
            if (val == (long)m) return i;
            else if (val > (long)m) break;
        }
        return -1;
    }

    public static void main(String[] args) {
        int n = 3, m = 27;
        int ans = NthRoot(n, m);
        System.out.println("The answer is: " + ans);
    }
}
/* 
## Algorithm:
1. We will first run a loop(say i) from 1 to m.
2. Inside the loop we will check the following:
 - If func(x, n) == m: This means x is the number we are looking for. So, we will return x from this step.
 - If func(x, n) > m: This means we have got a bigger number than our answer and until now we have not found any number that can be our answer. In this case, our answer does not exist and we will break out from this step and return -1.

## Complexity Analysis:
1. Time Complexity: O(m * log(n)), where m is the given number and n is the exponent.
2. Space Complexity: O(1), as we are using only a constant amount of space.
 */