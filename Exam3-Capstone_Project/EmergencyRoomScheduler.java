import java.util.ArrayList;
public class EmergencyRoomScheduler {

    private ArrayList<Patient> heap;

    public EmergencyRoomScheduler() {
        this.heap = new ArrayList<>();
    }

    // ─── Index Navigation ──────────────────────────────────────────────────

    private int parent(int j) { return (j - 1) / 2; }
    private int left(int j)   { return 2 * j + 1; }
    private int right(int j)  { return 2 * j + 2; }

    private boolean hasLeft(int j)  { return left(j) < heap.size(); }
    private boolean hasRight(int j) { return right(j) < heap.size(); }

    // ─── Core Heap Operations ──────────────────────────────────────────────

    /**
     * Admit a patient into the ER queue.
     * Adds to the end of the heap, then bubbles UP to restore heap-order.
     * Time: O(log n)
     */
    public void admit(Patient patient) {
        if (patient == null) {
            throw new IllegalArgumentException("Cannot admit a null patient.");
        }
        heap.add(patient);
        upheap(heap.size() - 1);
        System.out.println("Admitted: " + patient);
    }

    public Patient treatNext() {
        if (isEmpty()) {
            System.out.println("No patients in queue.");
            return null;
        }
        Patient critical = heap.get(0);
        Patient last = heap.remove(heap.size() - 1);

        if (!heap.isEmpty()) {
            heap.set(0, last);  // Move last patient to root
            downheap(0);        // Restore heap-order downward
        }

        System.out.println("Treating: " + critical);
        return critical;
    }

    /**
     * Peek at the highest-priority patient without removing them.
     * Time: O(1)
     */
    public Patient currentCritical() {
        return isEmpty() ? null : heap.get(0);
    }

    private void upheap(int j) {
        while (j > 0) {
            int p = parent(j);
            // Max-Heap: if child has HIGHER priority than parent, swap
            if (heap.get(j).compareTo(heap.get(p)) > 0) {
                swap(j, p);
                j = p;
            } else {
                break;  // Heap-order satisfied
            }
        }
    }

    private void downheap(int j) {
        while (hasLeft(j)) {
            int largerChild = left(j);

            // Check if right child exists and has higher priority
            if (hasRight(j) && heap.get(right(j)).compareTo(heap.get(left(j))) > 0) {
                largerChild = right(j);
            }

            // If parent >= larger child, heap-order satisfied
            if (heap.get(j).compareTo(heap.get(largerChild)) >= 0) {
                break;
            }

            // Parent is smaller — swap with larger child and continue down
            swap(j, largerChild);
            j = largerChild;
        }
    }

    // ─── Utility ───────────────────────────────────────────────────────────

    private void swap(int i, int j) {
        Patient temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }

    public boolean isEmpty() { return heap.isEmpty(); }
    public int size()        { return heap.size(); }

    /**
     * Display the current queue state (heap order, not sorted).
     */
    public void printQueue() {
        if (isEmpty()) {
            System.out.println("Queue is empty.");
            return;
        }
        System.out.println("Current queue (" + size() + " patients):");
        for (int i = 0; i < heap.size(); i++) {
            System.out.println("  [" + i + "] " + heap.get(i));
        }
    }
}
