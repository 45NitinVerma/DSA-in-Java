package Array.Max_Product_Subarray;
/* 
# Problem: Maximum Product Subarray
Given an integer array nums, find a subarray that has the largest product, and return the product.

## Intution: Use 3 nested loops to find the maximum product of all subarrays.
 */
public class Approach1 {
    // Approach 1: Using 3 nested loops to find the maximum product of all subarrays.
    static int maxProductSubArray(int arr[]) {
	    int result = Integer.MIN_VALUE;
	    for(int i=0;i<arr.length-1;i++) 
	        for(int j=i+1;j<arr.length;j++) {
	            int prod = 1;
	            for(int k=i;k<=j;k++) 
	                prod *= arr[k];
	            result = Math.max(result,prod);
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
 1. Run a loop on the array to choose the start point for each subarray.
 2. Run a nested loop to get the end point for each subarray.
 3. Multiply elements present in the chosen range.

## Complexity Analysis:
    1. Time Complexity: O(n^3) where n is the number of elements in the array.
    2. Space Complexity: O(1) as we are not using any extra space.
 */