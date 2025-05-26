package Array.Max_Product_Subarray;
/* 
# Problem: Maximum Product Subarray
Given an integer array nums, find a subarray that has the largest product, and return the product.

## Intution: Using 2 nested loops to find the maximum product of all subarrays.
 */
public class Approach2 {
    // Approach 2: Using 2 nested loops to find the maximum product of all subarrays.
    static int maxProductSubArray(int arr[]) {
	    int result = arr[0];
	    for(int i=0;i<arr.length-1;i++) {
	        int p = arr[i];
	        for(int j=i+1;j<arr.length;j++) {
	            result = Math.max(result,p);
	            p *= arr[j];
	        }
	        result = Math.max(result,p);
	    }
	   return result;     
	}
	public static void main(String[] args) {
		int nums[] = {1,2,-3,0,-4,-5};
		int answer = maxProductSubArray(nums);
		System.out.print("The maximum product subarray is: "+answer);
	}
}
/* 
## Algorithm:
 1. Run a loop to find the start of the subarrays.
 2. Run another nested loop
 3. Multiply each element and store the maximum value of all the subarray.

## Complexity Analysis:
    1. Time Complexity: O(n^2) where n is the number of elements in the array.
    2. Space Complexity: O(1) as we are not using any extra space.
 */