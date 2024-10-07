// Approach2: Using iterative approach
package Subsets;
import java.util.*;
public class Approach2 {
    public static List<List<Integer>> PowerSetIterative(List<Integer> set) {
        List<List<Integer>> powerSet = new ArrayList<>();
        powerSet.add(new ArrayList<>()); // Add empty set
        for (Integer item : set) {
            int size = powerSet.size();
            for (int i = 0; i < size; i++) {
                List<Integer> newSubset = new ArrayList<>(powerSet.get(i));
                newSubset.add(item);
                powerSet.add(newSubset);
            }
        }
        return powerSet;
    }
    public static void main(String[] args) {
        List<Integer> set = Arrays.asList(1, 2, 3);
        System.out.println("Power Set using Iterative method:");
        System.out.println(PowerSetIterative(set));
    }
}
// TC: O(n*2^n), SC: O(n*2^n)