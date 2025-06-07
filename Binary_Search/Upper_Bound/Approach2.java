package Binary_Search.Upper_Bound;
/* 
# Problem: Upper Bound
You are given a sorted array ‘arr’ containing ‘n’ integers and an integer ‘x’.Implement the ‘upper bound’ function to find the index of the upper bound of 'x' in the array.

Note:
1. The upper bound in a sorted array is the index of the first value that is greater than a given value. 
2. If the greater value does not exist then the answer is 'n', Where 'n' is the size of the array.
3. Try to write a solution that runs in log(n) time complexity.

## What is Upper Bound?
The upper bound algorithm finds the first or the smallest index in a sorted array where the value at that index is greater than the given key i.e. x.

The upper bound is the smallest index, ind, where arr[ind] > x.

But if any such index is not found, the upper bound algorithm returns n i.e. size of the given array. The main difference between the lower and upper bound is in the condition. For the lower bound the condition was arr[ind] >= x and here, in the case of the upper bound, it is arr[ind] > x.
*/
public class Approach2 {
    // Approach 2: Using Binary Search
    public static int upperBound(int[] arr, int x, int n) {
        int low = 0, high = n - 1;
        int ans = n;

        while (low <= high) {
            int mid = (low + high) / 2;
            // maybe an answer
            if (arr[mid] > x) {
                ans = mid;
                //look for smaller index on the left
                high = mid - 1;
            } else {
                low = mid + 1; // look on the right
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] arr = {3, 5, 8, 9, 15, 19};
        int n = 6, x = 9;
        int ind = upperBound(arr, x, n);
        System.out.println("The upper bound is the index: " + ind);
    }
}
/* 
## Algorithm:
As the array is sorted, we will apply the Binary Search algorithm to find the index. The steps are as follows:
We will declare the 2 pointers and an ‘ans’ variable initialized to n i.e. the size of the array(as If we don’t find any index, we will return n).

1. Place the 2 pointers i.e. low and high: Initially, we will place the pointers like this: low will point to the first index and high will point to the last index.
2. Calculate the ‘mid’: Now, we will calculate the value of mid using the following formula:
mid = (low+high) // 2 ( ‘//’ refers to integer division)
3. Compare arr[mid] with x: With comparing arr[mid] to x, we can observe 2 different cases:
 - Case 1 - If arr[mid] > x: This condition means that the index mid may be an answer. So, we will update the ‘ans’ variable with mid and search in the left half if there is any smaller index that satisfies the same condition. Here, we are eliminating the right half.
 - Case 2 - If arr[mid] <= x: In this case, mid cannot be our answer and we need to find some bigger element. So, we will eliminate the left half and search in the right half for the answer.

## Complexity Analysis:
1. Time Complexity: O(log n) in the worst case, where n is the size of the array.
2. Space Complexity: O(1) as we are using only a constant amount of space.
 */
