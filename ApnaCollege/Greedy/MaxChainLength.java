package ApnaCollege.Greedy;
/* 
Maximum Chain Length of Pairs
# Problem: You are given an array of n pairs pairs where pairs[i] = [lefti, righti] and lefti < righti.

A pair p2 = [c, d] follows a pair p1 = [a, b] if b < c. A chain of pairs can be formed in this fashion.

Return the length longest chain which can be formed.

You do not need to use up all the given intervals. You can select pairs in any order.
 */
import java.util.Arrays;
import java.util.Comparator;

public class MaxChainLength {
    public static void main(String args[]) {
        int pairs[][] = {{5, 24}, {39, 60}, {5, 28}, {27, 40}, {50, 90}};

        Arrays.sort(pairs, Comparator.comparingDouble(o -> o[1]));

        int maxLen = 1;
        int prevEnd = pairs[0][1];
        for(int i=1; i<pairs.length; i++) {
            if(pairs[i][0] > prevEnd) {
                maxLen++;
                prevEnd = pairs[i][1];
            }
        }

        System.out.println("max chain length = "+ maxLen);
    }
}