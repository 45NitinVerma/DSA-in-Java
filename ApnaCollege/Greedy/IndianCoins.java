package ApnaCollege.Greedy;
import java.util.*;
/* 
Coin Change Problem
#Problem: You are given an integer array coins representing coins of different denominations and an integer amount representing a total amount of money.
Return the fewest number of coins that you need to make up that amount. If that amount of money cannot be made up by any combination of the coins, return -1.
You may assume that you have an infinite number of each kind of coin.
 */
public class IndianCoins {
    public static void main(String args[]) {
        Integer coins[] = {1, 2, 5, 10, 20, 50, 100, 500, 2000};
        int val = 121;
        Arrays.sort(coins, Collections.reverseOrder());

        ArrayList<Integer> ans = new ArrayList<>();
        int count = 0;

        for(int i=0; i<coins.length; i++) {
            if(val >= coins[i]) {
                while(val >= coins[i]) {
                    ans.add(coins[i]);
                    count++;
                    val -= coins[i];
                }
            }
        }

        System.out.println(count);
    }
}