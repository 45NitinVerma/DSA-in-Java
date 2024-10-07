// Approach 3: Recursive Approach
package Subsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
public class Approach3 {
     public static List<List<Integer>> Subsets(List<Integer> set) {
        List<List<Integer>> powerSet = new ArrayList<>();
        generateSubsets(set, 0, new ArrayList<>(), powerSet);
        return powerSet;
    }
    
    private static void generateSubsets(List<Integer> set, int index, List<Integer> current, List<List<Integer>> powerSet) {
        if (index == set.size()) {
            powerSet.add(new ArrayList<>(current));
            return;
        }
        // Don't include the current element
        generateSubsets(set, index + 1, current, powerSet);
        
        // Include the current element
        current.add(set.get(index));
        generateSubsets(set, index + 1, current, powerSet);
        current.remove(current.size() - 1);
    }
    public static void main(String[] args) {
        List<Integer> set = Arrays.asList(1, 2, 3);
        System.out.println("Power Set using Recursive method:");
        System.out.println(Subsets(set));
    }
}
// TC: O(n*2^n), SC: O(n*2^n)