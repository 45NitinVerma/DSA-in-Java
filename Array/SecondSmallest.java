package Array;
public class SecondSmallest {
    public static int findSecondSmallest(int[] arr) {
        // Check if array is null or has less than 2 elements
        if (arr == null || arr.length < 2) {
            return -1;
        }
        
        int smallest = Integer.MAX_VALUE;
        int secondSmallest = Integer.MAX_VALUE;
        
        // Single pass to find both smallest and second smallest
        for (int num : arr) {
            if (num < smallest) {
                secondSmallest = smallest;
                smallest = num;
            } else if (num < secondSmallest && num != smallest) {
                secondSmallest = num;
            }
        }
        
        // Check if second smallest was found
        return secondSmallest == Integer.MAX_VALUE ? -1 : secondSmallest;
    }
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 7, 7, 5};
        int secondSmallest = findSecondSmallest(arr);
        if (secondSmallest != -1) {
            System.out.println("Second smallest element (Optimal): " + secondSmallest);
        }
    }
}
// Time Complexity: O(n)
// Space Complexity: O(1)