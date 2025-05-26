package Array.Max_Product_Subarray.Optimal;
/* 
# Problem: Maximum Product Subarray
Given an integer array nums, find a subarray that has the largest product, and return the product.

## Intution: 
- The key insight is handling positive, negative numbers, and zeros differently
- We use a simultaneous prefix and suffix approach to avoid missing any possible maximum products
- This handles the nuances of odd/even negative numbers without explicitly counting them

### Observations:
 - If the given array only contains positive numbers: If this is the case, we can confidently say that the maximum product subarray will be the entire array itself.
 - If the given also array contains an even number of negative numbers: As we know, an even number of negative numbers always results in a positive number. So, also, in this case, the answer will be the entire array itself.
 - If the given array also contains an odd number of negative numbers: Now, an odd number of negative numbers when multiplied result in a negative number. Removal of 1 negative number out of the odd number of negative numbers will leave us with an even number of negatives. Hence the idea is to remove 1 negative number from the result. Now we need to decide which 1 negative number to remove such that the remaining subarray yields the maximum product.

For example, the given array is: {3, 2, -1, 4, -6, 3, -2, 6}
We will try to remove each possible negative number and check in which case the subarray yields the maximum product.

[3, 2, -1, 4, -6, 3, -2, 6]

- Case 1: If we remove -1
=> [3, 2,     -1,    4, -6, 3, -2, 6] 
   Product=6   ↑     Product = 864

- Case 2: If we remove -6
=> [3, 2, -1, 4,  -6,     3, -2, 6] 
   Product=-24     ↑    Product = -36

- Case 3: If we remove -2
=> [3, 2, -1, 4, -6, 3,    -2,     6] 
   Product=432              ↑   Product = 6

- Upon observation, we notice that each chosen negative number divides the array into two parts.
- The answer will either be the prefix or the suffix of that negative number.
- To find the answer, we will check all possible prefix subarrays (starting from index 0) and all possible suffix subarrays (starting from index n-1).
- The maximum product obtained from these prefix and suffix subarrays will be our final answer.
- If the array contains 0’s as well: We should never consider 0’s in our answer(as considering 0 will always result in 0) and we want to obtain the maximum possible product. So, we will divide the given array based on the location of the 0’s and apply the logic of case 3 for each subarray.

For example, the given array is: {-2, 3, 4, -1, 0, -2, 3, 1, 4, 0, 4, 6, -1, 4}.
 - In this case, we will divide the array into 3 different subarrays based on the 0’s locations. So, the subarrays will be {-2, 3, 4, -1}, {-2, 3, 1, 4}, and {4, 6, -1, 4}.
 - In these 3 subarrays, we will apply the logic discussed in case 3. We will get 3 different answers for 3 different subarrays.
 - The maximum one among those 3 answers will be the final answer.

Summary: In real-life problems, we will not separate out the cases as we did in the observations. Instead, we can directly apply the logic discussed in the 4th observation to any given subarray, and it will automatically handle all the other cases.

## Key Observations with Examples

### Observation 1: Positive Numbers Only
If the array contains only positive numbers, the maximum product will be the product of the entire array.
* Example: [1, 2, 3, 4] → Maximum product = 24

### Observation 2: Even Number of Negatives
With an even number of negative numbers, multiplying them all together results in a positive number, potentially maximizing the product.
* Example: [1, -2, 3, -4] → Maximum product = 24 (all elements)

### Observation 3: Odd Number of Negatives
With an odd number of negatives, we need to consider removing one negative number to maximize the product.
* Example: [1, -2, 3, -4, 5]
* Including all elements: 1 × (-2) × 3 × (-4) × 5 = 120
* Removing first negative: 1 × 3 × (-4) × 5 = -60
* Removing second negative: 1 × (-2) × 3 × 5 = -30
* Maximum product = 120

### Observation 4: Arrays with Zeros
Zeros act as dividers, reducing any product to zero. We should treat subarrays separated by zeros independently.
* Example: [2, 3, -2, 4, 0, 5, 6]
* Subarrays: [2, 3, -2, 4] and [5, 6]
* Maximum product = max(48, 30) = 48

### Observation 5: Prefix and Suffix Approach
Instead of explicitly handling all cases, we can use a prefix and suffix approach that automatically handles all scenarios.
```
Array: [2, 3, -2, 4]
Prefix products: 2, 6, -12, -48
Suffix products: 4, -8, -24, 2
Maximum product: 6
```

## Step-by-Step Example
Let's work through the array [1, 2, -3, 0, -4, -5]:

1. Step One:
   ```
   Initialize pre = 1, suff = 1, ans = Integer.MIN_VALUE
   i = 0: arr[0] = 1, arr[n-0-1] = -5
   pre = pre * arr[0] = 1 * 1 = 1
   suff = suff * arr[n-0-1] = 1 * (-5) = -5
   ans = max(ans, max(pre, suff)) = max(MIN_VALUE, max(1, -5)) = 1
   ```
   We calculate both prefix and suffix products simultaneously.

2. Step Two:
   ```
   i = 1: arr[1] = 2, arr[n-1-1] = -4
   pre = pre * arr[1] = 1 * 2 = 2
   suff = suff * arr[n-1-1] = -5 * (-4) = 20
   ans = max(ans, max(pre, suff)) = max(1, max(2, 20)) = 20
   ```
   The suffix product becomes positive as we multiply two negatives.

3. Step Three:
   ```
   i = 2: arr[2] = -3, arr[n-2-1] = 0
   pre = pre * arr[2] = 2 * (-3) = -6
   suff = suff * arr[n-2-1] = 20 * 0 = 0
   suff is reset to 1 (since it's 0)
   ans = max(ans, max(pre, suff)) = max(20, max(-6, 1)) = 20
   ```
   Notice how we reset suff when it becomes 0.

4. Step Four:
   ```
   i = 3: arr[3] = 0, arr[n-3-1] = -3
   pre = pre * arr[3] = -6 * 0 = 0
   pre is reset to 1 (since it's 0)
   suff = suff * arr[n-3-1] = 1 * (-3) = -3
   ans = max(ans, max(pre, suff)) = max(20, max(1, -3)) = 20
   ```
   We reset pre when it becomes 0.

5. Step Five:
   ```
   i = 4: arr[4] = -4, arr[n-4-1] = 2
   pre = pre * arr[4] = 1 * (-4) = -4
   suff = suff * arr[n-4-1] = -3 * 2 = -6
   ans = max(ans, max(pre, suff)) = max(20, max(-4, -6)) = 20
   ```

6. Step Six:
   ```
   i = 5: arr[5] = -5, arr[n-5-1] = 1
   pre = pre * arr[5] = -4 * (-5) = 20
   suff = suff * arr[n-5-1] = -6 * 1 = -6
   ans = max(ans, max(pre, suff)) = max(20, max(20, -6)) = 20
   ```
   Final result: 20

## Special Cases

### Case 1: All Positive Numbers
Input: [1, 2, 3, 4]
- Behavior: Product increases with each element
- Result: 24 (product of all elements)

### Case 2: Contains Zero
Input: [2, 3, 0, 4]
- Behavior: Zero resets the running product
- Result: 4 (from the subarray [4])

### Case 3: Odd Number of Negatives
Input: [2, -3, 4]
- Behavior: Prefix or suffix approach automatically finds maximum
- Result: 4 (from the subarray [4])
*/

