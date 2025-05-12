package Array.Merge_Sorted_Arrays2;
/* 
# Problem: Merge Sorted Arrays
Given two sorted arrays arr1[] and arr2[] of sizes n and m in non-decreasing order. Merge them in sorted order. Modify arr1 so that it contains the first N elements and modify arr2 so that it contains the last M elements.

## Intution:
Similar to optimal approach 1, in this approach, we will use two pointers i.e. left and right, and swap the elements if the element at the left pointer is greater than the element at the right pointer. 

But the placing of the pointers will be based on the gap value calculated. The formula to calculate the initial gap is the following:
        Initial gap = ceil((size of arr1[] + size of arr2[]) / 2)
Assume the two arrays as a single continuous array and initially, we will place the left pointer at the first index and the right pointer at the (left+gap) index of that continuous array.
Now, we will compare the elements at the left and right pointers and move them by 1 place each time after comparison. While comparing we will swap the elements if the element at the left pointer > the element at the right pointer. After some steps, the right pointer will reach the end and the iteration will be stopped.

After each iteration, we will decrease the gap and will follow the same procedure until the iteration for gap = 1 gets completed. Now, after each iteration, the gap will be the following:
            gap = ceil( previous gap / 2)
The whole process will be applied to the imaginary continuous array constructed using arr1[] and arr2[].
*/
public class Approach3 {
    // Approach 3: Using gap method
    public static void swapIfGreater(long[] arr1, long[] arr2, int ind1, int ind2) {
        if (arr1[ind1] > arr2[ind2]) {
            long temp = arr1[ind1];
            arr1[ind1] = arr2[ind2];
            arr2[ind2] = temp;
        }
    }

    public static void merge(long[] arr1, long[] arr2, int n, int m) {

        // len of the imaginary single array:
        int len = n + m;

        // Initial gap:
        int gap = (len / 2) + (len % 2);

        while (gap > 0) {
            // Place 2 pointers:
            int left = 0;
            int right = left + gap;
            while (right < len) {
                // case 1: left in arr1[]
                //and right in arr2[]:
                if (left < n && right >= n) {
                    swapIfGreater(arr1, arr2, left, right - n);
                }
                // case 2: both pointers in arr2[]:
                else if (left >= n) {
                    swapIfGreater(arr2, arr2, left - n, right - n);
                }
                // case 3: both pointers in arr1[]:
                else {
                    swapIfGreater(arr1, arr1, left, right);
                }
                left++; right++;
            }
            // break if iteration gap=1 is completed:
            if (gap == 1) break;

            // Otherwise, calculate new gap:
            gap = (gap / 2) + (gap % 2);
        }
    }

    public static void main(String[] args) {
        long[] arr1 = {1, 4, 8, 10};
        long[] arr2 = {2, 3, 9};
        int n = 4, m = 3;
        merge(arr1, arr2, n, m);
        System.out.println("The merged arrays are:");
        System.out.print("arr1[] = ");
        for (int i = 0; i < n; i++) {
            System.out.print(arr1[i] + " ");
        }
        System.out.print("\narr2[] = ");
        for (int i = 0; i < m; i++) {
            System.out.print(arr2[i] + " ");
        }
        System.out.println();
    }
}
/* 
# Algorithm:
First, assume the two arrays as a single array and calculate the gap value i.e. ceil((size of arr1[] + size of arr2[]) / 2).
We will perform the following operations for each gap until the value of the gap becomes 0:
Place two pointers in their correct position like the left pointer at index 0 and the right pointer at index (left+gap).
Again we will run a loop until the right pointer reaches the end i.e. (n+m). Inside the loop, there will be 3 different cases:
If the left pointer is inside arr1[] and the right pointer is in arr2[]: We will compare arr1[left] and arr2[right-n] and swap them if arr1[left] > arr2[right-n].
If both the pointers are in arr2[]: We will compare arr1[left-n] and arr2[right-n] and swap them if arr1[left-n] > arr2[right-n].
If both the pointers are in arr1[]: We will compare arr1[left] and arr2[right] and swap them if arr1[left] > arr2[right].
After the right pointer reaches the end, we will decrease the value of the gap and it will become ceil(current gap / 2). 
Finally, after performing all the operations, we will get the merged sorted array.
 */