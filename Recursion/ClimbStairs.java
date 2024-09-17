public class ClimbStairs {
    public static int climbstairs(int n){
        if(n<0) return 0;
        if(n==0) return 1;
        return climbstairs(n-1)+climbstairs(n-2);
    }
    public static void main(String[] args) {
        int n=9;
        System.out.println(climbstairs(n));
    }
}
// TC: O(2^n), SC: O(n)