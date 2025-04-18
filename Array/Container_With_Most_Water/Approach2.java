package Array.Container_With_Most_Water;

public class Approach2 {
    // Approach 2: Using 2 pointers
    public static int maxVol(int[] height){
        int maxWater = 0;
        int left = 0;
        int right = height.length - 1;
        while(left<right){
            int ht = Math.min(height[left], height[right]);
            int width = right - left;
            int currWater = ht * width;
            maxWater = Math.max(maxWater, currWater);
            if(height[left] < height[right]){
                left++;
            } else {
                right--;
            }
        }
        return maxWater;
    }
    
    public static void main(String[] args) {
        int[] height = {1,8,6,2,5,4,8,3,7};
        System.out.println(maxVol(height));
    }
}
