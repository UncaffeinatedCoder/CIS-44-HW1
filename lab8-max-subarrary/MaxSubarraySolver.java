/**
 * MaxSubarraySolver - Two approaches to the Maximum Subarray Sum problem
 * 
 * Problem: Given an array of integers (positive and negative), find the
 * contiguous subarray with the largest possible sum.
 * 
 * This class demonstrates how algorithm design dramatically affects performance:
 * - Brute Force: O(n²) - checks every possible subarray
 * - Kadane's Algorithm: O(n) - single pass dynamic programming
 */
public class MaxSubarraySolver {

    /**
     * ═══════════════════════════════════════════════════════════════════════
     * ALGORITHM 1: BRUTE-FORCE APPROACH
     * ═══════════════════════════════════════════════════════════════════════
     * 
     * Strategy: Check EVERY possible subarray and track the maximum sum.
     * A subarray is defined by its start index i and end index j (where i <= j).
     * 
     * PRIMITIVE OPERATION COUNT ANALYSIS:
     * ──────────────────────────────────────────────────────────────────────
     * 
     * Let n = arr.length
     * 
     * Outer loop: i goes from 0 to n-1              → n iterations
     * Inner loop: j goes from i to n-1              → varies based on i
     *   - When i=0: j runs n times
     *   - When i=1: j runs n-1 times
     *   - When i=2: j runs n-2 times
     *   - ...
     *   - When i=n-1: j runs 1 time
     * 
     * Total inner loop iterations = n + (n-1) + (n-2) + ... + 1
     *                             = n(n+1)/2
     *                             = (n² + n) / 2
     * 
     * Operations per inner iteration:
     *   - 1 addition (currentSum += arr[j])
     *   - 1 comparison (currentSum > maxSum)
     *   - 0 or 1 assignment (maxSum = currentSum)
     *   ≈ 3 operations (constant)
     * 
     * Total operations = 3 × n(n+1)/2 = (3n² + 3n) / 2
     * 
     * By Big-O rules:
     *   - Drop constants: (n² + n) / 2
     *   - Drop lower-order terms: n²
     *   
     * THEREFORE: Time Complexity = O(n²)
     * 
     * ──────────────────────────────────────────────────────────────────────
     * 
     * @param arr The input array of integers (may include negatives)
     * @return The maximum subarray sum
     */
    public static int bruteForceMaxSum(int[] arr) {
        // Edge case: empty array
        if (arr == null || arr.length == 0) {
            return 0;
        }
        
        int n = arr.length;
        int maxSum = Integer.MIN_VALUE;  // 1 operation (initialization)
        
        // Outer loop: try every starting position
        // Runs n times: i = 0, 1, 2, ..., n-1
        for (int i = 0; i < n; i++) {                    // n iterations
            
            int currentSum = 0;  // Reset sum for new starting position
            
            // Inner loop: try every ending position from i onwards
            // When i=0: runs n times, when i=1: runs n-1 times, etc.
            // Total across all i values: n + (n-1) + ... + 1 = n(n+1)/2
            for (int j = i; j < n; j++) {                // n(n+1)/2 total iterations
                
                // --- PRIMITIVE OPERATIONS (constant per iteration) ---
                currentSum += arr[j];  // 1 addition, 1 array access
                
                if (currentSum > maxSum) {  // 1 comparison
                    maxSum = currentSum;     // 1 assignment (conditional)
                }
                // --- END PRIMITIVE OPERATIONS ---
            }
        }
        
        return maxSum;  // 1 operation (return)
        
        /*
         * TOTAL PRIMITIVE OPERATIONS:
         * 
         * Initialization:        2 ops
         * Outer loop overhead:   n comparisons, n increments = 2n ops
         * Inner loop overhead:   n(n+1)/2 comparisons + increments = n(n+1) ops
         * Core operations:       ~3 × n(n+1)/2 = 3n(n+1)/2 ops
         * Return:                1 op
         * 
         * Total ≈ 2 + 2n + n(n+1) + 3n(n+1)/2 + 1
         *       ≈ 5n²/2 + 7n/2 + 3
         *       = O(n²)
         */
    }
    
