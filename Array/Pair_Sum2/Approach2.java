package Array.Pair_Sum2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Approach2 {
    // Approach 3: Using Two Pointers
    public static List<int[]> pairSum2_TwoPointers(int[] arr, int n, int s) {
        int pivot = -1;
        int left, right=0;
        for(int i=0; i<n; i++){
            if(arr[i]<arr[i+1]){
                pivot = i;
                break;
            }
        }
        List<int[]> result = new ArrayList<>();
        left = (pivot + 1) % n;
        right = pivot;
        while (left != right) {
            int sum = arr[left] + arr[right];
            if (sum == s) {
                result.add(new int[]{arr[left], arr[right]});
                left = (left + 1) % n;
                right = (right - 1 + n) % n;
            } else if (sum < s) {
                left = (left + 1) % n;
            } else {
                right = (right - 1 + n) % n;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] arr = {11, 15, 6, 8, 9, 10};
        int target = 16;
        int n = arr.length;

        List<int[]> result = pairSum2_TwoPointers(arr, n, target);

        System.out.println("Pairs that sum to target:");
        for (int[] pair : result) {
            System.out.println(Arrays.toString(pair));
        }
    }
}
