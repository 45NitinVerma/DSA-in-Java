package Array.Count_Inversions;

import java.util.ArrayList;

/* 
# Problem: Count Inversions
For a given integer array/list 'ARR' of size 'N' containing all distinct values, find the total number of 'Inversions' that may exist.

An inversion is defined for a pair of integers in the array/list when the following two conditions are met.
A pair ('ARR[i]', 'ARR[j]') is said to be an inversion when:
1. 'ARR[i] > 'ARR[j]' 
2. 'i' < 'j'
Where 'i' and 'j' denote the indices ranging from [0, 'N').

## Intution: 
Observation: 

Let’s build the intuition for this approach using a modified version of the given question.

Assume two sorted arrays are given i.e. a1[] = {2, 3, 5, 6} and a2[] = {2, 2, 4, 4, 8}. Now, we have to count the pairs i.e. a1[i] and a2[j] such that a1[i] > a2[j].

In order to solve this, we will keep two pointers i and j, where i will point to the first index of a1[] and j will point to the first index of a2[]. Now in each iteration, we will do the following:

 - If a1[i] <= a2[j]: These two elements cannot be a pair and so we will move the pointer i to the next position. This case is illustrated below:
 a1 = {2, 3, 5, 6}      a2 = {2, 2, 4, 4, 8}
       ↑                      ↑
       i                      j
 Here, a1[i] == a2[j], so we will move the pointer to next postion

 - Why we moved the i pointer: We know, that the given arrays are sorted. So, all the elements after the pointer j, should be greater than a2[j]. Now, as a1[i] is smaller or equal to a2[j], it is obvious that a1[i] will be smaller or equal to all the elements after a2[j]. We need a bigger value of a1[i] to make a pair and so we move the i pointer to the next position i.e. next bigger value.

 - If a1[i] > a2[j]: These two elements can be a pair and so we will update the count of pairs. Now, here, we should observe that as a1[i] is greater than a2[j], all the elements after a1[i] will also be greater than a2[j] and so, those elements will also make pair with a2[j]. So, the number of pairs added will be n1-i (where n1 = size of a1[ ]). Now, we will move the j pointer to the next position. This case is also illustrated below:
 a1 = {2, 3, 5, 6}      a2 = {2, 2, 4, 4, 8}
          ↑                   ↑
          i                   j
 Here, a1[i] > a2[j], and elements after a1[i] i.e. 5 and 6 is also greater than a2[j]. So, the total number of pairs added to count will be n1-i = 4-1 = 3. 
 Threfore, cnt = cnt + 3
 Now, we will move the j pointer to next position i.e. j = 1.

The above process will continue until at least one of the pointers reaches the end.

Until now, we have figured out how to count the number of pairs in one go if two sorted arrays are given. But in our actual question, only a single unsorted array is given. So, how to break it into two sorted halves so that we can apply the above observation? 

We can think of the merge sort algorithm that works in a similar way we want. In the merge sort algorithm, at every step, we divide the given array into two halves and then sort them, and while doing that we can actually count the number of pairs.

Basically, we will use the merge sort algorithm to use the observation in the correct way.

### Observation 1: Split and Count Strategy
The total inversions in an array can be split into three parts:
* Inversions in the left half
* Inversions in the right half
* Cross inversions between elements of left and right halves

Example: For array [5, 3, 2, 4, 1]
- Left half [5, 3] has 1 inversion (5,3)
- Right half [2, 4, 1] has 2 inversions (2,1) and (4,1)
- Cross inversions: (5,2), (5,4), (5,1), (3,2), (3,1) - 5 inversions
- Total: 1 + 2 + 5 = 8 inversions

### Observation 2: Merge Step Cross-Inversion Counting
When merging two sorted subarrays, if an element from the right subarray is picked, it forms inversions with all remaining elements in the left subarray.

Example:
```
Left sorted: [3, 5]  Right sorted: [1, 2, 4]
```

When comparing 3 and 1:
- 1 is smaller, so we pick 1
- 1 forms inversions with both 3 and 5 (all remaining elements in left array)
- Number of inversions: (mid - left + 1) = 2

### Observation 3: Recursive Accumulation
By accumulating inversion counts from each recursive call and merge step, we get the total count.

```
                      [5, 3, 2, 4, 1]
                       /           \
                [5, 3, 2]          [4, 1]
                /      \           /    \
             [5, 3]    [2]        [4]  [1]
             /   \      |          |    |
            [5] [3]     |          |    |    
             \   /      |          \   /
             \  /       |           \ /
      (1inv)[3, 5]     [2]         [1, 4](1 inv)
               \      /               |
                \   /                 |
        (2inv)[2, 3, 5]            [1, 4]
                   \                /
                    \              /
                     \           /
                     [1, 2, 3, 4, 5] (4 inv)
```

## Step-by-Step Example
Let's work through the array [5, 3, 2, 4, 1]:

1. Split into [5, 3, 2] and [4, 1]
   ```
   For [5, 3, 2]:
   - Split into [5, 3] and [2]
    - For [5, 3]: Split into [5] and [3]
    - Merge [5] and [3] → [3, 5], count 1 inversion (5>3)
   - Merge [3, 5] and [2] → [2, 3, 5], count 2 inversion - [3,2] and [5,2]
   ```

2. For [4, 1]:
   ```
   - Split into [4] and [1]
   - Merge [4] and [1] → [1, 4], count 1 inversion (4>1)
   ```

3. Merge [2, 3, 5] and [1, 4]:
   ```
   - Compare 2 and 1: Take 1, count 3 inversions (2>1, 3>1, 5>1)
   - Compare 3 and 4: Take 3, count 0 inversion (3<4)
   - Compare 5 and 4: Take 4, count 1 inversion (5>4)
   - Add remaining [4, 5]
   - Final array: [1, 2, 3, 4, 5], total inversions: 1 + 2 + 1 + 4 = 8
   ```

## Special Cases

### Case 1: Already Sorted Array
Input: [1, 2, 3, 4, 5]
- Behavior: No element from right subarray will be smaller than left
- Result: 0 inversions

### Case 2: Reverse Sorted Array
Input: [5, 4, 3, 2, 1]
- Behavior: Every element forms inversions with all elements to its right
- Result: n*(n-1)/2 = 10 inversions for n=5

### Case 3: Partially Sorted Array
Input: [1, 5, 3, 4, 2]
- Behavior: Mix of in-order and out-of-order elements
- Result: 4 inversions (5,3), (5,4), (5,2), (3,2)
*/
public class Approach2 {
    // Approach 2: Merge Sort Approach
    public static int mergeSort(int[] arr, int low, int high) {
        int invCount = 0;
        
        // Base case: If subarray has 0 or 1 element, no inversions
        if (low >= high) return invCount;
        
        // Divide the array into two halves
        int mid = (low + high) / 2;
        
        // Count inversions in left half
        invCount += mergeSort(arr, low, mid);
        
        // Count inversions in right half
        invCount += mergeSort(arr, mid + 1, high);
        
        // Count cross inversions while merging
        invCount += merge(arr, low, mid, high);
        
        return invCount;
    }

