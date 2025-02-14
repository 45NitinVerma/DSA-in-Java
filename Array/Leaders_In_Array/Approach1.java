package Array.Leaders_In_Array;
import java.util.ArrayList;
/* 
Problem: Given an integer array 'a' of size 'n', find all Superior Elements (elements greater than 
all elements to their right) in the array.
Note: The last element is always a Superior Element.

Intuition: 
For each element, we need to check if it's greater than all elements to its right.
We can understand this with examples:

Example 1: [10, 22, 12, 3, 0, 6]
- 6 is last element → Superior Element
- 0 < 6 → Not Superior
- 3 < 6 → Not Superior
- 12 > 3,0,6 → Superior Element
- 22 > 12,3,0,6 → Superior Element
- 10 < 22 → Not Superior
Result: [22, 12, 6]

Example 2: [1, 2, 3, 2]
- 2 is last element → Superior Element
- 3 > 2 → Superior Element
- 2 < 3 → Not Superior
- 1 < 2 → Not Superior
Result: [3, 2]
*/

public class Approach1 {
    // Approach 1: Brute Force Approach By Comparison
    public static ArrayList<Integer> findSuperiorElements(int[] arr, int n) {
        ArrayList<Integer> superiorElements = new ArrayList<>();
        
        // Iterate through each element
        for (int i = 0; i < n; i++) {
            boolean isSuperior = true;
            
            // Compare current element with all elements to its right
            for (int j = i + 1; j < n; j++) {
                // If any element on right is greater, current element is not superior
                if (arr[j] > arr[i]) {
                    isSuperior = false;
                    break;
                }
            }
            
            // If element is superior, add to result list
            if (isSuperior) {
                superiorElements.add(arr[i]);
            }
        }
        
        return superiorElements;
    }

    public static void main(String args[]) {
        // Test array
        int n = 6;
        int arr[] = {10, 22, 12, 3, 0, 6};

        // Get superior elements
        ArrayList<Integer> result = findSuperiorElements(arr, n);
        
        // Print results
        for (int element : result) {
            System.out.print(element + " ");
        }
    }
}

