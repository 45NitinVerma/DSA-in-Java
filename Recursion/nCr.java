public class nCr {
    public static int ncr(int n, int r) {
        // Base cases
        if (r == 0 || r == n) {
            return 1;
        }
        // Recursive case
        return ncr(n - 1, r - 1) + ncr(n - 1, r);
    }
    public static void main(String[] args) {
        int n = 5;
        int r = 4;
        int result = ncr(n, r);
        System.out.println(n + "C" + r + "= " + result);
    }
}
// TC: O(2^n), SC: O(n)