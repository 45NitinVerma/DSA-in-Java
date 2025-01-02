// Approach 2: Using HashMap
package Array.Longest_Subarray_1;
import java.util.HashMap;
import java.util.Map;

public class Approach2 {
    public static int getLongestSubarray(int []a, long k) {
        int n = a.length; // size of the array
        
        // HashMap to store prefix sum and corresponding index
        Map<Long, Integer> preSumMap = new HashMap<>();
        long sum = 0;
        int maxLen = 0;
        
        for (int i = 0; i < n; i++) {
            // Calculate the prefix sum till index i
            sum += a[i];

            // If current sum equals k, update maxLen with current index + 1
            if (sum == k) {
                maxLen = Math.max(maxLen, i + 1);
            }

            // Calculate remainder needed for target sum
            long rem = sum - k;

            // If remainder exists in map, update maxLen if current length is greater
            if (preSumMap.containsKey(rem)) {
                int len = i - preSumMap.get(rem);
                maxLen = Math.max(maxLen, len);
            }

            // Only store first occurrence of sum to get maximum length
            if (!preSumMap.containsKey(sum)) {
                preSumMap.put(sum, i);
            }
        }
        return maxLen;
    }

    public static void main(String[] args) {
        int[] a = {2, 3, 5, 1, 9};
        long k = 10;
        int len = getLongestSubarray(a, k);
        System.out.println("The length of the longest subarray is: " + len);
    }
}

/* 
 * ALGORITHM:
 * 1. Initialize a `Map` to store prefix sums and their first occurrence indices.
 * 2. Traverse the array while calculating the running prefix sum (`sum`).
 * 3. For each index `i`:
 *    a. If `sum == k`, update `maxLen` to `i + 1`.
 *    b. Calculate the remaining part, `rem = sum - k`.
 *       - If `rem` exists in the map, calculate the subarray length (`len = i - preSumMap.get(rem)`) 
 *         and update `maxLen` if this subarray is longer.
 *    c. If `sum` is not already in the map, store it with the current index.
 * 4. Return the maximum length of the subarray with sum `k`.
*/

/* 
Complexity Analysis:
TIME COMPLEXITY: O(n)
- Single pass through array
- HashMap operations are O(1) average case

SPACE COMPLEXITY: 
1. Input Space: O(n)
   - Input array of size n
   - Target sum k uses O(1)

2. Auxiliary Space: O(n)
   - HashMap stores at most n prefix sums: O(n)
   - Variables (sum, maxLen, n): O(1)
   - Total auxiliary space: O(n)

Total Space: O(n) input + O(n) auxiliary = O(n)

Advantages:
1. Linear time complexity
2. Handles both positive and negative numbers
3. Works with any target sum k
4. Single pass solution

Limitations:
1. Uses extra space for HashMap
2. May have collision overhead in HashMap
3. Not suitable for streaming data

Detailed Walkthrough with Example:
Input: a = [2, 3, 5, 1, 9], k = 10

Step-by-Step Process for Array [2, 3, 5, 1, 9] with target k = 10
--------------------------------------------------------------------------------------------------------
Index | Current | Prefix | Remainder | Map Contents    | Max    | Detailed Explanation
      | Number  | Sum    | (sum - k) | (Sum -> Index)  | Length |
--------------------------------------------------------------------------------------------------------
0     | 2      | 2      | -8        | {2->0}          | 0      | - First element: 2
                                                               | - No subarray with sum 10 yet
                                                               | - Store prefix sum 2 at index 0
                                                               | - Remainder -8 not in map

1     | 3      | 5      | -5        | {2->0, 5->1}    | 0      | - Add 3 to prefix sum: 2+3 = 5
                                                               | - Still no subarray with sum 10
                                                               | - Store prefix sum 5 at index 1
                                                               | - Remainder -5 not in map

2     | 5      | 10     | 0         | {2->0,          | 3      | - Add 5 to prefix sum: 5+5 = 10
                                      5->1,                    | - Direct match! sum equals k
                                      10->2}                   | - Update maxLen = i+1 = 3
                                                               | - Store prefix sum 10 at index 2
                                                               | - Found subarray [2,3,5]

3     | 1      | 11     | 1         | {2->0,          | 3      | - Add 1 to prefix sum: 10+1 = 11
                                      5->1,                    | - Check if (11-10=1) exists in map
                                      10->2}                   | - Remainder 1 not in map
                                                               | - MaxLen remains 3

4     | 9      | 20     | 10        | {2->0,          | 3      | - Add 9 to prefix sum: 11+9 = 20
                                      5->1,                    | - Check if (20-10=10) exists in map
                                      10->2}                   | - Found remainder 10 at index 2!
                                                               | - Possible length = 4-2 = 2
                                                               | - Not longer than current maxLen
--------------------------------------------------------------------------------------------------------
Final Result: 3 (subarray [3, 5, 1])

Key Observations:
1. Prefix Sum Logic:
   - At each step, we add current number to running sum
   - Check if (current sum - k) exists in map
   - If exists, we found a subarray with sum k

2. MaxLen Updates:
   - Updates in two cases:
     a. When prefix sum equals k (entire subarray from start)
     b. When remainder exists in map (subarray between two indices)

3. Map Usage:
   - Stores first occurrence of each prefix sum
   - Helps find complementary sums quickly
   - Key = prefix sum, Value = index

4. Important Points:
   - Only first occurrence of sum is stored
   - Multiple valid subarrays possible
   - Always keeps track of maximum length found
   - Handles both positive and negative numbers

Sample Validation:
- Final subarray [3,5,1]:
  * Sum = 3 + 5 + 1 = 9
  * Length = 3
  * Validates our algorithm's result

Key Points:
1. Only stores first occurrence of each prefix sum
2. Uses remainder to find complementary prefix sum
3. Updates maxLen only when longer subarray found
4. Handles multiple valid subarrays by tracking maximum
*/

