// Approach3: Heap's Algorithm
import java.util.ArrayList;
import java.util.List;
public class Approach3 {
    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        heapPermutation(nums, nums.length, result);
        return result;
    }

    private static void heapPermutation(int[] nums, int size, List<List<Integer>> result) {
        if (size == 1) {
            List<Integer> perm = new ArrayList<>();
            for (int num : nums) {
                perm.add(num);
            }
            result.add(perm);
            return;
        }

        for (int i = 0; i < size; i++) {
            heapPermutation(nums, size - 1, result);

            if (size % 2 == 1) {
                int temp = nums[0];
                nums[0] = nums[size - 1];
                nums[size - 1] = temp;
            } else {
                int temp = nums[i];
                nums[i] = nums[size - 1];
                nums[size - 1] = temp;
            }
        }
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        List<List<Integer>> permutations = permute(nums);
        for (List<Integer> perm : permutations) {
            System.out.println(perm);
        }
    }
}
