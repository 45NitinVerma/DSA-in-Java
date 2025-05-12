package ApnaCollege.Greedy;
/* 
Chocola Problem
# Problem: We are given a bar of chocolate composed of m × n square pieces. One should break the chocolate into single squares. Parts of the chocolate may be broken along the vertical and horizontal lines as indicated by the broken lines in the picture.

A single break of a part of the chocolate along a chosen vertical or horizontal line divides that part into two smaller ones. Each break of a part of the chocolate is charged a cost expressed by a positive integer. This cost does not depend on the size of the part that is being broken but only depends on the line the break goes along. Let us denote the costs of breaking along consecutive vertical lines with x1, x2 ... xm-1 and along horizontal lines with y1, y2 ... yn-1.

The cost of breaking the whole bar into single squares is the sum of the successive breaks. One should compute the minimal cost of breaking the whole chocolate into single squares.
 */
import java.util.*;
public class Chocola {
    public static void main(String args[]) {
        int n = 4, m = 6;
        Integer costVer[] = {2, 1, 3, 1, 4}; //m-1
        Integer costHor[] = {4, 1, 2}; //n-1

        int hp = 1, vp = 1; //Horizontal & Vertical Pieces
        Arrays.sort(costVer, Collections.reverseOrder());
        Arrays.sort(costHor, Collections.reverseOrder());


        int finalCost = 0;
        int h=0, v=0;
        while(h<n-1 && v<m-1) {
            if(costHor[h] < costVer[v]) { //vertical cut
                finalCost += costVer[v] * vp;
                hp++;
                v++;
            } else { //horizontal cut
                finalCost += costHor[h] * hp;
                vp++;
                h++;
            }
        }

        while(v<m-1) {
            finalCost += costVer[v] * vp;
            hp++;
            v++;
        }

        while(h<n-1) {
            finalCost += costHor[h] * hp;
            vp++;
            h++;
        }

        System.out.println("min cost of cutting = " + finalCost);

    }
}