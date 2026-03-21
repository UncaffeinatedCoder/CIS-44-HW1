/**
 * SortingAlgorithms - Implementation of three sorting algorithms
 * for empirical analysis of time complexity.
 * 
 * Algorithms implemented:
 * 1. Selection Sort - O(n²) in all cases
 * 2. Insertion Sort - O(n²) average/worst, O(n) best case
 * 3. Merge Sort - O(n log n) in all cases
 */
public class SortingAlgorithms {

    /**
     * Implements the Selection Sort algorithm.
     * 
     * Algorithm:
     * - Find the minimum element in the unsorted portion
     * - Swap it with the first element of the unsorted portion
     * - Move the boundary of sorted/unsorted one element right
     * 
     * Theoretical Complexity: O(n²) for ALL cases
     * - Always performs n(n-1)/2 comparisons regardless of input
     * 
     * @param arr The array to sort in place
     */
    public static void selectionSort(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return;
        }
        
        int n = arr.length;
        
        // Move boundary of unsorted subarray one by one
        for (int i = 0; i < n - 1; i++) {
            // Find the minimum element in unsorted portion
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            
            // Swap the found minimum with the first element of unsorted portion
            if (minIndex != i) {
                int temp = arr[i];
                arr[i] = arr[minIndex];
                arr[minIndex] = temp;
            }
        }
    }

    /**
     * Implements the Insertion Sort algorithm.
     * 
     * Algorithm:
     * - Start from the second element
     * - Compare current element with elements before it
     * - Shift larger elements right and insert current in correct position
     * 
     * Theoretical Complexity:
     * - Best Case: O(n) - when array is already sorted (no shifts needed)
     * - Average Case: O(n²) - random data
     * - Worst Case: O(n²) - when array is reverse sorted (maximum shifts)
     * 
     * @param arr The array to sort in place
     */
    public static void insertionSort(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return;
        }
        
        int n = arr.length;
        
        // Start from second element (index 1)
        for (int i = 1; i < n; i++) {
            int key = arr[i];  // Element to be inserted
            int j = i - 1;
            
            // Shift elements of arr[0..i-1] that are greater than key
            // to one position ahead of their current position
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            
            // Insert key at the correct position
            arr[j + 1] = key;
        }
    }

    /**
     * Implements the Merge Sort algorithm. Public-facing method.
     * 
     * Algorithm:
     * - Divide the array into two halves
     * - Recursively sort each half
     * - Merge the sorted halves
     * 
     * Theoretical Complexity: O(n log n) for ALL cases
     * - Always divides into log n levels
     * - Each level does O(n) work for merging
     * 
     * Space Complexity: O(n) for the temporary array
     * 
     * @param arr The array to sort in place
     */
    public static void mergeSort(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return; // Already sorted
        }
        int[] temp = new int[arr.length];
        mergeSortRecursive(arr, temp, 0, arr.length - 1);
    }

    /**
     * Recursive helper method for merge sort.
     * Divides the array and recursively sorts each half.
     * 
     * @param arr The array being sorted
     * @param temp Temporary array for merging
     * @param left Left index of the subarray
     * @param right Right index of the subarray
     */
    private static void mergeSortRecursive(int[] arr, int[] temp, int left, int right) {
        if (left < right) {
            // Find the middle point to divide the array
            int mid = left + (right - left) / 2;  // Avoids overflow
            
            // Recursively sort first and second halves
            mergeSortRecursive(arr, temp, left, mid);
            mergeSortRecursive(arr, temp, mid + 1, right);
            
            // Merge the sorted halves
            merge(arr, temp, left, mid, right);
        }
    }

    /**
     * Merges two sorted subarrays into one sorted array.
     * 
     * @param arr The main array containing both subarrays
     * @param temp Temporary array to hold merged result
     * @param left Starting index of left subarray
     * @param mid Ending index of left subarray (mid+1 starts right subarray)
     * @param right Ending index of right subarray
     */
    private static void merge(int[] arr, int[] temp, int left, int mid, int right) {
        // Copy both halves into temp array
        for (int i = left; i <= right; i++) {
            temp[i] = arr[i];
        }
        
        int i = left;      // Initial index of left subarray
        int j = mid + 1;   // Initial index of right subarray
        int k = left;      // Initial index of merged subarray
        
        // Merge the temp arrays back into arr[left..right]
        while (i <= mid && j <= right) {
            if (temp[i] <= temp[j]) {
                arr[k] = temp[i];
                i++;
            } else {
                arr[k] = temp[j];
                j++;
            }
            k++;
        }
        
        // Copy remaining elements of left subarray (if any)
        while (i <= mid) {
            arr[k] = temp[i];
            i++;
            k++;
        }
        
        // Note: Remaining elements of right subarray are already in place
    }
    
    /**
     * Utility method to verify if an array is sorted.
     * Used for testing correctness of sorting algorithms.
     * 
     * @param arr The array to check
     * @return true if sorted in ascending order, false otherwise
     */
    public static boolean isSorted(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return true;
        }
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] > arr[i + 1]) {
                return false;
            }
        }
        return true;
    }
}