    private static int merge(int[] arr, int low, int mid, int high) {
        ArrayList<Integer> temp = new ArrayList<>(); // temporary array to store merged elements
        int left = low;      // starting index of left half
        int right = mid + 1; // starting index of right half
        
        // Counter for inversions found during this merge
        int invCount = 0;
        
        // Compare elements from both halves and merge in sorted order
        while (left <= mid && right <= high) {
            if (arr[left] <= arr[right]) {
                // No inversion if left element is smaller or equal
                temp.add(arr[left]);
                left++;
            } else {
                // If right element is smaller, it forms inversions with all
                // remaining elements in the left half
                temp.add(arr[right]);
                invCount += (mid - left + 1); // Key insight: counting cross inversions
                right++;
            }
        }
        
        // Add remaining elements from left half (if any)
        while (left <= mid) {
            temp.add(arr[left]);
            left++;
        }
        
        // Add remaining elements from right half (if any)
        while (right <= high) {
            temp.add(arr[right]);
            right++;
        }
        
        // Copy elements from temp array back to original array
        for (int i = low; i <= high; i++) {
            arr[i] = temp.get(i - low);
        }
        
        return invCount;
    }

    public static int numberOfInversions(int[] a, int n) {
        // Count the number of pairs:
        return mergeSort(a, 0, n - 1);
    }


