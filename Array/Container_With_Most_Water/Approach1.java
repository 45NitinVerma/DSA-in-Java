package Array.Container_With_Most_Water;

public class Approach1 {
    // Approach 1: Using 2 nested loops
    public static int maxVol(int[] height){
        int maxWater = 0;
        for (int i = 0; i < height.length; i++) {
            for (int j = i+1; j < height.length; j++) {
                int minHeight = Math.min(height[i], height[j]);
                int width = j - i;
                int water = minHeight * width;
                maxWater = Math.max(maxWater, water);
            }
        }
        return maxWater;
    }
    public static void main(String[] args) {
        int[] height = {1,8,6,2,5,4,8,3,7};
        System.out.println(maxVol(height));
    }
}