    /**
     * Alternative brute force that also tracks subarray indices.
     * Same O(n²) complexity but returns more information.
     */
    public static int[] bruteForceMaxSumWithIndices(int[] arr) {
        if (arr == null || arr.length == 0) {
            return new int[]{0, -1, -1};  // sum, start, end
        }
        
        int n = arr.length;
        int maxSum = Integer.MIN_VALUE;
        int startIndex = 0;
        int endIndex = 0;
        
        for (int i = 0; i < n; i++) {
            int currentSum = 0;
            for (int j = i; j < n; j++) {
                currentSum += arr[j];
                if (currentSum > maxSum) {
                    maxSum = currentSum;
                    startIndex = i;
                    endIndex = j;
                }
            }
        }
        
        return new int[]{maxSum, startIndex, endIndex};
    }

    /**
     * ═══════════════════════════════════════════════════════════════════════
     * ALGORITHM 2: KADANE'S ALGORITHM (Dynamic Programming)
     * ═══════════════════════════════════════════════════════════════════════
     * 
     * Strategy: Make ONE pass through the array, maintaining:
     *   - currentMax: best sum ENDING at current position
     *   - globalMax: best sum found ANYWHERE so far
     * 
     * Key Insight: At each position, we have two choices:
     *   1. Extend the previous subarray by including current element
     *   2. Start a new subarray from current element
     *   
     * We pick whichever gives a larger sum!
     * 
     * currentMax = max(arr[i], currentMax + arr[i])
     * 
     * This works because:
     * - If currentMax + arr[i] >= arr[i], extending is beneficial
     * - If currentMax + arr[i] < arr[i], previous sum was negative, start fresh
     * 
     * PRIMITIVE OPERATION COUNT ANALYSIS:
     * ──────────────────────────────────────────────────────────────────────
     * 
     * Let n = arr.length
     * 
     * Single loop: i goes from 0 to n-1              → n iterations
     * 
     * Operations per iteration:
     *   - 1 addition (currentMax + arr[i])
     *   - 1 comparison (for Math.max of currentMax)
     *   - 1 assignment (currentMax = ...)
     *   - 1 comparison (for Math.max of globalMax)  
     *   - 1 assignment (globalMax = ...)
     *   = 5 operations (constant)
     * 
     * Total operations = 5 × n = 5n
     * 
     * By Big-O rules:
     *   - Drop constants: n
     *   
     * THEREFORE: Time Complexity = O(n)
     * 
     * ──────────────────────────────────────────────────────────────────────
     * 
     * COMPARISON TO BRUTE FORCE:
     * 
     * For n = 100,000:
     *   - Brute Force: ~5,000,000,000 operations (n²)
     *   - Kadane's:    ~500,000 operations (n)
     *   - Ratio: 10,000x fewer operations!
     * 
     * ──────────────────────────────────────────────────────────────────────
     * 
     * @param arr The input array of integers (may include negatives)
     * @return The maximum subarray sum
     */
    public static int kadanesAlgorithmMaxSum(int[] arr) {
        // Edge case: empty array
        if (arr == null || arr.length == 0) {
            return 0;
        }
        
        int n = arr.length;
        
        // currentMax: maximum sum of subarray ENDING at current index
        // globalMax: maximum sum found so far across ALL subarrays
        int currentMax = arr[0];  // 1 operation (initialization)
        int globalMax = arr[0];   // 1 operation (initialization)
        
        // Single pass through array starting from index 1
        // Runs exactly n-1 times
        for (int i = 1; i < n; i++) {               // n-1 iterations
            
            // --- PRIMITIVE OPERATIONS (constant per iteration) ---
            
            // Decision: extend previous subarray OR start new one?
            // If previous sum was negative, better to start fresh
            currentMax = Math.max(arr[i], currentMax + arr[i]);  // 1 add, 1 compare, 1 assign
            
            // Update global maximum if current ending gives better result
            globalMax = Math.max(globalMax, currentMax);         // 1 compare, 1 assign
            
            // --- END PRIMITIVE OPERATIONS: 5 ops per iteration ---
        }
        
        return globalMax;  // 1 operation (return)
        
        /*
         * TOTAL PRIMITIVE OPERATIONS:
         * 
         * Initialization:        2 ops
         * Loop overhead:         (n-1) comparisons + increments = 2(n-1) ops
         * Core operations:       5 × (n-1) = 5n - 5 ops
         * Return:                1 op
         * 
         * Total = 2 + 2(n-1) + 5(n-1) + 1
         *       = 2 + 2n - 2 + 5n - 5 + 1
         *       = 7n - 4
         *       = O(n)
         * 
         * This is LINEAR - massively better than O(n²)!
         */
    }
    
