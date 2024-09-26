// Approach1: Using binary representation
package Power_Set;
import java.util.*;
public class Approach1 {
    public static List<List<Integer>> PowerSetBinary(List<Integer> set) {
        List<List<Integer>> powerSet = new ArrayList<>();
        int n = set.size();
        int powerSetSize = 1 << n; // 2^n
        for (int i = 0; i < powerSetSize; i++) {
            List<Integer> subset = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                if ((i & (1 << j)) > 0) {
                    subset.add(set.get(j));
                }
            }
            powerSet.add(subset);
        }
        return powerSet;
    }
    public static void main(String[] args) {
        List<Integer> set = Arrays.asList(1, 2, 3);

        System.out.println("Power Set using Binary method:");
        System.out.println(PowerSetBinary(set));
    }
}
// TC: O(n*2^n), SC: O(n*2^n)