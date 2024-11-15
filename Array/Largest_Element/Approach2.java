package Array.Largest_Element;
public class Approach2 {
    public static void main(String[] args) {
        int[] arr = {3,2,5,4,9};
        int n = arr.length;
        int largest = arr[0];
        for(int i=0; i<n; i++){
            if(arr[i]>largest) largest = arr[i];
        }
        System.err.println("Largest element is " + largest); 
    }
}
