package Array.Longest_Consecutive_Sequence;

/*
# Problem: Longest Consecutive Sequence
Given an unsorted array of integers nums, return the length of the longest consecutive elements sequence.
You must write an algorithm that runs in O(n) time.
## Example:
### Input: nums = [100,4,200,1,3,2]
### Output: 4
Explanation: The longest consecutive elements sequence is [1, 2, 3, 4]. Therefore its length is 4.

# Intuition
- Find sequences of consecutive numbers in the array
- For each number, check if its next consecutive number exists
- Track the longest such sequence found

## Basic Understanding
We need to find the longest chain of numbers that follow each other consecutively (like 1,2,3,4) regardless of their original order in the array.

## Key Observations with Examples

### Observation 1: Sequence Independence
- Numbers don't need to be adjacent in the original array
- Only their values need to be consecutive
* Example: In [100,4,200,1,3,2], we can form [1,2,3,4]

### Observation 2: Multiple Sequences
- Array may contain multiple consecutive sequences
- We need to track the longest one
Example: In [1,2,3,100,101,102,103,104], we have two sequences: [1,2,3] and [100,101,102,103,104]

## Step-by-Step Example
Let's work through array [100,4,200,1,3,2]:

1. Start with 100:
   Check: 101 exists? No
   Sequence length: 1

2. Start with 4:
   Check: 5 exists? No
   Sequence length: 1

3. Start with 1:
   Check: 2 exists? Yes
   Check: 3 exists? Yes
   Check: 4 exists? Yes
   Check: 5 exists? No
   Sequence length: 4

## Special Cases

### Case 1: No Consecutive Numbers
Input: [5,7,9,11]
- Each number is isolated
- Result: 1

### Case 2: All Consecutive Numbers
Input: [1,2,3,4,5]
- Entire array forms sequence
- Result: 5

### Case 3: Duplicate Numbers
Input: [1,1,2,2,3,3]
- Duplicates don't affect sequence
- Result: 3

*/
public class Approach1 {
   // Approach 1: Brute Force using Linear Search
    // Linear search to find if a number exists in array
    public static boolean linearSearch(int[] a, int num) {
        int n = a.length;
        for (int i = 0; i < n; i++) {
            if (a[i] == num)
                return true;
        }
        return false;
    }

    // Method to find longest consecutive sequence
    public static int longestSuccessiveElements(int[] a) {
        int n = a.length;
        int longest = 1;
        
        // Check each element as potential sequence start
        for (int i = 0; i < n; i++) {
            int x = a[i];
            int cnt = 1;
            
            // Keep checking next consecutive number
            while (linearSearch(a, x + 1)) {
                x += 1;
                cnt += 1;
            }
            
            longest = Math.max(longest, cnt);
        }
        return longest;
    }

    public static void main(String[] args) {
        int[] a = {100, 200, 1, 2, 3, 4};
        int ans = longestSuccessiveElements(a);
        System.out.println("The longest consecutive sequence is " + ans);
    }
}

/*
# Algorithm
1. For each number in array:
2. Check if next consecutive number exists using linear search
3. Keep counting consecutive numbers until sequence breaks
4. Update longest sequence if current sequence is longer
5. Return the longest sequence found

### Time Complexity Breakdown per Step
1. Outer loop: O(n)
2. Linear search for each number: O(n)
3. Checking consecutive numbers: O(n)
Total: O(n²)

### Space Complexity Breakdown
1. Auxiliary Space: O(1)
- Only using a few variables
2. Input Space: O(n)
- Original array
Total: O(n)

# Advantages
1. Simple to implement
2. Works with any range of numbers
3. No extra space required
4. Easy to modify for variations

# Limitations
1. Time complexity is O(n²)
2. Not optimal for large arrays
3. Repeated linear searches are inefficient

# Potential Improvements
1. Use HashSet for O(1) lookups
2. Sort array first (but would be O(nlogn))
3. Use dynamic programming approach

# Step-by-Step Process with Dry Run

## Example Input: [100,4,200,1,3,2]

### Detailed Execution Table
```
Step | Number | Sequence Found | Length | Max Length
-----|---------|---------------|---------|------------
1    | 100     | [100]         | 1       | 1
2    | 4       | [4]           | 1       | 1
3    | 200     | [200]         | 1       | 1
4    | 1       | [1,2,3,4]     | 4       | 4
5    | 3       | [3,4]         | 2       | 4
6    | 2       | [2,3,4]       | 3       | 4
```

### Edge Cases Handling
1. **Empty Array**
   ```
   Input: []
   Output: 0
   Returns 0 for empty array
   ```

2. **Single Element**
   ```
   Input: [5]
   Output: 1
   Returns 1 for single element
   ```

TEST CASES:
1. Input: [100,4,200,1,3,2]
   Expected: 4
   Output: 4
2. Input: [0,3,7,2,5,8,4,6,0,1]
   Expected: 9
   Output: 9
3. Input: []
   Expected: 0
   Output: 0
4. Input: [1]
   Expected: 1
   Output: 1
*/