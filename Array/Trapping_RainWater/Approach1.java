package Array.Trapping_RainWater;
/* 
# Trapping Rain Water
# Problem : Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it can trap after raining.

## Example 1:
### Visualisation of the elevation map:

Input: height = [0,1,0,2,1,0,1,3,2,1,2,1]
Output: 6
Explanation: The above elevation map (black section) is represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. In this case, 6 units of rain water (blue section) are being trapped.

# Trapping Rain Water Problem

# Problem Title
Compute Maximum Water Trapped Between Elevation Bars

# Intuition
The problem involves calculating the total amount of rainwater that can be trapped between bars of different heights. The key insight is understanding how water gets "trapped" between higher boundary bars.

## Basic Understanding
Given an array representing bar heights, determine how much water can be collected between these bars, where each bar has a width of 1 unit.

## Key Observations

### Observation 1: Minimum Bar Requirement
- At least 3 bars are required to trap water
- Water can only be trapped between higher boundary bars

### Observation 2: Water Trapping Conditions
- No water is trapped if bars are in strictly ascending or descending order
- Water trapping depends on the minimum height of left and right boundary bars

### Observation 3: Water Calculation Principle
- Water trapped at a specific bar = min(left max height, right max height) - current bar height
- If the calculated value is negative, no water is trapped

### Observation 4: Boundary Maximum Arrays
- Create two auxiliary arrays:
  * `leftMax`: Stores maximum height from left side
  * `rightMax`: Stores maximum height from right side

## Visualization
```
Input: [0,1,0,2,1,0,1,3,2,1,2,1]

Water Trapping Visualization:
   #
 # # #   #
 # # # # # #
 0 1 0 2 1 0 1 3 2 1 2 1  <- Bar Heights
   ↑ ↑ ↑ ↑ ↑ ↑           <- Trapped Water Areas
```
*/

public class Approach1 {
    public static int trap(int height[]){
        int n = height.length;
        // Calculate left max boundary array
        int leftMax[] = new int[n];
        leftMax[0] = height[0];
        for(int i=1; i<n; i++){
            leftMax[i] = Math.max(leftMax[i-1], height[i]);
        }

        // Calculate right max boundary array
        int rightMax[] = new int[n];
        rightMax[height.length-1] = height[n-1];
        for(int i=n-2; i>=0; i--){
            rightMax[i] = Math.max(rightMax[i+1], height[i]);
        }

        // Loop
        int trappedWater = 0;
        for(int i=0; i<n; i++){
            // waterLevel = min(leftMax, rightMax)
            int waterLevel = Math.min(leftMax[i], rightMax[i]);

            // trappedWater = waterLevel - height[i]
            trappedWater += waterLevel - height[i];
        }

        return trappedWater;
    }
    public static void main(String[] args) {
        int height[] = {0,1,0,2,1,0,1,3,2,1,2,1};
        System.out.println(trap(height));
    }
}

/*
# Algorithm
1. Create left maximum boundary array
2. Create right maximum boundary array
3. Iterate through each bar
4. Calculate water level at each bar
5. Accumulate trapped water

## Time Complexity Breakdown
1. Left Max Array Creation: O(n)
2. Right Max Array Creation: O(n)
3. Water Calculation Loop: O(n)
Total: O(n)

## Space Complexity Breakdown
1. Auxiliary Space (Left Max Array): O(n)
2. Auxiliary Space (Right Max Array): O(n)
Total: O(n)

# Advantages
1. Single pass solution
2. Easy to understand and implement
3. Handles complex elevation maps
4. Efficient time complexity

# Limitations
1. Requires additional space O(n)
2. Works only for non-negative integer heights
3. Assumes uniform bar width of 1 unit

# Potential Improvements
1. Two-pointer approach for O(1) space complexity
2. Handle floating-point height values
3. Add input validation

# Step-by-Step Process with Dry Run

## Example Input: [0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1]

### Detailed Execution Table
```
Step | Array State     | Action                     | Trapped Water | Explanation
------|-----------------|----------------------------|--------------|-------------
1     | Left Max Array  | [0, 1, 1, 2, 2, 2, 2, 3, 3, 3, 3, 3] | - | Compute max from left
2     | Right Max Array | [3, 3, 3, 3, 3, 3, 3, 3, 2, 2, 2, 1] | - | Compute max from right
3     | Water Calc      | Iterate and compute        | 6           | Sum trapped water
```

# Test Cases
1. Input: [0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1]
   Expected: 6
   Output: 6

2. Input: [4, 2, 0, 3, 2, 5]
   Expected: 9

3. Input: [0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1]
   Expected: 6
*/
