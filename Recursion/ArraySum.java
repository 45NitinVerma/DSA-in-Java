// Print sum of elements of an array
public class ArraySum {
    public static int arraySum(int[] arr, int n){
        if(n<=1) return n==0?0:arr[0]; // Base Case
        return arr[n-1]+arraySum(arr, n-1);
    }
    public static void main(String[] args) {
        int[] arr={1,2,3,4,5};
        System.out.println(arraySum(arr, arr.length));
    }
}
// TC: O(n), SC: O(n)