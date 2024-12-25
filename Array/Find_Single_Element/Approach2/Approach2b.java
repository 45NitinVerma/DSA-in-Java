// Approach 2b: Using HashMap 
package Array.Find_Single_Element.Approach2;
import java.util.HashMap;
import java.util.Map;

public class Approach2b {
    public static int getSingleElement(int []arr) {
        //size of the array:
        int n = arr.length;

        // Declare the hashmap and hash the given array:
        HashMap<Integer, Integer> mpp = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int value = mpp.getOrDefault(arr[i], 0);
            mpp.put(arr[i], value + 1);
        }

        //Find the single element and return the answer:
        for (Map.Entry<Integer, Integer> it : mpp.entrySet()) {
            if (it.getValue() == 1) {
                return it.getKey();
            }
        }

        //This line will never execute if array contains a single element
        return -1;
    }

    public static void main(String args[]) {
        int[] arr = {4, 1, 2, 1, 2};
        int ans = getSingleElement(arr);
        System.out.println("The single element is: " + ans);
    }
}

/*
  Approach2b: Using HashMap to find the single element in an array where every other element appears twice.
  
  ALGORITHM:
  1. Initialize a HashMap to store element frequencies.
  2. First pass through the array:
     a. For each element, increment its count in the HashMap.
     b. Use getOrDefault to handle first occurrences.
  3. Second pass through HashMap entries:
     a. Find the entry with frequency = 1.
     b. Return that element's key.
  4. Return -1 if no single element is found.
  
  Complexity Analysis:
  TIME COMPLEXITY:
  - O(n) where n is the length of the input array.
     * First loop: O(n) for populating the HashMap.
     * Second loop: O(d) where d is the number of distinct elements.
     * HashMap operations (put, get) are O(1) on average.
  
  SPACE COMPLEXITY:
  - Auxiliary Space: O(d) where d is the number of distinct elements.
     * HashMap stores at most d entries
     * In worst case, d = n/2 + 1 (when all elements except one appear twice)
  - Input Space: O(n) for the input array.
  
  * Given the problem constraints that all elements appear twice except one:
    - For an array of size n, d = (n + 1) / 2.
    - Example: In the array [4, 1, 2, 1, 2], n = 5, d = 3 unique elements (4, 1, 2).
  
  Example Calculation:
  - For the array [4, 1, 2, 1, 2] of length n = 5:
     * d (distinct elements) = 3.
     * Memory used = O(d) = O(3).
     * Time = O(n) = O(5) for the first loop + O(d) = O(3) for the second loop.
  
 Advantages:
  1. Simple and intuitive implementation.
  2. O(n) time complexity.
  3. Works with unsorted arrays.
  4. Handles any range of integer values.
  
  Limitations:
  1. Extra space required for HashMap.
  2. Not optimal for memory-constrained systems.
  3. Slightly more overhead than XOR approach.
  
  Detailed Walkthrough:
  Input Array: [4, 1, 2, 1, 2]
  -------------------------------------------------------------------------
  Step 1: Building HashMap
  Initial HashMap: {}
  
  -------------------------------------------------------------------------
  Array Index | Current Element | HashMap After Operation
  -------------------------------------------------------------------------
  i=0         | 4               | {4=1}
  i=1         | 1               | {4=1, 1=1}
  i=2         | 2               | {4=1, 1=1, 2=1}
  i=3         | 1               | {4=1, 1=2, 2=1}
  i=4         | 2               | {4=1, 1=2, 2=2}
  -------------------------------------------------------------------------
  
  Step 2: Finding Single Element
  HashMap entries: {4=1, 1=2, 2=2}
  - Check 4: count=1 ✓ (Found our answer!)
  - No need to check further
  -------------------------------------------------------------------------

Final Result: 4 (the only element with frequency 1)
*/