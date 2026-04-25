import java.util.*;

public class DataStructures {

    // ==========================================
    // 1. RECURSION
    // ==========================================
    /**
     * Calculate the nth Fibonacci number recursively.
     * sequence: 0, 1, 1, 2, 3, 5, 8, 13...
     * Example: fib(6) -> 8
     */
    public static int recursiveFibonacci(int n) {
        // Base Case 1: fib(0) = 0
        if (n == 0) return 0;

        // Base Case 2: fib(1) = 1
        if (n == 1) return 1;

        // Recursive Step: fib(n) = fib(n-1) + fib(n-2)
        return recursiveFibonacci(n - 1) + recursiveFibonacci(n - 2);
    }

    // ==========================================
    // 2. ANALYSIS OF ALGORITHMS
    // ==========================================
    /**
     * Find and return the SECOND LARGEST value in an array.
     * O(n) single-pass solution: track both max and secondMax simultaneously.
     */
    public static int findSecondMax(int[] arr) {
        if (arr == null || arr.length < 2) {
            throw new IllegalArgumentException("Array must have at least two elements");
        }

        int max = Integer.MIN_VALUE;
        int secondMax = Integer.MIN_VALUE;

        for (int val : arr) {
            if (val > max) {
                // Current max gets demoted to secondMax, val becomes new max
                secondMax = max;
                max = val;
            } else if (val > secondMax && val != max) {
                // val is between secondMax and max
                secondMax = val;
            }
        }

        return secondMax;
    }

    // ==========================================
    // 3. TREES
    // ==========================================
    static class Node {
        int value;
        Node left, right;

        public Node(int value) {
            this.value = value;
            this.left = null;
            this.right = null;
        }
    }

    /**
     * Return the sum of values of ONLY the leaf nodes.
     * A leaf is a node with no children (left and right are both null).
     */
    public static int sumLeafNodes(Node root) {
        // Base Case 1: empty node contributes 0
        if (root == null) return 0;

        // Base Case 2: leaf node - return its value
        if (root.left == null && root.right == null) return root.value;

        // Recursive Step: sum leaves from left and right subtrees
        return sumLeafNodes(root.left) + sumLeafNodes(root.right);
    }

    // ==========================================
    // 4. SEARCH ALGORITHMS
    // ==========================================
    /**
     * Binary Search on a SORTED array.
     * Returns the index of the target if found, otherwise -1.
     */
    public static int binarySearch(int[] arr, int target) {
        int low = 0;
        int high = arr.length - 1;

        while (low <= high) {
            int mid = (low + high) / 2;

            if (arr[mid] == target) {
                return mid;          // Found
            } else if (target < arr[mid]) {
                high = mid - 1;      // Search left half
            } else {
                low = mid + 1;       // Search right half
            }
        }

        return -1;  // Not found
    }

    // ==========================================
    // 5. SORTING ALGORITHMS
    // ==========================================
    /**
     * Selection Sort - ascending order.
     * Each pass finds the minimum in the unsorted portion and swaps it into place.
     */
    public static void selectionSort(int[] arr) {
        int n = arr.length;

        for (int i = 0; i < n - 1; i++) {
            // Find the index of the minimum element in arr[i..n-1]
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }

            // Swap minimum with first element of unsorted portion (one swap per pass)
            int temp = arr[minIndex];
            arr[minIndex] = arr[i];
            arr[i] = temp;
        }
    }

    // ==========================================
    // TEST DRIVER (Do not modify this part)
    // ==========================================
    public static void main(String[] args) {
        System.out.println("=== Coding Advanced Data Structures ===\n");

        // Test 1: Recursion (Fibonacci)
        int fibN = 6;
        int expectedFib = 8;
        int actualFib = recursiveFibonacci(fibN);
        printTestResult("1. Recursion (Fibonacci)", expectedFib, actualFib);

        // Test 2: Analysis (Second Max)
        int[] numbers = {10, 5, 20, 8, 15};
        int expectedSecondMax = 15;
        int actualSecondMax = findSecondMax(numbers);
        printTestResult("2. Analysis (Second Max)", expectedSecondMax, actualSecondMax);

        // Test 3: Trees (Leaf Sum)
        //       1
        //      / \
        //     2   3 (Leaf)
        //    /
        //   4 (Leaf)
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        int expectedLeafSum = 7;  // Nodes 3 and 4 are leaves: 3 + 4 = 7
        int actualLeafSum = sumLeafNodes(root);
        printTestResult("3. Trees (Leaf Sum)", expectedLeafSum, actualLeafSum);

        // Test 4: Search (Binary Search)
        int[] sortedData = {1, 2, 4, 7, 9};
        int target = 7;
        int expectedIndex = 3;
        int actualIndex = binarySearch(sortedData, target);
        printTestResult("4. Search (Binary)", expectedIndex, actualIndex);

        // Test 5: Sorting (Selection Sort)
        int[] sortData = {64, 25, 12, 22, 11};
        String expectedSort = "[11, 12, 22, 25, 64]";
        selectionSort(sortData);
        String actualSort = Arrays.toString(sortData);
        System.out.println("[Test 5] Sorting (Selection Sort)");
        System.out.println(" Expected: " + expectedSort);
        System.out.println(" Actual:   " + actualSort);
        if (expectedSort.equals(actualSort)) {
            System.out.println(" Result: [PASS]");
        } else {
            System.out.println(" Result: [FAIL]");
        }
        System.out.println();
    }

    // Helper to print results
    private static void printTestResult(String testName, int expected, int actual) {
        System.out.println("[Test] " + testName);
        System.out.println(" Expected: " + expected);
        System.out.println(" Actual:   " + actual);
        if (expected == actual) {
            System.out.println(" Result: [PASS]");
        } else {
            System.out.println(" Result: [FAIL]");
        }
        System.out.println();
    }
}
