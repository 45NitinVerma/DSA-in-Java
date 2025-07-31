package Binary_Search.Count_Array_Rotation;
/* 
Find out how many times the array has been rotated
# Problem: Given an integer array arr of size N, sorted in ascending order (with distinct values). Now the array is rotated between 1 to N times which is unknown. Find how many times the array has been rotated. 

## Intution:
!! How does the rotation occur in a sorted array?
- Let's consider a sorted array: {1, 2, 3, 4, 5}. If we rotate this array 2 times, it will become: {4, 5, 1, 2, 3}. In essence, we moved the element at the last index to the front, while shifting the remaining elements to the right. We performed this process twice.

- Observation: 
We can easily observe that the number of rotations in an array is equal to the index(0-based index) of its minimum element.
So, in order to solve this problem, we have to find the index of the minimum element.
 */
public class Approach1 {
    // Approach 1: Using Linear Search
    public static int findKRotation(int[] arr) {
        int n = arr.length; //size of array.
        int ans = Integer.MAX_VALUE, index = -1;
        for (int i = 0; i < n; i++) {
            // finding the index of the minimum element.
            if (arr[i] < ans) {
                ans = arr[i];
                index = i;
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
1. First, we will declare an ‘ans’ and an ‘index’ variable initialized with a large number and -1 respectively.
2. Next, we will iterate through the array and compare each element with the variable called ‘ans’. Whenever we encounter an element 'arr[i]' that is smaller than ‘ans’, we will update ‘ans’ with the value of 'arr[i]' and also update the 'index' variable with the corresponding index value, 'i'.
3. Finally, we will return ‘index’ as our answer.

# Complexity Analysis:
1. Time Complexity: O(N), N = size of the given array.
Reason: We have to iterate through the entire array to check if the target is present in the array.

2. Space Complexity: O(1)
Reason: We have not used any extra data structures, this makes space complexity, even in the worst case as O(1).
 */