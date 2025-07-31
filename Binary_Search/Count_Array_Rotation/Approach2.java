package Binary_Search.Count_Array_Rotation;
/* 
Find out how many times the array has been rotated
# Problem: Given an integer array arr of size N, sorted in ascending order (with distinct values). Now the array is rotated between 1 to N times which is unknown. Find how many times the array has been rotated. 

## Intution: In the previous article, find the minimum in a rotated sorted array, we have discussed how to find the minimum element in a rotated and sorted array using Binary search. In this problem, we will employ the same algorithm to determine the index of the minimum element. In the previous problem, we only stored the minimum element itself. However, in this updated approach, we will additionally keep track of the index. By making this small adjustment, we can effectively solve the problem using the existing algorithm.

 */
public class Approach2 {
    // Approach 2: Using Binary Search
    public static int findKRotation(int[] arr) {
        int low = 0, high = arr.length - 1;
        int ans = Integer.MAX_VALUE;
        int index = -1;
        while (low <= high) {
            int mid = (low + high) / 2;
            //search space is already sorted then arr[low] will always be the minimum in that search space:
            if (arr[low] <= arr[high]) {
                if (arr[low] < ans) {
                    index = low;
                    ans = arr[low];
                }
                break;
            }

            //if left part is sorted:
            if (arr[low] <= arr[mid]) {
                // keep the minimum:
                if (arr[low] < ans) {
                    index = low;
                    ans = arr[low];
                }

                // Eliminate left half:
                low = mid + 1;
            } else { //if right part is sorted:

                // keep the minimum:
                if (arr[mid] < ans) {
                    index = mid;
                    ans = arr[mid];
                }

                // Eliminate right half:
                high = mid - 1;
            }
        }
        return index;
    }

    public static void main(String[] args) {
        int[] arr = {4, 5, 6, 7, 0, 1, 2, 3};
        int ans = findKRotation(arr);
        System.out.println("The array is rotated " + ans + " times.");
    }
}
/* 
# Algorithm:
1. To begin, we will declare the variable 'ans' and initialize it with the largest possible value. Additionally, we will have two pointers, 'low' and 'high', as usual. In this case, we will also introduce an 'index' variable and initialize it with -1.
2. Place the 2 pointers i.e. low and high: Initially, we will place the pointers like this: low will point to the first index and high will point to the last index.
3. Calculate the ‘mid’: Now, inside a loop, we will calculate the value of ‘mid’ using the following formula:
    mid = (low+high) // 2 ( ‘//’ refers to integer division)
 - If arr[low] <= arr[high]: In this case, the array from index low to high is completely sorted. Therefore, we can select the minimum element, arr[low].
Now, if arr[low] < ans, we will update ‘ans’ with the value arr[low] and ‘index’ with the corresponding index low.
Once this is done, there is no need to continue with the binary search algorithm. So, we will break from this step.
4. Identify the sorted half, and after picking the leftmost element, eliminate that half.
 - If arr[low] <= arr[mid]: This condition ensures that the left part is sorted. So, we will pick the leftmost element i.e. arr[low].
 Now, if arr[low] < ans, we will update ‘ans’ with the value arr[low] and ‘index’ with the corresponding index low.
After that, we will eliminate this left half(i.e. low = mid+1).
 - Otherwise, if the right half is sorted:  This condition ensures that the right half is sorted. So, we will pick the leftmost element i.e. arr[mid].
Now, if arr[mid] < ans, we will update ‘ans’ with the value arr[mid] and ‘index’ with the corresponding index mid.
5. After that, we will eliminate this right half(i.e. high = mid-1).
6. This process will be inside a loop and the loop will continue until low crosses high. Finally, we will return the ‘index’ variable that stores the index of the minimum element.

# Complexity Analysis:
1. Time Complexity: O(logN), N = size of the given array.
Reason: We are basically using binary search to find the minimum.

2. Space Complexity: O(1)
Reason: We have not used any extra data structures, this makes space complexity, even in the worst case as O(1).
 */