/* 
ALGORITHM:
1. Initialize empty ArrayList for storing superior elements
2. For each element at index i:
   a. Assume it's a superior element (isSuperior = true)
   b. Compare with all elements to its right
   c. If any right element is greater, mark as not superior
3. If element remains superior after all comparisons, add to result
4. Return the list of superior elements

Complexity Analysis:
TIME COMPLEXITY: O(n²)
- For each element, we compare with all elements to its right
- Outer loop runs n times, inner loop runs (n-i) times

SPACE COMPLEXITY:
1. Auxiliary Space: O(k) where k is number of superior elements
   - Space for storing superior elements in ArrayList
2. Input Space: O(n)
   - Space for input array of size n

Advantages:
1. Simple to understand and implement
2. Works well for small arrays
3. No additional data structures needed except output list

Limitations:
1. Quadratic time complexity makes it inefficient for large arrays
2. Redundant comparisons as we check same elements multiple times

Potential Improvements:
1. Use right-to-left traversal to maintain maximum element seen so far
   - Time complexity can be reduced to O(n)
2. Use a stack to track potential leaders
3. Add sorting to return elements in sorted order if required
4. Optimize space by using the input array to mark leaders in-place

# Detailed Step-by-Step Analysis for Leaders in Array

Let's analyze the example array: [10, 22, 12, 3, 0, 6]

## Detailed Execution Table

```
-----------------------------------------------------------------------------------------
Step | Element | Compare With        | Comparisons Done           | Is Superior? | Result
-----------------------------------------------------------------------------------------
1    | 10      | [22,12,3,0,6]      | 10<22                     | No          | []
-----------------------------------------------------------------------------------------
2    | 22      | [12,3,0,6]         | 22>12                     | Yes         | [22]
                                     | 22>3
                                     | 22>0
                                     | 22>6
-----------------------------------------------------------------------------------------
3    | 12      | [3,0,6]            | 12>3                      | Yes         | [22,12]
                                     | 12>0
                                     | 12>6
-----------------------------------------------------------------------------------------
4    | 3       | [0,6]              | 3>0                       | No          | [22,12]
                                     | 3<6
-----------------------------------------------------------------------------------------
5    | 0       | [6]                | 0<6                       | No          | [22,12]
-----------------------------------------------------------------------------------------
6    | 6       | []                 | Last element              | Yes         | [22,12,6]
-----------------------------------------------------------------------------------------
```

## Step-by-Step Process Explanation

1. Step 1 (Element: 10)
   - Compare 10 with all elements to its right [22,12,3,0,6]
   - First comparison: 10 < 22
   - Since 10 is less than 22, break comparison
   - 10 is not a superior element
   - Result remains empty []

2. Step 2 (Element: 22)
   - Compare 22 with [12,3,0,6]
   - 22 > 12 ✓
   - 22 > 3  ✓
   - 22 > 0  ✓
   - 22 > 6  ✓
   - All comparisons successful
   - 22 is a superior element
   - Add to result: [22]

3. Step 3 (Element: 12)
   - Compare 12 with [3,0,6]
   - 12 > 3  ✓
   - 12 > 0  ✓
   - 12 > 6  ✓
   - All comparisons successful
   - 12 is a superior element
   - Add to result: [22,12]

4. Step 4 (Element: 3)
   - Compare 3 with [0,6]
   - 3 > 0   ✓
   - 3 < 6   ✗
   - Failed at second comparison
   - 3 is not a superior element
   - Result remains [22,12]

5. Step 5 (Element: 0)
   - Compare 0 with [6]
   - 0 < 6   ✗
   - Failed at first comparison
   - 0 is not a superior element
   - Result remains [22,12]

6. Step 6 (Element: 6)
   - Last element, no comparisons needed
   - Automatically a superior element
   - Add to result: [22,12,6]

## Key Points from the Execution:

1. First Element (10):
   - Demonstrates early termination when a larger element is found
   - Shows why we check ALL right elements

2. Second Element (22):
   - Shows a perfect case of superior element
   - Completes all comparisons successfully
   - Highest value in array

3. Third Element (12):
   - Another successful case
   - Shows how multiple superior elements can exist

4. Fourth Element (3):
   - Shows why we need complete right-side checking
   - Passes some comparisons but fails eventually

5. Fifth Element (0):
   - Shows immediate failure case
   - Demonstrates why checking first right element can be sufficient for failure

6. Last Element (6):
   - Demonstrates the special case of last element
   - Always included regardless of value

## Visual State Progression

```
Initial Array: [10, 22, 12, 3, 0, 6]

After Step 1: []
After Step 2: [22]
After Step 3: [22, 12]
After Step 4: [22, 12]
After Step 5: [22, 12]
Final Result: [22, 12, 6]
```

## Pattern Observations:

1. Results maintain original order of appearance
2. Superior elements form a decreasing sequence
3. Elements failing comparison never need to be rechecked
4. Last element is always included
5. Number of comparisons decreases as we move right
6. The highest element in the array will always be in the result

Key Observations:
1. Last element is always a superior element
2. Elements that become non-superior cannot become superior later
3. A strictly decreasing sequence will have all superior elements
4. A strictly increasing sequence will have only last element as superior

Sample Validation:
Input: [10, 22, 12, 3, 0, 6]
Expected: [22, 12, 6]
Output: [22, 12, 6]

Key Points:
1. Order of superior elements in result matches their order in input
2. Multiple passes over array can be avoided with optimized approach
3. Problem is related to finding "next greater element" concept

TEST CASES:
1. Input: [1, 2, 3, 2]
   Expected: [3, 2]
   Output: [3, 2]
2. Input: [5, 4, 3, 2, 1]
   Expected: [5, 4, 3, 2, 1]
   Output: [5, 4, 3, 2, 1]
3. Input: [1, 2, 3, 4, 5]
   Expected: [5]
   Output: [5]
*/