/*
Q. Why Store Only First Occurrence?
if (!preSumMap.containsKey(sum)) {
        preSumMap.put(sum, i);
    }

Array: [2,0,0,2], k=3

Step-by-Step Walkthrough:
-------------------------------------------------------------------
Index | Number | Prefix | Check        | Map After     | Max  | Why Important
      |        | Sum    | Remainder    | Step          | Len  |
-------------------------------------------------------------------
0     | 2      | 2      | rem = -1     | {2→0}         | 0    | First entry
                                                              | Stores first occurrence

1     | 0      | 2      | rem = -1     | {2→0}         | 0    | Duplicate prefix sum!
                                                              | Don't update map kyunki
                                                              | pehla index chota hai

2     | 0      | 2      | rem = -1     | {2→0}         | 0    | Same situation
                                                              | Map update nahi karenge

3     | 2      | 4      | rem = 1      | {2→0, 4→3}    | 3    | Finally new sum
                                                              | Check kiya 4-3=1
-------------------------------------------------------------------

Final Answer: 3 (subarray [0,0,2])

Agar hum har baar map update karte toh:
- Map mein {2→2} store hota index 2 pe
- Last mein jab 4-3=1 ka remainder check karte
- Humein chota subarray milta [2] instead of [0,0,2]

Isliye rule hai: Sirf pehli baar prefix sum store karo. 
First occurrence gives longest possible subarray!

Yeh condition kyun important hai?
- Hume sirf pehla occurrence chahiye prefix sum ka
- Agar baad mein same sum mile, toh purana index hi useful rahega longest subarray ke liye
- Agar update karte rahe toh chote subarray mil sakte hai
*/

 /* 
  * <!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Approach2 Program Visualization</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 20px; }
        .step { margin-bottom: 20px; padding: 10px; border: 1px solid #ccc; border-radius: 5px; }
        .highlight { background-color: #f0f8ff; }
        .array-container { display: flex; align-items: center; }
        .array-element { width: 40px; height: 40px; display: flex; justify-content: center; align-items: center; border: 1px solid #000; margin: 5px; }
        .prefix-sum { font-weight: bold; }
        .remainder { color: red; font-style: italic; }
        table { width: 100%; border-collapse: collapse; margin-top: 20px; }
        th, td { border: 1px solid #ddd; padding: 8px; text-align: left; }
        th { background-color: #f2f2f2; }
    </style>
</head>
<body>
    <h1>Visualization of Approach2 Program Execution</h1>
    <p>This program finds the length of the longest subarray with a sum equal to a given value <code>k</code>.</p>
    <h2>Program Code</h2>
    <pre><code>
public class Approach2 {
    public static int getLongestSubarray(int []a, long k) {
        int n = a.length; // size of the array
        
        // HashMap to store prefix sum and corresponding index
        Map&lt;Long, Integer&gt; preSumMap = new HashMap&lt;&gt;();
        long sum = 0;
        int maxLen = 0;
        
        for (int i = 0; i &lt; n; i++) {
            // Calculate the prefix sum till index i
            sum += a[i];

            // If current sum equals k, update maxLen with current index + 1
            if (sum == k) {
                maxLen = Math.max(maxLen, i + 1);
            }

            // Calculate remainder needed for target sum
            long rem = sum - k;

            // If remainder exists in map, update maxLen if current length is greater
            if (preSumMap.containsKey(rem)) {
                int len = i - preSumMap.get(rem);
                maxLen = Math.max(maxLen, len);
            }

            // Only store first occurrence of sum to get maximum length
            if (!preSumMap.containsKey(sum)) {
                preSumMap.put(sum, i);
            }
        }
        return maxLen;
    }

    public static void main(String[] args) {
        int[] a = {2, 3, 5, 1, 9};
        long k = 10;
        int len = getLongestSubarray(a, k);
        System.out.println("The length of the longest subarray is: " + len);
    }
}
    </code></pre>

    <h2>Step-by-Step Visualization</h2>
    <div class="step">
        <h3>Initialization</h3>
        <p>The array <code>a = [2, 3, 5, 1, 9]</code> and the target sum <code>k = 10</code> are initialized.</p>
        <p>A <code>HashMap</code> named <code>preSumMap</code> is created to store prefix sums and their corresponding indices.</p>
        <p>Variables <code>sum</code> and <code>maxLen</code> are initialized to 0.</p>
        <div class="array-container">
            <div class="array-element">2</div>
            <div class="array-element">3</div>
            <div class="array-element">5</div>
            <div class="array-element">1</div>
            <div class="array-element">9</div>
        </div>
        <p><code>preSumMap = {}</code></p>
        <p><code>sum = 0</code></p>
        <p><code>maxLen = 0</code></p>
    </div>
    <div class="step">
        <h3>Iteration 1 (i = 0)</h3>
        <p><code>sum = sum + a[0] = 0 + 2 = 2</code></p>
        <p>Since <code>sum != k</code>, we calculate <code>rem = sum - k = 2 - 10 = -8</code>.</p>
        <p><code>preSumMap</code> does not contain <code>rem</code>, so no update to <code>maxLen</code>.</p>
        <p><code>preSumMap</code> is updated with <code>{2: 0}</code>.</p>
        <div class="array-container">
            <div class="array-element highlight">2</div>
            <div class="array-element">3</div>
            <div class="array-element">5</div>
            <div class="array-element">1</div>
            <div class="array-element">9</div>
        </div>
        <p><code>preSumMap = {2: 0}</code></p>
        <p><code>sum = 2</code></p>
        <p><code>maxLen = 0</code></p>
    </div>
    <div class="step">
        <h3>Iteration 2 (i = 1)</h3>
        <p><code>sum = sum + a[1] = 2 + 3 = 5</code></p>
        <p>Since <code>sum != k</code>, we calculate <code>rem = sum - k = 5 - 10 = -5</code>.</p>
        <p><code>preSumMap</code> does not contain <code>rem</code>, so no update to <code>maxLen</code>.</p>
        <p><code>preSumMap</code> is updated with <code>{2: 0, 5: 1}</code>.</p>
        <div class="array-container">
            <div class="array-element highlight">2</div>
            <div class="array-element highlight">3</div>
            <div class="array-element">5</div>
            <div class="array-element">1</div>
            <div class="array-element">9</div>
        </div>
        <p><code>preSumMap = {2: 0, 5: 1}</code></p>
        <p><code>sum = 5</code></p>
        <p><code>maxLen = 0</code></p>
    </div>
    <div class="step">
        <h3>Iteration 3 (i = 2)</h3>
        <p><code>sum = sum + a[2] = 5 + 5 = 10</code></p>
        <p>Since <code>sum == k</code>, <code>maxLen = Math.max(maxLen, i + 1) = Math.max(0, 3) = 3</code>.</p>
        <p>No need to calculate <code>rem</code> as <code>sum == k</code>.</p>
        <p><code>preSumMap</code> is updated with <code>{2: 0, 5: 1, 10: 2}</code>.</p>
        <div class="array-container">
            <div class="array-element highlight">2</div>
            <div class="array-element highlight">3</div>
            <div class="array-element highlight">5</div>
            <div class="array-element">1</div>
            <div class="array-element">9</div>
        </div>
        <p><code>preSumMap = {2: 0, 5: 1, 10: 2}</code></p>
        <p><code>sum = 10</code></p>
        <p><code>maxLen = 3</code></p>
    </div>
    <div class="step">
        <h3>Iteration 4 (i = 3)</h3>
        <p><code>sum = sum + a[3] = 10 + 1 = 11</code></p>
        <p>Since <code>sum != k</code>, we calculate <code>rem = sum - k = 11 - 10 = 1</code>.</p>
        <p><code>preSumMap</code> does not contain <code>rem</code>, so no update to <code>maxLen</code>.</p>
        <p><code>preSumMap</code> is updated with <code>{2: 0, 5: 1, 10: 2, 11: 3}</code>.</p>
        <div class="array-container">
            <div class="array-element highlight">2</div>
            <div class="array-element highlight">3</div>
            <div class="array-element highlight">5</div>
            <div class="array-element highlight">1</div>
            <div class="array-element">9</div>
        </div>
        <p><code>preSumMap = {2: 0, 5: 1, 10: 2, 11: 3}</code></p>
        <p><code>sum = 11</code></p>
        <p><code>maxLen = 3</code></p>
    </div>
    <div class="step">
        <h3>Iteration 5 (i = 4)</h3>
        <p><code>sum = sum + a[4] = 11 + 9 = 20</code></p>
        <p>Since <code>sum != k</code>, we calculate <code>rem = sum - k = 20 - 10 = 10</code>.</p>
        <p><code>preSumMap</code> contains <code>rem</code>, so we calculate <code>len = i - preSumMap.get(rem) = 4 - 2 = 2</code>.</p>
        <p><code>maxLen = Math.max(maxLen, len) = Math.max(3, 2) = 3</code>.</p>
        <p><code>preSumMap</code> is updated with <code>{2: 0, 5: 1, 10: 2, 11: 3, 20: 4}</code>.</p>
        <div class="array-container">
            <div class="array-element highlight">2</div>
            <div class="array-element highlight">3</div>
            <div class="array-element highlight">5</div>
            <div class="array-element highlight">1</div>
            <div class="array-element highlight">9</div>
        </div>
        <p><code>preSumMap = {2: 0, 5: 1, 10: 2, 11: 3, 20: 4}</code></p>
        <p><code>sum = 20</code></p>
        <p><code>maxLen = 3</code></p>
    </div>
    <div class="step">
        <h3>Result</h3>
        <p>The length of the longest subarray with sum equal to <code>k = 10</code> is <code>3</code>.</p>
        <p>The subarray is <code>[2, 3, 5]</code>.</p>
        <div class="array-container">
            <div class="array-element highlight">2</div>
            <div class="array-element highlight">3</div>
            <div class="array-element highlight">5</div>
            <div class="array-element">1</div>
            <div class="array-element">9</div>
        </div>
    </div>

    <h2>Debugging Table</h2>
    <table>
        <thead>
            <tr>
                <th>Iteration (i)</th>
                <th>Array Element (a[i])</th>
                <th>Prefix Sum (sum)</th>
                <th>Remainder (rem)</th>
                <th>preSumMap</th>
                <th>maxLen</th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <td>0</td>
                <td>2</td>
                <td class="prefix-sum">2</td>
                <td class="remainder">-8</td>
                <td>{2: 0}</td>
                <td>0</td>
            </tr>
            <tr>
                <td>1</td>
                <td>3</td>
                <td class="prefix-sum">5</td>
                <td class="remainder">-5</td>
                <td>{2: 0, 5: 1}</td>
                <td>0</td>
            </tr>
            <tr>
                <td>2</td>
                <td>5</td>
                <td class="prefix-sum">10</td>
                <td class="remainder">0</td>
                <td>{2: 0, 5: 1, 10: 2}</td>
                <td>3</td>
            </tr>
            <tr>
                <td>3</td>
                <td>1</td>
                <td class="prefix-sum">11</td>
                <td class="remainder">1</td>
                <td>{2: 0, 5: 1, 10: 2, 11: 3}</td>
                <td>3</td>
            </tr>
            <tr>
                <td>4</td>
                <td>9</td>
                <td class="prefix-sum">20</td>
                <td class="remainder">10</td>
                <td>{2: 0, 5: 1, 10: 2, 11: 3, 20: 4}</td>
                <td>3</td>
            </tr>
        </tbody>
    </table>
</body>
</html>
  */