    public static void main(String[] args) {
        int[] a = {5, 4, 3, 2, 1};
        int n = 5;
        int cnt = numberOfInversions(a, n);
        System.out.println("The number of inversions are: " + cnt);
    }
}

/*
# Algorithm
1. Define a function to count inversions using merge sort
2. Split the array into two halves recursively until single elements
3. Count inversions in left half recursively
4. Count inversions in right half recursively
5. Count cross inversions during merge step:
   - When merging two sorted halves, if an element from right is picked, add (mid-left+1) to inversions
   - This counts inversions between all remaining elements in left half and current right element
6. Return total inversions by summing all three counts

# Approach: The steps are basically the same as they are in the case of the merge sort algorithm. The change will be just a one-line addition inside the merge() function. Inside the merge(), we need to add the number of pairs to the count when a[left] > a[right].

The steps of the merge() function were the following:
1. In the merge function, we will use a temp array to store the elements of the two sorted arrays after merging. Here, the range of the left array is low to mid and the range for the right half is mid+1 to high.
2. Now we will take two pointers left and right, where left starts from low and right starts from mid+1.
3. Using a while loop( while(left <= mid && right <= high)), we will select two elements, one from each half, and will consider the smallest one among the two. Then, we will insert the smallest element in the temp array. 
4. After that, the left-out elements in both halves will be copied as it is into the temp array.
5. Now, we will just transfer the elements of the temp array to the range low to high in the original array.

### Modifications in merge() and mergeSort(): 
- In order to count the number of pairs, we will keep a count variable, cnt, initialized to 0 beforehand inside the merge().
- While comparing a[left] and a[right] in the 3rd step of merge(), if a[left] > a[right], we will simply add this line:
cnt += mid-left+1 (mid+1 = size of the left half)
- Now, we will return this cnt from merge() to mergeSort(). 
- Inside mergeSort(), we will keep another counter variable that will store the final answer. With this cnt, we will add the answer returned from mergeSort() of the left half, mergeSort() of the right half, and merge().
- Finally, we will return this cnt, as our answer from mergeSort().

### Time Complexity Breakdown per Step
1. Dividing array: O(log n) levels of recursion
2. At each level, merging takes O(n) operations
3. Counting inversions is done during merging: O(n)

Total: O(n log n)

### Space Complexity Breakdown
1. Auxiliary Space: O(n)
   - Temporary array in merge step requires O(n) space
   - Recursion stack requires O(log n) space
2. Input Space: O(n)
   - Original array of size n

Total: O(n)

# Advantages
1. Efficient time complexity of O(n log n) compared to naive O(n²) approach
2. Works well for large arrays where brute force is impractical
3. Can be implemented with the standard merge sort with minimal modifications
4. Counts inversions while sorting, getting two tasks done in one pass

# Limitations
1. Requires O(n) extra space for the temporary array during merging
2. More complex than the brute force approach
3. May not be as intuitive to understand at first glance

# Potential Improvements
1. Use in-place merge sort to reduce space complexity
2. Parallelize the algorithm for very large arrays on multi-core systems
3. Use a Fenwick Tree (Binary Indexed Tree) for an alternative O(n log n) approach
4. Optimize memory usage by reusing the same temporary array across recursive calls

# Detailed Dry Run: Count Inversions Using Merge Sort

Let's perform a detailed trace of the algorithm on the input array `[5, 4, 3, 2, 1]`.

## Complete Execution Table

| Step | Function Call | Array State | Action | Left Half | Right Half | Temp Array | Inversions | Cumulative Count |
|------|--------------|-------------|--------|-----------|------------|------------|------------|-----------------|
| 1 | `mergeSort([5,4,3,2,1], 0, 4)` | [5,4,3,2,1] | Split at mid=2 | [5,4] | [3,2,1] | - | 0 | 0 |
| 2 | `mergeSort([5,4], 0, 1)` | [5,4] | Split at mid=0 | [5] | [4] | - | 0 | 0 |
| 3 | `mergeSort([5], 0, 0)` | [5] | Base case | - | - | - | 0 | 0 |
| 4 | `mergeSort([4], 1, 1)` | [4] | Base case | - | - | - | 0 | 0 |
| 5 | `merge([5,4], 0, 0, 1)` | [5,4] | Merge | [5] | [4] | [4,5] | 1 | 1 |
| 6 | `mergeSort([3,2,1], 2, 4)` | [3,2,1] | Split at mid=3 | [3] | [2,1] | - | 0 | 1 |
| 7 | `mergeSort([3], 2, 2)` | [3] | Base case | - | - | - | 0 | 1 |
| 8 | `mergeSort([2,1], 3, 4)` | [2,1] | Split at mid=3 | [2] | [1] | - | 0 | 1 |
| 9 | `mergeSort([2], 3, 3)` | [2] | Base case | - | - | - | 0 | 1 |
| 10 | `mergeSort([1], 4, 4)` | [1] | Base case | - | - | - | 0 | 1 |
| 11 | `merge([2,1], 3, 3, 4)` | [2,1] | Merge | [2] | [1] | [1,2] | 1 | 2 |
| 12 | `merge([3,2,1], 2, 2, 4)` | [3,2,1] | Merge | [3] | [1,2] | [1,2,3] | 2 | 4 |
| 13 | `merge([5,4,3,2,1], 0, 1, 4)` | [4,5,1,2,3] | Compare 4,1 | [4,5] | [1,2,3] | [1] | 2 | 6 |
| 14 | `merge([5,4,3,2,1], 0, 1, 4)` | [4,5,1,2,3] | Compare 4,2 | [4,5] | [2,3] | [1,2] | 2 | 8 |
| 15 | `merge([5,4,3,2,1], 0, 1, 4)` | [4,5,1,2,3] | Compare 4,3 | [4,5] | [3] | [1,2,3] | 2 | 10 |
| 16 | `merge([5,4,3,2,1], 0, 1, 4)` | [4,5,1,2,3] | Add remaining | [5] | [] | [1,2,3,4] | 0 | 10 |
| 17 | `merge([5,4,3,2,1], 0, 1, 4)` | [4,5,1,2,3] | Add remaining | [] | [] | [1,2,3,4,5] | 0 | 10 |

## Step-by-Step Explanation

### 1. Initial Call to Merge Sort
- **Input Array**: [5, 4, 3, 2, 1]
- **Action**: Call `mergeSort([5,4,3,2,1], 0, 4)`
- **Process**: Calculate mid = (0+4)/2 = 2, split into [5,4] and [3,2,1]
- **Result**: Array is split, recursively process each half

### 2. Process Left Half - First Recursive Call
- **Input**: [5, 4]
- **Action**: Call `mergeSort([5,4], 0, 1)`
- **Process**: Calculate mid = (0+1)/2 = 0, split into [5] and [4]
- **Result**: Array is split, recursively process each half

### 3-4. Process Single Elements
- **Input**: [5] and [4] separately
- **Action**: Base cases - single elements are already sorted
- **Process**: Return 0 inversions for each single element
- **Result**: Ready to merge [5] and [4]

### 5. First Merge Operation
- **Input**: [5] and [4]
- **Action**: Call `merge([5,4], 0, 0, 1)`
- **Process**:
  * Compare 5 and 4: 4 is smaller
  * 4 goes first in temp array
  * Since right element (4) is picked, count inversions: mid-left+1 = 0-0+1 = 1
  * Add 5 to temp array
- **Result**: 
  * Temp array: [4, 5]
  * Inversions counted: 1
  * Update array: [4, 5, 3, 2, 1]

### 6. Process Right Half - Second Recursive Call
- **Input**: [3, 2, 1]
- **Action**: Call `mergeSort([3,2,1], 2, 4)`
- **Process**: Calculate mid = (2+4)/2 = 3, split into [3] and [2,1]
- **Result**: Array is split, recursively process each half

### 7. Process Single Element
- **Input**: [3]
- **Action**: Base case - single element is already sorted
- **Process**: Return 0 inversions
- **Result**: Ready to process [2,1]

### 8. Process Right Half's Right Half
- **Input**: [2, 1]
- **Action**: Call `mergeSort([2,1], 3, 4)`
- **Process**: Calculate mid = (3+4)/2 = 3, split into [2] and [1]
- **Result**: Array is split, recursively process each half

### 9-10. Process Single Elements
- **Input**: [2] and [1] separately
- **Action**: Base cases - single elements are already sorted
- **Process**: Return 0 inversions for each single element
- **Result**: Ready to merge [2] and [1]

### 11. Merge [2] and [1]
- **Input**: [2] and [1]
- **Action**: Call `merge([2,1], 3, 3, 4)`
- **Process**:
  * Compare 2 and 1: 1 is smaller
  * 1 goes first in temp array
  * Since right element (1) is picked, count inversions: mid-left+1 = 3-3+1 = 1
  * Add 2 to temp array
- **Result**: 
  * Temp array: [1, 2]
  * Inversions counted: 1
  * Update array: [4, 5, 3, 1, 2]

### 12. Merge [3] and [1,2]
- **Input**: [3] and [1,2]
- **Action**: Call `merge([3,2,1], 2, 2, 4)` (now [3,1,2])
- **Process**:
  * Compare 3 and 1: 1 is smaller
  * 1 goes first in temp array
  * Since right element (1) is picked, count inversions: mid-left+1 = 2-2+1 = 1
  * Compare 3 and 2: 2 is smaller
  * 2 goes next in temp array
  * Since right element (2) is picked, count inversions: mid-left+1 = 2-2+1 = 1
  * Add 3 to temp array
- **Result**: 
  * Temp array: [1, 2, 3]
  * Inversions counted: 1+1 = 2
  * Update array: [4, 5, 1, 2, 3]

### 13-17. Final Merge Operation
- **Input**: [4,5] and [1,2,3]
- **Action**: Call `merge([4,5,1,2,3], 0, 1, 4)`
- **Process**:
  * Compare 4 and 1: 1 is smaller
  * 1 goes first in temp array
  * Since right element (1) is picked, count inversions: mid-left+1 = 1-0+1 = 2
  * Compare 4 and 2: 2 is smaller
  * 2 goes next in temp array
  * Since right element (2) is picked, count inversions: mid-left+1 = 1-0+1 = 2
  * Compare 4 and 3: 3 is smaller
  * 3 goes next in temp array
  * Since right element (3) is picked, count inversions: mid-left+1 = 1-0+1 = 2
  * Add remaining elements 4 and 5 to temp array
- **Result**: 
  * Temp array: [1, 2, 3, 4, 5]
  * Inversions counted: 2+2+2 = 6
  * Final array: [1, 2, 3, 4, 5]

## Visual Step-by-Step Process

### Initial Split and Recursive Processing
```
                      [5, 4, 3, 2, 1]
                       /           \
                [5, 4]               [3, 2, 1]
                /    \               /       \
             [5]     [4]          [3]      [2, 1]
                                           /    \
                                         [2]    [1]
```

### Merging and Counting Inversions

Merge [5] and [4]:
```
[5] [4] → Compare 5,4 → 4 is smaller → Pick 4 → Count 1 inversion → [4,5]
```

Merge [2] and [1]:
```
[2] [1] → Compare 2,1 → 1 is smaller → Pick 1 → Count 1 inversion → [1,2]
```

Merge [3] and [1,2]:
```
[3] [1,2] → 
  Compare 3,1 → 1 is smaller → Pick 1 → Count 1 inversion
  Compare 3,2 → 2 is smaller → Pick 2 → Count 1 inversion
  Add 3 → [1,2,3]
```

Final Merge [4,5] and [1,2,3]:
```
[4,5] [1,2,3] →
  Compare 4,1 → 1 is smaller → Pick 1 → Count 2 inversions (4>1, 5>1)
  Compare 4,2 → 2 is smaller → Pick 2 → Count 2 inversions (4>2, 5>2)
  Compare 4,3 → 3 is smaller → Pick 3 → Count 2 inversions (4>3, 5>3)
  Add 4,5 → [1,2,3,4,5]
```

## Inversion Pairs Identified

The 10 inversion pairs in our example are:
1. (5,4) - From first merge
2. (2,1) - From second merge
3. (3,1) - From third merge
4. (3,2) - From third merge
5. (4,1) - From final merge
6. (5,1) - From final merge
7. (4,2) - From final merge
8. (5,2) - From final merge
9. (4,3) - From final merge
10. (5,3) - From final merge

## Key Points of This Trace

1. **Divide Phase**: The array is recursively split until we have single elements
2. **Conquer Phase**: Single elements are already sorted (0 inversions)
3. **Combine Phase**: When merging, we count inversions whenever an element from the right half is picked
4. **Inversion Formula**: When an element from right is smaller, it forms inversions with all remaining elements in left half (mid-left+1)
5. **Accumulation**: Inversions from all recursive calls and merge operations are added to get the final count

This execution trace demonstrates how merge sort efficiently counts inversions in O(n log n) time, much better than the O(n²) approach of checking all pairs.

### Step-by-Step Explanation

1. **Initial Split**
   - Split [5, 4, 3, 2, 1] into [5, 4] and [3, 2, 1]
   ```
   [5, 4, 3, 2, 1]
    /          \
   [5, 4]     [3, 2, 1]
   ```

2. **Process Left Half**
   - Split [5, 4] into [5] and [4]
   - Merge [5] and [4] → [4, 5], count 1 inversion (5>4)
   ```
   [5] [4] → [4, 5], inversions: 1
   ```

3. **Process Right Half**
   - Split [3, 2, 1] into [3] and [2, 1]
   - Split [2, 1] into [2] and [1]
   - Merge [2] and [1] → [1, 2], count 1 inversion (2>1)
   ```
   [2] [1] → [1, 2], inversions: 1
   ```
   - Merge [3] and [1, 2] → [1, 2, 3], count 2 inversions (3>1, 3>2)
   ```
   [3] [1, 2] → [1, 2, 3], inversions: 2
   ```

4. **Final Merge**
   - Merge [4, 5] and [1, 2, 3] → [1, 2, 3, 4, 5]
   ```
   [4, 5] [1, 2, 3] → [1, 2, 3, 4, 5]
   ```
   - Compare 4 and 1: Pick 1, count 2 inversions (4>1, 5>1)
   - Compare 4 and 2: Pick 2, count 2 inversions (4>2, 5>2)
   - Compare 4 and 3: Pick 3, count 2 inversions (4>3, 5>3)
   - Total cross inversions in final merge: 6
   - Total inversions in entire array: 1 + 1 + 2 + 6 = 10

### Additional Example Cases

1. **Partially Sorted Array**
```
Input:  [1, 20, 6, 4, 5]
Step 1: Split into [1, 20] and [6, 4, 5]
Step 2: [1, 20] is already sorted with 0 inversions
Step 3: [6, 4, 5] has 2 inversions
Step 4: Merge [1, 20] and [4, 5, 6] gives 3 more inversions (20>4, 20>5, 20>6)
Output: 5 inversions
```

2. **Already Sorted Array**
```
Input:  [1, 2, 3, 4, 5]
Step 1: All subarrays are already sorted
Step 2: No element from right subarray is ever smaller than left
Output: 0 inversions
```

### Edge Cases Handling
1. **Single Element**
   ```
   Input: [5]
   Output: 0 inversions
   Explanation: A single element has no inversions.
   ```

2. **Empty Array**
   ```
   Input: []
   Output: 0 inversions
   Explanation: An empty array has no inversions.
   ```

3. **Equal Elements**
   ```
   Input: [3, 3, 3]
   Output: 0 inversions
   Explanation: Since we count inversions only when arr[i] > arr[j], equal elements don't contribute.
   ```

Key Observations:
1. The number of inversions equals the number of swaps needed in bubble sort
2. A perfectly sorted array has 0 inversions
3. A reverse-sorted array has maximum possible inversions: n*(n-1)/2
4. The inversion count gives a measure of how far the array is from being sorted

Sample Validation:
Input: [5, 4, 3, 2, 1]
Expected: 10 inversions
Output: 10 inversions

Key Points:
1. The cross-inversion count is the key insight - when a smaller element from right is picked, it forms inversions with all remaining elements in left
2. We leverage merge sort's divide and conquer to efficiently count inversions
3. The time complexity is O(n log n), making it efficient for large arrays
4. The solution can be extended to find the k-th smallest element in an array

TEST CASES:
1. Input: [5, 4, 3, 2, 1]
   Expected: 10
   Output: 10
2. Input: [1, 2, 3, 4, 5]
   Expected: 0
   Output: 0
3. Input: [1, 3, 5, 2, 4]
   Expected: 3
   Output: 3
4. Input: [5]
   Expected: 0
   Output: 0
5. Input: [3, 1, 2]
   Expected: 2
   Output: 2
 */
