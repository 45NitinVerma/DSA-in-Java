public class TowerOfHanoi {
    // Recursive method to solve Tower of Hanoi
    public static void solveTowerOfHanoi(int n, char source, char auxiliary, char destination) {
        // Base case: If there's only one disk, move it from source to destination
        if (n == 1) {
            System.out.println("Move disk 1 from rod " + source + " to rod " + destination);
            return;
        }
        
        // Step 1: Move n-1 disks from source to auxiliary
        solveTowerOfHanoi(n - 1, source, destination, auxiliary);
        
        // Step 2: Move the nth disk from source to destination
        System.out.println("Move disk " + n + " from rod " + source + " to rod " + destination);
        
        // Step 3: Move the n-1 disks from auxiliary to destination
        solveTowerOfHanoi(n - 1, auxiliary, source, destination);
    }
    
    public static void main(String[] args) {
        int n = 3; // Number of disks
        solveTowerOfHanoi(n, 'A', 'B', 'C');
    }
}
/*
Time Complexity Analysis:
- For `n` disks, each disk is moved once, but the recursive calls duplicate work:
  T(n) = 2 * T(n-1) + O(1)
- Solving this recurrence relation gives T(n) = O(2^n).
- Therefore, the time complexity is O(2^n), which grows exponentially with the number of disks.

Space Complexity Analysis:
- The space complexity is due to the depth of the recursion stack.
- For `n` recursive calls, the maximum depth of the stack is `n`.
- Thus, the space complexity is O(n).
*/