public class Approach3a {
    // Approach 3a: Using prefix and suffix subarrays 
    public static int maxProductSubArray(int[] arr) {
        int n = arr.length; // Size of array
        
        int pre = 1;  // For calculating prefix products
        int suff = 1; // For calculating suffix products
        int ans = Integer.MIN_VALUE; // Initialize answer to smallest possible value
        
        for (int i = 0; i < n; i++) {
            // Reset prefix product to 1 if it becomes 0
            // This is crucial for handling arrays with zeros
            if (pre == 0) pre = 1;
            
            // Reset suffix product to 1 if it becomes 0
            if (suff == 0) suff = 1;
            
            // Calculate prefix product by multiplying from left to right
            pre *= arr[i];
            
            // Calculate suffix product by multiplying from right to left
            suff *= arr[n - i - 1];
            
            // Update answer with the maximum value found so far
            // This automatically handles all scenarios including odd/even negatives
            ans = Math.max(ans, Math.max(pre, suff));
        }
        
        return ans;
    }

    public static void main(String[] args) {
        // Example array containing a mix of positive, negative numbers and zero
        int[] arr = {1, 2, -3, 0, -4, -5};
        
        // Calculate the maximum product subarray
        int answer = maxProductSubArray(arr);
        
        // Display the result
        System.out.println("The maximum product subarray is: " + answer);
    }
}

