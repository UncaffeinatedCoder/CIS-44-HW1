import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

// --- Heap Implementation ---
class HeapPriorityQueue<K extends Comparable<K>> {
    private ArrayList<K> heap = new ArrayList<>();

    // Index helpers for navigating the array-based tree
    protected int parent(int j) { return (j - 1) / 2; }
    protected int left(int j)   { return 2 * j + 1; }
    protected int right(int j)  { return 2 * j + 2; }

    public int size()      { return heap.size(); }
    public boolean isEmpty() { return heap.isEmpty(); }

    private void swap(int i, int j) {
        K temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }

    public void insert(K key) {
        heap.add(key);              // Add to next available position (end of array)
        upheap(heap.size() - 1);   // Restore heap-order property upward
    }

    public K removeMin() {
        if (isEmpty()) return null;
        K answer = heap.get(0);                     // Min is always the root

        K last = heap.remove(heap.size() - 1);      // Remove last element
        if (!heap.isEmpty()) {
            heap.set(0, last);                      // Move last to root
            downheap(0);                            // Restore heap-order property downward
        }
        return answer;
    }

    public K min() {
        return isEmpty() ? null : heap.get(0);
    }

    /**
     * Upheap: restore heap-order after insert.
     * Bubble element at index j UP the tree by swapping with parent
     * until parent <= child or we reach the root.
     * Time: O(log n) - at most tree height swaps
     */
    private void upheap(int j) {
        while (j > 0) {
            int p = parent(j);
            // If parent <= child, heap-order is satisfied - stop
            if (heap.get(p).compareTo(heap.get(j)) <= 0) break;
            // Parent is larger than child - swap and continue up
            swap(p, j);
            j = p;
        }
    }

    /**
     * Downheap: restore heap-order after removeMin.
     * Push element at index j DOWN the tree by swapping with the
     * smaller child until both children are >= parent or we hit a leaf.
     * Time: O(log n) - at most tree height swaps
     */
    private void downheap(int j) {
        while (left(j) < heap.size()) {  // While j has at least a left child
            int leftIdx = left(j);
            int smallerChild = leftIdx;  // Assume left is smaller to start

            // Check if right child exists and is smaller than left
            int rightIdx = right(j);
            if (rightIdx < heap.size() &&
                heap.get(rightIdx).compareTo(heap.get(leftIdx)) < 0) {
                smallerChild = rightIdx;
            }

            // If parent <= smaller child, heap-order is satisfied - stop
            if (heap.get(j).compareTo(heap.get(smallerChild)) <= 0) break;

            // Parent is larger than smaller child - swap and continue down
            swap(j, smallerChild);
            j = smallerChild;
        }
    }
}

// --- HeapSort Driver ---
public class HeapSorter {

    /**
     * Heap-Sort using a Min-Heap Priority Queue.
     * Phase 1 (Construction): Insert all n elements - O(n log n)
     * Phase 2 (Sorting): RemoveMin n times - O(n log n)
     * Total: O(n log n)
     */
    public static void heapSort(Integer[] arr) {
        HeapPriorityQueue<Integer> pq = new HeapPriorityQueue<>();

        // Phase 1: Insert all elements into the min-heap
        for (Integer x : arr) {
            pq.insert(x);
        }

        // Phase 2: Extract in sorted order (removeMin gives smallest each time)
        for (int i = 0; i < arr.length; i++) {
            arr[i] = pq.removeMin();
        }
    }

    public static void main(String[] args) {
        // Test 1: Random array
        Integer[] data = new Integer[10];
        Random rand = new Random(42);
        for (int i = 0; i < data.length; i++) data[i] = rand.nextInt(100);

        System.out.println("--- Test 1: Random Array ---");
        System.out.println("Before: " + Arrays.toString(data));
        heapSort(data);
        System.out.println("After:  " + Arrays.toString(data));

        // Test 2: Already sorted
        Integer[] sorted = {1, 3, 5, 7, 9};
        System.out.println("\n--- Test 2: Already Sorted ---");
        System.out.println("Before: " + Arrays.toString(sorted));
        heapSort(sorted);
        System.out.println("After:  " + Arrays.toString(sorted));

        // Test 3: Reverse sorted (worst case for many sorts)
        Integer[] reversed = {9, 7, 5, 3, 1};
        System.out.println("\n--- Test 3: Reverse Sorted ---");
        System.out.println("Before: " + Arrays.toString(reversed));
        heapSort(reversed);
        System.out.println("After:  " + Arrays.toString(reversed));

        // Test 4: Duplicates
        Integer[] dupes = {4, 1, 4, 2, 4, 1};
        System.out.println("\n--- Test 4: Duplicates ---");
        System.out.println("Before: " + Arrays.toString(dupes));
        heapSort(dupes);
        System.out.println("After:  " + Arrays.toString(dupes));
    }
}
