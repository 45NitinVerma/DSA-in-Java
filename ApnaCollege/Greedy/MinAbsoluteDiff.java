package ApnaCollege.Greedy;
import java.util.Arrays;
/* 
Minimum Absolute Difference of Pairs
# Problem: Given two arrays of integers A and B of size N, the task is to find the minimum absolute difference of pairs formed by taking one element from A and one element from B.
 */
public class MinAbsoluteDiff {
    public static void main(String args[]) {
        int A[] = {4, 1, 8, 7};
        int B[] = {2, 3, 6, 5};

        Arrays.sort(A);
        Arrays.sort(B);
        
        int sum = 0;
        for(int i=0; i<A.length; i++) {
            sum += Math.abs(A[i]-B[i]);
        }

        System.out.println("min absolute diff of pairs = " + sum);
    }
}