/*
# Algorithm
1. We will first declare 2 variables i.e. ‘pre’(stores the product of the prefix subarray) and ‘suff’(stores the product of the suffix subarray). They both will be initialized with 1(as we want to store the product).
2. Now, we will use a loop(say i) that will run from 0 to n-1.
3. We have to check 2 cases to handle the presence of 0:
 - If pre = 0: This means the previous element was 0. So, we will consider the current element as a part of the new subarray. So, we will set ‘pre’ to 1.
 - If suff = 0: This means the previous element was 0 in the suffix. So, we will consider the current element as a part of the new suffix subarray. So, we will set ‘suff’ to 1.
4. Next, we will multiply the elements from the starting index with ‘pre’ and the elements from the end with ‘suff’. To incorporate both cases inside a single loop, we will do the following:
 - We will multiply arr[i] with ‘pre’ i.e. pre *= arr[i].
 - We will multiply arr[n-i-1] with ‘suff’ i.e. suff *= arr[n-i-1].
5. After each iteration, we will consider the maximum among the previous answer, ‘pre’ and ‘suff’ i.e. max(previous_answer, pre, suff).
6. Finally, we will return the maximum product.

### Time Complexity Breakdown per Step
1. Initialization: O(1)
2. Single pass through array: O(n)
   - Each operation inside the loop is O(1)

Total: O(n) where n is the array length

### Space Complexity Breakdown
1. Auxiliary Space: O(1)
   - We only use three variables regardless of input size
2. Input Space: O(n)
   - The input array of size n

Total: O(1) auxiliary space

# Advantages
1. Handles all cases (positive numbers, negative numbers, zeros) with a single approach
2. Optimal time complexity of O(n)
3. Constant space complexity O(1)
4. No need to explicitly separate logic for different scenarios
5. Avoids the use of multiple passes through the array

# Limitations
1. Might be less intuitive to understand at first glance
2. Doesn't explicitly show which subarray produces the maximum product
3. Might face integer overflow for large arrays with large values

# Potential Improvements
1. Return the actual subarray that gives the maximum product
2. Use long instead of int to handle larger products
3. Add early termination conditions for common cases (all positive)

# Detailed Dry Run of Maximum Product Subarray Algorithm

## Input Array: [1, 2, -3, 0, -4, -5]

### Execution Table with Complete Details

| Step | Index (i) | Element (arr[i]) | Element from Right (arr[n-i-1]) | Pre Before | Pre After | Suff Before | Suff After | Current Max | Answer | Detailed Notes |
|------|-----------|------------------|----------------------------------|------------|-----------|-------------|------------|-------------|--------|---------------|
| Init | - | - | - | 1 | 1 | 1 | 1 | - | MIN_VALUE | Initialize pre=1, suff=1, ans=MIN_VALUE |
| 1 | 0 | 1 | -5 | 1 | 1 | 1 | -5 | 1 | 1 | pre=1×1=1, suff=1×(-5)=-5, ans=max(MIN_VALUE, max(1,-5))=1 |
| 2 | 1 | 2 | -4 | 1 | 2 | -5 | 20 | 20 | 20 | pre=1×2=2, suff=(-5)×(-4)=20, ans=max(1, max(2,20))=20 |
| 3 | 2 | -3 | 0 | 2 | -6 | 20 | 0→1 | 1 | 20 | pre=2×(-3)=-6, suff=20×0=0 (reset to 1), ans=max(20, max(-6,1))=20 |
| 4 | 3 | 0 | -3 | -6 | 0→1 | 1 | -3 | 1 | 20 | pre=-6×0=0 (reset to 1), suff=1×(-3)=-3, ans=max(20, max(1,-3))=20 |
| 5 | 4 | -4 | 2 | 1 | -4 | -3 | -6 | -4 | 20 | pre=1×(-4)=-4, suff=(-3)×2=-6, ans=max(20, max(-4,-6))=20 |
| 6 | 5 | -5 | 1 | -4 | 20 | -6 | -6 | 20 | 20 | pre=(-4)×(-5)=20, suff=(-6)×1=-6, ans=max(20, max(20,-6))=20 |

### Final Result: 20

## Step-by-Step Explanation of Each Operation

1. **Initialization Phase:**
   - We start with pre = 1, suff = 1, and ans = Integer.MIN_VALUE
   - pre tracks products from left to right (prefix products)
   - suff tracks products from right to left (suffix products)
   - ans stores the maximum product found so far

2. **Step 1 (i=0):**
   - We process the first element from left (1) and the first element from right (-5)
   - Pre calculation: pre = 1 × 1 = 1
   - Suff calculation: suff = 1 × (-5) = -5
   - Current maximum between pre and suff is 1
   - Update ans = max(MIN_VALUE, 1) = 1

3. **Step 2 (i=1):**
   - We process the second element from left (2) and the second element from right (-4)
   - Pre calculation: pre = 1 × 2 = 2
   - Suff calculation: suff = (-5) × (-4) = 20 (two negatives multiply to positive)
   - Current maximum between pre and suff is 20
   - Update ans = max(1, 20) = 20

4. **Step 3 (i=2):**
   - We process the third element from left (-3) and the third element from right (0)
   - Pre calculation: pre = 2 × (-3) = -6
   - Suff calculation: suff = 20 × 0 = 0
   - Since suff becomes 0, we reset it to 1 (this effectively starts a new subarray)
   - Current maximum between pre and suff is 1
   - Update ans = max(20, 1) = 20 (no change)

5. **Step 4 (i=3):**
   - We process the fourth element from left (0) and the fourth element from right (-3)
   - Pre calculation: pre = (-6) × 0 = 0
   - Since pre becomes 0, we reset it to 1 (this effectively starts a new subarray)
   - Suff calculation: suff = 1 × (-3) = -3
   - Current maximum between pre and suff is 1
   - Update ans = max(20, 1) = 20 (no change)

6. **Step 5 (i=4):**
   - We process the fifth element from left (-4) and the fifth element from right (2)
   - Pre calculation: pre = 1 × (-4) = -4
   - Suff calculation: suff = (-3) × 2 = -6
   - Current maximum between pre and suff is -4
   - Update ans = max(20, -4) = 20 (no change)

7. **Step 6 (i=5):**
   - We process the sixth element from left (-5) and the sixth element from right (1)
   - Pre calculation: pre = (-4) × (-5) = 20 (two negatives multiply to positive)
   - Suff calculation: suff = (-6) × 1 = -6
   - Current maximum between pre and suff is 20
   - Update ans = max(20, 20) = 20 (no change)

8. **Final Result:**
   - After processing all elements, the maximum product is 20

## Key Insights from the Execution:

1. **Handling of Zeros:**
   - When we encounter a zero in step 3 and step 4, we reset the product to 1
   - This effectively divides the array into subarrays separated by zeros
   - In our example, the zero at index 3 creates two subarrays: [1, 2, -3] and [-4, -5]

2. **Handling of Negative Numbers:**
   - In step 2, two negative numbers (-5 and -4) multiply to give a positive result (20)
   - In step 6, two negative numbers (-4 and -5) multiply to give a positive result (20)
   - This demonstrates how the approach automatically handles even numbers of negatives

3. **Maximum Product Found:**
   - The maximum product of 20 is actually found from the suffix product in step 2
   - This corresponds to the subarray [-4, -5] from the end of the array
   - The same product is also found from the prefix calculation in step 6

4. **Why This Approach Works:**
   - By calculating both prefix and suffix products simultaneously, we ensure we don't miss any potential maximum
   - This elegantly handles the case where removing one negative number might maximize the product
   - The reset mechanism for zeros ensures we correctly handle all subarrays

## Subarrays Considered:
- From prefix calculations: [1], [1,2], [1,2,-3], [1,2,-3,0], [-4], [-4,-5]
- From suffix calculations: [-5], [-5,-4], [-5,-4,0], [-5,-4,0,-3], [-5,-4,0,-3,2], [-5,-4,0,-3,2,1]
- Notable subarrays with high products:
  - [-4,-5] with product 20 (from prefix in last step)
  - [-4,-5] with product 20 (from suffix in second step, processed from right)

## Final Maximum Product: 20

### Step-by-Step Explanation

1. **Initialize Variables**
   - pre = 1, suff = 1, ans = MIN_VALUE
   ```
   pre: 1
   suff: 1
   ans: MIN_VALUE
   ```

2. **Process First Element (i=0)**
   - Update pre with arr[0] = 1: pre = 1 * 1 = 1
   - Update suff with arr[n-0-1] = arr[5] = -5: suff = 1 * (-5) = -5
   - Update ans = max(MIN_VALUE, max(1, -5)) = 1
   ```
   pre: 1
   suff: -5
   ans: 1
   ```

3. **Process Second Element (i=1)**
   - Update pre with arr[1] = 2: pre = 1 * 2 = 2
   - Update suff with arr[n-1-1] = arr[4] = -4: suff = (-5) * (-4) = 20
   - Update ans = max(1, max(2, 20)) = 20
   ```
   pre: 2
   suff: 20
   ans: 20
   ```

4. **Process Third Element (i=2)**
   - Update pre with arr[2] = -3: pre = 2 * (-3) = -6
   - Update suff with arr[n-2-1] = arr[3] = 0: suff = 20 * 0 = 0
   - Reset suff to 1 (since it's 0)
   - Update ans = max(20, max(-6, 1)) = 20
   ```
   pre: -6
   suff: 1
   ans: 20
   ```

5. **Process Fourth Element (i=3)**
   - Update pre with arr[3] = 0: pre = (-6) * 0 = 0
   - Reset pre to 1 (since it's 0)
   - Update suff with arr[n-3-1] = arr[2] = -3: suff = 1 * (-3) = -3
   - Update ans = max(20, max(1, -3)) = 20
   ```
   pre: 1
   suff: -3
   ans: 20
   ```

6. **Process Fifth Element (i=4)**
   - Update pre with arr[4] = -4: pre = 1 * (-4) = -4
   - Update suff with arr[n-4-1] = arr[1] = 2: suff = (-3) * 2 = -6
   - Update ans = max(20, max(-4, -6)) = 20
   ```
   pre: -4
   suff: -6
   ans: 20
   ```

7. **Process Sixth Element (i=5)**
   - Update pre with arr[5] = -5: pre = (-4) * (-5) = 20
   - Update suff with arr[n-5-1] = arr[0] = 1: suff = (-6) * 1 = -6
   - Update ans = max(20, max(20, -6)) = 20
   ```
   pre: 20
   suff: -6
   ans: 20
   ```

### Additional Example Cases

1. **All Positive Numbers**
```
Input:  [1, 2, 3, 4]
Process: pre calculates 1→1→2→6→24, suff calculates 1→4→12→24
Output: 24
```

2. **Array with Zeros**
```
Input:  [2, 3, 0, 4, 5]
Process: pre calculates 2→6→0→(reset)→4→20, suff calculates 5→20→0→(reset)→3→6
Output: 20
```

### Edge Cases Handling
1. **Empty Array**
   ```
   Input: []
   Output: Integer.MIN_VALUE (or could be modified to return 0)
   The function would not enter the loop and return the initial value of ans.
   ```

2. **Single Element Array**
   ```
   Input: [5]
   Output: 5
   Both pre and suff will be 5, and ans will be updated to 5.
   ```

3. **Array with All Zeros**
   ```
   Input: [0, 0, 0]
   Output: 0
   Both pre and suff will be reset to 1 after becoming 0, but ans will still be 0.
   ```

Key Observations:
1. The simultaneous prefix and suffix approach automatically handles the scenario of odd number of negatives
2. Zero handling is critical - resetting pre and suff to 1 after encountering a zero effectively creates subarray divisions
3. The maximum value is always updated regardless of whether pre or suff is larger

Sample Validation:
Input: [1, 2, -3, 0, -4, -5]
Expected: 20
Output: 20

TEST CASES:
1. Input: [2, 3, -2, 4]
   Expected: 6
   Output: 6
2. Input: [-2, 0, -1]
   Expected: 0
   Output: 0
3. Input: [-2]
   Expected: -2
   Output: -2
4. Input: [0, 2]
   Expected: 2
   Output: 2
 */