    /**
     * Kadane's Algorithm variant that also tracks subarray indices.
     * Still O(n) complexity but returns more information.
     */
    public static int[] kadanesAlgorithmMaxSumWithIndices(int[] arr) {
        if (arr == null || arr.length == 0) {
            return new int[]{0, -1, -1};  // sum, start, end
        }
        
        int n = arr.length;
        
        int currentMax = arr[0];
        int globalMax = arr[0];
        
        int currentStart = 0;   // Start of current subarray
        int globalStart = 0;    // Start of best subarray
        int globalEnd = 0;      // End of best subarray
        
        for (int i = 1; i < n; i++) {
            // If starting fresh is better, reset start index
            if (arr[i] > currentMax + arr[i]) {
                currentMax = arr[i];
                currentStart = i;
            } else {
                currentMax = currentMax + arr[i];
            }
            
            // Update global max and its indices
            if (currentMax > globalMax) {
                globalMax = currentMax;
                globalStart = currentStart;
                globalEnd = i;
            }
        }
        
        return new int[]{globalMax, globalStart, globalEnd};
    }
    
    /**
     * Utility method to verify both algorithms produce the same result.
     */
    public static boolean verify(int[] arr) {
        int bruteResult = bruteForceMaxSum(arr);
        int kadaneResult = kadanesAlgorithmMaxSum(arr);
        return bruteResult == kadaneResult;
    }
    
    /**
     * Utility method to print a subarray.
     */
    public static void printSubarray(int[] arr, int start, int end) {
        System.out.print("[");
        for (int i = start; i <= end; i++) {
            System.out.print(arr[i]);
            if (i < end) System.out.print(", ");
        }
        System.out.println("]");
    }
    
    /**
     * Demo method showing the algorithms on the classic example.
     */
    public static void demo() {
        int[] example = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        
        System.out.println("═══════════════════════════════════════════════════════════");
        System.out.println("MAXIMUM SUBARRAY SUM - ALGORITHM DEMONSTRATION");
        System.out.println("═══════════════════════════════════════════════════════════");
        System.out.println();
        
        System.out.print("Input array: ");
        printSubarray(example, 0, example.length - 1);
        System.out.println();
        
        // Brute Force with indices
        int[] bruteResult = bruteForceMaxSumWithIndices(example);
        System.out.println("Brute Force Result:");
        System.out.println("  Maximum Sum: " + bruteResult[0]);
        System.out.print("  Subarray: ");
        printSubarray(example, bruteResult[1], bruteResult[2]);
        System.out.println("  Indices: [" + bruteResult[1] + ", " + bruteResult[2] + "]");
        System.out.println();
        
        // Kadane's with indices
        int[] kadaneResult = kadanesAlgorithmMaxSumWithIndices(example);
        System.out.println("Kadane's Algorithm Result:");
        System.out.println("  Maximum Sum: " + kadaneResult[0]);
        System.out.print("  Subarray: ");
        printSubarray(example, kadaneResult[1], kadaneResult[2]);
        System.out.println("  Indices: [" + kadaneResult[1] + ", " + kadaneResult[2] + "]");
        System.out.println();
        
        System.out.println("Both algorithms correctly find the maximum subarray [4, -1, 2, 1]");
        System.out.println("with sum = 6");
    }
}
